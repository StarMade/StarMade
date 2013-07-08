package org.lwjgl.util;

import java.io.Serializable;

public final class Dimension
  implements Serializable, ReadableDimension, WritableDimension
{
  static final long serialVersionUID = 1L;
  private int width;
  private int height;
  
  public Dimension() {}
  
  public Dimension(int local_w, int local_h)
  {
    this.width = local_w;
    this.height = local_h;
  }
  
  public Dimension(ReadableDimension local_d)
  {
    setSize(local_d);
  }
  
  public void setSize(int local_w, int local_h)
  {
    this.width = local_w;
    this.height = local_h;
  }
  
  public void setSize(ReadableDimension local_d)
  {
    this.width = local_d.getWidth();
    this.height = local_d.getHeight();
  }
  
  public void getSize(WritableDimension dest)
  {
    dest.setSize(this);
  }
  
  public boolean equals(Object obj)
  {
    if ((obj instanceof ReadableDimension))
    {
      ReadableDimension local_d = (ReadableDimension)obj;
      return (this.width == local_d.getWidth()) && (this.height == local_d.getHeight());
    }
    return false;
  }
  
  public int hashCode()
  {
    int sum = this.width + this.height;
    return sum * (sum + 1) / 2 + this.width;
  }
  
  public String toString()
  {
    return getClass().getName() + "[width=" + this.width + ",height=" + this.height + "]";
  }
  
  public int getHeight()
  {
    return this.height;
  }
  
  public void setHeight(int height)
  {
    this.height = height;
  }
  
  public int getWidth()
  {
    return this.width;
  }
  
  public void setWidth(int width)
  {
    this.width = width;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.Dimension
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */