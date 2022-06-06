package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.SessionDto;
import ru.pranch.cinema.model.Session;

public class SessionMapper {
  public static Session mapSession(SessionDto sessionDto) {
    Session session = new Session();
    session.setCinemaHallId(sessionDto.getCinemaHallId());
    session.setSessionDate(sessionDto.getSessionDate());
    session.setMovieId(sessionDto.getMovieId());

    return session;
  }
}
