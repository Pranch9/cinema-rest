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
import org.springframework.web.bind.annotation.RequestParam;
import ru.pranch.cinema.dto.cinema.CinemaInfoDto;
import ru.pranch.cinema.dto.cinema.CreateCinemaDto;
import ru.pranch.cinema.dto.cinema.ResponseCreateCinemaDto;
import ru.pranch.cinema.dto.cinema.UpdateCinemaDto;
import ru.pranch.cinema.dto.cinema_hall.GetCinemaHallDto;
import ru.pranch.cinema.dto.seat.GetSeatDto;

@RequestMapping(value = "/api/v1/cinemas")
public interface ICinemaController {
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<CinemaInfoDto>> getCinemas(@RequestParam(name = "name", required = false) String cinemaName,
                                                 @RequestParam(name = "city", required = false) String city);

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CinemaInfoDto> getCinemasById(@PathVariable UUID id);

  @GetMapping(value = "/cinemaHalls/{cinemaId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<GetCinemaHallDto>> getCinemaHallByCinemaId(@PathVariable UUID cinemaId);

  @GetMapping(value = "/seats/{cinemaHallId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<GetSeatDto>> getSeatsByCinemaHallId(@PathVariable UUID cinemaHallId);

  @PostMapping(value = "/control", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ResponseCreateCinemaDto> addCinema(@RequestBody CreateCinemaDto createCinemaDto);

  @PutMapping(value = "/control/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CreateCinemaDto> editCinema(@RequestBody UpdateCinemaDto updateCinemaDto, @PathVariable UUID id);

  @DeleteMapping(value = "/control/{id}")
  ResponseEntity<Integer> deleteCinema(@PathVariable UUID id);
}
