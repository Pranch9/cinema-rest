package ru.pranch.cinema.model;

import java.util.UUID;

public class Cinema {
  private UUID id;
  private UUID addressId;
  private String cinemaName;

  public Cinema() {
    this.id = UUID.randomUUID();
  }

  public UUID getAddressId() {
    return addressId;
  }

  public void setAddressId(UUID addressId) {
    this.addressId = addressId;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public String getCinemaName() {
    return cinemaName;
  }

  public void setCinemaName(String cinemaName) {
    this.cinemaName = cinemaName;
  }

  @Override
  public String toString() {
    return "Cinema{" +
        "id=" + id +
        ", addressId=" + addressId +
        ", cinemaName='" + cinemaName + '\'' +
        '}';
  }
}
