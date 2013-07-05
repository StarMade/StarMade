/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics.util.ArrayPool;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ public class TriangleContact
/*     */ {
/*  42 */   private final ArrayPool<int[]> intArrays = ArrayPool.get(Integer.TYPE);
/*     */   public static final int MAX_TRI_CLIPPING = 16;
/*     */   public float penetration_depth;
/*     */   public int point_count;
/*  48 */   public final Vector4f separating_normal = new Vector4f();
/*  49 */   public Vector3f[] points = new Vector3f[16];
/*     */ 
/*     */   public TriangleContact() {
/*  52 */     for (int i = 0; i < this.points.length; i++)
/*  53 */       this.points[i] = new Vector3f();
/*     */   }
/*     */ 
/*     */   public TriangleContact(TriangleContact other)
/*     */   {
/*  58 */     copy_from(other);
/*     */   }
/*     */ 
/*     */   public void set(TriangleContact other) {
/*  62 */     copy_from(other);
/*     */   }
/*     */ 
/*     */   public void copy_from(TriangleContact other) {
/*  66 */     this.penetration_depth = other.penetration_depth;
/*  67 */     this.separating_normal.set(other.separating_normal);
/*  68 */     this.point_count = other.point_count;
/*  69 */     int i = this.point_count;
/*  70 */     while (i-- != 0)
/*  71 */       this.points[i].set(other.points[i]);
/*     */   }
/*     */ 
/*     */   public void merge_points(Vector4f plane, float margin, ObjectArrayList<Vector3f> points, int point_count)
/*     */   {
/*  79 */     this.point_count = 0;
/*  80 */     this.penetration_depth = -1000.0F;
/*     */ 
/*  82 */     int[] point_indices = (int[])this.intArrays.getFixed(16);
/*     */ 
/*  84 */     for (int _k = 0; _k < point_count; _k++) {
/*  85 */       float _dist = -ClipPolygon.distance_point_plane(plane, (Vector3f)points.getQuick(_k)) + margin;
/*     */ 
/*  87 */       if (_dist >= 0.0F) {
/*  88 */         if (_dist > this.penetration_depth) {
/*  89 */           this.penetration_depth = _dist;
/*  90 */           point_indices[0] = _k;
/*  91 */           this.point_count = 1;
/*     */         }
/*  93 */         else if (_dist + 1.192093E-007F >= this.penetration_depth) {
/*  94 */           point_indices[this.point_count] = _k;
/*  95 */           this.point_count += 1;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 100 */     for (int _k = 0; _k < this.point_count; _k++) {
/* 101 */       this.points[_k].set((Tuple3f)points.getQuick(point_indices[_k]));
/*     */     }
/*     */ 
/* 104 */     this.intArrays.release(point_indices);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.TriangleContact
 * JD-Core Version:    0.6.2
 */