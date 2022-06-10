package ru.pranch.cinema.rest.v1.api;

import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pranch.cinema.dto.Booking;
import ru.pranch.cinema.dto.CreateTicketDto;
import ru.pranch.cinema.enums.Status;
import ru.pranch.cinema.model.Ticket;

@RequestMapping(value = "/api/v1/tickets")
public interface ITicketController {
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Ticket>> getTickets();

  @GetMapping(value = "/ticket/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Ticket> getTicket(@PathVariable UUID id);

  @GetMapping(value = "/bookings/{sessionId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Booking>> getBookings(@PathVariable UUID sessionId);

  @PutMapping(value = "/control/status/{id}/{status}", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Ticket> updateTicketStatus(@PathVariable UUID id, @PathVariable Status status);

  @DeleteMapping(value = "/control")
  ResponseEntity<Integer> deleteTickets(@RequestBody List<UUID> ids);

  @PostMapping(value = "/control")
  ResponseEntity<List<Ticket>> addTickets(@RequestBody CreateTicketDto createTicketDto);
}
