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
import ru.pranch.cinema.dto.CinemaHallDto;
import ru.pranch.cinema.model.CinemaHall;

@RequestMapping(value = "/api/v1")
public interface ICinemaHallController {
  @GetMapping(value = "/cinemaHalls", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<CinemaHall>> getCinemaHalls();

  @GetMapping(value = "/cinemaHall/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CinemaHall> getCinemaHall(@PathVariable UUID id);

  @PostMapping(value = "/cinemaHall/add", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CinemaHall> addCinemaHall(@RequestBody CinemaHallDto cinemaHallDto);

  @PutMapping(value = "/cinemaHall/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CinemaHall> editCinemaHall(@RequestBody CinemaHallDto cinemaHallDto, @PathVariable UUID id);

  @PostMapping(value = "/cinemaHalls/add")
  ResponseEntity<List<CinemaHall>> addCinemaHalls(@RequestBody List<CinemaHallDto> cinemaHallsDto);

  @GetMapping("/cinemaHalls/{cinemaId}")
  ResponseEntity<List<CinemaHall>> getCinemaHallsByCinemaId(@PathVariable UUID cinemaId);

}
