package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.AddressDao;
import ru.pranch.cinema.dao.CinemaDao;
import ru.pranch.cinema.dao.CinemaHallDao;
import ru.pranch.cinema.dao.CinemaInfoDao;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.dto.CreateAddressDto;
import ru.pranch.cinema.dto.cinema.CinemaInfoDto;
import ru.pranch.cinema.dto.cinema.CreateCinemaDto;
import ru.pranch.cinema.dto.cinema.ResponseCreateCinemaDto;
import ru.pranch.cinema.dto.cinema.UpdateCinemaDto;
import ru.pranch.cinema.dto.cinema_hall.CreateCinemaHallDto;
import ru.pranch.cinema.dto.cinema_hall.GetCinemaHallDto;
import ru.pranch.cinema.dto.seat.GetSeatDto;
import ru.pranch.cinema.mapper.AddressMapper;
import ru.pranch.cinema.mapper.CinemaMapper;
import ru.pranch.cinema.model.Address;
import ru.pranch.cinema.model.Cinema;
import ru.pranch.cinema.model.CinemaHall;
import ru.pranch.cinema.model.Seat;
import ru.pranch.cinema.model.Session;

@Service
public class CinemaService {
  private final AddressDao addressDao;
  private final CinemaDao cinemaDao;
  private final CinemaInfoDao cinemaInfoDao;
  private final CinemaHallDao cinemaHallDao;
  private final SeatDao seatDao;
  private final SessionDao sessionDao;

  @Autowired
  public CinemaService(AddressDao addressDao, CinemaDao cinemaDao, CinemaInfoDao cinemaInfoDao, CinemaHallDao cinemaHallDao, SeatDao seatDao, SessionDao sessionDao) {
    this.addressDao = addressDao;
    this.cinemaDao = cinemaDao;
    this.cinemaInfoDao = cinemaInfoDao;
    this.cinemaHallDao = cinemaHallDao;
    this.seatDao = seatDao;
    this.sessionDao = sessionDao;
  }

  public List<CinemaInfoDto> getCinemas(String cinemaName, String city) {
    return cinemaInfoDao.findAll(cinemaName, city);
  }

  public List<GetCinemaHallDto> getCinemaHallsByCinemaId(UUID cinemaId) {
    return cinemaHallDao.findAllHallsFromCinema(cinemaId);
  }

  public Optional<CinemaInfoDto> getCinemaByName(String name) {
    return cinemaInfoDao.findByName(name);
  }

  public ResponseCreateCinemaDto addCinema(CreateCinemaDto cinema) throws Exception {
    if (cinemaDao.findByName(cinema.getCinemaName()).isPresent()) {
      throw new Exception("Cinema with title = {" + cinema.getCinemaName() + "} already exist!");
    }
    CreateAddressDto createAddressDto = cinema.getCreateAddressDto();
    Address addressFromDb = addressDao
      .findAddress(createAddressDto.getStreet(), createAddressDto.getHouseNumber(), createAddressDto.getCity(), createAddressDto.getZipCode())
      .orElseGet(() -> addressDao
        .save(AddressMapper.mapAddress(createAddressDto)));

    Cinema mapCinema = CinemaMapper.mapCreateCinema(cinema);
    mapCinema.setAddressId(addressFromDb.getId());

    Cinema cinemaFromDb = cinemaDao.save(mapCinema);
    List<GetCinemaHallDto> getCinemaHallDtos = addCinemaHallsToCinema(cinemaFromDb.getId(), cinema.getCreateCinemaHallDtos())
      .stream()
      .map(CinemaMapper::mapGetCinemaHall)
      .toList();
    return CinemaMapper.mapResponseCreateCinema(cinemaFromDb, getCinemaHallDtos, addressFromDb);
  }

  public CreateCinemaDto editCinema(UUID id, UpdateCinemaDto updateCinemaDto) throws Exception {
    Optional<Cinema> cinemaFromDb = cinemaDao.findById(id);
    if (cinemaFromDb.isEmpty())
      throw new Exception("Cinema with id= " + id + " not found");

    CreateAddressDto createAddressDto = updateCinemaDto.getCreateAddressDto();
    Address addressFromDb = addressDao.update(cinemaFromDb.get().getAddressId(), AddressMapper.mapAddress(createAddressDto))
      .orElseGet(() -> addressDao
        .save(AddressMapper.mapAddress(createAddressDto)));

    List<CreateCinemaHallDto> createCinemaHallDtos = updateCinemaHallsToCinema(id, updateCinemaDto.getCreateCinemaHallDtos());

    Cinema cinema = CinemaMapper.mapUpdateCinema(updateCinemaDto);
    cinema.setAddressId(addressFromDb.getId());
    Optional<Cinema> updatedCinemaOpt = cinemaDao.update(id, cinema);

    return CinemaMapper.mapResponseUpdateCinema(updatedCinemaOpt.get(), createCinemaHallDtos, createAddressDto);
  }

  public int deleteCinema(UUID cinemaId) {
    List<UUID> cinemaHallsIds = cinemaHallDao.findAllHallsFromCinema(cinemaId)
      .stream()
      .map(GetCinemaHallDto::getId)
      .toList();
    cinemaHallsIds
      .forEach(ch -> {
        List<UUID> seatIds = seatDao.findSeatsByCinemaHall(ch)
          .stream()
          .map(GetSeatDto::getId)
          .toList();
        if (!seatIds.isEmpty()) {
          seatDao.deleteAllById(seatIds);
        }
        List<UUID> sessionIds = sessionDao.findAllByCinemaHallId(ch)
          .stream()
          .map(Session::getId)
          .toList();
        if (!sessionIds.isEmpty()) {
          sessionDao.deleteAllById(sessionIds);
        }
      });

    if (!cinemaHallsIds.isEmpty())
      cinemaHallDao.deleteAllById(cinemaHallsIds);

    return cinemaDao.deleteById(cinemaId);
  }

  public List<CinemaInfoDto> getCinemasByCity(String city) {
    return cinemaInfoDao.findByCity(city);
  }

  public List<CinemaHall> addCinemaHallsToCinema(UUID cinemaId, List<CreateCinemaHallDto> cinemaHallsDto) throws Exception {
    if (cinemaHallsDto.isEmpty())
      throw new Exception("Cannot create cinema without cinemaHalls");

    boolean isNotUnique = cinemaHallsDto
      .stream()
      .anyMatch(ch -> cinemaHallDao.findByName(ch.getHallName()).isPresent());
    if (isNotUnique)
      throw new Exception("CinemaHall name not unique");

    List<CinemaHall> cinemaHalls = cinemaHallDao.saveAll(cinemaHallsDto
      .stream()
      .map(ch -> {
        CinemaHall cinemaHall = CinemaMapper.mapCreateCinemaHall(ch);
        cinemaHall.setCinemaId(cinemaId);
        return cinemaHall;
      }).toList());

    cinemaHalls.forEach(cinemaHall -> {
      try {
        addSeatsToCinemaHall(cinemaHall);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    });
    return cinemaHalls;
  }

  public List<CreateCinemaHallDto> updateCinemaHallsToCinema(UUID cinemaId, List<CreateCinemaHallDto> cinemaHallsDto) throws Exception {
    if (cinemaHallsDto.isEmpty())
      throw new Exception();

    return cinemaHallsDto.stream().map(ch -> {
      CinemaHall cinemaHallFromDb = cinemaHallDao.findByName(ch.getHallName())
        .orElseGet(() -> {
          CinemaHall cinemaHall = CinemaMapper.mapCreateCinemaHall(ch);
          cinemaHall.setCinemaId(cinemaId);
          return cinemaHallDao.save(cinemaHall);
        });
      cinemaHallFromDb.setHallName(ch.getHallName());
      cinemaHallFromDb.setPlacesNumber(ch.getPlacesNumber());
      cinemaHallFromDb.setRowsNumber(ch.getRowsNumber());

      Optional<CinemaHall> cinemaHallOptional = cinemaHallDao.update(cinemaHallFromDb.getId(), cinemaHallFromDb);
      cinemaHallOptional.ifPresent(chu -> {
        if (chu.getRowsNumber() != ch.getRowsNumber() || chu.getPlacesNumber() == ch.getPlacesNumber()) {
          try {
            addSeatsToCinemaHall(chu);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
      });
      return cinemaHallOptional.map(CinemaMapper::mapCreateCinemaHall).orElseGet(null);
    }).toList();
  }


  public Optional<CinemaInfoDto> getCinema(UUID id) {
    return cinemaInfoDao.findById(id);
  }

  public List<GetSeatDto> getSeatsByCinemaHallId(UUID cinemaHallId) {
    return seatDao.findSeatsByCinemaHall(cinemaHallId);
  }

  private void addSeatsToCinemaHall(CinemaHall cinemaHall) throws Exception {
    if (cinemaHall.getPlacesNumber() == 0 || cinemaHall.getRowsNumber() == 0)
      throw new Exception("Cannot create cinema without seats");

    List<GetSeatDto> seatsByCinemaHall = seatDao.findSeatsByCinemaHall(cinemaHall.getId());
    if (!seatsByCinemaHall.isEmpty()) {
      seatDao.deleteAllById(seatDao.findSeatsByCinemaHall(cinemaHall.getId())
        .stream()
        .map(GetSeatDto::getId)
        .toList());
    }

    for (int row = 1; row <= cinemaHall.getRowsNumber(); row++) {
      for (int place = 1; place <= cinemaHall.getPlacesNumber(); place++) {
        Seat seat = new Seat();
        seat.setCinemaHallId(cinemaHall.getId());
        seat.setRowNumber(row);
        seat.setPlace(place);
        seat.setBooked(false);
        seatDao.save(seat);
      }
    }
  }
}
