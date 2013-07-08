package org.newdawn.slick.geom;

import java.util.ArrayList;

public class Polygon
  extends Shape
{
  private boolean allowDups = false;
  private boolean closed = true;
  
  public Polygon(float[] points)
  {
    int length = points.length;
    this.points = new float[length];
    this.maxX = -1.401299E-045F;
    this.maxY = -1.401299E-045F;
    this.minX = 3.4028235E+38F;
    this.minY = 3.4028235E+38F;
    this.field_1743 = 3.4028235E+38F;
    this.field_1744 = 3.4028235E+38F;
    for (int local_i = 0; local_i < length; local_i++)
    {
      this.points[local_i] = points[local_i];
      if (local_i % 2 == 0)
      {
        if (points[local_i] > this.maxX) {
          this.maxX = points[local_i];
        }
        if (points[local_i] < this.minX) {
          this.minX = points[local_i];
        }
        if (points[local_i] < this.field_1743) {
          this.field_1743 = points[local_i];
        }
      }
      else
      {
        if (points[local_i] > this.maxY) {
          this.maxY = points[local_i];
        }
        if (points[local_i] < this.minY) {
          this.minY = points[local_i];
        }
        if (points[local_i] < this.field_1744) {
          this.field_1744 = points[local_i];
        }
      }
    }
    findCenter();
    calculateRadius();
    this.pointsDirty = true;
  }
  
  public Polygon()
  {
    this.points = new float[0];
    this.maxX = -1.401299E-045F;
    this.maxY = -1.401299E-045F;
    this.minX = 3.4028235E+38F;
    this.minY = 3.4028235E+38F;
  }
  
  public void setAllowDuplicatePoints(boolean allowDups)
  {
    this.allowDups = allowDups;
  }
  
  public void addPoint(float local_x, float local_y)
  {
    if ((hasVertex(local_x, local_y)) && (!this.allowDups)) {
      return;
    }
    ArrayList tempPoints = new ArrayList();
    for (int local_i = 0; local_i < this.points.length; local_i++) {
      tempPoints.add(new Float(this.points[local_i]));
    }
    tempPoints.add(new Float(local_x));
    tempPoints.add(new Float(local_y));
    int local_i = tempPoints.size();
    this.points = new float[local_i];
    for (int local_i1 = 0; local_i1 < local_i; local_i1++) {
      this.points[local_i1] = ((Float)tempPoints.get(local_i1)).floatValue();
    }
    if (local_x > this.maxX) {
      this.maxX = local_x;
    }
    if (local_y > this.maxY) {
      this.maxY = local_y;
    }
    if (local_x < this.minX) {
      this.minX = local_x;
    }
    if (local_y < this.minY) {
      this.minY = local_y;
    }
    findCenter();
    calculateRadius();
    this.pointsDirty = true;
  }
  
  public Shape transform(Transform transform)
  {
    checkPoints();
    Polygon resultPolygon = new Polygon();
    float[] result = new float[this.points.length];
    transform.transform(this.points, 0, result, 0, this.points.length / 2);
    resultPolygon.points = result;
    resultPolygon.findCenter();
    resultPolygon.closed = this.closed;
    return resultPolygon;
  }
  
  public void setX(float local_x)
  {
    super.setX(local_x);
    this.pointsDirty = false;
  }
  
  public void setY(float local_y)
  {
    super.setY(local_y);
    this.pointsDirty = false;
  }
  
  protected void createPoints() {}
  
  public boolean closed()
  {
    return this.closed;
  }
  
  public void setClosed(boolean closed)
  {
    this.closed = closed;
  }
  
  public Polygon copy()
  {
    float[] copyPoints = new float[this.points.length];
    System.arraycopy(this.points, 0, copyPoints, 0, copyPoints.length);
    return new Polygon(copyPoints);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.Polygon
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */