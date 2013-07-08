import com.eaio.uuid.UUIDGen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jasypt.util.text.BasicTextEncryptor;
import org.schema.game.common.api.ApiController;

final class class_1334
  implements ActionListener
{
  class_1334(class_1330 paramclass_1330) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    paramActionEvent = class_1330.a(this.field_1515).getText();
    Object localObject1 = new String(class_1330.a1(this.field_1515).getPassword());
    Object localObject2 = new class_719();
    try
    {
      ApiController.a((class_719)localObject2);
      ApiController.a4((class_719)localObject2, paramActionEvent, (String)localObject1);
      org.schema.game.common.Starter.field_2076 = (class_719)localObject2;
      if (class_1330.a2(this.field_1515).isSelected())
      {
        paramActionEvent = new class_1272(paramActionEvent, (String)localObject1);
        (localObject1 = new BasicTextEncryptor()).setPassword(UUIDGen.getMACAddress());
        localObject1 = ((BasicTextEncryptor)localObject1).encrypt(paramActionEvent.field_1457);
        localObject2 = new FileWriter(new File(class_1270.a2("StarMade"), "cred"));
        (localObject2 = new BufferedWriter((Writer)localObject2)).append(paramActionEvent.field_1458);
        ((BufferedWriter)localObject2).newLine();
        ((BufferedWriter)localObject2).append((CharSequence)localObject1);
        ((BufferedWriter)localObject2).flush();
        ((BufferedWriter)localObject2).close();
      }
      this.field_1515.dispose();
      return;
    }
    catch (Exception localException)
    {
      (paramActionEvent = localException).printStackTrace();
      class_29.a12(paramActionEvent);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1334
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */