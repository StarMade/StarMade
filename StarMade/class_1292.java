import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.schema.game.server.data.admin.AdminCommands;

final class class_1292
  implements ActionListener
{
  class_1292(class_1290 paramclass_1290, class_1316 paramclass_1316, class_371 paramclass_371) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      paramActionEvent = this.jdField_field_1470_of_type_Class_1316.a();
      this.jdField_field_1470_of_type_Class_371.a4().a23(AdminCommands.field_1948, new Object[] { Integer.valueOf(paramActionEvent.field_475), Integer.valueOf(paramActionEvent.field_476), Integer.valueOf(paramActionEvent.field_477) });
    }
    catch (IOException localIOException)
    {
      localIOException;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException;
    }
    this.jdField_field_1470_of_type_Class_1290.dispose();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1292
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */