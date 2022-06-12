package ru.pranch.cinema.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.pranch.cinema.dao.SessionInfoDao;
import ru.pranch.cinema.dto.session.SessionInfoDto;

@Repository
public class SessionInfoDaoImpl extends BasicDaoImpl<SessionInfoDto> implements SessionInfoDao {

  public SessionInfoDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  private final String selectAndJoinExpression = """
    select s.id as id, c.cinema_name as cinemaName, a.street as street, a.house_number as houseNumber, a.city as city, cr.hall_name as cinemaRoomName, m.title as movieTitle, m.length as length, s.session_date as sessionDate
    from addresses a 
    join cinemas c on a.id = c.address_id 
    join cinema_halls cr on c.id = cr.cinema_id
    join sessions s on cr.id = s.cinema_hall_id 
    join movies m on m.id = s.movie_id 
    """;

  @Override
  public List<SessionInfoDto> findByCinema(UUID cinemaId) {
    String sql = selectAndJoinExpression + "where c.id = :cinemaId";

    return jdbi.withHandle(handle -> handle
      .createQuery(sql)
      .bind("cinemaId", cinemaId)
      .mapToBean(SessionInfoDto.class)
      .list());
  }

  @Override
  public List<SessionInfoDto> findByMovie(UUID movieId) {
    String sql = selectAndJoinExpression + "where m.id = :movieId";

    return jdbi.withHandle(handle -> handle
      .createQuery(sql)
      .bind("movieId", movieId)
      .mapToBean(SessionInfoDto.class)
      .list());
  }

  @Override
  public List<SessionInfoDto> findByDate(LocalDateTime date) {
    String sql = selectAndJoinExpression + "where date(session_date) = :date";

    return jdbi.withHandle(handle -> handle
      .createQuery(sql)
      .bind("date", date)
      .mapToBean(SessionInfoDto.class)
      .list());
  }

  @Override
  public Optional<SessionInfoDto> findById(UUID id) {
    String sql = selectAndJoinExpression + "where s.id = :id;";

    return jdbi.withHandle(handle -> handle
      .createQuery(sql)
      .bind("id", id)
      .mapToBean(SessionInfoDto.class)
      .findOne());
  }

  @Override
  public List<SessionInfoDto> findAll(LocalDateTime date, UUID movieId, UUID cinemaId) {
    StringJoiner joiner = new StringJoiner(" and ", " where ", ";");
    String sql = selectAndJoinExpression;
    if (!ObjectUtils.isEmpty(date) || !ObjectUtils.isEmpty(date) || !ObjectUtils.isEmpty(date)) {
      if (!ObjectUtils.isEmpty(date)) {
        joiner.add("session_date ='" + date + "'");
      }
      if (!ObjectUtils.isEmpty(movieId)) {
        joiner.add("movie_id ='" + movieId + "'");
      }
      if (!ObjectUtils.isEmpty(cinemaId)) {
        joiner.add("cinema_id ='" + cinemaId + "'");
      }
      sql += joiner.toString();
    }

    String finalSql = sql;
    return jdbi.withHandle(handle -> handle
      .createQuery(finalSql)
      .mapToBean(SessionInfoDto.class)
      .list());
  }
}
