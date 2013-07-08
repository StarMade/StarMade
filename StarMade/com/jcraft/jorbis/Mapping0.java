package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

class Mapping0
  extends FuncMapping
{
  static int seq = 0;
  float[][] pcmbundle = null;
  int[] zerobundle = null;
  int[] nonzero = null;
  Object[] floormemo = null;
  
  void free_info(Object paramObject) {}
  
  void free_look(Object paramObject) {}
  
  Object look(DspState paramDspState, InfoMode paramInfoMode, Object paramObject)
  {
    Info localInfo = paramDspState.field_2242;
    LookMapping0 localLookMapping0 = new LookMapping0();
    InfoMapping0 localInfoMapping0 = localLookMapping0.map = (InfoMapping0)paramObject;
    localLookMapping0.mode = paramInfoMode;
    localLookMapping0.time_look = new Object[localInfoMapping0.submaps];
    localLookMapping0.floor_look = new Object[localInfoMapping0.submaps];
    localLookMapping0.residue_look = new Object[localInfoMapping0.submaps];
    localLookMapping0.time_func = new FuncTime[localInfoMapping0.submaps];
    localLookMapping0.floor_func = new FuncFloor[localInfoMapping0.submaps];
    localLookMapping0.residue_func = new FuncResidue[localInfoMapping0.submaps];
    for (int i = 0; i < localInfoMapping0.submaps; i++)
    {
      int j = localInfoMapping0.timesubmap[i];
      int k = localInfoMapping0.floorsubmap[i];
      int m = localInfoMapping0.residuesubmap[i];
      localLookMapping0.time_func[i] = FuncTime.time_P[localInfo.time_type[j]];
      localLookMapping0.time_look[i] = localLookMapping0.time_func[i].look(paramDspState, paramInfoMode, localInfo.time_param[j]);
      localLookMapping0.floor_func[i] = FuncFloor.floor_P[localInfo.floor_type[k]];
      localLookMapping0.floor_look[i] = localLookMapping0.floor_func[i].look(paramDspState, paramInfoMode, localInfo.floor_param[k]);
      localLookMapping0.residue_func[i] = FuncResidue.residue_P[localInfo.residue_type[m]];
      localLookMapping0.residue_look[i] = localLookMapping0.residue_func[i].look(paramDspState, paramInfoMode, localInfo.residue_param[m]);
    }
    if ((localInfo.psys != 0) && (paramDspState.analysisp != 0)) {}
    localLookMapping0.field_2226 = localInfo.channels;
    return localLookMapping0;
  }
  
  void pack(Info paramInfo, Object paramObject, Buffer paramBuffer)
  {
    InfoMapping0 localInfoMapping0 = (InfoMapping0)paramObject;
    if (localInfoMapping0.submaps > 1)
    {
      paramBuffer.write(1, 1);
      paramBuffer.write(localInfoMapping0.submaps - 1, 4);
    }
    else
    {
      paramBuffer.write(0, 1);
    }
    if (localInfoMapping0.coupling_steps > 0)
    {
      paramBuffer.write(1, 1);
      paramBuffer.write(localInfoMapping0.coupling_steps - 1, 8);
      for (i = 0; i < localInfoMapping0.coupling_steps; i++)
      {
        paramBuffer.write(localInfoMapping0.coupling_mag[i], ilog2(paramInfo.channels));
        paramBuffer.write(localInfoMapping0.coupling_ang[i], ilog2(paramInfo.channels));
      }
    }
    else
    {
      paramBuffer.write(0, 1);
    }
    paramBuffer.write(0, 2);
    if (localInfoMapping0.submaps > 1) {
      for (i = 0; i < paramInfo.channels; i++) {
        paramBuffer.write(localInfoMapping0.chmuxlist[i], 4);
      }
    }
    for (int i = 0; i < localInfoMapping0.submaps; i++)
    {
      paramBuffer.write(localInfoMapping0.timesubmap[i], 8);
      paramBuffer.write(localInfoMapping0.floorsubmap[i], 8);
      paramBuffer.write(localInfoMapping0.residuesubmap[i], 8);
    }
  }
  
  Object unpack(Info paramInfo, Buffer paramBuffer)
  {
    InfoMapping0 localInfoMapping0 = new InfoMapping0();
    if (paramBuffer.read(1) != 0) {
      localInfoMapping0.submaps = (paramBuffer.read(4) + 1);
    } else {
      localInfoMapping0.submaps = 1;
    }
    if (paramBuffer.read(1) != 0)
    {
      localInfoMapping0.coupling_steps = (paramBuffer.read(8) + 1);
      for (i = 0; i < localInfoMapping0.coupling_steps; i++)
      {
        int j = localInfoMapping0.coupling_mag[i] = paramBuffer.read(ilog2(paramInfo.channels));
        int k = localInfoMapping0.coupling_ang[i] = paramBuffer.read(ilog2(paramInfo.channels));
        if ((j < 0) || (k < 0) || (j == k) || (j >= paramInfo.channels) || (k >= paramInfo.channels))
        {
          localInfoMapping0.free();
          return null;
        }
      }
    }
    if (paramBuffer.read(2) > 0)
    {
      localInfoMapping0.free();
      return null;
    }
    if (localInfoMapping0.submaps > 1) {
      for (i = 0; i < paramInfo.channels; i++)
      {
        localInfoMapping0.chmuxlist[i] = paramBuffer.read(4);
        if (localInfoMapping0.chmuxlist[i] >= localInfoMapping0.submaps)
        {
          localInfoMapping0.free();
          return null;
        }
      }
    }
    for (int i = 0; i < localInfoMapping0.submaps; i++)
    {
      localInfoMapping0.timesubmap[i] = paramBuffer.read(8);
      if (localInfoMapping0.timesubmap[i] >= paramInfo.times)
      {
        localInfoMapping0.free();
        return null;
      }
      localInfoMapping0.floorsubmap[i] = paramBuffer.read(8);
      if (localInfoMapping0.floorsubmap[i] >= paramInfo.floors)
      {
        localInfoMapping0.free();
        return null;
      }
      localInfoMapping0.residuesubmap[i] = paramBuffer.read(8);
      if (localInfoMapping0.residuesubmap[i] >= paramInfo.residues)
      {
        localInfoMapping0.free();
        return null;
      }
    }
    return localInfoMapping0;
  }
  
  synchronized int inverse(Block paramBlock, Object paramObject)
  {
    DspState localDspState = paramBlock.field_2098;
    Info localInfo = localDspState.field_2242;
    LookMapping0 localLookMapping0 = (LookMapping0)paramObject;
    InfoMapping0 localInfoMapping0 = localLookMapping0.map;
    InfoMode localInfoMode = localLookMapping0.mode;
    int i = paramBlock.pcmend = localInfo.blocksizes[paramBlock.field_2096];
    float[] arrayOfFloat1 = localDspState.window[paramBlock.field_2096][paramBlock.field_2095][paramBlock.field_2097][localInfoMode.windowtype];
    if ((this.pcmbundle == null) || (this.pcmbundle.length < localInfo.channels))
    {
      this.pcmbundle = new float[localInfo.channels][];
      this.nonzero = new int[localInfo.channels];
      this.zerobundle = new int[localInfo.channels];
      this.floormemo = new Object[localInfo.channels];
    }
    for (int j = 0; j < localInfo.channels; j++)
    {
      float[] arrayOfFloat2 = paramBlock.pcm[j];
      m = localInfoMapping0.chmuxlist[j];
      this.floormemo[j] = localLookMapping0.floor_func[m].inverse1(paramBlock, localLookMapping0.floor_look[m], this.floormemo[j]);
      if (this.floormemo[j] != null) {
        this.nonzero[j] = 1;
      } else {
        this.nonzero[j] = 0;
      }
      for (n = 0; n < i / 2; n++) {
        arrayOfFloat2[n] = 0.0F;
      }
    }
    for (int k = 0; k < localInfoMapping0.coupling_steps; k++) {
      if ((this.nonzero[localInfoMapping0.coupling_mag[k]] != 0) || (this.nonzero[localInfoMapping0.coupling_ang[k]] != 0))
      {
        this.nonzero[localInfoMapping0.coupling_mag[k]] = 1;
        this.nonzero[localInfoMapping0.coupling_ang[k]] = 1;
      }
    }
    for (int m = 0; m < localInfoMapping0.submaps; m++)
    {
      n = 0;
      for (int i1 = 0; i1 < localInfo.channels; i1++) {
        if (localInfoMapping0.chmuxlist[i1] == m)
        {
          if (this.nonzero[i1] != 0) {
            this.zerobundle[n] = 1;
          } else {
            this.zerobundle[n] = 0;
          }
          this.pcmbundle[(n++)] = paramBlock.pcm[i1];
        }
      }
      localLookMapping0.residue_func[m].inverse(paramBlock, localLookMapping0.residue_look[m], this.pcmbundle, this.zerobundle, n);
    }
    float[] arrayOfFloat4;
    int i4;
    for (int n = localInfoMapping0.coupling_steps - 1; n >= 0; n--)
    {
      float[] arrayOfFloat3 = paramBlock.pcm[localInfoMapping0.coupling_mag[n]];
      arrayOfFloat4 = paramBlock.pcm[localInfoMapping0.coupling_ang[n]];
      for (i4 = 0; i4 < i / 2; i4++)
      {
        float f1 = arrayOfFloat3[i4];
        float f2 = arrayOfFloat4[i4];
        if (f1 > 0.0F)
        {
          if (f2 > 0.0F)
          {
            arrayOfFloat3[i4] = f1;
            arrayOfFloat4[i4] = (f1 - f2);
          }
          else
          {
            arrayOfFloat4[i4] = f1;
            arrayOfFloat3[i4] = (f1 + f2);
          }
        }
        else if (f2 > 0.0F)
        {
          arrayOfFloat3[i4] = f1;
          arrayOfFloat4[i4] = (f1 + f2);
        }
        else
        {
          arrayOfFloat4[i4] = f1;
          arrayOfFloat3[i4] = (f1 - f2);
        }
      }
    }
    for (int i2 = 0; i2 < localInfo.channels; i2++)
    {
      arrayOfFloat4 = paramBlock.pcm[i2];
      i4 = localInfoMapping0.chmuxlist[i2];
      localLookMapping0.floor_func[i4].inverse2(paramBlock, localLookMapping0.floor_look[i4], this.floormemo[i2], arrayOfFloat4);
    }
    for (int i3 = 0; i3 < localInfo.channels; i3++)
    {
      float[] arrayOfFloat5 = paramBlock.pcm[i3];
      ((Mdct)localDspState.transform[paramBlock.field_2096][0]).backward(arrayOfFloat5, arrayOfFloat5);
    }
    for (int i5 = 0; i5 < localInfo.channels; i5++)
    {
      float[] arrayOfFloat6 = paramBlock.pcm[i5];
      int i6;
      if (this.nonzero[i5] != 0) {
        for (i6 = 0; i6 < i; i6++) {
          arrayOfFloat6[i6] *= arrayOfFloat1[i6];
        }
      } else {
        for (i6 = 0; i6 < i; i6++) {
          arrayOfFloat6[i6] = 0.0F;
        }
      }
    }
    return 0;
  }
  
  private static int ilog2(int paramInt)
  {
    int i = 0;
    while (paramInt > 1)
    {
      i++;
      paramInt >>>= 1;
    }
    return i;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.Mapping0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */