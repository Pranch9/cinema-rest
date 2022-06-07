package ru.pranch.cinema.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pranch.cinema.model.Cinema;

@SpringBootTest
class BasicDaoTest {

  private final CinemaDao cinemaDao;

  @Autowired
  BasicDaoTest(CinemaDao cinemaDao) {
    this.cinemaDao = cinemaDao;
  }

  @Test
  void save() {
    Cinema cinema = new Cinema();
    cinemaDao.save(cinema);
  }

  @Test
  void update() {
  }

  @Test
  void findById() {
  }

  @Test
  void deleteById() {
  }

  @Test
  void saveAll() {
  }

  @Test
  void findAllById() {
  }

  @Test
  void findAll() {
  }

  @Test
  void deleteAllById() {
  }
}