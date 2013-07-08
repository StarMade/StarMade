import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

final class class_1299
  implements ListSelectionListener
{
  class_1299(class_1313 paramclass_1313, JButton paramJButton, JList paramJList) {}
  
  public final void valueChanged(ListSelectionEvent paramListSelectionEvent)
  {
    this.jdField_field_1476_of_type_JavaxSwingJButton.setEnabled(this.jdField_field_1476_of_type_JavaxSwingJList.getSelectedIndex() >= 0);
    class_1313.a(this.jdField_field_1476_of_type_Class_1313).setEnabled(this.jdField_field_1476_of_type_JavaxSwingJList.getSelectedIndex() >= 0);
    class_1313.b(this.jdField_field_1476_of_type_Class_1313).setEnabled(this.jdField_field_1476_of_type_JavaxSwingJList.getSelectedIndex() >= 0);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1299
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */