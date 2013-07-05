/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class EXTMultiDrawArrays
/*    */ {
/*    */   public static void glMultiDrawArraysEXT(int mode, IntBuffer piFirst, IntBuffer piCount)
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glMultiDrawArraysEXT;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     BufferChecks.checkDirect(piFirst);
/* 17 */     BufferChecks.checkBuffer(piCount, piFirst.remaining());
/* 18 */     nglMultiDrawArraysEXT(mode, MemoryUtil.getAddress(piFirst), MemoryUtil.getAddress(piCount), piFirst.remaining(), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglMultiDrawArraysEXT(int paramInt1, long paramLong1, long paramLong2, int paramInt2, long paramLong3);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.EXTMultiDrawArrays
 * JD-Core Version:    0.6.2
 */