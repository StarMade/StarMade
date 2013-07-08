/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.awt.Canvas;
/*  4:   */import java.awt.GraphicsConfiguration;
/*  5:   */import java.awt.GraphicsDevice;
/*  6:   */import org.lwjgl.LWJGLException;
/*  7:   */
/* 41:   */final class MacOSXCanvasImplementation
/* 42:   */  implements AWTCanvasImplementation
/* 43:   */{
/* 44:   */  public PeerInfo createPeerInfo(Canvas component, PixelFormat pixel_format, ContextAttribs attribs)
/* 45:   */    throws LWJGLException
/* 46:   */  {
/* 47:   */    try
/* 48:   */    {
/* 49:49 */      return new MacOSXAWTGLCanvasPeerInfo(component, pixel_format, attribs, true);
/* 50:   */    } catch (LWJGLException e) {}
/* 51:51 */    return new MacOSXAWTGLCanvasPeerInfo(component, pixel_format, attribs, false);
/* 52:   */  }
/* 53:   */  
/* 61:   */  public GraphicsConfiguration findConfiguration(GraphicsDevice device, PixelFormat pixel_format)
/* 62:   */    throws LWJGLException
/* 63:   */  {
/* 64:64 */    return null;
/* 65:   */  }
/* 66:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXCanvasImplementation
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */