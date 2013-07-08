package de.jarnbjo.vorbis;

class MdctFloat
{
  private static final float cPI3_8 = 0.3826834F;
  private static final float cPI2_8 = 0.7071068F;
  private static final float cPI1_8 = 0.92388F;
  private int field_1844;
  private int log2n;
  private float[] trig;
  private int[] bitrev;
  private float[] equalizer;
  private float scale;
  private int itmp1;
  private int itmp2;
  private int itmp3;
  private int itmp4;
  private int itmp5;
  private int itmp6;
  private int itmp7;
  private int itmp8;
  private int itmp9;
  private float dtmp1;
  private float dtmp2;
  private float dtmp3;
  private float dtmp4;
  private float dtmp5;
  private float dtmp6;
  private float dtmp7;
  private float dtmp8;
  private float dtmp9;
  private float[] field_1845 = new float[1024];
  private float[] field_1846 = new float[1024];
  
  protected MdctFloat(int local_n)
  {
    this.bitrev = new int[local_n / 4];
    this.trig = new float[local_n + local_n / 4];
    int local_n2 = local_n >>> 1;
    this.log2n = ((int)Math.rint(Math.log(local_n) / Math.log(2.0D)));
    this.field_1844 = local_n;
    int local_AE = 0;
    int local_AO = 1;
    int local_BE = local_AE + local_n / 2;
    int local_BO = local_BE + 1;
    int local_CE = local_BE + local_n / 2;
    int local_CO = local_CE + 1;
    for (int local_i = 0; local_i < local_n / 4; local_i++)
    {
      this.trig[(local_AE + local_i * 2)] = ((float)Math.cos(3.141592653589793D / local_n * (4 * local_i)));
      this.trig[(local_AO + local_i * 2)] = ((float)-Math.sin(3.141592653589793D / local_n * (4 * local_i)));
      this.trig[(local_BE + local_i * 2)] = ((float)Math.cos(3.141592653589793D / (2 * local_n) * (2 * local_i + 1)));
      this.trig[(local_BO + local_i * 2)] = ((float)Math.sin(3.141592653589793D / (2 * local_n) * (2 * local_i + 1)));
    }
    for (int local_i = 0; local_i < local_n / 8; local_i++)
    {
      this.trig[(local_CE + local_i * 2)] = ((float)Math.cos(3.141592653589793D / local_n * (4 * local_i + 2)));
      this.trig[(local_CO + local_i * 2)] = ((float)-Math.sin(3.141592653589793D / local_n * (4 * local_i + 2)));
    }
    int local_i = (1 << this.log2n - 1) - 1;
    int msb = 1 << this.log2n - 2;
    for (int local_i1 = 0; local_i1 < local_n / 8; local_i1++)
    {
      int acc = 0;
      for (int local_j = 0; msb >>> local_j != 0; local_j++) {
        if ((msb >>> local_j & local_i1) != 0) {
          acc |= 1 << local_j;
        }
      }
      this.bitrev[(local_i1 * 2)] = ((acc ^ 0xFFFFFFFF) & local_i);
      this.bitrev[(local_i1 * 2 + 1)] = acc;
    }
    this.scale = (4.0F / local_n);
  }
  
  protected void setEqualizer(float[] equalizer)
  {
    this.equalizer = equalizer;
  }
  
  protected float[] getEqualizer()
  {
    return this.equalizer;
  }
  
  protected synchronized void imdct(float[] frq, float[] window, int[] pcm)
  {
    float[] local_in = frq;
    if (this.field_1845.length < this.field_1844 / 2) {
      this.field_1845 = new float[this.field_1844 / 2];
    }
    if (this.field_1846.length < this.field_1844 / 2) {
      this.field_1846 = new float[this.field_1844 / 2];
    }
    float[] local_x = this.field_1845;
    float[] local_w = this.field_1846;
    int local_n2 = this.field_1844 >> 1;
    int local_n4 = this.field_1844 >> 2;
    int local_n8 = this.field_1844 >> 3;
    if (this.equalizer != null) {
      for (int local_i = 0; local_i < this.field_1844; local_i++) {
        frq[local_i] *= this.equalizer[local_i];
      }
    }
    int local_i = -1;
    int local_xO = 0;
    int local_A = local_n2;
    for (int local_i1 = 0; local_i1 < local_n8; local_i1++)
    {
      local_i += 2;
      this.dtmp1 = local_in[local_i];
      local_i += 2;
      this.dtmp2 = local_in[local_i];
      this.dtmp3 = this.trig[(--local_A)];
      this.dtmp4 = this.trig[(--local_A)];
      local_x[(local_xO++)] = (-this.dtmp2 * this.dtmp3 - this.dtmp1 * this.dtmp4);
      local_x[(local_xO++)] = (this.dtmp1 * this.dtmp3 - this.dtmp2 * this.dtmp4);
    }
    local_i = local_n2;
    for (local_i1 = 0; local_i1 < local_n8; local_i1++)
    {
      local_i -= 2;
      this.dtmp1 = local_in[local_i];
      local_i -= 2;
      this.dtmp2 = local_in[local_i];
      this.dtmp3 = this.trig[(--local_A)];
      this.dtmp4 = this.trig[(--local_A)];
      local_x[(local_xO++)] = (this.dtmp2 * this.dtmp3 + this.dtmp1 * this.dtmp4);
      local_x[(local_xO++)] = (this.dtmp2 * this.dtmp4 - this.dtmp1 * this.dtmp3);
    }
    float[] local_i = kernel(local_x, local_w, this.field_1844, local_n2, local_n4, local_n8);
    int local_xO = 0;
    int local_A = local_n2;
    int local_i1 = local_n4;
    int local_o2 = local_i1 - 1;
    int local_o3 = local_n4 + local_n2;
    int local_o4 = local_o3 - 1;
    for (int local_i2 = 0; local_i2 < local_n4; local_i2++)
    {
      this.dtmp1 = local_i[(local_xO++)];
      this.dtmp2 = local_i[(local_xO++)];
      this.dtmp3 = this.trig[(local_A++)];
      this.dtmp4 = this.trig[(local_A++)];
      float temp1 = this.dtmp1 * this.dtmp4 - this.dtmp2 * this.dtmp3;
      float temp2 = -(this.dtmp1 * this.dtmp3 + this.dtmp2 * this.dtmp4);
      pcm[local_i1] = ((int)(-temp1 * window[local_i1]));
      pcm[local_o2] = ((int)(temp1 * window[local_o2]));
      pcm[local_o3] = ((int)(temp2 * window[local_o3]));
      pcm[local_o4] = ((int)(temp2 * window[local_o4]));
      local_i1++;
      local_o2--;
      local_o3++;
      local_o4--;
    }
  }
  
  private float[] kernel(float[] local_x, float[] local_w, int local_n, int local_n2, int local_n4, int local_n8)
  {
    int local_xA = local_n4;
    int local_xB = 0;
    int local_w2 = local_n4;
    int local_A = local_n2;
    for (int local_i = 0; local_i < local_n4; local_i++)
    {
      float local_x0 = local_x[local_xA] - local_x[local_xB];
      local_w[(local_w2 + local_i)] = (local_x[(local_xA++)] + local_x[(local_xB++)]);
      float local_x1 = local_x[local_xA] - local_x[local_xB];
      local_A -= 4;
      local_w[(local_i++)] = (local_x0 * this.trig[local_A] + local_x1 * this.trig[(local_A + 1)]);
      local_w[local_i] = (local_x1 * this.trig[local_A] - local_x0 * this.trig[(local_A + 1)]);
      local_w[(local_w2 + local_i)] = (local_x[(local_xA++)] + local_x[(local_xB++)]);
    }
    for (int local_i = 0; local_i < this.log2n - 3; local_i++)
    {
      int local_x0 = local_n >>> local_i + 2;
      int local_x1 = 1 << local_i + 3;
      int wbase = local_n2 - 2;
      local_A = 0;
      for (int local_r = 0; local_r < local_x0 >>> 2; local_r++)
      {
        int local_w1 = wbase;
        local_w2 = local_w1 - (local_x0 >> 1);
        float AEv = this.trig[local_A];
        float AOv = this.trig[(local_A + 1)];
        wbase -= 2;
        local_x0++;
        for (int local_s = 0; local_s < 2 << local_i; local_s++)
        {
          this.dtmp1 = local_w[local_w1];
          this.dtmp2 = local_w[local_w2];
          float local_wB = this.dtmp1 - this.dtmp2;
          local_x[local_w1] = (this.dtmp1 + this.dtmp2);
          this.dtmp1 = local_w[(++local_w1)];
          this.dtmp2 = local_w[(++local_w2)];
          float local_wA = this.dtmp1 - this.dtmp2;
          local_x[local_w1] = (this.dtmp1 + this.dtmp2);
          local_x[local_w2] = (local_wA * AEv - local_wB * AOv);
          local_x[(local_w2 - 1)] = (local_wB * AEv + local_wA * AOv);
          local_w1 -= local_x0;
          local_w2 -= local_x0;
        }
        local_x0--;
        local_A += local_x1;
      }
      float[] temp = local_w;
      local_w = local_x;
      local_x = temp;
    }
    int local_i = local_n;
    int local_x0 = 0;
    int local_x1 = 0;
    int wbase = local_n2 - 1;
    for (int temp = 0; temp < local_n8; temp++)
    {
      int local_r = this.bitrev[(local_x0++)];
      int local_w1 = this.bitrev[(local_x0++)];
      float AEv = local_w[local_r] - local_w[(local_w1 + 1)];
      float local_wA = local_w[(local_r - 1)] + local_w[local_w1];
      float AOv = local_w[local_r] + local_w[(local_w1 + 1)];
      float local_wB = local_w[(local_r - 1)] - local_w[local_w1];
      float local_s = AEv * this.trig[local_i];
      float wBCE = local_wA * this.trig[(local_i++)];
      float wACO = AEv * this.trig[local_i];
      float wBCO = local_wA * this.trig[(local_i++)];
      local_x[(local_x1++)] = ((AOv + wACO + wBCE) * 16383.0F);
      local_x[(wbase--)] = ((-local_wB + wBCO - local_s) * 16383.0F);
      local_x[(local_x1++)] = ((local_wB + wBCO - local_s) * 16383.0F);
      local_x[(wbase--)] = ((AOv - wACO - wBCE) * 16383.0F);
    }
    return local_x;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.MdctFloat
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */