/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import java.nio.ShortBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class EXTDrawRangeElements
/*    */ {
/*    */   public static final int GL_MAX_ELEMENTS_VERTICES_EXT = 33000;
/*    */   public static final int GL_MAX_ELEMENTS_INDICES_EXT = 33001;
/*    */ 
/*    */   public static void glDrawRangeElementsEXT(int mode, int start, int end, ByteBuffer pIndices)
/*    */   {
/* 16 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 17 */     long function_pointer = caps.glDrawRangeElementsEXT;
/* 18 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 19 */     GLChecks.ensureElementVBOdisabled(caps);
/* 20 */     BufferChecks.checkDirect(pIndices);
/* 21 */     nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), 5121, MemoryUtil.getAddress(pIndices), function_pointer);
/*    */   }
/*    */   public static void glDrawRangeElementsEXT(int mode, int start, int end, IntBuffer pIndices) {
/* 24 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 25 */     long function_pointer = caps.glDrawRangeElementsEXT;
/* 26 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 27 */     GLChecks.ensureElementVBOdisabled(caps);
/* 28 */     BufferChecks.checkDirect(pIndices);
/* 29 */     nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), 5125, MemoryUtil.getAddress(pIndices), function_pointer);
/*    */   }
/*    */   public static void glDrawRangeElementsEXT(int mode, int start, int end, ShortBuffer pIndices) {
/* 32 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 33 */     long function_pointer = caps.glDrawRangeElementsEXT;
/* 34 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 35 */     GLChecks.ensureElementVBOdisabled(caps);
/* 36 */     BufferChecks.checkDirect(pIndices);
/* 37 */     nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), 5123, MemoryUtil.getAddress(pIndices), function_pointer);
/*    */   }
/*    */   static native void nglDrawRangeElementsEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*    */ 
/* 41 */   public static void glDrawRangeElementsEXT(int mode, int start, int end, int pIndices_count, int type, long pIndices_buffer_offset) { ContextCapabilities caps = GLContext.getCapabilities();
/* 42 */     long function_pointer = caps.glDrawRangeElementsEXT;
/* 43 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 44 */     GLChecks.ensureElementVBOenabled(caps);
/* 45 */     nglDrawRangeElementsEXTBO(mode, start, end, pIndices_count, type, pIndices_buffer_offset, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDrawRangeElementsEXTBO(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, long paramLong1, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTDrawRangeElements
 * JD-Core Version:    0.6.2
 */