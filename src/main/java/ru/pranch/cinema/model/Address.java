package ru.pranch.cinema.model;

import java.util.UUID;

public class Address {
  private UUID id;
  private String street;
  private String houseNumber;
  private String city;
  private String zipCode;

  public Address() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @Override
  public String toString() {
    return "Address{" +
        "id=" + id +
        ", street='" + street + '\'' +
        ", houseNumber='" + houseNumber + '\'' +
        ", city='" + city + '\'' +
        ", zipCode='" + zipCode + '\'' +
        '}';
  }
}
