import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

final class class_1301
  extends KeyAdapter
{
  class_1301(class_1303 paramclass_1303) {}
  
  public final void keyTyped(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getKeyCode() == 10) || (paramKeyEvent.getKeyChar() == '\n')) {
      class_1303.a(this.field_1478);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1301
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */