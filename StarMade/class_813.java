final class class_813
{
  private String jdField_field_1078_of_type_JavaLangString = "";
  private String field_1079 = "";
  
  class_813(class_815 paramclass_815) {}
  
  public final String toString()
  {
    int i = ((class_371)this.jdField_field_1078_of_type_Class_815.a24()).a20().h1();
    class_773 localclass_773;
    if (((localclass_773 = ((class_371)this.jdField_field_1078_of_type_Class_815.a24()).a45().a156(i)) != null) && (localclass_773.d8().length() > 0))
    {
      class_815.a114(this.jdField_field_1078_of_type_Class_815, localclass_773);
      if (!this.jdField_field_1078_of_type_JavaLangString.equals(localclass_773.d8()))
      {
        this.field_1079 = ("Home Base: " + localclass_773.d8().replaceFirst("ENTITY_", "").replaceFirst("SPACESTATION_", "").replaceFirst("PLANET_", "") + " at " + localclass_773.a44().field_475 + ", " + localclass_773.a44().field_476 + ", " + localclass_773.a44().field_477);
        this.jdField_field_1078_of_type_JavaLangString = new String(localclass_773.d8());
      }
      return this.field_1079;
    }
    class_815.a114(this.jdField_field_1078_of_type_Class_815, null);
    return "No Home Base";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_813
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */