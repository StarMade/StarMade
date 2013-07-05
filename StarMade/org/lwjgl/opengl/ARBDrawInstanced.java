/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import java.nio.IntBuffer;
/*    */ import java.nio.ShortBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class ARBDrawInstanced
/*    */ {
/*    */   public static void glDrawArraysInstancedARB(int mode, int first, int count, int primcount)
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glDrawArraysInstancedARB;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     nglDrawArraysInstancedARB(mode, first, count, primcount, function_pointer);
/*    */   }
/*    */   static native void nglDrawArraysInstancedARB(int paramInt1, int paramInt2, int paramInt3, int paramInt4, long paramLong);
/*    */ 
/*    */   public static void glDrawElementsInstancedARB(int mode, ByteBuffer indices, int primcount) {
/* 21 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 22 */     long function_pointer = caps.glDrawElementsInstancedARB;
/* 23 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 24 */     GLChecks.ensureElementVBOdisabled(caps);
/* 25 */     BufferChecks.checkDirect(indices);
/* 26 */     nglDrawElementsInstancedARB(mode, indices.remaining(), 5121, MemoryUtil.getAddress(indices), primcount, function_pointer);
/*    */   }
/*    */   public static void glDrawElementsInstancedARB(int mode, IntBuffer indices, int primcount) {
/* 29 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 30 */     long function_pointer = caps.glDrawElementsInstancedARB;
/* 31 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 32 */     GLChecks.ensureElementVBOdisabled(caps);
/* 33 */     BufferChecks.checkDirect(indices);
/* 34 */     nglDrawElementsInstancedARB(mode, indices.remaining(), 5125, MemoryUtil.getAddress(indices), primcount, function_pointer);
/*    */   }
/*    */   public static void glDrawElementsInstancedARB(int mode, ShortBuffer indices, int primcount) {
/* 37 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 38 */     long function_pointer = caps.glDrawElementsInstancedARB;
/* 39 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 40 */     GLChecks.ensureElementVBOdisabled(caps);
/* 41 */     BufferChecks.checkDirect(indices);
/* 42 */     nglDrawElementsInstancedARB(mode, indices.remaining(), 5123, MemoryUtil.getAddress(indices), primcount, function_pointer);
/*    */   }
/*    */   static native void nglDrawElementsInstancedARB(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/*    */ 
/* 46 */   public static void glDrawElementsInstancedARB(int mode, int indices_count, int type, long indices_buffer_offset, int primcount) { ContextCapabilities caps = GLContext.getCapabilities();
/* 47 */     long function_pointer = caps.glDrawElementsInstancedARB;
/* 48 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 49 */     GLChecks.ensureElementVBOenabled(caps);
/* 50 */     nglDrawElementsInstancedARBBO(mode, indices_count, type, indices_buffer_offset, primcount, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDrawElementsInstancedARBBO(int paramInt1, int paramInt2, int paramInt3, long paramLong1, int paramInt4, long paramLong2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDrawInstanced
 * JD-Core Version:    0.6.2
 */