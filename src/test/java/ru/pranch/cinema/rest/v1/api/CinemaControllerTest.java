package ru.pranch.cinema.rest.v1.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
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
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dto.CreateAddressDto;
import ru.pranch.cinema.dto.cinema.CreateCinemaDto;
import ru.pranch.cinema.dto.cinema.UpdateCinemaDto;
import ru.pranch.cinema.dto.cinema_hall.CreateCinemaHallDto;
import ru.pranch.cinema.model.Address;
import ru.pranch.cinema.model.Cinema;
import ru.pranch.cinema.model.CinemaHall;
import ru.pranch.cinema.model.Seat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
class CinemaControllerTest {
  private final MockMvc mockMvc;
  private final CinemaDao cinemaDao;
  private final AddressDao addressDao;
  private final CinemaHallDao cinemaHallDao;
  private final SeatDao seatDao;
  private final ObjectMapper objectMapper;

  @Autowired
  CinemaControllerTest(WebApplicationContext webApplicationContext, CinemaDao cinemaDao, AddressDao addressDao,
                       CinemaHallDao cinemaHallDao, SeatDao seatDao, ObjectMapper objectMapper) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    this.cinemaDao = cinemaDao;
    this.addressDao = addressDao;
    this.cinemaHallDao = cinemaHallDao;
    this.seatDao = seatDao;
    this.objectMapper = objectMapper;
  }

  @BeforeEach
  void setUp() {
    Cinema cinema = createCinema("Kinomir",
      createAddress("Pushkina", "Tomsk", "15", 644444).getId());
    CinemaHall red = createCinemaHall("Red", 2, 2, cinema.getId());
    CinemaHall blue = createCinemaHall("Blue", 1, 2, cinema.getId());
    createSeat(red.getRowsNumber(), red.getPlacesNumber(), red.getId());
    createSeat(blue.getRowsNumber(), blue.getPlacesNumber(), blue.getId());

    createCinema("Kinopoisk",
      createAddress("Lenina", "Tomsk", "16", 644444).getId());
    createCinema("KinoZal",
      createAddress("Gogola", "Novosibirsk", "12", 644444).getId());
    createCinema("Sea",
      createAddress("Gagarina", "Kazan", "31", 644444).getId());
    createCinema("Pure",
      createAddress("Batenkova", "Moscow", "66", 644444).getId());
  }

  @AfterEach
  void tearDown() {
    seatDao.findAll().forEach(r -> seatDao.deleteById(r.getId()));
    cinemaHallDao.findAll().forEach(r -> cinemaHallDao.deleteById(r.getId()));
    cinemaDao.findAll().forEach(r -> cinemaDao.deleteById(r.getId()));
    addressDao.findAll().forEach(r -> addressDao.deleteById(r.getId()));
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

  @Test
  void getCinemas() throws Exception {
    mockMvc
      .perform(get("/api/v1/cinemas"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(5)));

    mockMvc
      .perform(get("/api/v1/cinemas")
        .param("name", "Kinomir"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(1)));

    mockMvc
      .perform(get("/api/v1/cinemas")
        .param("city", "Tomsk"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(2)));

    mockMvc
      .perform(get("/api/v1/cinemas")
        .param("name", "KinoZal")
        .param("city", "Kazan"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.empty()));


    mockMvc
      .perform(get("/api/v1/cinemas")
        .param("name", "Kinopoisk")
        .param("city", "Tomsk"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(1)));
  }

  @Test
  void getCinemasById() throws Exception {
    Cinema cinema = cinemaDao.findByName("Kinopoisk").get();
    mockMvc
      .perform(get("/api/v1/cinemas/{id}", cinema.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id", Matchers.is(cinema.getId().toString())))
      .andExpect(jsonPath("$.cinemaName", Matchers.is("Kinopoisk")))
      .andExpect(jsonPath("$.street", Matchers.is("Lenina")))
      .andExpect(jsonPath("$.city", Matchers.is("Tomsk")))
      .andExpect(jsonPath("$.houseNumber", Matchers.is("16")))
      .andExpect(jsonPath("$.zipCode", Matchers.is(644444)));

    mockMvc
      .perform(get("/api/v1/cinemas/{id}", UUID.randomUUID()))
      .andExpect(status().isNotFound());
  }

  @Test
  void getCinemaHallByCinemaId() throws Exception {
    Cinema cinema = cinemaDao.findByName("Kinomir").get();

    mockMvc.perform(get("/api/v1/cinemas/cinemaHalls/{id}", cinema.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(2)));

    mockMvc.perform(get("/api/v1/cinemas/cinemaHalls/{id}", UUID.randomUUID()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.empty()));
  }

  @Test
  void getSeatsByCinemaHallId() throws Exception {
    CinemaHall cinemaHall = cinemaHallDao.findByName("Red").get();

    mockMvc.perform(get("/api/v1/cinemas/seats/{cinemaHallId}", cinemaHall.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.hasSize(4)));

    mockMvc.perform(get("/api/v1/cinemas/seats/{cinemaHallId}", UUID.randomUUID())).andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$", Matchers.empty()));
  }

  @Test
  void addCinema() throws Exception {
    CreateCinemaDto createCinemaDto = new CreateCinemaDto();
    CreateAddressDto createAddressDto = new CreateAddressDto();
    createAddressDto.setHouseNumber("8/1");
    createAddressDto.setStreet("Lenina");
    createAddressDto.setZipCode(11111);
    createAddressDto.setCity("Tomsk");

    CreateCinemaHallDto createCinemaHallDto = new CreateCinemaHallDto();
    createCinemaHallDto.setHallName("Add cinema hall");
    createCinemaHallDto.setRowsNumber(2);
    createCinemaHallDto.setPlacesNumber(2);

    createCinemaDto.setCinemaName("Add new cinema");
    createCinemaDto.setCreateAddressDto(createAddressDto);
    createCinemaDto.setCreateCinemaHallDtos(Collections.singletonList(createCinemaHallDto));

    mockMvc.perform(post("/api/v1/cinemas/control")
        .content(objectMapper.writeValueAsBytes(createCinemaDto))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    Assertions.assertEquals(6, cinemaDao.findAll().size());
    Assertions.assertEquals(6, addressDao.findAll().size());
    Assertions.assertEquals(3, cinemaHallDao.findAll().size());
    Assertions.assertEquals(10, seatDao.findAll().size());
  }

  @Test
  void editCinema() throws Exception {
    Cinema cinema = cinemaDao.findByName("Kinomir").get();
    UpdateCinemaDto updateCinemaDto = new UpdateCinemaDto();
    CreateAddressDto createAddressDto = new CreateAddressDto();
    createAddressDto.setHouseNumber("8/1");
    createAddressDto.setStreet("Lenina");
    createAddressDto.setZipCode(11111);
    createAddressDto.setCity("Tomsk");

    CreateCinemaHallDto createCinemaHallDto = new CreateCinemaHallDto();
    createCinemaHallDto.setHallName("Update cinema hall");
    createCinemaHallDto.setRowsNumber(2);
    createCinemaHallDto.setPlacesNumber(2);

    updateCinemaDto.setCinemaName("Updated cinema name");
    updateCinemaDto.setCreateCinemaHallDtos(Collections.singletonList(createCinemaHallDto));
    updateCinemaDto.setCreateAddressDto(createAddressDto);

    mockMvc.perform(put("/api/v1/cinemas/control/{cinemaId}", cinema.getId())
        .content(objectMapper.writeValueAsBytes(updateCinemaDto))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.cinemaName", Matchers.is("Updated cinema name")))
      .andExpect(jsonPath("$.address.city", Matchers.is("Tomsk")))
      .andExpect(jsonPath("$.address.zipCode", Matchers.is(11111)))
      .andExpect(jsonPath("$.address.street", Matchers.is("Lenina")))
      .andExpect(jsonPath("$.address.houseNumber", Matchers.is("8/1")))
      .andExpect(jsonPath("$.cinemaHalls.[0].hallName", Matchers.is("Update cinema hall")))
      .andExpect(jsonPath("$.cinemaHalls.[0].rowsNumber", Matchers.is(2)))
      .andExpect(jsonPath("$.cinemaHalls.[0].placesNumber", Matchers.is(2)));
  }

  @Test
  void deleteCinema() throws Exception {
    Cinema cinema = cinemaDao.findByName("Kinopoisk").get();
    Assertions.assertEquals(5, cinemaDao.findAll().size());

    mockMvc.perform(delete("/api/v1/cinemas/control/{id}", UUID.randomUUID()))
      .andExpect(status().isNotFound());

    mockMvc.perform(delete("/api/v1/cinemas/control/{id}", cinema.getId()))
      .andExpect(status().isOk());
    Assertions.assertEquals(4, cinemaDao.findAll().size());
  }
}