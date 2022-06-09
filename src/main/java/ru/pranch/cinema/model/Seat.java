package ru.pranch.cinema.model;

import java.util.UUID;

public class Seat {
  private UUID id;
  private int rowNumber;
  private int place;
  private boolean booked = false;
  private UUID cinemaHallId;

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

  public UUID getCinemaHallId() {
    return cinemaHallId;
  }

  public void setCinemaHallId(UUID cinemaHallId) {
    this.cinemaHallId = cinemaHallId;
  }

  public boolean isBooked() {
    return booked;
  }

  public void setBooked(boolean booked) {
    this.booked = booked;
  }

  @Override
  public String toString() {
    return "Seat{" +
        "id=" + id +
        ", rowNumber=" + rowNumber +
        ", place=" + place +
        ", booked=" + booked +
        ", cinemaHallId=" + cinemaHallId +
        '}';
  }
}
