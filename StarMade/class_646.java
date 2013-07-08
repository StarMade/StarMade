import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.facedit.ElementEditorFrame;
import org.schema.game.common.facedit.ElementTreePanel;

final class class_646
  implements class_684
{
  class_646(class_636 paramclass_636) {}
  
  public final void a(ElementInformation paramElementInformation)
  {
    paramElementInformation = new ElementTreePanel(this.field_169.field_915);
    ElementEditorFrame.a(this.field_169.field_915).setLeftComponent(new JScrollPane(paramElementInformation));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_646
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */