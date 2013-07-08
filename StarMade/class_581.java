import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

final class class_581
  implements ComboBoxModel
{
  private String jdField_field_881_of_type_JavaLangString;
  
  private class_581(class_489 paramclass_489) {}
  
  public final void addListDataListener(ListDataListener paramListDataListener) {}
  
  public final Object getElementAt(int paramInt)
  {
    return ((class_899)class_489.a1(this.jdField_field_881_of_type_Class_489).get(paramInt)).jdField_field_1125_of_type_JavaLangString + ":" + ((class_899)class_489.a1(this.jdField_field_881_of_type_Class_489).get(paramInt)).jdField_field_1125_of_type_Int;
  }
  
  public final Object getSelectedItem()
  {
    return this.jdField_field_881_of_type_JavaLangString;
  }
  
  public final int getSize()
  {
    return class_489.a1(this.jdField_field_881_of_type_Class_489).size();
  }
  
  public final void removeListDataListener(ListDataListener paramListDataListener) {}
  
  public final void setSelectedItem(Object paramObject)
  {
    this.jdField_field_881_of_type_JavaLangString = ((String)paramObject);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_581
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */