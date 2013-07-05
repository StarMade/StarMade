/*    */ package com.bulletphysics.collision.broadphase;
/*    */ 
/*    */ public class DbvtProxy extends BroadphaseProxy
/*    */ {
/* 34 */   public final DbvtAabbMm aabb = new DbvtAabbMm();
/*    */   public Dbvt.Node leaf;
/* 36 */   public final DbvtProxy[] links = new DbvtProxy[2];
/*    */   public int stage;
/*    */ 
/*    */   public DbvtProxy(Object userPtr, short collisionFilterGroup, short collisionFilterMask)
/*    */   {
/* 40 */     super(userPtr, collisionFilterGroup, collisionFilterMask);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.DbvtProxy
 * JD-Core Version:    0.6.2
 */