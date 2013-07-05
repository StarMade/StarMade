/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.LongBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class EXTTimerQuery
/*    */ {
/*    */   public static final int GL_TIME_ELAPSED_EXT = 35007;
/*    */ 
/*    */   public static void glGetQueryObjectEXT(int id, int pname, LongBuffer params)
/*    */   {
/* 19 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 20 */     long function_pointer = caps.glGetQueryObjecti64vEXT;
/* 21 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 22 */     BufferChecks.checkBuffer(params, 1);
/* 23 */     nglGetQueryObjecti64vEXT(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGetQueryObjecti64vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static long glGetQueryObjectEXT(int id, int pname) {
/* 29 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 30 */     long function_pointer = caps.glGetQueryObjecti64vEXT;
/* 31 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 32 */     LongBuffer params = APIUtil.getBufferLong(caps);
/* 33 */     nglGetQueryObjecti64vEXT(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 34 */     return params.get(0);
/*    */   }
/*    */ 
/*    */   public static void glGetQueryObjectuEXT(int id, int pname, LongBuffer params) {
/* 38 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 39 */     long function_pointer = caps.glGetQueryObjectui64vEXT;
/* 40 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 41 */     BufferChecks.checkBuffer(params, 1);
/* 42 */     nglGetQueryObjectui64vEXT(id, pname, MemoryUtil.getAddress(params), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGetQueryObjectui64vEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static long glGetQueryObjectuEXT(int id, int pname) {
/* 48 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 49 */     long function_pointer = caps.glGetQueryObjectui64vEXT;
/* 50 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 51 */     LongBuffer params = APIUtil.getBufferLong(caps);
/* 52 */     nglGetQueryObjectui64vEXT(id, pname, MemoryUtil.getAddress(params), function_pointer);
/* 53 */     return params.get(0);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTTimerQuery
 * JD-Core Version:    0.6.2
 */