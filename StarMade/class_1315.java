public final class class_1315
{
  public String field_1492;
  public String field_1493;
  public int field_1492;
  
  public class_1315(String paramString1, int paramInt, String paramString2)
  {
    this.field_1493 = paramString1;
    this.jdField_field_1492_of_type_Int = paramInt;
    this.jdField_field_1492_of_type_JavaLangString = paramString2;
  }
  
  public final String toString()
  {
    return this.jdField_field_1492_of_type_JavaLangString + "@" + this.field_1493 + ":" + this.jdField_field_1492_of_type_Int;
  }
  
  public final boolean equals(Object paramObject)
  {
    return (this.jdField_field_1492_of_type_JavaLangString.equals(((class_1315)paramObject).jdField_field_1492_of_type_JavaLangString)) && (this.field_1493.equals(((class_1315)paramObject).field_1493)) && (this.jdField_field_1492_of_type_Int == ((class_1315)paramObject).jdField_field_1492_of_type_Int);
  }
  
  public final int hashCode()
  {
    return this.jdField_field_1492_of_type_JavaLangString.hashCode() + this.field_1493.hashCode() + this.jdField_field_1492_of_type_Int;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1315
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */