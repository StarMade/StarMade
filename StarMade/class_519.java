import java.lang.reflect.Field;
import javax.swing.JTextField;
import org.schema.game.common.data.element.ElementInformation;

final class class_519
  implements class_678
{
  class_519(JTextField paramJTextField) {}
  
  public final void a(Field paramField, ElementInformation paramElementInformation)
  {
    paramField.set(paramElementInformation, this.field_170.getText());
  }
  
  public final void b(Field paramField, ElementInformation paramElementInformation)
  {
    this.field_170.setText(paramField.get(paramElementInformation).toString());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_519
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */