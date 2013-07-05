package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple3i
  implements Serializable, Cloneable
{
  static final long serialVersionUID = -732740491767276200L;
  public int x;
  public int y;
  public int z;

  public Tuple3i(int paramInt1, int paramInt2, int paramInt3)
  {
    this.x = paramInt1;
    this.y = paramInt2;
    this.z = paramInt3;
  }

  public Tuple3i(int[] paramArrayOfInt)
  {
    this.x = paramArrayOfInt[0];
    this.y = paramArrayOfInt[1];
    this.z = paramArrayOfInt[2];
  }

  public Tuple3i(Tuple3i paramTuple3i)
  {
    this.x = paramTuple3i.x;
    this.y = paramTuple3i.y;
    this.z = paramTuple3i.z;
  }

  public Tuple3i()
  {
    this.x = 0;
    this.y = 0;
    this.z = 0;
  }

  public final void set(int paramInt1, int paramInt2, int paramInt3)
  {
    this.x = paramInt1;
    this.y = paramInt2;
    this.z = paramInt3;
  }

  public final void set(int[] paramArrayOfInt)
  {
    this.x = paramArrayOfInt[0];
    this.y = paramArrayOfInt[1];
    this.z = paramArrayOfInt[2];
  }

  public final void set(Tuple3i paramTuple3i)
  {
    this.x = paramTuple3i.x;
    this.y = paramTuple3i.y;
    this.z = paramTuple3i.z;
  }

  public final void get(int[] paramArrayOfInt)
  {
    paramArrayOfInt[0] = this.x;
    paramArrayOfInt[1] = this.y;
    paramArrayOfInt[2] = this.z;
  }

  public final void get(Tuple3i paramTuple3i)
  {
    paramTuple3i.x = this.x;
    paramTuple3i.y = this.y;
    paramTuple3i.z = this.z;
  }

  public final void add(Tuple3i paramTuple3i1, Tuple3i paramTuple3i2)
  {
    paramTuple3i1.x += paramTuple3i2.x;
    paramTuple3i1.y += paramTuple3i2.y;
    paramTuple3i1.z += paramTuple3i2.z;
  }

  public final void add(Tuple3i paramTuple3i)
  {
    this.x += paramTuple3i.x;
    this.y += paramTuple3i.y;
    this.z += paramTuple3i.z;
  }

  public final void sub(Tuple3i paramTuple3i1, Tuple3i paramTuple3i2)
  {
    paramTuple3i1.x -= paramTuple3i2.x;
    paramTuple3i1.y -= paramTuple3i2.y;
    paramTuple3i1.z -= paramTuple3i2.z;
  }

  public final void sub(Tuple3i paramTuple3i)
  {
    this.x -= paramTuple3i.x;
    this.y -= paramTuple3i.y;
    this.z -= paramTuple3i.z;
  }

  public final void negate(Tuple3i paramTuple3i)
  {
    this.x = (-paramTuple3i.x);
    this.y = (-paramTuple3i.y);
    this.z = (-paramTuple3i.z);
  }

  public final void negate()
  {
    this.x = (-this.x);
    this.y = (-this.y);
    this.z = (-this.z);
  }

  public final void scale(int paramInt, Tuple3i paramTuple3i)
  {
    this.x = (paramInt * paramTuple3i.x);
    this.y = (paramInt * paramTuple3i.y);
    this.z = (paramInt * paramTuple3i.z);
  }

  public final void scale(int paramInt)
  {
    this.x *= paramInt;
    this.y *= paramInt;
    this.z *= paramInt;
  }

  public final void scaleAdd(int paramInt, Tuple3i paramTuple3i1, Tuple3i paramTuple3i2)
  {
    this.x = (paramInt * paramTuple3i1.x + paramTuple3i2.x);
    this.y = (paramInt * paramTuple3i1.y + paramTuple3i2.y);
    this.z = (paramInt * paramTuple3i1.z + paramTuple3i2.z);
  }

  public final void scaleAdd(int paramInt, Tuple3i paramTuple3i)
  {
    this.x = (paramInt * this.x + paramTuple3i.x);
    this.y = (paramInt * this.y + paramTuple3i.y);
    this.z = (paramInt * this.z + paramTuple3i.z);
  }

  public String toString()
  {
    return "(" + this.x + ", " + this.y + ", " + this.z + ")";
  }

  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple3i localTuple3i = (Tuple3i)paramObject;
      return (this.x == localTuple3i.x) && (this.y == localTuple3i.y) && (this.z == localTuple3i.z);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return false;
  }

  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + this.x;
    l = 31L * l + this.y;
    l = 31L * l + this.z;
    return (int)(l ^ l >> 32);
  }

  public final void clamp(int paramInt1, int paramInt2, Tuple3i paramTuple3i)
  {
    if (paramTuple3i.x > paramInt2)
      this.x = paramInt2;
    else if (paramTuple3i.x < paramInt1)
      this.x = paramInt1;
    else
      this.x = paramTuple3i.x;
    if (paramTuple3i.y > paramInt2)
      this.y = paramInt2;
    else if (paramTuple3i.y < paramInt1)
      this.y = paramInt1;
    else
      this.y = paramTuple3i.y;
    if (paramTuple3i.z > paramInt2)
      this.z = paramInt2;
    else if (paramTuple3i.z < paramInt1)
      this.z = paramInt1;
    else
      this.z = paramTuple3i.z;
  }

  public final void clampMin(int paramInt, Tuple3i paramTuple3i)
  {
    if (paramTuple3i.x < paramInt)
      this.x = paramInt;
    else
      this.x = paramTuple3i.x;
    if (paramTuple3i.y < paramInt)
      this.y = paramInt;
    else
      this.y = paramTuple3i.y;
    if (paramTuple3i.z < paramInt)
      this.z = paramInt;
    else
      this.z = paramTuple3i.z;
  }

  public final void clampMax(int paramInt, Tuple3i paramTuple3i)
  {
    if (paramTuple3i.x > paramInt)
      this.x = paramInt;
    else
      this.x = paramTuple3i.x;
    if (paramTuple3i.y > paramInt)
      this.y = paramInt;
    else
      this.y = paramTuple3i.y;
    if (paramTuple3i.z > paramInt)
      this.z = paramInt;
    else
      this.z = paramTuple3i.z;
  }

  public final void absolute(Tuple3i paramTuple3i)
  {
    this.x = Math.abs(paramTuple3i.x);
    this.y = Math.abs(paramTuple3i.y);
    this.z = Math.abs(paramTuple3i.z);
  }

  public final void clamp(int paramInt1, int paramInt2)
  {
    if (this.x > paramInt2)
      this.x = paramInt2;
    else if (this.x < paramInt1)
      this.x = paramInt1;
    if (this.y > paramInt2)
      this.y = paramInt2;
    else if (this.y < paramInt1)
      this.y = paramInt1;
    if (this.z > paramInt2)
      this.z = paramInt2;
    else if (this.z < paramInt1)
      this.z = paramInt1;
  }

  public final void clampMin(int paramInt)
  {
    if (this.x < paramInt)
      this.x = paramInt;
    if (this.y < paramInt)
      this.y = paramInt;
    if (this.z < paramInt)
      this.z = paramInt;
  }

  public final void clampMax(int paramInt)
  {
    if (this.x > paramInt)
      this.x = paramInt;
    if (this.y > paramInt)
      this.y = paramInt;
    if (this.z > paramInt)
      this.z = paramInt;
  }

  public final void absolute()
  {
    this.x = Math.abs(this.x);
    this.y = Math.abs(this.y);
    this.z = Math.abs(this.z);
  }

  public Object clone()
  {
    try
    {
      return super.clone();
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
    }
    throw new InternalError();
  }
}

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     javax.vecmath.Tuple3i
 * JD-Core Version:    0.6.2
 */