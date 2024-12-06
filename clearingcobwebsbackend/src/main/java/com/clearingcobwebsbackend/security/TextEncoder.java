package com.clearingcobwebsbackend.security;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Component
public class TextEncoder {

  private static final Argon2PasswordEncoder argon2 = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

  public static String encode(final String rawText) {
    return argon2.encode(rawText);
  }

  public static boolean match(String rawText, String hash) {
    return argon2.matches(rawText, hash);
  }

}
