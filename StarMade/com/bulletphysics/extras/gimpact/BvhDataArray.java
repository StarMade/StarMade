/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ class BvhDataArray
/*     */ {
/*  39 */   private int size = 0;
/*     */ 
/*  41 */   float[] bound = new float[0];
/*  42 */   int[] data = new int[0];
/*     */ 
/*     */   public int size() {
/*  45 */     return this.size;
/*     */   }
/*     */ 
/*     */   public void resize(int newSize) {
/*  49 */     float[] newBound = new float[newSize * 6];
/*  50 */     int[] newData = new int[newSize];
/*     */ 
/*  52 */     System.arraycopy(this.bound, 0, newBound, 0, this.size * 6);
/*  53 */     System.arraycopy(this.data, 0, newData, 0, this.size);
/*     */ 
/*  55 */     this.bound = newBound;
/*  56 */     this.data = newData;
/*     */ 
/*  58 */     this.size = newSize;
/*     */   }
/*     */ 
/*     */   public void swap(int idx1, int idx2) {
/*  62 */     int pos1 = idx1 * 6;
/*  63 */     int pos2 = idx2 * 6;
/*     */ 
/*  65 */     float b0 = this.bound[(pos1 + 0)];
/*  66 */     float b1 = this.bound[(pos1 + 1)];
/*  67 */     float b2 = this.bound[(pos1 + 2)];
/*  68 */     float b3 = this.bound[(pos1 + 3)];
/*  69 */     float b4 = this.bound[(pos1 + 4)];
/*  70 */     float b5 = this.bound[(pos1 + 5)];
/*  71 */     int d = this.data[idx1];
/*     */ 
/*  73 */     this.bound[(pos1 + 0)] = this.bound[(pos2 + 0)];
/*  74 */     this.bound[(pos1 + 1)] = this.bound[(pos2 + 1)];
/*  75 */     this.bound[(pos1 + 2)] = this.bound[(pos2 + 2)];
/*  76 */     this.bound[(pos1 + 3)] = this.bound[(pos2 + 3)];
/*  77 */     this.bound[(pos1 + 4)] = this.bound[(pos2 + 4)];
/*  78 */     this.bound[(pos1 + 5)] = this.bound[(pos2 + 5)];
/*  79 */     this.data[idx1] = this.data[idx2];
/*     */ 
/*  81 */     this.bound[(pos2 + 0)] = b0;
/*  82 */     this.bound[(pos2 + 1)] = b1;
/*  83 */     this.bound[(pos2 + 2)] = b2;
/*  84 */     this.bound[(pos2 + 3)] = b3;
/*  85 */     this.bound[(pos2 + 4)] = b4;
/*  86 */     this.bound[(pos2 + 5)] = b5;
/*  87 */     this.data[idx2] = d;
/*     */   }
/*     */ 
/*     */   public BoxCollision.AABB getBound(int idx, BoxCollision.AABB out) {
/*  91 */     int pos = idx * 6;
/*  92 */     out.min.set(this.bound[(pos + 0)], this.bound[(pos + 1)], this.bound[(pos + 2)]);
/*  93 */     out.max.set(this.bound[(pos + 3)], this.bound[(pos + 4)], this.bound[(pos + 5)]);
/*  94 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f getBoundMin(int idx, Vector3f out) {
/*  98 */     int pos = idx * 6;
/*  99 */     out.set(this.bound[(pos + 0)], this.bound[(pos + 1)], this.bound[(pos + 2)]);
/* 100 */     return out;
/*     */   }
/*     */ 
/*     */   public Vector3f getBoundMax(int idx, Vector3f out) {
/* 104 */     int pos = idx * 6;
/* 105 */     out.set(this.bound[(pos + 3)], this.bound[(pos + 4)], this.bound[(pos + 5)]);
/* 106 */     return out;
/*     */   }
/*     */ 
/*     */   public void setBound(int idx, BoxCollision.AABB aabb) {
/* 110 */     int pos = idx * 6;
/* 111 */     this.bound[(pos + 0)] = aabb.min.x;
/* 112 */     this.bound[(pos + 1)] = aabb.min.y;
/* 113 */     this.bound[(pos + 2)] = aabb.min.z;
/* 114 */     this.bound[(pos + 3)] = aabb.max.x;
/* 115 */     this.bound[(pos + 4)] = aabb.max.y;
/* 116 */     this.bound[(pos + 5)] = aabb.max.z;
/*     */   }
/*     */ 
/*     */   public int getData(int idx) {
/* 120 */     return this.data[idx];
/*     */   }
/*     */ 
/*     */   public void setData(int idx, int value) {
/* 124 */     this.data[idx] = value;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.BvhDataArray
 * JD-Core Version:    0.6.2
 */