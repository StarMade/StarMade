import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import javax.vecmath.Vector3f;
import org.schema.game.common.data.element.meta.MetaObject;
import org.schema.game.common.data.element.meta.MetaObjectManager;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.remote.RemoteInventoryMultMod;
import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteIntArray;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
import org.schema.schine.network.server.ServerStateInterface;

public abstract class class_639
  implements class_80
{
  class_48 jdField_field_136_of_type_Class_48;
  protected final Int2ObjectOpenHashMap field_136;
  class_635 jdField_field_136_of_type_Class_635;
  
  public class_639(class_635 paramclass_635, class_48 paramclass_48)
  {
    this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap = new Int2ObjectOpenHashMap();
    this.jdField_field_136_of_type_Class_635 = paramclass_635;
    this.jdField_field_136_of_type_Class_48 = paramclass_48;
  }
  
  public final boolean b2()
  {
    return a42((short)1) >= 0;
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    System.err.println("CLEARING INVENTORY (FROM TAG) " + this.jdField_field_136_of_type_Class_48 + "; " + this.jdField_field_136_of_type_Class_635);
    this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.clear();
    if ("inventory".equals(paramclass_69.a2()))
    {
      Object localObject1 = (paramclass_69 = (class_69[])paramclass_69.a4())[0];
      class_69[] arrayOfclass_69 = paramclass_69[1];
      paramclass_69 = paramclass_69[2];
      localObject1 = (class_69[])((class_69)localObject1).a4();
      arrayOfclass_69 = (class_69[])arrayOfclass_69.a4();
      paramclass_69 = (class_69[])paramclass_69.a4();
      for (int i = 0; i < localObject1.length; i++) {
        if (paramclass_69[i].a3() == class_79.field_551)
        {
          b9(((Integer)localObject1[i].a4()).intValue(), ((Short)arrayOfclass_69[i].a4()).shortValue(), ((Integer)paramclass_69[i].a4()).intValue());
        }
        else
        {
          if (paramclass_69[i].a3() == class_79.field_561)
          {
            ((Integer)(localObject2 = (class_69[])paramclass_69[i].a4())[0].a4()).intValue();
            MetaObject localMetaObject1;
            (localMetaObject1 = MetaObjectManager.instantiate(((Short)localObject2[1].a4()).shortValue())).fromTag(localObject2[2]);
            MetaObject localMetaObject2 = localMetaObject1;
            int k = ((Short)arrayOfclass_69[i].a4()).shortValue();
            int j = ((Integer)localObject1[i].a4()).intValue();
            Object localObject2 = this;
            if (k == 0) {
              synchronized (((class_639)localObject2).jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
              {
                ((class_639)localObject2).jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(j);
              }
            }
            if ((??? = (class_647)((class_639)localObject2).jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(j)) == null)
            {
              (??? = new class_647()).jdField_field_921_of_type_Short = localObject3;
              ((class_647)???).jdField_field_921_of_type_Int = j;
            }
            ((class_647)???).jdField_field_921_of_type_JavaLangObject = localMetaObject2;
            ((class_647)???).jdField_field_921_of_type_Short = localObject3;
            if ((!jdField_field_136_of_type_Boolean) && (((class_647)???).jdField_field_921_of_type_Int != j)) {
              throw new AssertionError();
            }
            if ((!jdField_field_136_of_type_Boolean) && (((class_647)???).jdField_field_921_of_type_Short != localObject3)) {
              throw new AssertionError("SLOT: " + ((class_647)???).jdField_field_921_of_type_Short + " TRANSMIT: " + localObject3);
            }
            synchronized (((class_639)localObject2).jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
            {
              ((class_639)localObject2).jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(j, ???);
            }
          }
          if (!jdField_field_136_of_type_Boolean) {
            throw new AssertionError();
          }
        }
      }
      return;
    }
    if (!jdField_field_136_of_type_Boolean) {
      throw new AssertionError();
    }
  }
  
  public class_69 toTagStructure()
  {
    System.currentTimeMillis();
    class_69 localclass_691 = new class_69("slots", class_79.field_551);
    class_69 localclass_692 = new class_69("types", class_79.field_550);
    class_69 localclass_693 = new class_69("values", class_79.field_551);
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
    {
      Iterator localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject2 = (Integer)localIterator.next();
        if (!a18(((Integer)localObject2).intValue()))
        {
          Object localObject3 = (class_647)this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get((Integer)localObject2);
          if ((!jdField_field_136_of_type_Boolean) && (((Integer)localObject2).intValue() != ((class_647)localObject3).jdField_field_921_of_type_Int)) {
            throw new AssertionError();
          }
          localclass_691.a1(new class_69(class_79.field_551, null, localObject2));
          localclass_692.a1(new class_69(class_79.field_550, null, Short.valueOf(((class_647)localObject3).jdField_field_921_of_type_Short)));
          localObject2 = (MetaObject)((class_647)localObject2).jdField_field_921_of_type_JavaLangObject;
          (localObject3 = new class_69[4])[0] = new class_69(class_79.field_551, null, Integer.valueOf(((MetaObject)localObject2).getId()));
          localObject3[1] = new class_69(class_79.field_550, null, Short.valueOf(((MetaObject)localObject2).getObjectBlockID()));
          localObject3[2] = ((MetaObject)localObject2).getBytesTag();
          localObject3[2] = new class_69(class_79.field_548, null, null);
          localclass_693.a1(((localObject2 = localObject3).jdField_field_921_of_type_JavaLangObject instanceof Integer) ? new class_69(class_79.field_551, null, (Integer)((class_647)localObject2).jdField_field_921_of_type_JavaLangObject) : new class_69(class_79.field_561, null, (class_69[])localObject3));
        }
      }
    }
    return new class_69(class_79.field_561, "inventory", new class_69[] { localObject1, localclass_692, localclass_693, new class_69(class_79.field_548, null, null) });
  }
  
  private class_647 a40(int paramInt)
  {
    return (class_647)this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt);
  }
  
  public abstract int a3();
  
  public final int a41(int paramInt)
  {
    if (!a18(paramInt)) {
      return a40(paramInt).a1();
    }
    return 0;
  }
  
  public final int a42(short paramShort)
  {
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
    {
      Iterator localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        Integer localInteger = (Integer)localIterator.next();
        if ((!a18(localInteger.intValue())) && (a45(localInteger.intValue()) == paramShort)) {
          return localInteger.intValue();
        }
      }
    }
    return -1;
  }
  
  public abstract int b5();
  
  public int c2()
  {
    return 0;
  }
  
  public final int b7(short paramShort)
  {
    int i = 0;
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
    {
      Iterator localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator();
      while (localIterator.hasNext())
      {
        class_647 localclass_647;
        if ((localclass_647 = (class_647)localIterator.next()).jdField_field_921_of_type_Short == paramShort) {
          i += localclass_647.a1();
        }
      }
    }
    return i;
  }
  
  public final void a43(short paramShort, Collection paramCollection)
  {
    int i = 0;
    while ((i = a42(paramShort)) >= 0)
    {
      this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(i);
      paramCollection.add(Integer.valueOf(i));
    }
  }
  
  public final class_48 a44()
  {
    return this.jdField_field_136_of_type_Class_48;
  }
  
  public abstract int d1();
  
  public final short a45(int paramInt)
  {
    if (!a18(paramInt)) {
      return a40(paramInt).jdField_field_921_of_type_Short;
    }
    return 0;
  }
  
  public String getUniqueIdentifier()
  {
    if ((!jdField_field_136_of_type_Boolean) && (this.jdField_field_136_of_type_Class_635.getName() == null)) {
      throw new AssertionError();
    }
    return this.jdField_field_136_of_type_Class_635.getName() + "_" + this.jdField_field_136_of_type_Class_48 + "_inventory";
  }
  
  public final void a46(class_643 paramclass_643)
  {
    Object localObject1;
    Object localObject2;
    if (paramclass_643.getInventoryUpdateBuffer().getReceiveBuffer().size() > 0)
    {
      localObject1 = new HashSet();
      localObject2 = paramclass_643.getInventoryUpdateBuffer().getReceiveBuffer().iterator();
      while (((Iterator)localObject2).hasNext())
      {
        Integer localInteger1 = Integer.valueOf((localObject3 = (RemoteIntArray)((Iterator)localObject2).next()).getIntArray()[0]);
        Integer localInteger2 = Integer.valueOf(localObject3.getIntArray()[1]);
        Object localObject3 = Integer.valueOf(localObject3.getIntArray()[2]);
        b9(localInteger1.intValue(), localInteger2.shortValue(), localInteger2.shortValue() != 0 ? ((Integer)localObject3).intValue() : 0);
        ((HashSet)localObject1).add(localInteger1);
        if ((this.jdField_field_136_of_type_Class_635.getState() instanceof ServerStateInterface)) {
          a56((Collection)localObject1);
        }
      }
    }
    if (paramclass_643.getInventoryMultModBuffer().getReceiveBuffer().size() > 0)
    {
      localObject1 = paramclass_643.getInventoryMultModBuffer().getReceiveBuffer().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (RemoteInventoryMultMod)((Iterator)localObject1).next();
        a58((class_645)((RemoteInventoryMultMod)localObject2).get());
      }
    }
  }
  
  public final void a47(int paramInt1, short paramShort, int paramInt2)
  {
    class_647 localclass_647;
    if ((localclass_647 = a40(paramInt1)) != null)
    {
      long l;
      if ((l = localclass_647.a1() + paramInt2) > 2147483647L) {
        paramInt2 = 2147483646;
      } else if (l < -2147483648L) {
        paramInt2 = 0;
      } else {
        paramInt2 = (int)l;
      }
      b9(paramInt1, paramShort, Math.max(0, paramInt2));
      return;
    }
    if (paramInt2 < 0)
    {
      if (!jdField_field_136_of_type_Boolean) {
        throw new AssertionError("TRYING TO SET INVENTORY TO NEGATIVE VALUE");
      }
    }
    else {
      b9(paramInt1, paramShort, Math.max(0, paramInt2));
    }
  }
  
  public final int a48(short paramShort, int paramInt)
  {
    int i;
    if ((i = a42(paramShort)) >= 0)
    {
      a47(i, paramShort, paramInt);
      return i;
    }
    throw new NoSlotFreeException();
  }
  
  public final int a49(class_643 paramclass_643)
  {
    int i;
    if ((i = a42((short)1)) >= 0)
    {
      a47(i, (short)1, -1);
      a50(paramclass_643, paramclass_643.getInventoryUpdateBuffer(), i);
      return i;
    }
    throw new NoSlotFreeException();
  }
  
  public final int b8(short paramShort, int paramInt)
  {
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
    {
      Iterator localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        Integer localInteger = (Integer)localIterator.next();
        if ((!a18(localInteger.intValue())) && (a45(localInteger.intValue()) == paramShort))
        {
          a47(localInteger.intValue(), paramShort, paramInt);
          return localInteger.intValue();
        }
      }
    }
    return c4(paramShort, paramInt);
  }
  
  public final boolean c3()
  {
    return this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.isEmpty();
  }
  
  public final boolean a18(int paramInt)
  {
    return ((paramInt = a40(paramInt)) == null) || (paramInt.a1() <= 0) || (paramInt.jdField_field_921_of_type_Short == 0);
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public final void b9(int paramInt1, short arg2, int paramInt2)
  {
    if (??? == 0) {
      synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
      {
        this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt1);
        return;
      }
    }
    if ((??? = (class_647)this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt1)) == null)
    {
      (??? = new class_647()).jdField_field_921_of_type_Short = ???;
      ((class_647)???).jdField_field_921_of_type_Int = paramInt1;
    }
    ((class_647)???).b(paramInt2);
    ((class_647)???).jdField_field_921_of_type_Short = ???;
    if ((!jdField_field_136_of_type_Boolean) && (((class_647)???).jdField_field_921_of_type_Int != paramInt1)) {
      throw new AssertionError();
    }
    if ((!jdField_field_136_of_type_Boolean) && (((class_647)???).jdField_field_921_of_type_Short != ???)) {
      throw new AssertionError("SLOT: " + ((class_647)???).jdField_field_921_of_type_Short + " TRANSMIT: " + ???);
    }
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
    {
      this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt1, ???);
      return;
    }
  }
  
  public final int c4(short paramShort, int paramInt)
  {
    for (int i = 0; (i < d1()) || (d1() < 0); i++) {
      if (a18(i))
      {
        b9(i, paramShort, paramInt);
        return i;
      }
    }
    throw new NoSlotFreeException();
  }
  
  private void a50(class_643 paramclass_643, RemoteIntArrayBuffer paramRemoteIntArrayBuffer, int paramInt)
  {
    if (!a18(paramInt))
    {
      a51(paramclass_643, paramRemoteIntArrayBuffer, a40(paramInt));
    }
    else if (paramRemoteIntArrayBuffer.getArraySize() == 6)
    {
      (paramclass_643 = new RemoteIntArray(6, (NetworkObject)paramclass_643)).set(0, paramInt);
      paramclass_643.set(1, 0);
      paramclass_643.set(2, 0);
      paramclass_643.set(3, this.jdField_field_136_of_type_Class_48.field_475);
      paramclass_643.set(4, this.jdField_field_136_of_type_Class_48.field_476);
      paramclass_643.set(5, this.jdField_field_136_of_type_Class_48.field_477);
      paramRemoteIntArrayBuffer.add(paramclass_643);
    }
    else
    {
      (paramclass_643 = new RemoteIntArray(3, (NetworkObject)paramclass_643)).set(0, paramInt);
      paramclass_643.set(1, 0);
      paramclass_643.set(2, 0);
      paramRemoteIntArrayBuffer.add(paramclass_643);
    }
    if (paramRemoteIntArrayBuffer.size() > 200) {
      try
      {
        if ((this.jdField_field_136_of_type_Class_635 != null) && ((this.jdField_field_136_of_type_Class_635 instanceof Sendable)))
        {
          paramInt = (Sendable)(paramclass_643 = (Sendable)this.jdField_field_136_of_type_Class_635).getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramclass_643.getId());
          NetworkObject localNetworkObject = (NetworkObject)paramclass_643.getState().getLocalAndRemoteObjectContainer().getRemoteObjects().get(paramclass_643.getId());
          throw new IllegalArgumentException("WARNING: inventory high of " + paramclass_643 + ": " + paramRemoteIntArrayBuffer.size() + "; " + paramInt + "; " + localNetworkObject);
        }
        throw new IllegalArgumentException("WARNING: inventory high: " + paramRemoteIntArrayBuffer.size() + "; no inventory holder ");
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException;
      }
    }
  }
  
  private void a51(class_643 paramclass_643, RemoteIntArrayBuffer paramRemoteIntArrayBuffer, class_647 paramclass_647)
  {
    if (paramRemoteIntArrayBuffer.getArraySize() == 6)
    {
      (paramclass_643 = new RemoteIntArray(6, (NetworkObject)paramclass_643)).set(0, paramclass_647.jdField_field_921_of_type_Int);
      paramclass_643.set(1, paramclass_647.jdField_field_921_of_type_Short);
      paramclass_643.set(2, paramclass_647.a1());
      paramclass_643.set(3, this.jdField_field_136_of_type_Class_48.field_475);
      paramclass_643.set(4, this.jdField_field_136_of_type_Class_48.field_476);
      paramclass_643.set(5, this.jdField_field_136_of_type_Class_48.field_477);
      paramRemoteIntArrayBuffer.add(paramclass_643);
      return;
    }
    (paramclass_643 = new RemoteIntArray(3, (NetworkObject)paramclass_643)).set(0, paramclass_647.jdField_field_921_of_type_Int);
    paramclass_643.set(1, paramclass_647.jdField_field_921_of_type_Short);
    paramclass_643.set(2, paramclass_647.a1());
    paramRemoteIntArrayBuffer.add(paramclass_643);
  }
  
  public final void b10(class_643 paramclass_643)
  {
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
    {
      Iterator localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator();
      while (localIterator.hasNext())
      {
        class_647 localclass_647 = (class_647)localIterator.next();
        a51(paramclass_643, paramclass_643.getInventoryUpdateBuffer(), localclass_647);
      }
      return;
    }
  }
  
  public final void a52(int paramInt1, int paramInt2, int paramInt3)
  {
    a53(paramInt1, paramInt2, this, paramInt3);
  }
  
  public final void a53(int paramInt1, int paramInt2, class_639 paramclass_639, int paramInt3)
  {
    class_647 localclass_6471 = a40(paramInt1);
    class_647 localclass_6472 = paramclass_639.a40(paramInt2);
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
    {
      if ((localclass_6471 != null) && (localclass_6472 != null) && (localclass_6471.jdField_field_921_of_type_Short == localclass_6472.jdField_field_921_of_type_Short) && (localclass_6471 != localclass_6472))
      {
        System.err.println("MERGING SLOT");
        localclass_6471.a(paramInt3);
        localclass_6472.a(-paramInt3);
        if (localclass_6472.a1() <= 0) {
          paramclass_639.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt2);
        }
      }
      else
      {
        if (localclass_6471 != null) {
          System.err.println("COUTNSFDN: " + paramInt3 + " of " + localclass_6471.a1() + " (other: " + localclass_6472 + ")");
        }
        if ((localclass_6471 != null) && (localclass_6472 != null) && (localclass_6471.jdField_field_921_of_type_Short != localclass_6472.jdField_field_921_of_type_Short) && (localclass_6471.jdField_field_921_of_type_Short != 0))
        {
          if ((localclass_6472.a1() == paramInt3) || (localclass_6471.a1() == 0) || (localclass_6472.a1() == 0))
          {
            localclass_6471.jdField_field_921_of_type_Int = paramInt2;
            if ((!jdField_field_136_of_type_Boolean) && (localclass_6471.jdField_field_921_of_type_Short == 0)) {
              throw new AssertionError();
            }
            paramclass_639.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt2, localclass_6471);
            this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt1);
            localclass_6472.jdField_field_921_of_type_Int = paramInt1;
            this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt1, localclass_6472);
            if (paramclass_639.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt2) != localclass_6471) {
              paramclass_639.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt2);
            }
          }
        }
        else
        {
          class_647 localclass_6473;
          if ((localclass_6471 != null) && ((localclass_6472 == null) || (localclass_6472.a1() == 0)))
          {
            (localclass_6473 = new class_647(localclass_6471, paramInt2)).b(paramInt3);
            if ((!jdField_field_136_of_type_Boolean) && (localclass_6473.jdField_field_921_of_type_Short == 0)) {
              throw new AssertionError();
            }
            paramclass_639.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt2, localclass_6473);
            if ((localclass_6473 = (class_647)this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt1)) != null)
            {
              localclass_6473.a(-paramInt3);
              if (localclass_6473.a1() <= 0) {
                this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt1);
              }
            }
          }
          if ((localclass_6472 != null) && ((localclass_6471 == null) || (localclass_6471.a1() == 0)))
          {
            (localclass_6473 = new class_647(localclass_6472, paramInt1)).b(paramInt3);
            if ((!jdField_field_136_of_type_Boolean) && (localclass_6473.jdField_field_921_of_type_Short == 0)) {
              throw new AssertionError();
            }
            this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(paramInt1, localclass_6473);
            if ((localclass_6473 = (class_647)paramclass_639.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.get(paramInt2)) != null)
            {
              localclass_6473.a(-paramInt3);
              if (localclass_6473.a1() <= 0) {
                paramclass_639.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(paramInt2);
              }
            }
          }
        }
      }
    }
    a36(paramInt1);
    paramclass_639.a36(paramInt2);
  }
  
  public String toString()
  {
    return "Inventory: (" + c2() + "; " + this.jdField_field_136_of_type_Class_48 + ")" + this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.toString();
  }
  
  public final int a54(DataOutputStream paramDataOutputStream)
  {
    int i = 4;
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
    {
      paramDataOutputStream.writeInt(this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.size());
      Iterator localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator();
      while (localIterator.hasNext())
      {
        class_647 localclass_647 = (class_647)localIterator.next();
        paramDataOutputStream.writeInt(localclass_647.jdField_field_921_of_type_Int);
        paramDataOutputStream.writeShort(localclass_647.jdField_field_921_of_type_Short);
        paramDataOutputStream.writeInt(localclass_647.a1());
        i += 10;
      }
    }
    return i;
  }
  
  public final void a1(DataInputStream paramDataInputStream)
  {
    int i = paramDataInputStream.readInt();
    for (int j = 0; j < i; j++)
    {
      int k = paramDataInputStream.readInt();
      short s = paramDataInputStream.readShort();
      int m = paramDataInputStream.readInt();
      b9(k, s, m);
    }
  }
  
  public final void a55(class_797 arg1)
  {
    class_670 localclass_670;
    if ((localclass_670 = ((class_1041)???.getState()).a62().getSector(???.getSectorId())) != null)
    {
      System.err.println("[INVENTORY][SPAWNING] spawning inventory at " + this.jdField_field_136_of_type_Class_48);
      Vector3f localVector3f1 = new Vector3f(this.jdField_field_136_of_type_Class_48.field_475 - 8, this.jdField_field_136_of_type_Class_48.field_476 - 8, this.jdField_field_136_of_type_Class_48.field_477 - 8);
      ???.getWorldTransform().transform(localVector3f1);
      synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap)
      {
        Iterator localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.values().iterator();
        while (localIterator.hasNext())
        {
          class_647 localclass_647;
          if ((localclass_647 = (class_647)localIterator.next()).jdField_field_921_of_type_Short != 0)
          {
            Vector3f localVector3f2;
            void tmp158_156 = (localVector3f2 = new Vector3f(localVector3f1));
            tmp158_156.field_615 = ((float)(tmp158_156.field_615 + (Math.random() - 0.5D)));
            Vector3f tmp177_175 = localVector3f2;
            tmp177_175.field_616 = ((float)(tmp177_175.field_616 + (Math.random() - 0.5D)));
            Vector3f tmp196_194 = localVector3f2;
            tmp196_194.field_617 = ((float)(tmp196_194.field_617 + (Math.random() - 0.5D)));
            System.err.println("[INVENTORY][SPAWNING] spawning inventory at " + this.jdField_field_136_of_type_Class_48 + " -> " + localVector3f2);
            localclass_670.a78().a36(localVector3f2, localclass_647.jdField_field_921_of_type_Short, localclass_647.a1());
          }
        }
        return;
      }
    }
    System.err.println("[INVENTORY][SPAWN] sector null of " + ???);
  }
  
  private void a36(int paramInt)
  {
    this.jdField_field_136_of_type_Class_635.sendInventoryModification(paramInt, this.jdField_field_136_of_type_Class_48);
  }
  
  public final void a56(Collection paramCollection)
  {
    this.jdField_field_136_of_type_Class_635.sendInventoryModification(paramCollection, this.jdField_field_136_of_type_Class_48);
  }
  
  public final void a57(RemoteIntArray paramRemoteIntArray, class_643 paramclass_643)
  {
    Integer localInteger1 = Integer.valueOf(paramRemoteIntArray.getIntArray()[0]);
    Integer localInteger2 = Integer.valueOf(paramRemoteIntArray.getIntArray()[1]);
    paramRemoteIntArray = Integer.valueOf(paramRemoteIntArray.getIntArray()[2]);
    b9(localInteger1.intValue(), localInteger2.shortValue(), localInteger2.shortValue() != 0 ? paramRemoteIntArray.intValue() : 0);
    if ((this.jdField_field_136_of_type_Class_635.getState() instanceof ServerStateInterface)) {
      a50(paramclass_643, paramclass_643.getInventoryUpdateBuffer(), localInteger1.intValue());
    }
  }
  
  public final void a58(class_645 paramclass_645)
  {
    ArrayList localArrayList = null;
    if ((this.jdField_field_136_of_type_Class_635.getState() instanceof ServerStateInterface)) {
      localArrayList = new ArrayList();
    }
    for (int i = 0; i < paramclass_645.field_920.length; i++)
    {
      class_647 localclass_647;
      if ((localclass_647 = paramclass_645.field_920[i]).jdField_field_921_of_type_Short == 0) {
        this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.remove(localclass_647.jdField_field_921_of_type_Int);
      } else {
        this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2ObjectOpenHashMap.put(localclass_647.jdField_field_921_of_type_Int, localclass_647);
      }
      if ((this.jdField_field_136_of_type_Class_635.getState() instanceof ServerStateInterface)) {
        localArrayList.add(Integer.valueOf(localclass_647.jdField_field_921_of_type_Int));
      }
    }
    if ((this.jdField_field_136_of_type_Class_635.getState() instanceof ServerStateInterface)) {
      this.jdField_field_136_of_type_Class_635.sendInventoryModification(localArrayList, this.jdField_field_136_of_type_Class_48);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_639
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */