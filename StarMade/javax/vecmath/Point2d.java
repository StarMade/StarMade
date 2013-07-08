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
    double d1 = this.field_580 - paramPoint2d.field_580;
    double d2 = this.field_581 - paramPoint2d.field_581;
    return d1 * d1 + d2 * d2;
  }
  
  public final double distance(Point2d paramPoint2d)
  {
    double d1 = this.field_580 - paramPoint2d.field_580;
    double d2 = this.field_581 - paramPoint2d.field_581;
    return Math.sqrt(d1 * d1 + d2 * d2);
  }
  
  public final double distanceL1(Point2d paramPoint2d)
  {
    return Math.abs(this.field_580 - paramPoint2d.field_580) + Math.abs(this.field_581 - paramPoint2d.field_581);
  }
  
  public final double distanceLinf(Point2d paramPoint2d)
  {
    return Math.max(Math.abs(this.field_580 - paramPoint2d.field_580), Math.abs(this.field_581 - paramPoint2d.field_581));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Point2d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */