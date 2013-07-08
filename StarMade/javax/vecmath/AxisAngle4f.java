package javax.vecmath;

import java.io.Serializable;

public class AxisAngle4f
  implements Serializable, Cloneable
{
  static final long serialVersionUID = -163246355858070601L;
  public float field_586;
  public float field_587;
  public float field_588;
  public float angle;
  static final double EPS = 1.0E-006D;
  
  public AxisAngle4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.field_586 = paramFloat1;
    this.field_587 = paramFloat2;
    this.field_588 = paramFloat3;
    this.angle = paramFloat4;
  }
  
  public AxisAngle4f(float[] paramArrayOfFloat)
  {
    this.field_586 = paramArrayOfFloat[0];
    this.field_587 = paramArrayOfFloat[1];
    this.field_588 = paramArrayOfFloat[2];
    this.angle = paramArrayOfFloat[3];
  }
  
  public AxisAngle4f(AxisAngle4f paramAxisAngle4f)
  {
    this.field_586 = paramAxisAngle4f.field_586;
    this.field_587 = paramAxisAngle4f.field_587;
    this.field_588 = paramAxisAngle4f.field_588;
    this.angle = paramAxisAngle4f.angle;
  }
  
  public AxisAngle4f(AxisAngle4d paramAxisAngle4d)
  {
    this.field_586 = ((float)paramAxisAngle4d.field_589);
    this.field_587 = ((float)paramAxisAngle4d.field_590);
    this.field_588 = ((float)paramAxisAngle4d.field_591);
    this.angle = ((float)paramAxisAngle4d.angle);
  }
  
  public AxisAngle4f(Vector3f paramVector3f, float paramFloat)
  {
    this.field_586 = paramVector3f.field_615;
    this.field_587 = paramVector3f.field_616;
    this.field_588 = paramVector3f.field_617;
    this.angle = paramFloat;
  }
  
  public AxisAngle4f()
  {
    this.field_586 = 0.0F;
    this.field_587 = 0.0F;
    this.field_588 = 1.0F;
    this.angle = 0.0F;
  }
  
  public final void set(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.field_586 = paramFloat1;
    this.field_587 = paramFloat2;
    this.field_588 = paramFloat3;
    this.angle = paramFloat4;
  }
  
  public final void set(float[] paramArrayOfFloat)
  {
    this.field_586 = paramArrayOfFloat[0];
    this.field_587 = paramArrayOfFloat[1];
    this.field_588 = paramArrayOfFloat[2];
    this.angle = paramArrayOfFloat[3];
  }
  
  public final void set(AxisAngle4f paramAxisAngle4f)
  {
    this.field_586 = paramAxisAngle4f.field_586;
    this.field_587 = paramAxisAngle4f.field_587;
    this.field_588 = paramAxisAngle4f.field_588;
    this.angle = paramAxisAngle4f.angle;
  }
  
  public final void set(AxisAngle4d paramAxisAngle4d)
  {
    this.field_586 = ((float)paramAxisAngle4d.field_589);
    this.field_587 = ((float)paramAxisAngle4d.field_590);
    this.field_588 = ((float)paramAxisAngle4d.field_591);
    this.angle = ((float)paramAxisAngle4d.angle);
  }
  
  public final void set(Vector3f paramVector3f, float paramFloat)
  {
    this.field_586 = paramVector3f.field_615;
    this.field_587 = paramVector3f.field_616;
    this.field_588 = paramVector3f.field_617;
    this.angle = paramFloat;
  }
  
  public final void get(float[] paramArrayOfFloat)
  {
    paramArrayOfFloat[0] = this.field_586;
    paramArrayOfFloat[1] = this.field_587;
    paramArrayOfFloat[2] = this.field_588;
    paramArrayOfFloat[3] = this.angle;
  }
  
  public final void set(Quat4f paramQuat4f)
  {
    double d1 = paramQuat4f.field_596 * paramQuat4f.field_596 + paramQuat4f.field_597 * paramQuat4f.field_597 + paramQuat4f.field_598 * paramQuat4f.field_598;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 1.0D / d1;
      this.field_586 = ((float)(paramQuat4f.field_596 * d2));
      this.field_587 = ((float)(paramQuat4f.field_597 * d2));
      this.field_588 = ((float)(paramQuat4f.field_598 * d2));
      this.angle = ((float)(2.0D * Math.atan2(d1, paramQuat4f.field_599)));
    }
    else
    {
      this.field_586 = 0.0F;
      this.field_587 = 1.0F;
      this.field_588 = 0.0F;
      this.angle = 0.0F;
    }
  }
  
  public final void set(Quat4d paramQuat4d)
  {
    double d1 = paramQuat4d.field_600 * paramQuat4d.field_600 + paramQuat4d.field_601 * paramQuat4d.field_601 + paramQuat4d.field_602 * paramQuat4d.field_602;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 1.0D / d1;
      this.field_586 = ((float)(paramQuat4d.field_600 * d2));
      this.field_587 = ((float)(paramQuat4d.field_601 * d2));
      this.field_588 = ((float)(paramQuat4d.field_602 * d2));
      this.angle = ((float)(2.0D * Math.atan2(d1, paramQuat4d.field_603)));
    }
    else
    {
      this.field_586 = 0.0F;
      this.field_587 = 1.0F;
      this.field_588 = 0.0F;
      this.angle = 0.0F;
    }
  }
  
  public final void set(Matrix4f paramMatrix4f)
  {
    Matrix3f localMatrix3f = new Matrix3f();
    paramMatrix4f.get(localMatrix3f);
    this.field_586 = (localMatrix3f.m21 - localMatrix3f.m12);
    this.field_587 = (localMatrix3f.m02 - localMatrix3f.m20);
    this.field_588 = (localMatrix3f.m10 - localMatrix3f.m01);
    double d1 = this.field_586 * this.field_586 + this.field_587 * this.field_587 + this.field_588 * this.field_588;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (localMatrix3f.m00 + localMatrix3f.m11 + localMatrix3f.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.field_586 = ((float)(this.field_586 * d4));
      this.field_587 = ((float)(this.field_587 * d4));
      this.field_588 = ((float)(this.field_588 * d4));
    }
    else
    {
      this.field_586 = 0.0F;
      this.field_587 = 1.0F;
      this.field_588 = 0.0F;
      this.angle = 0.0F;
    }
  }
  
  public final void set(Matrix4d paramMatrix4d)
  {
    Matrix3d localMatrix3d = new Matrix3d();
    paramMatrix4d.get(localMatrix3d);
    this.field_586 = ((float)(localMatrix3d.m21 - localMatrix3d.m12));
    this.field_587 = ((float)(localMatrix3d.m02 - localMatrix3d.m20));
    this.field_588 = ((float)(localMatrix3d.m10 - localMatrix3d.m01));
    double d1 = this.field_586 * this.field_586 + this.field_587 * this.field_587 + this.field_588 * this.field_588;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (localMatrix3d.m00 + localMatrix3d.m11 + localMatrix3d.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.field_586 = ((float)(this.field_586 * d4));
      this.field_587 = ((float)(this.field_587 * d4));
      this.field_588 = ((float)(this.field_588 * d4));
    }
    else
    {
      this.field_586 = 0.0F;
      this.field_587 = 1.0F;
      this.field_588 = 0.0F;
      this.angle = 0.0F;
    }
  }
  
  public final void set(Matrix3f paramMatrix3f)
  {
    this.field_586 = (paramMatrix3f.m21 - paramMatrix3f.m12);
    this.field_587 = (paramMatrix3f.m02 - paramMatrix3f.m20);
    this.field_588 = (paramMatrix3f.m10 - paramMatrix3f.m01);
    double d1 = this.field_586 * this.field_586 + this.field_587 * this.field_587 + this.field_588 * this.field_588;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.field_586 = ((float)(this.field_586 * d4));
      this.field_587 = ((float)(this.field_587 * d4));
      this.field_588 = ((float)(this.field_588 * d4));
    }
    else
    {
      this.field_586 = 0.0F;
      this.field_587 = 1.0F;
      this.field_588 = 0.0F;
      this.angle = 0.0F;
    }
  }
  
  public final void set(Matrix3d paramMatrix3d)
  {
    this.field_586 = ((float)(paramMatrix3d.m21 - paramMatrix3d.m12));
    this.field_587 = ((float)(paramMatrix3d.m02 - paramMatrix3d.m20));
    this.field_588 = ((float)(paramMatrix3d.m10 - paramMatrix3d.m01));
    double d1 = this.field_586 * this.field_586 + this.field_587 * this.field_587 + this.field_588 * this.field_588;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.field_586 = ((float)(this.field_586 * d4));
      this.field_587 = ((float)(this.field_587 * d4));
      this.field_588 = ((float)(this.field_588 * d4));
    }
    else
    {
      this.field_586 = 0.0F;
      this.field_587 = 1.0F;
      this.field_588 = 0.0F;
      this.angle = 0.0F;
    }
  }
  
  public String toString()
  {
    return "(" + this.field_586 + ", " + this.field_587 + ", " + this.field_588 + ", " + this.angle + ")";
  }
  
  public boolean equals(AxisAngle4f paramAxisAngle4f)
  {
    try
    {
      return (this.field_586 == paramAxisAngle4f.field_586) && (this.field_587 == paramAxisAngle4f.field_587) && (this.field_588 == paramAxisAngle4f.field_588) && (this.angle == paramAxisAngle4f.angle);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      AxisAngle4f localAxisAngle4f = (AxisAngle4f)paramObject;
      return (this.field_586 == localAxisAngle4f.field_586) && (this.field_587 == localAxisAngle4f.field_587) && (this.field_588 == localAxisAngle4f.field_588) && (this.angle == localAxisAngle4f.angle);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException) {}
    return false;
  }
  
  public boolean epsilonEquals(AxisAngle4f paramAxisAngle4f, float paramFloat)
  {
    float f = this.field_586 - paramAxisAngle4f.field_586;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.field_587 - paramAxisAngle4f.field_587;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.field_588 - paramAxisAngle4f.field_588;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.angle - paramAxisAngle4f.angle;
    return (f < 0.0F ? -f : f) <= paramFloat;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_586);
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_587);
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_588);
    l = 31L * l + VecMathUtil.floatToIntBits(this.angle);
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
 * Qualified Name:     javax.vecmath.AxisAngle4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */