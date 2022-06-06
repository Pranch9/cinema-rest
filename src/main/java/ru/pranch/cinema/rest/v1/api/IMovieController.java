package ru.pranch.cinema.rest.v1.api;

import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pranch.cinema.dto.MovieDto;
import ru.pranch.cinema.model.Movie;

@RequestMapping(value = "/api/v1")
public interface IMovieController {
  @GetMapping(value = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Movie>> getMovies();

  @GetMapping(value = "/movie/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Movie> getMovie(@PathVariable UUID id);

  @PostMapping(value = "/movie/add", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Movie> addMovie(@RequestBody MovieDto movieDto);

  @PutMapping(value = "/movie/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Movie> editMovie(@RequestBody MovieDto movieDto, @PathVariable UUID id);

  @PostMapping(value = "/movies/add")
  ResponseEntity<List<Movie>> addMovies(@RequestBody List<MovieDto> moviesDto);
}
