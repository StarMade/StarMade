/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.DoubleBuffer;
/*    */ import java.nio.FloatBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import java.nio.ShortBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class NVPixelDataRange
/*    */ {
/*    */   public static final int GL_WRITE_PIXEL_DATA_RANGE_NV = 34936;
/*    */   public static final int GL_READ_PIXEL_DATA_RANGE_NV = 34937;
/*    */   public static final int GL_WRITE_PIXEL_DATA_RANGE_LENGTH_NV = 34938;
/*    */   public static final int GL_READ_PIXEL_DATA_RANGE_LENGTH_NV = 34939;
/*    */   public static final int GL_WRITE_PIXEL_DATA_RANGE_POINTER_NV = 34940;
/*    */   public static final int GL_READ_PIXEL_DATA_RANGE_POINTER_NV = 34941;
/*    */ 
/*    */   public static void glPixelDataRangeNV(int target, ByteBuffer data)
/*    */   {
/* 34 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 35 */     long function_pointer = caps.glPixelDataRangeNV;
/* 36 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 37 */     BufferChecks.checkDirect(data);
/* 38 */     nglPixelDataRangeNV(target, data.remaining(), MemoryUtil.getAddress(data), function_pointer);
/*    */   }
/*    */   public static void glPixelDataRangeNV(int target, DoubleBuffer data) {
/* 41 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 42 */     long function_pointer = caps.glPixelDataRangeNV;
/* 43 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 44 */     BufferChecks.checkDirect(data);
/* 45 */     nglPixelDataRangeNV(target, data.remaining() << 3, MemoryUtil.getAddress(data), function_pointer);
/*    */   }
/*    */   public static void glPixelDataRangeNV(int target, FloatBuffer data) {
/* 48 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 49 */     long function_pointer = caps.glPixelDataRangeNV;
/* 50 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 51 */     BufferChecks.checkDirect(data);
/* 52 */     nglPixelDataRangeNV(target, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*    */   }
/*    */   public static void glPixelDataRangeNV(int target, IntBuffer data) {
/* 55 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 56 */     long function_pointer = caps.glPixelDataRangeNV;
/* 57 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 58 */     BufferChecks.checkDirect(data);
/* 59 */     nglPixelDataRangeNV(target, data.remaining() << 2, MemoryUtil.getAddress(data), function_pointer);
/*    */   }
/*    */   public static void glPixelDataRangeNV(int target, ShortBuffer data) {
/* 62 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 63 */     long function_pointer = caps.glPixelDataRangeNV;
/* 64 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 65 */     BufferChecks.checkDirect(data);
/* 66 */     nglPixelDataRangeNV(target, data.remaining() << 1, MemoryUtil.getAddress(data), function_pointer);
/*    */   }
/*    */   static native void nglPixelDataRangeNV(int paramInt1, int paramInt2, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glFlushPixelDataRangeNV(int target) {
/* 71 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 72 */     long function_pointer = caps.glFlushPixelDataRangeNV;
/* 73 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 74 */     nglFlushPixelDataRangeNV(target, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglFlushPixelDataRangeNV(int paramInt, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVPixelDataRange
 * JD-Core Version:    0.6.2
 */