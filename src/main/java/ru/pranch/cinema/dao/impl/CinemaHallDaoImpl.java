package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.CinemaHallDao;
import ru.pranch.cinema.dto.cinema_hall.GetCinemaHallDto;
import ru.pranch.cinema.model.CinemaHall;

@Repository
public class CinemaHallDaoImpl extends BasicDaoImpl<CinemaHall> implements CinemaHallDao {

  public CinemaHallDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  @Override
  public List<GetCinemaHallDto> findAllHallsFromCinema(UUID cinemaId) {
    return jdbi.withHandle(handle ->
      handle.createQuery("""
          select * from cinema_halls where cinema_id = :cinemaId;
          """)
        .bind("cinemaId", cinemaId)
        .mapToBean(GetCinemaHallDto.class)
        .list()
    );
  }

  @Override
  public Optional<CinemaHall> findByName(String name) {
    return jdbi.withHandle(handle ->
      handle.createQuery("""
          select * from cinema_halls where hall_name = :name;
          """)
        .bind("name", name)
        .mapToBean(CinemaHall.class)
        .findOne()
    );
  }
}
