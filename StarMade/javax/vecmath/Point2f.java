package javax.vecmath;

import java.io.Serializable;

public class Point2f
  extends Tuple2f
  implements Serializable
{
  static final long serialVersionUID = -4801347926528714435L;
  
  public Point2f(float paramFloat1, float paramFloat2)
  {
    super(paramFloat1, paramFloat2);
  }
  
  public Point2f(float[] paramArrayOfFloat)
  {
    super(paramArrayOfFloat);
  }
  
  public Point2f(Point2f paramPoint2f)
  {
    super(paramPoint2f);
  }
  
  public Point2f(Point2d paramPoint2d)
  {
    super(paramPoint2d);
  }
  
  public Point2f(Tuple2d paramTuple2d)
  {
    super(paramTuple2d);
  }
  
  public Point2f(Tuple2f paramTuple2f)
  {
    super(paramTuple2f);
  }
  
  public Point2f() {}
  
  public final float distanceSquared(Point2f paramPoint2f)
  {
    float f1 = this.field_577 - paramPoint2f.field_577;
    float f2 = this.field_578 - paramPoint2f.field_578;
    return f1 * f1 + f2 * f2;
  }
  
  public final float distance(Point2f paramPoint2f)
  {
    float f1 = this.field_577 - paramPoint2f.field_577;
    float f2 = this.field_578 - paramPoint2f.field_578;
    return (float)Math.sqrt(f1 * f1 + f2 * f2);
  }
  
  public final float distanceL1(Point2f paramPoint2f)
  {
    return Math.abs(this.field_577 - paramPoint2f.field_577) + Math.abs(this.field_578 - paramPoint2f.field_578);
  }
  
  public final float distanceLinf(Point2f paramPoint2f)
  {
    return Math.max(Math.abs(this.field_577 - paramPoint2f.field_577), Math.abs(this.field_578 - paramPoint2f.field_578));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Point2f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */