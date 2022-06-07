package ru.pranch.cinema.rest.v1.api;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pranch.cinema.dto.SessionDto;
import ru.pranch.cinema.dto.SessionInfoDto;
import ru.pranch.cinema.model.Session;

@RequestMapping(value = "/api/v1/sessions")
public interface ISessionController {
  @GetMapping(value = "/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Session>> getSessions();

  @GetMapping(value = "/session/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Session> getSession(@PathVariable UUID id);

  @GetMapping(value = "/date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<SessionInfoDto> getSessionInfoByDate(@PathVariable Date date);

  @PostMapping(value = "/control", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Session> addSession(@RequestBody SessionDto sessionDto);

  @PutMapping(value = "/control/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Session> editSession(@RequestBody SessionDto sessionDto, @PathVariable UUID id);

  @DeleteMapping(value = "/control/{id}")
  ResponseEntity<Session> deleteSession(@PathVariable UUID id);

}
