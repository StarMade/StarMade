/*  1:   */package com.bulletphysics.collision.broadphase;
/*  2:   */
/*  9:   */public class BroadphaseProxy
/* 10:   */{
/* 11:   */  public Object clientObject;
/* 12:   */  
/* 18:   */  public short collisionFilterGroup;
/* 19:   */  
/* 25:   */  public short collisionFilterMask;
/* 26:   */  
/* 31:   */  public Object multiSapParentProxy;
/* 32:   */  
/* 37:   */  public int uniqueId;
/* 38:   */  
/* 44:   */  public BroadphaseProxy() {}
/* 45:   */  
/* 51:   */  public BroadphaseProxy(Object userPtr, short collisionFilterGroup, short collisionFilterMask)
/* 52:   */  {
/* 53:53 */    this(userPtr, collisionFilterGroup, collisionFilterMask, null);
/* 54:   */  }
/* 55:   */  
/* 56:   */  public BroadphaseProxy(Object userPtr, short collisionFilterGroup, short collisionFilterMask, Object multiSapParentProxy) {
/* 57:57 */    this.clientObject = userPtr;
/* 58:58 */    this.collisionFilterGroup = collisionFilterGroup;
/* 59:59 */    this.collisionFilterMask = collisionFilterMask;
/* 60:60 */    this.multiSapParentProxy = multiSapParentProxy;
/* 61:   */  }
/* 62:   */  
/* 63:   */  public int getUid() {
/* 64:64 */    return this.uniqueId;
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.BroadphaseProxy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */