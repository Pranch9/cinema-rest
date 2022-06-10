package ru.pranch.cinema.rest.v1.api;

import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pranch.cinema.dto.CreateUserDto;
import ru.pranch.cinema.dto.GetUserDto;

@RequestMapping(value = "/api/v1/users")
public interface IUserController {
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<GetUserDto>> getUsers();

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<GetUserDto> getUser(@PathVariable UUID id);

  @GetMapping(value = "/username/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<GetUserDto> getUserByUsername(@PathVariable String username);

  @GetMapping(value = "/mail/{mail}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<GetUserDto> getUserByMail(@PathVariable String mail);

  @PostMapping(value = "/control", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<GetUserDto> addUser(@RequestBody CreateUserDto createUserDto);

  @PutMapping(value = "/control/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<GetUserDto> editUser(@RequestBody CreateUserDto createUserDto, @PathVariable UUID id);

  @DeleteMapping(value = "/control/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Integer> deleteUser(@PathVariable UUID id);

}
