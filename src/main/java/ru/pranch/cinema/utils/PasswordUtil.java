package ru.pranch.cinema.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {
  public static String encode(String password) {
    return BCrypt.withDefaults().hashToString(12, password.toCharArray());
  }

  public static boolean check(String password, String encodedPassword) {
    return BCrypt.verifyer().verify(password.toCharArray(), encodedPassword).verified;
  }

}
