import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JOptionPane;

final class class_593
  implements ActionListener
{
  class_593(class_489 paramclass_489) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      if (JOptionPane.showOptionDialog(this.field_890, "Database Migration from v0.078 to v0.079+.\nThis updates only the current universe.\nOld catalog data will automatically update, when you import!\nBe sure to have a Backup!\n(All you data got backed up on Version update)", "Migration", 0, 1, null, new String[] { "Ok", "Cancel" }, "Ok") == 1) {
        return;
      }
      JOptionPane.showMessageDialog(this.field_890, "Database Migration in Progress.\nThis may take a few minutes.\nPlease do not interrupt this progress!");
      class_1216.field_1429.a8();
      JOptionPane.showOptionDialog(this.field_890, "Database Migration was successfull.\nYou can now play the game. Have fun!", "Migration completed", 0, 1, null, new String[] { "Ok" }, "Ok");
      return;
    }
    catch (IOException localIOException)
    {
      (paramActionEvent = localIOException).printStackTrace();
      class_29.a12(paramActionEvent);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_593
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */