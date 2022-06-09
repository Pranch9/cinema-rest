package ru.pranch.cinema.dto;

import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.enums.Status;

public class CreateTicketDto {
  private UUID sessionId;
  private List<CreateSeatDto> seatsDto;
  private CreateUserDto createUserDto;
  private Status status;
  private double price;

  public UUID getSessionId() {
    return sessionId;
  }

  public void setSessionId(UUID sessionId) {
    this.sessionId = sessionId;
  }

  public List<CreateSeatDto> getSeatsDto() {
    return seatsDto;
  }

  public void setSeatsDto(List<CreateSeatDto> seatsDto) {
    this.seatsDto = seatsDto;
  }

  public CreateUserDto getUserDto() {
    return createUserDto;
  }

  public void setUserDto(CreateUserDto createUserDto) {
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
