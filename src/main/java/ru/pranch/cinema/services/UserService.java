package ru.pranch.cinema.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.UserDao;
import ru.pranch.cinema.dto.CreateUserDto;
import ru.pranch.cinema.mapper.UserMapper;
import ru.pranch.cinema.model.User;
import ru.pranch.cinema.utils.PasswordUtil;

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

  public Optional<User> getUserByUsername(String username) {
    return userDao.findByUsername(username);
  }

  public Optional<User> getUserByMail(String mail) {
    return userDao.findByMail(mail);
  }

  public int deleteUser(UUID id) {
    return userDao.deleteById(id);
  }

  public User addUser(CreateUserDto createUserDto) throws Exception {
    if (checkMailAndUsernameAvailability(createUserDto)) {
      throw new Exception();
    }

    User user = UserMapper.mapUser(createUserDto);
    user.setCreationDate(new Date());
    user.setPassword(PasswordUtil.encode(createUserDto.getPassword()));

    return userDao.save(user);
  }

  public Optional<User> editUser(UUID id, CreateUserDto createUserDto) throws Exception {
    if (checkMailAndUsernameAvailability(createUserDto)) {
      throw new Exception();
    }
    User userFromDb = userDao.findById(id)
        .orElseThrow(Exception::new);

    User user = UserMapper.mapUser(createUserDto);
    user.setCreationDate(userFromDb.getCreationDate());
    user.setPassword(PasswordUtil.encode(createUserDto.getPassword()));

    return userDao.update(id, user);
  }

  private boolean checkMailAndUsernameAvailability(CreateUserDto createUserDto) {
    return userDao.findByUsername(createUserDto.getUsername()).isPresent()
        || userDao.findByMail(createUserDto.getMail()).isPresent();
  }
}
