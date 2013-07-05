/*     */ package org.lwjgl.util.vector;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ 
/*     */ public class Quaternion extends Vector
/*     */   implements ReadableVector4f
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public float x;
/*     */   public float y;
/*     */   public float z;
/*     */   public float w;
/*     */ 
/*     */   public Quaternion()
/*     */   {
/*  55 */     setIdentity();
/*     */   }
/*     */ 
/*     */   public Quaternion(ReadableVector4f src)
/*     */   {
/*  64 */     set(src);
/*     */   }
/*     */ 
/*     */   public Quaternion(float x, float y, float z, float w)
/*     */   {
/*  72 */     set(x, y, z, w);
/*     */   }
/*     */ 
/*     */   public void set(float x, float y)
/*     */   {
/*  81 */     this.x = x;
/*  82 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public void set(float x, float y, float z)
/*     */   {
/*  91 */     this.x = x;
/*  92 */     this.y = y;
/*  93 */     this.z = z;
/*     */   }
/*     */ 
/*     */   public void set(float x, float y, float z, float w)
/*     */   {
/* 103 */     this.x = x;
/* 104 */     this.y = y;
/* 105 */     this.z = z;
/* 106 */     this.w = w;
/*     */   }
/*     */ 
/*     */   public Quaternion set(ReadableVector4f src)
/*     */   {
/* 117 */     this.x = src.getX();
/* 118 */     this.y = src.getY();
/* 119 */     this.z = src.getZ();
/* 120 */     this.w = src.getW();
/* 121 */     return this;
/*     */   }
/*     */ 
/*     */   public Quaternion setIdentity()
/*     */   {
/* 129 */     return setIdentity(this);
/*     */   }
/*     */ 
/*     */   public static Quaternion setIdentity(Quaternion q)
/*     */   {
/* 138 */     q.x = 0.0F;
/* 139 */     q.y = 0.0F;
/* 140 */     q.z = 0.0F;
/* 141 */     q.w = 1.0F;
/* 142 */     return q;
/*     */   }
/*     */ 
/*     */   public float lengthSquared()
/*     */   {
/* 149 */     return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
/*     */   }
/*     */ 
/*     */   public static Quaternion normalise(Quaternion src, Quaternion dest)
/*     */   {
/* 163 */     float inv_l = 1.0F / src.length();
/*     */ 
/* 165 */     if (dest == null) {
/* 166 */       dest = new Quaternion();
/*     */     }
/* 168 */     dest.set(src.x * inv_l, src.y * inv_l, src.z * inv_l, src.w * inv_l);
/*     */ 
/* 170 */     return dest;
/*     */   }
/*     */ 
/*     */   public Quaternion normalise(Quaternion dest)
/*     */   {
/* 182 */     return normalise(this, dest);
/*     */   }
/*     */ 
/*     */   public static float dot(Quaternion left, Quaternion right)
/*     */   {
/* 195 */     return left.x * right.x + left.y * right.y + left.z * right.z + left.w * right.w;
/*     */   }
/*     */ 
/*     */   public Quaternion negate(Quaternion dest)
/*     */   {
/* 207 */     return negate(this, dest);
/*     */   }
/*     */ 
/*     */   public static Quaternion negate(Quaternion src, Quaternion dest)
/*     */   {
/* 220 */     if (dest == null) {
/* 221 */       dest = new Quaternion();
/*     */     }
/* 223 */     dest.x = (-src.x);
/* 224 */     dest.y = (-src.y);
/* 225 */     dest.z = (-src.z);
/* 226 */     dest.w = src.w;
/*     */ 
/* 228 */     return dest;
/*     */   }
/*     */ 
/*     */   public Vector negate()
/*     */   {
/* 235 */     return negate(this, this);
/*     */   }
/*     */ 
/*     */   public Vector load(FloatBuffer buf)
/*     */   {
/* 242 */     this.x = buf.get();
/* 243 */     this.y = buf.get();
/* 244 */     this.z = buf.get();
/* 245 */     this.w = buf.get();
/* 246 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector scale(float scale)
/*     */   {
/* 255 */     return scale(scale, this, this);
/*     */   }
/*     */ 
/*     */   public static Quaternion scale(float scale, Quaternion src, Quaternion dest)
/*     */   {
/* 266 */     if (dest == null)
/* 267 */       dest = new Quaternion();
/* 268 */     src.x *= scale;
/* 269 */     src.y *= scale;
/* 270 */     src.z *= scale;
/* 271 */     src.w *= scale;
/* 272 */     return dest;
/*     */   }
/*     */ 
/*     */   public Vector store(FloatBuffer buf)
/*     */   {
/* 279 */     buf.put(this.x);
/* 280 */     buf.put(this.y);
/* 281 */     buf.put(this.z);
/* 282 */     buf.put(this.w);
/*     */ 
/* 284 */     return this;
/*     */   }
/*     */ 
/*     */   public final float getX()
/*     */   {
/* 291 */     return this.x;
/*     */   }
/*     */ 
/*     */   public final float getY()
/*     */   {
/* 298 */     return this.y;
/*     */   }
/*     */ 
/*     */   public final void setX(float x)
/*     */   {
/* 307 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public final void setY(float y)
/*     */   {
/* 316 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public void setZ(float z)
/*     */   {
/* 325 */     this.z = z;
/*     */   }
/*     */ 
/*     */   public float getZ()
/*     */   {
/* 334 */     return this.z;
/*     */   }
/*     */ 
/*     */   public void setW(float w)
/*     */   {
/* 343 */     this.w = w;
/*     */   }
/*     */ 
/*     */   public float getW()
/*     */   {
/* 352 */     return this.w;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 356 */     return "Quaternion: " + this.x + " " + this.y + " " + this.z + " " + this.w;
/*     */   }
/*     */ 
/*     */   public static Quaternion mul(Quaternion left, Quaternion right, Quaternion dest)
/*     */   {
/* 371 */     if (dest == null)
/* 372 */       dest = new Quaternion();
/* 373 */     dest.set(left.x * right.w + left.w * right.x + left.y * right.z - left.z * right.y, left.y * right.w + left.w * right.y + left.z * right.x - left.x * right.z, left.z * right.w + left.w * right.z + left.x * right.y - left.y * right.x, left.w * right.w - left.x * right.x - left.y * right.y - left.z * right.z);
/*     */ 
/* 379 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Quaternion mulInverse(Quaternion left, Quaternion right, Quaternion dest)
/*     */   {
/* 395 */     float n = right.lengthSquared();
/*     */ 
/* 397 */     n = n == 0.0D ? n : 1.0F / n;
/*     */ 
/* 399 */     if (dest == null)
/* 400 */       dest = new Quaternion();
/* 401 */     dest.set((left.x * right.w - left.w * right.x - left.y * right.z + left.z * right.y) * n, (left.y * right.w - left.w * right.y - left.z * right.x + left.x * right.z) * n, (left.z * right.w - left.w * right.z - left.x * right.y + left.y * right.x) * n, (left.w * right.w + left.x * right.x + left.y * right.y + left.z * right.z) * n);
/*     */ 
/* 412 */     return dest;
/*     */   }
/*     */ 
/*     */   public final void setFromAxisAngle(Vector4f a1)
/*     */   {
/* 423 */     this.x = a1.x;
/* 424 */     this.y = a1.y;
/* 425 */     this.z = a1.z;
/* 426 */     float n = (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */ 
/* 428 */     float s = (float)(Math.sin(0.5D * a1.w) / n);
/* 429 */     this.x *= s;
/* 430 */     this.y *= s;
/* 431 */     this.z *= s;
/* 432 */     this.w = ((float)Math.cos(0.5D * a1.w));
/*     */   }
/*     */ 
/*     */   public final Quaternion setFromMatrix(Matrix4f m)
/*     */   {
/* 444 */     return setFromMatrix(m, this);
/*     */   }
/*     */ 
/*     */   public static Quaternion setFromMatrix(Matrix4f m, Quaternion q)
/*     */   {
/* 458 */     return q.setFromMat(m.m00, m.m01, m.m02, m.m10, m.m11, m.m12, m.m20, m.m21, m.m22);
/*     */   }
/*     */ 
/*     */   public final Quaternion setFromMatrix(Matrix3f m)
/*     */   {
/* 470 */     return setFromMatrix(m, this);
/*     */   }
/*     */ 
/*     */   public static Quaternion setFromMatrix(Matrix3f m, Quaternion q)
/*     */   {
/* 484 */     return q.setFromMat(m.m00, m.m01, m.m02, m.m10, m.m11, m.m12, m.m20, m.m21, m.m22);
/*     */   }
/*     */ 
/*     */   private Quaternion setFromMat(float m00, float m01, float m02, float m10, float m11, float m12, float m20, float m21, float m22)
/*     */   {
/* 495 */     float tr = m00 + m11 + m22;
/* 496 */     if (tr >= 0.0D) {
/* 497 */       float s = (float)Math.sqrt(tr + 1.0D);
/* 498 */       this.w = (s * 0.5F);
/* 499 */       s = 0.5F / s;
/* 500 */       this.x = ((m21 - m12) * s);
/* 501 */       this.y = ((m02 - m20) * s);
/* 502 */       this.z = ((m10 - m01) * s);
/*     */     } else {
/* 504 */       float max = Math.max(Math.max(m00, m11), m22);
/* 505 */       if (max == m00) {
/* 506 */         float s = (float)Math.sqrt(m00 - (m11 + m22) + 1.0D);
/* 507 */         this.x = (s * 0.5F);
/* 508 */         s = 0.5F / s;
/* 509 */         this.y = ((m01 + m10) * s);
/* 510 */         this.z = ((m20 + m02) * s);
/* 511 */         this.w = ((m21 - m12) * s);
/* 512 */       } else if (max == m11) {
/* 513 */         float s = (float)Math.sqrt(m11 - (m22 + m00) + 1.0D);
/* 514 */         this.y = (s * 0.5F);
/* 515 */         s = 0.5F / s;
/* 516 */         this.z = ((m12 + m21) * s);
/* 517 */         this.x = ((m01 + m10) * s);
/* 518 */         this.w = ((m02 - m20) * s);
/*     */       } else {
/* 520 */         float s = (float)Math.sqrt(m22 - (m00 + m11) + 1.0D);
/* 521 */         this.z = (s * 0.5F);
/* 522 */         s = 0.5F / s;
/* 523 */         this.x = ((m20 + m02) * s);
/* 524 */         this.y = ((m12 + m21) * s);
/* 525 */         this.w = ((m10 - m01) * s);
/*     */       }
/*     */     }
/* 528 */     return this;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Quaternion
 * JD-Core Version:    0.6.2
 */