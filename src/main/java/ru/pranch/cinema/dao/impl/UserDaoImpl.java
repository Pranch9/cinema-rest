package ru.pranch.cinema.dao.impl;

import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.UserDao;
import ru.pranch.cinema.model.User;

@Repository
public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
  private final Jdbi jdbi;

  public UserDaoImpl(Jdbi jdbi) {
    super(jdbi);
    this.jdbi = jdbi;
  }

  @Override
  public Optional<User> findByUsername(String name) {
    return Optional.empty();
  }

  @Override
  public Optional<User> findByMail(String mail) {
    return Optional.empty();
  }
}
