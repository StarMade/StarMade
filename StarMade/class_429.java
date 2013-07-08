public final class class_429
  extends class_12
{
  private final class_91 jdField_field_4_of_type_Class_91;
  private final class_781 jdField_field_4_of_type_Class_781;
  
  public class_429(class_371 paramclass_371, class_781 paramclass_781)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Class_91 = new class_91(paramclass_371, this, paramclass_781);
    this.jdField_field_4_of_type_Class_91.c();
    this.jdField_field_4_of_type_Class_781 = paramclass_781;
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void handleKeyEvent() {}
  
  public final class_1363 a3()
  {
    return this.jdField_field_4_of_type_Class_91;
  }
  
  public final void a2()
  {
    this.field_4.a14().field_4.field_4.field_4.a13(500);
    this.field_4.a14().field_4.field_4.field_4.e2(false);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      if ((paramclass_1363.b29() instanceof Integer))
      {
        paramclass_1363 = ((Integer)paramclass_1363.b29()).intValue();
        this.field_4.a53().a95(this.field_4.getPlayerName(), this.jdField_field_4_of_type_Class_781.field_136, paramclass_1363 + 1);
        d();
        return;
      }
      if ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X"))) {
        d();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_429
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */