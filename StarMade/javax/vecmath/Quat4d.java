package javax.vecmath;

import java.io.Serializable;

public class Quat4d
  extends Tuple4d
  implements Serializable
{
  static final long serialVersionUID = 7577479888820201099L;
  static final double EPS = 1.0E-006D;
  static final double EPS2 = 1.E-030D;
  static final double PIO2 = 1.57079632679D;
  
  public Quat4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    double d = 1.0D / Math.sqrt(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2 + paramDouble3 * paramDouble3 + paramDouble4 * paramDouble4);
    this.field_600 = (paramDouble1 * d);
    this.field_601 = (paramDouble2 * d);
    this.field_602 = (paramDouble3 * d);
    this.field_603 = (paramDouble4 * d);
  }
  
  public Quat4d(double[] paramArrayOfDouble)
  {
    double d = 1.0D / Math.sqrt(paramArrayOfDouble[0] * paramArrayOfDouble[0] + paramArrayOfDouble[1] * paramArrayOfDouble[1] + paramArrayOfDouble[2] * paramArrayOfDouble[2] + paramArrayOfDouble[3] * paramArrayOfDouble[3]);
    this.field_600 = (paramArrayOfDouble[0] * d);
    this.field_601 = (paramArrayOfDouble[1] * d);
    this.field_602 = (paramArrayOfDouble[2] * d);
    this.field_603 = (paramArrayOfDouble[3] * d);
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
    double d = 1.0D / Math.sqrt(paramTuple4f.field_596 * paramTuple4f.field_596 + paramTuple4f.field_597 * paramTuple4f.field_597 + paramTuple4f.field_598 * paramTuple4f.field_598 + paramTuple4f.field_599 * paramTuple4f.field_599);
    this.field_600 = (paramTuple4f.field_596 * d);
    this.field_601 = (paramTuple4f.field_597 * d);
    this.field_602 = (paramTuple4f.field_598 * d);
    this.field_603 = (paramTuple4f.field_599 * d);
  }
  
  public Quat4d(Tuple4d paramTuple4d)
  {
    double d = 1.0D / Math.sqrt(paramTuple4d.field_600 * paramTuple4d.field_600 + paramTuple4d.field_601 * paramTuple4d.field_601 + paramTuple4d.field_602 * paramTuple4d.field_602 + paramTuple4d.field_603 * paramTuple4d.field_603);
    this.field_600 = (paramTuple4d.field_600 * d);
    this.field_601 = (paramTuple4d.field_601 * d);
    this.field_602 = (paramTuple4d.field_602 * d);
    this.field_603 = (paramTuple4d.field_603 * d);
  }
  
  public Quat4d() {}
  
  public final void conjugate(Quat4d paramQuat4d)
  {
    this.field_600 = (-paramQuat4d.field_600);
    this.field_601 = (-paramQuat4d.field_601);
    this.field_602 = (-paramQuat4d.field_602);
    this.field_603 = paramQuat4d.field_603;
  }
  
  public final void conjugate()
  {
    this.field_600 = (-this.field_600);
    this.field_601 = (-this.field_601);
    this.field_602 = (-this.field_602);
  }
  
  public final void mul(Quat4d paramQuat4d1, Quat4d paramQuat4d2)
  {
    if ((this != paramQuat4d1) && (this != paramQuat4d2))
    {
      this.field_603 = (paramQuat4d1.field_603 * paramQuat4d2.field_603 - paramQuat4d1.field_600 * paramQuat4d2.field_600 - paramQuat4d1.field_601 * paramQuat4d2.field_601 - paramQuat4d1.field_602 * paramQuat4d2.field_602);
      this.field_600 = (paramQuat4d1.field_603 * paramQuat4d2.field_600 + paramQuat4d2.field_603 * paramQuat4d1.field_600 + paramQuat4d1.field_601 * paramQuat4d2.field_602 - paramQuat4d1.field_602 * paramQuat4d2.field_601);
      this.field_601 = (paramQuat4d1.field_603 * paramQuat4d2.field_601 + paramQuat4d2.field_603 * paramQuat4d1.field_601 - paramQuat4d1.field_600 * paramQuat4d2.field_602 + paramQuat4d1.field_602 * paramQuat4d2.field_600);
      this.field_602 = (paramQuat4d1.field_603 * paramQuat4d2.field_602 + paramQuat4d2.field_603 * paramQuat4d1.field_602 + paramQuat4d1.field_600 * paramQuat4d2.field_601 - paramQuat4d1.field_601 * paramQuat4d2.field_600);
    }
    else
    {
      double d3 = paramQuat4d1.field_603 * paramQuat4d2.field_603 - paramQuat4d1.field_600 * paramQuat4d2.field_600 - paramQuat4d1.field_601 * paramQuat4d2.field_601 - paramQuat4d1.field_602 * paramQuat4d2.field_602;
      double d1 = paramQuat4d1.field_603 * paramQuat4d2.field_600 + paramQuat4d2.field_603 * paramQuat4d1.field_600 + paramQuat4d1.field_601 * paramQuat4d2.field_602 - paramQuat4d1.field_602 * paramQuat4d2.field_601;
      double d2 = paramQuat4d1.field_603 * paramQuat4d2.field_601 + paramQuat4d2.field_603 * paramQuat4d1.field_601 - paramQuat4d1.field_600 * paramQuat4d2.field_602 + paramQuat4d1.field_602 * paramQuat4d2.field_600;
      this.field_602 = (paramQuat4d1.field_603 * paramQuat4d2.field_602 + paramQuat4d2.field_603 * paramQuat4d1.field_602 + paramQuat4d1.field_600 * paramQuat4d2.field_601 - paramQuat4d1.field_601 * paramQuat4d2.field_600);
      this.field_603 = d3;
      this.field_600 = d1;
      this.field_601 = d2;
    }
  }
  
  public final void mul(Quat4d paramQuat4d)
  {
    double d3 = this.field_603 * paramQuat4d.field_603 - this.field_600 * paramQuat4d.field_600 - this.field_601 * paramQuat4d.field_601 - this.field_602 * paramQuat4d.field_602;
    double d1 = this.field_603 * paramQuat4d.field_600 + paramQuat4d.field_603 * this.field_600 + this.field_601 * paramQuat4d.field_602 - this.field_602 * paramQuat4d.field_601;
    double d2 = this.field_603 * paramQuat4d.field_601 + paramQuat4d.field_603 * this.field_601 - this.field_600 * paramQuat4d.field_602 + this.field_602 * paramQuat4d.field_600;
    this.field_602 = (this.field_603 * paramQuat4d.field_602 + paramQuat4d.field_603 * this.field_602 + this.field_600 * paramQuat4d.field_601 - this.field_601 * paramQuat4d.field_600);
    this.field_603 = d3;
    this.field_600 = d1;
    this.field_601 = d2;
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
    double d = 1.0D / (paramQuat4d.field_603 * paramQuat4d.field_603 + paramQuat4d.field_600 * paramQuat4d.field_600 + paramQuat4d.field_601 * paramQuat4d.field_601 + paramQuat4d.field_602 * paramQuat4d.field_602);
    this.field_603 = (d * paramQuat4d.field_603);
    this.field_600 = (-d * paramQuat4d.field_600);
    this.field_601 = (-d * paramQuat4d.field_601);
    this.field_602 = (-d * paramQuat4d.field_602);
  }
  
  public final void inverse()
  {
    double d = 1.0D / (this.field_603 * this.field_603 + this.field_600 * this.field_600 + this.field_601 * this.field_601 + this.field_602 * this.field_602);
    this.field_603 *= d;
    this.field_600 *= -d;
    this.field_601 *= -d;
    this.field_602 *= -d;
  }
  
  public final void normalize(Quat4d paramQuat4d)
  {
    double d = paramQuat4d.field_600 * paramQuat4d.field_600 + paramQuat4d.field_601 * paramQuat4d.field_601 + paramQuat4d.field_602 * paramQuat4d.field_602 + paramQuat4d.field_603 * paramQuat4d.field_603;
    if (d > 0.0D)
    {
      d = 1.0D / Math.sqrt(d);
      this.field_600 = (d * paramQuat4d.field_600);
      this.field_601 = (d * paramQuat4d.field_601);
      this.field_602 = (d * paramQuat4d.field_602);
      this.field_603 = (d * paramQuat4d.field_603);
    }
    else
    {
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 0.0D;
      this.field_603 = 0.0D;
    }
  }
  
  public final void normalize()
  {
    double d = this.field_600 * this.field_600 + this.field_601 * this.field_601 + this.field_602 * this.field_602 + this.field_603 * this.field_603;
    if (d > 0.0D)
    {
      d = 1.0D / Math.sqrt(d);
      this.field_600 *= d;
      this.field_601 *= d;
      this.field_602 *= d;
      this.field_603 *= d;
    }
    else
    {
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 0.0D;
      this.field_603 = 0.0D;
    }
  }
  
  public final void set(Matrix4f paramMatrix4f)
  {
    double d = 0.25D * (paramMatrix4f.m00 + paramMatrix4f.m11 + paramMatrix4f.m22 + paramMatrix4f.m33);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_603 = Math.sqrt(d);
        d = 0.25D / this.field_603;
        this.field_600 = ((paramMatrix4f.m21 - paramMatrix4f.m12) * d);
        this.field_601 = ((paramMatrix4f.m02 - paramMatrix4f.m20) * d);
        this.field_602 = ((paramMatrix4f.m10 - paramMatrix4f.m01) * d);
      }
    }
    else
    {
      this.field_603 = 0.0D;
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 1.0D;
      return;
    }
    this.field_603 = 0.0D;
    d = -0.5D * (paramMatrix4f.m11 + paramMatrix4f.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_600 = Math.sqrt(d);
        d = 1.0D / (2.0D * this.field_600);
        this.field_601 = (paramMatrix4f.m10 * d);
        this.field_602 = (paramMatrix4f.m20 * d);
      }
    }
    else
    {
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 1.0D;
      return;
    }
    this.field_600 = 0.0D;
    d = 0.5D * (1.0D - paramMatrix4f.m22);
    if (d >= 1.E-030D)
    {
      this.field_601 = Math.sqrt(d);
      this.field_602 = (paramMatrix4f.m21 / (2.0D * this.field_601));
      return;
    }
    this.field_601 = 0.0D;
    this.field_602 = 1.0D;
  }
  
  public final void set(Matrix4d paramMatrix4d)
  {
    double d = 0.25D * (paramMatrix4d.m00 + paramMatrix4d.m11 + paramMatrix4d.m22 + paramMatrix4d.m33);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_603 = Math.sqrt(d);
        d = 0.25D / this.field_603;
        this.field_600 = ((paramMatrix4d.m21 - paramMatrix4d.m12) * d);
        this.field_601 = ((paramMatrix4d.m02 - paramMatrix4d.m20) * d);
        this.field_602 = ((paramMatrix4d.m10 - paramMatrix4d.m01) * d);
      }
    }
    else
    {
      this.field_603 = 0.0D;
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 1.0D;
      return;
    }
    this.field_603 = 0.0D;
    d = -0.5D * (paramMatrix4d.m11 + paramMatrix4d.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_600 = Math.sqrt(d);
        d = 0.5D / this.field_600;
        this.field_601 = (paramMatrix4d.m10 * d);
        this.field_602 = (paramMatrix4d.m20 * d);
      }
    }
    else
    {
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 1.0D;
      return;
    }
    this.field_600 = 0.0D;
    d = 0.5D * (1.0D - paramMatrix4d.m22);
    if (d >= 1.E-030D)
    {
      this.field_601 = Math.sqrt(d);
      this.field_602 = (paramMatrix4d.m21 / (2.0D * this.field_601));
      return;
    }
    this.field_601 = 0.0D;
    this.field_602 = 1.0D;
  }
  
  public final void set(Matrix3f paramMatrix3f)
  {
    double d = 0.25D * (paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22 + 1.0D);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_603 = Math.sqrt(d);
        d = 0.25D / this.field_603;
        this.field_600 = ((paramMatrix3f.m21 - paramMatrix3f.m12) * d);
        this.field_601 = ((paramMatrix3f.m02 - paramMatrix3f.m20) * d);
        this.field_602 = ((paramMatrix3f.m10 - paramMatrix3f.m01) * d);
      }
    }
    else
    {
      this.field_603 = 0.0D;
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 1.0D;
      return;
    }
    this.field_603 = 0.0D;
    d = -0.5D * (paramMatrix3f.m11 + paramMatrix3f.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_600 = Math.sqrt(d);
        d = 0.5D / this.field_600;
        this.field_601 = (paramMatrix3f.m10 * d);
        this.field_602 = (paramMatrix3f.m20 * d);
      }
    }
    else
    {
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 1.0D;
      return;
    }
    this.field_600 = 0.0D;
    d = 0.5D * (1.0D - paramMatrix3f.m22);
    if (d >= 1.E-030D)
    {
      this.field_601 = Math.sqrt(d);
      this.field_602 = (paramMatrix3f.m21 / (2.0D * this.field_601));
    }
    this.field_601 = 0.0D;
    this.field_602 = 1.0D;
  }
  
  public final void set(Matrix3d paramMatrix3d)
  {
    double d = 0.25D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 + 1.0D);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_603 = Math.sqrt(d);
        d = 0.25D / this.field_603;
        this.field_600 = ((paramMatrix3d.m21 - paramMatrix3d.m12) * d);
        this.field_601 = ((paramMatrix3d.m02 - paramMatrix3d.m20) * d);
        this.field_602 = ((paramMatrix3d.m10 - paramMatrix3d.m01) * d);
      }
    }
    else
    {
      this.field_603 = 0.0D;
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 1.0D;
      return;
    }
    this.field_603 = 0.0D;
    d = -0.5D * (paramMatrix3d.m11 + paramMatrix3d.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_600 = Math.sqrt(d);
        d = 0.5D / this.field_600;
        this.field_601 = (paramMatrix3d.m10 * d);
        this.field_602 = (paramMatrix3d.m20 * d);
      }
    }
    else
    {
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 1.0D;
      return;
    }
    this.field_600 = 0.0D;
    d = 0.5D * (1.0D - paramMatrix3d.m22);
    if (d >= 1.E-030D)
    {
      this.field_601 = Math.sqrt(d);
      this.field_602 = (paramMatrix3d.m21 / (2.0D * this.field_601));
      return;
    }
    this.field_601 = 0.0D;
    this.field_602 = 1.0D;
  }
  
  public final void set(AxisAngle4f paramAxisAngle4f)
  {
    double d2 = Math.sqrt(paramAxisAngle4f.field_586 * paramAxisAngle4f.field_586 + paramAxisAngle4f.field_587 * paramAxisAngle4f.field_587 + paramAxisAngle4f.field_588 * paramAxisAngle4f.field_588);
    if (d2 < 1.0E-006D)
    {
      this.field_603 = 0.0D;
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 0.0D;
    }
    else
    {
      double d1 = Math.sin(paramAxisAngle4f.angle / 2.0D);
      d2 = 1.0D / d2;
      this.field_603 = Math.cos(paramAxisAngle4f.angle / 2.0D);
      this.field_600 = (paramAxisAngle4f.field_586 * d2 * d1);
      this.field_601 = (paramAxisAngle4f.field_587 * d2 * d1);
      this.field_602 = (paramAxisAngle4f.field_588 * d2 * d1);
    }
  }
  
  public final void set(AxisAngle4d paramAxisAngle4d)
  {
    double d2 = Math.sqrt(paramAxisAngle4d.field_589 * paramAxisAngle4d.field_589 + paramAxisAngle4d.field_590 * paramAxisAngle4d.field_590 + paramAxisAngle4d.field_591 * paramAxisAngle4d.field_591);
    if (d2 < 1.0E-006D)
    {
      this.field_603 = 0.0D;
      this.field_600 = 0.0D;
      this.field_601 = 0.0D;
      this.field_602 = 0.0D;
    }
    else
    {
      d2 = 1.0D / d2;
      double d1 = Math.sin(paramAxisAngle4d.angle / 2.0D);
      this.field_603 = Math.cos(paramAxisAngle4d.angle / 2.0D);
      this.field_600 = (paramAxisAngle4d.field_589 * d2 * d1);
      this.field_601 = (paramAxisAngle4d.field_590 * d2 * d1);
      this.field_602 = (paramAxisAngle4d.field_591 * d2 * d1);
    }
  }
  
  public final void interpolate(Quat4d paramQuat4d, double paramDouble)
  {
    double d1 = this.field_600 * paramQuat4d.field_600 + this.field_601 * paramQuat4d.field_601 + this.field_602 * paramQuat4d.field_602 + this.field_603 * paramQuat4d.field_603;
    if (d1 < 0.0D)
    {
      paramQuat4d.field_600 = (-paramQuat4d.field_600);
      paramQuat4d.field_601 = (-paramQuat4d.field_601);
      paramQuat4d.field_602 = (-paramQuat4d.field_602);
      paramQuat4d.field_603 = (-paramQuat4d.field_603);
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
    this.field_603 = (d2 * this.field_603 + d3 * paramQuat4d.field_603);
    this.field_600 = (d2 * this.field_600 + d3 * paramQuat4d.field_600);
    this.field_601 = (d2 * this.field_601 + d3 * paramQuat4d.field_601);
    this.field_602 = (d2 * this.field_602 + d3 * paramQuat4d.field_602);
  }
  
  public final void interpolate(Quat4d paramQuat4d1, Quat4d paramQuat4d2, double paramDouble)
  {
    double d1 = paramQuat4d2.field_600 * paramQuat4d1.field_600 + paramQuat4d2.field_601 * paramQuat4d1.field_601 + paramQuat4d2.field_602 * paramQuat4d1.field_602 + paramQuat4d2.field_603 * paramQuat4d1.field_603;
    if (d1 < 0.0D)
    {
      paramQuat4d1.field_600 = (-paramQuat4d1.field_600);
      paramQuat4d1.field_601 = (-paramQuat4d1.field_601);
      paramQuat4d1.field_602 = (-paramQuat4d1.field_602);
      paramQuat4d1.field_603 = (-paramQuat4d1.field_603);
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
    this.field_603 = (d2 * paramQuat4d1.field_603 + d3 * paramQuat4d2.field_603);
    this.field_600 = (d2 * paramQuat4d1.field_600 + d3 * paramQuat4d2.field_600);
    this.field_601 = (d2 * paramQuat4d1.field_601 + d3 * paramQuat4d2.field_601);
    this.field_602 = (d2 * paramQuat4d1.field_602 + d3 * paramQuat4d2.field_602);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Quat4d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */