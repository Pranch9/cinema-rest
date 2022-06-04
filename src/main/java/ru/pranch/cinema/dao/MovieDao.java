package ru.pranch.cinema.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import ru.pranch.cinema.enums.MovieGenre;
import ru.pranch.cinema.model.Movie;

public interface MovieDao extends BasicDao<Movie> {
  Optional<Movie> findByTitle(String name);

  List<Movie> findMoviesByGenre(MovieGenre movieGenre);
}
