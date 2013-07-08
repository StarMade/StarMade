public final class class_363
{
  public final int field_709;
  public final int field_710;
  public final class_797 field_709;
  
  public class_363(class_797 paramclass_797, int paramInt1, int paramInt2)
  {
    this.jdField_field_709_of_type_Int = paramInt1;
    this.field_710 = paramInt2;
    this.jdField_field_709_of_type_Class_797 = paramclass_797;
  }
  
  public final int hashCode()
  {
    return this.jdField_field_709_of_type_Class_797.hashCode() + this.jdField_field_709_of_type_Int + this.field_710 * 10000;
  }
  
  public final boolean equals(Object paramObject)
  {
    return (((class_363)paramObject).jdField_field_709_of_type_Class_797 == this.jdField_field_709_of_type_Class_797) && (((class_363)paramObject).jdField_field_709_of_type_Int == this.jdField_field_709_of_type_Int) && (((class_363)paramObject).field_710 == this.field_710);
  }
  
  public final String toString()
  {
    return "SectorChange[" + this.jdField_field_709_of_type_Int + " -> " + this.field_710 + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_363
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */