package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple2d
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 6205762482756093838L;
  public double field_580;
  public double field_581;
  
  public Tuple2d(double paramDouble1, double paramDouble2)
  {
    this.field_580 = paramDouble1;
    this.field_581 = paramDouble2;
  }
  
  public Tuple2d(double[] paramArrayOfDouble)
  {
    this.field_580 = paramArrayOfDouble[0];
    this.field_581 = paramArrayOfDouble[1];
  }
  
  public Tuple2d(Tuple2d paramTuple2d)
  {
    this.field_580 = paramTuple2d.field_580;
    this.field_581 = paramTuple2d.field_581;
  }
  
  public Tuple2d(Tuple2f paramTuple2f)
  {
    this.field_580 = paramTuple2f.field_577;
    this.field_581 = paramTuple2f.field_578;
  }
  
  public Tuple2d()
  {
    this.field_580 = 0.0D;
    this.field_581 = 0.0D;
  }
  
  public final void set(double paramDouble1, double paramDouble2)
  {
    this.field_580 = paramDouble1;
    this.field_581 = paramDouble2;
  }
  
  public final void set(double[] paramArrayOfDouble)
  {
    this.field_580 = paramArrayOfDouble[0];
    this.field_581 = paramArrayOfDouble[1];
  }
  
  public final void set(Tuple2d paramTuple2d)
  {
    this.field_580 = paramTuple2d.field_580;
    this.field_581 = paramTuple2d.field_581;
  }
  
  public final void set(Tuple2f paramTuple2f)
  {
    this.field_580 = paramTuple2f.field_577;
    this.field_581 = paramTuple2f.field_578;
  }
  
  public final void get(double[] paramArrayOfDouble)
  {
    paramArrayOfDouble[0] = this.field_580;
    paramArrayOfDouble[1] = this.field_581;
  }
  
  public final void add(Tuple2d paramTuple2d1, Tuple2d paramTuple2d2)
  {
    paramTuple2d1.field_580 += paramTuple2d2.field_580;
    paramTuple2d1.field_581 += paramTuple2d2.field_581;
  }
  
  public final void add(Tuple2d paramTuple2d)
  {
    this.field_580 += paramTuple2d.field_580;
    this.field_581 += paramTuple2d.field_581;
  }
  
  public final void sub(Tuple2d paramTuple2d1, Tuple2d paramTuple2d2)
  {
    paramTuple2d1.field_580 -= paramTuple2d2.field_580;
    paramTuple2d1.field_581 -= paramTuple2d2.field_581;
  }
  
  public final void sub(Tuple2d paramTuple2d)
  {
    this.field_580 -= paramTuple2d.field_580;
    this.field_581 -= paramTuple2d.field_581;
  }
  
  public final void negate(Tuple2d paramTuple2d)
  {
    this.field_580 = (-paramTuple2d.field_580);
    this.field_581 = (-paramTuple2d.field_581);
  }
  
  public final void negate()
  {
    this.field_580 = (-this.field_580);
    this.field_581 = (-this.field_581);
  }
  
  public final void scale(double paramDouble, Tuple2d paramTuple2d)
  {
    this.field_580 = (paramDouble * paramTuple2d.field_580);
    this.field_581 = (paramDouble * paramTuple2d.field_581);
  }
  
  public final void scale(double paramDouble)
  {
    this.field_580 *= paramDouble;
    this.field_581 *= paramDouble;
  }
  
  public final void scaleAdd(double paramDouble, Tuple2d paramTuple2d1, Tuple2d paramTuple2d2)
  {
    this.field_580 = (paramDouble * paramTuple2d1.field_580 + paramTuple2d2.field_580);
    this.field_581 = (paramDouble * paramTuple2d1.field_581 + paramTuple2d2.field_581);
  }
  
  public final void scaleAdd(double paramDouble, Tuple2d paramTuple2d)
  {
    this.field_580 = (paramDouble * this.field_580 + paramTuple2d.field_580);
    this.field_581 = (paramDouble * this.field_581 + paramTuple2d.field_581);
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_580);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.field_581);
    return (int)(l ^ l >> 32);
  }
  
  public boolean equals(Tuple2d paramTuple2d)
  {
    try
    {
      return (this.field_580 == paramTuple2d.field_580) && (this.field_581 == paramTuple2d.field_581);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple2d localTuple2d = (Tuple2d)paramObject;
      return (this.field_580 == localTuple2d.field_580) && (this.field_581 == localTuple2d.field_581);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException) {}
    return false;
  }
  
  public boolean epsilonEquals(Tuple2d paramTuple2d, double paramDouble)
  {
    double d = this.field_580 - paramTuple2d.field_580;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.field_581 - paramTuple2d.field_581;
    return (d < 0.0D ? -d : d) <= paramDouble;
  }
  
  public String toString()
  {
    return "(" + this.field_580 + ", " + this.field_581 + ")";
  }
  
  public final void clamp(double paramDouble1, double paramDouble2, Tuple2d paramTuple2d)
  {
    if (paramTuple2d.field_580 > paramDouble2) {
      this.field_580 = paramDouble2;
    } else if (paramTuple2d.field_580 < paramDouble1) {
      this.field_580 = paramDouble1;
    } else {
      this.field_580 = paramTuple2d.field_580;
    }
    if (paramTuple2d.field_581 > paramDouble2) {
      this.field_581 = paramDouble2;
    } else if (paramTuple2d.field_581 < paramDouble1) {
      this.field_581 = paramDouble1;
    } else {
      this.field_581 = paramTuple2d.field_581;
    }
  }
  
  public final void clampMin(double paramDouble, Tuple2d paramTuple2d)
  {
    if (paramTuple2d.field_580 < paramDouble) {
      this.field_580 = paramDouble;
    } else {
      this.field_580 = paramTuple2d.field_580;
    }
    if (paramTuple2d.field_581 < paramDouble) {
      this.field_581 = paramDouble;
    } else {
      this.field_581 = paramTuple2d.field_581;
    }
  }
  
  public final void clampMax(double paramDouble, Tuple2d paramTuple2d)
  {
    if (paramTuple2d.field_580 > paramDouble) {
      this.field_580 = paramDouble;
    } else {
      this.field_580 = paramTuple2d.field_580;
    }
    if (paramTuple2d.field_581 > paramDouble) {
      this.field_581 = paramDouble;
    } else {
      this.field_581 = paramTuple2d.field_581;
    }
  }
  
  public final void absolute(Tuple2d paramTuple2d)
  {
    this.field_580 = Math.abs(paramTuple2d.field_580);
    this.field_581 = Math.abs(paramTuple2d.field_581);
  }
  
  public final void clamp(double paramDouble1, double paramDouble2)
  {
    if (this.field_580 > paramDouble2) {
      this.field_580 = paramDouble2;
    } else if (this.field_580 < paramDouble1) {
      this.field_580 = paramDouble1;
    }
    if (this.field_581 > paramDouble2) {
      this.field_581 = paramDouble2;
    } else if (this.field_581 < paramDouble1) {
      this.field_581 = paramDouble1;
    }
  }
  
  public final void clampMin(double paramDouble)
  {
    if (this.field_580 < paramDouble) {
      this.field_580 = paramDouble;
    }
    if (this.field_581 < paramDouble) {
      this.field_581 = paramDouble;
    }
  }
  
  public final void clampMax(double paramDouble)
  {
    if (this.field_580 > paramDouble) {
      this.field_580 = paramDouble;
    }
    if (this.field_581 > paramDouble) {
      this.field_581 = paramDouble;
    }
  }
  
  public final void absolute()
  {
    this.field_580 = Math.abs(this.field_580);
    this.field_581 = Math.abs(this.field_581);
  }
  
  public final void interpolate(Tuple2d paramTuple2d1, Tuple2d paramTuple2d2, double paramDouble)
  {
    this.field_580 = ((1.0D - paramDouble) * paramTuple2d1.field_580 + paramDouble * paramTuple2d2.field_580);
    this.field_581 = ((1.0D - paramDouble) * paramTuple2d1.field_581 + paramDouble * paramTuple2d2.field_581);
  }
  
  public final void interpolate(Tuple2d paramTuple2d, double paramDouble)
  {
    this.field_580 = ((1.0D - paramDouble) * this.field_580 + paramDouble * paramTuple2d.field_580);
    this.field_581 = ((1.0D - paramDouble) * this.field_581 + paramDouble * paramTuple2d.field_581);
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
 * Qualified Name:     javax.vecmath.Tuple2d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */