/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.LWJGLException;
/*  5:   */
/* 40:   */final class LinuxPbufferPeerInfo
/* 41:   */  extends LinuxPeerInfo
/* 42:   */{
/* 43:   */  LinuxPbufferPeerInfo(int width, int height, PixelFormat pixel_format)
/* 44:   */    throws LWJGLException
/* 45:   */  {
/* 46:46 */    LinuxDisplay.lockAWT();
/* 47:   */    try {
/* 48:48 */      GLContext.loadOpenGLLibrary();
/* 49:   */      try {
/* 50:50 */        LinuxDisplay.incDisplay();
/* 51:   */        try {
/* 52:52 */          nInitHandle(LinuxDisplay.getDisplay(), LinuxDisplay.getDefaultScreen(), getHandle(), width, height, pixel_format);
/* 53:   */        }
/* 54:   */        catch (LWJGLException e) {
/* 55:55 */          throw e;
/* 56:   */        }
/* 57:   */      }
/* 58:   */      catch (LWJGLException e) {
/* 59:59 */        throw e;
/* 60:   */      }
/* 61:   */    } finally {
/* 62:62 */      LinuxDisplay.unlockAWT();
/* 63:   */    }
/* 64:   */  }
/* 65:   */  
/* 66:   */  private static native void nInitHandle(long paramLong, int paramInt1, ByteBuffer paramByteBuffer, int paramInt2, int paramInt3, PixelFormat paramPixelFormat) throws LWJGLException;
/* 67:   */  
/* 68:68 */  public void destroy() { LinuxDisplay.lockAWT();
/* 69:69 */    nDestroy(getHandle());
/* 70:70 */    LinuxDisplay.decDisplay();
/* 71:71 */    GLContext.unloadOpenGLLibrary();
/* 72:72 */    LinuxDisplay.unlockAWT();
/* 73:   */  }
/* 74:   */  
/* 75:   */  private static native void nDestroy(ByteBuffer paramByteBuffer);
/* 76:   */  
/* 77:   */  protected void doLockAndInitHandle()
/* 78:   */    throws LWJGLException
/* 79:   */  {}
/* 80:   */  
/* 81:   */  protected void doUnlock()
/* 82:   */    throws LWJGLException
/* 83:   */  {}
/* 84:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxPbufferPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */