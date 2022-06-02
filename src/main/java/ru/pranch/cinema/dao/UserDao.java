package ru.pranch.cinema.dao;

import java.util.Optional;
import java.util.UUID;
import ru.pranch.cinema.model.User;

public interface UserDao extends BasicDao<User, UUID> {
  Optional<User> findByUsername(String name);

  Optional<User> findByMail(String mail);
}
