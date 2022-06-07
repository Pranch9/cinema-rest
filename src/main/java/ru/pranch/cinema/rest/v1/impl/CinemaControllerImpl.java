package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.CinemaDto;
import ru.pranch.cinema.dto.CinemaInfoDto;
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
  public ResponseEntity<Cinema> getCinema(UUID id) {
    return cinemaService.getCinemaById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<Cinema> addCinema(CinemaDto cinemaDto) {
    try {
      return ResponseEntity.ok(cinemaService.addCinema(cinemaDto));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<Cinema> editCinema(CinemaDto cinemaDto, UUID id) {
    try {
      return cinemaService.editCinema(id, cinemaDto)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<CinemaInfoDto> getCinemaByName(String name) {
    return null;
//    return cinemaService.getCinemaByName(name)
//        .map(ResponseEntity::ok)
//        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<List<CinemaInfoDto>> getCinemaByCity(String city) {
    return null;
  }

  @Override
  public ResponseEntity<Integer> deleteCinema(UUID id) {
    return ResponseEntity.ok(cinemaService.deleteCinema(id));
  }
}
