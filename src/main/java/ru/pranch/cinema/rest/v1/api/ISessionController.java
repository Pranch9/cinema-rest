package ru.pranch.cinema.rest.v1.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.pranch.cinema.dto.session.CreateSessionDto;
import ru.pranch.cinema.dto.session.ResponseCreateSessionDto;
import ru.pranch.cinema.dto.session.ResponseUpdateSessionDto;
import ru.pranch.cinema.dto.session.SessionInfoDto;
import ru.pranch.cinema.dto.session.UpdateSessionDto;

@RequestMapping(value = "/api/v1/sessions")
public interface ISessionController {
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<SessionInfoDto>> getSessionsInfo(@RequestParam(name = "date", required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                                       @RequestParam(name = "movieId", required = false) UUID movieId,
                                                       @RequestParam(name = "cinemaId", required = false) UUID cinemaId);

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<SessionInfoDto> getSessionInfoById(@PathVariable UUID id);

  @PostMapping(value = "/control", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ResponseCreateSessionDto> addSession(@RequestBody CreateSessionDto createSessionDto);

  @PutMapping(value = "/control", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ResponseUpdateSessionDto> editSession(@RequestBody UpdateSessionDto updateSessionDto,
                                                       @RequestParam(name = "sessionId") UUID id,
                                                       @RequestParam(name = "cinemaHallId") UUID cinemaHallId);

  @DeleteMapping(value = "/control/{id}")
  ResponseEntity<Integer> deleteSession(@PathVariable UUID id);

}
