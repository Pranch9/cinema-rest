package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.MovieDto;
import ru.pranch.cinema.model.Movie;
import ru.pranch.cinema.rest.v1.api.IMovieController;

@RestController
public class MovieControllerImpl implements IMovieController {
  @Override
  public ResponseEntity<List<Movie>> getMovies() {
    return null;
  }

  @Override
  public ResponseEntity<Movie> getMovie(UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<Movie> addMovie(MovieDto movieDto) {
    return null;
  }

  @Override
  public ResponseEntity<Movie> editMovie(MovieDto movieDto, UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Movie>> addMovies(List<MovieDto> moviesDto) {
    return null;
  }
}
