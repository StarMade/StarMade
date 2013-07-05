/*     */ package com.bulletphysics.linearmath;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import javax.vecmath.Matrix3f;
/*     */ import javax.vecmath.Matrix4f;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class Transform
/*     */ {
/*  48 */   public final Matrix3f basis = new Matrix3f();
/*     */ 
/*  51 */   public final Vector3f origin = new Vector3f();
/*     */ 
/*     */   public Transform() {
/*     */   }
/*     */ 
/*     */   public Transform(Matrix3f mat) {
/*  57 */     this.basis.set(mat);
/*     */   }
/*     */ 
/*     */   public Transform(Matrix4f mat) {
/*  61 */     set(mat);
/*     */   }
/*     */ 
/*     */   public Transform(Transform tr) {
/*  65 */     set(tr);
/*     */   }
/*     */ 
/*     */   public void set(Transform tr) {
/*  69 */     this.basis.set(tr.basis);
/*  70 */     this.origin.set(tr.origin);
/*     */   }
/*     */ 
/*     */   public void set(Matrix3f mat) {
/*  74 */     this.basis.set(mat);
/*  75 */     this.origin.set(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   public void set(Matrix4f mat) {
/*  79 */     mat.getRotationScale(this.basis);
/*  80 */     this.origin.set(mat.m03, mat.m13, mat.m23);
/*     */   }
/*     */ 
/*     */   public void transform(Vector3f v) {
/*  84 */     this.basis.transform(v);
/*  85 */     v.add(this.origin);
/*     */   }
/*     */ 
/*     */   public void setIdentity() {
/*  89 */     this.basis.setIdentity();
/*  90 */     this.origin.set(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   public void inverse() {
/*  94 */     this.basis.transpose();
/*  95 */     this.origin.scale(-1.0F);
/*  96 */     this.basis.transform(this.origin);
/*     */   }
/*     */ 
/*     */   public void inverse(Transform tr) {
/* 100 */     set(tr);
/* 101 */     inverse();
/*     */   }
/*     */ 
/*     */   public void mul(Transform arg1) {
/* 105 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f vec = localStack.get$javax$vecmath$Vector3f(tr.origin);
/* 106 */       transform(vec);
/*     */ 
/* 108 */       this.basis.mul(tr.basis);
/* 109 */       this.origin.set(vec);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public void mul(Transform arg1, Transform arg2) {
/* 114 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f vec = localStack.get$javax$vecmath$Vector3f(tr2.origin);
/* 115 */       tr1.transform(vec);
/*     */ 
/* 117 */       this.basis.mul(tr1.basis, tr2.basis);
/* 118 */       this.origin.set(vec);
/*     */       return; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */   public void invXform(Vector3f arg1, Vector3f arg2) {
/* 122 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Matrix3f(); out.sub(inVec, this.origin);
/*     */ 
/* 124 */       Matrix3f mat = localStack.get$javax$vecmath$Matrix3f(this.basis);
/* 125 */       mat.transpose();
/* 126 */       mat.transform(out);
/*     */       return; } finally { localStack.pop$javax$vecmath$Matrix3f(); } throw finally;
/*     */   }
/*     */   public Quat4f getRotation(Quat4f out) {
/* 130 */     MatrixUtil.getRotation(this.basis, out);
/* 131 */     return out;
/*     */   }
/*     */ 
/*     */   public void setRotation(Quat4f q) {
/* 135 */     MatrixUtil.setRotation(this.basis, q);
/*     */   }
/*     */ 
/*     */   public void setFromOpenGLMatrix(float[] m) {
/* 139 */     MatrixUtil.setFromOpenGLSubMatrix(this.basis, m);
/* 140 */     this.origin.set(m[12], m[13], m[14]);
/*     */   }
/*     */ 
/*     */   public void getOpenGLMatrix(float[] m) {
/* 144 */     MatrixUtil.getOpenGLSubMatrix(this.basis, m);
/* 145 */     m[12] = this.origin.x;
/* 146 */     m[13] = this.origin.y;
/* 147 */     m[14] = this.origin.z;
/* 148 */     m[15] = 1.0F;
/*     */   }
/*     */ 
/*     */   public Matrix4f getMatrix(Matrix4f out) {
/* 152 */     out.set(this.basis);
/* 153 */     out.m03 = this.origin.x;
/* 154 */     out.m13 = this.origin.y;
/* 155 */     out.m23 = this.origin.z;
/* 156 */     return out;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 161 */     if ((obj == null) || (!(obj instanceof Transform))) return false;
/* 162 */     Transform tr = (Transform)obj;
/* 163 */     return (this.basis.equals(tr.basis)) && (this.origin.equals(tr.origin));
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 168 */     int hash = 3;
/* 169 */     hash = 41 * hash + this.basis.hashCode();
/* 170 */     hash = 41 * hash + this.origin.hashCode();
/* 171 */     return hash;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.Transform
 * JD-Core Version:    0.6.2
 */