package org.lwjgl.opengl;

import java.awt.Canvas;
import java.awt.Graphics;

final class MacOSXGLCanvas
  extends Canvas
{
  private static final long serialVersionUID = 6916664741667434870L;
  private boolean canvas_painted;
  private boolean dirty;
  
  public void update(Graphics local_g)
  {
    paint(local_g);
  }
  
  public void paint(Graphics local_g)
  {
    synchronized (this)
    {
      this.dirty = true;
      this.canvas_painted = true;
    }
  }
  
  public boolean syncCanvasPainted()
  {
    boolean result;
    synchronized (this)
    {
      result = this.canvas_painted;
      this.canvas_painted = false;
    }
    return result;
  }
  
  public boolean syncIsDirty()
  {
    boolean result;
    synchronized (this)
    {
      result = this.dirty;
      this.dirty = false;
    }
    return result;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.opengl.MacOSXGLCanvas
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */