import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JList;
import org.schema.game.common.data.element.FactoryResource;

final class class_552
  implements ActionListener
{
  class_552(class_526 paramclass_526) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    for (Object localObject : class_526.a1(this.field_858).getSelectedValues()) {
      if (paramActionEvent != null) {
        class_526.a(this.field_858).b((FactoryResource)localObject);
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_552
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */