import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public final class class_787
  extends Observable
  implements Observer
{
  private final class_748 jdField_field_1043_of_type_Class_748;
  public final ArrayList field_1043;
  public final ArrayList field_1044 = new ArrayList();
  public final ArrayList field_1045 = new ArrayList();
  private boolean jdField_field_1043_of_type_Boolean = true;
  private class_738 jdField_field_1043_of_type_Class_738;
  
  public class_787(class_748 paramclass_748)
  {
    this.jdField_field_1043_of_type_JavaUtilArrayList = new ArrayList();
    this.jdField_field_1043_of_type_Class_748 = paramclass_748;
  }
  
  public final void a()
  {
    if ((this.jdField_field_1043_of_type_Class_738 == null) && (((class_1072)this.jdField_field_1043_of_type_Class_748.getState()).a() != null))
    {
      this.jdField_field_1043_of_type_Class_738 = ((class_1072)this.jdField_field_1043_of_type_Class_748.getState()).a();
      this.jdField_field_1043_of_type_Class_738.addObserver(this);
    }
    if (this.jdField_field_1043_of_type_Class_738 == null) {
      return;
    }
    if (this.jdField_field_1043_of_type_Boolean)
    {
      class_787 localclass_787 = this;
      Object localObject = this.jdField_field_1043_of_type_Class_738.a101();
      localclass_787.jdField_field_1043_of_type_JavaUtilArrayList.clear();
      class_781 localclass_7812 = null;
      localclass_787.field_1044.clear();
      localclass_787.field_1045.clear();
      localObject = ((Collection)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        class_781 localclass_7811;
        localclass_7812 = localclass_7811 = (class_781)((Iterator)localObject).next();
        int i = 0;
        int j = 0;
        if (localclass_7812.field_139.equals(localclass_787.jdField_field_1043_of_type_Class_748.getName()))
        {
          i = 1;
          j = 1;
        }
        else if (localclass_7812.a7())
        {
          i = 1;
        }
        else
        {
          class_773 localclass_773;
          if ((localclass_7812.b2()) && ((localclass_773 = ((class_1039)localclass_787.jdField_field_1043_of_type_Class_748.getState()).a().a156(localclass_787.jdField_field_1043_of_type_Class_748.getId())) != null) && (localclass_773.a162().keySet().contains(localclass_7812.field_139))) {
            i = 1;
          }
        }
        if (i != 0) {
          localclass_787.jdField_field_1043_of_type_JavaUtilArrayList.add(localclass_7811);
        }
        if (j != 0) {
          localclass_787.field_1044.add(localclass_7811);
        }
        localclass_787.field_1045.add(localclass_7811);
      }
      if (localclass_787.jdField_field_1043_of_type_Class_748.a7()) {
        ((class_371)localclass_787.jdField_field_1043_of_type_Class_748.getState()).e1();
      }
      localclass_787.setChanged();
      localclass_787.notifyObservers();
      this.jdField_field_1043_of_type_Boolean = false;
    }
  }
  
  public final void b()
  {
    if (this.jdField_field_1043_of_type_Class_738 != null) {
      this.jdField_field_1043_of_type_Class_738.deleteObserver(this);
    }
  }
  
  public final void update(Observable paramObservable, Object paramObject)
  {
    this.jdField_field_1043_of_type_Boolean = true;
  }
  
  public final ArrayList a1()
  {
    return this.field_1044;
  }
  
  public final ArrayList b1()
  {
    return this.field_1045;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_787
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */