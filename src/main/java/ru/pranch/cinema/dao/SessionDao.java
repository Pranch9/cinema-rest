package ru.pranch.cinema.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.model.Session;

public interface SessionDao extends BasicDao<Session> {
  List<Session> findAllByMovieId(UUID movieId);

  List<Session> findAllByCinemaRoomAndDate(UUID cinemaRoomId, Date sessionDate);
}
