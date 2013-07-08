public final class class_916
{
  public int field_1139;
  public int field_1140;
  public int field_1141;
  public short field_1139;
  
  public class_916(class_48 paramclass_48, short paramShort)
  {
    a1(paramclass_48);
    this.jdField_field_1139_of_type_Short = paramShort;
  }
  
  public class_916() {}
  
  public class_916(class_916 paramclass_916)
  {
    this.jdField_field_1139_of_type_Int = paramclass_916.jdField_field_1139_of_type_Int;
    this.field_1140 = paramclass_916.field_1140;
    this.field_1141 = paramclass_916.field_1141;
    this.jdField_field_1139_of_type_Short = paramclass_916.jdField_field_1139_of_type_Short;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject != null)
    {
      if ((paramObject instanceof class_916))
      {
        paramObject = (class_916)paramObject;
        return (this.jdField_field_1139_of_type_Short == paramObject.jdField_field_1139_of_type_Short) && (this.jdField_field_1139_of_type_Int == paramObject.jdField_field_1139_of_type_Int) && (this.field_1140 == paramObject.field_1140) && (this.field_1141 == paramObject.field_1141);
      }
      paramObject = (class_48)paramObject;
      return (this.jdField_field_1139_of_type_Int == paramObject.field_475) && (this.field_1140 == paramObject.field_476) && (this.field_1141 == paramObject.field_477);
    }
    return false;
  }
  
  public final int hashCode()
  {
    return ((this.jdField_field_1139_of_type_Int ^ this.jdField_field_1139_of_type_Int >>> 16) * 15 + (this.field_1140 ^ this.field_1140 >>> 16)) * 15 + (this.field_1141 ^ this.field_1141 >>> 16);
  }
  
  public final void a(class_48 paramclass_48, short paramShort)
  {
    a1(paramclass_48);
    this.jdField_field_1139_of_type_Short = paramShort;
  }
  
  private void a1(class_48 paramclass_48)
  {
    this.jdField_field_1139_of_type_Int = paramclass_48.field_475;
    this.field_1140 = paramclass_48.field_476;
    this.field_1141 = paramclass_48.field_477;
  }
  
  public final String toString()
  {
    return "ElementPosition [x=" + this.jdField_field_1139_of_type_Int + ", y=" + this.field_1140 + ", z=" + this.field_1141 + ", type=" + this.jdField_field_1139_of_type_Short + "]";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_916
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */