/*     */ package org.lwjgl.util.vector;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.nio.FloatBuffer;
/*     */ 
/*     */ public class Matrix2f extends Matrix
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public float m00;
/*     */   public float m01;
/*     */   public float m10;
/*     */   public float m11;
/*     */ 
/*     */   public Matrix2f()
/*     */   {
/*  56 */     setIdentity();
/*     */   }
/*     */ 
/*     */   public Matrix2f(Matrix2f src)
/*     */   {
/*  63 */     load(src);
/*     */   }
/*     */ 
/*     */   public Matrix2f load(Matrix2f src)
/*     */   {
/*  72 */     return load(src, this);
/*     */   }
/*     */ 
/*     */   public static Matrix2f load(Matrix2f src, Matrix2f dest)
/*     */   {
/*  82 */     if (dest == null) {
/*  83 */       dest = new Matrix2f();
/*     */     }
/*  85 */     dest.m00 = src.m00;
/*  86 */     dest.m01 = src.m01;
/*  87 */     dest.m10 = src.m10;
/*  88 */     dest.m11 = src.m11;
/*     */ 
/*  90 */     return dest;
/*     */   }
/*     */ 
/*     */   public Matrix load(FloatBuffer buf)
/*     */   {
/* 102 */     this.m00 = buf.get();
/* 103 */     this.m01 = buf.get();
/* 104 */     this.m10 = buf.get();
/* 105 */     this.m11 = buf.get();
/*     */ 
/* 107 */     return this;
/*     */   }
/*     */ 
/*     */   public Matrix loadTranspose(FloatBuffer buf)
/*     */   {
/* 119 */     this.m00 = buf.get();
/* 120 */     this.m10 = buf.get();
/* 121 */     this.m01 = buf.get();
/* 122 */     this.m11 = buf.get();
/*     */ 
/* 124 */     return this;
/*     */   }
/*     */ 
/*     */   public Matrix store(FloatBuffer buf)
/*     */   {
/* 133 */     buf.put(this.m00);
/* 134 */     buf.put(this.m01);
/* 135 */     buf.put(this.m10);
/* 136 */     buf.put(this.m11);
/* 137 */     return this;
/*     */   }
/*     */ 
/*     */   public Matrix storeTranspose(FloatBuffer buf)
/*     */   {
/* 146 */     buf.put(this.m00);
/* 147 */     buf.put(this.m10);
/* 148 */     buf.put(this.m01);
/* 149 */     buf.put(this.m11);
/* 150 */     return this;
/*     */   }
/*     */ 
/*     */   public static Matrix2f add(Matrix2f left, Matrix2f right, Matrix2f dest)
/*     */   {
/* 163 */     if (dest == null) {
/* 164 */       dest = new Matrix2f();
/*     */     }
/* 166 */     left.m00 += right.m00;
/* 167 */     left.m01 += right.m01;
/* 168 */     left.m10 += right.m10;
/* 169 */     left.m11 += right.m11;
/*     */ 
/* 171 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Matrix2f sub(Matrix2f left, Matrix2f right, Matrix2f dest)
/*     */   {
/* 182 */     if (dest == null) {
/* 183 */       dest = new Matrix2f();
/*     */     }
/* 185 */     left.m00 -= right.m00;
/* 186 */     left.m01 -= right.m01;
/* 187 */     left.m10 -= right.m10;
/* 188 */     left.m11 -= right.m11;
/*     */ 
/* 190 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Matrix2f mul(Matrix2f left, Matrix2f right, Matrix2f dest)
/*     */   {
/* 201 */     if (dest == null) {
/* 202 */       dest = new Matrix2f();
/*     */     }
/* 204 */     float m00 = left.m00 * right.m00 + left.m10 * right.m01;
/* 205 */     float m01 = left.m01 * right.m00 + left.m11 * right.m01;
/* 206 */     float m10 = left.m00 * right.m10 + left.m10 * right.m11;
/* 207 */     float m11 = left.m01 * right.m10 + left.m11 * right.m11;
/*     */ 
/* 209 */     dest.m00 = m00;
/* 210 */     dest.m01 = m01;
/* 211 */     dest.m10 = m10;
/* 212 */     dest.m11 = m11;
/*     */ 
/* 214 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Vector2f transform(Matrix2f left, Vector2f right, Vector2f dest)
/*     */   {
/* 226 */     if (dest == null) {
/* 227 */       dest = new Vector2f();
/*     */     }
/* 229 */     float x = left.m00 * right.x + left.m10 * right.y;
/* 230 */     float y = left.m01 * right.x + left.m11 * right.y;
/*     */ 
/* 232 */     dest.x = x;
/* 233 */     dest.y = y;
/*     */ 
/* 235 */     return dest;
/*     */   }
/*     */ 
/*     */   public Matrix transpose()
/*     */   {
/* 243 */     return transpose(this);
/*     */   }
/*     */ 
/*     */   public Matrix2f transpose(Matrix2f dest)
/*     */   {
/* 252 */     return transpose(this, dest);
/*     */   }
/*     */ 
/*     */   public static Matrix2f transpose(Matrix2f src, Matrix2f dest)
/*     */   {
/* 262 */     if (dest == null) {
/* 263 */       dest = new Matrix2f();
/*     */     }
/* 265 */     float m01 = src.m10;
/* 266 */     float m10 = src.m01;
/*     */ 
/* 268 */     dest.m01 = m01;
/* 269 */     dest.m10 = m10;
/*     */ 
/* 271 */     return dest;
/*     */   }
/*     */ 
/*     */   public Matrix invert()
/*     */   {
/* 279 */     return invert(this, this);
/*     */   }
/*     */ 
/*     */   public static Matrix2f invert(Matrix2f src, Matrix2f dest)
/*     */   {
/* 293 */     float determinant = src.determinant();
/* 294 */     if (determinant != 0.0F) {
/* 295 */       if (dest == null)
/* 296 */         dest = new Matrix2f();
/* 297 */       float determinant_inv = 1.0F / determinant;
/* 298 */       float t00 = src.m11 * determinant_inv;
/* 299 */       float t01 = -src.m01 * determinant_inv;
/* 300 */       float t11 = src.m00 * determinant_inv;
/* 301 */       float t10 = -src.m10 * determinant_inv;
/*     */ 
/* 303 */       dest.m00 = t00;
/* 304 */       dest.m01 = t01;
/* 305 */       dest.m10 = t10;
/* 306 */       dest.m11 = t11;
/* 307 */       return dest;
/*     */     }
/* 309 */     return null;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 316 */     StringBuilder buf = new StringBuilder();
/* 317 */     buf.append(this.m00).append(' ').append(this.m10).append(' ').append('\n');
/* 318 */     buf.append(this.m01).append(' ').append(this.m11).append(' ').append('\n');
/* 319 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public Matrix negate()
/*     */   {
/* 327 */     return negate(this);
/*     */   }
/*     */ 
/*     */   public Matrix2f negate(Matrix2f dest)
/*     */   {
/* 336 */     return negate(this, dest);
/*     */   }
/*     */ 
/*     */   public static Matrix2f negate(Matrix2f src, Matrix2f dest)
/*     */   {
/* 346 */     if (dest == null) {
/* 347 */       dest = new Matrix2f();
/*     */     }
/* 349 */     dest.m00 = (-src.m00);
/* 350 */     dest.m01 = (-src.m01);
/* 351 */     dest.m10 = (-src.m10);
/* 352 */     dest.m11 = (-src.m11);
/*     */ 
/* 354 */     return dest;
/*     */   }
/*     */ 
/*     */   public Matrix setIdentity()
/*     */   {
/* 362 */     return setIdentity(this);
/*     */   }
/*     */ 
/*     */   public static Matrix2f setIdentity(Matrix2f src)
/*     */   {
/* 371 */     src.m00 = 1.0F;
/* 372 */     src.m01 = 0.0F;
/* 373 */     src.m10 = 0.0F;
/* 374 */     src.m11 = 1.0F;
/* 375 */     return src;
/*     */   }
/*     */ 
/*     */   public Matrix setZero()
/*     */   {
/* 383 */     return setZero(this);
/*     */   }
/*     */ 
/*     */   public static Matrix2f setZero(Matrix2f src) {
/* 387 */     src.m00 = 0.0F;
/* 388 */     src.m01 = 0.0F;
/* 389 */     src.m10 = 0.0F;
/* 390 */     src.m11 = 0.0F;
/* 391 */     return src;
/*     */   }
/*     */ 
/*     */   public float determinant()
/*     */   {
/* 398 */     return this.m00 * this.m11 - this.m01 * this.m10;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Matrix2f
 * JD-Core Version:    0.6.2
 */