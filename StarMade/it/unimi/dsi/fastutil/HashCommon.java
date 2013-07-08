/*   1:    */package it.unimi.dsi.fastutil;
/*   2:    */
/*  27:    */public class HashCommon
/*  28:    */{
/*  29: 29 */  public static final Object REMOVED = new Object();
/*  30:    */  
/*  39:    */  public static final int murmurHash3(int x)
/*  40:    */  {
/*  41: 41 */    x ^= x >>> 16;
/*  42: 42 */    x *= -2048144789;
/*  43: 43 */    x ^= x >>> 13;
/*  44: 44 */    x *= -1028477387;
/*  45: 45 */    x ^= x >>> 16;
/*  46: 46 */    return x;
/*  47:    */  }
/*  48:    */  
/*  57:    */  public static final long murmurHash3(long x)
/*  58:    */  {
/*  59: 59 */    x ^= x >>> 33;
/*  60: 60 */    x *= -49064778989728563L;
/*  61: 61 */    x ^= x >>> 33;
/*  62: 62 */    x *= -4265267296055464877L;
/*  63: 63 */    x ^= x >>> 33;
/*  64:    */    
/*  65: 65 */    return x;
/*  66:    */  }
/*  67:    */  
/*  72:    */  public static final int float2int(float f)
/*  73:    */  {
/*  74: 74 */    return Float.floatToRawIntBits(f);
/*  75:    */  }
/*  76:    */  
/*  81:    */  public static final int double2int(double d)
/*  82:    */  {
/*  83: 83 */    long l = Double.doubleToRawLongBits(d);
/*  84: 84 */    return (int)(l ^ l >>> 32);
/*  85:    */  }
/*  86:    */  
/*  90:    */  public static final int long2int(long l)
/*  91:    */  {
/*  92: 92 */    return (int)(l ^ l >>> 32);
/*  93:    */  }
/*  94:    */  
/* 101:    */  public static int nextPowerOfTwo(int x)
/* 102:    */  {
/* 103:103 */    if (x == 0) return 1;
/* 104:104 */    x--;
/* 105:105 */    x |= x >> 1;
/* 106:106 */    x |= x >> 2;
/* 107:107 */    x |= x >> 4;
/* 108:108 */    x |= x >> 8;
/* 109:109 */    return (x | x >> 16) + 1;
/* 110:    */  }
/* 111:    */  
/* 118:    */  public static long nextPowerOfTwo(long x)
/* 119:    */  {
/* 120:120 */    if (x == 0L) return 1L;
/* 121:121 */    x -= 1L;
/* 122:122 */    x |= x >> 1;
/* 123:123 */    x |= x >> 2;
/* 124:124 */    x |= x >> 4;
/* 125:125 */    x |= x >> 8;
/* 126:126 */    x |= x >> 16;
/* 127:127 */    return (x | x >> 32) + 1L;
/* 128:    */  }
/* 129:    */  
/* 136:    */  public static int maxFill(int n, float f)
/* 137:    */  {
/* 138:138 */    return (int)Math.ceil(n * f);
/* 139:    */  }
/* 140:    */  
/* 146:    */  public static long maxFill(long n, float f)
/* 147:    */  {
/* 148:148 */    return Math.ceil((float)n * f);
/* 149:    */  }
/* 150:    */  
/* 157:    */  public static int arraySize(int expected, float f)
/* 158:    */  {
/* 159:159 */    long s = nextPowerOfTwo(Math.ceil(expected / f));
/* 160:160 */    if (s > 1073741824L) throw new IllegalArgumentException("Too large (" + expected + " expected elements with load factor " + f + ")");
/* 161:161 */    return (int)s;
/* 162:    */  }
/* 163:    */  
/* 169:    */  public static long bigArraySize(long expected, float f)
/* 170:    */  {
/* 171:171 */    return nextPowerOfTwo(Math.ceil((float)expected / f));
/* 172:    */  }
/* 173:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     it.unimi.dsi.fastutil.HashCommon
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */