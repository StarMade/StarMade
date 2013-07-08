import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.schema.game.common.updater.Launcher;
import org.schema.game.common.updater.MemorySettings;

public final class class_1219
  implements ActionListener
{
  public class_1219(Launcher paramLauncher) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if (((paramActionEvent = (String)JOptionPane.showInputDialog(this.field_1433, "Please enter a port to start the server on", "Port", -1, null, null, String.valueOf(class_1201.field_1420))) != null) && (paramActionEvent.length() > 0)) {
      try
      {
        paramActionEvent = 0;
        class_1201.field_1420 = Integer.parseInt(paramActionEvent);
        JOptionPane.showMessageDialog(this.field_1433, "Saving Setting: Port set to " + class_1201.field_1420 + ".", "Port", 1);
        MemorySettings.b();
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        JOptionPane.showMessageDialog(this.field_1433, "Port must be a number", "Port set error", 0);
        return;
      }
      catch (IOException localIOException)
      {
        paramActionEvent = null;
        localIOException.printStackTrace();
      }
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1219
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */