import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.element.ControlElementMapper;
import org.schema.game.common.data.element.ElementCollection;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteIntegerArray;
import org.schema.schine.network.objects.remote.RemoteString;

public final class class_359
  implements class_80
{
  private String jdField_field_136_of_type_JavaLangString;
  private String field_139;
  public ConcurrentHashMap field_136;
  public ConcurrentHashMap field_139;
  private class_748 jdField_field_136_of_type_Class_748;
  
  public class_359(class_748 paramclass_748, String paramString)
  {
    this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap = new ConcurrentHashMap();
    this.jdField_field_139_of_type_JavaUtilConcurrentConcurrentHashMap = new ConcurrentHashMap();
    this.jdField_field_139_of_type_JavaLangString = paramclass_748.getUniqueIdentifier();
    this.jdField_field_136_of_type_Class_748 = paramclass_748;
    this.jdField_field_136_of_type_JavaLangString = paramString;
  }
  
  public final boolean equals(Object paramObject)
  {
    return (this.jdField_field_136_of_type_JavaLangString.equals(((class_359)paramObject).jdField_field_136_of_type_JavaLangString)) && (this.jdField_field_139_of_type_JavaLangString.equals(((class_359)paramObject).jdField_field_139_of_type_JavaLangString));
  }
  
  public final void fromTagStructure(class_69 paramclass_69) {}
  
  public final class_48 a17(int paramInt)
  {
    return (class_48)this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap.get(Integer.valueOf(paramInt));
  }
  
  public final String getUniqueIdentifier()
  {
    return null;
  }
  
  public final boolean a18(int paramInt)
  {
    return this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap.containsKey(Integer.valueOf(paramInt));
  }
  
  public final int hashCode()
  {
    return this.jdField_field_136_of_type_JavaLangString.hashCode() + this.jdField_field_139_of_type_JavaLangString.hashCode();
  }
  
  public final boolean a19(class_48 paramclass_48)
  {
    return this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap.containsValue(paramclass_48);
  }
  
  public final boolean isVolatile()
  {
    return false;
  }
  
  public final boolean a20(int paramInt, class_48 paramclass_48, boolean paramBoolean)
  {
    System.err.println("[SHIP][KEYCONFIG] ASSIGNED Key " + paramInt + " to " + paramclass_48 + " on " + this.jdField_field_136_of_type_JavaLangString + " on " + this.jdField_field_136_of_type_Class_748.getState());
    Object localObject = (class_48)this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap.get(Integer.valueOf(paramInt));
    if (paramclass_48.equals(localObject)) {
      return false;
    }
    if (localObject != null) {
      this.jdField_field_139_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localObject);
    }
    if ((localObject = (Integer)this.jdField_field_139_of_type_JavaUtilConcurrentConcurrentHashMap.get(paramclass_48)) != null) {
      this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localObject);
    }
    this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap.put(Integer.valueOf(paramInt), paramclass_48);
    this.jdField_field_139_of_type_JavaUtilConcurrentConcurrentHashMap.put(paramclass_48, Integer.valueOf(paramInt));
    if (paramBoolean) {
      a24(paramInt, paramclass_48, true);
    }
    return true;
  }
  
  public final void a21(class_371 paramclass_371, class_846 paramclass_846)
  {
    paramclass_371 = paramclass_371.a25();
    int i = 0;
    if (paramclass_371 != null)
    {
      class_48 localclass_48 = null;
      long l1 = ElementCollection.getIndex(new class_48(8, 8, 8));
      paramclass_371 = paramclass_371.getControlElementMap().getControllingMap().keySet().iterator();
      while (paramclass_371.hasNext())
      {
        long l2;
        if ((l2 = ((Long)paramclass_371.next()).longValue()) != l1)
        {
          localclass_48 = ElementCollection.getPosFromIndex(l2, new class_48());
          if (paramclass_846.field_1094.contains(localclass_48)) {
            a20(i, localclass_48, true);
          }
          if (i > 9) {
            break;
          }
          i++;
        }
      }
      return;
    }
    System.err.println("[ShipKeyConfig] WARNING: no ship for state but tried to update assignments");
  }
  
  public final class_48 a22(int paramInt, boolean paramBoolean)
  {
    class_48 localclass_48;
    if ((localclass_48 = (class_48)this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap.get(Integer.valueOf(paramInt))) != null)
    {
      this.jdField_field_139_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localclass_48);
      this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap.remove(Integer.valueOf(paramInt));
      if (paramBoolean) {
        a24(paramInt, localclass_48, false);
      }
      return localclass_48;
    }
    return null;
  }
  
  public final int a23(class_48 paramclass_48)
  {
    Integer localInteger;
    if ((localInteger = (Integer)this.jdField_field_139_of_type_JavaUtilConcurrentConcurrentHashMap.get(paramclass_48)) != null)
    {
      this.jdField_field_139_of_type_JavaUtilConcurrentConcurrentHashMap.remove(paramclass_48);
      this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap.remove(localInteger);
      a24(localInteger.intValue(), paramclass_48, false);
      return localInteger.intValue();
    }
    return -1;
  }
  
  private void a24(int paramInt, class_48 paramclass_48, boolean paramBoolean)
  {
    synchronized (this.jdField_field_136_of_type_Class_748.a127())
    {
      RemoteIntegerArray localRemoteIntegerArray;
      (localRemoteIntegerArray = new RemoteIntegerArray(4, this.jdField_field_136_of_type_Class_748.a127())).set(0, Integer.valueOf(paramBoolean ? paramInt : -paramInt - 1));
      localRemoteIntegerArray.set(1, Integer.valueOf(paramclass_48.field_475));
      localRemoteIntegerArray.set(2, Integer.valueOf(paramclass_48.field_476));
      localRemoteIntegerArray.set(3, Integer.valueOf(paramclass_48.field_477));
      this.jdField_field_136_of_type_Class_748.a127().controllerKeyNameBuffer.add(new RemoteString(this.jdField_field_136_of_type_JavaLangString, this.jdField_field_136_of_type_Class_748.a127()));
      this.jdField_field_136_of_type_Class_748.a127().controllerKeyBuffer.add(localRemoteIntegerArray);
      return;
    }
  }
  
  public final void a13()
  {
    synchronized (this.jdField_field_136_of_type_Class_748.a127())
    {
      Iterator localIterator = this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        a24(((Integer)localEntry.getKey()).intValue(), (class_48)localEntry.getValue(), true);
      }
      return;
    }
  }
  
  public final String toString()
  {
    return "ShipKeyConfiguration [shipIdentifier=" + this.jdField_field_136_of_type_JavaLangString + ", playerIdentifier=" + this.jdField_field_139_of_type_JavaLangString + ", keyControllerMap=" + this.jdField_field_136_of_type_JavaUtilConcurrentConcurrentHashMap + ", controllerKeyMap=" + this.jdField_field_139_of_type_JavaUtilConcurrentConcurrentHashMap + "]";
  }
  
  public final class_69 toTagStructure()
  {
    return null;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_359
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */