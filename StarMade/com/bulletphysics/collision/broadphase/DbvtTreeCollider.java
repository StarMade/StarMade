package com.bulletphysics.collision.broadphase;

public class DbvtTreeCollider
  extends Dbvt.ICollide
{
  public DbvtBroadphase pbp;
  
  public DbvtTreeCollider(DbvtBroadphase local_p)
  {
    this.pbp = local_p;
  }
  
  public void Process(Dbvt.Node local_na, Dbvt.Node local_nb)
  {
    DbvtProxy local_pa = (DbvtProxy)local_na.data;
    DbvtProxy local_pb = (DbvtProxy)local_nb.data;
    if (DbvtAabbMm.Intersect(local_pa.aabb, local_pb.aabb))
    {
      if (local_pa.hashCode() > local_pb.hashCode())
      {
        DbvtProxy tmp = local_pa;
        local_pa = local_pb;
        local_pb = tmp;
      }
      this.pbp.paircache.addOverlappingPair(local_pa, local_pb);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtTreeCollider
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */