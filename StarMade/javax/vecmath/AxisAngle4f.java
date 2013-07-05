package javax.vecmath;

import java.io.Serializable;

public class AxisAngle4f
  implements Serializable, Cloneable
{
  static final long serialVersionUID = -163246355858070601L;
  public float x;
  public float y;
  public float z;
  public float angle;
  static final double EPS = 1.0E-006D;

  public AxisAngle4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
    this.z = paramFloat3;
    this.angle = paramFloat4;
  }

  public AxisAngle4f(float[] paramArrayOfFloat)
  {
    this.x = paramArrayOfFloat[0];
    this.y = paramArrayOfFloat[1];
    this.z = paramArrayOfFloat[2];
    this.angle = paramArrayOfFloat[3];
  }

  public AxisAngle4f(AxisAngle4f paramAxisAngle4f)
  {
    this.x = paramAxisAngle4f.x;
    this.y = paramAxisAngle4f.y;
    this.z = paramAxisAngle4f.z;
    this.angle = paramAxisAngle4f.angle;
  }

  public AxisAngle4f(AxisAngle4d paramAxisAngle4d)
  {
    this.x = ((float)paramAxisAngle4d.x);
    this.y = ((float)paramAxisAngle4d.y);
    this.z = ((float)paramAxisAngle4d.z);
    this.angle = ((float)paramAxisAngle4d.angle);
  }

  public AxisAngle4f(Vector3f paramVector3f, float paramFloat)
  {
    this.x = paramVector3f.x;
    this.y = paramVector3f.y;
    this.z = paramVector3f.z;
    this.angle = paramFloat;
  }

  public AxisAngle4f()
  {
    this.x = 0.0F;
    this.y = 0.0F;
    this.z = 1.0F;
    this.angle = 0.0F;
  }

  public final void set(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
    this.z = paramFloat3;
    this.angle = paramFloat4;
  }

  public final void set(float[] paramArrayOfFloat)
  {
    this.x = paramArrayOfFloat[0];
    this.y = paramArrayOfFloat[1];
    this.z = paramArrayOfFloat[2];
    this.angle = paramArrayOfFloat[3];
  }

  public final void set(AxisAngle4f paramAxisAngle4f)
  {
    this.x = paramAxisAngle4f.x;
    this.y = paramAxisAngle4f.y;
    this.z = paramAxisAngle4f.z;
    this.angle = paramAxisAngle4f.angle;
  }

  public final void set(AxisAngle4d paramAxisAngle4d)
  {
    this.x = ((float)paramAxisAngle4d.x);
    this.y = ((float)paramAxisAngle4d.y);
    this.z = ((float)paramAxisAngle4d.z);
    this.angle = ((float)paramAxisAngle4d.angle);
  }

  public final void set(Vector3f paramVector3f, float paramFloat)
  {
    this.x = paramVector3f.x;
    this.y = paramVector3f.y;
    this.z = paramVector3f.z;
    this.angle = paramFloat;
  }

  public final void get(float[] paramArrayOfFloat)
  {
    paramArrayOfFloat[0] = this.x;
    paramArrayOfFloat[1] = this.y;
    paramArrayOfFloat[2] = this.z;
    paramArrayOfFloat[3] = this.angle;
  }

  public final void set(Quat4f paramQuat4f)
  {
    double d1 = paramQuat4f.x * paramQuat4f.x + paramQuat4f.y * paramQuat4f.y + paramQuat4f.z * paramQuat4f.z;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 1.0D / d1;
      this.x = ((float)(paramQuat4f.x * d2));
      this.y = ((float)(paramQuat4f.y * d2));
      this.z = ((float)(paramQuat4f.z * d2));
      this.angle = ((float)(2.0D * Math.atan2(d1, paramQuat4f.w)));
    }
    else
    {
      this.x = 0.0F;
      this.y = 1.0F;
      this.z = 0.0F;
      this.angle = 0.0F;
    }
  }

  public final void set(Quat4d paramQuat4d)
  {
    double d1 = paramQuat4d.x * paramQuat4d.x + paramQuat4d.y * paramQuat4d.y + paramQuat4d.z * paramQuat4d.z;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 1.0D / d1;
      this.x = ((float)(paramQuat4d.x * d2));
      this.y = ((float)(paramQuat4d.y * d2));
      this.z = ((float)(paramQuat4d.z * d2));
      this.angle = ((float)(2.0D * Math.atan2(d1, paramQuat4d.w)));
    }
    else
    {
      this.x = 0.0F;
      this.y = 1.0F;
      this.z = 0.0F;
      this.angle = 0.0F;
    }
  }

  public final void set(Matrix4f paramMatrix4f)
  {
    Matrix3f localMatrix3f = new Matrix3f();
    paramMatrix4f.get(localMatrix3f);
    this.x = (localMatrix3f.m21 - localMatrix3f.m12);
    this.y = (localMatrix3f.m02 - localMatrix3f.m20);
    this.z = (localMatrix3f.m10 - localMatrix3f.m01);
    double d1 = this.x * this.x + this.y * this.y + this.z * this.z;
    if (d1 > 1.0E-006D)
    {
      d1 = Math.sqrt(d1);
      double d2 = 0.5D * d1;
      double d3 = 0.5D * (localMatrix3f.m00 + localMatrix3f.m11 + localMatrix3f.m22 - 1.0D);
      this.angle = ((float)Math.atan2(d2, d3));
      double d4 = 1.0D / d1;
      this.x = ((float)(this.x * d4));
      this.y = ((float)(this.y * d4));
      this.z = ((float)(this.z * d4));
    }
    else
    {
      this.x = 0.0F;
      this.y = 1.0F;
      this.z = 0.0F;
      this.angle = 0.0F;
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
      this.x = ((float)(this.x * d4));
      this.y = ((float)(this.y * d4));
      this.z = ((float)(this.z * d4));
    }
    else
    {
      this.x = 0.0F;
      this.y = 1.0F;
      this.z = 0.0F;
      this.angle = 0.0F;
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
      this.x = ((float)(this.x * d4));
      this.y = ((float)(this.y * d4));
      this.z = ((float)(this.z * d4));
    }
    else
    {
      this.x = 0.0F;
      this.y = 1.0F;
      this.z = 0.0F;
      this.angle = 0.0F;
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
      this.x = ((float)(this.x * d4));
      this.y = ((float)(this.y * d4));
      this.z = ((float)(this.z * d4));
    }
    else
    {
      this.x = 0.0F;
      this.y = 1.0F;
      this.z = 0.0F;
      this.angle = 0.0F;
    }
  }

  public String toString()
  {
    return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.angle + ")";
  }

  public boolean equals(AxisAngle4f paramAxisAngle4f)
  {
    try
    {
      return (this.x == paramAxisAngle4f.x) && (this.y == paramAxisAngle4f.y) && (this.z == paramAxisAngle4f.z) && (this.angle == paramAxisAngle4f.angle);
    }
    catch (NullPointerException localNullPointerException)
    {
    }
    return false;
  }

  public boolean equals(Object paramObject)
  {
    try
    {
      AxisAngle4f localAxisAngle4f = (AxisAngle4f)paramObject;
      return (this.x == localAxisAngle4f.x) && (this.y == localAxisAngle4f.y) && (this.z == localAxisAngle4f.z) && (this.angle == localAxisAngle4f.angle);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return false;
  }

  public boolean epsilonEquals(AxisAngle4f paramAxisAngle4f, float paramFloat)
  {
    float f = this.x - paramAxisAngle4f.x;
    if ((f < 0.0F ? -f : f) > paramFloat)
      return false;
    f = this.y - paramAxisAngle4f.y;
    if ((f < 0.0F ? -f : f) > paramFloat)
      return false;
    f = this.z - paramAxisAngle4f.z;
    if ((f < 0.0F ? -f : f) > paramFloat)
      return false;
    f = this.angle - paramAxisAngle4f.angle;
    return (f < 0.0F ? -f : f) <= paramFloat;
  }

  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.floatToIntBits(this.x);
    l = 31L * l + VecMathUtil.floatToIntBits(this.y);
    l = 31L * l + VecMathUtil.floatToIntBits(this.z);
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
    }
    throw new InternalError();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.AxisAngle4f
 * JD-Core Version:    0.6.2
 */