import org.schema.schine.network.client.ClientState;

public final class class_90
  extends class_196
{
  private class_773 jdField_field_89_of_type_Class_773;
  private class_156[] jdField_field_89_of_type_ArrayOfClass_156;
  
  public class_90(ClientState paramClientState, class_346 paramclass_346, class_773 paramclass_773)
  {
    super(paramClientState, paramclass_346, "Edit Roles", "");
    this.jdField_field_89_of_type_Class_773 = paramclass_773;
  }
  
  public final void c()
  {
    super.c();
    class_968 localclass_968 = new class_968(406.0F, 139.0F, a24());
    class_964 localclass_964 = new class_964(a24());
    this.jdField_field_89_of_type_ArrayOfClass_156 = new class_156[5];
    for (int i = 0; i < 5; i++)
    {
      this.jdField_field_89_of_type_ArrayOfClass_156[i] = new class_156((class_371)a24(), this.jdField_field_89_of_type_Class_773, i);
      this.jdField_field_89_of_type_ArrayOfClass_156[i].c();
      localclass_964.a144(new class_959(this.jdField_field_89_of_type_ArrayOfClass_156[i], this.jdField_field_89_of_type_ArrayOfClass_156[i], a24()));
    }
    localclass_968.c7(localclass_964);
    this.jdField_field_89_of_type_Class_1414.a9(localclass_968);
  }
  
  public final class_156[] a13()
  {
    return this.jdField_field_89_of_type_ArrayOfClass_156;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_90
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */