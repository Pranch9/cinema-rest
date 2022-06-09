package ru.pranch.cinema.dto;

import java.util.UUID;

public class CreateSeatDto {
  private int rowNumber;
  private int place;
  private boolean isBooked = false;
  private UUID cinemaHallId;

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

  public UUID getCinemaHallId() {
    return cinemaHallId;
  }

  public void setCinemaHallId(UUID cinemaHallId) {
    this.cinemaHallId = cinemaHallId;
  }

  public boolean isBooked() {
    return isBooked;
  }

  public void setBooked(boolean booked) {
    isBooked = booked;
  }
}
