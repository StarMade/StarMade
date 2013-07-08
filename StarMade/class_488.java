import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import org.schema.game.common.data.element.ElementInformation;

final class class_488
  implements ActionListener
{
  class_488(class_490 paramclass_490, Set paramSet1, Set paramSet2) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    this.jdField_field_803_of_type_JavaUtilSet.clear();
    this.jdField_field_803_of_type_JavaUtilSet.addAll(class_490.a(this.jdField_field_803_of_type_Class_490).a2());
    this.field_804.clear();
    paramActionEvent = this.jdField_field_803_of_type_JavaUtilSet.iterator();
    while (paramActionEvent.hasNext())
    {
      ElementInformation localElementInformation = (ElementInformation)paramActionEvent.next();
      this.field_804.add(Short.valueOf(localElementInformation.getId()));
    }
    this.jdField_field_803_of_type_Class_490.dispose();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_488
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */