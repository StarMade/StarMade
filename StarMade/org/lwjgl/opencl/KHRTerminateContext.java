package org.lwjgl.opencl;

import org.lwjgl.BufferChecks;

public final class KHRTerminateContext
{
  public static final int CL_DEVICE_TERMINATE_CAPABILITY_KHR = 8207;
  public static final int CL_CONTEXT_TERMINATE_KHR = 8208;
  
  public static int clTerminateContextKHR(CLContext context)
  {
    long function_pointer = CLCapabilities.clTerminateContextKHR;
    BufferChecks.checkFunctionAddress(function_pointer);
    int __result = nclTerminateContextKHR(context.getPointer(), function_pointer);
    return __result;
  }
  
  static native int nclTerminateContextKHR(long paramLong1, long paramLong2);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.KHRTerminateContext
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */