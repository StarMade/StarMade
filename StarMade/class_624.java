import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.facedit.ElementEditorFrame;
import org.schema.game.common.facedit.ElementTreePanel;

public final class class_624
  implements ActionListener
{
  public class_624(ElementEditorFrame paramElementEditorFrame) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if (ElementEditorFrame.a3(this.field_909) != null)
    {
      paramActionEvent = new Object[] { "Ok", "Cancel" };
      Object localObject = "Critical Error";
      (localObject = new JFrame((String)localObject)).setUndecorated(true);
      ((JFrame)localObject).setVisible(true);
      Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
      ((JFrame)localObject).setLocation(localDimension.width / 2, localDimension.height / 2);
      switch (JOptionPane.showOptionDialog((Component)localObject, "Are you sure you want to remove " + ElementEditorFrame.a3(this.field_909).getName() + "?", "Confirm Remove", 2, 2, null, paramActionEvent, paramActionEvent[1]))
      {
      case 0: 
        ElementKeyMap.removeFromExisting(ElementEditorFrame.a3(this.field_909));
        paramActionEvent = new ElementTreePanel(this.field_909);
        ElementEditorFrame.a(this.field_909).setLeftComponent(new JScrollPane(paramActionEvent));
        ElementEditorFrame.a(this.field_909).setRightComponent(new JLabel("nothing selected"));
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_624
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */