/*    */ package org.lwjgl.util.glu;
/*    */ 
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ class PixelStoreState extends Util
/*    */ {
/*    */   public int unpackRowLength;
/*    */   public int unpackAlignment;
/*    */   public int unpackSkipRows;
/*    */   public int unpackSkipPixels;
/*    */   public int packRowLength;
/*    */   public int packAlignment;
/*    */   public int packSkipRows;
/*    */   public int packSkipPixels;
/*    */ 
/*    */   PixelStoreState()
/*    */   {
/* 59 */     load();
/*    */   }
/*    */ 
/*    */   public void load() {
/* 63 */     this.unpackRowLength = glGetIntegerv(3314);
/* 64 */     this.unpackAlignment = glGetIntegerv(3317);
/* 65 */     this.unpackSkipRows = glGetIntegerv(3315);
/* 66 */     this.unpackSkipPixels = glGetIntegerv(3316);
/* 67 */     this.packRowLength = glGetIntegerv(3330);
/* 68 */     this.packAlignment = glGetIntegerv(3333);
/* 69 */     this.packSkipRows = glGetIntegerv(3331);
/* 70 */     this.packSkipPixels = glGetIntegerv(3332);
/*    */   }
/*    */ 
/*    */   public void save() {
/* 74 */     GL11.glPixelStorei(3314, this.unpackRowLength);
/* 75 */     GL11.glPixelStorei(3317, this.unpackAlignment);
/* 76 */     GL11.glPixelStorei(3315, this.unpackSkipRows);
/* 77 */     GL11.glPixelStorei(3316, this.unpackSkipPixels);
/* 78 */     GL11.glPixelStorei(3330, this.packRowLength);
/* 79 */     GL11.glPixelStorei(3333, this.packAlignment);
/* 80 */     GL11.glPixelStorei(3331, this.packSkipRows);
/* 81 */     GL11.glPixelStorei(3332, this.packSkipPixels);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.glu.PixelStoreState
 * JD-Core Version:    0.6.2
 */