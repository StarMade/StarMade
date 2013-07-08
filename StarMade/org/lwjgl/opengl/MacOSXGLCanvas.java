/*  1:   */package org.lwjgl.opengl;
/*  2:   */
/*  3:   */import java.awt.Canvas;
/*  4:   */import java.awt.Graphics;
/*  5:   */
/* 41:   */final class MacOSXGLCanvas
/* 42:   */  extends Canvas
/* 43:   */{
/* 44:   */  private static final long serialVersionUID = 6916664741667434870L;
/* 45:   */  private boolean canvas_painted;
/* 46:   */  private boolean dirty;
/* 47:   */  
/* 48:   */  public void update(Graphics g)
/* 49:   */  {
/* 50:50 */    paint(g);
/* 51:   */  }
/* 52:   */  
/* 53:   */  public void paint(Graphics g) {
/* 54:54 */    synchronized (this) {
/* 55:55 */      this.dirty = true;
/* 56:56 */      this.canvas_painted = true;
/* 57:   */    }
/* 58:   */  }
/* 59:   */  
/* 60:   */  public boolean syncCanvasPainted() {
/* 61:   */    boolean result;
/* 62:62 */    synchronized (this) {
/* 63:63 */      result = this.canvas_painted;
/* 64:64 */      this.canvas_painted = false;
/* 65:   */    }
/* 66:66 */    return result;
/* 67:   */  }
/* 68:   */  
/* 69:   */  public boolean syncIsDirty() {
/* 70:   */    boolean result;
/* 71:71 */    synchronized (this) {
/* 72:72 */      result = this.dirty;
/* 73:73 */      this.dirty = false;
/* 74:   */    }
/* 75:75 */    return result;
/* 76:   */  }
/* 77:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.lwjgl.opengl.MacOSXGLCanvas
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */