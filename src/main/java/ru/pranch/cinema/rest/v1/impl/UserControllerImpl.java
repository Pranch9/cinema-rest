package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.UserDto;
import ru.pranch.cinema.model.User;
import ru.pranch.cinema.rest.v1.api.IUserController;

@RestController
public class UserControllerImpl implements IUserController {
  @Override
  public ResponseEntity<List<User>> getUsers() {
    return null;
  }

  @Override
  public ResponseEntity<User> getUser(UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<User> addUser(UserDto userDto) {
    return null;
  }

  @Override
  public ResponseEntity<User> editUser(UserDto userDto, UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<List<User>> addUsers(List<UserDto> usersDto) {
    return null;
  }
}
