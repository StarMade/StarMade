/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.awt.Canvas;
/*  4:   */import java.nio.ByteBuffer;
/*  5:   */import org.lwjgl.LWJGLException;
/*  6:   */import org.lwjgl.LWJGLUtil;
/*  7:   */
/* 44:   */final class LinuxAWTGLCanvasPeerInfo
/* 45:   */  extends LinuxPeerInfo
/* 46:   */{
/* 47:   */  private final Canvas component;
/* 48:48 */  private final AWTSurfaceLock awt_surface = new AWTSurfaceLock();
/* 49:49 */  private int screen = -1;
/* 50:   */  
/* 51:   */  LinuxAWTGLCanvasPeerInfo(Canvas component) {
/* 52:52 */    this.component = component;
/* 53:   */  }
/* 54:   */  
/* 55:   */  protected void doLockAndInitHandle() throws LWJGLException {
/* 56:56 */    ByteBuffer surface_handle = this.awt_surface.lockAndGetHandle(this.component);
/* 57:57 */    if (this.screen == -1) {
/* 58:   */      try {
/* 59:59 */        this.screen = getScreenFromSurfaceInfo(surface_handle);
/* 60:   */      } catch (LWJGLException e) {
/* 61:61 */        LWJGLUtil.log("Got exception while trying to determine screen: " + e);
/* 62:62 */        this.screen = 0;
/* 63:   */      }
/* 64:   */    }
/* 65:65 */    nInitHandle(this.screen, surface_handle, getHandle()); }
/* 66:   */  
/* 67:   */  private static native int getScreenFromSurfaceInfo(ByteBuffer paramByteBuffer) throws LWJGLException;
/* 68:   */  
/* 69:   */  private static native void nInitHandle(int paramInt, ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/* 70:   */  
/* 71:71 */  protected void doUnlock() throws LWJGLException { this.awt_surface.unlock(); }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.LinuxAWTGLCanvasPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */