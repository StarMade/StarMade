/*    */ package de.jarnbjo.vorbis;
/*    */ 
/*    */ import de.jarnbjo.util.io.BitInputStream;
/*    */ import java.io.IOException;
/*    */ 
/*    */ class Mode
/*    */ {
/*    */   private boolean blockFlag;
/*    */   private int windowType;
/*    */   private int transformType;
/*    */   private int mapping;
/*    */ 
/*    */   protected Mode(BitInputStream source, SetupHeader header)
/*    */     throws VorbisFormatException, IOException
/*    */   {
/* 36 */     this.blockFlag = source.getBit();
/* 37 */     this.windowType = source.getInt(16);
/* 38 */     this.transformType = source.getInt(16);
/* 39 */     this.mapping = source.getInt(8);
/*    */ 
/* 41 */     if (this.windowType != 0) {
/* 42 */       throw new VorbisFormatException("Window type = " + this.windowType + ", != 0");
/*    */     }
/*    */ 
/* 45 */     if (this.transformType != 0) {
/* 46 */       throw new VorbisFormatException("Transform type = " + this.transformType + ", != 0");
/*    */     }
/*    */ 
/* 49 */     if (this.mapping > header.getMappings().length)
/* 50 */       throw new VorbisFormatException("Mode mapping number is higher than total number of mappings.");
/*    */   }
/*    */ 
/*    */   protected boolean getBlockFlag()
/*    */   {
/* 55 */     return this.blockFlag;
/*    */   }
/*    */ 
/*    */   protected int getWindowType() {
/* 59 */     return this.windowType;
/*    */   }
/*    */ 
/*    */   protected int getTransformType() {
/* 63 */     return this.transformType;
/*    */   }
/*    */ 
/*    */   protected int getMapping() {
/* 67 */     return this.mapping;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     de.jarnbjo.vorbis.Mode
 * JD-Core Version:    0.6.2
 */