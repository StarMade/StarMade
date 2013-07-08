package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

class StaticCodeBook
{
  int dim;
  int entries;
  int[] lengthlist;
  int maptype;
  int q_min;
  int q_delta;
  int q_quant;
  int q_sequencep;
  int[] quantlist;
  EncodeAuxNearestMatch nearest_tree;
  EncodeAuxThreshMatch thresh_tree;
  static final int VQ_FEXP = 10;
  static final int VQ_FMAN = 21;
  static final int VQ_FEXP_BIAS = 768;
  
  StaticCodeBook() {}
  
  StaticCodeBook(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int[] paramArrayOfInt2, Object paramObject1, Object paramObject2)
  {
    this();
    this.dim = paramInt1;
    this.entries = paramInt2;
    this.lengthlist = paramArrayOfInt1;
    this.maptype = paramInt3;
    this.q_min = paramInt4;
    this.q_delta = paramInt5;
    this.q_quant = paramInt6;
    this.q_sequencep = paramInt7;
    this.quantlist = paramArrayOfInt2;
  }
  
  int pack(Buffer paramBuffer)
  {
    int j = 0;
    paramBuffer.write(5653314, 24);
    paramBuffer.write(this.dim, 16);
    paramBuffer.write(this.entries, 24);
    for (int i = 1; i < this.entries; i++) {
      if (this.lengthlist[i] < this.lengthlist[(i - 1)]) {
        break;
      }
    }
    if (i == this.entries) {
      j = 1;
    }
    int k;
    if (j != 0)
    {
      k = 0;
      paramBuffer.write(1, 1);
      paramBuffer.write(this.lengthlist[0] - 1, 5);
      for (i = 1; i < this.entries; i++)
      {
        int m = this.lengthlist[i];
        int n = this.lengthlist[(i - 1)];
        if (m > n) {
          for (int i1 = n; i1 < m; i1++)
          {
            paramBuffer.write(i - k, ilog(this.entries - k));
            k = i;
          }
        }
      }
      paramBuffer.write(i - k, ilog(this.entries - k));
    }
    else
    {
      paramBuffer.write(0, 1);
      for (i = 0; i < this.entries; i++) {
        if (this.lengthlist[i] == 0) {
          break;
        }
      }
      if (i == this.entries)
      {
        paramBuffer.write(0, 1);
        for (i = 0; i < this.entries; i++) {
          paramBuffer.write(this.lengthlist[i] - 1, 5);
        }
      }
      else
      {
        paramBuffer.write(1, 1);
        for (i = 0; i < this.entries; i++) {
          if (this.lengthlist[i] == 0)
          {
            paramBuffer.write(0, 1);
          }
          else
          {
            paramBuffer.write(1, 1);
            paramBuffer.write(this.lengthlist[i] - 1, 5);
          }
        }
      }
    }
    paramBuffer.write(this.maptype, 4);
    switch (this.maptype)
    {
    case 0: 
      break;
    case 1: 
    case 2: 
      if (this.quantlist == null) {
        return -1;
      }
      paramBuffer.write(this.q_min, 32);
      paramBuffer.write(this.q_delta, 32);
      paramBuffer.write(this.q_quant - 1, 4);
      paramBuffer.write(this.q_sequencep, 1);
      k = 0;
      switch (this.maptype)
      {
      case 1: 
        k = maptype1_quantvals();
        break;
      case 2: 
        k = this.entries * this.dim;
      }
      for (i = 0; i < k; i++) {
        paramBuffer.write(Math.abs(this.quantlist[i]), this.q_quant);
      }
      break;
    default: 
      return -1;
    }
    return 0;
  }
  
  int unpack(Buffer paramBuffer)
  {
    if (paramBuffer.read(24) != 5653314)
    {
      clear();
      return -1;
    }
    this.dim = paramBuffer.read(16);
    this.entries = paramBuffer.read(24);
    if (this.entries == -1)
    {
      clear();
      return -1;
    }
    int i;
    int j;
    switch (paramBuffer.read(1))
    {
    case 0: 
      this.lengthlist = new int[this.entries];
      if (paramBuffer.read(1) != 0) {
        for (i = 0; i < this.entries; i++) {
          if (paramBuffer.read(1) != 0)
          {
            j = paramBuffer.read(5);
            if (j == -1)
            {
              clear();
              return -1;
            }
            this.lengthlist[i] = (j + 1);
          }
          else
          {
            this.lengthlist[i] = 0;
          }
        }
      } else {
        for (i = 0; i < this.entries; i++)
        {
          j = paramBuffer.read(5);
          if (j == -1)
          {
            clear();
            return -1;
          }
          this.lengthlist[i] = (j + 1);
        }
      }
      break;
    case 1: 
      j = paramBuffer.read(5) + 1;
      this.lengthlist = new int[this.entries];
      i = 0;
      while (i < this.entries)
      {
        int k = paramBuffer.read(ilog(this.entries - i));
        if (k == -1)
        {
          clear();
          return -1;
        }
        int m = 0;
        while (m < k)
        {
          this.lengthlist[i] = j;
          m++;
          i++;
        }
        j++;
      }
      break;
    default: 
      return -1;
    }
    switch (this.maptype = paramBuffer.read(4))
    {
    case 0: 
      break;
    case 1: 
    case 2: 
      this.q_min = paramBuffer.read(32);
      this.q_delta = paramBuffer.read(32);
      this.q_quant = (paramBuffer.read(4) + 1);
      this.q_sequencep = paramBuffer.read(1);
      j = 0;
      switch (this.maptype)
      {
      case 1: 
        j = maptype1_quantvals();
        break;
      case 2: 
        j = this.entries * this.dim;
      }
      this.quantlist = new int[j];
      for (i = 0; i < j; i++) {
        this.quantlist[i] = paramBuffer.read(this.q_quant);
      }
      if (this.quantlist[(j - 1)] == -1)
      {
        clear();
        return -1;
      }
      break;
    default: 
      clear();
      return -1;
    }
    return 0;
  }
  
  private int maptype1_quantvals()
  {
    int i = (int)Math.floor(Math.pow(this.entries, 1.0D / this.dim));
    for (;;)
    {
      int j = 1;
      int k = 1;
      for (int m = 0; m < this.dim; m++)
      {
        j *= i;
        k *= (i + 1);
      }
      if ((j <= this.entries) && (k > this.entries)) {
        return i;
      }
      if (j > this.entries) {
        i--;
      } else {
        i++;
      }
    }
  }
  
  void clear() {}
  
  float[] unquantize()
  {
    if ((this.maptype == 1) || (this.maptype == 2))
    {
      float f1 = float32_unpack(this.q_min);
      float f2 = float32_unpack(this.q_delta);
      float[] arrayOfFloat = new float[this.entries * this.dim];
      int n;
      switch (this.maptype)
      {
      case 1: 
        int i = maptype1_quantvals();
        for (int j = 0; j < this.entries; j++)
        {
          float f3 = 0.0F;
          int m = 1;
          for (n = 0; n < this.dim; n++)
          {
            int i1 = j / m % i;
            float f6 = this.quantlist[i1];
            f6 = Math.abs(f6) * f2 + f1 + f3;
            if (this.q_sequencep != 0) {
              f3 = f6;
            }
            arrayOfFloat[(j * this.dim + n)] = f6;
            m *= i;
          }
        }
        break;
      case 2: 
        for (int k = 0; k < this.entries; k++)
        {
          float f4 = 0.0F;
          for (n = 0; n < this.dim; n++)
          {
            float f5 = this.quantlist[(k * this.dim + n)];
            f5 = Math.abs(f5) * f2 + f1 + f4;
            if (this.q_sequencep != 0) {
              f4 = f5;
            }
            arrayOfFloat[(k * this.dim + n)] = f5;
          }
        }
      }
      return arrayOfFloat;
    }
    return null;
  }
  
  private static int ilog(int paramInt)
  {
    int i = 0;
    while (paramInt != 0)
    {
      i++;
      paramInt >>>= 1;
    }
    return i;
  }
  
  static long float32_pack(float paramFloat)
  {
    int i = 0;
    if (paramFloat < 0.0F)
    {
      i = -2147483648;
      paramFloat = -paramFloat;
    }
    int j = (int)Math.floor(Math.log(paramFloat) / Math.log(2.0D));
    int k = (int)Math.rint(Math.pow(paramFloat, 20 - j));
    j = j + 768 << 21;
    return i | j | k;
  }
  
  static float float32_unpack(int paramInt)
  {
    float f1 = paramInt & 0x1FFFFF;
    float f2 = paramInt & 0x80000000;
    float f3 = (paramInt & 0x7FE00000) >>> 21;
    if ((paramInt & 0x80000000) != 0) {
      f1 = -f1;
    }
    return ldexp(f1, (int)f3 - 20 - 768);
  }
  
  static float ldexp(float paramFloat, int paramInt)
  {
    return (float)(paramFloat * Math.pow(2.0D, paramInt));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.StaticCodeBook
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */