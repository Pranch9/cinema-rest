package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.CinemaInfoDao;
import ru.pranch.cinema.dto.cinema.CinemaInfoDto;
import ru.pranch.cinema.model.Cinema;

@Repository
public class CinemaInfoDaoImpl extends BasicDaoImpl<Cinema> implements CinemaInfoDao {

  public CinemaInfoDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  private final String selectAndJoinExpression = """
      SELECT c.name as name, a.street as street, a.house_number as houseNumber, a.city as city, a.zip_code as zipCode 
      FROM addresses a join cinemas c on a.id = c.address_id
      """;

  @Override
  public List<CinemaInfoDto> findByCity(String city) {
    String sql = selectAndJoinExpression + " WHERE a.city LIKE :city;";
    return jdbi.withHandle(handle -> handle
        .createQuery(sql)
        .bind("city", city)
        .mapToBean(CinemaInfoDto.class)
        .list());

  }

  @Override
  public Optional<CinemaInfoDto> findByName(String name) {
    String sql = selectAndJoinExpression + " WHERE c.name LIKE :name;";

    return jdbi.withHandle(handle -> handle
        .createQuery(sql)
        .bind("name", name)
        .mapToBean(CinemaInfoDto.class)
        .findOne());
  }
}
