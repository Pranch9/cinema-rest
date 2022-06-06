package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.dto.SessionDto;
import ru.pranch.cinema.mapper.SessionMapper;
import ru.pranch.cinema.model.Session;

@Service
public class SessionService {
  private final SessionDao sessionDao;

  @Autowired
  public SessionService(SessionDao sessionDao) {
    this.sessionDao = sessionDao;
  }

  public List<Session> getSessions() {
    return sessionDao.findAll();
  }

  public Optional<Session> getSessionById(UUID id) {
    return sessionDao.findById(id);
  }

  public Session addSession(SessionDto session) {
    return sessionDao.save(SessionMapper.mapSession(session));
  }

  public List<Session> addSessions(List<SessionDto> sessions) {
    return sessionDao.saveAll(sessions
      .stream()
      .map(SessionMapper::mapSession)
      .toList());
  }

  public Optional<Session> editSession(UUID id, SessionDto session) {
    return sessionDao.update(id, SessionMapper.mapSession(session));
  }
}
