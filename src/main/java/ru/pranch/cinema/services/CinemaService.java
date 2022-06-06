package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.CinemaDao;
import ru.pranch.cinema.dto.CinemaDto;
import ru.pranch.cinema.mapper.CinemaMapper;
import ru.pranch.cinema.model.Cinema;

@Service
public class CinemaService {
  private final CinemaDao cinemaDao;

  @Autowired
  public CinemaService(CinemaDao cinemaDao) {
    this.cinemaDao = cinemaDao;
  }

  public List<Cinema> getCinemas() {
    return cinemaDao.findAll();
  }

  public Optional<Cinema> getCinemaById(UUID id) {
    return cinemaDao.findById(id);
  }

  public Optional<Cinema> getCinemaByName(String name) {
    return cinemaDao.findByName(name);
  }

  public Cinema addCinema(CinemaDto cinema) {
    return cinemaDao.save(CinemaMapper.mapCinema(cinema));
  }

  public List<Cinema> addCinemas(List<CinemaDto> cinemas) {
    return cinemaDao.saveAll(cinemas
      .stream()
      .map(CinemaMapper::mapCinema)
      .toList());
  }

  public Optional<Cinema> editCinema(UUID id, CinemaDto cinema) {
    return cinemaDao.update(id, CinemaMapper.mapCinema(cinema));
  }
}
