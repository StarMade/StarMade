import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

final class class_618
  implements ActionListener
{
  class_618(class_610 paramclass_610, JFrame paramJFrame, JLabel paramJLabel) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if (((paramActionEvent = (String)JOptionPane.showInputDialog(this.jdField_field_901_of_type_JavaxSwingJFrame, "Enter an icon ID", "Pick ID", -1, null, null, null)) != null) && (paramActionEvent.length() > 0)) {
      try
      {
        paramActionEvent = Short.parseShort(paramActionEvent.trim());
        class_610.c(this.jdField_field_901_of_type_Class_610, paramActionEvent);
        this.jdField_field_901_of_type_JavaxSwingJLabel.setText(String.valueOf(class_610.b1(this.jdField_field_901_of_type_Class_610)));
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        paramActionEvent = null;
        localNumberFormatException.printStackTrace();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_618
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */