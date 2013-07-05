/*     */ package org.lwjgl.util.vector;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.nio.FloatBuffer;
/*     */ 
/*     */ public class Vector2f extends Vector
/*     */   implements Serializable, ReadableVector2f, WritableVector2f
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public float x;
/*     */   public float y;
/*     */ 
/*     */   public Vector2f()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Vector2f(ReadableVector2f src)
/*     */   {
/*  63 */     set(src);
/*     */   }
/*     */ 
/*     */   public Vector2f(float x, float y)
/*     */   {
/*  70 */     set(x, y);
/*     */   }
/*     */ 
/*     */   public void set(float x, float y)
/*     */   {
/*  77 */     this.x = x;
/*  78 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public Vector2f set(ReadableVector2f src)
/*     */   {
/*  87 */     this.x = src.getX();
/*  88 */     this.y = src.getY();
/*  89 */     return this;
/*     */   }
/*     */ 
/*     */   public float lengthSquared()
/*     */   {
/*  96 */     return this.x * this.x + this.y * this.y;
/*     */   }
/*     */ 
/*     */   public Vector2f translate(float x, float y)
/*     */   {
/* 106 */     this.x += x;
/* 107 */     this.y += y;
/* 108 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector negate()
/*     */   {
/* 116 */     this.x = (-this.x);
/* 117 */     this.y = (-this.y);
/* 118 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector2f negate(Vector2f dest)
/*     */   {
/* 127 */     if (dest == null)
/* 128 */       dest = new Vector2f();
/* 129 */     dest.x = (-this.x);
/* 130 */     dest.y = (-this.y);
/* 131 */     return dest;
/*     */   }
/*     */ 
/*     */   public Vector2f normalise(Vector2f dest)
/*     */   {
/* 141 */     float l = length();
/*     */ 
/* 143 */     if (dest == null)
/* 144 */       dest = new Vector2f(this.x / l, this.y / l);
/*     */     else {
/* 146 */       dest.set(this.x / l, this.y / l);
/*     */     }
/* 148 */     return dest;
/*     */   }
/*     */ 
/*     */   public static float dot(Vector2f left, Vector2f right)
/*     */   {
/* 159 */     return left.x * right.x + left.y * right.y;
/*     */   }
/*     */ 
/*     */   public static float angle(Vector2f a, Vector2f b)
/*     */   {
/* 171 */     float dls = dot(a, b) / (a.length() * b.length());
/* 172 */     if (dls < -1.0F)
/* 173 */       dls = -1.0F;
/* 174 */     else if (dls > 1.0F)
/* 175 */       dls = 1.0F;
/* 176 */     return (float)Math.acos(dls);
/*     */   }
/*     */ 
/*     */   public static Vector2f add(Vector2f left, Vector2f right, Vector2f dest)
/*     */   {
/* 188 */     if (dest == null) {
/* 189 */       return new Vector2f(left.x + right.x, left.y + right.y);
/*     */     }
/* 191 */     dest.set(left.x + right.x, left.y + right.y);
/* 192 */     return dest;
/*     */   }
/*     */ 
/*     */   public static Vector2f sub(Vector2f left, Vector2f right, Vector2f dest)
/*     */   {
/* 205 */     if (dest == null) {
/* 206 */       return new Vector2f(left.x - right.x, left.y - right.y);
/*     */     }
/* 208 */     dest.set(left.x - right.x, left.y - right.y);
/* 209 */     return dest;
/*     */   }
/*     */ 
/*     */   public Vector store(FloatBuffer buf)
/*     */   {
/* 219 */     buf.put(this.x);
/* 220 */     buf.put(this.y);
/* 221 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector load(FloatBuffer buf)
/*     */   {
/* 230 */     this.x = buf.get();
/* 231 */     this.y = buf.get();
/* 232 */     return this;
/*     */   }
/*     */ 
/*     */   public Vector scale(float scale)
/*     */   {
/* 240 */     this.x *= scale;
/* 241 */     this.y *= scale;
/*     */ 
/* 243 */     return this;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 250 */     StringBuilder sb = new StringBuilder(64);
/*     */ 
/* 252 */     sb.append("Vector2f[");
/* 253 */     sb.append(this.x);
/* 254 */     sb.append(", ");
/* 255 */     sb.append(this.y);
/* 256 */     sb.append(']');
/* 257 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public final float getX()
/*     */   {
/* 264 */     return this.x;
/*     */   }
/*     */ 
/*     */   public final float getY()
/*     */   {
/* 271 */     return this.y;
/*     */   }
/*     */ 
/*     */   public final void setX(float x)
/*     */   {
/* 279 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public final void setY(float y)
/*     */   {
/* 287 */     this.y = y;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.util.vector.Vector2f
 * JD-Core Version:    0.6.2
 */