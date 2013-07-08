package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple4b
  implements Serializable, Cloneable
{
  static final long serialVersionUID = -8226727741811898211L;
  public byte field_592;
  public byte field_593;
  public byte field_594;
  public byte field_595;
  
  public Tuple4b(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4)
  {
    this.field_592 = paramByte1;
    this.field_593 = paramByte2;
    this.field_594 = paramByte3;
    this.field_595 = paramByte4;
  }
  
  public Tuple4b(byte[] paramArrayOfByte)
  {
    this.field_592 = paramArrayOfByte[0];
    this.field_593 = paramArrayOfByte[1];
    this.field_594 = paramArrayOfByte[2];
    this.field_595 = paramArrayOfByte[3];
  }
  
  public Tuple4b(Tuple4b paramTuple4b)
  {
    this.field_592 = paramTuple4b.field_592;
    this.field_593 = paramTuple4b.field_593;
    this.field_594 = paramTuple4b.field_594;
    this.field_595 = paramTuple4b.field_595;
  }
  
  public Tuple4b()
  {
    this.field_592 = 0;
    this.field_593 = 0;
    this.field_594 = 0;
    this.field_595 = 0;
  }
  
  public String toString()
  {
    return "(" + (this.field_592 & 0xFF) + ", " + (this.field_593 & 0xFF) + ", " + (this.field_594 & 0xFF) + ", " + (this.field_595 & 0xFF) + ")";
  }
  
  public final void get(byte[] paramArrayOfByte)
  {
    paramArrayOfByte[0] = this.field_592;
    paramArrayOfByte[1] = this.field_593;
    paramArrayOfByte[2] = this.field_594;
    paramArrayOfByte[3] = this.field_595;
  }
  
  public final void get(Tuple4b paramTuple4b)
  {
    paramTuple4b.field_592 = this.field_592;
    paramTuple4b.field_593 = this.field_593;
    paramTuple4b.field_594 = this.field_594;
    paramTuple4b.field_595 = this.field_595;
  }
  
  public final void set(Tuple4b paramTuple4b)
  {
    this.field_592 = paramTuple4b.field_592;
    this.field_593 = paramTuple4b.field_593;
    this.field_594 = paramTuple4b.field_594;
    this.field_595 = paramTuple4b.field_595;
  }
  
  public final void set(byte[] paramArrayOfByte)
  {
    this.field_592 = paramArrayOfByte[0];
    this.field_593 = paramArrayOfByte[1];
    this.field_594 = paramArrayOfByte[2];
    this.field_595 = paramArrayOfByte[3];
  }
  
  public boolean equals(Tuple4b paramTuple4b)
  {
    try
    {
      return (this.field_592 == paramTuple4b.field_592) && (this.field_593 == paramTuple4b.field_593) && (this.field_594 == paramTuple4b.field_594) && (this.field_595 == paramTuple4b.field_595);
    }
    catch (NullPointerException localNullPointerException) {}
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      Tuple4b localTuple4b = (Tuple4b)paramObject;
      return (this.field_592 == localTuple4b.field_592) && (this.field_593 == localTuple4b.field_593) && (this.field_594 == localTuple4b.field_594) && (this.field_595 == localTuple4b.field_595);
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
    return (this.field_592 & 0xFF) << 0 | (this.field_593 & 0xFF) << 8 | (this.field_594 & 0xFF) << 16 | (this.field_595 & 0xFF) << 24;
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
 * Qualified Name:     javax.vecmath.Tuple4b
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */