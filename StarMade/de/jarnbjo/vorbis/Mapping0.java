package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;
import java.io.PrintStream;

class Mapping0
  extends Mapping
{
  private int[] magnitudes;
  private int[] angles;
  private int[] mux;
  private int[] submapFloors;
  private int[] submapResidues;
  
  protected Mapping0(VorbisStream vorbis, BitInputStream source, SetupHeader header)
    throws VorbisFormatException, IOException
  {
    int submaps = 1;
    if (source.getBit()) {
      submaps = source.getInt(4) + 1;
    }
    int channels = vorbis.getIdentificationHeader().getChannels();
    int ilogChannels = Util.ilog(channels - 1);
    if (source.getBit())
    {
      int couplingSteps = source.getInt(8) + 1;
      this.magnitudes = new int[couplingSteps];
      this.angles = new int[couplingSteps];
      for (int local_i = 0; local_i < couplingSteps; local_i++)
      {
        this.magnitudes[local_i] = source.getInt(ilogChannels);
        this.angles[local_i] = source.getInt(ilogChannels);
        if ((this.magnitudes[local_i] == this.angles[local_i]) || (this.magnitudes[local_i] >= channels) || (this.angles[local_i] >= channels))
        {
          System.err.println(this.magnitudes[local_i]);
          System.err.println(this.angles[local_i]);
          throw new VorbisFormatException("The channel magnitude and/or angle mismatch.");
        }
      }
    }
    else
    {
      this.magnitudes = new int[0];
      this.angles = new int[0];
    }
    if (source.getInt(2) != 0) {
      throw new VorbisFormatException("A reserved mapping field has an invalid value.");
    }
    this.mux = new int[channels];
    if (submaps > 1) {
      for (int couplingSteps = 0; couplingSteps < channels; couplingSteps++)
      {
        this.mux[couplingSteps] = source.getInt(4);
        if (this.mux[couplingSteps] > submaps) {
          throw new VorbisFormatException("A mapping mux value is higher than the number of submaps");
        }
      }
    } else {
      for (int couplingSteps = 0; couplingSteps < channels; couplingSteps++) {
        this.mux[couplingSteps] = 0;
      }
    }
    this.submapFloors = new int[submaps];
    this.submapResidues = new int[submaps];
    int couplingSteps = header.getFloors().length;
    int local_i = header.getResidues().length;
    for (int local_i1 = 0; local_i1 < submaps; local_i1++)
    {
      source.getInt(8);
      this.submapFloors[local_i1] = source.getInt(8);
      this.submapResidues[local_i1] = source.getInt(8);
      if (this.submapFloors[local_i1] > couplingSteps) {
        throw new VorbisFormatException("A mapping floor value is higher than the number of floors.");
      }
      if (this.submapResidues[local_i1] > local_i) {
        throw new VorbisFormatException("A mapping residue value is higher than the number of residues.");
      }
    }
  }
  
  protected int getType()
  {
    return 0;
  }
  
  protected int[] getAngles()
  {
    return this.angles;
  }
  
  protected int[] getMagnitudes()
  {
    return this.magnitudes;
  }
  
  protected int[] getMux()
  {
    return this.mux;
  }
  
  protected int[] getSubmapFloors()
  {
    return this.submapFloors;
  }
  
  protected int[] getSubmapResidues()
  {
    return this.submapResidues;
  }
  
  protected int getCouplingSteps()
  {
    return this.angles.length;
  }
  
  protected int getSubmaps()
  {
    return this.submapFloors.length;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.Mapping0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */