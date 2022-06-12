package ru.pranch.cinema.dto.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import ru.pranch.cinema.dto.CreateMovieDto;

public class ResponseUpdateSessionDto {
  @JsonProperty(value = "movie")
  private CreateMovieDto createMovieDto;
  private LocalDateTime sessionDate;

  public CreateMovieDto getCreateMovieDto() {
    return createMovieDto;
  }

  public void setCreateMovieDto(CreateMovieDto createMovieDto) {
    this.createMovieDto = createMovieDto;
  }

  public LocalDateTime getSessionDate() {
    return sessionDate;
  }

  public void setSessionDate(LocalDateTime sessionDate) {
    this.sessionDate = sessionDate;
  }
}
