package ru.pranch.cinema.dao;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.dto.SessionInfoDto;
import ru.pranch.cinema.model.Session;

public interface SessionInfoDao {
  List<SessionInfoDto> findByCinema(UUID cinemaId);

  List<SessionInfoDto> findByMovie(UUID movieId);

  List<SessionInfoDto> findByDate(Date date);

  List<SessionInfoDto> findAll();
}
