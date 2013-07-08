/*  1:   */package com.bulletphysics.collision.broadphase;
/*  2:   */
/*  3:   */import java.util.Comparator;
/*  4:   */
/* 35:   */public class BroadphasePair
/* 36:   */{
/* 37:   */  public BroadphaseProxy pProxy0;
/* 38:   */  public BroadphaseProxy pProxy1;
/* 39:   */  public CollisionAlgorithm algorithm;
/* 40:   */  public Object userInfo;
/* 41:   */  
/* 42:   */  public BroadphasePair() {}
/* 43:   */  
/* 44:   */  public BroadphasePair(BroadphaseProxy pProxy0, BroadphaseProxy pProxy1)
/* 45:   */  {
/* 46:46 */    this.pProxy0 = pProxy0;
/* 47:47 */    this.pProxy1 = pProxy1;
/* 48:48 */    this.algorithm = null;
/* 49:49 */    this.userInfo = null;
/* 50:   */  }
/* 51:   */  
/* 52:   */  public void set(BroadphasePair p) {
/* 53:53 */    this.pProxy0 = p.pProxy0;
/* 54:54 */    this.pProxy1 = p.pProxy1;
/* 55:55 */    this.algorithm = p.algorithm;
/* 56:56 */    this.userInfo = p.userInfo;
/* 57:   */  }
/* 58:   */  
/* 59:   */  public boolean equals(BroadphasePair p) {
/* 60:60 */    return (this.pProxy0 == p.pProxy0) && (this.pProxy1 == p.pProxy1);
/* 61:   */  }
/* 62:   */  
/* 63:63 */  public static final Comparator<BroadphasePair> broadphasePairSortPredicate = new Comparator()
/* 64:   */  {
/* 65:   */    public int compare(BroadphasePair a, BroadphasePair b) {
/* 66:66 */      boolean result = (a.pProxy0.getUid() > b.pProxy0.getUid()) || ((a.pProxy0.getUid() == b.pProxy0.getUid()) && (a.pProxy1.getUid() > b.pProxy1.getUid())) || ((a.pProxy0.getUid() == b.pProxy0.getUid()) && (a.pProxy1.getUid() == b.pProxy1.getUid()));
/* 67:   */      
/* 69:69 */      return result ? -1 : 1;
/* 70:   */    }
/* 71:   */  };
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.BroadphasePair
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */