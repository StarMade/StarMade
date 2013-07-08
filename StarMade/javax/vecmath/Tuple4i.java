package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple4i
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 8064614250942616720L;
  public int x;
  public int y;
  public int z;
  public int w;
  
  public Tuple4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.x = paramInt1;
    this.y = paramInt2;
    this.z = paramInt3;
    this.w = paramInt4;
  }
  
  public Tuple4i(int[] paramArrayOfInt)
  {
    this.x = paramArrayOfInt[0];
    this.y = paramArrayOfInt[1];
    this.z = paramArrayOfInt[2];
    this.w = paramArrayOfInt[3];
  }
  
  public Tuple4i(Tuple4i paramTuple4i)
  {
    this.x = paramTuple4i.x;
    this.y = paramTuple4i.y;
    this.z = paramTuple4i.z;
    this.w = paramTuple4i.w;
  }
  
  public Tuple4i()
  {
    this.x = 0;
    this.y = 0;
    this.z = 0;
    this.w = 0;
  }
  
  public final void set(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.x = paramInt1;
    this.y = paramInt2;
    this.z = paramInt3;
    this.w = paramInt4;
  }
  
  public final void set(int[] paramArrayOfInt)
  {
    this.x = paramArrayOfInt[0];
    this.y = paramArrayOfInt[1];
    this.z = paramArrayOfInt[2];
    this.w = paramArrayOfInt[3];
  }
  
  public final void set(Tuple4i paramTuple4i)
  {
    this.x = paramTuple4i.x;
    this.y = paramTuple4i.y;
    this.z = paramTuple4i.z;
    this.w = paramTuple4i.w;
  }
  
  public final void get(int[] paramArrayOfInt)
  {
    paramArrayOfInt[0] = this.x;
    paramArrayOfInt[1] = this.y;
    paramArrayOfInt[2] = this.z;
    paramArrayOfInt[3] = this.w;
  }
  
  public final void get(Tuple4i paramTuple4i)
  {
    paramTuple4i.x = this.x;
    paramTuple4i.y = this.y;
    paramTuple4i.z = this.z;
    paramTuple4i.w = this.w;
  }
  
  public final void add(Tuple4i paramTuple4i1, Tuple4i paramTuple4i2)
  {
    paramTuple4i1.x += paramTuple4i2.x;
    paramTuple4i1.y += paramTuple4i2.y;
    paramTuple4i1.z += paramTuple4i2.z;
    paramTuple4i1.w += paramTuple4i2.w;
  }
  
  public final void add(Tuple4i paramTuple4i)
  {
    this.x += paramTuple4i.x;
    this.y += paramTuple4i.y;
    this.z += paramTuple4i.z;
    this.w += paramTuple4i.w;
  }
  
  public final void sub(Tuple4i paramTuple4i1, Tuple4i paramTuple4i2)
  {
    paramTuple4i1.x -= paramTuple4i2.x;
    paramTuple4i1.y -= paramTuple4i2.y;
    paramTuple4i1.z -= paramTuple4i2.z;
    paramTuple4i1.w -= paramTuple4i2.w;
  }
  
  public final void sub(Tuple4i paramTuple4i)
  {
    this.x -= paramTuple4i.x;
    this.y -= paramTuple4i.y;
    this.z -= paramTuple4i.z;
    this.w -= paramTuple4i.w;
  }
  
  public final void negate(Tuple4i paramTuple4i)
  {
    this.x = (-paramTuple4i.x);
    this.y = (-paramTuple4i.y);
    this.z = (-paramTuple4i.z);
    this.w = (-paramTuple4i.w);
  }
  
  public final void negate()
  {
    this.x = (-this.x);
    this.y = (-this.y);
    this.z = (-this.z);
    this.w = (-this.w);
  }
  
  public final void scale(int paramInt, Tuple4i paramTuple4i)
  {
    this.x = (paramInt * paramTuple4i.x);
    this.y = (paramInt * paramTuple4i.y);
    this.z = (paramInt * paramTuple4i.z);
    this.w = (paramInt * paramTuple4i.w);
  }
  
  public final void scale(int paramInt)
  {
    this.x *= paramInt;
    this.y *= paramInt;
    this.z *= paramInt;
    this.w *= paramInt;
  }
  
  public final void scaleAdd(int paramInt, Tuple4i paramTuple4i1, Tuple4i paramTuple4i2)
  {
    this.x = (paramInt * paramTuple4i1.x + paramTuple4i2.x);
    this.y = (paramInt * paramTuple4i1.y + paramTuple4i2.y);
    this.z = (paramInt * paramTuple4i1.z + paramTuple4i2.z);
    this.w = (paramInt * paramTuple4i1.w + paramTuple4i2.w);
  }
  
  public final void scaleAdd(int paramInt, Tuple4i paramTuple4i)
  {
    this.x = (paramInt * this.x + paramTuple4i.x);
    this.y = (paramInt * this.y + paramTuple4i.y);
    this.z = (paramInt * this.z + paramTuple4i.z);
    this.w = (paramInt * this.w + paramTuple4i.w);
  }
  
  public String toString()
  {
    return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple4i localTuple4i = (Tuple4i)paramObject;
      return (this.x == localTuple4i.x) && (this.y == localTuple4i.y) && (this.z == localTuple4i.z) && (this.w == localTuple4i.w);
    }
    catch (NullPointerException localNullPointerException)
    {
      return false;
    }
    catch (ClassCastException localClassCastException) {}
    return false;
  }
  
  public int hashCode()
  {
    long l = 1L;
    l = 31L * l + this.x;
    l = 31L * l + this.y;
    l = 31L * l + this.z;
    l = 31L * l + this.w;
    return (int)(l ^ l >> 32);
  }
  
  public final void clamp(int paramInt1, int paramInt2, Tuple4i paramTuple4i)
  {
    if (paramTuple4i.x > paramInt2) {
      this.x = paramInt2;
    } else if (paramTuple4i.x < paramInt1) {
      this.x = paramInt1;
    } else {
      this.x = paramTuple4i.x;
    }
    if (paramTuple4i.y > paramInt2) {
      this.y = paramInt2;
    } else if (paramTuple4i.y < paramInt1) {
      this.y = paramInt1;
    } else {
      this.y = paramTuple4i.y;
    }
    if (paramTuple4i.z > paramInt2) {
      this.z = paramInt2;
    } else if (paramTuple4i.z < paramInt1) {
      this.z = paramInt1;
    } else {
      this.z = paramTuple4i.z;
    }
    if (paramTuple4i.w > paramInt2) {
      this.w = paramInt2;
    } else if (paramTuple4i.w < paramInt1) {
      this.w = paramInt1;
    } else {
      this.w = paramTuple4i.w;
    }
  }
  
  public final void clampMin(int paramInt, Tuple4i paramTuple4i)
  {
    if (paramTuple4i.x < paramInt) {
      this.x = paramInt;
    } else {
      this.x = paramTuple4i.x;
    }
    if (paramTuple4i.y < paramInt) {
      this.y = paramInt;
    } else {
      this.y = paramTuple4i.y;
    }
    if (paramTuple4i.z < paramInt) {
      this.z = paramInt;
    } else {
      this.z = paramTuple4i.z;
    }
    if (paramTuple4i.w < paramInt) {
      this.w = paramInt;
    } else {
      this.w = paramTuple4i.w;
    }
  }
  
  public final void clampMax(int paramInt, Tuple4i paramTuple4i)
  {
    if (paramTuple4i.x > paramInt) {
      this.x = paramInt;
    } else {
      this.x = paramTuple4i.x;
    }
    if (paramTuple4i.y > paramInt) {
      this.y = paramInt;
    } else {
      this.y = paramTuple4i.y;
    }
    if (paramTuple4i.z > paramInt) {
      this.z = paramInt;
    } else {
      this.z = paramTuple4i.z;
    }
    if (paramTuple4i.w > paramInt) {
      this.w = paramInt;
    } else {
      this.w = paramTuple4i.z;
    }
  }
  
  public final void absolute(Tuple4i paramTuple4i)
  {
    this.x = Math.abs(paramTuple4i.x);
    this.y = Math.abs(paramTuple4i.y);
    this.z = Math.abs(paramTuple4i.z);
    this.w = Math.abs(paramTuple4i.w);
  }
  
  public final void clamp(int paramInt1, int paramInt2)
  {
    if (this.x > paramInt2) {
      this.x = paramInt2;
    } else if (this.x < paramInt1) {
      this.x = paramInt1;
    }
    if (this.y > paramInt2) {
      this.y = paramInt2;
    } else if (this.y < paramInt1) {
      this.y = paramInt1;
    }
    if (this.z > paramInt2) {
      this.z = paramInt2;
    } else if (this.z < paramInt1) {
      this.z = paramInt1;
    }
    if (this.w > paramInt2) {
      this.w = paramInt2;
    } else if (this.w < paramInt1) {
      this.w = paramInt1;
    }
  }
  
  public final void clampMin(int paramInt)
  {
    if (this.x < paramInt) {
      this.x = paramInt;
    }
    if (this.y < paramInt) {
      this.y = paramInt;
    }
    if (this.z < paramInt) {
      this.z = paramInt;
    }
    if (this.w < paramInt) {
      this.w = paramInt;
    }
  }
  
  public final void clampMax(int paramInt)
  {
    if (this.x > paramInt) {
      this.x = paramInt;
    }
    if (this.y > paramInt) {
      this.y = paramInt;
    }
    if (this.z > paramInt) {
      this.z = paramInt;
    }
    if (this.w > paramInt) {
      this.w = paramInt;
    }
  }
  
  public final void absolute()
  {
    this.x = Math.abs(this.x);
    this.y = Math.abs(this.y);
    this.z = Math.abs(this.z);
    this.w = Math.abs(this.w);
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
 * Qualified Name:     javax.vecmath.Tuple4i
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */