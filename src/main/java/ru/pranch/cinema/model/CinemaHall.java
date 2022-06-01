package ru.pranch.cinema.model;

import java.util.UUID;

public class CinemaHall {
  private UUID id;
  private String hallName;
  private int rowsNum;
  private int placeNumber;
  private Cinema cinema;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getHallName() {
    return hallName;
  }

  public void setHallName(String hallName) {
    this.hallName = hallName;
  }

  public int getRowsNum() {
    return rowsNum;
  }

  public void setRowsNum(int rowsNum) {
    this.rowsNum = rowsNum;
  }

  public int getPlaceNumber() {
    return placeNumber;
  }

  public void setPlaceNumber(int placeNumber) {
    this.placeNumber = placeNumber;
  }

  public Cinema getCinema() {
    return cinema;
  }

  public void setCinema(Cinema cinema) {
    this.cinema = cinema;
  }

  @Override
  public String toString() {
    return "CinemaHall{" +
      "id=" + id +
      ", hallName='" + hallName + '\'' +
      ", rowsNum=" + rowsNum +
      ", placeNumber=" + placeNumber +
      ", cinema=" + cinema +
      '}';
  }
}
