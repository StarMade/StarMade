package com.jcraft.jorbis;

public class DspState
{
  static final float M_PI = 3.141593F;
  static final int VI_TRANSFORMB = 1;
  static final int VI_WINDOWB = 1;
  int analysisp;
  Info field_2242;
  int modebits;
  float[][] pcm;
  int pcm_storage;
  int pcm_current;
  int pcm_returned;
  float[] multipliers;
  int envelope_storage;
  int envelope_current;
  int eofflag;
  int field_2243;
  int field_2244;
  int field_2245;
  int centerW;
  long granulepos;
  long sequence;
  long glue_bits;
  long time_bits;
  long floor_bits;
  long res_bits;
  float[][][][][] window = new float[2][][][][];
  Object[][] transform = new Object[2][];
  CodeBook[] fullbooks;
  Object[] mode;
  byte[] header;
  byte[] header1;
  byte[] header2;
  
  public DspState()
  {
    this.window[0] = new float[2][][][];
    this.window[0][0] = new float[2][][];
    this.window[0][1] = new float[2][][];
    this.window[0][0][0] = new float[2][];
    this.window[0][0][1] = new float[2][];
    this.window[0][1][0] = new float[2][];
    this.window[0][1][1] = new float[2][];
    this.window[1] = new float[2][][][];
    this.window[1][0] = new float[2][][];
    this.window[1][1] = new float[2][][];
    this.window[1][0][0] = new float[2][];
    this.window[1][0][1] = new float[2][];
    this.window[1][1][0] = new float[2][];
    this.window[1][1][1] = new float[2][];
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
  
  static float[] window(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float[] arrayOfFloat = new float[paramInt2];
    switch (paramInt1)
    {
    case 0: 
      int i = paramInt2 / 4 - paramInt3 / 2;
      int j = paramInt2 - paramInt2 / 4 - paramInt4 / 2;
      for (int k = 0; k < paramInt3; k++)
      {
        float f1 = (float)((k + 0.5D) / paramInt3 * 3.141592741012573D / 2.0D);
        f1 = (float)Math.sin(f1);
        f1 *= f1;
        f1 = (float)(f1 * 1.570796370506287D);
        f1 = (float)Math.sin(f1);
        arrayOfFloat[(k + i)] = f1;
      }
      for (int m = i + paramInt3; m < j; m++) {
        arrayOfFloat[m] = 1.0F;
      }
      for (int n = 0; n < paramInt4; n++)
      {
        float f2 = (float)((paramInt4 - n - 0.5D) / paramInt4 * 3.141592741012573D / 2.0D);
        f2 = (float)Math.sin(f2);
        f2 *= f2;
        f2 = (float)(f2 * 1.570796370506287D);
        f2 = (float)Math.sin(f2);
        arrayOfFloat[(n + j)] = f2;
      }
      break;
    default: 
      return null;
    }
    return arrayOfFloat;
  }
  
  int init(Info paramInfo, boolean paramBoolean)
  {
    this.field_2242 = paramInfo;
    this.modebits = ilog2(paramInfo.modes);
    this.transform[0] = new Object[1];
    this.transform[1] = new Object[1];
    this.transform[0][0] = new Mdct();
    this.transform[1][0] = new Mdct();
    ((Mdct)this.transform[0][0]).init(paramInfo.blocksizes[0]);
    ((Mdct)this.transform[1][0]).init(paramInfo.blocksizes[1]);
    this.window[0][0][0] = new float[1][];
    this.window[0][0][1] = this.window[0][0][0];
    this.window[0][1][0] = this.window[0][0][0];
    this.window[0][1][1] = this.window[0][0][0];
    this.window[1][0][0] = new float[1][];
    this.window[1][0][1] = new float[1][];
    this.window[1][1][0] = new float[1][];
    this.window[1][1][1] = new float[1][];
    for (int i = 0; i < 1; i++)
    {
      this.window[0][0][0][i] = window(i, paramInfo.blocksizes[0], paramInfo.blocksizes[0] / 2, paramInfo.blocksizes[0] / 2);
      this.window[1][0][0][i] = window(i, paramInfo.blocksizes[1], paramInfo.blocksizes[0] / 2, paramInfo.blocksizes[0] / 2);
      this.window[1][0][1][i] = window(i, paramInfo.blocksizes[1], paramInfo.blocksizes[0] / 2, paramInfo.blocksizes[1] / 2);
      this.window[1][1][0][i] = window(i, paramInfo.blocksizes[1], paramInfo.blocksizes[1] / 2, paramInfo.blocksizes[0] / 2);
      this.window[1][1][1][i] = window(i, paramInfo.blocksizes[1], paramInfo.blocksizes[1] / 2, paramInfo.blocksizes[1] / 2);
    }
    this.fullbooks = new CodeBook[paramInfo.books];
    for (int j = 0; j < paramInfo.books; j++)
    {
      this.fullbooks[j] = new CodeBook();
      this.fullbooks[j].init_decode(paramInfo.book_param[j]);
    }
    this.pcm_storage = 8192;
    this.pcm = new float[paramInfo.channels][];
    for (int k = 0; k < paramInfo.channels; k++) {
      this.pcm[k] = new float[this.pcm_storage];
    }
    this.field_2243 = 0;
    this.field_2244 = 0;
    this.centerW = (paramInfo.blocksizes[1] / 2);
    this.pcm_current = this.centerW;
    this.mode = new Object[paramInfo.modes];
    for (k = 0; k < paramInfo.modes; k++)
    {
      int m = paramInfo.mode_param[k].mapping;
      int n = paramInfo.map_type[m];
      this.mode[k] = FuncMapping.mapping_P[n].look(this, paramInfo.mode_param[k], paramInfo.map_param[m]);
    }
    return 0;
  }
  
  public int synthesis_init(Info paramInfo)
  {
    init(paramInfo, false);
    this.pcm_returned = this.centerW;
    this.centerW -= paramInfo.blocksizes[this.field_2244] / 4 + paramInfo.blocksizes[this.field_2243] / 4;
    this.granulepos = -1L;
    this.sequence = -1L;
    return 0;
  }
  
  DspState(Info paramInfo)
  {
    this();
    init(paramInfo, false);
    this.pcm_returned = this.centerW;
    this.centerW -= paramInfo.blocksizes[this.field_2244] / 4 + paramInfo.blocksizes[this.field_2243] / 4;
    this.granulepos = -1L;
    this.sequence = -1L;
  }
  
  public int synthesis_blockin(Block paramBlock)
  {
    if ((this.centerW > this.field_2242.blocksizes[1] / 2) && (this.pcm_returned > 8192))
    {
      arrayOfFloat1 = this.centerW - this.field_2242.blocksizes[1] / 2;
      arrayOfFloat1 = this.pcm_returned < arrayOfFloat1 ? this.pcm_returned : arrayOfFloat1;
      this.pcm_current -= arrayOfFloat1;
      this.centerW -= arrayOfFloat1;
      this.pcm_returned -= arrayOfFloat1;
      if (arrayOfFloat1 != 0) {
        for (i = 0; i < this.field_2242.channels; i++) {
          System.arraycopy(this.pcm[i], arrayOfFloat1, this.pcm[i], 0, this.pcm_current);
        }
      }
    }
    this.field_2243 = this.field_2244;
    this.field_2244 = paramBlock.field_2096;
    this.field_2245 = -1;
    this.glue_bits += paramBlock.glue_bits;
    this.time_bits += paramBlock.time_bits;
    this.floor_bits += paramBlock.floor_bits;
    this.res_bits += paramBlock.res_bits;
    if (this.sequence + 1L != paramBlock.sequence) {
      this.granulepos = -1L;
    }
    this.sequence = paramBlock.sequence;
    float[] arrayOfFloat1 = this.field_2242.blocksizes[this.field_2244];
    int i = this.centerW + this.field_2242.blocksizes[this.field_2243] / 4 + arrayOfFloat1 / 4;
    float[] arrayOfFloat2 = i - arrayOfFloat1 / 2;
    int j = arrayOfFloat2 + arrayOfFloat1;
    float[] arrayOfFloat3 = 0;
    float[] arrayOfFloat4 = 0;
    float[] arrayOfFloat5;
    if (j > this.pcm_storage)
    {
      this.pcm_storage = (j + this.field_2242.blocksizes[1]);
      for (k = 0; k < this.field_2242.channels; k++)
      {
        arrayOfFloat5 = new float[this.pcm_storage];
        System.arraycopy(this.pcm[k], 0, arrayOfFloat5, 0, this.pcm[k].length);
        this.pcm[k] = arrayOfFloat5;
      }
    }
    switch (this.field_2244)
    {
    case 0: 
      arrayOfFloat3 = 0;
      arrayOfFloat4 = this.field_2242.blocksizes[0] / 2;
      break;
    case 1: 
      arrayOfFloat3 = this.field_2242.blocksizes[1] / 4 - this.field_2242.blocksizes[this.field_2243] / 4;
      arrayOfFloat4 = arrayOfFloat3 + this.field_2242.blocksizes[this.field_2243] / 2;
    }
    for (int k = 0; k < this.field_2242.channels; k++)
    {
      arrayOfFloat5 = arrayOfFloat2;
      float[] arrayOfFloat6 = 0;
      for (arrayOfFloat6 = arrayOfFloat3; arrayOfFloat6 < arrayOfFloat4; arrayOfFloat6++) {
        this.pcm[k][(arrayOfFloat5 + arrayOfFloat6)] += paramBlock.pcm[k][arrayOfFloat6];
      }
      while (arrayOfFloat6 < arrayOfFloat1)
      {
        this.pcm[k][(arrayOfFloat5 + arrayOfFloat6)] = paramBlock.pcm[k][arrayOfFloat6];
        arrayOfFloat6++;
      }
    }
    if (this.granulepos == -1L)
    {
      this.granulepos = paramBlock.granulepos;
    }
    else
    {
      this.granulepos += i - this.centerW;
      if ((paramBlock.granulepos != -1L) && (this.granulepos != paramBlock.granulepos))
      {
        if ((this.granulepos > paramBlock.granulepos) && (paramBlock.eofflag != 0)) {
          i = (int)(i - (this.granulepos - paramBlock.granulepos));
        }
        this.granulepos = paramBlock.granulepos;
      }
    }
    this.centerW = i;
    this.pcm_current = j;
    if (paramBlock.eofflag != 0) {
      this.eofflag = 1;
    }
    return 0;
  }
  
  public int synthesis_pcmout(float[][][] paramArrayOfFloat, int[] paramArrayOfInt)
  {
    if (this.pcm_returned < this.centerW)
    {
      if (paramArrayOfFloat != null)
      {
        for (int i = 0; i < this.field_2242.channels; i++) {
          paramArrayOfInt[i] = this.pcm_returned;
        }
        paramArrayOfFloat[0] = this.pcm;
      }
      return this.centerW - this.pcm_returned;
    }
    return 0;
  }
  
  public int synthesis_read(int paramInt)
  {
    if ((paramInt != 0) && (this.pcm_returned + paramInt > this.centerW)) {
      return -1;
    }
    this.pcm_returned += paramInt;
    return 0;
  }
  
  public void clear() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.jcraft.jorbis.DspState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */