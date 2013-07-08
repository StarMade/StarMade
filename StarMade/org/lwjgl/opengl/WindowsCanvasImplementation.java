package org.lwjgl.opengl;

import java.awt.Canvas;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.Toolkit;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

final class WindowsCanvasImplementation
  implements AWTCanvasImplementation
{
  public PeerInfo createPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs)
    throws LWJGLException
  {
    return new WindowsAWTGLCanvasPeerInfo(component, pixel_format);
  }
  
  public GraphicsConfiguration findConfiguration(GraphicsDevice device, PixelFormat pixel_format)
    throws LWJGLException
  {
    return null;
  }
  
  static
  {
    Toolkit.getDefaultToolkit();
    AccessController.doPrivileged(new PrivilegedAction()
    {
      public Object run()
      {
        try
        {
          System.loadLibrary("jawt");
        }
        catch (UnsatisfiedLinkError local_e)
        {
          LWJGLUtil.log("Failed to load jawt: " + local_e.getMessage());
        }
        return null;
      }
    });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.WindowsCanvasImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */