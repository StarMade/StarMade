import java.util.ArrayList;
import java.util.Iterator;

public final class class_338
  extends class_16
  implements class_1412
{
  public class_340 field_4 = new class_340();
  
  public class_338(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((this.field_6) && (!this.field_4))
    {
      paramclass_939 = a6().getMouseEvents().iterator();
      while (paramclass_939.hasNext())
      {
        Object localObject;
        if (((localObject = (class_939)paramclass_939.next()).jdField_field_1163_of_type_Int == 0) && (!((class_939)localObject).jdField_field_1163_of_type_Boolean) && ((paramclass_1363 instanceof class_959)))
        {
          class_964 localclass_964;
          (localclass_964 = (class_964)(localObject = (class_959)paramclass_1363).a154()).indexOf(localObject);
          localclass_964.e();
          ((class_959)localObject).a29(true);
          a6().a14().field_4.field_4.field_4.a56(((class_847)((class_959)localObject).a139()).a60());
        }
      }
    }
  }
  
  public final void handleKeyEvent()
  {
    super.handleKeyEvent();
  }
  
  public final void a12(class_939 paramclass_939)
  {
    super.a12(paramclass_939);
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
  
  public final void a15(class_941 paramclass_941)
  {
    class_1046.field_1306 = false;
    a6().a14().field_4.field_4.field_4.e2(true);
  }
  
  public final class_340 a44()
  {
    return new class_340(this.field_4);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_338
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */