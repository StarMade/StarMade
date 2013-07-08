package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

class CodeBook
{
  int dim;
  int entries;
  StaticCodeBook field_1691 = new StaticCodeBook();
  float[] valuelist;
  int[] codelist;
  DecodeAux decode_tree;
  private int[] field_1692 = new int[15];
  
  int encode(int paramInt, Buffer paramBuffer)
  {
    paramBuffer.write(this.codelist[paramInt], this.field_1691.lengthlist[paramInt]);
    return this.field_1691.lengthlist[paramInt];
  }
  
  int errorv(float[] paramArrayOfFloat)
  {
    int i = best(paramArrayOfFloat, 1);
    for (int j = 0; j < this.dim; j++) {
      paramArrayOfFloat[j] = this.valuelist[(i * this.dim + j)];
    }
    return i;
  }
  
  int encodev(int paramInt, float[] paramArrayOfFloat, Buffer paramBuffer)
  {
    for (int i = 0; i < this.dim; i++) {
      paramArrayOfFloat[i] = this.valuelist[(paramInt * this.dim + i)];
    }
    return encode(paramInt, paramBuffer);
  }
  
  int encodevs(float[] paramArrayOfFloat, Buffer paramBuffer, int paramInt1, int paramInt2)
  {
    int i = besterror(paramArrayOfFloat, paramInt1, paramInt2);
    return encode(i, paramBuffer);
  }
  
  synchronized int decodevs_add(float[] paramArrayOfFloat, int paramInt1, Buffer paramBuffer, int paramInt2)
  {
    int i = paramInt2 / this.dim;
    if (this.field_1692.length < i) {
      this.field_1692 = new int[i];
    }
    for (int k = 0; k < i; k++)
    {
      int j = decode(paramBuffer);
      if (j == -1) {
        return -1;
      }
      this.field_1692[k] = (j * this.dim);
    }
    k = 0;
    int n = 0;
    while (k < this.dim)
    {
      for (int m = 0; m < i; m++) {
        paramArrayOfFloat[(paramInt1 + n + m)] += this.valuelist[(this.field_1692[m] + k)];
      }
      k++;
      n += i;
    }
    return 0;
  }
  
  int decodev_add(float[] paramArrayOfFloat, int paramInt1, Buffer paramBuffer, int paramInt2)
  {
    int i;
    int k;
    int m;
    int j;
    if (this.dim > 8)
    {
      i = 0;
      while (i < paramInt2)
      {
        k = decode(paramBuffer);
        if (k == -1) {
          return -1;
        }
        m = k * this.dim;
        j = 0;
        while (j < this.dim) {
          paramArrayOfFloat[(paramInt1 + i++)] += this.valuelist[(m + j++)];
        }
      }
    }
    else
    {
      i = 0;
      while (i < paramInt2)
      {
        k = decode(paramBuffer);
        if (k == -1) {
          return -1;
        }
        m = k * this.dim;
        j = 0;
        switch (this.dim)
        {
        case 8: 
          paramArrayOfFloat[(paramInt1 + i++)] += this.valuelist[(m + j++)];
        case 7: 
          paramArrayOfFloat[(paramInt1 + i++)] += this.valuelist[(m + j++)];
        case 6: 
          paramArrayOfFloat[(paramInt1 + i++)] += this.valuelist[(m + j++)];
        case 5: 
          paramArrayOfFloat[(paramInt1 + i++)] += this.valuelist[(m + j++)];
        case 4: 
          paramArrayOfFloat[(paramInt1 + i++)] += this.valuelist[(m + j++)];
        case 3: 
          paramArrayOfFloat[(paramInt1 + i++)] += this.valuelist[(m + j++)];
        case 2: 
          paramArrayOfFloat[(paramInt1 + i++)] += this.valuelist[(m + j++)];
        case 1: 
          paramArrayOfFloat[(paramInt1 + i++)] += this.valuelist[(m + j++)];
        }
      }
    }
    return 0;
  }
  
  int decodev_set(float[] paramArrayOfFloat, int paramInt1, Buffer paramBuffer, int paramInt2)
  {
    int i = 0;
    while (i < paramInt2)
    {
      int k = decode(paramBuffer);
      if (k == -1) {
        return -1;
      }
      int m = k * this.dim;
      int j = 0;
      while (j < this.dim) {
        paramArrayOfFloat[(paramInt1 + i++)] = this.valuelist[(m + j++)];
      }
    }
    return 0;
  }
  
  int decodevv_add(float[][] paramArrayOfFloat, int paramInt1, int paramInt2, Buffer paramBuffer, int paramInt3)
  {
    int m = 0;
    int i = paramInt1 / paramInt2;
    while (i < (paramInt1 + paramInt3) / paramInt2)
    {
      int k = decode(paramBuffer);
      if (k == -1) {
        return -1;
      }
      int n = k * this.dim;
      for (int j = 0; j < this.dim; j++)
      {
        paramArrayOfFloat[(m++)][i] += this.valuelist[(n + j)];
        if (m == paramInt2)
        {
          m = 0;
          i++;
        }
      }
    }
    return 0;
  }
  
  int decode(Buffer paramBuffer)
  {
    int i = 0;
    DecodeAux localDecodeAux = this.decode_tree;
    int j = paramBuffer.look(localDecodeAux.tabn);
    if (j >= 0)
    {
      i = localDecodeAux.tab[j];
      paramBuffer.adv(localDecodeAux.tabl[j]);
      if (i <= 0) {
        return -i;
      }
    }
    do
    {
      switch (paramBuffer.read1())
      {
      case 0: 
        i = localDecodeAux.ptr0[i];
        break;
      case 1: 
        i = localDecodeAux.ptr1[i];
        break;
      case -1: 
      default: 
        return -1;
      }
    } while (i > 0);
    return -i;
  }
  
  int decodevs(float[] paramArrayOfFloat, int paramInt1, Buffer paramBuffer, int paramInt2, int paramInt3)
  {
    int i = decode(paramBuffer);
    if (i == -1) {
      return -1;
    }
    switch (paramInt3)
    {
    case -1: 
      int j = 0;
      int k = 0;
      while (j < this.dim)
      {
        paramArrayOfFloat[(paramInt1 + k)] = this.valuelist[(i * this.dim + j)];
        j++;
        k += paramInt2;
      }
      break;
    case 0: 
      int m = 0;
      int n = 0;
      while (m < this.dim)
      {
        paramArrayOfFloat[(paramInt1 + n)] += this.valuelist[(i * this.dim + m)];
        m++;
        n += paramInt2;
      }
      break;
    case 1: 
      int i1 = 0;
      int i2 = 0;
      for (;;)
      {
        paramArrayOfFloat[(paramInt1 + i2)] *= this.valuelist[(i * this.dim + i1)];
        i1++;
        i2 += paramInt2;
        if (i1 >= this.dim) {
          break;
        }
      }
    }
    return i;
  }
  
  int best(float[] paramArrayOfFloat, int paramInt)
  {
    EncodeAuxNearestMatch localEncodeAuxNearestMatch = this.field_1691.nearest_tree;
    EncodeAuxThreshMatch localEncodeAuxThreshMatch = this.field_1691.thresh_tree;
    int i = 0;
    int m;
    if (localEncodeAuxThreshMatch != null)
    {
      int j = 0;
      m = 0;
      n = paramInt * (this.dim - 1);
      while (m < this.dim)
      {
        for (i1 = 0; i1 < localEncodeAuxThreshMatch.threshvals - 1; i1++) {
          if (paramArrayOfFloat[n] < localEncodeAuxThreshMatch.quantthresh[i1]) {
            break;
          }
        }
        j = j * localEncodeAuxThreshMatch.quantvals + localEncodeAuxThreshMatch.quantmap[i1];
        m++;
        n -= paramInt;
      }
      if (this.field_1691.lengthlist[j] > 0) {
        return j;
      }
    }
    if (localEncodeAuxNearestMatch != null)
    {
      for (;;)
      {
        float f1 = 0.0F;
        m = localEncodeAuxNearestMatch.field_1869[i];
        n = localEncodeAuxNearestMatch.field_1870[i];
        i1 = 0;
        int i2 = 0;
        while (i1 < this.dim)
        {
          f1 = (float)(f1 + (this.valuelist[(m + i1)] - this.valuelist[(n + i1)]) * (paramArrayOfFloat[i2] - (this.valuelist[(m + i1)] + this.valuelist[(n + i1)]) * 0.5D));
          i1++;
          i2 += paramInt;
        }
        if (f1 > 0.0D) {
          i = -localEncodeAuxNearestMatch.ptr0[i];
        } else {
          i = -localEncodeAuxNearestMatch.ptr1[i];
        }
        if (i <= 0) {
          break;
        }
      }
      return -i;
    }
    int k = -1;
    float f2 = 0.0F;
    int n = 0;
    for (int i1 = 0; i1 < this.entries; i1++)
    {
      if (this.field_1691.lengthlist[i1] > 0)
      {
        float f3 = dist(this.dim, this.valuelist, n, paramArrayOfFloat, paramInt);
        if ((k == -1) || (f3 < f2))
        {
          f2 = f3;
          k = i1;
        }
      }
      n += this.dim;
    }
    return k;
  }
  
  int besterror(float[] paramArrayOfFloat, int paramInt1, int paramInt2)
  {
    int i = best(paramArrayOfFloat, paramInt1);
    switch (paramInt2)
    {
    case 0: 
      int j = 0;
      int k = 0;
      while (j < this.dim)
      {
        paramArrayOfFloat[k] -= this.valuelist[(i * this.dim + j)];
        j++;
        k += paramInt1;
      }
      break;
    case 1: 
      int m = 0;
      int n = 0;
      while (m < this.dim)
      {
        float f = this.valuelist[(i * this.dim + m)];
        if (f == 0.0F) {
          paramArrayOfFloat[n] = 0.0F;
        } else {
          paramArrayOfFloat[n] /= f;
        }
        m++;
        n += paramInt1;
      }
    }
    return i;
  }
  
  void clear() {}
  
  private static float dist(int paramInt1, float[] paramArrayOfFloat1, int paramInt2, float[] paramArrayOfFloat2, int paramInt3)
  {
    float f1 = 0.0F;
    for (int i = 0; i < paramInt1; i++)
    {
      float f2 = paramArrayOfFloat1[(paramInt2 + i)] - paramArrayOfFloat2[(i * paramInt3)];
      f1 += f2 * f2;
    }
    return f1;
  }
  
  int init_decode(StaticCodeBook paramStaticCodeBook)
  {
    this.field_1691 = paramStaticCodeBook;
    this.entries = paramStaticCodeBook.entries;
    this.dim = paramStaticCodeBook.dim;
    this.valuelist = paramStaticCodeBook.unquantize();
    this.decode_tree = make_decode_tree();
    if (this.decode_tree == null)
    {
      clear();
      return -1;
    }
    return 0;
  }
  
  static int[] make_words(int[] paramArrayOfInt, int paramInt)
  {
    int[] arrayOfInt1 = new int[33];
    int[] arrayOfInt2 = new int[paramInt];
    int k;
    int m;
    for (int i = 0; i < paramInt; i++)
    {
      j = paramArrayOfInt[i];
      if (j > 0)
      {
        k = arrayOfInt1[j];
        if ((j < 32) && (k >>> j != 0)) {
          return null;
        }
        arrayOfInt2[i] = k;
        for (m = j; m > 0; m--)
        {
          if ((arrayOfInt1[m] & 0x1) != 0)
          {
            if (m == 1)
            {
              arrayOfInt1[1] += 1;
              break;
            }
            arrayOfInt1[m] = (arrayOfInt1[(m - 1)] << 1);
            break;
          }
          arrayOfInt1[m] += 1;
        }
        for (m = j + 1; m < 33; m++)
        {
          if (arrayOfInt1[m] >>> 1 != k) {
            break;
          }
          k = arrayOfInt1[m];
          arrayOfInt1[m] = (arrayOfInt1[(m - 1)] << 1);
        }
      }
    }
    for (int j = 0; j < paramInt; j++)
    {
      k = 0;
      for (m = 0; m < paramArrayOfInt[j]; m++)
      {
        k <<= 1;
        k |= arrayOfInt2[j] >>> m & 0x1;
      }
      arrayOfInt2[j] = k;
    }
    return arrayOfInt2;
  }
  
  DecodeAux make_decode_tree()
  {
    int i = 0;
    DecodeAux localDecodeAux = new DecodeAux();
    int[] arrayOfInt1 = localDecodeAux.ptr0 = new int[this.entries * 2];
    int[] arrayOfInt2 = localDecodeAux.ptr1 = new int[this.entries * 2];
    int[] arrayOfInt3 = make_words(this.field_1691.lengthlist, this.field_1691.entries);
    if (arrayOfInt3 == null) {
      return null;
    }
    localDecodeAux.aux = (this.entries * 2);
    int n;
    for (int j = 0; j < this.entries; j++) {
      if (this.field_1691.lengthlist[j] > 0)
      {
        k = 0;
        for (m = 0; m < this.field_1691.lengthlist[j] - 1; m++)
        {
          n = arrayOfInt3[j] >>> m & 0x1;
          if (n == 0)
          {
            if (arrayOfInt1[k] == 0) {
              arrayOfInt1[k] = (++i);
            }
            k = arrayOfInt1[k];
          }
          else
          {
            if (arrayOfInt2[k] == 0) {
              arrayOfInt2[k] = (++i);
            }
            k = arrayOfInt2[k];
          }
        }
        if ((arrayOfInt3[j] >>> m & 0x1) == 0) {
          arrayOfInt1[k] = (-j);
        } else {
          arrayOfInt2[k] = (-j);
        }
      }
    }
    localDecodeAux.tabn = (ilog(this.entries) - 4);
    if (localDecodeAux.tabn < 5) {
      localDecodeAux.tabn = 5;
    }
    int k = 1 << localDecodeAux.tabn;
    localDecodeAux.tab = new int[k];
    localDecodeAux.tabl = new int[k];
    for (int m = 0; m < k; m++)
    {
      n = 0;
      int i1 = 0;
      for (i1 = 0; (i1 < localDecodeAux.tabn) && ((n > 0) || (i1 == 0)); i1++) {
        if ((m & 1 << i1) != 0) {
          n = arrayOfInt2[n];
        } else {
          n = arrayOfInt1[n];
        }
      }
      localDecodeAux.tab[m] = n;
      localDecodeAux.tabl[m] = i1;
    }
    return localDecodeAux;
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
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.CodeBook
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */