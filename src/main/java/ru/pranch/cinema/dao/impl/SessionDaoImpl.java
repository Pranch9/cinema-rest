package ru.pranch.cinema.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.model.Session;

@Repository
public class SessionDaoImpl extends BasicDaoImpl<Session> implements SessionDao {

  public SessionDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  @Override
  public List<Session> findAllByMovieId(UUID movieId) {
    return jdbi.withHandle(handle ->
      handle.createQuery("""
          select * from sessions where movie_id = :movieId;
          """)
        .bind("movieId", movieId)
        .mapToBean(Session.class)
        .list());
  }

  @Override
  public List<Session> findAllByCinemaHallAndDate(UUID cinemaHallId, LocalDateTime sessionDate) {
    return jdbi.withHandle(handle ->
      handle.createQuery("""
          select * from sessions where cinema_hall_id = :cinemaHallId and date(session_date) = :sessionDate;
          """)
        .bind("cinemaHallId", cinemaHallId)
        .bind("sessionDate", sessionDate)
        .mapToBean(Session.class)
        .list());
  }

  @Override
  public List<Session> findAllByCinemaHallId(UUID cinemaHallId) {
    return jdbi.withHandle(handle ->
      handle.createQuery("""
          select * from sessions where cinema_hall_id = :cinemaHallId;
          """)
        .bind("cinemaHallId", cinemaHallId)
        .mapToBean(Session.class)
        .list());
  }
}
