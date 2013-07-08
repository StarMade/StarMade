import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.shorts.Short2ObjectArrayMap;
import it.unimi.dsi.fastutil.shorts.ShortOpenHashSet;
import java.util.ArrayList;
import java.util.Iterator;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.game.network.objects.remote.RemoteMissileUpdate;
import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteShort;

public final class class_794
{
  private class_371 jdField_field_1053_of_type_Class_371;
  private Short2ObjectArrayMap jdField_field_1053_of_type_ItUnimiDsiFastutilShortsShort2ObjectArrayMap = new Short2ObjectArrayMap();
  private ShortOpenHashSet jdField_field_1053_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet = new ShortOpenHashSet();
  private ArrayList jdField_field_1053_of_type_JavaUtilArrayList = new ArrayList();
  
  public class_794(class_371 paramclass_371)
  {
    this.jdField_field_1053_of_type_Class_371 = paramclass_371;
  }
  
  public final void a(class_941 paramclass_941, class_45 paramclass_45)
  {
    Object localObject = paramclass_45;
    paramclass_45 = this;
    Iterator localIterator = this.jdField_field_1053_of_type_JavaUtilArrayList.iterator();
    while (localIterator.hasNext()) {
      ((class_611)localIterator.next()).a2(paramclass_45.jdField_field_1053_of_type_Class_371, paramclass_45.jdField_field_1053_of_type_ItUnimiDsiFastutilShortsShort2ObjectArrayMap, (class_45)localObject);
    }
    paramclass_45.jdField_field_1053_of_type_JavaUtilArrayList.clear();
    paramclass_45 = this.jdField_field_1053_of_type_ItUnimiDsiFastutilShortsShort2ObjectArrayMap.values().iterator();
    while (paramclass_45.hasNext())
    {
      localObject = (class_597)paramclass_45.next();
      if (this.jdField_field_1053_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.size() > 0) {
        this.jdField_field_1053_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.remove(((class_597)localObject).a18());
      }
      ((class_597)localObject).b3(paramclass_941);
      ((class_597)localObject).a6(this.jdField_field_1053_of_type_Class_371.a8(), this.jdField_field_1053_of_type_Class_371.a20().a44());
    }
  }
  
  public final void a1(NetworkClientChannel paramNetworkClientChannel)
  {
    for (int i = 0; i < paramNetworkClientChannel.missileUpdateBuffer.getReceiveBuffer().size(); i++)
    {
      class_611 localclass_611 = (class_611)((RemoteMissileUpdate)paramNetworkClientChannel.missileUpdateBuffer.getReceiveBuffer().get(i)).get();
      this.jdField_field_1053_of_type_JavaUtilArrayList.add(localclass_611);
    }
  }
  
  public final void a2(short paramShort, class_45 paramclass_45)
  {
    if (!this.jdField_field_1053_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.contains(paramShort))
    {
      this.jdField_field_1053_of_type_ItUnimiDsiFastutilShortsShortOpenHashSet.add(paramShort);
      paramclass_45.a().missileMissingRequestBuffer.add(new RemoteShort(paramShort, false));
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_794
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */