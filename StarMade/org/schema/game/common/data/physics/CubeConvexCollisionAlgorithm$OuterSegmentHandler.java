/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*     */ import com.bulletphysics.collision.dispatch.ManifoldResult;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import jM;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ 
/*     */ class CubeConvexCollisionAlgorithm$OuterSegmentHandler
/*     */   implements jM
/*     */ {
/*     */   CubeShape cubeShape0;
/*     */   CollisionObject col1;
/*     */   DispatcherInfo dispatchInfo;
/*     */   ManifoldResult resultOut;
/*     */ 
/*     */   private CubeConvexCollisionAlgorithm$OuterSegmentHandler(CubeConvexCollisionAlgorithm paramCubeConvexCollisionAlgorithm)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean handle(Segment paramSegment)
/*     */   {
/* 101 */     if ((paramSegment.a() == null) || (paramSegment.g())) {
/* 102 */       return true;
/*     */     }
/* 104 */     this.cubeShape0.getSegmentAabb(paramSegment, CubeConvexCollisionAlgorithm.access$000(this.this$0).cubeMeshTransform, CubeConvexCollisionAlgorithm.access$000(this.this$0).outMin, CubeConvexCollisionAlgorithm.access$000(this.this$0).outMax, CubeConvexCollisionAlgorithm.access$000(this.this$0).localMinOut, CubeConvexCollisionAlgorithm.access$000(this.this$0).localMaxOut, CubeConvexCollisionAlgorithm.access$000(this.this$0).aabbVarSet);
/*     */ 
/* 113 */     if (AabbUtil2.testAabbAgainstAabb2(CubeConvexCollisionAlgorithm.access$000(this.this$0).shapeMin, CubeConvexCollisionAlgorithm.access$000(this.this$0).shapeMax, CubeConvexCollisionAlgorithm.access$000(this.this$0).outMin, CubeConvexCollisionAlgorithm.access$000(this.this$0).outMax))
/*     */     {
/*     */       try
/*     */       {
/* 117 */         this.this$0.processDistinctCollision(this.cubeShape0, this.col1, paramSegment.a(), CubeConvexCollisionAlgorithm.access$000(this.this$0).cubeMeshTransform, CubeConvexCollisionAlgorithm.access$000(this.this$0).convexShapeTransform, this.dispatchInfo, this.resultOut);
/*     */       }
/*     */       catch (ErrorDialogException localErrorDialogException)
/*     */       {
/* 124 */         localErrorDialogException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 127 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeConvexCollisionAlgorithm.OuterSegmentHandler
 * JD-Core Version:    0.6.2
 */