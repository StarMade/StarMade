package org.newdawn.slick;

import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class CachedRender
{
  protected static SGL field_1935 = ;
  private Runnable runnable;
  private int list = -1;
  
  public CachedRender(Runnable runnable)
  {
    this.runnable = runnable;
    build();
  }
  
  private void build()
  {
    if (this.list == -1)
    {
      this.list = field_1935.glGenLists(1);
      SlickCallable.enterSafeBlock();
      field_1935.glNewList(this.list, 4864);
      this.runnable.run();
      field_1935.glEndList();
      SlickCallable.leaveSafeBlock();
    }
    else
    {
      throw new RuntimeException("Attempt to build the display list more than once in CachedRender");
    }
  }
  
  public void render()
  {
    if (this.list == -1) {
      throw new RuntimeException("Attempt to render cached operations that have been destroyed");
    }
    SlickCallable.enterSafeBlock();
    field_1935.glCallList(this.list);
    SlickCallable.leaveSafeBlock();
  }
  
  public void destroy()
  {
    field_1935.glDeleteLists(this.list, 1);
    this.list = -1;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.CachedRender
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */