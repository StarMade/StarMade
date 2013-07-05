/*     */ package org.lwjgl.util.vector;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.nio.FloatBuffer;
/*     */ 
/*     */ public class Matrix3f extends Matrix
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public float m00;
/*     */   public float m01;
/*     */   public float m02;
/*     */   public float m10;
/*     */   public float m11;
/*     */   public float m12;
/*     */   public float m20;
/*     */   public float m21;
/*     */   public float m22;
/*     */ 
/*     */   public Matrix3f()
/*     */   {
/*  65 */     setIdentity();
/*     */   }
/*     */ 
/*     */   public Matrix3f load(Matrix3f src)
/*     */   {
/*  74 */     return load(src, this);
/*     */   }
/*     */ 
/*     */   public static Matrix3f load(Matrix3f src, Matrix3f dest)
/*     */   {
/*  84 */     if (dest == null) {
/*  85 */       dest = new Matrix3f();
/*     */     }
/*  87 */     dest.m00 = src.m00;
/*  88 */     dest.m10 = src.m10;
/*  89 */     dest.m20 = src.m20;
/*  90 */     dest.m01 = src.m01;
/*  91 */     dest.m11 = src.m11;
/*  92 */     dest.m21 = src.m21;
/*  93 */     dest.m02 = src.m02;
/*  94 */     dest.m12 = src.m12;
/*  95 */     dest.m22 = src.m22;
/*     */ 
/*  97 */     return dest;
/*     */   }
/*     */ 
/*     */   public Matrix load(FloatBuffer buf)
/*     */   {
/* 109 */     this.m00 = buf.get();
/* 110 */     this.m01 = buf.get();
/* 111 */     this.m02 = buf.get();
/* 112 */     this.m10 = buf.get();
/* 113 */     this.m11 = buf.get();
/* 114 */     this.m12 = buf.get();
/* 115 */     this.m20 = buf.get();
/* 116 */     this.m21 = buf.get();
/* 117 */     this.m22 = buf.get();
/*     */ 
/* 119 */     return this;
/*     */   }
/*     */ 
/*     */   public Matrix loadTranspose(FloatBuffer buf)
/*     */   {
/* 131 */     this.m00 = buf.get();
/* 132 */     this.m10 = buf.get();
/* 133 */     this.m20 = buf.get();
/* 134 */     this.m01 = buf.get();
/* 135 */     this.m11 = buf.get();
/* 136 */     this.m21 = buf.get();
/* 137 */     this.m02 = buf.get();
/* 138 */     this.m12 = buf.get();
/* 139 */     this.m22 = buf.get();
/*     */ 
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */   public Matrix store(FloatBuffer buf)
/*     */   {
/* 150 */     buf.put(this.m00);
/* 151 */     buf.put(this.m01);
/* 152 */     buf.put(this.m02);
/* 153 */     buf.put(this.m10);
/* 154 */     buf.put(this.m11);
/* 155 */     buf.put(this.m12);
/* 156 */     buf.put(this.m20);
/* 157 */     buf.put(this.m21);
/* 158 */     buf.put(this.m22);
/* 159 */     return this;
/*     */   }
/*     */ 
/*     */   public Matrix storeTranspose(FloatBuffer buf)
/*     */   {
/* 168 */     buf.put(this.m00);
/* 169 */     buf.put(this.m10);
/* 170 */     buf.put(this.m20);
/* 171 */     buf.put(this.m01);
/* 172 */     buf.put(this.m11);
/* 173 */     buf.put(this.m21);
/* 174 */     buf.put(this.m02);
/* 175 */     buf.put(this.m12);
/* 176 */     buf.put(this.m22);
/* 177 */     return this;
/*     */   }
/*     */ 
/*     */   public static Matrix3f add(Matrix3f left, Matrix3f right, Matrix3f dest)
/*     */   {
/* 188 */     if (dest == null) {
/* 189 */       dest = new Matrix3f();
/*     */     }
/* 191 */     left.m00 += right.m00;
/* 192 */     left.m01 += right.m01;
/* 193 */     left.m02 += right.m02;
/* 194 */     left.m10 += right.m10;
/* 195 */     left.m11 += right.m11;
/* 196 */     left.m12 += right.m12;
/* 197 */     left.m20 += right.m20;
/* 198 */     left.m21 += right.m21;
/* 199 */     left.m22 += right.m22;
/*     */ 
/* 201 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Matrix3f sub(Matrix3f left, Matrix3f right, Matrix3f dest)
/*     */   {
/* 212 */     if (dest == null) {
/* 213 */       dest = new Matrix3f();
/*     */     }
/* 215 */     left.m00 -= right.m00;
/* 216 */     left.m01 -= right.m01;
/* 217 */     left.m02 -= right.m02;
/* 218 */     left.m10 -= right.m10;
/* 219 */     left.m11 -= right.m11;
/* 220 */     left.m12 -= right.m12;
/* 221 */     left.m20 -= right.m20;
/* 222 */     left.m21 -= right.m21;
/* 223 */     left.m22 -= right.m22;
/*     */ 
/* 225 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Matrix3f mul(Matrix3f left, Matrix3f right, Matrix3f dest)
/*     */   {
/* 236 */     if (dest == null) {
/* 237 */       dest = new Matrix3f();
/*     */     }
/* 239 */     float m00 = left.m00 * right.m00 + left.m10 * right.m01 + left.m20 * right.m02;
/*     */ 
/* 241 */     float m01 = left.m01 * right.m00 + left.m11 * right.m01 + left.m21 * right.m02;
/*     */ 
/* 243 */     float m02 = left.m02 * right.m00 + left.m12 * right.m01 + left.m22 * right.m02;
/*     */ 
/* 245 */     float m10 = left.m00 * right.m10 + left.m10 * right.m11 + left.m20 * right.m12;
/*     */ 
/* 247 */     float m11 = left.m01 * right.m10 + left.m11 * right.m11 + left.m21 * right.m12;
/*     */ 
/* 249 */     float m12 = left.m02 * right.m10 + left.m12 * right.m11 + left.m22 * right.m12;
/*     */ 
/* 251 */     float m20 = left.m00 * right.m20 + left.m10 * right.m21 + left.m20 * right.m22;
/*     */ 
/* 253 */     float m21 = left.m01 * right.m20 + left.m11 * right.m21 + left.m21 * right.m22;
/*     */ 
/* 255 */     float m22 = left.m02 * right.m20 + left.m12 * right.m21 + left.m22 * right.m22;
/*     */ 
/* 258 */     dest.m00 = m00;
/* 259 */     dest.m01 = m01;
/* 260 */     dest.m02 = m02;
/* 261 */     dest.m10 = m10;
/* 262 */     dest.m11 = m11;
/* 263 */     dest.m12 = m12;
/* 264 */     dest.m20 = m20;
/* 265 */     dest.m21 = m21;
/* 266 */     dest.m22 = m22;
/*     */ 
/* 268 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Vector3f transform(Matrix3f left, Vector3f right, Vector3f dest)
/*     */   {
/* 280 */     if (dest == null) {
/* 281 */       dest = new Vector3f();
/*     */     }
/* 283 */     float x = left.m00 * right.x + left.m10 * right.y + left.m20 * right.z;
/* 284 */     float y = left.m01 * right.x + left.m11 * right.y + left.m21 * right.z;
/* 285 */     float z = left.m02 * right.x + left.m12 * right.y + left.m22 * right.z;
/*     */ 
/* 287 */     dest.x = x;
/* 288 */     dest.y = y;
/* 289 */     dest.z = z;
/*     */ 
/* 291 */     return dest;
/*     */   }
/*     */ 
/*     */   public Matrix transpose()
/*     */   {
/* 299 */     return transpose(this, this);
/*     */   }
/*     */ 
/*     */   public Matrix3f transpose(Matrix3f dest)
/*     */   {
/* 308 */     return transpose(this, dest);
/*     */   }
/*     */ 
/*     */   public static Matrix3f transpose(Matrix3f src, Matrix3f dest)
/*     */   {
/* 318 */     if (dest == null)
/* 319 */       dest = new Matrix3f();
/* 320 */     float m00 = src.m00;
/* 321 */     float m01 = src.m10;
/* 322 */     float m02 = src.m20;
/* 323 */     float m10 = src.m01;
/* 324 */     float m11 = src.m11;
/* 325 */     float m12 = src.m21;
/* 326 */     float m20 = src.m02;
/* 327 */     float m21 = src.m12;
/* 328 */     float m22 = src.m22;
/*     */ 
/* 330 */     dest.m00 = m00;
/* 331 */     dest.m01 = m01;
/* 332 */     dest.m02 = m02;
/* 333 */     dest.m10 = m10;
/* 334 */     dest.m11 = m11;
/* 335 */     dest.m12 = m12;
/* 336 */     dest.m20 = m20;
/* 337 */     dest.m21 = m21;
/* 338 */     dest.m22 = m22;
/* 339 */     return dest;
/*     */   }
/*     */ 
/*     */   public float determinant()
/*     */   {
/* 346 */     float f = this.m00 * (this.m11 * this.m22 - this.m12 * this.m21) + this.m01 * (this.m12 * this.m20 - this.m10 * this.m22) + this.m02 * (this.m10 * this.m21 - this.m11 * this.m20);
/*     */ 
/* 350 */     return f;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 357 */     StringBuilder buf = new StringBuilder();
/* 358 */     buf.append(this.m00).append(' ').append(this.m10).append(' ').append(this.m20).append(' ').append('\n');
/* 359 */     buf.append(this.m01).append(' ').append(this.m11).append(' ').append(this.m21).append(' ').append('\n');
/* 360 */     buf.append(this.m02).append(' ').append(this.m12).append(' ').append(this.m22).append(' ').append('\n');
/* 361 */     return buf.toString();
/*     */   }
/*     */ 
/*     */   public Matrix invert()
/*     */   {
/* 369 */     return invert(this, this);
/*     */   }
/*     */ 
/*     */   public static Matrix3f invert(Matrix3f src, Matrix3f dest)
/*     */   {
/* 379 */     float determinant = src.determinant();
/*     */ 
/* 381 */     if (determinant != 0.0F) {
/* 382 */       if (dest == null) {
/* 383 */         dest = new Matrix3f();
/*     */       }
/*     */ 
/* 392 */       float determinant_inv = 1.0F / determinant;
/*     */ 
/* 395 */       float t00 = src.m11 * src.m22 - src.m12 * src.m21;
/* 396 */       float t01 = -src.m10 * src.m22 + src.m12 * src.m20;
/* 397 */       float t02 = src.m10 * src.m21 - src.m11 * src.m20;
/* 398 */       float t10 = -src.m01 * src.m22 + src.m02 * src.m21;
/* 399 */       float t11 = src.m00 * src.m22 - src.m02 * src.m20;
/* 400 */       float t12 = -src.m00 * src.m21 + src.m01 * src.m20;
/* 401 */       float t20 = src.m01 * src.m12 - src.m02 * src.m11;
/* 402 */       float t21 = -src.m00 * src.m12 + src.m02 * src.m10;
/* 403 */       float t22 = src.m00 * src.m11 - src.m01 * src.m10;
/*     */ 
/* 405 */       dest.m00 = (t00 * determinant_inv);
/* 406 */       dest.m11 = (t11 * determinant_inv);
/* 407 */       dest.m22 = (t22 * determinant_inv);
/* 408 */       dest.m01 = (t10 * determinant_inv);
/* 409 */       dest.m10 = (t01 * determinant_inv);
/* 410 */       dest.m20 = (t02 * determinant_inv);
/* 411 */       dest.m02 = (t20 * determinant_inv);
/* 412 */       dest.m12 = (t21 * determinant_inv);
/* 413 */       dest.m21 = (t12 * determinant_inv);
/* 414 */       return dest;
/*     */     }
/* 416 */     return null;
/*     */   }
/*     */ 
/*     */   public Matrix negate()
/*     */   {
/* 425 */     return negate(this);
/*     */   }
/*     */ 
/*     */   public Matrix3f negate(Matrix3f dest)
/*     */   {
/* 434 */     return negate(this, dest);
/*     */   }
/*     */ 
/*     */   public static Matrix3f negate(Matrix3f src, Matrix3f dest)
/*     */   {
/* 444 */     if (dest == null) {
/* 445 */       dest = new Matrix3f();
/*     */     }
/* 447 */     dest.m00 = (-src.m00);
/* 448 */     dest.m01 = (-src.m02);
/* 449 */     dest.m02 = (-src.m01);
/* 450 */     dest.m10 = (-src.m10);
/* 451 */     dest.m11 = (-src.m12);
/* 452 */     dest.m12 = (-src.m11);
/* 453 */     dest.m20 = (-src.m20);
/* 454 */     dest.m21 = (-src.m22);
/* 455 */     dest.m22 = (-src.m21);
/* 456 */     return dest;
/*     */   }
/*     */ 
/*     */   public Matrix setIdentity()
/*     */   {
/* 464 */     return setIdentity(this);
/*     */   }
/*     */ 
/*     */   public static Matrix3f setIdentity(Matrix3f m)
/*     */   {
/* 473 */     m.m00 = 1.0F;
/* 474 */     m.m01 = 0.0F;
/* 475 */     m.m02 = 0.0F;
/* 476 */     m.m10 = 0.0F;
/* 477 */     m.m11 = 1.0F;
/* 478 */     m.m12 = 0.0F;
/* 479 */     m.m20 = 0.0F;
/* 480 */     m.m21 = 0.0F;
/* 481 */     m.m22 = 1.0F;
/* 482 */     return m;
/*     */   }
/*     */ 
/*     */   public Matrix setZero()
/*     */   {
/* 490 */     return setZero(this);
/*     */   }
/*     */ 
/*     */   public static Matrix3f setZero(Matrix3f m)
/*     */   {
/* 499 */     m.m00 = 0.0F;
/* 500 */     m.m01 = 0.0F;
/* 501 */     m.m02 = 0.0F;
/* 502 */     m.m10 = 0.0F;
/* 503 */     m.m11 = 0.0F;
/* 504 */     m.m12 = 0.0F;
/* 505 */     m.m20 = 0.0F;
/* 506 */     m.m21 = 0.0F;
/* 507 */     m.m22 = 0.0F;
/* 508 */     return m;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Matrix3f
 * JD-Core Version:    0.6.2
 */