package ru.pranch.cinema.dao;

import java.util.Optional;
import ru.pranch.cinema.model.User;

public interface UserDao extends BasicDao<User> {
  Optional<User> findByUsername(String username);

  Optional<User> findByMail(String mail);
}
