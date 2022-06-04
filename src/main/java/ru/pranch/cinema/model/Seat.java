package ru.pranch.cinema.model;

import java.util.UUID;

public class Seat {
  private UUID id;
  private int rowNumber;
  private int place;
  private CinemaHall cinemaHall;

  public Seat() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getRowNumber() {
    return rowNumber;
  }

  public void setRowNumber(int rowNumber) {
    this.rowNumber = rowNumber;
  }

  public int getPlace() {
    return place;
  }

  public void setPlace(int place) {
    this.place = place;
  }

  public CinemaHall getCinemaHall() {
    return cinemaHall;
  }

  public void setCinemaHall(CinemaHall cinemaHall) {
    this.cinemaHall = cinemaHall;
  }

  @Override
  public String toString() {
    return "Seat{" +
      "id=" + id +
      ", rowNumber=" + rowNumber +
      ", place=" + place +
      ", cinemaHall=" + cinemaHall +
      '}';
  }
}
