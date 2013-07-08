package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple3d
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 5542096614926168415L;
  public double field_612;
  public double field_613;
  public double field_614;
  
  public Tuple3d(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    this.field_612 = paramDouble1;
    this.field_613 = paramDouble2;
    this.field_614 = paramDouble3;
  }
  
  public Tuple3d(double[] paramArrayOfDouble)
  {
    this.field_612 = paramArrayOfDouble[0];
    this.field_613 = paramArrayOfDouble[1];
    this.field_614 = paramArrayOfDouble[2];
  }
  
  public Tuple3d(Tuple3d paramTuple3d)
  {
    this.field_612 = paramTuple3d.field_612;
    this.field_613 = paramTuple3d.field_613;
    this.field_614 = paramTuple3d.field_614;
  }
  
  public Tuple3d(Tuple3f paramTuple3f)
  {
    this.field_612 = paramTuple3f.field_615;
    this.field_613 = paramTuple3f.field_616;
    this.field_614 = paramTuple3f.field_617;
  }
  
  public Tuple3d()
  {
    this.field_612 = 0.0D;
    this.field_613 = 0.0D;
    this.field_614 = 0.0D;
  }
  
  public final void set(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    this.field_612 = paramDouble1;
    this.field_613 = paramDouble2;
    this.field_614 = paramDouble3;
  }
  
  public final void set(double[] paramArrayOfDouble)
  {
    this.field_612 = paramArrayOfDouble[0];
    this.field_613 = paramArrayOfDouble[1];
    this.field_614 = paramArrayOfDouble[2];
  }
  
  public final void set(Tuple3d paramTuple3d)
  {
    this.field_612 = paramTuple3d.field_612;
    this.field_613 = paramTuple3d.field_613;
    this.field_614 = paramTuple3d.field_614;
  }
  
  public final void set(Tuple3f paramTuple3f)
  {
    this.field_612 = paramTuple3f.field_615;
    this.field_613 = paramTuple3f.field_616;
    this.field_614 = paramTuple3f.field_617;
  }
  
  public final void get(double[] paramArrayOfDouble)
  {
    paramArrayOfDouble[0] = this.field_612;
    paramArrayOfDouble[1] = this.field_613;
    paramArrayOfDouble[2] = this.field_614;
  }
  
  public final void get(Tuple3d paramTuple3d)
  {
    paramTuple3d.field_612 = this.field_612;
    paramTuple3d.field_613 = this.field_613;
    paramTuple3d.field_614 = this.field_614;
  }
  
  public final void add(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2)
  {
    paramTuple3d1.field_612 += paramTuple3d2.field_612;
    paramTuple3d1.field_613 += paramTuple3d2.field_613;
    paramTuple3d1.field_614 += paramTuple3d2.field_614;
  }
  
  public final void add(Tuple3d paramTuple3d)
  {
    this.field_612 += paramTuple3d.field_612;
    this.field_613 += paramTuple3d.field_613;
    this.field_614 += paramTuple3d.field_614;
  }
  
  public final void sub(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2)
  {
    paramTuple3d1.field_612 -= paramTuple3d2.field_612;
    paramTuple3d1.field_613 -= paramTuple3d2.field_613;
    paramTuple3d1.field_614 -= paramTuple3d2.field_614;
  }
  
  public final void sub(Tuple3d paramTuple3d)
  {
    this.field_612 -= paramTuple3d.field_612;
    this.field_613 -= paramTuple3d.field_613;
    this.field_614 -= paramTuple3d.field_614;
  }
  
  public final void negate(Tuple3d paramTuple3d)
  {
    this.field_612 = (-paramTuple3d.field_612);
    this.field_613 = (-paramTuple3d.field_613);
    this.field_614 = (-paramTuple3d.field_614);
  }
  
  public final void negate()
  {
    this.field_612 = (-this.field_612);
    this.field_613 = (-this.field_613);
    this.field_614 = (-this.field_614);
  }
  
  public final void scale(double paramDouble, Tuple3d paramTuple3d)
  {
    this.field_612 = (paramDouble * paramTuple3d.field_612);
    this.field_613 = (paramDouble * paramTuple3d.field_613);
    this.field_614 = (paramDouble * paramTuple3d.field_614);
  }
  
  public final void scale(double paramDouble)
  {
    this.field_612 *= paramDouble;
    this.field_613 *= paramDouble;
    this.field_614 *= paramDouble;
  }
  
  public final void scaleAdd(double paramDouble, Tuple3d paramTuple3d1, Tuple3d paramTuple3d2)
  {
    this.field_612 = (paramDouble * paramTuple3d1.field_612 + paramTuple3d2.field_612);
    this.field_613 = (paramDouble * paramTuple3d1.field_613 + paramTuple3d2.field_613);
    this.field_614 = (paramDouble * paramTuple3d1.field_614 + paramTuple3d2.field_614);
  }
  
  /**
   * @deprecated
   */
  public final void scaleAdd(double paramDouble, Tuple3f paramTuple3f)
  {
    scaleAdd(paramDouble, new Point3d(paramTuple3f));
  }
  
  public final void scaleAdd(double paramDouble, Tuple3d paramTuple3d)
  {
    this.field_612 = (paramDouble * this.field_612 + paramTuple3d.field_612);
    this.field_613 = (paramDouble * this.field_613 + paramTuple3d.field_613);
    this.field_614 = (paramDouble * this.field_614 + paramTuple3d.field_614);
  }
  
  public String toString()
  {
    return "(" + this.field_612 + ", " + this.field_613 + ", " + this.field_614 + ")";
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_612);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_613);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_614);
    return (int)(l ^ l >> 32);
  }
  
  public boolean equals(Tuple3d paramTuple3d)
  {
    try
    {
      return (this.field_612 == paramTuple3d.field_612) && (this.field_613 == paramTuple3d.field_613) && (this.field_614 == paramTuple3d.field_614);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple3d localTuple3d = (Tuple3d)paramObject;
      return (this.field_612 == localTuple3d.field_612) && (this.field_613 == localTuple3d.field_613) && (this.field_614 == localTuple3d.field_614);
    }
    catch (ClassCastException localClassCastException)
    {
      return false;
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean epsilonEquals(Tuple3d paramTuple3d, double paramDouble)
  {
    double d = this.field_612 - paramTuple3d.field_612;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.field_613 - paramTuple3d.field_613;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.field_614 - paramTuple3d.field_614;
    return (d < 0.0D ? -d : d) <= paramDouble;
  }
  
  /**
   * @deprecated
   */
  public final void clamp(float paramFloat1, float paramFloat2, Tuple3d paramTuple3d)
  {
    clamp(paramFloat1, paramFloat2, paramTuple3d);
  }
  
  public final void clamp(double paramDouble1, double paramDouble2, Tuple3d paramTuple3d)
  {
    if (paramTuple3d.field_612 > paramDouble2) {
      this.field_612 = paramDouble2;
    } else if (paramTuple3d.field_612 < paramDouble1) {
      this.field_612 = paramDouble1;
    } else {
      this.field_612 = paramTuple3d.field_612;
    }
    if (paramTuple3d.field_613 > paramDouble2) {
      this.field_613 = paramDouble2;
    } else if (paramTuple3d.field_613 < paramDouble1) {
      this.field_613 = paramDouble1;
    } else {
      this.field_613 = paramTuple3d.field_613;
    }
    if (paramTuple3d.field_614 > paramDouble2) {
      this.field_614 = paramDouble2;
    } else if (paramTuple3d.field_614 < paramDouble1) {
      this.field_614 = paramDouble1;
    } else {
      this.field_614 = paramTuple3d.field_614;
    }
  }
  
  /**
   * @deprecated
   */
  public final void clampMin(float paramFloat, Tuple3d paramTuple3d)
  {
    clampMin(paramFloat, paramTuple3d);
  }
  
  public final void clampMin(double paramDouble, Tuple3d paramTuple3d)
  {
    if (paramTuple3d.field_612 < paramDouble) {
      this.field_612 = paramDouble;
    } else {
      this.field_612 = paramTuple3d.field_612;
    }
    if (paramTuple3d.field_613 < paramDouble) {
      this.field_613 = paramDouble;
    } else {
      this.field_613 = paramTuple3d.field_613;
    }
    if (paramTuple3d.field_614 < paramDouble) {
      this.field_614 = paramDouble;
    } else {
      this.field_614 = paramTuple3d.field_614;
    }
  }
  
  /**
   * @deprecated
   */
  public final void clampMax(float paramFloat, Tuple3d paramTuple3d)
  {
    clampMax(paramFloat, paramTuple3d);
  }
  
  public final void clampMax(double paramDouble, Tuple3d paramTuple3d)
  {
    if (paramTuple3d.field_612 > paramDouble) {
      this.field_612 = paramDouble;
    } else {
      this.field_612 = paramTuple3d.field_612;
    }
    if (paramTuple3d.field_613 > paramDouble) {
      this.field_613 = paramDouble;
    } else {
      this.field_613 = paramTuple3d.field_613;
    }
    if (paramTuple3d.field_614 > paramDouble) {
      this.field_614 = paramDouble;
    } else {
      this.field_614 = paramTuple3d.field_614;
    }
  }
  
  public final void absolute(Tuple3d paramTuple3d)
  {
    this.field_612 = Math.abs(paramTuple3d.field_612);
    this.field_613 = Math.abs(paramTuple3d.field_613);
    this.field_614 = Math.abs(paramTuple3d.field_614);
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
    if (this.field_612 > paramDouble2) {
      this.field_612 = paramDouble2;
    } else if (this.field_612 < paramDouble1) {
      this.field_612 = paramDouble1;
    }
    if (this.field_613 > paramDouble2) {
      this.field_613 = paramDouble2;
    } else if (this.field_613 < paramDouble1) {
      this.field_613 = paramDouble1;
    }
    if (this.field_614 > paramDouble2) {
      this.field_614 = paramDouble2;
    } else if (this.field_614 < paramDouble1) {
      this.field_614 = paramDouble1;
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
    if (this.field_612 < paramDouble) {
      this.field_612 = paramDouble;
    }
    if (this.field_613 < paramDouble) {
      this.field_613 = paramDouble;
    }
    if (this.field_614 < paramDouble) {
      this.field_614 = paramDouble;
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
    if (this.field_612 > paramDouble) {
      this.field_612 = paramDouble;
    }
    if (this.field_613 > paramDouble) {
      this.field_613 = paramDouble;
    }
    if (this.field_614 > paramDouble) {
      this.field_614 = paramDouble;
    }
  }
  
  public final void absolute()
  {
    this.field_612 = Math.abs(this.field_612);
    this.field_613 = Math.abs(this.field_613);
    this.field_614 = Math.abs(this.field_614);
  }
  
  /**
   * @deprecated
   */
  public final void interpolate(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2, float paramFloat)
  {
    interpolate(paramTuple3d1, paramTuple3d2, paramFloat);
  }
  
  public final void interpolate(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2, double paramDouble)
  {
    this.field_612 = ((1.0D - paramDouble) * paramTuple3d1.field_612 + paramDouble * paramTuple3d2.field_612);
    this.field_613 = ((1.0D - paramDouble) * paramTuple3d1.field_613 + paramDouble * paramTuple3d2.field_613);
    this.field_614 = ((1.0D - paramDouble) * paramTuple3d1.field_614 + paramDouble * paramTuple3d2.field_614);
  }
  
  /**
   * @deprecated
   */
  public final void interpolate(Tuple3d paramTuple3d, float paramFloat)
  {
    interpolate(paramTuple3d, paramFloat);
  }
  
  public final void interpolate(Tuple3d paramTuple3d, double paramDouble)
  {
    this.field_612 = ((1.0D - paramDouble) * this.field_612 + paramDouble * paramTuple3d.field_612);
    this.field_613 = ((1.0D - paramDouble) * this.field_613 + paramDouble * paramTuple3d.field_613);
    this.field_614 = ((1.0D - paramDouble) * this.field_614 + paramDouble * paramTuple3d.field_614);
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
 * Qualified Name:     javax.vecmath.Tuple3d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */