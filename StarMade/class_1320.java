import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JTextField;
import org.schema.game.server.data.admin.AdminCommands;

final class class_1320
  implements ActionListener
{
  class_1320(class_1314 paramclass_1314, class_371 paramclass_371) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      this.jdField_field_1500_of_type_Class_371.a4().a23(AdminCommands.field_1987, new Object[] { class_1314.a(this.jdField_field_1500_of_type_Class_1314).getText().trim(), "no description" });
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
    this.jdField_field_1500_of_type_Class_1314.dispose();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1320
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */