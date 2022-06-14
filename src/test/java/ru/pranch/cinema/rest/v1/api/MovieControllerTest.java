package ru.pranch.cinema.rest.v1.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.pranch.cinema.dao.MovieDao;
import ru.pranch.cinema.dto.CreateMovieDto;
import ru.pranch.cinema.enums.MovieGenre;
import ru.pranch.cinema.model.Movie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
class MovieControllerTest {
  private final ObjectMapper objectMapper;
  private final MovieDao movieDao;
  private final MockMvc mockMvc;

  @Autowired
  MovieControllerTest(WebApplicationContext webApplicationContext, ObjectMapper objectMapper, MovieDao movieDao) {
    this.objectMapper = objectMapper;
    this.movieDao = movieDao;
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @BeforeEach
  void setUp() {
    createMovie(MovieGenre.ACTION, 120, "Godzilla");
    createMovie(MovieGenre.HORROR, 100, "Mummy");
    createMovie(MovieGenre.CARTOON, 90, "Agent 007");
    createMovie(MovieGenre.COMEDY, 210, "The boys");
    createMovie(MovieGenre.COMEDY, 100, "Pink panter");
  }

  @AfterEach
  void tearDown() {
    movieDao.findAll().forEach(movie -> movieDao.deleteById(movie.getId()));
  }

  @Test
  void getMovies() throws Exception {
    mockMvc
      .perform(get("/api/v1/movies"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(5)));
    mockMvc
      .perform(get("/api/v1/movies")
        .param("title", "The boys"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(1)));
    mockMvc
      .perform(get("/api/v1/movies")
        .param("genre", MovieGenre.COMEDY.name()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(2)));
    mockMvc
      .perform(get("/api/v1/movies")
        .param("title", "Mummy")
        .param("genre", MovieGenre.HORROR.name()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(1)));
  }

  @Test
  void getMovie() throws Exception {
    Movie movie = movieDao.findByTitle("The boys").get();

    mockMvc
      .perform(get("/api/v1/movies/{id}", movie.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.title", Matchers.is("The boys")))
      .andExpect(jsonPath("$.movieGenre", Matchers.is(MovieGenre.COMEDY.name())))
      .andExpect(jsonPath("$.length", Matchers.is(210)));

    mockMvc
      .perform(get("/api/v1/movies/{id}", UUID.randomUUID()))
      .andExpect(status().isNotFound());
  }

  @Test
  void getMovieGenres() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/movies/genres"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(4)))
      .andReturn();

    List<MovieGenre> movieGenres = objectMapper.readValue(result.getResponse().getContentAsByteArray(), new TypeReference<>() {
    });

    Assertions.assertTrue(movieGenres.containsAll(List.of(MovieGenre.values())));
  }

  @Test
  void addMovies() throws Exception {
    Assertions.assertEquals(5, movieDao.findAll().size());

    CreateMovieDto movie1 = new CreateMovieDto();
    movie1.setMovieGenre(MovieGenre.ACTION);
    movie1.setLength(111);
    movie1.setTitle("Dogs");

    CreateMovieDto movie2 = new CreateMovieDto();
    movie2.setMovieGenre(MovieGenre.HORROR);
    movie2.setLength(55);
    movie2.setTitle("Mask");

    mockMvc.perform(post("/api/v1/movies/control")
        .content(objectMapper.writeValueAsBytes(Arrays.asList(movie1, movie2)))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(2)));

    Assertions.assertEquals(7, movieDao.findAll().size());
  }

  @Test
  void editMovie() throws Exception {
    Movie movie = movieDao.findByTitle("The boys").get();
    CreateMovieDto createMovieDto = new CreateMovieDto();
    createMovieDto.setMovieGenre(MovieGenre.CARTOON);
    createMovieDto.setLength(99);
    createMovieDto.setTitle("Dogs update");


    mockMvc.perform(put("/api/v1/movies/control/{id}", movie.getId())
        .content(objectMapper.writeValueAsBytes(createMovieDto))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length", Matchers.is(99)))
      .andExpect(jsonPath("$.movieGenre", Matchers.is(MovieGenre.CARTOON.name())))
      .andExpect(jsonPath("$.title", Matchers.is("Dogs update")));
  }

  @Test
  void deleteMovies() throws Exception {
    List<Movie> movies = movieDao.findAll();
    Assertions.assertEquals(5, movies.size());
    List<UUID> ids = movies
      .stream()
      .map(Movie::getId)
      .toList();

    mockMvc.perform(delete("/api/v1/movies/control")
        .content(objectMapper.writeValueAsBytes(ids))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk());

    Assertions.assertTrue(movieDao.findAll().isEmpty());
  }

  private Movie createMovie(MovieGenre movieGenre, int length, String title) {
    Movie movie = new Movie();
    movie.setMovieGenre(movieGenre);
    movie.setLength(length);
    movie.setTitle(title);
    return movieDao.save(movie);
  }
}