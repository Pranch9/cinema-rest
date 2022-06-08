package ru.pranch.cinema.dto.cinema_hall;

public class CreateCinemaHallDto {

  private String hallName;
  private int rowsNumber;
  private int placeNumber;

  public String getHallName() {
    return hallName;
  }

  public void setHallName(String hallName) {
    this.hallName = hallName;
  }

  public int getRowsNumber() {
    return rowsNumber;
  }

  public void setRowsNumber(int rowsNumber) {
    this.rowsNumber = rowsNumber;
  }

  public int getPlaceNumber() {
    return placeNumber;
  }

  public void setPlaceNumber(int placeNumber) {
    this.placeNumber = placeNumber;
  }
}
