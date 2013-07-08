import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

final class class_1332
  implements ActionListener
{
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      new File(class_1270.a2("StarMade"), "cred").delete();
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
 * Qualified Name:     class_1332
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */