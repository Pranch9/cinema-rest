package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dto.GetSeatDto;
import ru.pranch.cinema.model.Seat;

@Repository
public class SeatDaoImpl extends BasicDaoImpl<Seat> implements SeatDao {

  public SeatDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  @Override
  public List<GetSeatDto> findSeatsByCinemaHall(UUID cinemaHallId) {
    return jdbi.withHandle(handle ->
        handle.createQuery("""
                select * from seats where cinema_hall_id = :cinemaHallId;
                """)
            .bind("cinemaHallId", cinemaHallId)
            .mapToBean(GetSeatDto.class)
            .list());
  }
}
