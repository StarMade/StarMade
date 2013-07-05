/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ class BvhTreeNodeArray
/*     */ {
/*  38 */   private int size = 0;
/*     */ 
/*  40 */   private float[] bound = new float[0];
/*  41 */   private int[] escapeIndexOrDataIndex = new int[0];
/*     */ 
/*     */   public void clear() {
/*  44 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   public void resize(int newSize) {
/*  48 */     float[] newBound = new float[newSize * 6];
/*  49 */     int[] newEIODI = new int[newSize];
/*     */ 
/*  51 */     System.arraycopy(this.bound, 0, newBound, 0, this.size * 6);
/*  52 */     System.arraycopy(this.escapeIndexOrDataIndex, 0, newEIODI, 0, this.size);
/*     */ 
/*  54 */     this.bound = newBound;
/*  55 */     this.escapeIndexOrDataIndex = newEIODI;
/*     */ 
/*  57 */     this.size = newSize;
/*     */   }
/*     */ 
/*     */   public void set(int destIdx, BvhTreeNodeArray array, int srcIdx) {
/*  61 */     int dpos = destIdx * 6;
/*  62 */     int spos = srcIdx * 6;
/*     */ 
/*  64 */     this.bound[(dpos + 0)] = array.bound[(spos + 0)];
/*  65 */     this.bound[(dpos + 1)] = array.bound[(spos + 1)];
/*  66 */     this.bound[(dpos + 2)] = array.bound[(spos + 2)];
/*  67 */     this.bound[(dpos + 3)] = array.bound[(spos + 3)];
/*  68 */     this.bound[(dpos + 4)] = array.bound[(spos + 4)];
/*  69 */     this.bound[(dpos + 5)] = array.bound[(spos + 5)];
/*  70 */     this.escapeIndexOrDataIndex[destIdx] = array.escapeIndexOrDataIndex[srcIdx];
/*     */   }
/*     */ 
/*     */   public void set(int destIdx, BvhDataArray array, int srcIdx) {
/*  74 */     int dpos = destIdx * 6;
/*  75 */     int spos = srcIdx * 6;
/*     */ 
/*  77 */     this.bound[(dpos + 0)] = array.bound[(spos + 0)];
/*  78 */     this.bound[(dpos + 1)] = array.bound[(spos + 1)];
/*  79 */     this.bound[(dpos + 2)] = array.bound[(spos + 2)];
/*  80 */     this.bound[(dpos + 3)] = array.bound[(spos + 3)];
/*  81 */     this.bound[(dpos + 4)] = array.bound[(spos + 4)];
/*  82 */     this.bound[(dpos + 5)] = array.bound[(spos + 5)];
/*  83 */     this.escapeIndexOrDataIndex[destIdx] = array.data[srcIdx];
/*     */   }
/*     */ 
/*     */   public BoxCollision.AABB getBound(int nodeIndex, BoxCollision.AABB out) {
/*  87 */     int pos = nodeIndex * 6;
/*  88 */     out.min.set(this.bound[(pos + 0)], this.bound[(pos + 1)], this.bound[(pos + 2)]);
/*  89 */     out.max.set(this.bound[(pos + 3)], this.bound[(pos + 4)], this.bound[(pos + 5)]);
/*  90 */     return out;
/*     */   }
/*     */ 
/*     */   public void setBound(int nodeIndex, BoxCollision.AABB aabb) {
/*  94 */     int pos = nodeIndex * 6;
/*  95 */     this.bound[(pos + 0)] = aabb.min.x;
/*  96 */     this.bound[(pos + 1)] = aabb.min.y;
/*  97 */     this.bound[(pos + 2)] = aabb.min.z;
/*  98 */     this.bound[(pos + 3)] = aabb.max.x;
/*  99 */     this.bound[(pos + 4)] = aabb.max.y;
/* 100 */     this.bound[(pos + 5)] = aabb.max.z;
/*     */   }
/*     */ 
/*     */   public boolean isLeafNode(int nodeIndex)
/*     */   {
/* 105 */     return this.escapeIndexOrDataIndex[nodeIndex] >= 0;
/*     */   }
/*     */ 
/*     */   public int getEscapeIndex(int nodeIndex)
/*     */   {
/* 110 */     return -this.escapeIndexOrDataIndex[nodeIndex];
/*     */   }
/*     */ 
/*     */   public void setEscapeIndex(int nodeIndex, int index) {
/* 114 */     this.escapeIndexOrDataIndex[nodeIndex] = (-index);
/*     */   }
/*     */ 
/*     */   public int getDataIndex(int nodeIndex)
/*     */   {
/* 119 */     return this.escapeIndexOrDataIndex[nodeIndex];
/*     */   }
/*     */ 
/*     */   public void setDataIndex(int nodeIndex, int index) {
/* 123 */     this.escapeIndexOrDataIndex[nodeIndex] = index;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.BvhTreeNodeArray
 * JD-Core Version:    0.6.2
 */