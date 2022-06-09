package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.CreateTicketDto;
import ru.pranch.cinema.enums.Status;
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
  public ResponseEntity<Map<UUID, Boolean>> getBookings(UUID id) {
    try {
      return ResponseEntity.ok(ticketService.getBookings(id));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<Ticket> editTicket(CreateTicketDto createTicketDto, UUID id) {
    return ticketService.editTicket(id, createTicketDto)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<List<Ticket>> updateTicketStatus(List<CreateTicketDto> ticketsDto, UUID id, Status status) {
    ticketService.updateTicketsStatus(ticketsDto, id, status);
    return null;
  }

  @Override
  public ResponseEntity<Integer> deleteTickets(List<UUID> ids) {
    ticketService.deleteTickets(ids);
    return null;
  }

  @Override
  public ResponseEntity<List<Ticket>> addTickets(List<CreateTicketDto> ticketsDto) {
    return ResponseEntity.ok(ticketService.addTickets(ticketsDto));
  }
}
