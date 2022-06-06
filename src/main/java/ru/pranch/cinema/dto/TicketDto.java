package ru.pranch.cinema.dto;

import java.util.UUID;
import ru.pranch.cinema.enums.Status;

public class TicketDto {
  private UUID sessionId;
  private UUID seatId;
  private UUID userId;
  private Status status;
  private double price;

  public UUID getSessionId() {
    return sessionId;
  }

  public void setSessionId(UUID sessionId) {
    this.sessionId = sessionId;
  }

  public UUID getSeatId() {
    return seatId;
  }

  public void setSeatId(UUID seatId) {
    this.seatId = seatId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
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
