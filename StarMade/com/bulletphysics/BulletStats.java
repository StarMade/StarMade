/*     */ package com.bulletphysics;
/*     */ 
/*     */ import com.bulletphysics.linearmath.CProfileManager;
/*     */ import com.bulletphysics.linearmath.Clock;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class BulletStats
/*     */ {
/*     */   public static int gTotalContactPoints;
/*  41 */   public static int gNumDeepPenetrationChecks = 0;
/*  42 */   public static int gNumGjkChecks = 0;
/*  43 */   public static int gNumSplitImpulseRecoveries = 0;
/*     */   public static int gNumAlignedAllocs;
/*     */   public static int gNumAlignedFree;
/*     */   public static int gTotalBytesAlignedAllocs;
/*  49 */   public static int gPickingConstraintId = 0;
/*  50 */   public static final Vector3f gOldPickingPos = new Vector3f();
/*  51 */   public static float gOldPickingDist = 0.0F;
/*     */ 
/*  53 */   public static int gOverlappingPairs = 0;
/*  54 */   public static int gRemovePairs = 0;
/*  55 */   public static int gAddedPairs = 0;
/*  56 */   public static int gFindPairs = 0;
/*     */ 
/*  58 */   public static final Clock gProfileClock = new Clock();
/*     */ 
/*  61 */   public static int gNumClampedCcdMotions = 0;
/*     */   public static long stepSimulationTime;
/*     */   public static long updateTime;
/*  67 */   private static boolean enableProfile = false;
/*     */ 
/*     */   public static boolean isProfileEnabled()
/*     */   {
/*  72 */     return enableProfile;
/*     */   }
/*     */ 
/*     */   public static void setProfileEnabled(boolean b) {
/*  76 */     enableProfile = b;
/*     */   }
/*     */ 
/*     */   public static long profileGetTicks() {
/*  80 */     long ticks = gProfileClock.getTimeMicroseconds();
/*  81 */     return ticks;
/*     */   }
/*     */ 
/*     */   public static float profileGetTickRate()
/*     */   {
/*  86 */     return 1000.0F;
/*     */   }
/*     */ 
/*     */   public static void pushProfile(String name)
/*     */   {
/*  95 */     if (enableProfile)
/*  96 */       CProfileManager.startProfile(name);
/*     */   }
/*     */ 
/*     */   public static void popProfile()
/*     */   {
/* 104 */     if (enableProfile)
/* 105 */       CProfileManager.stopProfile();
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.BulletStats
 * JD-Core Version:    0.6.2
 */