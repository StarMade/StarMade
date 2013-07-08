package org.lwjgl.opencl;

import org.lwjgl.BufferChecks;

public final class APPLESetMemObjectDestructor
{
  public static int clSetMemObjectDestructorAPPLE(CLMem memobj, CLMemObjectDestructorCallback pfn_notify)
  {
    long function_pointer = CLCapabilities.clSetMemObjectDestructorAPPLE;
    BufferChecks.checkFunctionAddress(function_pointer);
    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
    int __result = 0;
    try
    {
      __result = nclSetMemObjectDestructorAPPLE(memobj.getPointer(), pfn_notify.getPointer(), user_data, function_pointer);
      int i = __result;
      return i;
    }
    finally
    {
      CallbackUtil.checkCallback(__result, user_data);
    }
  }
  
  static native int nclSetMemObjectDestructorAPPLE(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opencl.APPLESetMemObjectDestructor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */