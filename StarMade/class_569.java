import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

final class class_569
  implements ActionListener
{
  class_569(class_489 paramclass_489, JFrame paramJFrame) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      class_489.a4(this.jdField_field_874_of_type_Class_489);
      return;
    }
    catch (Exception paramActionEvent)
    {
      JOptionPane.showOptionDialog(this.jdField_field_874_of_type_JavaxSwingJFrame, paramActionEvent.getMessage(), "Login Failed", 0, 0, null, new String[] { "Ok" }, "Ok");
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_569
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */