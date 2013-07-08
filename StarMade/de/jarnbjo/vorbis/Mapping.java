/*  1:   */package de.jarnbjo.vorbis;
/*  2:   */
/*  3:   */import de.jarnbjo.util.io.BitInputStream;
/*  4:   */import java.io.IOException;
/*  5:   */
/* 29:   */abstract class Mapping
/* 30:   */{
/* 31:   */  protected static Mapping createInstance(VorbisStream vorbis, BitInputStream source, SetupHeader header)
/* 32:   */    throws VorbisFormatException, IOException
/* 33:   */  {
/* 34:34 */    int type = source.getInt(16);
/* 35:35 */    switch (type)
/* 36:   */    {
/* 37:   */    case 0: 
/* 38:38 */      return new Mapping0(vorbis, source, header);
/* 39:   */    }
/* 40:40 */    throw new VorbisFormatException("Mapping type " + type + " is not supported.");
/* 41:   */  }
/* 42:   */  
/* 43:   */  protected abstract int getType();
/* 44:   */  
/* 45:   */  protected abstract int[] getAngles();
/* 46:   */  
/* 47:   */  protected abstract int[] getMagnitudes();
/* 48:   */  
/* 49:   */  protected abstract int[] getMux();
/* 50:   */  
/* 51:   */  protected abstract int[] getSubmapFloors();
/* 52:   */  
/* 53:   */  protected abstract int[] getSubmapResidues();
/* 54:   */  
/* 55:   */  protected abstract int getCouplingSteps();
/* 56:   */  
/* 57:   */  protected abstract int getSubmaps();
/* 58:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Mapping
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */