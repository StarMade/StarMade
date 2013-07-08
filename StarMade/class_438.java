public abstract class class_438
  implements class_80
{
  public class_48 field_136;
  public class_347[] field_136;
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    this.jdField_field_136_of_type_Class_48 = ((class_48)paramclass_69[0].a4());
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_558, null, this.jdField_field_136_of_type_Class_48);
    class_69 localclass_692 = null.toTagStructure();
    class_69[] arrayOfclass_69 = new class_69[this.jdField_field_136_of_type_ArrayOfClass_347.length + 1];
    for (int i = 0; i < arrayOfclass_69.length; i++) {
      arrayOfclass_69[i] = this.jdField_field_136_of_type_ArrayOfClass_347[i].toTagStructure();
    }
    arrayOfclass_69[(arrayOfclass_69.length - 1)] = new class_69(class_79.field_548, null, null);
    class_69 localclass_693 = new class_69(class_79.field_561, null, arrayOfclass_69);
    return new class_69(class_79.field_561, null, new class_69[] { localclass_691, localclass_692, localclass_693, new class_69(class_79.field_548, null, null) });
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_438
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */