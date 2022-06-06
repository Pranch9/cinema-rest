package ru.pranch.cinema.dto;

import ru.pranch.cinema.enums.MovieGenre;

public class MovieDto {
  private String title;
  private MovieGenre movieGenre;

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
}
