import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.schema.game.common.updater.Launcher;

public final class class_1213
  implements ActionListener
{
  public class_1213(Launcher paramLauncher) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    { "ham", "spam" }[2] = "yam";
    if (((paramActionEvent = (String)JOptionPane.showInputDialog(this.field_1427, "Mirror URL", "Mirror", -1, null, null, "")) != null) && (paramActionEvent.length() > 0))
    {
      class_1182.field_1392 = paramActionEvent;
      return;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1213
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */