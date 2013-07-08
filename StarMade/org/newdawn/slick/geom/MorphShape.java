package org.newdawn.slick.geom;

import java.util.ArrayList;

public class MorphShape
  extends Shape
{
  private ArrayList shapes = new ArrayList();
  private float offset;
  private Shape current;
  private Shape next;
  
  public MorphShape(Shape base)
  {
    this.shapes.add(base);
    float[] copy = base.points;
    this.points = new float[copy.length];
    this.current = base;
    this.next = base;
  }
  
  public void addShape(Shape shape)
  {
    if (shape.points.length != this.points.length) {
      throw new RuntimeException("Attempt to morph between two shapes with different vertex counts");
    }
    Shape prev = (Shape)this.shapes.get(this.shapes.size() - 1);
    if (equalShapes(prev, shape)) {
      this.shapes.add(prev);
    } else {
      this.shapes.add(shape);
    }
    if (this.shapes.size() == 2) {
      this.next = ((Shape)this.shapes.get(1));
    }
  }
  
  private boolean equalShapes(Shape local_a, Shape local_b)
  {
    local_a.checkPoints();
    local_b.checkPoints();
    for (int local_i = 0; local_i < local_a.points.length; local_i++) {
      if (local_a.points[local_i] != local_b.points[local_i]) {
        return false;
      }
    }
    return true;
  }
  
  public void setMorphTime(float time)
  {
    int local_p = (int)time;
    int local_n = local_p + 1;
    float offset = time - local_p;
    local_p = rational(local_p);
    local_n = rational(local_n);
    setFrame(local_p, local_n, offset);
  }
  
  public void updateMorphTime(float delta)
  {
    this.offset += delta;
    if (this.offset < 0.0F)
    {
      int index = this.shapes.indexOf(this.current);
      if (index < 0) {
        index = this.shapes.size() - 1;
      }
      int nframe = rational(index + 1);
      setFrame(index, nframe, this.offset);
      this.offset += 1.0F;
    }
    else if (this.offset > 1.0F)
    {
      int index = this.shapes.indexOf(this.next);
      if (index < 1) {
        index = 0;
      }
      int nframe = rational(index + 1);
      setFrame(index, nframe, this.offset);
      this.offset -= 1.0F;
    }
    else
    {
      this.pointsDirty = true;
    }
  }
  
  public void setExternalFrame(Shape current)
  {
    this.current = current;
    this.next = ((Shape)this.shapes.get(0));
    this.offset = 0.0F;
  }
  
  private int rational(int local_n)
  {
    while (local_n >= this.shapes.size()) {
      local_n -= this.shapes.size();
    }
    while (local_n < 0) {
      local_n += this.shapes.size();
    }
    return local_n;
  }
  
  private void setFrame(int local_a, int local_b, float offset)
  {
    this.current = ((Shape)this.shapes.get(local_a));
    this.next = ((Shape)this.shapes.get(local_b));
    this.offset = offset;
    this.pointsDirty = true;
  }
  
  protected void createPoints()
  {
    if (this.current == this.next)
    {
      System.arraycopy(this.current.points, 0, this.points, 0, this.points.length);
      return;
    }
    float[] apoints = this.current.points;
    float[] bpoints = this.next.points;
    for (int local_i = 0; local_i < this.points.length; local_i++)
    {
      this.points[local_i] = (apoints[local_i] * (1.0F - this.offset));
      this.points[local_i] += bpoints[local_i] * this.offset;
    }
  }
  
  public Shape transform(Transform transform)
  {
    createPoints();
    Polygon poly = new Polygon(this.points);
    return poly;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.MorphShape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */