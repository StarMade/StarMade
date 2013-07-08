import java.lang.reflect.Field;
import javax.swing.JTextField;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.annotation.Element;

final class class_662
  implements class_678
{
  class_662(JTextField paramJTextField, Element paramElement) {}
  
  public final void a(Field paramField, ElementInformation paramElementInformation)
  {
    short s = Short.parseShort(this.jdField_field_170_of_type_JavaxSwingJTextField.getText());
    if ((this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.from() != -1) && (this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.to() != -1)) {
      s = (short)Math.max(this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.from(), Math.min(this.jdField_field_170_of_type_OrgSchemaGameCommonDataElementAnnotationElement.to(), s));
    }
    paramField.setShort(paramElementInformation, s);
  }
  
  public final void b(Field paramField, ElementInformation paramElementInformation)
  {
    this.jdField_field_170_of_type_JavaxSwingJTextField.setText(String.valueOf(paramField.getShort(paramElementInformation)));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_662
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */