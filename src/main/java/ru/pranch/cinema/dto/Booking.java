package ru.pranch.cinema.dto;

import java.util.UUID;

public class Booking {
  private UUID seatId;
  private boolean isBooked;

  public UUID getSeatId() {
    return seatId;
  }

  public void setSeatId(UUID seatId) {
    this.seatId = seatId;
  }

  public boolean isBooked() {
    return isBooked;
  }

  public void setBooked(boolean booked) {
    isBooked = booked;
  }
}
