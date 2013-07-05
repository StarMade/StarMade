/*     */ package com.bulletphysics.extras.gimpact;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.collision.shapes.TriangleShape;
/*     */ import com.bulletphysics.linearmath.Transform;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ public class TriangleShapeEx extends TriangleShape
/*     */ {
/*     */   public TriangleShapeEx()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TriangleShapeEx(Vector3f p0, Vector3f p1, Vector3f p2)
/*     */   {
/*  48 */     super(p0, p1, p2);
/*     */   }
/*     */ 
/*     */   public void getAabb(Transform arg1, Vector3f arg2, Vector3f arg3)
/*     */   {
/*  53 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); tmp7_5.push$javax$vecmath$Vector3f(); Vector3f tv0 = localStack.get$javax$vecmath$Vector3f(this.vertices1[0]);
/*  54 */       t.transform(tv0);
/*  55 */       Vector3f tv1 = localStack.get$javax$vecmath$Vector3f(this.vertices1[1]);
/*  56 */       t.transform(tv1);
/*  57 */       Vector3f tv2 = localStack.get$javax$vecmath$Vector3f(this.vertices1[2]);
/*  58 */       t.transform(tv2);
/*     */ 
/*  60 */       BoxCollision.AABB trianglebox = localStack.get$com$bulletphysics$extras$gimpact$BoxCollision$AABB();
/*  61 */       trianglebox.init(tv0, tv1, tv2, this.collisionMargin);
/*     */ 
/*  63 */       aabbMin.set(trianglebox.min);
/*  64 */       aabbMax.set(trianglebox.max);
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/*  65 */       .Stack tmp126_124 = localStack; tmp126_124.pop$com$bulletphysics$extras$gimpact$BoxCollision$AABB(); tmp126_124.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void applyTransform(Transform t) {
/*  68 */     t.transform(this.vertices1[0]);
/*  69 */     t.transform(this.vertices1[1]);
/*  70 */     t.transform(this.vertices1[2]);
/*     */   }
/*     */ 
/*     */   public void buildTriPlane(Vector4f arg1) {
/*  74 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f tmp1 = localStack.get$javax$vecmath$Vector3f();
/*  75 */       Vector3f tmp2 = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  77 */       Vector3f normal = localStack.get$javax$vecmath$Vector3f();
/*  78 */       tmp1.sub(this.vertices1[1], this.vertices1[0]);
/*  79 */       tmp2.sub(this.vertices1[2], this.vertices1[0]);
/*  80 */       normal.cross(tmp1, tmp2);
/*  81 */       normal.normalize();
/*     */ 
/*  83 */       plane.set(normal.x, normal.y, normal.z, this.vertices1[0].dot(normal));
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public boolean overlap_test_conservative(TriangleShapeEx arg1) {
/*  87 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector4f(); float total_margin = getMargin() + other.getMargin();
/*     */ 
/*  89 */       Vector4f plane0 = localStack.get$javax$vecmath$Vector4f();
/*  90 */       buildTriPlane(plane0);
/*  91 */       Vector4f plane1 = localStack.get$javax$vecmath$Vector4f();
/*  92 */       other.buildTriPlane(plane1);
/*     */ 
/*  95 */       float dis0 = ClipPolygon.distance_point_plane(plane0, other.vertices1[0]) - total_margin;
/*     */ 
/*  97 */       float dis1 = ClipPolygon.distance_point_plane(plane0, other.vertices1[1]) - total_margin;
/*     */ 
/*  99 */       float dis2 = ClipPolygon.distance_point_plane(plane0, other.vertices1[2]) - total_margin;
/*     */ 
/* 101 */       if ((dis0 > 0.0F) && (dis1 > 0.0F) && (dis2 > 0.0F)) {
/* 102 */         return false;
/*     */       }
/* 104 */       dis0 = ClipPolygon.distance_point_plane(plane1, this.vertices1[0]) - total_margin;
/*     */ 
/* 106 */       dis1 = ClipPolygon.distance_point_plane(plane1, this.vertices1[1]) - total_margin;
/*     */ 
/* 108 */       dis2 = ClipPolygon.distance_point_plane(plane1, this.vertices1[2]) - total_margin;
/*     */ 
/* 110 */       if ((dis0 > 0.0F) && (dis1 > 0.0F) && (dis2 > 0.0F)) {
/* 111 */         return false;
/*     */       }
/* 113 */       return true; } finally { localStack.pop$javax$vecmath$Vector4f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.extras.gimpact.TriangleShapeEx
 * JD-Core Version:    0.6.2
 */