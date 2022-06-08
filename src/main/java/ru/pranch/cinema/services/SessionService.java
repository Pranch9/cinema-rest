package ru.pranch.cinema.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.MovieDao;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.dto.SessionDto;
import ru.pranch.cinema.mapper.SessionMapper;
import ru.pranch.cinema.model.Movie;
import ru.pranch.cinema.model.Session;

@Service
public class SessionService {
  private final SessionDao sessionDao;
  private final MovieDao movieDao;

  @Autowired
  public SessionService(SessionDao sessionDao, MovieDao movieDao) {
    this.sessionDao = sessionDao;
    this.movieDao = movieDao;
  }

  public List<Session> getSessions() {
    return sessionDao.findAll();
  }

  public Optional<Session> getSessionById(UUID id) {
    return sessionDao.findById(id);
  }

  public Session addSession(SessionDto session) throws Exception {
    if (!checkTimeAvailability(session)) {
      throw new Exception();
    }
    return sessionDao.save(SessionMapper.mapSession(session));
  }

  public List<Session> addSessions(List<SessionDto> sessions) throws Exception {
    if (sessions.stream().anyMatch(this::checkTimeAvailability)) {
      throw new Exception();
    }
    return sessionDao.saveAll(sessions
        .stream()
        .map(SessionMapper::mapSession)
        .toList());
  }

  public Optional<Session> editSession(UUID id, SessionDto session) throws Exception {
    if (!checkTimeAvailability(session)) {
      throw new Exception();
    }

    return sessionDao.update(id, SessionMapper.mapSession(session));
  }

  private boolean checkTimeAvailability(SessionDto session) {
    Optional<Movie> movieOpt = movieDao.findById(session.getMovieId());
    if (movieOpt.isEmpty()) {
      return false;
    }
    LocalTime timeToCheck = LocalDateTime.ofInstant(session.getSessionDate().toInstant(), ZoneId.systemDefault()).toLocalTime();

    List<Session> sessionsFromDb = sessionDao.findAllByCinemaHallAndDate(session.getCinemaHallId(), session.getSessionDate());

    return sessionsFromDb.stream().noneMatch(sessionFromDb -> {
      LocalTime sessionTime = LocalTime.ofInstant(sessionFromDb.getSessionDate().toInstant(), ZoneId.systemDefault());
      LocalTime endOfSession = sessionTime.plusMinutes(movieDao.findById(sessionFromDb.getMovieId()).get().getLength());

      return (sessionTime.equals(timeToCheck) ||
          (sessionTime.isBefore(timeToCheck) && endOfSession.isAfter(timeToCheck)) ||
          (sessionTime.isAfter(timeToCheck) && timeToCheck.plusMinutes(movieOpt.get().getLength()).isAfter(sessionTime)));
    });
  }
}
