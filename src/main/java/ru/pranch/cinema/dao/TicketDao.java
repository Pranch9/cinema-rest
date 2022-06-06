package ru.pranch.cinema.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import ru.pranch.cinema.model.Ticket;

public interface TicketDao extends BasicDao<Ticket> {
  List<Ticket> findAllBySessionId(UUID sessionId);

  Optional<Ticket> findBySessionAndSeat(UUID sessionId, UUID seatId);
}
