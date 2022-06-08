package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.cinema.CreateCinemaDto;
import ru.pranch.cinema.dto.cinema.UpdateCinemaDto;
import ru.pranch.cinema.dto.cinema_hall.CreateCinemaHallDto;
import ru.pranch.cinema.dto.cinema_hall.UpdateCinemaHallDto;
import ru.pranch.cinema.model.Cinema;
import ru.pranch.cinema.model.CinemaHall;

public class CinemaMapper {
  public static Cinema mapCinema(CreateCinemaDto createCinemaDto) {
    Cinema cinema = new Cinema();
    cinema.setCinemaName(createCinemaDto.getCinemaName());

    return cinema;
  }

  public static Cinema mapCinema(UpdateCinemaDto updateCinemaDto) {
    Cinema cinema = new Cinema();
    cinema.setCinemaName(updateCinemaDto.getCinemaName());

    return cinema;
  }

  public static CinemaHall mapCinemaHall(CreateCinemaHallDto cinemaHallDto) {
    CinemaHall cinemaHall = new CinemaHall();
    cinemaHall.setHallName(cinemaHallDto.getHallName());
    cinemaHall.setRowsNumber(cinemaHallDto.getRowsNumber());
    cinemaHall.setPlaceNumber(cinemaHallDto.getPlaceNumber());

    return cinemaHall;
  }

  public static CinemaHall mapCinemaHall(UpdateCinemaHallDto cinemaHallDto) {
    CinemaHall cinemaHall = new CinemaHall();
    cinemaHall.setId(cinemaHallDto.getId());
    cinemaHall.setHallName(cinemaHallDto.getHallName());
    cinemaHall.setRowsNumber(cinemaHallDto.getRowsNumber());
    cinemaHall.setPlaceNumber(cinemaHallDto.getPlaceNumber());

    return cinemaHall;
  }
}
