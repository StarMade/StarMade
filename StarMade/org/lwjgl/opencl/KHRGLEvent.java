/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.nio.IntBuffer;
/*  4:   */import org.lwjgl.BufferChecks;
/*  5:   */import org.lwjgl.MemoryUtil;
/*  6:   */import org.lwjgl.opengl.GLSync;
/*  7:   */
/* 13:   */public final class KHRGLEvent
/* 14:   */{
/* 15:   */  public static final int CL_COMMAND_GL_FENCE_SYNC_OBJECT_KHR = 8205;
/* 16:   */  
/* 17:   */  public static CLEvent clCreateEventFromGLsyncKHR(CLContext context, GLSync sync, IntBuffer errcode_ret)
/* 18:   */  {
/* 19:19 */    long function_pointer = CLCapabilities.clCreateEventFromGLsyncKHR;
/* 20:20 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 21:21 */    if (errcode_ret != null)
/* 22:22 */      BufferChecks.checkBuffer(errcode_ret, 1);
/* 23:23 */    CLEvent __result = new CLEvent(nclCreateEventFromGLsyncKHR(context.getPointer(), sync.getPointer(), MemoryUtil.getAddressSafe(errcode_ret), function_pointer), context);
/* 24:24 */    return __result;
/* 25:   */  }
/* 26:   */  
/* 27:   */  static native long nclCreateEventFromGLsyncKHR(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 28:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.KHRGLEvent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */