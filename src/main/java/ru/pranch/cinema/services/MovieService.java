package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.MovieDao;
import ru.pranch.cinema.dto.CreateMovieDto;
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

  public List<Movie> addMovies(List<CreateMovieDto> movies) {
    return movieDao.saveAll(movies
        .stream()
        .map(MovieMapper::mapMovie)
        .toList());
  }

  public Optional<Movie> editMovie(UUID id, CreateMovieDto movie) throws Exception {
    if (movieDao.findByTitle(movie.getTitle()).isPresent()) {
      throw new Exception("Movie with title = {" + movie.getTitle() + "} already exist!");
    }
    return movieDao.update(id, MovieMapper.mapMovie(movie));
  }

  public int deleteMovies(List<UUID> ids) {
    return movieDao.deleteAllById(ids);
  }
}
