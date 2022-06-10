package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.CreateUserDto;
import ru.pranch.cinema.model.User;

public class UserMapper {
  public static User mapUser(CreateUserDto createUserDto) {
    User user = new User();
    user.setMail(createUserDto.getMail());
    user.setPassword(createUserDto.getPassword());
    user.setUsername(createUserDto.getUsername());

    return user;
  }
}
