package javax.vecmath;

import java.io.Serializable;

public class AxisAngle4d
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 3644296204459140589L;
  public double x;
  public double y;
  public double z;
  public double angle;
  static final double EPS = 1.0E-006D;
  
  public AxisAngle4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.x = paramDouble1;
    this.y = paramDouble2;
    this.z = paramDouble3;
    this.angle = paramDouble4;
  }
  
  public AxisAngle4d(double[] paramArrayOfDouble)
  {
    this.x = paramArrayOfDouble[0];
    this.y = paramArrayOfDouble[1];
    this.z = paramArrayOfDouble[2];
    this.angle = paramArrayOfDouble[3];
  }
  
  public AxisAngle4d(AxisAngle4d paramAxisAngle4d)
  {
    this.x = paramAxisAngle4d.x;
    this.y = paramAxisAngle4d.y;
    this.z = paramAxisAngle4d.z;
    this.angle = paramAxisAngle4d.angle;
  }
  
  public AxisAngle4d(AxisAngle4f paramAxisAngle4f)
  {
    this.x = paramAxisAngle4f.x;
    this.y = paramAxisAngle4f.y;
    this.z = paramAxisAngle4f.z;
    this.angle = paramAxisAngle4f.angle;
  }
  
  public AxisAngle4d(Vector3d paramVector3d, double paramDouble)
  {
    this.x = paramVector3d.x;
    this.y = paramVector3d.y;
    this.z = paramVector3d.z;
    this.angle = paramDouble;
  }
  
  public AxisAngle4d()
  {
    this.x = 0.0D;
    this.y = 0.0D;
    this.z = 1.0D;
    this.angle = 0.0D;
  }
  
  public final void set(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.x = paramDouble1;
    this.y = paramDouble2;
    this.z = paramDouble3;
    this.angle = paramDouble4;
  }
  
  public final void set(double[] paramArrayOfDouble)
  {
    this.x = paramArrayOfDouble[0];
    this.y = paramArrayOfDouble[1];
    this.z = paramArrayOfDouble[2];
    this.angle = paramArrayOfDouble[3];
  }
  
  public final void set(AxisAngle4d paramAxisAngle4d)
  {
    this.x = paramAxisAngle4d.x;
    this.y = paramAxisAngle4d.y;
    this.z = paramAxisAngle4d.z;
    this.angle = paramAxisAngle4d.angle;
  }
  
  public final void set(AxisAngle4f paramAxisAngle4f)
  {
    this.x = paramAxisAngle4f.x;
    this.y = paramAxisAngle4f.y;
    this.z = paramAxisAngle4f.z;
    this.angle = paramAxisAngle4f.angle;
  }
  
  public final void set(Vector3d paramVector3d, double paramDouble)
  {
    this.x = paramVector3d.x;
    this.y = paramVector3d.y;
    this.z = paramVector3d.z;
    this.angle = paramDouble;
  }
  
  public final void get(double[] paramArrayOfDouble)
  {
    paramArrayOfDouble[0] = this.x;
    paramArrayOfDouble[1] = this.y;
    paramArrayOfDouble[2] = this.z;
    paramArrayOfDouble[3] = this.angle;
  }
  
  public final void set(Matrix4f paramMatrix4f)
  {
    Matrix3d localMatrix3d = new Matrix3d();
    paramMatrix4f.get(localMatrix3d);
    this.x = ((float)(localMatrix3d.m21 - localMatrix3d.m12));
    this.y = ((float)(localMatrix3d.m02 - localMatrix3d.m20));
    this.z = ((float)(localMatrix3d.m10 - localMatrix3d.m01));
    double d1 = this.x * this.x + this.y * this.y + this.z * this.z;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (localMatrix3d.m00 + localMatrix3d.m11 + localMatrix3d.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.x *= d4;
      this.y *= d4;
      this.z *= d4;
    }
    else
    {
      this.x = 0.0D;
      this.y = 1.0D;
      this.z = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public final void set(Matrix4d paramMatrix4d)
  {
    Matrix3d localMatrix3d = new Matrix3d();
    paramMatrix4d.get(localMatrix3d);
    this.x = ((float)(localMatrix3d.m21 - localMatrix3d.m12));
    this.y = ((float)(localMatrix3d.m02 - localMatrix3d.m20));
    this.z = ((float)(localMatrix3d.m10 - localMatrix3d.m01));
    double d1 = this.x * this.x + this.y * this.y + this.z * this.z;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (localMatrix3d.m00 + localMatrix3d.m11 + localMatrix3d.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.x *= d4;
      this.y *= d4;
      this.z *= d4;
    }
    else
    {
      this.x = 0.0D;
      this.y = 1.0D;
      this.z = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public final void set(Matrix3f paramMatrix3f)
  {
    this.x = (paramMatrix3f.m21 - paramMatrix3f.m12);
    this.y = (paramMatrix3f.m02 - paramMatrix3f.m20);
    this.z = (paramMatrix3f.m10 - paramMatrix3f.m01);
    double d1 = this.x * this.x + this.y * this.y + this.z * this.z;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.x *= d4;
      this.y *= d4;
      this.z *= d4;
    }
    else
    {
      this.x = 0.0D;
      this.y = 1.0D;
      this.z = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public final void set(Matrix3d paramMatrix3d)
  {
    this.x = ((float)(paramMatrix3d.m21 - paramMatrix3d.m12));
    this.y = ((float)(paramMatrix3d.m02 - paramMatrix3d.m20));
    this.z = ((float)(paramMatrix3d.m10 - paramMatrix3d.m01));
    double d1 = this.x * this.x + this.y * this.y + this.z * this.z;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.x *= d4;
      this.y *= d4;
      this.z *= d4;
    }
    else
    {
      this.x = 0.0D;
      this.y = 1.0D;
      this.z = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public final void set(Quat4f paramQuat4f)
  {
    double d1 = paramQuat4f.x * paramQuat4f.x + paramQuat4f.y * paramQuat4f.y + paramQuat4f.z * paramQuat4f.z;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 1.0D / d1;
      this.x = (paramQuat4f.x * d2);
      this.y = (paramQuat4f.y * d2);
      this.z = (paramQuat4f.z * d2);
      this.angle = (2.0D * Math.atan2(d1, paramQuat4f.w));
    }
    else
    {
      this.x = 0.0D;
      this.y = 1.0D;
      this.z = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public final void set(Quat4d paramQuat4d)
  {
    double d1 = paramQuat4d.x * paramQuat4d.x + paramQuat4d.y * paramQuat4d.y + paramQuat4d.z * paramQuat4d.z;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 1.0D / d1;
      this.x = (paramQuat4d.x * d2);
      this.y = (paramQuat4d.y * d2);
      this.z = (paramQuat4d.z * d2);
      this.angle = (2.0D * Math.atan2(d1, paramQuat4d.w));
    }
    else
    {
      this.x = 0.0D;
      this.y = 1.0D;
      this.z = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public String toString()
  {
    return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.angle + ")";
  }
  
  public boolean equals(AxisAngle4d paramAxisAngle4d)
  {
    try
    {
      return (this.x == paramAxisAngle4d.x) && (this.y == paramAxisAngle4d.y) && (this.z == paramAxisAngle4d.z) && (this.angle == paramAxisAngle4d.angle);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      AxisAngle4d localAxisAngle4d = (AxisAngle4d)paramObject;
      return (this.x == localAxisAngle4d.x) && (this.y == localAxisAngle4d.y) && (this.z == localAxisAngle4d.z) && (this.angle == localAxisAngle4d.angle);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException) {}
    return false;
  }
  
  public boolean epsilonEquals(AxisAngle4d paramAxisAngle4d, double paramDouble)
  {
    double d = this.x - paramAxisAngle4d.x;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.y - paramAxisAngle4d.y;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.z - paramAxisAngle4d.z;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.angle - paramAxisAngle4d.angle;
    return (d < 0.0D ? -d : d) <= paramDouble;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.x);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.y);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.z);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.angle);
    return (int)(l ^ l >> 32);
  }
  
  public Object clone()
  {
    try
    {
      return super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new InternalError();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.AxisAngle4d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */