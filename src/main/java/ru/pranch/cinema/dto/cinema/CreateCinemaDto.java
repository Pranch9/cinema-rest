package ru.pranch.cinema.dto.cinema;

import java.util.List;
import ru.pranch.cinema.dto.CreateAddressDto;
import ru.pranch.cinema.dto.cinema_hall.CreateCinemaHallDto;

public class CreateCinemaDto {
  private String cinemaName;
  private CreateAddressDto createAddressDto;
  private List<CreateCinemaHallDto> cinemaHallDtos;

  public CreateAddressDto getCreateAddressDto() {
    return createAddressDto;
  }

  public void setCreateAddressDto(CreateAddressDto createAddressDto) {
    this.createAddressDto = createAddressDto;
  }

  public List<CreateCinemaHallDto> getCinemaHallDtos() {
    return cinemaHallDtos;
  }

  public void setCinemaHallDtos(List<CreateCinemaHallDto> cinemaHallDtos) {
    this.cinemaHallDtos = cinemaHallDtos;
  }

  public String getCinemaName() {
    return cinemaName;
  }

  public void setCinemaName(String cinemaName) {
    this.cinemaName = cinemaName;
  }
}
