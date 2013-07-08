package com.eaio.util.lang;

import java.io.IOException;

public final class Hex
{
  private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
  
  public static Appendable append(Appendable local_a, short local_in)
  {
    return append(local_a, local_in, 4);
  }
  
  public static Appendable append(Appendable local_a, short local_in, int length)
  {
    return append(local_a, local_in, length);
  }
  
  public static Appendable append(Appendable local_a, int local_in)
  {
    return append(local_a, local_in, 8);
  }
  
  public static Appendable append(Appendable local_a, int local_in, int length)
  {
    return append(local_a, local_in, length);
  }
  
  public static Appendable append(Appendable local_a, long local_in)
  {
    return append(local_a, local_in, 16);
  }
  
  public static Appendable append(Appendable local_a, long local_in, int length)
  {
    try
    {
      for (int lim = (length << 2) - 4; lim >= 0; lim -= 4) {
        local_a.append(DIGITS[((byte)(int)(local_in >> lim) & 0xF)]);
      }
    }
    catch (IOException lim) {}
    return local_a;
  }
  
  public static Appendable append(Appendable local_a, byte[] bytes)
  {
    try
    {
      for (byte local_b : bytes)
      {
        local_a.append(DIGITS[((byte)((local_b & 0xF0) >> 4))]);
        local_a.append(DIGITS[((byte)(local_b & 0xF))]);
      }
    }
    catch (IOException arr$) {}
    return local_a;
  }
  
  public static long parseLong(CharSequence local_s)
  {
    long out = 0L;
    byte shifts = 0;
    for (int local_i = 0; (local_i < local_s.length()) && (shifts < 16); local_i++)
    {
      char local_c = local_s.charAt(local_i);
      if ((local_c > '/') && (local_c < ':'))
      {
        shifts = (byte)(shifts + 1);
        out <<= 4;
        out |= local_c - '0';
      }
      else if ((local_c > '@') && (local_c < 'G'))
      {
        shifts = (byte)(shifts + 1);
        out <<= 4;
        out |= local_c - '7';
      }
      else if ((local_c > '`') && (local_c < 'g'))
      {
        shifts = (byte)(shifts + 1);
        out <<= 4;
        out |= local_c - 'W';
      }
    }
    return out;
  }
  
  public static short parseShort(String local_s)
  {
    short out = 0;
    byte shifts = 0;
    for (int local_i = 0; (local_i < local_s.length()) && (shifts < 4); local_i++)
    {
      char local_c = local_s.charAt(local_i);
      if ((local_c > '/') && (local_c < ':'))
      {
        shifts = (byte)(shifts + 1);
        out = (short)(out << 4);
        out = (short)(out | local_c - '0');
      }
      else if ((local_c > '@') && (local_c < 'G'))
      {
        shifts = (byte)(shifts + 1);
        out = (short)(out << 4);
        out = (short)(out | local_c - '7');
      }
      else if ((local_c > '`') && (local_c < 'g'))
      {
        shifts = (byte)(shifts + 1);
        out = (short)(out << 4);
        out = (short)(out | local_c - 'W');
      }
    }
    return out;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.eaio.util.lang.Hex
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */