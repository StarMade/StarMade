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
    this.x = (paramFloat1 * f);
    this.y = (paramFloat2 * f);
    this.z = (paramFloat3 * f);
    this.w = (paramFloat4 * f);
  }
  
  public Quat4f(float[] paramArrayOfFloat)
  {
    float f = (float)(1.0D / Math.sqrt(paramArrayOfFloat[0] * paramArrayOfFloat[0] + paramArrayOfFloat[1] * paramArrayOfFloat[1] + paramArrayOfFloat[2] * paramArrayOfFloat[2] + paramArrayOfFloat[3] * paramArrayOfFloat[3]));
    this.x = (paramArrayOfFloat[0] * f);
    this.y = (paramArrayOfFloat[1] * f);
    this.z = (paramArrayOfFloat[2] * f);
    this.w = (paramArrayOfFloat[3] * f);
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
    float f = (float)(1.0D / Math.sqrt(paramTuple4f.x * paramTuple4f.x + paramTuple4f.y * paramTuple4f.y + paramTuple4f.z * paramTuple4f.z + paramTuple4f.w * paramTuple4f.w));
    this.x = (paramTuple4f.x * f);
    this.y = (paramTuple4f.y * f);
    this.z = (paramTuple4f.z * f);
    this.w = (paramTuple4f.w * f);
  }
  
  public Quat4f(Tuple4d paramTuple4d)
  {
    double d = 1.0D / Math.sqrt(paramTuple4d.x * paramTuple4d.x + paramTuple4d.y * paramTuple4d.y + paramTuple4d.z * paramTuple4d.z + paramTuple4d.w * paramTuple4d.w);
    this.x = ((float)(paramTuple4d.x * d));
    this.y = ((float)(paramTuple4d.y * d));
    this.z = ((float)(paramTuple4d.z * d));
    this.w = ((float)(paramTuple4d.w * d));
  }
  
  public Quat4f() {}
  
  public final void conjugate(Quat4f paramQuat4f)
  {
    this.x = (-paramQuat4f.x);
    this.y = (-paramQuat4f.y);
    this.z = (-paramQuat4f.z);
    this.w = paramQuat4f.w;
  }
  
  public final void conjugate()
  {
    this.x = (-this.x);
    this.y = (-this.y);
    this.z = (-this.z);
  }
  
  public final void mul(Quat4f paramQuat4f1, Quat4f paramQuat4f2)
  {
    if ((this != paramQuat4f1) && (this != paramQuat4f2))
    {
      this.w = (paramQuat4f1.w * paramQuat4f2.w - paramQuat4f1.x * paramQuat4f2.x - paramQuat4f1.y * paramQuat4f2.y - paramQuat4f1.z * paramQuat4f2.z);
      this.x = (paramQuat4f1.w * paramQuat4f2.x + paramQuat4f2.w * paramQuat4f1.x + paramQuat4f1.y * paramQuat4f2.z - paramQuat4f1.z * paramQuat4f2.y);
      this.y = (paramQuat4f1.w * paramQuat4f2.y + paramQuat4f2.w * paramQuat4f1.y - paramQuat4f1.x * paramQuat4f2.z + paramQuat4f1.z * paramQuat4f2.x);
      this.z = (paramQuat4f1.w * paramQuat4f2.z + paramQuat4f2.w * paramQuat4f1.z + paramQuat4f1.x * paramQuat4f2.y - paramQuat4f1.y * paramQuat4f2.x);
    }
    else
    {
      float f3 = paramQuat4f1.w * paramQuat4f2.w - paramQuat4f1.x * paramQuat4f2.x - paramQuat4f1.y * paramQuat4f2.y - paramQuat4f1.z * paramQuat4f2.z;
      float f1 = paramQuat4f1.w * paramQuat4f2.x + paramQuat4f2.w * paramQuat4f1.x + paramQuat4f1.y * paramQuat4f2.z - paramQuat4f1.z * paramQuat4f2.y;
      float f2 = paramQuat4f1.w * paramQuat4f2.y + paramQuat4f2.w * paramQuat4f1.y - paramQuat4f1.x * paramQuat4f2.z + paramQuat4f1.z * paramQuat4f2.x;
      this.z = (paramQuat4f1.w * paramQuat4f2.z + paramQuat4f2.w * paramQuat4f1.z + paramQuat4f1.x * paramQuat4f2.y - paramQuat4f1.y * paramQuat4f2.x);
      this.w = f3;
      this.x = f1;
      this.y = f2;
    }
  }
  
  public final void mul(Quat4f paramQuat4f)
  {
    float f3 = this.w * paramQuat4f.w - this.x * paramQuat4f.x - this.y * paramQuat4f.y - this.z * paramQuat4f.z;
    float f1 = this.w * paramQuat4f.x + paramQuat4f.w * this.x + this.y * paramQuat4f.z - this.z * paramQuat4f.y;
    float f2 = this.w * paramQuat4f.y + paramQuat4f.w * this.y - this.x * paramQuat4f.z + this.z * paramQuat4f.x;
    this.z = (this.w * paramQuat4f.z + paramQuat4f.w * this.z + this.x * paramQuat4f.y - this.y * paramQuat4f.x);
    this.w = f3;
    this.x = f1;
    this.y = f2;
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
    float f = 1.0F / (paramQuat4f.w * paramQuat4f.w + paramQuat4f.x * paramQuat4f.x + paramQuat4f.y * paramQuat4f.y + paramQuat4f.z * paramQuat4f.z);
    this.w = (f * paramQuat4f.w);
    this.x = (-f * paramQuat4f.x);
    this.y = (-f * paramQuat4f.y);
    this.z = (-f * paramQuat4f.z);
  }
  
  public final void inverse()
  {
    float f = 1.0F / (this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z);
    this.w *= f;
    this.x *= -f;
    this.y *= -f;
    this.z *= -f;
  }
  
  public final void normalize(Quat4f paramQuat4f)
  {
    float f = paramQuat4f.x * paramQuat4f.x + paramQuat4f.y * paramQuat4f.y + paramQuat4f.z * paramQuat4f.z + paramQuat4f.w * paramQuat4f.w;
    if (f > 0.0F)
    {
      f = 1.0F / (float)Math.sqrt(f);
      this.x = (f * paramQuat4f.x);
      this.y = (f * paramQuat4f.y);
      this.z = (f * paramQuat4f.z);
      this.w = (f * paramQuat4f.w);
    }
    else
    {
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 0.0F;
      this.w = 0.0F;
    }
  }
  
  public final void normalize()
  {
    float f = this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    if (f > 0.0F)
    {
      f = 1.0F / (float)Math.sqrt(f);
      this.x *= f;
      this.y *= f;
      this.z *= f;
      this.w *= f;
    }
    else
    {
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 0.0F;
      this.w = 0.0F;
    }
  }
  
  public final void set(Matrix4f paramMatrix4f)
  {
    float f = 0.25F * (paramMatrix4f.m00 + paramMatrix4f.m11 + paramMatrix4f.m22 + paramMatrix4f.m33);
    if (f >= 0.0F)
    {
      if (f >= 1.E-030D)
      {
        this.w = ((float)Math.sqrt(f));
        f = 0.25F / this.w;
        this.x = ((paramMatrix4f.m21 - paramMatrix4f.m12) * f);
        this.y = ((paramMatrix4f.m02 - paramMatrix4f.m20) * f);
        this.z = ((paramMatrix4f.m10 - paramMatrix4f.m01) * f);
      }
    }
    else
    {
      this.w = 0.0F;
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 1.0F;
      return;
    }
    this.w = 0.0F;
    f = -0.5F * (paramMatrix4f.m11 + paramMatrix4f.m22);
    if (f >= 0.0F)
    {
      if (f >= 1.E-030D)
      {
        this.x = ((float)Math.sqrt(f));
        f = 1.0F / (2.0F * this.x);
        this.y = (paramMatrix4f.m10 * f);
        this.z = (paramMatrix4f.m20 * f);
      }
    }
    else
    {
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 1.0F;
      return;
    }
    this.x = 0.0F;
    f = 0.5F * (1.0F - paramMatrix4f.m22);
    if (f >= 1.E-030D)
    {
      this.y = ((float)Math.sqrt(f));
      this.z = (paramMatrix4f.m21 / (2.0F * this.y));
      return;
    }
    this.y = 0.0F;
    this.z = 1.0F;
  }
  
  public final void set(Matrix4d paramMatrix4d)
  {
    double d = 0.25D * (paramMatrix4d.m00 + paramMatrix4d.m11 + paramMatrix4d.m22 + paramMatrix4d.m33);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.w = ((float)Math.sqrt(d));
        d = 0.25D / this.w;
        this.x = ((float)((paramMatrix4d.m21 - paramMatrix4d.m12) * d));
        this.y = ((float)((paramMatrix4d.m02 - paramMatrix4d.m20) * d));
        this.z = ((float)((paramMatrix4d.m10 - paramMatrix4d.m01) * d));
      }
    }
    else
    {
      this.w = 0.0F;
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 1.0F;
      return;
    }
    this.w = 0.0F;
    d = -0.5D * (paramMatrix4d.m11 + paramMatrix4d.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.x = ((float)Math.sqrt(d));
        d = 0.5D / this.x;
        this.y = ((float)(paramMatrix4d.m10 * d));
        this.z = ((float)(paramMatrix4d.m20 * d));
      }
    }
    else
    {
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 1.0F;
      return;
    }
    this.x = 0.0F;
    d = 0.5D * (1.0D - paramMatrix4d.m22);
    if (d >= 1.E-030D)
    {
      this.y = ((float)Math.sqrt(d));
      this.z = ((float)(paramMatrix4d.m21 / (2.0D * this.y)));
      return;
    }
    this.y = 0.0F;
    this.z = 1.0F;
  }
  
  public final void set(Matrix3f paramMatrix3f)
  {
    float f = 0.25F * (paramMatrix3f.m00 + paramMatrix3f.m11 + paramMatrix3f.m22 + 1.0F);
    if (f >= 0.0F)
    {
      if (f >= 1.E-030D)
      {
        this.w = ((float)Math.sqrt(f));
        f = 0.25F / this.w;
        this.x = ((paramMatrix3f.m21 - paramMatrix3f.m12) * f);
        this.y = ((paramMatrix3f.m02 - paramMatrix3f.m20) * f);
        this.z = ((paramMatrix3f.m10 - paramMatrix3f.m01) * f);
      }
    }
    else
    {
      this.w = 0.0F;
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 1.0F;
      return;
    }
    this.w = 0.0F;
    f = -0.5F * (paramMatrix3f.m11 + paramMatrix3f.m22);
    if (f >= 0.0F)
    {
      if (f >= 1.E-030D)
      {
        this.x = ((float)Math.sqrt(f));
        f = 0.5F / this.x;
        this.y = (paramMatrix3f.m10 * f);
        this.z = (paramMatrix3f.m20 * f);
      }
    }
    else
    {
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 1.0F;
      return;
    }
    this.x = 0.0F;
    f = 0.5F * (1.0F - paramMatrix3f.m22);
    if (f >= 1.E-030D)
    {
      this.y = ((float)Math.sqrt(f));
      this.z = (paramMatrix3f.m21 / (2.0F * this.y));
      return;
    }
    this.y = 0.0F;
    this.z = 1.0F;
  }
  
  public final void set(Matrix3d paramMatrix3d)
  {
    double d = 0.25D * (paramMatrix3d.m00 + paramMatrix3d.m11 + paramMatrix3d.m22 + 1.0D);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.w = ((float)Math.sqrt(d));
        d = 0.25D / this.w;
        this.x = ((float)((paramMatrix3d.m21 - paramMatrix3d.m12) * d));
        this.y = ((float)((paramMatrix3d.m02 - paramMatrix3d.m20) * d));
        this.z = ((float)((paramMatrix3d.m10 - paramMatrix3d.m01) * d));
      }
    }
    else
    {
      this.w = 0.0F;
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 1.0F;
      return;
    }
    this.w = 0.0F;
    d = -0.5D * (paramMatrix3d.m11 + paramMatrix3d.m22);
    if (d >= 0.0D)
    {
      if (d >= 1.E-030D)
      {
        this.x = ((float)Math.sqrt(d));
        d = 0.5D / this.x;
        this.y = ((float)(paramMatrix3d.m10 * d));
        this.z = ((float)(paramMatrix3d.m20 * d));
      }
    }
    else
    {
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 1.0F;
      return;
    }
    this.x = 0.0F;
    d = 0.5D * (1.0D - paramMatrix3d.m22);
    if (d >= 1.E-030D)
    {
      this.y = ((float)Math.sqrt(d));
      this.z = ((float)(paramMatrix3d.m21 / (2.0D * this.y)));
      return;
    }
    this.y = 0.0F;
    this.z = 1.0F;
  }
  
  public final void set(AxisAngle4f paramAxisAngle4f)
  {
    float f2 = (float)Math.sqrt(paramAxisAngle4f.x * paramAxisAngle4f.x + paramAxisAngle4f.y * paramAxisAngle4f.y + paramAxisAngle4f.z * paramAxisAngle4f.z);
    if (f2 < 1.0E-006D)
    {
      this.w = 0.0F;
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 0.0F;
    }
    else
    {
      f2 = 1.0F / f2;
      float f1 = (float)Math.sin(paramAxisAngle4f.angle / 2.0D);
      this.w = ((float)Math.cos(paramAxisAngle4f.angle / 2.0D));
      this.x = (paramAxisAngle4f.x * f2 * f1);
      this.y = (paramAxisAngle4f.y * f2 * f1);
      this.z = (paramAxisAngle4f.z * f2 * f1);
    }
  }
  
  public final void set(AxisAngle4d paramAxisAngle4d)
  {
    float f2 = (float)(1.0D / Math.sqrt(paramAxisAngle4d.x * paramAxisAngle4d.x + paramAxisAngle4d.y * paramAxisAngle4d.y + paramAxisAngle4d.z * paramAxisAngle4d.z));
    if (f2 < 1.0E-006D)
    {
      this.w = 0.0F;
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 0.0F;
    }
    else
    {
      f2 = 1.0F / f2;
      float f1 = (float)Math.sin(paramAxisAngle4d.angle / 2.0D);
      this.w = ((float)Math.cos(paramAxisAngle4d.angle / 2.0D));
      this.x = ((float)paramAxisAngle4d.x * f2 * f1);
      this.y = ((float)paramAxisAngle4d.y * f2 * f1);
      this.z = ((float)paramAxisAngle4d.z * f2 * f1);
    }
  }
  
  public final void interpolate(Quat4f paramQuat4f, float paramFloat)
  {
    double d1 = this.x * paramQuat4f.x + this.y * paramQuat4f.y + this.z * paramQuat4f.z + this.w * paramQuat4f.w;
    if (d1 < 0.0D)
    {
      paramQuat4f.x = (-paramQuat4f.x);
      paramQuat4f.y = (-paramQuat4f.y);
      paramQuat4f.z = (-paramQuat4f.z);
      paramQuat4f.w = (-paramQuat4f.w);
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
    this.w = ((float)(d2 * this.w + d3 * paramQuat4f.w));
    this.x = ((float)(d2 * this.x + d3 * paramQuat4f.x));
    this.y = ((float)(d2 * this.y + d3 * paramQuat4f.y));
    this.z = ((float)(d2 * this.z + d3 * paramQuat4f.z));
  }
  
  public final void interpolate(Quat4f paramQuat4f1, Quat4f paramQuat4f2, float paramFloat)
  {
    double d1 = paramQuat4f2.x * paramQuat4f1.x + paramQuat4f2.y * paramQuat4f1.y + paramQuat4f2.z * paramQuat4f1.z + paramQuat4f2.w * paramQuat4f1.w;
    if (d1 < 0.0D)
    {
      paramQuat4f1.x = (-paramQuat4f1.x);
      paramQuat4f1.y = (-paramQuat4f1.y);
      paramQuat4f1.z = (-paramQuat4f1.z);
      paramQuat4f1.w = (-paramQuat4f1.w);
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
    this.w = ((float)(d2 * paramQuat4f1.w + d3 * paramQuat4f2.w));
    this.x = ((float)(d2 * paramQuat4f1.x + d3 * paramQuat4f2.x));
    this.y = ((float)(d2 * paramQuat4f1.y + d3 * paramQuat4f2.y));
    this.z = ((float)(d2 * paramQuat4f1.z + d3 * paramQuat4f2.z));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Quat4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */