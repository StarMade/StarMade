import java.io.PrintStream;

public class class_4
  extends class_12
{
  private static class_853 jdField_field_4_of_type_Class_853;
  private Object jdField_field_4_of_type_JavaLangObject;
  private Object jdField_field_5_of_type_JavaLangObject;
  private Object field_6;
  private Object field_7;
  
  public class_4(class_371 paramclass_371)
  {
    super(paramclass_371);
    if (jdField_field_4_of_type_Class_853 == null) {
      jdField_field_4_of_type_Class_853 = new class_853(paramclass_371, this);
    }
    b();
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_1363.b29() != null) && (!paramclass_1363.l1()) && (paramclass_1363.a_())) {
      class_969.b("0022_action - buttons push small");
    }
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      if (paramclass_1363.b29().equals("OK"))
      {
        class_969.b("0022_menu_ui - enter");
        d();
        this.field_4.a14().jdField_field_4_of_type_Class_477.c2(false);
        this.field_4.a14().jdField_field_4_of_type_Class_26.d2(true);
        this.field_4.a14().jdField_field_4_of_type_Class_26.a13(200);
        if (this.jdField_field_4_of_type_JavaLangObject != class_949.field_1176.a4())
        {
          System.err.println("ScreenSetting Changed");
          class_933.b3();
        }
        if (this.jdField_field_5_of_type_JavaLangObject != class_949.field_1178.a4())
        {
          System.err.println("ScreenSetting Changed");
          class_933.b3();
        }
        if (this.field_6 != class_949.field_1177.a4())
        {
          System.err.println("ScreenSetting Changed");
          class_933.b3();
        }
        if (this.field_7 != class_949.field_1179.a4())
        {
          System.err.println("VIS distance Changed");
          class_969.field_1259.a1();
        }
        b();
        return;
      }
      if (paramclass_1363.b29().equals("CANCEL"))
      {
        class_969.b("0022_menu_ui - back");
        d();
        this.field_4.a14().jdField_field_4_of_type_Class_477.c2(false);
        this.field_4.a14().jdField_field_4_of_type_Class_26.d2(true);
        this.field_4.a14().jdField_field_4_of_type_Class_26.a13(200);
        return;
      }
      if (paramclass_1363.b29().equals("X"))
      {
        class_969.b("0022_menu_ui - back");
        d();
        this.field_4.a14().jdField_field_4_of_type_Class_477.c2(false);
        this.field_4.a14().jdField_field_4_of_type_Class_26.d2(true);
        this.field_4.a14().jdField_field_4_of_type_Class_26.a13(200);
        return;
      }
      if (!jdField_field_5_of_type_Boolean) {
        throw new AssertionError("not known command: '" + paramclass_1363.b29() + "'");
      }
    }
  }
  
  public void handleKeyEvent() {}
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void a2() {}
  
  private void b()
  {
    this.jdField_field_4_of_type_JavaLangObject = class_949.field_1176.a4();
    this.jdField_field_5_of_type_JavaLangObject = class_949.field_1178.a4();
    this.field_6 = class_949.field_1177.a4();
    this.field_7 = class_949.field_1179.a4();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_4
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */