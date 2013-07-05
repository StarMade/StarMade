/*    */ package com.bulletphysics.collision.narrowphase;
/*    */ 
/*    */ import com.bulletphysics.collision.shapes.ConvexShape;
/*    */ import com.bulletphysics.collision.shapes.TriangleCallback;
/*    */ import com.bulletphysics.collision.shapes.TriangleShape;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public abstract class TriangleConvexcastCallback extends TriangleCallback
/*    */ {
/*    */   public ConvexShape convexShape;
/* 40 */   public final Transform convexShapeFrom = new Transform();
/* 41 */   public final Transform convexShapeTo = new Transform();
/* 42 */   public final Transform triangleToWorld = new Transform();
/*    */   public float hitFraction;
/*    */   public float triangleCollisionMargin;
/*    */ 
/*    */   public TriangleConvexcastCallback(ConvexShape convexShape, Transform convexShapeFrom, Transform convexShapeTo, Transform triangleToWorld, float triangleCollisionMargin)
/*    */   {
/* 47 */     this.convexShape = convexShape;
/* 48 */     this.convexShapeFrom.set(convexShapeFrom);
/* 49 */     this.convexShapeTo.set(convexShapeTo);
/* 50 */     this.triangleToWorld.set(triangleToWorld);
/* 51 */     this.hitFraction = 1.0F;
/* 52 */     this.triangleCollisionMargin = triangleCollisionMargin;
/*    */   }
/*    */ 
/*    */   public void processTriangle(Vector3f[] triangle, int partId, int triangleIndex) {
/* 56 */     TriangleShape triangleShape = new TriangleShape(triangle[0], triangle[1], triangle[2]);
/* 57 */     triangleShape.setMargin(this.triangleCollisionMargin);
/*    */ 
/* 59 */     VoronoiSimplexSolver simplexSolver = new VoronoiSimplexSolver();
/* 60 */     GjkEpaPenetrationDepthSolver gjkEpaPenetrationSolver = new GjkEpaPenetrationDepthSolver();
/*    */ 
/* 66 */     SubsimplexConvexCast convexCaster = new SubsimplexConvexCast(this.convexShape, triangleShape, simplexSolver);
/*    */ 
/* 72 */     ConvexCast.CastResult castResult = new ConvexCast.CastResult();
/* 73 */     castResult.fraction = 1.0F;
/* 74 */     if (convexCaster.calcTimeOfImpact(this.convexShapeFrom, this.convexShapeTo, this.triangleToWorld, this.triangleToWorld, castResult))
/*    */     {
/* 76 */       if ((castResult.normal.lengthSquared() > 1.0E-004F) && 
/* 77 */         (castResult.fraction < this.hitFraction))
/*    */       {
/* 86 */         castResult.normal.normalize();
/*    */ 
/* 88 */         reportHit(castResult.normal, castResult.hitPoint, castResult.fraction, partId, triangleIndex);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public abstract float reportHit(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, int paramInt1, int paramInt2);
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.narrowphase.TriangleConvexcastCallback
 * JD-Core Version:    0.6.2
 */