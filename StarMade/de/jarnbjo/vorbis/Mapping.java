package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;

abstract class Mapping
{
  protected static Mapping createInstance(VorbisStream vorbis, BitInputStream source, SetupHeader header)
    throws VorbisFormatException, IOException
  {
    int type = source.getInt(16);
    switch (type)
    {
    case 0: 
      return new Mapping0(vorbis, source, header);
    }
    throw new VorbisFormatException("Mapping type " + type + " is not supported.");
  }
  
  protected abstract int getType();
  
  protected abstract int[] getAngles();
  
  protected abstract int[] getMagnitudes();
  
  protected abstract int[] getMux();
  
  protected abstract int[] getSubmapFloors();
  
  protected abstract int[] getSubmapResidues();
  
  protected abstract int getCouplingSteps();
  
  protected abstract int getSubmaps();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.Mapping
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */