package ru.pranch.cinema.dto.cinema_hall;

import java.util.UUID;

public class GetCinemaHallDto {
  private UUID id;
  private String hallName;
  private int rowsNumber;
  private int placesNumber;

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

  public int getRowsNumber() {
    return rowsNumber;
  }

  public void setRowsNumber(int rowsNumber) {
    this.rowsNumber = rowsNumber;
  }

  public int getPlacesNumber() {
    return placesNumber;
  }

  public void setPlacesNumber(int placesNumber) {
    this.placesNumber = placesNumber;
  }
}
