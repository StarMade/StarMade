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
    super(paramTuple3d.field_612, paramTuple3d.field_613, paramTuple3d.field_614, 0.0D);
  }
  
  public Vector4d() {}
  
  public final void set(Tuple3d paramTuple3d)
  {
    this.field_600 = paramTuple3d.field_612;
    this.field_601 = paramTuple3d.field_613;
    this.field_602 = paramTuple3d.field_614;
    this.field_603 = 0.0D;
  }
  
  public final double length()
  {
    return Math.sqrt(this.field_600 * this.field_600 + this.field_601 * this.field_601 + this.field_602 * this.field_602 + this.field_603 * this.field_603);
  }
  
  public final double lengthSquared()
  {
    return this.field_600 * this.field_600 + this.field_601 * this.field_601 + this.field_602 * this.field_602 + this.field_603 * this.field_603;
  }
  
  public final double dot(Vector4d paramVector4d)
  {
    return this.field_600 * paramVector4d.field_600 + this.field_601 * paramVector4d.field_601 + this.field_602 * paramVector4d.field_602 + this.field_603 * paramVector4d.field_603;
  }
  
  public final void normalize(Vector4d paramVector4d)
  {
    double d = 1.0D / Math.sqrt(paramVector4d.field_600 * paramVector4d.field_600 + paramVector4d.field_601 * paramVector4d.field_601 + paramVector4d.field_602 * paramVector4d.field_602 + paramVector4d.field_603 * paramVector4d.field_603);
    paramVector4d.field_600 *= d;
    paramVector4d.field_601 *= d;
    paramVector4d.field_602 *= d;
    paramVector4d.field_603 *= d;
  }
  
  public final void normalize()
  {
    double d = 1.0D / Math.sqrt(this.field_600 * this.field_600 + this.field_601 * this.field_601 + this.field_602 * this.field_602 + this.field_603 * this.field_603);
    this.field_600 *= d;
    this.field_601 *= d;
    this.field_602 *= d;
    this.field_603 *= d;
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


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Vector4d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */