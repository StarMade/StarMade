package com.bulletphysics;

import com.bulletphysics.linearmath.CProfileManager;
import com.bulletphysics.linearmath.Clock;
import javax.vecmath.Vector3f;

public class BulletStats
{
  public static int gTotalContactPoints;
  public static int gNumDeepPenetrationChecks = 0;
  public static int gNumGjkChecks = 0;
  public static int gNumSplitImpulseRecoveries = 0;
  public static int gNumAlignedAllocs;
  public static int gNumAlignedFree;
  public static int gTotalBytesAlignedAllocs;
  public static int gPickingConstraintId = 0;
  public static final Vector3f gOldPickingPos = new Vector3f();
  public static float gOldPickingDist = 0.0F;
  public static int gOverlappingPairs = 0;
  public static int gRemovePairs = 0;
  public static int gAddedPairs = 0;
  public static int gFindPairs = 0;
  public static final Clock gProfileClock = new Clock();
  public static int gNumClampedCcdMotions = 0;
  public static long stepSimulationTime;
  public static long updateTime;
  private static boolean enableProfile = false;
  
  public static boolean isProfileEnabled()
  {
    return enableProfile;
  }
  
  public static void setProfileEnabled(boolean local_b)
  {
    enableProfile = local_b;
  }
  
  public static long profileGetTicks()
  {
    long ticks = gProfileClock.getTimeMicroseconds();
    return ticks;
  }
  
  public static float profileGetTickRate()
  {
    return 1000.0F;
  }
  
  public static void pushProfile(String name)
  {
    if (enableProfile) {
      CProfileManager.startProfile(name);
    }
  }
  
  public static void popProfile()
  {
    if (enableProfile) {
      CProfileManager.stopProfile();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.BulletStats
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */