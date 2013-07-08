import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;

final class class_623
  implements ActionListener
{
  class_623(class_610 paramclass_610, JFrame paramJFrame, class_684 paramclass_684) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    if ((class_610.c1(this.jdField_field_908_of_type_Class_610) < 0) || (class_610.a4(this.jdField_field_908_of_type_Class_610).length() == 0) || (class_610.a1(this.jdField_field_908_of_type_Class_610) < 0) || (class_610.b1(this.jdField_field_908_of_type_Class_610) < 0) || (class_610.a6(this.jdField_field_908_of_type_Class_610) == null))
    {
      JOptionPane.showMessageDialog(this.jdField_field_908_of_type_JavaxSwingJFrame, "Every field in this dialog\nhas to be filled.", "Error", 0);
      return;
    }
    (paramActionEvent = new ElementInformation(class_610.c1(this.jdField_field_908_of_type_Class_610), class_610.a4(this.jdField_field_908_of_type_Class_610), class_610.a6(this.jdField_field_908_of_type_Class_610), class_610.a1(this.jdField_field_908_of_type_Class_610))).setBuildIconNum(class_610.b1(this.jdField_field_908_of_type_Class_610));
    paramActionEvent.setIndividualSides(class_610.a7(this.jdField_field_908_of_type_Class_610));
    try
    {
      ElementKeyMap.addInformationToExisting(paramActionEvent);
      this.jdField_field_908_of_type_Class_610.dispose();
      this.jdField_field_908_of_type_Class_684.a(paramActionEvent);
      return;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      (paramActionEvent = localParserConfigurationException).printStackTrace();
      class_29.a12(paramActionEvent);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_623
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */