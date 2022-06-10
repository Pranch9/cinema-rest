package ru.pranch.cinema.dto;

import java.util.List;
import java.util.UUID;
import ru.pranch.cinema.enums.Status;
import ru.pranch.cinema.model.Seat;

public class CreateTicketDto {
  private UUID sessionId;
  private List<Seat> seats;
  private CreateUserDto createUserDto;
  private Status status;
  private double price;

  public UUID getSessionId() {
    return sessionId;
  }

  public void setSessionId(UUID sessionId) {
    this.sessionId = sessionId;
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public void setSeats(List<Seat> seats) {
    this.seats = seats;
  }

  public CreateUserDto getCreateUserDto() {
    return createUserDto;
  }

  public void setCreateUserDto(CreateUserDto createUserDto) {
    this.createUserDto = createUserDto;
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
