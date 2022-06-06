package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dto.SeatDto;
import ru.pranch.cinema.mapper.SeatMapper;
import ru.pranch.cinema.model.Seat;

@Service
public class SeatService {
  private final SeatDao seatDao;

  @Autowired
  public SeatService(SeatDao seatDao) {
    this.seatDao = seatDao;
  }

  public List<Seat> getSeats() {
    return seatDao.findAll();
  }

  public Optional<Seat> getSeatById(UUID id) {
    return seatDao.findById(id);
  }


  public Seat addSeat(SeatDto seat) {
    return seatDao.save(SeatMapper.mapSeat(seat));
  }

  public List<Seat> addSeats(List<SeatDto> seats) {
    return seatDao.saveAll(seats
      .stream()
      .map(SeatMapper::mapSeat)
      .toList());
  }

  public Optional<Seat> editSeat(UUID id, SeatDto seat) {
    return seatDao.update(id, SeatMapper.mapSeat(seat));
  }
}
