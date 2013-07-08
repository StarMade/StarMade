import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ship.ShipElement;

final class class_606
  implements ActionListener
{
  class_606(class_610 paramclass_610, JFrame paramJFrame, JLabel paramJLabel) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    paramActionEvent = new ElementInformation((short)0, "dummy", ShipElement.class, (short)0);
    new class_685(this.jdField_field_893_of_type_JavaxSwingJFrame, paramActionEvent, new class_604(this, paramActionEvent)).setVisible(true);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_606
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */