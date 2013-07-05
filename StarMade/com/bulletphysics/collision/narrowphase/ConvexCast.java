/*    */ package com.bulletphysics.collision.narrowphase;
/*    */ 
/*    */ import com.bulletphysics.linearmath.IDebugDraw;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public abstract class ConvexCast
/*    */ {
/*    */   public abstract boolean calcTimeOfImpact(Transform paramTransform1, Transform paramTransform2, Transform paramTransform3, Transform paramTransform4, CastResult paramCastResult);
/*    */ 
/*    */   public static class CastResult
/*    */   {
/* 49 */     public final Transform hitTransformA = new Transform();
/* 50 */     public final Transform hitTransformB = new Transform();
/*    */ 
/* 52 */     public final Vector3f normal = new Vector3f();
/* 53 */     public final Vector3f hitPoint = new Vector3f();
/* 54 */     public float fraction = 1.0E+030F;
/* 55 */     public float allowedPenetration = 0.0F;
/*    */     public IDebugDraw debugDrawer;
/*    */ 
/*    */     public void debugDraw(float fraction)
/*    */     {
/*    */     }
/*    */ 
/*    */     public void drawCoordSystem(Transform trans)
/*    */     {
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.ConvexCast
 * JD-Core Version:    0.6.2
 */