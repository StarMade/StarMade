import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.AbstractListModel;

public final class class_568
  extends AbstractListModel
{
  private class_371 jdField_field_873_of_type_Class_371;
  private final ArrayList jdField_field_873_of_type_JavaUtilArrayList = new ArrayList();
  private static final long serialVersionUID = -1793500436899220631L;
  
  public class_568(class_371 paramclass_371)
  {
    this.jdField_field_873_of_type_Class_371 = paramclass_371;
    fireContentsChanged(this, 0, paramclass_371.a45().a101().size());
  }
  
  public final Object getElementAt(int paramInt)
  {
    return this.jdField_field_873_of_type_JavaUtilArrayList.get(paramInt);
  }
  
  public final int getSize()
  {
    return this.jdField_field_873_of_type_JavaUtilArrayList.size();
  }
  
  public final void a()
  {
    this.jdField_field_873_of_type_JavaUtilArrayList.clear();
    this.jdField_field_873_of_type_JavaUtilArrayList.addAll(this.jdField_field_873_of_type_Class_371.a45().a101());
    Collections.sort(this.jdField_field_873_of_type_JavaUtilArrayList, new class_570());
    fireContentsChanged(this, 0, this.jdField_field_873_of_type_Class_371.a45().a101().size());
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_568
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */