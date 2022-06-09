package ru.pranch.cinema.dto;

import java.util.Date;
import java.util.UUID;

public class CreateSessionDto {
  private CreateMovieDto createMovieDto;
  private UUID cinemaHallId;
  private Date sessionDate;

  public CreateMovieDto getMovieDto() {
    return createMovieDto;
  }

  public void setMovieDto(CreateMovieDto createMovieDto) {
    this.createMovieDto = createMovieDto;
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
