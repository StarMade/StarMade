import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import org.schema.game.common.Starter;

final class class_1335
  implements ActionListener
{
  class_1335(class_1347 paramclass_1347) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if (class_1347.a1(this.field_1516).getText().length() > 0)
    {
      Starter.b1(Starter.field_2076 = class_1347.a1(this.field_1516).getText());
    }
    else
    {
      Starter.field_2076 = "";
      Starter.b1("");
    }
    this.field_1516.dispose();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1335
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */