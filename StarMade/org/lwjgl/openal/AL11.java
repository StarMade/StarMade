/*     */ package org.lwjgl.openal;
/*     */ 
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class AL11
/*     */ {
/*     */   public static final int AL_SEC_OFFSET = 4132;
/*     */   public static final int AL_SAMPLE_OFFSET = 4133;
/*     */   public static final int AL_BYTE_OFFSET = 4134;
/*     */   public static final int AL_STATIC = 4136;
/*     */   public static final int AL_STREAMING = 4137;
/*     */   public static final int AL_UNDETERMINED = 4144;
/*     */   public static final int AL_ILLEGAL_COMMAND = 40964;
/*     */   public static final int AL_SPEED_OF_SOUND = 49155;
/*     */   public static final int AL_LINEAR_DISTANCE = 53251;
/*     */   public static final int AL_LINEAR_DISTANCE_CLAMPED = 53252;
/*     */   public static final int AL_EXPONENT_DISTANCE = 53253;
/*     */   public static final int AL_EXPONENT_DISTANCE_CLAMPED = 53254;
/*     */ 
/*     */   static native void initNativeStubs()
/*     */     throws LWJGLException;
/*     */ 
/*     */   public static void alListener3i(int pname, int v1, int v2, int v3)
/*     */   {
/*  76 */     nalListener3i(pname, v1, v2, v3);
/*     */   }
/*     */ 
/*     */   static native void nalListener3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */ 
/*     */   public static void alGetListeneri(int pname, FloatBuffer intdata)
/*     */   {
/*  88 */     BufferChecks.checkBuffer(intdata, 1);
/*  89 */     nalGetListeneriv(pname, MemoryUtil.getAddress(intdata));
/*     */   }
/*     */ 
/*     */   static native void nalGetListeneriv(int paramInt, long paramLong);
/*     */ 
/*     */   public static void alSource3i(int source, int pname, int v1, int v2, int v3)
/*     */   {
/* 104 */     nalSource3i(source, pname, v1, v2, v3);
/*     */   }
/*     */ 
/*     */   static native void nalSource3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
/*     */ 
/*     */   public static void alSource(int source, int pname, IntBuffer value)
/*     */   {
/* 117 */     BufferChecks.checkBuffer(value, 1);
/* 118 */     nalSourceiv(source, pname, MemoryUtil.getAddress(value));
/*     */   }
/*     */ 
/*     */   static native void nalSourceiv(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void alBufferf(int buffer, int pname, float value)
/*     */   {
/* 132 */     nalBufferf(buffer, pname, value);
/*     */   }
/*     */ 
/*     */   static native void nalBufferf(int paramInt1, int paramInt2, float paramFloat);
/*     */ 
/*     */   public static void alBuffer3f(int buffer, int pname, float v1, float v2, float v3)
/*     */   {
/* 148 */     nalBuffer3f(buffer, pname, v1, v2, v3);
/*     */   }
/*     */ 
/*     */   static native void nalBuffer3f(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3);
/*     */ 
/*     */   public static void alBuffer(int buffer, int pname, FloatBuffer value)
/*     */   {
/* 162 */     BufferChecks.checkBuffer(value, 1);
/* 163 */     nalBufferfv(buffer, pname, MemoryUtil.getAddress(value));
/*     */   }
/*     */ 
/*     */   static native void nalBufferfv(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void alBufferi(int buffer, int pname, int value)
/*     */   {
/* 177 */     nalBufferi(buffer, pname, value);
/*     */   }
/*     */ 
/*     */   static native void nalBufferi(int paramInt1, int paramInt2, int paramInt3);
/*     */ 
/*     */   public static void alBuffer3i(int buffer, int pname, int v1, int v2, int v3)
/*     */   {
/* 193 */     nalBuffer3i(buffer, pname, v1, v2, v3);
/*     */   }
/*     */ 
/*     */   static native void nalBuffer3i(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
/*     */ 
/*     */   public static void alBuffer(int buffer, int pname, IntBuffer value)
/*     */   {
/* 207 */     BufferChecks.checkBuffer(value, 1);
/* 208 */     nalBufferiv(buffer, pname, MemoryUtil.getAddress(value));
/*     */   }
/*     */ 
/*     */   static native void nalBufferiv(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static int alGetBufferi(int buffer, int pname)
/*     */   {
/* 222 */     int __result = nalGetBufferi(buffer, pname);
/* 223 */     return __result;
/*     */   }
/*     */ 
/*     */   static native int nalGetBufferi(int paramInt1, int paramInt2);
/*     */ 
/*     */   public static void alGetBuffer(int buffer, int pname, IntBuffer values)
/*     */   {
/* 234 */     BufferChecks.checkBuffer(values, 1);
/* 235 */     nalGetBufferiv(buffer, pname, MemoryUtil.getAddress(values));
/*     */   }
/*     */ 
/*     */   static native void nalGetBufferiv(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static float alGetBufferf(int buffer, int pname)
/*     */   {
/* 249 */     float __result = nalGetBufferf(buffer, pname);
/* 250 */     return __result;
/*     */   }
/*     */ 
/*     */   static native float nalGetBufferf(int paramInt1, int paramInt2);
/*     */ 
/*     */   public static void alGetBuffer(int buffer, int pname, FloatBuffer values)
/*     */   {
/* 263 */     BufferChecks.checkBuffer(values, 1);
/* 264 */     nalGetBufferfv(buffer, pname, MemoryUtil.getAddress(values));
/*     */   }
/*     */ 
/*     */   static native void nalGetBufferfv(int paramInt1, int paramInt2, long paramLong);
/*     */ 
/*     */   public static void alSpeedOfSound(float value)
/*     */   {
/* 286 */     nalSpeedOfSound(value);
/*     */   }
/*     */ 
/*     */   static native void nalSpeedOfSound(float paramFloat);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.AL11
 * JD-Core Version:    0.6.2
 */