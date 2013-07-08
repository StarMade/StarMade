import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.facedit.ElementEditorFrame;

public final class class_628
  implements ActionListener
{
  public class_628(ElementEditorFrame paramElementEditorFrame) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    ElementKeyMap.writeDocument(ElementEditorFrame.a2(this.field_911).field_949);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_628
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */