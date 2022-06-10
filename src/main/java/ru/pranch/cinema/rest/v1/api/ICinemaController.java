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
import ru.pranch.cinema.dto.GetSeatDto;
import ru.pranch.cinema.dto.cinema.CinemaInfoDto;
import ru.pranch.cinema.dto.cinema.CreateCinemaDto;
import ru.pranch.cinema.dto.cinema.UpdateCinemaDto;
import ru.pranch.cinema.dto.cinema_hall.GetCinemaHallDto;
import ru.pranch.cinema.model.Cinema;

@RequestMapping(value = "/api/v1/cinemas")
public interface ICinemaController {
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Cinema>> getCinemas();

  @GetMapping(value = "/cinemaHalls/{cinemaId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<GetCinemaHallDto>> getCinemaHallByCinemaId(@PathVariable UUID cinemaId);

  @GetMapping(value = "/seats/{cinemaHallId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<GetSeatDto>> getSeatsByCinemaHallId(@PathVariable UUID cinemaHallId);

  @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CinemaInfoDto> getCinemaByName(@PathVariable String name);

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CinemaInfoDto> getCinema(@PathVariable UUID id);

  @GetMapping(value = "/city/{city}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<CinemaInfoDto>> getCinemaByCity(@PathVariable String city);

  @PostMapping(value = "/control", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Cinema> addCinema(@RequestBody CreateCinemaDto createCinemaDto);

  @PutMapping(value = "/control/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Cinema> editCinema(@RequestBody UpdateCinemaDto updateCinemaDto, @PathVariable UUID id);

  @DeleteMapping(value = "/control/{id}")
  ResponseEntity<Integer> deleteCinema(@PathVariable UUID id);

}
