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
import ru.pranch.cinema.dto.SeatDto;
import ru.pranch.cinema.model.Seat;

@RequestMapping(value = "/api/v1")
public interface ISeatController {
  @GetMapping(value = "/seats", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Seat>> getSeats();

  @GetMapping(value = "/seat/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Seat> getSeat(@PathVariable UUID id);

  @PostMapping(value = "/seat/add", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Seat> addSeat(@RequestBody SeatDto seatDto);

  @PutMapping(value = "/seat/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Seat> editSeat(@RequestBody SeatDto seatDto, @PathVariable UUID id);

  @PostMapping(value = "/seats/add")
  ResponseEntity<List<Seat>> addSeats(@RequestBody List<SeatDto> seatsDto);
}
