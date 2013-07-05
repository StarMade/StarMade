/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import jM;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ 
/*     */ public class RayCubeGridSolver
/*     */ {
/*     */   private SegmentController controller;
/*  15 */   private Vector3f inputStart = new Vector3f();
/*  16 */   private Vector3f inputEnd = new Vector3f();
/*     */ 
/*  18 */   private Vector3f dir = new Vector3f();
/*     */ 
/*  20 */   private RayTraceGridTraverser tra = new RayTraceGridTraverser();
/*     */ 
/*  22 */   private Ray r = new Ray();
/*     */   private static final float MIN_DEPTH = 3.0F;
/*  26 */   private final Transform inv = new Transform();
/*     */ 
/*     */   public void initialize(Vector3f paramVector3f1, Vector3f paramVector3f2, SegmentController paramSegmentController, Transform paramTransform) {
/*  29 */     this.inputStart.set(paramVector3f1);
/*  30 */     this.inputEnd.set(paramVector3f2);
/*  31 */     this.controller = paramSegmentController;
/*     */ 
/*  33 */     this.inv.set(paramTransform);
/*  34 */     this.inv.inverse();
/*  35 */     this.inv.transform(this.inputStart);
/*  36 */     this.inv.transform(this.inputEnd);
/*     */ 
/*  41 */     this.inputStart.x += 8.5F;
/*  42 */     this.inputStart.y += 8.5F;
/*  43 */     this.inputStart.z += 8.5F;
/*     */ 
/*  45 */     this.inputEnd.x += 8.5F;
/*  46 */     this.inputEnd.y += 8.5F;
/*  47 */     this.inputEnd.z += 8.5F;
/*     */ 
/*  49 */     if (SubsimplexRayCubesCovexCast.debug) {
/*  50 */       System.err.println("START OF TRAVERSE +888 IS " + this.inputStart);
/*     */     }
/*     */ 
/*  61 */     this.inputStart.x /= 16.0F;
/*  62 */     this.inputStart.y /= 16.0F;
/*  63 */     this.inputStart.z /= 16.0F;
/*     */ 
/*  65 */     this.inputEnd.x /= 16.0F;
/*  66 */     this.inputEnd.y /= 16.0F;
/*  67 */     this.inputEnd.z /= 16.0F;
/*  68 */     if (SubsimplexRayCubesCovexCast.debug) {
/*  69 */       System.err.println("START OF TRAVERSE SMALL IS " + this.inputStart);
/*     */     }
/*     */ 
/*  83 */     this.dir.sub(this.inputEnd, this.inputStart);
/*     */ 
/*  85 */     this.dir.scale(1.5F);
/*     */ 
/*  87 */     if (this.dir.length() < 3.0F) {
/*  88 */       this.dir.normalize();
/*  89 */       this.dir.scale(3.0F);
/*     */     }
/*     */ 
/*  92 */     this.r.direction.set(this.dir);
/*  93 */     this.r.position.set(this.inputStart);
/*     */   }
/*     */ 
/*     */   public void traverseSegmentsOnRay(jM paramjM)
/*     */   {
/* 102 */     this.tra.getCellsOnRay(this.r, (int)Math.max(3.0F, FastMath.b(this.dir.length())), paramjM, this.controller);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.RayCubeGridSolver
 * JD-Core Version:    0.6.2
 */