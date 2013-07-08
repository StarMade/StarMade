import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JTextField;
import org.schema.game.server.data.admin.AdminCommands;

final class class_1342
  implements ActionListener
{
  class_1342(class_1340 paramclass_1340, class_371 paramclass_371) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      this.jdField_field_1522_of_type_Class_371.a4().a23(AdminCommands.field_2003, new Object[] { class_1340.a(this.jdField_field_1522_of_type_Class_1340).getText().trim(), "no description" });
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
    this.jdField_field_1522_of_type_Class_1340.dispose();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1342
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */