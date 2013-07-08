/*  1:   */package com.bulletphysics.collision.broadphase;
/*  2:   */
/* 12:   */public class DbvtLeafCollider
/* 13:   */  extends Dbvt.ICollide
/* 14:   */{
/* 15:   */  public DbvtBroadphase pbp;
/* 16:   */  
/* 25:   */  public DbvtProxy ppx;
/* 26:   */  
/* 36:   */  public DbvtLeafCollider(DbvtBroadphase p, DbvtProxy px)
/* 37:   */  {
/* 38:38 */    this.pbp = p;
/* 39:39 */    this.ppx = px;
/* 40:   */  }
/* 41:   */  
/* 42:   */  public void Process(Dbvt.Node na)
/* 43:   */  {
/* 44:44 */    Dbvt.Node nb = this.ppx.leaf;
/* 45:45 */    if (nb != na) {
/* 46:46 */      DbvtProxy pa = (DbvtProxy)na.data;
/* 47:47 */      DbvtProxy pb = (DbvtProxy)nb.data;
/* 48:   */      
/* 50:50 */      if (DbvtAabbMm.Intersect(pa.aabb, pb.aabb))
/* 51:   */      {
/* 54:54 */        if (pa.hashCode() > pb.hashCode()) {
/* 55:55 */          DbvtProxy tmp = pa;
/* 56:56 */          pa = pb;
/* 57:57 */          pb = tmp;
/* 58:   */        }
/* 59:59 */        this.pbp.paircache.addOverlappingPair(pa, pb);
/* 60:   */      }
/* 61:   */    }
/* 62:   */  }
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtLeafCollider
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */