package ru.pranch.cinema.model;

import java.util.UUID;
import ru.pranch.cinema.enums.SeatStatus;

public class Seat {
  private UUID id;
  private int rowNumber;
  private int place;
  private SeatStatus seatStatus;
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

  public SeatStatus getSeatStatus() {
    return seatStatus;
  }

  public void setSeatStatus(SeatStatus seatStatus) {
    this.seatStatus = seatStatus;
  }

  public UUID getCinemaHallId() {
    return cinemaHallId;
  }

  public void setCinemaHallId(UUID cinemaHallId) {
    this.cinemaHallId = cinemaHallId;
  }

  @Override
  public String toString() {
    return "Seat{" +
      "id=" + id +
      ", rowNumber=" + rowNumber +
      ", place=" + place +
      ", cinemaHallId=" + cinemaHallId +
      '}';
  }
}
