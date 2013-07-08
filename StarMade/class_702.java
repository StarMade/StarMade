import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import org.schema.game.common.data.element.BlockFactory;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.FactoryResource;

final class class_702
  implements ActionListener
{
  class_702(class_700 paramclass_700, Set paramSet, class_563 paramclass_563, ElementInformation paramElementInformation, class_532 paramclass_532) {}
  
  public final void actionPerformed(ActionEvent paramActionEvent)
  {
    this.jdField_field_970_of_type_JavaUtilSet.clear();
    this.jdField_field_970_of_type_JavaUtilSet.addAll(class_700.a(this.jdField_field_970_of_type_Class_700).a2());
    this.jdField_field_970_of_type_Class_563.jdField_field_869_of_type_JavaUtilArrayList.clear();
    this.jdField_field_970_of_type_Class_563.jdField_field_869_of_type_JavaUtilArrayList.addAll(this.jdField_field_970_of_type_JavaUtilSet);
    for (paramActionEvent = 0; paramActionEvent < this.jdField_field_970_of_type_Class_563.jdField_field_869_of_type_JavaUtilArrayList.size(); paramActionEvent++) {
      if (((class_561)this.jdField_field_970_of_type_Class_563.jdField_field_869_of_type_JavaUtilArrayList.get(paramActionEvent)).field_866.isEmpty())
      {
        this.jdField_field_970_of_type_Class_563.jdField_field_869_of_type_JavaUtilArrayList.remove(paramActionEvent);
        paramActionEvent--;
      }
    }
    ElementInformation localElementInformation = this.jdField_field_970_of_type_OrgSchemaGameCommonDataElementElementInformation;
    paramActionEvent = this.jdField_field_970_of_type_Class_563;
    BlockFactory localBlockFactory;
    (localBlockFactory = new BlockFactory()).enhancer = paramActionEvent.jdField_field_869_of_type_Short;
    localBlockFactory.output = new FactoryResource[paramActionEvent.jdField_field_869_of_type_JavaUtilArrayList.size()][];
    localBlockFactory.input = new FactoryResource[paramActionEvent.jdField_field_869_of_type_JavaUtilArrayList.size()][];
    for (int i = 0; i < paramActionEvent.jdField_field_869_of_type_JavaUtilArrayList.size(); i++)
    {
      localBlockFactory.input[i] = new FactoryResource[((class_561)paramActionEvent.jdField_field_869_of_type_JavaUtilArrayList.get(i)).field_866.size()];
      for (int j = 0; j < ((class_561)paramActionEvent.jdField_field_869_of_type_JavaUtilArrayList.get(i)).field_866.size(); j++) {
        localBlockFactory.input[i][j] = ((FactoryResource)((class_561)paramActionEvent.jdField_field_869_of_type_JavaUtilArrayList.get(i)).field_866.get(j));
      }
      localBlockFactory.output[i] = new FactoryResource[((class_561)paramActionEvent.jdField_field_869_of_type_JavaUtilArrayList.get(i)).field_867.size()];
      for (j = 0; j < ((class_561)paramActionEvent.jdField_field_869_of_type_JavaUtilArrayList.get(i)).field_867.size(); j++) {
        localBlockFactory.output[i][j] = ((FactoryResource)((class_561)paramActionEvent.jdField_field_869_of_type_JavaUtilArrayList.get(i)).field_867.get(j));
      }
    }
    localElementInformation.setFactory(localBlockFactory);
    this.jdField_field_970_of_type_Class_532.a();
    this.jdField_field_970_of_type_Class_700.dispose();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_702
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */