/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.LWJGLException;
/*  5:   */
/* 40:   */final class MacOSXPbufferPeerInfo
/* 41:   */  extends MacOSXPeerInfo
/* 42:   */{
/* 43:   */  MacOSXPbufferPeerInfo(int width, int height, PixelFormat pixel_format, ContextAttribs attribs)
/* 44:   */    throws LWJGLException
/* 45:   */  {
/* 46:46 */    super(pixel_format, attribs, false, false, true, false);
/* 47:47 */    nCreate(getHandle(), width, height);
/* 48:   */  }
/* 49:   */  
/* 50:   */  private static native void nCreate(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) throws LWJGLException;
/* 51:   */  
/* 52:52 */  public void destroy() { nDestroy(getHandle()); }
/* 53:   */  
/* 54:   */  private static native void nDestroy(ByteBuffer paramByteBuffer);
/* 55:   */  
/* 56:   */  protected void doLockAndInitHandle()
/* 57:   */    throws LWJGLException
/* 58:   */  {}
/* 59:   */  
/* 60:   */  protected void doUnlock()
/* 61:   */    throws LWJGLException
/* 62:   */  {}
/* 63:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXPbufferPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */