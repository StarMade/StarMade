package com.jcraft.jorbis;

class Lpc
{
  Drft fft = new Drft();
  int field_1908;
  int field_1909;
  
  static float lpc_from_data(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt1, int paramInt2)
  {
    float[] arrayOfFloat = new float[paramInt2 + 1];
    int j = paramInt2 + 1;
    float f2;
    while (j-- != 0)
    {
      f2 = 0.0F;
      for (i = j; i < paramInt1; i++) {
        f2 += paramArrayOfFloat1[i] * paramArrayOfFloat1[(i - j)];
      }
      arrayOfFloat[j] = f2;
    }
    float f1 = arrayOfFloat[0];
    for (int i = 0; i < paramInt2; i++)
    {
      f2 = -arrayOfFloat[(i + 1)];
      if (f1 == 0.0F)
      {
        for (int k = 0; k < paramInt2; k++) {
          paramArrayOfFloat2[k] = 0.0F;
        }
        return 0.0F;
      }
      for (j = 0; j < i; j++) {
        f2 -= paramArrayOfFloat2[j] * arrayOfFloat[(i - j)];
      }
      f2 /= f1;
      paramArrayOfFloat2[i] = f2;
      for (j = 0; j < i / 2; j++)
      {
        float f3 = paramArrayOfFloat2[j];
        paramArrayOfFloat2[j] += f2 * paramArrayOfFloat2[(i - 1 - j)];
        paramArrayOfFloat2[(i - 1 - j)] += f2 * f3;
      }
      if (i % 2 != 0) {
        paramArrayOfFloat2[j] += paramArrayOfFloat2[j] * f2;
      }
      f1 = (float)(f1 * (1.0D - f2 * f2));
    }
    return f1;
  }
  
  float lpc_from_curve(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    int i = this.field_1908;
    float[] arrayOfFloat = new float[i + i];
    float f1 = (float)(0.5D / i);
    for (int j = 0; j < i; j++)
    {
      arrayOfFloat[(j * 2)] = (paramArrayOfFloat1[j] * f1);
      arrayOfFloat[(j * 2 + 1)] = 0.0F;
    }
    arrayOfFloat[(i * 2 - 1)] = (paramArrayOfFloat1[(i - 1)] * f1);
    i *= 2;
    this.fft.backward(arrayOfFloat);
    j = 0;
    int k = i / 2;
    while (j < i / 2)
    {
      float f2 = arrayOfFloat[j];
      arrayOfFloat[(j++)] = arrayOfFloat[k];
      arrayOfFloat[(k++)] = f2;
    }
    return lpc_from_data(arrayOfFloat, paramArrayOfFloat2, i, this.field_1909);
  }
  
  void init(int paramInt1, int paramInt2)
  {
    this.field_1908 = paramInt1;
    this.field_1909 = paramInt2;
    this.fft.init(paramInt1 * 2);
  }
  
  void clear()
  {
    this.fft.clear();
  }
  
  static float FAST_HYPOT(float paramFloat1, float paramFloat2)
  {
    return (float)Math.sqrt(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
  }
  
  void lpc_to_curve(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float paramFloat)
  {
    for (int i = 0; i < this.field_1908 * 2; i++) {
      paramArrayOfFloat1[i] = 0.0F;
    }
    if (paramFloat == 0.0F) {
      return;
    }
    for (int j = 0; j < this.field_1909; j++)
    {
      paramArrayOfFloat1[(j * 2 + 1)] = (paramArrayOfFloat2[j] / (4.0F * paramFloat));
      paramArrayOfFloat1[(j * 2 + 2)] = (-paramArrayOfFloat2[j] / (4.0F * paramFloat));
    }
    this.fft.backward(paramArrayOfFloat1);
    int k = this.field_1908 * 2;
    float f1 = (float)(1.0D / paramFloat);
    paramArrayOfFloat1[0] = ((float)(1.0D / (paramArrayOfFloat1[0] * 2.0F + f1)));
    for (int m = 1; m < this.field_1908; m++)
    {
      float f2 = paramArrayOfFloat1[m] + paramArrayOfFloat1[(k - m)];
      float f3 = paramArrayOfFloat1[m] - paramArrayOfFloat1[(k - m)];
      float f4 = f2 + f1;
      paramArrayOfFloat1[m] = ((float)(1.0D / FAST_HYPOT(f4, f3)));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Lpc
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */