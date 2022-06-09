package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.CreateSessionDto;
import ru.pranch.cinema.model.Session;

public class SessionMapper {
  public static Session mapSession(CreateSessionDto createSessionDto) {
    Session session = new Session();
    session.setCinemaHallId(createSessionDto.getCinemaHallId());
    session.setSessionDate(createSessionDto.getSessionDate());

    return session;
  }
}
