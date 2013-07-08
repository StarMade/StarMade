package org.apache.commons.lang3.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;

public class NumberUtils
{
  public static final Long LONG_ZERO = Long.valueOf(0L);
  public static final Long LONG_ONE = Long.valueOf(1L);
  public static final Long LONG_MINUS_ONE = Long.valueOf(-1L);
  public static final Integer INTEGER_ZERO = Integer.valueOf(0);
  public static final Integer INTEGER_ONE = Integer.valueOf(1);
  public static final Integer INTEGER_MINUS_ONE = Integer.valueOf(-1);
  public static final Short SHORT_ZERO = Short.valueOf((short)0);
  public static final Short SHORT_ONE = Short.valueOf((short)1);
  public static final Short SHORT_MINUS_ONE = Short.valueOf((short)-1);
  public static final Byte BYTE_ZERO = Byte.valueOf((byte)0);
  public static final Byte BYTE_ONE = Byte.valueOf((byte)1);
  public static final Byte BYTE_MINUS_ONE = Byte.valueOf((byte)-1);
  public static final Double DOUBLE_ZERO = Double.valueOf(0.0D);
  public static final Double DOUBLE_ONE = Double.valueOf(1.0D);
  public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0D);
  public static final Float FLOAT_ZERO = Float.valueOf(0.0F);
  public static final Float FLOAT_ONE = Float.valueOf(1.0F);
  public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0F);
  
  public static int toInt(String str)
  {
    return toInt(str, 0);
  }
  
  public static int toInt(String str, int defaultValue)
  {
    if (str == null) {
      return defaultValue;
    }
    try
    {
      return Integer.parseInt(str);
    }
    catch (NumberFormatException nfe) {}
    return defaultValue;
  }
  
  public static long toLong(String str)
  {
    return toLong(str, 0L);
  }
  
  public static long toLong(String str, long defaultValue)
  {
    if (str == null) {
      return defaultValue;
    }
    try
    {
      return Long.parseLong(str);
    }
    catch (NumberFormatException nfe) {}
    return defaultValue;
  }
  
  public static float toFloat(String str)
  {
    return toFloat(str, 0.0F);
  }
  
  public static float toFloat(String str, float defaultValue)
  {
    if (str == null) {
      return defaultValue;
    }
    try
    {
      return Float.parseFloat(str);
    }
    catch (NumberFormatException nfe) {}
    return defaultValue;
  }
  
  public static double toDouble(String str)
  {
    return toDouble(str, 0.0D);
  }
  
  public static double toDouble(String str, double defaultValue)
  {
    if (str == null) {
      return defaultValue;
    }
    try
    {
      return Double.parseDouble(str);
    }
    catch (NumberFormatException nfe) {}
    return defaultValue;
  }
  
  public static byte toByte(String str)
  {
    return toByte(str, (byte)0);
  }
  
  public static byte toByte(String str, byte defaultValue)
  {
    if (str == null) {
      return defaultValue;
    }
    try
    {
      return Byte.parseByte(str);
    }
    catch (NumberFormatException nfe) {}
    return defaultValue;
  }
  
  public static short toShort(String str)
  {
    return toShort(str, (short)0);
  }
  
  public static short toShort(String str, short defaultValue)
  {
    if (str == null) {
      return defaultValue;
    }
    try
    {
      return Short.parseShort(str);
    }
    catch (NumberFormatException nfe) {}
    return defaultValue;
  }
  
  public static Number createNumber(String str)
    throws NumberFormatException
  {
    if (str == null) {
      return null;
    }
    if (StringUtils.isBlank(str)) {
      throw new NumberFormatException("A blank string is not a valid number");
    }
    if (str.startsWith("--")) {
      return null;
    }
    if ((str.startsWith("0x")) || (str.startsWith("-0x")) || (str.startsWith("0X")) || (str.startsWith("-0X"))) {
      return createInteger(str);
    }
    char lastChar = str.charAt(str.length() - 1);
    int decPos = str.indexOf('.');
    int expPos = str.indexOf('e') + str.indexOf('E') + 1;
    String mant;
    String mant;
    String dec;
    if (decPos > -1)
    {
      String dec;
      String dec;
      if (expPos > -1)
      {
        if ((expPos < decPos) || (expPos > str.length())) {
          throw new NumberFormatException(str + " is not a valid number.");
        }
        dec = str.substring(decPos + 1, expPos);
      }
      else
      {
        dec = str.substring(decPos + 1);
      }
      mant = str.substring(0, decPos);
    }
    else
    {
      String mant;
      if (expPos > -1)
      {
        if (expPos > str.length()) {
          throw new NumberFormatException(str + " is not a valid number.");
        }
        mant = str.substring(0, expPos);
      }
      else
      {
        mant = str;
      }
      dec = null;
    }
    if ((!Character.isDigit(lastChar)) && (lastChar != '.'))
    {
      String exp;
      String exp;
      if ((expPos > -1) && (expPos < str.length() - 1)) {
        exp = str.substring(expPos + 1, str.length() - 1);
      } else {
        exp = null;
      }
      String numeric = str.substring(0, str.length() - 1);
      boolean allZeros = (isAllZeros(mant)) && (isAllZeros(exp));
      switch (lastChar)
      {
      case 'L': 
      case 'l': 
        if ((dec == null) && (exp == null) && (((numeric.charAt(0) == '-') && (isDigits(numeric.substring(1)))) || (isDigits(numeric)))) {
          try
          {
            return createLong(numeric);
          }
          catch (NumberFormatException nfe)
          {
            return createBigInteger(numeric);
          }
        }
        throw new NumberFormatException(str + " is not a valid number.");
      case 'F': 
      case 'f': 
        try
        {
          Float nfe = createFloat(numeric);
          if ((!nfe.isInfinite()) && ((nfe.floatValue() != 0.0F) || (allZeros))) {
            return nfe;
          }
        }
        catch (NumberFormatException nfe) {}
      case 'D': 
      case 'd': 
        try
        {
          Double nfe = createDouble(numeric);
          if ((!nfe.isInfinite()) && ((nfe.floatValue() != 0.0D) || (allZeros))) {
            return nfe;
          }
        }
        catch (NumberFormatException nfe) {}
        try
        {
          return createBigDecimal(numeric);
        }
        catch (NumberFormatException nfe) {}
      }
      throw new NumberFormatException(str + " is not a valid number.");
    }
    String exp;
    String exp;
    if ((expPos > -1) && (expPos < str.length() - 1)) {
      exp = str.substring(expPos + 1, str.length());
    } else {
      exp = null;
    }
    if ((dec == null) && (exp == null)) {
      try
      {
        return createInteger(str);
      }
      catch (NumberFormatException numeric)
      {
        try
        {
          return createLong(str);
        }
        catch (NumberFormatException numeric)
        {
          return createBigInteger(str);
        }
      }
    }
    boolean numeric = (isAllZeros(mant)) && (isAllZeros(exp));
    try
    {
      Float allZeros = createFloat(str);
      if ((!allZeros.isInfinite()) && ((allZeros.floatValue() != 0.0F) || (numeric))) {
        return allZeros;
      }
    }
    catch (NumberFormatException allZeros) {}
    try
    {
      Double allZeros = createDouble(str);
      if ((!allZeros.isInfinite()) && ((allZeros.doubleValue() != 0.0D) || (numeric))) {
        return allZeros;
      }
    }
    catch (NumberFormatException allZeros) {}
    return createBigDecimal(str);
  }
  
  private static boolean isAllZeros(String str)
  {
    if (str == null) {
      return true;
    }
    for (int local_i = str.length() - 1; local_i >= 0; local_i--) {
      if (str.charAt(local_i) != '0') {
        return false;
      }
    }
    return str.length() > 0;
  }
  
  public static Float createFloat(String str)
  {
    if (str == null) {
      return null;
    }
    return Float.valueOf(str);
  }
  
  public static Double createDouble(String str)
  {
    if (str == null) {
      return null;
    }
    return Double.valueOf(str);
  }
  
  public static Integer createInteger(String str)
  {
    if (str == null) {
      return null;
    }
    return Integer.decode(str);
  }
  
  public static Long createLong(String str)
  {
    if (str == null) {
      return null;
    }
    return Long.decode(str);
  }
  
  public static BigInteger createBigInteger(String str)
  {
    if (str == null) {
      return null;
    }
    return new BigInteger(str);
  }
  
  public static BigDecimal createBigDecimal(String str)
  {
    if (str == null) {
      return null;
    }
    if (StringUtils.isBlank(str)) {
      throw new NumberFormatException("A blank string is not a valid number");
    }
    return new BigDecimal(str);
  }
  
  public static long min(long[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    long min = array[0];
    for (int local_i = 1; local_i < array.length; local_i++) {
      if (array[local_i] < min) {
        min = array[local_i];
      }
    }
    return min;
  }
  
  public static int min(int[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    int min = array[0];
    for (int local_j = 1; local_j < array.length; local_j++) {
      if (array[local_j] < min) {
        min = array[local_j];
      }
    }
    return min;
  }
  
  public static short min(short[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    short min = array[0];
    for (int local_i = 1; local_i < array.length; local_i++) {
      if (array[local_i] < min) {
        min = array[local_i];
      }
    }
    return min;
  }
  
  public static byte min(byte[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    byte min = array[0];
    for (int local_i = 1; local_i < array.length; local_i++) {
      if (array[local_i] < min) {
        min = array[local_i];
      }
    }
    return min;
  }
  
  public static double min(double[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    double min = array[0];
    for (int local_i = 1; local_i < array.length; local_i++)
    {
      if (Double.isNaN(array[local_i])) {
        return (0.0D / 0.0D);
      }
      if (array[local_i] < min) {
        min = array[local_i];
      }
    }
    return min;
  }
  
  public static float min(float[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    float min = array[0];
    for (int local_i = 1; local_i < array.length; local_i++)
    {
      if (Float.isNaN(array[local_i])) {
        return (0.0F / 0.0F);
      }
      if (array[local_i] < min) {
        min = array[local_i];
      }
    }
    return min;
  }
  
  public static long max(long[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    long max = array[0];
    for (int local_j = 1; local_j < array.length; local_j++) {
      if (array[local_j] > max) {
        max = array[local_j];
      }
    }
    return max;
  }
  
  public static int max(int[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    int max = array[0];
    for (int local_j = 1; local_j < array.length; local_j++) {
      if (array[local_j] > max) {
        max = array[local_j];
      }
    }
    return max;
  }
  
  public static short max(short[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    short max = array[0];
    for (int local_i = 1; local_i < array.length; local_i++) {
      if (array[local_i] > max) {
        max = array[local_i];
      }
    }
    return max;
  }
  
  public static byte max(byte[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    byte max = array[0];
    for (int local_i = 1; local_i < array.length; local_i++) {
      if (array[local_i] > max) {
        max = array[local_i];
      }
    }
    return max;
  }
  
  public static double max(double[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    double max = array[0];
    for (int local_j = 1; local_j < array.length; local_j++)
    {
      if (Double.isNaN(array[local_j])) {
        return (0.0D / 0.0D);
      }
      if (array[local_j] > max) {
        max = array[local_j];
      }
    }
    return max;
  }
  
  public static float max(float[] array)
  {
    if (array == null) {
      throw new IllegalArgumentException("The Array must not be null");
    }
    if (array.length == 0) {
      throw new IllegalArgumentException("Array cannot be empty.");
    }
    float max = array[0];
    for (int local_j = 1; local_j < array.length; local_j++)
    {
      if (Float.isNaN(array[local_j])) {
        return (0.0F / 0.0F);
      }
      if (array[local_j] > max) {
        max = array[local_j];
      }
    }
    return max;
  }
  
  public static long min(long local_a, long local_b, long local_c)
  {
    if (local_b < local_a) {
      local_a = local_b;
    }
    if (local_c < local_a) {
      local_a = local_c;
    }
    return local_a;
  }
  
  public static int min(int local_a, int local_b, int local_c)
  {
    if (local_b < local_a) {
      local_a = local_b;
    }
    if (local_c < local_a) {
      local_a = local_c;
    }
    return local_a;
  }
  
  public static short min(short local_a, short local_b, short local_c)
  {
    if (local_b < local_a) {
      local_a = local_b;
    }
    if (local_c < local_a) {
      local_a = local_c;
    }
    return local_a;
  }
  
  public static byte min(byte local_a, byte local_b, byte local_c)
  {
    if (local_b < local_a) {
      local_a = local_b;
    }
    if (local_c < local_a) {
      local_a = local_c;
    }
    return local_a;
  }
  
  public static double min(double local_a, double local_b, double local_c)
  {
    return Math.min(Math.min(local_a, local_b), local_c);
  }
  
  public static float min(float local_a, float local_b, float local_c)
  {
    return Math.min(Math.min(local_a, local_b), local_c);
  }
  
  public static long max(long local_a, long local_b, long local_c)
  {
    if (local_b > local_a) {
      local_a = local_b;
    }
    if (local_c > local_a) {
      local_a = local_c;
    }
    return local_a;
  }
  
  public static int max(int local_a, int local_b, int local_c)
  {
    if (local_b > local_a) {
      local_a = local_b;
    }
    if (local_c > local_a) {
      local_a = local_c;
    }
    return local_a;
  }
  
  public static short max(short local_a, short local_b, short local_c)
  {
    if (local_b > local_a) {
      local_a = local_b;
    }
    if (local_c > local_a) {
      local_a = local_c;
    }
    return local_a;
  }
  
  public static byte max(byte local_a, byte local_b, byte local_c)
  {
    if (local_b > local_a) {
      local_a = local_b;
    }
    if (local_c > local_a) {
      local_a = local_c;
    }
    return local_a;
  }
  
  public static double max(double local_a, double local_b, double local_c)
  {
    return Math.max(Math.max(local_a, local_b), local_c);
  }
  
  public static float max(float local_a, float local_b, float local_c)
  {
    return Math.max(Math.max(local_a, local_b), local_c);
  }
  
  public static boolean isDigits(String str)
  {
    if (StringUtils.isEmpty(str)) {
      return false;
    }
    for (int local_i = 0; local_i < str.length(); local_i++) {
      if (!Character.isDigit(str.charAt(local_i))) {
        return false;
      }
    }
    return true;
  }
  
  public static boolean isNumber(String str)
  {
    if (StringUtils.isEmpty(str)) {
      return false;
    }
    char[] chars = str.toCharArray();
    int local_sz = chars.length;
    boolean hasExp = false;
    boolean hasDecPoint = false;
    boolean allowSigns = false;
    boolean foundDigit = false;
    int start = chars[0] == '-' ? 1 : 0;
    if ((local_sz > start + 1) && (chars[start] == '0') && (chars[(start + 1)] == 'x'))
    {
      int local_i = start + 2;
      if (local_i == local_sz) {
        return false;
      }
      while (local_i < chars.length)
      {
        if (((chars[local_i] < '0') || (chars[local_i] > '9')) && ((chars[local_i] < 'a') || (chars[local_i] > 'f')) && ((chars[local_i] < 'A') || (chars[local_i] > 'F'))) {
          return false;
        }
        local_i++;
      }
      return true;
    }
    local_sz--;
    for (int local_i = start; (local_i < local_sz) || ((local_i < local_sz + 1) && (allowSigns) && (!foundDigit)); local_i++) {
      if ((chars[local_i] >= '0') && (chars[local_i] <= '9'))
      {
        foundDigit = true;
        allowSigns = false;
      }
      else if (chars[local_i] == '.')
      {
        if ((hasDecPoint) || (hasExp)) {
          return false;
        }
        hasDecPoint = true;
      }
      else if ((chars[local_i] == 'e') || (chars[local_i] == 'E'))
      {
        if (hasExp) {
          return false;
        }
        if (!foundDigit) {
          return false;
        }
        hasExp = true;
        allowSigns = true;
      }
      else if ((chars[local_i] == '+') || (chars[local_i] == '-'))
      {
        if (!allowSigns) {
          return false;
        }
        allowSigns = false;
        foundDigit = false;
      }
      else
      {
        return false;
      }
    }
    if (local_i < chars.length)
    {
      if ((chars[local_i] >= '0') && (chars[local_i] <= '9')) {
        return true;
      }
      if ((chars[local_i] == 'e') || (chars[local_i] == 'E')) {
        return false;
      }
      if (chars[local_i] == '.')
      {
        if ((hasDecPoint) || (hasExp)) {
          return false;
        }
        return foundDigit;
      }
      if ((!allowSigns) && ((chars[local_i] == 'd') || (chars[local_i] == 'D') || (chars[local_i] == 'f') || (chars[local_i] == 'F'))) {
        return foundDigit;
      }
      if ((chars[local_i] == 'l') || (chars[local_i] == 'L')) {
        return (foundDigit) && (!hasExp) && (!hasDecPoint);
      }
      return false;
    }
    return (!allowSigns) && (foundDigit);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.math.NumberUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */