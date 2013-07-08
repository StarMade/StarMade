package org.lwjgl.opengl;

import java.awt.Canvas;
import org.lwjgl.LWJGLException;

final class MacOSXAWTGLCanvasPeerInfo
  extends MacOSXCanvasPeerInfo
{
  private final Canvas component;
  
  MacOSXAWTGLCanvasPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs, boolean support_pbuffer)
    throws LWJGLException
  {
    super(pixel_format, attribs, support_pbuffer);
    this.component = component;
  }
  
  protected void doLockAndInitHandle()
    throws LWJGLException
  {
    initHandle(this.component);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.MacOSXAWTGLCanvasPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */