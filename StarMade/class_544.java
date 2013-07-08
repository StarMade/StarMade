import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

final class class_544
  implements ChangeListener
{
  class_544(class_550 paramclass_550) {}
  
  public final void stateChanged(ChangeEvent paramChangeEvent)
  {
    if ((!jdField_field_846_of_type_Boolean) && (class_550.b(this.jdField_field_846_of_type_Class_550) == null)) {
      throw new AssertionError();
    }
    class_550.b(this.jdField_field_846_of_type_Class_550).setText("Count " + String.valueOf(class_550.a3(this.jdField_field_846_of_type_Class_550).getValue()));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_544
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */