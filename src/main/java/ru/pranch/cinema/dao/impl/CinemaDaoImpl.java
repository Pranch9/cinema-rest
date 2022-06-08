package ru.pranch.cinema.dao.impl;

import java.util.Optional;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.CinemaDao;
import ru.pranch.cinema.model.Cinema;

@Repository
public class CinemaDaoImpl extends BasicDaoImpl<Cinema> implements CinemaDao {

  public CinemaDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  @Override
  public Optional<Cinema> findByName(String name) {
    return jdbi.withHandle(handle ->
        handle
            .createQuery("""
                select * from cinemas where cinema_name = :cinemaName;
                """)
            .bind("cinemaName", name)
            .mapToBean(Cinema.class)
            .findOne());
  }

  @Override
  public Optional<Cinema> findByAddress(UUID addressId) {
    return jdbi.withHandle(handle -> handle
        .createQuery("""
            select * from  cinemas where address_id = :address_id;
            """)
        .bind("address_id", addressId)
        .mapToBean(Cinema.class)
        .findFirst());
  }
}
