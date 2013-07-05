package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple2i
  implements Serializable, Cloneable
{
  static final long serialVersionUID = -3555701650170169638L;
  public int x;
  public int y;

  public Tuple2i(int paramInt1, int paramInt2)
  {
    this.x = paramInt1;
    this.y = paramInt2;
  }

  public Tuple2i(int[] paramArrayOfInt)
  {
    this.x = paramArrayOfInt[0];
    this.y = paramArrayOfInt[1];
  }

  public Tuple2i(Tuple2i paramTuple2i)
  {
    this.x = paramTuple2i.x;
    this.y = paramTuple2i.y;
  }

  public Tuple2i()
  {
    this.x = 0;
    this.y = 0;
  }

  public final void set(int paramInt1, int paramInt2)
  {
    this.x = paramInt1;
    this.y = paramInt2;
  }

  public final void set(int[] paramArrayOfInt)
  {
    this.x = paramArrayOfInt[0];
    this.y = paramArrayOfInt[1];
  }

  public final void set(Tuple2i paramTuple2i)
  {
    this.x = paramTuple2i.x;
    this.y = paramTuple2i.y;
  }

  public final void get(int[] paramArrayOfInt)
  {
    paramArrayOfInt[0] = this.x;
    paramArrayOfInt[1] = this.y;
  }

  public final void get(Tuple2i paramTuple2i)
  {
    paramTuple2i.x = this.x;
    paramTuple2i.y = this.y;
  }

  public final void add(Tuple2i paramTuple2i1, Tuple2i paramTuple2i2)
  {
    paramTuple2i1.x += paramTuple2i2.x;
    paramTuple2i1.y += paramTuple2i2.y;
  }

  public final void add(Tuple2i paramTuple2i)
  {
    this.x += paramTuple2i.x;
    this.y += paramTuple2i.y;
  }

  public final void sub(Tuple2i paramTuple2i1, Tuple2i paramTuple2i2)
  {
    paramTuple2i1.x -= paramTuple2i2.x;
    paramTuple2i1.y -= paramTuple2i2.y;
  }

  public final void sub(Tuple2i paramTuple2i)
  {
    this.x -= paramTuple2i.x;
    this.y -= paramTuple2i.y;
  }

  public final void negate(Tuple2i paramTuple2i)
  {
    this.x = (-paramTuple2i.x);
    this.y = (-paramTuple2i.y);
  }

  public final void negate()
  {
    this.x = (-this.x);
    this.y = (-this.y);
  }

  public final void scale(int paramInt, Tuple2i paramTuple2i)
  {
    this.x = (paramInt * paramTuple2i.x);
    this.y = (paramInt * paramTuple2i.y);
  }

  public final void scale(int paramInt)
  {
    this.x *= paramInt;
    this.y *= paramInt;
  }

  public final void scaleAdd(int paramInt, Tuple2i paramTuple2i1, Tuple2i paramTuple2i2)
  {
    this.x = (paramInt * paramTuple2i1.x + paramTuple2i2.x);
    this.y = (paramInt * paramTuple2i1.y + paramTuple2i2.y);
  }

  public final void scaleAdd(int paramInt, Tuple2i paramTuple2i)
  {
    this.x = (paramInt * this.x + paramTuple2i.x);
    this.y = (paramInt * this.y + paramTuple2i.y);
  }

  public String toString()
  {
    return "(" + this.x + ", " + this.y + ")";
  }

  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple2i localTuple2i = (Tuple2i)paramObject;
      return (this.x == localTuple2i.x) && (this.y == localTuple2i.y);
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
    return (int)(l ^ l >> 32);
  }

  public final void clamp(int paramInt1, int paramInt2, Tuple2i paramTuple2i)
  {
    if (paramTuple2i.x > paramInt2)
      this.x = paramInt2;
    else if (paramTuple2i.x < paramInt1)
      this.x = paramInt1;
    else
      this.x = paramTuple2i.x;
    if (paramTuple2i.y > paramInt2)
      this.y = paramInt2;
    else if (paramTuple2i.y < paramInt1)
      this.y = paramInt1;
    else
      this.y = paramTuple2i.y;
  }

  public final void clampMin(int paramInt, Tuple2i paramTuple2i)
  {
    if (paramTuple2i.x < paramInt)
      this.x = paramInt;
    else
      this.x = paramTuple2i.x;
    if (paramTuple2i.y < paramInt)
      this.y = paramInt;
    else
      this.y = paramTuple2i.y;
  }

  public final void clampMax(int paramInt, Tuple2i paramTuple2i)
  {
    if (paramTuple2i.x > paramInt)
      this.x = paramInt;
    else
      this.x = paramTuple2i.x;
    if (paramTuple2i.y > paramInt)
      this.y = paramInt;
    else
      this.y = paramTuple2i.y;
  }

  public final void absolute(Tuple2i paramTuple2i)
  {
    this.x = Math.abs(paramTuple2i.x);
    this.y = Math.abs(paramTuple2i.y);
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
  }

  public final void clampMin(int paramInt)
  {
    if (this.x < paramInt)
      this.x = paramInt;
    if (this.y < paramInt)
      this.y = paramInt;
  }

  public final void clampMax(int paramInt)
  {
    if (this.x > paramInt)
      this.x = paramInt;
    if (this.y > paramInt)
      this.y = paramInt;
  }

  public final void absolute()
  {
    this.x = Math.abs(this.x);
    this.y = Math.abs(this.y);
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
 * Qualified Name:     javax.vecmath.Tuple2i
 * JD-Core Version:    0.6.2
 */