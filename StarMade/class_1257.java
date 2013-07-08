import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.AbstractListModel;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.Sendable;

public final class class_1257
  extends AbstractListModel
{
  private static final long serialVersionUID = -2709488666387980490L;
  private ArrayList jdField_field_1440_of_type_JavaUtilArrayList = new ArrayList();
  private class_371 jdField_field_1440_of_type_Class_371;
  
  public class_1257(class_371 paramclass_371)
  {
    this.jdField_field_1440_of_type_Class_371 = paramclass_371;
    a();
  }
  
  public final void a()
  {
    this.jdField_field_1440_of_type_JavaUtilArrayList.clear();
    Iterator localIterator = this.jdField_field_1440_of_type_Class_371.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
    while (localIterator.hasNext())
    {
      Sendable localSendable;
      if (((localSendable = (Sendable)localIterator.next()) instanceof class_748)) {
        this.jdField_field_1440_of_type_JavaUtilArrayList.add((class_748)localSendable);
      }
    }
    Collections.sort(this.jdField_field_1440_of_type_JavaUtilArrayList, new class_1255());
    fireContentsChanged(this, 0, this.jdField_field_1440_of_type_JavaUtilArrayList.size());
  }
  
  public final Object getElementAt(int paramInt)
  {
    try
    {
      return this.jdField_field_1440_of_type_JavaUtilArrayList.get(paramInt);
    }
    catch (Exception localException)
    {
      localException;
    }
    return "Exception";
  }
  
  public final int getSize()
  {
    return this.jdField_field_1440_of_type_JavaUtilArrayList.size();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1257
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */