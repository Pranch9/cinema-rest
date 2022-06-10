package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.seat.GetSeatDto;
import ru.pranch.cinema.dto.cinema.CreateCinemaDto;
import ru.pranch.cinema.dto.cinema.CinemaInfoDto;
import ru.pranch.cinema.dto.cinema.UpdateCinemaDto;
import ru.pranch.cinema.dto.cinema_hall.GetCinemaHallDto;
import ru.pranch.cinema.model.Cinema;
import ru.pranch.cinema.rest.v1.api.ICinemaController;
import ru.pranch.cinema.services.CinemaService;

@RestController
public class CinemaControllerImpl implements ICinemaController {
  private final CinemaService cinemaService;

  @Autowired
  public CinemaControllerImpl(CinemaService cinemaService) {
    this.cinemaService = cinemaService;
  }

  @Override
  public ResponseEntity<List<Cinema>> getCinemas() {
    return ResponseEntity.ok(cinemaService.getCinemas());
  }

  @Override
  public ResponseEntity<List<GetCinemaHallDto>> getCinemaHallByCinemaId(UUID cinemaId) {
    return ResponseEntity.ok(cinemaService.getCinemaHallsByCinemaId(cinemaId));
  }

  @Override
  public ResponseEntity<List<GetSeatDto>> getSeatsByCinemaHallId(UUID cinemaHallId) {
    return ResponseEntity.ok(cinemaService.getSeatsByCinemaHallId(cinemaHallId));
  }

  @Override
  public ResponseEntity<Cinema> addCinema(CreateCinemaDto createCinemaDto) {
    try {
      return ResponseEntity.ok(cinemaService.addCinema(createCinemaDto));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<Cinema> editCinema(UpdateCinemaDto updateCinemaDto, UUID id) {
    try {
      return cinemaService.editCinema(id, updateCinemaDto)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<CinemaInfoDto> getCinemaByName(String name) {
    return cinemaService.getCinemaByName(name)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<CinemaInfoDto> getCinema(UUID id) {
    return cinemaService.getCinema(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<List<CinemaInfoDto>> getCinemaByCity(String city) {
    return ResponseEntity.ok(cinemaService.getCinemasByCity(city));
  }

  @Override
  public ResponseEntity<Integer> deleteCinema(UUID id) {
    return ResponseEntity.ok(cinemaService.deleteCinema(id));
  }
}
