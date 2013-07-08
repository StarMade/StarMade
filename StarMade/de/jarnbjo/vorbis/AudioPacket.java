package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;

class AudioPacket
{
  private int modeNumber;
  private Mode mode;
  private Mapping mapping;
  private int field_1910;
  private boolean blockFlag;
  private boolean previousWindowFlag;
  private boolean nextWindowFlag;
  private int windowCenter;
  private int leftWindowStart;
  private int leftWindowEnd;
  private int leftN;
  private int rightWindowStart;
  private int rightWindowEnd;
  private int rightN;
  private float[] window;
  private float[][] pcm;
  private int[][] pcmInt;
  private Floor[] channelFloors;
  private boolean[] noResidues;
  private static final float[][] windows = new float[8][];
  
  protected AudioPacket(VorbisStream vorbis, BitInputStream source)
    throws VorbisFormatException, IOException
  {
    SetupHeader sHeader = vorbis.getSetupHeader();
    IdentificationHeader iHeader = vorbis.getIdentificationHeader();
    Mode[] modes = sHeader.getModes();
    Mapping[] mappings = sHeader.getMappings();
    Residue[] residues = sHeader.getResidues();
    int channels = iHeader.getChannels();
    if (source.getInt(1) != 0) {
      throw new VorbisFormatException("Packet type mismatch when trying to create an audio packet.");
    }
    this.modeNumber = source.getInt(Util.ilog(modes.length - 1));
    try
    {
      this.mode = modes[this.modeNumber];
    }
    catch (ArrayIndexOutOfBoundsException local_e)
    {
      throw new VorbisFormatException("Reference to invalid mode in audio packet.");
    }
    this.mapping = mappings[this.mode.getMapping()];
    int[] local_e = this.mapping.getMagnitudes();
    int[] angles = this.mapping.getAngles();
    this.blockFlag = this.mode.getBlockFlag();
    int blockSize0 = iHeader.getBlockSize0();
    int blockSize1 = iHeader.getBlockSize1();
    this.field_1910 = (this.blockFlag ? blockSize1 : blockSize0);
    if (this.blockFlag)
    {
      this.previousWindowFlag = source.getBit();
      this.nextWindowFlag = source.getBit();
    }
    this.windowCenter = (this.field_1910 / 2);
    if ((this.blockFlag) && (!this.previousWindowFlag))
    {
      this.leftWindowStart = (this.field_1910 / 4 - blockSize0 / 4);
      this.leftWindowEnd = (this.field_1910 / 4 + blockSize0 / 4);
      this.leftN = (blockSize0 / 2);
    }
    else
    {
      this.leftWindowStart = 0;
      this.leftWindowEnd = (this.field_1910 / 2);
      this.leftN = this.windowCenter;
    }
    if ((this.blockFlag) && (!this.nextWindowFlag))
    {
      this.rightWindowStart = (this.field_1910 * 3 / 4 - blockSize0 / 4);
      this.rightWindowEnd = (this.field_1910 * 3 / 4 + blockSize0 / 4);
      this.rightN = (blockSize0 / 2);
    }
    else
    {
      this.rightWindowStart = this.windowCenter;
      this.rightWindowEnd = this.field_1910;
      this.rightN = (this.field_1910 / 2);
    }
    this.window = getComputedWindow();
    this.channelFloors = new Floor[channels];
    this.noResidues = new boolean[channels];
    this.pcm = new float[channels][this.field_1910];
    this.pcmInt = new int[channels][this.field_1910];
    boolean allFloorsEmpty = true;
    for (int local_i = 0; local_i < channels; local_i++)
    {
      int submapNumber = this.mapping.getMux()[local_i];
      int floorNumber = this.mapping.getSubmapFloors()[submapNumber];
      Floor decodedFloor = sHeader.getFloors()[floorNumber].decodeFloor(vorbis, source);
      this.channelFloors[local_i] = decodedFloor;
      this.noResidues[local_i] = (decodedFloor == null ? 1 : false);
      if (decodedFloor != null) {
        allFloorsEmpty = false;
      }
    }
    if (allFloorsEmpty) {
      return;
    }
    for (int local_i = 0; local_i < local_e.length; local_i++) {
      if ((this.noResidues[local_e[local_i]] == 0) || (this.noResidues[angles[local_i]] == 0))
      {
        this.noResidues[local_e[local_i]] = false;
        this.noResidues[angles[local_i]] = false;
      }
    }
    Residue[] local_i = new Residue[this.mapping.getSubmaps()];
    for (int submapNumber = 0; submapNumber < this.mapping.getSubmaps(); submapNumber++)
    {
      int floorNumber = 0;
      boolean[] decodedFloor = new boolean[channels];
      for (int local_j = 0; local_j < channels; local_j++) {
        if (this.mapping.getMux()[local_j] == submapNumber) {
          decodedFloor[(floorNumber++)] = this.noResidues[local_j];
        }
      }
      int local_j = this.mapping.getSubmapResidues()[submapNumber];
      Residue residue = residues[local_j];
      residue.decodeResidue(vorbis, source, this.mode, floorNumber, decodedFloor, this.pcm);
    }
    for (int submapNumber = this.mapping.getCouplingSteps() - 1; submapNumber >= 0; submapNumber--)
    {
      double floorNumber = 0.0D;
      double local_j = 0.0D;
      float[] magnitudeVector = this.pcm[local_e[submapNumber]];
      float[] angleVector = this.pcm[angles[submapNumber]];
      for (int local_j1 = 0; local_j1 < magnitudeVector.length; local_j1++)
      {
        float local_a = angleVector[local_j1];
        float local_m = magnitudeVector[local_j1];
        if (local_a > 0.0F)
        {
          angleVector[local_j1] = (local_m > 0.0F ? local_m - local_a : local_m + local_a);
        }
        else
        {
          magnitudeVector[local_j1] = (local_m > 0.0F ? local_m + local_a : local_m - local_a);
          angleVector[local_j1] = local_m;
        }
      }
    }
    for (int submapNumber = 0; submapNumber < channels; submapNumber++) {
      if (this.channelFloors[submapNumber] != null) {
        this.channelFloors[submapNumber].computeFloor(this.pcm[submapNumber]);
      }
    }
    for (int submapNumber = 0; submapNumber < channels; submapNumber++)
    {
      MdctFloat floorNumber = this.blockFlag ? iHeader.getMdct1() : iHeader.getMdct0();
      floorNumber.imdct(this.pcm[submapNumber], this.window, this.pcmInt[submapNumber]);
    }
  }
  
  private float[] getComputedWindow()
  {
    int local_ix = (this.blockFlag ? 4 : 0) + (this.previousWindowFlag ? 2 : 0) + (this.nextWindowFlag ? 1 : 0);
    float[] local_w = windows[local_ix];
    if (local_w == null)
    {
      local_w = new float[this.field_1910];
      for (int local_i = 0; local_i < this.leftN; local_i++)
      {
        float local_x = (float)((local_i + 0.5D) / this.leftN * 3.141592653589793D / 2.0D);
        local_x = (float)Math.sin(local_x);
        local_x *= local_x;
        local_x = (float)(local_x * 1.570796370506287D);
        local_x = (float)Math.sin(local_x);
        local_w[(local_i + this.leftWindowStart)] = local_x;
      }
      int local_i = this.leftWindowEnd;
      while (local_i < this.rightWindowStart) {
        local_w[(local_i++)] = 1.0F;
      }
      for (int local_i = 0; local_i < this.rightN; local_i++)
      {
        float local_x = (float)((this.rightN - local_i - 0.5D) / this.rightN * 3.141592653589793D / 2.0D);
        local_x = (float)Math.sin(local_x);
        local_x *= local_x;
        local_x = (float)(local_x * 1.570796370506287D);
        local_x = (float)Math.sin(local_x);
        local_w[(local_i + this.rightWindowStart)] = local_x;
      }
      windows[local_ix] = local_w;
    }
    return local_w;
  }
  
  protected int getNumberOfSamples()
  {
    return this.rightWindowStart - this.leftWindowStart;
  }
  
  protected int getPcm(AudioPacket previousPacket, int[][] buffer)
  {
    int channels = this.pcm.length;
    for (int local_i = 0; local_i < channels; local_i++)
    {
      int local_j1 = 0;
      int local_j2 = previousPacket.rightWindowStart;
      int[] ppcm = previousPacket.pcmInt[local_i];
      int[] tpcm = this.pcmInt[local_i];
      int[] target = buffer[local_i];
      for (int local_j = this.leftWindowStart; local_j < this.leftWindowEnd; local_j++)
      {
        int val = ppcm[(local_j2++)] + tpcm[local_j];
        if (val > 32767) {
          val = 32767;
        }
        if (val < -32768) {
          val = -32768;
        }
        target[(local_j1++)] = val;
      }
    }
    if (this.leftWindowEnd + 1 < this.rightWindowStart) {
      for (int local_i = 0; local_i < channels; local_i++) {
        System.arraycopy(this.pcmInt[local_i], this.leftWindowEnd, buffer[local_i], this.leftWindowEnd - this.leftWindowStart, this.rightWindowStart - this.leftWindowEnd);
      }
    }
    return this.rightWindowStart - this.leftWindowStart;
  }
  
  protected void getPcm(AudioPacket previousPacket, byte[] buffer)
  {
    int channels = this.pcm.length;
    for (int local_i = 0; local_i < channels; local_i++)
    {
      int local_ix = 0;
      int local_j2 = previousPacket.rightWindowStart;
      int[] ppcm = previousPacket.pcmInt[local_i];
      int[] tpcm = this.pcmInt[local_i];
      for (int local_j = this.leftWindowStart; local_j < this.leftWindowEnd; local_j++)
      {
        int val = ppcm[(local_j2++)] + tpcm[local_j];
        if (val > 32767) {
          val = 32767;
        }
        if (val < -32768) {
          val = -32768;
        }
        buffer[(local_ix + local_i * 2 + 1)] = ((byte)(val & 0xFF));
        buffer[(local_ix + local_i * 2)] = ((byte)(val >> 8 & 0xFF));
        local_ix += channels * 2;
      }
      local_ix = (this.leftWindowEnd - this.leftWindowStart) * channels * 2;
      for (int local_j = this.leftWindowEnd; local_j < this.rightWindowStart; local_j++)
      {
        int val = tpcm[local_j];
        if (val > 32767) {
          val = 32767;
        }
        if (val < -32768) {
          val = -32768;
        }
        buffer[(local_ix + local_i * 2 + 1)] = ((byte)(val & 0xFF));
        buffer[(local_ix + local_i * 2)] = ((byte)(val >> 8 & 0xFF));
        local_ix += channels * 2;
      }
    }
  }
  
  protected float[] getWindow()
  {
    return this.window;
  }
  
  protected int getLeftWindowStart()
  {
    return this.leftWindowStart;
  }
  
  protected int getLeftWindowEnd()
  {
    return this.leftWindowEnd;
  }
  
  protected int getRightWindowStart()
  {
    return this.rightWindowStart;
  }
  
  protected int getRightWindowEnd()
  {
    return this.rightWindowEnd;
  }
  
  public int[][] getPcm()
  {
    return this.pcmInt;
  }
  
  public float[][] getFreqencyDomain()
  {
    return this.pcm;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.AudioPacket
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */