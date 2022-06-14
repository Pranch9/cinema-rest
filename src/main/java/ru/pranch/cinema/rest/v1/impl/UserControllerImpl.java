package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.user.CreateUserDto;
import ru.pranch.cinema.dto.user.GetUserDto;
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
  public ResponseEntity<List<GetUserDto>> getUsers(String username, String mail) {
    return ResponseEntity.ok(userService.getUsers());
  }

  @Override
  public ResponseEntity<GetUserDto> getUser(UUID id) {
    try {
      return ResponseEntity.ok(userService.getUserById(id));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<GetUserDto> addUser(CreateUserDto createUserDto) {
    try {
      return ResponseEntity.ok(userService.addUser(createUserDto));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<GetUserDto> editUser(CreateUserDto createUserDto, UUID id) {
    try {
      return ResponseEntity.ok(userService.editUser(id, createUserDto));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<Integer> deleteUser(UUID id) {
    return ResponseEntity.ok(userService.deleteUser(id));
  }

}
