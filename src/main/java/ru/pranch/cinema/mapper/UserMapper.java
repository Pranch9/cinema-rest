package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.user.CreateUserDto;
import ru.pranch.cinema.dto.user.GetUserDto;
import ru.pranch.cinema.model.User;

public class UserMapper {
  public static User mapUser(CreateUserDto createUserDto) {
    User user = new User();
    user.setMail(createUserDto.getMail());
    user.setPassword(createUserDto.getPassword());
    user.setUsername(createUserDto.getUsername());

    return user;
  }

  public static GetUserDto mapGetUserDto(User user) {
    GetUserDto getUserDto = new GetUserDto();
    getUserDto.setId(user.getId());
    getUserDto.setCreationDate(user.getCreationDate());
    getUserDto.setUsername(user.getUsername());
    getUserDto.setMail(user.getMail());
    return getUserDto;
  }
}
