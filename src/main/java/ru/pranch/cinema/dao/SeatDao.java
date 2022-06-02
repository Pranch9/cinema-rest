package ru.pranch.cinema.dao;

import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.model.Seat;

public interface SeatDao extends BasicDao<Seat, UUID> {
  List<Seat> findSeatsByCinemaRoom(UUID cinemaRoomId);

  List<UUID> findIdsByCinemaRoom(UUID cinemaRoomId);
}
