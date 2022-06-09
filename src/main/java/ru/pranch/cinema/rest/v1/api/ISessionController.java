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
import ru.pranch.cinema.dto.CreateSessionDto;
import ru.pranch.cinema.dto.SessionInfoDto;
import ru.pranch.cinema.model.Session;

@RequestMapping(value = "/api/v1/sessions")
public interface ISessionController {
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<SessionInfoDto>> getSessionsInfo();

  @GetMapping(value = "/date/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<SessionInfoDto>> getSessionInfoByDate(@PathVariable Date date);

  @GetMapping(value = "/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<SessionInfoDto>> getSessionInfoByValue(@PathVariable String value);

  @GetMapping(value = "/movie/{movieId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<SessionInfoDto>> getSessionInfoByMovie(@PathVariable UUID movieId);

  @GetMapping(value = "/cinema/{cinemaId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<SessionInfoDto>> getSessionInfoByCinemaId(@PathVariable UUID cinemaId);

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Session> getSession(@PathVariable UUID id);

  @PostMapping(value = "/control", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Session> addSession(@RequestBody CreateSessionDto createSessionDto);

  @PutMapping(value = "/control/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Session> editSession(@RequestBody CreateSessionDto createSessionDto, @PathVariable UUID id);

  @DeleteMapping(value = "/control/{id}")
  ResponseEntity<Integer> deleteSession(@PathVariable UUID id);

}
