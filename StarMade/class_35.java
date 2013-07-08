public class class_35
{
  public byte field_453;
  public byte field_454;
  public byte field_455;
  
  public class_35() {}
  
  public class_35(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    this.field_453 = paramByte1;
    this.field_454 = paramByte2;
    this.field_455 = paramByte3;
  }
  
  public class_35(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    this.field_453 = ((byte)(int)paramFloat1);
    this.field_454 = ((byte)(int)paramFloat2);
    this.field_455 = ((byte)(int)paramFloat3);
  }
  
  public class_35(class_35 paramclass_35)
  {
    this.field_453 = paramclass_35.field_453;
    this.field_454 = paramclass_35.field_454;
    this.field_455 = paramclass_35.field_455;
  }
  
  public final void a(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    this.field_453 = ((byte)(this.field_453 + paramByte1));
    this.field_454 = ((byte)(this.field_454 + paramByte2));
    this.field_455 = ((byte)(this.field_455 + paramByte3));
  }
  
  public final void a1(class_35 paramclass_35)
  {
    this.field_453 = ((byte)(this.field_453 + paramclass_35.field_453));
    this.field_454 = ((byte)(this.field_454 + paramclass_35.field_454));
    this.field_455 = ((byte)(this.field_455 + paramclass_35.field_455));
  }
  
  public final void a2()
  {
    this.field_453 = ((byte)(this.field_453 / 2));
    this.field_454 = ((byte)(this.field_454 / 2));
    this.field_455 = ((byte)(this.field_455 / 2));
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      paramObject = (class_35)paramObject;
      return (this.field_453 == paramObject.field_453) && (this.field_454 == paramObject.field_454) && (this.field_455 == paramObject.field_455);
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
    long l = 7L + this.field_453;
    l = 7L * l + this.field_454;
    long tmp33_32 = (7L * l + this.field_455);
    return (byte)(int)(tmp33_32 ^ tmp33_32 >> 8);
  }
  
  public final void b(byte paramByte1, byte paramByte2, byte paramByte3)
  {
    this.field_453 = paramByte1;
    this.field_454 = paramByte2;
    this.field_455 = paramByte3;
  }
  
  public final void b1(class_35 paramclass_35)
  {
    b(paramclass_35.field_453, paramclass_35.field_454, paramclass_35.field_455);
  }
  
  public final void c(class_35 paramclass_35)
  {
    this.field_453 = ((byte)(this.field_453 - paramclass_35.field_453));
    this.field_454 = ((byte)(this.field_454 - paramclass_35.field_454));
    this.field_455 = ((byte)(this.field_455 - paramclass_35.field_455));
  }
  
  public String toString()
  {
    return "(" + this.field_453 + ", " + this.field_454 + ", " + this.field_455 + ")";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_35
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */