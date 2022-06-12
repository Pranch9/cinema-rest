package ru.pranch.cinema.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.MovieDao;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.dao.SessionInfoDao;
import ru.pranch.cinema.dto.CreateMovieDto;
import ru.pranch.cinema.dto.session.CreateSessionDto;
import ru.pranch.cinema.dto.session.ResponseCreateSessionDto;
import ru.pranch.cinema.dto.session.ResponseUpdateSessionDto;
import ru.pranch.cinema.dto.session.SessionInfoDto;
import ru.pranch.cinema.dto.session.UpdateSessionDto;
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

  public Optional<SessionInfoDto> getSessionInfoById(UUID id) {
    return sessionInfoDao.findById(id);
  }

  public ResponseCreateSessionDto addSession(CreateSessionDto createSessionDto) throws Exception {
    if (checkTimeAvailability(createSessionDto.getSessionDate(), createSessionDto.getCinemaHallId(), createSessionDto.getCreateMovieDto().getLength())) {
      throw new Exception();
    }

    Movie movie = MovieMapper.mapMovie(createSessionDto.getCreateMovieDto());
    Movie movieFromDb = movieDao.findByTitle(movie.getTitle())
      .orElseGet(() -> movieDao
        .save(movie));

    Session session = SessionMapper.mapSession(createSessionDto);
    session.setMovieId(movieFromDb.getId());

    return SessionMapper.mapResponseCreateSession(sessionDao.save(session), movieFromDb);
  }

  public ResponseUpdateSessionDto editSession(UUID id, UUID cinemaHallId, UpdateSessionDto updateSessionDto) throws Exception {
    if (checkTimeAvailability(updateSessionDto.getSessionDate(), cinemaHallId, updateSessionDto.getCreateMovieDto().getLength())) {
      throw new Exception();
    }

    CreateMovieDto createMovieDto = updateSessionDto.getCreateMovieDto();
    Movie movieFromDb = movieDao.findByTitle(createMovieDto.getTitle())
      .orElseGet(() -> movieDao
        .save(MovieMapper.mapMovie(createMovieDto)));

    Session session = SessionMapper.mapSession(updateSessionDto, cinemaHallId);
    session.setMovieId(movieFromDb.getId());

    return SessionMapper.mapResponseUpdateSession(sessionDao.update(id, session).get(), createMovieDto);
  }

  public List<SessionInfoDto> getSessionsInfo(LocalDateTime date, UUID movieId, UUID cinemaId) {
    return sessionInfoDao.findAll(date, movieId, cinemaId);
  }

  public List<SessionInfoDto> getSessionInfoByDate(LocalDateTime sessionDate) {
    return sessionInfoDao.findByDate(sessionDate);
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

  private boolean checkTimeAvailability(LocalDateTime sessionDate, UUID cinemaHallId, int movieLength) {
    LocalTime timeToCheck = sessionDate.toLocalTime();

    List<Session> sessionsFromDb = sessionDao.findAllByCinemaHallAndDate(cinemaHallId, sessionDate);

    return sessionsFromDb
      .stream()
      .anyMatch(sessionFromDb -> {
        LocalTime sessionTime = sessionFromDb.getSessionDate().toLocalTime();
        LocalTime endOfSession = sessionTime.plusMinutes(movieDao.findById(sessionFromDb.getMovieId()).get().getLength());

        return (sessionTime.equals(timeToCheck) ||
          (sessionTime.isBefore(timeToCheck) && endOfSession.isAfter(timeToCheck)) ||
          (sessionTime.isAfter(timeToCheck) && timeToCheck.plusMinutes(movieLength).isAfter(sessionTime)));
      });
  }
}
