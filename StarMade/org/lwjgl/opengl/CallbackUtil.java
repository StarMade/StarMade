/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.util.HashMap;
/*   4:    */import java.util.Map;
/*   5:    */
/*  43:    */final class CallbackUtil
/*  44:    */{
/*  45: 45 */  private static final Map<ContextCapabilities, Long> contextUserParamsARB = new HashMap();
/*  46:    */  
/*  47: 47 */  private static final Map<ContextCapabilities, Long> contextUserParamsAMD = new HashMap();
/*  48:    */  
/*  49: 49 */  private static final Map<ContextCapabilities, Long> contextUserParamsKHR = new HashMap();
/*  50:    */  
/*  59:    */  static long createGlobalRef(Object obj)
/*  60:    */  {
/*  61: 61 */    return obj == null ? 0L : ncreateGlobalRef(obj);
/*  62:    */  }
/*  63:    */  
/*  71:    */  private static native long ncreateGlobalRef(Object paramObject);
/*  72:    */  
/*  80:    */  private static native void deleteGlobalRef(long paramLong);
/*  81:    */  
/*  88:    */  private static void registerContextCallback(long userParam, Map<ContextCapabilities, Long> contextUserData)
/*  89:    */  {
/*  90: 90 */    ContextCapabilities caps = GLContext.getCapabilities();
/*  91: 91 */    if (caps == null) {
/*  92: 92 */      deleteGlobalRef(userParam);
/*  93: 93 */      throw new IllegalStateException("No context is current.");
/*  94:    */    }
/*  95:    */    
/*  96: 96 */    Long userParam_old = (Long)contextUserData.remove(caps);
/*  97: 97 */    if (userParam_old != null) {
/*  98: 98 */      deleteGlobalRef(userParam_old.longValue());
/*  99:    */    }
/* 100:100 */    if (userParam != 0L) {
/* 101:101 */      contextUserData.put(caps, Long.valueOf(userParam));
/* 102:    */    }
/* 103:    */  }
/* 104:    */  
/* 109:    */  static void unregisterCallbacks(Object context)
/* 110:    */  {
/* 111:111 */    ContextCapabilities caps = GLContext.getCapabilities(context);
/* 112:    */    
/* 113:113 */    Long userParam = (Long)contextUserParamsARB.remove(caps);
/* 114:114 */    if (userParam != null) {
/* 115:115 */      deleteGlobalRef(userParam.longValue());
/* 116:    */    }
/* 117:117 */    userParam = (Long)contextUserParamsAMD.remove(caps);
/* 118:118 */    if (userParam != null) {
/* 119:119 */      deleteGlobalRef(userParam.longValue());
/* 120:    */    }
/* 121:121 */    userParam = (Long)contextUserParamsKHR.remove(caps);
/* 122:122 */    if (userParam != null) {
/* 123:123 */      deleteGlobalRef(userParam.longValue());
/* 124:    */    }
/* 125:    */  }
/* 126:    */  
/* 133:    */  static native long getDebugOutputCallbackARB();
/* 134:    */  
/* 141:    */  static void registerContextCallbackARB(long userParam)
/* 142:    */  {
/* 143:143 */    registerContextCallback(userParam, contextUserParamsARB);
/* 144:    */  }
/* 145:    */  
/* 153:    */  static native long getDebugOutputCallbackAMD();
/* 154:    */  
/* 161:    */  static void registerContextCallbackAMD(long userParam)
/* 162:    */  {
/* 163:163 */    registerContextCallback(userParam, contextUserParamsAMD);
/* 164:    */  }
/* 165:    */  
/* 173:    */  static native long getDebugCallbackKHR();
/* 174:    */  
/* 181:    */  static void registerContextCallbackKHR(long userParam)
/* 182:    */  {
/* 183:183 */    registerContextCallback(userParam, contextUserParamsKHR);
/* 184:    */  }
/* 185:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.CallbackUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */