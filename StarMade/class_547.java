import java.util.ArrayList;
import java.util.Collections;

final class class_547
  implements Runnable
{
  class_547(class_541 paramclass_541, ArrayList paramArrayList, class_371 paramclass_371) {}
  
  public final void run()
  {
    class_541.a2(this.jdField_field_852_of_type_Class_541).clear();
    for (int i = 0; i < this.jdField_field_852_of_type_JavaUtilArrayList.size(); i++)
    {
      class_527 localclass_527 = new class_527((class_781)this.jdField_field_852_of_type_JavaUtilArrayList.get(i), this.jdField_field_852_of_type_Class_371, this.jdField_field_852_of_type_Class_541);
      class_541.a2(this.jdField_field_852_of_type_Class_541).add(localclass_527);
    }
    Collections.sort(class_541.a2(this.jdField_field_852_of_type_Class_541));
    this.jdField_field_852_of_type_Class_541.fireTableDataChanged();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_547
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */