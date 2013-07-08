import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JRadioButton;
import org.schema.game.server.data.admin.AdminCommands;

final class class_1346
  implements ActionListener
{
  class_1346(class_1344 paramclass_1344, JRadioButton paramJRadioButton1, class_773 paramclass_773, JRadioButton paramJRadioButton2, JRadioButton paramJRadioButton3) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      if (this.jdField_field_1525_of_type_JavaxSwingJRadioButton.isSelected())
      {
        class_1344.a1(this.jdField_field_1525_of_type_Class_1344).a4().a23(AdminCommands.field_2011, new Object[] { Integer.valueOf(class_1344.a(this.jdField_field_1525_of_type_Class_1344).a3()), Integer.valueOf(this.jdField_field_1525_of_type_Class_773.a3()), "enemy" });
        return;
      }
      if (this.field_1526.isSelected())
      {
        class_1344.a1(this.jdField_field_1525_of_type_Class_1344).a4().a23(AdminCommands.field_2011, new Object[] { Integer.valueOf(class_1344.a(this.jdField_field_1525_of_type_Class_1344).a3()), Integer.valueOf(this.jdField_field_1525_of_type_Class_773.a3()), "neutral" });
        return;
      }
      if (this.field_1527.isSelected()) {
        class_1344.a1(this.jdField_field_1525_of_type_Class_1344).a4().a23(AdminCommands.field_2011, new Object[] { Integer.valueOf(class_1344.a(this.jdField_field_1525_of_type_Class_1344).a3()), Integer.valueOf(this.jdField_field_1525_of_type_Class_773.a3()), "ally" });
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1346
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */