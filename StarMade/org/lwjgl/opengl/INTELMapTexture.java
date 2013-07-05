/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.ByteOrder;
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.LWJGLUtil;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class INTELMapTexture
/*    */ {
/*    */   public static final int GL_TEXTURE_MEMORY_LAYOUT_INTEL = 33791;
/*    */   public static final int GL_LAYOUT_DEFAULT_INTEL = 0;
/*    */   public static final int GL_LAYOUT_LINEAR_INTEL = 1;
/*    */   public static final int GL_LAYOUT_LINEAR_CPU_CACHED_INTEL = 2;
/*    */ 
/*    */   public static ByteBuffer glMapTexture2DINTEL(int texture, int level, long length, int access, IntBuffer stride, IntBuffer layout, ByteBuffer old_buffer)
/*    */   {
/* 30 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 31 */     long function_pointer = caps.glMapTexture2DINTEL;
/* 32 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 33 */     BufferChecks.checkBuffer(stride, 1);
/* 34 */     BufferChecks.checkBuffer(layout, 1);
/* 35 */     if (old_buffer != null)
/* 36 */       BufferChecks.checkDirect(old_buffer);
/* 37 */     ByteBuffer __result = nglMapTexture2DINTEL(texture, level, length, access, MemoryUtil.getAddress(stride), MemoryUtil.getAddress(layout), old_buffer, function_pointer);
/* 38 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*    */   }
/*    */   static native ByteBuffer nglMapTexture2DINTEL(int paramInt1, int paramInt2, long paramLong1, int paramInt3, long paramLong2, long paramLong3, ByteBuffer paramByteBuffer, long paramLong4);
/*    */ 
/*    */   public static void glUnmapTexture2DINTEL(int texture, int level) {
/* 43 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 44 */     long function_pointer = caps.glUnmapTexture2DINTEL;
/* 45 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 46 */     nglUnmapTexture2DINTEL(texture, level, function_pointer);
/*    */   }
/*    */   static native void nglUnmapTexture2DINTEL(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static void glSyncTextureINTEL(int texture) {
/* 51 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 52 */     long function_pointer = caps.glSyncTextureINTEL;
/* 53 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 54 */     nglSyncTextureINTEL(texture, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglSyncTextureINTEL(int paramInt, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.INTELMapTexture
 * JD-Core Version:    0.6.2
 */