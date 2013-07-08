import org.lwjgl.input.Keyboard;

public final class class_24
  extends class_16
{
  public int field_4;
  private int field_5 = 3;
  
  public class_24(class_371 paramclass_371)
  {
    super(paramclass_371);
  }
  
  public final void handleKeyEvent()
  {
    super.handleKeyEvent();
    if (Keyboard.getEventKeyState()) {
      switch (Keyboard.getEventKey())
      {
      case 203: 
        this.field_4 -= 1;
        if (this.field_4 < 0)
        {
          this.field_4 = (this.field_5 - 1);
          return;
        }
        break;
      case 205: 
        this.field_4 = ((this.field_4 + 1) % this.field_5);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_24
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */