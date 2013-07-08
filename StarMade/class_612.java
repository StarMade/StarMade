import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.schema.game.common.data.element.ElementParser;

final class class_612
  implements ActionListener
{
  class_612(class_610 paramclass_610, JFrame paramJFrame, JLabel paramJLabel) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    paramActionEvent = ElementParser.getTypeStringArray();
    if (((paramActionEvent = (String)JOptionPane.showInputDialog(this.jdField_field_898_of_type_JavaxSwingJFrame, "Pick a category", "Pick Category", -1, null, paramActionEvent, paramActionEvent[0])) != null) && (paramActionEvent.length() > 0))
    {
      class_610.a5(this.jdField_field_898_of_type_Class_610, ElementParser.getTypeFromString(paramActionEvent));
      this.jdField_field_898_of_type_JavaxSwingJLabel.setText(paramActionEvent);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_612
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */