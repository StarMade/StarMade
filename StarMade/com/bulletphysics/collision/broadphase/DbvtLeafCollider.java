/*    */ package com.bulletphysics.collision.broadphase;
/*    */ 
/*    */ public class DbvtLeafCollider extends Dbvt.ICollide
/*    */ {
/*    */   public DbvtBroadphase pbp;
/*    */   public DbvtProxy ppx;
/*    */ 
/*    */   public DbvtLeafCollider(DbvtBroadphase p, DbvtProxy px)
/*    */   {
/* 38 */     this.pbp = p;
/* 39 */     this.ppx = px;
/*    */   }
/*    */ 
/*    */   public void Process(Dbvt.Node na)
/*    */   {
/* 44 */     Dbvt.Node nb = this.ppx.leaf;
/* 45 */     if (nb != na) {
/* 46 */       DbvtProxy pa = (DbvtProxy)na.data;
/* 47 */       DbvtProxy pb = (DbvtProxy)nb.data;
/*    */ 
/* 50 */       if (DbvtAabbMm.Intersect(pa.aabb, pb.aabb))
/*    */       {
/* 54 */         if (pa.hashCode() > pb.hashCode()) {
/* 55 */           DbvtProxy tmp = pa;
/* 56 */           pa = pb;
/* 57 */           pb = tmp;
/*    */         }
/* 59 */         this.pbp.paircache.addOverlappingPair(pa, pb);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtLeafCollider
 * JD-Core Version:    0.6.2
 */