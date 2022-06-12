package ru.pranch.cinema.dto.session;

import java.time.LocalDateTime;
import java.util.UUID;
import ru.pranch.cinema.model.Movie;

public class ResponseCreateSessionDto {
  private UUID id;
  private Movie movie;
  private UUID cinemaHallId;
  private LocalDateTime sessionDate;

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

  public UUID getCinemaHallId() {
    return cinemaHallId;
  }

  public void setCinemaHallId(UUID cinemaHallId) {
    this.cinemaHallId = cinemaHallId;
  }

  public LocalDateTime getSessionDate() {
    return sessionDate;
  }

  public void setSessionDate(LocalDateTime sessionDate) {
    this.sessionDate = sessionDate;
  }
}
