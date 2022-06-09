package ru.pranch.cinema.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.SessionInfoDao;
import ru.pranch.cinema.dto.SessionInfoDto;

@Repository
public class SessionInfoDaoImpl extends BasicDaoImpl<SessionInfoDto> implements SessionInfoDao {

  public SessionInfoDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  private final String selectAndJoinExpression = """
          select s.id as id, c.name as cinemaName, a.street as street, a.house_number as houseNumber, a.city as city, cr.name as cinemaRoomName, m.title as movieTitle, m.length as length, s.session_date as sessionDate
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
  public List<SessionInfoDto> findByDate(Date date) {
    String sql = selectAndJoinExpression + "where date(session_date) = :date";

    return jdbi.withHandle(handle -> handle
        .createQuery(sql)
        .bind("date", date)
        .mapToBean(SessionInfoDto.class)
        .list());
  }

  @Override
  public List<SessionInfoDto> findByValue(String value) {
    String sql = selectAndJoinExpression + """
        where c.name like :value or a.city like :value or a.street like :value or c.name like :value or cr.name like 
        :value or m.title like :value
        """;

    return jdbi.withHandle(handle -> handle
        .createQuery(sql)
        .bind("value", value)
        .mapToBean(SessionInfoDto.class)
        .list());
  }

  @Override
  public List<SessionInfoDto> findAll() {
    return jdbi.withHandle(handle -> handle
        .createQuery(selectAndJoinExpression)
        .mapToBean(SessionInfoDto.class)
        .list());
  }
}
