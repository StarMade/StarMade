/*  1:   */package de.jarnbjo.vorbis;
/*  2:   */
/*  3:   */import de.jarnbjo.util.io.BitInputStream;
/*  4:   */import java.io.IOException;
/*  5:   */
/* 24:   */class Floor0
/* 25:   */  extends Floor
/* 26:   */{
/* 27:   */  private int order;
/* 28:   */  private int rate;
/* 29:   */  private int barkMapSize;
/* 30:   */  private int amplitudeBits;
/* 31:   */  private int amplitudeOffset;
/* 32:   */  private int[] bookList;
/* 33:   */  
/* 34:   */  protected Floor0(BitInputStream source, SetupHeader header)
/* 35:   */    throws VorbisFormatException, IOException
/* 36:   */  {
/* 37:37 */    this.order = source.getInt(8);
/* 38:38 */    this.rate = source.getInt(16);
/* 39:39 */    this.barkMapSize = source.getInt(16);
/* 40:40 */    this.amplitudeBits = source.getInt(6);
/* 41:41 */    this.amplitudeOffset = source.getInt(8);
/* 42:   */    
/* 43:43 */    int bookCount = source.getInt(4) + 1;
/* 44:44 */    this.bookList = new int[bookCount];
/* 45:   */    
/* 46:46 */    for (int i = 0; i < this.bookList.length; i++) {
/* 47:47 */      this.bookList[i] = source.getInt(8);
/* 48:48 */      if (this.bookList[i] > header.getCodeBooks().length) {
/* 49:49 */        throw new VorbisFormatException("A floor0_book_list entry is higher than the code book count.");
/* 50:   */      }
/* 51:   */    }
/* 52:   */  }
/* 53:   */  
/* 54:   */  protected int getType() {
/* 55:55 */    return 0;
/* 56:   */  }
/* 57:   */  
/* 58:   */  protected Floor decodeFloor(VorbisStream vorbis, BitInputStream source) throws VorbisFormatException, IOException
/* 59:   */  {
/* 60:60 */    throw new UnsupportedOperationException();
/* 61:   */  }
/* 62:   */  
/* 63:   */  protected void computeFloor(float[] vector)
/* 64:   */  {
/* 65:65 */    throw new UnsupportedOperationException();
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Floor0
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */