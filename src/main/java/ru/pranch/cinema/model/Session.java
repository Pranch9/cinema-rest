package ru.pranch.cinema.model;

import java.util.Date;
import java.util.UUID;

public class Session {
  private UUID id;
  private Movie movie;
  private CinemaHall cinemaHall;
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

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public CinemaHall getCinemaHall() {
    return cinemaHall;
  }

  public void setCinemaHall(CinemaHall cinemaHall) {
    this.cinemaHall = cinemaHall;
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
      ", cinemaHall=" + cinemaHall +
      ", date=" + sessionDate +
      '}';
  }
}
