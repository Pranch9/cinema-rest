package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.SeatDto;
import ru.pranch.cinema.model.Seat;
import ru.pranch.cinema.rest.v1.api.ISeatController;

@RestController
public class SeatControllerImpl implements ISeatController {
  @Override
  public ResponseEntity<List<Seat>> getSeats() {
    return null;
  }

  @Override
  public ResponseEntity<Seat> getSeat(UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<Seat> addSeat(SeatDto seatDto) {
    return null;
  }

  @Override
  public ResponseEntity<Seat> editSeat(SeatDto seatDto, UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Seat>> addSeats(List<SeatDto> seatsDto) {
    return null;
  }
}
