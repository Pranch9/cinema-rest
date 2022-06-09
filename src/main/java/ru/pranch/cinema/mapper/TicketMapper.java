package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.CreateTicketDto;
import ru.pranch.cinema.model.Ticket;

public class TicketMapper {
  public static Ticket mapTicket(CreateTicketDto createTicketDto) {
    Ticket ticket = new Ticket();
    ticket.setPrice(createTicketDto.getPrice());
    ticket.setStatus(createTicketDto.getStatus());
    ticket.setSessionId(createTicketDto.getSessionId());

    return ticket;
  }
}
