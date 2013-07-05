/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics.linearmath.VectorUtil;
/*     */ import com.bulletphysics.util.ArrayPool;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Tuple3f;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ class ClipPolygon
/*     */ {
/*     */   public static float distance_point_plane(Vector4f plane, Vector3f point)
/*     */   {
/*  44 */     return VectorUtil.dot3(point, plane) - plane.w;
/*     */   }
/*     */ 
/*     */   public static void vec_blend(Vector3f vr, Vector3f va, Vector3f vb, float blend_factor)
/*     */   {
/*  51 */     vr.scale(1.0F - blend_factor, va);
/*  52 */     vr.scaleAdd(blend_factor, vb, vr);
/*     */   }
/*     */ 
/*     */   public static void plane_clip_polygon_collect(Vector3f point0, Vector3f point1, float dist0, float dist1, ObjectArrayList<Vector3f> clipped, int[] clipped_count)
/*     */   {
/*  59 */     boolean _prevclassif = dist0 > 1.192093E-007F;
/*  60 */     boolean _classif = dist1 > 1.192093E-007F;
/*  61 */     if (_classif != _prevclassif) {
/*  62 */       float blendfactor = -dist0 / (dist1 - dist0);
/*  63 */       vec_blend((Vector3f)clipped.getQuick(clipped_count[0]), point0, point1, blendfactor);
/*  64 */       clipped_count[0] += 1;
/*     */     }
/*  66 */     if (!_classif) {
/*  67 */       ((Vector3f)clipped.getQuick(clipped_count[0])).set(point1);
/*  68 */       clipped_count[0] += 1;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static int plane_clip_polygon(Vector4f plane, ObjectArrayList<Vector3f> polygon_points, int polygon_point_count, ObjectArrayList<Vector3f> clipped)
/*     */   {
/*  78 */     ArrayPool intArrays = ArrayPool.get(Integer.TYPE);
/*     */ 
/*  80 */     int[] clipped_count = (int[])intArrays.getFixed(1);
/*  81 */     clipped_count[0] = 0;
/*     */ 
/*  84 */     float firstdist = distance_point_plane(plane, (Vector3f)polygon_points.getQuick(0));
/*  85 */     if (firstdist <= 1.192093E-007F) {
/*  86 */       ((Vector3f)clipped.getQuick(clipped_count[0])).set((Tuple3f)polygon_points.getQuick(0));
/*  87 */       clipped_count[0] += 1;
/*     */     }
/*     */ 
/*  90 */     float olddist = firstdist;
/*  91 */     for (int i = 1; i < polygon_point_count; i++) {
/*  92 */       float dist = distance_point_plane(plane, (Vector3f)polygon_points.getQuick(i));
/*     */ 
/*  94 */       plane_clip_polygon_collect((Vector3f)polygon_points.getQuick(i - 1), (Vector3f)polygon_points.getQuick(i), olddist, dist, clipped, clipped_count);
/*     */ 
/* 102 */       olddist = dist;
/*     */     }
/*     */ 
/* 107 */     plane_clip_polygon_collect((Vector3f)polygon_points.getQuick(polygon_point_count - 1), (Vector3f)polygon_points.getQuick(0), olddist, firstdist, clipped, clipped_count);
/*     */ 
/* 114 */     int ret = clipped_count[0];
/* 115 */     intArrays.release(clipped_count);
/* 116 */     return ret;
/*     */   }
/*     */ 
/*     */   public static int plane_clip_triangle(Vector4f plane, Vector3f point0, Vector3f point1, Vector3f point2, ObjectArrayList<Vector3f> clipped)
/*     */   {
/* 126 */     ArrayPool intArrays = ArrayPool.get(Integer.TYPE);
/*     */ 
/* 128 */     int[] clipped_count = (int[])intArrays.getFixed(1);
/* 129 */     clipped_count[0] = 0;
/*     */ 
/* 132 */     float firstdist = distance_point_plane(plane, point0);
/* 133 */     if (firstdist <= 1.192093E-007F) {
/* 134 */       ((Vector3f)clipped.getQuick(clipped_count[0])).set(point0);
/* 135 */       clipped_count[0] += 1;
/*     */     }
/*     */ 
/* 139 */     float olddist = firstdist;
/* 140 */     float dist = distance_point_plane(plane, point1);
/*     */ 
/* 142 */     plane_clip_polygon_collect(point0, point1, olddist, dist, clipped, clipped_count);
/*     */ 
/* 149 */     olddist = dist;
/*     */ 
/* 153 */     dist = distance_point_plane(plane, point2);
/*     */ 
/* 155 */     plane_clip_polygon_collect(point1, point2, olddist, dist, clipped, clipped_count);
/*     */ 
/* 161 */     olddist = dist;
/*     */ 
/* 166 */     plane_clip_polygon_collect(point2, point0, olddist, firstdist, clipped, clipped_count);
/*     */ 
/* 173 */     int ret = clipped_count[0];
/* 174 */     intArrays.release(clipped_count);
/* 175 */     return ret;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.ClipPolygon
 * JD-Core Version:    0.6.2
 */