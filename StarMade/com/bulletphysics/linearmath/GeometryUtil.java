/*     */ package com.bulletphysics.linearmath;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import com.bulletphysics.util.ObjectArrayList;
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ public class GeometryUtil
/*     */ {
/*     */   public static boolean isPointInsidePlanes(ObjectArrayList<Vector4f> planeEquations, Vector3f point, float margin)
/*     */   {
/*  40 */     int numbrushes = planeEquations.size();
/*  41 */     for (int i = 0; i < numbrushes; i++) {
/*  42 */       Vector4f N1 = (Vector4f)planeEquations.getQuick(i);
/*  43 */       float dist = VectorUtil.dot3(N1, point) + N1.w - margin;
/*  44 */       if (dist > 0.0F) {
/*  45 */         return false;
/*     */       }
/*     */     }
/*  48 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean areVerticesBehindPlane(Vector4f planeNormal, ObjectArrayList<Vector3f> vertices, float margin) {
/*  52 */     int numvertices = vertices.size();
/*  53 */     for (int i = 0; i < numvertices; i++) {
/*  54 */       Vector3f N1 = (Vector3f)vertices.getQuick(i);
/*  55 */       float dist = VectorUtil.dot3(planeNormal, N1) + planeNormal.w - margin;
/*  56 */       if (dist > 0.0F) {
/*  57 */         return false;
/*     */       }
/*     */     }
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   private static boolean notExist(Vector4f planeEquation, ObjectArrayList<Vector4f> planeEquations) {
/*  64 */     int numbrushes = planeEquations.size();
/*  65 */     for (int i = 0; i < numbrushes; i++) {
/*  66 */       Vector4f N1 = (Vector4f)planeEquations.getQuick(i);
/*  67 */       if (VectorUtil.dot3(planeEquation, N1) > 0.999F) {
/*  68 */         return false;
/*     */       }
/*     */     }
/*  71 */     return true;
/*     */   }
/*     */ 
/*     */   public static void getPlaneEquationsFromVertices(ObjectArrayList<Vector3f> arg0, ObjectArrayList<Vector4f> arg1) {
/*  75 */     .Stack localStack = .Stack.get();
/*     */     try
/*     */     {
/*     */       .Stack tmp7_5 = localStack; tmp7_5.push$javax$vecmath$Vector3f(); tmp7_5.push$javax$vecmath$Vector4f(); Vector4f planeEquation = localStack.get$javax$vecmath$Vector4f();
/*  76 */       Vector3f edge0 = localStack.get$javax$vecmath$Vector3f(); Vector3f edge1 = localStack.get$javax$vecmath$Vector3f();
/*  77 */       Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/*  79 */       int numvertices = vertices.size();
/*     */ 
/*  81 */       for (int i = 0; i < numvertices; i++) {
/*  82 */         Vector3f N1 = (Vector3f)vertices.getQuick(i);
/*     */ 
/*  84 */         for (int j = i + 1; j < numvertices; j++) {
/*  85 */           Vector3f N2 = (Vector3f)vertices.getQuick(j);
/*     */ 
/*  87 */           for (int k = j + 1; k < numvertices; k++) {
/*  88 */             Vector3f N3 = (Vector3f)vertices.getQuick(k);
/*     */ 
/*  90 */             edge0.sub(N2, N1);
/*  91 */             edge1.sub(N3, N1);
/*  92 */             float normalSign = 1.0F;
/*  93 */             for (int ww = 0; ww < 2; ww++) {
/*  94 */               tmp.cross(edge0, edge1);
/*  95 */               planeEquation.x = (normalSign * tmp.x);
/*  96 */               planeEquation.y = (normalSign * tmp.y);
/*  97 */               planeEquation.z = (normalSign * tmp.z);
/*     */ 
/*  99 */               if (VectorUtil.lengthSquared3(planeEquation) > 1.0E-004F) {
/* 100 */                 VectorUtil.normalize3(planeEquation);
/* 101 */                 if (notExist(planeEquation, planeEquationsOut)) {
/* 102 */                   planeEquation.w = (-VectorUtil.dot3(planeEquation, N1));
/*     */ 
/* 105 */                   if (areVerticesBehindPlane(planeEquation, vertices, 0.01F)) {
/* 106 */                     planeEquationsOut.add(new Vector4f(planeEquation));
/*     */                   }
/*     */                 }
/*     */               }
/* 110 */               normalSign = -1.0F;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       return;
/*     */     }
/*     */     finally
/*     */     {
/* 115 */       .Stack tmp284_282 = localStack; tmp284_282.pop$javax$vecmath$Vector3f(); tmp284_282.pop$javax$vecmath$Vector4f(); } throw finally;
/*     */   }
/*     */   public static void getVerticesFromPlaneEquations(ObjectArrayList<Vector4f> arg0, ObjectArrayList<Vector3f> arg1) {
/* 118 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f n2n3 = localStack.get$javax$vecmath$Vector3f();
/* 119 */       Vector3f n3n1 = localStack.get$javax$vecmath$Vector3f();
/* 120 */       Vector3f n1n2 = localStack.get$javax$vecmath$Vector3f();
/* 121 */       Vector3f potentialVertex = localStack.get$javax$vecmath$Vector3f();
/*     */ 
/* 123 */       int numbrushes = planeEquations.size();
/*     */ 
/* 125 */       for (int i = 0; i < numbrushes; i++) {
/* 126 */         Vector4f N1 = (Vector4f)planeEquations.getQuick(i);
/*     */ 
/* 128 */         for (int j = i + 1; j < numbrushes; j++) {
/* 129 */           Vector4f N2 = (Vector4f)planeEquations.getQuick(j);
/*     */ 
/* 131 */           for (int k = j + 1; k < numbrushes; k++) {
/* 132 */             Vector4f N3 = (Vector4f)planeEquations.getQuick(k);
/*     */ 
/* 134 */             VectorUtil.cross3(n2n3, N2, N3);
/* 135 */             VectorUtil.cross3(n3n1, N3, N1);
/* 136 */             VectorUtil.cross3(n1n2, N1, N2);
/*     */ 
/* 138 */             if ((n2n3.lengthSquared() > 1.0E-004F) && (n3n1.lengthSquared() > 1.0E-004F) && (n1n2.lengthSquared() > 1.0E-004F))
/*     */             {
/* 147 */               float quotient = VectorUtil.dot3(N1, n2n3);
/* 148 */               if (Math.abs(quotient) > 1.0E-006F) {
/* 149 */                 quotient = -1.0F / quotient;
/* 150 */                 n2n3.scale(N1.w);
/* 151 */                 n3n1.scale(N2.w);
/* 152 */                 n1n2.scale(N3.w);
/* 153 */                 potentialVertex.set(n2n3);
/* 154 */                 potentialVertex.add(n3n1);
/* 155 */                 potentialVertex.add(n1n2);
/* 156 */                 potentialVertex.scale(quotient);
/*     */ 
/* 159 */                 if (isPointInsidePlanes(planeEquations, potentialVertex, 0.01F))
/* 160 */                   verticesOut.add(new Vector3f(potentialVertex));
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */       return; } finally {
/* 167 */       localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.GeometryUtil
 * JD-Core Version:    0.6.2
 */