package ru.pranch.cinema.rest.v1.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.CreateSessionDto;
import ru.pranch.cinema.dto.SessionInfoDto;
import ru.pranch.cinema.model.Session;
import ru.pranch.cinema.rest.v1.api.ISessionController;
import ru.pranch.cinema.services.SessionService;

@RestController
public class SessionControllerImpl implements ISessionController {
  private final SessionService sessionService;

  @Autowired
  public SessionControllerImpl(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @Override
  public ResponseEntity<Session> getSession(UUID id) {
    return sessionService.getSessionById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<List<SessionInfoDto>> getSessionInfoByDate(Date date) {
    return ResponseEntity.ok(sessionService.getSessionInfoByDate(date));
  }

  @Override
  public ResponseEntity<List<SessionInfoDto>> getSessionInfoByValue(String value) {
    return ResponseEntity.ok(sessionService.getSessionInfoByValue(value));
  }

  @Override
  public ResponseEntity<List<SessionInfoDto>> getSessionInfoByMovie(UUID movieId) {
    return ResponseEntity.ok(sessionService.getSessionInfoByMovie(movieId));
  }

  @Override
  public ResponseEntity<List<SessionInfoDto>> getSessionInfoByCinemaId(UUID cinemaId) {
    return ResponseEntity.ok(sessionService.getSessionInfoByCinemaId(cinemaId));
  }

  @Override
  public ResponseEntity<List<SessionInfoDto>> getSessionsInfo() {
    return ResponseEntity.ok(sessionService.getSessionsInfo());
  }

  @Override
  public ResponseEntity<Session> addSession(CreateSessionDto createSessionDto) {
    try {
      return ResponseEntity.ok(sessionService.addSession(createSessionDto));
    } catch (Exception e) {
      return ResponseEntity.unprocessableEntity().build();
    }
  }

  @Override
  public ResponseEntity<Session> editSession(CreateSessionDto createSessionDto, UUID id) {
    try {
      return sessionService.editSession(id, createSessionDto)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (Exception e) {
      return ResponseEntity.unprocessableEntity().build();
    }
  }

  @Override
  public ResponseEntity<Integer> deleteSession(UUID id) {
    return ResponseEntity.ok(sessionService.deleteSession(id));
  }
}
