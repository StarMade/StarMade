import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.facedit.ElementEditorFrame;
import org.schema.game.common.facedit.ElementTreePanel;

public final class class_632
  implements ActionListener
{
  public class_632(ElementEditorFrame paramElementEditorFrame) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    ElementKeyMap.clear();
    paramActionEvent = new ElementTreePanel(this.field_913);
    ElementEditorFrame.a(this.field_913).setLeftComponent(new JScrollPane(paramActionEvent));
    ElementEditorFrame.a(this.field_913).setRightComponent(new JLabel("nothing selected"));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_632
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */