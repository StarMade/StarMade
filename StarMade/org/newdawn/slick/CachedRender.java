/*  1:   */package org.newdawn.slick;
/*  2:   */
/*  3:   */import org.newdawn.slick.opengl.SlickCallable;
/*  4:   */import org.newdawn.slick.opengl.renderer.Renderer;
/*  5:   */import org.newdawn.slick.opengl.renderer.SGL;
/*  6:   */
/* 18:   */public class CachedRender
/* 19:   */{
/* 20:20 */  protected static SGL GL = ;
/* 21:   */  
/* 23:   */  private Runnable runnable;
/* 24:   */  
/* 25:25 */  private int list = -1;
/* 26:   */  
/* 32:   */  public CachedRender(Runnable runnable)
/* 33:   */  {
/* 34:34 */    this.runnable = runnable;
/* 35:35 */    build();
/* 36:   */  }
/* 37:   */  
/* 40:   */  private void build()
/* 41:   */  {
/* 42:42 */    if (this.list == -1) {
/* 43:43 */      this.list = GL.glGenLists(1);
/* 44:   */      
/* 45:45 */      SlickCallable.enterSafeBlock();
/* 46:46 */      GL.glNewList(this.list, 4864);
/* 47:47 */      this.runnable.run();
/* 48:48 */      GL.glEndList();
/* 49:49 */      SlickCallable.leaveSafeBlock();
/* 50:   */    } else {
/* 51:51 */      throw new RuntimeException("Attempt to build the display list more than once in CachedRender");
/* 52:   */    }
/* 53:   */  }
/* 54:   */  
/* 58:   */  public void render()
/* 59:   */  {
/* 60:60 */    if (this.list == -1) {
/* 61:61 */      throw new RuntimeException("Attempt to render cached operations that have been destroyed");
/* 62:   */    }
/* 63:   */    
/* 64:64 */    SlickCallable.enterSafeBlock();
/* 65:65 */    GL.glCallList(this.list);
/* 66:66 */    SlickCallable.leaveSafeBlock();
/* 67:   */  }
/* 68:   */  
/* 71:   */  public void destroy()
/* 72:   */  {
/* 73:73 */    GL.glDeleteLists(this.list, 1);
/* 74:74 */    this.list = -1;
/* 75:   */  }
/* 76:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.CachedRender
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */