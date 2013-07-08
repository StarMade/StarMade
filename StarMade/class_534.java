import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import org.schema.game.common.data.element.BlockLevel;
import org.schema.game.common.data.element.ElementInformation;

final class class_534
  implements ActionListener
{
  class_534(class_515 paramclass_515, ElementInformation paramElementInformation, JTextPane paramJTextPane) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if (class_515.a1(this.jdField_field_838_of_type_Class_515) > 0) {
      this.jdField_field_838_of_type_OrgSchemaGameCommonDataElementElementInformation.setLevel(new BlockLevel(class_515.a1(this.jdField_field_838_of_type_Class_515), class_515.a3(this.jdField_field_838_of_type_Class_515).getValue()));
    } else {
      this.jdField_field_838_of_type_OrgSchemaGameCommonDataElementElementInformation.setLevel(null);
    }
    this.jdField_field_838_of_type_JavaxSwingJTextPane.setText(this.jdField_field_838_of_type_OrgSchemaGameCommonDataElementElementInformation.getLevel() != null ? this.jdField_field_838_of_type_OrgSchemaGameCommonDataElementElementInformation.getLevel().toString() : "   -   ");
    this.jdField_field_838_of_type_Class_515.dispose();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_534
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */