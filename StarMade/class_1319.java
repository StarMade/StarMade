import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JList;
import org.schema.game.common.staremote.Staremote;

final class class_1319
  implements ActionListener
{
  class_1319(JList paramJList, JFrame paramJFrame, Staremote paramStaremote) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if ((paramActionEvent = this.jdField_field_1499_of_type_JavaxSwingJList.getSelectedValue()) != null)
    {
      paramActionEvent = (class_1315)paramActionEvent;
      this.jdField_field_1499_of_type_JavaxSwingJFrame.dispose();
      this.jdField_field_1499_of_type_OrgSchemaGameCommonStaremoteStaremote.a2(paramActionEvent);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1319
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */