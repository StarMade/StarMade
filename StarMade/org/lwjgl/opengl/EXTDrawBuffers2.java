/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class EXTDrawBuffers2
/*    */ {
/*    */   public static void glColorMaskIndexedEXT(int buf, boolean r, boolean g, boolean b, boolean a)
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glColorMaskIndexedEXT;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     nglColorMaskIndexedEXT(buf, r, g, b, a, function_pointer);
/*    */   }
/*    */   static native void nglColorMaskIndexedEXT(int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, long paramLong);
/*    */ 
/*    */   public static void glGetBooleanIndexedEXT(int value, int index, ByteBuffer data) {
/* 21 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 22 */     long function_pointer = caps.glGetBooleanIndexedvEXT;
/* 23 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 24 */     BufferChecks.checkBuffer(data, 4);
/* 25 */     nglGetBooleanIndexedvEXT(value, index, MemoryUtil.getAddress(data), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGetBooleanIndexedvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static boolean glGetBooleanIndexedEXT(int value, int index) {
/* 31 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 32 */     long function_pointer = caps.glGetBooleanIndexedvEXT;
/* 33 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 34 */     ByteBuffer data = APIUtil.getBufferByte(caps, 1);
/* 35 */     nglGetBooleanIndexedvEXT(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 36 */     return data.get(0) == 1;
/*    */   }
/*    */ 
/*    */   public static void glGetIntegerIndexedEXT(int value, int index, IntBuffer data) {
/* 40 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 41 */     long function_pointer = caps.glGetIntegerIndexedvEXT;
/* 42 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 43 */     BufferChecks.checkBuffer(data, 4);
/* 44 */     nglGetIntegerIndexedvEXT(value, index, MemoryUtil.getAddress(data), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglGetIntegerIndexedvEXT(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static int glGetIntegerIndexedEXT(int value, int index) {
/* 50 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 51 */     long function_pointer = caps.glGetIntegerIndexedvEXT;
/* 52 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 53 */     IntBuffer data = APIUtil.getBufferInt(caps);
/* 54 */     nglGetIntegerIndexedvEXT(value, index, MemoryUtil.getAddress(data), function_pointer);
/* 55 */     return data.get(0);
/*    */   }
/*    */ 
/*    */   public static void glEnableIndexedEXT(int target, int index) {
/* 59 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 60 */     long function_pointer = caps.glEnableIndexedEXT;
/* 61 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 62 */     nglEnableIndexedEXT(target, index, function_pointer);
/*    */   }
/*    */   static native void nglEnableIndexedEXT(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static void glDisableIndexedEXT(int target, int index) {
/* 67 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 68 */     long function_pointer = caps.glDisableIndexedEXT;
/* 69 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 70 */     nglDisableIndexedEXT(target, index, function_pointer);
/*    */   }
/*    */   static native void nglDisableIndexedEXT(int paramInt1, int paramInt2, long paramLong);
/*    */ 
/*    */   public static boolean glIsEnabledIndexedEXT(int target, int index) {
/* 75 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 76 */     long function_pointer = caps.glIsEnabledIndexedEXT;
/* 77 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 78 */     boolean __result = nglIsEnabledIndexedEXT(target, index, function_pointer);
/* 79 */     return __result;
/*    */   }
/*    */ 
/*    */   static native boolean nglIsEnabledIndexedEXT(int paramInt1, int paramInt2, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTDrawBuffers2
 * JD-Core Version:    0.6.2
 */