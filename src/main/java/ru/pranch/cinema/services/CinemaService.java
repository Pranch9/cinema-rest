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
import ru.pranch.cinema.dto.seat.GetSeatDto;
import ru.pranch.cinema.dto.cinema.CinemaInfoDto;
import ru.pranch.cinema.dto.cinema.CreateCinemaDto;
import ru.pranch.cinema.dto.cinema.UpdateCinemaDto;
import ru.pranch.cinema.dto.cinema_hall.CreateCinemaHallDto;
import ru.pranch.cinema.dto.cinema_hall.GetCinemaHallDto;
import ru.pranch.cinema.dto.cinema_hall.UpdateCinemaHallDto;
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

  /**
   * Метод возвращает все имеющиеся кинотеатры из БД.
   *
   * @return список кинотеатров.
   */

  public List<Cinema> getCinemas() {
    return cinemaDao.findAll();
  }

  /**
   * Метод возвращает кинотеатр по его идентификатору.
   *
   * @return кинотеатр.
   */
  public List<GetCinemaHallDto> getCinemaHallsByCinemaId(UUID cinemaId) {
    return cinemaHallDao.findAllHallsFromCinema(cinemaId);
  }

  /**
   * @param name название кинотеатра для поиска.
   * @return кинотеатр с данным именем.
   */
  public Optional<CinemaInfoDto> getCinemaByName(String name) {
    return cinemaInfoDao.findByName(name);
  }

  /**
   * Метод используется для создания/добавления нового кинотетра.
   *
   * @param cinema кинотеатр для создания.
   * @return созданный кинотеатр.
   */
  public Cinema addCinema(CreateCinemaDto cinema) throws Exception {
    if (cinemaDao.findByName(cinema.getCinemaName()).isPresent()) {
      throw new Exception("Movie with title = {" + cinema.getCinemaName() + "} already exist!");
    }
    CreateAddressDto createAddressDto = cinema.getCreateAddressDto();
    Address addressFromDb = addressDao
        .findAddress(createAddressDto.getStreet(), createAddressDto.getHouseNumber(), createAddressDto.getCity(), createAddressDto.getZipCode())
        .orElseGet(() -> addressDao
            .save(AddressMapper.mapAddress(createAddressDto)));

    Cinema mapCinema = CinemaMapper.mapCinema(cinema);
    mapCinema.setAddressId(addressFromDb.getId());

    Cinema cinemaFromDb = cinemaDao.save(mapCinema);

    addCinemaHallsToCinema(cinemaFromDb.getId(), cinema.getCreateCinemaHallDtos());

    return cinemaFromDb;
  }

  /**
   * Метод обновляет данные по кинотеатру
   *
   * @param id              кинотеатр который будет обновлен.
   * @param updateCinemaDto обновленные данные кинотеатра.
   * @return обновленный кинотеатр.
   */
  public Optional<Cinema> editCinema(UUID id, UpdateCinemaDto updateCinemaDto) throws Exception {
    if (cinemaDao.findByName(updateCinemaDto.getCinemaName()).isPresent()) {
      throw new Exception("Movie with title = {" + updateCinemaDto.getCinemaName() + "} already exist!");
    }

    Optional<Cinema> cinemaFromDb = cinemaDao.findById(id);

    CreateAddressDto createAddressDto = updateCinemaDto.getCreateAddressDto();
    Address addressFromDb = addressDao.update(cinemaFromDb.get().getAddressId(), AddressMapper.mapAddress(createAddressDto))
        .orElseGet(() -> addressDao
            .save(AddressMapper.mapAddress(createAddressDto)));

    updateCinemaHallsToCinema(id, updateCinemaDto.getUpdateCinemaHallDtos());
    Cinema cinema = CinemaMapper.mapCinema(updateCinemaDto);
    cinema.setAddressId(addressFromDb.getId());

    return cinemaDao.update(id, cinema);
  }

  /**
   * Метод удаляет кинотеатр со всеми кинозалами и местами.
   *
   * @param cinemaId кинотеатр для удаления.
   * @return количество удалений.
   */

  public int deleteCinema(UUID cinemaId) {
    List<UUID> cinemaHallsIds = cinemaHallDao.findAllHallsFromCinema(cinemaId)
        .stream()
        .map(GetCinemaHallDto::getId)
        .toList();
    cinemaHallsIds
        .forEach(ch -> {
          seatDao.deleteAllById(seatDao.findSeatsByCinemaHall(ch)
              .stream()
              .map(GetSeatDto::getId)
              .toList());
          sessionDao.deleteAllById(sessionDao.findAllByCinemaHallId(ch)
              .stream()
              .map(Session::getId)
              .toList());
        });

    cinemaHallDao.deleteAllById(cinemaHallsIds);

    return cinemaDao.deleteById(cinemaId);
  }

  public List<CinemaInfoDto> getCinemasByCity(String city) {
    return cinemaInfoDao.findByCity(city);
  }

  public void addCinemaHallsToCinema(UUID cinemaId, List<CreateCinemaHallDto> cinemaHallsDto) throws Exception {
    if (cinemaHallsDto.isEmpty())
      throw new Exception();

    List<CinemaHall> cinemaHalls = cinemaHallDao.saveAll(cinemaHallsDto
        .stream()
        .map(ch -> {
          CinemaHall cinemaHall = CinemaMapper.mapCinemaHall(ch);
          cinemaHall.setCinemaId(cinemaId);
          return cinemaHall;
        }).toList());

    cinemaHalls.forEach(this::addSeatsToCinemaHall);
  }

  public void updateCinemaHallsToCinema(UUID cinemaId, List<UpdateCinemaHallDto> cinemaHallsDto) throws Exception {
    if (cinemaHallsDto.isEmpty())
      throw new Exception();
    cinemaHallsDto.forEach(ch -> {
      CinemaHall cinemaHallFromDb = cinemaHallDao.findById(ch.getId())
          .orElseGet(() -> {
            CinemaHall cinemaHall = CinemaMapper.mapCinemaHall(ch);
            cinemaHall.setCinemaId(cinemaId);
            return cinemaHallDao.save(cinemaHall);
          });
      cinemaHallFromDb.setHallName(ch.getHallName());
      cinemaHallFromDb.setPlaceNumber(ch.getPlaceNumber());
      cinemaHallFromDb.setRowsNumber(ch.getRowsNumber());

      cinemaHallDao.update(cinemaHallFromDb.getId(), cinemaHallFromDb)
          .ifPresent(chu -> {
            if (chu.getRowsNumber() != ch.getRowsNumber() || chu.getPlaceNumber() == ch.getPlaceNumber()) {
              addSeatsToCinemaHall(chu);
            }
          });
    });
  }


  public Optional<CinemaInfoDto> getCinema(UUID id) {
    return cinemaInfoDao.findById(id);
  }

  public List<GetSeatDto> getSeatsByCinemaHallId(UUID cinemaHallId) {
    return seatDao.findSeatsByCinemaHall(cinemaHallId);
  }

  private void addSeatsToCinemaHall(CinemaHall cinemaHall) {
    List<GetSeatDto> seatsByCinemaHall = seatDao.findSeatsByCinemaHall(cinemaHall.getId());
    if (!seatsByCinemaHall.isEmpty()) {
      seatDao.deleteAllById(seatDao.findSeatsByCinemaHall(cinemaHall.getId())
          .stream()
          .map(GetSeatDto::getId)
          .toList());
    }

    for (int row = 1; row <= cinemaHall.getRowsNumber(); row++) {
      for (int place = 1; place <= cinemaHall.getPlaceNumber(); place++) {
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
