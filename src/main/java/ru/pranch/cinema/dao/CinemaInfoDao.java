package ru.pranch.cinema.dao;

import java.util.List;
import java.util.Optional;
import ru.pranch.cinema.dto.CinemaInfoDto;

public interface CinemaInfoDao {
  List<CinemaInfoDto> findByCity(String city);

  Optional<CinemaInfoDto> findByName(String name);
}
