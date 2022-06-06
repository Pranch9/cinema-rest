package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.SeatDto;
import ru.pranch.cinema.model.Seat;

public class SeatMapper {
  public static Seat mapSeat(SeatDto seatDto) {
    Seat seat = new Seat();
    seat.setSeatStatus(seatDto.getSeatStatus());
    seat.setCinemaHallId(seatDto.getCinemaHallId());
    seat.setPlace(seatDto.getPlace());
    seat.setRowNumber(seatDto.getRowNumber());

    return seat;
  }
}
