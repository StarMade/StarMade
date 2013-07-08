package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple3i
  implements Serializable, Cloneable
{
  static final long serialVersionUID = -732740491767276200L;
  public int field_604;
  public int field_605;
  public int field_606;
  
  public Tuple3i(int paramInt1, int paramInt2, int paramInt3)
  {
    this.field_604 = paramInt1;
    this.field_605 = paramInt2;
    this.field_606 = paramInt3;
  }
  
  public Tuple3i(int[] paramArrayOfInt)
  {
    this.field_604 = paramArrayOfInt[0];
    this.field_605 = paramArrayOfInt[1];
    this.field_606 = paramArrayOfInt[2];
  }
  
  public Tuple3i(Tuple3i paramTuple3i)
  {
    this.field_604 = paramTuple3i.field_604;
    this.field_605 = paramTuple3i.field_605;
    this.field_606 = paramTuple3i.field_606;
  }
  
  public Tuple3i()
  {
    this.field_604 = 0;
    this.field_605 = 0;
    this.field_606 = 0;
  }
  
  public final void set(int paramInt1, int paramInt2, int paramInt3)
  {
    this.field_604 = paramInt1;
    this.field_605 = paramInt2;
    this.field_606 = paramInt3;
  }
  
  public final void set(int[] paramArrayOfInt)
  {
    this.field_604 = paramArrayOfInt[0];
    this.field_605 = paramArrayOfInt[1];
    this.field_606 = paramArrayOfInt[2];
  }
  
  public final void set(Tuple3i paramTuple3i)
  {
    this.field_604 = paramTuple3i.field_604;
    this.field_605 = paramTuple3i.field_605;
    this.field_606 = paramTuple3i.field_606;
  }
  
  public final void get(int[] paramArrayOfInt)
  {
    paramArrayOfInt[0] = this.field_604;
    paramArrayOfInt[1] = this.field_605;
    paramArrayOfInt[2] = this.field_606;
  }
  
  public final void get(Tuple3i paramTuple3i)
  {
    paramTuple3i.field_604 = this.field_604;
    paramTuple3i.field_605 = this.field_605;
    paramTuple3i.field_606 = this.field_606;
  }
  
  public final void add(Tuple3i paramTuple3i1, Tuple3i paramTuple3i2)
  {
    paramTuple3i1.field_604 += paramTuple3i2.field_604;
    paramTuple3i1.field_605 += paramTuple3i2.field_605;
    paramTuple3i1.field_606 += paramTuple3i2.field_606;
  }
  
  public final void add(Tuple3i paramTuple3i)
  {
    this.field_604 += paramTuple3i.field_604;
    this.field_605 += paramTuple3i.field_605;
    this.field_606 += paramTuple3i.field_606;
  }
  
  public final void sub(Tuple3i paramTuple3i1, Tuple3i paramTuple3i2)
  {
    paramTuple3i1.field_604 -= paramTuple3i2.field_604;
    paramTuple3i1.field_605 -= paramTuple3i2.field_605;
    paramTuple3i1.field_606 -= paramTuple3i2.field_606;
  }
  
  public final void sub(Tuple3i paramTuple3i)
  {
    this.field_604 -= paramTuple3i.field_604;
    this.field_605 -= paramTuple3i.field_605;
    this.field_606 -= paramTuple3i.field_606;
  }
  
  public final void negate(Tuple3i paramTuple3i)
  {
    this.field_604 = (-paramTuple3i.field_604);
    this.field_605 = (-paramTuple3i.field_605);
    this.field_606 = (-paramTuple3i.field_606);
  }
  
  public final void negate()
  {
    this.field_604 = (-this.field_604);
    this.field_605 = (-this.field_605);
    this.field_606 = (-this.field_606);
  }
  
  public final void scale(int paramInt, Tuple3i paramTuple3i)
  {
    this.field_604 = (paramInt * paramTuple3i.field_604);
    this.field_605 = (paramInt * paramTuple3i.field_605);
    this.field_606 = (paramInt * paramTuple3i.field_606);
  }
  
  public final void scale(int paramInt)
  {
    this.field_604 *= paramInt;
    this.field_605 *= paramInt;
    this.field_606 *= paramInt;
  }
  
  public final void scaleAdd(int paramInt, Tuple3i paramTuple3i1, Tuple3i paramTuple3i2)
  {
    this.field_604 = (paramInt * paramTuple3i1.field_604 + paramTuple3i2.field_604);
    this.field_605 = (paramInt * paramTuple3i1.field_605 + paramTuple3i2.field_605);
    this.field_606 = (paramInt * paramTuple3i1.field_606 + paramTuple3i2.field_606);
  }
  
  public final void scaleAdd(int paramInt, Tuple3i paramTuple3i)
  {
    this.field_604 = (paramInt * this.field_604 + paramTuple3i.field_604);
    this.field_605 = (paramInt * this.field_605 + paramTuple3i.field_605);
    this.field_606 = (paramInt * this.field_606 + paramTuple3i.field_606);
  }
  
  public String toString()
  {
    return "(" + this.field_604 + ", " + this.field_605 + ", " + this.field_606 + ")";
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple3i localTuple3i = (Tuple3i)paramObject;
      return (this.field_604 == localTuple3i.field_604) && (this.field_605 == localTuple3i.field_605) && (this.field_606 == localTuple3i.field_606);
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
    l = 31L * l + this.field_604;
    l = 31L * l + this.field_605;
    l = 31L * l + this.field_606;
    return (int)(l ^ l >> 32);
  }
  
  public final void clamp(int paramInt1, int paramInt2, Tuple3i paramTuple3i)
  {
    if (paramTuple3i.field_604 > paramInt2) {
      this.field_604 = paramInt2;
    } else if (paramTuple3i.field_604 < paramInt1) {
      this.field_604 = paramInt1;
    } else {
      this.field_604 = paramTuple3i.field_604;
    }
    if (paramTuple3i.field_605 > paramInt2) {
      this.field_605 = paramInt2;
    } else if (paramTuple3i.field_605 < paramInt1) {
      this.field_605 = paramInt1;
    } else {
      this.field_605 = paramTuple3i.field_605;
    }
    if (paramTuple3i.field_606 > paramInt2) {
      this.field_606 = paramInt2;
    } else if (paramTuple3i.field_606 < paramInt1) {
      this.field_606 = paramInt1;
    } else {
      this.field_606 = paramTuple3i.field_606;
    }
  }
  
  public final void clampMin(int paramInt, Tuple3i paramTuple3i)
  {
    if (paramTuple3i.field_604 < paramInt) {
      this.field_604 = paramInt;
    } else {
      this.field_604 = paramTuple3i.field_604;
    }
    if (paramTuple3i.field_605 < paramInt) {
      this.field_605 = paramInt;
    } else {
      this.field_605 = paramTuple3i.field_605;
    }
    if (paramTuple3i.field_606 < paramInt) {
      this.field_606 = paramInt;
    } else {
      this.field_606 = paramTuple3i.field_606;
    }
  }
  
  public final void clampMax(int paramInt, Tuple3i paramTuple3i)
  {
    if (paramTuple3i.field_604 > paramInt) {
      this.field_604 = paramInt;
    } else {
      this.field_604 = paramTuple3i.field_604;
    }
    if (paramTuple3i.field_605 > paramInt) {
      this.field_605 = paramInt;
    } else {
      this.field_605 = paramTuple3i.field_605;
    }
    if (paramTuple3i.field_606 > paramInt) {
      this.field_606 = paramInt;
    } else {
      this.field_606 = paramTuple3i.field_606;
    }
  }
  
  public final void absolute(Tuple3i paramTuple3i)
  {
    this.field_604 = Math.abs(paramTuple3i.field_604);
    this.field_605 = Math.abs(paramTuple3i.field_605);
    this.field_606 = Math.abs(paramTuple3i.field_606);
  }
  
  public final void clamp(int paramInt1, int paramInt2)
  {
    if (this.field_604 > paramInt2) {
      this.field_604 = paramInt2;
    } else if (this.field_604 < paramInt1) {
      this.field_604 = paramInt1;
    }
    if (this.field_605 > paramInt2) {
      this.field_605 = paramInt2;
    } else if (this.field_605 < paramInt1) {
      this.field_605 = paramInt1;
    }
    if (this.field_606 > paramInt2) {
      this.field_606 = paramInt2;
    } else if (this.field_606 < paramInt1) {
      this.field_606 = paramInt1;
    }
  }
  
  public final void clampMin(int paramInt)
  {
    if (this.field_604 < paramInt) {
      this.field_604 = paramInt;
    }
    if (this.field_605 < paramInt) {
      this.field_605 = paramInt;
    }
    if (this.field_606 < paramInt) {
      this.field_606 = paramInt;
    }
  }
  
  public final void clampMax(int paramInt)
  {
    if (this.field_604 > paramInt) {
      this.field_604 = paramInt;
    }
    if (this.field_605 > paramInt) {
      this.field_605 = paramInt;
    }
    if (this.field_606 > paramInt) {
      this.field_606 = paramInt;
    }
  }
  
  public final void absolute()
  {
    this.field_604 = Math.abs(this.field_604);
    this.field_605 = Math.abs(this.field_605);
    this.field_606 = Math.abs(this.field_606);
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
 * Qualified Name:     javax.vecmath.Tuple3i
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */