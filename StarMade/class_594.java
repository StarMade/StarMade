import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.schema.game.server.data.admin.AdminCommands;

final class class_594
  implements ActionListener
{
  class_594(class_590 paramclass_590) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      class_590.a3(this.field_891).a4().a23(AdminCommands.field_2012, new Object[] { class_590.a2(this.field_891).field_136 });
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
 * Qualified Name:     class_594
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */