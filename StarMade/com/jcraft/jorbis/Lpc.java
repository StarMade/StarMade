/*   1:    */package com.jcraft.jorbis;
/*   2:    */
/*  29:    */class Lpc
/*  30:    */{
/*  31: 31 */  Drft fft = new Drft();
/*  32:    */  
/*  34:    */  int ln;
/*  35:    */  
/*  37:    */  int m;
/*  38:    */  
/*  41:    */  static float lpc_from_data(float[] data, float[] lpc, int n, int m)
/*  42:    */  {
/*  43: 43 */    float[] aut = new float[m + 1];
/*  44:    */    
/*  49: 49 */    int j = m + 1;
/*  50: 50 */    while (j-- != 0) {
/*  51: 51 */      float d = 0.0F;
/*  52: 52 */      for (int i = j; i < n; i++)
/*  53: 53 */        d += data[i] * data[(i - j)];
/*  54: 54 */      aut[j] = d;
/*  55:    */    }
/*  56:    */    
/*  59: 59 */    float error = aut[0];
/*  60:    */    
/*  67: 67 */    for (int i = 0; i < m; i++) {
/*  68: 68 */      float r = -aut[(i + 1)];
/*  69:    */      
/*  70: 70 */      if (error == 0.0F) {
/*  71: 71 */        for (int k = 0; k < m; k++)
/*  72: 72 */          lpc[k] = 0.0F;
/*  73: 73 */        return 0.0F;
/*  74:    */      }
/*  75:    */      
/*  81: 81 */      for (j = 0; j < i; j++)
/*  82: 82 */        r -= lpc[j] * aut[(i - j)];
/*  83: 83 */      r /= error;
/*  84:    */      
/*  87: 87 */      lpc[i] = r;
/*  88: 88 */      for (j = 0; j < i / 2; j++) {
/*  89: 89 */        float tmp = lpc[j];
/*  90: 90 */        lpc[j] += r * lpc[(i - 1 - j)];
/*  91: 91 */        lpc[(i - 1 - j)] += r * tmp;
/*  92:    */      }
/*  93: 93 */      if (i % 2 != 0) {
/*  94: 94 */        lpc[j] += lpc[j] * r;
/*  95:    */      }
/*  96: 96 */      error = (float)(error * (1.0D - r * r));
/*  97:    */    }
/*  98:    */    
/* 102:102 */    return error;
/* 103:    */  }
/* 104:    */  
/* 107:    */  float lpc_from_curve(float[] curve, float[] lpc)
/* 108:    */  {
/* 109:109 */    int n = this.ln;
/* 110:110 */    float[] work = new float[n + n];
/* 111:111 */    float fscale = (float)(0.5D / n);
/* 112:    */    
/* 116:116 */    for (int i = 0; i < n; i++) {
/* 117:117 */      work[(i * 2)] = (curve[i] * fscale);
/* 118:118 */      work[(i * 2 + 1)] = 0.0F;
/* 119:    */    }
/* 120:120 */    work[(n * 2 - 1)] = (curve[(n - 1)] * fscale);
/* 121:    */    
/* 122:122 */    n *= 2;
/* 123:123 */    this.fft.backward(work);
/* 124:    */    
/* 128:128 */    i = 0; for (int j = n / 2; i < n / 2;) {
/* 129:129 */      float temp = work[i];
/* 130:130 */      work[(i++)] = work[j];
/* 131:131 */      work[(j++)] = temp;
/* 132:    */    }
/* 133:    */    
/* 134:134 */    return lpc_from_data(work, lpc, n, this.m);
/* 135:    */  }
/* 136:    */  
/* 137:    */  void init(int mapped, int m) {
/* 138:138 */    this.ln = mapped;
/* 139:139 */    this.m = m;
/* 140:    */    
/* 142:142 */    this.fft.init(mapped * 2);
/* 143:    */  }
/* 144:    */  
/* 145:    */  void clear() {
/* 146:146 */    this.fft.clear();
/* 147:    */  }
/* 148:    */  
/* 149:    */  static float FAST_HYPOT(float a, float b) {
/* 150:150 */    return (float)Math.sqrt(a * a + b * b);
/* 151:    */  }
/* 152:    */  
/* 160:    */  void lpc_to_curve(float[] curve, float[] lpc, float amp)
/* 161:    */  {
/* 162:162 */    for (int i = 0; i < this.ln * 2; i++) {
/* 163:163 */      curve[i] = 0.0F;
/* 164:    */    }
/* 165:165 */    if (amp == 0.0F) {
/* 166:166 */      return;
/* 167:    */    }
/* 168:168 */    for (int i = 0; i < this.m; i++) {
/* 169:169 */      curve[(i * 2 + 1)] = (lpc[i] / (4.0F * amp));
/* 170:170 */      curve[(i * 2 + 2)] = (-lpc[i] / (4.0F * amp));
/* 171:    */    }
/* 172:    */    
/* 173:173 */    this.fft.backward(curve);
/* 174:    */    
/* 176:176 */    int l2 = this.ln * 2;
/* 177:177 */    float unit = (float)(1.0D / amp);
/* 178:178 */    curve[0] = ((float)(1.0D / (curve[0] * 2.0F + unit)));
/* 179:179 */    for (int i = 1; i < this.ln; i++) {
/* 180:180 */      float real = curve[i] + curve[(l2 - i)];
/* 181:181 */      float imag = curve[i] - curve[(l2 - i)];
/* 182:    */      
/* 183:183 */      float a = real + unit;
/* 184:184 */      curve[i] = ((float)(1.0D / FAST_HYPOT(a, imag)));
/* 185:    */    }
/* 186:    */  }
/* 187:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.jcraft.jorbis.Lpc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */