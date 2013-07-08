package org.newdawn.slick.geom;

import java.io.Serializable;

public abstract class Shape
  implements Serializable
{
  protected float[] points;
  protected float[] center;
  protected float field_1743;
  protected float field_1744;
  protected float maxX;
  protected float maxY;
  protected float minX;
  protected float minY;
  protected float boundingCircleRadius;
  protected boolean pointsDirty = true;
  protected transient Triangulator tris;
  protected boolean trianglesDirty;
  
  public void setLocation(float local_x, float local_y)
  {
    setX(local_x);
    setY(local_y);
  }
  
  public abstract Shape transform(Transform paramTransform);
  
  protected abstract void createPoints();
  
  public float getX()
  {
    return this.field_1743;
  }
  
  public void setX(float local_x)
  {
    if (local_x != this.field_1743)
    {
      float local_dx = local_x - this.field_1743;
      this.field_1743 = local_x;
      if ((this.points == null) || (this.center == null)) {
        checkPoints();
      }
      for (int local_i = 0; local_i < this.points.length / 2; local_i++) {
        this.points[(local_i * 2)] += local_dx;
      }
      this.center[0] += local_dx;
      local_x += local_dx;
      this.maxX += local_dx;
      this.minX += local_dx;
      this.trianglesDirty = true;
    }
  }
  
  public void setY(float local_y)
  {
    if (local_y != this.field_1744)
    {
      float local_dy = local_y - this.field_1744;
      this.field_1744 = local_y;
      if ((this.points == null) || (this.center == null)) {
        checkPoints();
      }
      for (int local_i = 0; local_i < this.points.length / 2; local_i++) {
        this.points[(local_i * 2 + 1)] += local_dy;
      }
      this.center[1] += local_dy;
      local_y += local_dy;
      this.maxY += local_dy;
      this.minY += local_dy;
      this.trianglesDirty = true;
    }
  }
  
  public float getY()
  {
    return this.field_1744;
  }
  
  public void setLocation(Vector2f loc)
  {
    setX(loc.field_1680);
    setY(loc.field_1681);
  }
  
  public float getCenterX()
  {
    checkPoints();
    return this.center[0];
  }
  
  public void setCenterX(float centerX)
  {
    if ((this.points == null) || (this.center == null)) {
      checkPoints();
    }
    float xDiff = centerX - getCenterX();
    setX(this.field_1743 + xDiff);
  }
  
  public float getCenterY()
  {
    checkPoints();
    return this.center[1];
  }
  
  public void setCenterY(float centerY)
  {
    if ((this.points == null) || (this.center == null)) {
      checkPoints();
    }
    float yDiff = centerY - getCenterY();
    setY(this.field_1744 + yDiff);
  }
  
  public float getMaxX()
  {
    checkPoints();
    return this.maxX;
  }
  
  public float getMaxY()
  {
    checkPoints();
    return this.maxY;
  }
  
  public float getMinX()
  {
    checkPoints();
    return this.minX;
  }
  
  public float getMinY()
  {
    checkPoints();
    return this.minY;
  }
  
  public float getBoundingCircleRadius()
  {
    checkPoints();
    return this.boundingCircleRadius;
  }
  
  public float[] getCenter()
  {
    checkPoints();
    return this.center;
  }
  
  public float[] getPoints()
  {
    checkPoints();
    return this.points;
  }
  
  public int getPointCount()
  {
    checkPoints();
    return this.points.length / 2;
  }
  
  public float[] getPoint(int index)
  {
    checkPoints();
    float[] result = new float[2];
    result[0] = this.points[(index * 2)];
    result[1] = this.points[(index * 2 + 1)];
    return result;
  }
  
  public float[] getNormal(int index)
  {
    float[] current = getPoint(index);
    float[] prev = getPoint(index - 1 < 0 ? getPointCount() - 1 : index - 1);
    float[] next = getPoint(index + 1 >= getPointCount() ? 0 : index + 1);
    float[] local_t1 = getNormal(prev, current);
    float[] local_t2 = getNormal(current, next);
    if ((index == 0) && (!closed())) {
      return local_t2;
    }
    if ((index == getPointCount() - 1) && (!closed())) {
      return local_t1;
    }
    float local_tx = (local_t1[0] + local_t2[0]) / 2.0F;
    float local_ty = (local_t1[1] + local_t2[1]) / 2.0F;
    float len = (float)Math.sqrt(local_tx * local_tx + local_ty * local_ty);
    return new float[] { local_tx / len, local_ty / len };
  }
  
  public boolean contains(Shape other)
  {
    if (other.intersects(this)) {
      return false;
    }
    for (int local_i = 0; local_i < other.getPointCount(); local_i++)
    {
      float[] local_pt = other.getPoint(local_i);
      if (!contains(local_pt[0], local_pt[1])) {
        return false;
      }
    }
    return true;
  }
  
  private float[] getNormal(float[] start, float[] end)
  {
    float local_dx = start[0] - end[0];
    float local_dy = start[1] - end[1];
    float len = (float)Math.sqrt(local_dx * local_dx + local_dy * local_dy);
    local_dx /= len;
    local_dy /= len;
    return new float[] { -local_dy, local_dx };
  }
  
  public boolean includes(float local_x, float local_y)
  {
    if (this.points.length == 0) {
      return false;
    }
    checkPoints();
    Line testLine = new Line(0.0F, 0.0F, 0.0F, 0.0F);
    Vector2f local_pt = new Vector2f(local_x, local_y);
    for (int local_i = 0; local_i < this.points.length; local_i += 2)
    {
      int local_n = local_i + 2;
      if (local_n >= this.points.length) {
        local_n = 0;
      }
      testLine.set(this.points[local_i], this.points[(local_i + 1)], this.points[local_n], this.points[(local_n + 1)]);
      if (testLine.on(local_pt)) {
        return true;
      }
    }
    return false;
  }
  
  public int indexOf(float local_x, float local_y)
  {
    for (int local_i = 0; local_i < this.points.length; local_i += 2) {
      if ((this.points[local_i] == local_x) && (this.points[(local_i + 1)] == local_y)) {
        return local_i / 2;
      }
    }
    return -1;
  }
  
  public boolean contains(float local_x, float local_y)
  {
    checkPoints();
    if (this.points.length == 0) {
      return false;
    }
    boolean result = false;
    int npoints = this.points.length;
    float xold = this.points[(npoints - 2)];
    float yold = this.points[(npoints - 1)];
    for (int local_i = 0; local_i < npoints; local_i += 2)
    {
      float xnew = this.points[local_i];
      float ynew = this.points[(local_i + 1)];
      float local_y2;
      float local_x1;
      float local_x2;
      float local_y1;
      float local_y2;
      if (xnew > xold)
      {
        float local_x1 = xold;
        float local_x2 = xnew;
        float local_y1 = yold;
        local_y2 = ynew;
      }
      else
      {
        local_x1 = xnew;
        local_x2 = xold;
        local_y1 = ynew;
        local_y2 = yold;
      }
      if ((xnew < local_x ? 1 : 0) == (local_x <= xold ? 1 : 0)) {
        if ((local_y - local_y1) * (local_x2 - local_x1) < (local_y2 - local_y1) * (local_x - local_x1)) {
          result = !result;
        }
      }
      xold = xnew;
      yold = ynew;
    }
    return result;
  }
  
  public boolean intersects(Shape shape)
  {
    checkPoints();
    boolean result = false;
    float[] points = getPoints();
    float[] thatPoints = shape.getPoints();
    int length = points.length;
    int thatLength = thatPoints.length;
    if (!closed()) {
      length -= 2;
    }
    if (!shape.closed()) {
      thatLength -= 2;
    }
    for (int local_i = 0; local_i < length; local_i += 2)
    {
      int iNext = local_i + 2;
      if (iNext >= points.length) {
        iNext = 0;
      }
      for (int local_j = 0; local_j < thatLength; local_j += 2)
      {
        int jNext = local_j + 2;
        if (jNext >= thatPoints.length) {
          jNext = 0;
        }
        double unknownA = ((points[iNext] - points[local_i]) * (thatPoints[(local_j + 1)] - points[(local_i + 1)]) - (points[(iNext + 1)] - points[(local_i + 1)]) * (thatPoints[local_j] - points[local_i])) / ((points[(iNext + 1)] - points[(local_i + 1)]) * (thatPoints[jNext] - thatPoints[local_j]) - (points[iNext] - points[local_i]) * (thatPoints[(jNext + 1)] - thatPoints[(local_j + 1)]));
        double unknownB = ((thatPoints[jNext] - thatPoints[local_j]) * (thatPoints[(local_j + 1)] - points[(local_i + 1)]) - (thatPoints[(jNext + 1)] - thatPoints[(local_j + 1)]) * (thatPoints[local_j] - points[local_i])) / ((points[(iNext + 1)] - points[(local_i + 1)]) * (thatPoints[jNext] - thatPoints[local_j]) - (points[iNext] - points[local_i]) * (thatPoints[(jNext + 1)] - thatPoints[(local_j + 1)]));
        if ((unknownA >= 0.0D) && (unknownA <= 1.0D) && (unknownB >= 0.0D) && (unknownB <= 1.0D))
        {
          result = true;
          break;
        }
      }
      if (result) {
        break;
      }
    }
    return result;
  }
  
  public boolean hasVertex(float local_x, float local_y)
  {
    if (this.points.length == 0) {
      return false;
    }
    checkPoints();
    for (int local_i = 0; local_i < this.points.length; local_i += 2) {
      if ((this.points[local_i] == local_x) && (this.points[(local_i + 1)] == local_y)) {
        return true;
      }
    }
    return false;
  }
  
  protected void findCenter()
  {
    this.center = new float[] { 0.0F, 0.0F };
    int length = this.points.length;
    for (int local_i = 0; local_i < length; local_i += 2)
    {
      this.center[0] += this.points[local_i];
      this.center[1] += this.points[(local_i + 1)];
    }
    this.center[0] /= length / 2;
    this.center[1] /= length / 2;
  }
  
  protected void calculateRadius()
  {
    this.boundingCircleRadius = 0.0F;
    for (int local_i = 0; local_i < this.points.length; local_i += 2)
    {
      float temp = (this.points[local_i] - this.center[0]) * (this.points[local_i] - this.center[0]) + (this.points[(local_i + 1)] - this.center[1]) * (this.points[(local_i + 1)] - this.center[1]);
      this.boundingCircleRadius = (this.boundingCircleRadius > temp ? this.boundingCircleRadius : temp);
    }
    this.boundingCircleRadius = ((float)Math.sqrt(this.boundingCircleRadius));
  }
  
  protected void calculateTriangles()
  {
    if ((!this.trianglesDirty) && (this.tris != null)) {
      return;
    }
    if (this.points.length >= 6)
    {
      boolean clockwise = true;
      float area = 0.0F;
      for (int local_i = 0; local_i < this.points.length / 2 - 1; local_i++)
      {
        float local_x1 = this.points[(local_i * 2)];
        float local_y1 = this.points[(local_i * 2 + 1)];
        float local_x2 = this.points[(local_i * 2 + 2)];
        float local_y2 = this.points[(local_i * 2 + 3)];
        area += local_x1 * local_y2 - local_y1 * local_x2;
      }
      area /= 2.0F;
      clockwise = area > 0.0F;
      this.tris = new NeatTriangulator();
      for (int local_i = 0; local_i < this.points.length; local_i += 2) {
        this.tris.addPolyPoint(this.points[local_i], this.points[(local_i + 1)]);
      }
      this.tris.triangulate();
    }
    this.trianglesDirty = false;
  }
  
  public void increaseTriangulation()
  {
    checkPoints();
    calculateTriangles();
    this.tris = new OverTriangulator(this.tris);
  }
  
  public Triangulator getTriangles()
  {
    checkPoints();
    calculateTriangles();
    return this.tris;
  }
  
  protected final void checkPoints()
  {
    if (this.pointsDirty)
    {
      createPoints();
      findCenter();
      calculateRadius();
      if (this.points.length > 0)
      {
        this.maxX = this.points[0];
        this.maxY = this.points[1];
        this.minX = this.points[0];
        this.minY = this.points[1];
        for (int local_i = 0; local_i < this.points.length / 2; local_i++)
        {
          this.maxX = Math.max(this.points[(local_i * 2)], this.maxX);
          this.maxY = Math.max(this.points[(local_i * 2 + 1)], this.maxY);
          this.minX = Math.min(this.points[(local_i * 2)], this.minX);
          this.minY = Math.min(this.points[(local_i * 2 + 1)], this.minY);
        }
      }
      this.pointsDirty = false;
      this.trianglesDirty = true;
    }
  }
  
  public void preCache()
  {
    checkPoints();
    getTriangles();
  }
  
  public boolean closed()
  {
    return true;
  }
  
  public Shape prune()
  {
    Polygon result = new Polygon();
    for (int local_i = 0; local_i < getPointCount(); local_i++)
    {
      int next = local_i + 1 >= getPointCount() ? 0 : local_i + 1;
      int prev = local_i - 1 < 0 ? getPointCount() - 1 : local_i - 1;
      float dx1 = getPoint(local_i)[0] - getPoint(prev)[0];
      float dy1 = getPoint(local_i)[1] - getPoint(prev)[1];
      float dx2 = getPoint(next)[0] - getPoint(local_i)[0];
      float dy2 = getPoint(next)[1] - getPoint(local_i)[1];
      float len1 = (float)Math.sqrt(dx1 * dx1 + dy1 * dy1);
      float len2 = (float)Math.sqrt(dx2 * dx2 + dy2 * dy2);
      dx1 /= len1;
      dy1 /= len1;
      dx2 /= len2;
      dy2 /= len2;
      if ((dx1 != dx2) || (dy1 != dy2)) {
        result.addPoint(getPoint(local_i)[0], getPoint(local_i)[1]);
      }
    }
    return result;
  }
  
  public Shape[] subtract(Shape other)
  {
    return new GeomUtil().subtract(this, other);
  }
  
  public Shape[] union(Shape other)
  {
    return new GeomUtil().union(this, other);
  }
  
  public float getWidth()
  {
    return this.maxX - this.minX;
  }
  
  public float getHeight()
  {
    return this.maxY - this.minY;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.Shape
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */