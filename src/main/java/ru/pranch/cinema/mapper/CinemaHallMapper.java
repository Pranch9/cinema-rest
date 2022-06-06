package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.CinemaHallDto;
import ru.pranch.cinema.model.CinemaHall;

public class CinemaHallMapper {
  public static CinemaHall mapCinemaHall(CinemaHallDto cinemaHallDto) {
    CinemaHall cinemaHall = new CinemaHall();
    cinemaHall.setCinemaId(cinemaHallDto.getCinemaId());
    cinemaHall.setHallName(cinemaHallDto.getHallName());
    cinemaHall.setRowsNumber(cinemaHallDto.getRowsNumber());
    cinemaHall.setPlaceNumber(cinemaHallDto.getPlaceNumber());

    return cinemaHall;
  }
}
