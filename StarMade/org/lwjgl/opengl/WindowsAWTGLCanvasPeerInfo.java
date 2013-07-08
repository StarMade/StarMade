/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.awt.Canvas;
/*  4:   */import java.nio.ByteBuffer;
/*  5:   */import org.lwjgl.LWJGLException;
/*  6:   */
/* 44:   */final class WindowsAWTGLCanvasPeerInfo
/* 45:   */  extends WindowsPeerInfo
/* 46:   */{
/* 47:   */  private final Canvas component;
/* 48:48 */  private final AWTSurfaceLock awt_surface = new AWTSurfaceLock();
/* 49:   */  private final PixelFormat pixel_format;
/* 50:   */  private boolean has_pixel_format;
/* 51:   */  
/* 52:   */  WindowsAWTGLCanvasPeerInfo(Canvas component, PixelFormat pixel_format) {
/* 53:53 */    this.component = component;
/* 54:54 */    this.pixel_format = pixel_format;
/* 55:   */  }
/* 56:   */  
/* 57:   */  protected void doLockAndInitHandle() throws LWJGLException {
/* 58:58 */    nInitHandle(this.awt_surface.lockAndGetHandle(this.component), getHandle());
/* 59:59 */    if ((!this.has_pixel_format) && (this.pixel_format != null))
/* 60:   */    {
/* 61:61 */      int format = choosePixelFormat(getHdc(), this.component.getX(), this.component.getY(), this.pixel_format, null, true, true, false, true);
/* 62:62 */      setPixelFormat(getHdc(), format);
/* 63:63 */      this.has_pixel_format = true;
/* 64:   */    }
/* 65:   */  }
/* 66:   */  
/* 67:   */  private static native void nInitHandle(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2) throws LWJGLException;
/* 68:   */  
/* 69:69 */  protected void doUnlock() throws LWJGLException { this.awt_surface.unlock(); }
/* 70:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsAWTGLCanvasPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */