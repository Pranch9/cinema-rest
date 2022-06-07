package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.CinemaDao;
import ru.pranch.cinema.dao.CinemaInfoDao;
import ru.pranch.cinema.dto.CinemaInfoDto;
import ru.pranch.cinema.enums.TableName;
import ru.pranch.cinema.model.Cinema;

@Repository
public class CinemaInfoDaoImpl extends BasicDaoImpl<Cinema> implements CinemaInfoDao {
  private final Jdbi jdbi;

  public CinemaInfoDaoImpl(Jdbi jdbi) {
    super(jdbi);
    this.jdbi = jdbi;
  }


  @Override
  public List<CinemaInfoDto> findByCity(String city) {
    return null;
  }

  @Override
  public Optional<CinemaInfoDto> findByName(String name) {
    return Optional.empty();
  }
}
