import java.io.PrintStream;

public class class_2
  extends class_12
{
  private class_200 field_4 = new class_200(paramclass_371, this, "Main Menu");
  
  public class_2(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_1363.b29() != null) && (!paramclass_1363.l1()) && (paramclass_1363.a_())) {
      class_969.b("0022_action - buttons push small");
    }
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      if ((paramclass_1363.b29().equals("RESUME")) || (paramclass_1363.b29().equals("X")))
      {
        class_969.b("0022_menu_ui - enter");
        d();
        this.field_4.a14().jdField_field_4_of_type_Class_26.c2(false);
        this.field_4.a14().jdField_field_4_of_type_Class_467.d2(true);
        this.field_4.a14().jdField_field_4_of_type_Class_467.a13(500);
        return;
      }
      if (paramclass_1363.b29().equals("EXIT"))
      {
        class_969.b("0022_menu_ui - back");
        paramclass_939 = "Sorry! there is no menu to go back to. Plase choose 'exit game'.";
        this.field_4.a17(paramclass_939);
        return;
      }
      if (paramclass_1363.b29().equals("SUICIDE"))
      {
        class_969.b("0022_menu_ui - enter");
        paramclass_1363 = "Are you sure you want to commit suicide?\nYou will spawn at the last activated\n\"Undeathinator\" module.";
        new class_3(this.field_4, "Commit suicide?", paramclass_1363).c1();
        return;
      }
      if (paramclass_1363.b29().equals("EXIT_TO_WINDOWS"))
      {
        System.err.println("MAIN MENU EXIT");
        class_969.b("0022_menu_ui - back");
        class_933.a3();
        return;
      }
      if (paramclass_1363.b29().equals("OPTIONS"))
      {
        class_969.b("0022_menu_ui - enter");
        this.field_4.a14().jdField_field_4_of_type_Class_26.c2(false);
        this.field_4.a14().jdField_field_4_of_type_Class_477.c2(true);
        return;
      }
      if (!field_5) {
        throw new AssertionError("not known command: " + paramclass_1363.b29());
      }
    }
  }
  
  public void handleKeyEvent() {}
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void a2() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_2
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */