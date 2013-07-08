import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

final class class_614
  implements ActionListener
{
  class_614(class_610 paramclass_610, JFrame paramJFrame, JLabel paramJLabel) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if (((paramActionEvent = (String)JOptionPane.showInputDialog(this.jdField_field_899_of_type_JavaxSwingJFrame, "Pick a Name", "Pick Name", -1, null, null, null)) != null) && (paramActionEvent.length() > 0))
    {
      class_610.a3(this.jdField_field_899_of_type_Class_610, paramActionEvent.trim());
      this.jdField_field_899_of_type_JavaxSwingJLabel.setText(class_610.a4(this.jdField_field_899_of_type_Class_610));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_614
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */