/*  1:   */package com.bulletphysics.collision.broadphase;
/*  2:   */
/* 31:   */public class DbvtProxy
/* 32:   */  extends BroadphaseProxy
/* 33:   */{
/* 34:34 */  public final DbvtAabbMm aabb = new DbvtAabbMm();
/* 35:   */  public Dbvt.Node leaf;
/* 36:36 */  public final DbvtProxy[] links = new DbvtProxy[2];
/* 37:   */  public int stage;
/* 38:   */  
/* 39:   */  public DbvtProxy(Object userPtr, short collisionFilterGroup, short collisionFilterMask) {
/* 40:40 */    super(userPtr, collisionFilterGroup, collisionFilterMask);
/* 41:   */  }
/* 42:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtProxy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */