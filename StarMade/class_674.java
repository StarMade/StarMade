import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import org.schema.game.common.data.element.ElementInformation;

final class class_674
  implements ActionListener
{
  class_674(class_644 paramclass_644, JFrame paramJFrame, JTextPane paramJTextPane) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      paramActionEvent = null;
      new class_515(this.jdField_field_943_of_type_JavaxSwingJFrame, class_644.a2(this.jdField_field_943_of_type_Class_644).getLevel(), class_644.a2(this.jdField_field_943_of_type_Class_644), this.jdField_field_943_of_type_JavaxSwingJTextPane).setVisible(true);
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
 * Qualified Name:     class_674
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */