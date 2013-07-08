import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import org.lwjgl.input.Keyboard;
import org.schema.schine.network.ChatSystem;

public final class class_467
  extends class_16
{
  private class_455 field_4;
  public class_483 field_4;
  public class_471 field_4;
  public class_445 field_4;
  
  public class_467(class_371 paramclass_371)
  {
    super(paramclass_371);
    paramclass_371 = this;
    this.jdField_field_4_of_type_Class_455 = new class_455(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_483 = new class_483(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_471 = new class_471(paramclass_371.a6());
    paramclass_371.jdField_field_4_of_type_Class_445 = new class_445(paramclass_371.a6());
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_455);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_483);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_471);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_445);
    paramclass_371.jdField_field_4_of_type_Class_471.c2(true);
  }
  
  public final class_471 a76()
  {
    return this.jdField_field_4_of_type_Class_471;
  }
  
  public final class_455 a77()
  {
    return this.jdField_field_4_of_type_Class_455;
  }
  
  public final class_483 a78()
  {
    return this.jdField_field_4_of_type_Class_483;
  }
  
  public final class_445 a79()
  {
    return this.jdField_field_4_of_type_Class_445;
  }
  
  public final void handleKeyEvent()
  {
    synchronized (a6().b())
    {
      int i = 0;
      if (b1()) {
        return;
      }
    }
    boolean bool1 = this.jdField_field_4_of_type_Class_455.field_5;
    if (Keyboard.getEventKeyState()) {
      if (Keyboard.getEventKey() == class_367.field_743.a5())
      {
        if (!bool1)
        {
          if ((Keyboard.isKeyDown(42)) || (Keyboard.isKeyDown(54)))
          {
            System.err.println("USING FACTION CHAT");
            a6().getChat().getTextInput().a9("/f ");
          }
          this.jdField_field_4_of_type_Class_455.d2(true);
        }
        else
        {
          this.jdField_field_4_of_type_Class_455.d2(false);
        }
      }
      else if ((Keyboard.getEventKey() == 87) && (!bool1))
      {
        boolean bool2;
        if ((!(bool2 = this.jdField_field_4_of_type_Class_483.field_5)) && (!a6().b().isEmpty())) {
          return;
        }
        if ((bool2) && (a6().a3() == null))
        {
          System.out.println("no player character: spawning");
          a6().a4().e1();
        }
        else
        {
          this.jdField_field_4_of_type_Class_445.c2(bool2);
          this.jdField_field_4_of_type_Class_483.c2(!bool2);
        }
        if ((this.jdField_field_4_of_type_Class_471.field_5) && (!bool2))
        {
          this.jdField_field_4_of_type_Class_471.c2(false);
          this.jdField_field_4_of_type_Class_483.c2(true);
        }
      }
    }
    super.handleKeyEvent();
  }
  
  public final void a12(class_939 paramclass_939)
  {
    super.a12(paramclass_939);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    super.a15(paramclass_941);
    if (!a6().b().isEmpty()) {
      class_1046.field_1306 = false;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_467
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */