public class class_777
  implements class_80
{
  private String jdField_field_136_of_type_JavaLangString;
  private String field_139;
  private long jdField_field_136_of_type_Long;
  private int jdField_field_136_of_type_Int;
  
  public class_777(String paramString1, String paramString2, int paramInt, long paramLong)
  {
    a188(paramString1, paramString2, paramInt, paramLong);
  }
  
  public class_777() {}
  
  public final String a()
  {
    return this.field_139;
  }
  
  public final long a28()
  {
    return this.jdField_field_136_of_type_Long;
  }
  
  public int hashCode()
  {
    return this.field_139.hashCode() + this.jdField_field_136_of_type_JavaLangString.hashCode() + this.jdField_field_136_of_type_Int;
  }
  
  public boolean equals(Object paramObject)
  {
    return (((class_777)paramObject).field_139.equals(this.field_139)) && (((class_777)paramObject).jdField_field_136_of_type_JavaLangString.equals(this.jdField_field_136_of_type_JavaLangString)) && (((class_777)paramObject).jdField_field_136_of_type_Int == this.jdField_field_136_of_type_Int);
  }
  
  public String toString()
  {
    return "FactionInvite [fromPlayerName=" + this.jdField_field_136_of_type_JavaLangString + ", toPlayerName=" + this.field_139 + ", date=" + this.jdField_field_136_of_type_Long + ", factionUID=" + this.jdField_field_136_of_type_Int + "]";
  }
  
  public class_69 toTagStructure()
  {
    return new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_556, null, this.jdField_field_136_of_type_JavaLangString), new class_69(class_79.field_556, null, this.field_139), new class_69(class_79.field_551, null, Integer.valueOf(this.jdField_field_136_of_type_Int)), new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_136_of_type_Long)), new class_69(class_79.field_548, null, null) });
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.jdField_field_136_of_type_JavaLangString = ((String)paramclass_69[0].a4());
    this.field_139 = ((String)paramclass_69[1].a4());
    this.jdField_field_136_of_type_Int = ((Integer)paramclass_69[2].a4()).intValue();
    this.jdField_field_136_of_type_Long = ((Long)paramclass_69[3].a4()).longValue();
  }
  
  public String getUniqueIdentifier()
  {
    return "INVITE_" + this.jdField_field_136_of_type_JavaLangString + "_" + this.field_139 + "_" + this.jdField_field_136_of_type_Int + "_" + this.jdField_field_136_of_type_Long;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public final String b()
  {
    return this.jdField_field_136_of_type_JavaLangString;
  }
  
  public final int a3()
  {
    return this.jdField_field_136_of_type_Int;
  }
  
  public final void a188(String paramString1, String paramString2, int paramInt, long paramLong)
  {
    this.jdField_field_136_of_type_JavaLangString = paramString1;
    this.field_139 = paramString2;
    this.jdField_field_136_of_type_Int = paramInt;
    this.jdField_field_136_of_type_Long = paramLong;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_777
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */