public class class_341
{
  public class_48 field_234;
  public byte field_234;
  
  public class_341() {}
  
  public class_341(class_341 paramclass_341)
  {
    this(paramclass_341.jdField_field_234_of_type_Byte, new class_48(paramclass_341.jdField_field_234_of_type_Class_48));
  }
  
  public class_341(byte paramByte, class_48 paramclass_48)
  {
    this.jdField_field_234_of_type_Byte = paramByte;
    this.jdField_field_234_of_type_Class_48 = paramclass_48;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this.jdField_field_234_of_type_Byte == ((class_341)paramObject).jdField_field_234_of_type_Byte) && (this.jdField_field_234_of_type_Class_48.equals(((class_341)paramObject).jdField_field_234_of_type_Class_48));
  }
  
  public int hashCode()
  {
    return this.jdField_field_234_of_type_Byte * 90000 + this.jdField_field_234_of_type_Class_48.hashCode();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_341
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */