package ru.pranch.cinema.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {
  public static char[] encode(char[] password) {
    return BCrypt.withDefaults().hashToChar(12, password);
  }

  public static boolean check(String password, String encodedPassword) {
    return BCrypt.verifyer().verify(password.toCharArray(), encodedPassword).verified;
  }

}
