/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class VoronoiSimplexSolverExt$SubSimplexClosestResult
/*     */ {
/* 715 */   public final Vector3f closestPointOnSimplex = new Vector3f();
/*     */ 
/* 719 */   public final VoronoiSimplexSolverExt.UsageBitfield usedVertices = new VoronoiSimplexSolverExt.UsageBitfield();
/* 720 */   public final float[] barycentricCoords = new float[4];
/*     */   public boolean degenerate;
/*     */ 
/*     */   public void reset()
/*     */   {
/* 724 */     this.degenerate = false;
/* 725 */     setBarycentricCoordinates(0.0F, 0.0F, 0.0F, 0.0F);
/* 726 */     this.usedVertices.reset();
/*     */   }
/*     */ 
/*     */   public boolean isValid() {
/* 730 */     if ((this.barycentricCoords[0] >= 0.0F) && (this.barycentricCoords[1] >= 0.0F) && (this.barycentricCoords[2] >= 0.0F) && (this.barycentricCoords[3] >= 0.0F)) return true;
/*     */ 
/* 734 */     return false;
/*     */   }
/*     */ 
/*     */   public void setBarycentricCoordinates(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
/*     */   {
/* 738 */     this.barycentricCoords[0] = paramFloat1;
/* 739 */     this.barycentricCoords[1] = paramFloat2;
/* 740 */     this.barycentricCoords[2] = paramFloat3;
/* 741 */     this.barycentricCoords[3] = paramFloat4;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.VoronoiSimplexSolverExt.SubSimplexClosestResult
 * JD-Core Version:    0.6.2
 */