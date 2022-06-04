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
  private final Jdbi jdbi;

  public TicketDaoImpl(Jdbi jdbi) {
    super(jdbi);
    this.jdbi = jdbi;
  }

  @Override
  public List<Ticket> findAllByScreeningId(UUID id) {
    return null;
  }

  @Override
  public Optional<Ticket> findByScreeningAndSeat(UUID screeningId, UUID seatId) {
    return Optional.empty();
  }
}
