package ru.pranch.cinema.dto;

import java.util.Date;

public class CreateUserDto {
  private String username;
  private String mail;
  private char[] password;

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
}
