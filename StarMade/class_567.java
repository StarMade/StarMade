import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

final class class_567
  implements ChangeListener
{
  class_567(class_489 paramclass_489) {}
  
  public final void stateChanged(ChangeEvent paramChangeEvent)
  {
    class_489.a3(this.field_872).setEnabled(!class_489.a2(this.field_872).isSelected());
    class_489.b(this.field_872).setEnabled(class_489.a2(this.field_872).isSelected());
    if (class_489.a2(this.field_872).isSelected())
    {
      class_949.field_1217.a8("Single Player");
      return;
    }
    class_949.field_1217.a8("Multi Player");
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_567
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */