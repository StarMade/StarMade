package org.newdawn.slick.fills;

import org.newdawn.slick.Color;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class GradientFill
  implements ShapeFill
{
  private Vector2f none = new Vector2f(0.0F, 0.0F);
  private Vector2f start;
  private Vector2f end;
  private Color startCol;
  private Color endCol;
  private boolean local = false;
  
  public GradientFill(float local_sx, float local_sy, Color startCol, float local_ex, float local_ey, Color endCol)
  {
    this(local_sx, local_sy, startCol, local_ex, local_ey, endCol, false);
  }
  
  public GradientFill(float local_sx, float local_sy, Color startCol, float local_ex, float local_ey, Color endCol, boolean local)
  {
    this(new Vector2f(local_sx, local_sy), startCol, new Vector2f(local_ex, local_ey), endCol, local);
  }
  
  public GradientFill(Vector2f start, Color startCol, Vector2f end, Color endCol, boolean local)
  {
    this.start = new Vector2f(start);
    this.end = new Vector2f(end);
    this.startCol = new Color(startCol);
    this.endCol = new Color(endCol);
    this.local = local;
  }
  
  public GradientFill getInvertedCopy()
  {
    return new GradientFill(this.start, this.endCol, this.end, this.startCol, this.local);
  }
  
  public void setLocal(boolean local)
  {
    this.local = local;
  }
  
  public Vector2f getStart()
  {
    return this.start;
  }
  
  public Vector2f getEnd()
  {
    return this.end;
  }
  
  public Color getStartColor()
  {
    return this.startCol;
  }
  
  public Color getEndColor()
  {
    return this.endCol;
  }
  
  public void setStart(float local_x, float local_y)
  {
    setStart(new Vector2f(local_x, local_y));
  }
  
  public void setStart(Vector2f start)
  {
    this.start = new Vector2f(start);
  }
  
  public void setEnd(float local_x, float local_y)
  {
    setEnd(new Vector2f(local_x, local_y));
  }
  
  public void setEnd(Vector2f end)
  {
    this.end = new Vector2f(end);
  }
  
  public void setStartColor(Color color)
  {
    this.startCol = new Color(color);
  }
  
  public void setEndColor(Color color)
  {
    this.endCol = new Color(color);
  }
  
  public Color colorAt(Shape shape, float local_x, float local_y)
  {
    if (this.local) {
      return colorAt(local_x - shape.getCenterX(), local_y - shape.getCenterY());
    }
    return colorAt(local_x, local_y);
  }
  
  public Color colorAt(float local_x, float local_y)
  {
    float dx1 = this.end.getX() - this.start.getX();
    float dy1 = this.end.getY() - this.start.getY();
    float dx2 = -dy1;
    float dy2 = dx1;
    float denom = dy2 * dx1 - dx2 * dy1;
    if (denom == 0.0F) {
      return Color.black;
    }
    float local_ua = dx2 * (this.start.getY() - local_y) - dy2 * (this.start.getX() - local_x);
    local_ua /= denom;
    float local_ub = dx1 * (this.start.getY() - local_y) - dy1 * (this.start.getX() - local_x);
    local_ub /= denom;
    float local_u = local_ua;
    if (local_u < 0.0F) {
      local_u = 0.0F;
    }
    if (local_u > 1.0F) {
      local_u = 1.0F;
    }
    float local_v = 1.0F - local_u;
    Color col = new Color(1, 1, 1, 1);
    col.field_1776 = (local_u * this.endCol.field_1776 + local_v * this.startCol.field_1776);
    col.field_1778 = (local_u * this.endCol.field_1778 + local_v * this.startCol.field_1778);
    col.field_1777 = (local_u * this.endCol.field_1777 + local_v * this.startCol.field_1777);
    col.field_1779 = (local_u * this.endCol.field_1779 + local_v * this.startCol.field_1779);
    return col;
  }
  
  public Vector2f getOffsetAt(Shape shape, float local_x, float local_y)
  {
    return this.none;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.fills.GradientFill
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */