package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.CinemaHallDto;
import ru.pranch.cinema.model.CinemaHall;
import ru.pranch.cinema.rest.v1.api.ICinemaHallController;
import ru.pranch.cinema.services.CinemaHallService;

@RestController
public class CinemaHallControllerImpl implements ICinemaHallController {
  private final CinemaHallService cinemaHallService;

  public CinemaHallControllerImpl(CinemaHallService cinemaHallService) {
    this.cinemaHallService = cinemaHallService;
  }

  @Override
  public ResponseEntity<List<CinemaHall>> getCinemaHalls() {
    return ResponseEntity.ok(cinemaHallService.getCinemaHalls());
  }

  @Override
  public ResponseEntity<CinemaHall> getCinemaHall(UUID id) {
    return cinemaHallService.getCinemaHallById(id)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<CinemaHall> addCinemaHall(CinemaHallDto cinemaHallDto) {
    return ResponseEntity.ok(cinemaHallService.addCinemaHall(cinemaHallDto));
  }

  @Override
  public ResponseEntity<CinemaHall> editCinemaHall(CinemaHallDto cinemaHallDto, UUID id) {
    return cinemaHallService.editCinemaHall(id, cinemaHallDto)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<List<CinemaHall>> addCinemaHalls(List<CinemaHallDto> cinemaHallsDto) {
    return ResponseEntity.ok(cinemaHallService.addCinemaHalls(cinemaHallsDto));
  }

  @Override
  public ResponseEntity<List<CinemaHall>> getCinemaHallsByCinemaId(UUID cinemaId) {
    return ResponseEntity.ok(cinemaHallService.getCinemaHallsByCinemaId(cinemaId));
  }
}
