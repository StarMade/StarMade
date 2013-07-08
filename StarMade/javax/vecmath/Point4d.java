package javax.vecmath;

import java.io.Serializable;

public class Point4d
  extends Tuple4d
  implements Serializable
{
  static final long serialVersionUID = 1733471895962736949L;
  
  public Point4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    super(paramDouble1, paramDouble2, paramDouble3, paramDouble4);
  }
  
  public Point4d(double[] paramArrayOfDouble)
  {
    super(paramArrayOfDouble);
  }
  
  public Point4d(Point4d paramPoint4d)
  {
    super(paramPoint4d);
  }
  
  public Point4d(Point4f paramPoint4f)
  {
    super(paramPoint4f);
  }
  
  public Point4d(Tuple4f paramTuple4f)
  {
    super(paramTuple4f);
  }
  
  public Point4d(Tuple4d paramTuple4d)
  {
    super(paramTuple4d);
  }
  
  public Point4d(Tuple3d paramTuple3d)
  {
    super(paramTuple3d.field_612, paramTuple3d.field_613, paramTuple3d.field_614, 1.0D);
  }
  
  public Point4d() {}
  
  public final void set(Tuple3d paramTuple3d)
  {
    this.field_600 = paramTuple3d.field_612;
    this.field_601 = paramTuple3d.field_613;
    this.field_602 = paramTuple3d.field_614;
    this.field_603 = 1.0D;
  }
  
  public final double distanceSquared(Point4d paramPoint4d)
  {
    double d1 = this.field_600 - paramPoint4d.field_600;
    double d2 = this.field_601 - paramPoint4d.field_601;
    double d3 = this.field_602 - paramPoint4d.field_602;
    double d4 = this.field_603 - paramPoint4d.field_603;
    return d1 * d1 + d2 * d2 + d3 * d3 + d4 * d4;
  }
  
  public final double distance(Point4d paramPoint4d)
  {
    double d1 = this.field_600 - paramPoint4d.field_600;
    double d2 = this.field_601 - paramPoint4d.field_601;
    double d3 = this.field_602 - paramPoint4d.field_602;
    double d4 = this.field_603 - paramPoint4d.field_603;
    return Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3 + d4 * d4);
  }
  
  public final double distanceL1(Point4d paramPoint4d)
  {
    return Math.abs(this.field_600 - paramPoint4d.field_600) + Math.abs(this.field_601 - paramPoint4d.field_601) + Math.abs(this.field_602 - paramPoint4d.field_602) + Math.abs(this.field_603 - paramPoint4d.field_603);
  }
  
  public final double distanceLinf(Point4d paramPoint4d)
  {
    double d1 = Math.max(Math.abs(this.field_600 - paramPoint4d.field_600), Math.abs(this.field_601 - paramPoint4d.field_601));
    double d2 = Math.max(Math.abs(this.field_602 - paramPoint4d.field_602), Math.abs(this.field_603 - paramPoint4d.field_603));
    return Math.max(d1, d2);
  }
  
  public final void project(Point4d paramPoint4d)
  {
    double d = 1.0D / paramPoint4d.field_603;
    paramPoint4d.field_600 *= d;
    paramPoint4d.field_601 *= d;
    paramPoint4d.field_602 *= d;
    this.field_603 = 1.0D;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Point4d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */