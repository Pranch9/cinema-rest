package ru.pranch.cinema.model;

import java.util.UUID;
import ru.pranch.cinema.enums.Status;

public class Ticket {
  private UUID id;
  private UUID sessionId;
  private UUID seatId;
  private UUID userId;
  private Status status;
  private double price;

  public Ticket() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

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

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Ticket{" +
      "id=" + id +
      ", sessionId=" + sessionId +
      ", seatId=" + seatId +
      ", userId=" + userId +
      ", status=" + status +
      ", price=" + price +
      '}';
  }
}
