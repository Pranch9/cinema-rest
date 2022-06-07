package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.MovieDao;
import ru.pranch.cinema.dto.MovieDto;
import ru.pranch.cinema.enums.MovieGenre;
import ru.pranch.cinema.mapper.MovieMapper;
import ru.pranch.cinema.model.Movie;

@Service
public class MovieService {
  private final MovieDao movieDao;

  @Autowired
  public MovieService(MovieDao movieDao) {
    this.movieDao = movieDao;
  }

  public List<Movie> getMovies() {
    return movieDao.findAll();
  }

  public Optional<Movie> getMovieById(UUID id) {
    return movieDao.findById(id);
  }

  public Optional<Movie> getMovieByTitle(String title) {
    return movieDao.findByTitle(title);
  }

  public List<Movie> getMovieByGenre(MovieGenre movieGenre) {
    return movieDao.findMoviesByGenre(movieGenre);
  }

  public Movie addMovie(MovieDto movie) throws Exception {
    if (movieDao.findByTitle(movie.getTitle()).isPresent()) {
      throw new Exception("Movie with title = {" + movie.getTitle() + "} already exist!");
    }
    return movieDao.save(MovieMapper.mapMovie(movie));
  }

  public List<Movie> addMovies(List<MovieDto> movies) {
    return movieDao.saveAll(movies
        .stream()
        .map(MovieMapper::mapMovie)
        .toList());
  }

  public Optional<Movie> editMovie(UUID id, MovieDto movie) throws Exception {
    if (movieDao.findByTitle(movie.getTitle()).isPresent()) {
      throw new Exception("Movie with title = {" + movie.getTitle() + "} already exist!");
    }
    return movieDao.update(id, MovieMapper.mapMovie(movie));
  }

  public int deleteMovie(UUID id) {
    return movieDao.deleteById(id);
  }
}
