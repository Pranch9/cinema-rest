package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.MovieDto;
import ru.pranch.cinema.enums.MovieGenre;
import ru.pranch.cinema.model.Movie;
import ru.pranch.cinema.rest.v1.api.IMovieController;
import ru.pranch.cinema.services.MovieService;

@RestController
public class MovieControllerImpl implements IMovieController {
  private final MovieService movieService;

  @Autowired
  public MovieControllerImpl(MovieService movieService) {
    this.movieService = movieService;
  }

  @Override
  public ResponseEntity<List<Movie>> getMovies() {
    return ResponseEntity.ok(movieService.getMovies());
  }

  @Override
  public ResponseEntity<Movie> getMovie(UUID id) {
    return movieService.getMovieById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<Movie> addMovie(MovieDto movieDto) throws Exception {
    return ResponseEntity.ok(movieService.addMovie(movieDto));
  }

  @Override
  public ResponseEntity<Movie> editMovie(MovieDto movieDto, UUID id) throws Exception {
    return movieService.editMovie(id, movieDto)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<Movie> getMovieByTitle(String title) {
    return movieService.getMovieByTitle(title)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<List<Movie>> getMoviesByGenre(MovieGenre genre) {
    return ResponseEntity.ok(movieService.getMovieByGenre(genre));
  }

  @Override
  public ResponseEntity<Integer> deleteMovie(UUID id) {
    return ResponseEntity.ok(movieService.deleteMovie(id));
  }
}
