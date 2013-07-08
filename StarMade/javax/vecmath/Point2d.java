package javax.vecmath;

import java.io.Serializable;

public class Point2d
  extends Tuple2d
  implements Serializable
{
  static final long serialVersionUID = 1133748791492571954L;
  
  public Point2d(double paramDouble1, double paramDouble2)
  {
    super(paramDouble1, paramDouble2);
  }
  
  public Point2d(double[] paramArrayOfDouble)
  {
    super(paramArrayOfDouble);
  }
  
  public Point2d(Point2d paramPoint2d)
  {
    super(paramPoint2d);
  }
  
  public Point2d(Point2f paramPoint2f)
  {
    super(paramPoint2f);
  }
  
  public Point2d(Tuple2d paramTuple2d)
  {
    super(paramTuple2d);
  }
  
  public Point2d(Tuple2f paramTuple2f)
  {
    super(paramTuple2f);
  }
  
  public Point2d() {}
  
  public final double distanceSquared(Point2d paramPoint2d)
  {
    double d1 = this.x - paramPoint2d.x;
    double d2 = this.y - paramPoint2d.y;
    return d1 * d1 + d2 * d2;
  }
  
  public final double distance(Point2d paramPoint2d)
  {
    double d1 = this.x - paramPoint2d.x;
    double d2 = this.y - paramPoint2d.y;
    return Math.sqrt(d1 * d1 + d2 * d2);
  }
  
  public final double distanceL1(Point2d paramPoint2d)
  {
    return Math.abs(this.x - paramPoint2d.x) + Math.abs(this.y - paramPoint2d.y);
  }
  
  public final double distanceLinf(Point2d paramPoint2d)
  {
    return Math.max(Math.abs(this.x - paramPoint2d.x), Math.abs(this.y - paramPoint2d.y));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Point2d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */