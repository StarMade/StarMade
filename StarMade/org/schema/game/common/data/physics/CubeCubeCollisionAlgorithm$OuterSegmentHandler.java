/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import jL;
/*     */ import jM;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ 
/*     */ class CubeCubeCollisionAlgorithm$OuterSegmentHandler
/*     */   implements jM
/*     */ {
/*     */   private CubeShape cubeShape1;
/*     */   private CubeShape cubeShape0;
/*     */ 
/*     */   private CubeCubeCollisionAlgorithm$OuterSegmentHandler(CubeCubeCollisionAlgorithm paramCubeCubeCollisionAlgorithm)
/*     */   {
/*     */   }
/*     */ 
/*     */   public boolean handle(Segment paramSegment)
/*     */   {
/* 173 */     CubeCubeCollisionAlgorithm.access$008(this.this$0);
/* 174 */     if ((paramSegment.a() == null) || (paramSegment.g())) {
/* 175 */       return true;
/*     */     }
/* 177 */     this.cubeShape0.setMargin(CubeCubeCollisionAlgorithm.margin);
/* 178 */     this.cubeShape0.getSegmentAabb(paramSegment, this.this$0.v.tmpTrans1, this.this$0.v.outOuterMin, this.this$0.v.outOuterMax, this.this$0.v.localMinOut, this.this$0.v.localMaxOut, this.this$0.v.aabbVarSet);
/*     */ 
/* 186 */     CubeCubeCollisionAlgorithm.InnerSegmentHandler.access$302(CubeCubeCollisionAlgorithm.access$200(this.this$0), paramSegment);
/*     */     try
/*     */     {
/* 189 */       this.cubeShape1.getSegmentBuffer().b(CubeCubeCollisionAlgorithm.access$200(this.this$0), this.this$0.v.minIntB, this.this$0.v.maxIntB);
/*     */     }
/*     */     catch (Exception localException) {
/* 192 */       localException.printStackTrace();
/*     */     }
/*     */ 
/* 194 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.CubeCubeCollisionAlgorithm.OuterSegmentHandler
 * JD-Core Version:    0.6.2
 */