package org.newdawn.slick.geom;

public class Point
  extends Shape
{
  public Point(float local_x, float local_y)
  {
    this.field_1743 = local_x;
    this.field_1744 = local_y;
    checkPoints();
  }
  
  public Shape transform(Transform transform)
  {
    float[] result = new float[this.points.length];
    transform.transform(this.points, 0, result, 0, this.points.length / 2);
    return new Point(this.points[0], this.points[1]);
  }
  
  protected void createPoints()
  {
    this.points = new float[2];
    this.points[0] = getX();
    this.points[1] = getY();
    this.maxX = this.field_1743;
    this.maxY = this.field_1744;
    this.minX = this.field_1743;
    this.minY = this.field_1744;
    findCenter();
    calculateRadius();
  }
  
  protected void findCenter()
  {
    this.center = new float[2];
    this.center[0] = this.points[0];
    this.center[1] = this.points[1];
  }
  
  protected void calculateRadius()
  {
    this.boundingCircleRadius = 0.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.newdawn.slick.geom.Point
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */