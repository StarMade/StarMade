package com.jcraft.jorbis;

class Mdct
{
  private static final float cPI3_8 = 0.3826834F;
  private static final float cPI2_8 = 0.7071068F;
  private static final float cPI1_8 = 0.92388F;
  int field_2064;
  int log2n;
  float[] trig;
  int[] bitrev;
  float scale;
  float[] field_2065 = new float[1024];
  float[] field_2066 = new float[1024];
  
  void init(int paramInt)
  {
    this.bitrev = new int[paramInt / 4];
    this.trig = new float[paramInt + paramInt / 4];
    int i = paramInt >>> 1;
    this.log2n = ((int)Math.rint(Math.log(paramInt) / Math.log(2.0D)));
    this.field_2064 = paramInt;
    int j = 0;
    int k = 1;
    int m = j + paramInt / 2;
    int n = m + 1;
    int i1 = m + paramInt / 2;
    int i2 = i1 + 1;
    for (int i3 = 0; i3 < paramInt / 4; i3++)
    {
      this.trig[(j + i3 * 2)] = ((float)Math.cos(3.141592653589793D / paramInt * (4 * i3)));
      this.trig[(k + i3 * 2)] = ((float)-Math.sin(3.141592653589793D / paramInt * (4 * i3)));
      this.trig[(m + i3 * 2)] = ((float)Math.cos(3.141592653589793D / (2 * paramInt) * (2 * i3 + 1)));
      this.trig[(n + i3 * 2)] = ((float)Math.sin(3.141592653589793D / (2 * paramInt) * (2 * i3 + 1)));
    }
    for (int i4 = 0; i4 < paramInt / 8; i4++)
    {
      this.trig[(i1 + i4 * 2)] = ((float)Math.cos(3.141592653589793D / paramInt * (4 * i4 + 2)));
      this.trig[(i2 + i4 * 2)] = ((float)-Math.sin(3.141592653589793D / paramInt * (4 * i4 + 2)));
    }
    int i5 = (1 << this.log2n - 1) - 1;
    int i6 = 1 << this.log2n - 2;
    for (int i7 = 0; i7 < paramInt / 8; i7++)
    {
      int i8 = 0;
      for (int i9 = 0; i6 >>> i9 != 0; i9++) {
        if ((i6 >>> i9 & i7) != 0) {
          i8 |= 1 << i9;
        }
      }
      this.bitrev[(i7 * 2)] = ((i8 ^ 0xFFFFFFFF) & i5);
      this.bitrev[(i7 * 2 + 1)] = i8;
    }
    this.scale = (4.0F / paramInt);
  }
  
  void clear() {}
  
  void forward(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2) {}
  
  synchronized void backward(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    if (this.field_2065.length < this.field_2064 / 2) {
      this.field_2065 = new float[this.field_2064 / 2];
    }
    if (this.field_2066.length < this.field_2064 / 2) {
      this.field_2066 = new float[this.field_2064 / 2];
    }
    float[] arrayOfFloat1 = this.field_2065;
    float[] arrayOfFloat2 = this.field_2066;
    int i = this.field_2064 >>> 1;
    int j = this.field_2064 >>> 2;
    int k = this.field_2064 >>> 3;
    int m = 1;
    int n = 0;
    int i1 = i;
    for (int i2 = 0; i2 < k; i2++)
    {
      i1 -= 2;
      arrayOfFloat1[(n++)] = (-paramArrayOfFloat1[(m + 2)] * this.trig[(i1 + 1)] - paramArrayOfFloat1[m] * this.trig[i1]);
      arrayOfFloat1[(n++)] = (paramArrayOfFloat1[m] * this.trig[(i1 + 1)] - paramArrayOfFloat1[(m + 2)] * this.trig[i1]);
      m += 4;
    }
    m = i - 4;
    for (i2 = 0; i2 < k; i2++)
    {
      i1 -= 2;
      arrayOfFloat1[(n++)] = (paramArrayOfFloat1[m] * this.trig[(i1 + 1)] + paramArrayOfFloat1[(m + 2)] * this.trig[i1]);
      arrayOfFloat1[(n++)] = (paramArrayOfFloat1[m] * this.trig[i1] - paramArrayOfFloat1[(m + 2)] * this.trig[(i1 + 1)]);
      m -= 4;
    }
    float[] arrayOfFloat3 = mdct_kernel(arrayOfFloat1, arrayOfFloat2, this.field_2064, i, j, k);
    n = 0;
    i1 = i;
    i2 = j;
    int i3 = i2 - 1;
    int i4 = j + i;
    int i5 = i4 - 1;
    for (int i6 = 0; i6 < j; i6++)
    {
      float f1 = arrayOfFloat3[n] * this.trig[(i1 + 1)] - arrayOfFloat3[(n + 1)] * this.trig[i1];
      float f2 = -(arrayOfFloat3[n] * this.trig[i1] + arrayOfFloat3[(n + 1)] * this.trig[(i1 + 1)]);
      paramArrayOfFloat2[i2] = (-f1);
      paramArrayOfFloat2[i3] = f1;
      paramArrayOfFloat2[i4] = f2;
      paramArrayOfFloat2[i5] = f2;
      i2++;
      i3--;
      i4++;
      i5--;
      n += 2;
      i1 += 2;
    }
  }
  
  private float[] mdct_kernel(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = paramInt3;
    int j = 0;
    int k = paramInt3;
    int m = paramInt2;
    for (int n = 0; n < paramInt3; n++)
    {
      float f1 = paramArrayOfFloat1[i] - paramArrayOfFloat1[j];
      paramArrayOfFloat2[(k + n)] = (paramArrayOfFloat1[(i++)] + paramArrayOfFloat1[(j++)]);
      float f2 = paramArrayOfFloat1[i] - paramArrayOfFloat1[j];
      m -= 4;
      paramArrayOfFloat2[(n++)] = (f1 * this.trig[m] + f2 * this.trig[(m + 1)]);
      paramArrayOfFloat2[n] = (f2 * this.trig[m] - f1 * this.trig[(m + 1)]);
      paramArrayOfFloat2[(k + n)] = (paramArrayOfFloat1[(i++)] + paramArrayOfFloat1[(j++)]);
    }
    int i6;
    int i7;
    float f3;
    float f5;
    float f6;
    float f4;
    for (int i1 = 0; i1 < this.log2n - 3; i1++)
    {
      i2 = paramInt1 >>> i1 + 2;
      i3 = 1 << i1 + 3;
      i4 = paramInt2 - 2;
      m = 0;
      for (i6 = 0; i6 < i2 >>> 2; i6++)
      {
        i7 = i4;
        k = i7 - (i2 >> 1);
        f3 = this.trig[m];
        f5 = this.trig[(m + 1)];
        i4 -= 2;
        i2++;
        for (int i8 = 0; i8 < 2 << i1; i8++)
        {
          f6 = paramArrayOfFloat2[i7] - paramArrayOfFloat2[k];
          paramArrayOfFloat2[i7] += paramArrayOfFloat2[k];
          f4 = paramArrayOfFloat2[(++i7)] - paramArrayOfFloat2[(++k)];
          paramArrayOfFloat2[i7] += paramArrayOfFloat2[k];
          paramArrayOfFloat1[k] = (f4 * f3 - f6 * f5);
          paramArrayOfFloat1[(k - 1)] = (f6 * f3 + f4 * f5);
          i7 -= i2;
          k -= i2;
        }
        i2--;
        m += i3;
      }
      float[] arrayOfFloat = paramArrayOfFloat2;
      paramArrayOfFloat2 = paramArrayOfFloat1;
      paramArrayOfFloat1 = arrayOfFloat;
    }
    i1 = paramInt1;
    int i2 = 0;
    int i3 = 0;
    int i4 = paramInt2 - 1;
    for (int i5 = 0; i5 < paramInt4; i5++)
    {
      i6 = this.bitrev[(i2++)];
      i7 = this.bitrev[(i2++)];
      f3 = paramArrayOfFloat2[i6] - paramArrayOfFloat2[(i7 + 1)];
      f4 = paramArrayOfFloat2[(i6 - 1)] + paramArrayOfFloat2[i7];
      f5 = paramArrayOfFloat2[i6] + paramArrayOfFloat2[(i7 + 1)];
      f6 = paramArrayOfFloat2[(i6 - 1)] - paramArrayOfFloat2[i7];
      float f7 = f3 * this.trig[i1];
      float f8 = f4 * this.trig[(i1++)];
      float f9 = f3 * this.trig[i1];
      float f10 = f4 * this.trig[(i1++)];
      paramArrayOfFloat1[(i3++)] = ((f5 + f9 + f8) * 0.5F);
      paramArrayOfFloat1[(i4--)] = ((-f6 + f10 - f7) * 0.5F);
      paramArrayOfFloat1[(i3++)] = ((f6 + f10 - f7) * 0.5F);
      paramArrayOfFloat1[(i4--)] = ((f5 - f9 - f8) * 0.5F);
    }
    return paramArrayOfFloat1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Mdct
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */