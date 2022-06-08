package ru.pranch.cinema.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.TicketDao;
import ru.pranch.cinema.model.Ticket;

@Repository
public class TicketDaoImpl extends BasicDaoImpl<Ticket> implements TicketDao {

  public TicketDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  @Override
  public List<Ticket> findAllBySessionId(UUID sessionId) {
    return jdbi.withHandle(handle ->
        handle.createQuery("""
                select * from tickets where session_id = :sessionId
                """)
            .bind("sessionId", sessionId)
            .mapToBean(Ticket.class)
            .list());
  }

  @Override
  public Optional<Ticket> findBySessionAndSeat(UUID sessionId, UUID seatId) {
    return jdbi.withHandle(handle ->
        handle.createQuery("""
                select * from tickets where session_id = :sessionId and seat_id = :seatId
                """)
            .bind("sessionId", sessionId)
            .bind("seatId", seatId)
            .mapToBean(Ticket.class)
            .findOne());
  }
}
