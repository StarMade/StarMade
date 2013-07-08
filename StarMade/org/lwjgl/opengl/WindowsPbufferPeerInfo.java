/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import java.nio.IntBuffer;
/*  5:   */import org.lwjgl.LWJGLException;
/*  6:   */
/* 41:   */final class WindowsPbufferPeerInfo
/* 42:   */  extends WindowsPeerInfo
/* 43:   */{
/* 44:   */  WindowsPbufferPeerInfo(int width, int height, PixelFormat pixel_format, IntBuffer pixelFormatCaps, IntBuffer pBufferAttribs)
/* 45:   */    throws LWJGLException
/* 46:   */  {
/* 47:47 */    nCreate(getHandle(), width, height, pixel_format, pixelFormatCaps, pBufferAttribs);
/* 48:   */  }
/* 49:   */  
/* 50:   */  private static native void nCreate(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, PixelFormat paramPixelFormat, IntBuffer paramIntBuffer1, IntBuffer paramIntBuffer2) throws LWJGLException;
/* 51:   */  
/* 52:52 */  public boolean isBufferLost() { return nIsBufferLost(getHandle()); }
/* 53:   */  
/* 54:   */  private static native boolean nIsBufferLost(ByteBuffer paramByteBuffer);
/* 55:   */  
/* 56:   */  public void setPbufferAttrib(int attrib, int value) {
/* 57:57 */    nSetPbufferAttrib(getHandle(), attrib, value);
/* 58:   */  }
/* 59:   */  
/* 60:   */  private static native void nSetPbufferAttrib(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
/* 61:   */  
/* 62:62 */  public void bindTexImageToPbuffer(int buffer) { nBindTexImageToPbuffer(getHandle(), buffer); }
/* 63:   */  
/* 64:   */  private static native void nBindTexImageToPbuffer(ByteBuffer paramByteBuffer, int paramInt);
/* 65:   */  
/* 66:   */  public void releaseTexImageFromPbuffer(int buffer) {
/* 67:67 */    nReleaseTexImageFromPbuffer(getHandle(), buffer);
/* 68:   */  }
/* 69:   */  
/* 70:   */  private static native void nReleaseTexImageFromPbuffer(ByteBuffer paramByteBuffer, int paramInt);
/* 71:   */  
/* 72:72 */  public void destroy() { nDestroy(getHandle()); }
/* 73:   */  
/* 74:   */  private static native void nDestroy(ByteBuffer paramByteBuffer);
/* 75:   */  
/* 76:   */  protected void doLockAndInitHandle()
/* 77:   */    throws LWJGLException
/* 78:   */  {}
/* 79:   */  
/* 80:   */  protected void doUnlock()
/* 81:   */    throws LWJGLException
/* 82:   */  {}
/* 83:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsPbufferPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */