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
import ru.pranch.cinema.dto.CinemaDto;
import ru.pranch.cinema.model.Cinema;

@RequestMapping(value = "/api/v1")
public interface ICinemaController {
  @GetMapping(value = "/cinemas", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Cinema>> getCinemas();

  @GetMapping(value = "/cinema/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Cinema> getCinema(@PathVariable UUID id);

  @PostMapping(value = "/cinema/add", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Cinema> addCinema(@RequestBody CinemaDto cinemaDto);

  @PutMapping(value = "/cinema/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Cinema> editCinema(@RequestBody CinemaDto cinemaDto, @PathVariable UUID id);

  @GetMapping(value = "/cinema/{name}")
  ResponseEntity<Cinema> getCinemaByName(@PathVariable String name);

  @PostMapping(value = "/cinemas/add")
  ResponseEntity<List<Cinema>> addCinemas(@RequestBody List<CinemaDto> cinemas);
}
