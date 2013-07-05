/*    */ package de.jarnbjo.vorbis;
/*    */ 
/*    */ import de.jarnbjo.util.io.BitInputStream;
/*    */ import java.io.IOException;
/*    */ 
/*    */ class Residue1 extends Residue
/*    */ {
/*    */   protected Residue1(BitInputStream source, SetupHeader header)
/*    */     throws VorbisFormatException, IOException
/*    */   {
/* 34 */     super(source, header);
/*    */   }
/*    */ 
/*    */   protected int getType() {
/* 38 */     return 1;
/*    */   }
/*    */ 
/*    */   protected void decodeResidue(VorbisStream vorbis, BitInputStream source, Mode mode, int ch, boolean[] doNotDecodeFlags, float[][] vectors) throws VorbisFormatException, IOException
/*    */   {
/* 43 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Residue1
 * JD-Core Version:    0.6.2
 */