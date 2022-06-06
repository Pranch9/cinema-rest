package ru.pranch.cinema.dto;

import java.util.UUID;

public class CinemaHallDto {
  private String hallName;
  private int rowsNumber;
  private int placeNumber;
  private UUID cinemaId;

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

  public UUID getCinemaId() {
    return cinemaId;
  }

  public void setCinemaId(UUID cinemaId) {
    this.cinemaId = cinemaId;
  }
}
