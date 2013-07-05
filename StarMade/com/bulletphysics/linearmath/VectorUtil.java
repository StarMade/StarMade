/*     */ package com.bulletphysics.linearmath;
/*     */ 
/*     */ import javax.vecmath.Vector3f;
/*     */ import javax.vecmath.Vector4f;
/*     */ 
/*     */ public class VectorUtil
/*     */ {
/*     */   public static int maxAxis(Vector3f v)
/*     */   {
/*  37 */     int maxIndex = -1;
/*  38 */     float maxVal = -1.0E+030F;
/*  39 */     if (v.x > maxVal) {
/*  40 */       maxIndex = 0;
/*  41 */       maxVal = v.x;
/*     */     }
/*  43 */     if (v.y > maxVal) {
/*  44 */       maxIndex = 1;
/*  45 */       maxVal = v.y;
/*     */     }
/*  47 */     if (v.z > maxVal) {
/*  48 */       maxIndex = 2;
/*  49 */       maxVal = v.z;
/*     */     }
/*     */ 
/*  52 */     return maxIndex;
/*     */   }
/*     */ 
/*     */   public static int maxAxis4(Vector4f v) {
/*  56 */     int maxIndex = -1;
/*  57 */     float maxVal = -1.0E+030F;
/*  58 */     if (v.x > maxVal) {
/*  59 */       maxIndex = 0;
/*  60 */       maxVal = v.x;
/*     */     }
/*  62 */     if (v.y > maxVal) {
/*  63 */       maxIndex = 1;
/*  64 */       maxVal = v.y;
/*     */     }
/*  66 */     if (v.z > maxVal) {
/*  67 */       maxIndex = 2;
/*  68 */       maxVal = v.z;
/*     */     }
/*  70 */     if (v.w > maxVal) {
/*  71 */       maxIndex = 3;
/*  72 */       maxVal = v.w;
/*     */     }
/*     */ 
/*  75 */     return maxIndex;
/*     */   }
/*     */ 
/*     */   public static int closestAxis4(Vector4f vec) {
/*  79 */     Vector4f tmp = new Vector4f(vec);
/*  80 */     tmp.absolute();
/*  81 */     return maxAxis4(tmp);
/*     */   }
/*     */ 
/*     */   public static float getCoord(Vector3f vec, int num) {
/*  85 */     switch (num) { case 0:
/*  86 */       return vec.x;
/*     */     case 1:
/*  87 */       return vec.y;
/*     */     case 2:
/*  88 */       return vec.z; }
/*  89 */     throw new InternalError();
/*     */   }
/*     */ 
/*     */   public static void setCoord(Vector3f vec, int num, float value)
/*     */   {
/*  94 */     switch (num) { case 0:
/*  95 */       vec.x = value; break;
/*     */     case 1:
/*  96 */       vec.y = value; break;
/*     */     case 2:
/*  97 */       vec.z = value; break;
/*     */     default:
/*  98 */       throw new InternalError(); }
/*     */   }
/*     */ 
/*     */   public static void mulCoord(Vector3f vec, int num, float value)
/*     */   {
/* 103 */     switch (num) { case 0:
/* 104 */       vec.x *= value; break;
/*     */     case 1:
/* 105 */       vec.y *= value; break;
/*     */     case 2:
/* 106 */       vec.z *= value; break;
/*     */     default:
/* 107 */       throw new InternalError(); }
/*     */   }
/*     */ 
/*     */   public static void setInterpolate3(Vector3f dest, Vector3f v0, Vector3f v1, float rt)
/*     */   {
/* 112 */     float s = 1.0F - rt;
/* 113 */     dest.x = (s * v0.x + rt * v1.x);
/* 114 */     dest.y = (s * v0.y + rt * v1.y);
/* 115 */     dest.z = (s * v0.z + rt * v1.z);
/*     */   }
/*     */ 
/*     */   public static void add(Vector3f dest, Vector3f v1, Vector3f v2)
/*     */   {
/* 121 */     v1.x += v2.x;
/* 122 */     v1.y += v2.y;
/* 123 */     v1.z += v2.z;
/*     */   }
/*     */ 
/*     */   public static void add(Vector3f dest, Vector3f v1, Vector3f v2, Vector3f v3) {
/* 127 */     dest.x = (v1.x + v2.x + v3.x);
/* 128 */     dest.y = (v1.y + v2.y + v3.y);
/* 129 */     dest.z = (v1.z + v2.z + v3.z);
/*     */   }
/*     */ 
/*     */   public static void add(Vector3f dest, Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4) {
/* 133 */     dest.x = (v1.x + v2.x + v3.x + v4.x);
/* 134 */     dest.y = (v1.y + v2.y + v3.y + v4.y);
/* 135 */     dest.z = (v1.z + v2.z + v3.z + v4.z);
/*     */   }
/*     */ 
/*     */   public static void mul(Vector3f dest, Vector3f v1, Vector3f v2) {
/* 139 */     v1.x *= v2.x;
/* 140 */     v1.y *= v2.y;
/* 141 */     v1.z *= v2.z;
/*     */   }
/*     */ 
/*     */   public static void div(Vector3f dest, Vector3f v1, Vector3f v2) {
/* 145 */     v1.x /= v2.x;
/* 146 */     v1.y /= v2.y;
/* 147 */     v1.z /= v2.z;
/*     */   }
/*     */ 
/*     */   public static void setMin(Vector3f a, Vector3f b) {
/* 151 */     a.x = Math.min(a.x, b.x);
/* 152 */     a.y = Math.min(a.y, b.y);
/* 153 */     a.z = Math.min(a.z, b.z);
/*     */   }
/*     */ 
/*     */   public static void setMax(Vector3f a, Vector3f b) {
/* 157 */     a.x = Math.max(a.x, b.x);
/* 158 */     a.y = Math.max(a.y, b.y);
/* 159 */     a.z = Math.max(a.z, b.z);
/*     */   }
/*     */ 
/*     */   public static float dot3(Vector4f v0, Vector3f v1) {
/* 163 */     return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z;
/*     */   }
/*     */ 
/*     */   public static float dot3(Vector4f v0, Vector4f v1) {
/* 167 */     return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z;
/*     */   }
/*     */ 
/*     */   public static float dot3(Vector3f v0, Vector4f v1) {
/* 171 */     return v0.x * v1.x + v0.y * v1.y + v0.z * v1.z;
/*     */   }
/*     */ 
/*     */   public static float lengthSquared3(Vector4f v) {
/* 175 */     return v.x * v.x + v.y * v.y + v.z * v.z;
/*     */   }
/*     */ 
/*     */   public static void normalize3(Vector4f v) {
/* 179 */     float norm = (float)(1.0D / Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z));
/* 180 */     v.x *= norm;
/* 181 */     v.y *= norm;
/* 182 */     v.z *= norm;
/*     */   }
/*     */ 
/*     */   public static void cross3(Vector3f dest, Vector4f v1, Vector4f v2)
/*     */   {
/* 187 */     float x = v1.y * v2.z - v1.z * v2.y;
/* 188 */     float y = v2.x * v1.z - v2.z * v1.x;
/* 189 */     dest.z = (v1.x * v2.y - v1.y * v2.x);
/* 190 */     dest.x = x;
/* 191 */     dest.y = y;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.VectorUtil
 * JD-Core Version:    0.6.2
 */