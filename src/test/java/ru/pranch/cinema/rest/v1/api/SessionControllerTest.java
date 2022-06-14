package ru.pranch.cinema.rest.v1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.Month;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.pranch.cinema.dao.AddressDao;
import ru.pranch.cinema.dao.CinemaDao;
import ru.pranch.cinema.dao.CinemaHallDao;
import ru.pranch.cinema.dao.MovieDao;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.dto.CreateMovieDto;
import ru.pranch.cinema.dto.session.CreateSessionDto;
import ru.pranch.cinema.dto.session.UpdateSessionDto;
import ru.pranch.cinema.enums.MovieGenre;
import ru.pranch.cinema.model.Address;
import ru.pranch.cinema.model.Cinema;
import ru.pranch.cinema.model.CinemaHall;
import ru.pranch.cinema.model.Movie;
import ru.pranch.cinema.model.Seat;
import ru.pranch.cinema.model.Session;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
class SessionControllerTest {
  private final ObjectMapper objectMapper;
  private final SessionDao sessionDao;
  private final MockMvc mockMvc;
  private final CinemaDao cinemaDao;
  private final AddressDao addressDao;
  private final MovieDao movieDao;
  private final CinemaHallDao cinemaHallDao;
  private final SeatDao seatDao;

  @Autowired
  SessionControllerTest(ObjectMapper objectMapper, SessionDao sessionDao, WebApplicationContext webApplicationContext, CinemaDao cinemaDao, AddressDao addressDao, MovieDao movieDao, CinemaHallDao cinemaHallDao, SeatDao seatDao) {
    this.objectMapper = objectMapper;
    this.sessionDao = sessionDao;
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    this.cinemaDao = cinemaDao;
    this.addressDao = addressDao;
    this.movieDao = movieDao;
    this.cinemaHallDao = cinemaHallDao;
    this.seatDao = seatDao;
  }

  @BeforeEach
  void setUp() {
    Cinema cinema = createCinema("Kinomir",
      createAddress("Pushkina", "Tomsk", "15", 644444).getId());
    CinemaHall red = createCinemaHall("Red", 2, 2, cinema.getId());
    createSeat(red.getRowsNumber(), red.getPlacesNumber(), red.getId());

    Movie theBoys = createMovie(MovieGenre.ACTION, 210, "The boys");
    Movie pinkPanter = createMovie(MovieGenre.COMEDY, 100, "Pink panter");
    createSession(LocalDateTime.of(2020, Month.JUNE, 20, 14, 30), red.getId(), theBoys.getId());
    createSession(LocalDateTime.of(2020, Month.JUNE, 21, 14, 30), red.getId(), pinkPanter.getId());
  }

  @AfterEach
  void tearDown() {
    sessionDao.findAll().forEach(session -> sessionDao.deleteById(session.getId()));
    movieDao.findAll().forEach(session -> movieDao.deleteById(session.getId()));
    seatDao.findAll().forEach(session -> seatDao.deleteById(session.getId()));
    cinemaHallDao.findAll().forEach(session -> cinemaHallDao.deleteById(session.getId()));
    cinemaDao.findAll().forEach(session -> cinemaDao.deleteById(session.getId()));
    addressDao.findAll().forEach(session -> addressDao.deleteById(session.getId()));
  }

  @Test
  void getSessionsInfo() throws Exception {
    Cinema cinema = cinemaDao.findByName("Kinomir").get();
    Movie movie = movieDao.findByTitle("The boys").get();
    mockMvc.perform(get("/api/v1/sessions"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(2)));

    mockMvc.perform(get("/api/v1/sessions")
        .param("date", LocalDateTime.of(2020, Month.JUNE, 21, 14, 30).toString()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(1)));

    mockMvc.perform(get("/api/v1/sessions")
        .param("cinemaId", cinema.getId().toString()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(2)));

    mockMvc.perform(get("/api/v1/sessions")
        .param("movieId", movie.getId().toString()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(2)));
    mockMvc.perform(get("/api/v1/sessions")
        .param("date", LocalDateTime.of(2020, Month.JUNE, 20, 14, 30).toString())
        .param("movieId", movie.getId().toString())
        .param("cinemaId", cinema.getId().toString()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(1)));
  }

  @Test
  void getSessionInfoById() throws Exception {
    Session session = sessionDao.findAll().get(0);
    mockMvc
      .perform(get("/api/v1/sessions/{id}", session.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.cinemaName", Matchers.is("Kinomir")))
      .andExpect(jsonPath("$.cinemaRoomName", Matchers.is("Red")))
      .andExpect(jsonPath("$.movieTitle", Matchers.is("The boys")))
      .andExpect(jsonPath("$.length", Matchers.is(210)));
  }

  @Test
  void addSession() throws Exception {
    Assertions.assertEquals(2, sessionDao.findAll().size());
    CinemaHall cinemaHall = cinemaHallDao.findByName("Red").get();
    CreateSessionDto createSessionDto = new CreateSessionDto();
    CreateMovieDto createMovieDto = new CreateMovieDto();
    createMovieDto.setTitle("Horror");
    createMovieDto.setMovieGenre(MovieGenre.HORROR);
    createMovieDto.setLength(123);

    createSessionDto.setSessionDate(LocalDateTime.of(2022, Month.JUNE, 22, 20, 0));
    createSessionDto.setCreateMovieDto(createMovieDto);
    createSessionDto.setCinemaHallId(cinemaHall.getId());

    mockMvc
      .perform(post("/api/v1/sessions/control")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsBytes(createSessionDto)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    Assertions.assertEquals(3, sessionDao.findAll().size());
  }

  @Test
  void editSession() throws Exception {
    Session session = sessionDao.findAll().get(0);
    CinemaHall cinemaHall = cinemaHallDao.findByName("Red").get();
    UpdateSessionDto updateSessionDto = new UpdateSessionDto();
    CreateMovieDto createMovieDto = new CreateMovieDto();
    createMovieDto.setTitle("Horror update");
    createMovieDto.setMovieGenre(MovieGenre.CARTOON);
    createMovieDto.setLength(10);
    updateSessionDto.setSessionDate(LocalDateTime.of(2022, Month.JUNE, 30, 20, 0));
    updateSessionDto.setCreateMovieDto(createMovieDto);
    mockMvc
      .perform(put("/api/v1/sessions/control")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsBytes(updateSessionDto))
        .param("sessionId", session.getId().toString())
        .param("cinemaHallId", cinemaHall.getId().toString()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.movie.title", Matchers.is("Horror update")))
      .andExpect(jsonPath("$.movie.movieGenre", Matchers.is(MovieGenre.CARTOON.name())))
      .andExpect(jsonPath("$.movie.length", Matchers.is(10)));
  }

  @Test
  void deleteSession() throws Exception {
    Assertions.assertEquals(2, sessionDao.findAll().size());

    Session session = sessionDao.findAll().get(0);
    mockMvc
      .perform(delete("/api/v1/sessions/control/{id}", session.getId()))
      .andExpect(status().isOk());

    Assertions.assertEquals(1, sessionDao.findAll().size());
  }

  private Movie createMovie(MovieGenre movieGenre, int length, String title) {
    Movie movie = new Movie();
    movie.setMovieGenre(movieGenre);
    movie.setLength(length);
    movie.setTitle(title);
    return movieDao.save(movie);
  }

  private Cinema createCinema(String cinemaName, UUID addressId) {
    Cinema cinema = new Cinema();
    cinema.setCinemaName(cinemaName);
    cinema.setAddressId(addressId);
    return cinemaDao.save(cinema);
  }

  private void createSeat(int rows, int places, UUID cinemaHallId) {
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= places; j++) {
        Seat seat = new Seat();
        seat.setPlace(j);
        seat.setRowNumber(i);
        seat.setCinemaHallId(cinemaHallId);
        seat.setBooked(false);
        seatDao.save(seat);
      }
    }
  }

  private CinemaHall createCinemaHall(String hallName, int rowsNumber, int placesNumber, UUID cinemaId) {
    CinemaHall cinemaHall = new CinemaHall();
    cinemaHall.setCinemaId(cinemaId);
    cinemaHall.setHallName(hallName);
    cinemaHall.setRowsNumber(rowsNumber);
    cinemaHall.setPlacesNumber(placesNumber);
    return cinemaHallDao.save(cinemaHall);
  }

  private Address createAddress(String street, String city, String houseNumber, int zipCode) {
    Address address = new Address();
    address.setZipCode(zipCode);
    address.setStreet(street);
    address.setCity(city);
    address.setHouseNumber(houseNumber);
    return addressDao.save(address);
  }

  private Session createSession(LocalDateTime sessionDate, UUID cinemaHallId, UUID movieId) {
    Session session = new Session();
    session.setMovieId(movieId);
    session.setCinemaHallId(cinemaHallId);
    session.setSessionDate(sessionDate);
    return sessionDao.save(session);
  }
}