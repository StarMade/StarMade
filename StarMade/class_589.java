import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

final class class_589
  implements ActionListener
{
  class_589(class_489 paramclass_489, JCheckBoxMenuItem paramJCheckBoxMenuItem) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if (this.jdField_field_886_of_type_JavaxSwingJCheckBoxMenuItem.isSelected())
    {
      paramActionEvent = new class_583();
      class_489.a5(this.jdField_field_886_of_type_Class_489).setViewportView(paramActionEvent);
      paramActionEvent.setBorder(new TitledBorder(null, "Settings", 4, 2, null, null));
      return;
    }
    paramActionEvent = new class_585();
    class_489.a5(this.jdField_field_886_of_type_Class_489).setViewportView(paramActionEvent);
    paramActionEvent.setBorder(new TitledBorder(null, "Settings", 4, 2, null, null));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_589
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */