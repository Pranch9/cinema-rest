package ru.pranch.cinema.dao.impl;

import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.UserDao;
import ru.pranch.cinema.enums.TableName;
import ru.pranch.cinema.model.User;

@Repository
public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
  private final Jdbi jdbi;

  public UserDaoImpl(Jdbi jdbi) {
    super(jdbi);
    this.jdbi = jdbi;
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return jdbi.withHandle(handle ->
      handle.createQuery("select * " +
          "from " + TableName.USER.getDbTableName() + " " +
          "where username = :username")
        .bind("username", username)
        .mapToBean(User.class)
        .findOne());
  }

  @Override
  public Optional<User> findByMail(String mail) {
    return jdbi.withHandle(handle ->
      handle.createQuery("select * " +
          "from " + TableName.USER.getDbTableName() + " " +
          "where mail = :mail")
        .bind("mail", mail)
        .mapToBean(User.class)
        .findOne());
  }
}
