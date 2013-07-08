package org.apache.commons.lang3;

import java.util.Random;

public class RandomStringUtils
{
  private static final Random RANDOM = new Random();
  
  public static String random(int count)
  {
    return random(count, false, false);
  }
  
  public static String randomAscii(int count)
  {
    return random(count, 32, 127, false, false);
  }
  
  public static String randomAlphabetic(int count)
  {
    return random(count, true, false);
  }
  
  public static String randomAlphanumeric(int count)
  {
    return random(count, true, true);
  }
  
  public static String randomNumeric(int count)
  {
    return random(count, false, true);
  }
  
  public static String random(int count, boolean letters, boolean numbers)
  {
    return random(count, 0, 0, letters, numbers);
  }
  
  public static String random(int count, int start, int end, boolean letters, boolean numbers)
  {
    return random(count, start, end, letters, numbers, null, RANDOM);
  }
  
  public static String random(int count, int start, int end, boolean letters, boolean numbers, char... chars)
  {
    return random(count, start, end, letters, numbers, chars, RANDOM);
  }
  
  public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random)
  {
    if (count == 0) {
      return "";
    }
    if (count < 0) {
      throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
    }
    if ((start == 0) && (end == 0))
    {
      end = 123;
      start = 32;
      if ((!letters) && (!numbers))
      {
        start = 0;
        end = 2147483647;
      }
    }
    char[] buffer = new char[count];
    int gap = end - start;
    while (count-- != 0)
    {
      char local_ch;
      char local_ch;
      if (chars == null) {
        local_ch = (char)(random.nextInt(gap) + start);
      } else {
        local_ch = chars[(random.nextInt(gap) + start)];
      }
      if (((letters) && (Character.isLetter(local_ch))) || ((numbers) && (Character.isDigit(local_ch))) || ((!letters) && (!numbers)))
      {
        if ((local_ch >= 56320) && (local_ch <= 57343))
        {
          if (count == 0)
          {
            count++;
          }
          else
          {
            buffer[count] = local_ch;
            count--;
            buffer[count] = ((char)(55296 + random.nextInt(128)));
          }
        }
        else if ((local_ch >= 55296) && (local_ch <= 56191))
        {
          if (count == 0)
          {
            count++;
          }
          else
          {
            buffer[count] = ((char)(56320 + random.nextInt(128)));
            count--;
            buffer[count] = local_ch;
          }
        }
        else if ((local_ch >= 56192) && (local_ch <= 56319)) {
          count++;
        } else {
          buffer[count] = local_ch;
        }
      }
      else {
        count++;
      }
    }
    return new String(buffer);
  }
  
  public static String random(int count, String chars)
  {
    if (chars == null) {
      return random(count, 0, 0, false, false, null, RANDOM);
    }
    return random(count, chars.toCharArray());
  }
  
  public static String random(int count, char... chars)
  {
    if (chars == null) {
      return random(count, 0, 0, false, false, null, RANDOM);
    }
    return random(count, 0, chars.length, false, false, chars, RANDOM);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.RandomStringUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */