package ru.pranch.cinema.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import ru.pranch.cinema.dto.SessionInfoDto;
import ru.pranch.cinema.dto.cinema.CinemaInfoDto;

public interface CinemaInfoDao {
  List<CinemaInfoDto> findByCity(String city);

  Optional<CinemaInfoDto> findById(UUID id);

  Optional<CinemaInfoDto> findByName(String name);

  List<CinemaInfoDto> findAll();
}
