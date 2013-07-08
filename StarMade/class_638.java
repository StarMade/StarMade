import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.facedit.ElementEditorFrame;

public final class class_638
  implements ActionListener
{
  public class_638(ElementEditorFrame paramElementEditorFrame) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if ((paramActionEvent = ElementEditorFrame.a1(this.field_916, this.field_916, "Save As...")) != null) {
      ElementKeyMap.writeDocument(paramActionEvent);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_638
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */