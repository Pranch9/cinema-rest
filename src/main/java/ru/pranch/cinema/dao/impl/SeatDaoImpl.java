package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.enums.TableName;
import ru.pranch.cinema.model.Seat;

@Repository
public class SeatDaoImpl extends BasicDaoImpl<Seat> implements SeatDao {
  private final Jdbi jdbi;

  public SeatDaoImpl(Jdbi jdbi) {
    super(jdbi);
    this.jdbi = jdbi;
  }

  @Override
  public List<Seat> findSeatsByCinemaHall(UUID cinemaRoomId) {
    return jdbi.withHandle(handle ->
      handle.createQuery("select * " +
          "from " + TableName.SEAT.getDbTableName() + " " +
          "where cinema_room_id = :cinemaRoomId;")
        .bind("cinemaRoomId", cinemaRoomId)
        .mapToBean(Seat.class)
        .list());
  }
}
