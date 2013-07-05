/*    */ package com.bulletphysics.collision.broadphase;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class BroadphasePair
/*    */ {
/*    */   public BroadphaseProxy pProxy0;
/*    */   public BroadphaseProxy pProxy1;
/*    */   public CollisionAlgorithm algorithm;
/*    */   public Object userInfo;
/* 63 */   public static final Comparator<BroadphasePair> broadphasePairSortPredicate = new Comparator()
/*    */   {
/*    */     public int compare(BroadphasePair a, BroadphasePair b) {
/* 66 */       boolean result = (a.pProxy0.getUid() > b.pProxy0.getUid()) || ((a.pProxy0.getUid() == b.pProxy0.getUid()) && (a.pProxy1.getUid() > b.pProxy1.getUid())) || ((a.pProxy0.getUid() == b.pProxy0.getUid()) && (a.pProxy1.getUid() == b.pProxy1.getUid()));
/*    */ 
/* 69 */       return result ? -1 : 1;
/*    */     }
/* 63 */   };
/*    */ 
/*    */   public BroadphasePair()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BroadphasePair(BroadphaseProxy pProxy0, BroadphaseProxy pProxy1)
/*    */   {
/* 46 */     this.pProxy0 = pProxy0;
/* 47 */     this.pProxy1 = pProxy1;
/* 48 */     this.algorithm = null;
/* 49 */     this.userInfo = null;
/*    */   }
/*    */ 
/*    */   public void set(BroadphasePair p) {
/* 53 */     this.pProxy0 = p.pProxy0;
/* 54 */     this.pProxy1 = p.pProxy1;
/* 55 */     this.algorithm = p.algorithm;
/* 56 */     this.userInfo = p.userInfo;
/*    */   }
/*    */ 
/*    */   public boolean equals(BroadphasePair p) {
/* 60 */     return (this.pProxy0 == p.pProxy0) && (this.pProxy1 == p.pProxy1);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.BroadphasePair
 * JD-Core Version:    0.6.2
 */