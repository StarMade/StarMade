/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class ARBInstancedArrays
/*    */ {
/*    */   public static final int GL_VERTEX_ATTRIB_ARRAY_DIVISOR_ARB = 35070;
/*    */ 
/*    */   public static void glVertexAttribDivisorARB(int index, int divisor)
/*    */   {
/* 19 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 20 */     long function_pointer = caps.glVertexAttribDivisorARB;
/* 21 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 22 */     nglVertexAttribDivisorARB(index, divisor, function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglVertexAttribDivisorARB(int paramInt1, int paramInt2, long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.ARBInstancedArrays
 * JD-Core Version:    0.6.2
 */