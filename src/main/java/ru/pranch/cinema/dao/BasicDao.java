package ru.pranch.cinema.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicDao<T> {
  Optional<T> save(T t);

  Optional<T> update(UUID id, T t);

  Optional<T> findById(UUID id);

  int deleteById(UUID id);

  List<T> saveAll(List<T> items);

  List<T> findAllById(List<UUID> ids);

  List<T> findAll();

  int deleteAllById(List<UUID> ids);
}
