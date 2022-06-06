package ru.pranch.cinema.dto;

import java.util.Date;
import java.util.UUID;

public class SessionDto {
  private UUID movieId;
  private UUID cinemaHallId;
  private Date sessionDate;

  public UUID getMovieId() {
    return movieId;
  }

  public void setMovieId(UUID movieId) {
    this.movieId = movieId;
  }

  public UUID getCinemaHallId() {
    return cinemaHallId;
  }

  public void setCinemaHallId(UUID cinemaHallId) {
    this.cinemaHallId = cinemaHallId;
  }

  public Date getSessionDate() {
    return sessionDate;
  }

  public void setSessionDate(Date sessionDate) {
    this.sessionDate = sessionDate;
  }
}
