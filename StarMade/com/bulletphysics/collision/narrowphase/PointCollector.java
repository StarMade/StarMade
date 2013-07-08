/*  1:   */package com.bulletphysics.collision.narrowphase;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */
/* 31:   */public class PointCollector
/* 32:   */  extends DiscreteCollisionDetectorInterface.Result
/* 33:   */{
/* 34:34 */  public final Vector3f normalOnBInWorld = new Vector3f();
/* 35:35 */  public final Vector3f pointInWorld = new Vector3f();
/* 36:36 */  public float distance = 1.0E+030F;
/* 37:   */  
/* 38:38 */  public boolean hasResult = false;
/* 39:   */  
/* 41:   */  public void setShapeIdentifiers(int partId0, int index0, int partId1, int index1) {}
/* 42:   */  
/* 43:   */  public void addContactPoint(Vector3f normalOnBInWorld, Vector3f pointInWorld, float depth)
/* 44:   */  {
/* 45:45 */    if (depth < this.distance) {
/* 46:46 */      this.hasResult = true;
/* 47:47 */      this.normalOnBInWorld.set(normalOnBInWorld);
/* 48:48 */      this.pointInWorld.set(pointInWorld);
/* 49:   */      
/* 50:50 */      this.distance = depth;
/* 51:   */    }
/* 52:   */  }
/* 53:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.PointCollector
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */