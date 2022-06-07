package ru.pranch.cinema.mapper;

import ru.pranch.cinema.dto.CreateAddressDto;
import ru.pranch.cinema.model.Address;

public class AddressMapper {
  public static Address mapAddress(CreateAddressDto createAddressDto) {
    Address address = new Address();
    address.setCity(createAddressDto.getCity());
    address.setHouseNumber(createAddressDto.getHouseNumber());
    address.setZipCode(createAddressDto.getZipCode());
    address.setStreet(createAddressDto.getStreet());

    return address;
  }
}
