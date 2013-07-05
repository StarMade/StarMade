/*     */ package org.lwjgl.util.vector;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.nio.FloatBuffer;
/*     */ 
/*     */ public class Vector4f extends Vector
/*     */   implements Serializable, ReadableVector4f, WritableVector4f
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public float x;
/*     */   public float y;
/*     */   public float z;
/*     */   public float w;
/*     */ 
/*     */   public Vector4f()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Vector4f(ReadableVector4f src)
/*     */   {
/*  63 */     set(src);
/*     */   }
/*     */ 
/*     */   public Vector4f(float x, float y, float z, float w)
/*     */   {
/*  70 */     set(x, y, z, w);
/*     */   }
/*     */ 
/*     */   public void set(float x, float y)
/*     */   {
/*  77 */     this.x = x;
/*  78 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public void set(float x, float y, float z)
/*     */   {
/*  85 */     this.x = x;
/*  86 */     this.y = y;
/*  87 */     this.z = z;
/*     */   }
/*     */ 
/*     */   public void set(float x, float y, float z, float w)
/*     */   {
/*  94 */     this.x = x;
/*  95 */     this.y = y;
/*  96 */     this.z = z;
/*  97 */     this.w = w;
/*     */   }
/*     */ 
/*     */   public Vector4f set(ReadableVector4f src)
/*     */   {
/* 106 */     this.x = src.getX();
/* 107 */     this.y = src.getY();
/* 108 */     this.z = src.getZ();
/* 109 */     this.w = src.getW();
/* 110 */     return this;
/*     */   }
/*     */ 
/*     */   public float lengthSquared()
/*     */   {
/* 117 */     return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
/*     */   }
/*     */ 
/*     */   public Vector4f translate(float x, float y, float z, float w)
/*     */   {
/* 127 */     this.x += x;
/* 128 */     this.y += y;
/* 129 */     this.z += z;
/* 130 */     this.w += w;
/* 131 */     return this;
/*     */   }
/*     */ 
/*     */   public static Vector4f add(Vector4f left, Vector4f right, Vector4f dest)
/*     */   {
/* 143 */     if (dest == null) {
/* 144 */       return new Vector4f(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
/*     */     }
/* 146 */     dest.set(left.x + right.x, left.y + right.y, left.z + right.z, left.w + right.w);
/* 147 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Vector4f sub(Vector4f left, Vector4f right, Vector4f dest)
/*     */   {
/* 160 */     if (dest == null) {
/* 161 */       return new Vector4f(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
/*     */     }
/* 163 */     dest.set(left.x - right.x, left.y - right.y, left.z - right.z, left.w - right.w);
/* 164 */     return dest;
/*     */   }
/*     */ 
/*     */   public Vector negate()
/*     */   {
/* 174 */     this.x = (-this.x);
/* 175 */     this.y = (-this.y);
/* 176 */     this.z = (-this.z);
/* 177 */     this.w = (-this.w);
/* 178 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector4f negate(Vector4f dest)
/*     */   {
/* 187 */     if (dest == null)
/* 188 */       dest = new Vector4f();
/* 189 */     dest.x = (-this.x);
/* 190 */     dest.y = (-this.y);
/* 191 */     dest.z = (-this.z);
/* 192 */     dest.w = (-this.w);
/* 193 */     return dest;
/*     */   }
/*     */ 
/*     */   public Vector4f normalise(Vector4f dest)
/*     */   {
/* 203 */     float l = length();
/*     */ 
/* 205 */     if (dest == null)
/* 206 */       dest = new Vector4f(this.x / l, this.y / l, this.z / l, this.w / l);
/*     */     else {
/* 208 */       dest.set(this.x / l, this.y / l, this.z / l, this.w / l);
/*     */     }
/* 210 */     return dest;
/*     */   }
/*     */ 
/*     */   public static float dot(Vector4f left, Vector4f right)
/*     */   {
/* 221 */     return left.x * right.x + left.y * right.y + left.z * right.z + left.w * right.w;
/*     */   }
/*     */ 
/*     */   public static float angle(Vector4f a, Vector4f b)
/*     */   {
/* 231 */     float dls = dot(a, b) / (a.length() * b.length());
/* 232 */     if (dls < -1.0F)
/* 233 */       dls = -1.0F;
/* 234 */     else if (dls > 1.0F)
/* 235 */       dls = 1.0F;
/* 236 */     return (float)Math.acos(dls);
/*     */   }
/*     */ 
/*     */   public Vector load(FloatBuffer buf)
/*     */   {
/* 243 */     this.x = buf.get();
/* 244 */     this.y = buf.get();
/* 245 */     this.z = buf.get();
/* 246 */     this.w = buf.get();
/* 247 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector scale(float scale)
/*     */   {
/* 254 */     this.x *= scale;
/* 255 */     this.y *= scale;
/* 256 */     this.z *= scale;
/* 257 */     this.w *= scale;
/* 258 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector store(FloatBuffer buf)
/*     */   {
/* 266 */     buf.put(this.x);
/* 267 */     buf.put(this.y);
/* 268 */     buf.put(this.z);
/* 269 */     buf.put(this.w);
/*     */ 
/* 271 */     return this;
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 275 */     return "Vector4f: " + this.x + " " + this.y + " " + this.z + " " + this.w;
/*     */   }
/*     */ 
/*     */   public final float getX()
/*     */   {
/* 282 */     return this.x;
/*     */   }
/*     */ 
/*     */   public final float getY()
/*     */   {
/* 289 */     return this.y;
/*     */   }
/*     */ 
/*     */   public final void setX(float x)
/*     */   {
/* 297 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public final void setY(float y)
/*     */   {
/* 305 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public void setZ(float z)
/*     */   {
/* 313 */     this.z = z;
/*     */   }
/*     */ 
/*     */   public float getZ()
/*     */   {
/* 321 */     return this.z;
/*     */   }
/*     */ 
/*     */   public void setW(float w)
/*     */   {
/* 329 */     this.w = w;
/*     */   }
/*     */ 
/*     */   public float getW()
/*     */   {
/* 336 */     return this.w;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Vector4f
 * JD-Core Version:    0.6.2
 */