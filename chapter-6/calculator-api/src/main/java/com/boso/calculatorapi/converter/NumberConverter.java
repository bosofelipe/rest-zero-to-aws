package com.boso.calculatorapi.converter;

public class NumberConverter {

  private static final String NUMBER_PATTERN = "[-+]?[0-9]*\\.?[0-9]+";

  public static Double toDouble(final String strNumber) {
    if (strNumber == null) {
      return 0D;
    }
    String number = replaceToDot(strNumber);
    if (isNumeric(number)) {
      return Double.parseDouble(number);
    }
    return 0D;
  }

  public static boolean isNumeric(final String strNumber) {
    if (strNumber == null) {
      return false;
    }
    String number = replaceToDot(strNumber);
    return number.matches(NUMBER_PATTERN);
  }

  private static String replaceToDot(final String strNumber) {
    return strNumber.replace(",", ".");
  }
}
