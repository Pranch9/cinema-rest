package ru.pranch.cinema.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pranch.cinema.dao.TicketDao;
import ru.pranch.cinema.dto.TicketDto;
import ru.pranch.cinema.mapper.TicketMapper;
import ru.pranch.cinema.model.Ticket;

@Service
public class TicketService {
  private final TicketDao ticketDao;

  @Autowired
  public TicketService(TicketDao ticketDao) {
    this.ticketDao = ticketDao;
  }

  public List<Ticket> getTickets() {
    return ticketDao.findAll();
  }

  public Optional<Ticket> getTicketById(UUID id) {
    return ticketDao.findById(id);
  }

  public Ticket addTicket(TicketDto ticket) {
    return ticketDao.save(TicketMapper.mapTicket(ticket));
  }

  public List<Ticket> addTickets(List<TicketDto> tickets) {
    return ticketDao.saveAll(tickets
      .stream()
      .map(TicketMapper::mapTicket)
      .toList());
  }

  public Optional<Ticket> editTicket(UUID id, TicketDto ticket) {
    return ticketDao.update(id, TicketMapper.mapTicket(ticket));
  }
}
