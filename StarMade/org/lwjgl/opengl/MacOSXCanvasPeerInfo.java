package org.lwjgl.opengl;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.nio.ByteBuffer;
import org.lwjgl.LWJGLException;

abstract class MacOSXCanvasPeerInfo
  extends MacOSXPeerInfo
{
  private final AWTSurfaceLock awt_surface = new AWTSurfaceLock();
  public ByteBuffer window_handle;
  
  protected MacOSXCanvasPeerInfo(PixelFormat pixel_format, ContextAttribs attribs, boolean support_pbuffer)
    throws LWJGLException
  {
    super(pixel_format, attribs, true, true, support_pbuffer, true);
  }
  
  protected void initHandle(Canvas component)
    throws LWJGLException
  {
    boolean forceCALayer = true;
    String javaVersion = System.getProperty("java.version");
    if ((javaVersion.startsWith("1.5")) || (javaVersion.startsWith("1.6"))) {
      forceCALayer = false;
    }
    Insets insets = getInsets(component);
    int top = insets != null ? insets.top : 0;
    int left = insets != null ? insets.left : 0;
    this.window_handle = nInitHandle(this.awt_surface.lockAndGetHandle(component), getHandle(), this.window_handle, forceCALayer, component.getX() - left, component.getY() - top);
    if (javaVersion.startsWith("1.7")) {
      addComponentListener(component);
    }
  }
  
  private void addComponentListener(final Canvas component)
  {
    ComponentListener[] components = component.getComponentListeners();
    for (int local_i = 0; local_i < components.length; local_i++)
    {
      ComponentListener local_c = components[local_i];
      if (local_c.toString() == "CanvasPeerInfoListener") {
        return;
      }
    }
    ComponentListener local_i = new ComponentListener()
    {
      public void componentHidden(ComponentEvent local_e) {}
      
      public void componentMoved(ComponentEvent local_e)
      {
        Insets insets = MacOSXCanvasPeerInfo.this.getInsets(component);
        int top = insets != null ? insets.top : 0;
        int left = insets != null ? insets.left : 0;
        MacOSXCanvasPeerInfo.nSetLayerPosition(MacOSXCanvasPeerInfo.this.getHandle(), component.getX() - left, component.getY() - top);
      }
      
      public void componentResized(ComponentEvent local_e)
      {
        Insets insets = MacOSXCanvasPeerInfo.this.getInsets(component);
        int top = insets != null ? insets.top : 0;
        int left = insets != null ? insets.left : 0;
        MacOSXCanvasPeerInfo.nSetLayerPosition(MacOSXCanvasPeerInfo.this.getHandle(), component.getX() - left, component.getY() - top);
      }
      
      public void componentShown(ComponentEvent local_e) {}
      
      public String toString()
      {
        return "CanvasPeerInfoListener";
      }
    };
    component.addComponentListener(local_i);
  }
  
  private static native ByteBuffer nInitHandle(ByteBuffer paramByteBuffer1, ByteBuffer paramByteBuffer2, ByteBuffer paramByteBuffer3, boolean paramBoolean, int paramInt1, int paramInt2)
    throws LWJGLException;
  
  private static native void nSetLayerPosition(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2);
  
  protected void doUnlock()
    throws LWJGLException
  {
    this.awt_surface.unlock();
  }
  
  private Insets getInsets(Canvas component)
  {
    for (Component parent = component.getParent(); parent != null; parent = parent.getParent()) {
      if ((parent instanceof Container)) {
        return ((Container)parent).getInsets();
      }
    }
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.MacOSXCanvasPeerInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */