package ru.pranch.cinema.dao;

import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.model.CinemaHall;

public interface CinemaHallDao extends BasicDao<CinemaHall> {
  List<CinemaHall> findAllRoomsFromCinema(UUID cinemaId);

  List<UUID> findAllIdsFromCinema(UUID cinemaId);
}
