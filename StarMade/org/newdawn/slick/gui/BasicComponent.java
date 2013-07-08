/*  1:   */package org.newdawn.slick.gui;
/*  2:   */
/*  3:   */import org.newdawn.slick.Graphics;
/*  4:   */import org.newdawn.slick.SlickException;
/*  5:   */
/* 16:   *//**
/* 17:   */ * @deprecated
/* 18:   */ */
/* 19:   */public abstract class BasicComponent
/* 20:   */  extends AbstractComponent
/* 21:   */{
/* 22:   */  protected int x;
/* 23:   */  protected int y;
/* 24:   */  protected int width;
/* 25:   */  protected int height;
/* 26:   */  
/* 27:   */  public BasicComponent(GUIContext container)
/* 28:   */  {
/* 29:29 */    super(container);
/* 30:   */  }
/* 31:   */  
/* 34:   */  public int getHeight()
/* 35:   */  {
/* 36:36 */    return this.height;
/* 37:   */  }
/* 38:   */  
/* 41:   */  public int getWidth()
/* 42:   */  {
/* 43:43 */    return this.width;
/* 44:   */  }
/* 45:   */  
/* 48:   */  public int getX()
/* 49:   */  {
/* 50:50 */    return this.x;
/* 51:   */  }
/* 52:   */  
/* 55:   */  public int getY()
/* 56:   */  {
/* 57:57 */    return this.y;
/* 58:   */  }
/* 59:   */  
/* 64:   */  public abstract void renderImpl(GUIContext paramGUIContext, Graphics paramGraphics);
/* 65:   */  
/* 69:   */  public void render(GUIContext container, Graphics g)
/* 70:   */    throws SlickException
/* 71:   */  {
/* 72:72 */    renderImpl(container, g);
/* 73:   */  }
/* 74:   */  
/* 77:   */  public void setLocation(int x, int y)
/* 78:   */  {
/* 79:79 */    this.x = x;
/* 80:80 */    this.y = y;
/* 81:   */  }
/* 82:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.gui.BasicComponent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */