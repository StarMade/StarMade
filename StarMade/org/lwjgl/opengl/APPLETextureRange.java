/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.Buffer;
/*    */ import java.nio.ByteBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class APPLETextureRange
/*    */ {
/*    */   public static final int GL_TEXTURE_STORAGE_HINT_APPLE = 34236;
/*    */   public static final int GL_STORAGE_PRIVATE_APPLE = 34237;
/*    */   public static final int GL_STORAGE_CACHED_APPLE = 34238;
/*    */   public static final int GL_STORAGE_SHARED_APPLE = 34239;
/*    */   public static final int GL_TEXTURE_RANGE_LENGTH_APPLE = 34231;
/*    */   public static final int GL_TEXTURE_RANGE_POINTER_APPLE = 34232;
/*    */ 
/*    */   public static void glTextureRangeAPPLE(int target, ByteBuffer pointer)
/*    */   {
/* 39 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 40 */     long function_pointer = caps.glTextureRangeAPPLE;
/* 41 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 42 */     BufferChecks.checkDirect(pointer);
/* 43 */     nglTextureRangeAPPLE(target, pointer.remaining(), MemoryUtil.getAddress(pointer), function_pointer);
/*    */   }
/*    */   static native void nglTextureRangeAPPLE(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static Buffer glGetTexParameterPointervAPPLE(int target, int pname, long result_size) {
/* 48 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 49 */     long function_pointer = caps.glGetTexParameterPointervAPPLE;
/* 50 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 51 */     Buffer __result = nglGetTexParameterPointervAPPLE(target, pname, result_size, function_pointer);
/* 52 */     return __result;
/*    */   }
/*    */ 
/*    */   static native Buffer nglGetTexParameterPointervAPPLE(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLETextureRange
 * JD-Core Version:    0.6.2
 */