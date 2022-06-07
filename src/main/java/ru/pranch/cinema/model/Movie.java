package ru.pranch.cinema.model;

import java.util.UUID;
import ru.pranch.cinema.enums.MovieGenre;

public class Movie {
  private UUID id;
  private String title;
  private MovieGenre movieGenre;
  private int length;

  public Movie() {
    this.id = UUID.randomUUID();
  }

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

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  @Override
  public String toString() {
    return "Movie{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", movieGenre=" + movieGenre +
        ", length=" + length +
        '}';
  }
}
