/*   1:    */package com.bulletphysics;
/*   2:    */
/*   3:    */import com.bulletphysics.util.ArrayPool;
/*   4:    */import com.bulletphysics.util.ObjectPool;
/*   5:    */
/*  40:    */public class BulletGlobals
/*  41:    */{
/*  42:    */  public static final boolean DEBUG = false;
/*  43:    */  public static final float CONVEX_DISTANCE_MARGIN = 0.04F;
/*  44:    */  public static final float FLT_EPSILON = 1.192093E-007F;
/*  45:    */  public static final float SIMD_EPSILON = 1.192093E-007F;
/*  46:    */  public static final float SIMD_2_PI = 6.283186F;
/*  47:    */  public static final float SIMD_PI = 3.141593F;
/*  48:    */  public static final float SIMD_HALF_PI = 1.570796F;
/*  49:    */  public static final float SIMD_RADS_PER_DEG = 0.01745329F;
/*  50:    */  public static final float SIMD_DEGS_PER_RAD = 57.295776F;
/*  51:    */  public static final float SIMD_INFINITY = 3.4028235E+38F;
/*  52: 52 */  private static ThreadLocal<BulletGlobals> threadLocal = new ThreadLocal()
/*  53:    */  {
/*  54:    */    protected BulletGlobals initialValue() {
/*  55: 55 */      return new BulletGlobals();
/*  56:    */    }
/*  57:    */  };
/*  58:    */  
/*  59:    */  private ContactDestroyedCallback gContactDestroyedCallback;
/*  60:    */  
/*  61:    */  private ContactAddedCallback gContactAddedCallback;
/*  62:    */  private ContactProcessedCallback gContactProcessedCallback;
/*  63: 63 */  private float contactBreakingThreshold = 0.02F;
/*  64:    */  
/*  65: 65 */  private float deactivationTime = 2.0F;
/*  66: 66 */  private boolean disableDeactivation = false;
/*  67:    */  
/*  68:    */  public static ContactAddedCallback getContactAddedCallback() {
/*  69: 69 */    return ((BulletGlobals)threadLocal.get()).gContactAddedCallback;
/*  70:    */  }
/*  71:    */  
/*  72:    */  public static void setContactAddedCallback(ContactAddedCallback callback) {
/*  73: 73 */    ((BulletGlobals)threadLocal.get()).gContactAddedCallback = callback;
/*  74:    */  }
/*  75:    */  
/*  76:    */  public static ContactDestroyedCallback getContactDestroyedCallback() {
/*  77: 77 */    return ((BulletGlobals)threadLocal.get()).gContactDestroyedCallback;
/*  78:    */  }
/*  79:    */  
/*  80:    */  public static void setContactDestroyedCallback(ContactDestroyedCallback callback) {
/*  81: 81 */    ((BulletGlobals)threadLocal.get()).gContactDestroyedCallback = callback;
/*  82:    */  }
/*  83:    */  
/*  84:    */  public static ContactProcessedCallback getContactProcessedCallback() {
/*  85: 85 */    return ((BulletGlobals)threadLocal.get()).gContactProcessedCallback;
/*  86:    */  }
/*  87:    */  
/*  88:    */  public static void setContactProcessedCallback(ContactProcessedCallback callback) {
/*  89: 89 */    ((BulletGlobals)threadLocal.get()).gContactProcessedCallback = callback;
/*  90:    */  }
/*  91:    */  
/*  93:    */  public static float getContactBreakingThreshold()
/*  94:    */  {
/*  95: 95 */    return ((BulletGlobals)threadLocal.get()).contactBreakingThreshold;
/*  96:    */  }
/*  97:    */  
/*  98:    */  public static void setContactBreakingThreshold(float threshold) {
/*  99: 99 */    ((BulletGlobals)threadLocal.get()).contactBreakingThreshold = threshold;
/* 100:    */  }
/* 101:    */  
/* 102:    */  public static float getDeactivationTime() {
/* 103:103 */    return ((BulletGlobals)threadLocal.get()).deactivationTime;
/* 104:    */  }
/* 105:    */  
/* 106:    */  public static void setDeactivationTime(float time) {
/* 107:107 */    ((BulletGlobals)threadLocal.get()).deactivationTime = time;
/* 108:    */  }
/* 109:    */  
/* 110:    */  public static boolean isDeactivationDisabled() {
/* 111:111 */    return ((BulletGlobals)threadLocal.get()).disableDeactivation;
/* 112:    */  }
/* 113:    */  
/* 114:    */  public static void setDeactivationDisabled(boolean disable) {
/* 115:115 */    ((BulletGlobals)threadLocal.get()).disableDeactivation = disable;
/* 116:    */  }
/* 117:    */  
/* 122:    */  public static void cleanCurrentThread()
/* 123:    */  {
/* 124:124 */    threadLocal.remove();
/* 125:125 */    .Stack.threadLocal.remove();
/* 126:126 */    ObjectPool.cleanCurrentThread();
/* 127:127 */    ArrayPool.cleanCurrentThread();
/* 128:    */  }
/* 129:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.BulletGlobals
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */