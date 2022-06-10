package ru.pranch.cinema.dto.cinema;

import java.util.List;
import ru.pranch.cinema.dto.CreateAddressDto;
import ru.pranch.cinema.dto.cinema_hall.UpdateCinemaHallDto;

public class UpdateCinemaDto {
  private String cinemaName;
  private CreateAddressDto createAddressDto;
  private List<UpdateCinemaHallDto> updateCinemaHallDtos;

  public CreateAddressDto getCreateAddressDto() {
    return createAddressDto;
  }

  public void setCreateAddressDto(CreateAddressDto createAddressDto) {
    this.createAddressDto = createAddressDto;
  }

  public List<UpdateCinemaHallDto> getUpdateCinemaHallDtos() {
    return updateCinemaHallDtos;
  }

  public void setUpdateCinemaHallDtos(List<UpdateCinemaHallDto> updateCinemaHallDtos) {
    this.updateCinemaHallDtos = updateCinemaHallDtos;
  }

  public String getCinemaName() {
    return cinemaName;
  }

  public void setCinemaName(String cinemaName) {
    this.cinemaName = cinemaName;
  }
}
