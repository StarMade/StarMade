/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*  22:    */class Lsp
/*  23:    */{
/*  24:    */  static final float M_PI = 3.141593F;
/*  25:    */  
/*  44:    */  static void lsp_to_curve(float[] curve, int[] map, int n, int ln, float[] lsp, int m, float amp, float ampoffset)
/*  45:    */  {
/*  46: 46 */    float wdel = 3.141593F / ln;
/*  47: 47 */    for (int i = 0; i < m; i++)
/*  48: 48 */      lsp[i] = Lookup.coslook(lsp[i]);
/*  49: 49 */    int m2 = m / 2 * 2;
/*  50:    */    
/*  51: 51 */    i = 0;
/*  52: 52 */    while (i < n) {
/*  53: 53 */      int k = map[i];
/*  54: 54 */      float p = 0.7071068F;
/*  55: 55 */      float q = 0.7071068F;
/*  56: 56 */      float w = Lookup.coslook(wdel * k);
/*  57:    */      
/*  58: 58 */      for (int j = 0; j < m2; j += 2) {
/*  59: 59 */        q *= (lsp[j] - w);
/*  60: 60 */        p *= (lsp[(j + 1)] - w);
/*  61:    */      }
/*  62:    */      
/*  63: 63 */      if ((m & 0x1) != 0)
/*  64:    */      {
/*  66: 66 */        q *= (lsp[(m - 1)] - w);
/*  67: 67 */        q *= q;
/*  68: 68 */        p *= p * (1.0F - w * w);
/*  69:    */      }
/*  70:    */      else
/*  71:    */      {
/*  72: 72 */        q *= q * (1.0F + w);
/*  73: 73 */        p *= p * (1.0F - w);
/*  74:    */      }
/*  75:    */      
/*  77: 77 */      q = p + q;
/*  78: 78 */      int hx = Float.floatToIntBits(q);
/*  79: 79 */      int ix = 0x7FFFFFFF & hx;
/*  80: 80 */      int qexp = 0;
/*  81:    */      
/*  82: 82 */      if ((ix < 2139095040) && (ix != 0))
/*  83:    */      {
/*  86: 86 */        if (ix < 8388608) {
/*  87: 87 */          q = (float)(q * 33554432.0D);
/*  88: 88 */          hx = Float.floatToIntBits(q);
/*  89: 89 */          ix = 0x7FFFFFFF & hx;
/*  90: 90 */          qexp = -25;
/*  91:    */        }
/*  92: 92 */        qexp += (ix >>> 23) - 126;
/*  93: 93 */        hx = hx & 0x807FFFFF | 0x3F000000;
/*  94: 94 */        q = Float.intBitsToFloat(hx);
/*  95:    */      }
/*  96:    */      
/*  97: 97 */      q = Lookup.fromdBlook(amp * Lookup.invsqlook(q) * Lookup.invsq2explook(qexp + m) - ampoffset);
/*  98:    */      
/*  99:    */      do
/* 100:    */      {
/* 101:101 */        curve[(i++)] *= q;
/* 102:    */      }
/* 103:103 */      while ((i < n) && (map[i] == k));
/* 104:    */    }
/* 105:    */  }
/* 106:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Lsp
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */