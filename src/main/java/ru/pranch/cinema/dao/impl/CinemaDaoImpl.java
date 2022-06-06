package ru.pranch.cinema.dao.impl;

import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.CinemaDao;
import ru.pranch.cinema.enums.TableName;
import ru.pranch.cinema.model.Cinema;

@Repository
public class CinemaDaoImpl extends BasicDaoImpl<Cinema> implements CinemaDao {
  private final Jdbi jdbi;

  public CinemaDaoImpl(Jdbi jdbi) {
    super(jdbi);
    this.jdbi = jdbi;
  }

  @Override
  public Optional<Cinema> findByName(String name) {
    return jdbi.withHandle(handle ->
      handle
        .createQuery("select * " +
          "from " + " " + TableName.CINEMA.getDbTableName() + " " +
          "where cinema_name = :cinemaName")
        .bind("cinemaName", name)
        .mapToBean(Cinema.class)
        .findOne());
  }
}
