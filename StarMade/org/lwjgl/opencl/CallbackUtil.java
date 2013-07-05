/*    */ package org.lwjgl.opencl;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ final class CallbackUtil
/*    */ {
/* 44 */   private static final Map<CLContext, Long> contextUserData = new HashMap();
/*    */ 
/*    */   static long createGlobalRef(Object obj)
/*    */   {
/* 56 */     return obj == null ? 0L : ncreateGlobalRef(obj);
/*    */   }
/*    */ 
/*    */   private static native long ncreateGlobalRef(Object paramObject);
/*    */ 
/*    */   static native void deleteGlobalRef(long paramLong);
/*    */ 
/*    */   static void checkCallback(int errcode, long user_data)
/*    */   {
/* 82 */     if ((errcode != 0) && (user_data != 0L))
/* 83 */       deleteGlobalRef(user_data);
/*    */   }
/*    */ 
/*    */   static native long getContextCallback();
/*    */ 
/*    */   static native long getMemObjectDestructorCallback();
/*    */ 
/*    */   static native long getProgramCallback();
/*    */ 
/*    */   static native long getNativeKernelCallback();
/*    */ 
/*    */   static native long getEventCallback();
/*    */ 
/*    */   static native long getPrintfCallback();
/*    */ 
/*    */   static native long getLogMessageToSystemLogAPPLE();
/*    */ 
/*    */   static native long getLogMessageToStdoutAPPLE();
/*    */ 
/*    */   static native long getLogMessageToStderrAPPLE();
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opencl.CallbackUtil
 * JD-Core Version:    0.6.2
 */