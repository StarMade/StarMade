/*     */ package org.schema.game.common.data.physics;
/*     */ 
/*     */ import com.bulletphysics.collision.broadphase.AxisSweep3;
/*     */ import com.bulletphysics.collision.broadphase.AxisSweep3.HandleImpl;
/*     */ import com.bulletphysics.collision.broadphase.AxisSweep3Internal.EdgeArray;
/*     */ import com.bulletphysics.collision.broadphase.AxisSweep3Internal.Handle;
/*     */ import com.bulletphysics.collision.broadphase.OverlappingPairCache;
/*     */ import java.io.PrintStream;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class AxisSweep3Ext extends AxisSweep3
/*     */ {
/*     */   public AxisSweep3Ext(Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt, OverlappingPairCache paramOverlappingPairCache)
/*     */   {
/*  13 */     super(paramVector3f1, paramVector3f2, paramInt, paramOverlappingPairCache);
/*     */   }
/*     */ 
/*     */   public AxisSweep3Ext(Vector3f paramVector3f1, Vector3f paramVector3f2, int paramInt)
/*     */   {
/*  19 */     super(paramVector3f1, paramVector3f2, paramInt);
/*     */   }
/*     */ 
/*     */   public AxisSweep3Ext(Vector3f paramVector3f1, Vector3f paramVector3f2)
/*     */   {
/*  24 */     super(paramVector3f1, paramVector3f2);
/*     */   }
/*     */ 
/*     */   public void cleanUpReferences() {
/*  28 */     for (int i = 0; i < this.pHandles.length; i++)
/*  29 */       this.pHandles[i].clientObject = null;
/*     */   }
/*     */ 
/*     */   private boolean assertNonNull()
/*     */   {
/*  39 */     for (int i = 0; i < this.maxHandles; i++) {
/*  40 */       assert (this.pHandles[i] != null) : i;
/*     */     }
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   protected AxisSweep3Internal.EdgeArray createEdgeArray(int paramInt)
/*     */   {
/*  63 */     return new AxisSweep3Ext.EdgeArrayImplExt(paramInt);
/*     */   }
/*     */ 
/*     */   protected int allocHandle()
/*     */   {
/*  68 */     if (this.firstFreeHandle == 0)
/*     */     {
/*  74 */       AxisSweep3Internal.Handle[] arrayOfHandle1 = this.pHandles;
/*  75 */       int j = (this.pHandles.length - 1 << 1) + 1;
/*     */ 
/*  77 */       System.err.println("[Physics][AXIS-SWEEP] Handle Array grows: " + arrayOfHandle1.length + " -> " + j);
/*     */ 
/*  79 */       this.pHandles = new AxisSweep3.HandleImpl[j];
/*  80 */       for (AxisSweep3Internal.Handle[] arrayOfHandle2 = 0; arrayOfHandle2 < arrayOfHandle1.length; arrayOfHandle2++) {
/*  81 */         this.pHandles[arrayOfHandle2] = arrayOfHandle1[arrayOfHandle2];
/*     */       }
/*  83 */       arrayOfHandle2 = arrayOfHandle1.length;
/*     */ 
/*  85 */       this.maxHandles = j;
/*     */ 
/*  88 */       for (arrayOfHandle1 = arrayOfHandle2; arrayOfHandle1 < this.maxHandles; arrayOfHandle1++) {
/*  89 */         this.pHandles[arrayOfHandle1] = createHandle();
/*  90 */         this.pHandles[arrayOfHandle1].setNextFree(arrayOfHandle1 + 1);
/*     */       }
/*  92 */       this.pHandles[(this.maxHandles - 1)].setNextFree(0);
/*     */ 
/*  94 */       this.firstFreeHandle = arrayOfHandle2;
/*     */ 
/*  97 */       for (i = 0; i < 3; i++) {
/*  98 */         AxisSweep3Internal.EdgeArray localEdgeArray = this.pEdges[i];
/*  99 */         this.pEdges[i] = createEdgeArray(this.maxHandles << 1);
/*     */ 
/* 101 */         ((AxisSweep3Ext.EdgeArrayImplExt)this.pEdges[i]).insert((AxisSweep3Ext.EdgeArrayImplExt)localEdgeArray);
/*     */       }
/* 103 */       assert (assertNonNull());
/*     */     }
/* 105 */     assert (this.firstFreeHandle != 0);
/* 106 */     int i = this.firstFreeHandle;
/* 107 */     this.firstFreeHandle = getHandle(i).getNextFree();
/* 108 */     this.numHandles += 1;
/*     */ 
/* 110 */     return i;
/*     */   }
/*     */ 
/*     */   public void cleanUp() {
/* 114 */     for (int i = 0; i < this.pHandles.length; i++) {
/* 115 */       this.pHandles[i].clientObject = null;
/* 116 */       this.pHandles[i] = null;
/*     */     }
/* 118 */     for (i = 0; i < this.pEdges.length; i++)
/* 119 */       this.pEdges[i] = null;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.AxisSweep3Ext
 * JD-Core Version:    0.6.2
 */