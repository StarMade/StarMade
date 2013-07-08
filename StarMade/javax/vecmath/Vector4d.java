package javax.vecmath;

import java.io.Serializable;

public class Vector4d
  extends Tuple4d
  implements Serializable
{
  static final long serialVersionUID = 3938123424117448700L;
  
  public Vector4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    super(paramDouble1, paramDouble2, paramDouble3, paramDouble4);
  }
  
  public Vector4d(double[] paramArrayOfDouble)
  {
    super(paramArrayOfDouble);
  }
  
  public Vector4d(Vector4d paramVector4d)
  {
    super(paramVector4d);
  }
  
  public Vector4d(Vector4f paramVector4f)
  {
    super(paramVector4f);
  }
  
  public Vector4d(Tuple4f paramTuple4f)
  {
    super(paramTuple4f);
  }
  
  public Vector4d(Tuple4d paramTuple4d)
  {
    super(paramTuple4d);
  }
  
  public Vector4d(Tuple3d paramTuple3d)
  {
    super(paramTuple3d.x, paramTuple3d.y, paramTuple3d.z, 0.0D);
  }
  
  public Vector4d() {}
  
  public final void set(Tuple3d paramTuple3d)
  {
    this.x = paramTuple3d.x;
    this.y = paramTuple3d.y;
    this.z = paramTuple3d.z;
    this.w = 0.0D;
  }
  
  public final double length()
  {
    return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
  }
  
  public final double lengthSquared()
  {
    return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
  }
  
  public final double dot(Vector4d paramVector4d)
  {
    return this.x * paramVector4d.x + this.y * paramVector4d.y + this.z * paramVector4d.z + this.w * paramVector4d.w;
  }
  
  public final void normalize(Vector4d paramVector4d)
  {
    double d = 1.0D / Math.sqrt(paramVector4d.x * paramVector4d.x + paramVector4d.y * paramVector4d.y + paramVector4d.z * paramVector4d.z + paramVector4d.w * paramVector4d.w);
    paramVector4d.x *= d;
    paramVector4d.y *= d;
    paramVector4d.z *= d;
    paramVector4d.w *= d;
  }
  
  public final void normalize()
  {
    double d = 1.0D / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    this.x *= d;
    this.y *= d;
    this.z *= d;
    this.w *= d;
  }
  
  public final double angle(Vector4d paramVector4d)
  {
    double d = dot(paramVector4d) / (length() * paramVector4d.length());
    if (d < -1.0D) {
      d = -1.0D;
    }
    if (d > 1.0D) {
      d = 1.0D;
    }
    return Math.acos(d);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Vector4d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */