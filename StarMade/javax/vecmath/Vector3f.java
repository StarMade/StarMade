package javax.vecmath;

import java.io.Serializable;

public class Vector3f
  extends Tuple3f
  implements Serializable
{
  static final long serialVersionUID = -7031930069184524614L;
  
  public Vector3f(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public Vector3f(float[] paramArrayOfFloat)
  {
    super(paramArrayOfFloat);
  }
  
  public Vector3f(Vector3f paramVector3f)
  {
    super(paramVector3f);
  }
  
  public Vector3f(Vector3d paramVector3d)
  {
    super(paramVector3d);
  }
  
  public Vector3f(Tuple3f paramTuple3f)
  {
    super(paramTuple3f);
  }
  
  public Vector3f(Tuple3d paramTuple3d)
  {
    super(paramTuple3d);
  }
  
  public Vector3f() {}
  
  public final float lengthSquared()
  {
    return this.x * this.x + this.y * this.y + this.z * this.z;
  }
  
  public final float length()
  {
    return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
  }
  
  public final void cross(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    float f1 = paramVector3f1.y * paramVector3f2.z - paramVector3f1.z * paramVector3f2.y;
    float f2 = paramVector3f2.x * paramVector3f1.z - paramVector3f2.z * paramVector3f1.x;
    this.z = (paramVector3f1.x * paramVector3f2.y - paramVector3f1.y * paramVector3f2.x);
    this.x = f1;
    this.y = f2;
  }
  
  public final float dot(Vector3f paramVector3f)
  {
    return this.x * paramVector3f.x + this.y * paramVector3f.y + this.z * paramVector3f.z;
  }
  
  public final void normalize(Vector3f paramVector3f)
  {
    float f = (float)(1.0D / Math.sqrt(paramVector3f.x * paramVector3f.x + paramVector3f.y * paramVector3f.y + paramVector3f.z * paramVector3f.z));
    paramVector3f.x *= f;
    paramVector3f.y *= f;
    paramVector3f.z *= f;
  }
  
  public final void normalize()
  {
    float f = (float)(1.0D / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z));
    this.x *= f;
    this.y *= f;
    this.z *= f;
  }
  
  public final float angle(Vector3f paramVector3f)
  {
    double d = dot(paramVector3f) / (length() * paramVector3f.length());
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
 * Qualified Name:     javax.vecmath.Vector3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */