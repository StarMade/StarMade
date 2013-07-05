/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class ARBTextureStorage
/*    */ {
/*    */   public static final int GL_TEXTURE_IMMUTABLE_FORMAT = 37167;
/*    */ 
/*    */   public static void glTexStorage1D(int target, int levels, int internalformat, int width)
/*    */   {
/* 18 */     GL42.glTexStorage1D(target, levels, internalformat, width);
/*    */   }
/*    */ 
/*    */   public static void glTexStorage2D(int target, int levels, int internalformat, int width, int height) {
/* 22 */     GL42.glTexStorage2D(target, levels, internalformat, width, height);
/*    */   }
/*    */ 
/*    */   public static void glTexStorage3D(int target, int levels, int internalformat, int width, int height, int depth) {
/* 26 */     GL42.glTexStorage3D(target, levels, internalformat, width, height, depth);
/*    */   }
/*    */ 
/*    */   public static void glTextureStorage1DEXT(int texture, int target, int levels, int internalformat, int width) {
/* 30 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 31 */     long function_pointer = caps.glTextureStorage1DEXT;
/* 32 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 33 */     nglTextureStorage1DEXT(texture, target, levels, internalformat, width, function_pointer);
/*    */   }
/*    */   static native void nglTextureStorage1DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong);
/*    */ 
/*    */   public static void glTextureStorage2DEXT(int texture, int target, int levels, int internalformat, int width, int height) {
/* 38 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 39 */     long function_pointer = caps.glTextureStorage2DEXT;
/* 40 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 41 */     nglTextureStorage2DEXT(texture, target, levels, internalformat, width, height, function_pointer);
/*    */   }
/*    */   static native void nglTextureStorage2DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, long paramLong);
/*    */ 
/*    */   public static void glTextureStorage3DEXT(int texture, int target, int levels, int internalformat, int width, int height, int depth) {
/* 46 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 47 */     long function_pointer = caps.glTextureStorage3DEXT;
/* 48 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 49 */     nglTextureStorage3DEXT(texture, target, levels, internalformat, width, height, depth, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglTextureStorage3DEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBTextureStorage
 * JD-Core Version:    0.6.2
 */