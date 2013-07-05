/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class ARBTextureBufferRange
/*    */ {
/*    */   public static final int GL_TEXTURE_BUFFER_OFFSET = 37277;
/*    */   public static final int GL_TEXTURE_BUFFER_SIZE = 37278;
/*    */   public static final int GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 37279;
/*    */ 
/*    */   public static void glTexBufferRange(int target, int internalformat, int buffer, long offset, long size)
/*    */   {
/* 25 */     GL43.glTexBufferRange(target, internalformat, buffer, offset, size);
/*    */   }
/*    */ 
/*    */   public static void glTextureBufferRangeEXT(int texture, int target, int internalformat, int buffer, long offset, long size) {
/* 29 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 30 */     long function_pointer = caps.glTextureBufferRangeEXT;
/* 31 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 32 */     nglTextureBufferRangeEXT(texture, target, internalformat, buffer, offset, size, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglTextureBufferRangeEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong1, long paramLong2, long paramLong3);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTextureBufferRange
 * JD-Core Version:    0.6.2
 */