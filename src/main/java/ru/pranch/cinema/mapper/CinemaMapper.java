package ru.pranch.cinema.mapper;

import java.util.List;
import ru.pranch.cinema.dto.CreateAddressDto;
import ru.pranch.cinema.dto.cinema.CreateCinemaDto;
import ru.pranch.cinema.dto.cinema.ResponseCreateCinemaDto;
import ru.pranch.cinema.dto.cinema.UpdateCinemaDto;
import ru.pranch.cinema.dto.cinema_hall.CreateCinemaHallDto;
import ru.pranch.cinema.dto.cinema_hall.GetCinemaHallDto;
import ru.pranch.cinema.model.Address;
import ru.pranch.cinema.model.Cinema;
import ru.pranch.cinema.model.CinemaHall;

public class CinemaMapper {
  public static Cinema mapCreateCinema(CreateCinemaDto createCinemaDto) {
    Cinema cinema = new Cinema();
    cinema.setCinemaName(createCinemaDto.getCinemaName());

    return cinema;
  }

  public static Cinema mapUpdateCinema(UpdateCinemaDto updateCinemaDto) {
    Cinema cinema = new Cinema();
    cinema.setCinemaName(updateCinemaDto.getCinemaName());

    return cinema;
  }

  public static ResponseCreateCinemaDto mapResponseCreateCinema(Cinema cinema, List<GetCinemaHallDto> getCinemaHallDtos, Address addressFromDb) {
    ResponseCreateCinemaDto responseCreateCinemaDto = new ResponseCreateCinemaDto();
    responseCreateCinemaDto.setId(cinema.getId());
    responseCreateCinemaDto.setCinemaName(cinema.getCinemaName());
    responseCreateCinemaDto.setAddress(addressFromDb);
    responseCreateCinemaDto.setGetCinemaHallDtos(getCinemaHallDtos);

    return responseCreateCinemaDto;
  }

  public static CreateCinemaDto mapResponseUpdateCinema(Cinema cinema, List<CreateCinemaHallDto> createCinemaHallDtos, CreateAddressDto createAddressDto) {
    CreateCinemaDto createCinemaDto = new CreateCinemaDto();
    createCinemaDto.setCinemaName(cinema.getCinemaName());
    createCinemaDto.setCreateCinemaHallDtos(createCinemaHallDtos);
    createCinemaDto.setCreateAddressDto(createAddressDto);

    return createCinemaDto;
  }

  public static CinemaHall mapCreateCinemaHall(CreateCinemaHallDto cinemaHallDto) {
    CinemaHall cinemaHall = new CinemaHall();
    cinemaHall.setHallName(cinemaHallDto.getHallName());
    cinemaHall.setRowsNumber(cinemaHallDto.getRowsNumber());
    cinemaHall.setPlacesNumber(cinemaHallDto.getPlacesNumber());

    return cinemaHall;
  }

  public static GetCinemaHallDto mapGetCinemaHall(CinemaHall cinemaHall) {
    GetCinemaHallDto getCinemaHallDto = new GetCinemaHallDto();
    getCinemaHallDto.setHallName(cinemaHall.getHallName());
    getCinemaHallDto.setId(cinemaHall.getId());
    getCinemaHallDto.setPlacesNumber(cinemaHall.getPlacesNumber());
    getCinemaHallDto.setRowsNumber(cinemaHall.getRowsNumber());

    return getCinemaHallDto;
  }

  public static CreateCinemaHallDto mapCreateCinemaHall(CinemaHall cinemaHall) {
    CreateCinemaHallDto createCinemaHallDto = new CreateCinemaHallDto();
    createCinemaHallDto.setHallName(cinemaHall.getHallName());
    createCinemaHallDto.setPlacesNumber(cinemaHall.getPlacesNumber());
    createCinemaHallDto.setRowsNumber(cinemaHall.getRowsNumber());

    return createCinemaHallDto;
  }
}
