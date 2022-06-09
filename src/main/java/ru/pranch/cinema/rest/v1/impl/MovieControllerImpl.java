package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.CreateMovieDto;
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
  public ResponseEntity<Movie> editMovie(CreateMovieDto createMovieDto, UUID id) {
    try {
      return movieService.editMovie(id, createMovieDto)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<Integer> deleteMovies(List<UUID> ids) {
    return ResponseEntity.ok(movieService.deleteMovies(ids));
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
  public ResponseEntity<List<Movie>> addMovies(List<CreateMovieDto> moviesDto) {
    return ResponseEntity.ok(movieService.addMovies(moviesDto));
  }
}
