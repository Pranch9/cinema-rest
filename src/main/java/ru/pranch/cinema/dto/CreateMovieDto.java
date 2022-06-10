package ru.pranch.cinema.dto;

import ru.pranch.cinema.enums.MovieGenre;

public class CreateMovieDto {
  private String title;
  private MovieGenre movieGenre;
  private int length;

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
}
