package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.CinemaHallDao;
import ru.pranch.cinema.model.CinemaHall;

@Repository
public class CinemaHallDaoImpl extends BasicDaoImpl<CinemaHall> implements CinemaHallDao {
  private final Jdbi jdbi;

  public CinemaHallDaoImpl(Jdbi jdbi) {
    super(jdbi);
    this.jdbi = jdbi;
  }

  @Override
  public List<CinemaHall> findAllRoomsFromCinema(UUID cinemaId) {
    return null;
  }

  @Override
  public List<UUID> findAllIdsFromCinema(UUID cinemaId) {
    return null;
  }
}
