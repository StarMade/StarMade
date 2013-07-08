package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple3d
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 5542096614926168415L;
  public double x;
  public double y;
  public double z;
  
  public Tuple3d(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    this.x = paramDouble1;
    this.y = paramDouble2;
    this.z = paramDouble3;
  }
  
  public Tuple3d(double[] paramArrayOfDouble)
  {
    this.x = paramArrayOfDouble[0];
    this.y = paramArrayOfDouble[1];
    this.z = paramArrayOfDouble[2];
  }
  
  public Tuple3d(Tuple3d paramTuple3d)
  {
    this.x = paramTuple3d.x;
    this.y = paramTuple3d.y;
    this.z = paramTuple3d.z;
  }
  
  public Tuple3d(Tuple3f paramTuple3f)
  {
    this.x = paramTuple3f.x;
    this.y = paramTuple3f.y;
    this.z = paramTuple3f.z;
  }
  
  public Tuple3d()
  {
    this.x = 0.0D;
    this.y = 0.0D;
    this.z = 0.0D;
  }
  
  public final void set(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    this.x = paramDouble1;
    this.y = paramDouble2;
    this.z = paramDouble3;
  }
  
  public final void set(double[] paramArrayOfDouble)
  {
    this.x = paramArrayOfDouble[0];
    this.y = paramArrayOfDouble[1];
    this.z = paramArrayOfDouble[2];
  }
  
  public final void set(Tuple3d paramTuple3d)
  {
    this.x = paramTuple3d.x;
    this.y = paramTuple3d.y;
    this.z = paramTuple3d.z;
  }
  
  public final void set(Tuple3f paramTuple3f)
  {
    this.x = paramTuple3f.x;
    this.y = paramTuple3f.y;
    this.z = paramTuple3f.z;
  }
  
  public final void get(double[] paramArrayOfDouble)
  {
    paramArrayOfDouble[0] = this.x;
    paramArrayOfDouble[1] = this.y;
    paramArrayOfDouble[2] = this.z;
  }
  
  public final void get(Tuple3d paramTuple3d)
  {
    paramTuple3d.x = this.x;
    paramTuple3d.y = this.y;
    paramTuple3d.z = this.z;
  }
  
  public final void add(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2)
  {
    paramTuple3d1.x += paramTuple3d2.x;
    paramTuple3d1.y += paramTuple3d2.y;
    paramTuple3d1.z += paramTuple3d2.z;
  }
  
  public final void add(Tuple3d paramTuple3d)
  {
    this.x += paramTuple3d.x;
    this.y += paramTuple3d.y;
    this.z += paramTuple3d.z;
  }
  
  public final void sub(Tuple3d paramTuple3d1, Tuple3d paramTuple3d2)
  {
    paramTuple3d1.x -= paramTuple3d2.x;
    paramTuple3d1.y -= paramTuple3d2.y;
    paramTuple3d1.z -= paramTuple3d2.z;
  }
  
  public final void sub(Tuple3d paramTuple3d)
  {
    this.x -= paramTuple3d.x;
    this.y -= paramTuple3d.y;
    this.z -= paramTuple3d.z;
  }
  
  public final void negate(Tuple3d paramTuple3d)
  {
    this.x = (-paramTuple3d.x);
    this.y = (-paramTuple3d.y);
    this.z = (-paramTuple3d.z);
  }
  
  public final void negate()
  {
    this.x = (-this.x);
    this.y = (-this.y);
    this.z = (-this.z);
  }
  
  public final void scale(double paramDouble, Tuple3d paramTuple3d)
  {
    this.x = (paramDouble * paramTuple3d.x);
    this.y = (paramDouble * paramTuple3d.y);
    this.z = (paramDouble * paramTuple3d.z);
  }
  
  public final void scale(double paramDouble)
  {
    this.x *= paramDouble;
    this.y *= paramDouble;
    this.z *= paramDouble;
  }
  
  public final void scaleAdd(double paramDouble, Tuple3d paramTuple3d1, Tuple3d paramTuple3d2)
  {
    this.x = (paramDouble * paramTuple3d1.x + paramTuple3d2.x);
    this.y = (paramDouble * paramTuple3d1.y + paramTuple3d2.y);
    this.z = (paramDouble * paramTuple3d1.z + paramTuple3d2.z);
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
    this.x = (paramDouble * this.x + paramTuple3d.x);
    this.y = (paramDouble * this.y + paramTuple3d.y);
    this.z = (paramDouble * this.z + paramTuple3d.z);
  }
  
  public String toString()
  {
    return "(" + this.x + ", " + this.y + ", " + this.z + ")";
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.doubleToLongBits(this.x);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.y);
    l = 31L * l + VecMathUtil.doubleToLongBits(this.z);
    return (int)(l ^ l >> 32);
  }
  
  public boolean equals(Tuple3d paramTuple3d)
  {
    try
    {
      return (this.x == paramTuple3d.x) && (this.y == paramTuple3d.y) && (this.z == paramTuple3d.z);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple3d localTuple3d = (Tuple3d)paramObject;
      return (this.x == localTuple3d.x) && (this.y == localTuple3d.y) && (this.z == localTuple3d.z);
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
    double d = this.x - paramTuple3d.x;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.y - paramTuple3d.y;
    if ((d < 0.0D ? -d : d) > paramDouble) {
      return false;
    }
    d = this.z - paramTuple3d.z;
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
    if (paramTuple3d.x > paramDouble2) {
      this.x = paramDouble2;
    } else if (paramTuple3d.x < paramDouble1) {
      this.x = paramDouble1;
    } else {
      this.x = paramTuple3d.x;
    }
    if (paramTuple3d.y > paramDouble2) {
      this.y = paramDouble2;
    } else if (paramTuple3d.y < paramDouble1) {
      this.y = paramDouble1;
    } else {
      this.y = paramTuple3d.y;
    }
    if (paramTuple3d.z > paramDouble2) {
      this.z = paramDouble2;
    } else if (paramTuple3d.z < paramDouble1) {
      this.z = paramDouble1;
    } else {
      this.z = paramTuple3d.z;
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
    if (paramTuple3d.x < paramDouble) {
      this.x = paramDouble;
    } else {
      this.x = paramTuple3d.x;
    }
    if (paramTuple3d.y < paramDouble) {
      this.y = paramDouble;
    } else {
      this.y = paramTuple3d.y;
    }
    if (paramTuple3d.z < paramDouble) {
      this.z = paramDouble;
    } else {
      this.z = paramTuple3d.z;
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
    if (paramTuple3d.x > paramDouble) {
      this.x = paramDouble;
    } else {
      this.x = paramTuple3d.x;
    }
    if (paramTuple3d.y > paramDouble) {
      this.y = paramDouble;
    } else {
      this.y = paramTuple3d.y;
    }
    if (paramTuple3d.z > paramDouble) {
      this.z = paramDouble;
    } else {
      this.z = paramTuple3d.z;
    }
  }
  
  public final void absolute(Tuple3d paramTuple3d)
  {
    this.x = Math.abs(paramTuple3d.x);
    this.y = Math.abs(paramTuple3d.y);
    this.z = Math.abs(paramTuple3d.z);
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
  }
  
  public final void absolute()
  {
    this.x = Math.abs(this.x);
    this.y = Math.abs(this.y);
    this.z = Math.abs(this.z);
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
    this.x = ((1.0D - paramDouble) * paramTuple3d1.x + paramDouble * paramTuple3d2.x);
    this.y = ((1.0D - paramDouble) * paramTuple3d1.y + paramDouble * paramTuple3d2.y);
    this.z = ((1.0D - paramDouble) * paramTuple3d1.z + paramDouble * paramTuple3d2.z);
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
    this.x = ((1.0D - paramDouble) * this.x + paramDouble * paramTuple3d.x);
    this.y = ((1.0D - paramDouble) * this.y + paramDouble * paramTuple3d.y);
    this.z = ((1.0D - paramDouble) * this.z + paramDouble * paramTuple3d.z);
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
 * Qualified Name:     javax.vecmath.Tuple3d
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */