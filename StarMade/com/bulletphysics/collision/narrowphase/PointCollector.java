/*    */ package com.bulletphysics.collision.narrowphase;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class PointCollector extends DiscreteCollisionDetectorInterface.Result
/*    */ {
/* 34 */   public final Vector3f normalOnBInWorld = new Vector3f();
/* 35 */   public final Vector3f pointInWorld = new Vector3f();
/* 36 */   public float distance = 1.0E+030F;
/*    */ 
/* 38 */   public boolean hasResult = false;
/*    */ 
/*    */   public void setShapeIdentifiers(int partId0, int index0, int partId1, int index1)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void addContactPoint(Vector3f normalOnBInWorld, Vector3f pointInWorld, float depth) {
/* 45 */     if (depth < this.distance) {
/* 46 */       this.hasResult = true;
/* 47 */       this.normalOnBInWorld.set(normalOnBInWorld);
/* 48 */       this.pointInWorld.set(pointInWorld);
/*    */ 
/* 50 */       this.distance = depth;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.PointCollector
 * JD-Core Version:    0.6.2
 */