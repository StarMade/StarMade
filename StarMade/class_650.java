import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.JComboBox;

final class class_650
  implements ActionListener
{
  class_650(class_644 paramclass_644, Field paramField, JComboBox paramJComboBox) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      this.jdField_field_922_of_type_JavaLangReflectField.setBoolean(class_644.a2(this.jdField_field_922_of_type_Class_644), ((Boolean)this.jdField_field_922_of_type_JavaxSwingJComboBox.getSelectedItem()).booleanValue());
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
 * Qualified Name:     class_650
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */