package com.bulletphysics.collision.broadphase;

public class BroadphaseProxy
{
  public Object clientObject;
  public short collisionFilterGroup;
  public short collisionFilterMask;
  public Object multiSapParentProxy;
  public int uniqueId;
  
  public BroadphaseProxy() {}
  
  public BroadphaseProxy(Object userPtr, short collisionFilterGroup, short collisionFilterMask)
  {
    this(userPtr, collisionFilterGroup, collisionFilterMask, null);
  }
  
  public BroadphaseProxy(Object userPtr, short collisionFilterGroup, short collisionFilterMask, Object multiSapParentProxy)
  {
    this.clientObject = userPtr;
    this.collisionFilterGroup = collisionFilterGroup;
    this.collisionFilterMask = collisionFilterMask;
    this.multiSapParentProxy = multiSapParentProxy;
  }
  
  public int getUid()
  {
    return this.uniqueId;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.BroadphaseProxy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */