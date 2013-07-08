/*   1:    */package com.bulletphysics;
/*   2:    */
/*   3:    */import com.bulletphysics.linearmath.CProfileManager;
/*   4:    */import com.bulletphysics.linearmath.Clock;
/*   5:    */import javax.vecmath.Vector3f;
/*   6:    */
/*  38:    */public class BulletStats
/*  39:    */{
/*  40:    */  public static int gTotalContactPoints;
/*  41: 41 */  public static int gNumDeepPenetrationChecks = 0;
/*  42: 42 */  public static int gNumGjkChecks = 0;
/*  43: 43 */  public static int gNumSplitImpulseRecoveries = 0;
/*  44:    */  
/*  45:    */  public static int gNumAlignedAllocs;
/*  46:    */  
/*  47:    */  public static int gNumAlignedFree;
/*  48:    */  public static int gTotalBytesAlignedAllocs;
/*  49: 49 */  public static int gPickingConstraintId = 0;
/*  50: 50 */  public static final Vector3f gOldPickingPos = new Vector3f();
/*  51: 51 */  public static float gOldPickingDist = 0.0F;
/*  52:    */  
/*  53: 53 */  public static int gOverlappingPairs = 0;
/*  54: 54 */  public static int gRemovePairs = 0;
/*  55: 55 */  public static int gAddedPairs = 0;
/*  56: 56 */  public static int gFindPairs = 0;
/*  57:    */  
/*  58: 58 */  public static final Clock gProfileClock = new Clock();
/*  59:    */  
/*  61: 61 */  public static int gNumClampedCcdMotions = 0;
/*  62:    */  
/*  63:    */  public static long stepSimulationTime;
/*  64:    */  
/*  65:    */  public static long updateTime;
/*  66:    */  
/*  67: 67 */  private static boolean enableProfile = false;
/*  68:    */  
/*  70:    */  public static boolean isProfileEnabled()
/*  71:    */  {
/*  72: 72 */    return enableProfile;
/*  73:    */  }
/*  74:    */  
/*  75:    */  public static void setProfileEnabled(boolean b) {
/*  76: 76 */    enableProfile = b;
/*  77:    */  }
/*  78:    */  
/*  79:    */  public static long profileGetTicks() {
/*  80: 80 */    long ticks = gProfileClock.getTimeMicroseconds();
/*  81: 81 */    return ticks;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public static float profileGetTickRate()
/*  85:    */  {
/*  86: 86 */    return 1000.0F;
/*  87:    */  }
/*  88:    */  
/*  93:    */  public static void pushProfile(String name)
/*  94:    */  {
/*  95: 95 */    if (enableProfile) {
/*  96: 96 */      CProfileManager.startProfile(name);
/*  97:    */    }
/*  98:    */  }
/*  99:    */  
/* 102:    */  public static void popProfile()
/* 103:    */  {
/* 104:104 */    if (enableProfile) {
/* 105:105 */      CProfileManager.stopProfile();
/* 106:    */    }
/* 107:    */  }
/* 108:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.BulletStats
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */