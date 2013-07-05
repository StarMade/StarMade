/*     */ package com.bulletphysics.collision.broadphase;
/*     */ 
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class AxisSweep3_32 extends AxisSweep3Internal
/*     */ {
/*     */   public AxisSweep3_32(Vector3f worldAabbMin, Vector3f worldAabbMax)
/*     */   {
/*  41 */     this(worldAabbMin, worldAabbMax, 1500000, null);
/*     */   }
/*     */ 
/*     */   public AxisSweep3_32(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles) {
/*  45 */     this(worldAabbMin, worldAabbMax, maxHandles, null);
/*     */   }
/*     */ 
/*     */   public AxisSweep3_32(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles, OverlappingPairCache pairCache) {
/*  49 */     super(worldAabbMin, worldAabbMax, -2, 2147483647, maxHandles, pairCache);
/*     */ 
/*  51 */     assert ((maxHandles > 1) && (maxHandles < 2147483647));
/*     */   }
/*     */ 
/*     */   protected AxisSweep3Internal.EdgeArray createEdgeArray(int size)
/*     */   {
/*  56 */     return new EdgeArrayImpl(size);
/*     */   }
/*     */ 
/*     */   protected AxisSweep3Internal.Handle createHandle()
/*     */   {
/*  61 */     return new HandleImpl();
/*     */   }
/*     */ 
/*     */   protected int getMask() {
/*  65 */     return -1;
/*     */   }
/*     */ 
/*     */   protected static class HandleImpl extends AxisSweep3Internal.Handle
/*     */   {
/*     */     private int minEdges0;
/*     */     private int minEdges1;
/*     */     private int minEdges2;
/*     */     private int maxEdges0;
/*     */     private int maxEdges1;
/*     */     private int maxEdges2;
/*     */ 
/*     */     public int getMinEdges(int edgeIndex)
/*     */     {
/* 127 */       switch (edgeIndex) { case 0:
/*     */       default:
/* 129 */         return this.minEdges0;
/*     */       case 1:
/* 130 */         return this.minEdges1;
/* 131 */       case 2: } return this.minEdges2;
/*     */     }
/*     */ 
/*     */     public void setMinEdges(int edgeIndex, int value)
/*     */     {
/* 137 */       switch (edgeIndex) { case 0:
/* 138 */         this.minEdges0 = value; break;
/*     */       case 1:
/* 139 */         this.minEdges1 = value; break;
/*     */       case 2:
/* 140 */         this.minEdges2 = value;
/*     */       }
/*     */     }
/*     */ 
/*     */     public int getMaxEdges(int edgeIndex)
/*     */     {
/* 146 */       switch (edgeIndex) { case 0:
/*     */       default:
/* 148 */         return this.maxEdges0;
/*     */       case 1:
/* 149 */         return this.maxEdges1;
/* 150 */       case 2: } return this.maxEdges2;
/*     */     }
/*     */ 
/*     */     public void setMaxEdges(int edgeIndex, int value)
/*     */     {
/* 156 */       switch (edgeIndex) { case 0:
/* 157 */         this.maxEdges0 = value; break;
/*     */       case 1:
/* 158 */         this.maxEdges1 = value; break;
/*     */       case 2:
/* 159 */         this.maxEdges2 = value;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static class EdgeArrayImpl extends AxisSweep3Internal.EdgeArray
/*     */   {
/*     */     private int[] pos;
/*     */     private int[] handle;
/*     */ 
/*     */     public EdgeArrayImpl(int size)
/*     */     {
/*  73 */       this.pos = new int[size];
/*  74 */       this.handle = new int[size];
/*     */     }
/*     */ 
/*     */     public void swap(int idx1, int idx2)
/*     */     {
/*  79 */       int tmpPos = this.pos[idx1];
/*  80 */       int tmpHandle = this.handle[idx1];
/*     */ 
/*  82 */       this.pos[idx1] = this.pos[idx2];
/*  83 */       this.handle[idx1] = this.handle[idx2];
/*     */ 
/*  85 */       this.pos[idx2] = tmpPos;
/*  86 */       this.handle[idx2] = tmpHandle;
/*     */     }
/*     */ 
/*     */     public void set(int dest, int src)
/*     */     {
/*  91 */       this.pos[dest] = this.pos[src];
/*  92 */       this.handle[dest] = this.handle[src];
/*     */     }
/*     */ 
/*     */     public int getPos(int index)
/*     */     {
/*  97 */       return this.pos[index];
/*     */     }
/*     */ 
/*     */     public void setPos(int index, int value)
/*     */     {
/* 102 */       this.pos[index] = value;
/*     */     }
/*     */ 
/*     */     public int getHandle(int index)
/*     */     {
/* 107 */       return this.handle[index];
/*     */     }
/*     */ 
/*     */     public void setHandle(int index, int value)
/*     */     {
/* 112 */       this.handle[index] = value;
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.AxisSweep3_32
 * JD-Core Version:    0.6.2
 */