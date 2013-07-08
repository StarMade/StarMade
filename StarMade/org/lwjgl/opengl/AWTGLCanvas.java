package org.lwjgl.opengl;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.PointerBuffer;
import org.lwjgl.Sys;

public class AWTGLCanvas
  extends Canvas
  implements DrawableLWJGL, ComponentListener, HierarchyListener
{
  private static final long serialVersionUID = 1L;
  private static final AWTCanvasImplementation implementation = createImplementation();
  private boolean update_context;
  private Object SYNC_LOCK = new Object();
  private final PixelFormat pixel_format;
  private final Drawable drawable;
  private final ContextAttribs attribs;
  private PeerInfo peer_info;
  private ContextGL context;
  private int reentry_count;
  private boolean first_run;
  
  static AWTCanvasImplementation createImplementation()
  {
    switch ()
    {
    case 1: 
      return new LinuxCanvasImplementation();
    case 3: 
      return new WindowsCanvasImplementation();
    case 2: 
      return new MacOSXCanvasImplementation();
    }
    throw new IllegalStateException("Unsupported platform");
  }
  
  private void setUpdate()
  {
    synchronized (this.SYNC_LOCK)
    {
      this.update_context = true;
    }
  }
  
  public void setPixelFormat(PixelFormatLWJGL local_pf)
    throws LWJGLException
  {
    throw new UnsupportedOperationException();
  }
  
  public void setPixelFormat(PixelFormatLWJGL local_pf, ContextAttribs attribs)
    throws LWJGLException
  {
    throw new UnsupportedOperationException();
  }
  
  public PixelFormatLWJGL getPixelFormat()
  {
    return this.pixel_format;
  }
  
  public ContextGL getContext()
  {
    return this.context;
  }
  
  public ContextGL createSharedContext()
    throws LWJGLException
  {
    synchronized (this.SYNC_LOCK)
    {
      if (this.context == null) {
        throw new IllegalStateException("Canvas not yet displayable");
      }
      return new ContextGL(this.peer_info, this.context.getContextAttribs(), this.context);
    }
  }
  
  public void checkGLError() {}
  
  public void initContext(float local_r, float local_g, float local_b)
  {
    GL11.glClearColor(local_r, local_g, local_b, 0.0F);
    GL11.glClear(16384);
  }
  
  public AWTGLCanvas()
    throws LWJGLException
  {
    this(new PixelFormat());
  }
  
  public AWTGLCanvas(PixelFormat pixel_format)
    throws LWJGLException
  {
    this(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(), pixel_format);
  }
  
  public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format)
    throws LWJGLException
  {
    this(device, pixel_format, null);
  }
  
  public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format, Drawable drawable)
    throws LWJGLException
  {
    this(device, pixel_format, drawable, null);
  }
  
  public AWTGLCanvas(GraphicsDevice device, PixelFormat pixel_format, Drawable drawable, ContextAttribs attribs)
    throws LWJGLException
  {
    super(implementation.findConfiguration(device, pixel_format));
    if (pixel_format == null) {
      throw new NullPointerException("Pixel format must be non-null");
    }
    addHierarchyListener(this);
    addComponentListener(this);
    this.drawable = drawable;
    this.pixel_format = pixel_format;
    this.attribs = attribs;
  }
  
  public void addNotify()
  {
    super.addNotify();
  }
  
  public void removeNotify()
  {
    synchronized (this.SYNC_LOCK)
    {
      destroy();
      super.removeNotify();
    }
  }
  
  public void setSwapInterval(int swap_interval)
  {
    synchronized (this.SYNC_LOCK)
    {
      if (this.context == null) {
        throw new IllegalStateException("Canvas not yet displayable");
      }
      ContextGL.setSwapInterval(swap_interval);
    }
  }
  
  public void setVSyncEnabled(boolean enabled)
  {
    setSwapInterval(enabled ? 1 : 0);
  }
  
  public void swapBuffers()
    throws LWJGLException
  {
    synchronized (this.SYNC_LOCK)
    {
      if (this.context == null) {
        throw new IllegalStateException("Canvas not yet displayable");
      }
      ContextGL.swapBuffers();
    }
  }
  
  public boolean isCurrent()
    throws LWJGLException
  {
    synchronized (this.SYNC_LOCK)
    {
      if (this.context == null) {
        throw new IllegalStateException("Canvas not yet displayable");
      }
      return this.context.isCurrent();
    }
  }
  
  public void makeCurrent()
    throws LWJGLException
  {
    synchronized (this.SYNC_LOCK)
    {
      if (this.context == null) {
        throw new IllegalStateException("Canvas not yet displayable");
      }
      this.context.makeCurrent();
    }
  }
  
  public void releaseContext()
    throws LWJGLException
  {
    synchronized (this.SYNC_LOCK)
    {
      if (this.context == null) {
        throw new IllegalStateException("Canvas not yet displayable");
      }
      if (this.context.isCurrent()) {
        this.context.releaseCurrent();
      }
    }
  }
  
  public final void destroy()
  {
    synchronized (this.SYNC_LOCK)
    {
      try
      {
        if (this.context != null)
        {
          this.context.forceDestroy();
          this.context = null;
          this.reentry_count = 0;
          this.peer_info.destroy();
          this.peer_info = null;
        }
      }
      catch (LWJGLException local_e)
      {
        throw new RuntimeException(local_e);
      }
    }
  }
  
  public final void setCLSharingProperties(PointerBuffer properties)
    throws LWJGLException
  {
    synchronized (this.SYNC_LOCK)
    {
      if (this.context == null) {
        throw new IllegalStateException("Canvas not yet displayable");
      }
      this.context.setCLSharingProperties(properties);
    }
  }
  
  protected void initGL() {}
  
  protected void paintGL() {}
  
  public final void paint(Graphics local_g)
  {
    LWJGLException exception = null;
    synchronized (this.SYNC_LOCK)
    {
      if (!isDisplayable()) {
        return;
      }
      try
      {
        if (this.peer_info == null) {
          this.peer_info = implementation.createPeerInfo(this, this.pixel_format, this.attribs);
        }
        this.peer_info.lockAndGetHandle();
        try
        {
          if (this.context == null)
          {
            this.context = new ContextGL(this.peer_info, this.attribs, this.drawable != null ? (ContextGL)((DrawableLWJGL)this.drawable).getContext() : null);
            this.first_run = true;
          }
          if (this.reentry_count == 0) {
            this.context.makeCurrent();
          }
          this.reentry_count += 1;
          try
          {
            if (this.update_context)
            {
              this.context.update();
              this.update_context = false;
            }
            if (this.first_run)
            {
              this.first_run = false;
              initGL();
            }
            paintGL();
          }
          finally
          {
            this.reentry_count -= 1;
            if (this.reentry_count == 0) {
              this.context.releaseCurrent();
            }
          }
        }
        finally
        {
          this.peer_info.unlock();
        }
      }
      catch (LWJGLException local_e)
      {
        exception = local_e;
      }
    }
    if (exception != null) {
      exceptionOccurred(exception);
    }
  }
  
  protected void exceptionOccurred(LWJGLException exception)
  {
    LWJGLUtil.log("Unhandled exception occurred, skipping paint(): " + exception);
  }
  
  public void update(Graphics local_g)
  {
    paint(local_g);
  }
  
  public void componentShown(ComponentEvent local_e) {}
  
  public void componentHidden(ComponentEvent local_e) {}
  
  public void componentResized(ComponentEvent local_e)
  {
    setUpdate();
  }
  
  public void componentMoved(ComponentEvent local_e)
  {
    setUpdate();
  }
  
  public void setLocation(int local_x, int local_y)
  {
    super.setLocation(local_x, local_y);
    setUpdate();
  }
  
  public void setLocation(Point local_p)
  {
    super.setLocation(local_p);
    setUpdate();
  }
  
  public void setSize(Dimension local_d)
  {
    super.setSize(local_d);
    setUpdate();
  }
  
  public void setSize(int width, int height)
  {
    super.setSize(width, height);
    setUpdate();
  }
  
  public void setBounds(int local_x, int local_y, int width, int height)
  {
    super.setBounds(local_x, local_y, width, height);
    setUpdate();
  }
  
  public void hierarchyChanged(HierarchyEvent local_e)
  {
    setUpdate();
  }
  
  static {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.AWTGLCanvas
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */