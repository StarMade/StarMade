import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.facedit.ElementEditorFrame;
import org.schema.game.common.facedit.ElementTreePanel;

public final class class_626
  implements ActionListener
{
  public class_626(ElementEditorFrame paramElementEditorFrame) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    paramActionEvent = ElementEditorFrame.a1(this.field_910, this.field_910, "Open");
    ElementKeyMap.clear();
    ElementKeyMap.reinitializeData(paramActionEvent, null);
    paramActionEvent = new ElementTreePanel(this.field_910);
    ElementEditorFrame.a(this.field_910).setLeftComponent(new JScrollPane(paramActionEvent));
    ElementEditorFrame.a(this.field_910).setRightComponent(new JLabel("nothing selected"));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_626
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */