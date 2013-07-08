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
    super(paramTuple3f.x, paramTuple3f.y, paramTuple3f.z, 0.0F);
  }
  
  public Vector4f() {}
  
  public final void set(Tuple3f paramTuple3f)
  {
    this.x = paramTuple3f.x;
    this.y = paramTuple3f.y;
    this.z = paramTuple3f.z;
    this.w = 0.0F;
  }
  
  public final float length()
  {
    return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
  }
  
  public final float lengthSquared()
  {
    return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
  }
  
  public final float dot(Vector4f paramVector4f)
  {
    return this.x * paramVector4f.x + this.y * paramVector4f.y + this.z * paramVector4f.z + this.w * paramVector4f.w;
  }
  
  public final void normalize(Vector4f paramVector4f)
  {
    float f = (float)(1.0D / Math.sqrt(paramVector4f.x * paramVector4f.x + paramVector4f.y * paramVector4f.y + paramVector4f.z * paramVector4f.z + paramVector4f.w * paramVector4f.w));
    paramVector4f.x *= f;
    paramVector4f.y *= f;
    paramVector4f.z *= f;
    paramVector4f.w *= f;
  }
  
  public final void normalize()
  {
    float f = (float)(1.0D / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w));
    this.x *= f;
    this.y *= f;
    this.z *= f;
    this.w *= f;
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


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Vector4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */