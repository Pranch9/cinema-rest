package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.CinemaDto;
import ru.pranch.cinema.model.Cinema;

public class CinemaMapper {
  public static Cinema mapCinema(CinemaDto cinemaDto) {
    Cinema cinema = new Cinema();
    cinema.setCinemaName(cinemaDto.getCinemaName());

    return cinema;
  }
}
