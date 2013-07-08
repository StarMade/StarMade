package org.jasypt.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

public final class CommonUtils
{
  public static final String STRING_OUTPUT_TYPE_BASE64 = "base64";
  public static final String STRING_OUTPUT_TYPE_HEXADECIMAL = "hexadecimal";
  private static final List STRING_OUTPUT_TYPE_HEXADECIMAL_NAMES = Arrays.asList(new String[] { "HEXADECIMAL", "HEXA", "0X", "HEX", "HEXADEC" });
  private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
  
  public static Boolean getStandardBooleanValue(String valueStr)
  {
    if (valueStr == null) {
      return null;
    }
    String upperValue = valueStr.toUpperCase();
    if (("TRUE".equals(upperValue)) || ("ON".equals(upperValue)) || ("YES".equals(upperValue))) {
      return Boolean.TRUE;
    }
    if (("FALSE".equals(upperValue)) || ("OFF".equals(upperValue)) || ("NO".equals(upperValue))) {
      return Boolean.FALSE;
    }
    return null;
  }
  
  public static String getStandardStringOutputType(String valueStr)
  {
    if (valueStr == null) {
      return null;
    }
    if (STRING_OUTPUT_TYPE_HEXADECIMAL_NAMES.contains(valueStr.toUpperCase())) {
      return "hexadecimal";
    }
    return "base64";
  }
  
  public static String toHexadecimal(byte[] message)
  {
    if (message == null) {
      return null;
    }
    StringBuffer buffer = new StringBuffer();
    for (int local_i = 0; local_i < message.length; local_i++)
    {
      int curByte = message[local_i] & 0xFF;
      buffer.append(hexDigits[(curByte >> 4)]);
      buffer.append(hexDigits[(curByte & 0xF)]);
    }
    return buffer.toString();
  }
  
  public static byte[] fromHexadecimal(String message)
  {
    if (message == null) {
      return null;
    }
    if (message.length() % 2 != 0) {
      throw new EncryptionOperationNotPossibleException();
    }
    try
    {
      byte[] result = new byte[message.length() / 2];
      int local_i = 0;
      while (local_i < message.length())
      {
        int first = Integer.parseInt("" + message.charAt(local_i), 16);
        int second = Integer.parseInt("" + message.charAt(local_i + 1), 16);
        result[(local_i / 2)] = ((byte)(0 + ((first & 0xFF) << 4) + (second & 0xFF)));
        local_i += 2;
      }
      return result;
    }
    catch (Exception result)
    {
      throw new EncryptionOperationNotPossibleException();
    }
  }
  
  public static boolean isEmpty(String string)
  {
    return (string == null) || (string.length() == 0);
  }
  
  public static boolean isNotEmpty(String string)
  {
    return (string != null) && (string.length() != 0);
  }
  
  public static void validateNotNull(Object object, String message)
  {
    if (object == null) {
      throw new IllegalArgumentException(message);
    }
  }
  
  public static void validateNotEmpty(String string, String message)
  {
    if (isEmpty(string)) {
      throw new IllegalArgumentException(message);
    }
  }
  
  public static void validateIsTrue(boolean expression, String message)
  {
    if (!expression) {
      throw new IllegalArgumentException(message);
    }
  }
  
  public static String[] split(String string)
  {
    return split(string, null);
  }
  
  public static String[] split(String string, String separators)
  {
    if (string == null) {
      return null;
    }
    int length = string.length();
    if (length == 0) {
      return new String[0];
    }
    List results = new ArrayList();
    int local_i = 0;
    int start = 0;
    boolean tokenInProgress = false;
    if (separators == null) {
      while (local_i < length) {
        if (Character.isWhitespace(string.charAt(local_i)))
        {
          if (tokenInProgress)
          {
            results.add(string.substring(start, local_i));
            tokenInProgress = false;
          }
          local_i++;
          start = local_i;
        }
        else
        {
          tokenInProgress = true;
          local_i++;
        }
      }
    }
    if (separators.length() == 1)
    {
      char separator = separators.charAt(0);
      while (local_i < length) {
        if (string.charAt(local_i) == separator)
        {
          if (tokenInProgress)
          {
            results.add(string.substring(start, local_i));
            tokenInProgress = false;
          }
          local_i++;
          start = local_i;
        }
        else
        {
          tokenInProgress = true;
          local_i++;
        }
      }
    }
    else
    {
      while (local_i < length) {
        if (separators.indexOf(string.charAt(local_i)) >= 0)
        {
          if (tokenInProgress)
          {
            results.add(string.substring(start, local_i));
            tokenInProgress = false;
          }
          local_i++;
          start = local_i;
        }
        else
        {
          tokenInProgress = true;
          local_i++;
        }
      }
    }
    if (tokenInProgress) {
      results.add(string.substring(start, local_i));
    }
    return (String[])results.toArray(new String[results.size()]);
  }
  
  public static String substringBefore(String string, String separator)
  {
    if ((isEmpty(string)) || (separator == null)) {
      return string;
    }
    if (separator.length() == 0) {
      return "";
    }
    int pos = string.indexOf(separator);
    if (pos == -1) {
      return string;
    }
    return string.substring(0, pos);
  }
  
  public static String substringAfter(String string, String separator)
  {
    if (isEmpty(string)) {
      return string;
    }
    if (separator == null) {
      return "";
    }
    int pos = string.indexOf(separator);
    if (pos == -1) {
      return "";
    }
    return string.substring(pos + separator.length());
  }
  
  public static int nextRandomInt()
  {
    return (int)(Math.random() * 2147483647.0D);
  }
  
  public static byte[] appendArrays(byte[] firstArray, byte[] secondArray)
  {
    validateNotNull(firstArray, "Appended array cannot be null");
    validateNotNull(secondArray, "Appended array cannot be null");
    byte[] result = new byte[firstArray.length + secondArray.length];
    System.arraycopy(firstArray, 0, result, 0, firstArray.length);
    System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
    return result;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.jasypt.commons.CommonUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */