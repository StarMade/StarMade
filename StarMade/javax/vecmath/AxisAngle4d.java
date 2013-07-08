package javax.vecmath;

import java.io.Serializable;

public class AxisAngle4d
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 3644296204459140589L;
  public double field_589;
  public double field_590;
  public double field_591;
  public double angle;
  static final double EPS = 1.0E-006D;
  
  public AxisAngle4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.field_589 = paramDouble1;
    this.field_590 = paramDouble2;
    this.field_591 = paramDouble3;
    this.angle = paramDouble4;
  }
  
  public AxisAngle4d(double[] paramArrayOfDouble)
  {
    this.field_589 = paramArrayOfDouble[0];
    this.field_590 = paramArrayOfDouble[1];
    this.field_591 = paramArrayOfDouble[2];
    this.angle = paramArrayOfDouble[3];
  }
  
  public AxisAngle4d(AxisAngle4d paramAxisAngle4d)
  {
    this.field_589 = paramAxisAngle4d.field_589;
    this.field_590 = paramAxisAngle4d.field_590;
    this.field_591 = paramAxisAngle4d.field_591;
    this.angle = paramAxisAngle4d.angle;
  }
  
  public AxisAngle4d(AxisAngle4f paramAxisAngle4f)
  {
    this.field_589 = paramAxisAngle4f.field_586;
    this.field_590 = paramAxisAngle4f.field_587;
    this.field_591 = paramAxisAngle4f.field_588;
    this.angle = paramAxisAngle4f.angle;
  }
  
  public AxisAngle4d(Vector3d paramVector3d, double paramDouble)
  {
    this.field_589 = paramVector3d.field_612;
    this.field_590 = paramVector3d.field_613;
    this.field_591 = paramVector3d.field_614;
    this.angle = paramDouble;
  }
  
  public AxisAngle4d()
  {
    this.field_589 = 0.0D;
    this.field_590 = 0.0D;
    this.field_591 = 1.0D;
    this.angle = 0.0D;
  }
  
  public final void set(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.field_589 = paramDouble1;
    this.field_590 = paramDouble2;
    this.field_591 = paramDouble3;
    this.angle = paramDouble4;
  }
  
  public final void set(double[] paramArrayOfDouble)
  {
    this.field_589 = paramArrayOfDouble[0];
    this.field_590 = paramArrayOfDouble[1];
    this.field_591 = paramArrayOfDouble[2];
    this.angle = paramArrayOfDouble[3];
  }
  
  public final void set(AxisAngle4d paramAxisAngle4d)
  {
    this.field_589 = paramAxisAngle4d.field_589;
    this.field_590 = paramAxisAngle4d.field_590;
    this.field_591 = paramAxisAngle4d.field_591;
    this.angle = paramAxisAngle4d.angle;
  }
  
  public final void set(AxisAngle4f paramAxisAngle4f)
  {
    this.field_589 = paramAxisAngle4f.field_586;
    this.field_590 = paramAxisAngle4f.field_587;
    this.field_591 = paramAxisAngle4f.field_588;
    this.angle = paramAxisAngle4f.angle;
  }
  
  public final void set(Vector3d paramVector3d, double paramDouble)
  {
    this.field_589 = paramVector3d.field_612;
    this.field_590 = paramVector3d.field_613;
    this.field_591 = paramVector3d.field_614;
    this.angle = paramDouble;
  }
  
  public final void get(double[] paramArrayOfDouble)
  {
    paramArrayOfDouble[0] = this.field_589;
    paramArrayOfDouble[1] = this.field_590;
    paramArrayOfDouble[2] = this.field_591;
    paramArrayOfDouble[3] = this.angle;
  }
  
  public final void set(Matrix4f paramMatrix4f)
  {
    Matrix3d localMatrix3d = new Matrix3d();
    paramMatrix4f.get(localMatrix3d);
    this.field_589 = ((float)(localMatrix3d.m21 - localMatrix3d.m12));
    this.field_590 = ((float)(localMatrix3d.m02 - localMatrix3d.m20));
    this.field_591 = ((float)(localMatrix3d.m10 - localMatrix3d.m01));
    double d1 = this.field_589 * this.field_589 + this.field_590 * this.field_590 + this.field_591 * this.field_591;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (localMatrix3d.m00 + localMatrix3d.m11 + localMatrix3d.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.field_589 *= d4;
      this.field_590 *= d4;
      this.field_591 *= d4;
    }
    else
    {
      this.field_589 = 0.0D;
      this.field_590 = 1.0D;
      this.field_591 = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public final void set(Matrix4d paramMatrix4d)
  {
    Matrix3d localMatrix3d = new Matrix3d();
    paramMatrix4d.get(localMatrix3d);
    this.field_589 = ((float)(localMatrix3d.m21 - localMatrix3d.m12));
    this.field_590 = ((float)(localMatrix3d.m02 - localMatrix3d.m20));
    this.field_591 = ((float)(localMatrix3d.m10 - localMatrix3d.m01));
    double d1 = this.field_589 * this.field_589 + this.field_590 * this.field_590 + this.field_591 * this.field_591;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (localMatrix3d.m00 + localMatrix3d.m11 + localMatrix3d.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.field_589 *= d4;
      this.field_590 *= d4;
      this.field_591 *= d4;
    }
    else
    {
      this.field_589 = 0.0D;
      this.field_590 = 1.0D;
      this.field_591 = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public final void set(Matrix3f paramMatrix3f)
  {
    this.field_589 = (paramMatrix3f.m21 - paramMatrix3f.m12);
    this.field_590 = (paramMatrix3f.m02 - paramMatrix3f.m20);
    this.field_591 = (paramMatrix3f.m10 - paramMatrix3f.m01);
    double d1 = this.field_589 * this.field_589 + this.field_590 * this.field_590 + this.field_591 * this.field_591;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.field_589 *= d4;
      this.field_590 *= d4;
      this.field_591 *= d4;
    }
    else
    {
      this.field_589 = 0.0D;
      this.field_590 = 1.0D;
      this.field_591 = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public final void set(Matrix3d paramMatrix3d)
  {
    this.field_589 = ((float)(paramMatrix3d.m21 - paramMatrix3d.m12));
    this.field_590 = ((float)(paramMatrix3d.m02 - paramMatrix3d.m20));
    this.field_591 = ((float)(paramMatrix3d.m10 - paramMatrix3d.m01));
    double d1 = this.field_589 * this.field_589 + this.field_590 * this.field_590 + this.field_591 * this.field_591;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.field_589 *= d4;
      this.field_590 *= d4;
      this.field_591 *= d4;
    }
    else
    {
      this.field_589 = 0.0D;
      this.field_590 = 1.0D;
      this.field_591 = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public final void set(Quat4f paramQuat4f)
  {
    double d1 = paramQuat4f.field_596 * paramQuat4f.field_596 + paramQuat4f.field_597 * paramQuat4f.field_597 + paramQuat4f.field_598 * paramQuat4f.field_598;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 1.0D / d1;
      this.field_589 = (paramQuat4f.field_596 * d2);
      this.field_590 = (paramQuat4f.field_597 * d2);
      this.field_591 = (paramQuat4f.field_598 * d2);
      this.angle = (2.0D * Math.atan2(d1, paramQuat4f.field_599));
    }
    else
    {
      this.field_589 = 0.0D;
      this.field_590 = 1.0D;
      this.field_591 = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public final void set(Quat4d paramQuat4d)
  {
    double d1 = paramQuat4d.field_600 * paramQuat4d.field_600 + paramQuat4d.field_601 * paramQuat4d.field_601 + paramQuat4d.field_602 * paramQuat4d.field_602;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 1.0D / d1;
      this.field_589 = (paramQuat4d.field_600 * d2);
      this.field_590 = (paramQuat4d.field_601 * d2);
      this.field_591 = (paramQuat4d.field_602 * d2);
      this.angle = (2.0D * Math.atan2(d1, paramQuat4d.field_603));
    }
    else
    {
      this.field_589 = 0.0D;
      this.field_590 = 1.0D;
      this.field_591 = 0.0D;
      this.angle = 0.0D;
    }
  }
  
  public String toString()
  {
    return "(" + this.field_589 + ", " + this.field_590 + ", " + this.field_591 + ", " + this.angle + ")";
  }
  
  public boolean equals(AxisAngle4d paramAxisAngle4d)
  {
    try
    {
      return (this.field_589 == paramAxisAngle4d.field_589) && (this.field_590 == paramAxisAngle4d.field_590) && (this.field_591 == paramAxisAngle4d.field_591) && (this.angle == paramAxisAngle4d.angle);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      AxisAngle4d localAxisAngle4d = (AxisAngle4d)paramObject;
      return (this.field_589 == localAxisAngle4d.field_589) && (this.field_590 == localAxisAngle4d.field_590) && (this.field_591 == localAxisAngle4d.field_591) && (this.angle == localAxisAngle4d.angle);
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
    double d = this.field_589 - paramAxisAngle4d.field_589;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.field_590 - paramAxisAngle4d.field_590;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.field_591 - paramAxisAngle4d.field_591;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.angle - paramAxisAngle4d.angle;
    return (d < 0.0D ? -d : d) <= paramDouble;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_589);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_590);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_591);
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


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.AxisAngle4d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */