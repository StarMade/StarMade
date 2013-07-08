package org.lwjgl.util;

import java.io.Serializable;

public final class Point
  implements ReadablePoint, WritablePoint, Serializable
{
  static final long serialVersionUID = 1L;
  private int field_292;
  private int field_293;
  
  public Point() {}
  
  public Point(int local_x, int local_y)
  {
    setLocation(local_x, local_y);
  }
  
  public Point(ReadablePoint local_p)
  {
    setLocation(local_p);
  }
  
  public void setLocation(int local_x, int local_y)
  {
    this.field_292 = local_x;
    this.field_293 = local_y;
  }
  
  public void setLocation(ReadablePoint local_p)
  {
    this.field_292 = local_p.getX();
    this.field_293 = local_p.getY();
  }
  
  public void setX(int local_x)
  {
    this.field_292 = local_x;
  }
  
  public void setY(int local_y)
  {
    this.field_293 = local_y;
  }
  
  public void translate(int local_dx, int local_dy)
  {
    this.field_292 += local_dx;
    this.field_293 += local_dy;
  }
  
  public void translate(ReadablePoint local_p)
  {
    this.field_292 += local_p.getX();
    this.field_293 += local_p.getY();
  }
  
  public void untranslate(ReadablePoint local_p)
  {
    this.field_292 -= local_p.getX();
    this.field_293 -= local_p.getY();
  }
  
  public boolean equals(Object obj)
  {
    if ((obj instanceof Point))
    {
      Point local_pt = (Point)obj;
      return (this.field_292 == local_pt.field_292) && (this.field_293 == local_pt.field_293);
    }
    return super.equals(obj);
  }
  
  public String toString()
  {
    return getClass().getName() + "[x=" + this.field_292 + ",y=" + this.field_293 + "]";
  }
  
  public int hashCode()
  {
    int sum = this.field_292 + this.field_293;
    return sum * (sum + 1) / 2 + this.field_292;
  }
  
  public int getX()
  {
    return this.field_292;
  }
  
  public int getY()
  {
    return this.field_293;
  }
  
  public void getLocation(WritablePoint dest)
  {
    dest.setLocation(this.field_292, this.field_293);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.Point
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */