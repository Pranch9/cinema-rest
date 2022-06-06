package ru.pranch.cinema.model;

import java.util.Date;
import java.util.UUID;

public class Session {
  private UUID id;
  private UUID movieId;
  private UUID cinemaHallId;
  private Date sessionDate;

  public Session() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

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

  @Override
  public String toString() {
    return "Session{" +
      "id=" + id +
      ", movieId=" + movieId +
      ", cinemaHallId=" + cinemaHallId +
      ", date=" + sessionDate +
      '}';
  }
}
