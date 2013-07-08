package com.bulletphysics.collision.broadphase;

import java.util.Comparator;

public class BroadphasePair
{
  public BroadphaseProxy pProxy0;
  public BroadphaseProxy pProxy1;
  public CollisionAlgorithm algorithm;
  public Object userInfo;
  public static final Comparator<BroadphasePair> broadphasePairSortPredicate = new Comparator()
  {
    public int compare(BroadphasePair local_a, BroadphasePair local_b)
    {
      boolean result = (local_a.pProxy0.getUid() > local_b.pProxy0.getUid()) || ((local_a.pProxy0.getUid() == local_b.pProxy0.getUid()) && (local_a.pProxy1.getUid() > local_b.pProxy1.getUid())) || ((local_a.pProxy0.getUid() == local_b.pProxy0.getUid()) && (local_a.pProxy1.getUid() == local_b.pProxy1.getUid()));
      return result ? -1 : 1;
    }
  };
  
  public BroadphasePair() {}
  
  public BroadphasePair(BroadphaseProxy pProxy0, BroadphaseProxy pProxy1)
  {
    this.pProxy0 = pProxy0;
    this.pProxy1 = pProxy1;
    this.algorithm = null;
    this.userInfo = null;
  }
  
  public void set(BroadphasePair local_p)
  {
    this.pProxy0 = local_p.pProxy0;
    this.pProxy1 = local_p.pProxy1;
    this.algorithm = local_p.algorithm;
    this.userInfo = local_p.userInfo;
  }
  
  public boolean equals(BroadphasePair local_p)
  {
    return (this.pProxy0 == local_p.pProxy0) && (this.pProxy1 == local_p.pProxy1);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.BroadphasePair
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */