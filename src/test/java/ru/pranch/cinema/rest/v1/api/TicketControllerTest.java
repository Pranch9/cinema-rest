package ru.pranch.cinema.rest.v1.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
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
import ru.pranch.cinema.dao.AddressDao;
import ru.pranch.cinema.dao.CinemaDao;
import ru.pranch.cinema.dao.CinemaHallDao;
import ru.pranch.cinema.dao.MovieDao;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.dao.TicketDao;
import ru.pranch.cinema.dao.UserDao;
import ru.pranch.cinema.dto.Booking;
import ru.pranch.cinema.dto.CreateTicketDto;
import ru.pranch.cinema.dto.user.CreateUserDto;
import ru.pranch.cinema.enums.MovieGenre;
import ru.pranch.cinema.enums.Status;
import ru.pranch.cinema.model.Address;
import ru.pranch.cinema.model.Cinema;
import ru.pranch.cinema.model.CinemaHall;
import ru.pranch.cinema.model.Movie;
import ru.pranch.cinema.model.Seat;
import ru.pranch.cinema.model.Session;
import ru.pranch.cinema.model.Ticket;
import ru.pranch.cinema.model.User;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
class TicketControllerTest {
  private final ObjectMapper objectMapper;
  private final MockMvc mockMvc;
  private final TicketDao ticketDao;
  private final SessionDao sessionDao;
  private final CinemaDao cinemaDao;
  private final AddressDao addressDao;
  private final MovieDao movieDao;
  private final CinemaHallDao cinemaHallDao;
  private final SeatDao seatDao;
  private final UserDao userDao;

  @Autowired
  TicketControllerTest(ObjectMapper objectMapper, WebApplicationContext webApplicationContext, TicketDao ticketDao,
                       SessionDao sessionDao, CinemaDao cinemaDao, AddressDao addressDao, MovieDao movieDao, CinemaHallDao cinemaHallDao, SeatDao seatDao, UserDao userDao) {
    this.objectMapper = objectMapper;
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    this.ticketDao = ticketDao;
    this.sessionDao = sessionDao;
    this.cinemaDao = cinemaDao;
    this.addressDao = addressDao;
    this.movieDao = movieDao;
    this.cinemaHallDao = cinemaHallDao;
    this.seatDao = seatDao;
    this.userDao = userDao;
  }

  @BeforeEach
  void setUp() {
    User user = new User();
    user.setUsername("user");
    user.setMail("test@mail.net");
    user.setPassword("pswd");
    user.setCreationDate(LocalDateTime.now());
    User userFromDb = userDao.save(user);

    Cinema cinema = createCinema("Kinomir",
      createAddress("Pushkina", "Tomsk", "15", 644444).getId());
    CinemaHall red = createCinemaHall("Red", 2, 2, cinema.getId());
    List<Seat> seats = createSeat(red.getRowsNumber(), red.getPlacesNumber(), red.getId());

    Movie theBoys = createMovie(MovieGenre.ACTION, 210, "The boys");
    Movie pinkPanter = createMovie(MovieGenre.COMEDY, 100, "Pink panter");

    Session session1 = createSession(LocalDateTime.of(2020, Month.JUNE, 20, 14, 30), red.getId(), theBoys.getId());
    Session session2 = createSession(LocalDateTime.of(2020, Month.JUNE, 21, 14, 30), red.getId(), pinkPanter.getId());

    Seat seat1 = seats.get(0);
    Seat seat2 = seats.get(1);
    createTicket(userFromDb.getId(), seat1.getId(), session1.getId(), 1200, Status.UNCONFIRMED);
    createTicket(userFromDb.getId(), seat2.getId(), session2.getId(), 600, Status.CONFIRMED);
    seats.forEach(seat -> {
      if (seat.getId().equals(seat1.getId()) || seat.getId().equals(seat2.getId())) {
        seat.setBooked(true);
        seatDao.update(seat.getId(), seat);
      }
    });
  }

  @AfterEach
  void tearDown() {
    ticketDao.findAll().forEach(ticket -> ticketDao.deleteById(ticket.getId()));
    userDao.findAll().forEach(user -> userDao.deleteById(user.getId()));
    sessionDao.findAll().forEach(session -> sessionDao.deleteById(session.getId()));
    movieDao.findAll().forEach(movie -> movieDao.deleteById(movie.getId()));
    seatDao.findAll().forEach(seat -> seatDao.deleteById(seat.getId()));
    cinemaHallDao.findAll().forEach(cinemaHall -> cinemaHallDao.deleteById(cinemaHall.getId()));
    cinemaDao.findAll().forEach(cinema -> cinemaDao.deleteById(cinema.getId()));
    addressDao.findAll().forEach(address -> addressDao.deleteById(address.getId()));
  }

  @Test
  void getTickets() throws Exception {
    mockMvc
      .perform(get("/api/v1/tickets"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(2)));
  }

  @Test
  void getTicket() throws Exception {
    Ticket ticket = ticketDao.findAll().get(0);
    mockMvc
      .perform(get("/api/v1/tickets/{id}", ticket.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.sessionId", Matchers.is(ticket.getSessionId().toString())))
      .andExpect(jsonPath("$.userId", Matchers.is(ticket.getUserId().toString())))
      .andExpect(jsonPath("$.seatId", Matchers.is(ticket.getSeatId().toString())))
      .andExpect(jsonPath("$.price", Matchers.is(ticket.getPrice())))
      .andExpect(jsonPath("$.status", Matchers.is(ticket.getStatus().name())));
  }

  @Test
  void getBookings() throws Exception {
    Session session = sessionDao.findAll().get(0);
    List<Seat> seats = seatDao.findAll();
    MvcResult result = mockMvc
      .perform(get("/api/v1/tickets/bookings/{sessionId}", session.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(4)))
      .andReturn();

    List<Booking> bookings = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
    });
    Assertions.assertEquals(1, bookings.stream().filter(Booking::isBooked).toList().size());
    Assertions.assertEquals(3, bookings.stream().filter(booking -> !booking.isBooked()).toList().size());
  }

  @Test
  void updateTicketStatus() throws Exception {
    Ticket ticket = ticketDao.findAll().get(0);
    mockMvc
      .perform(put("/api/v1/tickets/control/status/{id}/{status}", ticket.getId(), Status.CONFIRMED)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.status", Matchers.is(Status.CONFIRMED.name())));
  }

  @Test
  void deleteTickets() throws Exception {
    List<Ticket> tickets = ticketDao.findAll();
    Assertions.assertEquals(2, tickets.size());

    mockMvc
      .perform(delete("/api/v1/tickets/control")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsBytes(tickets.stream().map(Ticket::getId).toList())))
      .andExpect(status().isOk());

    Assertions.assertEquals(0, ticketDao.findAll().size());
  }

  @Test
  void addTickets() throws Exception {
    Assertions.assertEquals(2, ticketDao.findAll().size());

    Session session = sessionDao.findAll().get(0);
    CreateTicketDto createTicketDto = new CreateTicketDto();

    CreateUserDto createUserDto = new CreateUserDto();
    List<Seat> seats = seatDao.findAll().stream().filter(seat -> !seat.isBooked()).toList();
    createUserDto.setMail("user@user.net");
    createUserDto.setUsername("user");
    createUserDto.setPassword("pswd");

    createTicketDto.setSeats(seats);
    createTicketDto.setCreateUserDto(createUserDto);
    createTicketDto.setStatus(Status.CONFIRMED);
    createTicketDto.setPrice(500);
    createTicketDto.setSessionId(session.getId());

    mockMvc
      .perform(post("/api/v1/tickets/control")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsBytes(createTicketDto)))
      .andExpect(status().isOk());

    Assertions.assertEquals(4, ticketDao.findAll().size());
  }

  private Ticket createTicket(UUID userId, UUID seatId, UUID sessionId, double price, Status status) {
    Ticket ticket = new Ticket();
    ticket.setUserId(userId);
    ticket.setSeatId(seatId);
    ticket.setSessionId(sessionId);
    ticket.setPrice(price);
    ticket.setStatus(status);
    return ticketDao.save(ticket);
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

  private List<Seat> createSeat(int rows, int places, UUID cinemaHallId) {
    List<Seat> seats = new ArrayList<>();
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= places; j++) {
        Seat seat = new Seat();
        seat.setPlace(j);
        seat.setRowNumber(i);
        seat.setCinemaHallId(cinemaHallId);
        seat.setBooked(false);
        seats.add(seatDao.save(seat));
      }
    }
    return seats;
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