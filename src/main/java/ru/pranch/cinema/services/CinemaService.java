package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.CinemaDao;
import ru.pranch.cinema.dao.CinemaHallDao;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.dto.CinemaDto;
import ru.pranch.cinema.mapper.CinemaMapper;
import ru.pranch.cinema.model.Cinema;
import ru.pranch.cinema.model.CinemaHall;
import ru.pranch.cinema.model.Seat;
import ru.pranch.cinema.model.Session;

@Service
public class CinemaService {
  private final CinemaDao cinemaDao;
  private final CinemaHallDao cinemaHallDao;
  private final SeatDao seatDao;
  private final SessionDao sessionDao;

  @Autowired
  public CinemaService(CinemaDao cinemaDao, CinemaHallDao cinemaHallDao, SeatDao seatDao, SessionDao sessionDao) {
    this.cinemaDao = cinemaDao;
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
  public Optional<Cinema> getCinemaById(UUID id) {
    return cinemaDao.findById(id);
  }

  /**
   * @param name название кинотеатра для поиска.
   * @return кинотеатр с данным именем.
   */
  public Optional<Cinema> getCinemaByName(String name) {
    return cinemaDao.findByName(name);
  }

  /**
   * Метод используется для создания/добавления нового кинотетра.
   *
   * @param cinema кинотеатр для создания.
   * @return созданный кинотеатр.
   */
  public Cinema addCinema(CinemaDto cinema) throws Exception {
    if (cinemaDao.findByName(cinema.getCinemaName()).isPresent()) {
      throw new Exception("Movie with title = {" + cinema.getCinemaName() + "} already exist!");
    }
    return cinemaDao.save(CinemaMapper.mapCinema(cinema));
  }

  /**
   * Метод используется для создания/добавления списка кинотетров.
   *
   * @param cinemas список кинотеатров для создания.
   * @return список созданных кинотеатров.
   */
  public List<Cinema> addCinemas(List<CinemaDto> cinemas) {
    return cinemaDao.saveAll(cinemas
      .stream()
      .map(CinemaMapper::mapCinema)
      .toList());
  }

  /**
   * Метод обновляет данные по кинотеатру
   *
   * @param id     кинотеатр который будет обновлен.
   * @param cinema обновленные данные кинотеатра.
   * @return обновленный кинотеатр.
   */
  public Optional<Cinema> editCinema(UUID id, CinemaDto cinema) throws Exception {
    if (cinemaDao.findByName(cinema.getCinemaName()).isPresent()) {
      throw new Exception("Movie with title = {" + cinema.getCinemaName() + "} already exist!");
    }
    return cinemaDao.update(id, CinemaMapper.mapCinema(cinema));
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
      .map(CinemaHall::getId)
      .toList();
    cinemaHallsIds
      .forEach(ch -> {
        seatDao.deleteAllById(seatDao.findSeatsByCinemaHall(ch)
          .stream()
          .map(Seat::getId)
          .toList());
        sessionDao.deleteAllById(sessionDao.findAllByCinemaRoomId(ch)
          .stream()
          .map(Session::getId)
          .toList());
      });

    cinemaHallDao.deleteAllById(cinemaHallsIds);

    return cinemaDao.deleteById(cinemaId);
  }
}
