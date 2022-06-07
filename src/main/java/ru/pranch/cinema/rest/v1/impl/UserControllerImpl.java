package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.UserDto;
import ru.pranch.cinema.model.User;
import ru.pranch.cinema.rest.v1.api.IUserController;
import ru.pranch.cinema.services.UserService;

@RestController
public class UserControllerImpl implements IUserController {
  private final UserService userService;

  @Autowired
  public UserControllerImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok(userService.getUsers());
  }

  @Override
  public ResponseEntity<User> getUser(UUID id) {
    return userService.getUserById(id)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<User> addUser(UserDto userDto) {
    return ResponseEntity.ok(userService.addUser(userDto));
  }

  @Override
  public ResponseEntity<User> editUser(UserDto userDto, UUID id) {
    return userService.editUser(id, userDto)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<List<User>> addUsers(List<UserDto> usersDto) {
    return ResponseEntity.ok(userService.addUsers(usersDto));
  }
}
