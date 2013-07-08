import it.unimi.dsi.fastutil.shorts.Short2IntOpenHashMap;
import it.unimi.dsi.fastutil.shorts.ShortSet;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteIntegerArray;
import org.schema.schine.network.server.ServerStateInterface;

public class class_633
{
  private class_748 jdField_field_914_of_type_Class_748;
  private Short2IntOpenHashMap jdField_field_914_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap = new Short2IntOpenHashMap();
  
  public class_633(class_748 paramclass_748)
  {
    this.jdField_field_914_of_type_Class_748 = paramclass_748;
    if ((paramclass_748.getState() instanceof ServerStateInterface))
    {
      paramclass_748 = this;
      if ((!jdField_field_914_of_type_Boolean) && (!paramclass_748.jdField_field_914_of_type_Class_748.getInventory(null).c3())) {
        throw new AssertionError();
      }
      paramclass_748.jdField_field_914_of_type_Class_748.getInventory(null).b9(0, (short)5, 25);
      paramclass_748.jdField_field_914_of_type_Class_748.getInventory(null).b9(1, (short)2, 6);
      paramclass_748.jdField_field_914_of_type_Class_748.getInventory(null).b9(2, (short)8, 6);
      paramclass_748.jdField_field_914_of_type_Class_748.getInventory(null).b9(3, (short)6, 1);
      paramclass_748.jdField_field_914_of_type_Class_748.getInventory(null).b9(4, (short)16, 20);
      paramclass_748.jdField_field_914_of_type_Class_748.getInventory(null).b9(10, (short)1, 1);
    }
  }
  
  public final void a(short paramShort, int paramInt)
  {
    RemoteIntegerArray localRemoteIntegerArray;
    (localRemoteIntegerArray = new RemoteIntegerArray(2, this.jdField_field_914_of_type_Class_748.a127())).set(0, Integer.valueOf(paramInt));
    localRemoteIntegerArray.set(1, Integer.valueOf(paramShort));
    this.jdField_field_914_of_type_Class_748.a127().buyBuffer.add(localRemoteIntegerArray);
  }
  
  public final void b(short paramShort, int paramInt)
  {
    RemoteIntegerArray localRemoteIntegerArray;
    (localRemoteIntegerArray = new RemoteIntegerArray(2, this.jdField_field_914_of_type_Class_748.a127())).set(0, Integer.valueOf(paramInt));
    localRemoteIntegerArray.set(1, Integer.valueOf(paramShort));
    this.jdField_field_914_of_type_Class_748.a127().sellBuffer.add(localRemoteIntegerArray);
  }
  
  public final void a1(short paramShort)
  {
    int i = this.jdField_field_914_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.containsKey(paramShort) ? this.jdField_field_914_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.get(paramShort) : 0;
    this.jdField_field_914_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.put(paramShort, i + 1);
  }
  
  public final void a2()
  {
    if (!this.jdField_field_914_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.isEmpty())
    {
      HashSet localHashSet = new HashSet();
      Iterator localIterator = this.jdField_field_914_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        short s = ((Short)localIterator.next()).shortValue();
        class_639 localclass_639 = this.jdField_field_914_of_type_Class_748.getInventory(null);
        try
        {
          int i = localclass_639.b8(s, this.jdField_field_914_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.get(s));
          localHashSet.add(Integer.valueOf(i));
        }
        catch (NoSlotFreeException localNoSlotFreeException)
        {
          System.err.println("NO FREE SLOT [TODO send server message]");
        }
      }
      this.jdField_field_914_of_type_Class_748.sendInventoryModification(localHashSet, null);
      this.jdField_field_914_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.clear();
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_633
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */