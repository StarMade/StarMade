/*     */ package org.lwjgl.util.vector;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.nio.FloatBuffer;
/*     */ 
/*     */ public class Vector3f extends Vector
/*     */   implements Serializable, ReadableVector3f, WritableVector3f
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public float x;
/*     */   public float y;
/*     */   public float z;
/*     */ 
/*     */   public Vector3f()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Vector3f(ReadableVector3f src)
/*     */   {
/*  63 */     set(src);
/*     */   }
/*     */ 
/*     */   public Vector3f(float x, float y, float z)
/*     */   {
/*  70 */     set(x, y, z);
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
/*     */   public Vector3f set(ReadableVector3f src)
/*     */   {
/*  96 */     this.x = src.getX();
/*  97 */     this.y = src.getY();
/*  98 */     this.z = src.getZ();
/*  99 */     return this;
/*     */   }
/*     */ 
/*     */   public float lengthSquared()
/*     */   {
/* 106 */     return this.x * this.x + this.y * this.y + this.z * this.z;
/*     */   }
/*     */ 
/*     */   public Vector3f translate(float x, float y, float z)
/*     */   {
/* 116 */     this.x += x;
/* 117 */     this.y += y;
/* 118 */     this.z += z;
/* 119 */     return this;
/*     */   }
/*     */ 
/*     */   public static Vector3f add(Vector3f left, Vector3f right, Vector3f dest)
/*     */   {
/* 131 */     if (dest == null) {
/* 132 */       return new Vector3f(left.x + right.x, left.y + right.y, left.z + right.z);
/*     */     }
/* 134 */     dest.set(left.x + right.x, left.y + right.y, left.z + right.z);
/* 135 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Vector3f sub(Vector3f left, Vector3f right, Vector3f dest)
/*     */   {
/* 148 */     if (dest == null) {
/* 149 */       return new Vector3f(left.x - right.x, left.y - right.y, left.z - right.z);
/*     */     }
/* 151 */     dest.set(left.x - right.x, left.y - right.y, left.z - right.z);
/* 152 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Vector3f cross(Vector3f left, Vector3f right, Vector3f dest)
/*     */   {
/* 170 */     if (dest == null) {
/* 171 */       dest = new Vector3f();
/*     */     }
/* 173 */     dest.set(left.y * right.z - left.z * right.y, right.x * left.z - right.z * left.x, left.x * right.y - left.y * right.x);
/*     */ 
/* 179 */     return dest;
/*     */   }
/*     */ 
/*     */   public Vector negate()
/*     */   {
/* 189 */     this.x = (-this.x);
/* 190 */     this.y = (-this.y);
/* 191 */     this.z = (-this.z);
/* 192 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector3f negate(Vector3f dest)
/*     */   {
/* 201 */     if (dest == null)
/* 202 */       dest = new Vector3f();
/* 203 */     dest.x = (-this.x);
/* 204 */     dest.y = (-this.y);
/* 205 */     dest.z = (-this.z);
/* 206 */     return dest;
/*     */   }
/*     */ 
/*     */   public Vector3f normalise(Vector3f dest)
/*     */   {
/* 216 */     float l = length();
/*     */ 
/* 218 */     if (dest == null)
/* 219 */       dest = new Vector3f(this.x / l, this.y / l, this.z / l);
/*     */     else {
/* 221 */       dest.set(this.x / l, this.y / l, this.z / l);
/*     */     }
/* 223 */     return dest;
/*     */   }
/*     */ 
/*     */   public static float dot(Vector3f left, Vector3f right)
/*     */   {
/* 234 */     return left.x * right.x + left.y * right.y + left.z * right.z;
/*     */   }
/*     */ 
/*     */   public static float angle(Vector3f a, Vector3f b)
/*     */   {
/* 244 */     float dls = dot(a, b) / (a.length() * b.length());
/* 245 */     if (dls < -1.0F)
/* 246 */       dls = -1.0F;
/* 247 */     else if (dls > 1.0F)
/* 248 */       dls = 1.0F;
/* 249 */     return (float)Math.acos(dls);
/*     */   }
/*     */ 
/*     */   public Vector load(FloatBuffer buf)
/*     */   {
/* 256 */     this.x = buf.get();
/* 257 */     this.y = buf.get();
/* 258 */     this.z = buf.get();
/* 259 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector scale(float scale)
/*     */   {
/* 267 */     this.x *= scale;
/* 268 */     this.y *= scale;
/* 269 */     this.z *= scale;
/*     */ 
/* 271 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector store(FloatBuffer buf)
/*     */   {
/* 280 */     buf.put(this.x);
/* 281 */     buf.put(this.y);
/* 282 */     buf.put(this.z);
/*     */ 
/* 284 */     return this;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 291 */     StringBuilder sb = new StringBuilder(64);
/*     */ 
/* 293 */     sb.append("Vector3f[");
/* 294 */     sb.append(this.x);
/* 295 */     sb.append(", ");
/* 296 */     sb.append(this.y);
/* 297 */     sb.append(", ");
/* 298 */     sb.append(this.z);
/* 299 */     sb.append(']');
/* 300 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public final float getX()
/*     */   {
/* 307 */     return this.x;
/*     */   }
/*     */ 
/*     */   public final float getY()
/*     */   {
/* 314 */     return this.y;
/*     */   }
/*     */ 
/*     */   public final void setX(float x)
/*     */   {
/* 322 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public final void setY(float y)
/*     */   {
/* 330 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public void setZ(float z)
/*     */   {
/* 338 */     this.z = z;
/*     */   }
/*     */ 
/*     */   public float getZ()
/*     */   {
/* 345 */     return this.z;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Vector3f
 * JD-Core Version:    0.6.2
 */