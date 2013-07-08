package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple4f
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 7068460319248845763L;
  public float field_596;
  public float field_597;
  public float field_598;
  public float field_599;
  
  public Tuple4f(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.field_596 = paramFloat1;
    this.field_597 = paramFloat2;
    this.field_598 = paramFloat3;
    this.field_599 = paramFloat4;
  }
  
  public Tuple4f(float[] paramArrayOfFloat)
  {
    this.field_596 = paramArrayOfFloat[0];
    this.field_597 = paramArrayOfFloat[1];
    this.field_598 = paramArrayOfFloat[2];
    this.field_599 = paramArrayOfFloat[3];
  }
  
  public Tuple4f(Tuple4f paramTuple4f)
  {
    this.field_596 = paramTuple4f.field_596;
    this.field_597 = paramTuple4f.field_597;
    this.field_598 = paramTuple4f.field_598;
    this.field_599 = paramTuple4f.field_599;
  }
  
  public Tuple4f(Tuple4d paramTuple4d)
  {
    this.field_596 = ((float)paramTuple4d.field_600);
    this.field_597 = ((float)paramTuple4d.field_601);
    this.field_598 = ((float)paramTuple4d.field_602);
    this.field_599 = ((float)paramTuple4d.field_603);
  }
  
  public Tuple4f()
  {
    this.field_596 = 0.0F;
    this.field_597 = 0.0F;
    this.field_598 = 0.0F;
    this.field_599 = 0.0F;
  }
  
  public final void set(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.field_596 = paramFloat1;
    this.field_597 = paramFloat2;
    this.field_598 = paramFloat3;
    this.field_599 = paramFloat4;
  }
  
  public final void set(float[] paramArrayOfFloat)
  {
    this.field_596 = paramArrayOfFloat[0];
    this.field_597 = paramArrayOfFloat[1];
    this.field_598 = paramArrayOfFloat[2];
    this.field_599 = paramArrayOfFloat[3];
  }
  
  public final void set(Tuple4f paramTuple4f)
  {
    this.field_596 = paramTuple4f.field_596;
    this.field_597 = paramTuple4f.field_597;
    this.field_598 = paramTuple4f.field_598;
    this.field_599 = paramTuple4f.field_599;
  }
  
  public final void set(Tuple4d paramTuple4d)
  {
    this.field_596 = ((float)paramTuple4d.field_600);
    this.field_597 = ((float)paramTuple4d.field_601);
    this.field_598 = ((float)paramTuple4d.field_602);
    this.field_599 = ((float)paramTuple4d.field_603);
  }
  
  public final void get(float[] paramArrayOfFloat)
  {
    paramArrayOfFloat[0] = this.field_596;
    paramArrayOfFloat[1] = this.field_597;
    paramArrayOfFloat[2] = this.field_598;
    paramArrayOfFloat[3] = this.field_599;
  }
  
  public final void get(Tuple4f paramTuple4f)
  {
    paramTuple4f.field_596 = this.field_596;
    paramTuple4f.field_597 = this.field_597;
    paramTuple4f.field_598 = this.field_598;
    paramTuple4f.field_599 = this.field_599;
  }
  
  public final void add(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2)
  {
    paramTuple4f1.field_596 += paramTuple4f2.field_596;
    paramTuple4f1.field_597 += paramTuple4f2.field_597;
    paramTuple4f1.field_598 += paramTuple4f2.field_598;
    paramTuple4f1.field_599 += paramTuple4f2.field_599;
  }
  
  public final void add(Tuple4f paramTuple4f)
  {
    this.field_596 += paramTuple4f.field_596;
    this.field_597 += paramTuple4f.field_597;
    this.field_598 += paramTuple4f.field_598;
    this.field_599 += paramTuple4f.field_599;
  }
  
  public final void sub(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2)
  {
    paramTuple4f1.field_596 -= paramTuple4f2.field_596;
    paramTuple4f1.field_597 -= paramTuple4f2.field_597;
    paramTuple4f1.field_598 -= paramTuple4f2.field_598;
    paramTuple4f1.field_599 -= paramTuple4f2.field_599;
  }
  
  public final void sub(Tuple4f paramTuple4f)
  {
    this.field_596 -= paramTuple4f.field_596;
    this.field_597 -= paramTuple4f.field_597;
    this.field_598 -= paramTuple4f.field_598;
    this.field_599 -= paramTuple4f.field_599;
  }
  
  public final void negate(Tuple4f paramTuple4f)
  {
    this.field_596 = (-paramTuple4f.field_596);
    this.field_597 = (-paramTuple4f.field_597);
    this.field_598 = (-paramTuple4f.field_598);
    this.field_599 = (-paramTuple4f.field_599);
  }
  
  public final void negate()
  {
    this.field_596 = (-this.field_596);
    this.field_597 = (-this.field_597);
    this.field_598 = (-this.field_598);
    this.field_599 = (-this.field_599);
  }
  
  public final void scale(float paramFloat, Tuple4f paramTuple4f)
  {
    this.field_596 = (paramFloat * paramTuple4f.field_596);
    this.field_597 = (paramFloat * paramTuple4f.field_597);
    this.field_598 = (paramFloat * paramTuple4f.field_598);
    this.field_599 = (paramFloat * paramTuple4f.field_599);
  }
  
  public final void scale(float paramFloat)
  {
    this.field_596 *= paramFloat;
    this.field_597 *= paramFloat;
    this.field_598 *= paramFloat;
    this.field_599 *= paramFloat;
  }
  
  public final void scaleAdd(float paramFloat, Tuple4f paramTuple4f1, Tuple4f paramTuple4f2)
  {
    this.field_596 = (paramFloat * paramTuple4f1.field_596 + paramTuple4f2.field_596);
    this.field_597 = (paramFloat * paramTuple4f1.field_597 + paramTuple4f2.field_597);
    this.field_598 = (paramFloat * paramTuple4f1.field_598 + paramTuple4f2.field_598);
    this.field_599 = (paramFloat * paramTuple4f1.field_599 + paramTuple4f2.field_599);
  }
  
  public final void scaleAdd(float paramFloat, Tuple4f paramTuple4f)
  {
    this.field_596 = (paramFloat * this.field_596 + paramTuple4f.field_596);
    this.field_597 = (paramFloat * this.field_597 + paramTuple4f.field_597);
    this.field_598 = (paramFloat * this.field_598 + paramTuple4f.field_598);
    this.field_599 = (paramFloat * this.field_599 + paramTuple4f.field_599);
  }
  
  public String toString()
  {
    return "(" + this.field_596 + ", " + this.field_597 + ", " + this.field_598 + ", " + this.field_599 + ")";
  }
  
  public boolean equals(Tuple4f paramTuple4f)
  {
    try
    {
      return (this.field_596 == paramTuple4f.field_596) && (this.field_597 == paramTuple4f.field_597) && (this.field_598 == paramTuple4f.field_598) && (this.field_599 == paramTuple4f.field_599);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple4f localTuple4f = (Tuple4f)paramObject;
      return (this.field_596 == localTuple4f.field_596) && (this.field_597 == localTuple4f.field_597) && (this.field_598 == localTuple4f.field_598) && (this.field_599 == localTuple4f.field_599);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException) {}
    return false;
  }
  
  public boolean epsilonEquals(Tuple4f paramTuple4f, float paramFloat)
  {
    float f = this.field_596 - paramTuple4f.field_596;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.field_597 - paramTuple4f.field_597;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.field_598 - paramTuple4f.field_598;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.field_599 - paramTuple4f.field_599;
    return (f < 0.0F ? -f : f) <= paramFloat;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_596);
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_597);
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_598);
    l = 31L * l + VecMathUtil.floatToIntBits(this.field_599);
    return (int)(l ^ l >> 32);
  }
  
  public final void clamp(float paramFloat1, float paramFloat2, Tuple4f paramTuple4f)
  {
    if (paramTuple4f.field_596 > paramFloat2) {
      this.field_596 = paramFloat2;
    } else if (paramTuple4f.field_596 < paramFloat1) {
      this.field_596 = paramFloat1;
    } else {
      this.field_596 = paramTuple4f.field_596;
    }
    if (paramTuple4f.field_597 > paramFloat2) {
      this.field_597 = paramFloat2;
    } else if (paramTuple4f.field_597 < paramFloat1) {
      this.field_597 = paramFloat1;
    } else {
      this.field_597 = paramTuple4f.field_597;
    }
    if (paramTuple4f.field_598 > paramFloat2) {
      this.field_598 = paramFloat2;
    } else if (paramTuple4f.field_598 < paramFloat1) {
      this.field_598 = paramFloat1;
    } else {
      this.field_598 = paramTuple4f.field_598;
    }
    if (paramTuple4f.field_599 > paramFloat2) {
      this.field_599 = paramFloat2;
    } else if (paramTuple4f.field_599 < paramFloat1) {
      this.field_599 = paramFloat1;
    } else {
      this.field_599 = paramTuple4f.field_599;
    }
  }
  
  public final void clampMin(float paramFloat, Tuple4f paramTuple4f)
  {
    if (paramTuple4f.field_596 < paramFloat) {
      this.field_596 = paramFloat;
    } else {
      this.field_596 = paramTuple4f.field_596;
    }
    if (paramTuple4f.field_597 < paramFloat) {
      this.field_597 = paramFloat;
    } else {
      this.field_597 = paramTuple4f.field_597;
    }
    if (paramTuple4f.field_598 < paramFloat) {
      this.field_598 = paramFloat;
    } else {
      this.field_598 = paramTuple4f.field_598;
    }
    if (paramTuple4f.field_599 < paramFloat) {
      this.field_599 = paramFloat;
    } else {
      this.field_599 = paramTuple4f.field_599;
    }
  }
  
  public final void clampMax(float paramFloat, Tuple4f paramTuple4f)
  {
    if (paramTuple4f.field_596 > paramFloat) {
      this.field_596 = paramFloat;
    } else {
      this.field_596 = paramTuple4f.field_596;
    }
    if (paramTuple4f.field_597 > paramFloat) {
      this.field_597 = paramFloat;
    } else {
      this.field_597 = paramTuple4f.field_597;
    }
    if (paramTuple4f.field_598 > paramFloat) {
      this.field_598 = paramFloat;
    } else {
      this.field_598 = paramTuple4f.field_598;
    }
    if (paramTuple4f.field_599 > paramFloat) {
      this.field_599 = paramFloat;
    } else {
      this.field_599 = paramTuple4f.field_598;
    }
  }
  
  public final void absolute(Tuple4f paramTuple4f)
  {
    this.field_596 = Math.abs(paramTuple4f.field_596);
    this.field_597 = Math.abs(paramTuple4f.field_597);
    this.field_598 = Math.abs(paramTuple4f.field_598);
    this.field_599 = Math.abs(paramTuple4f.field_599);
  }
  
  public final void clamp(float paramFloat1, float paramFloat2)
  {
    if (this.field_596 > paramFloat2) {
      this.field_596 = paramFloat2;
    } else if (this.field_596 < paramFloat1) {
      this.field_596 = paramFloat1;
    }
    if (this.field_597 > paramFloat2) {
      this.field_597 = paramFloat2;
    } else if (this.field_597 < paramFloat1) {
      this.field_597 = paramFloat1;
    }
    if (this.field_598 > paramFloat2) {
      this.field_598 = paramFloat2;
    } else if (this.field_598 < paramFloat1) {
      this.field_598 = paramFloat1;
    }
    if (this.field_599 > paramFloat2) {
      this.field_599 = paramFloat2;
    } else if (this.field_599 < paramFloat1) {
      this.field_599 = paramFloat1;
    }
  }
  
  public final void clampMin(float paramFloat)
  {
    if (this.field_596 < paramFloat) {
      this.field_596 = paramFloat;
    }
    if (this.field_597 < paramFloat) {
      this.field_597 = paramFloat;
    }
    if (this.field_598 < paramFloat) {
      this.field_598 = paramFloat;
    }
    if (this.field_599 < paramFloat) {
      this.field_599 = paramFloat;
    }
  }
  
  public final void clampMax(float paramFloat)
  {
    if (this.field_596 > paramFloat) {
      this.field_596 = paramFloat;
    }
    if (this.field_597 > paramFloat) {
      this.field_597 = paramFloat;
    }
    if (this.field_598 > paramFloat) {
      this.field_598 = paramFloat;
    }
    if (this.field_599 > paramFloat) {
      this.field_599 = paramFloat;
    }
  }
  
  public final void absolute()
  {
    this.field_596 = Math.abs(this.field_596);
    this.field_597 = Math.abs(this.field_597);
    this.field_598 = Math.abs(this.field_598);
    this.field_599 = Math.abs(this.field_599);
  }
  
  public void interpolate(Tuple4f paramTuple4f1, Tuple4f paramTuple4f2, float paramFloat)
  {
    this.field_596 = ((1.0F - paramFloat) * paramTuple4f1.field_596 + paramFloat * paramTuple4f2.field_596);
    this.field_597 = ((1.0F - paramFloat) * paramTuple4f1.field_597 + paramFloat * paramTuple4f2.field_597);
    this.field_598 = ((1.0F - paramFloat) * paramTuple4f1.field_598 + paramFloat * paramTuple4f2.field_598);
    this.field_599 = ((1.0F - paramFloat) * paramTuple4f1.field_599 + paramFloat * paramTuple4f2.field_599);
  }
  
  public void interpolate(Tuple4f paramTuple4f, float paramFloat)
  {
    this.field_596 = ((1.0F - paramFloat) * this.field_596 + paramFloat * paramTuple4f.field_596);
    this.field_597 = ((1.0F - paramFloat) * this.field_597 + paramFloat * paramTuple4f.field_597);
    this.field_598 = ((1.0F - paramFloat) * this.field_598 + paramFloat * paramTuple4f.field_598);
    this.field_599 = ((1.0F - paramFloat) * this.field_599 + paramFloat * paramTuple4f.field_599);
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
 * Qualified Name:     javax.vecmath.Tuple4f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */