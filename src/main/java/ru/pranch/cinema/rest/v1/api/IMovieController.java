package ru.pranch.cinema.rest.v1.api;

import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pranch.cinema.dto.CreateMovieDto;
import ru.pranch.cinema.enums.MovieGenre;
import ru.pranch.cinema.model.Movie;

@RequestMapping(value = "/api/v1/movies")
public interface IMovieController {
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Movie>> getMovies();

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Movie> getMovie(@PathVariable UUID id);

  @GetMapping(value = "/{title}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Movie> getMovieByTitle(@PathVariable String title);

  @GetMapping(value = "/genre/{genre}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable MovieGenre genre);

  @PostMapping(value = "/control", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Movie>> addMovies(@RequestBody List<CreateMovieDto> moviesDto);

  @PutMapping(value = "/control/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Movie> editMovie(@RequestBody CreateMovieDto createMovieDto, @PathVariable UUID id);

  @DeleteMapping(value = "/control")
  ResponseEntity<Integer> deleteMovies(@RequestBody List<UUID> ids);

}
