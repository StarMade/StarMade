package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple2i
  implements Serializable, Cloneable
{
  static final long serialVersionUID = -3555701650170169638L;
  public int field_575;
  public int field_576;
  
  public Tuple2i(int paramInt1, int paramInt2)
  {
    this.field_575 = paramInt1;
    this.field_576 = paramInt2;
  }
  
  public Tuple2i(int[] paramArrayOfInt)
  {
    this.field_575 = paramArrayOfInt[0];
    this.field_576 = paramArrayOfInt[1];
  }
  
  public Tuple2i(Tuple2i paramTuple2i)
  {
    this.field_575 = paramTuple2i.field_575;
    this.field_576 = paramTuple2i.field_576;
  }
  
  public Tuple2i()
  {
    this.field_575 = 0;
    this.field_576 = 0;
  }
  
  public final void set(int paramInt1, int paramInt2)
  {
    this.field_575 = paramInt1;
    this.field_576 = paramInt2;
  }
  
  public final void set(int[] paramArrayOfInt)
  {
    this.field_575 = paramArrayOfInt[0];
    this.field_576 = paramArrayOfInt[1];
  }
  
  public final void set(Tuple2i paramTuple2i)
  {
    this.field_575 = paramTuple2i.field_575;
    this.field_576 = paramTuple2i.field_576;
  }
  
  public final void get(int[] paramArrayOfInt)
  {
    paramArrayOfInt[0] = this.field_575;
    paramArrayOfInt[1] = this.field_576;
  }
  
  public final void get(Tuple2i paramTuple2i)
  {
    paramTuple2i.field_575 = this.field_575;
    paramTuple2i.field_576 = this.field_576;
  }
  
  public final void add(Tuple2i paramTuple2i1, Tuple2i paramTuple2i2)
  {
    paramTuple2i1.field_575 += paramTuple2i2.field_575;
    paramTuple2i1.field_576 += paramTuple2i2.field_576;
  }
  
  public final void add(Tuple2i paramTuple2i)
  {
    this.field_575 += paramTuple2i.field_575;
    this.field_576 += paramTuple2i.field_576;
  }
  
  public final void sub(Tuple2i paramTuple2i1, Tuple2i paramTuple2i2)
  {
    paramTuple2i1.field_575 -= paramTuple2i2.field_575;
    paramTuple2i1.field_576 -= paramTuple2i2.field_576;
  }
  
  public final void sub(Tuple2i paramTuple2i)
  {
    this.field_575 -= paramTuple2i.field_575;
    this.field_576 -= paramTuple2i.field_576;
  }
  
  public final void negate(Tuple2i paramTuple2i)
  {
    this.field_575 = (-paramTuple2i.field_575);
    this.field_576 = (-paramTuple2i.field_576);
  }
  
  public final void negate()
  {
    this.field_575 = (-this.field_575);
    this.field_576 = (-this.field_576);
  }
  
  public final void scale(int paramInt, Tuple2i paramTuple2i)
  {
    this.field_575 = (paramInt * paramTuple2i.field_575);
    this.field_576 = (paramInt * paramTuple2i.field_576);
  }
  
  public final void scale(int paramInt)
  {
    this.field_575 *= paramInt;
    this.field_576 *= paramInt;
  }
  
  public final void scaleAdd(int paramInt, Tuple2i paramTuple2i1, Tuple2i paramTuple2i2)
  {
    this.field_575 = (paramInt * paramTuple2i1.field_575 + paramTuple2i2.field_575);
    this.field_576 = (paramInt * paramTuple2i1.field_576 + paramTuple2i2.field_576);
  }
  
  public final void scaleAdd(int paramInt, Tuple2i paramTuple2i)
  {
    this.field_575 = (paramInt * this.field_575 + paramTuple2i.field_575);
    this.field_576 = (paramInt * this.field_576 + paramTuple2i.field_576);
  }
  
  public String toString()
  {
    return "(" + this.field_575 + ", " + this.field_576 + ")";
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple2i localTuple2i = (Tuple2i)paramObject;
      return (this.field_575 == localTuple2i.field_575) && (this.field_576 == localTuple2i.field_576);
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
    l = 31L * l + this.field_575;
    l = 31L * l + this.field_576;
    return (int)(l ^ l >> 32);
  }
  
  public final void clamp(int paramInt1, int paramInt2, Tuple2i paramTuple2i)
  {
    if (paramTuple2i.field_575 > paramInt2) {
      this.field_575 = paramInt2;
    } else if (paramTuple2i.field_575 < paramInt1) {
      this.field_575 = paramInt1;
    } else {
      this.field_575 = paramTuple2i.field_575;
    }
    if (paramTuple2i.field_576 > paramInt2) {
      this.field_576 = paramInt2;
    } else if (paramTuple2i.field_576 < paramInt1) {
      this.field_576 = paramInt1;
    } else {
      this.field_576 = paramTuple2i.field_576;
    }
  }
  
  public final void clampMin(int paramInt, Tuple2i paramTuple2i)
  {
    if (paramTuple2i.field_575 < paramInt) {
      this.field_575 = paramInt;
    } else {
      this.field_575 = paramTuple2i.field_575;
    }
    if (paramTuple2i.field_576 < paramInt) {
      this.field_576 = paramInt;
    } else {
      this.field_576 = paramTuple2i.field_576;
    }
  }
  
  public final void clampMax(int paramInt, Tuple2i paramTuple2i)
  {
    if (paramTuple2i.field_575 > paramInt) {
      this.field_575 = paramInt;
    } else {
      this.field_575 = paramTuple2i.field_575;
    }
    if (paramTuple2i.field_576 > paramInt) {
      this.field_576 = paramInt;
    } else {
      this.field_576 = paramTuple2i.field_576;
    }
  }
  
  public final void absolute(Tuple2i paramTuple2i)
  {
    this.field_575 = Math.abs(paramTuple2i.field_575);
    this.field_576 = Math.abs(paramTuple2i.field_576);
  }
  
  public final void clamp(int paramInt1, int paramInt2)
  {
    if (this.field_575 > paramInt2) {
      this.field_575 = paramInt2;
    } else if (this.field_575 < paramInt1) {
      this.field_575 = paramInt1;
    }
    if (this.field_576 > paramInt2) {
      this.field_576 = paramInt2;
    } else if (this.field_576 < paramInt1) {
      this.field_576 = paramInt1;
    }
  }
  
  public final void clampMin(int paramInt)
  {
    if (this.field_575 < paramInt) {
      this.field_575 = paramInt;
    }
    if (this.field_576 < paramInt) {
      this.field_576 = paramInt;
    }
  }
  
  public final void clampMax(int paramInt)
  {
    if (this.field_575 > paramInt) {
      this.field_575 = paramInt;
    }
    if (this.field_576 > paramInt) {
      this.field_576 = paramInt;
    }
  }
  
  public final void absolute()
  {
    this.field_575 = Math.abs(this.field_575);
    this.field_576 = Math.abs(this.field_576);
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
 * Qualified Name:     javax.vecmath.Tuple2i
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */