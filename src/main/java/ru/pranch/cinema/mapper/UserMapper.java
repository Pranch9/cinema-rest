package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.UserDto;
import ru.pranch.cinema.model.User;

public class UserMapper {
  public static User mapUser(UserDto userDto) {
    User user = new User();
    user.setCreationDate(userDto.getCreationDate());
    user.setMail(userDto.getMail());
    user.setPassword(userDto.getPassword());
    user.setUsername(userDto.getUsername());

    return user;
  }
}
