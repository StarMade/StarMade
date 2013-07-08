package org.apache.commons.lang3;

public class CharUtils
{
  private static final String[] CHAR_STRING_ARRAY = new String[''];
  public static final char field_1809 = '\n';
  public static final char field_1810 = '\r';
  
  @Deprecated
  public static Character toCharacterObject(char local_ch)
  {
    return Character.valueOf(local_ch);
  }
  
  public static Character toCharacterObject(String str)
  {
    if (StringUtils.isEmpty(str)) {
      return null;
    }
    return Character.valueOf(str.charAt(0));
  }
  
  public static char toChar(Character local_ch)
  {
    if (local_ch == null) {
      throw new IllegalArgumentException("The Character must not be null");
    }
    return local_ch.charValue();
  }
  
  public static char toChar(Character local_ch, char defaultValue)
  {
    if (local_ch == null) {
      return defaultValue;
    }
    return local_ch.charValue();
  }
  
  public static char toChar(String str)
  {
    if (StringUtils.isEmpty(str)) {
      throw new IllegalArgumentException("The String must not be empty");
    }
    return str.charAt(0);
  }
  
  public static char toChar(String str, char defaultValue)
  {
    if (StringUtils.isEmpty(str)) {
      return defaultValue;
    }
    return str.charAt(0);
  }
  
  public static int toIntValue(char local_ch)
  {
    if (!isAsciiNumeric(local_ch)) {
      throw new IllegalArgumentException("The character " + local_ch + " is not in the range '0' - '9'");
    }
    return local_ch - '0';
  }
  
  public static int toIntValue(char local_ch, int defaultValue)
  {
    if (!isAsciiNumeric(local_ch)) {
      return defaultValue;
    }
    return local_ch - '0';
  }
  
  public static int toIntValue(Character local_ch)
  {
    if (local_ch == null) {
      throw new IllegalArgumentException("The character must not be null");
    }
    return toIntValue(local_ch.charValue());
  }
  
  public static int toIntValue(Character local_ch, int defaultValue)
  {
    if (local_ch == null) {
      return defaultValue;
    }
    return toIntValue(local_ch.charValue(), defaultValue);
  }
  
  public static String toString(char local_ch)
  {
    if (local_ch < '') {
      return CHAR_STRING_ARRAY[local_ch];
    }
    return new String(new char[] { local_ch });
  }
  
  public static String toString(Character local_ch)
  {
    if (local_ch == null) {
      return null;
    }
    return toString(local_ch.charValue());
  }
  
  public static String unicodeEscaped(char local_ch)
  {
    if (local_ch < '\020') {
      return "\\u000" + Integer.toHexString(local_ch);
    }
    if (local_ch < 'Ā') {
      return "\\u00" + Integer.toHexString(local_ch);
    }
    if (local_ch < 'က') {
      return "\\u0" + Integer.toHexString(local_ch);
    }
    return "\\u" + Integer.toHexString(local_ch);
  }
  
  public static String unicodeEscaped(Character local_ch)
  {
    if (local_ch == null) {
      return null;
    }
    return unicodeEscaped(local_ch.charValue());
  }
  
  public static boolean isAscii(char local_ch)
  {
    return local_ch < '';
  }
  
  public static boolean isAsciiPrintable(char local_ch)
  {
    return (local_ch >= ' ') && (local_ch < '');
  }
  
  public static boolean isAsciiControl(char local_ch)
  {
    return (local_ch < ' ') || (local_ch == '');
  }
  
  public static boolean isAsciiAlpha(char local_ch)
  {
    return ((local_ch >= 'A') && (local_ch <= 'Z')) || ((local_ch >= 'a') && (local_ch <= 'z'));
  }
  
  public static boolean isAsciiAlphaUpper(char local_ch)
  {
    return (local_ch >= 'A') && (local_ch <= 'Z');
  }
  
  public static boolean isAsciiAlphaLower(char local_ch)
  {
    return (local_ch >= 'a') && (local_ch <= 'z');
  }
  
  public static boolean isAsciiNumeric(char local_ch)
  {
    return (local_ch >= '0') && (local_ch <= '9');
  }
  
  public static boolean isAsciiAlphanumeric(char local_ch)
  {
    return ((local_ch >= 'A') && (local_ch <= 'Z')) || ((local_ch >= 'a') && (local_ch <= 'z')) || ((local_ch >= '0') && (local_ch <= '9'));
  }
  
  static
  {
    for (char local_c = '\000'; local_c < CHAR_STRING_ARRAY.length; local_c = (char)(local_c + '\001')) {
      CHAR_STRING_ARRAY[local_c] = String.valueOf(local_c);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.CharUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */