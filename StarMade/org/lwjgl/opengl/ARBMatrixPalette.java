/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import java.nio.ShortBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.LWJGLUtil;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class ARBMatrixPalette
/*    */ {
/*    */   public static final int GL_MATRIX_PALETTE_ARB = 34880;
/*    */   public static final int GL_MAX_MATRIX_PALETTE_STACK_DEPTH_ARB = 34881;
/*    */   public static final int GL_MAX_PALETTE_MATRICES_ARB = 34882;
/*    */   public static final int GL_CURRENT_PALETTE_MATRIX_ARB = 34883;
/*    */   public static final int GL_MATRIX_INDEX_ARRAY_ARB = 34884;
/*    */   public static final int GL_CURRENT_MATRIX_INDEX_ARB = 34885;
/*    */   public static final int GL_MATRIX_INDEX_ARRAY_SIZE_ARB = 34886;
/*    */   public static final int GL_MATRIX_INDEX_ARRAY_TYPE_ARB = 34887;
/*    */   public static final int GL_MATRIX_INDEX_ARRAY_STRIDE_ARB = 34888;
/*    */   public static final int GL_MATRIX_INDEX_ARRAY_POINTER_ARB = 34889;
/*    */ 
/*    */   public static void glCurrentPaletteMatrixARB(int index)
/*    */   {
/* 24 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 25 */     long function_pointer = caps.glCurrentPaletteMatrixARB;
/* 26 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 27 */     nglCurrentPaletteMatrixARB(index, function_pointer);
/*    */   }
/*    */   static native void nglCurrentPaletteMatrixARB(int paramInt, long paramLong);
/*    */ 
/*    */   public static void glMatrixIndexPointerARB(int size, int stride, ByteBuffer pPointer) {
/* 32 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 33 */     long function_pointer = caps.glMatrixIndexPointerARB;
/* 34 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 35 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 36 */     BufferChecks.checkDirect(pPointer);
/* 37 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = pPointer;
/* 38 */     nglMatrixIndexPointerARB(size, 5121, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   public static void glMatrixIndexPointerARB(int size, int stride, IntBuffer pPointer) {
/* 41 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 42 */     long function_pointer = caps.glMatrixIndexPointerARB;
/* 43 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 44 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 45 */     BufferChecks.checkDirect(pPointer);
/* 46 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = pPointer;
/* 47 */     nglMatrixIndexPointerARB(size, 5125, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   public static void glMatrixIndexPointerARB(int size, int stride, ShortBuffer pPointer) {
/* 50 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 51 */     long function_pointer = caps.glMatrixIndexPointerARB;
/* 52 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 53 */     GLChecks.ensureArrayVBOdisabled(caps);
/* 54 */     BufferChecks.checkDirect(pPointer);
/* 55 */     if (LWJGLUtil.CHECKS) StateTracker.getReferences(caps).ARB_matrix_palette_glMatrixIndexPointerARB_pPointer = pPointer;
/* 56 */     nglMatrixIndexPointerARB(size, 5123, stride, MemoryUtil.getAddress(pPointer), function_pointer);
/*    */   }
/*    */   static native void nglMatrixIndexPointerARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*    */ 
/* 60 */   public static void glMatrixIndexPointerARB(int size, int type, int stride, long pPointer_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 61 */     long function_pointer = caps.glMatrixIndexPointerARB;
/* 62 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 63 */     GLChecks.ensureArrayVBOenabled(caps);
/* 64 */     nglMatrixIndexPointerARBBO(size, type, stride, pPointer_buffer_offset, function_pointer); }
/*    */ 
/*    */   static native void nglMatrixIndexPointerARBBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glMatrixIndexuARB(ByteBuffer pIndices) {
/* 69 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 70 */     long function_pointer = caps.glMatrixIndexubvARB;
/* 71 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 72 */     BufferChecks.checkDirect(pIndices);
/* 73 */     nglMatrixIndexubvARB(pIndices.remaining(), MemoryUtil.getAddress(pIndices), function_pointer);
/*    */   }
/*    */   static native void nglMatrixIndexubvARB(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glMatrixIndexuARB(ShortBuffer pIndices) {
/* 78 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 79 */     long function_pointer = caps.glMatrixIndexusvARB;
/* 80 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 81 */     BufferChecks.checkDirect(pIndices);
/* 82 */     nglMatrixIndexusvARB(pIndices.remaining(), MemoryUtil.getAddress(pIndices), function_pointer);
/*    */   }
/*    */   static native void nglMatrixIndexusvARB(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glMatrixIndexuARB(IntBuffer pIndices) {
/* 87 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 88 */     long function_pointer = caps.glMatrixIndexuivARB;
/* 89 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 90 */     BufferChecks.checkDirect(pIndices);
/* 91 */     nglMatrixIndexuivARB(pIndices.remaining(), MemoryUtil.getAddress(pIndices), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglMatrixIndexuivARB(int paramInt, long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBMatrixPalette
 * JD-Core Version:    0.6.2
 */