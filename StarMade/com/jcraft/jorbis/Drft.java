package com.jcraft.jorbis;

class Drft
{
  int field_512;
  float[] trigcache;
  int[] splitcache;
  static int[] ntryh = { 4, 2, 3, 5 };
  static float tpi = 6.283186F;
  static float hsqt2 = 0.7071068F;
  static float taui = 0.8660254F;
  static float taur = -0.5F;
  static float sqrt2 = 1.414214F;
  
  void backward(float[] paramArrayOfFloat)
  {
    if (this.field_512 == 1) {
      return;
    }
    drftb1(this.field_512, paramArrayOfFloat, this.trigcache, this.trigcache, this.field_512, this.splitcache);
  }
  
  void init(int paramInt)
  {
    this.field_512 = paramInt;
    this.trigcache = new float[3 * paramInt];
    this.splitcache = new int[32];
    fdrffti(paramInt, this.trigcache, this.splitcache);
  }
  
  void clear()
  {
    if (this.trigcache != null) {
      this.trigcache = null;
    }
    if (this.splitcache != null) {
      this.splitcache = null;
    }
  }
  
  static void drfti1(int paramInt1, float[] paramArrayOfFloat, int paramInt2, int[] paramArrayOfInt)
  {
    int i = 0;
    int k = -1;
    int i12 = paramInt1;
    int i13 = 0;
    int i14 = 101;
    for (;;)
    {
      int j;
      switch (i14)
      {
      case 101: 
        k++;
        if (k < 4) {
          i = ntryh[k];
        } else {
          i += 2;
        }
      case 104: 
        int i7 = i12 / i;
        int i8 = i12 - i * i7;
        if (i8 != 0)
        {
          i14 = 101;
        }
        else
        {
          i13++;
          paramArrayOfInt[(i13 + 1)] = i;
          i12 = i7;
          if (i != 2)
          {
            i14 = 107;
          }
          else if (i13 == 1)
          {
            i14 = 107;
          }
          else
          {
            for (j = 1; j < i13; j++)
            {
              int i2 = i13 - j + 1;
              paramArrayOfInt[(i2 + 1)] = paramArrayOfInt[i2];
            }
            paramArrayOfInt[2] = 2;
          }
        }
        break;
      case 107: 
        if (i12 != 1)
        {
          i14 = 104;
        }
        else
        {
          paramArrayOfInt[0] = paramInt1;
          paramArrayOfInt[1] = i13;
          float f2 = tpi / paramInt1;
          int i6 = 0;
          int i11 = i13 - 1;
          int n = 1;
          if (i11 == 0) {
            return;
          }
          for (int m = 0; m < i11; m++)
          {
            int i5 = paramArrayOfInt[(m + 2)];
            int i3 = 0;
            int i1 = n * i5;
            int i9 = paramInt1 / i1;
            int i10 = i5 - 1;
            for (k = 0; k < i10; k++)
            {
              i3 += n;
              j = i6;
              float f3 = i3 * f2;
              float f4 = 0.0F;
              for (int i4 = 2; i4 < i9; i4 += 2)
              {
                f4 += 1.0F;
                float f1 = f4 * f3;
                paramArrayOfFloat[(paramInt2 + j++)] = ((float)Math.cos(f1));
                paramArrayOfFloat[(paramInt2 + j++)] = ((float)Math.sin(f1));
              }
              i6 += i9;
            }
            n = i1;
          }
          return;
        }
        break;
      }
    }
  }
  
  static void fdrffti(int paramInt, float[] paramArrayOfFloat, int[] paramArrayOfInt)
  {
    if (paramInt == 1) {
      return;
    }
    drfti1(paramInt, paramArrayOfFloat, paramInt, paramArrayOfInt);
  }
  
  static void dradf2(int paramInt1, int paramInt2, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, int paramInt3)
  {
    int m = 0;
    int n;
    int k = n = paramInt2 * paramInt1;
    int i1 = paramInt1 << 1;
    for (int j = 0; j < paramInt2; j++)
    {
      paramArrayOfFloat2[(m << 1)] = (paramArrayOfFloat1[m] + paramArrayOfFloat1[n]);
      paramArrayOfFloat2[((m << 1) + i1 - 1)] = (paramArrayOfFloat1[m] - paramArrayOfFloat1[n]);
      m += paramInt1;
      n += paramInt1;
    }
    if (paramInt1 < 2) {
      return;
    }
    if (paramInt1 != 2)
    {
      m = 0;
      n = k;
      for (j = 0; j < paramInt2; j++)
      {
        i1 = n;
        int i2 = (m << 1) + (paramInt1 << 1);
        int i3 = m;
        int i4 = m + m;
        for (int i = 2; i < paramInt1; i += 2)
        {
          i1 += 2;
          i2 -= 2;
          i3 += 2;
          i4 += 2;
          float f2 = paramArrayOfFloat3[(paramInt3 + i - 2)] * paramArrayOfFloat1[(i1 - 1)] + paramArrayOfFloat3[(paramInt3 + i - 1)] * paramArrayOfFloat1[i1];
          float f1 = paramArrayOfFloat3[(paramInt3 + i - 2)] * paramArrayOfFloat1[i1] - paramArrayOfFloat3[(paramInt3 + i - 1)] * paramArrayOfFloat1[(i1 - 1)];
          paramArrayOfFloat1[i3] += f1;
          paramArrayOfFloat2[i2] = (f1 - paramArrayOfFloat1[i3]);
          paramArrayOfFloat1[(i3 - 1)] += f2;
          paramArrayOfFloat1[(i3 - 1)] -= f2;
        }
        m += paramInt1;
        n += paramInt1;
      }
      if (paramInt1 % 2 == 1) {
        return;
      }
    }
    i1 = n = (m = paramInt1) - 1;
    n += k;
    for (j = 0; j < paramInt2; j++)
    {
      paramArrayOfFloat2[m] = (-paramArrayOfFloat1[n]);
      paramArrayOfFloat2[(m - 1)] = paramArrayOfFloat1[i1];
      m += (paramInt1 << 1);
      n += paramInt1;
      i1 += paramInt1;
    }
  }
  
  static void dradf4(int paramInt1, int paramInt2, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, int paramInt3, float[] paramArrayOfFloat4, int paramInt4, float[] paramArrayOfFloat5, int paramInt5)
  {
    int k = paramInt2 * paramInt1;
    int m = k;
    int i2 = m << 1;
    int n = m + (m << 1);
    int i1 = 0;
    float f11;
    float f12;
    for (int j = 0; j < paramInt2; j++)
    {
      f11 = paramArrayOfFloat1[m] + paramArrayOfFloat1[n];
      f12 = paramArrayOfFloat1[i1] + paramArrayOfFloat1[i2];
      int tmp60_59 = (i1 << 2);
      i3 = tmp60_59;
      paramArrayOfFloat2[tmp60_59] = (f11 + f12);
      paramArrayOfFloat2[((paramInt1 << 2) + i3 - 1)] = (f12 - f11);
      int tmp91_90 = (i3 + (paramInt1 << 1));
      i3 = tmp91_90;
      paramArrayOfFloat2[(tmp91_90 - 1)] = (paramArrayOfFloat1[i1] - paramArrayOfFloat1[i2]);
      paramArrayOfFloat1[n] -= paramArrayOfFloat1[m];
      m += paramInt1;
      n += paramInt1;
      i1 += paramInt1;
      i2 += paramInt1;
    }
    if (paramInt1 < 2) {
      return;
    }
    float f7;
    if (paramInt1 != 2)
    {
      m = 0;
      for (j = 0; j < paramInt2; j++)
      {
        n = m;
        i2 = m << 2;
        i3 = (i4 = paramInt1 << 1) + i2;
        for (int i = 2; i < paramInt1; i += 2)
        {
          n += 2;
          i1 = n;
          i2 += 2;
          i3 -= 2;
          i1 += k;
          float f4 = paramArrayOfFloat3[(paramInt3 + i - 2)] * paramArrayOfFloat1[(i1 - 1)] + paramArrayOfFloat3[(paramInt3 + i - 1)] * paramArrayOfFloat1[i1];
          float f1 = paramArrayOfFloat3[(paramInt3 + i - 2)] * paramArrayOfFloat1[i1] - paramArrayOfFloat3[(paramInt3 + i - 1)] * paramArrayOfFloat1[(i1 - 1)];
          i1 += k;
          float f5 = paramArrayOfFloat4[(paramInt4 + i - 2)] * paramArrayOfFloat1[(i1 - 1)] + paramArrayOfFloat4[(paramInt4 + i - 1)] * paramArrayOfFloat1[i1];
          float f2 = paramArrayOfFloat4[(paramInt4 + i - 2)] * paramArrayOfFloat1[i1] - paramArrayOfFloat4[(paramInt4 + i - 1)] * paramArrayOfFloat1[(i1 - 1)];
          i1 += k;
          float f6 = paramArrayOfFloat5[(paramInt5 + i - 2)] * paramArrayOfFloat1[(i1 - 1)] + paramArrayOfFloat5[(paramInt5 + i - 1)] * paramArrayOfFloat1[i1];
          float f3 = paramArrayOfFloat5[(paramInt5 + i - 2)] * paramArrayOfFloat1[i1] - paramArrayOfFloat5[(paramInt5 + i - 1)] * paramArrayOfFloat1[(i1 - 1)];
          f11 = f4 + f6;
          float f14 = f6 - f4;
          f7 = f1 + f3;
          float f10 = f1 - f3;
          float f8 = paramArrayOfFloat1[n] + f2;
          float f9 = paramArrayOfFloat1[n] - f2;
          f12 = paramArrayOfFloat1[(n - 1)] + f5;
          float f13 = paramArrayOfFloat1[(n - 1)] - f5;
          paramArrayOfFloat2[(i2 - 1)] = (f11 + f12);
          paramArrayOfFloat2[i2] = (f7 + f8);
          paramArrayOfFloat2[(i3 - 1)] = (f13 - f10);
          paramArrayOfFloat2[i3] = (f14 - f9);
          paramArrayOfFloat2[(i2 + i4 - 1)] = (f10 + f13);
          paramArrayOfFloat2[(i2 + i4)] = (f14 + f9);
          paramArrayOfFloat2[(i3 + i4 - 1)] = (f12 - f11);
          paramArrayOfFloat2[(i3 + i4)] = (f7 - f8);
        }
        m += paramInt1;
      }
      if ((paramInt1 & 0x1) != 0) {
        return;
      }
    }
    n = (m = k + paramInt1 - 1) + (k << 1);
    i1 = paramInt1 << 2;
    i2 = paramInt1;
    int i3 = paramInt1 << 1;
    int i4 = paramInt1;
    for (j = 0; j < paramInt2; j++)
    {
      f7 = -hsqt2 * (paramArrayOfFloat1[m] + paramArrayOfFloat1[n]);
      f11 = hsqt2 * (paramArrayOfFloat1[m] - paramArrayOfFloat1[n]);
      paramArrayOfFloat2[(i2 - 1)] = (f11 + paramArrayOfFloat1[(i4 - 1)]);
      paramArrayOfFloat2[(i2 + i3 - 1)] = (paramArrayOfFloat1[(i4 - 1)] - f11);
      paramArrayOfFloat2[i2] = (f7 - paramArrayOfFloat1[(m + k)]);
      paramArrayOfFloat2[(i2 + i3)] = (f7 + paramArrayOfFloat1[(m + k)]);
      m += paramInt1;
      n += paramInt1;
      i2 += i1;
      i4 += paramInt1;
    }
  }
  
  static void dradfg(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, float[] paramArrayOfFloat4, float[] paramArrayOfFloat5, float[] paramArrayOfFloat6, int paramInt5)
  {
    int i7 = 0;
    float f7 = 0.0F;
    float f9 = 0.0F;
    float f8 = tpi / paramInt2;
    f7 = (float)Math.cos(f8);
    f9 = (float)Math.sin(f8);
    int j = paramInt2 + 1 >> 1;
    int i18 = paramInt2;
    int i17 = paramInt1;
    int i16 = paramInt1 - 1 >> 1;
    int i5 = paramInt3 * paramInt1;
    int i15 = paramInt2 * paramInt1;
    int i19 = 100;
    for (;;)
    {
      int i3;
      int i6;
      int m;
      int n;
      int i8;
      int k;
      int i9;
      int i10;
      int i11;
      int i12;
      int i13;
      int i14;
      switch (i19)
      {
      case 101: 
        if (paramInt1 == 1)
        {
          i19 = 119;
        }
        else
        {
          for (i3 = 0; i3 < paramInt4; i3++) {
            paramArrayOfFloat5[i3] = paramArrayOfFloat3[i3];
          }
          i6 = 0;
          for (m = 1; m < paramInt2; m++)
          {
            i6 += i5;
            i7 = i6;
            for (n = 0; n < paramInt3; n++)
            {
              paramArrayOfFloat4[i7] = paramArrayOfFloat2[i7];
              i7 += paramInt1;
            }
          }
          int i4 = -paramInt1;
          i6 = 0;
          int i;
          if (i16 > paramInt3) {
            for (m = 1; m < paramInt2; m++)
            {
              i6 += i5;
              i4 += paramInt1;
              i7 = -paramInt1 + i6;
              for (n = 0; n < paramInt3; n++)
              {
                i = i4 - 1;
                i7 += paramInt1;
                i8 = i7;
                for (k = 2; k < paramInt1; k += 2)
                {
                  i += 2;
                  i8 += 2;
                  paramArrayOfFloat4[(i8 - 1)] = (paramArrayOfFloat6[(paramInt5 + i - 1)] * paramArrayOfFloat2[(i8 - 1)] + paramArrayOfFloat6[(paramInt5 + i)] * paramArrayOfFloat2[i8]);
                  paramArrayOfFloat4[i8] = (paramArrayOfFloat6[(paramInt5 + i - 1)] * paramArrayOfFloat2[i8] - paramArrayOfFloat6[(paramInt5 + i)] * paramArrayOfFloat2[(i8 - 1)]);
                }
              }
            }
          } else {
            for (m = 1; m < paramInt2; m++)
            {
              i4 += paramInt1;
              i = i4 - 1;
              i6 += i5;
              i7 = i6;
              for (k = 2; k < paramInt1; k += 2)
              {
                i += 2;
                i7 += 2;
                i8 = i7;
                for (n = 0; n < paramInt3; n++)
                {
                  paramArrayOfFloat4[(i8 - 1)] = (paramArrayOfFloat6[(paramInt5 + i - 1)] * paramArrayOfFloat2[(i8 - 1)] + paramArrayOfFloat6[(paramInt5 + i)] * paramArrayOfFloat2[i8]);
                  paramArrayOfFloat4[i8] = (paramArrayOfFloat6[(paramInt5 + i - 1)] * paramArrayOfFloat2[i8] - paramArrayOfFloat6[(paramInt5 + i)] * paramArrayOfFloat2[(i8 - 1)]);
                  i8 += paramInt1;
                }
              }
            }
          }
          i6 = 0;
          i7 = i18 * i5;
          if (i16 < paramInt3) {
            for (m = 1; m < j; m++)
            {
              i6 += i5;
              i7 -= i5;
              i8 = i6;
              i9 = i7;
              for (k = 2; k < paramInt1; k += 2)
              {
                i8 += 2;
                i9 += 2;
                i10 = i8 - paramInt1;
                i11 = i9 - paramInt1;
                for (n = 0; n < paramInt3; n++)
                {
                  i10 += paramInt1;
                  i11 += paramInt1;
                  paramArrayOfFloat4[(i10 - 1)] += paramArrayOfFloat4[(i11 - 1)];
                  paramArrayOfFloat2[(i11 - 1)] = (paramArrayOfFloat4[i10] - paramArrayOfFloat4[i11]);
                  paramArrayOfFloat4[i10] += paramArrayOfFloat4[i11];
                  paramArrayOfFloat2[i11] = (paramArrayOfFloat4[(i11 - 1)] - paramArrayOfFloat4[(i10 - 1)]);
                }
              }
            }
          } else {
            for (m = 1; m < j; m++)
            {
              i6 += i5;
              i7 -= i5;
              i8 = i6;
              i9 = i7;
              for (n = 0; n < paramInt3; n++)
              {
                i10 = i8;
                i11 = i9;
                for (k = 2; k < paramInt1; k += 2)
                {
                  i10 += 2;
                  i11 += 2;
                  paramArrayOfFloat4[(i10 - 1)] += paramArrayOfFloat4[(i11 - 1)];
                  paramArrayOfFloat2[(i11 - 1)] = (paramArrayOfFloat4[i10] - paramArrayOfFloat4[i11]);
                  paramArrayOfFloat4[i10] += paramArrayOfFloat4[i11];
                  paramArrayOfFloat2[i11] = (paramArrayOfFloat4[(i11 - 1)] - paramArrayOfFloat4[(i10 - 1)]);
                }
                i8 += paramInt1;
                i9 += paramInt1;
              }
            }
          }
        }
        break;
      case 119: 
        for (i3 = 0; i3 < paramInt4; i3++) {
          paramArrayOfFloat3[i3] = paramArrayOfFloat5[i3];
        }
        i6 = 0;
        i7 = i18 * paramInt4;
        for (m = 1; m < j; m++)
        {
          i6 += i5;
          i7 -= i5;
          i8 = i6 - paramInt1;
          i9 = i7 - paramInt1;
          for (n = 0; n < paramInt3; n++)
          {
            i8 += paramInt1;
            i9 += paramInt1;
            paramArrayOfFloat4[i8] += paramArrayOfFloat4[i9];
            paramArrayOfFloat4[i9] -= paramArrayOfFloat4[i8];
          }
        }
        float f4 = 1.0F;
        float f2 = 0.0F;
        i6 = 0;
        i7 = i18 * paramInt4;
        i8 = (paramInt2 - 1) * paramInt4;
        for (int i1 = 1; i1 < j; i1++)
        {
          i6 += paramInt4;
          i7 -= paramInt4;
          float f10 = f7 * f4 - f9 * f2;
          f2 = f7 * f2 + f9 * f4;
          f4 = f10;
          i9 = i6;
          i10 = i7;
          i11 = i8;
          i12 = paramInt4;
          for (i3 = 0; i3 < paramInt4; i3++)
          {
            paramArrayOfFloat5[(i9++)] = (paramArrayOfFloat3[i3] + f4 * paramArrayOfFloat3[(i12++)]);
            paramArrayOfFloat5[(i10++)] = (f2 * paramArrayOfFloat3[(i11++)]);
          }
          float f1 = f4;
          float f6 = f2;
          float f5 = f4;
          float f3 = f2;
          i9 = paramInt4;
          i10 = (i18 - 1) * paramInt4;
          for (m = 2; m < j; m++)
          {
            i9 += paramInt4;
            i10 -= paramInt4;
            float f11 = f1 * f5 - f6 * f3;
            f3 = f1 * f3 + f6 * f5;
            f5 = f11;
            i11 = i6;
            i12 = i7;
            i13 = i9;
            i14 = i10;
            for (i3 = 0; i3 < paramInt4; i3++)
            {
              paramArrayOfFloat5[(i11++)] += f5 * paramArrayOfFloat3[(i13++)];
              paramArrayOfFloat5[(i12++)] += f3 * paramArrayOfFloat3[(i14++)];
            }
          }
        }
        i6 = 0;
        for (m = 1; m < j; m++)
        {
          i6 += paramInt4;
          i7 = i6;
          for (i3 = 0; i3 < paramInt4; i3++) {
            paramArrayOfFloat5[i3] += paramArrayOfFloat3[(i7++)];
          }
        }
        if (paramInt1 < paramInt3)
        {
          i19 = 132;
        }
        else
        {
          i6 = 0;
          i7 = 0;
          for (n = 0; n < paramInt3; n++)
          {
            i8 = i6;
            i9 = i7;
            for (k = 0; k < paramInt1; k++) {
              paramArrayOfFloat1[(i9++)] = paramArrayOfFloat4[(i8++)];
            }
            i6 += paramInt1;
            i7 += i15;
          }
          i19 = 135;
        }
        break;
      case 132: 
        for (k = 0; k < paramInt1; k++)
        {
          i6 = k;
          i7 = k;
          for (n = 0; n < paramInt3; n++)
          {
            paramArrayOfFloat1[i7] = paramArrayOfFloat4[i6];
            i6 += paramInt1;
            i7 += i15;
          }
        }
      case 135: 
        i6 = 0;
        i7 = paramInt1 << 1;
        i8 = 0;
        i9 = i18 * i5;
        for (m = 1; m < j; m++)
        {
          i6 += i7;
          i8 += i5;
          i9 -= i5;
          i10 = i6;
          i11 = i8;
          i12 = i9;
          for (n = 0; n < paramInt3; n++)
          {
            paramArrayOfFloat1[(i10 - 1)] = paramArrayOfFloat4[i11];
            paramArrayOfFloat1[i10] = paramArrayOfFloat4[i12];
            i10 += i15;
            i11 += paramInt1;
            i12 += paramInt1;
          }
        }
        if (paramInt1 == 1) {
          return;
        }
        if (i16 < paramInt3)
        {
          i19 = 141;
        }
        else
        {
          i6 = -paramInt1;
          i8 = 0;
          i9 = 0;
          i10 = i18 * i5;
          for (m = 1; m < j; m++)
          {
            i6 += i7;
            i8 += i7;
            i9 += i5;
            i10 -= i5;
            i11 = i6;
            i12 = i8;
            i13 = i9;
            i14 = i10;
            for (n = 0; n < paramInt3; n++)
            {
              for (k = 2; k < paramInt1; k += 2)
              {
                int i2 = i17 - k;
                paramArrayOfFloat4[(k + i13 - 1)] += paramArrayOfFloat4[(k + i14 - 1)];
                paramArrayOfFloat4[(k + i13 - 1)] -= paramArrayOfFloat4[(k + i14 - 1)];
                paramArrayOfFloat4[(k + i13)] += paramArrayOfFloat4[(k + i14)];
                paramArrayOfFloat4[(k + i14)] -= paramArrayOfFloat4[(k + i13)];
              }
              i11 += i15;
              i12 += i15;
              i13 += paramInt1;
              i14 += paramInt1;
            }
          }
          return;
        }
        break;
      case 141: 
        i6 = -paramInt1;
        i8 = 0;
        i9 = 0;
        i10 = i18 * i5;
        for (m = 1; m < j; m++)
        {
          i6 += i7;
          i8 += i7;
          i9 += i5;
          i10 -= i5;
          for (k = 2; k < paramInt1; k += 2)
          {
            i11 = i17 + i6 - k;
            i12 = k + i8;
            i13 = k + i9;
            i14 = k + i10;
            for (n = 0; n < paramInt3; n++)
            {
              paramArrayOfFloat4[(i13 - 1)] += paramArrayOfFloat4[(i14 - 1)];
              paramArrayOfFloat4[(i13 - 1)] -= paramArrayOfFloat4[(i14 - 1)];
              paramArrayOfFloat4[i13] += paramArrayOfFloat4[i14];
              paramArrayOfFloat4[i14] -= paramArrayOfFloat4[i13];
              i11 += i15;
              i12 += i15;
              i13 += paramInt1;
              i14 += paramInt1;
            }
          }
        }
        return;
      }
    }
  }
  
  static void drftf1(int paramInt, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, int[] paramArrayOfInt)
  {
    int i2 = paramArrayOfInt[1];
    int n = 1;
    int m = paramInt;
    int i4 = paramInt;
    label383:
    for (int j = 0; j < i2; j++)
    {
      int i1 = i2 - j;
      int i3 = paramArrayOfInt[(i1 + 1)];
      int k = m / i3;
      int i5 = paramInt / m;
      int i6 = i5 * k;
      i4 -= (i3 - 1) * i5;
      n = 1 - n;
      int i9 = 100;
      for (;;)
      {
        switch (i9)
        {
        case 100: 
          if (i3 != 4)
          {
            i9 = 102;
          }
          else
          {
            int i7 = i4 + i5;
            int i8 = i7 + i5;
            if (n != 0) {
              dradf4(i5, k, paramArrayOfFloat2, paramArrayOfFloat1, paramArrayOfFloat3, i4 - 1, paramArrayOfFloat3, i7 - 1, paramArrayOfFloat3, i8 - 1);
            } else {
              dradf4(i5, k, paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat3, i4 - 1, paramArrayOfFloat3, i7 - 1, paramArrayOfFloat3, i8 - 1);
            }
            i9 = 110;
          }
          break;
        case 102: 
          if (i3 != 2)
          {
            i9 = 104;
          }
          else if (n != 0)
          {
            i9 = 103;
          }
          else
          {
            dradf2(i5, k, paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat3, i4 - 1);
            i9 = 110;
          }
          break;
        case 103: 
          dradf2(i5, k, paramArrayOfFloat2, paramArrayOfFloat1, paramArrayOfFloat3, i4 - 1);
        case 104: 
          if (i5 == 1) {
            n = 1 - n;
          }
          if (n != 0)
          {
            i9 = 109;
          }
          else
          {
            dradfg(i5, i3, k, i6, paramArrayOfFloat1, paramArrayOfFloat1, paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat2, paramArrayOfFloat3, i4 - 1);
            n = 1;
            i9 = 110;
          }
          break;
        case 109: 
          dradfg(i5, i3, k, i6, paramArrayOfFloat2, paramArrayOfFloat2, paramArrayOfFloat2, paramArrayOfFloat1, paramArrayOfFloat1, paramArrayOfFloat3, i4 - 1);
          n = 0;
        case 110: 
          m = k;
          break label383;
        }
      }
    }
    if (n == 1) {
      return;
    }
    for (int i = 0; i < paramInt; i++) {
      paramArrayOfFloat1[i] = paramArrayOfFloat2[i];
    }
  }
  
  static void dradb2(int paramInt1, int paramInt2, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, int paramInt3)
  {
    int k = paramInt2 * paramInt1;
    int m = 0;
    int n = 0;
    int i1 = (paramInt1 << 1) - 1;
    for (int j = 0; j < paramInt2; j++)
    {
      paramArrayOfFloat1[n] += paramArrayOfFloat1[(i1 + n)];
      paramArrayOfFloat2[(m + k)] = (paramArrayOfFloat1[n] - paramArrayOfFloat1[(i1 + n)]);
      n = m += paramInt1 << 1;
    }
    if (paramInt1 < 2) {
      return;
    }
    if (paramInt1 != 2)
    {
      m = 0;
      n = 0;
      for (j = 0; j < paramInt2; j++)
      {
        i1 = m;
        int i2;
        int i3 = (i2 = n) + (paramInt1 << 1);
        int i4 = k + m;
        for (int i = 2; i < paramInt1; i += 2)
        {
          i1 += 2;
          i2 += 2;
          i3 -= 2;
          i4 += 2;
          paramArrayOfFloat1[(i2 - 1)] += paramArrayOfFloat1[(i3 - 1)];
          float f2 = paramArrayOfFloat1[(i2 - 1)] - paramArrayOfFloat1[(i3 - 1)];
          paramArrayOfFloat1[i2] -= paramArrayOfFloat1[i3];
          float f1 = paramArrayOfFloat1[i2] + paramArrayOfFloat1[i3];
          paramArrayOfFloat2[(i4 - 1)] = (paramArrayOfFloat3[(paramInt3 + i - 2)] * f2 - paramArrayOfFloat3[(paramInt3 + i - 1)] * f1);
          paramArrayOfFloat2[i4] = (paramArrayOfFloat3[(paramInt3 + i - 2)] * f1 + paramArrayOfFloat3[(paramInt3 + i - 1)] * f2);
        }
        n = m += paramInt1 << 1;
      }
      if (paramInt1 % 2 == 1) {
        return;
      }
    }
    m = paramInt1 - 1;
    n = paramInt1 - 1;
    for (j = 0; j < paramInt2; j++)
    {
      paramArrayOfFloat1[n] += paramArrayOfFloat1[n];
      paramArrayOfFloat2[(m + k)] = (-(paramArrayOfFloat1[(n + 1)] + paramArrayOfFloat1[(n + 1)]));
      m += paramInt1;
      n += (paramInt1 << 1);
    }
  }
  
  static void dradb3(int paramInt1, int paramInt2, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, int paramInt3, float[] paramArrayOfFloat4, int paramInt4)
  {
    int k = paramInt2 * paramInt1;
    int m = 0;
    int n = k << 1;
    int i1 = paramInt1 << 1;
    int i2 = paramInt1 + (paramInt1 << 1);
    int i3 = 0;
    float f10;
    float f5;
    float f2;
    for (int j = 0; j < paramInt2; j++)
    {
      f10 = paramArrayOfFloat1[(i1 - 1)] + paramArrayOfFloat1[(i1 - 1)];
      f5 = paramArrayOfFloat1[i3] + taur * f10;
      paramArrayOfFloat1[i3] += f10;
      f2 = taui * (paramArrayOfFloat1[i1] + paramArrayOfFloat1[i1]);
      paramArrayOfFloat2[(m + k)] = (f5 - f2);
      paramArrayOfFloat2[(m + n)] = (f5 + f2);
      m += paramInt1;
      i1 += i2;
      i3 += i2;
    }
    if (paramInt1 == 1) {
      return;
    }
    m = 0;
    i1 = paramInt1 << 1;
    for (j = 0; j < paramInt2; j++)
    {
      int i5 = m + (m << 1);
      int i4 = i3 = i5 + i1;
      int i6 = m;
      int i7;
      int i8 = (i7 = m + k) + k;
      for (int i = 2; i < paramInt1; i += 2)
      {
        i3 += 2;
        i4 -= 2;
        i5 += 2;
        i6 += 2;
        i7 += 2;
        i8 += 2;
        f10 = paramArrayOfFloat1[(i3 - 1)] + paramArrayOfFloat1[(i4 - 1)];
        f5 = paramArrayOfFloat1[(i5 - 1)] + taur * f10;
        paramArrayOfFloat1[(i5 - 1)] += f10;
        float f9 = paramArrayOfFloat1[i3] - paramArrayOfFloat1[i4];
        float f1 = paramArrayOfFloat1[i5] + taur * f9;
        paramArrayOfFloat1[i5] += f9;
        float f6 = taui * (paramArrayOfFloat1[(i3 - 1)] - paramArrayOfFloat1[(i4 - 1)]);
        f2 = taui * (paramArrayOfFloat1[i3] + paramArrayOfFloat1[i4]);
        float f7 = f5 - f2;
        float f8 = f5 + f2;
        float f3 = f1 + f6;
        float f4 = f1 - f6;
        paramArrayOfFloat2[(i7 - 1)] = (paramArrayOfFloat3[(paramInt3 + i - 2)] * f7 - paramArrayOfFloat3[(paramInt3 + i - 1)] * f3);
        paramArrayOfFloat2[i7] = (paramArrayOfFloat3[(paramInt3 + i - 2)] * f3 + paramArrayOfFloat3[(paramInt3 + i - 1)] * f7);
        paramArrayOfFloat2[(i8 - 1)] = (paramArrayOfFloat4[(paramInt4 + i - 2)] * f8 - paramArrayOfFloat4[(paramInt4 + i - 1)] * f4);
        paramArrayOfFloat2[i8] = (paramArrayOfFloat4[(paramInt4 + i - 2)] * f4 + paramArrayOfFloat4[(paramInt4 + i - 1)] * f8);
      }
      m += paramInt1;
    }
  }
  
  static void dradb4(int paramInt1, int paramInt2, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, int paramInt3, float[] paramArrayOfFloat4, int paramInt4, float[] paramArrayOfFloat5, int paramInt5)
  {
    int k = paramInt2 * paramInt1;
    int m = 0;
    int n = paramInt1 << 2;
    int i1 = 0;
    int i4 = paramInt1 << 1;
    int i3;
    float f13;
    float f14;
    float f11;
    float f12;
    for (int j = 0; j < paramInt2; j++)
    {
      i2 = i1 + i4;
      i3 = m;
      f13 = paramArrayOfFloat1[(i2 - 1)] + paramArrayOfFloat1[(i2 - 1)];
      f14 = paramArrayOfFloat1[i2] + paramArrayOfFloat1[i2];
      f11 = paramArrayOfFloat1[i1] - paramArrayOfFloat1[(i2 += i4 - 1)];
      f12 = paramArrayOfFloat1[i1] + paramArrayOfFloat1[(i2 - 1)];
      paramArrayOfFloat2[i3] = (f12 + f13);
      int tmp111_110 = (i3 + k);
      i3 = tmp111_110;
      paramArrayOfFloat2[tmp111_110] = (f11 - f14);
      int tmp126_125 = (i3 + k);
      i3 = tmp126_125;
      paramArrayOfFloat2[tmp126_125] = (f12 - f13);
      int tmp141_140 = (i3 + k);
      i3 = tmp141_140;
      paramArrayOfFloat2[tmp141_140] = (f11 + f14);
      m += paramInt1;
      i1 += n;
    }
    if (paramInt1 < 2) {
      return;
    }
    float f7;
    float f8;
    if (paramInt1 != 2)
    {
      m = 0;
      for (j = 0; j < paramInt2; j++)
      {
        i3 = (i2 = i1 = (n = m << 2) + i4) + i4;
        int i5 = m;
        for (int i = 2; i < paramInt1; i += 2)
        {
          n += 2;
          i1 += 2;
          i2 -= 2;
          i3 -= 2;
          i5 += 2;
          f7 = paramArrayOfFloat1[n] + paramArrayOfFloat1[i3];
          f8 = paramArrayOfFloat1[n] - paramArrayOfFloat1[i3];
          float f9 = paramArrayOfFloat1[i1] - paramArrayOfFloat1[i2];
          f14 = paramArrayOfFloat1[i1] + paramArrayOfFloat1[i2];
          f11 = paramArrayOfFloat1[(n - 1)] - paramArrayOfFloat1[(i3 - 1)];
          f12 = paramArrayOfFloat1[(n - 1)] + paramArrayOfFloat1[(i3 - 1)];
          float f10 = paramArrayOfFloat1[(i1 - 1)] - paramArrayOfFloat1[(i2 - 1)];
          f13 = paramArrayOfFloat1[(i1 - 1)] + paramArrayOfFloat1[(i2 - 1)];
          paramArrayOfFloat2[(i5 - 1)] = (f12 + f13);
          float f5 = f12 - f13;
          paramArrayOfFloat2[i5] = (f8 + f9);
          float f2 = f8 - f9;
          float f4 = f11 - f14;
          float f6 = f11 + f14;
          float f1 = f7 + f10;
          float f3 = f7 - f10;
          int tmp410_409 = (i5 + k);
          int i6 = tmp410_409;
          paramArrayOfFloat2[(tmp410_409 - 1)] = (paramArrayOfFloat3[(paramInt3 + i - 2)] * f4 - paramArrayOfFloat3[(paramInt3 + i - 1)] * f1);
          paramArrayOfFloat2[i6] = (paramArrayOfFloat3[(paramInt3 + i - 2)] * f1 + paramArrayOfFloat3[(paramInt3 + i - 1)] * f4);
          int tmp480_479 = (i6 + k);
          i6 = tmp480_479;
          paramArrayOfFloat2[(tmp480_479 - 1)] = (paramArrayOfFloat4[(paramInt4 + i - 2)] * f5 - paramArrayOfFloat4[(paramInt4 + i - 1)] * f2);
          paramArrayOfFloat2[i6] = (paramArrayOfFloat4[(paramInt4 + i - 2)] * f2 + paramArrayOfFloat4[(paramInt4 + i - 1)] * f5);
          int tmp550_549 = (i6 + k);
          i6 = tmp550_549;
          paramArrayOfFloat2[(tmp550_549 - 1)] = (paramArrayOfFloat5[(paramInt5 + i - 2)] * f6 - paramArrayOfFloat5[(paramInt5 + i - 1)] * f3);
          paramArrayOfFloat2[i6] = (paramArrayOfFloat5[(paramInt5 + i - 2)] * f3 + paramArrayOfFloat5[(paramInt5 + i - 1)] * f6);
        }
        m += paramInt1;
      }
      if (paramInt1 % 2 == 1) {
        return;
      }
    }
    m = paramInt1;
    n = paramInt1 << 2;
    i1 = paramInt1 - 1;
    int i2 = paramInt1 + (paramInt1 << 1);
    for (j = 0; j < paramInt2; j++)
    {
      i3 = i1;
      f7 = paramArrayOfFloat1[m] + paramArrayOfFloat1[i2];
      f8 = paramArrayOfFloat1[i2] - paramArrayOfFloat1[m];
      f11 = paramArrayOfFloat1[(m - 1)] - paramArrayOfFloat1[(i2 - 1)];
      f12 = paramArrayOfFloat1[(m - 1)] + paramArrayOfFloat1[(i2 - 1)];
      paramArrayOfFloat2[i3] = (f12 + f12);
      int tmp743_742 = (i3 + k);
      i3 = tmp743_742;
      paramArrayOfFloat2[tmp743_742] = (sqrt2 * (f11 - f7));
      int tmp762_761 = (i3 + k);
      i3 = tmp762_761;
      paramArrayOfFloat2[tmp762_761] = (f8 + f8);
      int tmp777_776 = (i3 + k);
      i3 = tmp777_776;
      paramArrayOfFloat2[tmp777_776] = (-sqrt2 * (f11 + f7));
      i1 += paramInt1;
      m += n;
      i2 += n;
    }
  }
  
  static void dradbg(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, float[] paramArrayOfFloat4, float[] paramArrayOfFloat5, float[] paramArrayOfFloat6, int paramInt5)
  {
    int j = 0;
    int i4 = 0;
    int i14 = 0;
    int i17 = 0;
    float f7 = 0.0F;
    float f9 = 0.0F;
    int i18 = 0;
    int i19 = 100;
    for (;;)
    {
      int i5;
      int i6;
      int n;
      int i7;
      int i8;
      int k;
      int i9;
      int i11;
      int m;
      int i10;
      int i12;
      int i13;
      int i15;
      int i16;
      int i2;
      int i3;
      int i;
      switch (i19)
      {
      case 100: 
        i14 = paramInt2 * paramInt1;
        i4 = paramInt3 * paramInt1;
        float f8 = tpi / paramInt2;
        f7 = (float)Math.cos(f8);
        f9 = (float)Math.sin(f8);
        i17 = paramInt1 - 1 >>> 1;
        i18 = paramInt2;
        j = paramInt2 + 1 >>> 1;
        if (paramInt1 < paramInt3)
        {
          i19 = 103;
        }
        else
        {
          i5 = 0;
          i6 = 0;
          for (n = 0; n < paramInt3; n++)
          {
            i7 = i5;
            i8 = i6;
            for (k = 0; k < paramInt1; k++)
            {
              paramArrayOfFloat4[i7] = paramArrayOfFloat1[i8];
              i7++;
              i8++;
            }
            i5 += paramInt1;
            i6 += i14;
          }
          i19 = 106;
        }
        break;
      case 103: 
        i5 = 0;
        for (k = 0; k < paramInt1; k++)
        {
          i6 = i5;
          i7 = i5;
          for (n = 0; n < paramInt3; n++)
          {
            paramArrayOfFloat4[i6] = paramArrayOfFloat1[i7];
            i6 += paramInt1;
            i7 += i14;
          }
          i5++;
        }
      case 106: 
        i5 = 0;
        i6 = i18 * i4;
        i11 = i9 = paramInt1 << 1;
        for (m = 1; m < j; m++)
        {
          i5 += i4;
          i6 -= i4;
          i7 = i5;
          i8 = i6;
          i10 = i9;
          for (n = 0; n < paramInt3; n++)
          {
            paramArrayOfFloat4[i7] = (paramArrayOfFloat1[(i10 - 1)] + paramArrayOfFloat1[(i10 - 1)]);
            paramArrayOfFloat1[i10] += paramArrayOfFloat1[i10];
            i7 += paramInt1;
            i8 += paramInt1;
            i10 += i14;
          }
          i9 += i11;
        }
        if (paramInt1 == 1)
        {
          i19 = 116;
        }
        else if (i17 < paramInt3)
        {
          i19 = 112;
        }
        else
        {
          i5 = 0;
          i6 = i18 * i4;
          i11 = 0;
          for (m = 1; m < j; m++)
          {
            i5 += i4;
            i6 -= i4;
            i7 = i5;
            i8 = i6;
            i11 += (paramInt1 << 1);
            i12 = i11;
            for (n = 0; n < paramInt3; n++)
            {
              i9 = i7;
              i10 = i8;
              i13 = i12;
              i15 = i12;
              for (k = 2; k < paramInt1; k += 2)
              {
                i9 += 2;
                i10 += 2;
                i13 += 2;
                i15 -= 2;
                paramArrayOfFloat1[(i13 - 1)] += paramArrayOfFloat1[(i15 - 1)];
                paramArrayOfFloat1[(i13 - 1)] -= paramArrayOfFloat1[(i15 - 1)];
                paramArrayOfFloat1[i13] -= paramArrayOfFloat1[i15];
                paramArrayOfFloat1[i13] += paramArrayOfFloat1[i15];
              }
              i7 += paramInt1;
              i8 += paramInt1;
              i12 += i14;
            }
          }
          i19 = 116;
        }
        break;
      case 112: 
        i5 = 0;
        i6 = i18 * i4;
        i11 = 0;
        for (m = 1; m < j; m++)
        {
          i5 += i4;
          i6 -= i4;
          i7 = i5;
          i8 = i6;
          i11 += (paramInt1 << 1);
          i12 = i11;
          i13 = i11;
          for (k = 2; k < paramInt1; k += 2)
          {
            i7 += 2;
            i8 += 2;
            i12 += 2;
            i13 -= 2;
            i9 = i7;
            i10 = i8;
            i15 = i12;
            i16 = i13;
            for (n = 0; n < paramInt3; n++)
            {
              paramArrayOfFloat1[(i15 - 1)] += paramArrayOfFloat1[(i16 - 1)];
              paramArrayOfFloat1[(i15 - 1)] -= paramArrayOfFloat1[(i16 - 1)];
              paramArrayOfFloat1[i15] -= paramArrayOfFloat1[i16];
              paramArrayOfFloat1[i15] += paramArrayOfFloat1[i16];
              i9 += paramInt1;
              i10 += paramInt1;
              i15 += i14;
              i16 += i14;
            }
          }
        }
      case 116: 
        float f4 = 1.0F;
        float f2 = 0.0F;
        i5 = 0;
        i13 = i6 = i18 * paramInt4;
        i7 = (paramInt2 - 1) * paramInt4;
        for (int i1 = 1; i1 < j; i1++)
        {
          i5 += paramInt4;
          i6 -= paramInt4;
          float f10 = f7 * f4 - f9 * f2;
          f2 = f7 * f2 + f9 * f4;
          f4 = f10;
          i8 = i5;
          i9 = i6;
          i10 = 0;
          i11 = paramInt4;
          i12 = i7;
          for (i2 = 0; i2 < paramInt4; i2++)
          {
            paramArrayOfFloat5[(i10++)] += f4 * paramArrayOfFloat5[(i11++)];
            paramArrayOfFloat3[(i9++)] = (f2 * paramArrayOfFloat5[(i12++)]);
          }
          float f1 = f4;
          float f6 = f2;
          float f5 = f4;
          float f3 = f2;
          i10 = paramInt4;
          i11 = i13 - paramInt4;
          for (m = 2; m < j; m++)
          {
            i10 += paramInt4;
            i11 -= paramInt4;
            float f11 = f1 * f5 - f6 * f3;
            f3 = f1 * f3 + f6 * f5;
            f5 = f11;
            i8 = i5;
            i9 = i6;
            i15 = i10;
            i16 = i11;
            for (i2 = 0; i2 < paramInt4; i2++)
            {
              paramArrayOfFloat3[(i8++)] += f5 * paramArrayOfFloat5[(i15++)];
              paramArrayOfFloat3[(i9++)] += f3 * paramArrayOfFloat5[(i16++)];
            }
          }
        }
        i5 = 0;
        for (m = 1; m < j; m++)
        {
          i5 += paramInt4;
          i6 = i5;
          for (i2 = 0; i2 < paramInt4; i2++) {
            paramArrayOfFloat5[i2] += paramArrayOfFloat5[(i6++)];
          }
        }
        i5 = 0;
        i6 = i18 * i4;
        for (m = 1; m < j; m++)
        {
          i5 += i4;
          i6 -= i4;
          i7 = i5;
          i8 = i6;
          for (n = 0; n < paramInt3; n++)
          {
            paramArrayOfFloat2[i7] -= paramArrayOfFloat2[i8];
            paramArrayOfFloat2[i7] += paramArrayOfFloat2[i8];
            i7 += paramInt1;
            i8 += paramInt1;
          }
        }
        if (paramInt1 == 1)
        {
          i19 = 132;
        }
        else if (i17 < paramInt3)
        {
          i19 = 128;
        }
        else
        {
          i5 = 0;
          i6 = i18 * i4;
          for (m = 1; m < j; m++)
          {
            i5 += i4;
            i6 -= i4;
            i7 = i5;
            i8 = i6;
            for (n = 0; n < paramInt3; n++)
            {
              i9 = i7;
              i10 = i8;
              for (k = 2; k < paramInt1; k += 2)
              {
                i9 += 2;
                i10 += 2;
                paramArrayOfFloat2[(i9 - 1)] -= paramArrayOfFloat2[i10];
                paramArrayOfFloat2[(i9 - 1)] += paramArrayOfFloat2[i10];
                paramArrayOfFloat2[i9] += paramArrayOfFloat2[(i10 - 1)];
                paramArrayOfFloat2[i9] -= paramArrayOfFloat2[(i10 - 1)];
              }
              i7 += paramInt1;
              i8 += paramInt1;
            }
          }
          i19 = 132;
        }
        break;
      case 128: 
        i5 = 0;
        i6 = i18 * i4;
        for (m = 1; m < j; m++)
        {
          i5 += i4;
          i6 -= i4;
          i7 = i5;
          i8 = i6;
          for (k = 2; k < paramInt1; k += 2)
          {
            i7 += 2;
            i8 += 2;
            i9 = i7;
            i10 = i8;
            for (n = 0; n < paramInt3; n++)
            {
              paramArrayOfFloat2[(i9 - 1)] -= paramArrayOfFloat2[i10];
              paramArrayOfFloat2[(i9 - 1)] += paramArrayOfFloat2[i10];
              paramArrayOfFloat2[i9] += paramArrayOfFloat2[(i10 - 1)];
              paramArrayOfFloat2[i9] -= paramArrayOfFloat2[(i10 - 1)];
              i9 += paramInt1;
              i10 += paramInt1;
            }
          }
        }
      case 132: 
        if (paramInt1 == 1) {
          return;
        }
        for (i2 = 0; i2 < paramInt4; i2++) {
          paramArrayOfFloat3[i2] = paramArrayOfFloat5[i2];
        }
        i5 = 0;
        for (m = 1; m < paramInt2; m++)
        {
          i6 = i5 += i4;
          for (n = 0; n < paramInt3; n++)
          {
            paramArrayOfFloat2[i6] = paramArrayOfFloat4[i6];
            i6 += paramInt1;
          }
        }
        if (i17 > paramInt3)
        {
          i19 = 139;
        }
        else
        {
          i3 = -paramInt1 - 1;
          i5 = 0;
          for (m = 1; m < paramInt2; m++)
          {
            i3 += paramInt1;
            i5 += i4;
            i = i3;
            i6 = i5;
            for (k = 2; k < paramInt1; k += 2)
            {
              i6 += 2;
              i += 2;
              i7 = i6;
              for (n = 0; n < paramInt3; n++)
              {
                paramArrayOfFloat2[(i7 - 1)] = (paramArrayOfFloat6[(paramInt5 + i - 1)] * paramArrayOfFloat4[(i7 - 1)] - paramArrayOfFloat6[(paramInt5 + i)] * paramArrayOfFloat4[i7]);
                paramArrayOfFloat2[i7] = (paramArrayOfFloat6[(paramInt5 + i - 1)] * paramArrayOfFloat4[i7] + paramArrayOfFloat6[(paramInt5 + i)] * paramArrayOfFloat4[(i7 - 1)]);
                i7 += paramInt1;
              }
            }
          }
          return;
        }
        break;
      case 139: 
        i3 = -paramInt1 - 1;
        i5 = 0;
        for (m = 1; m < paramInt2; m++)
        {
          i3 += paramInt1;
          i5 += i4;
          i6 = i5;
          for (n = 0; n < paramInt3; n++)
          {
            i = i3;
            i7 = i6;
            for (k = 2; k < paramInt1; k += 2)
            {
              i += 2;
              i7 += 2;
              paramArrayOfFloat2[(i7 - 1)] = (paramArrayOfFloat6[(paramInt5 + i - 1)] * paramArrayOfFloat4[(i7 - 1)] - paramArrayOfFloat6[(paramInt5 + i)] * paramArrayOfFloat4[i7]);
              paramArrayOfFloat2[i7] = (paramArrayOfFloat6[(paramInt5 + i - 1)] * paramArrayOfFloat4[i7] + paramArrayOfFloat6[(paramInt5 + i)] * paramArrayOfFloat4[(i7 - 1)]);
            }
            i6 += paramInt1;
          }
        }
        return;
      }
    }
  }
  
  static void drftb1(int paramInt1, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, int paramInt2, int[] paramArrayOfInt)
  {
    int m = 0;
    int i2 = 0;
    int i6 = 0;
    int i7 = 0;
    int i1 = paramArrayOfInt[1];
    int n = 0;
    int k = 1;
    int i3 = 1;
    label484:
    for (int j = 0; j < i1; j++)
    {
      int i8 = 100;
      for (;;)
      {
        int i4;
        switch (i8)
        {
        case 100: 
          i2 = paramArrayOfInt[(j + 2)];
          m = i2 * k;
          i6 = paramInt1 / m;
          i7 = i6 * k;
          if (i2 != 4)
          {
            i8 = 103;
          }
          else
          {
            i4 = i3 + i6;
            int i5 = i4 + i6;
            if (n != 0) {
              dradb4(i6, k, paramArrayOfFloat2, paramArrayOfFloat1, paramArrayOfFloat3, paramInt2 + i3 - 1, paramArrayOfFloat3, paramInt2 + i4 - 1, paramArrayOfFloat3, paramInt2 + i5 - 1);
            } else {
              dradb4(i6, k, paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat3, paramInt2 + i3 - 1, paramArrayOfFloat3, paramInt2 + i4 - 1, paramArrayOfFloat3, paramInt2 + i5 - 1);
            }
            n = 1 - n;
            i8 = 115;
          }
          break;
        case 103: 
          if (i2 != 2)
          {
            i8 = 106;
          }
          else
          {
            if (n != 0) {
              dradb2(i6, k, paramArrayOfFloat2, paramArrayOfFloat1, paramArrayOfFloat3, paramInt2 + i3 - 1);
            } else {
              dradb2(i6, k, paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat3, paramInt2 + i3 - 1);
            }
            n = 1 - n;
            i8 = 115;
          }
          break;
        case 106: 
          if (i2 != 3)
          {
            i8 = 109;
          }
          else
          {
            i4 = i3 + i6;
            if (n != 0) {
              dradb3(i6, k, paramArrayOfFloat2, paramArrayOfFloat1, paramArrayOfFloat3, paramInt2 + i3 - 1, paramArrayOfFloat3, paramInt2 + i4 - 1);
            } else {
              dradb3(i6, k, paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat3, paramInt2 + i3 - 1, paramArrayOfFloat3, paramInt2 + i4 - 1);
            }
            n = 1 - n;
            i8 = 115;
          }
          break;
        case 109: 
          if (n != 0) {
            dradbg(i6, i2, k, i7, paramArrayOfFloat2, paramArrayOfFloat2, paramArrayOfFloat2, paramArrayOfFloat1, paramArrayOfFloat1, paramArrayOfFloat3, paramInt2 + i3 - 1);
          } else {
            dradbg(i6, i2, k, i7, paramArrayOfFloat1, paramArrayOfFloat1, paramArrayOfFloat1, paramArrayOfFloat2, paramArrayOfFloat2, paramArrayOfFloat3, paramInt2 + i3 - 1);
          }
          if (i6 == 1) {
            n = 1 - n;
          }
        case 115: 
          k = m;
          i3 += (i2 - 1) * i6;
          break label484;
        }
      }
    }
    if (n == 0) {
      return;
    }
    for (int i = 0; i < paramInt1; i++) {
      paramArrayOfFloat1[i] = paramArrayOfFloat2[i];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Drft
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */