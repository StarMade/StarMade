/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class AMDMultiDrawIndirect
/*    */ {
/*    */   public static void glMultiDrawArraysIndirectAMD(int mode, ByteBuffer indirect, int primcount, int stride)
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glMultiDrawArraysIndirectAMD;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     GLChecks.ensureIndirectBOdisabled(caps);
/* 17 */     BufferChecks.checkBuffer(indirect, (stride == 0 ? 16 : stride) * primcount);
/* 18 */     nglMultiDrawArraysIndirectAMD(mode, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/*    */   }
/*    */   static native void nglMultiDrawArraysIndirectAMD(int paramInt1, long paramLong1, int paramInt2, int paramInt3, long paramLong2);
/*    */ 
/* 22 */   public static void glMultiDrawArraysIndirectAMD(int mode, long indirect_buffer_offset, int primcount, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 23 */     long function_pointer = caps.glMultiDrawArraysIndirectAMD;
/* 24 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 25 */     GLChecks.ensureIndirectBOenabled(caps);
/* 26 */     nglMultiDrawArraysIndirectAMDBO(mode, indirect_buffer_offset, primcount, stride, function_pointer); }
/*    */ 
/*    */   static native void nglMultiDrawArraysIndirectAMDBO(int paramInt1, long paramLong1, int paramInt2, int paramInt3, long paramLong2);
/*    */ 
/*    */   public static void glMultiDrawArraysIndirectAMD(int mode, IntBuffer indirect, int primcount, int stride)
/*    */   {
/* 32 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 33 */     long function_pointer = caps.glMultiDrawArraysIndirectAMD;
/* 34 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 35 */     GLChecks.ensureIndirectBOdisabled(caps);
/* 36 */     BufferChecks.checkBuffer(indirect, (stride == 0 ? 4 : stride >> 2) * primcount);
/* 37 */     nglMultiDrawArraysIndirectAMD(mode, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/*    */   }
/*    */ 
/*    */   public static void glMultiDrawElementsIndirectAMD(int mode, int type, ByteBuffer indirect, int primcount, int stride) {
/* 41 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 42 */     long function_pointer = caps.glMultiDrawElementsIndirectAMD;
/* 43 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 44 */     GLChecks.ensureIndirectBOdisabled(caps);
/* 45 */     BufferChecks.checkBuffer(indirect, (stride == 0 ? 20 : stride) * primcount);
/* 46 */     nglMultiDrawElementsIndirectAMD(mode, type, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/*    */   }
/*    */   static native void nglMultiDrawElementsIndirectAMD(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, long paramLong2);
/*    */ 
/* 50 */   public static void glMultiDrawElementsIndirectAMD(int mode, int type, long indirect_buffer_offset, int primcount, int stride) { ContextCapabilities caps = GLContext.getCapabilities();
/* 51 */     long function_pointer = caps.glMultiDrawElementsIndirectAMD;
/* 52 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 53 */     GLChecks.ensureIndirectBOenabled(caps);
/* 54 */     nglMultiDrawElementsIndirectAMDBO(mode, type, indirect_buffer_offset, primcount, stride, function_pointer); }
/*    */ 
/*    */   static native void nglMultiDrawElementsIndirectAMDBO(int paramInt1, int paramInt2, long paramLong1, int paramInt3, int paramInt4, long paramLong2);
/*    */ 
/*    */   public static void glMultiDrawElementsIndirectAMD(int mode, int type, IntBuffer indirect, int primcount, int stride)
/*    */   {
/* 60 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 61 */     long function_pointer = caps.glMultiDrawElementsIndirectAMD;
/* 62 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 63 */     GLChecks.ensureIndirectBOdisabled(caps);
/* 64 */     BufferChecks.checkBuffer(indirect, (stride == 0 ? 5 : stride >> 2) * primcount);
/* 65 */     nglMultiDrawElementsIndirectAMD(mode, type, MemoryUtil.getAddress(indirect), primcount, stride, function_pointer);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.AMDMultiDrawIndirect
 * JD-Core Version:    0.6.2
 */