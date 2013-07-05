package javax.vecmath;

import java.io.Serializable;

public class Quat4d extends Tuple4d
  implements Serializable
{
  static final long serialVersionUID = 7577479888820201099L;
  static final double EPS = 1.0E-006D;
  static final double EPS2 = 1.E-030D;
  static final double PIO2 = 1.57079632679D;

  public Quat4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d = 1.0D / Math.sqrt(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2 + paramDouble3 * paramDouble3 + paramDouble4 * paramDouble4);
    this.x = (paramDouble1 * d);
    this.y = (paramDouble2 * d);
    this.z = (paramDouble3 * d);
    this.w = (paramDouble4 * d);
  }

  public Quat4d(double[] paramArrayOfDouble)
  {
    double d = 1.0D / Math.sqrt(paramArrayOfDouble[0] * paramArrayOfDouble[0] + paramArrayOfDouble[1] * paramArrayOfDouble[1] + paramArrayOfDouble[2] * paramArrayOfDouble[2] + paramArrayOfDouble[3] * paramArrayOfDouble[3]);
    this.x = (paramArrayOfDouble[0] * d);
    this.y = (paramArrayOfDouble[1] * d);
    this.z = (paramArrayOfDouble[2] * d);
    this.w = (paramArrayOfDouble[3] * d);
  }

  public Quat4d(Quat4d paramQuat4d)
  {
    super(paramQuat4d);
  }

  public Quat4d(Quat4f paramQuat4f)
  {
    super(paramQuat4f);
  }

  public Quat4d(Tuple4f paramTuple4f)
  {
    double d = 1.0D / Math.sqrt(paramTuple4f.x * paramTuple4f.x + paramTuple4f.y * paramTuple4f.y + paramTuple4f.z * paramTuple4f.z + paramTuple4f.w * paramTuple4f.w);
    this.x = (paramTuple4f.x * d);
    this.y = (paramTuple4f.y * d);
    this.z = (paramTuple4f.z * d);
    this.w = (paramTuple4f.w * d);
  }

  public Quat4d(Tuple4d paramTuple4d)
  {
    double d = 1.0D / Math.sqrt(paramTuple4d.x * paramTuple4d.x + paramTuple4d.y * paramTuple4d.y + paramTuple4d.z * paramTuple4d.z + paramTuple4d.w * paramTuple4d.w);
    this.x = (paramTuple4d.x * d);
    this.y = (paramTuple4d.y * d);
    this.z = (paramTuple4d.z * d);
    this.w = (paramTuple4d.w * d);
  }

  public Quat4d()
  {
  }

  public final void conjugate(Quat4d paramQuat4d)
  {
    this.x = (-paramQuat4d.x);
    this.y = (-paramQuat4d.y);
    this.z = (-paramQuat4d.z);
    this.w = paramQuat4d.w;
  }

  public final void conjugate()
  {
    this.x = (-this.x);
    this.y = (-this.y);
    this.z = (-this.z);
  }

  public final void mul(Quat4d paramQuat4d1, Quat4d paramQuat4d2)
  {
    if ((this != paramQuat4d1) && (this != paramQuat4d2))
    {
      this.w = (paramQuat4d1.w * paramQuat4d2.w - paramQuat4d1.x * paramQuat4d2.x - paramQuat4d1.y * paramQuat4d2.y - paramQuat4d1.z * paramQuat4d2.z);
      this.x = (paramQuat4d1.w * paramQuat4d2.x + paramQuat4d2.w * paramQuat4d1.x + paramQuat4d1.y * paramQuat4d2.z - paramQuat4d1.z * paramQuat4d2.y);
      this.y = (paramQuat4d1.w * paramQuat4d2.y + paramQuat4d2.w * paramQuat4d1.y - paramQuat4d1.x * paramQuat4d2.z + paramQuat4d1.z * paramQuat4d2.x);
      this.z = (paramQuat4d1.w * paramQuat4d2.z + paramQuat4d2.w * paramQuat4d1.z + paramQuat4d1.x * paramQuat4d2.y - paramQuat4d1.y * paramQuat4d2.x);
    }
    else
    {
      double d3 = paramQuat4d1.w * paramQuat4d2.w - paramQuat4d1.x * paramQuat4d2.x - paramQuat4d1.y * paramQuat4d2.y - paramQuat4d1.z * paramQuat4d2.z;
      double d1 = paramQuat4d1.w * paramQuat4d2.x + paramQuat4d2.w * paramQuat4d1.x + paramQuat4d1.y * paramQuat4d2.z - paramQuat4d1.z * paramQuat4d2.y;
      double d2 = paramQuat4d1.w * paramQuat4d2.y + paramQuat4d2.w * paramQuat4d1.y - paramQuat4d1.x * paramQuat4d2.z + paramQuat4d1.z * paramQuat4d2.x;
      this.z = (paramQuat4d1.w * paramQuat4d2.z + paramQuat4d2.w * paramQuat4d1.z + paramQuat4d1.x * paramQuat4d2.y - paramQuat4d1.y * paramQuat4d2.x);
      this.w = d3;
      this.x = d1;
      this.y = d2;
    }
  }

  public final void mul(Quat4d paramQuat4d)
  {
    double d3 = this.w * paramQuat4d.w - this.x * paramQuat4d.x - this.y * paramQuat4d.y - this.z * paramQuat4d.z;
    double d1 = this.w * paramQuat4d.x + paramQuat4d.w * this.x + this.y * paramQuat4d.z - this.z * paramQuat4d.y;
    double d2 = this.w * paramQuat4d.y + paramQuat4d.w * this.y - this.x * paramQuat4d.z + this.z * paramQuat4d.x;
    this.z = (this.w * paramQuat4d.z + paramQuat4d.w * this.z + this.x * paramQuat4d.y - this.y * paramQuat4d.x);
    this.w = d3;
    this.x = d1;
    this.y = d2;
  }

  public final void mulInverse(Quat4d paramQuat4d1, Quat4d paramQuat4d2)
  {
    Quat4d localQuat4d = new Quat4d(paramQuat4d2);
    localQuat4d.inverse();
    mul(paramQuat4d1, localQuat4d);
  }

  public final void mulInverse(Quat4d paramQuat4d)
  {
    Quat4d localQuat4d = new Quat4d(paramQuat4d);
    localQuat4d.inverse();
    mul(localQuat4d);
  }

  public final void inverse(Quat4d paramQuat4d)
  {
    double d = 1.0D / (paramQuat4d.w * paramQuat4d.w + paramQuat4d.x * paramQuat4d.x + paramQuat4d.y * paramQuat4d.y + paramQuat4d.z * paramQuat4d.z);
    this.w = (d * paramQuat4d.w);
    this.x = (-d * paramQuat4d.x);
    this.y = (-d * paramQuat4d.y);
    this.z = (-d * paramQuat4d.z);
  }

  public final void inverse()
  {
    double d = 1.0D / (this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z);
    this.w *= d;
    this.x *= -d;
    this.y *= -d;
    this.z *= -d;
  }

  public final void normalize(Quat4d paramQuat4d)
  {
    double d = paramQuat4d.x * paramQuat4d.x + paramQuat4d.y * paramQuat4d.y + paramQuat4d.z * paramQuat4d.z + paramQuat4d.w * paramQuat4d.w;
    if (d > 0.0D)
    {
      d = 1.0D / Math.sqrt(d);
      this.x = (d * paramQuat4d.x);
      this.y = (d * paramQuat4d.y);
      this.z = (d * paramQuat4d.z);
      this.w = (d * paramQuat4d.w);
    }
    else
    {
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 0.0D;
      this.w = 0.0D;
    }
  }

  public final void normalize()
  {
    double d = this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    if (d > 0.0D)
    {
      d = 1.0D / Math.sqrt(d);
      this.x *= d;
      this.y *= d;
      this.z *= d;
      this.w *= d;
    }
    else
    {
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 0.0D;
      this.w = 0.0D;
    }
  }

  public final void set(Matrix4f paramMatrix4f)
  {
    double d = 0.25D * (paramMatrix4f.m00 + paramMatrix4f.m11 + paramMatrix4f.m22 + paramMatrix4f.m33);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.w = Math.sqrt(d);
        d = 0.25D / this.w;
        this.x = ((paramMatrix4f.m21 - paramMatrix4f.m12) * d);
        this.y = ((paramMatrix4f.m02 - paramMatrix4f.m20) * d);
        this.z = ((paramMatrix4f.m10 - paramMatrix4f.m01) * d);
      }
    }
    else
    {
      this.w = 0.0D;
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 1.0D;
      return;
    }
    this.w = 0.0D;
    d = -0.5D * (paramMatrix4f.m11 + paramMatrix4f.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.x = Math.sqrt(d);
        d = 1.0D / (2.0D * this.x);
        this.y = (paramMatrix4f.m10 * d);
        this.z = (paramMatrix4f.m20 * d);
      }
    }
    else
    {
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 1.0D;
      return;
    }
    this.x = 0.0D;
    d = 0.5D * (1.0D - paramMatrix4f.m22);
    if (d >= 1.E-030D)
    {
      this.y = Math.sqrt(d);
      this.z = (paramMatrix4f.m21 / (2.0D * this.y));
      return;
    }
    this.y = 0.0D;
    this.z = 1.0D;
  }

  public final void set(Matrix4d paramMatrix4d)
  {
    double d = 0.25D * (paramMatrix4d.m00 + paramMatrix4d.m11 + paramMatrix4d.m22 + paramMatrix4d.m33);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.w = Math.sqrt(d);
        d = 0.25D / this.w;
        this.x = ((paramMatrix4d.m21 - paramMatrix4d.m12) * d);
        this.y = ((paramMatrix4d.m02 - paramMatrix4d.m20) * d);
        this.z = ((paramMatrix4d.m10 - paramMatrix4d.m01) * d);
      }
    }
    else
    {
      this.w = 0.0D;
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 1.0D;
      return;
    }
    this.w = 0.0D;
    d = -0.5D * (paramMatrix4d.m11 + paramMatrix4d.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.x = Math.sqrt(d);
        d = 0.5D / this.x;
        this.y = (paramMatrix4d.m10 * d);
        this.z = (paramMatrix4d.m20 * d);
      }
    }
    else
    {
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 1.0D;
      return;
    }
    this.x = 0.0D;
    d = 0.5D * (1.0D - paramMatrix4d.m22);
    if (d >= 1.E-030D)
    {
      this.y = Math.sqrt(d);
      this.z = (paramMatrix4d.m21 / (2.0D * this.y));
      return;
    }
    this.y = 0.0D;
    this.z = 1.0D;
  }

  public final void set(Matrix3f paramMatrix3f)
  {
    double d = 0.25D * (paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22 + 1.0D);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.w = Math.sqrt(d);
        d = 0.25D / this.w;
        this.x = ((paramMatrix3f.m21 - paramMatrix3f.m12) * d);
        this.y = ((paramMatrix3f.m02 - paramMatrix3f.m20) * d);
        this.z = ((paramMatrix3f.m10 - paramMatrix3f.m01) * d);
      }
    }
    else
    {
      this.w = 0.0D;
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 1.0D;
      return;
    }
    this.w = 0.0D;
    d = -0.5D * (paramMatrix3f.m11 + paramMatrix3f.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.x = Math.sqrt(d);
        d = 0.5D / this.x;
        this.y = (paramMatrix3f.m10 * d);
        this.z = (paramMatrix3f.m20 * d);
      }
    }
    else
    {
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 1.0D;
      return;
    }
    this.x = 0.0D;
    d = 0.5D * (1.0D - paramMatrix3f.m22);
    if (d >= 1.E-030D)
    {
      this.y = Math.sqrt(d);
      this.z = (paramMatrix3f.m21 / (2.0D * this.y));
    }
    this.y = 0.0D;
    this.z = 1.0D;
  }

  public final void set(Matrix3d paramMatrix3d)
  {
    double d = 0.25D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 + 1.0D);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.w = Math.sqrt(d);
        d = 0.25D / this.w;
        this.x = ((paramMatrix3d.m21 - paramMatrix3d.m12) * d);
        this.y = ((paramMatrix3d.m02 - paramMatrix3d.m20) * d);
        this.z = ((paramMatrix3d.m10 - paramMatrix3d.m01) * d);
      }
    }
    else
    {
      this.w = 0.0D;
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 1.0D;
      return;
    }
    this.w = 0.0D;
    d = -0.5D * (paramMatrix3d.m11 + paramMatrix3d.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.x = Math.sqrt(d);
        d = 0.5D / this.x;
        this.y = (paramMatrix3d.m10 * d);
        this.z = (paramMatrix3d.m20 * d);
      }
    }
    else
    {
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 1.0D;
      return;
    }
    this.x = 0.0D;
    d = 0.5D * (1.0D - paramMatrix3d.m22);
    if (d >= 1.E-030D)
    {
      this.y = Math.sqrt(d);
      this.z = (paramMatrix3d.m21 / (2.0D * this.y));
      return;
    }
    this.y = 0.0D;
    this.z = 1.0D;
  }

  public final void set(AxisAngle4f paramAxisAngle4f)
  {
    double d2 = Math.sqrt(paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z);
    if (d2 < 1.0E-006D)
    {
      this.w = 0.0D;
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 0.0D;
    }
    else
    {
      double d1 = Math.sin(paramAxisAngle4f.angle / 2.0D);
      d2 = 1.0D / d2;
      this.w = Math.cos(paramAxisAngle4f.angle / 2.0D);
      this.x = (paramAxisAngle4f.x * d2 * d1);
      this.y = (paramAxisAngle4f.y * d2 * d1);
      this.z = (paramAxisAngle4f.z * d2 * d1);
    }
  }

  public final void set(AxisAngle4d paramAxisAngle4d)
  {
    double d2 = Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z);
    if (d2 < 1.0E-006D)
    {
      this.w = 0.0D;
      this.x = 0.0D;
      this.y = 0.0D;
      this.z = 0.0D;
    }
    else
    {
      d2 = 1.0D / d2;
      double d1 = Math.sin(paramAxisAngle4d.angle / 2.0D);
      this.w = Math.cos(paramAxisAngle4d.angle / 2.0D);
      this.x = (paramAxisAngle4d.x * d2 * d1);
      this.y = (paramAxisAngle4d.y * d2 * d1);
      this.z = (paramAxisAngle4d.z * d2 * d1);
    }
  }

  public final void interpolate(Quat4d paramQuat4d, double paramDouble)
  {
    double d1 = this.x * paramQuat4d.x + this.y * paramQuat4d.y + this.z * paramQuat4d.z + this.w * paramQuat4d.w;
    if (d1 < 0.0D)
    {
      paramQuat4d.x = (-paramQuat4d.x);
      paramQuat4d.y = (-paramQuat4d.y);
      paramQuat4d.z = (-paramQuat4d.z);
      paramQuat4d.w = (-paramQuat4d.w);
      d1 = -d1;
    }
    double d2;
    double d3;
    if (1.0D - d1 > 1.0E-006D)
    {
      double d4 = Math.acos(d1);
      double d5 = Math.sin(d4);
      d2 = Math.sin((1.0D - paramDouble) * d4) / d5;
      d3 = Math.sin(paramDouble * d4) / d5;
    }
    else
    {
      d2 = 1.0D - paramDouble;
      d3 = paramDouble;
    }
    this.w = (d2 * this.w + d3 * paramQuat4d.w);
    this.x = (d2 * this.x + d3 * paramQuat4d.x);
    this.y = (d2 * this.y + d3 * paramQuat4d.y);
    this.z = (d2 * this.z + d3 * paramQuat4d.z);
  }

  public final void interpolate(Quat4d paramQuat4d1, Quat4d paramQuat4d2, double paramDouble)
  {
    double d1 = paramQuat4d2.x * paramQuat4d1.x + paramQuat4d2.y * paramQuat4d1.y + paramQuat4d2.z * paramQuat4d1.z + paramQuat4d2.w * paramQuat4d1.w;
    if (d1 < 0.0D)
    {
      paramQuat4d1.x = (-paramQuat4d1.x);
      paramQuat4d1.y = (-paramQuat4d1.y);
      paramQuat4d1.z = (-paramQuat4d1.z);
      paramQuat4d1.w = (-paramQuat4d1.w);
      d1 = -d1;
    }
    double d2;
    double d3;
    if (1.0D - d1 > 1.0E-006D)
    {
      double d4 = Math.acos(d1);
      double d5 = Math.sin(d4);
      d2 = Math.sin((1.0D - paramDouble) * d4) / d5;
      d3 = Math.sin(paramDouble * d4) / d5;
    }
    else
    {
      d2 = 1.0D - paramDouble;
      d3 = paramDouble;
    }
    this.w = (d2 * paramQuat4d1.w + d3 * paramQuat4d2.w);
    this.x = (d2 * paramQuat4d1.x + d3 * paramQuat4d2.x);
    this.y = (d2 * paramQuat4d1.y + d3 * paramQuat4d2.y);
    this.z = (d2 * paramQuat4d1.z + d3 * paramQuat4d2.z);
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Quat4d
 * JD-Core Version:    0.6.2
 */