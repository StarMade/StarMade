package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;

class Mode
{
  private boolean blockFlag;
  private int windowType;
  private int transformType;
  private int mapping;
  
  protected Mode(BitInputStream source, SetupHeader header)
    throws VorbisFormatException, IOException
  {
    this.blockFlag = source.getBit();
    this.windowType = source.getInt(16);
    this.transformType = source.getInt(16);
    this.mapping = source.getInt(8);
    if (this.windowType != 0) {
      throw new VorbisFormatException("Window type = " + this.windowType + ", != 0");
    }
    if (this.transformType != 0) {
      throw new VorbisFormatException("Transform type = " + this.transformType + ", != 0");
    }
    if (this.mapping > header.getMappings().length) {
      throw new VorbisFormatException("Mode mapping number is higher than total number of mappings.");
    }
  }
  
  protected boolean getBlockFlag()
  {
    return this.blockFlag;
  }
  
  protected int getWindowType()
  {
    return this.windowType;
  }
  
  protected int getTransformType()
  {
    return this.transformType;
  }
  
  protected int getMapping()
  {
    return this.mapping;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.Mode
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */