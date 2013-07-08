import java.util.HashSet;
import org.lwjgl.input.Keyboard;
import org.schema.game.common.controller.EditableSendableSegmentController;

public final class class_328
  extends class_16
  implements class_457
{
  public class_332 field_4;
  public class_453 field_4;
  
  public class_328(class_371 paramclass_371)
  {
    super(paramclass_371);
    paramclass_371 = this;
    this.jdField_field_4_of_type_Class_453 = new class_453(paramclass_371.a6(), paramclass_371);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_453);
    paramclass_371.jdField_field_4_of_type_Class_332 = new class_332(paramclass_371);
    paramclass_371.field_4.add(paramclass_371.jdField_field_4_of_type_Class_332);
  }
  
  public final class_48 a35()
  {
    return new class_48(class_747.field_136);
  }
  
  public final class_453 a36()
  {
    return this.jdField_field_4_of_type_Class_453;
  }
  
  public final EditableSendableSegmentController a37()
  {
    return a6().a25();
  }
  
  public final class_332 a38()
  {
    return this.jdField_field_4_of_type_Class_332;
  }
  
  public final void handleKeyEvent()
  {
    super.handleKeyEvent();
    if ((Keyboard.getEventKeyState()) && (Keyboard.getEventKey() == class_367.field_728.a5()))
    {
      class_328 localclass_328 = this;
      boolean bool = this.jdField_field_4_of_type_Class_453.field_5;
      localclass_328.jdField_field_4_of_type_Class_332.c2(bool);
      localclass_328.jdField_field_4_of_type_Class_453.c2(!bool);
    }
  }
  
  public final void a12(class_939 paramclass_939)
  {
    super.a12(paramclass_939);
  }
  
  public final void b2(boolean paramBoolean)
  {
    if ((paramBoolean) && (!this.jdField_field_4_of_type_Class_453.field_5) && (!this.jdField_field_4_of_type_Class_332.field_5)) {
      this.jdField_field_4_of_type_Class_453.c2(true);
    }
    super.b2(paramBoolean);
  }
  
  public final void a15(class_941 paramclass_941)
  {
    if (a6().a25() == null) {
      return;
    }
    super.a15(paramclass_941);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_328
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */