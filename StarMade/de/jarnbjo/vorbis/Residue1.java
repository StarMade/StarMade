package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;

class Residue1
  extends Residue
{
  protected Residue1(BitInputStream source, SetupHeader header)
    throws VorbisFormatException, IOException
  {
    super(source, header);
  }
  
  protected int getType()
  {
    return 1;
  }
  
  protected void decodeResidue(VorbisStream vorbis, BitInputStream source, Mode mode, int local_ch, boolean[] doNotDecodeFlags, float[][] vectors)
    throws VorbisFormatException, IOException
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.Residue1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */