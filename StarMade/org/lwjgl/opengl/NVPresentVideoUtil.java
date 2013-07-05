/*     */ package org.lwjgl.opengl;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.LongBuffer;
/*     */ import org.lwjgl.BufferChecks;
/*     */ import org.lwjgl.LWJGLUtil;
/*     */ 
/*     */ public final class NVPresentVideoUtil
/*     */ {
/*     */   private static void checkExtension()
/*     */   {
/*  54 */     if ((LWJGLUtil.CHECKS) && (!GLContext.getCapabilities().GL_NV_present_video))
/*  55 */       throw new IllegalStateException("NV_present_video is not supported");
/*     */   }
/*     */ 
/*     */   private static ByteBuffer getPeerInfo() {
/*  59 */     return ContextGL.getCurrentContext().getPeerInfo().getHandle();
/*     */   }
/*     */ 
/*     */   public static int glEnumerateVideoDevicesNV(LongBuffer devices)
/*     */   {
/*  74 */     checkExtension();
/*     */ 
/*  76 */     if (devices != null)
/*  77 */       BufferChecks.checkBuffer(devices, 1);
/*  78 */     return nglEnumerateVideoDevicesNV(getPeerInfo(), devices, devices == null ? 0 : devices.position());
/*     */   }
/*     */ 
/*     */   private static native int nglEnumerateVideoDevicesNV(ByteBuffer paramByteBuffer, LongBuffer paramLongBuffer, int paramInt);
/*     */ 
/*     */   public static boolean glBindVideoDeviceNV(int video_slot, long video_device, IntBuffer attrib_list)
/*     */   {
/*  96 */     checkExtension();
/*     */ 
/*  98 */     if (attrib_list != null)
/*  99 */       BufferChecks.checkNullTerminated(attrib_list);
/* 100 */     return nglBindVideoDeviceNV(getPeerInfo(), video_slot, video_device, attrib_list, attrib_list == null ? 0 : attrib_list.position());
/*     */   }
/*     */ 
/*     */   private static native boolean nglBindVideoDeviceNV(ByteBuffer paramByteBuffer, int paramInt1, long paramLong, IntBuffer paramIntBuffer, int paramInt2);
/*     */ 
/*     */   public static boolean glQueryContextNV(int attrib, IntBuffer value)
/*     */   {
/* 113 */     checkExtension();
/*     */ 
/* 115 */     BufferChecks.checkBuffer(value, 1);
/* 116 */     ContextGL ctx = ContextGL.getCurrentContext();
/* 117 */     return nglQueryContextNV(ctx.getPeerInfo().getHandle(), ctx.getHandle(), attrib, value, value.position());
/*     */   }
/*     */ 
/*     */   private static native boolean nglQueryContextNV(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2, int paramInt1, IntBuffer paramIntBuffer, int paramInt2);
/*     */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVPresentVideoUtil
 * JD-Core Version:    0.6.2
 */