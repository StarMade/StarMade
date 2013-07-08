package it.unimi.dsi.fastutil;

public class HashCommon
{
  public static final Object REMOVED = new Object();
  
  public static final int murmurHash3(int local_x)
  {
    local_x ^= local_x >>> 16;
    local_x *= -2048144789;
    local_x ^= local_x >>> 13;
    local_x *= -1028477387;
    local_x ^= local_x >>> 16;
    return local_x;
  }
  
  public static final long murmurHash3(long local_x)
  {
    local_x ^= local_x >>> 33;
    local_x *= -49064778989728563L;
    local_x ^= local_x >>> 33;
    local_x *= -4265267296055464877L;
    local_x ^= local_x >>> 33;
    return local_x;
  }
  
  public static final int float2int(float local_f)
  {
    return Float.floatToRawIntBits(local_f);
  }
  
  public static final int double2int(double local_d)
  {
    long local_l = Double.doubleToRawLongBits(local_d);
    return (int)(local_l ^ local_l >>> 32);
  }
  
  public static final int long2int(long local_l)
  {
    return (int)(local_l ^ local_l >>> 32);
  }
  
  public static int nextPowerOfTwo(int local_x)
  {
    if (local_x == 0) {
      return 1;
    }
    local_x--;
    local_x |= local_x >> 1;
    local_x |= local_x >> 2;
    local_x |= local_x >> 4;
    local_x |= local_x >> 8;
    return (local_x | local_x >> 16) + 1;
  }
  
  public static long nextPowerOfTwo(long local_x)
  {
    if (local_x == 0L) {
      return 1L;
    }
    local_x -= 1L;
    local_x |= local_x >> 1;
    local_x |= local_x >> 2;
    local_x |= local_x >> 4;
    local_x |= local_x >> 8;
    local_x |= local_x >> 16;
    return (local_x | local_x >> 32) + 1L;
  }
  
  public static int maxFill(int local_n, float local_f)
  {
    return (int)Math.ceil(local_n * local_f);
  }
  
  public static long maxFill(long local_n, float local_f)
  {
    return Math.ceil((float)local_n * local_f);
  }
  
  public static int arraySize(int expected, float local_f)
  {
    long local_s = nextPowerOfTwo(Math.ceil(expected / local_f));
    if (local_s > 1073741824L) {
      throw new IllegalArgumentException("Too large (" + expected + " expected elements with load factor " + local_f + ")");
    }
    return (int)local_s;
  }
  
  public static long bigArraySize(long expected, float local_f)
  {
    return nextPowerOfTwo(Math.ceil((float)expected / local_f));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     it.unimi.dsi.fastutil.HashCommon
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */