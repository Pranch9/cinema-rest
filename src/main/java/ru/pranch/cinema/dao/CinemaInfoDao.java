package ru.pranch.cinema.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dto.CinemaInfoDto;
import ru.pranch.cinema.model.Cinema;

@Repository
public interface CinemaInfoDao {
  List<CinemaInfoDto> findByCity(String city);

  Optional<CinemaInfoDto> findByName(String name);
}
