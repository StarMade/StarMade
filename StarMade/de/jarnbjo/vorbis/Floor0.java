/*    */ package de.jarnbjo.vorbis;
/*    */ 
/*    */ import de.jarnbjo.util.io.BitInputStream;
/*    */ import java.io.IOException;
/*    */ 
/*    */ class Floor0 extends Floor
/*    */ {
/*    */   private int order;
/*    */   private int rate;
/*    */   private int barkMapSize;
/*    */   private int amplitudeBits;
/*    */   private int amplitudeOffset;
/*    */   private int[] bookList;
/*    */ 
/*    */   protected Floor0(BitInputStream source, SetupHeader header)
/*    */     throws VorbisFormatException, IOException
/*    */   {
/* 37 */     this.order = source.getInt(8);
/* 38 */     this.rate = source.getInt(16);
/* 39 */     this.barkMapSize = source.getInt(16);
/* 40 */     this.amplitudeBits = source.getInt(6);
/* 41 */     this.amplitudeOffset = source.getInt(8);
/*    */ 
/* 43 */     int bookCount = source.getInt(4) + 1;
/* 44 */     this.bookList = new int[bookCount];
/*    */ 
/* 46 */     for (int i = 0; i < this.bookList.length; i++) {
/* 47 */       this.bookList[i] = source.getInt(8);
/* 48 */       if (this.bookList[i] > header.getCodeBooks().length)
/* 49 */         throw new VorbisFormatException("A floor0_book_list entry is higher than the code book count.");
/*    */     }
/*    */   }
/*    */ 
/*    */   protected int getType()
/*    */   {
/* 55 */     return 0;
/*    */   }
/*    */ 
/*    */   protected Floor decodeFloor(VorbisStream vorbis, BitInputStream source) throws VorbisFormatException, IOException
/*    */   {
/* 60 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ 
/*    */   protected void computeFloor(float[] vector)
/*    */   {
/* 65 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Floor0
 * JD-Core Version:    0.6.2
 */