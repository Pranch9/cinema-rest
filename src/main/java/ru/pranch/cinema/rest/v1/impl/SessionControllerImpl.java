package ru.pranch.cinema.rest.v1.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.SessionDto;
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
  public ResponseEntity<List<Session>> getSessions() {
    return ResponseEntity.ok(sessionService.getSessions());
  }

  @Override
  public ResponseEntity<Session> getSession(UUID id) {
    return sessionService.getSessionById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<SessionInfoDto> getSessionInfoByDate(Date date) {
    return null;
  }

  @Override
  public ResponseEntity<Session> addSession(SessionDto sessionDto) {
    try {
      return ResponseEntity.ok(sessionService.addSession(sessionDto));
    } catch (Exception e) {
      return ResponseEntity.unprocessableEntity().build();
    }
  }

  @Override
  public ResponseEntity<Session> editSession(SessionDto sessionDto, UUID id) {
    try {
      return sessionService.editSession(id, sessionDto)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (Exception e) {
      return ResponseEntity.unprocessableEntity().build();
    }
  }

  @Override
  public ResponseEntity<Session> deleteSession(UUID id) {
    return null;
  }
}
