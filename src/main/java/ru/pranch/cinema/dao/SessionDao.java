package ru.pranch.cinema.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.model.Session;

public interface SessionDao extends BasicDao<Session, UUID> {
  List<Session> findAllByMovieId(UUID movieId);

  List<Session> findAllByCinemaRoomAndDate(UUID cinemaRoomId, Date date);

  List<UUID> findAllIdsByCinemaRoom(UUID cinemaRoomId);
}
