package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.CinemaHallDao;
import ru.pranch.cinema.dto.CinemaHallDto;
import ru.pranch.cinema.mapper.CinemaHallMapper;
import ru.pranch.cinema.model.CinemaHall;

@Service
public class CinemaHallService {
  private final CinemaHallDao cinemaHallDao;

  @Autowired
  public CinemaHallService(CinemaHallDao cinemaHallDao) {
    this.cinemaHallDao = cinemaHallDao;
  }

  public List<CinemaHall> getCinemaHalls() {
    return cinemaHallDao.findAll();
  }

  public Optional<CinemaHall> getCinemaHallById(UUID id) {
    return cinemaHallDao.findById(id);
  }

  public CinemaHall addCinemaHall(CinemaHallDto cinemaHall) {
    return cinemaHallDao.save(CinemaHallMapper.mapCinemaHall(cinemaHall));
  }

  public List<CinemaHall> addCinemaHalls(List<CinemaHallDto> cinemaHalls) {
    return cinemaHallDao.saveAll(cinemaHalls
      .stream()
      .map(CinemaHallMapper::mapCinemaHall)
      .toList());
  }

  public Optional<CinemaHall> editCinemaHall(UUID id, CinemaHallDto cinemaHall) {
    return cinemaHallDao.update(id, CinemaHallMapper.mapCinemaHall(cinemaHall));
  }

  public List<CinemaHall> getCinemaHallsByCinemaId(UUID cinemaId) {
    return cinemaHallDao.findAllHallsFromCinema(cinemaId);
  }
}
