package org.newdawn.slick.geom;

public class Rectangle
  extends Shape
{
  protected float width;
  protected float height;
  
  public Rectangle(float local_x, float local_y, float width, float height)
  {
    this.field_1743 = local_x;
    this.field_1744 = local_y;
    this.width = width;
    this.height = height;
    this.maxX = (local_x + width);
    this.maxY = (local_y + height);
    checkPoints();
  }
  
  public boolean contains(float local_xp, float local_yp)
  {
    if (local_xp <= getX()) {
      return false;
    }
    if (local_yp <= getY()) {
      return false;
    }
    if (local_xp >= this.maxX) {
      return false;
    }
    return local_yp < this.maxY;
  }
  
  public void setBounds(Rectangle other)
  {
    setBounds(other.getX(), other.getY(), other.getWidth(), other.getHeight());
  }
  
  public void setBounds(float local_x, float local_y, float width, float height)
  {
    setX(local_x);
    setY(local_y);
    setSize(width, height);
  }
  
  public void setSize(float width, float height)
  {
    setWidth(width);
    setHeight(height);
  }
  
  public float getWidth()
  {
    return this.width;
  }
  
  public float getHeight()
  {
    return this.height;
  }
  
  public void grow(float local_h, float local_v)
  {
    setX(getX() - local_h);
    setY(getY() - local_v);
    setWidth(getWidth() + local_h * 2.0F);
    setHeight(getHeight() + local_v * 2.0F);
  }
  
  public void scaleGrow(float local_h, float local_v)
  {
    grow(getWidth() * (local_h - 1.0F), getHeight() * (local_v - 1.0F));
  }
  
  public void setWidth(float width)
  {
    if (width != this.width)
    {
      this.pointsDirty = true;
      this.width = width;
      this.maxX = (this.field_1743 + width);
    }
  }
  
  public void setHeight(float height)
  {
    if (height != this.height)
    {
      this.pointsDirty = true;
      this.height = height;
      this.maxY = (this.field_1744 + height);
    }
  }
  
  public boolean intersects(Shape shape)
  {
    if ((shape instanceof Rectangle))
    {
      Rectangle other = (Rectangle)shape;
      if ((this.field_1743 > other.field_1743 + other.width) || (this.field_1743 + this.width < other.field_1743)) {
        return false;
      }
      return (this.field_1744 <= other.field_1744 + other.height) && (this.field_1744 + this.height >= other.field_1744);
    }
    if ((shape instanceof Circle)) {
      return intersects((Circle)shape);
    }
    return super.intersects(shape);
  }
  
  protected void createPoints()
  {
    float useWidth = this.width;
    float useHeight = this.height;
    this.points = new float[8];
    this.points[0] = this.field_1743;
    this.points[1] = this.field_1744;
    this.points[2] = (this.field_1743 + useWidth);
    this.points[3] = this.field_1744;
    this.points[4] = (this.field_1743 + useWidth);
    this.points[5] = (this.field_1744 + useHeight);
    this.points[6] = this.field_1743;
    this.points[7] = (this.field_1744 + useHeight);
    this.maxX = this.points[2];
    this.maxY = this.points[5];
    this.minX = this.points[0];
    this.minY = this.points[1];
    findCenter();
    calculateRadius();
  }
  
  private boolean intersects(Circle other)
  {
    return other.intersects(this);
  }
  
  public String toString()
  {
    return "[Rectangle " + this.width + "x" + this.height + "]";
  }
  
  public static boolean contains(float local_xp, float local_yp, float local_xr, float local_yr, float widthr, float heightr)
  {
    return (local_xp >= local_xr) && (local_yp >= local_yr) && (local_xp <= local_xr + widthr) && (local_yp <= local_yr + heightr);
  }
  
  public Shape transform(Transform transform)
  {
    checkPoints();
    Polygon resultPolygon = new Polygon();
    float[] result = new float[this.points.length];
    transform.transform(this.points, 0, result, 0, this.points.length / 2);
    resultPolygon.points = result;
    resultPolygon.findCenter();
    resultPolygon.checkPoints();
    return resultPolygon;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.Rectangle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */