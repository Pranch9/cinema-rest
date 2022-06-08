package ru.pranch.cinema.dao;

import java.util.Optional;
import java.util.UUID;
import ru.pranch.cinema.model.Cinema;

public interface CinemaDao extends BasicDao<Cinema> {
  Optional<Cinema> findByName(String name);

  Optional<Cinema> findByAddress(UUID address_id);
}
