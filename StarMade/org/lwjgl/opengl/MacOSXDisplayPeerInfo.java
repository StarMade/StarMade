/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.awt.Canvas;
/*  4:   */import org.lwjgl.LWJGLException;
/*  5:   */
/* 40:   */final class MacOSXDisplayPeerInfo
/* 41:   */  extends MacOSXCanvasPeerInfo
/* 42:   */{
/* 43:   */  private boolean locked;
/* 44:   */  
/* 45:   */  MacOSXDisplayPeerInfo(PixelFormat pixel_format, ContextAttribs attribs, boolean support_pbuffer)
/* 46:   */    throws LWJGLException
/* 47:   */  {
/* 48:48 */    super(pixel_format, attribs, support_pbuffer);
/* 49:   */  }
/* 50:   */  
/* 51:   */  protected void doLockAndInitHandle() throws LWJGLException {
/* 52:52 */    if (this.locked)
/* 53:53 */      throw new RuntimeException("Already locked");
/* 54:54 */    Canvas canvas = ((MacOSXDisplay)Display.getImplementation()).getCanvas();
/* 55:55 */    if (canvas != null) {
/* 56:56 */      initHandle(canvas);
/* 57:57 */      this.locked = true;
/* 58:   */    }
/* 59:   */  }
/* 60:   */  
/* 61:   */  protected void doUnlock() throws LWJGLException {
/* 62:62 */    if (this.locked) {
/* 63:63 */      super.doUnlock();
/* 64:64 */      this.locked = false;
/* 65:   */    }
/* 66:   */  }
/* 67:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXDisplayPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */