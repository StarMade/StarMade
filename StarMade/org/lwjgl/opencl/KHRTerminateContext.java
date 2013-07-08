/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class KHRTerminateContext
/* 10:   */{
/* 11:   */  public static final int CL_DEVICE_TERMINATE_CAPABILITY_KHR = 8207;
/* 12:   */  public static final int CL_CONTEXT_TERMINATE_KHR = 8208;
/* 13:   */  
/* 14:   */  public static int clTerminateContextKHR(CLContext context)
/* 15:   */  {
/* 16:16 */    long function_pointer = CLCapabilities.clTerminateContextKHR;
/* 17:17 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 18:18 */    int __result = nclTerminateContextKHR(context.getPointer(), function_pointer);
/* 19:19 */    return __result;
/* 20:   */  }
/* 21:   */  
/* 22:   */  static native int nclTerminateContextKHR(long paramLong1, long paramLong2);
/* 23:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.KHRTerminateContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */