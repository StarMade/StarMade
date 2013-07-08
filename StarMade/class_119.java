import java.util.ArrayList;

final class class_119
  implements class_1412
{
  private class_119(class_113 paramclass_113) {}
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0)) {
      if (paramclass_1363.b29().equals("INVENTORY"))
      {
        if (!class_113.a25(this.jdField_field_91_of_type_Class_113)) {
          class_113.a26(this.jdField_field_91_of_type_Class_113);
        }
      }
      else if (paramclass_1363.b29().equals("WEAPON"))
      {
        if (!class_113.b4(this.jdField_field_91_of_type_Class_113)) {
          class_113.b5(this.jdField_field_91_of_type_Class_113);
        }
      }
      else if (paramclass_1363.b29().equals("FACTION"))
      {
        if (!class_113.c2(this.jdField_field_91_of_type_Class_113)) {
          class_113.c3(this.jdField_field_91_of_type_Class_113);
        }
      }
      else if (paramclass_1363.b29().equals("CATALOG"))
      {
        if (!class_113.d2(this.jdField_field_91_of_type_Class_113)) {
          class_113.d3(this.jdField_field_91_of_type_Class_113);
        }
      }
      else if (paramclass_1363.b29().equals("AI"))
      {
        if (!class_113.e2(this.jdField_field_91_of_type_Class_113)) {
          class_113.e3(this.jdField_field_91_of_type_Class_113);
        }
      }
      else if (paramclass_1363.b29().equals("SHOP"))
      {
        if (!class_113.f2(this.jdField_field_91_of_type_Class_113)) {
          class_113.f3(this.jdField_field_91_of_type_Class_113);
        }
      }
      else if (paramclass_1363.b29().equals("NAVIGATION"))
      {
        if (!class_113.g1(this.jdField_field_91_of_type_Class_113)) {
          class_113.g2(this.jdField_field_91_of_type_Class_113);
        }
      }
      else
      {
        if (paramclass_1363.b29().equals("X"))
        {
          class_113.h(this.jdField_field_91_of_type_Class_113);
          return;
        }
        if (!jdField_field_91_of_type_Boolean) {
          throw new AssertionError("not known command: " + paramclass_1363.b29());
        }
      }
    }
  }
  
  public final boolean a1()
  {
    return !this.jdField_field_91_of_type_Class_113.a20().b().isEmpty();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_119
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */