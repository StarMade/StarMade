import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import javax.swing.JComboBox;
import org.schema.game.common.data.element.annotation.Element;

final class class_656
  implements ActionListener
{
  class_656(class_644 paramclass_644, JComboBox paramJComboBox, Field paramField, Element paramElement, class_642 paramclass_642) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      paramActionEvent = Integer.parseInt(this.jdField_field_925_of_type_JavaxSwingJComboBox.getSelectedItem().toString());
      this.jdField_field_925_of_type_JavaLangReflectField.setInt(class_644.a2(this.jdField_field_925_of_type_Class_644), paramActionEvent);
      if (this.jdField_field_925_of_type_OrgSchemaGameCommonDataElementAnnotationElement.updateTextures()) {
        this.jdField_field_925_of_type_Class_642.a();
      }
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
 * Qualified Name:     class_656
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */