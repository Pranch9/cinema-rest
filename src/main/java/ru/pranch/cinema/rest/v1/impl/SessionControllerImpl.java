package ru.pranch.cinema.rest.v1.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.session.CreateSessionDto;
import ru.pranch.cinema.dto.session.ResponseCreateSessionDto;
import ru.pranch.cinema.dto.session.ResponseUpdateSessionDto;
import ru.pranch.cinema.dto.session.SessionInfoDto;
import ru.pranch.cinema.dto.session.UpdateSessionDto;
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
  public ResponseEntity<List<SessionInfoDto>> getSessionsInfo(LocalDateTime date, UUID movieId, UUID cinemaId) {
    return ResponseEntity.ok(sessionService.getSessionsInfo(date, movieId, cinemaId));
  }

  @Override
  public ResponseEntity<SessionInfoDto> getSessionInfoById(UUID id) {
    return sessionService.getSessionInfoById(id)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<ResponseCreateSessionDto> addSession(CreateSessionDto createSessionDto) {
    try {
      return ResponseEntity.ok(sessionService.addSession(createSessionDto));
    } catch (Exception e) {
      return ResponseEntity.unprocessableEntity().build();
    }
  }

  @Override
  public ResponseEntity<ResponseUpdateSessionDto> editSession(UpdateSessionDto updateSessionDto, UUID id, UUID cinemaHallId) {
    try {
      return ResponseEntity.ok(sessionService.editSession(id, cinemaHallId, updateSessionDto));
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @Override
  public ResponseEntity<Integer> deleteSession(UUID id) {
    return ResponseEntity.ok(sessionService.deleteSession(id));
  }
}
