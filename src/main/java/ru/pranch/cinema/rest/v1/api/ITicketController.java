package ru.pranch.cinema.rest.v1.api;

import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pranch.cinema.dto.TicketDto;
import ru.pranch.cinema.model.Ticket;

@RequestMapping(value = "/api/v1")
public interface ITicketController {
  @GetMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Ticket>> getTickets();

  @GetMapping(value = "/ticket/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Ticket> getTicket(@PathVariable UUID id);

  @PostMapping(value = "/ticket/add", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Ticket> addTicket(@RequestBody TicketDto ticketDto);

  @PutMapping(value = "/ticket/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Ticket> editTicket(@RequestBody TicketDto ticketDto, @PathVariable UUID id);

  @PostMapping(value = "/tickets/add")
  ResponseEntity<List<Ticket>> addTickets(@RequestBody List<TicketDto> ticketsDto);

}
