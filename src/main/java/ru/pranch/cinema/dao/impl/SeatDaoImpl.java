package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dto.seat.GetSeatDto;
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

  @Override
  public Optional<Seat> findSeatsByPlaceAndRow(int row, int place, UUID sessionId) {
    String sql = """
        select s.row_number as rowNumber, s.id as id, s.place as place, s.cinema_hall_id as cinemaHallId, s.booked as booked
        from seats s join sessions s2 on s.cinema_hall_id = s2.cinema_hall_id where s.row_number = :row and s.place = :place and s2.id = : sessionId;
        """;
    return jdbi.withHandle(handle ->
        handle.createQuery(sql)
            .bind("row", row)
            .bind("place", place)
            .bind("sessionId", sessionId)
            .mapToBean(Seat.class)
            .findOne());
  }
}
