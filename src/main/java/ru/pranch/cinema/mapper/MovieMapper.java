package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.MovieDto;
import ru.pranch.cinema.model.Movie;

public class MovieMapper {
  public static Movie mapMovie(MovieDto movieDao) {
    Movie movie = new Movie();
    movie.setMovieGenre(movieDao.getMovieGenre());
    movie.setTitle(movieDao.getTitle());

    return movie;
  }
}
