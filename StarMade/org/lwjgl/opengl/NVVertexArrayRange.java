/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.ByteOrder;
/*    */ import java.nio.DoubleBuffer;
/*    */ import java.nio.FloatBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import java.nio.ShortBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.LWJGLUtil;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class NVVertexArrayRange
/*    */ {
/*    */   public static final int GL_VERTEX_ARRAY_RANGE_NV = 34077;
/*    */   public static final int GL_VERTEX_ARRAY_RANGE_LENGTH_NV = 34078;
/*    */   public static final int GL_VERTEX_ARRAY_RANGE_VALID_NV = 34079;
/*    */   public static final int GL_MAX_VERTEX_ARRAY_RANGE_ELEMENT_NV = 34080;
/*    */   public static final int GL_VERTEX_ARRAY_RANGE_POINTER_NV = 34081;
/*    */ 
/*    */   public static void glVertexArrayRangeNV(ByteBuffer pPointer)
/*    */   {
/* 19 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 20 */     long function_pointer = caps.glVertexArrayRangeNV;
/* 21 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 22 */     BufferChecks.checkDirect(pPointer);
/* 23 */     nglVertexArrayRangeNV(pPointer.remaining(), MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   public static void glVertexArrayRangeNV(DoubleBuffer pPointer) {
/* 26 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 27 */     long function_pointer = caps.glVertexArrayRangeNV;
/* 28 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 29 */     BufferChecks.checkDirect(pPointer);
/* 30 */     nglVertexArrayRangeNV(pPointer.remaining() << 3, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   public static void glVertexArrayRangeNV(FloatBuffer pPointer) {
/* 33 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 34 */     long function_pointer = caps.glVertexArrayRangeNV;
/* 35 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 36 */     BufferChecks.checkDirect(pPointer);
/* 37 */     nglVertexArrayRangeNV(pPointer.remaining() << 2, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   public static void glVertexArrayRangeNV(IntBuffer pPointer) {
/* 40 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 41 */     long function_pointer = caps.glVertexArrayRangeNV;
/* 42 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 43 */     BufferChecks.checkDirect(pPointer);
/* 44 */     nglVertexArrayRangeNV(pPointer.remaining() << 2, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   public static void glVertexArrayRangeNV(ShortBuffer pPointer) {
/* 47 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 48 */     long function_pointer = caps.glVertexArrayRangeNV;
/* 49 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 50 */     BufferChecks.checkDirect(pPointer);
/* 51 */     nglVertexArrayRangeNV(pPointer.remaining() << 1, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   static native void nglVertexArrayRangeNV(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glFlushVertexArrayRangeNV() {
/* 56 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 57 */     long function_pointer = caps.glFlushVertexArrayRangeNV;
/* 58 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 59 */     nglFlushVertexArrayRangeNV(function_pointer);
/*    */   }
/*    */   static native void nglFlushVertexArrayRangeNV(long paramLong);
/*    */ 
/*    */   public static ByteBuffer glAllocateMemoryNV(int size, float readFrequency, float writeFrequency, float priority) {
/* 64 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 65 */     long function_pointer = caps.glAllocateMemoryNV;
/* 66 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 67 */     ByteBuffer __result = nglAllocateMemoryNV(size, readFrequency, writeFrequency, priority, size, function_pointer);
/* 68 */     return (LWJGLUtil.CHECKS) && (__result == null) ? null : __result.order(ByteOrder.nativeOrder());
/*    */   }
/*    */   static native ByteBuffer nglAllocateMemoryNV(int paramInt, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glFreeMemoryNV(ByteBuffer pointer) {
/* 73 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 74 */     long function_pointer = caps.glFreeMemoryNV;
/* 75 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 76 */     BufferChecks.checkDirect(pointer);
/* 77 */     nglFreeMemoryNV(MemoryUtil.getAddress(pointer), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglFreeMemoryNV(long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVertexArrayRange
 * JD-Core Version:    0.6.2
 */