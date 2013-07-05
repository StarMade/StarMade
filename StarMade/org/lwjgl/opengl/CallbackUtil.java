/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ final class CallbackUtil
/*     */ {
/*  45 */   private static final Map<ContextCapabilities, Long> contextUserParamsARB = new HashMap();
/*     */ 
/*  47 */   private static final Map<ContextCapabilities, Long> contextUserParamsAMD = new HashMap();
/*     */ 
/*  49 */   private static final Map<ContextCapabilities, Long> contextUserParamsKHR = new HashMap();
/*     */ 
/*     */   static long createGlobalRef(Object obj)
/*     */   {
/*  61 */     return obj == null ? 0L : ncreateGlobalRef(obj);
/*     */   }
/*     */ 
/*     */   private static native long ncreateGlobalRef(Object paramObject);
/*     */ 
/*     */   private static native void deleteGlobalRef(long paramLong);
/*     */ 
/*     */   private static void registerContextCallback(long userParam, Map<ContextCapabilities, Long> contextUserData)
/*     */   {
/*  90 */     ContextCapabilities caps = GLContext.getCapabilities();
/*  91 */     if (caps == null) {
/*  92 */       deleteGlobalRef(userParam);
/*  93 */       throw new IllegalStateException("No context is current.");
/*     */     }
/*     */ 
/*  96 */     Long userParam_old = (Long)contextUserData.remove(caps);
/*  97 */     if (userParam_old != null) {
/*  98 */       deleteGlobalRef(userParam_old.longValue());
/*     */     }
/* 100 */     if (userParam != 0L)
/* 101 */       contextUserData.put(caps, Long.valueOf(userParam));
/*     */   }
/*     */ 
/*     */   static void unregisterCallbacks(Object context)
/*     */   {
/* 111 */     ContextCapabilities caps = GLContext.getCapabilities(context);
/*     */ 
/* 113 */     Long userParam = (Long)contextUserParamsARB.remove(caps);
/* 114 */     if (userParam != null) {
/* 115 */       deleteGlobalRef(userParam.longValue());
/*     */     }
/* 117 */     userParam = (Long)contextUserParamsAMD.remove(caps);
/* 118 */     if (userParam != null) {
/* 119 */       deleteGlobalRef(userParam.longValue());
/*     */     }
/* 121 */     userParam = (Long)contextUserParamsKHR.remove(caps);
/* 122 */     if (userParam != null)
/* 123 */       deleteGlobalRef(userParam.longValue());
/*     */   }
/*     */ 
/*     */   static native long getDebugOutputCallbackARB();
/*     */ 
/*     */   static void registerContextCallbackARB(long userParam)
/*     */   {
/* 143 */     registerContextCallback(userParam, contextUserParamsARB);
/*     */   }
/*     */ 
/*     */   static native long getDebugOutputCallbackAMD();
/*     */ 
/*     */   static void registerContextCallbackAMD(long userParam)
/*     */   {
/* 163 */     registerContextCallback(userParam, contextUserParamsAMD);
/*     */   }
/*     */ 
/*     */   static native long getDebugCallbackKHR();
/*     */ 
/*     */   static void registerContextCallbackKHR(long userParam)
/*     */   {
/* 183 */     registerContextCallback(userParam, contextUserParamsKHR);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.CallbackUtil
 * JD-Core Version:    0.6.2
 */