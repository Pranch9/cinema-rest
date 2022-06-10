package ru.pranch.cinema.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.UserDao;
import ru.pranch.cinema.dto.user.CreateUserDto;
import ru.pranch.cinema.dto.user.GetUserDto;
import ru.pranch.cinema.mapper.UserMapper;
import ru.pranch.cinema.model.User;
import ru.pranch.cinema.utils.PasswordUtil;

@Service
public class UserService {
  private final UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<GetUserDto> getUsers() {
    return userDao.findAll().stream().map(UserMapper::mapGetUserDto).toList();
  }

  public GetUserDto getUserById(UUID id) throws Exception {
    return userDao.findById(id).map(UserMapper::mapGetUserDto).orElseThrow(Exception::new);
  }

  public GetUserDto getUserByUsername(String username) throws Exception {
    return userDao.findByUsername(username).map(UserMapper::mapGetUserDto).orElseThrow(Exception::new);
  }

  public GetUserDto getUserByMail(String mail) throws Exception {
    return userDao.findByMail(mail).map(UserMapper::mapGetUserDto).orElseThrow(Exception::new);
  }

  public int deleteUser(UUID id) {
    return userDao.deleteById(id);
  }

  public GetUserDto addUser(CreateUserDto createUserDto) throws Exception {
    if (checkMailAndUsernameAvailability(createUserDto)) {
      throw new Exception();
    }

    User user = UserMapper.mapUser(createUserDto);
    user.setCreationDate(new Date());
    user.setPassword(PasswordUtil.encode(createUserDto.getPassword()));

    return UserMapper.mapGetUserDto(userDao.save(user));
  }

  public GetUserDto editUser(UUID id, CreateUserDto createUserDto) throws Exception {
    if (checkMailAndUsernameAvailability(createUserDto)) {
      throw new Exception();
    }
    User userFromDb = userDao.findById(id)
        .orElseThrow(Exception::new);

    User user = UserMapper.mapUser(createUserDto);
    user.setCreationDate(userFromDb.getCreationDate());
    user.setPassword(PasswordUtil.encode(createUserDto.getPassword()));

    return userDao.update(id, user).map(UserMapper::mapGetUserDto).orElseThrow(Exception::new);
  }

  private boolean checkMailAndUsernameAvailability(CreateUserDto createUserDto) {
    return userDao.findByUsername(createUserDto.getUsername()).isPresent()
        || userDao.findByMail(createUserDto.getMail()).isPresent();
  }
}
