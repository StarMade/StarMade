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
    super(paramTuple3d.x, paramTuple3d.y, paramTuple3d.z, 1.0D);
  }
  
  public Point4d() {}
  
  public final void set(Tuple3d paramTuple3d)
  {
    this.x = paramTuple3d.x;
    this.y = paramTuple3d.y;
    this.z = paramTuple3d.z;
    this.w = 1.0D;
  }
  
  public final double distanceSquared(Point4d paramPoint4d)
  {
    double d1 = this.x - paramPoint4d.x;
    double d2 = this.y - paramPoint4d.y;
    double d3 = this.z - paramPoint4d.z;
    double d4 = this.w - paramPoint4d.w;
    return d1 * d1 + d2 * d2 + d3 * d3 + d4 * d4;
  }
  
  public final double distance(Point4d paramPoint4d)
  {
    double d1 = this.x - paramPoint4d.x;
    double d2 = this.y - paramPoint4d.y;
    double d3 = this.z - paramPoint4d.z;
    double d4 = this.w - paramPoint4d.w;
    return Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3 + d4 * d4);
  }
  
  public final double distanceL1(Point4d paramPoint4d)
  {
    return Math.abs(this.x - paramPoint4d.x) + Math.abs(this.y - paramPoint4d.y) + Math.abs(this.z - paramPoint4d.z) + Math.abs(this.w - paramPoint4d.w);
  }
  
  public final double distanceLinf(Point4d paramPoint4d)
  {
    double d1 = Math.max(Math.abs(this.x - paramPoint4d.x), Math.abs(this.y - paramPoint4d.y));
    double d2 = Math.max(Math.abs(this.z - paramPoint4d.z), Math.abs(this.w - paramPoint4d.w));
    return Math.max(d1, d2);
  }
  
  public final void project(Point4d paramPoint4d)
  {
    double d = 1.0D / paramPoint4d.w;
    paramPoint4d.x *= d;
    paramPoint4d.y *= d;
    paramPoint4d.z *= d;
    this.w = 1.0D;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Point4d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */