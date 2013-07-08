/*   1:    */package de.jarnbjo.vorbis;
/*   2:    */
/*  28:    */public final class Util
/*  29:    */{
/*  30:    */  public static final int ilog(int x)
/*  31:    */  {
/*  32: 32 */    for (int res = 0; 
/*  33: 33 */        x > 0; res++) x >>= 1;
/*  34: 34 */    return res;
/*  35:    */  }
/*  36:    */  
/*  37:    */  public static final float float32unpack(int x) {
/*  38: 38 */    float mantissa = x & 0x1FFFFF;
/*  39: 39 */    float e = (x & 0x7FE00000) >> 21;
/*  40: 40 */    if ((x & 0x80000000) != 0) {
/*  41: 41 */      mantissa = -mantissa;
/*  42:    */    }
/*  43: 43 */    return mantissa * (float)Math.pow(2.0D, e - 788.0D);
/*  44:    */  }
/*  45:    */  
/*  46:    */  public static final int lookup1Values(int a, int b) {
/*  47: 47 */    int res = (int)Math.pow(2.718281828459045D, Math.log(a) / b);
/*  48: 48 */    return intPow(res + 1, b) <= a ? res + 1 : res;
/*  49:    */  }
/*  50:    */  
/*  51:    */  public static final int intPow(int base, int e) {
/*  52: 52 */    int res = 1;
/*  53: 53 */    for (; e > 0; res *= base) e--;
/*  54: 54 */    return res;
/*  55:    */  }
/*  56:    */  
/*  57:    */  public static final boolean isBitSet(int value, int bit) {
/*  58: 58 */    return (value & 1 << bit) != 0;
/*  59:    */  }
/*  60:    */  
/*  61:    */  public static final int icount(int value) {
/*  62: 62 */    int res = 0;
/*  63: 63 */    while (value > 0) {
/*  64: 64 */      res += (value & 0x1);
/*  65: 65 */      value >>= 1;
/*  66:    */    }
/*  67: 67 */    return res;
/*  68:    */  }
/*  69:    */  
/*  70:    */  public static final int lowNeighbour(int[] v, int x) {
/*  71: 71 */    int max = -1;int n = 0;
/*  72: 72 */    for (int i = 0; (i < v.length) && (i < x); i++) {
/*  73: 73 */      if ((v[i] > max) && (v[i] < v[x])) {
/*  74: 74 */        max = v[i];
/*  75: 75 */        n = i;
/*  76:    */      }
/*  77:    */    }
/*  78: 78 */    return n;
/*  79:    */  }
/*  80:    */  
/*  81:    */  public static final int highNeighbour(int[] v, int x) {
/*  82: 82 */    int min = 2147483647;int n = 0;
/*  83: 83 */    for (int i = 0; (i < v.length) && (i < x); i++) {
/*  84: 84 */      if ((v[i] < min) && (v[i] > v[x])) {
/*  85: 85 */        min = v[i];
/*  86: 86 */        n = i;
/*  87:    */      }
/*  88:    */    }
/*  89: 89 */    return n;
/*  90:    */  }
/*  91:    */  
/*  92:    */  public static final int renderPoint(int x0, int x1, int y0, int y1, int x) {
/*  93: 93 */    int dy = y1 - y0;
/*  94: 94 */    int ady = dy < 0 ? -dy : dy;
/*  95: 95 */    int off = ady * (x - x0) / (x1 - x0);
/*  96: 96 */    return dy < 0 ? y0 - off : y0 + off;
/*  97:    */  }
/*  98:    */  
/*  99:    */  public static final void renderLine(int x0, int y0, int x1, int y1, float[] v) {
/* 100:100 */    int dy = y1 - y0;
/* 101:101 */    int adx = x1 - x0;
/* 102:102 */    int base = dy / adx;
/* 103:103 */    int sy = dy < 0 ? base - 1 : base + 1;
/* 104:104 */    int x = x0;
/* 105:105 */    int y = y0;
/* 106:106 */    int err = 0;
/* 107:107 */    int ady = (dy < 0 ? -dy : dy) - (base > 0 ? base * adx : -base * adx);
/* 108:    */    
/* 109:109 */    v[x] *= Floor.DB_STATIC_TABLE[y];
/* 110:110 */    for (x = x0 + 1; x < x1; x++) {
/* 111:111 */      err += ady;
/* 112:112 */      if (err >= adx) {
/* 113:113 */        err -= adx; int 
/* 114:114 */          tmp139_138 = (y + sy);y = tmp139_138;v[x] *= Floor.DB_STATIC_TABLE[tmp139_138];
/* 115:    */      }
/* 116:    */      else {
/* 117:117 */        int tmp162_161 = (y + base);y = tmp162_161;v[x] *= Floor.DB_STATIC_TABLE[tmp162_161];
/* 118:    */      }
/* 119:    */    }
/* 120:    */  }
/* 121:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Util
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */