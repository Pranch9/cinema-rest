package ru.pranch.cinema.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.model.Session;

@Repository
public class SessionDaoImpl extends BasicDaoImpl<Session> implements SessionDao {
  private final Jdbi jdbi;

  public SessionDaoImpl(Jdbi jdbi) {
    super(jdbi);
    this.jdbi = jdbi;
  }

  @Override
  public List<Session> findAllByMovieId(UUID movieId) {
    return null;
  }

  @Override
  public List<Session> findAllByCinemaRoomAndDate(UUID cinemaRoomId, Date date) {
    return null;
  }

  @Override
  public List<UUID> findAllIdsByCinemaRoom(UUID cinemaRoomId) {
    return null;
  }
}
