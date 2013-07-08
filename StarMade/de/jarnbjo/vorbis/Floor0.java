package de.jarnbjo.vorbis;

import de.jarnbjo.util.io.BitInputStream;
import java.io.IOException;

class Floor0
  extends Floor
{
  private int order;
  private int rate;
  private int barkMapSize;
  private int amplitudeBits;
  private int amplitudeOffset;
  private int[] bookList;
  
  protected Floor0(BitInputStream source, SetupHeader header)
    throws VorbisFormatException, IOException
  {
    this.order = source.getInt(8);
    this.rate = source.getInt(16);
    this.barkMapSize = source.getInt(16);
    this.amplitudeBits = source.getInt(6);
    this.amplitudeOffset = source.getInt(8);
    int bookCount = source.getInt(4) + 1;
    this.bookList = new int[bookCount];
    for (int local_i = 0; local_i < this.bookList.length; local_i++)
    {
      this.bookList[local_i] = source.getInt(8);
      if (this.bookList[local_i] > header.getCodeBooks().length) {
        throw new VorbisFormatException("A floor0_book_list entry is higher than the code book count.");
      }
    }
  }
  
  protected int getType()
  {
    return 0;
  }
  
  protected Floor decodeFloor(VorbisStream vorbis, BitInputStream source)
    throws VorbisFormatException, IOException
  {
    throw new UnsupportedOperationException();
  }
  
  protected void computeFloor(float[] vector)
  {
    throw new UnsupportedOperationException();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     de.jarnbjo.vorbis.Floor0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */