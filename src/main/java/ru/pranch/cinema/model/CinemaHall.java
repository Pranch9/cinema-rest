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

  public CinemaHall(String hallName, int rowsNumber, int placesNumber, UUID cinemaId) {
    this.hallName = hallName;
    this.rowsNumber = rowsNumber;
    this.placesNumber = placesNumber;
    this.cinemaId = cinemaId;
  }

  public CinemaHall(UUID id, String hallName, int rowsNumber, int placesNumber, UUID cinemaId) {
    this.id = id;
    this.hallName = hallName;
    this.rowsNumber = rowsNumber;
    this.placesNumber = placesNumber;
    this.cinemaId = cinemaId;
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
      ", placesNumber=" + placesNumber +
      ", cinemaId=" + cinemaId +
      '}';
  }
}
