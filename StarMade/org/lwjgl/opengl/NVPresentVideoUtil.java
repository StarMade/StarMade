/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import java.nio.LongBuffer;
/*   6:    */import org.lwjgl.BufferChecks;
/*   7:    */import org.lwjgl.LWJGLUtil;
/*   8:    */
/*  50:    */public final class NVPresentVideoUtil
/*  51:    */{
/*  52:    */  private static void checkExtension()
/*  53:    */  {
/*  54: 54 */    if ((LWJGLUtil.CHECKS) && (!GLContext.getCapabilities().GL_NV_present_video))
/*  55: 55 */      throw new IllegalStateException("NV_present_video is not supported");
/*  56:    */  }
/*  57:    */  
/*  58:    */  private static ByteBuffer getPeerInfo() {
/*  59: 59 */    return ContextGL.getCurrentContext().getPeerInfo().getHandle();
/*  60:    */  }
/*  61:    */  
/*  68:    */  public static int glEnumerateVideoDevicesNV(LongBuffer devices)
/*  69:    */  {
/*  70:    */    
/*  71:    */    
/*  76: 76 */    if (devices != null)
/*  77: 77 */      BufferChecks.checkBuffer(devices, 1);
/*  78: 78 */    return nglEnumerateVideoDevicesNV(getPeerInfo(), devices, devices == null ? 0 : devices.position());
/*  79:    */  }
/*  80:    */  
/*  85:    */  private static native int nglEnumerateVideoDevicesNV(ByteBuffer paramByteBuffer, LongBuffer paramLongBuffer, int paramInt);
/*  86:    */  
/*  91:    */  public static boolean glBindVideoDeviceNV(int video_slot, long video_device, IntBuffer attrib_list)
/*  92:    */  {
/*  93:    */    
/*  94:    */    
/*  98: 98 */    if (attrib_list != null)
/*  99: 99 */      BufferChecks.checkNullTerminated(attrib_list);
/* 100:100 */    return nglBindVideoDeviceNV(getPeerInfo(), video_slot, video_device, attrib_list, attrib_list == null ? 0 : attrib_list.position());
/* 101:    */  }
/* 102:    */  
/* 106:    */  private static native boolean nglBindVideoDeviceNV(ByteBuffer paramByteBuffer, int paramInt1, long paramLong, IntBuffer paramIntBuffer, int paramInt2);
/* 107:    */  
/* 111:    */  public static boolean glQueryContextNV(int attrib, IntBuffer value)
/* 112:    */  {
/* 113:113 */    checkExtension();
/* 114:    */    
/* 115:115 */    BufferChecks.checkBuffer(value, 1);
/* 116:116 */    ContextGL ctx = ContextGL.getCurrentContext();
/* 117:117 */    return nglQueryContextNV(ctx.getPeerInfo().getHandle(), ctx.getHandle(), attrib, value, value.position());
/* 118:    */  }
/* 119:    */  
/* 120:    */  private static native boolean nglQueryContextNV(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2, int paramInt1, IntBuffer paramIntBuffer, int paramInt2);
/* 121:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVPresentVideoUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */