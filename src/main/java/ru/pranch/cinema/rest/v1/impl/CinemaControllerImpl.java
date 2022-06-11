package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.cinema.CinemaInfoDto;
import ru.pranch.cinema.dto.cinema.CreateCinemaDto;
import ru.pranch.cinema.dto.cinema.ResponseCreateCinemaDto;
import ru.pranch.cinema.dto.cinema.UpdateCinemaDto;
import ru.pranch.cinema.dto.cinema_hall.GetCinemaHallDto;
import ru.pranch.cinema.dto.seat.GetSeatDto;
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
  public ResponseEntity<List<CinemaInfoDto>> getCinemas(String cinemaName, String city) {
    return ResponseEntity.ok(cinemaService.getCinemas(cinemaName, city));
  }

  @Override
  public ResponseEntity<CinemaInfoDto> getCinemasById(UUID id) {
    return cinemaService.getCinema(id)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
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
  public ResponseEntity<ResponseCreateCinemaDto> addCinema(CreateCinemaDto createCinemaDto) {
    try {
      return ResponseEntity.ok(cinemaService.addCinema(createCinemaDto));
    } catch (Throwable e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @Override
  public ResponseEntity<CreateCinemaDto> editCinema(UpdateCinemaDto updateCinemaDto, UUID id) {
    try {
      return ResponseEntity.ok(cinemaService.editCinema(id, updateCinemaDto));
    } catch (Throwable e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @Override
  public ResponseEntity<Integer> deleteCinema(UUID id) {
    int result = cinemaService.deleteCinema(id);
    return result != 0
      ? ResponseEntity.ok(result)
      : ResponseEntity.notFound().build();
  }
}
