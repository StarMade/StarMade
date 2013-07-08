package javax.vecmath;

import java.io.Serializable;

public class Point3f
  extends Tuple3f
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
  
  public Point3f() {}
  
  public final float distanceSquared(Point3f paramPoint3f)
  {
    float f1 = this.field_615 - paramPoint3f.field_615;
    float f2 = this.field_616 - paramPoint3f.field_616;
    float f3 = this.field_617 - paramPoint3f.field_617;
    return f1 * f1 + f2 * f2 + f3 * f3;
  }
  
  public final float distance(Point3f paramPoint3f)
  {
    float f1 = this.field_615 - paramPoint3f.field_615;
    float f2 = this.field_616 - paramPoint3f.field_616;
    float f3 = this.field_617 - paramPoint3f.field_617;
    return (float)Math.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
  }
  
  public final float distanceL1(Point3f paramPoint3f)
  {
    return Math.abs(this.field_615 - paramPoint3f.field_615) + Math.abs(this.field_616 - paramPoint3f.field_616) + Math.abs(this.field_617 - paramPoint3f.field_617);
  }
  
  public final float distanceLinf(Point3f paramPoint3f)
  {
    float f = Math.max(Math.abs(this.field_615 - paramPoint3f.field_615), Math.abs(this.field_616 - paramPoint3f.field_616));
    return Math.max(f, Math.abs(this.field_617 - paramPoint3f.field_617));
  }
  
  public final void project(Point4f paramPoint4f)
  {
    float f = 1.0F / paramPoint4f.field_599;
    this.field_615 = (paramPoint4f.field_596 * f);
    this.field_616 = (paramPoint4f.field_597 * f);
    this.field_617 = (paramPoint4f.field_598 * f);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Point3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */