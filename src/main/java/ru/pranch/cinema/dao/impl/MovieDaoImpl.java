package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import ru.pranch.cinema.dao.MovieDao;
import ru.pranch.cinema.enums.MovieGenre;
import ru.pranch.cinema.model.Movie;

@Repository
public class MovieDaoImpl extends BasicDaoImpl<Movie> implements MovieDao {

  public MovieDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  @Override
  public Optional<Movie> findByTitle(String title) {
    return jdbi.withHandle(handle ->
      handle.createQuery("""
          select * from movies where title = :title;
          """)
        .bind("title", title)
        .mapToBean(Movie.class)
        .findOne());
  }

  @Override
  public List<Movie> findMoviesByGenre(MovieGenre movieGenre) {
    return jdbi.withHandle(handle ->
      handle.createQuery("""
          select * from movies where movie_genre = :movieGenre;
          """)
        .bind("movieGenre", movieGenre)
        .mapToBean(Movie.class)
        .list());
  }

  @Override
  public List<Movie> findAll(String title, MovieGenre movieGenre) {
    String sql = "select * from movies";
    if (StringUtils.hasText(title) && !ObjectUtils.isEmpty(movieGenre)) {
      sql += " where lower(title) = '" + title.toLowerCase() + "' and movie_genre = '" + movieGenre + "'";
    } else if (StringUtils.hasText(title)) {
      sql += " where lower(title) = '" + title.toLowerCase() + "'";
    } else if (!ObjectUtils.isEmpty(movieGenre)) {
      sql += " where movie_genre = '" + movieGenre + "'";
    }

    String finalSql = sql + ";";
    return jdbi.withHandle(handle ->
      handle.createQuery(finalSql)
        .mapToBean(Movie.class)
        .list());
  }

}
