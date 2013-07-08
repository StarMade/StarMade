import java.io.PrintStream;
import org.lwjgl.input.Keyboard;

public abstract class class_15
  extends class_12
  implements class_1079, class_954
{
  public final class_1077 field_4;
  public final class_196 field_4;
  
  public class_15(class_371 paramclass_371, int paramInt, Object paramObject1, Object paramObject2)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Class_1077 = new class_1077(paramInt, this);
    this.jdField_field_4_of_type_Class_196 = new class_259(paramclass_371, this, paramObject1, paramObject2, this.jdField_field_4_of_type_Class_1077);
    this.jdField_field_4_of_type_Class_196.a141(this);
  }
  
  public class_15(class_371 paramclass_371, int paramInt, Object paramObject1, Object paramObject2, String paramString)
  {
    this(paramclass_371, paramInt, paramObject1, paramObject2);
    if ((paramString != null) && (paramString.length() > 0))
    {
      this.jdField_field_4_of_type_Class_1077.a9(paramString);
      this.jdField_field_4_of_type_Class_1077.e();
      this.jdField_field_4_of_type_Class_1077.g1();
    }
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      if (paramclass_1363.b29().equals("OK"))
      {
        System.err.println("OK");
        this.jdField_field_4_of_type_Class_1077.b();
      }
      if ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X")))
      {
        System.err.println("CANCEL");
        d();
      }
    }
  }
  
  public final class_1077 a8()
  {
    return this.jdField_field_4_of_type_Class_1077;
  }
  
  public void handleKeyEvent()
  {
    if (Keyboard.getEventKeyState())
    {
      if ((this.field_4) && (Keyboard.getEventKey() == 1))
      {
        d();
        return;
      }
      this.jdField_field_4_of_type_Class_1077.handleKeyEvent();
    }
  }
  
  public abstract boolean a7(String paramString);
  
  public void onTextEnter(String paramString, boolean paramBoolean)
  {
    if (a7(paramString)) {
      d();
    }
  }
  
  public final void a9(String paramString)
  {
    this.jdField_field_4_of_type_Class_196.a17(paramString);
  }
  
  public final void a10(class_956 paramclass_956)
  {
    this.jdField_field_4_of_type_Class_1077.a10(paramclass_956);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_15
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */