package com.lcaohoanq.samplewebapplication.utils;

public class OtpUtil {

  public static String generateOtp() {
    return String.format("%06d", (int) (Math.random() * 1000000));
  }
}
