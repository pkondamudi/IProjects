package com.marrker.util;
import java.security.SecureRandom;
import java.math.BigInteger;

public final class SessionIdentifierGenerator {

  public static String nextSessionId() {
    return new BigInteger(130, new SecureRandom()).toString(32);
  }
}