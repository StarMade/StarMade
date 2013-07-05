/*    */ package org.lwjgl.opengl;
/*    */ 
/*    */ import org.lwjgl.BufferChecks;
/*    */ 
/*    */ public final class GREMEDYFrameTerminator
/*    */ {
/*    */   public static void glFrameTerminatorGREMEDY()
/*    */   {
/* 13 */     ContextCapabilities caps = GLContext.getCapabilities();
/* 14 */     long function_pointer = caps.glFrameTerminatorGREMEDY;
/* 15 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 16 */     nglFrameTerminatorGREMEDY(function_pointer);
/*    */   }
/*    */ 
/*    */   static native void nglFrameTerminatorGREMEDY(long paramLong);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.GREMEDYFrameTerminator
 * JD-Core Version:    0.6.2
 */