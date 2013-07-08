import java.io.PrintStream;

public final class class_121
  extends class_12
{
  private class_127 jdField_field_4_of_type_Class_127 = new class_127(this, this.field_4, this, "Outgoing Invites", "");
  private class_16 jdField_field_4_of_type_Class_16;
  
  public class_121(class_371 paramclass_371, class_16 paramclass_16)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Class_16 = paramclass_16;
    paramclass_16.e2(true);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if (paramclass_939.a())
    {
      if (paramclass_1363.b29().equals("OK")) {
        d();
      }
      if ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X")))
      {
        System.err.println("CANCEL");
        d();
      }
    }
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void handleKeyEvent() {}
  
  public final class_1363 a3()
  {
    return this.jdField_field_4_of_type_Class_127;
  }
  
  public final void a2()
  {
    this.jdField_field_4_of_type_Class_16.e2(false);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_121
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */