/*  1:   */package org.newdawn.slick.opengl.renderer;
/*  2:   */
/*  8:   */public class DefaultLineStripRenderer
/*  9:   */  implements LineStripRenderer
/* 10:   */{
/* 11:11 */  private SGL GL = Renderer.get();
/* 12:   */  
/* 15:   */  public void end()
/* 16:   */  {
/* 17:17 */    this.GL.glEnd();
/* 18:   */  }
/* 19:   */  
/* 22:   */  public void setAntiAlias(boolean antialias)
/* 23:   */  {
/* 24:24 */    if (antialias) {
/* 25:25 */      this.GL.glEnable(2848);
/* 26:   */    } else {
/* 27:27 */      this.GL.glDisable(2848);
/* 28:   */    }
/* 29:   */  }
/* 30:   */  
/* 33:   */  public void setWidth(float width)
/* 34:   */  {
/* 35:35 */    this.GL.glLineWidth(width);
/* 36:   */  }
/* 37:   */  
/* 40:   */  public void start()
/* 41:   */  {
/* 42:42 */    this.GL.glBegin(3);
/* 43:   */  }
/* 44:   */  
/* 47:   */  public void vertex(float x, float y)
/* 48:   */  {
/* 49:49 */    this.GL.glVertex2f(x, y);
/* 50:   */  }
/* 51:   */  
/* 54:   */  public void color(float r, float g, float b, float a)
/* 55:   */  {
/* 56:56 */    this.GL.glColor4f(r, g, b, a);
/* 57:   */  }
/* 58:   */  
/* 62:   */  public void setLineCaps(boolean caps) {}
/* 63:   */  
/* 67:   */  public boolean applyGLLineFixes()
/* 68:   */  {
/* 69:69 */    return true;
/* 70:   */  }
/* 71:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.opengl.renderer.DefaultLineStripRenderer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */