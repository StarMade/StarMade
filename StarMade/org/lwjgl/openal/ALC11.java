/*     */ package org.lwjgl.openal;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.HashMap;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.LWJGLException;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ import org.lwjgl.MemoryUtil;
/*     */ 
/*     */ public final class ALC11
/*     */ {
/*     */   public static final int ALC_DEFAULT_ALL_DEVICES_SPECIFIER = 4114;
/*     */   public static final int ALC_ALL_DEVICES_SPECIFIER = 4115;
/*     */   public static final int ALC_CAPTURE_DEVICE_SPECIFIER = 784;
/*     */   public static final int ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER = 785;
/*     */   public static final int ALC_CAPTURE_SAMPLES = 786;
/*     */   public static final int ALC_MONO_SOURCES = 4112;
/*     */   public static final int ALC_STEREO_SOURCES = 4113;
/*     */ 
/*     */   public static ALCdevice alcCaptureOpenDevice(String devicename, int frequency, int format, int buffersize)
/*     */   {
/*  95 */     ByteBuffer buffer = MemoryUtil.encodeASCII(devicename);
/*  96 */     long device_address = nalcCaptureOpenDevice(MemoryUtil.getAddressSafe(buffer), frequency, format, buffersize);
/*  97 */     if (device_address != 0L) {
/*  98 */       ALCdevice device = new ALCdevice(device_address);
/*  99 */       synchronized (ALC10.devices) {
/* 100 */         ALC10.devices.put(Long.valueOf(device_address), device);
/*     */       }
/* 102 */       return device;
/*     */     }
/* 104 */     return null;
/*     */   }
/*     */ 
/*     */   private static native long nalcCaptureOpenDevice(long paramLong, int paramInt1, int paramInt2, int paramInt3);
/*     */ 
/*     */   public static boolean alcCaptureCloseDevice(ALCdevice device)
/*     */   {
/* 118 */     boolean result = nalcCaptureCloseDevice(ALC10.getDevice(device));
/* 119 */     synchronized (ALC10.devices) {
/* 120 */       device.setInvalid();
/* 121 */       ALC10.devices.remove(new Long(device.device));
/*     */     }
/* 123 */     return result;
/*     */   }
/*     */ 
/*     */   static native boolean nalcCaptureCloseDevice(long paramLong);
/*     */ 
/*     */   public static void alcCaptureStart(ALCdevice device)
/*     */   {
/* 138 */     nalcCaptureStart(ALC10.getDevice(device));
/*     */   }
/*     */ 
/*     */   static native void nalcCaptureStart(long paramLong);
/*     */ 
/*     */   public static void alcCaptureStop(ALCdevice device)
/*     */   {
/* 150 */     nalcCaptureStop(ALC10.getDevice(device));
/*     */   }
/*     */ 
/*     */   static native void nalcCaptureStop(long paramLong);
/*     */ 
/*     */   public static void alcCaptureSamples(ALCdevice device, ByteBuffer buffer, int samples)
/*     */   {
/* 166 */     nalcCaptureSamples(ALC10.getDevice(device), MemoryUtil.getAddress(buffer), samples);
/*     */   }
/*     */ 
/*     */   static native void nalcCaptureSamples(long paramLong1, long paramLong2, int paramInt);
/*     */ 
/*     */   static native void initNativeStubs()
/*     */     throws LWJGLException;
/*     */ 
/*     */   static boolean initialize()
/*     */   {
/*     */     try
/*     */     {
/* 178 */       IntBuffer ib = BufferUtils.createIntBuffer(2);
/* 179 */       ALC10.alcGetInteger(AL.getDevice(), 4096, ib);
/* 180 */       ib.position(1);
/* 181 */       ALC10.alcGetInteger(AL.getDevice(), 4097, ib);
/*     */ 
/* 183 */       int major = ib.get(0);
/* 184 */       int minor = ib.get(1);
/*     */ 
/* 187 */       if (major >= 1)
/*     */       {
/* 190 */         if ((major > 1) || (minor >= 1)) {
/* 191 */           initNativeStubs();
/* 192 */           AL11.initNativeStubs();
/*     */         }
/*     */       }
/*     */     } catch (LWJGLException le) {
/* 196 */       LWJGLUtil.log("failed to initialize ALC11: " + le);
/* 197 */       return false;
/*     */     }
/* 199 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.openal.ALC11
 * JD-Core Version:    0.6.2
 */