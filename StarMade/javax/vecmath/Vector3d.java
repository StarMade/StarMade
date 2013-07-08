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
    double d1 = paramVector3d1.y * paramVector3d2.z - paramVector3d1.z * paramVector3d2.y;
    double d2 = paramVector3d2.x * paramVector3d1.z - paramVector3d2.z * paramVector3d1.x;
    this.z = (paramVector3d1.x * paramVector3d2.y - paramVector3d1.y * paramVector3d2.x);
    this.x = d1;
    this.y = d2;
  }
  
  public final void normalize(Vector3d paramVector3d)
  {
    double d = 1.0D / Math.sqrt(paramVector3d.x * paramVector3d.x + paramVector3d.y * paramVector3d.y + paramVector3d.z * paramVector3d.z);
    paramVector3d.x *= d;
    paramVector3d.y *= d;
    paramVector3d.z *= d;
  }
  
  public final void normalize()
  {
    double d = 1.0D / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    this.x *= d;
    this.y *= d;
    this.z *= d;
  }
  
  public final double dot(Vector3d paramVector3d)
  {
    return this.x * paramVector3d.x + this.y * paramVector3d.y + this.z * paramVector3d.z;
  }
  
  public final double lengthSquared()
  {
    return this.x * this.x + this.y * this.y + this.z * this.z;
  }
  
  public final double length()
  {
    return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
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


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Vector3d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */