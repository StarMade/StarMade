import java.io.PrintStream;
import org.schema.schine.ai.stateMachines.FSMException;

public class class_482
  extends class_12
{
  private class_913 jdField_field_4_of_type_Class_913;
  private class_454 jdField_field_4_of_type_Class_454;
  
  public class_482(class_371 paramclass_371, String paramString, class_454 paramclass_454)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Class_454 = paramclass_454;
    this.jdField_field_4_of_type_Class_913 = new class_913(paramclass_371, this, paramString);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0) && (!b1()))
    {
      class_12.jdField_field_4_of_type_Long = System.currentTimeMillis();
      if (paramclass_1363.b29().equals("NEXT"))
      {
        class_969.b("0022_menu_ui - enter");
        this.jdField_field_4_of_type_Class_454.b1();
        d();
        return;
      }
      if (paramclass_1363.b29().equals("BACK"))
      {
        class_969.b("0022_menu_ui - enter");
        try
        {
          this.jdField_field_4_of_type_Class_371.a4().a5().a();
          return;
        }
        catch (FSMException paramclass_1363)
        {
          System.err.println("FSMException: " + paramclass_1363.getMessage());
          return;
        }
      }
      if (paramclass_1363.b29().equals("SKIP"))
      {
        class_969.b("0022_menu_ui - cancel");
        this.jdField_field_4_of_type_Class_371.a4().a5().f();
        return;
      }
      if (paramclass_1363.b29().equals("END"))
      {
        class_969.b("0022_menu_ui - cancel");
        this.jdField_field_4_of_type_Class_371.a4().a5().b();
        return;
      }
      if (!field_5) {
        throw new AssertionError("not known command: " + paramclass_1363.b29());
      }
    }
  }
  
  public final class_454 a80()
  {
    return this.jdField_field_4_of_type_Class_454;
  }
  
  public final class_1363 a3()
  {
    return this.jdField_field_4_of_type_Class_913;
  }
  
  public void handleKeyEvent() {}
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void a2() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_482
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */