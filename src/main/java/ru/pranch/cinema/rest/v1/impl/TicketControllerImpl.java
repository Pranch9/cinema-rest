package ru.pranch.cinema.rest.v1.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.pranch.cinema.dto.TicketDto;
import ru.pranch.cinema.model.Ticket;
import ru.pranch.cinema.rest.v1.api.ITicketController;

@RestController
public class TicketControllerImpl implements ITicketController {
  @Override
  public ResponseEntity<List<Ticket>> getTickets() {
    return null;
  }

  @Override
  public ResponseEntity<Ticket> getTicket(UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<Ticket> addTicket(TicketDto ticketDto) {
    return null;
  }

  @Override
  public ResponseEntity<Ticket> editTicket(TicketDto ticketDto, UUID id) {
    return null;
  }

  @Override
  public ResponseEntity<List<Ticket>> addTickets(List<TicketDto> ticketsDto) {
    return null;
  }
}
