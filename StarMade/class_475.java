import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public final class class_475
  extends class_16
{
  public static final HashSet field_5;
  private long field_5;
  
  public class_475(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void handleKeyEvent()
  {
    super.handleKeyEvent();
    if ((Keyboard.getEventKeyState()) && (Keyboard.getEventKey() == 88)) {
      a6().a4().a29().a8().a(new class_48(0, 0, 0));
    }
    a6().a27().a101().handleKeyEvent();
  }
  
  public final void a12(class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      int i = System.currentTimeMillis() - this.jdField_field_5_of_type_Long < 300L ? 1 : 0;
      Iterator localIterator = jdField_field_5_of_type_JavaUtilHashSet.iterator();
      while (localIterator.hasNext())
      {
        class_347 localclass_347 = (class_347)localIterator.next();
        if (i != 0)
        {
          class_48 localclass_48 = a6().a27().a101().a29().field_117;
          class_337 localclass_337 = (class_337)localclass_347;
          a6().a27().a101().a29().a1((int)(localclass_337.a8().field_615 / 6.25F) + (localclass_48.field_475 << 4), (int)(localclass_337.a8().field_616 / 6.25F) + (localclass_48.field_476 << 4), (int)(localclass_337.a8().field_617 / 6.25F) + (localclass_48.field_477 << 4), false);
        }
        System.err.println("[CLIENT][MAPMANAGER] clicked on " + localclass_347);
      }
      this.jdField_field_5_of_type_Long = System.currentTimeMillis();
    }
    super.a12(paramclass_939);
  }
  
  public final void b2(boolean paramBoolean)
  {
    System.err.println("MAP CONTROL MANAGER ACTIVE: " + paramBoolean);
    if (paramBoolean)
    {
      class_969.b("0022_menu_ui - swoosh scroll large");
      a6().a4().a29().a8().a(new class_48(0, 0, 0));
    }
    else
    {
      class_969.b("0022_menu_ui - swoosh scroll small");
    }
    a6().a14().field_4.field_4.field_4.a51().a45().jdField_field_4_of_type_Class_332.e2(paramBoolean);
    a6().a14().field_4.field_4.field_4.a51().a45().jdField_field_4_of_type_Class_453.e2(paramBoolean);
    if (paramBoolean) {
      a6().a27().a101().d();
    }
    super.b2(paramBoolean);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    class_1046.field_1306 = Mouse.isButtonDown(1);
    a6().a14().field_4.field_4.field_4.e2(true);
  }
  
  static
  {
    jdField_field_5_of_type_JavaUtilHashSet = new HashSet();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_475
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */