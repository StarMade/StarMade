package org.newdawn.slick.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * @deprecated
 */
public abstract class BasicComponent
  extends AbstractComponent
{
  protected int field_50;
  protected int field_51;
  protected int width;
  protected int height;
  
  public BasicComponent(GUIContext container)
  {
    super(container);
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public int getX()
  {
    return this.field_50;
  }
  
  public int getY()
  {
    return this.field_51;
  }
  
  public abstract void renderImpl(GUIContext paramGUIContext, Graphics paramGraphics);
  
  public void render(GUIContext container, Graphics local_g)
    throws SlickException
  {
    renderImpl(container, local_g);
  }
  
  public void setLocation(int local_x, int local_y)
  {
    this.field_50 = local_x;
    this.field_51 = local_y;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.gui.BasicComponent
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */