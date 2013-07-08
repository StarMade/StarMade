package javax.vecmath;

import java.io.Serializable;

public class Vector2f
  extends Tuple2f
  implements Serializable
{
  static final long serialVersionUID = -2168194326883512320L;
  
  public Vector2f(float paramFloat1, float paramFloat2)
  {
    super(paramFloat1, paramFloat2);
  }
  
  public Vector2f(float[] paramArrayOfFloat)
  {
    super(paramArrayOfFloat);
  }
  
  public Vector2f(Vector2f paramVector2f)
  {
    super(paramVector2f);
  }
  
  public Vector2f(Vector2d paramVector2d)
  {
    super(paramVector2d);
  }
  
  public Vector2f(Tuple2f paramTuple2f)
  {
    super(paramTuple2f);
  }
  
  public Vector2f(Tuple2d paramTuple2d)
  {
    super(paramTuple2d);
  }
  
  public Vector2f() {}
  
  public final float dot(Vector2f paramVector2f)
  {
    return this.field_577 * paramVector2f.field_577 + this.field_578 * paramVector2f.field_578;
  }
  
  public final float length()
  {
    return (float)Math.sqrt(this.field_577 * this.field_577 + this.field_578 * this.field_578);
  }
  
  public final float lengthSquared()
  {
    return this.field_577 * this.field_577 + this.field_578 * this.field_578;
  }
  
  public final void normalize(Vector2f paramVector2f)
  {
    float f = (float)(1.0D / Math.sqrt(paramVector2f.field_577 * paramVector2f.field_577 + paramVector2f.field_578 * paramVector2f.field_578));
    paramVector2f.field_577 *= f;
    paramVector2f.field_578 *= f;
  }
  
  public final void normalize()
  {
    float f = (float)(1.0D / Math.sqrt(this.field_577 * this.field_577 + this.field_578 * this.field_578));
    this.field_577 *= f;
    this.field_578 *= f;
  }
  
  public final float angle(Vector2f paramVector2f)
  {
    double d = dot(paramVector2f) / (length() * paramVector2f.length());
    if (d < -1.0D) {
      d = -1.0D;
    }
    if (d > 1.0D) {
      d = 1.0D;
    }
    return (float)Math.acos(d);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Vector2f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */