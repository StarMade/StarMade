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
    super(paramTuple3f.field_615, paramTuple3f.field_616, paramTuple3f.field_617, 1.0F);
  }
  
  public Point4f() {}
  
  public final void set(Tuple3f paramTuple3f)
  {
    this.field_596 = paramTuple3f.field_615;
    this.field_597 = paramTuple3f.field_616;
    this.field_598 = paramTuple3f.field_617;
    this.field_599 = 1.0F;
  }
  
  public final float distanceSquared(Point4f paramPoint4f)
  {
    float f1 = this.field_596 - paramPoint4f.field_596;
    float f2 = this.field_597 - paramPoint4f.field_597;
    float f3 = this.field_598 - paramPoint4f.field_598;
    float f4 = this.field_599 - paramPoint4f.field_599;
    return f1 * f1 + f2 * f2 + f3 * f3 + f4 * f4;
  }
  
  public final float distance(Point4f paramPoint4f)
  {
    float f1 = this.field_596 - paramPoint4f.field_596;
    float f2 = this.field_597 - paramPoint4f.field_597;
    float f3 = this.field_598 - paramPoint4f.field_598;
    float f4 = this.field_599 - paramPoint4f.field_599;
    return (float)Math.sqrt(f1 * f1 + f2 * f2 + f3 * f3 + f4 * f4);
  }
  
  public final float distanceL1(Point4f paramPoint4f)
  {
    return Math.abs(this.field_596 - paramPoint4f.field_596) + Math.abs(this.field_597 - paramPoint4f.field_597) + Math.abs(this.field_598 - paramPoint4f.field_598) + Math.abs(this.field_599 - paramPoint4f.field_599);
  }
  
  public final float distanceLinf(Point4f paramPoint4f)
  {
    float f1 = Math.max(Math.abs(this.field_596 - paramPoint4f.field_596), Math.abs(this.field_597 - paramPoint4f.field_597));
    float f2 = Math.max(Math.abs(this.field_598 - paramPoint4f.field_598), Math.abs(this.field_599 - paramPoint4f.field_599));
    return Math.max(f1, f2);
  }
  
  public final void project(Point4f paramPoint4f)
  {
    float f = 1.0F / paramPoint4f.field_599;
    paramPoint4f.field_596 *= f;
    paramPoint4f.field_597 *= f;
    paramPoint4f.field_598 *= f;
    this.field_599 = 1.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Point4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */