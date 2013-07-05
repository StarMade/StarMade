/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ 
/*    */ public final class ATIDrawBuffers
/*    */ {
/*    */   public static final int GL_MAX_DRAW_BUFFERS_ATI = 34852;
/*    */   public static final int GL_DRAW_BUFFER0_ATI = 34853;
/*    */   public static final int GL_DRAW_BUFFER1_ATI = 34854;
/*    */   public static final int GL_DRAW_BUFFER2_ATI = 34855;
/*    */   public static final int GL_DRAW_BUFFER3_ATI = 34856;
/*    */   public static final int GL_DRAW_BUFFER4_ATI = 34857;
/*    */   public static final int GL_DRAW_BUFFER5_ATI = 34858;
/*    */   public static final int GL_DRAW_BUFFER6_ATI = 34859;
/*    */   public static final int GL_DRAW_BUFFER7_ATI = 34860;
/*    */   public static final int GL_DRAW_BUFFER8_ATI = 34861;
/*    */   public static final int GL_DRAW_BUFFER9_ATI = 34862;
/*    */   public static final int GL_DRAW_BUFFER10_ATI = 34863;
/*    */   public static final int GL_DRAW_BUFFER11_ATI = 34864;
/*    */   public static final int GL_DRAW_BUFFER12_ATI = 34865;
/*    */   public static final int GL_DRAW_BUFFER13_ATI = 34866;
/*    */   public static final int GL_DRAW_BUFFER14_ATI = 34867;
/*    */   public static final int GL_DRAW_BUFFER15_ATI = 34868;
/*    */ 
/*    */   public static void glDrawBuffersATI(IntBuffer buffers)
/*    */   {
/* 35 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 36 */     long function_pointer = caps.glDrawBuffersATI;
/* 37 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 38 */     BufferChecks.checkDirect(buffers);
/* 39 */     nglDrawBuffersATI(buffers.remaining(), MemoryUtil.getAddress(buffers), function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglDrawBuffersATI(int paramInt, long paramLong1, long paramLong2);
/*    */ 
/*    */   public static void glDrawBuffersATI(int buffer) {
/* 45 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 46 */     long function_pointer = caps.glDrawBuffersATI;
/* 47 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 48 */     nglDrawBuffersATI(1, APIUtil.getInt(caps, buffer), function_pointer);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ATIDrawBuffers
 * JD-Core Version:    0.6.2
 */