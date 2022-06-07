package ru.pranch.cinema.dao;

import java.util.Optional;
import ru.pranch.cinema.model.Address;

public interface AddressDao extends BasicDao<Address> {
  Optional<Address> findAddress(String street, String houseNumber, String city, String zipCode);

}
