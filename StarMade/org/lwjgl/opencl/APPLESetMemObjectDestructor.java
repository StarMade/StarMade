/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import org.lwjgl.BufferChecks;
/*  4:   */
/*  9:   */public final class APPLESetMemObjectDestructor
/* 10:   */{
/* 11:   */  public static int clSetMemObjectDestructorAPPLE(CLMem memobj, CLMemObjectDestructorCallback pfn_notify)
/* 12:   */  {
/* 13:13 */    long function_pointer = CLCapabilities.clSetMemObjectDestructorAPPLE;
/* 14:14 */    BufferChecks.checkFunctionAddress(function_pointer);
/* 15:15 */    long user_data = CallbackUtil.createGlobalRef(pfn_notify);
/* 16:16 */    int __result = 0;
/* 17:   */    try {
/* 18:18 */      __result = nclSetMemObjectDestructorAPPLE(memobj.getPointer(), pfn_notify.getPointer(), user_data, function_pointer);
/* 19:19 */      return __result;
/* 20:   */    } finally {
/* 21:21 */      CallbackUtil.checkCallback(__result, user_data);
/* 22:   */    }
/* 23:   */  }
/* 24:   */  
/* 25:   */  static native int nclSetMemObjectDestructorAPPLE(long paramLong1, long paramLong2, long paramLong3, long paramLong4);
/* 26:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.APPLESetMemObjectDestructor
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */