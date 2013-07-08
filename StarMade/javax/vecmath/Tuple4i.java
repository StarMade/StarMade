package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple4i
  implements Serializable, Cloneable
{
  static final long serialVersionUID = 8064614250942616720L;
  public int field_582;
  public int field_583;
  public int field_584;
  public int field_585;
  
  public Tuple4i(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.field_582 = paramInt1;
    this.field_583 = paramInt2;
    this.field_584 = paramInt3;
    this.field_585 = paramInt4;
  }
  
  public Tuple4i(int[] paramArrayOfInt)
  {
    this.field_582 = paramArrayOfInt[0];
    this.field_583 = paramArrayOfInt[1];
    this.field_584 = paramArrayOfInt[2];
    this.field_585 = paramArrayOfInt[3];
  }
  
  public Tuple4i(Tuple4i paramTuple4i)
  {
    this.field_582 = paramTuple4i.field_582;
    this.field_583 = paramTuple4i.field_583;
    this.field_584 = paramTuple4i.field_584;
    this.field_585 = paramTuple4i.field_585;
  }
  
  public Tuple4i()
  {
    this.field_582 = 0;
    this.field_583 = 0;
    this.field_584 = 0;
    this.field_585 = 0;
  }
  
  public final void set(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.field_582 = paramInt1;
    this.field_583 = paramInt2;
    this.field_584 = paramInt3;
    this.field_585 = paramInt4;
  }
  
  public final void set(int[] paramArrayOfInt)
  {
    this.field_582 = paramArrayOfInt[0];
    this.field_583 = paramArrayOfInt[1];
    this.field_584 = paramArrayOfInt[2];
    this.field_585 = paramArrayOfInt[3];
  }
  
  public final void set(Tuple4i paramTuple4i)
  {
    this.field_582 = paramTuple4i.field_582;
    this.field_583 = paramTuple4i.field_583;
    this.field_584 = paramTuple4i.field_584;
    this.field_585 = paramTuple4i.field_585;
  }
  
  public final void get(int[] paramArrayOfInt)
  {
    paramArrayOfInt[0] = this.field_582;
    paramArrayOfInt[1] = this.field_583;
    paramArrayOfInt[2] = this.field_584;
    paramArrayOfInt[3] = this.field_585;
  }
  
  public final void get(Tuple4i paramTuple4i)
  {
    paramTuple4i.field_582 = this.field_582;
    paramTuple4i.field_583 = this.field_583;
    paramTuple4i.field_584 = this.field_584;
    paramTuple4i.field_585 = this.field_585;
  }
  
  public final void add(Tuple4i paramTuple4i1, Tuple4i paramTuple4i2)
  {
    paramTuple4i1.field_582 += paramTuple4i2.field_582;
    paramTuple4i1.field_583 += paramTuple4i2.field_583;
    paramTuple4i1.field_584 += paramTuple4i2.field_584;
    paramTuple4i1.field_585 += paramTuple4i2.field_585;
  }
  
  public final void add(Tuple4i paramTuple4i)
  {
    this.field_582 += paramTuple4i.field_582;
    this.field_583 += paramTuple4i.field_583;
    this.field_584 += paramTuple4i.field_584;
    this.field_585 += paramTuple4i.field_585;
  }
  
  public final void sub(Tuple4i paramTuple4i1, Tuple4i paramTuple4i2)
  {
    paramTuple4i1.field_582 -= paramTuple4i2.field_582;
    paramTuple4i1.field_583 -= paramTuple4i2.field_583;
    paramTuple4i1.field_584 -= paramTuple4i2.field_584;
    paramTuple4i1.field_585 -= paramTuple4i2.field_585;
  }
  
  public final void sub(Tuple4i paramTuple4i)
  {
    this.field_582 -= paramTuple4i.field_582;
    this.field_583 -= paramTuple4i.field_583;
    this.field_584 -= paramTuple4i.field_584;
    this.field_585 -= paramTuple4i.field_585;
  }
  
  public final void negate(Tuple4i paramTuple4i)
  {
    this.field_582 = (-paramTuple4i.field_582);
    this.field_583 = (-paramTuple4i.field_583);
    this.field_584 = (-paramTuple4i.field_584);
    this.field_585 = (-paramTuple4i.field_585);
  }
  
  public final void negate()
  {
    this.field_582 = (-this.field_582);
    this.field_583 = (-this.field_583);
    this.field_584 = (-this.field_584);
    this.field_585 = (-this.field_585);
  }
  
  public final void scale(int paramInt, Tuple4i paramTuple4i)
  {
    this.field_582 = (paramInt * paramTuple4i.field_582);
    this.field_583 = (paramInt * paramTuple4i.field_583);
    this.field_584 = (paramInt * paramTuple4i.field_584);
    this.field_585 = (paramInt * paramTuple4i.field_585);
  }
  
  public final void scale(int paramInt)
  {
    this.field_582 *= paramInt;
    this.field_583 *= paramInt;
    this.field_584 *= paramInt;
    this.field_585 *= paramInt;
  }
  
  public final void scaleAdd(int paramInt, Tuple4i paramTuple4i1, Tuple4i paramTuple4i2)
  {
    this.field_582 = (paramInt * paramTuple4i1.field_582 + paramTuple4i2.field_582);
    this.field_583 = (paramInt * paramTuple4i1.field_583 + paramTuple4i2.field_583);
    this.field_584 = (paramInt * paramTuple4i1.field_584 + paramTuple4i2.field_584);
    this.field_585 = (paramInt * paramTuple4i1.field_585 + paramTuple4i2.field_585);
  }
  
  public final void scaleAdd(int paramInt, Tuple4i paramTuple4i)
  {
    this.field_582 = (paramInt * this.field_582 + paramTuple4i.field_582);
    this.field_583 = (paramInt * this.field_583 + paramTuple4i.field_583);
    this.field_584 = (paramInt * this.field_584 + paramTuple4i.field_584);
    this.field_585 = (paramInt * this.field_585 + paramTuple4i.field_585);
  }
  
  public String toString()
  {
    return "(" + this.field_582 + ", " + this.field_583 + ", " + this.field_584 + ", " + this.field_585 + ")";
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple4i localTuple4i = (Tuple4i)paramObject;
      return (this.field_582 == localTuple4i.field_582) && (this.field_583 == localTuple4i.field_583) && (this.field_584 == localTuple4i.field_584) && (this.field_585 == localTuple4i.field_585);
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
    l = 31L * l + this.field_582;
    l = 31L * l + this.field_583;
    l = 31L * l + this.field_584;
    l = 31L * l + this.field_585;
    return (int)(l ^ l >> 32);
  }
  
  public final void clamp(int paramInt1, int paramInt2, Tuple4i paramTuple4i)
  {
    if (paramTuple4i.field_582 > paramInt2) {
      this.field_582 = paramInt2;
    } else if (paramTuple4i.field_582 < paramInt1) {
      this.field_582 = paramInt1;
    } else {
      this.field_582 = paramTuple4i.field_582;
    }
    if (paramTuple4i.field_583 > paramInt2) {
      this.field_583 = paramInt2;
    } else if (paramTuple4i.field_583 < paramInt1) {
      this.field_583 = paramInt1;
    } else {
      this.field_583 = paramTuple4i.field_583;
    }
    if (paramTuple4i.field_584 > paramInt2) {
      this.field_584 = paramInt2;
    } else if (paramTuple4i.field_584 < paramInt1) {
      this.field_584 = paramInt1;
    } else {
      this.field_584 = paramTuple4i.field_584;
    }
    if (paramTuple4i.field_585 > paramInt2) {
      this.field_585 = paramInt2;
    } else if (paramTuple4i.field_585 < paramInt1) {
      this.field_585 = paramInt1;
    } else {
      this.field_585 = paramTuple4i.field_585;
    }
  }
  
  public final void clampMin(int paramInt, Tuple4i paramTuple4i)
  {
    if (paramTuple4i.field_582 < paramInt) {
      this.field_582 = paramInt;
    } else {
      this.field_582 = paramTuple4i.field_582;
    }
    if (paramTuple4i.field_583 < paramInt) {
      this.field_583 = paramInt;
    } else {
      this.field_583 = paramTuple4i.field_583;
    }
    if (paramTuple4i.field_584 < paramInt) {
      this.field_584 = paramInt;
    } else {
      this.field_584 = paramTuple4i.field_584;
    }
    if (paramTuple4i.field_585 < paramInt) {
      this.field_585 = paramInt;
    } else {
      this.field_585 = paramTuple4i.field_585;
    }
  }
  
  public final void clampMax(int paramInt, Tuple4i paramTuple4i)
  {
    if (paramTuple4i.field_582 > paramInt) {
      this.field_582 = paramInt;
    } else {
      this.field_582 = paramTuple4i.field_582;
    }
    if (paramTuple4i.field_583 > paramInt) {
      this.field_583 = paramInt;
    } else {
      this.field_583 = paramTuple4i.field_583;
    }
    if (paramTuple4i.field_584 > paramInt) {
      this.field_584 = paramInt;
    } else {
      this.field_584 = paramTuple4i.field_584;
    }
    if (paramTuple4i.field_585 > paramInt) {
      this.field_585 = paramInt;
    } else {
      this.field_585 = paramTuple4i.field_584;
    }
  }
  
  public final void absolute(Tuple4i paramTuple4i)
  {
    this.field_582 = Math.abs(paramTuple4i.field_582);
    this.field_583 = Math.abs(paramTuple4i.field_583);
    this.field_584 = Math.abs(paramTuple4i.field_584);
    this.field_585 = Math.abs(paramTuple4i.field_585);
  }
  
  public final void clamp(int paramInt1, int paramInt2)
  {
    if (this.field_582 > paramInt2) {
      this.field_582 = paramInt2;
    } else if (this.field_582 < paramInt1) {
      this.field_582 = paramInt1;
    }
    if (this.field_583 > paramInt2) {
      this.field_583 = paramInt2;
    } else if (this.field_583 < paramInt1) {
      this.field_583 = paramInt1;
    }
    if (this.field_584 > paramInt2) {
      this.field_584 = paramInt2;
    } else if (this.field_584 < paramInt1) {
      this.field_584 = paramInt1;
    }
    if (this.field_585 > paramInt2) {
      this.field_585 = paramInt2;
    } else if (this.field_585 < paramInt1) {
      this.field_585 = paramInt1;
    }
  }
  
  public final void clampMin(int paramInt)
  {
    if (this.field_582 < paramInt) {
      this.field_582 = paramInt;
    }
    if (this.field_583 < paramInt) {
      this.field_583 = paramInt;
    }
    if (this.field_584 < paramInt) {
      this.field_584 = paramInt;
    }
    if (this.field_585 < paramInt) {
      this.field_585 = paramInt;
    }
  }
  
  public final void clampMax(int paramInt)
  {
    if (this.field_582 > paramInt) {
      this.field_582 = paramInt;
    }
    if (this.field_583 > paramInt) {
      this.field_583 = paramInt;
    }
    if (this.field_584 > paramInt) {
      this.field_584 = paramInt;
    }
    if (this.field_585 > paramInt) {
      this.field_585 = paramInt;
    }
  }
  
  public final void absolute()
  {
    this.field_582 = Math.abs(this.field_582);
    this.field_583 = Math.abs(this.field_583);
    this.field_584 = Math.abs(this.field_584);
    this.field_585 = Math.abs(this.field_585);
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
 * Qualified Name:     javax.vecmath.Tuple4i
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */