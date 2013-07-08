import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JOptionPane;
import org.schema.game.common.Starter;

final class class_510
  implements ActionListener
{
  class_510(class_489 paramclass_489) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    paramActionEvent = new Object[] { "BackUp & Reset", "Reset", "Cancel" };
    switch (JOptionPane.showOptionDialog(this.field_820, "Reset Universe. Should be done if game mode will change.\nWARNING: deleting without backup CANNOT be undone", "Reset Universe", 1, 3, null, paramActionEvent, paramActionEvent[2]))
    {
    case 0: 
      try
      {
        Starter.b();
      }
      catch (IOException localIOException)
      {
        (paramActionEvent = localIOException).printStackTrace();
        class_29.a12(paramActionEvent);
      }
      System.out.println("RESETTING DB");
      break;
    case 1: 
      Starter.c();
    }
    System.out.println("Creating empty database");
    new File(class_1041.field_144).mkdirs();
    new File(class_1041.field_149).mkdirs();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_510
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */