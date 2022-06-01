package ru.pranch.cinema.model;

import java.util.Date;
import java.util.UUID;

public class Session {
  private UUID id;
  private Movie movie;
  private Cinema cinema;
  private Date sessionDate;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public Cinema getCinema() {
    return cinema;
  }

  public void setCinema(Cinema cinema) {
    this.cinema = cinema;
  }

  public Date getSessionDate() {
    return sessionDate;
  }

  public void setSessionDate(Date sessionDate) {
    this.sessionDate = sessionDate;
  }

  @Override
  public String toString() {
    return "Screening{" +
      "id=" + id +
      ", movie=" + movie +
      ", cinema=" + cinema +
      ", date=" + sessionDate +
      '}';
  }
}
