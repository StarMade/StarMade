package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

class Floor0
  extends FuncFloor
{
  float[] lsp = null;
  
  void pack(Object paramObject, Buffer paramBuffer)
  {
    InfoFloor0 localInfoFloor0 = (InfoFloor0)paramObject;
    paramBuffer.write(localInfoFloor0.order, 8);
    paramBuffer.write(localInfoFloor0.rate, 16);
    paramBuffer.write(localInfoFloor0.barkmap, 16);
    paramBuffer.write(localInfoFloor0.ampbits, 6);
    paramBuffer.write(localInfoFloor0.ampdB, 8);
    paramBuffer.write(localInfoFloor0.numbooks - 1, 4);
    for (int i = 0; i < localInfoFloor0.numbooks; i++) {
      paramBuffer.write(localInfoFloor0.books[i], 8);
    }
  }
  
  Object unpack(Info paramInfo, Buffer paramBuffer)
  {
    InfoFloor0 localInfoFloor0 = new InfoFloor0();
    localInfoFloor0.order = paramBuffer.read(8);
    localInfoFloor0.rate = paramBuffer.read(16);
    localInfoFloor0.barkmap = paramBuffer.read(16);
    localInfoFloor0.ampbits = paramBuffer.read(6);
    localInfoFloor0.ampdB = paramBuffer.read(8);
    localInfoFloor0.numbooks = (paramBuffer.read(4) + 1);
    if ((localInfoFloor0.order < 1) || (localInfoFloor0.rate < 1) || (localInfoFloor0.barkmap < 1) || (localInfoFloor0.numbooks < 1)) {
      return null;
    }
    for (int i = 0; i < localInfoFloor0.numbooks; i++)
    {
      localInfoFloor0.books[i] = paramBuffer.read(8);
      if ((localInfoFloor0.books[i] < 0) || (localInfoFloor0.books[i] >= paramInfo.books)) {
        return null;
      }
    }
    return localInfoFloor0;
  }
  
  Object look(DspState paramDspState, InfoMode paramInfoMode, Object paramObject)
  {
    Info localInfo = paramDspState.field_2242;
    InfoFloor0 localInfoFloor0 = (InfoFloor0)paramObject;
    LookFloor0 localLookFloor0 = new LookFloor0();
    localLookFloor0.field_1896 = localInfoFloor0.order;
    localLookFloor0.field_1894 = (localInfo.blocksizes[paramInfoMode.blockflag] / 2);
    localLookFloor0.field_1895 = localInfoFloor0.barkmap;
    localLookFloor0.field_1897 = localInfoFloor0;
    localLookFloor0.lpclook.init(localLookFloor0.field_1895, localLookFloor0.field_1896);
    float f = localLookFloor0.field_1895 / toBARK((float)(localInfoFloor0.rate / 2.0D));
    localLookFloor0.linearmap = new int[localLookFloor0.field_1894];
    for (int i = 0; i < localLookFloor0.field_1894; i++)
    {
      int j = (int)Math.floor(toBARK((float)(localInfoFloor0.rate / 2.0D / localLookFloor0.field_1894 * i)) * f);
      if (j >= localLookFloor0.field_1895) {
        j = localLookFloor0.field_1895;
      }
      localLookFloor0.linearmap[i] = j;
    }
    return localLookFloor0;
  }
  
  static float toBARK(float paramFloat)
  {
    return (float)(13.1D * Math.atan(0.00074D * paramFloat) + 2.24D * Math.atan(paramFloat * paramFloat * 1.85E-008D) + 0.0001D * paramFloat);
  }
  
  Object state(Object paramObject)
  {
    EchstateFloor0 localEchstateFloor0 = new EchstateFloor0();
    InfoFloor0 localInfoFloor0 = (InfoFloor0)paramObject;
    localEchstateFloor0.codewords = new int[localInfoFloor0.order];
    localEchstateFloor0.curve = new float[localInfoFloor0.barkmap];
    localEchstateFloor0.frameno = -1L;
    return localEchstateFloor0;
  }
  
  void free_info(Object paramObject) {}
  
  void free_look(Object paramObject) {}
  
  void free_state(Object paramObject) {}
  
  int forward(Block paramBlock, Object paramObject1, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, Object paramObject2)
  {
    return 0;
  }
  
  int inverse(Block paramBlock, Object paramObject, float[] paramArrayOfFloat)
  {
    LookFloor0 localLookFloor0 = (LookFloor0)paramObject;
    InfoFloor0 localInfoFloor0 = localLookFloor0.field_1897;
    int i = paramBlock.opb.read(localInfoFloor0.ampbits);
    if (i > 0)
    {
      int j = (1 << localInfoFloor0.ampbits) - 1;
      float f1 = i / j * localInfoFloor0.ampdB;
      int k = paramBlock.opb.read(ilog(localInfoFloor0.numbooks));
      if ((k != -1) && (k < localInfoFloor0.numbooks)) {
        synchronized (this)
        {
          if ((this.lsp == null) || (this.lsp.length < localLookFloor0.field_1896)) {
            this.lsp = new float[localLookFloor0.field_1896];
          } else {
            for (int m = 0; m < localLookFloor0.field_1896; m++) {
              this.lsp[m] = 0.0F;
            }
          }
          CodeBook localCodeBook = paramBlock.field_2098.fullbooks[localInfoFloor0.books[k]];
          float f2 = 0.0F;
          for (int n = 0; n < localLookFloor0.field_1896; n++) {
            paramArrayOfFloat[n] = 0.0F;
          }
          int i1 = 0;
          while (i1 < localLookFloor0.field_1896)
          {
            if (localCodeBook.decodevs(this.lsp, i1, paramBlock.opb, 1, -1) == -1)
            {
              for (i2 = 0; i2 < localLookFloor0.field_1894; i2++) {
                paramArrayOfFloat[i2] = 0.0F;
              }
              i3 = 0;
              return i3;
            }
            i1 += localCodeBook.dim;
          }
          int i2 = 0;
          while (i2 < localLookFloor0.field_1896)
          {
            i3 = 0;
            while (i3 < localCodeBook.dim)
            {
              this.lsp[i2] += f2;
              i3++;
              i2++;
            }
            f2 = this.lsp[(i2 - 1)];
          }
          Lsp.lsp_to_curve(paramArrayOfFloat, localLookFloor0.linearmap, localLookFloor0.field_1894, localLookFloor0.field_1895, this.lsp, localLookFloor0.field_1896, f1, localInfoFloor0.ampdB);
          int i3 = 1;
          return i3;
        }
      }
    }
    return 0;
  }
  
  Object inverse1(Block paramBlock, Object paramObject1, Object paramObject2)
  {
    LookFloor0 localLookFloor0 = (LookFloor0)paramObject1;
    InfoFloor0 localInfoFloor0 = localLookFloor0.field_1897;
    float[] arrayOfFloat = null;
    if ((paramObject2 instanceof float[])) {
      arrayOfFloat = (float[])paramObject2;
    }
    int i = paramBlock.opb.read(localInfoFloor0.ampbits);
    if (i > 0)
    {
      int j = (1 << localInfoFloor0.ampbits) - 1;
      float f1 = i / j * localInfoFloor0.ampdB;
      int k = paramBlock.opb.read(ilog(localInfoFloor0.numbooks));
      if ((k != -1) && (k < localInfoFloor0.numbooks))
      {
        CodeBook localCodeBook = paramBlock.field_2098.fullbooks[localInfoFloor0.books[k]];
        float f2 = 0.0F;
        if ((arrayOfFloat == null) || (arrayOfFloat.length < localLookFloor0.field_1896 + 1)) {
          arrayOfFloat = new float[localLookFloor0.field_1896 + 1];
        } else {
          for (m = 0; m < arrayOfFloat.length; m++) {
            arrayOfFloat[m] = 0.0F;
          }
        }
        int m = 0;
        while (m < localLookFloor0.field_1896)
        {
          if (localCodeBook.decodev_set(arrayOfFloat, m, paramBlock.opb, localCodeBook.dim) == -1) {
            return null;
          }
          m += localCodeBook.dim;
        }
        int n = 0;
        while (n < localLookFloor0.field_1896)
        {
          int i1 = 0;
          while (i1 < localCodeBook.dim)
          {
            arrayOfFloat[n] += f2;
            i1++;
            n++;
          }
          f2 = arrayOfFloat[(n - 1)];
        }
        arrayOfFloat[localLookFloor0.field_1896] = f1;
        return arrayOfFloat;
      }
    }
    return null;
  }
  
  int inverse2(Block paramBlock, Object paramObject1, Object paramObject2, float[] paramArrayOfFloat)
  {
    LookFloor0 localLookFloor0 = (LookFloor0)paramObject1;
    InfoFloor0 localInfoFloor0 = localLookFloor0.field_1897;
    if (paramObject2 != null)
    {
      float[] arrayOfFloat = (float[])paramObject2;
      float f = arrayOfFloat[localLookFloor0.field_1896];
      Lsp.lsp_to_curve(paramArrayOfFloat, localLookFloor0.linearmap, localLookFloor0.field_1894, localLookFloor0.field_1895, arrayOfFloat, localLookFloor0.field_1896, f, localInfoFloor0.ampdB);
      return 1;
    }
    for (int i = 0; i < localLookFloor0.field_1894; i++) {
      paramArrayOfFloat[i] = 0.0F;
    }
    return 0;
  }
  
  static float fromdB(float paramFloat)
  {
    return (float)Math.exp(paramFloat * 0.11512925D);
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
  
  static void lsp_to_lpc(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, int paramInt)
  {
    int k = paramInt / 2;
    float[] arrayOfFloat1 = new float[k];
    float[] arrayOfFloat2 = new float[k];
    float[] arrayOfFloat3 = new float[k + 1];
    float[] arrayOfFloat4 = new float[k + 1];
    float[] arrayOfFloat5 = new float[k];
    float[] arrayOfFloat6 = new float[k];
    for (int i = 0; i < k; i++)
    {
      arrayOfFloat1[i] = ((float)(-2.0D * Math.cos(paramArrayOfFloat1[(i * 2)])));
      arrayOfFloat2[i] = ((float)(-2.0D * Math.cos(paramArrayOfFloat1[(i * 2 + 1)])));
    }
    for (int j = 0; j < k; j++)
    {
      arrayOfFloat3[j] = 0.0F;
      arrayOfFloat4[j] = 1.0F;
      arrayOfFloat5[j] = 0.0F;
      arrayOfFloat6[j] = 1.0F;
    }
    arrayOfFloat4[j] = 1.0F;
    arrayOfFloat3[j] = 1.0F;
    for (i = 1; i < paramInt + 1; i++)
    {
      float f2;
      float f1 = f2 = 0.0F;
      for (j = 0; j < k; j++)
      {
        float f3 = arrayOfFloat1[j] * arrayOfFloat4[j] + arrayOfFloat3[j];
        arrayOfFloat3[j] = arrayOfFloat4[j];
        arrayOfFloat4[j] = f1;
        f1 += f3;
        f3 = arrayOfFloat2[j] * arrayOfFloat6[j] + arrayOfFloat5[j];
        arrayOfFloat5[j] = arrayOfFloat6[j];
        arrayOfFloat6[j] = f2;
        f2 += f3;
      }
      paramArrayOfFloat2[(i - 1)] = ((f1 + arrayOfFloat4[j] + f2 - arrayOfFloat3[j]) / 2.0F);
      arrayOfFloat4[j] = f1;
      arrayOfFloat3[j] = f2;
    }
  }
  
  static void lpc_to_curve(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float paramFloat, LookFloor0 paramLookFloor0, String paramString, int paramInt)
  {
    float[] arrayOfFloat = new float[Math.max(paramLookFloor0.field_1895 * 2, paramLookFloor0.field_1896 * 2 + 2)];
    if (paramFloat == 0.0F)
    {
      for (i = 0; i < paramLookFloor0.field_1894; i++) {
        paramArrayOfFloat1[i] = 0.0F;
      }
      return;
    }
    paramLookFloor0.lpclook.lpc_to_curve(arrayOfFloat, paramArrayOfFloat2, paramFloat);
    for (int i = 0; i < paramLookFloor0.field_1894; i++) {
      paramArrayOfFloat1[i] = arrayOfFloat[paramLookFloor0.linearmap[i]];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Floor0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */