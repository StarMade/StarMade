/*   1:    */package org.newdawn.slick.util;
/*   2:    */
/*   3:    */import org.newdawn.slick.Color;
/*   4:    */import org.newdawn.slick.Image;
/*   5:    */
/*  15:    */public class LocatedImage
/*  16:    */{
/*  17:    */  private Image image;
/*  18:    */  private int x;
/*  19:    */  private int y;
/*  20: 20 */  private Color filter = Color.white;
/*  21:    */  
/*  24:    */  private float width;
/*  25:    */  
/*  28:    */  private float height;
/*  29:    */  
/*  32:    */  public LocatedImage(Image image, int x, int y)
/*  33:    */  {
/*  34: 34 */    this.image = image;
/*  35: 35 */    this.x = x;
/*  36: 36 */    this.y = y;
/*  37: 37 */    this.width = image.getWidth();
/*  38: 38 */    this.height = image.getHeight();
/*  39:    */  }
/*  40:    */  
/*  45:    */  public float getHeight()
/*  46:    */  {
/*  47: 47 */    return this.height;
/*  48:    */  }
/*  49:    */  
/*  54:    */  public float getWidth()
/*  55:    */  {
/*  56: 56 */    return this.width;
/*  57:    */  }
/*  58:    */  
/*  63:    */  public void setHeight(float height)
/*  64:    */  {
/*  65: 65 */    this.height = height;
/*  66:    */  }
/*  67:    */  
/*  72:    */  public void setWidth(float width)
/*  73:    */  {
/*  74: 74 */    this.width = width;
/*  75:    */  }
/*  76:    */  
/*  81:    */  public void setColor(Color c)
/*  82:    */  {
/*  83: 83 */    this.filter = c;
/*  84:    */  }
/*  85:    */  
/*  90:    */  public Color getColor()
/*  91:    */  {
/*  92: 92 */    return this.filter;
/*  93:    */  }
/*  94:    */  
/*  99:    */  public void setX(int x)
/* 100:    */  {
/* 101:101 */    this.x = x;
/* 102:    */  }
/* 103:    */  
/* 108:    */  public void setY(int y)
/* 109:    */  {
/* 110:110 */    this.y = y;
/* 111:    */  }
/* 112:    */  
/* 117:    */  public int getX()
/* 118:    */  {
/* 119:119 */    return this.x;
/* 120:    */  }
/* 121:    */  
/* 126:    */  public int getY()
/* 127:    */  {
/* 128:128 */    return this.y;
/* 129:    */  }
/* 130:    */  
/* 133:    */  public void draw()
/* 134:    */  {
/* 135:135 */    this.image.draw(this.x, this.y, this.width, this.height, this.filter);
/* 136:    */  }
/* 137:    */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.newdawn.slick.util.LocatedImage
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */