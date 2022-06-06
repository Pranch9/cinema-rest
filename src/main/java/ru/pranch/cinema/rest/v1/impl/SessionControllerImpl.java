package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.SessionDto;
import ru.pranch.cinema.model.Session;
import ru.pranch.cinema.rest.v1.api.ISessionController;

@RestController
public class SessionControllerImpl implements ISessionController {
  @Override
  public ResponseEntity<List<Session>> getSessions() {
    return null;
  }

  @Override
  public ResponseEntity<Session> getSession(UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<Session> addSession(SessionDto sessionDto) {
    return null;
  }

  @Override
  public ResponseEntity<Session> editSession(SessionDto sessionDto, UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Session>> addSessions(List<SessionDto> sessionsDto) {
    return null;
  }
}
