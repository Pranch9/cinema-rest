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
import ru.pranch.cinema.dto.CinemaDto;
import ru.pranch.cinema.dto.CinemaInfoDto;
import ru.pranch.cinema.model.Cinema;

@RequestMapping(value = "/api/v1/cinemas")
public interface ICinemaController {
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Cinema>> getCinemas();

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Cinema> getCinema(@PathVariable UUID id);

  @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CinemaInfoDto> getCinemaByName(@PathVariable String name);

  @GetMapping(value = "/city/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<CinemaInfoDto>> getCinemaByCity(@PathVariable String city);

  @PostMapping(value = "/control", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Cinema> addCinema(@RequestBody CinemaDto cinemaDto);

  @PutMapping(value = "/control/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Cinema> editCinema(@RequestBody CinemaDto cinemaDto, @PathVariable UUID id);

  @DeleteMapping(value = "/control/{id}")
  ResponseEntity<Integer> deleteCinema(@PathVariable UUID id);

}
