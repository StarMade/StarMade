package de.jarnbjo.vorbis;

public final class Util
{
  public static final int ilog(int local_x)
  {
    for (int res = 0; local_x > 0; res++) {
      local_x >>= 1;
    }
    return res;
  }
  
  public static final float float32unpack(int local_x)
  {
    float mantissa = local_x & 0x1FFFFF;
    float local_e = (local_x & 0x7FE00000) >> 21;
    if ((local_x & 0x80000000) != 0) {
      mantissa = -mantissa;
    }
    return mantissa * (float)Math.pow(2.0D, local_e - 788.0D);
  }
  
  public static final int lookup1Values(int local_a, int local_b)
  {
    int res = (int)Math.pow(2.718281828459045D, Math.log(local_a) / local_b);
    return intPow(res + 1, local_b) <= local_a ? res + 1 : res;
  }
  
  public static final int intPow(int base, int local_e)
  {
    int res = 1;
    while (local_e > 0)
    {
      local_e--;
      res *= base;
    }
    return res;
  }
  
  public static final boolean isBitSet(int value, int bit)
  {
    return (value & 1 << bit) != 0;
  }
  
  public static final int icount(int value)
  {
    int res = 0;
    while (value > 0)
    {
      res += (value & 0x1);
      value >>= 1;
    }
    return res;
  }
  
  public static final int lowNeighbour(int[] local_v, int local_x)
  {
    int max = -1;
    int local_n = 0;
    for (int local_i = 0; (local_i < local_v.length) && (local_i < local_x); local_i++) {
      if ((local_v[local_i] > max) && (local_v[local_i] < local_v[local_x]))
      {
        max = local_v[local_i];
        local_n = local_i;
      }
    }
    return local_n;
  }
  
  public static final int highNeighbour(int[] local_v, int local_x)
  {
    int min = 2147483647;
    int local_n = 0;
    for (int local_i = 0; (local_i < local_v.length) && (local_i < local_x); local_i++) {
      if ((local_v[local_i] < min) && (local_v[local_i] > local_v[local_x]))
      {
        min = local_v[local_i];
        local_n = local_i;
      }
    }
    return local_n;
  }
  
  public static final int renderPoint(int local_x0, int local_x1, int local_y0, int local_y1, int local_x)
  {
    int local_dy = local_y1 - local_y0;
    int ady = local_dy < 0 ? -local_dy : local_dy;
    int off = ady * (local_x - local_x0) / (local_x1 - local_x0);
    return local_dy < 0 ? local_y0 - off : local_y0 + off;
  }
  
  public static final void renderLine(int local_x0, int local_y0, int local_x1, int local_y1, float[] local_v)
  {
    int local_dy = local_y1 - local_y0;
    int adx = local_x1 - local_x0;
    int base = local_dy / adx;
    int local_sy = local_dy < 0 ? base - 1 : base + 1;
    int local_x = local_x0;
    int local_y = local_y0;
    int err = 0;
    int ady = (local_dy < 0 ? -local_dy : local_dy) - (base > 0 ? base * adx : -base * adx);
    local_v[local_x] *= Floor.DB_STATIC_TABLE[local_y];
    for (local_x = local_x0 + 1; local_x < local_x1; local_x++)
    {
      err += ady;
      if (err >= adx)
      {
        err -= adx;
        int tmp139_138 = (local_y + local_sy);
        local_y = tmp139_138;
        local_v[local_x] *= Floor.DB_STATIC_TABLE[tmp139_138];
      }
      else
      {
        int tmp162_161 = (local_y + base);
        local_y = tmp162_161;
        local_v[local_x] *= Floor.DB_STATIC_TABLE[tmp162_161];
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */