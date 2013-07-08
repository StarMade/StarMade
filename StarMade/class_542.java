import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import org.schema.game.common.data.element.FactoryResource;

final class class_542
  implements ActionListener
{
  class_542(class_550 paramclass_550, class_680 paramclass_680) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if ((class_550.a1(this.jdField_field_844_of_type_Class_550) > 0) && (((Integer)class_550.a3(this.jdField_field_844_of_type_Class_550).getValue()).intValue() > 0)) {
      this.jdField_field_844_of_type_Class_680.a1(new FactoryResource(((Integer)class_550.a3(this.jdField_field_844_of_type_Class_550).getValue()).intValue(), class_550.a1(this.jdField_field_844_of_type_Class_550)));
    }
    this.jdField_field_844_of_type_Class_550.dispose();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_542
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */