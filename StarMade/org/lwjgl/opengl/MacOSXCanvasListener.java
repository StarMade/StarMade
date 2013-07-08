package org.lwjgl.opengl;

import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

final class MacOSXCanvasListener
  implements ComponentListener, HierarchyListener
{
  private final Canvas canvas;
  private int width;
  private int height;
  private boolean context_update;
  private boolean resized;
  
  MacOSXCanvasListener(Canvas canvas)
  {
    this.canvas = canvas;
    canvas.addComponentListener(this);
    canvas.addHierarchyListener(this);
    setUpdate();
  }
  
  public void disableListeners()
  {
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        MacOSXCanvasListener.this.canvas.removeComponentListener(MacOSXCanvasListener.this);
        MacOSXCanvasListener.this.canvas.removeHierarchyListener(MacOSXCanvasListener.this);
      }
    });
  }
  
  public boolean syncShouldUpdateContext()
  {
    boolean should_update;
    synchronized (this)
    {
      should_update = this.context_update;
      this.context_update = false;
    }
    return should_update;
  }
  
  private synchronized void setUpdate()
  {
    synchronized (this)
    {
      this.width = this.canvas.getWidth();
      this.height = this.canvas.getHeight();
      this.context_update = true;
    }
  }
  
  public int syncGetWidth()
  {
    synchronized (this)
    {
      return this.width;
    }
  }
  
  public int syncGetHeight()
  {
    synchronized (this)
    {
      return this.height;
    }
  }
  
  public void componentShown(ComponentEvent local_e) {}
  
  public void componentHidden(ComponentEvent local_e) {}
  
  public void componentResized(ComponentEvent local_e)
  {
    setUpdate();
    this.resized = true;
  }
  
  public void componentMoved(ComponentEvent local_e)
  {
    setUpdate();
  }
  
  public void hierarchyChanged(HierarchyEvent local_e)
  {
    setUpdate();
  }
  
  public boolean wasResized()
  {
    if (this.resized)
    {
      this.resized = false;
      return true;
    }
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.MacOSXCanvasListener
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */