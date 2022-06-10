package ru.pranch.cinema.dto.cinema;

import java.util.List;
import ru.pranch.cinema.dto.CreateAddressDto;
import ru.pranch.cinema.dto.cinema_hall.CreateCinemaHallDto;

public class CreateCinemaDto {
  private String cinemaName;
  private CreateAddressDto createAddressDto;
  private List<CreateCinemaHallDto> createCinemaHallDtos;

  public String getCinemaName() {
    return cinemaName;
  }

  public void setCinemaName(String cinemaName) {
    this.cinemaName = cinemaName;
  }

  public CreateAddressDto getCreateAddressDto() {
    return createAddressDto;
  }

  public void setCreateAddressDto(CreateAddressDto createAddressDto) {
    this.createAddressDto = createAddressDto;
  }

  public List<CreateCinemaHallDto> getCreateCinemaHallDtos() {
    return createCinemaHallDtos;
  }

  public void setCreateCinemaHallDtos(List<CreateCinemaHallDto> createCinemaHallDtos) {
    this.createCinemaHallDtos = createCinemaHallDtos;
  }
}
