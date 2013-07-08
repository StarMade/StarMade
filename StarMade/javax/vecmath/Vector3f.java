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
    return this.field_615 * this.field_615 + this.field_616 * this.field_616 + this.field_617 * this.field_617;
  }
  
  public final float length()
  {
    return (float)Math.sqrt(this.field_615 * this.field_615 + this.field_616 * this.field_616 + this.field_617 * this.field_617);
  }
  
  public final void cross(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    float f1 = paramVector3f1.field_616 * paramVector3f2.field_617 - paramVector3f1.field_617 * paramVector3f2.field_616;
    float f2 = paramVector3f2.field_615 * paramVector3f1.field_617 - paramVector3f2.field_617 * paramVector3f1.field_615;
    this.field_617 = (paramVector3f1.field_615 * paramVector3f2.field_616 - paramVector3f1.field_616 * paramVector3f2.field_615);
    this.field_615 = f1;
    this.field_616 = f2;
  }
  
  public final float dot(Vector3f paramVector3f)
  {
    return this.field_615 * paramVector3f.field_615 + this.field_616 * paramVector3f.field_616 + this.field_617 * paramVector3f.field_617;
  }
  
  public final void normalize(Vector3f paramVector3f)
  {
    float f = (float)(1.0D / Math.sqrt(paramVector3f.field_615 * paramVector3f.field_615 + paramVector3f.field_616 * paramVector3f.field_616 + paramVector3f.field_617 * paramVector3f.field_617));
    paramVector3f.field_615 *= f;
    paramVector3f.field_616 *= f;
    paramVector3f.field_617 *= f;
  }
  
  public final void normalize()
  {
    float f = (float)(1.0D / Math.sqrt(this.field_615 * this.field_615 + this.field_616 * this.field_616 + this.field_617 * this.field_617));
    this.field_615 *= f;
    this.field_616 *= f;
    this.field_617 *= f;
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


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Vector3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */