package ru.pranch.cinema.model;

import java.util.Date;
import java.util.UUID;

public class User {
  private UUID id;
  private String username;
  private String mail;
  private String password;
  private Date creationDate;

  public User() {
    this.id = UUID.randomUUID();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", mail='" + mail + '\'' +
        ", password='" + password + '\'' +
        ", creationDate=" + creationDate +
        '}';
  }
}
