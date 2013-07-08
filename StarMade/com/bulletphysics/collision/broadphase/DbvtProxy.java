package com.bulletphysics.collision.broadphase;

public class DbvtProxy
  extends BroadphaseProxy
{
  public final DbvtAabbMm aabb = new DbvtAabbMm();
  public Dbvt.Node leaf;
  public final DbvtProxy[] links = new DbvtProxy[2];
  public int stage;
  
  public DbvtProxy(Object userPtr, short collisionFilterGroup, short collisionFilterMask)
  {
    super(userPtr, collisionFilterGroup, collisionFilterMask);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtProxy
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */