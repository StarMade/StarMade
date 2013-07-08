import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.JComboBox;

final class class_660
  implements ActionListener
{
  class_660(class_644 paramclass_644, JComboBox paramJComboBox, Field paramField) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      long l = Long.parseLong(this.jdField_field_927_of_type_JavaxSwingJComboBox.getSelectedItem().toString());
      this.jdField_field_927_of_type_JavaLangReflectField.setLong(class_644.a2(this.jdField_field_927_of_type_Class_644), l);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      (localObject = localIllegalArgumentException).printStackTrace();
      class_29.a12((Exception)localObject);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Object localObject;
      (localObject = localIllegalAccessException).printStackTrace();
      class_29.a12((Exception)localObject);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_660
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */