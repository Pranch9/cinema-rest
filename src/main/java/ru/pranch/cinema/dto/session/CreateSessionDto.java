package ru.pranch.cinema.dto.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.UUID;
import ru.pranch.cinema.dto.CreateMovieDto;

public class CreateSessionDto {
  @JsonProperty(value = "movie")
  private CreateMovieDto createMovieDto;
  private UUID cinemaHallId;
  private LocalDateTime sessionDate;

  public CreateMovieDto getCreateMovieDto() {
    return createMovieDto;
  }

  public void setCreateMovieDto(CreateMovieDto createMovieDto) {
    this.createMovieDto = createMovieDto;
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
