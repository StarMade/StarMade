package javax.vecmath;

import java.io.Serializable;

public class Point4f
  extends Tuple4f
  implements Serializable
{
  static final long serialVersionUID = 4643134103185764459L;
  
  public Point4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    super(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public Point4f(float[] paramArrayOfFloat)
  {
    super(paramArrayOfFloat);
  }
  
  public Point4f(Point4f paramPoint4f)
  {
    super(paramPoint4f);
  }
  
  public Point4f(Point4d paramPoint4d)
  {
    super(paramPoint4d);
  }
  
  public Point4f(Tuple4f paramTuple4f)
  {
    super(paramTuple4f);
  }
  
  public Point4f(Tuple4d paramTuple4d)
  {
    super(paramTuple4d);
  }
  
  public Point4f(Tuple3f paramTuple3f)
  {
    super(paramTuple3f.x, paramTuple3f.y, paramTuple3f.z, 1.0F);
  }
  
  public Point4f() {}
  
  public final void set(Tuple3f paramTuple3f)
  {
    this.x = paramTuple3f.x;
    this.y = paramTuple3f.y;
    this.z = paramTuple3f.z;
    this.w = 1.0F;
  }
  
  public final float distanceSquared(Point4f paramPoint4f)
  {
    float f1 = this.x - paramPoint4f.x;
    float f2 = this.y - paramPoint4f.y;
    float f3 = this.z - paramPoint4f.z;
    float f4 = this.w - paramPoint4f.w;
    return f1 * f1 + f2 * f2 + f3 * f3 + f4 * f4;
  }
  
  public final float distance(Point4f paramPoint4f)
  {
    float f1 = this.x - paramPoint4f.x;
    float f2 = this.y - paramPoint4f.y;
    float f3 = this.z - paramPoint4f.z;
    float f4 = this.w - paramPoint4f.w;
    return (float)Math.sqrt(f1 * f1 + f2 * f2 + f3 * f3 + f4 * f4);
  }
  
  public final float distanceL1(Point4f paramPoint4f)
  {
    return Math.abs(this.x - paramPoint4f.x) + Math.abs(this.y - paramPoint4f.y) + Math.abs(this.z - paramPoint4f.z) + Math.abs(this.w - paramPoint4f.w);
  }
  
  public final float distanceLinf(Point4f paramPoint4f)
  {
    float f1 = Math.max(Math.abs(this.x - paramPoint4f.x), Math.abs(this.y - paramPoint4f.y));
    float f2 = Math.max(Math.abs(this.z - paramPoint4f.z), Math.abs(this.w - paramPoint4f.w));
    return Math.max(f1, f2);
  }
  
  public final void project(Point4f paramPoint4f)
  {
    float f = 1.0F / paramPoint4f.w;
    paramPoint4f.x *= f;
    paramPoint4f.y *= f;
    paramPoint4f.z *= f;
    this.w = 1.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Point4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */