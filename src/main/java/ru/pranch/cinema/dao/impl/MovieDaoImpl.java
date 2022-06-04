package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.MovieDao;
import ru.pranch.cinema.enums.MovieGenre;
import ru.pranch.cinema.model.Movie;

@Repository
public class MovieDaoImpl extends BasicDaoImpl<Movie> implements MovieDao {
  private final Jdbi jdbi;

  public MovieDaoImpl(Jdbi jdbi) {
    super(jdbi);
    this.jdbi = jdbi;
  }


  @Override
  public Optional<Movie> findByTitle(String name) {
    return Optional.empty();
  }

  @Override
  public List<Movie> findMoviesByGenre(MovieGenre movieGenre) {
    return null;
  }
}
