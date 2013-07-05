/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import java.nio.IntBuffer;
/*    */ import org.lwjgl.BufferChecks;
/*    */ import org.lwjgl.MemoryUtil;
/*    */ import org.lwjgl.opengl.GLSync;
/*    */ 
/*    */ public final class KHRGLEvent
/*    */ {
/*    */   public static final int CL_COMMAND_GL_FENCE_SYNC_OBJECT_KHR = 8205;
/*    */ 
/*    */   public static CLEvent clCreateEventFromGLsyncKHR(CLContext context, GLSync sync, IntBuffer errcode_ret)
/*    */   {
/* 19 */     long function_pointer = CLCapabilities.clCreateEventFromGLsyncKHR;
/* 20 */     BufferChecks.checkFunctionAddress(function_pointer);
/* 21 */     if (errcode_ret != null)
/* 22 */       BufferChecks.checkBuffer(errcode_ret, 1);
/* 23 */     CLEvent __result = new CLEvent(nclCreateEventFromGLsyncKHR(context.getPointer(), sync.getPointer(), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 24 */     return __result;
/*    */   }
/*    */ 
/*    */   static native long nclCreateEventFromGLsyncKHR(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.KHRGLEvent
 * JD-Core Version:    0.6.2
 */