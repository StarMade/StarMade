import java.lang.reflect.Field;
import javax.swing.JTextField;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.annotation.Element;

final class class_658
  implements class_678
{
  class_658(JTextField paramJTextField, Element paramElement) {}
  
  public final void a(Field paramField, ElementInformation paramElementInformation)
  {
    long l = Long.parseLong(this.jdField_field_170_of_type_JavaxSwingJTextField.getText());
    if ((this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.from() != -1) && (this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.to() != -1)) {
      l = Math.max(this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.from(), Math.min(this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.to(), l));
    }
    paramField.setLong(paramElementInformation, l);
  }
  
  public final void b(Field paramField, ElementInformation paramElementInformation)
  {
    this.jdField_field_170_of_type_JavaxSwingJTextField.setText(String.valueOf(paramField.getLong(paramElementInformation)));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_658
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */