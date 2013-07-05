/*     */ package com.bulletphysics;
/*     */ 
/*     */ import com.bulletphysics.util.ArrayPool;
/*     */ import com.bulletphysics.util.ObjectPool;
/*     */ 
/*     */ public class BulletGlobals
/*     */ {
/*     */   public static final boolean DEBUG = false;
/*     */   public static final float CONVEX_DISTANCE_MARGIN = 0.04F;
/*     */   public static final float FLT_EPSILON = 1.192093E-007F;
/*     */   public static final float SIMD_EPSILON = 1.192093E-007F;
/*     */   public static final float SIMD_2_PI = 6.283186F;
/*     */   public static final float SIMD_PI = 3.141593F;
/*     */   public static final float SIMD_HALF_PI = 1.570796F;
/*     */   public static final float SIMD_RADS_PER_DEG = 0.01745329F;
/*     */   public static final float SIMD_DEGS_PER_RAD = 57.295776F;
/*     */   public static final float SIMD_INFINITY = 3.4028235E+38F;
/*  52 */   private static ThreadLocal<BulletGlobals> threadLocal = new ThreadLocal()
/*     */   {
/*     */     protected BulletGlobals initialValue() {
/*  55 */       return new BulletGlobals();
/*     */     }
/*  52 */   };
/*     */   private ContactDestroyedCallback gContactDestroyedCallback;
/*     */   private ContactAddedCallback gContactAddedCallback;
/*     */   private ContactProcessedCallback gContactProcessedCallback;
/*  63 */   private float contactBreakingThreshold = 0.02F;
/*     */ 
/*  65 */   private float deactivationTime = 2.0F;
/*  66 */   private boolean disableDeactivation = false;
/*     */ 
/*     */   public static ContactAddedCallback getContactAddedCallback() {
/*  69 */     return ((BulletGlobals)threadLocal.get()).gContactAddedCallback;
/*     */   }
/*     */ 
/*     */   public static void setContactAddedCallback(ContactAddedCallback callback) {
/*  73 */     ((BulletGlobals)threadLocal.get()).gContactAddedCallback = callback;
/*     */   }
/*     */ 
/*     */   public static ContactDestroyedCallback getContactDestroyedCallback() {
/*  77 */     return ((BulletGlobals)threadLocal.get()).gContactDestroyedCallback;
/*     */   }
/*     */ 
/*     */   public static void setContactDestroyedCallback(ContactDestroyedCallback callback) {
/*  81 */     ((BulletGlobals)threadLocal.get()).gContactDestroyedCallback = callback;
/*     */   }
/*     */ 
/*     */   public static ContactProcessedCallback getContactProcessedCallback() {
/*  85 */     return ((BulletGlobals)threadLocal.get()).gContactProcessedCallback;
/*     */   }
/*     */ 
/*     */   public static void setContactProcessedCallback(ContactProcessedCallback callback) {
/*  89 */     ((BulletGlobals)threadLocal.get()).gContactProcessedCallback = callback;
/*     */   }
/*     */ 
/*     */   public static float getContactBreakingThreshold()
/*     */   {
/*  95 */     return ((BulletGlobals)threadLocal.get()).contactBreakingThreshold;
/*     */   }
/*     */ 
/*     */   public static void setContactBreakingThreshold(float threshold) {
/*  99 */     ((BulletGlobals)threadLocal.get()).contactBreakingThreshold = threshold;
/*     */   }
/*     */ 
/*     */   public static float getDeactivationTime() {
/* 103 */     return ((BulletGlobals)threadLocal.get()).deactivationTime;
/*     */   }
/*     */ 
/*     */   public static void setDeactivationTime(float time) {
/* 107 */     ((BulletGlobals)threadLocal.get()).deactivationTime = time;
/*     */   }
/*     */ 
/*     */   public static boolean isDeactivationDisabled() {
/* 111 */     return ((BulletGlobals)threadLocal.get()).disableDeactivation;
/*     */   }
/*     */ 
/*     */   public static void setDeactivationDisabled(boolean disable) {
/* 115 */     ((BulletGlobals)threadLocal.get()).disableDeactivation = disable;
/*     */   }
/*     */ 
/*     */   public static void cleanCurrentThread()
/*     */   {
/* 124 */     threadLocal.remove();
/* 125 */     .Stack.threadLocal.remove();
/* 126 */     ObjectPool.cleanCurrentThread();
/* 127 */     ArrayPool.cleanCurrentThread();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.BulletGlobals
 * JD-Core Version:    0.6.2
 */