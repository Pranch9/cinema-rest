package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.model.Seat;

@Repository
public class SeatDaoImpl extends BasicDaoImpl<Seat> implements SeatDao {
  private final Jdbi jdbi;

  public SeatDaoImpl(Jdbi jdbi) {
    super(jdbi);
    this.jdbi = jdbi;
  }

  @Override
  public List<Seat> findSeatsByCinemaRoom(UUID cinemaRoomId) {
    return null;
  }

  @Override
  public List<UUID> findIdsByCinemaRoom(UUID cinemaRoomId) {
    return null;
  }
}