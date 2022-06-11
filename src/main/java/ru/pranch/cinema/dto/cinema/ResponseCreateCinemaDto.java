package ru.pranch.cinema.dto.cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.dto.CreateAddressDto;
import ru.pranch.cinema.dto.cinema_hall.CreateCinemaHallDto;
import ru.pranch.cinema.dto.cinema_hall.GetCinemaHallDto;
import ru.pranch.cinema.model.Address;

public class ResponseCreateCinemaDto {
  private UUID id;
  private String cinemaName;
  private Address address;
  @JsonProperty(value = "cinemaHalls")
  private List<GetCinemaHallDto> getCinemaHallDtos;

  public String getCinemaName() {
    return cinemaName;
  }

  public void setCinemaName(String cinemaName) {
    this.cinemaName = cinemaName;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<GetCinemaHallDto> getGetCinemaHallDtos() {
    return getCinemaHallDtos;
  }

  public void setGetCinemaHallDtos(List<GetCinemaHallDto> getCinemaHallDtos) {
    this.getCinemaHallDtos = getCinemaHallDtos;
  }
}
