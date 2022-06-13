package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ru.pranch.cinema.dao.CinemaInfoDao;
import ru.pranch.cinema.dto.cinema.CinemaInfoDto;

@Repository
public class CinemaInfoDaoImpl extends BasicDaoImpl<CinemaInfoDto> implements CinemaInfoDao {

  public CinemaInfoDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  private final String selectAndJoinExpression = """
    select c.id as id, c.cinema_name as cinemaName, a.street as street, a.house_number as houseNumber, a.city as city, a.zip_code as zipCode 
    from addresses a join cinemas c on a.id = c.address_id
    """;

  @Override
  public List<CinemaInfoDto> findByCity(String city) {
    String sql = selectAndJoinExpression + " where lower(a.city) like :city;";
    return jdbi.withHandle(handle -> handle
      .createQuery(sql)
      .bind("city", city.toLowerCase())
      .mapToBean(CinemaInfoDto.class)
      .list());

  }

  @Override
  public Optional<CinemaInfoDto> findByName(String name) {
    String sql = selectAndJoinExpression + " where lower(c.cinema_name) like :name;";

    return jdbi.withHandle(handle -> handle
      .createQuery(sql)
      .bind("name", name.toLowerCase())
      .mapToBean(CinemaInfoDto.class)
      .findOne());
  }

  @Override
  public Optional<CinemaInfoDto> findById(UUID id) {
    String sql = selectAndJoinExpression + " where c.id = :id;";

    return jdbi.withHandle(handle -> handle
      .createQuery(sql)
      .bind("id", id)
      .mapToBean(CinemaInfoDto.class)
      .findOne());
  }

  @Override
  public List<CinemaInfoDto> findAll(String name, String city) {
    StringJoiner joiner = new StringJoiner(" and ", " where ", ";");
    String sql = selectAndJoinExpression;
    if (StringUtils.hasText(name) || StringUtils.hasText(city)) {
      if (StringUtils.hasText(name)) {
        joiner.add("lower(cinema_name) = '" + name.toLowerCase() + "'");
      }
      if (StringUtils.hasText(city)) {
        joiner.add("lower(city) = '" + city.toLowerCase() + "'");
      }
      sql += joiner.toString();
    }

    String finalSql = sql;
    return jdbi.withHandle(handle -> handle
      .createQuery(finalSql)
      .mapToBean(CinemaInfoDto.class)
      .list());
  }
}
