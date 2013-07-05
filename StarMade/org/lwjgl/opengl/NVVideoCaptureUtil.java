/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ public final class NVVideoCaptureUtil
/*     */ {
/*     */   private static void checkExtension()
/*     */   {
/*  54 */     if ((LWJGLUtil.CHECKS) && (!GLContext.getCapabilities().GL_NV_video_capture))
/*  55 */       throw new IllegalStateException("NV_video_capture is not supported");
/*     */   }
/*     */ 
/*     */   private static ByteBuffer getPeerInfo() {
/*  59 */     return ContextGL.getCurrentContext().getPeerInfo().getHandle();
/*     */   }
/*     */ 
/*     */   public static boolean glBindVideoCaptureDeviceNV(int video_slot, long device)
/*     */   {
/*  73 */     checkExtension();
/*  74 */     return nglBindVideoCaptureDeviceNV(getPeerInfo(), video_slot, device);
/*     */   }
/*     */ 
/*     */   private static native boolean nglBindVideoCaptureDeviceNV(ByteBuffer paramByteBuffer, int paramInt, long paramLong);
/*     */ 
/*     */   public static int glEnumerateVideoCaptureDevicesNV(LongBuffer devices)
/*     */   {
/*  91 */     checkExtension();
/*     */ 
/*  93 */     if (devices != null)
/*  94 */       BufferChecks.checkBuffer(devices, 1);
/*  95 */     return nglEnumerateVideoCaptureDevicesNV(getPeerInfo(), devices, devices == null ? 0 : devices.position());
/*     */   }
/*     */ 
/*     */   private static native int nglEnumerateVideoCaptureDevicesNV(ByteBuffer paramByteBuffer, LongBuffer paramLongBuffer, int paramInt);
/*     */ 
/*     */   public static boolean glLockVideoCaptureDeviceNV(long device)
/*     */   {
/* 112 */     checkExtension();
/* 113 */     return nglLockVideoCaptureDeviceNV(getPeerInfo(), device);
/*     */   }
/*     */ 
/*     */   private static native boolean nglLockVideoCaptureDeviceNV(ByteBuffer paramByteBuffer, long paramLong);
/*     */ 
/*     */   public static boolean glQueryVideoCaptureDeviceNV(long device, int attribute, IntBuffer value)
/*     */   {
/* 129 */     checkExtension();
/*     */ 
/* 131 */     BufferChecks.checkBuffer(value, 1);
/* 132 */     return nglQueryVideoCaptureDeviceNV(getPeerInfo(), device, attribute, value, value.position());
/*     */   }
/*     */ 
/*     */   private static native boolean nglQueryVideoCaptureDeviceNV(ByteBuffer paramByteBuffer, long paramLong, int paramInt1, IntBuffer paramIntBuffer, int paramInt2);
/*     */ 
/*     */   public static boolean glReleaseVideoCaptureDeviceNV(long device)
/*     */   {
/* 146 */     checkExtension();
/* 147 */     return nglReleaseVideoCaptureDeviceNV(getPeerInfo(), device);
/*     */   }
/*     */ 
/*     */   private static native boolean nglReleaseVideoCaptureDeviceNV(ByteBuffer paramByteBuffer, long paramLong);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVideoCaptureUtil
 * JD-Core Version:    0.6.2
 */