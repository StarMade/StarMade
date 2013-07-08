import java.util.Map;

public final class class_245
  implements class_965
{
  private class_371 jdField_field_98_of_type_Class_371;
  private boolean jdField_field_98_of_type_Boolean;
  private class_1380[] jdField_field_98_of_type_ArrayOfClass_1380;
  
  public class_245(class_371 paramclass_371)
  {
    this.jdField_field_98_of_type_Class_371 = paramclass_371;
  }
  
  public final void a() {}
  
  public final void b()
  {
    if (!this.jdField_field_98_of_type_Boolean) {
      c();
    }
    class_665 localclass_665;
    if (((localclass_665 = this.jdField_field_98_of_type_Class_371.a9()) == null) || (localclass_665.a40().isEmpty())) {
      return;
    }
    for (int i = 0; i < this.jdField_field_98_of_type_ArrayOfClass_1380.length; i++)
    {
      class_1380 localclass_1380;
      (localclass_1380 = this.jdField_field_98_of_type_ArrayOfClass_1380[i]).b28(0.01F, 0.01F, 0.01F);
      localclass_1380.c15(true);
      localclass_1380.a29(true);
      class_1380.a171(localclass_1380, localclass_665.a40().values(), class_969.a1());
      localclass_1380.a29(false);
      localclass_1380.c15(false);
      localclass_1380.b28(1.0F, 1.0F, 1.0F);
    }
  }
  
  public final void c()
  {
    this.jdField_field_98_of_type_ArrayOfClass_1380 = new class_1380[3];
    this.jdField_field_98_of_type_ArrayOfClass_1380[0] = class_969.a2().a5("build-icons-00-16x16-gui-");
    this.jdField_field_98_of_type_ArrayOfClass_1380[1] = class_969.a2().a5("build-icons-01-16x16-gui-");
    this.jdField_field_98_of_type_ArrayOfClass_1380[2] = class_969.a2().a5("build-icons-extra-gui-");
    this.jdField_field_98_of_type_Boolean = true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_245
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */