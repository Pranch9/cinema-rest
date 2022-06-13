package ru.pranch.cinema.services;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.pranch.cinema.dao.AddressDao;
import ru.pranch.cinema.dao.CinemaDao;
import ru.pranch.cinema.model.Address;
import ru.pranch.cinema.model.Cinema;

@SpringBootTest
@ActiveProfiles(value = "test")
class CinemaServiceTest {

  private final CinemaService cinemaService;
  private final CinemaDao cinemaDao;
  private final AddressDao addressDao;

  @Autowired
  CinemaServiceTest(CinemaService cinemaService, CinemaDao cinemaDao, AddressDao addressDao) {
    this.cinemaService = cinemaService;
    this.cinemaDao = cinemaDao;
    this.addressDao = addressDao;
  }

  @Test
  void getCinemas() {
    Assertions.assertEquals(0, cinemaService.getCinemas(null, null).size());

    for (int i = 0; i < 10; i++) {
      Address address = new Address();
      address.setCity("City Test " + i);
      address.setHouseNumber("1" + i);
      address.setStreet("Street test " + i);
      address.setZipCode(634000 + i);
      address.setId(UUID.randomUUID());
      Address addressFromDb = addressDao.save(address);

      Cinema cinema = new Cinema();
      cinema.setCinemaName("Cinema name " + i);
      cinema.setAddressId(addressFromDb.getId());
      cinema.setId(UUID.randomUUID());
      cinemaDao.save(cinema);
    }
    Assertions.assertEquals(10, cinemaService.getCinemas(null, null).size());
  }

  @Test
  void getCinemaHallsByCinemaId() {
  }

  @Test
  void getCinemaByName() {
  }

  @Test
  void addCinema() {
  }

  @Test
  void editCinema() {
  }

  @Test
  void deleteCinema() {
  }

  @Test
  void getCinemasByCity() {
  }

  @Test
  void addCinemaHallsToCinema() {
  }

  @Test
  void updateCinemaHallsToCinema() {
  }

  @Test
  void getCinema() {
  }

  @Test
  void getSeatsByCinemaHallId() {
  }

}