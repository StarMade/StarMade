package org.lwjgl.util;

import java.io.Serializable;

public final class Rectangle
  implements ReadableRectangle, WritableRectangle, Serializable
{
  static final long serialVersionUID = 1L;
  private int field_335;
  private int field_336;
  private int width;
  private int height;
  
  public Rectangle() {}
  
  public Rectangle(int local_x, int local_y, int local_w, int local_h)
  {
    this.field_335 = local_x;
    this.field_336 = local_y;
    this.width = local_w;
    this.height = local_h;
  }
  
  public Rectangle(ReadablePoint local_p, ReadableDimension local_d)
  {
    this.field_335 = local_p.getX();
    this.field_336 = local_p.getY();
    this.width = local_d.getWidth();
    this.height = local_d.getHeight();
  }
  
  public Rectangle(ReadableRectangle local_r)
  {
    this.field_335 = local_r.getX();
    this.field_336 = local_r.getY();
    this.width = local_r.getWidth();
    this.height = local_r.getHeight();
  }
  
  public void setLocation(int local_x, int local_y)
  {
    this.field_335 = local_x;
    this.field_336 = local_y;
  }
  
  public void setLocation(ReadablePoint local_p)
  {
    this.field_335 = local_p.getX();
    this.field_336 = local_p.getY();
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
  
  public void setBounds(int local_x, int local_y, int local_w, int local_h)
  {
    this.field_335 = local_x;
    this.field_336 = local_y;
    this.width = local_w;
    this.height = local_h;
  }
  
  public void setBounds(ReadablePoint local_p, ReadableDimension local_d)
  {
    this.field_335 = local_p.getX();
    this.field_336 = local_p.getY();
    this.width = local_d.getWidth();
    this.height = local_d.getHeight();
  }
  
  public void setBounds(ReadableRectangle local_r)
  {
    this.field_335 = local_r.getX();
    this.field_336 = local_r.getY();
    this.width = local_r.getWidth();
    this.height = local_r.getHeight();
  }
  
  public void getBounds(WritableRectangle dest)
  {
    dest.setBounds(this.field_335, this.field_336, this.width, this.height);
  }
  
  public void getLocation(WritablePoint dest)
  {
    dest.setLocation(this.field_335, this.field_336);
  }
  
  public void getSize(WritableDimension dest)
  {
    dest.setSize(this.width, this.height);
  }
  
  public void translate(int local_x, int local_y)
  {
    this.field_335 += local_x;
    this.field_336 += local_y;
  }
  
  public void translate(ReadablePoint point)
  {
    this.field_335 += point.getX();
    this.field_336 += point.getY();
  }
  
  public void untranslate(ReadablePoint point)
  {
    this.field_335 -= point.getX();
    this.field_336 -= point.getY();
  }
  
  public boolean contains(ReadablePoint local_p)
  {
    return contains(local_p.getX(), local_p.getY());
  }
  
  public boolean contains(int local_X, int local_Y)
  {
    int local_w = this.width;
    int local_h = this.height;
    if ((local_w | local_h) < 0) {
      return false;
    }
    int local_x = this.field_335;
    int local_y = this.field_336;
    if ((local_X < local_x) || (local_Y < local_y)) {
      return false;
    }
    local_w += local_x;
    local_h += local_y;
    return ((local_w < local_x) || (local_w > local_X)) && ((local_h < local_y) || (local_h > local_Y));
  }
  
  public boolean contains(ReadableRectangle local_r)
  {
    return contains(local_r.getX(), local_r.getY(), local_r.getWidth(), local_r.getHeight());
  }
  
  public boolean contains(int local_X, int local_Y, int local_W, int local_H)
  {
    int local_w = this.width;
    int local_h = this.height;
    if ((local_w | local_h | local_W | local_H) < 0) {
      return false;
    }
    int local_x = this.field_335;
    int local_y = this.field_336;
    if ((local_X < local_x) || (local_Y < local_y)) {
      return false;
    }
    local_w += local_x;
    local_W += local_X;
    if (local_W <= local_X)
    {
      if ((local_w >= local_x) || (local_W > local_w)) {
        return false;
      }
    }
    else if ((local_w >= local_x) && (local_W > local_w)) {
      return false;
    }
    local_h += local_y;
    local_H += local_Y;
    if (local_H <= local_Y)
    {
      if ((local_h >= local_y) || (local_H > local_h)) {
        return false;
      }
    }
    else if ((local_h >= local_y) && (local_H > local_h)) {
      return false;
    }
    return true;
  }
  
  public boolean intersects(ReadableRectangle local_r)
  {
    int local_tw = this.width;
    int local_th = this.height;
    int local_rw = local_r.getWidth();
    int local_rh = local_r.getHeight();
    if ((local_rw <= 0) || (local_rh <= 0) || (local_tw <= 0) || (local_th <= 0)) {
      return false;
    }
    int local_tx = this.field_335;
    int local_ty = this.field_336;
    int local_rx = local_r.getX();
    int local_ry = local_r.getY();
    local_rw += local_rx;
    local_rh += local_ry;
    local_tw += local_tx;
    local_th += local_ty;
    return ((local_rw < local_rx) || (local_rw > local_tx)) && ((local_rh < local_ry) || (local_rh > local_ty)) && ((local_tw < local_tx) || (local_tw > local_rx)) && ((local_th < local_ty) || (local_th > local_ry));
  }
  
  public Rectangle intersection(ReadableRectangle local_r, Rectangle dest)
  {
    int tx1 = this.field_335;
    int ty1 = this.field_336;
    int rx1 = local_r.getX();
    int ry1 = local_r.getY();
    long tx2 = tx1;
    tx2 += this.width;
    long ty2 = ty1;
    ty2 += this.height;
    long rx2 = rx1;
    rx2 += local_r.getWidth();
    long ry2 = ry1;
    ry2 += local_r.getHeight();
    if (tx1 < rx1) {
      tx1 = rx1;
    }
    if (ty1 < ry1) {
      ty1 = ry1;
    }
    if (tx2 > rx2) {
      tx2 = rx2;
    }
    if (ty2 > ry2) {
      ty2 = ry2;
    }
    tx2 -= tx1;
    ty2 -= ty1;
    if (tx2 < -2147483648L) {
      tx2 = -2147483648L;
    }
    if (ty2 < -2147483648L) {
      ty2 = -2147483648L;
    }
    if (dest == null) {
      dest = new Rectangle(tx1, ty1, (int)tx2, (int)ty2);
    } else {
      dest.setBounds(tx1, ty1, (int)tx2, (int)ty2);
    }
    return dest;
  }
  
  public WritableRectangle union(ReadableRectangle local_r, WritableRectangle dest)
  {
    int local_x1 = Math.min(this.field_335, local_r.getX());
    int local_x2 = Math.max(this.field_335 + this.width, local_r.getX() + local_r.getWidth());
    int local_y1 = Math.min(this.field_336, local_r.getY());
    int local_y2 = Math.max(this.field_336 + this.height, local_r.getY() + local_r.getHeight());
    dest.setBounds(local_x1, local_y1, local_x2 - local_x1, local_y2 - local_y1);
    return dest;
  }
  
  public void add(int newx, int newy)
  {
    int local_x1 = Math.min(this.field_335, newx);
    int local_x2 = Math.max(this.field_335 + this.width, newx);
    int local_y1 = Math.min(this.field_336, newy);
    int local_y2 = Math.max(this.field_336 + this.height, newy);
    this.field_335 = local_x1;
    this.field_336 = local_y1;
    this.width = (local_x2 - local_x1);
    this.height = (local_y2 - local_y1);
  }
  
  public void add(ReadablePoint local_pt)
  {
    add(local_pt.getX(), local_pt.getY());
  }
  
  public void add(ReadableRectangle local_r)
  {
    int local_x1 = Math.min(this.field_335, local_r.getX());
    int local_x2 = Math.max(this.field_335 + this.width, local_r.getX() + local_r.getWidth());
    int local_y1 = Math.min(this.field_336, local_r.getY());
    int local_y2 = Math.max(this.field_336 + this.height, local_r.getY() + local_r.getHeight());
    this.field_335 = local_x1;
    this.field_336 = local_y1;
    this.width = (local_x2 - local_x1);
    this.height = (local_y2 - local_y1);
  }
  
  public void grow(int local_h, int local_v)
  {
    this.field_335 -= local_h;
    this.field_336 -= local_v;
    this.width += local_h * 2;
    this.height += local_v * 2;
  }
  
  public boolean isEmpty()
  {
    return (this.width <= 0) || (this.height <= 0);
  }
  
  public boolean equals(Object obj)
  {
    if ((obj instanceof Rectangle))
    {
      Rectangle local_r = (Rectangle)obj;
      return (this.field_335 == local_r.field_335) && (this.field_336 == local_r.field_336) && (this.width == local_r.width) && (this.height == local_r.height);
    }
    return super.equals(obj);
  }
  
  public String toString()
  {
    return getClass().getName() + "[x=" + this.field_335 + ",y=" + this.field_336 + ",width=" + this.width + ",height=" + this.height + "]";
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
  
  public int getX()
  {
    return this.field_335;
  }
  
  public void setX(int local_x)
  {
    this.field_335 = local_x;
  }
  
  public int getY()
  {
    return this.field_336;
  }
  
  public void setY(int local_y)
  {
    this.field_336 = local_y;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.lwjgl.util.Rectangle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */