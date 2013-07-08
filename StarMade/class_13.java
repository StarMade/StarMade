import java.io.PrintStream;

public abstract class class_13
  extends class_12
{
  final class_196 field_4;
  
  public class_13(class_371 paramclass_371, Object paramObject1, Object paramObject2)
  {
    super(paramclass_371);
    this.field_4 = new class_196(paramclass_371, this, paramObject1, paramObject2);
    this.field_4.a141(this);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((paramclass_939.jdField_field_1163_of_type_Boolean) && (paramclass_939.jdField_field_1163_of_type_Int == 0))
    {
      if (paramclass_1363.b29().equals("OK"))
      {
        System.err.println("OK");
        b();
      }
      if ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X")))
      {
        System.err.println("CANCEL");
        d();
      }
    }
  }
  
  public void handleKeyEvent() {}
  
  public abstract void b();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_13
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */