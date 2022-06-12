package ru.pranch.cinema.model;

import java.util.UUID;

public class CinemaHall {
  private UUID id;
  private String hallName;
  private int rowsNumber;
  private int placesNumber;
  private UUID cinemaId;

  public CinemaHall() {
    this.id = UUID.randomUUID();
  }

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

  public UUID getCinemaId() {
    return cinemaId;
  }

  public void setCinemaId(UUID cinemaId) {
    this.cinemaId = cinemaId;
  }

  @Override
  public String toString() {
    return "CinemaHall{" +
      "id=" + id +
      ", hallName='" + hallName + '\'' +
      ", rowsNumber=" + rowsNumber +
      ", placeNumber=" + placesNumber +
      ", cinemaId=" + cinemaId +
      '}';
  }
}
