import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.AbstractListModel;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.objects.Sendable;

public final class class_1289
  extends AbstractListModel
{
  private ArrayList jdField_field_1469_of_type_JavaUtilArrayList = new ArrayList();
  private class_371 jdField_field_1469_of_type_Class_371;
  
  public class_1289(class_371 paramclass_371)
  {
    this.jdField_field_1469_of_type_Class_371 = paramclass_371;
    a();
  }
  
  public final void a()
  {
    this.jdField_field_1469_of_type_JavaUtilArrayList.clear();
    Iterator localIterator = this.jdField_field_1469_of_type_Class_371.getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
    while (localIterator.hasNext())
    {
      Sendable localSendable = (Sendable)localIterator.next();
      this.jdField_field_1469_of_type_JavaUtilArrayList.add(localSendable);
    }
    Collections.sort(this.jdField_field_1469_of_type_JavaUtilArrayList, new class_1291());
    fireContentsChanged(this, 0, this.jdField_field_1469_of_type_JavaUtilArrayList.size());
  }
  
  public final Object getElementAt(int paramInt)
  {
    try
    {
      return this.jdField_field_1469_of_type_JavaUtilArrayList.get(paramInt);
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      localIndexOutOfBoundsException;
    }
    return null;
  }
  
  public final int getSize()
  {
    return this.jdField_field_1469_of_type_JavaUtilArrayList.size();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1289
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */