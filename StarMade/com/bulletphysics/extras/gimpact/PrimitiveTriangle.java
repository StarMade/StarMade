/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ public class PrimitiveTriangle
/*     */ {
/*  42 */   private final ObjectArrayList<Vector3f> tmpVecList1 = new ObjectArrayList(16);
/*  43 */   private final ObjectArrayList<Vector3f> tmpVecList2 = new ObjectArrayList(16);
/*  44 */   private final ObjectArrayList<Vector3f> tmpVecList3 = new ObjectArrayList(16);
/*     */   public final Vector3f[] vertices;
/*     */   public final Vector4f plane;
/*     */   public float margin;
/*     */ 
/*     */   public PrimitiveTriangle()
/*     */   {
/*  47 */     for (int i = 0; i < 16; i++) {
/*  48 */       this.tmpVecList1.add(new Vector3f());
/*  49 */       this.tmpVecList2.add(new Vector3f());
/*  50 */       this.tmpVecList3.add(new Vector3f());
/*     */     }
/*     */ 
/*  54 */     this.vertices = new Vector3f[3];
/*  55 */     this.plane = new Vector4f();
/*  56 */     this.margin = 0.01F;
/*     */ 
/*  59 */     for (int i = 0; i < this.vertices.length; i++)
/*  60 */       this.vertices[i] = new Vector3f();
/*     */   }
/*     */ 
/*     */   public void set(PrimitiveTriangle tri)
/*     */   {
/*  65 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   public void buildTriPlane() {
/*  69 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  70 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  72 */       Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/*  73 */       tmp1.sub(this.vertices[1], this.vertices[0]);
/*  74 */       tmp2.sub(this.vertices[2], this.vertices[0]);
/*  75 */       normal.cross(tmp1, tmp2);
/*  76 */       normal.normalize();
/*     */ 
/*  78 */       this.plane.set(normal.x, normal.y, normal.z, this.vertices[0].dot(normal));
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean overlap_test_conservative(PrimitiveTriangle other)
/*     */   {
/*  85 */     float total_margin = this.margin + other.margin;
/*     */ 
/*  87 */     float dis0 = ClipPolygon.distance_point_plane(this.plane, other.vertices[0]) - total_margin;
/*     */ 
/*  89 */     float dis1 = ClipPolygon.distance_point_plane(this.plane, other.vertices[1]) - total_margin;
/*     */ 
/*  91 */     float dis2 = ClipPolygon.distance_point_plane(this.plane, other.vertices[2]) - total_margin;
/*     */ 
/*  93 */     if ((dis0 > 0.0F) && (dis1 > 0.0F) && (dis2 > 0.0F)) {
/*  94 */       return false;
/*     */     }
/*     */ 
/*  97 */     dis0 = ClipPolygon.distance_point_plane(other.plane, this.vertices[0]) - total_margin;
/*     */ 
/*  99 */     dis1 = ClipPolygon.distance_point_plane(other.plane, this.vertices[1]) - total_margin;
/*     */ 
/* 101 */     dis2 = ClipPolygon.distance_point_plane(other.plane, this.vertices[2]) - total_margin;
/*     */ 
/* 103 */     if ((dis0 > 0.0F) && (dis1 > 0.0F) && (dis2 > 0.0F)) {
/* 104 */       return false;
/*     */     }
/* 106 */     return true;
/*     */   }
/*     */ 
/*     */   public void get_edge_plane(int arg1, Vector4f arg2)
/*     */   {
/* 114 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f e0 = this.vertices[edge_index];
/* 115 */       Vector3f e1 = this.vertices[((edge_index + 1) % 3)];
/*     */ 
/* 117 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/* 118 */       tmp.set(this.plane.x, this.plane.y, this.plane.z);
/*     */ 
/* 120 */       GeometryOperations.edge_plane(e0, e1, tmp, plane);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void applyTransform(Transform t) {
/* 124 */     t.transform(this.vertices[0]);
/* 125 */     t.transform(this.vertices[1]);
/* 126 */     t.transform(this.vertices[2]);
/*     */   }
/*     */ 
/*     */   public int clip_triangle(PrimitiveTriangle arg1, ObjectArrayList<Vector3f> arg2)
/*     */   {
/* 137 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector4f(); ObjectArrayList temp_points = this.tmpVecList1;
/*     */ 
/* 139 */       Vector4f edgeplane = localStack.get$javax$vecmath$Vector4f();
/*     */ 
/* 141 */       get_edge_plane(0, edgeplane);
/*     */ 
/* 143 */       int clipped_count = ClipPolygon.plane_clip_triangle(edgeplane, other.vertices[0], other.vertices[1], other.vertices[2], temp_points);
/*     */ 
/* 145 */       if (clipped_count == 0) {
/* 146 */         return 0;
/*     */       }
/* 148 */       ObjectArrayList temp_points1 = this.tmpVecList2;
/*     */ 
/* 151 */       get_edge_plane(1, edgeplane);
/*     */ 
/* 153 */       clipped_count = ClipPolygon.plane_clip_polygon(edgeplane, temp_points, clipped_count, temp_points1);
/*     */ 
/* 155 */       if (clipped_count == 0) {
/* 156 */         return 0;
/*     */       }
/* 158 */       get_edge_plane(2, edgeplane);
/*     */ 
/* 160 */       return ClipPolygon.plane_clip_polygon(edgeplane, temp_points1, clipped_count, clipped_points);
/*     */     } finally {
/* 162 */       localStack.pop$javax$vecmath$Vector4f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public boolean find_triangle_collision_clip_method(PrimitiveTriangle arg1, TriangleContact arg2)
/*     */   {
/* 170 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$com$bulletphysics$extras$gimpact$TriangleContact(); float margin = this.margin + other.margin;
/*     */ 
/* 172 */       ObjectArrayList clipped_points = this.tmpVecList3;
/*     */ 
/* 178 */       TriangleContact contacts1 = localStack.get$com$bulletphysics$extras$gimpact$TriangleContact();
/*     */ 
/* 180 */       contacts1.separating_normal.set(this.plane);
/*     */ 
/* 182 */       int clipped_count = clip_triangle(other, clipped_points);
/*     */ 
/* 184 */       if (clipped_count == 0) {
/* 185 */         return false;
/*     */       }
/*     */ 
/* 189 */       contacts1.merge_points(contacts1.separating_normal, margin, clipped_points, clipped_count);
/* 190 */       if (contacts1.point_count == 0) {
/* 191 */         return false;
/*     */       }
/*     */ 
/* 194 */       contacts1.separating_normal.x *= -1.0F;
/* 195 */       contacts1.separating_normal.y *= -1.0F;
/* 196 */       contacts1.separating_normal.z *= -1.0F;
/*     */ 
/* 199 */       TriangleContact contacts2 = localStack.get$com$bulletphysics$extras$gimpact$TriangleContact();
/* 200 */       contacts2.separating_normal.set(other.plane);
/*     */ 
/* 202 */       clipped_count = other.clip_triangle(this, clipped_points);
/*     */ 
/* 204 */       if (clipped_count == 0) {
/* 205 */         return false;
/*     */       }
/*     */ 
/* 209 */       contacts2.merge_points(contacts2.separating_normal, margin, clipped_points, clipped_count);
/* 210 */       if (contacts2.point_count == 0) {
/* 211 */         return false;
/*     */       }
/*     */ 
/* 215 */       if (contacts2.penetration_depth < contacts1.penetration_depth) {
/* 216 */         contacts.copy_from(contacts2);
/*     */       }
/*     */       else {
/* 219 */         contacts.copy_from(contacts1);
/*     */       }
/* 221 */       return true; } finally { localStack.pop$com$bulletphysics$extras$gimpact$TriangleContact(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.PrimitiveTriangle
 * JD-Core Version:    0.6.2
 */