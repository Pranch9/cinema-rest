package ru.pranch.cinema.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import ru.pranch.cinema.dto.session.SessionInfoDto;

public interface SessionInfoDao {
  List<SessionInfoDto> findByCinema(UUID cinemaId);

  List<SessionInfoDto> findByMovie(UUID movieId);

  List<SessionInfoDto> findByDate(LocalDateTime date);

  Optional<SessionInfoDto> findById(UUID id);

  List<SessionInfoDto> findAll(LocalDateTime date, UUID movieId, UUID cinemaId);
}
