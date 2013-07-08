package javax.vecmath;

import java.io.Serializable;

public class Vector3d
  extends Tuple3d
  implements Serializable
{
  static final long serialVersionUID = 3761969948420550442L;
  
  public Vector3d(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    super(paramDouble1, paramDouble2, paramDouble3);
  }
  
  public Vector3d(double[] paramArrayOfDouble)
  {
    super(paramArrayOfDouble);
  }
  
  public Vector3d(Vector3d paramVector3d)
  {
    super(paramVector3d);
  }
  
  public Vector3d(Vector3f paramVector3f)
  {
    super(paramVector3f);
  }
  
  public Vector3d(Tuple3f paramTuple3f)
  {
    super(paramTuple3f);
  }
  
  public Vector3d(Tuple3d paramTuple3d)
  {
    super(paramTuple3d);
  }
  
  public Vector3d() {}
  
  public final void cross(Vector3d paramVector3d1, Vector3d paramVector3d2)
  {
    double d1 = paramVector3d1.field_613 * paramVector3d2.field_614 - paramVector3d1.field_614 * paramVector3d2.field_613;
    double d2 = paramVector3d2.field_612 * paramVector3d1.field_614 - paramVector3d2.field_614 * paramVector3d1.field_612;
    this.field_614 = (paramVector3d1.field_612 * paramVector3d2.field_613 - paramVector3d1.field_613 * paramVector3d2.field_612);
    this.field_612 = d1;
    this.field_613 = d2;
  }
  
  public final void normalize(Vector3d paramVector3d)
  {
    double d = 1.0D / Math.sqrt(paramVector3d.field_612 * paramVector3d.field_612 + paramVector3d.field_613 * paramVector3d.field_613 + paramVector3d.field_614 * paramVector3d.field_614);
    paramVector3d.field_612 *= d;
    paramVector3d.field_613 *= d;
    paramVector3d.field_614 *= d;
  }
  
  public final void normalize()
  {
    double d = 1.0D / Math.sqrt(this.field_612 * this.field_612 + this.field_613 * this.field_613 + this.field_614 * this.field_614);
    this.field_612 *= d;
    this.field_613 *= d;
    this.field_614 *= d;
  }
  
  public final double dot(Vector3d paramVector3d)
  {
    return this.field_612 * paramVector3d.field_612 + this.field_613 * paramVector3d.field_613 + this.field_614 * paramVector3d.field_614;
  }
  
  public final double lengthSquared()
  {
    return this.field_612 * this.field_612 + this.field_613 * this.field_613 + this.field_614 * this.field_614;
  }
  
  public final double length()
  {
    return Math.sqrt(this.field_612 * this.field_612 + this.field_613 * this.field_613 + this.field_614 * this.field_614);
  }
  
  public final double angle(Vector3d paramVector3d)
  {
    double d = dot(paramVector3d) / (length() * paramVector3d.length());
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
 * Qualified Name:     javax.vecmath.Vector3d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */