import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import org.schema.game.common.controller.database.DatabaseIndex;

final class class_508
  implements ActionListener
{
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      (paramActionEvent = new DatabaseIndex()).a13("SELECT * FROM ENTITIES");
      paramActionEvent.d();
      return;
    }
    catch (SQLException localSQLException)
    {
      localSQLException;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_508
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */