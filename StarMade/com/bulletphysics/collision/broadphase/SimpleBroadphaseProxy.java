/*  1:   */package com.bulletphysics.collision.broadphase;
/*  2:   */
/*  3:   */import javax.vecmath.Vector3f;
/*  4:   */
/* 31:   */class SimpleBroadphaseProxy
/* 32:   */  extends BroadphaseProxy
/* 33:   */{
/* 34:34 */  protected final Vector3f min = new Vector3f();
/* 35:35 */  protected final Vector3f max = new Vector3f();
/* 36:   */  
/* 37:   */  public SimpleBroadphaseProxy() {}
/* 38:   */  
/* 39:   */  public SimpleBroadphaseProxy(Vector3f minpt, Vector3f maxpt, BroadphaseNativeType shapeType, Object userPtr, short collisionFilterGroup, short collisionFilterMask, Object multiSapProxy)
/* 40:   */  {
/* 41:41 */    super(userPtr, collisionFilterGroup, collisionFilterMask, multiSapProxy);
/* 42:42 */    this.min.set(minpt);
/* 43:43 */    this.max.set(maxpt);
/* 44:   */  }
/* 45:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.SimpleBroadphaseProxy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */