package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.TicketDto;
import ru.pranch.cinema.model.Ticket;
import ru.pranch.cinema.rest.v1.api.ITicketController;
import ru.pranch.cinema.services.TicketService;

@RestController
public class TicketControllerImpl implements ITicketController {
  private final TicketService ticketService;

  @Autowired
  public TicketControllerImpl(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @Override
  public ResponseEntity<List<Ticket>> getTickets() {
    return ResponseEntity.ok(ticketService.getTickets());
  }

  @Override
  public ResponseEntity<Ticket> getTicket(UUID id) {
    return ticketService.getTicketById(id)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<Ticket> editTicket(TicketDto ticketDto, UUID id) {
    return ticketService.editTicket(id, ticketDto)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<Ticket> deleteTicket(TicketDto ticketDto, UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Ticket>> addTickets(List<TicketDto> ticketsDto) {
    return ResponseEntity.ok(ticketService.addTickets(ticketsDto));
  }
}
