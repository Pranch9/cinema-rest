package ru.pranch.cinema.model;

import java.util.UUID;

public class Seat {
  private UUID id;
  private int rowNum;
  private int place;
  private CinemaHall cinemaHall;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getRowNum() {
    return rowNum;
  }

  public void setRowNum(int rowNum) {
    this.rowNum = rowNum;
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
      ", rowNum=" + rowNum +
      ", place=" + place +
      ", cinemaHall=" + cinemaHall +
      '}';
  }
}
