public final class class_758
  implements class_80
{
  public String field_136;
  public byte field_136;
  
  public final boolean a148(class_773 paramclass_773)
  {
    return paramclass_773.a180().a18(this.jdField_field_136_of_type_Byte);
  }
  
  public final boolean b24(class_773 paramclass_773)
  {
    return paramclass_773.a180().b3(this.jdField_field_136_of_type_Byte);
  }
  
  public final boolean c15(class_773 paramclass_773)
  {
    return paramclass_773.a180().c(this.jdField_field_136_of_type_Byte);
  }
  
  public final boolean d7(class_773 paramclass_773)
  {
    return paramclass_773.a180().d(this.jdField_field_136_of_type_Byte);
  }
  
  public class_758(class_748 paramclass_748)
  {
    this.jdField_field_136_of_type_JavaLangString = paramclass_748.getName();
    this.jdField_field_136_of_type_Byte = 4;
  }
  
  public class_758(String paramString, byte paramByte)
  {
    this.jdField_field_136_of_type_JavaLangString = paramString;
    this.jdField_field_136_of_type_Byte = paramByte;
  }
  
  public class_758() {}
  
  public final void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.jdField_field_136_of_type_JavaLangString = ((String)paramclass_69[0].a4());
    this.jdField_field_136_of_type_Byte = ((Byte)paramclass_69[1].a4()).byteValue();
  }
  
  public final String getUniqueIdentifier()
  {
    return null;
  }
  
  public final boolean isVolatile()
  {
    return false;
  }
  
  public final class_69 toTagStructure()
  {
    return new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_556, null, this.jdField_field_136_of_type_JavaLangString), new class_69(class_79.field_549, null, Byte.valueOf(this.jdField_field_136_of_type_Byte)), new class_69(class_79.field_548, null, null) });
  }
  
  public final boolean equals(Object paramObject)
  {
    return this.jdField_field_136_of_type_JavaLangString.equals(((class_758)paramObject).jdField_field_136_of_type_JavaLangString);
  }
  
  public final int hashCode()
  {
    return this.jdField_field_136_of_type_JavaLangString.hashCode();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_758
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */