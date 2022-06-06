package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.UserDao;
import ru.pranch.cinema.dto.UserDto;
import ru.pranch.cinema.mapper.UserMapper;
import ru.pranch.cinema.model.User;

@Service
public class UserService {
  private final UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<User> getUsers() {
    return userDao.findAll();
  }

  public Optional<User> getUserById(UUID id) {
    return userDao.findById(id);
  }

  public User addUser(UserDto user) {
    return userDao.save(UserMapper.mapUser(user));
  }

  public List<User> addUsers(List<UserDto> users) {
    return userDao.saveAll(users
      .stream()
      .map(UserMapper::mapUser)
      .toList());
  }

  public Optional<User> editUser(UUID id, UserDto user) {
    return userDao.update(id, UserMapper.mapUser(user));
  }
}
