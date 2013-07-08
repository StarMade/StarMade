import java.io.Serializable;

public class class_46
  implements Serializable
{
  static final long serialVersionUID = 8749319902347760659L;
  public int field_467;
  public int field_468;
  public int field_469;
  public int field_470;
  
  public class_46() {}
  
  public class_46(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.field_467 = paramInt1;
    this.field_468 = paramInt2;
    this.field_469 = paramInt3;
    this.field_470 = paramInt4;
  }
  
  public class_46(class_48 paramclass_48)
  {
    this(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477, 0);
  }
  
  public class_46(class_48 paramclass_48, int paramInt)
  {
    this(paramclass_48.field_475, paramclass_48.field_476, paramclass_48.field_477, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      paramObject = (class_46)paramObject;
      return (this.field_467 == paramObject.field_467) && (this.field_468 == paramObject.field_468) && (this.field_469 == paramObject.field_469) && (this.field_470 == paramObject.field_470);
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
    long l = 31L + this.field_467;
    l = 31L * l + this.field_468;
    l = 31L * l + this.field_469;
    long tmp45_44 = (31L * l + this.field_470);
    return (int)(tmp45_44 ^ tmp45_44 >> 32);
  }
  
  public final void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.field_467 = paramInt1;
    this.field_468 = paramInt2;
    this.field_469 = paramInt3;
    this.field_470 = paramInt4;
  }
  
  public String toString()
  {
    return "(" + this.field_467 + ", " + this.field_468 + ", " + this.field_469 + ", " + this.field_470 + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_46
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */