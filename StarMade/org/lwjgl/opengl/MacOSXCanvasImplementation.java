package org.lwjgl.opengl;

import java.awt.Canvas;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import org.lwjgl.LWJGLException;

final class MacOSXCanvasImplementation
  implements AWTCanvasImplementation
{
  public PeerInfo createPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs)
    throws LWJGLException
  {
    try
    {
      return new MacOSXAWTGLCanvasPeerInfo(component, pixel_format, attribs, true);
    }
    catch (LWJGLException local_e) {}
    return new MacOSXAWTGLCanvasPeerInfo(component, pixel_format, attribs, false);
  }
  
  public GraphicsConfiguration findConfiguration(GraphicsDevice device, PixelFormat pixel_format)
    throws LWJGLException
  {
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.MacOSXCanvasImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */