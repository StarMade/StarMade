package org.newdawn.slick.opengl.renderer;

public class DefaultLineStripRenderer
  implements LineStripRenderer
{
  private SGL field_364 = Renderer.get();
  
  public void end()
  {
    this.field_364.glEnd();
  }
  
  public void setAntiAlias(boolean antialias)
  {
    if (antialias) {
      this.field_364.glEnable(2848);
    } else {
      this.field_364.glDisable(2848);
    }
  }
  
  public void setWidth(float width)
  {
    this.field_364.glLineWidth(width);
  }
  
  public void start()
  {
    this.field_364.glBegin(3);
  }
  
  public void vertex(float local_x, float local_y)
  {
    this.field_364.glVertex2f(local_x, local_y);
  }
  
  public void color(float local_r, float local_g, float local_b, float local_a)
  {
    this.field_364.glColor4f(local_r, local_g, local_b, local_a);
  }
  
  public void setLineCaps(boolean caps) {}
  
  public boolean applyGLLineFixes()
  {
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.opengl.renderer.DefaultLineStripRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */