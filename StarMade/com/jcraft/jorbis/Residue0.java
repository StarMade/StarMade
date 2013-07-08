package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;
import java.io.PrintStream;

class Residue0
  extends FuncResidue
{
  static int[][][] partword = new int[2][][];
  
  void pack(Object paramObject, Buffer paramBuffer)
  {
    InfoResidue0 localInfoResidue0 = (InfoResidue0)paramObject;
    int i = 0;
    paramBuffer.write(localInfoResidue0.begin, 24);
    paramBuffer.write(localInfoResidue0.end, 24);
    paramBuffer.write(localInfoResidue0.grouping - 1, 24);
    paramBuffer.write(localInfoResidue0.partitions - 1, 6);
    paramBuffer.write(localInfoResidue0.groupbook, 8);
    for (int j = 0; j < localInfoResidue0.partitions; j++)
    {
      if (ilog(localInfoResidue0.secondstages[j]) > 3)
      {
        paramBuffer.write(localInfoResidue0.secondstages[j], 3);
        paramBuffer.write(1, 1);
        paramBuffer.write(localInfoResidue0.secondstages[j] >>> 3, 5);
      }
      else
      {
        paramBuffer.write(localInfoResidue0.secondstages[j], 4);
      }
      i += icount(localInfoResidue0.secondstages[j]);
    }
    for (int k = 0; k < i; k++) {
      paramBuffer.write(localInfoResidue0.booklist[k], 8);
    }
  }
  
  Object unpack(Info paramInfo, Buffer paramBuffer)
  {
    int i = 0;
    InfoResidue0 localInfoResidue0 = new InfoResidue0();
    localInfoResidue0.begin = paramBuffer.read(24);
    localInfoResidue0.end = paramBuffer.read(24);
    localInfoResidue0.grouping = (paramBuffer.read(24) + 1);
    localInfoResidue0.partitions = (paramBuffer.read(6) + 1);
    localInfoResidue0.groupbook = paramBuffer.read(8);
    for (int j = 0; j < localInfoResidue0.partitions; j++)
    {
      k = paramBuffer.read(3);
      if (paramBuffer.read(1) != 0) {
        k |= paramBuffer.read(5) << 3;
      }
      localInfoResidue0.secondstages[j] = k;
      i += icount(k);
    }
    for (int k = 0; k < i; k++) {
      localInfoResidue0.booklist[k] = paramBuffer.read(8);
    }
    if (localInfoResidue0.groupbook >= paramInfo.books)
    {
      free_info(localInfoResidue0);
      return null;
    }
    for (int m = 0; m < i; m++) {
      if (localInfoResidue0.booklist[m] >= paramInfo.books)
      {
        free_info(localInfoResidue0);
        return null;
      }
    }
    return localInfoResidue0;
  }
  
  Object look(DspState paramDspState, InfoMode paramInfoMode, Object paramObject)
  {
    InfoResidue0 localInfoResidue0 = (InfoResidue0)paramObject;
    LookResidue0 localLookResidue0 = new LookResidue0();
    int i = 0;
    int k = 0;
    localLookResidue0.info = localInfoResidue0;
    localLookResidue0.map = paramInfoMode.mapping;
    localLookResidue0.parts = localInfoResidue0.partitions;
    localLookResidue0.fullbooks = paramDspState.fullbooks;
    localLookResidue0.phrasebook = paramDspState.fullbooks[localInfoResidue0.groupbook];
    int j = localLookResidue0.phrasebook.dim;
    localLookResidue0.partbooks = new int[localLookResidue0.parts][];
    int i1;
    for (int m = 0; m < localLookResidue0.parts; m++)
    {
      n = ilog(localInfoResidue0.secondstages[m]);
      if (n != 0)
      {
        if (n > k) {
          k = n;
        }
        localLookResidue0.partbooks[m] = new int[n];
        for (i1 = 0; i1 < n; i1++) {
          if ((localInfoResidue0.secondstages[m] & 1 << i1) != 0) {
            localLookResidue0.partbooks[m][i1] = localInfoResidue0.booklist[(i++)];
          }
        }
      }
    }
    localLookResidue0.partvals = ((int)Math.rint(Math.pow(localLookResidue0.parts, j)));
    localLookResidue0.stages = k;
    localLookResidue0.decodemap = new int[localLookResidue0.partvals][];
    for (int n = 0; n < localLookResidue0.partvals; n++)
    {
      i1 = n;
      int i2 = localLookResidue0.partvals / localLookResidue0.parts;
      localLookResidue0.decodemap[n] = new int[j];
      for (int i3 = 0; i3 < j; i3++)
      {
        int i4 = i1 / i2;
        i1 -= i4 * i2;
        i2 /= localLookResidue0.parts;
        localLookResidue0.decodemap[n][i3] = i4;
      }
    }
    return localLookResidue0;
  }
  
  void free_info(Object paramObject) {}
  
  void free_look(Object paramObject) {}
  
  int forward(Block paramBlock, Object paramObject, float[][] paramArrayOfFloat, int paramInt)
  {
    System.err.println("Residue0.forward: not implemented");
    return 0;
  }
  
  static synchronized int _01inverse(Block paramBlock, Object paramObject, float[][] paramArrayOfFloat, int paramInt1, int paramInt2)
  {
    LookResidue0 localLookResidue0 = (LookResidue0)paramObject;
    InfoResidue0 localInfoResidue0 = localLookResidue0.info;
    int i1 = localInfoResidue0.grouping;
    int i2 = localLookResidue0.phrasebook.dim;
    int i3 = localInfoResidue0.end - localInfoResidue0.begin;
    int i4 = i3 / i1;
    int i5 = (i4 + i2 - 1) / i2;
    int j;
    if (partword.length < paramInt1)
    {
      partword = new int[paramInt1][][];
      for (j = 0; j < paramInt1; j++) {
        partword[j] = new int[i5][];
      }
    }
    else
    {
      for (j = 0; j < paramInt1; j++) {
        if ((partword[j] == null) || (partword[j].length < i5)) {
          partword[j] = new int[i5][];
        }
      }
    }
    for (int n = 0; n < localLookResidue0.stages; n++)
    {
      int i = 0;
      for (int m = 0; i < i4; m++)
      {
        int i6;
        if (n == 0) {
          for (j = 0; j < paramInt1; j++)
          {
            i6 = localLookResidue0.phrasebook.decode(paramBlock.opb);
            if (i6 == -1) {
              return 0;
            }
            partword[j][m] = localLookResidue0.decodemap[i6];
            if (partword[j][m] == null) {
              return 0;
            }
          }
        }
        int k = 0;
        while ((k < i2) && (i < i4))
        {
          for (j = 0; j < paramInt1; j++)
          {
            i6 = localInfoResidue0.begin + i * i1;
            if ((localInfoResidue0.secondstages[partword[j][m][k]] & 1 << n) != 0)
            {
              CodeBook localCodeBook = localLookResidue0.fullbooks[localLookResidue0.partbooks[partword[j][m][k]][n]];
              if (localCodeBook != null) {
                if (paramInt2 == 0)
                {
                  if (localCodeBook.decodevs_add(paramArrayOfFloat[j], i6, paramBlock.opb, i1) == -1) {
                    return 0;
                  }
                }
                else if ((paramInt2 == 1) && (localCodeBook.decodev_add(paramArrayOfFloat[j], i6, paramBlock.opb, i1) == -1)) {
                  return 0;
                }
              }
            }
          }
          k++;
          i++;
        }
      }
    }
    return 0;
  }
  
  static int _2inverse(Block paramBlock, Object paramObject, float[][] paramArrayOfFloat, int paramInt)
  {
    LookResidue0 localLookResidue0 = (LookResidue0)paramObject;
    InfoResidue0 localInfoResidue0 = localLookResidue0.info;
    int n = localInfoResidue0.grouping;
    int i1 = localLookResidue0.phrasebook.dim;
    int i2 = localInfoResidue0.end - localInfoResidue0.begin;
    int i3 = i2 / n;
    int i4 = (i3 + i1 - 1) / i1;
    int[][] arrayOfInt = new int[i4][];
    for (int m = 0; m < localLookResidue0.stages; m++)
    {
      int i = 0;
      for (int k = 0; i < i3; k++)
      {
        int i5;
        if (m == 0)
        {
          i5 = localLookResidue0.phrasebook.decode(paramBlock.opb);
          if (i5 == -1) {
            return 0;
          }
          arrayOfInt[k] = localLookResidue0.decodemap[i5];
          if (arrayOfInt[k] == null) {
            return 0;
          }
        }
        int j = 0;
        while ((j < i1) && (i < i3))
        {
          i5 = localInfoResidue0.begin + i * n;
          if ((localInfoResidue0.secondstages[arrayOfInt[k][j]] & 1 << m) != 0)
          {
            CodeBook localCodeBook = localLookResidue0.fullbooks[localLookResidue0.partbooks[arrayOfInt[k][j]][m]];
            if ((localCodeBook != null) && (localCodeBook.decodevv_add(paramArrayOfFloat, i5, paramInt, paramBlock.opb, n) == -1)) {
              return 0;
            }
          }
          j++;
          i++;
        }
      }
    }
    return 0;
  }
  
  int inverse(Block paramBlock, Object paramObject, float[][] paramArrayOfFloat, int[] paramArrayOfInt, int paramInt)
  {
    int i = 0;
    for (int j = 0; j < paramInt; j++) {
      if (paramArrayOfInt[j] != 0) {
        paramArrayOfFloat[(i++)] = paramArrayOfFloat[j];
      }
    }
    if (i != 0) {
      return _01inverse(paramBlock, paramObject, paramArrayOfFloat, i, 0);
    }
    return 0;
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
  
  private static int icount(int paramInt)
  {
    int i = 0;
    while (paramInt != 0)
    {
      i += (paramInt & 0x1);
      paramInt >>>= 1;
    }
    return i;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Residue0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */