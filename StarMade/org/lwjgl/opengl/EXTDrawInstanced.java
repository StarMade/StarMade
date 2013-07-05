/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import java.nio.ShortBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class EXTDrawInstanced
/*    */ {
/*    */   public static void glDrawArraysInstancedEXT(int mode, int first, int count, int primcount)
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glDrawArraysInstancedEXT;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     nglDrawArraysInstancedEXT(mode, first, count, primcount, function_pointer);
/*    */   }
/*    */   static native void nglDrawArraysInstancedEXT(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*    */ 
/*    */   public static void glDrawElementsInstancedEXT(int mode, ByteBuffer indices, int primcount) {
/* 21 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 22 */     long function_pointer = caps.glDrawElementsInstancedEXT;
/* 23 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 24 */     GLChecks.ensureElementVBOdisabled(caps);
/* 25 */     BufferChecks.checkDirect(indices);
/* 26 */     nglDrawElementsInstancedEXT(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), primcount, function_pointer);
/*    */   }
/*    */   public static void glDrawElementsInstancedEXT(int mode, IntBuffer indices, int primcount) {
/* 29 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 30 */     long function_pointer = caps.glDrawElementsInstancedEXT;
/* 31 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 32 */     GLChecks.ensureElementVBOdisabled(caps);
/* 33 */     BufferChecks.checkDirect(indices);
/* 34 */     nglDrawElementsInstancedEXT(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), primcount, function_pointer);
/*    */   }
/*    */   public static void glDrawElementsInstancedEXT(int mode, ShortBuffer indices, int primcount) {
/* 37 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 38 */     long function_pointer = caps.glDrawElementsInstancedEXT;
/* 39 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 40 */     GLChecks.ensureElementVBOdisabled(caps);
/* 41 */     BufferChecks.checkDirect(indices);
/* 42 */     nglDrawElementsInstancedEXT(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), primcount, function_pointer);
/*    */   }
/*    */   static native void nglDrawElementsInstancedEXT(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/*    */ 
/* 46 */   public static void glDrawElementsInstancedEXT(int mode, int indices_count, int type, long indices_buffer_offset, int primcount) { ContextCapabilities caps = GLContext.getCapabilities();
/* 47 */     long function_pointer = caps.glDrawElementsInstancedEXT;
/* 48 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 49 */     GLChecks.ensureElementVBOenabled(caps);
/* 50 */     nglDrawElementsInstancedEXTBO(mode, indices_count, type, indices_buffer_offset, primcount, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDrawElementsInstancedEXTBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTDrawInstanced
 * JD-Core Version:    0.6.2
 */