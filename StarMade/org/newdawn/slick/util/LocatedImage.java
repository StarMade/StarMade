package org.newdawn.slick.util;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class LocatedImage
{
  private Image image;
  private int field_497;
  private int field_498;
  private Color filter = Color.white;
  private float width;
  private float height;
  
  public LocatedImage(Image image, int local_x, int local_y)
  {
    this.image = image;
    this.field_497 = local_x;
    this.field_498 = local_y;
    this.width = image.getWidth();
    this.height = image.getHeight();
  }
  
  public float getHeight()
  {
    return this.height;
  }
  
  public float getWidth()
  {
    return this.width;
  }
  
  public void setHeight(float height)
  {
    this.height = height;
  }
  
  public void setWidth(float width)
  {
    this.width = width;
  }
  
  public void setColor(Color local_c)
  {
    this.filter = local_c;
  }
  
  public Color getColor()
  {
    return this.filter;
  }
  
  public void setX(int local_x)
  {
    this.field_497 = local_x;
  }
  
  public void setY(int local_y)
  {
    this.field_498 = local_y;
  }
  
  public int getX()
  {
    return this.field_497;
  }
  
  public int getY()
  {
    return this.field_498;
  }
  
  public void draw()
  {
    this.image.draw(this.field_497, this.field_498, this.width, this.height, this.filter);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.util.LocatedImage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */