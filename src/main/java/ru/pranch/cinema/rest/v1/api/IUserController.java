package ru.pranch.cinema.rest.v1.api;

import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pranch.cinema.dto.UserDto;
import ru.pranch.cinema.model.User;

@RequestMapping(value = "/api/v1")
public interface IUserController {
  @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<User>> getUsers();

  @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<User> getUser(@PathVariable UUID id);

  @PostMapping(value = "/user/add", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<User> addUser(@RequestBody UserDto userDto);

  @PutMapping(value = "/user/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<User> editUser(@RequestBody UserDto userDto, @PathVariable UUID id);

  @PostMapping(value = "/users/add")
  ResponseEntity<List<User>> addUsers(@RequestBody List<UserDto> usersDto);
}