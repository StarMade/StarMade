import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.Set;
import javax.swing.JFrame;

final class class_669
  implements ActionListener
{
  class_669(class_644 paramclass_644, JFrame paramJFrame, Field paramField) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      paramActionEvent = null;
      new class_490(this.jdField_field_941_of_type_JavaxSwingJFrame, (Set)this.jdField_field_941_of_type_JavaLangReflectField.get(class_644.a2(this.jdField_field_941_of_type_Class_644))).setVisible(true);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      (paramActionEvent = localIllegalArgumentException).printStackTrace();
      class_29.a12(paramActionEvent);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      (paramActionEvent = localIllegalAccessException).printStackTrace();
      class_29.a12(paramActionEvent);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_669
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */