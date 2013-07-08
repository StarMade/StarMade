import java.io.PrintStream;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

final class class_857
  extends class_12
{
  private boolean field_5;
  
  class_857(class_843 paramclass_843, class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void a(class_1363 paramclass_1363, class_939 paramclass_939)
  {
    if ((Mouse.isButtonDown(0)) && (!this.field_5) && ((paramclass_1363.b29().equals("CANCEL")) || (paramclass_1363.b29().equals("X"))))
    {
      System.err.println("CANCEL");
      d();
    }
    this.field_5 = Mouse.isButtonDown(0);
  }
  
  public final class_1363 a3()
  {
    return class_843.a116(this.field_4);
  }
  
  public final void handleKeyEvent()
  {
    if (Keyboard.getEventKeyState())
    {
      if (Keyboard.getEventKey() == 1)
      {
        d();
        return;
      }
      if (!class_949.field_1216.b1())
      {
        Keyboard.getEventKey();
        this.field_4.c1();
      }
      class_843.a117(this.field_4).a7(Keyboard.getEventKey());
      class_367.b();
      d();
    }
  }
  
  protected final void e()
  {
    class_843.a118(this.field_4, new class_196(this.field_4, this, "Assign New Key to " + class_843.a117(this.field_4).a4(), "Press a Key to assign it to \n\n<" + class_843.a117(this.field_4).a4() + "> \n\nor press ESC to cancel."));
    class_843.a116(this.field_4).e();
    class_843.a116(this.field_4).a141(this);
  }
  
  public final boolean a1()
  {
    return false;
  }
  
  public final void a2()
  {
    class_843.d1();
    class_843.a119(System.currentTimeMillis());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_857
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */