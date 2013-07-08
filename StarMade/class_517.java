import java.io.PrintStream;
import java.lang.reflect.Field;
import javax.swing.JTextArea;
import org.schema.game.common.data.element.ElementInformation;

final class class_517
  implements class_678
{
  class_517(JTextArea paramJTextArea) {}
  
  public final void a(Field paramField, ElementInformation paramElementInformation)
  {
    paramField.set(paramElementInformation, this.field_170.getText());
    System.err.println("APPLIED TEXT AREA");
  }
  
  public final void b(Field paramField, ElementInformation paramElementInformation) {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_517
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */