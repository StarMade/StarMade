/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.awt.Canvas;
/*  4:   */import java.awt.GraphicsConfiguration;
/*  5:   */import java.awt.GraphicsDevice;
/*  6:   */import java.awt.Toolkit;
/*  7:   */import java.security.AccessController;
/*  8:   */import java.security.PrivilegedAction;
/*  9:   */import org.lwjgl.LWJGLException;
/* 10:   */import org.lwjgl.LWJGLUtil;
/* 11:   */
/* 48:   */final class WindowsCanvasImplementation
/* 49:   */  implements AWTCanvasImplementation
/* 50:   */{
/* 51:   */  static
/* 52:   */  {
/* 53:53 */    Toolkit.getDefaultToolkit();
/* 54:54 */    AccessController.doPrivileged(new PrivilegedAction() {
/* 55:   */      public Object run() {
/* 56:   */        try {
/* 57:57 */          System.loadLibrary("jawt");
/* 59:   */        }
/* 60:   */        catch (UnsatisfiedLinkError e)
/* 61:   */        {
/* 62:62 */          LWJGLUtil.log("Failed to load jawt: " + e.getMessage());
/* 63:   */        }
/* 64:64 */        return null;
/* 65:   */      }
/* 66:   */    });
/* 67:   */  }
/* 68:   */  
/* 69:   */  public PeerInfo createPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
/* 70:70 */    return new WindowsAWTGLCanvasPeerInfo(component, pixel_format);
/* 71:   */  }
/* 72:   */  
/* 80:   */  public GraphicsConfiguration findConfiguration(GraphicsDevice device, PixelFormat pixel_format)
/* 81:   */    throws LWJGLException
/* 82:   */  {
/* 83:83 */    return null;
/* 84:   */  }
/* 85:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.WindowsCanvasImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */