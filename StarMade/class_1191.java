import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.schema.game.common.updater.MemorySettings;

public final class class_1191
  implements ActionListener
{
  public class_1191(MemorySettings paramMemorySettings) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      if (MemorySettings.a())
      {
        class_1201.field_1411 = Integer.parseInt(MemorySettings.a2(this.field_1398).getText());
        class_1201.field_1412 = Integer.parseInt(MemorySettings.b1(this.field_1398).getText());
        class_1201.field_1413 = Integer.parseInt(MemorySettings.c(this.field_1398).getText());
      }
      else
      {
        class_1201.field_1414 = Integer.parseInt(MemorySettings.a2(this.field_1398).getText());
        class_1201.field_1415 = Integer.parseInt(MemorySettings.b1(this.field_1398).getText());
        class_1201.field_1416 = Integer.parseInt(MemorySettings.c(this.field_1398).getText());
      }
      class_1201.field_1417 = Integer.parseInt(MemorySettings.d(this.field_1398).getText());
      class_1201.field_1418 = Integer.parseInt(MemorySettings.e(this.field_1398).getText());
      class_1201.field_1419 = Integer.parseInt(MemorySettings.f(this.field_1398).getText());
      try
      {
        MemorySettings.b();
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        JOptionPane.showOptionDialog(new JPanel(), "Settings applied but failed to save for next session", "ERROR", 0, 0, null, null, null);
      }
      this.field_1398.dispose();
      return;
    }
    catch (Exception localException2)
    {
      localException2.printStackTrace();
      JOptionPane.showOptionDialog(new JPanel(), "Please only use numbers", "ERROR", 0, 0, null, null, null);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1191
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */