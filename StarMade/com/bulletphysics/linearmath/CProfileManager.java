package com.bulletphysics.linearmath;

import com.bulletphysics.BulletStats;

public class CProfileManager
{
  private static CProfileNode root = new CProfileNode("Root", null);
  private static CProfileNode currentNode = root;
  private static int frameCounter = 0;
  private static long resetTime = 0L;
  
  public static void startProfile(String name)
  {
    if (name != currentNode.getName()) {
      currentNode = currentNode.getSubNode(name);
    }
    currentNode.call();
  }
  
  public static void stopProfile()
  {
    if (currentNode.Return()) {
      currentNode = currentNode.getParent();
    }
  }
  
  public static void cleanupMemory()
  {
    root.cleanupMemory();
  }
  
  public static void reset()
  {
    root.reset();
    root.call();
    frameCounter = 0;
    resetTime = BulletStats.profileGetTicks();
  }
  
  public static void incrementFrameCounter()
  {
    frameCounter += 1;
  }
  
  public static int getFrameCountSinceReset()
  {
    return frameCounter;
  }
  
  public static float getTimeSinceReset()
  {
    long time = BulletStats.profileGetTicks();
    time -= resetTime;
    return (float)time / BulletStats.profileGetTickRate();
  }
  
  public static CProfileIterator getIterator()
  {
    return new CProfileIterator(root);
  }
  
  public static void releaseIterator(CProfileIterator iterator) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.CProfileManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */