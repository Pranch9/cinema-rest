package ru.pranch.cinema.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.MovieDao;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.dao.SessionInfoDao;
import ru.pranch.cinema.dto.CreateMovieDto;
import ru.pranch.cinema.dto.CreateSessionDto;
import ru.pranch.cinema.dto.SessionInfoDto;
import ru.pranch.cinema.mapper.MovieMapper;
import ru.pranch.cinema.mapper.SessionMapper;
import ru.pranch.cinema.model.Movie;
import ru.pranch.cinema.model.Session;

@Service
public class SessionService {
  private final SessionDao sessionDao;
  private final SessionInfoDao sessionInfoDao;
  private final MovieDao movieDao;

  @Autowired
  public SessionService(SessionDao sessionDao, SessionInfoDao sessionInfoDao, MovieDao movieDao) {
    this.sessionDao = sessionDao;
    this.sessionInfoDao = sessionInfoDao;
    this.movieDao = movieDao;
  }


  public Optional<Session> getSessionById(UUID id) {
    return sessionDao.findById(id);
  }

  public Session addSession(CreateSessionDto createSessionDto) throws Exception {
    if (!checkTimeAvailability(createSessionDto)) {
      throw new Exception();
    }

    Movie movie = MovieMapper.mapMovie(createSessionDto.getCreateMovieDto());
    Movie movieFromDb = movieDao.findByTitle(movie.getTitle())
        .orElseGet(() -> movieDao
            .save(movie));

    Session session = SessionMapper.mapSession(createSessionDto);
    session.setMovieId(movieFromDb.getId());

    return sessionDao.save(session);
  }

  public Optional<Session> editSession(UUID id, CreateSessionDto createSessionDto) throws Exception {
    if (!checkTimeAvailability(createSessionDto)) {
      throw new Exception();
    }

    CreateMovieDto createMovieDto = createSessionDto.getCreateMovieDto();
    Movie movieFromDb = movieDao.findByTitle(createMovieDto.getTitle())
        .orElseGet(() -> movieDao
            .save(MovieMapper.mapMovie(createMovieDto)));

    Session session = SessionMapper.mapSession(createSessionDto);
    session.setMovieId(movieFromDb.getId());

    return sessionDao.update(id, session);
  }

  public List<SessionInfoDto> getSessionsInfo() {
    return sessionInfoDao.findAll();
  }

  public List<SessionInfoDto> getSessionInfoByDate(Date sessionDate) {
    return sessionInfoDao.findByDate(sessionDate);
  }

  public List<SessionInfoDto> getSessionInfoByValue(String value) {
    return sessionInfoDao.findByValue(value);
  }

  public List<SessionInfoDto> getSessionInfoByMovie(UUID movieId) {
    return sessionInfoDao.findByMovie(movieId);
  }

  public List<SessionInfoDto> getSessionInfoByCinemaId(UUID cinemaId) {
    return sessionInfoDao.findByCinema(cinemaId);
  }


  public int deleteSession(UUID id) {
    return sessionDao.deleteById(id);
  }

  private boolean checkTimeAvailability(CreateSessionDto session) {
    LocalTime timeToCheck = LocalDateTime.ofInstant(session.getSessionDate().toInstant(), ZoneId.systemDefault()).toLocalTime();

    List<Session> sessionsFromDb = sessionDao.findAllByCinemaHallAndDate(session.getCinemaHallId(), session.getSessionDate());

    return sessionsFromDb
        .stream()
        .noneMatch(sessionFromDb -> {
          LocalTime sessionTime = LocalTime.ofInstant(sessionFromDb.getSessionDate().toInstant(), ZoneId.systemDefault());
          LocalTime endOfSession = sessionTime.plusMinutes(movieDao.findById(sessionFromDb.getMovieId()).get().getLength());

          return (sessionTime.equals(timeToCheck) ||
              (sessionTime.isBefore(timeToCheck) && endOfSession.isAfter(timeToCheck)) ||
              (sessionTime.isAfter(timeToCheck) && timeToCheck.plusMinutes(session.getCreateMovieDto().getLength()).isAfter(sessionTime)));
        });
  }
}
