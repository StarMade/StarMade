/*    */ package org.lwjgl.opencl.api;
/*    */ 
/*    */ import org.lwjgl.PointerBuffer;
/*    */ 
/*    */ public final class CLBufferRegion
/*    */ {
/* 44 */   public static final int STRUCT_SIZE = 2 * PointerBuffer.getPointerSize();
/*    */   private final int origin;
/*    */   private final int size;
/*    */ 
/*    */   public CLBufferRegion(int origin, int size)
/*    */   {
/* 50 */     this.origin = origin;
/* 51 */     this.size = size;
/*    */   }
/*    */ 
/*    */   public int getOrigin() {
/* 55 */     return this.origin;
/*    */   }
/*    */ 
/*    */   public int getSize() {
/* 59 */     return this.size;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.api.CLBufferRegion
 * JD-Core Version:    0.6.2
 */