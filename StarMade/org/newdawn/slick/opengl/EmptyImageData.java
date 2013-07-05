/*    */ package org.newdawn.slick.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.newdawn.slick.util.GlUtil;
/*    */ 
/*    */ public class EmptyImageData
/*    */   implements ImageData
/*    */ {
/*    */   private int width;
/*    */   private int height;
/*    */ 
/*    */   public EmptyImageData(int width, int height)
/*    */   {
/* 26 */     this.width = width;
/* 27 */     this.height = height;
/*    */   }
/*    */ 
/*    */   public int getDepth()
/*    */   {
/* 34 */     return 32;
/*    */   }
/*    */ 
/*    */   public int getHeight()
/*    */   {
/* 41 */     return this.height;
/*    */   }
/*    */ 
/*    */   public ByteBuffer getImageBufferData()
/*    */   {
/* 48 */     return GlUtil.getDynamicByteBuffer(getTexWidth() * getTexHeight() * 4);
/*    */   }
/*    */ 
/*    */   public int getTexHeight()
/*    */   {
/* 55 */     return InternalTextureLoader.get2Fold(this.height);
/*    */   }
/*    */ 
/*    */   public int getTexWidth()
/*    */   {
/* 62 */     return InternalTextureLoader.get2Fold(this.width);
/*    */   }
/*    */ 
/*    */   public int getWidth()
/*    */   {
/* 69 */     return this.width;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.EmptyImageData
 * JD-Core Version:    0.6.2
 */