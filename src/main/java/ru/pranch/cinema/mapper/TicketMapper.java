package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.TicketDto;
import ru.pranch.cinema.model.Ticket;

public class TicketMapper {
  public static Ticket mapTicket(TicketDto ticketDto) {
    Ticket ticket = new Ticket();
    ticket.setPrice(ticketDto.getPrice());
    ticket.setSeatId(ticketDto.getSeatId());
    ticket.setStatus(ticketDto.getStatus());
    ticket.setSessionId(ticketDto.getSessionId());
    ticket.setUserId(ticketDto.getUserId());

    return ticket;
  }
}
