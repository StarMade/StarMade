/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.nio.ByteBuffer;
/*  4:   */import org.lwjgl.LWJGLException;
/*  5:   */
/* 41:   */final class WindowsDisplayPeerInfo
/* 42:   */  extends WindowsPeerInfo
/* 43:   */{
/* 44:   */  final boolean egl;
/* 45:   */  
/* 46:   */  WindowsDisplayPeerInfo(boolean egl)
/* 47:   */    throws LWJGLException
/* 48:   */  {
/* 49:49 */    this.egl = egl;
/* 50:   */    
/* 51:51 */    if (egl) {
/* 52:52 */      org.lwjgl.opengles.GLContext.loadOpenGLLibrary();
/* 53:   */    } else
/* 54:54 */      GLContext.loadOpenGLLibrary();
/* 55:   */  }
/* 56:   */  
/* 57:   */  void initDC(long hwnd, long hdc) throws LWJGLException {
/* 58:58 */    nInitDC(getHandle(), hwnd, hdc);
/* 59:   */  }
/* 60:   */  
/* 61:   */  private static native void nInitDC(ByteBuffer paramByteBuffer, long paramLong1, long paramLong2);
/* 62:   */  
/* 63:   */  protected void doLockAndInitHandle() throws LWJGLException
/* 64:   */  {}
/* 65:   */  
/* 66:   */  protected void doUnlock() throws LWJGLException
/* 67:   */  {}
/* 68:   */  
/* 69:   */  public void destroy()
/* 70:   */  {
/* 71:71 */    super.destroy();
/* 72:   */    
/* 73:73 */    if (this.egl) {
/* 74:74 */      org.lwjgl.opengles.GLContext.unloadOpenGLLibrary();
/* 75:   */    } else {
/* 76:76 */      GLContext.unloadOpenGLLibrary();
/* 77:   */    }
/* 78:   */  }
/* 79:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsDisplayPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */