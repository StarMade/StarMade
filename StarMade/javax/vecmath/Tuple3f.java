package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple3f
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 5019834619484343712L;
  public float x;
  public float y;
  public float z;
  
  public Tuple3f(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
    this.z = paramFloat3;
  }
  
  public Tuple3f(float[] paramArrayOfFloat)
  {
    this.x = paramArrayOfFloat[0];
    this.y = paramArrayOfFloat[1];
    this.z = paramArrayOfFloat[2];
  }
  
  public Tuple3f(Tuple3f paramTuple3f)
  {
    this.x = paramTuple3f.x;
    this.y = paramTuple3f.y;
    this.z = paramTuple3f.z;
  }
  
  public Tuple3f(Tuple3d paramTuple3d)
  {
    this.x = ((float)paramTuple3d.x);
    this.y = ((float)paramTuple3d.y);
    this.z = ((float)paramTuple3d.z);
  }
  
  public Tuple3f()
  {
    this.x = 0.0F;
    this.y = 0.0F;
    this.z = 0.0F;
  }
  
  public String toString()
  {
    return "(" + this.x + ", " + this.y + ", " + this.z + ")";
  }
  
  public final void set(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.x = paramFloat1;
    this.y = paramFloat2;
    this.z = paramFloat3;
  }
  
  public final void set(float[] paramArrayOfFloat)
  {
    this.x = paramArrayOfFloat[0];
    this.y = paramArrayOfFloat[1];
    this.z = paramArrayOfFloat[2];
  }
  
  public final void set(Tuple3f paramTuple3f)
  {
    this.x = paramTuple3f.x;
    this.y = paramTuple3f.y;
    this.z = paramTuple3f.z;
  }
  
  public final void set(Tuple3d paramTuple3d)
  {
    this.x = ((float)paramTuple3d.x);
    this.y = ((float)paramTuple3d.y);
    this.z = ((float)paramTuple3d.z);
  }
  
  public final void get(float[] paramArrayOfFloat)
  {
    paramArrayOfFloat[0] = this.x;
    paramArrayOfFloat[1] = this.y;
    paramArrayOfFloat[2] = this.z;
  }
  
  public final void get(Tuple3f paramTuple3f)
  {
    paramTuple3f.x = this.x;
    paramTuple3f.y = this.y;
    paramTuple3f.z = this.z;
  }
  
  public final void add(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2)
  {
    paramTuple3f1.x += paramTuple3f2.x;
    paramTuple3f1.y += paramTuple3f2.y;
    paramTuple3f1.z += paramTuple3f2.z;
  }
  
  public final void add(Tuple3f paramTuple3f)
  {
    this.x += paramTuple3f.x;
    this.y += paramTuple3f.y;
    this.z += paramTuple3f.z;
  }
  
  public final void sub(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2)
  {
    paramTuple3f1.x -= paramTuple3f2.x;
    paramTuple3f1.y -= paramTuple3f2.y;
    paramTuple3f1.z -= paramTuple3f2.z;
  }
  
  public final void sub(Tuple3f paramTuple3f)
  {
    this.x -= paramTuple3f.x;
    this.y -= paramTuple3f.y;
    this.z -= paramTuple3f.z;
  }
  
  public final void negate(Tuple3f paramTuple3f)
  {
    this.x = (-paramTuple3f.x);
    this.y = (-paramTuple3f.y);
    this.z = (-paramTuple3f.z);
  }
  
  public final void negate()
  {
    this.x = (-this.x);
    this.y = (-this.y);
    this.z = (-this.z);
  }
  
  public final void scale(float paramFloat, Tuple3f paramTuple3f)
  {
    this.x = (paramFloat * paramTuple3f.x);
    this.y = (paramFloat * paramTuple3f.y);
    this.z = (paramFloat * paramTuple3f.z);
  }
  
  public final void scale(float paramFloat)
  {
    this.x *= paramFloat;
    this.y *= paramFloat;
    this.z *= paramFloat;
  }
  
  public final void scaleAdd(float paramFloat, Tuple3f paramTuple3f1, Tuple3f paramTuple3f2)
  {
    this.x = (paramFloat * paramTuple3f1.x + paramTuple3f2.x);
    this.y = (paramFloat * paramTuple3f1.y + paramTuple3f2.y);
    this.z = (paramFloat * paramTuple3f1.z + paramTuple3f2.z);
  }
  
  public final void scaleAdd(float paramFloat, Tuple3f paramTuple3f)
  {
    this.x = (paramFloat * this.x + paramTuple3f.x);
    this.y = (paramFloat * this.y + paramTuple3f.y);
    this.z = (paramFloat * this.z + paramTuple3f.z);
  }
  
  public boolean equals(Tuple3f paramTuple3f)
  {
    try
    {
      return (this.x == paramTuple3f.x) && (this.y == paramTuple3f.y) && (this.z == paramTuple3f.z);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple3f localTuple3f = (Tuple3f)paramObject;
      return (this.x == localTuple3f.x) && (this.y == localTuple3f.y) && (this.z == localTuple3f.z);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException) {}
    return false;
  }
  
  public boolean epsilonEquals(Tuple3f paramTuple3f, float paramFloat)
  {
    float f = this.x - paramTuple3f.x;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.y - paramTuple3f.y;
    if ((f < 0.0F ? -f : f) > paramFloat) {
      return false;
    }
    f = this.z - paramTuple3f.z;
    return (f < 0.0F ? -f : f) <= paramFloat;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + VecMathUtil.floatToIntBits(this.x);
    l = 31L * l + VecMathUtil.floatToIntBits(this.y);
    l = 31L * l + VecMathUtil.floatToIntBits(this.z);
    return (int)(l ^ l >> 32);
  }
  
  public final void clamp(float paramFloat1, float paramFloat2, Tuple3f paramTuple3f)
  {
    if (paramTuple3f.x > paramFloat2) {
      this.x = paramFloat2;
    } else if (paramTuple3f.x < paramFloat1) {
      this.x = paramFloat1;
    } else {
      this.x = paramTuple3f.x;
    }
    if (paramTuple3f.y > paramFloat2) {
      this.y = paramFloat2;
    } else if (paramTuple3f.y < paramFloat1) {
      this.y = paramFloat1;
    } else {
      this.y = paramTuple3f.y;
    }
    if (paramTuple3f.z > paramFloat2) {
      this.z = paramFloat2;
    } else if (paramTuple3f.z < paramFloat1) {
      this.z = paramFloat1;
    } else {
      this.z = paramTuple3f.z;
    }
  }
  
  public final void clampMin(float paramFloat, Tuple3f paramTuple3f)
  {
    if (paramTuple3f.x < paramFloat) {
      this.x = paramFloat;
    } else {
      this.x = paramTuple3f.x;
    }
    if (paramTuple3f.y < paramFloat) {
      this.y = paramFloat;
    } else {
      this.y = paramTuple3f.y;
    }
    if (paramTuple3f.z < paramFloat) {
      this.z = paramFloat;
    } else {
      this.z = paramTuple3f.z;
    }
  }
  
  public final void clampMax(float paramFloat, Tuple3f paramTuple3f)
  {
    if (paramTuple3f.x > paramFloat) {
      this.x = paramFloat;
    } else {
      this.x = paramTuple3f.x;
    }
    if (paramTuple3f.y > paramFloat) {
      this.y = paramFloat;
    } else {
      this.y = paramTuple3f.y;
    }
    if (paramTuple3f.z > paramFloat) {
      this.z = paramFloat;
    } else {
      this.z = paramTuple3f.z;
    }
  }
  
  public final void absolute(Tuple3f paramTuple3f)
  {
    this.x = Math.abs(paramTuple3f.x);
    this.y = Math.abs(paramTuple3f.y);
    this.z = Math.abs(paramTuple3f.z);
  }
  
  public final void clamp(float paramFloat1, float paramFloat2)
  {
    if (this.x > paramFloat2) {
      this.x = paramFloat2;
    } else if (this.x < paramFloat1) {
      this.x = paramFloat1;
    }
    if (this.y > paramFloat2) {
      this.y = paramFloat2;
    } else if (this.y < paramFloat1) {
      this.y = paramFloat1;
    }
    if (this.z > paramFloat2) {
      this.z = paramFloat2;
    } else if (this.z < paramFloat1) {
      this.z = paramFloat1;
    }
  }
  
  public final void clampMin(float paramFloat)
  {
    if (this.x < paramFloat) {
      this.x = paramFloat;
    }
    if (this.y < paramFloat) {
      this.y = paramFloat;
    }
    if (this.z < paramFloat) {
      this.z = paramFloat;
    }
  }
  
  public final void clampMax(float paramFloat)
  {
    if (this.x > paramFloat) {
      this.x = paramFloat;
    }
    if (this.y > paramFloat) {
      this.y = paramFloat;
    }
    if (this.z > paramFloat) {
      this.z = paramFloat;
    }
  }
  
  public final void absolute()
  {
    this.x = Math.abs(this.x);
    this.y = Math.abs(this.y);
    this.z = Math.abs(this.z);
  }
  
  public final void interpolate(Tuple3f paramTuple3f1, Tuple3f paramTuple3f2, float paramFloat)
  {
    this.x = ((1.0F - paramFloat) * paramTuple3f1.x + paramFloat * paramTuple3f2.x);
    this.y = ((1.0F - paramFloat) * paramTuple3f1.y + paramFloat * paramTuple3f2.y);
    this.z = ((1.0F - paramFloat) * paramTuple3f1.z + paramFloat * paramTuple3f2.z);
  }
  
  public final void interpolate(Tuple3f paramTuple3f, float paramFloat)
  {
    this.x = ((1.0F - paramFloat) * this.x + paramFloat * paramTuple3f.x);
    this.y = ((1.0F - paramFloat) * this.y + paramFloat * paramTuple3f.y);
    this.z = ((1.0F - paramFloat) * this.z + paramFloat * paramTuple3f.z);
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
 * Qualified Name:     javax.vecmath.Tuple3f
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */