import java.io.PrintStream;
import org.lwjgl.input.Keyboard;

public abstract class class_14
  extends class_12
  implements class_1079, class_954
{
  private final class_951 jdField_field_4_of_type_Class_951;
  private final class_196 jdField_field_4_of_type_Class_196;
  
  public class_14(class_371 paramclass_371, int paramInt, Object paramObject1, Object paramObject2)
  {
    super(paramclass_371);
    this.jdField_field_4_of_type_Class_951 = new class_951(140, paramInt, this);
    this.jdField_field_4_of_type_Class_196 = new class_263(paramclass_371, this, paramObject1, paramObject2, this.jdField_field_4_of_type_Class_951);
    this.jdField_field_4_of_type_Class_196.a141(this);
  }
  
  public class_14(class_371 paramclass_371, int paramInt, Object paramObject1, Object paramObject2, String paramString)
  {
    this(paramclass_371, paramInt, paramObject1, paramObject2);
    if ((paramString != null) && (paramString.length() > 0))
    {
      this.jdField_field_4_of_type_Class_951.a9(paramString);
      this.jdField_field_4_of_type_Class_951.e();
      this.jdField_field_4_of_type_Class_951.g1();
    }
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      if (paramclass_1363.b29().equals("OK"))
      {
        System.err.println("OK");
        this.jdField_field_4_of_type_Class_951.b();
      }
      if ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X")))
      {
        System.err.println("CANCEL");
        d();
      }
    }
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
      this.jdField_field_4_of_type_Class_951.handleKeyEvent();
    }
  }
  
  public abstract boolean a7(String paramString);
  
  public void onTextEnter(String paramString, boolean paramBoolean)
  {
    if (a7(paramString)) {
      d();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_14
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */