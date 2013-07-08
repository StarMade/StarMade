import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

final class class_1265
  implements ActionListener
{
  class_1265(class_1271 paramclass_1271, JCheckBox paramJCheckBox1, int paramInt, JCheckBox paramJCheckBox2, JCheckBox paramJCheckBox3, JCheckBox paramJCheckBox4) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if (this.jdField_field_1442_of_type_JavaxSwingJCheckBox.isSelected()) {
      class_1271.a1(this.jdField_field_1442_of_type_Class_1271).a25()[this.jdField_field_1442_of_type_Int] |= 1L;
    }
    if (this.field_1443.isSelected()) {
      class_1271.a1(this.jdField_field_1442_of_type_Class_1271).a25()[this.jdField_field_1442_of_type_Int] |= 4L;
    }
    if (this.field_1444.isSelected()) {
      class_1271.a1(this.jdField_field_1442_of_type_Class_1271).a25()[this.jdField_field_1442_of_type_Int] |= 2L;
    }
    if (this.field_1445.isSelected()) {
      class_1271.a1(this.jdField_field_1442_of_type_Class_1271).a25()[this.jdField_field_1442_of_type_Int] |= 8L;
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1265
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */