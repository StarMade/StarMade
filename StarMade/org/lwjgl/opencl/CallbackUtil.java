/*  1:   */package org.lwjgl.opencl;
/*  2:   */
/*  3:   */import java.util.HashMap;
/*  4:   */import java.util.Map;
/*  5:   */
/* 42:   */final class CallbackUtil
/* 43:   */{
/* 44:44 */  private static final Map<CLContext, Long> contextUserData = new HashMap();
/* 45:   */  
/* 54:   */  static long createGlobalRef(Object obj)
/* 55:   */  {
/* 56:56 */    return obj == null ? 0L : ncreateGlobalRef(obj);
/* 57:   */  }
/* 58:   */  
/* 65:   */  private static native long ncreateGlobalRef(Object paramObject);
/* 66:   */  
/* 73:   */  static native void deleteGlobalRef(long paramLong);
/* 74:   */  
/* 80:   */  static void checkCallback(int errcode, long user_data)
/* 81:   */  {
/* 82:82 */    if ((errcode != 0) && (user_data != 0L)) {
/* 83:83 */      deleteGlobalRef(user_data);
/* 84:   */    }
/* 85:   */  }
/* 86:   */  
/* 87:   */  static native long getContextCallback();
/* 88:   */  
/* 89:   */  static native long getMemObjectDestructorCallback();
/* 90:   */  
/* 91:   */  static native long getProgramCallback();
/* 92:   */  
/* 93:   */  static native long getNativeKernelCallback();
/* 94:   */  
/* 95:   */  static native long getEventCallback();
/* 96:   */  
/* 97:   */  static native long getPrintfCallback();
/* 98:   */  
/* 99:   */  static native long getLogMessageToSystemLogAPPLE();
/* 100:   */  
/* 101:   */  static native long getLogMessageToStdoutAPPLE();
/* 102:   */  
/* 103:   */  static native long getLogMessageToStderrAPPLE();
/* 104:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CallbackUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */