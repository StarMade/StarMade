package javax.vecmath;

import java.io.Serializable;

public class Vector4f
  extends Tuple4f
  implements Serializable
{
  static final long serialVersionUID = 8749319902347760659L;
  
  public Vector4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    super(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public Vector4f(float[] paramArrayOfFloat)
  {
    super(paramArrayOfFloat);
  }
  
  public Vector4f(Vector4f paramVector4f)
  {
    super(paramVector4f);
  }
  
  public Vector4f(Vector4d paramVector4d)
  {
    super(paramVector4d);
  }
  
  public Vector4f(Tuple4f paramTuple4f)
  {
    super(paramTuple4f);
  }
  
  public Vector4f(Tuple4d paramTuple4d)
  {
    super(paramTuple4d);
  }
  
  public Vector4f(Tuple3f paramTuple3f)
  {
    super(paramTuple3f.field_615, paramTuple3f.field_616, paramTuple3f.field_617, 0.0F);
  }
  
  public Vector4f() {}
  
  public final void set(Tuple3f paramTuple3f)
  {
    this.field_596 = paramTuple3f.field_615;
    this.field_597 = paramTuple3f.field_616;
    this.field_598 = paramTuple3f.field_617;
    this.field_599 = 0.0F;
  }
  
  public final float length()
  {
    return (float)Math.sqrt(this.field_596 * this.field_596 + this.field_597 * this.field_597 + this.field_598 * this.field_598 + this.field_599 * this.field_599);
  }
  
  public final float lengthSquared()
  {
    return this.field_596 * this.field_596 + this.field_597 * this.field_597 + this.field_598 * this.field_598 + this.field_599 * this.field_599;
  }
  
  public final float dot(Vector4f paramVector4f)
  {
    return this.field_596 * paramVector4f.field_596 + this.field_597 * paramVector4f.field_597 + this.field_598 * paramVector4f.field_598 + this.field_599 * paramVector4f.field_599;
  }
  
  public final void normalize(Vector4f paramVector4f)
  {
    float f = (float)(1.0D / Math.sqrt(paramVector4f.field_596 * paramVector4f.field_596 + paramVector4f.field_597 * paramVector4f.field_597 + paramVector4f.field_598 * paramVector4f.field_598 + paramVector4f.field_599 * paramVector4f.field_599));
    paramVector4f.field_596 *= f;
    paramVector4f.field_597 *= f;
    paramVector4f.field_598 *= f;
    paramVector4f.field_599 *= f;
  }
  
  public final void normalize()
  {
    float f = (float)(1.0D / Math.sqrt(this.field_596 * this.field_596 + this.field_597 * this.field_597 + this.field_598 * this.field_598 + this.field_599 * this.field_599));
    this.field_596 *= f;
    this.field_597 *= f;
    this.field_598 *= f;
    this.field_599 *= f;
  }
  
  public final float angle(Vector4f paramVector4f)
  {
    double d = dot(paramVector4f) / (length() * paramVector4f.length());
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
 * Qualified Name:     javax.vecmath.Vector4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */