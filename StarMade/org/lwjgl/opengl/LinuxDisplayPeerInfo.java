/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.LWJGLException;
/*  5:   */
/* 41:   */final class LinuxDisplayPeerInfo
/* 42:   */  extends LinuxPeerInfo
/* 43:   */{
/* 44:   */  final boolean egl;
/* 45:   */  
/* 46:   */  LinuxDisplayPeerInfo()
/* 47:   */    throws LWJGLException
/* 48:   */  {
/* 49:49 */    this.egl = true;
/* 50:50 */    org.lwjgl.opengles.GLContext.loadOpenGLLibrary();
/* 51:   */  }
/* 52:   */  
/* 53:   */  LinuxDisplayPeerInfo(PixelFormat pixel_format) throws LWJGLException {
/* 54:54 */    this.egl = false;
/* 55:55 */    LinuxDisplay.lockAWT();
/* 56:   */    try {
/* 57:57 */      GLContext.loadOpenGLLibrary();
/* 58:   */      try {
/* 59:59 */        LinuxDisplay.incDisplay();
/* 60:   */        try {
/* 61:61 */          initDefaultPeerInfo(LinuxDisplay.getDisplay(), LinuxDisplay.getDefaultScreen(), getHandle(), pixel_format);
/* 62:   */        }
/* 63:   */        catch (LWJGLException e) {
/* 64:64 */          throw e;
/* 65:   */        }
/* 66:   */      }
/* 67:   */      catch (LWJGLException e) {
/* 68:68 */        throw e;
/* 69:   */      }
/* 70:   */    } finally {
/* 71:71 */      LinuxDisplay.unlockAWT();
/* 72:   */    }
/* 73:   */  }
/* 74:   */  
/* 75:   */  private static native void initDefaultPeerInfo(long paramLong, int paramInt, ByteBuffer paramByteBuffer, PixelFormat paramPixelFormat) throws LWJGLException;
/* 76:   */  
/* 77:   */  protected void doLockAndInitHandle() throws LWJGLException {
/* 78:   */    
/* 79:79 */    try { initDrawable(LinuxDisplay.getWindow(), getHandle());
/* 80:   */    } finally {
/* 81:81 */      LinuxDisplay.unlockAWT();
/* 82:   */    }
/* 83:   */  }
/* 84:   */  
/* 85:   */  private static native void initDrawable(long paramLong, ByteBuffer paramByteBuffer);
/* 86:   */  
/* 87:   */  protected void doUnlock() throws LWJGLException
/* 88:   */  {}
/* 89:   */  
/* 90:   */  public void destroy() {
/* 91:91 */    super.destroy();
/* 92:   */    
/* 93:93 */    if (this.egl) {
/* 94:94 */      org.lwjgl.opengles.GLContext.unloadOpenGLLibrary();
/* 95:   */    } else {
/* 96:96 */      LinuxDisplay.lockAWT();
/* 97:97 */      LinuxDisplay.decDisplay();
/* 98:98 */      GLContext.unloadOpenGLLibrary();
/* 99:99 */      LinuxDisplay.unlockAWT();
/* 100:   */    }
/* 101:   */  }
/* 102:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxDisplayPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */