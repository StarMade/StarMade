/*    */ package com.bulletphysics.collision.narrowphase;
/*    */ 
/*    */ import com.bulletphysics.linearmath.IDebugDraw;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public abstract class DiscreteCollisionDetectorInterface
/*    */ {
/*    */   public final void getClosestPoints(ClosestPointInput input, Result output, IDebugDraw debugDraw)
/*    */   {
/* 69 */     getClosestPoints(input, output, debugDraw, false);
/*    */   }
/*    */ 
/*    */   public abstract void getClosestPoints(ClosestPointInput paramClosestPointInput, Result paramResult, IDebugDraw paramIDebugDraw, boolean paramBoolean);
/*    */ 
/*    */   public static class ClosestPointInput
/*    */   {
/* 50 */     public final Transform transformA = new Transform();
/* 51 */     public final Transform transformB = new Transform();
/*    */     public float maximumDistanceSquared;
/*    */ 
/*    */     public ClosestPointInput()
/*    */     {
/* 56 */       init();
/*    */     }
/*    */ 
/*    */     public void init() {
/* 60 */       this.maximumDistanceSquared = 3.4028235E+38F;
/*    */     }
/*    */   }
/*    */ 
/*    */   public static abstract class Result
/*    */   {
/*    */     public abstract void setShapeIdentifiers(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*    */ 
/*    */     public abstract void addContactPoint(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.DiscreteCollisionDetectorInterface
 * JD-Core Version:    0.6.2
 */