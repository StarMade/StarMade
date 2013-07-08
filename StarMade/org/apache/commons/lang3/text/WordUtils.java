package org.apache.commons.lang3.text;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

public class WordUtils
{
  public static String wrap(String str, int wrapLength)
  {
    return wrap(str, wrapLength, null, false);
  }
  
  public static String wrap(String str, int wrapLength, String newLineStr, boolean wrapLongWords)
  {
    if (str == null) {
      return null;
    }
    if (newLineStr == null) {
      newLineStr = SystemUtils.LINE_SEPARATOR;
    }
    if (wrapLength < 1) {
      wrapLength = 1;
    }
    int inputLineLength = str.length();
    int offset = 0;
    StringBuilder wrappedLine = new StringBuilder(inputLineLength + 32);
    while (inputLineLength - offset > wrapLength) {
      if (str.charAt(offset) == ' ')
      {
        offset++;
      }
      else
      {
        int spaceToWrapAt = str.lastIndexOf(' ', wrapLength + offset);
        if (spaceToWrapAt >= offset)
        {
          wrappedLine.append(str.substring(offset, spaceToWrapAt));
          wrappedLine.append(newLineStr);
          offset = spaceToWrapAt + 1;
        }
        else if (wrapLongWords)
        {
          wrappedLine.append(str.substring(offset, wrapLength + offset));
          wrappedLine.append(newLineStr);
          offset += wrapLength;
        }
        else
        {
          spaceToWrapAt = str.indexOf(' ', wrapLength + offset);
          if (spaceToWrapAt >= 0)
          {
            wrappedLine.append(str.substring(offset, spaceToWrapAt));
            wrappedLine.append(newLineStr);
            offset = spaceToWrapAt + 1;
          }
          else
          {
            wrappedLine.append(str.substring(offset));
            offset = inputLineLength;
          }
        }
      }
    }
    wrappedLine.append(str.substring(offset));
    return wrappedLine.toString();
  }
  
  public static String capitalize(String str)
  {
    return capitalize(str, null);
  }
  
  public static String capitalize(String str, char... delimiters)
  {
    int delimLen = delimiters == null ? -1 : delimiters.length;
    if ((StringUtils.isEmpty(str)) || (delimLen == 0)) {
      return str;
    }
    char[] buffer = str.toCharArray();
    boolean capitalizeNext = true;
    for (int local_i = 0; local_i < buffer.length; local_i++)
    {
      char local_ch = buffer[local_i];
      if (isDelimiter(local_ch, delimiters))
      {
        capitalizeNext = true;
      }
      else if (capitalizeNext)
      {
        buffer[local_i] = Character.toTitleCase(local_ch);
        capitalizeNext = false;
      }
    }
    return new String(buffer);
  }
  
  public static String capitalizeFully(String str)
  {
    return capitalizeFully(str, null);
  }
  
  public static String capitalizeFully(String str, char... delimiters)
  {
    int delimLen = delimiters == null ? -1 : delimiters.length;
    if ((StringUtils.isEmpty(str)) || (delimLen == 0)) {
      return str;
    }
    str = str.toLowerCase();
    return capitalize(str, delimiters);
  }
  
  public static String uncapitalize(String str)
  {
    return uncapitalize(str, null);
  }
  
  public static String uncapitalize(String str, char... delimiters)
  {
    int delimLen = delimiters == null ? -1 : delimiters.length;
    if ((StringUtils.isEmpty(str)) || (delimLen == 0)) {
      return str;
    }
    char[] buffer = str.toCharArray();
    boolean uncapitalizeNext = true;
    for (int local_i = 0; local_i < buffer.length; local_i++)
    {
      char local_ch = buffer[local_i];
      if (isDelimiter(local_ch, delimiters))
      {
        uncapitalizeNext = true;
      }
      else if (uncapitalizeNext)
      {
        buffer[local_i] = Character.toLowerCase(local_ch);
        uncapitalizeNext = false;
      }
    }
    return new String(buffer);
  }
  
  public static String swapCase(String str)
  {
    if (StringUtils.isEmpty(str)) {
      return str;
    }
    char[] buffer = str.toCharArray();
    boolean whitespace = true;
    for (int local_i = 0; local_i < buffer.length; local_i++)
    {
      char local_ch = buffer[local_i];
      if (Character.isUpperCase(local_ch))
      {
        buffer[local_i] = Character.toLowerCase(local_ch);
        whitespace = false;
      }
      else if (Character.isTitleCase(local_ch))
      {
        buffer[local_i] = Character.toLowerCase(local_ch);
        whitespace = false;
      }
      else if (Character.isLowerCase(local_ch))
      {
        if (whitespace)
        {
          buffer[local_i] = Character.toTitleCase(local_ch);
          whitespace = false;
        }
        else
        {
          buffer[local_i] = Character.toUpperCase(local_ch);
        }
      }
      else
      {
        whitespace = Character.isWhitespace(local_ch);
      }
    }
    return new String(buffer);
  }
  
  public static String initials(String str)
  {
    return initials(str, null);
  }
  
  public static String initials(String str, char... delimiters)
  {
    if (StringUtils.isEmpty(str)) {
      return str;
    }
    if ((delimiters != null) && (delimiters.length == 0)) {
      return "";
    }
    int strLen = str.length();
    char[] buf = new char[strLen / 2 + 1];
    int count = 0;
    boolean lastWasGap = true;
    for (int local_i = 0; local_i < strLen; local_i++)
    {
      char local_ch = str.charAt(local_i);
      if (isDelimiter(local_ch, delimiters))
      {
        lastWasGap = true;
      }
      else if (lastWasGap)
      {
        buf[(count++)] = local_ch;
        lastWasGap = false;
      }
    }
    return new String(buf, 0, count);
  }
  
  private static boolean isDelimiter(char local_ch, char[] delimiters)
  {
    if (delimiters == null) {
      return Character.isWhitespace(local_ch);
    }
    for (char delimiter : delimiters) {
      if (local_ch == delimiter) {
        return true;
      }
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.commons.lang3.text.WordUtils
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */