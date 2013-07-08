public class class_771
  implements class_80, Comparable
{
  private int jdField_field_136_of_type_Int;
  private String jdField_field_136_of_type_JavaLangString;
  private long jdField_field_136_of_type_Long;
  private String jdField_field_139_of_type_JavaLangString;
  private int jdField_field_139_of_type_Int;
  
  public final void a170(int paramInt1, String paramString1, long paramLong, String paramString2, int paramInt2)
  {
    this.jdField_field_136_of_type_Int = paramInt1;
    this.jdField_field_136_of_type_JavaLangString = paramString1;
    this.jdField_field_136_of_type_Long = paramLong;
    this.jdField_field_139_of_type_JavaLangString = paramString2;
    this.jdField_field_139_of_type_Int = paramInt2;
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    class_69[] arrayOfclass_69 = (class_69[])paramclass_69.a4();
    if ("fp-v0".equals(paramclass_69.a2()))
    {
      this.jdField_field_136_of_type_Int = ((Integer)arrayOfclass_69[0].a4()).intValue();
      this.jdField_field_136_of_type_JavaLangString = ((String)arrayOfclass_69[1].a4());
      this.jdField_field_136_of_type_Long = ((Long)arrayOfclass_69[2].a4()).longValue();
      this.jdField_field_139_of_type_JavaLangString = ((String)arrayOfclass_69[3].a4());
      this.jdField_field_139_of_type_Int = ((Integer)arrayOfclass_69[4].a4()).intValue();
      return;
    }
    if (!jdField_field_136_of_type_Boolean) {
      throw new AssertionError(paramclass_69.a2());
    }
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_551, "id", Integer.valueOf(this.jdField_field_136_of_type_Int));
    class_69 localclass_692 = new class_69(class_79.field_556, "op", this.jdField_field_136_of_type_JavaLangString);
    class_69 localclass_693 = new class_69(class_79.field_552, "dt", Long.valueOf(this.jdField_field_136_of_type_Long));
    class_69 localclass_694 = new class_69(class_79.field_556, "msg", this.jdField_field_139_of_type_JavaLangString);
    class_69 localclass_695 = new class_69(class_79.field_551, "perm", Integer.valueOf(this.jdField_field_139_of_type_Int));
    return new class_69(class_79.field_561, "fp-v0", new class_69[] { localclass_691, localclass_692, localclass_693, localclass_694, localclass_695, new class_69(class_79.field_548, null, null) });
  }
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public boolean equals(Object paramObject)
  {
    paramObject = (class_771)paramObject;
    return (this.jdField_field_136_of_type_Int == paramObject.jdField_field_136_of_type_Int) && (this.jdField_field_136_of_type_Long == paramObject.jdField_field_136_of_type_Long) && (this.jdField_field_136_of_type_JavaLangString.equals(paramObject.jdField_field_136_of_type_JavaLangString)) && (this.jdField_field_139_of_type_JavaLangString.equals(paramObject.jdField_field_139_of_type_JavaLangString));
  }
  
  public int hashCode()
  {
    return (int)(this.jdField_field_136_of_type_Int + this.jdField_field_136_of_type_Long + this.jdField_field_136_of_type_JavaLangString.hashCode() + this.jdField_field_139_of_type_JavaLangString.hashCode());
  }
  
  public final String a()
  {
    return this.jdField_field_136_of_type_JavaLangString;
  }
  
  public final long a28()
  {
    return this.jdField_field_136_of_type_Long;
  }
  
  public final String b()
  {
    return this.jdField_field_139_of_type_JavaLangString;
  }
  
  public final int a3()
  {
    return this.jdField_field_139_of_type_Int;
  }
  
  public final int b5()
  {
    return this.jdField_field_136_of_type_Int;
  }
  
  public String toString()
  {
    return "FactionNewsPost [factionId=" + this.jdField_field_136_of_type_Int + ", op=" + this.jdField_field_136_of_type_JavaLangString + ", date=" + this.jdField_field_136_of_type_Long + ", message=" + this.jdField_field_139_of_type_JavaLangString + ", permission=" + this.jdField_field_139_of_type_Int + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_771
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */