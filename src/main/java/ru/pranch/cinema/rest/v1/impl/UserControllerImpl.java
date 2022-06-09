package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.CreateUserDto;
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
  public ResponseEntity<User> getUserByUsername(String username) {
    return null;
  }

  @Override
  public ResponseEntity<User> getUserByMail(String mail) {
    return null;
  }

  @Override
  public ResponseEntity<User> addUser(CreateUserDto createUserDto) {
    return ResponseEntity.ok(userService.addUser(createUserDto));
  }

  @Override
  public ResponseEntity<User> editUser(CreateUserDto createUserDto, UUID id) {
    return userService.editUser(id, createUserDto)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<Integer> deleteUser(UUID id) {
    return null;
  }

}
