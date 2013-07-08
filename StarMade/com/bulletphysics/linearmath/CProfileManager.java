/*  1:   */package com.bulletphysics.linearmath;
/*  2:   */
/*  3:   */import com.bulletphysics.BulletStats;
/*  4:   */
/* 41:   */public class CProfileManager
/* 42:   */{
/* 43:43 */  private static CProfileNode root = new CProfileNode("Root", null);
/* 44:44 */  private static CProfileNode currentNode = root;
/* 45:45 */  private static int frameCounter = 0;
/* 46:46 */  private static long resetTime = 0L;
/* 47:   */  
/* 50:   */  public static void startProfile(String name)
/* 51:   */  {
/* 52:52 */    if (name != currentNode.getName()) {
/* 53:53 */      currentNode = currentNode.getSubNode(name);
/* 54:   */    }
/* 55:   */    
/* 56:56 */    currentNode.call();
/* 57:   */  }
/* 58:   */  
/* 60:   */  public static void stopProfile()
/* 61:   */  {
/* 62:62 */    if (currentNode.Return()) {
/* 63:63 */      currentNode = currentNode.getParent();
/* 64:   */    }
/* 65:   */  }
/* 66:   */  
/* 67:   */  public static void cleanupMemory() {
/* 68:68 */    root.cleanupMemory();
/* 69:   */  }
/* 70:   */  
/* 71:   */  public static void reset() {
/* 72:72 */    root.reset();
/* 73:73 */    root.call();
/* 74:74 */    frameCounter = 0;
/* 75:75 */    resetTime = BulletStats.profileGetTicks();
/* 76:   */  }
/* 77:   */  
/* 78:   */  public static void incrementFrameCounter() {
/* 79:79 */    frameCounter += 1;
/* 80:   */  }
/* 81:   */  
/* 82:   */  public static int getFrameCountSinceReset() {
/* 83:83 */    return frameCounter;
/* 84:   */  }
/* 85:   */  
/* 86:   */  public static float getTimeSinceReset() {
/* 87:87 */    long time = BulletStats.profileGetTicks();
/* 88:88 */    time -= resetTime;
/* 89:89 */    return (float)time / BulletStats.profileGetTickRate();
/* 90:   */  }
/* 91:   */  
/* 92:   */  public static CProfileIterator getIterator() {
/* 93:93 */    return new CProfileIterator(root);
/* 94:   */  }
/* 95:   */  
/* 96:   */  public static void releaseIterator(CProfileIterator iterator) {}
/* 97:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.CProfileManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */