package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.Booking;
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
  public ResponseEntity<List<Booking>> getBookings(UUID sessionId) {
    try {
      return ResponseEntity.ok(ticketService.getBookings(sessionId));
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  public ResponseEntity<Ticket> updateTicketStatus(UUID id, Status status) {
    try {
      return ticketService.updateTicketsStatus(id, status)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public ResponseEntity<Integer> deleteTickets(List<UUID> ids) {
    return ResponseEntity.ok(ticketService.deleteTickets(ids));
  }

  @Override
  public ResponseEntity<List<Ticket>> addTickets(CreateTicketDto createTicketDto) {
    try {
      return ResponseEntity.ok(ticketService.addTickets(createTicketDto));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
