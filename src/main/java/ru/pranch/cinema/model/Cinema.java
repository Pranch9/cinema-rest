package ru.pranch.cinema.model;

import java.util.UUID;

public class Cinema {
  private UUID id;
  private String cinemaName;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getCinemaName() {
    return cinemaName;
  }

  public void setCinemaName(String cinemaName) {
    this.cinemaName = cinemaName;
  }

  @Override
  public String toString() {
    return "Cinema{" +
      "id=" + id +
      ", cinemaName='" + cinemaName + '\'' +
      '}';
  }
}
