package ru.pranch.cinema.model;

import java.util.UUID;

public class CinemaHall {
  private UUID id;
  private String name;
  private int rowsNum;
  private int placeNumber;
  private Cinema cinema;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
      ", name='" + name + '\'' +
      ", rowsNum=" + rowsNum +
      ", placeNumber=" + placeNumber +
      ", cinema=" + cinema +
      '}';
  }
}
