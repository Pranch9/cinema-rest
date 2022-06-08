package ru.pranch.cinema.dao.impl;

import java.util.Locale;
import java.util.Optional;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;
import ru.pranch.cinema.dao.AddressDao;
import ru.pranch.cinema.model.Address;

@Repository
public class AddressDaoImpl extends BasicDaoImpl<Address> implements AddressDao {

  public AddressDaoImpl(Jdbi jdbi) {
    super(jdbi);
  }

  @Override
  public Optional<Address> findAddress(String street, String houseNumber, String city, int zipCode) {
    return jdbi.withHandle(handle -> handle
        .createQuery("""
            select * from addresses where street = :street AND house_number = :houseNumber AND city = :city AND zip_code = :zipCode;
            """)
        .bind("city", city.toLowerCase(Locale.ROOT))
        .bind("street", street.toLowerCase(Locale.ROOT))
        .bind("houseNumber", houseNumber)
        .bind("zipCode", zipCode)
        .mapToBean(Address.class)
        .findFirst());
  }
}
