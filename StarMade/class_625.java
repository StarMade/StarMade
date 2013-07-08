public class class_625
  implements class_80
{
  private final long[] jdField_field_136_of_type_ArrayOfLong = { 0L, 0L, 0L, 0L, 2047L };
  private final String[] jdField_field_136_of_type_ArrayOfJavaLangString = { "Member 4th Rank", "Member 3rd Rank", "Member 2rd Rank", "Member 1st Rank", "Founder" };
  public int field_136;
  
  public String getUniqueIdentifier()
  {
    return null;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if (paramclass_69.a2().equals("0"))
    {
      paramclass_69 = (class_69[])paramclass_69.a4();
      this.jdField_field_136_of_type_Int = ((Integer)paramclass_69[0].a4()).intValue();
      class_69[] arrayOfclass_69 = (class_69[])paramclass_69[1].a4();
      paramclass_69 = (class_69[])paramclass_69[2].a4();
      for (int i = 0; i < 5; i++)
      {
        this.jdField_field_136_of_type_ArrayOfLong[i] = ((Long)arrayOfclass_69[i].a4()).longValue();
        this.jdField_field_136_of_type_ArrayOfJavaLangString[i] = ((String)paramclass_69[i].a4());
      }
      return;
    }
    if (!jdField_field_136_of_type_Boolean) {
      throw new AssertionError();
    }
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_136_of_type_ArrayOfLong[0])), new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_136_of_type_ArrayOfLong[1])), new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_136_of_type_ArrayOfLong[2])), new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_136_of_type_ArrayOfLong[3])), new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_136_of_type_ArrayOfLong[4])), new class_69(class_79.field_548, null, null) });
    class_69 localclass_692 = new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_556, null, this.jdField_field_136_of_type_ArrayOfJavaLangString[0]), new class_69(class_79.field_556, null, this.jdField_field_136_of_type_ArrayOfJavaLangString[1]), new class_69(class_79.field_556, null, this.jdField_field_136_of_type_ArrayOfJavaLangString[2]), new class_69(class_79.field_556, null, this.jdField_field_136_of_type_ArrayOfJavaLangString[3]), new class_69(class_79.field_556, null, this.jdField_field_136_of_type_ArrayOfJavaLangString[4]), new class_69(class_79.field_548, null, null) });
    return new class_69(class_79.field_561, "0", new class_69[] { new class_69(class_79.field_551, null, Integer.valueOf(this.jdField_field_136_of_type_Int)), localclass_691, localclass_692, new class_69(class_79.field_548, null, null) });
  }
  
  public final long[] a25()
  {
    return this.jdField_field_136_of_type_ArrayOfLong;
  }
  
  public final String[] a26()
  {
    return this.jdField_field_136_of_type_ArrayOfJavaLangString;
  }
  
  public final boolean a18(int paramInt)
  {
    return (this.jdField_field_136_of_type_ArrayOfLong[paramInt] & 1L) == 1L;
  }
  
  public final boolean b3(int paramInt)
  {
    return (this.jdField_field_136_of_type_ArrayOfLong[paramInt] & 0x2) == 2L;
  }
  
  public final boolean c(int paramInt)
  {
    return (this.jdField_field_136_of_type_ArrayOfLong[paramInt] & 0x4) == 4L;
  }
  
  public final boolean d(int paramInt)
  {
    return (this.jdField_field_136_of_type_ArrayOfLong[paramInt] & 0x8) == 8L;
  }
  
  public final void a27(class_625 paramclass_625)
  {
    for (int i = 0; i < 5; i++)
    {
      if ((!jdField_field_136_of_type_Boolean) && (this.jdField_field_136_of_type_Int != paramclass_625.jdField_field_136_of_type_Int)) {
        throw new AssertionError();
      }
      this.jdField_field_136_of_type_ArrayOfLong[i] = paramclass_625.jdField_field_136_of_type_ArrayOfLong[i];
      this.jdField_field_136_of_type_ArrayOfJavaLangString[i] = paramclass_625.jdField_field_136_of_type_ArrayOfJavaLangString[i];
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_625
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */