package org.apache.ws.commons.util;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.TimeZone;

public class XsDateTimeFormat
  extends Format
{
  private static final long serialVersionUID = 3258131340871479609L;
  final boolean parseDate;
  final boolean parseTime;
  
  XsDateTimeFormat(boolean pParseDate, boolean pParseTime)
  {
    this.parseDate = pParseDate;
    this.parseTime = pParseTime;
  }
  
  public XsDateTimeFormat()
  {
    this(true, true);
  }
  
  private int parseInt(String pString, int pOffset, StringBuffer pDigits)
  {
    int length = pString.length();
    pDigits.setLength(0);
    while (pOffset < length)
    {
      char local_c = pString.charAt(pOffset);
      if (!Character.isDigit(local_c)) {
        break;
      }
      pDigits.append(local_c);
      pOffset++;
    }
    return pOffset;
  }
  
  public Object parseObject(String pString, ParsePosition pParsePosition)
  {
    if (pString == null) {
      throw new NullPointerException("The String argument must not be null.");
    }
    if (pParsePosition == null) {
      throw new NullPointerException("The ParsePosition argument must not be null.");
    }
    int offset = pParsePosition.getIndex();
    int length = pString.length();
    boolean isMinus = false;
    StringBuffer digits = new StringBuffer();
    int mday;
    int month;
    int year;
    if (this.parseDate)
    {
      if (offset < length)
      {
        char local_c = pString.charAt(offset);
        if (local_c == '+')
        {
          offset++;
        }
        else if (local_c == '-')
        {
          offset++;
          isMinus = true;
        }
      }
      offset = parseInt(pString, offset, digits);
      if (digits.length() < 4)
      {
        pParsePosition.setErrorIndex(offset);
        return null;
      }
      int year = Integer.parseInt(digits.toString());
      if ((offset < length) && (pString.charAt(offset) == '-'))
      {
        offset++;
      }
      else
      {
        pParsePosition.setErrorIndex(offset);
        return null;
      }
      offset = parseInt(pString, offset, digits);
      if (digits.length() != 2)
      {
        pParsePosition.setErrorIndex(offset);
        return null;
      }
      int month = Integer.parseInt(digits.toString());
      if ((offset < length) && (pString.charAt(offset) == '-'))
      {
        offset++;
      }
      else
      {
        pParsePosition.setErrorIndex(offset);
        return null;
      }
      offset = parseInt(pString, offset, digits);
      if (digits.length() != 2)
      {
        pParsePosition.setErrorIndex(offset);
        return null;
      }
      int mday = Integer.parseInt(digits.toString());
      if (this.parseTime) {
        if ((offset < length) && (pString.charAt(offset) == 'T'))
        {
          offset++;
        }
        else
        {
          pParsePosition.setErrorIndex(offset);
          return null;
        }
      }
    }
    else
    {
      year = month = mday = 0;
    }
    int millis;
    int millis;
    int second;
    int minute;
    int local_c;
    if (this.parseTime)
    {
      offset = parseInt(pString, offset, digits);
      if (digits.length() != 2)
      {
        pParsePosition.setErrorIndex(offset);
        return null;
      }
      int local_c = Integer.parseInt(digits.toString());
      if ((offset < length) && (pString.charAt(offset) == ':'))
      {
        offset++;
      }
      else
      {
        pParsePosition.setErrorIndex(offset);
        return null;
      }
      offset = parseInt(pString, offset, digits);
      if (digits.length() != 2)
      {
        pParsePosition.setErrorIndex(offset);
        return null;
      }
      int minute = Integer.parseInt(digits.toString());
      if ((offset < length) && (pString.charAt(offset) == ':'))
      {
        offset++;
      }
      else
      {
        pParsePosition.setErrorIndex(offset);
        return null;
      }
      offset = parseInt(pString, offset, digits);
      if (digits.length() != 2)
      {
        pParsePosition.setErrorIndex(offset);
        return null;
      }
      int second = Integer.parseInt(digits.toString());
      int millis;
      if ((offset < length) && (pString.charAt(offset) == '.'))
      {
        offset++;
        offset = parseInt(pString, offset, digits);
        if (digits.length() > 0)
        {
          int millis = Integer.parseInt(digits.toString());
          if (millis > 999)
          {
            pParsePosition.setErrorIndex(offset);
            return null;
          }
          for (int local_i = digits.length(); local_i < 3; local_i++) {
            millis *= 10;
          }
        }
        else
        {
          millis = 0;
        }
      }
      else
      {
        millis = 0;
      }
    }
    else
    {
      local_c = minute = second = millis = 0;
    }
    digits.setLength(0);
    digits.append("GMT");
    if (offset < length)
    {
      char local_i = pString.charAt(offset);
      if (local_i == 'Z')
      {
        offset++;
      }
      else if ((local_i == '+') || (local_i == '-'))
      {
        digits.append(local_i);
        offset++;
        for (int local_i1 = 0; local_i1 < 5; local_i1++)
        {
          if (offset >= length)
          {
            pParsePosition.setErrorIndex(offset);
            return null;
          }
          local_i = pString.charAt(offset);
          if (((local_i1 != 2) && (Character.isDigit(local_i))) || ((local_i1 == 2) && (local_i == ':')))
          {
            digits.append(local_i);
          }
          else
          {
            pParsePosition.setErrorIndex(offset);
            return null;
          }
          offset++;
        }
      }
    }
    Calendar local_i = Calendar.getInstance(TimeZone.getTimeZone(digits.toString()));
    local_i.set(isMinus ? -year : year, this.parseDate ? month - 1 : month, mday, local_c, minute, second);
    local_i.set(14, millis);
    pParsePosition.setIndex(offset);
    return local_i;
  }
  
  private void append(StringBuffer pBuffer, int pNum, int pMinLen)
  {
    String local_s = Integer.toString(pNum);
    for (int local_i = local_s.length(); local_i < pMinLen; local_i++) {
      pBuffer.append('0');
    }
    pBuffer.append(local_s);
  }
  
  public StringBuffer format(Object pCalendar, StringBuffer pBuffer, FieldPosition pPos)
  {
    if (pCalendar == null) {
      throw new NullPointerException("The Calendar argument must not be null.");
    }
    if (pBuffer == null) {
      throw new NullPointerException("The StringBuffer argument must not be null.");
    }
    if (pPos == null) {
      throw new NullPointerException("The FieldPosition argument must not be null.");
    }
    Calendar cal = (Calendar)pCalendar;
    if (this.parseDate)
    {
      int year = cal.get(1);
      if (year < 0)
      {
        pBuffer.append('-');
        year = -year;
      }
      append(pBuffer, year, 4);
      pBuffer.append('-');
      append(pBuffer, cal.get(2) + 1, 2);
      pBuffer.append('-');
      append(pBuffer, cal.get(5), 2);
      if (this.parseTime) {
        pBuffer.append('T');
      }
    }
    if (this.parseTime)
    {
      append(pBuffer, cal.get(11), 2);
      pBuffer.append(':');
      append(pBuffer, cal.get(12), 2);
      pBuffer.append(':');
      append(pBuffer, cal.get(13), 2);
      int year = cal.get(14);
      if (year > 0)
      {
        pBuffer.append('.');
        append(pBuffer, year, 3);
      }
    }
    TimeZone year = cal.getTimeZone();
    int offset = cal.get(15);
    if (year.inDaylightTime(cal.getTime())) {
      offset += cal.get(16);
    }
    if (offset == 0)
    {
      pBuffer.append('Z');
    }
    else
    {
      if (offset < 0)
      {
        pBuffer.append('-');
        offset = -offset;
      }
      else
      {
        pBuffer.append('+');
      }
      int minutes = offset / 60000;
      int hours = minutes / 60;
      minutes -= hours * 60;
      append(pBuffer, hours, 2);
      pBuffer.append(':');
      append(pBuffer, minutes, 2);
    }
    return pBuffer;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.apache.ws.commons.util.XsDateTimeFormat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */