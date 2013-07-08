/*   1:    */package org.lwjgl.opengl;
/*   2:    */
/*   3:    */import java.nio.ByteBuffer;
/*   4:    */import java.nio.IntBuffer;
/*   5:    */import java.nio.LongBuffer;
/*   6:    */import org.lwjgl.BufferChecks;
/*   7:    */import org.lwjgl.LWJGLUtil;
/*   8:    */
/*  50:    */public final class NVVideoCaptureUtil
/*  51:    */{
/*  52:    */  private static void checkExtension()
/*  53:    */  {
/*  54: 54 */    if ((LWJGLUtil.CHECKS) && (!GLContext.getCapabilities().GL_NV_video_capture))
/*  55: 55 */      throw new IllegalStateException("NV_video_capture is not supported");
/*  56:    */  }
/*  57:    */  
/*  58:    */  private static ByteBuffer getPeerInfo() {
/*  59: 59 */    return ContextGL.getCurrentContext().getPeerInfo().getHandle();
/*  60:    */  }
/*  61:    */  
/*  71:    */  public static boolean glBindVideoCaptureDeviceNV(int video_slot, long device)
/*  72:    */  {
/*  73: 73 */    checkExtension();
/*  74: 74 */    return nglBindVideoCaptureDeviceNV(getPeerInfo(), video_slot, device);
/*  75:    */  }
/*  76:    */  
/*  81:    */  private static native boolean nglBindVideoCaptureDeviceNV(ByteBuffer paramByteBuffer, int paramInt, long paramLong);
/*  82:    */  
/*  87:    */  public static int glEnumerateVideoCaptureDevicesNV(LongBuffer devices)
/*  88:    */  {
/*  89:    */    
/*  90:    */    
/*  93: 93 */    if (devices != null)
/*  94: 94 */      BufferChecks.checkBuffer(devices, 1);
/*  95: 95 */    return nglEnumerateVideoCaptureDevicesNV(getPeerInfo(), devices, devices == null ? 0 : devices.position());
/*  96:    */  }
/*  97:    */  
/* 103:    */  private static native int nglEnumerateVideoCaptureDevicesNV(ByteBuffer paramByteBuffer, LongBuffer paramLongBuffer, int paramInt);
/* 104:    */  
/* 110:    */  public static boolean glLockVideoCaptureDeviceNV(long device)
/* 111:    */  {
/* 112:112 */    checkExtension();
/* 113:113 */    return nglLockVideoCaptureDeviceNV(getPeerInfo(), device);
/* 114:    */  }
/* 115:    */  
/* 121:    */  private static native boolean nglLockVideoCaptureDeviceNV(ByteBuffer paramByteBuffer, long paramLong);
/* 122:    */  
/* 127:    */  public static boolean glQueryVideoCaptureDeviceNV(long device, int attribute, IntBuffer value)
/* 128:    */  {
/* 129:129 */    checkExtension();
/* 130:    */    
/* 131:131 */    BufferChecks.checkBuffer(value, 1);
/* 132:132 */    return nglQueryVideoCaptureDeviceNV(getPeerInfo(), device, attribute, value, value.position());
/* 133:    */  }
/* 134:    */  
/* 139:    */  private static native boolean nglQueryVideoCaptureDeviceNV(ByteBuffer paramByteBuffer, long paramLong, int paramInt1, IntBuffer paramIntBuffer, int paramInt2);
/* 140:    */  
/* 144:    */  public static boolean glReleaseVideoCaptureDeviceNV(long device)
/* 145:    */  {
/* 146:146 */    checkExtension();
/* 147:147 */    return nglReleaseVideoCaptureDeviceNV(getPeerInfo(), device);
/* 148:    */  }
/* 149:    */  
/* 150:    */  private static native boolean nglReleaseVideoCaptureDeviceNV(ByteBuffer paramByteBuffer, long paramLong);
/* 151:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.NVVideoCaptureUtil
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */