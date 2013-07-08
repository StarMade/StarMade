package org.newdawn.slick.opengl.renderer;

public abstract interface LineStripRenderer
{
  public abstract boolean applyGLLineFixes();
  
  public abstract void start();
  
  public abstract void end();
  
  public abstract void vertex(float paramFloat1, float paramFloat2);
  
  public abstract void color(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4);
  
  public abstract void setWidth(float paramFloat);
  
  public abstract void setAntiAlias(boolean paramBoolean);
  
  public abstract void setLineCaps(boolean paramBoolean);
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.renderer.LineStripRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */