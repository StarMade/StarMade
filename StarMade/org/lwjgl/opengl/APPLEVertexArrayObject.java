/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class APPLEVertexArrayObject
/*    */ {
/*    */   public static final int GL_VERTEX_ARRAY_BINDING_APPLE = 34229;
/*    */ 
/*    */   public static void glBindVertexArrayAPPLE(int array)
/*    */   {
/* 19 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 20 */     long function_pointer = caps.glBindVertexArrayAPPLE;
/* 21 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 22 */     nglBindVertexArrayAPPLE(array, function_pointer);
/*    */   }
/*    */   static native void nglBindVertexArrayAPPLE(int paramInt, long paramLong);
/*    */ 
/*    */   public static void glDeleteVertexArraysAPPLE(IntBuffer arrays) {
/* 27 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 28 */     long function_pointer = caps.glDeleteVertexArraysAPPLE;
/* 29 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 30 */     BufferChecks.checkDirect(arrays);
/* 31 */     nglDeleteVertexArraysAPPLE(arrays.remaining(), MemoryUtil.getAddress(arrays), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDeleteVertexArraysAPPLE(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glDeleteVertexArraysAPPLE(int array) {
/* 37 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 38 */     long function_pointer = caps.glDeleteVertexArraysAPPLE;
/* 39 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 40 */     nglDeleteVertexArraysAPPLE(1, APIUtil.getInt(caps, array), function_pointer);
/*    */   }
/*    */ 
/*    */   public static void glGenVertexArraysAPPLE(IntBuffer arrays) {
/* 44 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 45 */     long function_pointer = caps.glGenVertexArraysAPPLE;
/* 46 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 47 */     BufferChecks.checkDirect(arrays);
/* 48 */     nglGenVertexArraysAPPLE(arrays.remaining(), MemoryUtil.getAddress(arrays), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGenVertexArraysAPPLE(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static int glGenVertexArraysAPPLE() {
/* 54 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 55 */     long function_pointer = caps.glGenVertexArraysAPPLE;
/* 56 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 57 */     IntBuffer arrays = APIUtil.getBufferInt(caps);
/* 58 */     nglGenVertexArraysAPPLE(1, MemoryUtil.getAddress(arrays), function_pointer);
/* 59 */     return arrays.get(0);
/*    */   }
/*    */ 
/*    */   public static boolean glIsVertexArrayAPPLE(int array) {
/* 63 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 64 */     long function_pointer = caps.glIsVertexArrayAPPLE;
/* 65 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 66 */     boolean __result = nglIsVertexArrayAPPLE(array, function_pointer);
/* 67 */     return __result;
/*    */   }
/*    */ 
/*    */   static native boolean nglIsVertexArrayAPPLE(int paramInt, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.APPLEVertexArrayObject
 * JD-Core Version:    0.6.2
 */