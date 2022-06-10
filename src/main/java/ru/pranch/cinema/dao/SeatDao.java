package ru.pranch.cinema.dao;

import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.dto.GetSeatDto;
import ru.pranch.cinema.model.Seat;

public interface SeatDao extends BasicDao<Seat> {
  List<GetSeatDto> findSeatsByCinemaHall(UUID cinemaHallId);
}
