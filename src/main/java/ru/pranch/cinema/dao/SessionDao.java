package ru.pranch.cinema.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.model.Session;

public interface SessionDao extends BasicDao<Session> {
  List<Session> findAllByMovieId(UUID movieId);

  List<Session> findAllByCinemaHallAndDate(UUID cinemaHallId, LocalDateTime sessionDate);

  List<Session> findAllByCinemaHallId(UUID cinemaHallId);
}
