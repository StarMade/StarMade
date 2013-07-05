/*    */ package com.bulletphysics.collision.broadphase;
/*    */ 
/*    */ public class DbvtTreeCollider extends Dbvt.ICollide
/*    */ {
/*    */   public DbvtBroadphase pbp;
/*    */ 
/*    */   public DbvtTreeCollider(DbvtBroadphase p)
/*    */   {
/* 37 */     this.pbp = p;
/*    */   }
/*    */ 
/*    */   public void Process(Dbvt.Node na, Dbvt.Node nb)
/*    */   {
/* 42 */     DbvtProxy pa = (DbvtProxy)na.data;
/* 43 */     DbvtProxy pb = (DbvtProxy)nb.data;
/*    */ 
/* 45 */     if (DbvtAabbMm.Intersect(pa.aabb, pb.aabb))
/*    */     {
/* 49 */       if (pa.hashCode() > pb.hashCode()) {
/* 50 */         DbvtProxy tmp = pa;
/* 51 */         pa = pb;
/* 52 */         pb = tmp;
/*    */       }
/* 54 */       this.pbp.paircache.addOverlappingPair(pa, pb);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtTreeCollider
 * JD-Core Version:    0.6.2
 */