package javax.vecmath;

import java.io.Serializable;

public class Point3d
  extends Tuple3d
  implements Serializable
{
  static final long serialVersionUID = 5718062286069042927L;
  
  public Point3d(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    super(paramDouble1, paramDouble2, paramDouble3);
  }
  
  public Point3d(double[] paramArrayOfDouble)
  {
    super(paramArrayOfDouble);
  }
  
  public Point3d(Point3d paramPoint3d)
  {
    super(paramPoint3d);
  }
  
  public Point3d(Point3f paramPoint3f)
  {
    super(paramPoint3f);
  }
  
  public Point3d(Tuple3f paramTuple3f)
  {
    super(paramTuple3f);
  }
  
  public Point3d(Tuple3d paramTuple3d)
  {
    super(paramTuple3d);
  }
  
  public Point3d() {}
  
  public final double distanceSquared(Point3d paramPoint3d)
  {
    double d1 = this.field_612 - paramPoint3d.field_612;
    double d2 = this.field_613 - paramPoint3d.field_613;
    double d3 = this.field_614 - paramPoint3d.field_614;
    return d1 * d1 + d2 * d2 + d3 * d3;
  }
  
  public final double distance(Point3d paramPoint3d)
  {
    double d1 = this.field_612 - paramPoint3d.field_612;
    double d2 = this.field_613 - paramPoint3d.field_613;
    double d3 = this.field_614 - paramPoint3d.field_614;
    return Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
  }
  
  public final double distanceL1(Point3d paramPoint3d)
  {
    return Math.abs(this.field_612 - paramPoint3d.field_612) + Math.abs(this.field_613 - paramPoint3d.field_613) + Math.abs(this.field_614 - paramPoint3d.field_614);
  }
  
  public final double distanceLinf(Point3d paramPoint3d)
  {
    double d = Math.max(Math.abs(this.field_612 - paramPoint3d.field_612), Math.abs(this.field_613 - paramPoint3d.field_613));
    return Math.max(d, Math.abs(this.field_614 - paramPoint3d.field_614));
  }
  
  public final void project(Point4d paramPoint4d)
  {
    double d = 1.0D / paramPoint4d.field_603;
    this.field_612 = (paramPoint4d.field_600 * d);
    this.field_613 = (paramPoint4d.field_601 * d);
    this.field_614 = (paramPoint4d.field_602 * d);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Point3d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */