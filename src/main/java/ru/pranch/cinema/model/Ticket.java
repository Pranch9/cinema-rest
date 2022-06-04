package ru.pranch.cinema.model;

import java.util.UUID;
import ru.pranch.cinema.enums.Status;

public class Ticket {
  private UUID id;
  private Session session;
  private Seat seat;
  private User user;
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

  public Session getScreening() {
    return session;
  }

  public void setScreening(Session session) {
    this.session = session;
  }

  public Seat getSeat() {
    return seat;
  }

  public void setSeat(Seat seat) {
    this.seat = seat;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
      ", screening=" + session +
      ", seat=" + seat +
      ", user=" + user +
      ", status=" + status +
      ", price=" + price +
      '}';
  }
}
