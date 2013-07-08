import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

final class class_616
  implements ActionListener
{
  class_616(class_610 paramclass_610, JLabel paramJLabel) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    paramActionEvent = 0;
    short[] arrayOfShort;
    int i = (arrayOfShort = ElementKeyMap.typeList()).length;
    for (int j = 0; j < i; j++)
    {
      Short localShort = Short.valueOf(arrayOfShort[j]);
      paramActionEvent = (short)Math.max(paramActionEvent, ElementKeyMap.getInfo(localShort.shortValue()).getBuildIconNum());
    }
    paramActionEvent = (short)(paramActionEvent + 1);
    class_610.c(this.jdField_field_900_of_type_Class_610, paramActionEvent);
    this.jdField_field_900_of_type_JavaxSwingJLabel.setText(String.valueOf(class_610.b1(this.jdField_field_900_of_type_Class_610)));
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_616
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */