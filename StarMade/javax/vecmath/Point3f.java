package javax.vecmath;

import java.io.Serializable;

public class Point3f extends Tuple3f
  implements Serializable
{
  static final long serialVersionUID = -8689337816398030143L;

  public Point3f(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramFloat1, paramFloat2, paramFloat3);
  }

  public Point3f(float[] paramArrayOfFloat)
  {
    super(paramArrayOfFloat);
  }

  public Point3f(Point3f paramPoint3f)
  {
    super(paramPoint3f);
  }

  public Point3f(Point3d paramPoint3d)
  {
    super(paramPoint3d);
  }

  public Point3f(Tuple3f paramTuple3f)
  {
    super(paramTuple3f);
  }

  public Point3f(Tuple3d paramTuple3d)
  {
    super(paramTuple3d);
  }

  public Point3f()
  {
  }

  public final float distanceSquared(Point3f paramPoint3f)
  {
    float f1 = this.x - paramPoint3f.x;
    float f2 = this.y - paramPoint3f.y;
    float f3 = this.z - paramPoint3f.z;
    return f1 * f1 + f2 * f2 + f3 * f3;
  }

  public final float distance(Point3f paramPoint3f)
  {
    float f1 = this.x - paramPoint3f.x;
    float f2 = this.y - paramPoint3f.y;
    float f3 = this.z - paramPoint3f.z;
    return (float)Math.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
  }

  public final float distanceL1(Point3f paramPoint3f)
  {
    return Math.abs(this.x - paramPoint3f.x) + Math.abs(this.y - paramPoint3f.y) + Math.abs(this.z - paramPoint3f.z);
  }

  public final float distanceLinf(Point3f paramPoint3f)
  {
    float f = Math.max(Math.abs(this.x - paramPoint3f.x), Math.abs(this.y - paramPoint3f.y));
    return Math.max(f, Math.abs(this.z - paramPoint3f.z));
  }

  public final void project(Point4f paramPoint4f)
  {
    float f = 1.0F / paramPoint4f.w;
    this.x = (paramPoint4f.x * f);
    this.y = (paramPoint4f.y * f);
    this.z = (paramPoint4f.z * f);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Point3f
 * JD-Core Version:    0.6.2
 */