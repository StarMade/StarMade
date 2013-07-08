package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple4d
  implements Serializable, Cloneable
{
  static final long serialVersionUID = -4748953690425311052L;
  public double x;
  public double y;
  public double z;
  public double w;
  
  public Tuple4d(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.x = paramDouble1;
    this.y = paramDouble2;
    this.z = paramDouble3;
    this.w = paramDouble4;
  }
  
  public Tuple4d(double[] paramArrayOfDouble)
  {
    this.x = paramArrayOfDouble[0];
    this.y = paramArrayOfDouble[1];
    this.z = paramArrayOfDouble[2];
    this.w = paramArrayOfDouble[3];
  }
  
  public Tuple4d(Tuple4d paramTuple4d)
  {
    this.x = paramTuple4d.x;
    this.y = paramTuple4d.y;
    this.z = paramTuple4d.z;
    this.w = paramTuple4d.w;
  }
  
  public Tuple4d(Tuple4f paramTuple4f)
  {
    this.x = paramTuple4f.x;
    this.y = paramTuple4f.y;
    this.z = paramTuple4f.z;
    this.w = paramTuple4f.w;
  }
  
  public Tuple4d()
  {
    this.x = 0.0D;
    this.y = 0.0D;
    this.z = 0.0D;
    this.w = 0.0D;
  }
  
  public final void set(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
  {
    this.x = paramDouble1;
    this.y = paramDouble2;
    this.z = paramDouble3;
    this.w = paramDouble4;
  }
  
  public final void set(double[] paramArrayOfDouble)
  {
    this.x = paramArrayOfDouble[0];
    this.y = paramArrayOfDouble[1];
    this.z = paramArrayOfDouble[2];
    this.w = paramArrayOfDouble[3];
  }
  
  public final void set(Tuple4d paramTuple4d)
  {
    this.x = paramTuple4d.x;
    this.y = paramTuple4d.y;
    this.z = paramTuple4d.z;
    this.w = paramTuple4d.w;
  }
  
  public final void set(Tuple4f paramTuple4f)
  {
    this.x = paramTuple4f.x;
    this.y = paramTuple4f.y;
    this.z = paramTuple4f.z;
    this.w = paramTuple4f.w;
  }
  
  public final void get(double[] paramArrayOfDouble)
  {
    paramArrayOfDouble[0] = this.x;
    paramArrayOfDouble[1] = this.y;
    paramArrayOfDouble[2] = this.z;
    paramArrayOfDouble[3] = this.w;
  }
  
  public final void get(Tuple4d paramTuple4d)
  {
    paramTuple4d.x = this.x;
    paramTuple4d.y = this.y;
    paramTuple4d.z = this.z;
    paramTuple4d.w = this.w;
  }
  
  public final void add(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2)
  {
    paramTuple4d1.x += paramTuple4d2.x;
    paramTuple4d1.y += paramTuple4d2.y;
    paramTuple4d1.z += paramTuple4d2.z;
    paramTuple4d1.w += paramTuple4d2.w;
  }
  
  public final void add(Tuple4d paramTuple4d)
  {
    this.x += paramTuple4d.x;
    this.y += paramTuple4d.y;
    this.z += paramTuple4d.z;
    this.w += paramTuple4d.w;
  }
  
  public final void sub(Tuple4d paramTuple4d1, Tuple4d paramTuple4d2)
  {
    paramTuple4d1.x -= paramTuple4d2.x;
    paramTuple4d1.y -= paramTuple4d2.y;
    paramTuple4d1.z -= paramTuple4d2.z;
    paramTuple4d1.w -= paramTuple4d2.w;
  }
  
  public final void sub(Tuple4d paramTuple4d)
  {
    this.x -= paramTuple4d.x;
    this.y -= paramTuple4d.y;
    this.z -= paramTuple4d.z;
    this.w -= paramTuple4d.w;
  }
  
  public final void negate(Tuple4d paramTuple4d)
  {
    this.x = (-paramTuple4d.x);
    this.y = (-paramTuple4d.y);
    this.z = (-paramTuple4d.z);
    this.w = (-paramTuple4d.w);
  }
  
  public final void negate()
  {
    this.x = (-this.x);
    this.y = (-this.y);
    this.z = (-this.z);
    this.w = (-this.w);
  }
  
  public final void scale(double paramDouble, Tuple4d paramTuple4d)
  {
    this.x = (paramDouble * paramTuple4d.x);
    this.y = (paramDouble * paramTuple4d.y);
    this.z = (paramDouble * paramTuple4d.z);
    this.w = (paramDouble * paramTuple4d.w);
  }
  
  public final void scale(double paramDouble)
  {
    this.x *= paramDouble;
    this.y *= paramDouble;
    this.z *= paramDouble;
    this.w *= paramDouble;
  }
  
  public final void scaleAdd(double paramDouble, Tuple4d paramTuple4d1, Tuple4d paramTuple4d2)
  {
    this.x = (paramDouble * paramTuple4d1.x + paramTuple4d2.x);
    this.y = (paramDouble * paramTuple4d1.y + paramTuple4d2.y);
    this.z = (paramDouble * paramTuple4d1.z + paramTuple4d2.z);
    this.w = (paramDouble * paramTuple4d1.w + paramTuple4d2.w);
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
    this.x = (paramDouble * this.x + paramTuple4d.x);
    this.y = (paramDouble * this.y + paramTuple4d.y);
    this.z = (paramDouble * this.z + paramTuple4d.z);
    this.w = (paramDouble * this.w + paramTuple4d.w);
  }
  
  public String toString()
  {
    return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
  }
  
  public boolean equals(Tuple4d paramTuple4d)
  {
    try
    {
      return (this.x == paramTuple4d.x) && (this.y == paramTuple4d.y) && (this.z == paramTuple4d.z) && (this.w == paramTuple4d.w);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple4d localTuple4d = (Tuple4d)paramObject;
      return (this.x == localTuple4d.x) && (this.y == localTuple4d.y) && (this.z == localTuple4d.z) && (this.w == localTuple4d.w);
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
    double d = this.x - paramTuple4d.x;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.y - paramTuple4d.y;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.z - paramTuple4d.z;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.w - paramTuple4d.w;
    return (d < 0.0D ? -d : d) <= paramDouble;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.x);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.y);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.z);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.w);
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
    if (paramTuple4d.x > paramDouble2) {
      this.x = paramDouble2;
    } else if (paramTuple4d.x < paramDouble1) {
      this.x = paramDouble1;
    } else {
      this.x = paramTuple4d.x;
    }
    if (paramTuple4d.y > paramDouble2) {
      this.y = paramDouble2;
    } else if (paramTuple4d.y < paramDouble1) {
      this.y = paramDouble1;
    } else {
      this.y = paramTuple4d.y;
    }
    if (paramTuple4d.z > paramDouble2) {
      this.z = paramDouble2;
    } else if (paramTuple4d.z < paramDouble1) {
      this.z = paramDouble1;
    } else {
      this.z = paramTuple4d.z;
    }
    if (paramTuple4d.w > paramDouble2) {
      this.w = paramDouble2;
    } else if (paramTuple4d.w < paramDouble1) {
      this.w = paramDouble1;
    } else {
      this.w = paramTuple4d.w;
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
    if (paramTuple4d.x < paramDouble) {
      this.x = paramDouble;
    } else {
      this.x = paramTuple4d.x;
    }
    if (paramTuple4d.y < paramDouble) {
      this.y = paramDouble;
    } else {
      this.y = paramTuple4d.y;
    }
    if (paramTuple4d.z < paramDouble) {
      this.z = paramDouble;
    } else {
      this.z = paramTuple4d.z;
    }
    if (paramTuple4d.w < paramDouble) {
      this.w = paramDouble;
    } else {
      this.w = paramTuple4d.w;
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
    if (paramTuple4d.x > paramDouble) {
      this.x = paramDouble;
    } else {
      this.x = paramTuple4d.x;
    }
    if (paramTuple4d.y > paramDouble) {
      this.y = paramDouble;
    } else {
      this.y = paramTuple4d.y;
    }
    if (paramTuple4d.z > paramDouble) {
      this.z = paramDouble;
    } else {
      this.z = paramTuple4d.z;
    }
    if (paramTuple4d.w > paramDouble) {
      this.w = paramDouble;
    } else {
      this.w = paramTuple4d.z;
    }
  }
  
  public final void absolute(Tuple4d paramTuple4d)
  {
    this.x = Math.abs(paramTuple4d.x);
    this.y = Math.abs(paramTuple4d.y);
    this.z = Math.abs(paramTuple4d.z);
    this.w = Math.abs(paramTuple4d.w);
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
    if (this.x > paramDouble2) {
      this.x = paramDouble2;
    } else if (this.x < paramDouble1) {
      this.x = paramDouble1;
    }
    if (this.y > paramDouble2) {
      this.y = paramDouble2;
    } else if (this.y < paramDouble1) {
      this.y = paramDouble1;
    }
    if (this.z > paramDouble2) {
      this.z = paramDouble2;
    } else if (this.z < paramDouble1) {
      this.z = paramDouble1;
    }
    if (this.w > paramDouble2) {
      this.w = paramDouble2;
    } else if (this.w < paramDouble1) {
      this.w = paramDouble1;
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
    if (this.x < paramDouble) {
      this.x = paramDouble;
    }
    if (this.y < paramDouble) {
      this.y = paramDouble;
    }
    if (this.z < paramDouble) {
      this.z = paramDouble;
    }
    if (this.w < paramDouble) {
      this.w = paramDouble;
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
    if (this.x > paramDouble) {
      this.x = paramDouble;
    }
    if (this.y > paramDouble) {
      this.y = paramDouble;
    }
    if (this.z > paramDouble) {
      this.z = paramDouble;
    }
    if (this.w > paramDouble) {
      this.w = paramDouble;
    }
  }
  
  public final void absolute()
  {
    this.x = Math.abs(this.x);
    this.y = Math.abs(this.y);
    this.z = Math.abs(this.z);
    this.w = Math.abs(this.w);
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
    this.x = ((1.0D - paramDouble) * paramTuple4d1.x + paramDouble * paramTuple4d2.x);
    this.y = ((1.0D - paramDouble) * paramTuple4d1.y + paramDouble * paramTuple4d2.y);
    this.z = ((1.0D - paramDouble) * paramTuple4d1.z + paramDouble * paramTuple4d2.z);
    this.w = ((1.0D - paramDouble) * paramTuple4d1.w + paramDouble * paramTuple4d2.w);
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
    this.x = ((1.0D - paramDouble) * this.x + paramDouble * paramTuple4d.x);
    this.y = ((1.0D - paramDouble) * this.y + paramDouble * paramTuple4d.y);
    this.z = ((1.0D - paramDouble) * this.z + paramDouble * paramTuple4d.z);
    this.w = ((1.0D - paramDouble) * this.w + paramDouble * paramTuple4d.w);
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


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Tuple4d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */