package ru.pranch.cinema.dao;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.model.Cinema;

@Repository
public interface CinemaDao extends BasicDao<Cinema> {
  Optional<Cinema> findByName(String name);
}
