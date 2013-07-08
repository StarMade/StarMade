package org.newdawn.slick.geom;

public class Circle
  extends Ellipse
{
  public float radius;
  
  public strictfp Circle(float centerPointX, float centerPointY, float radius)
  {
    this(centerPointX, centerPointY, radius, 50);
  }
  
  public strictfp Circle(float centerPointX, float centerPointY, float radius, int segmentCount)
  {
    super(centerPointX, centerPointY, radius, radius, segmentCount);
    this.field_1743 = (centerPointX - radius);
    this.field_1744 = (centerPointY - radius);
    this.radius = radius;
    this.boundingCircleRadius = radius;
  }
  
  public strictfp float getCenterX()
  {
    return getX() + this.radius;
  }
  
  public strictfp float getCenterY()
  {
    return getY() + this.radius;
  }
  
  public strictfp void setRadius(float radius)
  {
    if (radius != this.radius)
    {
      this.pointsDirty = true;
      this.radius = radius;
      setRadii(radius, radius);
    }
  }
  
  public strictfp float getRadius()
  {
    return this.radius;
  }
  
  public strictfp boolean intersects(Shape shape)
  {
    if ((shape instanceof Circle))
    {
      Circle other = (Circle)shape;
      float totalRad2 = getRadius() + other.getRadius();
      if (Math.abs(other.getCenterX() - getCenterX()) > totalRad2) {
        return false;
      }
      if (Math.abs(other.getCenterY() - getCenterY()) > totalRad2) {
        return false;
      }
      totalRad2 *= totalRad2;
      float local_dx = Math.abs(other.getCenterX() - getCenterX());
      float local_dy = Math.abs(other.getCenterY() - getCenterY());
      return totalRad2 >= local_dx * local_dx + local_dy * local_dy;
    }
    if ((shape instanceof Rectangle)) {
      return intersects((Rectangle)shape);
    }
    return super.intersects(shape);
  }
  
  public strictfp boolean contains(float local_x, float local_y)
  {
    return (local_x - getX()) * (local_x - getX()) + (local_y - getY()) * (local_y - getY()) < getRadius() * getRadius();
  }
  
  private strictfp boolean contains(Line line)
  {
    return (contains(line.getX1(), line.getY1())) && (contains(line.getX2(), line.getY2()));
  }
  
  protected strictfp void findCenter()
  {
    this.center = new float[2];
    this.center[0] = (this.field_1743 + this.radius);
    this.center[1] = (this.field_1744 + this.radius);
  }
  
  protected strictfp void calculateRadius()
  {
    this.boundingCircleRadius = this.radius;
  }
  
  private strictfp boolean intersects(Rectangle other)
  {
    Rectangle box = other;
    Circle circle = this;
    if (box.contains(this.field_1743 + this.radius, this.field_1744 + this.radius)) {
      return true;
    }
    float local_x1 = box.getX();
    float local_y1 = box.getY();
    float local_x2 = box.getX() + box.getWidth();
    float local_y2 = box.getY() + box.getHeight();
    Line[] lines = new Line[4];
    lines[0] = new Line(local_x1, local_y1, local_x2, local_y1);
    lines[1] = new Line(local_x2, local_y1, local_x2, local_y2);
    lines[2] = new Line(local_x2, local_y2, local_x1, local_y2);
    lines[3] = new Line(local_x1, local_y2, local_x1, local_y1);
    float local_r2 = circle.getRadius() * circle.getRadius();
    Vector2f pos = new Vector2f(circle.getCenterX(), circle.getCenterY());
    for (int local_i = 0; local_i < 4; local_i++)
    {
      float dis = lines[local_i].distanceSquared(pos);
      if (dis < local_r2) {
        return true;
      }
    }
    return false;
  }
  
  private strictfp boolean intersects(Line other)
  {
    Vector2f lineSegmentStart = new Vector2f(other.getX1(), other.getY1());
    Vector2f lineSegmentEnd = new Vector2f(other.getX2(), other.getY2());
    Vector2f circleCenter = new Vector2f(getCenterX(), getCenterY());
    Vector2f segv = lineSegmentEnd.copy().sub(lineSegmentStart);
    Vector2f ptv = circleCenter.copy().sub(lineSegmentStart);
    float segvLength = segv.length();
    float projvl = ptv.dot(segv) / segvLength;
    Vector2f closest;
    Vector2f closest;
    if (projvl < 0.0F)
    {
      closest = lineSegmentStart;
    }
    else
    {
      Vector2f closest;
      if (projvl > segvLength)
      {
        closest = lineSegmentEnd;
      }
      else
      {
        Vector2f projv = segv.copy().scale(projvl / segvLength);
        closest = lineSegmentStart.copy().add(projv);
      }
    }
    boolean projv = circleCenter.copy().sub(closest).lengthSquared() <= getRadius() * getRadius();
    return projv;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.Circle
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */