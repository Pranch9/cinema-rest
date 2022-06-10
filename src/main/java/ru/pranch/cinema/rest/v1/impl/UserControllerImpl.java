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
    return userService.getUserByUsername(username)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<User> getUserByMail(String mail) {
    return userService.getUserByMail(mail)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<User> addUser(CreateUserDto createUserDto) {
    try {
      return ResponseEntity.ok(userService.addUser(createUserDto));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<User> editUser(CreateUserDto createUserDto, UUID id) {
    try {
      return userService.editUser(id, createUserDto)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<Integer> deleteUser(UUID id) {
    return ResponseEntity.ok(userService.deleteUser(id));
  }

}
