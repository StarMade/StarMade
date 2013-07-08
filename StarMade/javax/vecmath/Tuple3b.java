package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple3b
  implements Serializable, Cloneable
{
  static final long serialVersionUID = -483782685323607044L;
  public byte field_607;
  public byte field_608;
  public byte field_609;
  
  public Tuple3b(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    this.field_607 = paramByte1;
    this.field_608 = paramByte2;
    this.field_609 = paramByte3;
  }
  
  public Tuple3b(byte[] paramArrayOfByte)
  {
    this.field_607 = paramArrayOfByte[0];
    this.field_608 = paramArrayOfByte[1];
    this.field_609 = paramArrayOfByte[2];
  }
  
  public Tuple3b(Tuple3b paramTuple3b)
  {
    this.field_607 = paramTuple3b.field_607;
    this.field_608 = paramTuple3b.field_608;
    this.field_609 = paramTuple3b.field_609;
  }
  
  public Tuple3b()
  {
    this.field_607 = 0;
    this.field_608 = 0;
    this.field_609 = 0;
  }
  
  public String toString()
  {
    return "(" + (this.field_607 & 0xFF) + ", " + (this.field_608 & 0xFF) + ", " + (this.field_609 & 0xFF) + ")";
  }
  
  public final void get(byte[] paramArrayOfByte)
  {
    paramArrayOfByte[0] = this.field_607;
    paramArrayOfByte[1] = this.field_608;
    paramArrayOfByte[2] = this.field_609;
  }
  
  public final void get(Tuple3b paramTuple3b)
  {
    paramTuple3b.field_607 = this.field_607;
    paramTuple3b.field_608 = this.field_608;
    paramTuple3b.field_609 = this.field_609;
  }
  
  public final void set(Tuple3b paramTuple3b)
  {
    this.field_607 = paramTuple3b.field_607;
    this.field_608 = paramTuple3b.field_608;
    this.field_609 = paramTuple3b.field_609;
  }
  
  public final void set(byte[] paramArrayOfByte)
  {
    this.field_607 = paramArrayOfByte[0];
    this.field_608 = paramArrayOfByte[1];
    this.field_609 = paramArrayOfByte[2];
  }
  
  public boolean equals(Tuple3b paramTuple3b)
  {
    try
    {
      return (this.field_607 == paramTuple3b.field_607) && (this.field_608 == paramTuple3b.field_608) && (this.field_609 == paramTuple3b.field_609);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple3b localTuple3b = (Tuple3b)paramObject;
      return (this.field_607 == localTuple3b.field_607) && (this.field_608 == localTuple3b.field_608) && (this.field_609 == localTuple3b.field_609);
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
    return (this.field_607 & 0xFF) << 0 | (this.field_608 & 0xFF) << 8 | (this.field_609 & 0xFF) << 16;
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
 * Qualified Name:     javax.vecmath.Tuple3b
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */