package ru.pranch.cinema.model;

import java.util.UUID;
import ru.pranch.cinema.enums.MovieGenre;

public class Movie {
  private UUID id;
  private String title;
  private MovieGenre movieGenre;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public MovieGenre getMovieGenre() {
    return movieGenre;
  }

  public void setMovieGenre(MovieGenre movieGenre) {
    this.movieGenre = movieGenre;
  }

  @Override
  public String toString() {
    return "Movie{" +
      "id=" + id +
      ", title='" + title + '\'' +
      ", movieGenre=" + movieGenre +
      '}';
  }
}
