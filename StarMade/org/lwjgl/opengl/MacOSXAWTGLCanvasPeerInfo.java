/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.awt.Canvas;
/*  4:   */import org.lwjgl.LWJGLException;
/*  5:   */
/* 40:   */final class MacOSXAWTGLCanvasPeerInfo
/* 41:   */  extends MacOSXCanvasPeerInfo
/* 42:   */{
/* 43:   */  private final Canvas component;
/* 44:   */  
/* 45:   */  MacOSXAWTGLCanvasPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs, boolean support_pbuffer)
/* 46:   */    throws LWJGLException
/* 47:   */  {
/* 48:48 */    super(pixel_format, attribs, support_pbuffer);
/* 49:49 */    this.component = component;
/* 50:   */  }
/* 51:   */  
/* 52:   */  protected void doLockAndInitHandle() throws LWJGLException {
/* 53:53 */    initHandle(this.component);
/* 54:   */  }
/* 55:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXAWTGLCanvasPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */