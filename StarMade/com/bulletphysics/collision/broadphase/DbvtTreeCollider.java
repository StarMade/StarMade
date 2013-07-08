/*  1:   */package com.bulletphysics.collision.broadphase;
/*  2:   */
/* 17:   */public class DbvtTreeCollider
/* 18:   */  extends Dbvt.ICollide
/* 19:   */{
/* 20:   */  public DbvtBroadphase pbp;
/* 21:   */  
/* 35:   */  public DbvtTreeCollider(DbvtBroadphase p)
/* 36:   */  {
/* 37:37 */    this.pbp = p;
/* 38:   */  }
/* 39:   */  
/* 40:   */  public void Process(Dbvt.Node na, Dbvt.Node nb)
/* 41:   */  {
/* 42:42 */    DbvtProxy pa = (DbvtProxy)na.data;
/* 43:43 */    DbvtProxy pb = (DbvtProxy)nb.data;
/* 44:   */    
/* 45:45 */    if (DbvtAabbMm.Intersect(pa.aabb, pb.aabb))
/* 46:   */    {
/* 49:49 */      if (pa.hashCode() > pb.hashCode()) {
/* 50:50 */        DbvtProxy tmp = pa;
/* 51:51 */        pa = pb;
/* 52:52 */        pb = tmp;
/* 53:   */      }
/* 54:54 */      this.pbp.paircache.addOverlappingPair(pa, pb);
/* 55:   */    }
/* 56:   */  }
/* 57:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtTreeCollider
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */