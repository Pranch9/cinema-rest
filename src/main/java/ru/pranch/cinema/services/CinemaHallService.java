package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.CinemaHallDao;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dto.CinemaHallDto;
import ru.pranch.cinema.mapper.CinemaHallMapper;
import ru.pranch.cinema.model.CinemaHall;
import ru.pranch.cinema.model.Seat;

@Service
public class CinemaHallService {
  private final CinemaHallDao cinemaHallDao;
  private final SeatDao seatDao;

  @Autowired
  public CinemaHallService(CinemaHallDao cinemaHallDao, SeatDao seatDao) {
    this.cinemaHallDao = cinemaHallDao;
    this.seatDao = seatDao;
  }

  /**
   * Метод добавляет кинозалы к заданному кинотеатру
   *
   * @param cinemaHallsDto кинозалы для добавления к кинотеатру.
   * @return созданные кинозалы.
   */
  public List<CinemaHall> addCinemaHallsToCinema(List<CinemaHallDto> cinemaHallsDto) throws Exception {
    if (cinemaHallsDto.isEmpty())
      throw new Exception();
    List<CinemaHall> cinemaHalls = cinemaHallDao.saveAll(cinemaHallsDto
        .stream()
        .map(CinemaHallMapper::mapCinemaHall).toList());

    cinemaHalls.forEach(this::addSeatsToCinemaHall);

    return cinemaHalls;
  }

  public List<CinemaHall> getCinemaHalls() {
    return cinemaHallDao.findAll();
  }

  public Optional<CinemaHall> getCinemaHallById(UUID id) {
    return cinemaHallDao.findById(id);
  }

  public Optional<CinemaHall> editCinemaHall(UUID id, CinemaHallDto cinemaHall) {
    return cinemaHallDao.update(id, CinemaHallMapper.mapCinemaHall(cinemaHall));
  }

  public List<CinemaHall> getCinemaHallsByCinemaId(UUID cinemaId) {
    return cinemaHallDao.findAllHallsFromCinema(cinemaId);
  }

  private void addSeatsToCinemaHall(CinemaHall cinemaHall) {
    for (int row = 1; row <= cinemaHall.getRowsNumber(); row++) {
      for (int place = 1; place <= cinemaHall.getPlaceNumber(); place++) {
        Seat seat = new Seat();
        seat.setCinemaHallId(cinemaHall.getId());
        seat.setRowNumber(row);
        seat.setPlace(place);
        seatDao.save(seat);
      }
    }
  }
}
