import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import it.unimi.dsi.fastutil.shorts.Short2IntOpenHashMap;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.ship.ShipElement;
import org.schema.game.common.data.element.terrain.MineralElement;
import org.schema.game.common.data.element.terrain.TerrainElement;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkShop;
import org.schema.game.network.objects.remote.RemoteInventoryMultMod;
import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteIntArray;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
import org.schema.schine.network.server.ServerMessage;

public class class_743
  extends EditableSendableSegmentController
  implements class_75, class_635
{
  private static long jdField_field_136_of_type_Long = 300000L;
  private class_649 jdField_field_136_of_type_Class_649;
  private final Short2IntOpenHashMap jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap = new Short2IntOpenHashMap();
  private final Vector3f jdField_field_136_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Vector3f jdField_field_139_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Vector3f jdField_field_182_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Vector3f jdField_field_183_of_type_JavaxVecmathVector3f = new Vector3f();
  private long jdField_field_139_of_type_Long;
  private long jdField_field_182_of_type_Long;
  private long jdField_field_183_of_type_Long;
  private long field_184;
  private double jdField_field_136_of_type_Double = 1.0D;
  private Int2IntOpenHashMap jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap = new Int2IntOpenHashMap();
  
  public class_743(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void cleanUpOnEntityDelete()
  {
    super.cleanUpOnEntityDelete();
    synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (localIterator.hasNext())
      {
        Sendable localSendable;
        if (((localSendable = (Sendable)localIterator.next()) instanceof class_739)) {
          ((class_739)localSendable).a().remove(this);
        }
      }
      return;
    }
  }
  
  public final void a72(boolean paramBoolean)
  {
    IntOpenHashSet localIntOpenHashSet = new IntOpenHashSet();
    int i = Universe.getRandom().nextInt(5) == 0 ? 1 : 0;
    short[] arrayOfShort;
    int j = (arrayOfShort = ElementKeyMap.typeList()).length;
    for (int k = 0; k < j; k++)
    {
      Short localShort;
      ElementInformation localElementInformation = ElementKeyMap.getInfo((localShort = Short.valueOf(arrayOfShort[k])).shortValue());
      if (ShipElement.class.isAssignableFrom(localElementInformation.getType()))
      {
        if (Universe.getRandom().nextInt(10) != 0) {
          localIntOpenHashSet.add(this.jdField_field_136_of_type_Class_649.b8(localShort.shortValue(), 100 + Universe.getRandom().nextInt(1000)));
        }
      }
      else if (TerrainElement.class.isAssignableFrom(localElementInformation.getType()))
      {
        if (MineralElement.class.isAssignableFrom(localElementInformation.getType()))
        {
          if (i != 0) {
            localIntOpenHashSet.add(this.jdField_field_136_of_type_Class_649.b8(localShort.shortValue(), 2000 + Universe.getRandom().nextInt(1000)));
          } else if (Universe.getRandom().nextInt(3) == 0) {
            if (Universe.getRandom().nextInt(10) == 0) {
              localIntOpenHashSet.add(this.jdField_field_136_of_type_Class_649.b8(localShort.shortValue(), 500 + Universe.getRandom().nextInt(500)));
            } else {
              localIntOpenHashSet.add(this.jdField_field_136_of_type_Class_649.b8(localShort.shortValue(), Universe.getRandom().nextInt(10)));
            }
          }
        }
        else {
          localIntOpenHashSet.add(this.jdField_field_136_of_type_Class_649.b8(localShort.shortValue(), Universe.getRandom().nextInt(1000)));
        }
      }
      else if (ki.class.isAssignableFrom(localElementInformation.getType())) {
        localIntOpenHashSet.add(this.jdField_field_136_of_type_Class_649.b8(localShort.shortValue(), 100 + Universe.getRandom().nextInt(500)));
      } else {
        localIntOpenHashSet.add(this.jdField_field_136_of_type_Class_649.b8(localShort.shortValue(), Universe.getRandom().nextInt(100)));
      }
    }
    if (paramBoolean) {
      this.jdField_field_136_of_type_Class_649.a56(localIntOpenHashSet);
    }
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if (paramclass_69.a2().equals("ShopSpaceStation1"))
    {
      paramclass_69 = (class_69[])paramclass_69.a4();
      if ((!jdField_field_136_of_type_Boolean) && (!"inventory".equals(paramclass_69[1].a2()))) {
        throw new AssertionError();
      }
      this.jdField_field_136_of_type_Class_649.fromTagStructure(paramclass_69[1]);
      this.jdField_field_136_of_type_Double = ((Double)paramclass_69[2].a4()).doubleValue();
      this.jdField_field_182_of_type_Long = ((Long)paramclass_69[3].a4()).longValue();
      class_69[] arrayOfclass_691 = (class_69[])paramclass_69[4].a4();
      for (int i = 0; i < arrayOfclass_691.length - 1; i++)
      {
        class_69[] arrayOfclass_692 = (class_69[])arrayOfclass_691[i].a4();
        this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.put((Short)arrayOfclass_692[0].a4(), (Integer)arrayOfclass_692[1].a4());
      }
      a111(false, true);
      super.fromTagStructure(paramclass_69[0]);
      return;
    }
    if (paramclass_69.a2().equals("ShopSpaceStation0"))
    {
      paramclass_69 = (class_69[])paramclass_69.a4();
      if ((!jdField_field_136_of_type_Boolean) && (!"inventory".equals(paramclass_69[1].a2()))) {
        throw new AssertionError();
      }
      this.jdField_field_136_of_type_Class_649.fromTagStructure(paramclass_69[1]);
      super.fromTagStructure(paramclass_69[0]);
      a111(false, false);
      return;
    }
    super.fromTagStructure(paramclass_69);
  }
  
  public class_639 getInventory(class_48 paramclass_48)
  {
    return this.jdField_field_136_of_type_Class_649;
  }
  
  public String getName()
  {
    return null;
  }
  
  public final int a107(ElementInformation paramElementInformation, int paramInt)
  {
    if (this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.containsKey(paramElementInformation.getId()))
    {
      long l = Math.abs(paramInt) * this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.get(paramElementInformation.getId());
      return (int)Math.min(2147483647L, l);
    }
    System.err.println("Shop Exception: " + this + "; " + getState() + ": price not found for " + paramElementInformation.getId() + ": " + this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap);
    return 0;
  }
  
  public final class_649 a108()
  {
    return this.jdField_field_136_of_type_Class_649;
  }
  
  public void handleExplosion(Transform paramTransform, float paramFloat1, float paramFloat2, class_809 paramclass_809, byte paramByte)
  {
    super.handleExplosion(paramTransform, paramFloat1, paramFloat2, paramclass_809, paramByte);
    if (!isOnServer())
    {
      if ((paramclass_809 != null) && ((paramclass_809 instanceof class_365)) && (((class_365)paramclass_809).a26().contains(((class_371)getState()).a20()))) {
        ((class_371)getState()).a4().b1("WARNING:\nAttacking a shop is not a good idea!\n");
      }
    }
    else if ((paramclass_809 != null) && ((paramclass_809 instanceof Sendable))) {
      a109((Sendable)paramclass_809);
    }
  }
  
  private void a109(Sendable paramSendable)
  {
    if ((paramSendable != null) && ((paramSendable instanceof class_365)) && (((class_365)paramSendable).a26().size() > 0) && (System.currentTimeMillis() - this.jdField_field_183_of_type_Long > 2000L))
    {
      Iterator localIterator = ((class_365)paramSendable).a26().iterator();
      while (localIterator.hasNext())
      {
        class_748 localclass_748 = (class_748)localIterator.next();
        int i = 0;
        if (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap.containsKey(localclass_748.getId())) {
          i = this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap.get(localclass_748.getId());
        }
        i++;
        if (i < 3)
        {
          System.err.println("STRIKES: " + i);
          if (i <= 1) {
            localclass_748.a129(new ServerMessage("####### Transmission Start\n\nCease fire immediately!\n\n####### Transmission End", 2, localclass_748.getId()));
          } else {
            localclass_748.a129(new ServerMessage("####### Transmission Start\n\nCease fire immediately!\nThis is the last warning!\n\n####### Transmission End", 3, localclass_748.getId()));
          }
          this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsInt2IntOpenHashMap.put(localclass_748.getId(), i);
        }
        else if (System.currentTimeMillis() - this.field_184 > 40000L)
        {
          localclass_748.a129(new ServerMessage("####### Transmission Start\n\nFleet dispatched!\n\n####### Transmission End", 3, localclass_748.getId()));
          this.field_184 = System.currentTimeMillis();
          ((class_1041)getState()).a69().a211((class_797)paramSendable);
        }
        else
        {
          switch (Universe.getRandom().nextInt(7))
          {
          case 0: 
            localclass_748.a129(new ServerMessage("####### Transmission Start\n\nThe Fleet is on the way to kill you!\n\n####### Transmission End", 3, localclass_748.getId()));
            break;
          case 1: 
            localclass_748.a129(new ServerMessage("####### Transmission Start\n\nThe Traiding Guild will\neliminate you!\n\n####### Transmission End", 3, localclass_748.getId()));
            break;
          case 2: 
            localclass_748.a129(new ServerMessage("####### Transmission Start\n\nAre you suicidal?!\n\n####### Transmission End", 3, localclass_748.getId()));
            break;
          case 3: 
            localclass_748.a129(new ServerMessage("####### Transmission Start\n\nIn space, nobody can hear you scream!\n\n####### Transmission End", 3, localclass_748.getId()));
            break;
          case 4: 
            localclass_748.a129(new ServerMessage("####### Transmission Start\n\nFreeze!\n\n####### Transmission End", 3, localclass_748.getId()));
            break;
          case 5: 
            localclass_748.a129(new ServerMessage("####### Transmission Start\n\nPlease pull over, sir!\n\n####### Transmission End", 3, localclass_748.getId()));
            break;
          case 6: 
            localclass_748.a129(new ServerMessage("####### Transmission Start\n\nNo soup for YOU!\n\n####### Transmission End", 3, localclass_748.getId()));
          }
        }
      }
      this.jdField_field_183_of_type_Long = System.currentTimeMillis();
    }
  }
  
  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, class_809 paramclass_809, float paramFloat)
  {
    if (!isOnServer())
    {
      ((class_371)getState()).a27().a91().a22(paramClosestRayResultCallback);
      (paramclass_809 = new Transform()).setIdentity();
      paramclass_809.origin.set(paramClosestRayResultCallback.hitPointWorld);
      class_969.a8("0022_spaceship enemy - hit no explosion metallic impact on enemy ship", paramclass_809, 5.0F);
      return;
    }
    if ((paramclass_809 != null) && ((paramclass_809 instanceof Sendable))) {
      a109((Sendable)paramclass_809);
    }
  }
  
  public void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    super.initFromNetworkObject(paramNetworkObject);
    if (!isOnServer())
    {
      a13();
      paramNetworkObject = (NetworkShop)paramNetworkObject;
      this.jdField_field_136_of_type_Class_649.a46(paramNetworkObject);
    }
  }
  
  public void initialize()
  {
    super.initialize();
    this.jdField_field_136_of_type_Class_649 = new class_649(this, new class_48(0, 0, 0));
    setMass(0.0F);
  }
  
  private boolean a110(class_739 paramclass_739)
  {
    if (paramclass_739.getSectorId() != getSectorId()) {
      return false;
    }
    paramclass_739.getTransformedAABB(this.jdField_field_182_of_type_JavaxVecmathVector3f, this.jdField_field_183_of_type_JavaxVecmathVector3f, 0.0F, new Vector3f(), new Vector3f());
    if (getSegmentBuffer().b2())
    {
      this.jdField_field_183_of_type_JavaxVecmathVector3f.sub(this.jdField_field_182_of_type_JavaxVecmathVector3f);
      this.jdField_field_183_of_type_JavaxVecmathVector3f.scale(0.5F);
      this.jdField_field_182_of_type_JavaxVecmathVector3f.add(this.jdField_field_183_of_type_JavaxVecmathVector3f);
      this.jdField_field_182_of_type_JavaxVecmathVector3f.sub(getWorldTransform().origin);
      return this.jdField_field_182_of_type_JavaxVecmathVector3f.length() < 64.0F;
    }
    paramclass_739 = new Vector3f(getSegmentBuffer().a6().field_1273);
    Vector3f localVector3f = new Vector3f(getSegmentBuffer().a6().field_1274);
    if (!GlUtil.a1(paramclass_739, localVector3f))
    {
      getSegmentBuffer().a23();
      return false;
    }
    AabbUtil2.transformAabb(paramclass_739, localVector3f, 64.0F, getWorldTransform(), this.jdField_field_136_of_type_JavaxVecmathVector3f, this.jdField_field_139_of_type_JavaxVecmathVector3f);
    return AabbUtil2.testAabbAgainstAabb2(this.jdField_field_136_of_type_JavaxVecmathVector3f, this.jdField_field_139_of_type_JavaxVecmathVector3f, this.jdField_field_182_of_type_JavaxVecmathVector3f, this.jdField_field_183_of_type_JavaxVecmathVector3f);
  }
  
  public final boolean a86(String[] paramArrayOfString, class_48 paramclass_48)
  {
    return false;
  }
  
  public void newNetworkObject()
  {
    setNetworkObject(new NetworkShop(getState(), this));
  }
  
  protected void onCoreDestroyed(class_809 paramclass_809) {}
  
  public void onSectorInactiveClient()
  {
    super.onSectorInactiveClient();
    synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects())
    {
      Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
      while (localIterator.hasNext())
      {
        Sendable localSendable;
        if (((localSendable = (Sendable)localIterator.next()) instanceof class_739)) {
          if ((a110((class_739)localSendable)) && (((class_739)localSendable).getSectorId() == getSectorId())) {
            ((class_739)localSendable).a().add(this);
          } else {
            ((class_739)localSendable).a().remove(this);
          }
        }
      }
      return;
    }
  }
  
  public String printInventories()
  {
    return this.jdField_field_136_of_type_Class_649.toString();
  }
  
  private void a13()
  {
    if ((!jdField_field_136_of_type_Boolean) && (isOnServer())) {
      throw new AssertionError();
    }
    for (int i = 0; i < ((NetworkShop)super.getNetworkObject()).pricesUpdateBuffer.getReceiveBuffer().size(); i++)
    {
      RemoteIntArray localRemoteIntArray = (RemoteIntArray)((NetworkShop)super.getNetworkObject()).pricesUpdateBuffer.getReceiveBuffer().get(i);
      this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.put((short)localRemoteIntArray.getIntArray()[0], localRemoteIntArray.getIntArray()[1]);
    }
  }
  
  public void sendInventoryModification(int paramInt, class_48 paramclass_48)
  {
    (paramclass_48 = new RemoteIntArray(3, (NetworkShop)super.getNetworkObject())).set(0, paramInt);
    if (!this.jdField_field_136_of_type_Class_649.a18(paramInt))
    {
      paramclass_48.set(1, this.jdField_field_136_of_type_Class_649.a45(paramInt));
      paramclass_48.set(2, this.jdField_field_136_of_type_Class_649.a41(paramInt));
    }
    ((NetworkShop)super.getNetworkObject()).getInventoryUpdateBuffer().add(paramclass_48);
  }
  
  private void b4()
  {
    Iterator localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      RemoteIntArray localRemoteIntArray;
      (localRemoteIntArray = new RemoteIntArray(2, (NetworkShop)super.getNetworkObject())).set(0, ((Short)localEntry.getKey()).intValue());
      localRemoteIntArray.set(1, ((Integer)localEntry.getValue()).intValue());
      ((NetworkShop)super.getNetworkObject()).pricesUpdateBuffer.add(localRemoteIntArray);
    }
  }
  
  public void startCreatorThread()
  {
    if (getCreatorThread() == null) {
      setCreatorThread(new class_734(this));
    }
  }
  
  public String toNiceString()
  {
    return "Shop (" + getTotalElements() + ")";
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_69 = this.jdField_field_136_of_type_Class_649.toTagStructure();
    class_69[] tmp20_17 = new class_69[this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.size() + 1];
    class_69[] tmp21_20 = tmp20_17;
    class_69[] arrayOfclass_69 = tmp21_20;
    tmp20_17[(tmp21_20.length - 1)] = new class_69(class_79.field_548, null, null);
    int i = 0;
    Iterator localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      arrayOfclass_69[i] = new class_69(class_79.field_561, null, new class_69[] { new class_69(class_79.field_550, null, localEntry.getKey()), new class_69(class_79.field_551, null, localEntry.getValue()), new class_69(class_79.field_548, "fin", null) });
      i++;
    }
    return new class_69(class_79.field_561, "ShopSpaceStation1", new class_69[] { super.toTagStructure(), localclass_69, new class_69(class_79.field_554, null, Double.valueOf(this.jdField_field_136_of_type_Double)), new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_182_of_type_Long)), new class_69(class_79.field_561, null, arrayOfclass_69), new class_69(class_79.field_548, "fin", null) });
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    super.updateFromNetworkObject(paramNetworkObject);
    paramNetworkObject = (NetworkShop)paramNetworkObject;
    this.jdField_field_136_of_type_Class_649.a46(paramNetworkObject);
    if (!isOnServer()) {
      a13();
    }
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    super.updateLocal(paramclass_941);
    long l1;
    if ((l1 = System.currentTimeMillis()) > this.jdField_field_139_of_type_Long + 500L) {
      synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalUpdatableObjects().values().iterator();
        while (localIterator.hasNext())
        {
          Sendable localSendable;
          if (((localSendable = (Sendable)localIterator.next()) instanceof class_739)) {
            if ((a110((class_739)localSendable)) && (((class_739)localSendable).getSectorId() == getSectorId())) {
              ((class_739)localSendable).a().add(this);
            } else {
              ((class_739)localSendable).a().remove(this);
            }
          }
        }
        this.jdField_field_139_of_type_Long = l1;
      }
    }
    if ((isOnServer()) && ((class_1041.field_144) || (l1 > this.jdField_field_182_of_type_Long + jdField_field_136_of_type_Long)))
    {
      long l2 = System.currentTimeMillis();
      a111(true, false);
      long l3;
      if ((l3 = System.currentTimeMillis() - l2) > 3L) {
        System.err.println("[SERVER] updating prices for: " + this + " took " + l3);
      }
      jdField_field_136_of_type_Long = (300000.0D + Math.random() * 10.0D * 60000.0D);
      this.jdField_field_182_of_type_Long = l1;
    }
  }
  
  private void a111(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((!jdField_field_136_of_type_Boolean) && (!isOnServer())) {
      throw new AssertionError();
    }
    System.err.println("[SERVER] update prices for " + this);
    short[] arrayOfShort;
    int i = (arrayOfShort = ElementKeyMap.typeList()).length;
    for (int j = 0; j < i; j++)
    {
      Short localShort = Short.valueOf(arrayOfShort[j]);
      if ((!paramBoolean2) || (!this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.containsKey(localShort)))
      {
        ElementInformation localElementInformation1 = ElementKeyMap.getInfo(localShort.shortValue());
        ElementInformation localElementInformation2 = localElementInformation1;
        class_743 localclass_743 = this;
        if ((!jdField_field_136_of_type_Boolean) && (!localclass_743.isOnServer())) {
          throw new AssertionError();
        }
        int k = (k = localclass_743.jdField_field_136_of_type_Class_649.a42(localElementInformation2.getId())) >= 0 ? localclass_743.jdField_field_136_of_type_Class_649.a41(k) : 0;
        double d1 = Math.pow(Math.max(1, class_649.e() - k), 0.35D) * 0.1000000014901161D * localElementInformation2.getPrice();
        double d2 = Math.max(localElementInformation2.getPrice(), d1 * localclass_743.jdField_field_136_of_type_Double);
        if (localElementInformation2.getId() == 1) {
          System.err.println("CORE PRICE DIF: " + d1 + " -> " + d2 + "; basic " + localclass_743.jdField_field_136_of_type_Double);
        }
        this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.put(localElementInformation1.getId(), (int)Math.min(2147483647L, d2));
        if (localShort.shortValue() == 1) {
          System.err.println("CORE PRICE: " + this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShort2IntOpenHashMap.get(localElementInformation1.getId()));
        }
      }
    }
    if (paramBoolean1) {
      b4();
    }
  }
  
  public void updateToFullNetworkObject()
  {
    this.jdField_field_136_of_type_Class_649.b10((NetworkShop)super.getNetworkObject());
    super.updateToFullNetworkObject();
    System.currentTimeMillis();
    b4();
  }
  
  public class_643 getInventoryNetworkObject()
  {
    return (NetworkShop)super.getNetworkObject();
  }
  
  public HashMap getInventories()
  {
    HashMap localHashMap;
    (localHashMap = new HashMap(1)).put(new class_48(), this.jdField_field_136_of_type_Class_649);
    return localHashMap;
  }
  
  public void getRelationColor(class_762 paramclass_762, Vector4f paramVector4f, float paramFloat)
  {
    switch (class_741.field_1010[paramclass_762.ordinal()])
    {
    case 1: 
      paramVector4f.field_596 = (paramFloat + 0.3F);
      paramVector4f.field_597 = 0.0F;
      paramVector4f.field_598 = 0.3F;
      return;
    case 2: 
      paramVector4f.field_596 = 0.5F;
      paramVector4f.field_597 = paramFloat;
      paramVector4f.field_598 = 0.5F;
      return;
    case 3: 
      paramVector4f.field_596 = 1.0F;
      paramVector4f.field_597 = paramFloat;
      paramVector4f.field_598 = 1.0F;
    }
  }
  
  public void sendInventoryModification(Collection paramCollection, class_48 paramclass_48)
  {
    class_639 localclass_639;
    if ((localclass_639 = getInventory(paramclass_48)) != null)
    {
      paramCollection = new class_645(paramCollection, localclass_639, paramclass_48);
      ((NetworkShop)super.getNetworkObject()).getInventoryMultModBuffer().add(new RemoteInventoryMultMod(paramCollection, (NetworkShop)super.getNetworkObject()));
      return;
    }
    try
    {
      throw new IllegalArgumentException("[INVENTORY] Exception: tried to send inventory " + paramclass_48);
    }
    catch (Exception localException)
    {
      localException;
    }
  }
  
  protected String getSegmentControllerTypeString()
  {
    return "Shop";
  }
  
  public final String a()
  {
    return "0022_ambience loop - shop machinery (loop)";
  }
  
  public final String b()
  {
    return "0022_ambience loop - shop machinery (loop)";
  }
  
  public final float b1()
  {
    return 1.5F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_743
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */