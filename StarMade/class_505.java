import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import org.schema.game.common.data.element.ElementInformation;

final class class_505
  implements ActionListener
{
  class_505(class_490 paramclass_490) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if ((paramActionEvent = class_490.a1(this.field_818).getSelectedValues()) != null) {
      for (int i = 0; i < paramActionEvent.length; i++)
      {
        Object localObject;
        if ((localObject = paramActionEvent[i]) != null) {
          class_490.a(this.field_818).b((ElementInformation)localObject);
        }
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_505
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */