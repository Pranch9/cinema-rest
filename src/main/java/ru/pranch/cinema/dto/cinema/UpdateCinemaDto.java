package ru.pranch.cinema.dto.cinema;

import java.util.List;
import ru.pranch.cinema.dto.CreateAddressDto;
import ru.pranch.cinema.dto.cinema_hall.UpdateCinemaHallDto;

public class UpdateCinemaDto {
  private String cinemaName;
  private CreateAddressDto addressDto;
  private List<UpdateCinemaHallDto> cinemaHallDtos;

  public CreateAddressDto getAddressDto() {
    return addressDto;
  }

  public void setAddressDto(CreateAddressDto addressDto) {
    this.addressDto = addressDto;
  }

  public List<UpdateCinemaHallDto> getCinemaHallDtos() {
    return cinemaHallDtos;
  }

  public void setCinemaHallDtos(List<UpdateCinemaHallDto> cinemaHallDtos) {
    this.cinemaHallDtos = cinemaHallDtos;
  }

  public String getCinemaName() {
    return cinemaName;
  }

  public void setCinemaName(String cinemaName) {
    this.cinemaName = cinemaName;
  }
}
