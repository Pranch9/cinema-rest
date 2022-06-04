package ru.pranch.cinema.enums;

public enum TableName {
  CINEMA("cinemas"),
  CINEMAHALL("cinema_halls"),
  MOVIE("movies"),
  SEAT("seats"),
  SESSION("sessions"),
  TICKET("tickets"),
  USER("users");

  private final String dbTableName;

  TableName(String dbTableName) {
    this.dbTableName = dbTableName;
  }

  public String getDbTableName() {
    return dbTableName;
  }
}
