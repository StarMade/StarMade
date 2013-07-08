import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;

final class class_926
  implements class_1412
{
  class_926(class_924 paramclass_924) {}
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    paramclass_1363 = null;
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      class_924.a131(this.field_91, !class_924.a132(this.field_91));
      try
      {
        class_924.a133(this.field_91).e();
        class_924.a134(this.field_91);
        return;
      }
      catch (StateParameterNotFoundException localStateParameterNotFoundException)
      {
        (paramclass_1363 = localStateParameterNotFoundException).printStackTrace();
        class_933.a2(paramclass_1363);
      }
    }
  }
  
  public final boolean a1()
  {
    return false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_926
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */