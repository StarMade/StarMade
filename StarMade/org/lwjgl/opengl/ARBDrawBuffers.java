/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class ARBDrawBuffers
/*    */ {
/*    */   public static final int GL_MAX_DRAW_BUFFERS_ARB = 34852;
/*    */   public static final int GL_DRAW_BUFFER0_ARB = 34853;
/*    */   public static final int GL_DRAW_BUFFER1_ARB = 34854;
/*    */   public static final int GL_DRAW_BUFFER2_ARB = 34855;
/*    */   public static final int GL_DRAW_BUFFER3_ARB = 34856;
/*    */   public static final int GL_DRAW_BUFFER4_ARB = 34857;
/*    */   public static final int GL_DRAW_BUFFER5_ARB = 34858;
/*    */   public static final int GL_DRAW_BUFFER6_ARB = 34859;
/*    */   public static final int GL_DRAW_BUFFER7_ARB = 34860;
/*    */   public static final int GL_DRAW_BUFFER8_ARB = 34861;
/*    */   public static final int GL_DRAW_BUFFER9_ARB = 34862;
/*    */   public static final int GL_DRAW_BUFFER10_ARB = 34863;
/*    */   public static final int GL_DRAW_BUFFER11_ARB = 34864;
/*    */   public static final int GL_DRAW_BUFFER12_ARB = 34865;
/*    */   public static final int GL_DRAW_BUFFER13_ARB = 34866;
/*    */   public static final int GL_DRAW_BUFFER14_ARB = 34867;
/*    */   public static final int GL_DRAW_BUFFER15_ARB = 34868;
/*    */ 
/*    */   public static void glDrawBuffersARB(IntBuffer buffers)
/*    */   {
/* 35 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 36 */     long function_pointer = caps.glDrawBuffersARB;
/* 37 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 38 */     BufferChecks.checkDirect(buffers);
/* 39 */     nglDrawBuffersARB(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDrawBuffersARB(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glDrawBuffersARB(int buffer) {
/* 45 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 46 */     long function_pointer = caps.glDrawBuffersARB;
/* 47 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 48 */     nglDrawBuffersARB(1, APIUtil.getInt(caps, buffer), function_pointer);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBDrawBuffers
 * JD-Core Version:    0.6.2
 */