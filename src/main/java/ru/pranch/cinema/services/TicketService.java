package ru.pranch.cinema.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.dao.TicketDao;
import ru.pranch.cinema.dao.UserDao;
import ru.pranch.cinema.dto.Booking;
import ru.pranch.cinema.dto.CreateTicketDto;
import ru.pranch.cinema.enums.Status;
import ru.pranch.cinema.mapper.TicketMapper;
import ru.pranch.cinema.mapper.UserMapper;
import ru.pranch.cinema.model.Seat;
import ru.pranch.cinema.model.Session;
import ru.pranch.cinema.model.Ticket;
import ru.pranch.cinema.model.User;

@Service
public class TicketService {
  private final TicketDao ticketDao;
  private final SessionDao sessionDao;
  private final SeatDao seatDao;
  private final UserDao userDao;

  @Autowired
  public TicketService(TicketDao ticketDao, SessionDao sessionDao, SeatDao seatDao, UserDao userDao) {
    this.ticketDao = ticketDao;
    this.sessionDao = sessionDao;
    this.seatDao = seatDao;
    this.userDao = userDao;
  }

  public List<Ticket> getTickets() {
    return ticketDao.findAll();
  }

  public Optional<Ticket> getTicketById(UUID id) {
    return ticketDao.findById(id);
  }

  public List<Ticket> addTickets(CreateTicketDto createTicketDto) throws Exception {
    UUID sessionId = createTicketDto.getSessionId();
    User user = UserMapper.mapUser(createTicketDto.getCreateUserDto());

    User userFromDb = userDao.findByUsername(user.getUsername())
      .orElseGet(() -> {
        user.setCreationDate(LocalDateTime.now());
        return userDao.save(user);
      });

    List<Seat> seats = createTicketDto.getSeats()
      .stream()
      .map(s -> seatDao.findSeatsByPlaceAndRow(s.getRowNumber(), s.getPlace(), sessionId).get())
      .toList();

    if (!checkSeatAvailability(seats, createTicketDto.getSessionId())) {
      throw new Exception();
    }

    List<Ticket> tickets = seats
      .stream()
      .map(seat -> {
        Ticket ticket = TicketMapper.mapTicket(createTicketDto);
        ticket.setUserId(userFromDb.getId());
        ticket.setSeatId(seat.getId());

        seat.setBooked(true);
        seatDao.update(seat.getId(), seat);

        return ticket;
      }).toList();

    return ticketDao.saveAll(tickets);
  }

  public List<Booking> getBookings(UUID sessionId) throws Exception {
    Session session = sessionDao.findById(sessionId)
      .orElseThrow(Exception::new);

    return seatDao.findSeatsByCinemaHall(session.getCinemaHallId())
      .stream()
      .map(seat -> {
        Booking booking = new Booking();
        booking.setSeatId(seat.getId());
        booking.setBooked(ticketDao.findBySessionAndSeat(session.getId(), seat.getId()).isPresent());
        return booking;
      }).toList();

  }

  public Optional<Ticket> updateTicketsStatus(UUID id, Status status) throws Exception {
    Ticket ticketFromDb = ticketDao.findById(id)
      .orElseThrow(Exception::new);
    ticketFromDb.setStatus(status);

    return ticketDao.update(id, ticketFromDb);
  }

  public int deleteTickets(List<UUID> ids) {
    return ticketDao.deleteAllById(ids);
  }

  private boolean checkSeatAvailability(List<Seat> seatsToBook, UUID sessionId) {
    return seatsToBook
      .stream()
      .noneMatch(seatToBook -> {
        try {
          return getBookings(sessionId)
            .stream()
            .filter(booking -> seatToBook.getId().equals(booking.getSeatId()))
            .anyMatch(Booking::isBooked);
        } catch (Exception e) {
          return true;
        }
      });
  }
}
