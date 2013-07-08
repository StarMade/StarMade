import org.lwjgl.input.Keyboard;
import org.schema.schine.network.ChatSystem;

public final class class_455
  extends class_16
{
  public class_455(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void handleKeyEvent()
  {
    a6().getChat().handleKeyEvent();
  }
  
  public final void a12(class_939 paramclass_939)
  {
    super.a12(paramclass_939);
  }
  
  public final void b2(boolean paramBoolean)
  {
    a6().a14().field_4.jdField_field_4_of_type_Class_445.e2(paramBoolean);
    a6().a14().field_4.jdField_field_4_of_type_Class_483.e2(paramBoolean);
    a6().a14().field_4.jdField_field_4_of_type_Class_471.e2(paramBoolean);
    super.b2(paramBoolean);
  }
  
  public final void c2(boolean paramBoolean)
  {
    super.c2(paramBoolean);
    Keyboard.enableRepeatEvents(paramBoolean);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    super.a15(paramclass_941);
    class_1046.field_1306 = false;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_455
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */