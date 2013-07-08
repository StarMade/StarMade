import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.schema.game.common.data.world.SectorNotFoundException;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkClientChannel;
import org.schema.game.network.objects.remote.RemoteMissileUpdate;
import org.schema.game.network.objects.remote.RemoteMissileUpdateBuffer;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteShort;

public class class_595
{
  private final Short2ObjectOpenHashMap jdField_field_892_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap = new Short2ObjectOpenHashMap();
  private final class_1041 jdField_field_892_of_type_Class_1041;
  
  public class_595(class_1041 paramclass_1041)
  {
    this.jdField_field_892_of_type_Class_1041 = paramclass_1041;
  }
  
  public final void a(class_597 paramclass_597)
  {
    paramclass_597.initPhysics();
    this.jdField_field_892_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.put(paramclass_597.a18(), paramclass_597);
    paramclass_597.field_172.add(a4(paramclass_597));
    System.err.println("[SERVER] MISSILE ADDED " + paramclass_597);
  }
  
  public final void a1(class_941 paramclass_941)
  {
    ObjectIterator localObjectIterator = this.jdField_field_892_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.values().iterator();
    while (localObjectIterator.hasNext())
    {
      class_597 localclass_597;
      if ((localclass_597 = (class_597)localObjectIterator.next()).a5())
      {
        localclass_597.a15(paramclass_941);
        localclass_597.a16().clear();
        Iterator localIterator = this.jdField_field_892_of_type_Class_1041.b10().values().iterator();
        while (localIterator.hasNext())
        {
          class_748 localclass_748;
          if ((localclass_748 = (class_748)localIterator.next()).c2() == localclass_597.b6())
          {
            localclass_597.a16().add(localclass_748);
          }
          else
          {
            class_670 localclass_670;
            if ((localclass_670 = this.jdField_field_892_of_type_Class_1041.a62().getSector(localclass_597.b6())) != null)
            {
              if ((Math.abs(localclass_670.field_136.field_475 - localclass_748.a44().field_475) < 2) && (Math.abs(localclass_670.field_136.field_476 - localclass_748.a44().field_476) < 2) && (Math.abs(localclass_670.field_136.field_477 - localclass_748.a44().field_477) < 2)) {
                localclass_597.a16().add(localclass_748);
              }
            }
            else {
              localclass_597.d();
            }
          }
        }
      }
      else
      {
        try
        {
          localclass_597.c1();
        }
        catch (SectorNotFoundException localSectorNotFoundException)
        {
          System.err.println("[SERVER][MISSILEMAN] WARNING: Physics for missile " + localclass_597 + " has not been removed: Sector not loaded: " + localSectorNotFoundException.getMessage());
        }
        localObjectIterator.remove();
      }
    }
    a2();
  }
  
  private void a2()
  {
    Iterator localIterator1 = this.jdField_field_892_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.values().iterator();
    while (localIterator1.hasNext())
    {
      class_597 localclass_597;
      Iterator localIterator2;
      Object localObject1;
      Object localObject2;
      if ((localclass_597 = (class_597)localIterator1.next()).c3())
      {
        System.err.println("[SERVER] BROADCAST MISSILE UPDATE " + localclass_597 + "; Missile Count: " + this.jdField_field_892_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.size() + "; " + localclass_597.field_172);
        localIterator2 = this.jdField_field_892_of_type_Class_1041.b10().values().iterator();
        while (localIterator2.hasNext())
        {
          localObject1 = (class_748)localIterator2.next();
          if ((localObject2 = (RegisteredClientOnServer)this.jdField_field_892_of_type_Class_1041.getClients().get(Integer.valueOf(((class_748)localObject1).a3()))) != null)
          {
            ((class_748)localObject1).a44();
            if (((localObject2 = (Sendable)((RegisteredClientOnServer)localObject2).getLocalAndRemoteObjectContainer().getLocalObjects().get(0)) != null) && ((localObject2 instanceof class_45)))
            {
              localObject1 = (class_45)localObject2;
              localclass_597.b5((class_45)localObject1);
            }
            else
            {
              System.err.println("[SERVER] BROADCAST MISSILE UPDATE FAILED FOR " + localObject1 + ": NO CLIENT CHANNEL");
            }
          }
          else
          {
            System.err.println("[SEVRER][MISSILEMAN] client for player not found: " + localObject1);
          }
        }
      }
      if ((localclass_597.b9()) && (localclass_597.a5()))
      {
        localIterator2 = localclass_597.a16().iterator();
        while (localIterator2.hasNext())
        {
          localObject1 = (class_748)localIterator2.next();
          if ((localObject2 = (RegisteredClientOnServer)this.jdField_field_892_of_type_Class_1041.getClients().get(Integer.valueOf(((class_748)localObject1).a3()))) != null)
          {
            ((class_748)localObject1).a44();
            if (((localObject2 = (Sendable)((RegisteredClientOnServer)localObject2).getLocalAndRemoteObjectContainer().getLocalObjects().get(0)) != null) && ((localObject2 instanceof class_45)))
            {
              localObject1 = (class_45)localObject2;
              localclass_597.a17((class_45)localObject1);
            }
          }
          else
          {
            System.err.println("[SEVRER][MISSILEMAN] broadcast client for player not found: " + localObject1);
          }
        }
      }
      localclass_597.e();
    }
  }
  
  public final void a3(NetworkClientChannel paramNetworkClientChannel)
  {
    for (int i = 0; i < paramNetworkClientChannel.missileMissingRequestBuffer.getReceiveBuffer().size(); i++)
    {
      short s = ((Short)((RemoteShort)paramNetworkClientChannel.missileMissingRequestBuffer.getReceiveBuffer().get(i)).get()).shortValue();
      class_597 localclass_597;
      if ((localclass_597 = (class_597)this.jdField_field_892_of_type_ItUnimiDsiFastutilShortsShort2ObjectOpenHashMap.get(s)) != null)
      {
        System.err.println("[SERVER] Adding Requested Spawn " + localclass_597);
        paramNetworkClientChannel.missileUpdateBuffer.add(new RemoteMissileUpdate(a4(localclass_597), paramNetworkClientChannel));
      }
    }
  }
  
  private static class_615 a4(class_597 paramclass_597)
  {
    int i = -1;
    if (((paramclass_597 instanceof class_609)) && (((class_609)paramclass_597).a23() != null)) {
      i = ((class_609)paramclass_597).a23().getId();
    }
    class_615 localclass_615 = new class_615(paramclass_597.a18(), paramclass_597.a3().origin, paramclass_597.a2(), i, paramclass_597.a21(), paramclass_597.b6());
    if ((!jdField_field_892_of_type_Boolean) && (localclass_615.field_179 != paramclass_597.a21())) {
      throw new AssertionError();
    }
    return localclass_615;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_595
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */