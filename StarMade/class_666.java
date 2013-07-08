import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.JFrame;
import javax.swing.JTextPane;

final class class_666
  implements ActionListener
{
  class_666(class_644 paramclass_644, JFrame paramJFrame, Field paramField, JTextPane paramJTextPane, class_642 paramclass_642) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      paramActionEvent = null;
      new class_685(this.jdField_field_939_of_type_JavaxSwingJFrame, class_644.a2(this.jdField_field_939_of_type_Class_644), new class_671(this)).setVisible(true);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      (paramActionEvent = localIllegalArgumentException).printStackTrace();
      class_29.a12(paramActionEvent);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_666
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */