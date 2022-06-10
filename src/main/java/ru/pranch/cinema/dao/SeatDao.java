package ru.pranch.cinema.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import ru.pranch.cinema.dto.seat.GetSeatDto;
import ru.pranch.cinema.model.Seat;

public interface SeatDao extends BasicDao<Seat> {
  List<GetSeatDto> findSeatsByCinemaHall(UUID cinemaHallId);

  Optional<Seat> findSeatsByPlaceAndRow(int row, int place, UUID sessionId);
}
