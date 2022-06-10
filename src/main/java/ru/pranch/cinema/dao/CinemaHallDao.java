package ru.pranch.cinema.dao;

import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.dto.cinema_hall.GetCinemaHallDto;
import ru.pranch.cinema.model.CinemaHall;

public interface CinemaHallDao extends BasicDao<CinemaHall> {
  List<GetCinemaHallDto> findAllHallsFromCinema(UUID cinemaId);
}
