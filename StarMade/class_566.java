import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.schema.game.server.data.admin.AdminCommands;

final class class_566
  implements ActionListener
{
  class_566(class_371 paramclass_371, class_773 paramclass_773) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      this.jdField_field_871_of_type_Class_371.a4().a23(AdminCommands.field_2004, new Object[] { Integer.valueOf(this.jdField_field_871_of_type_Class_773.a3()) });
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
 * Qualified Name:     class_566
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */