import java.lang.reflect.Field;
import javax.swing.JTextField;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.annotation.Element;

final class class_654
  implements class_678
{
  class_654(JTextField paramJTextField, Element paramElement) {}
  
  public final void a(Field paramField, ElementInformation paramElementInformation)
  {
    int i = Integer.parseInt(this.jdField_field_170_of_type_JavaxSwingJTextField.getText());
    if ((this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.from() != -1) && (this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.to() != -1)) {
      i = Math.max(this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.from(), Math.min(this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.to(), i));
    }
    paramField.setInt(paramElementInformation, i);
  }
  
  public final void b(Field paramField, ElementInformation paramElementInformation)
  {
    this.jdField_field_170_of_type_JavaxSwingJTextField.setText(String.valueOf(paramField.getInt(paramElementInformation)));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_654
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */