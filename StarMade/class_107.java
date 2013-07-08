final class class_107
  implements class_1412
{
  class_107(class_116 paramclass_116, class_758 paramclass_758) {}
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      if (!class_112.a21(this.jdField_field_91_of_type_Class_116.field_89).jdField_field_136_of_type_JavaLangString.equals(((class_371)this.jdField_field_91_of_type_Class_116.a24()).a20().getName()))
      {
        if (!class_116.a30(this.jdField_field_91_of_type_Class_116).a180().b3(this.jdField_field_91_of_type_Class_758.jdField_field_136_of_type_Byte)) {
          ((class_371)this.jdField_field_91_of_type_Class_116.a24()).a4().b1("You don't have kick permission!");
        } else if (this.jdField_field_91_of_type_Class_758.jdField_field_136_of_type_Byte <= class_112.a21(this.jdField_field_91_of_type_Class_116.field_89).jdField_field_136_of_type_Byte) {
          ((class_371)this.jdField_field_91_of_type_Class_116.a24()).a4().b1("You cannot kick\nhigher ranked players!");
        } else {
          class_116.a30(this.jdField_field_91_of_type_Class_116).a176(this.jdField_field_91_of_type_Class_758.jdField_field_136_of_type_JavaLangString, class_112.a21(this.jdField_field_91_of_type_Class_116.field_89).jdField_field_136_of_type_JavaLangString, ((class_371)this.jdField_field_91_of_type_Class_116.a24()).a12());
        }
        this.jdField_field_91_of_type_Class_116.field_89.d();
        return;
      }
      ((class_371)this.jdField_field_91_of_type_Class_116.a24()).a4().b1("You cannot kick yourself");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_107
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */