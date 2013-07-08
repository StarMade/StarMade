import java.io.PrintStream;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.schema.game.client.controller.PlayerNotYetInitializedException;

public class class_1
  extends class_12
{
  private class_198 field_4 = new class_198(paramclass_371, this, "Spawn Menu");
  
  public class_1(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_1363.b29() != null) && (!paramclass_1363.l1()) && (paramclass_1363.a_())) {
      class_969.b("0022_action - buttons push small");
    }
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0) && (!this.jdField_field_4_of_type_Class_371.a14().jdField_field_4_of_type_Class_26.field_5) && (!b1()))
    {
      class_12.jdField_field_4_of_type_Long = System.currentTimeMillis();
      try
      {
        if (paramclass_1363.b29().equals("JOIN"))
        {
          if (!this.jdField_field_4_of_type_Class_371.f())
          {
            class_969.b("0022_menu_ui - enter");
            this.jdField_field_4_of_type_Class_371.a4().e1();
            d();
          }
        }
        else
        {
          if (paramclass_1363.b29().equals("EXIT"))
          {
            System.err.println("EXIT: WAS: " + paramclass_1363.l1() + " -> " + paramclass_1363.a_() + ": " + paramclass_1363.b29());
            class_969.b("0022_menu_ui - cancel");
            System.err.println("[GAMEMENU] MENU EXIT: SYSTEM WILL NOW EXIT");
            class_933.a3();
            return;
          }
          if (paramclass_1363.b29().equals("X"))
          {
            class_969.b("0022_menu_ui - cancel");
            this.jdField_field_4_of_type_Class_371.setLastDeactivatedMenu(System.currentTimeMillis());
            this.jdField_field_4_of_type_Class_371.a14().b();
            return;
          }
          if (!field_5) {
            throw new AssertionError("not known command: " + paramclass_1363.b29());
          }
        }
        return;
      }
      catch (PlayerNotYetInitializedException localPlayerNotYetInitializedException)
      {
        localPlayerNotYetInitializedException.printStackTrace();
        this.jdField_field_4_of_type_Class_371.a4().b1("Login Procedure not finished\n please try again");
      }
    }
  }
  
  public void handleKeyEvent()
  {
    if (Keyboard.getEventKeyState()) {
      switch (Keyboard.getEventKey())
      {
      case 87: 
        this.jdField_field_4_of_type_Class_371.a14().jdField_field_4_of_type_Class_467.jdField_field_4_of_type_Class_471.c2(false);
        this.jdField_field_4_of_type_Class_371.a14().jdField_field_4_of_type_Class_467.jdField_field_4_of_type_Class_483.c2(true);
        d();
      }
    }
  }
  
  public final boolean a1()
  {
    return this.jdField_field_4_of_type_Class_371.b().indexOf(this) != this.jdField_field_4_of_type_Class_371.b().size() - 1;
  }
  
  public final void a2() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */