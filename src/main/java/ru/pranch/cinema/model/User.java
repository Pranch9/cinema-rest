package ru.pranch.cinema.model;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class User {
  private UUID id;
  private String username;
  private String mail;
  private char[] password;
  private Date creationDate;

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

  public char[] getPassword() {
    return password;
  }

  public void setPassword(char[] password) {
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
      ", password=" + Arrays.toString(password) +
      ", creationDate=" + creationDate +
      '}';
  }
}
