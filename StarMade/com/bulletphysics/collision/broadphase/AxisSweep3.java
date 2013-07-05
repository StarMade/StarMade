/*     */ package com.bulletphysics.collision.broadphase;
/*     */ 
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class AxisSweep3 extends AxisSweep3Internal
/*     */ {
/*     */   public AxisSweep3(Vector3f worldAabbMin, Vector3f worldAabbMax)
/*     */   {
/*  44 */     this(worldAabbMin, worldAabbMax, 16384, null);
/*     */   }
/*     */ 
/*     */   public AxisSweep3(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles) {
/*  48 */     this(worldAabbMin, worldAabbMax, maxHandles, null);
/*     */   }
/*     */ 
/*     */   public AxisSweep3(Vector3f worldAabbMin, Vector3f worldAabbMax, int maxHandles, OverlappingPairCache pairCache) {
/*  52 */     super(worldAabbMin, worldAabbMax, 65534, 65535, maxHandles, pairCache);
/*     */ 
/*  54 */     assert ((maxHandles > 1) && (maxHandles < 32767));
/*     */   }
/*     */ 
/*     */   protected AxisSweep3Internal.EdgeArray createEdgeArray(int size)
/*     */   {
/*  59 */     return new EdgeArrayImpl(size);
/*     */   }
/*     */ 
/*     */   protected AxisSweep3Internal.Handle createHandle()
/*     */   {
/*  64 */     return new HandleImpl();
/*     */   }
/*     */ 
/*     */   protected int getMask() {
/*  68 */     return 65535;
/*     */   }
/*     */ 
/*     */   protected static class HandleImpl extends AxisSweep3Internal.Handle
/*     */   {
/*     */     private short minEdges0;
/*     */     private short minEdges1;
/*     */     private short minEdges2;
/*     */     private short maxEdges0;
/*     */     private short maxEdges1;
/*     */     private short maxEdges2;
/*     */ 
/*     */     public int getMinEdges(int edgeIndex)
/*     */     {
/* 130 */       switch (edgeIndex) { case 0:
/*     */       default:
/* 132 */         return this.minEdges0 & 0xFFFF;
/*     */       case 1:
/* 133 */         return this.minEdges1 & 0xFFFF;
/* 134 */       case 2: } return this.minEdges2 & 0xFFFF;
/*     */     }
/*     */ 
/*     */     public void setMinEdges(int edgeIndex, int value)
/*     */     {
/* 140 */       switch (edgeIndex) { case 0:
/* 141 */         this.minEdges0 = ((short)value); break;
/*     */       case 1:
/* 142 */         this.minEdges1 = ((short)value); break;
/*     */       case 2:
/* 143 */         this.minEdges2 = ((short)value);
/*     */       }
/*     */     }
/*     */ 
/*     */     public int getMaxEdges(int edgeIndex)
/*     */     {
/* 149 */       switch (edgeIndex) { case 0:
/*     */       default:
/* 151 */         return this.maxEdges0 & 0xFFFF;
/*     */       case 1:
/* 152 */         return this.maxEdges1 & 0xFFFF;
/* 153 */       case 2: } return this.maxEdges2 & 0xFFFF;
/*     */     }
/*     */ 
/*     */     public void setMaxEdges(int edgeIndex, int value)
/*     */     {
/* 159 */       switch (edgeIndex) { case 0:
/* 160 */         this.maxEdges0 = ((short)value); break;
/*     */       case 1:
/* 161 */         this.maxEdges1 = ((short)value); break;
/*     */       case 2:
/* 162 */         this.maxEdges2 = ((short)value);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static class EdgeArrayImpl extends AxisSweep3Internal.EdgeArray
/*     */   {
/*     */     private short[] pos;
/*     */     private short[] handle;
/*     */ 
/*     */     public EdgeArrayImpl(int size)
/*     */     {
/*  76 */       this.pos = new short[size];
/*  77 */       this.handle = new short[size];
/*     */     }
/*     */ 
/*     */     public void swap(int idx1, int idx2)
/*     */     {
/*  82 */       short tmpPos = this.pos[idx1];
/*  83 */       short tmpHandle = this.handle[idx1];
/*     */ 
/*  85 */       this.pos[idx1] = this.pos[idx2];
/*  86 */       this.handle[idx1] = this.handle[idx2];
/*     */ 
/*  88 */       this.pos[idx2] = tmpPos;
/*  89 */       this.handle[idx2] = tmpHandle;
/*     */     }
/*     */ 
/*     */     public void set(int dest, int src)
/*     */     {
/*  94 */       this.pos[dest] = this.pos[src];
/*  95 */       this.handle[dest] = this.handle[src];
/*     */     }
/*     */ 
/*     */     public int getPos(int index)
/*     */     {
/* 100 */       return this.pos[index] & 0xFFFF;
/*     */     }
/*     */ 
/*     */     public void setPos(int index, int value)
/*     */     {
/* 105 */       this.pos[index] = ((short)value);
/*     */     }
/*     */ 
/*     */     public int getHandle(int index)
/*     */     {
/* 110 */       return this.handle[index] & 0xFFFF;
/*     */     }
/*     */ 
/*     */     public void setHandle(int index, int value)
/*     */     {
/* 115 */       this.handle[index] = ((short)value);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.collision.broadphase.AxisSweep3
 * JD-Core Version:    0.6.2
 */