/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import jL;
/*     */ import jM;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Vector3f;
/*     */ import org.schema.common.FastMath;
/*     */ import org.schema.game.common.controller.SegmentController;
/*     */ import org.schema.game.common.data.world.Segment;
/*     */ import q;
/*     */ 
/*     */ public class RayTraceGridTraverser
/*     */ {
/*  18 */   private q cell = new q();
/*  19 */   private Vector3f tMax = new Vector3f();
/*  20 */   private Vector3f tDelta = new Vector3f();
/*  21 */   private Vector3f cellBoundary = new Vector3f();
/*     */   private int stepX;
/*     */   private int stepY;
/*     */   private int stepZ;
/*     */   private Ray ray;
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  29 */     paramArrayOfString = new RayTraceGridTraverser();
/*     */     Ray localRay;
/*  30 */     (
/*  31 */       localRay = new Ray()).position = 
/*  31 */       new Vector3f(16.638065F, 5.568517F, 18.922298F);
/*     */ 
/*  33 */     localRay.direction = new Vector3f(0.0F, -6.0F, 0.0F);
/*  34 */     paramArrayOfString.getCellsOnRay(localRay, 6, null, null);
/*     */   }
/*     */ 
/*     */   public void getCellsOnRay(Ray paramRay, int paramInt, jM paramjM, SegmentController paramSegmentController)
/*     */   {
/*  39 */     this.ray = paramRay;
/*     */     q localq;
/*  56 */     int j = (
/*  56 */       localq = getCellAt(paramRay.position, this.cell)).a;
/*     */ 
/*  57 */     int k = localq.b;
/*  58 */     int i = localq.c;
/*     */ 
/*  62 */     this.stepX = ((int)Math.signum(paramRay.direction.x));
/*  63 */     this.stepY = ((int)Math.signum(paramRay.direction.y));
/*  64 */     this.stepZ = ((int)Math.signum(paramRay.direction.z));
/*     */ 
/*  69 */     this.cellBoundary.set(j + (this.stepX > 0 ? 1 : 0), k + (this.stepY > 0 ? 1 : 0), i + (this.stepZ > 0 ? 1 : 0));
/*     */ 
/*  79 */     this.tMax.set((this.cellBoundary.x - paramRay.position.x) / paramRay.direction.x, (this.cellBoundary.y - paramRay.position.y) / paramRay.direction.y, (this.cellBoundary.z - paramRay.position.z) / paramRay.direction.z);
/*     */ 
/*  83 */     if ((Float.isNaN(this.tMax.x)) || (Float.isInfinite(this.tMax.x))) this.tMax.x = (1.0F / 1.0F);
/*  84 */     if ((Float.isNaN(this.tMax.y)) || (Float.isInfinite(this.tMax.y))) this.tMax.y = (1.0F / 1.0F);
/*  85 */     if ((Float.isNaN(this.tMax.z)) || (Float.isInfinite(this.tMax.z))) this.tMax.z = (1.0F / 1.0F);
/*     */ 
/*  88 */     this.tDelta.set(this.stepX / paramRay.direction.x, this.stepY / paramRay.direction.y, this.stepZ / paramRay.direction.z);
/*     */ 
/*  92 */     if (Float.isNaN(this.tDelta.x)) this.tDelta.x = (1.0F / 1.0F);
/*  93 */     if (Float.isNaN(this.tDelta.y)) this.tDelta.y = (1.0F / 1.0F);
/*  94 */     if (Float.isNaN(this.tDelta.z)) this.tDelta.z = (1.0F / 1.0F);
/*     */ 
/* 100 */     for (paramRay = 0; paramRay < paramInt; paramRay++)
/*     */     {
/* 105 */       if (!handle(j, k, i, paramjM, paramSegmentController)) {
/* 106 */         return;
/*     */       }
/*     */ 
/* 129 */       if (this.tMax.x < this.tMax.y) {
/* 130 */         if (this.tMax.x < this.tMax.z) {
/* 131 */           j += this.stepX;
/* 132 */           this.tMax.x += this.tDelta.x;
/*     */         } else {
/* 134 */           i += this.stepZ;
/* 135 */           this.tMax.z += this.tDelta.z;
/*     */         }
/*     */       }
/* 138 */       else if (this.tMax.y < this.tMax.z) {
/* 139 */         k += this.stepY;
/* 140 */         this.tMax.y += this.tDelta.y;
/*     */       } else {
/* 142 */         i += this.stepZ;
/* 143 */         this.tMax.z += this.tDelta.z;
/*     */       }
/*     */     }
/*     */ 
/* 147 */     if (SubsimplexRayCubesCovexCast.debug)
/* 148 */       System.err.println("NO SEGMENT FOUND ON PATH: " + paramInt);
/*     */   }
/*     */ 
/*     */   private boolean handleDebug(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 153 */     System.err.println("TESTING: " + paramInt1 + ", " + paramInt2 + ", " + paramInt3 + "; " + this.stepX + ", " + this.stepY + ", " + this.stepZ + "; MAX " + this.tMax + "; DELTA " + this.tDelta + "; " + this.ray.position + " -> " + this.ray.direction);
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */   private boolean handle(int paramInt1, int paramInt2, int paramInt3, jM paramjM, SegmentController paramSegmentController)
/*     */   {
/* 160 */     paramInt1 <<= 4;
/* 161 */     paramInt2 <<= 4;
/* 162 */     paramInt3 <<= 4;
/*     */ 
/* 182 */     if (((
/* 182 */       paramInt1 = paramSegmentController.getSegmentBuffer().a(paramInt1, paramInt2, paramInt3)) != null) && 
/* 182 */       (!paramInt1.g())) {
/* 183 */       if (SubsimplexRayCubesCovexCast.debug) {
/* 184 */         System.err.println("TRAVERSED TO " + paramInt1.a);
/*     */       }
/* 186 */       return paramjM.handle(paramInt1);
/*     */     }
/* 188 */     return true;
/*     */   }
/*     */ 
/*     */   private q getCellAt(Vector3f paramVector3f, q paramq) {
/* 192 */     paramq.b(FastMath.a(paramVector3f.x), FastMath.a(paramVector3f.y), FastMath.a(paramVector3f.z));
/*     */ 
/* 196 */     return paramq;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.RayTraceGridTraverser
 * JD-Core Version:    0.6.2
 */