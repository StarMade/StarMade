package javax.vecmath;

import java.io.Serializable;

public class Vector2f extends Tuple2f
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

  public Vector2f()
  {
  }

  public final float dot(Vector2f paramVector2f)
  {
    return this.x * paramVector2f.x + this.y * paramVector2f.y;
  }

  public final float length()
  {
    return (float)Math.sqrt(this.x * this.x + this.y * this.y);
  }

  public final float lengthSquared()
  {
    return this.x * this.x + this.y * this.y;
  }

  public final void normalize(Vector2f paramVector2f)
  {
    float f = (float)(1.0D / Math.sqrt(paramVector2f.x * paramVector2f.x + paramVector2f.y * paramVector2f.y));
    paramVector2f.x *= f;
    paramVector2f.y *= f;
  }

  public final void normalize()
  {
    float f = (float)(1.0D / Math.sqrt(this.x * this.x + this.y * this.y));
    this.x *= f;
    this.y *= f;
  }

  public final float angle(Vector2f paramVector2f)
  {
    double d = dot(paramVector2f) / (length() * paramVector2f.length());
    if (d < -1.0D)
      d = -1.0D;
    if (d > 1.0D)
      d = 1.0D;
    return (float)Math.acos(d);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Vector2f
 * JD-Core Version:    0.6.2
 */