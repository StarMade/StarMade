/*    */ package com.bulletphysics.linearmath;
/*    */ 
/*    */ import com.bulletphysics.BulletStats;
/*    */ 
/*    */ public class CProfileManager
/*    */ {
/* 43 */   private static CProfileNode root = new CProfileNode("Root", null);
/* 44 */   private static CProfileNode currentNode = root;
/* 45 */   private static int frameCounter = 0;
/* 46 */   private static long resetTime = 0L;
/*    */ 
/*    */   public static void startProfile(String name)
/*    */   {
/* 52 */     if (name != currentNode.getName()) {
/* 53 */       currentNode = currentNode.getSubNode(name);
/*    */     }
/*    */ 
/* 56 */     currentNode.call();
/*    */   }
/*    */ 
/*    */   public static void stopProfile()
/*    */   {
/* 62 */     if (currentNode.Return())
/* 63 */       currentNode = currentNode.getParent();
/*    */   }
/*    */ 
/*    */   public static void cleanupMemory()
/*    */   {
/* 68 */     root.cleanupMemory();
/*    */   }
/*    */ 
/*    */   public static void reset() {
/* 72 */     root.reset();
/* 73 */     root.call();
/* 74 */     frameCounter = 0;
/* 75 */     resetTime = BulletStats.profileGetTicks();
/*    */   }
/*    */ 
/*    */   public static void incrementFrameCounter() {
/* 79 */     frameCounter += 1;
/*    */   }
/*    */ 
/*    */   public static int getFrameCountSinceReset() {
/* 83 */     return frameCounter;
/*    */   }
/*    */ 
/*    */   public static float getTimeSinceReset() {
/* 87 */     long time = BulletStats.profileGetTicks();
/* 88 */     time -= resetTime;
/* 89 */     return (float)time / BulletStats.profileGetTickRate();
/*    */   }
/*    */ 
/*    */   public static CProfileIterator getIterator() {
/* 93 */     return new CProfileIterator(root);
/*    */   }
/*    */ 
/*    */   public static void releaseIterator(CProfileIterator iterator)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.CProfileManager
 * JD-Core Version:    0.6.2
 */