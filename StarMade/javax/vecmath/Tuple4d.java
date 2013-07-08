package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple4d
  implements Serializable, Cloneable
{
  static final long serialVersionUID = -4748953690425311052L;
  public double field_600;
  public double field_601;
  public double field_602;
  public double field_603;
  
  public Tuple4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.field_600 = paramDouble1;
    this.field_601 = paramDouble2;
    this.field_602 = paramDouble3;
    this.field_603 = paramDouble4;
  }
  
  public Tuple4d(double[] paramArrayOfDouble)
  {
    this.field_600 = paramArrayOfDouble[0];
    this.field_601 = paramArrayOfDouble[1];
    this.field_602 = paramArrayOfDouble[2];
    this.field_603 = paramArrayOfDouble[3];
  }
  
  public Tuple4d(Tuple4d paramTuple4d)
  {
    this.field_600 = paramTuple4d.field_600;
    this.field_601 = paramTuple4d.field_601;
    this.field_602 = paramTuple4d.field_602;
    this.field_603 = paramTuple4d.field_603;
  }
  
  public Tuple4d(Tuple4f paramTuple4f)
  {
    this.field_600 = paramTuple4f.field_596;
    this.field_601 = paramTuple4f.field_597;
    this.field_602 = paramTuple4f.field_598;
    this.field_603 = paramTuple4f.field_599;
  }
  
  public Tuple4d()
  {
    this.field_600 = 0.0D;
    this.field_601 = 0.0D;
    this.field_602 = 0.0D;
    this.field_603 = 0.0D;
  }
  
  public final void set(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.field_600 = paramDouble1;
    this.field_601 = paramDouble2;
    this.field_602 = paramDouble3;
    this.field_603 = paramDouble4;
  }
  
  public final void set(double[] paramArrayOfDouble)
  {
    this.field_600 = paramArrayOfDouble[0];
    this.field_601 = paramArrayOfDouble[1];
    this.field_602 = paramArrayOfDouble[2];
    this.field_603 = paramArrayOfDouble[3];
  }
  
  public final void set(Tuple4d paramTuple4d)
  {
    this.field_600 = paramTuple4d.field_600;
    this.field_601 = paramTuple4d.field_601;
    this.field_602 = paramTuple4d.field_602;
    this.field_603 = paramTuple4d.field_603;
  }
  
  public final void set(Tuple4f paramTuple4f)
  {
    this.field_600 = paramTuple4f.field_596;
    this.field_601 = paramTuple4f.field_597;
    this.field_602 = paramTuple4f.field_598;
    this.field_603 = paramTuple4f.field_599;
  }
  
  public final void get(double[] paramArrayOfDouble)
  {
    paramArrayOfDouble[0] = this.field_600;
    paramArrayOfDouble[1] = this.field_601;
    paramArrayOfDouble[2] = this.field_602;
    paramArrayOfDouble[3] = this.field_603;
  }
  
  public final void get(Tuple4d paramTuple4d)
  {
    paramTuple4d.field_600 = this.field_600;
    paramTuple4d.field_601 = this.field_601;
    paramTuple4d.field_602 = this.field_602;
    paramTuple4d.field_603 = this.field_603;
  }
  
  public final void add(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2)
  {
    paramTuple4d1.field_600 += paramTuple4d2.field_600;
    paramTuple4d1.field_601 += paramTuple4d2.field_601;
    paramTuple4d1.field_602 += paramTuple4d2.field_602;
    paramTuple4d1.field_603 += paramTuple4d2.field_603;
  }
  
  public final void add(Tuple4d paramTuple4d)
  {
    this.field_600 += paramTuple4d.field_600;
    this.field_601 += paramTuple4d.field_601;
    this.field_602 += paramTuple4d.field_602;
    this.field_603 += paramTuple4d.field_603;
  }
  
  public final void sub(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2)
  {
    paramTuple4d1.field_600 -= paramTuple4d2.field_600;
    paramTuple4d1.field_601 -= paramTuple4d2.field_601;
    paramTuple4d1.field_602 -= paramTuple4d2.field_602;
    paramTuple4d1.field_603 -= paramTuple4d2.field_603;
  }
  
  public final void sub(Tuple4d paramTuple4d)
  {
    this.field_600 -= paramTuple4d.field_600;
    this.field_601 -= paramTuple4d.field_601;
    this.field_602 -= paramTuple4d.field_602;
    this.field_603 -= paramTuple4d.field_603;
  }
  
  public final void negate(Tuple4d paramTuple4d)
  {
    this.field_600 = (-paramTuple4d.field_600);
    this.field_601 = (-paramTuple4d.field_601);
    this.field_602 = (-paramTuple4d.field_602);
    this.field_603 = (-paramTuple4d.field_603);
  }
  
  public final void negate()
  {
    this.field_600 = (-this.field_600);
    this.field_601 = (-this.field_601);
    this.field_602 = (-this.field_602);
    this.field_603 = (-this.field_603);
  }
  
  public final void scale(double paramDouble, Tuple4d paramTuple4d)
  {
    this.field_600 = (paramDouble * paramTuple4d.field_600);
    this.field_601 = (paramDouble * paramTuple4d.field_601);
    this.field_602 = (paramDouble * paramTuple4d.field_602);
    this.field_603 = (paramDouble * paramTuple4d.field_603);
  }
  
  public final void scale(double paramDouble)
  {
    this.field_600 *= paramDouble;
    this.field_601 *= paramDouble;
    this.field_602 *= paramDouble;
    this.field_603 *= paramDouble;
  }
  
  public final void scaleAdd(double paramDouble, Tuple4d paramTuple4d1, Tuple4d paramTuple4d2)
  {
    this.field_600 = (paramDouble * paramTuple4d1.field_600 + paramTuple4d2.field_600);
    this.field_601 = (paramDouble * paramTuple4d1.field_601 + paramTuple4d2.field_601);
    this.field_602 = (paramDouble * paramTuple4d1.field_602 + paramTuple4d2.field_602);
    this.field_603 = (paramDouble * paramTuple4d1.field_603 + paramTuple4d2.field_603);
  }
  
  /**
   * @deprecated
   */
  public final void scaleAdd(float paramFloat, Tuple4d paramTuple4d)
  {
    scaleAdd(paramFloat, paramTuple4d);
  }
  
  public final void scaleAdd(double paramDouble, Tuple4d paramTuple4d)
  {
    this.field_600 = (paramDouble * this.field_600 + paramTuple4d.field_600);
    this.field_601 = (paramDouble * this.field_601 + paramTuple4d.field_601);
    this.field_602 = (paramDouble * this.field_602 + paramTuple4d.field_602);
    this.field_603 = (paramDouble * this.field_603 + paramTuple4d.field_603);
  }
  
  public String toString()
  {
    return "(" + this.field_600 + ", " + this.field_601 + ", " + this.field_602 + ", " + this.field_603 + ")";
  }
  
  public boolean equals(Tuple4d paramTuple4d)
  {
    try
    {
      return (this.field_600 == paramTuple4d.field_600) && (this.field_601 == paramTuple4d.field_601) && (this.field_602 == paramTuple4d.field_602) && (this.field_603 == paramTuple4d.field_603);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple4d localTuple4d = (Tuple4d)paramObject;
      return (this.field_600 == localTuple4d.field_600) && (this.field_601 == localTuple4d.field_601) && (this.field_602 == localTuple4d.field_602) && (this.field_603 == localTuple4d.field_603);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException) {}
    return false;
  }
  
  public boolean epsilonEquals(Tuple4d paramTuple4d, double paramDouble)
  {
    double d = this.field_600 - paramTuple4d.field_600;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.field_601 - paramTuple4d.field_601;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.field_602 - paramTuple4d.field_602;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.field_603 - paramTuple4d.field_603;
    return (d < 0.0D ? -d : d) <= paramDouble;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_600);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_601);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_602);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_603);
    return (int)(l ^ l >> 32);
  }
  
  /**
   * @deprecated
   */
  public final void clamp(float paramFloat1, float paramFloat2, Tuple4d paramTuple4d)
  {
    clamp(paramFloat1, paramFloat2, paramTuple4d);
  }
  
  public final void clamp(double paramDouble1, double paramDouble2, Tuple4d paramTuple4d)
  {
    if (paramTuple4d.field_600 > paramDouble2) {
      this.field_600 = paramDouble2;
    } else if (paramTuple4d.field_600 < paramDouble1) {
      this.field_600 = paramDouble1;
    } else {
      this.field_600 = paramTuple4d.field_600;
    }
    if (paramTuple4d.field_601 > paramDouble2) {
      this.field_601 = paramDouble2;
    } else if (paramTuple4d.field_601 < paramDouble1) {
      this.field_601 = paramDouble1;
    } else {
      this.field_601 = paramTuple4d.field_601;
    }
    if (paramTuple4d.field_602 > paramDouble2) {
      this.field_602 = paramDouble2;
    } else if (paramTuple4d.field_602 < paramDouble1) {
      this.field_602 = paramDouble1;
    } else {
      this.field_602 = paramTuple4d.field_602;
    }
    if (paramTuple4d.field_603 > paramDouble2) {
      this.field_603 = paramDouble2;
    } else if (paramTuple4d.field_603 < paramDouble1) {
      this.field_603 = paramDouble1;
    } else {
      this.field_603 = paramTuple4d.field_603;
    }
  }
  
  /**
   * @deprecated
   */
  public final void clampMin(float paramFloat, Tuple4d paramTuple4d)
  {
    clampMin(paramFloat, paramTuple4d);
  }
  
  public final void clampMin(double paramDouble, Tuple4d paramTuple4d)
  {
    if (paramTuple4d.field_600 < paramDouble) {
      this.field_600 = paramDouble;
    } else {
      this.field_600 = paramTuple4d.field_600;
    }
    if (paramTuple4d.field_601 < paramDouble) {
      this.field_601 = paramDouble;
    } else {
      this.field_601 = paramTuple4d.field_601;
    }
    if (paramTuple4d.field_602 < paramDouble) {
      this.field_602 = paramDouble;
    } else {
      this.field_602 = paramTuple4d.field_602;
    }
    if (paramTuple4d.field_603 < paramDouble) {
      this.field_603 = paramDouble;
    } else {
      this.field_603 = paramTuple4d.field_603;
    }
  }
  
  /**
   * @deprecated
   */
  public final void clampMax(float paramFloat, Tuple4d paramTuple4d)
  {
    clampMax(paramFloat, paramTuple4d);
  }
  
  public final void clampMax(double paramDouble, Tuple4d paramTuple4d)
  {
    if (paramTuple4d.field_600 > paramDouble) {
      this.field_600 = paramDouble;
    } else {
      this.field_600 = paramTuple4d.field_600;
    }
    if (paramTuple4d.field_601 > paramDouble) {
      this.field_601 = paramDouble;
    } else {
      this.field_601 = paramTuple4d.field_601;
    }
    if (paramTuple4d.field_602 > paramDouble) {
      this.field_602 = paramDouble;
    } else {
      this.field_602 = paramTuple4d.field_602;
    }
    if (paramTuple4d.field_603 > paramDouble) {
      this.field_603 = paramDouble;
    } else {
      this.field_603 = paramTuple4d.field_602;
    }
  }
  
  public final void absolute(Tuple4d paramTuple4d)
  {
    this.field_600 = Math.abs(paramTuple4d.field_600);
    this.field_601 = Math.abs(paramTuple4d.field_601);
    this.field_602 = Math.abs(paramTuple4d.field_602);
    this.field_603 = Math.abs(paramTuple4d.field_603);
  }
  
  /**
   * @deprecated
   */
  public final void clamp(float paramFloat1, float paramFloat2)
  {
    clamp(paramFloat1, paramFloat2);
  }
  
  public final void clamp(double paramDouble1, double paramDouble2)
  {
    if (this.field_600 > paramDouble2) {
      this.field_600 = paramDouble2;
    } else if (this.field_600 < paramDouble1) {
      this.field_600 = paramDouble1;
    }
    if (this.field_601 > paramDouble2) {
      this.field_601 = paramDouble2;
    } else if (this.field_601 < paramDouble1) {
      this.field_601 = paramDouble1;
    }
    if (this.field_602 > paramDouble2) {
      this.field_602 = paramDouble2;
    } else if (this.field_602 < paramDouble1) {
      this.field_602 = paramDouble1;
    }
    if (this.field_603 > paramDouble2) {
      this.field_603 = paramDouble2;
    } else if (this.field_603 < paramDouble1) {
      this.field_603 = paramDouble1;
    }
  }
  
  /**
   * @deprecated
   */
  public final void clampMin(float paramFloat)
  {
    clampMin(paramFloat);
  }
  
  public final void clampMin(double paramDouble)
  {
    if (this.field_600 < paramDouble) {
      this.field_600 = paramDouble;
    }
    if (this.field_601 < paramDouble) {
      this.field_601 = paramDouble;
    }
    if (this.field_602 < paramDouble) {
      this.field_602 = paramDouble;
    }
    if (this.field_603 < paramDouble) {
      this.field_603 = paramDouble;
    }
  }
  
  /**
   * @deprecated
   */
  public final void clampMax(float paramFloat)
  {
    clampMax(paramFloat);
  }
  
  public final void clampMax(double paramDouble)
  {
    if (this.field_600 > paramDouble) {
      this.field_600 = paramDouble;
    }
    if (this.field_601 > paramDouble) {
      this.field_601 = paramDouble;
    }
    if (this.field_602 > paramDouble) {
      this.field_602 = paramDouble;
    }
    if (this.field_603 > paramDouble) {
      this.field_603 = paramDouble;
    }
  }
  
  public final void absolute()
  {
    this.field_600 = Math.abs(this.field_600);
    this.field_601 = Math.abs(this.field_601);
    this.field_602 = Math.abs(this.field_602);
    this.field_603 = Math.abs(this.field_603);
  }
  
  /**
   * @deprecated
   */
  public void interpolate(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2, float paramFloat)
  {
    interpolate(paramTuple4d1, paramTuple4d2, paramFloat);
  }
  
  public void interpolate(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2, double paramDouble)
  {
    this.field_600 = ((1.0D - paramDouble) * paramTuple4d1.field_600 + paramDouble * paramTuple4d2.field_600);
    this.field_601 = ((1.0D - paramDouble) * paramTuple4d1.field_601 + paramDouble * paramTuple4d2.field_601);
    this.field_602 = ((1.0D - paramDouble) * paramTuple4d1.field_602 + paramDouble * paramTuple4d2.field_602);
    this.field_603 = ((1.0D - paramDouble) * paramTuple4d1.field_603 + paramDouble * paramTuple4d2.field_603);
  }
  
  /**
   * @deprecated
   */
  public void interpolate(Tuple4d paramTuple4d, float paramFloat)
  {
    interpolate(paramTuple4d, paramFloat);
  }
  
  public void interpolate(Tuple4d paramTuple4d, double paramDouble)
  {
    this.field_600 = ((1.0D - paramDouble) * this.field_600 + paramDouble * paramTuple4d.field_600);
    this.field_601 = ((1.0D - paramDouble) * this.field_601 + paramDouble * paramTuple4d.field_601);
    this.field_602 = ((1.0D - paramDouble) * this.field_602 + paramDouble * paramTuple4d.field_602);
    this.field_603 = ((1.0D - paramDouble) * this.field_603 + paramDouble * paramTuple4d.field_603);
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
 * Qualified Name:     javax.vecmath.Tuple4d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */