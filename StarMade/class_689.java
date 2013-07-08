import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import org.schema.game.common.data.element.ElementInformation;

final class class_689
  implements ActionListener
{
  class_689(class_685 paramclass_685, ElementInformation paramElementInformation, class_532 paramclass_532) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    int i;
    if ((i = (paramActionEvent = (class_676)class_685.a1(this.jdField_field_955_of_type_Class_685).getSelectedComponent()).a()) > 0)
    {
      i += (paramActionEvent.b() << 8);
      this.jdField_field_955_of_type_OrgSchemaGameCommonDataElementElementInformation.textureId = ((short)i);
      this.jdField_field_955_of_type_OrgSchemaGameCommonDataElementElementInformation.setIndividualSides(class_685.a2(this.jdField_field_955_of_type_Class_685).getSelectedItem() != null ? ((Integer)class_685.a2(this.jdField_field_955_of_type_Class_685).getSelectedItem()).intValue() : 1);
      this.jdField_field_955_of_type_Class_532.a();
      this.jdField_field_955_of_type_Class_685.dispose();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_689
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */