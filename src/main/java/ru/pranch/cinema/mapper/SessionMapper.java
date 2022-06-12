package ru.pranch.cinema.mapper;

import java.util.UUID;
import ru.pranch.cinema.dto.CreateMovieDto;
import ru.pranch.cinema.dto.session.CreateSessionDto;
import ru.pranch.cinema.dto.session.ResponseCreateSessionDto;
import ru.pranch.cinema.dto.session.ResponseUpdateSessionDto;
import ru.pranch.cinema.dto.session.UpdateSessionDto;
import ru.pranch.cinema.model.Movie;
import ru.pranch.cinema.model.Session;

public class SessionMapper {
  public static Session mapSession(CreateSessionDto createSessionDto) {
    Session session = new Session();
    session.setCinemaHallId(createSessionDto.getCinemaHallId());
    session.setSessionDate(createSessionDto.getSessionDate());

    return session;
  }

  public static Session mapSession(UpdateSessionDto updateSessionDto, UUID cinemaHallId) {
    Session session = new Session();
    session.setCinemaHallId(cinemaHallId);
    session.setSessionDate(updateSessionDto.getSessionDate());

    return session;
  }

  public static ResponseCreateSessionDto mapResponseCreateSession(Session session, Movie movie) {
    ResponseCreateSessionDto responseCreateSessionDto = new ResponseCreateSessionDto();
    responseCreateSessionDto.setSessionDate(session.getSessionDate());
    responseCreateSessionDto.setId(session.getId());
    responseCreateSessionDto.setCinemaHallId(session.getCinemaHallId());
    responseCreateSessionDto.setMovie(movie);

    return responseCreateSessionDto;
  }

  public static ResponseUpdateSessionDto mapResponseUpdateSession(Session session, CreateMovieDto movie) {
    ResponseUpdateSessionDto responseUpdateSessionDto = new ResponseUpdateSessionDto();
    responseUpdateSessionDto.setSessionDate(session.getSessionDate());
    responseUpdateSessionDto.setCreateMovieDto(movie);

    return responseUpdateSessionDto;
  }
}
