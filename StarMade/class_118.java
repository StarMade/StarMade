final class class_118
  extends class_13
{
  class_118(class_112 paramclass_112, class_371 paramclass_371, Object paramObject1, Object paramObject2, class_758 paramclass_758, int paramInt)
  {
    super(paramclass_371, paramObject1, paramObject2);
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void b()
  {
    if (!this.jdField_field_4_of_type_Class_758.d7(this.jdField_field_4_of_type_Class_112.jdField_field_4_of_type_Class_773)) {
      this.field_4.a4().b1("You cannot change role:\npermission denied!");
    } else if (this.jdField_field_4_of_type_Class_758.jdField_field_136_of_type_Byte < this.jdField_field_4_of_type_Class_112.jdField_field_4_of_type_Class_758.jdField_field_136_of_type_Byte) {
      this.field_4.a4().b1("You cannot change\nroles of higher ranked\nplayers");
    } else {
      this.jdField_field_4_of_type_Class_112.jdField_field_4_of_type_Class_773.b32(this.field_4.a20().getName(), this.jdField_field_4_of_type_Class_112.jdField_field_4_of_type_Class_758.jdField_field_136_of_type_JavaLangString, (byte)this.jdField_field_4_of_type_Int, this.field_4.a12());
    }
    d();
  }
  
  public final void a2() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_118
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */