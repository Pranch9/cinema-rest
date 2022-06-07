package ru.pranch.cinema.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.enums.TableName;
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
    return jdbi.withHandle(handle ->
        handle.createQuery("select * " +
                "from " + TableName.SESSION.getDbTableName() + " " +
                "where movie_id = :movieId;")
            .bind("movieId", movieId)
            .mapToBean(Session.class)
            .list());
  }

  @Override
  public List<Session> findAllByCinemaRoomAndDate(UUID cinemaRoomId, Date sessionDate) {
    return jdbi.withHandle(handle ->
        handle.createQuery("select * " +
                "from " + TableName.SESSION.getDbTableName() + " " +
                "where cinema_room_id = :cinemaRoomId and session_date = :sessionDate;")
            .bind("cinemaRoomId", cinemaRoomId)
            .bind("sessionDate", sessionDate)
            .mapToBean(Session.class)
            .list());
  }

  @Override
  public List<Session> findAllByCinemaRoomId(UUID cinemaRoomId) {
    return jdbi.withHandle(handle ->
        handle.createQuery("select * " +
                "from " + TableName.SESSION.getDbTableName() + " " +
                "where cinema_room_id = :cinemaRoomId;")
            .bind("cinemaRoomId", cinemaRoomId)
            .mapToBean(Session.class)
            .list());
  }
}
