package ru.pranch.cinema.dto.session;

import java.time.LocalDateTime;
import java.util.UUID;

public class SessionInfoDto {
  private UUID id;
  private String cinemaName;
  private String cinemaRoomName;
  private String movieTitle;
  private Integer length;
  private LocalDateTime sessionDate;

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

  public String getCinemaRoomName() {
    return cinemaRoomName;
  }

  public void setCinemaRoomName(String cinemaRoomName) {
    this.cinemaRoomName = cinemaRoomName;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public LocalDateTime getSessionDate() {
    return sessionDate;
  }

  public void setSessionDate(LocalDateTime sessionDate) {
    this.sessionDate = sessionDate;
  }
}
