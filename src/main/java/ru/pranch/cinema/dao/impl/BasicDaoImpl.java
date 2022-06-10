package ru.pranch.cinema.dao.impl;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.jdbi.v3.core.Jdbi;
import org.springframework.core.GenericTypeResolver;
import ru.pranch.cinema.dao.BasicDao;
import ru.pranch.cinema.enums.TableName;

public abstract class BasicDaoImpl<T> implements BasicDao<T> {
  protected final Jdbi jdbi;

  @SuppressWarnings(value = "unchecked")
  private final Class<T> entityType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BasicDaoImpl.class);

  public BasicDaoImpl(Jdbi jdbi) {
    this.jdbi = jdbi;
  }

  @Override
  public T save(T t) {
    return jdbi.withHandle(handle -> handle
        .createUpdate("insert into " + tableName() + " " + columnNamesForSave() + " values " + valuesForSave(t) + ";")
        .executeAndReturnGeneratedKeys()
        .mapToBean(entityType)
        .first());
  }

  @Override
  public List<T> saveAll(List<T> items) {
    return items.stream()
        .map(row -> jdbi.withHandle(handle ->
            handle.createUpdate("insert into " + tableName() + " " + columnNamesForSave() + " values " + valuesForSave(row) + ";")
                .executeAndReturnGeneratedKeys()
                .mapToBean(entityType)
                .first()))
        .toList();
  }

  @Override
  public Optional<T> update(UUID id, T t) {
    return jdbi.withHandle(handle ->
        handle
            .createUpdate("update " + tableName() + " set " + columnAndValuesForUpdate(t) + " where id = '" + id + "';")
            .executeAndReturnGeneratedKeys()
            .mapToBean(entityType)
            .findOne());
  }

  @Override
  public Optional<T> findById(UUID id) {
    return jdbi.withHandle(handle ->
        handle
            .createQuery("select * from " + tableName() + " where id = :id;")
            .bind("id", id)
            .mapToBean(entityType)
            .findOne());
  }

  @Override
  public List<T> findAllById(List<UUID> ids) {
    return jdbi.withHandle(handle ->
        handle
            .createQuery("select * from " + tableName() + " where id in (<ids>);")
            .bindList("ids", ids)
            .mapToBean(entityType)
            .list());
  }

  @Override
  public List<T> findAll() {
    return jdbi.withHandle(handle ->
        handle
            .createQuery("select * from " + tableName() + ";")
            .mapToBean(entityType)
            .list());
  }

  @Override
  public int deleteById(UUID id) {
    return jdbi.withHandle(handle ->
        handle
            .createUpdate("delete from " + tableName() + " where id = '" + id + "';")
            .execute());
  }

  @Override
  public int deleteAllById(List<UUID> ids) {
    return jdbi.withHandle(handle -> handle
        .createUpdate("delete from " + tableName() + " where id in (<ids>);")
        .bindList("ids", ids)
        .execute()
    );
  }

  private String tableName() {
    return TableName.valueOf(entityType.getSimpleName().toUpperCase()).getDbTableName();
  }

  private String valuesForSave(T t) {
    try {
      return " ( " + Arrays
          .stream(entityType.getDeclaredFields())
          .map(field -> {
            field.setAccessible(true);
            try {
              if (isVarchar(field)) {
                return "'" + field.get(t) + "'";
              }
              return field.get(t).toString();
            } catch (Exception ee) {
              throw new RuntimeException(ee.getMessage());
            }
          })
          .collect(Collectors.joining(", ")) + " ) ";
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private boolean isVarchar(Field field) {
    return field.getType().equals(String.class) || field.getType().equals(LocalDateTime.class) || field.getType().equals(Date.class)
        || field.getType().equals(LocalDate.class) || field.getType().isEnum() || field.getType().equals(UUID.class);
  }

  private String columnNamesForSave() {
    try {
      return " ( " + Arrays
          .stream(entityType.getDeclaredFields())
          .map(field -> field.getName().replaceAll("(([A-Z][a-z])|(\\d{1,}))", "_$1").toLowerCase())
          .collect(Collectors.joining(", ")) + " ) ";
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private String columnAndValuesForUpdate(T t) {
    try {
      return Arrays
          .stream(entityType.getDeclaredFields())
          .filter(field -> !field.getName().equalsIgnoreCase("id"))
          .map(field -> {
            field.setAccessible(true);
            String convertedField = field.getName().replaceAll("(([A-Z][a-z])|(\\d{1,}))", "_$1").toLowerCase();
            try {
              if (isVarchar(field)) {
                return convertedField + " = '" + field.get(t) + "'";
              }
              return convertedField + " = " + field.get(t).toString();
            } catch (Exception ee) {
              throw new RuntimeException(ee.getMessage());
            }
          })
          .collect(Collectors.joining(", "));
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
