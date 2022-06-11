package ru.pranch.cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.dto.seat.CreateSeatDto;
import ru.pranch.cinema.dto.user.CreateUserDto;
import ru.pranch.cinema.enums.Status;
import ru.pranch.cinema.model.Seat;

public class CreateTicketDto {
  private UUID sessionId;
  @JsonProperty(value = "seats")
  private List<CreateSeatDto> createSeatDtos;
  @JsonProperty(value = "user")
  private CreateUserDto createUserDto;
  private Status status;
  private double price;

  public UUID getSessionId() {
    return sessionId;
  }

  public void setSessionId(UUID sessionId) {
    this.sessionId = sessionId;
  }

  public List<CreateSeatDto> getCreateSeatDtos() {
    return createSeatDtos;
  }

  public void setCreateSeatDtos(List<CreateSeatDto> createSeatDtos) {
    this.createSeatDtos = createSeatDtos;
  }

  public CreateUserDto getCreateUserDto() {
    return createUserDto;
  }

  public void setCreateUserDto(CreateUserDto createUserDto) {
    this.createUserDto = createUserDto;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
