package ru.pranch.cinema.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.SeatDao;
import ru.pranch.cinema.dao.SessionDao;
import ru.pranch.cinema.dao.TicketDao;
import ru.pranch.cinema.dto.CreateTicketDto;
import ru.pranch.cinema.enums.Status;
import ru.pranch.cinema.mapper.TicketMapper;
import ru.pranch.cinema.model.Seat;
import ru.pranch.cinema.model.Session;
import ru.pranch.cinema.model.Ticket;

@Service
public class TicketService {
  private final TicketDao ticketDao;
  private final SessionDao sessionDao;
  private final SeatDao seatDao;

  @Autowired
  public TicketService(TicketDao ticketDao, SessionDao sessionDao, SeatDao seatDao) {
    this.ticketDao = ticketDao;
    this.sessionDao = sessionDao;
    this.seatDao = seatDao;
  }

  public List<Ticket> getTickets() {
    return ticketDao.findAll();
  }

  public Optional<Ticket> getTicketById(UUID id) {
    return ticketDao.findById(id);
  }

  public Ticket addTicket(CreateTicketDto ticket) {
    return ticketDao.save(TicketMapper.mapTicket(ticket));
  }

  public List<Ticket> addTickets(List<CreateTicketDto> tickets) {
    return ticketDao.saveAll(tickets
        .stream()
        .map(TicketMapper::mapTicket)
        .toList());
  }

  public Optional<Ticket> editTicket(UUID id, CreateTicketDto ticket) {
    return ticketDao.update(id, TicketMapper.mapTicket(ticket));
  }

  public Map<UUID, Boolean> getBookings(UUID sessionId) throws Exception {
    Session session = sessionDao.findById(sessionId)
        .orElseThrow(() -> new Exception());

    return seatDao.findSeatsByCinemaHall(session.getCinemaHallId())
        .stream()
        .collect(Collectors.toMap(Seat::getId, seat -> ticketDao.findBySessionAndSeat(session.getId(), seat.getId()).isPresent()));

  }

  public List<Ticket> updateTicketsStatus(List<CreateTicketDto> ticketsDto, UUID id, Status status) {
    return null;
  }

  public int deleteTickets(List<UUID> ids) {
    return 0;
  }
}
