/*  1:   */package de.jarnbjo.vorbis;
/*  2:   */
/*  3:   */import de.jarnbjo.util.io.BitInputStream;
/*  4:   */import java.io.IOException;
/*  5:   */
/* 28:   */class Residue1
/* 29:   */  extends Residue
/* 30:   */{
/* 31:   */  protected Residue1(BitInputStream source, SetupHeader header)
/* 32:   */    throws VorbisFormatException, IOException
/* 33:   */  {
/* 34:34 */    super(source, header);
/* 35:   */  }
/* 36:   */  
/* 37:   */  protected int getType() {
/* 38:38 */    return 1;
/* 39:   */  }
/* 40:   */  
/* 41:   */  protected void decodeResidue(VorbisStream vorbis, BitInputStream source, Mode mode, int ch, boolean[] doNotDecodeFlags, float[][] vectors) throws VorbisFormatException, IOException
/* 42:   */  {
/* 43:43 */    throw new UnsupportedOperationException();
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Residue1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */