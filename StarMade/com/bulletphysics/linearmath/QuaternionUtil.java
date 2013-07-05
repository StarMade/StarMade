/*     */ package com.bulletphysics.linearmath;
/*     */ 
/*     */ import com.bulletphysics..Stack;
/*     */ import javax.vecmath.Quat4f;
/*     */ import javax.vecmath.Vector3f;
/*     */ 
/*     */ public class QuaternionUtil
/*     */ {
/*     */   public static float getAngle(Quat4f q)
/*     */   {
/*  39 */     float s = 2.0F * (float)Math.acos(q.w);
/*  40 */     return s;
/*     */   }
/*     */ 
/*     */   public static void setRotation(Quat4f q, Vector3f axis, float angle) {
/*  44 */     float d = axis.length();
/*  45 */     assert (d != 0.0F);
/*  46 */     float s = (float)Math.sin(angle * 0.5F) / d;
/*  47 */     q.set(axis.x * s, axis.y * s, axis.z * s, (float)Math.cos(angle * 0.5F));
/*     */   }
/*     */ 
/*     */   public static Quat4f shortestArcQuat(Vector3f arg0, Vector3f arg1, Quat4f arg2)
/*     */   {
/*  52 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Vector3f(); Vector3f c = localStack.get$javax$vecmath$Vector3f();
/*  53 */       c.cross(v0, v1);
/*  54 */       float d = v0.dot(v1);
/*     */ 
/*  56 */       if (d < -0.9999998807907105D)
/*     */       {
/*  58 */         out.set(0.0F, 1.0F, 0.0F, 0.0F);
/*  59 */         return out;
/*     */       }
/*     */ 
/*  62 */       float s = (float)Math.sqrt((1.0F + d) * 2.0F);
/*  63 */       float rs = 1.0F / s;
/*     */ 
/*  65 */       out.set(c.x * rs, c.y * rs, c.z * rs, s * 0.5F);
/*  66 */       return out; } finally { localStack.pop$javax$vecmath$Vector3f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static void mul(Quat4f q, Vector3f w) {
/*  70 */     float rx = q.w * w.x + q.y * w.z - q.z * w.y;
/*  71 */     float ry = q.w * w.y + q.z * w.x - q.x * w.z;
/*  72 */     float rz = q.w * w.z + q.x * w.y - q.y * w.x;
/*  73 */     float rw = -q.x * w.x - q.y * w.y - q.z * w.z;
/*  74 */     q.set(rx, ry, rz, rw);
/*     */   }
/*     */ 
/*     */   public static Vector3f quatRotate(Quat4f arg0, Vector3f arg1, Vector3f arg2) {
/*  78 */     .Stack localStack = .Stack.get();
/*     */     try { localStack.push$javax$vecmath$Quat4f(); Quat4f q = localStack.get$javax$vecmath$Quat4f(rotation);
/*  79 */       mul(q, v);
/*     */ 
/*  81 */       Quat4f tmp = localStack.get$javax$vecmath$Quat4f();
/*  82 */       inverse(tmp, rotation);
/*  83 */       q.mul(tmp);
/*     */ 
/*  85 */       out.set(q.x, q.y, q.z);
/*  86 */       return out; } finally { localStack.pop$javax$vecmath$Quat4f(); } throw finally;
/*     */   }
/*     */ 
/*     */   public static void inverse(Quat4f q) {
/*  90 */     q.x = (-q.x);
/*  91 */     q.y = (-q.y);
/*  92 */     q.z = (-q.z);
/*     */   }
/*     */ 
/*     */   public static void inverse(Quat4f q, Quat4f src) {
/*  96 */     q.x = (-src.x);
/*  97 */     q.y = (-src.y);
/*  98 */     q.z = (-src.z);
/*  99 */     q.w = src.w;
/*     */   }
/*     */ 
/*     */   public static void setEuler(Quat4f q, float yaw, float pitch, float roll) {
/* 103 */     float halfYaw = yaw * 0.5F;
/* 104 */     float halfPitch = pitch * 0.5F;
/* 105 */     float halfRoll = roll * 0.5F;
/* 106 */     float cosYaw = (float)Math.cos(halfYaw);
/* 107 */     float sinYaw = (float)Math.sin(halfYaw);
/* 108 */     float cosPitch = (float)Math.cos(halfPitch);
/* 109 */     float sinPitch = (float)Math.sin(halfPitch);
/* 110 */     float cosRoll = (float)Math.cos(halfRoll);
/* 111 */     float sinRoll = (float)Math.sin(halfRoll);
/* 112 */     q.x = (cosRoll * sinPitch * cosYaw + sinRoll * cosPitch * sinYaw);
/* 113 */     q.y = (cosRoll * cosPitch * sinYaw - sinRoll * sinPitch * cosYaw);
/* 114 */     q.z = (sinRoll * cosPitch * cosYaw - cosRoll * sinPitch * sinYaw);
/* 115 */     q.w = (cosRoll * cosPitch * cosYaw + sinRoll * sinPitch * sinYaw);
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.QuaternionUtil
 * JD-Core Version:    0.6.2
 */