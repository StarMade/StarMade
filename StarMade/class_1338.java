import java.util.ArrayList;
import java.util.Collections;

final class class_1338
  implements Runnable
{
  class_1338(class_1336 paramclass_1336, ArrayList paramArrayList, class_371 paramclass_371) {}
  
  public final void run()
  {
    class_1336.a2(this.jdField_field_1519_of_type_Class_1336).clear();
    for (int i = 0; i < this.jdField_field_1519_of_type_JavaUtilArrayList.size(); i++)
    {
      class_590 localclass_590 = new class_590((class_758)this.jdField_field_1519_of_type_JavaUtilArrayList.get(i), class_1336.a3(this.jdField_field_1519_of_type_Class_1336), this.jdField_field_1519_of_type_Class_371);
      class_1336.a2(this.jdField_field_1519_of_type_Class_1336).add(localclass_590);
    }
    Collections.sort(class_1336.a2(this.jdField_field_1519_of_type_Class_1336));
    this.jdField_field_1519_of_type_Class_1336.fireTableDataChanged();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1338
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */