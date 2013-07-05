/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.DispatcherInfo;
/*     */ import com.bulletphysics.collision.dispatch.ManifoldResult;
/*     */ import com.bulletphysics.linearmath.AabbUtil2;
/*     */ import jL;
/*     */ import jM;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import org.schema.schine.graphicsengine.shader.ErrorDialogException;
/*     */ import xO;
/*     */ 
/*     */ class CubeCubeCollisionAlgorithm$InnerSegmentHandler
/*     */   implements jM
/*     */ {
/*     */   private CubeShape cubeShape1;
/*     */   private CubeShape cubeShape0;
/*     */   private int distinctTests;
/*     */   private long distinctTime;
/*     */   private DispatcherInfo dispatchInfo;
/*     */   private ManifoldResult resultOut;
/*     */   private Segment sOuter;
/*     */ 
/*     */   private CubeCubeCollisionAlgorithm$InnerSegmentHandler(CubeCubeCollisionAlgorithm paramCubeCubeCollisionAlgorithm)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean handle(Segment paramSegment)
/*     */   {
/* 111 */     CubeCubeCollisionAlgorithm.access$008(this.this$0);
/* 112 */     if ((paramSegment.a() == null) || (paramSegment.g()))
/*     */     {
/* 115 */       return true;
/*     */     }
/* 117 */     assert (paramSegment.a() != this.sOuter.a()) : (paramSegment.a() + "; " + this.sOuter.a());
/* 118 */     if (paramSegment.a() == this.sOuter.a())
/*     */     {
/* 120 */       return false;
/*     */     }
/* 122 */     assert (paramSegment.a() == this.cubeShape1.getSegmentBuffer().a());
/*     */ 
/* 124 */     this.cubeShape1.setMargin(CubeCubeCollisionAlgorithm.margin);
/* 125 */     this.cubeShape1.getSegmentAabb(paramSegment, this.this$0.v.tmpTrans2, this.this$0.v.outInnerMin, this.this$0.v.outInnerMax, this.this$0.v.localMinOut, this.this$0.v.localMaxOut, this.this$0.v.aabbVarSet);
/*     */ 
/* 133 */     if (AabbUtil2.testAabbAgainstAabb2(this.this$0.v.outInnerMin, this.this$0.v.outInnerMax, this.this$0.v.outOuterMin, this.this$0.v.outOuterMax))
/*     */     {
/* 135 */       this.this$0.v.bbOuterSeg.b(this.this$0.v.outOuterMin, this.this$0.v.outOuterMax);
/* 136 */       this.this$0.v.bbInnerSeg.b(this.this$0.v.outInnerMin, this.this$0.v.outInnerMax);
/*     */ 
/* 138 */       this.this$0.v.bbOuterSeg.a(this.this$0.v.bbInnerSeg, this.this$0.v.bbSectorIntersection);
/*     */ 
/* 145 */       this.distinctTests += 1;
/*     */       try {
/* 147 */         long l = System.nanoTime();
/* 148 */         CubeCubeCollisionAlgorithm.access$100(this.this$0, this.cubeShape0, this.cubeShape1, this.sOuter.a(), paramSegment.a(), this.this$0.v.tmpTrans1, this.this$0.v.tmpTrans2, this.dispatchInfo, this.resultOut);
/*     */ 
/* 152 */         this.distinctTime += System.nanoTime() - l;
/*     */       }
/*     */       catch (ErrorDialogException localErrorDialogException)
/*     */       {
/* 157 */         localErrorDialogException.printStackTrace();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 159 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm.InnerSegmentHandler
 * JD-Core Version:    0.6.2
 */