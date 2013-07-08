import java.util.ArrayList;
import java.util.Iterator;

public final class class_19
  extends class_16
  implements class_1412
{
  public class_796 field_4;
  public boolean field_7;
  
  public class_19(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    paramclass_1363 = null;
    if ((this.field_6) && (!this.field_4))
    {
      paramclass_1363 = a6().getMouseEvents().iterator();
      while (paramclass_1363.hasNext()) {
        if ((paramclass_939 = (class_939)paramclass_1363.next()).jdField_field_1163_of_type_Int != 0) {}
      }
    }
  }
  
  public final void handleKeyEvent()
  {
    super.handleKeyEvent();
  }
  
  public final boolean a1()
  {
    return !a6().b().isEmpty();
  }
  
  public final void b2(boolean paramBoolean)
  {
    class_1046.field_1306 = !paramBoolean;
    if (paramBoolean) {
      class_969.b("0022_menu_ui - swoosh scroll large");
    } else {
      class_969.b("0022_menu_ui - swoosh scroll small");
    }
    a6().a14().field_4.field_4.field_4.a51().a45().jdField_field_4_of_type_Class_332.e2(paramBoolean);
    a6().a14().field_4.field_4.field_4.a51().a45().jdField_field_4_of_type_Class_453.e2(paramBoolean);
    super.b2(paramBoolean);
  }
  
  public final void a16(class_796 paramclass_796)
  {
    this.field_4 = paramclass_796;
    this.field_7 = true;
  }
  
  public final void a15(class_941 paramclass_941)
  {
    class_1046.field_1306 = false;
    a6().a14().field_4.field_4.field_4.e2(true);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_19
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */