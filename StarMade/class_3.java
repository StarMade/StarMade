import org.schema.schine.graphicsengine.shader.ErrorDialogException;

final class class_3
  extends class_13
{
  class_3(class_371 paramclass_371, Object paramObject1, Object paramObject2)
  {
    super(paramclass_371, paramObject1, paramObject2);
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void b()
  {
    class_969.b("0022_action - buttons push medium");
    if (this.field_4.a4().b4())
    {
      d();
      try
      {
        this.field_4.a14().jdField_field_4_of_type_Class_26.c2(false);
      }
      catch (ErrorDialogException localErrorDialogException)
      {
        localErrorDialogException;
      }
      this.field_4.a14().jdField_field_4_of_type_Class_467.d2(true);
      this.field_4.a14().jdField_field_4_of_type_Class_467.a13(500);
    }
    else
    {
      String str = "Those who don't exist cannot be killed!.";
      this.field_4.a17(str);
    }
    d();
  }
  
  public final void a2()
  {
    this.field_4.a14().jdField_field_4_of_type_Class_467.field_4.a13(400);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_3
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */