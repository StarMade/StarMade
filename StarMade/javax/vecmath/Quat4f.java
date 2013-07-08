package javax.vecmath;

import java.io.Serializable;

public class Quat4f
  extends Tuple4f
  implements Serializable
{
  static final long serialVersionUID = 2675933778405442383L;
  static final double EPS = 1.0E-006D;
  static final double EPS2 = 1.E-030D;
  static final double PIO2 = 1.57079632679D;
  
  public Quat4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    float f = (float)(1.0D / Math.sqrt(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2 + paramFloat3 * paramFloat3 + paramFloat4 * paramFloat4));
    this.field_596 = (paramFloat1 * f);
    this.field_597 = (paramFloat2 * f);
    this.field_598 = (paramFloat3 * f);
    this.field_599 = (paramFloat4 * f);
  }
  
  public Quat4f(float[] paramArrayOfFloat)
  {
    float f = (float)(1.0D / Math.sqrt(paramArrayOfFloat[0] * paramArrayOfFloat[0] + paramArrayOfFloat[1] * paramArrayOfFloat[1] + paramArrayOfFloat[2] * paramArrayOfFloat[2] + paramArrayOfFloat[3] * paramArrayOfFloat[3]));
    this.field_596 = (paramArrayOfFloat[0] * f);
    this.field_597 = (paramArrayOfFloat[1] * f);
    this.field_598 = (paramArrayOfFloat[2] * f);
    this.field_599 = (paramArrayOfFloat[3] * f);
  }
  
  public Quat4f(Quat4f paramQuat4f)
  {
    super(paramQuat4f);
  }
  
  public Quat4f(Quat4d paramQuat4d)
  {
    super(paramQuat4d);
  }
  
  public Quat4f(Tuple4f paramTuple4f)
  {
    float f = (float)(1.0D / Math.sqrt(paramTuple4f.field_596 * paramTuple4f.field_596 + paramTuple4f.field_597 * paramTuple4f.field_597 + paramTuple4f.field_598 * paramTuple4f.field_598 + paramTuple4f.field_599 * paramTuple4f.field_599));
    this.field_596 = (paramTuple4f.field_596 * f);
    this.field_597 = (paramTuple4f.field_597 * f);
    this.field_598 = (paramTuple4f.field_598 * f);
    this.field_599 = (paramTuple4f.field_599 * f);
  }
  
  public Quat4f(Tuple4d paramTuple4d)
  {
    double d = 1.0D / Math.sqrt(paramTuple4d.field_600 * paramTuple4d.field_600 + paramTuple4d.field_601 * paramTuple4d.field_601 + paramTuple4d.field_602 * paramTuple4d.field_602 + paramTuple4d.field_603 * paramTuple4d.field_603);
    this.field_596 = ((float)(paramTuple4d.field_600 * d));
    this.field_597 = ((float)(paramTuple4d.field_601 * d));
    this.field_598 = ((float)(paramTuple4d.field_602 * d));
    this.field_599 = ((float)(paramTuple4d.field_603 * d));
  }
  
  public Quat4f() {}
  
  public final void conjugate(Quat4f paramQuat4f)
  {
    this.field_596 = (-paramQuat4f.field_596);
    this.field_597 = (-paramQuat4f.field_597);
    this.field_598 = (-paramQuat4f.field_598);
    this.field_599 = paramQuat4f.field_599;
  }
  
  public final void conjugate()
  {
    this.field_596 = (-this.field_596);
    this.field_597 = (-this.field_597);
    this.field_598 = (-this.field_598);
  }
  
  public final void mul(Quat4f paramQuat4f1, Quat4f paramQuat4f2)
  {
    if ((this != paramQuat4f1) && (this != paramQuat4f2))
    {
      this.field_599 = (paramQuat4f1.field_599 * paramQuat4f2.field_599 - paramQuat4f1.field_596 * paramQuat4f2.field_596 - paramQuat4f1.field_597 * paramQuat4f2.field_597 - paramQuat4f1.field_598 * paramQuat4f2.field_598);
      this.field_596 = (paramQuat4f1.field_599 * paramQuat4f2.field_596 + paramQuat4f2.field_599 * paramQuat4f1.field_596 + paramQuat4f1.field_597 * paramQuat4f2.field_598 - paramQuat4f1.field_598 * paramQuat4f2.field_597);
      this.field_597 = (paramQuat4f1.field_599 * paramQuat4f2.field_597 + paramQuat4f2.field_599 * paramQuat4f1.field_597 - paramQuat4f1.field_596 * paramQuat4f2.field_598 + paramQuat4f1.field_598 * paramQuat4f2.field_596);
      this.field_598 = (paramQuat4f1.field_599 * paramQuat4f2.field_598 + paramQuat4f2.field_599 * paramQuat4f1.field_598 + paramQuat4f1.field_596 * paramQuat4f2.field_597 - paramQuat4f1.field_597 * paramQuat4f2.field_596);
    }
    else
    {
      float f3 = paramQuat4f1.field_599 * paramQuat4f2.field_599 - paramQuat4f1.field_596 * paramQuat4f2.field_596 - paramQuat4f1.field_597 * paramQuat4f2.field_597 - paramQuat4f1.field_598 * paramQuat4f2.field_598;
      float f1 = paramQuat4f1.field_599 * paramQuat4f2.field_596 + paramQuat4f2.field_599 * paramQuat4f1.field_596 + paramQuat4f1.field_597 * paramQuat4f2.field_598 - paramQuat4f1.field_598 * paramQuat4f2.field_597;
      float f2 = paramQuat4f1.field_599 * paramQuat4f2.field_597 + paramQuat4f2.field_599 * paramQuat4f1.field_597 - paramQuat4f1.field_596 * paramQuat4f2.field_598 + paramQuat4f1.field_598 * paramQuat4f2.field_596;
      this.field_598 = (paramQuat4f1.field_599 * paramQuat4f2.field_598 + paramQuat4f2.field_599 * paramQuat4f1.field_598 + paramQuat4f1.field_596 * paramQuat4f2.field_597 - paramQuat4f1.field_597 * paramQuat4f2.field_596);
      this.field_599 = f3;
      this.field_596 = f1;
      this.field_597 = f2;
    }
  }
  
  public final void mul(Quat4f paramQuat4f)
  {
    float f3 = this.field_599 * paramQuat4f.field_599 - this.field_596 * paramQuat4f.field_596 - this.field_597 * paramQuat4f.field_597 - this.field_598 * paramQuat4f.field_598;
    float f1 = this.field_599 * paramQuat4f.field_596 + paramQuat4f.field_599 * this.field_596 + this.field_597 * paramQuat4f.field_598 - this.field_598 * paramQuat4f.field_597;
    float f2 = this.field_599 * paramQuat4f.field_597 + paramQuat4f.field_599 * this.field_597 - this.field_596 * paramQuat4f.field_598 + this.field_598 * paramQuat4f.field_596;
    this.field_598 = (this.field_599 * paramQuat4f.field_598 + paramQuat4f.field_599 * this.field_598 + this.field_596 * paramQuat4f.field_597 - this.field_597 * paramQuat4f.field_596);
    this.field_599 = f3;
    this.field_596 = f1;
    this.field_597 = f2;
  }
  
  public final void mulInverse(Quat4f paramQuat4f1, Quat4f paramQuat4f2)
  {
    Quat4f localQuat4f = new Quat4f(paramQuat4f2);
    localQuat4f.inverse();
    mul(paramQuat4f1, localQuat4f);
  }
  
  public final void mulInverse(Quat4f paramQuat4f)
  {
    Quat4f localQuat4f = new Quat4f(paramQuat4f);
    localQuat4f.inverse();
    mul(localQuat4f);
  }
  
  public final void inverse(Quat4f paramQuat4f)
  {
    float f = 1.0F / (paramQuat4f.field_599 * paramQuat4f.field_599 + paramQuat4f.field_596 * paramQuat4f.field_596 + paramQuat4f.field_597 * paramQuat4f.field_597 + paramQuat4f.field_598 * paramQuat4f.field_598);
    this.field_599 = (f * paramQuat4f.field_599);
    this.field_596 = (-f * paramQuat4f.field_596);
    this.field_597 = (-f * paramQuat4f.field_597);
    this.field_598 = (-f * paramQuat4f.field_598);
  }
  
  public final void inverse()
  {
    float f = 1.0F / (this.field_599 * this.field_599 + this.field_596 * this.field_596 + this.field_597 * this.field_597 + this.field_598 * this.field_598);
    this.field_599 *= f;
    this.field_596 *= -f;
    this.field_597 *= -f;
    this.field_598 *= -f;
  }
  
  public final void normalize(Quat4f paramQuat4f)
  {
    float f = paramQuat4f.field_596 * paramQuat4f.field_596 + paramQuat4f.field_597 * paramQuat4f.field_597 + paramQuat4f.field_598 * paramQuat4f.field_598 + paramQuat4f.field_599 * paramQuat4f.field_599;
    if (f > 0.0F)
    {
      f = 1.0F / (float)Math.sqrt(f);
      this.field_596 = (f * paramQuat4f.field_596);
      this.field_597 = (f * paramQuat4f.field_597);
      this.field_598 = (f * paramQuat4f.field_598);
      this.field_599 = (f * paramQuat4f.field_599);
    }
    else
    {
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 0.0F;
      this.field_599 = 0.0F;
    }
  }
  
  public final void normalize()
  {
    float f = this.field_596 * this.field_596 + this.field_597 * this.field_597 + this.field_598 * this.field_598 + this.field_599 * this.field_599;
    if (f > 0.0F)
    {
      f = 1.0F / (float)Math.sqrt(f);
      this.field_596 *= f;
      this.field_597 *= f;
      this.field_598 *= f;
      this.field_599 *= f;
    }
    else
    {
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 0.0F;
      this.field_599 = 0.0F;
    }
  }
  
  public final void set(Matrix4f paramMatrix4f)
  {
    float f = 0.25F * (paramMatrix4f.m00 + paramMatrix4f.m11 + paramMatrix4f.m22 + paramMatrix4f.m33);
    if (f >= 0.0F)
    {
      if (f >= 1.E-030D)
      {
        this.field_599 = ((float)Math.sqrt(f));
        f = 0.25F / this.field_599;
        this.field_596 = ((paramMatrix4f.m21 - paramMatrix4f.m12) * f);
        this.field_597 = ((paramMatrix4f.m02 - paramMatrix4f.m20) * f);
        this.field_598 = ((paramMatrix4f.m10 - paramMatrix4f.m01) * f);
      }
    }
    else
    {
      this.field_599 = 0.0F;
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 1.0F;
      return;
    }
    this.field_599 = 0.0F;
    f = -0.5F * (paramMatrix4f.m11 + paramMatrix4f.m22);
    if (f >= 0.0F)
    {
      if (f >= 1.E-030D)
      {
        this.field_596 = ((float)Math.sqrt(f));
        f = 1.0F / (2.0F * this.field_596);
        this.field_597 = (paramMatrix4f.m10 * f);
        this.field_598 = (paramMatrix4f.m20 * f);
      }
    }
    else
    {
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 1.0F;
      return;
    }
    this.field_596 = 0.0F;
    f = 0.5F * (1.0F - paramMatrix4f.m22);
    if (f >= 1.E-030D)
    {
      this.field_597 = ((float)Math.sqrt(f));
      this.field_598 = (paramMatrix4f.m21 / (2.0F * this.field_597));
      return;
    }
    this.field_597 = 0.0F;
    this.field_598 = 1.0F;
  }
  
  public final void set(Matrix4d paramMatrix4d)
  {
    double d = 0.25D * (paramMatrix4d.m00 + paramMatrix4d.m11 + paramMatrix4d.m22 + paramMatrix4d.m33);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_599 = ((float)Math.sqrt(d));
        d = 0.25D / this.field_599;
        this.field_596 = ((float)((paramMatrix4d.m21 - paramMatrix4d.m12) * d));
        this.field_597 = ((float)((paramMatrix4d.m02 - paramMatrix4d.m20) * d));
        this.field_598 = ((float)((paramMatrix4d.m10 - paramMatrix4d.m01) * d));
      }
    }
    else
    {
      this.field_599 = 0.0F;
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 1.0F;
      return;
    }
    this.field_599 = 0.0F;
    d = -0.5D * (paramMatrix4d.m11 + paramMatrix4d.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_596 = ((float)Math.sqrt(d));
        d = 0.5D / this.field_596;
        this.field_597 = ((float)(paramMatrix4d.m10 * d));
        this.field_598 = ((float)(paramMatrix4d.m20 * d));
      }
    }
    else
    {
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 1.0F;
      return;
    }
    this.field_596 = 0.0F;
    d = 0.5D * (1.0D - paramMatrix4d.m22);
    if (d >= 1.E-030D)
    {
      this.field_597 = ((float)Math.sqrt(d));
      this.field_598 = ((float)(paramMatrix4d.m21 / (2.0D * this.field_597)));
      return;
    }
    this.field_597 = 0.0F;
    this.field_598 = 1.0F;
  }
  
  public final void set(Matrix3f paramMatrix3f)
  {
    float f = 0.25F * (paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22 + 1.0F);
    if (f >= 0.0F)
    {
      if (f >= 1.E-030D)
      {
        this.field_599 = ((float)Math.sqrt(f));
        f = 0.25F / this.field_599;
        this.field_596 = ((paramMatrix3f.m21 - paramMatrix3f.m12) * f);
        this.field_597 = ((paramMatrix3f.m02 - paramMatrix3f.m20) * f);
        this.field_598 = ((paramMatrix3f.m10 - paramMatrix3f.m01) * f);
      }
    }
    else
    {
      this.field_599 = 0.0F;
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 1.0F;
      return;
    }
    this.field_599 = 0.0F;
    f = -0.5F * (paramMatrix3f.m11 + paramMatrix3f.m22);
    if (f >= 0.0F)
    {
      if (f >= 1.E-030D)
      {
        this.field_596 = ((float)Math.sqrt(f));
        f = 0.5F / this.field_596;
        this.field_597 = (paramMatrix3f.m10 * f);
        this.field_598 = (paramMatrix3f.m20 * f);
      }
    }
    else
    {
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 1.0F;
      return;
    }
    this.field_596 = 0.0F;
    f = 0.5F * (1.0F - paramMatrix3f.m22);
    if (f >= 1.E-030D)
    {
      this.field_597 = ((float)Math.sqrt(f));
      this.field_598 = (paramMatrix3f.m21 / (2.0F * this.field_597));
      return;
    }
    this.field_597 = 0.0F;
    this.field_598 = 1.0F;
  }
  
  public final void set(Matrix3d paramMatrix3d)
  {
    double d = 0.25D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 + 1.0D);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_599 = ((float)Math.sqrt(d));
        d = 0.25D / this.field_599;
        this.field_596 = ((float)((paramMatrix3d.m21 - paramMatrix3d.m12) * d));
        this.field_597 = ((float)((paramMatrix3d.m02 - paramMatrix3d.m20) * d));
        this.field_598 = ((float)((paramMatrix3d.m10 - paramMatrix3d.m01) * d));
      }
    }
    else
    {
      this.field_599 = 0.0F;
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 1.0F;
      return;
    }
    this.field_599 = 0.0F;
    d = -0.5D * (paramMatrix3d.m11 + paramMatrix3d.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.field_596 = ((float)Math.sqrt(d));
        d = 0.5D / this.field_596;
        this.field_597 = ((float)(paramMatrix3d.m10 * d));
        this.field_598 = ((float)(paramMatrix3d.m20 * d));
      }
    }
    else
    {
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 1.0F;
      return;
    }
    this.field_596 = 0.0F;
    d = 0.5D * (1.0D - paramMatrix3d.m22);
    if (d >= 1.E-030D)
    {
      this.field_597 = ((float)Math.sqrt(d));
      this.field_598 = ((float)(paramMatrix3d.m21 / (2.0D * this.field_597)));
      return;
    }
    this.field_597 = 0.0F;
    this.field_598 = 1.0F;
  }
  
  public final void set(AxisAngle4f paramAxisAngle4f)
  {
    float f2 = (float)Math.sqrt(paramAxisAngle4f.field_586 * paramAxisAngle4f.field_586 + paramAxisAngle4f.field_587 * paramAxisAngle4f.field_587 + paramAxisAngle4f.field_588 * paramAxisAngle4f.field_588);
    if (f2 < 1.0E-006D)
    {
      this.field_599 = 0.0F;
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 0.0F;
    }
    else
    {
      f2 = 1.0F / f2;
      float f1 = (float)Math.sin(paramAxisAngle4f.angle / 2.0D);
      this.field_599 = ((float)Math.cos(paramAxisAngle4f.angle / 2.0D));
      this.field_596 = (paramAxisAngle4f.field_586 * f2 * f1);
      this.field_597 = (paramAxisAngle4f.field_587 * f2 * f1);
      this.field_598 = (paramAxisAngle4f.field_588 * f2 * f1);
    }
  }
  
  public final void set(AxisAngle4d paramAxisAngle4d)
  {
    float f2 = (float)(1.0D / Math.sqrt(paramAxisAngle4d.field_589 * paramAxisAngle4d.field_589 + paramAxisAngle4d.field_590 * paramAxisAngle4d.field_590 + paramAxisAngle4d.field_591 * paramAxisAngle4d.field_591));
    if (f2 < 1.0E-006D)
    {
      this.field_599 = 0.0F;
      this.field_596 = 0.0F;
      this.field_597 = 0.0F;
      this.field_598 = 0.0F;
    }
    else
    {
      f2 = 1.0F / f2;
      float f1 = (float)Math.sin(paramAxisAngle4d.angle / 2.0D);
      this.field_599 = ((float)Math.cos(paramAxisAngle4d.angle / 2.0D));
      this.field_596 = ((float)paramAxisAngle4d.field_589 * f2 * f1);
      this.field_597 = ((float)paramAxisAngle4d.field_590 * f2 * f1);
      this.field_598 = ((float)paramAxisAngle4d.field_591 * f2 * f1);
    }
  }
  
  public final void interpolate(Quat4f paramQuat4f, float paramFloat)
  {
    double d1 = this.field_596 * paramQuat4f.field_596 + this.field_597 * paramQuat4f.field_597 + this.field_598 * paramQuat4f.field_598 + this.field_599 * paramQuat4f.field_599;
    if (d1 < 0.0D)
    {
      paramQuat4f.field_596 = (-paramQuat4f.field_596);
      paramQuat4f.field_597 = (-paramQuat4f.field_597);
      paramQuat4f.field_598 = (-paramQuat4f.field_598);
      paramQuat4f.field_599 = (-paramQuat4f.field_599);
      d1 = -d1;
    }
    double d2;
    double d3;
    if (1.0D - d1 > 1.0E-006D)
    {
      double d4 = Math.acos(d1);
      double d5 = Math.sin(d4);
      d2 = Math.sin((1.0D - paramFloat) * d4) / d5;
      d3 = Math.sin(paramFloat * d4) / d5;
    }
    else
    {
      d2 = 1.0D - paramFloat;
      d3 = paramFloat;
    }
    this.field_599 = ((float)(d2 * this.field_599 + d3 * paramQuat4f.field_599));
    this.field_596 = ((float)(d2 * this.field_596 + d3 * paramQuat4f.field_596));
    this.field_597 = ((float)(d2 * this.field_597 + d3 * paramQuat4f.field_597));
    this.field_598 = ((float)(d2 * this.field_598 + d3 * paramQuat4f.field_598));
  }
  
  public final void interpolate(Quat4f paramQuat4f1, Quat4f paramQuat4f2, float paramFloat)
  {
    double d1 = paramQuat4f2.field_596 * paramQuat4f1.field_596 + paramQuat4f2.field_597 * paramQuat4f1.field_597 + paramQuat4f2.field_598 * paramQuat4f1.field_598 + paramQuat4f2.field_599 * paramQuat4f1.field_599;
    if (d1 < 0.0D)
    {
      paramQuat4f1.field_596 = (-paramQuat4f1.field_596);
      paramQuat4f1.field_597 = (-paramQuat4f1.field_597);
      paramQuat4f1.field_598 = (-paramQuat4f1.field_598);
      paramQuat4f1.field_599 = (-paramQuat4f1.field_599);
      d1 = -d1;
    }
    double d2;
    double d3;
    if (1.0D - d1 > 1.0E-006D)
    {
      double d4 = Math.acos(d1);
      double d5 = Math.sin(d4);
      d2 = Math.sin((1.0D - paramFloat) * d4) / d5;
      d3 = Math.sin(paramFloat * d4) / d5;
    }
    else
    {
      d2 = 1.0D - paramFloat;
      d3 = paramFloat;
    }
    this.field_599 = ((float)(d2 * paramQuat4f1.field_599 + d3 * paramQuat4f2.field_599));
    this.field_596 = ((float)(d2 * paramQuat4f1.field_596 + d3 * paramQuat4f2.field_596));
    this.field_597 = ((float)(d2 * paramQuat4f1.field_597 + d3 * paramQuat4f2.field_597));
    this.field_598 = ((float)(d2 * paramQuat4f1.field_598 + d3 * paramQuat4f2.field_598));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     javax.vecmath.Quat4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */