import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.bytes.ByteArrayList;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.ZipException;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.player.PlayerControlledTransformableNotFound;
import org.schema.game.common.data.player.ShipConfigurationNotFoundException;
import org.schema.game.common.data.player.inventory.NoSlotFreeException;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkPlayer;
import org.schema.game.network.objects.remote.RemoteInventoryMultMod;
import org.schema.game.network.objects.remote.RemoteInventoryMultModBuffer;
import org.schema.game.server.controller.EntityAlreadyExistsException;
import org.schema.game.server.controller.EntityNotFountException;
import org.schema.game.server.controller.GameServerController;
import org.schema.game.server.controller.NotEnoughCreditsException;
import org.schema.schine.graphicsengine.camera.Camera;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.graphicsengine.core.settings.StateParameterNotFoundException;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.RegisteredClientOnServer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBooleanArray;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteByte;
import org.schema.schine.network.objects.remote.RemoteField;
import org.schema.schine.network.objects.remote.RemoteFloat;
import org.schema.schine.network.objects.remote.RemoteIntArray;
import org.schema.schine.network.objects.remote.RemoteIntArrayBuffer;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteIntegerArray;
import org.schema.schine.network.objects.remote.RemoteLong;
import org.schema.schine.network.objects.remote.RemoteShort;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteVector3f;
import org.schema.schine.network.objects.remote.RemoteVector3i;
import org.schema.schine.network.objects.remote.RemoteVector4f;
import org.schema.schine.network.server.ServerMessage;
import org.schema.schine.network.server.ServerProcessor;
import org.schema.schine.network.server.ServerStateInterface;

public class class_748
  implements class_80, class_809, class_635, Sendable
{
  private float jdField_field_136_of_type_Float = 300.0F;
  private int jdField_field_136_of_type_Int;
  private int jdField_field_139_of_type_Int;
  private NetworkPlayer jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer;
  private StateInterface jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface;
  private class_639 jdField_field_136_of_type_Class_639;
  private class_633 jdField_field_136_of_type_Class_633;
  private String jdField_field_136_of_type_JavaLangString;
  private int jdField_field_182_of_type_Int;
  private boolean jdField_field_139_of_type_Boolean;
  private int jdField_field_183_of_type_Int;
  private final ShortArrayList jdField_field_136_of_type_ItUnimiDsiFastutilShortsShortArrayList = new ShortArrayList();
  private final ByteArrayList jdField_field_136_of_type_ItUnimiDsiFastutilBytesByteArrayList = new ByteArrayList();
  private final class_619 jdField_field_136_of_type_Class_619;
  private final class_740 jdField_field_136_of_type_Class_740;
  private final ArrayList jdField_field_136_of_type_JavaUtilArrayList = new ArrayList();
  private final boolean jdField_field_182_of_type_Boolean;
  private boolean jdField_field_183_of_type_Boolean;
  private final HashSet jdField_field_136_of_type_JavaUtilHashSet = new HashSet();
  private long jdField_field_139_of_type_Long = 0L;
  private long jdField_field_182_of_type_Long = 0L;
  private ArrayList jdField_field_139_of_type_JavaUtilArrayList = new ArrayList();
  private ArrayList jdField_field_182_of_type_JavaUtilArrayList = new ArrayList();
  private ArrayList jdField_field_183_of_type_JavaUtilArrayList = new ArrayList();
  private final ArrayList jdField_field_184_of_type_JavaUtilArrayList = new ArrayList();
  private final class_48 jdField_field_136_of_type_Class_48 = new class_48(class_670.jdField_field_139_of_type_Class_48);
  private int jdField_field_184_of_type_Int;
  private boolean[] jdField_field_136_of_type_ArrayOfBoolean = new boolean[class_367.field_711.length];
  private boolean[] jdField_field_139_of_type_ArrayOfBoolean = new boolean[4];
  private final Set jdField_field_136_of_type_JavaUtilSet = new HashSet();
  private final Vector3f jdField_field_136_of_type_JavaxVecmathVector3f = new Vector3f();
  private final class_48 jdField_field_139_of_type_Class_48 = new class_48();
  private final Vector3f jdField_field_139_of_type_JavaxVecmathVector3f = new Vector3f();
  private final class_48 jdField_field_182_of_type_Class_48 = new class_48();
  private int jdField_field_185_of_type_Int;
  private int jdField_field_186_of_type_Int;
  private boolean jdField_field_184_of_type_Boolean;
  private boolean jdField_field_185_of_type_Boolean;
  private class_797 jdField_field_136_of_type_Class_797;
  private final class_787 jdField_field_136_of_type_Class_787;
  private final ArrayList jdField_field_185_of_type_JavaUtilArrayList = new ArrayList();
  private class_750 jdField_field_136_of_type_Class_750;
  public boolean field_136;
  private RegisteredClientOnServer jdField_field_136_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer;
  private ArrayList jdField_field_186_of_type_JavaUtilArrayList = new ArrayList();
  private LongArrayList jdField_field_136_of_type_ItUnimiDsiFastutilLongsLongArrayList = new LongArrayList();
  public long field_136;
  private class_653 jdField_field_136_of_type_Class_653;
  private boolean jdField_field_186_of_type_Boolean = true;
  private class_659 jdField_field_136_of_type_Class_659;
  private boolean field_192;
  private long jdField_field_183_of_type_Long = -1L;
  private long jdField_field_184_of_type_Long;
  private final class_742 jdField_field_136_of_type_Class_742;
  private final class_744 jdField_field_136_of_type_Class_744;
  private final class_850 jdField_field_136_of_type_Class_850;
  private final long jdField_field_185_of_type_Long;
  private final Transform jdField_field_136_of_type_ComBulletphysicsLinearmathTransform = new Transform();
  private boolean field_193;
  private final IntArrayList jdField_field_136_of_type_ItUnimiDsiFastutilIntsIntArrayList = new IntArrayList();
  private final Vector3f jdField_field_182_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Vector3f jdField_field_183_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Vector3f jdField_field_184_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Vector3f jdField_field_185_of_type_JavaxVecmathVector3f = new Vector3f();
  private String jdField_field_139_of_type_JavaLangString;
  public static ByteBuffer field_136;
  
  public class_748(StateInterface paramStateInterface)
  {
    this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_185_of_type_Long = System.currentTimeMillis();
    this.jdField_field_182_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
    this.jdField_field_136_of_type_Class_619 = new class_619(this);
    this.jdField_field_136_of_type_Class_787 = new class_787(this);
    this.jdField_field_136_of_type_Class_653 = new class_653(this);
    this.jdField_field_136_of_type_Class_659 = new class_659(this);
    this.jdField_field_136_of_type_Class_740 = new class_746(this);
    this.jdField_field_136_of_type_Class_742 = new class_742(this);
    this.jdField_field_136_of_type_Class_744 = new class_744(this);
    this.jdField_field_136_of_type_Class_850 = new class_850(this);
  }
  
  private void a117(class_809 paramclass_809)
  {
    if ((isOnServer()) && (!this.jdField_field_136_of_type_Class_619.a1().isEmpty())) {
      this.jdField_field_186_of_type_Int += 1;
    }
    if (paramclass_809 != null) {
      synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        Iterator localIterator1 = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        label373:
        while (localIterator1.hasNext())
        {
          Object localObject1;
          if (((localObject1 = (Sendable)localIterator1.next()) instanceof class_748))
          {
            class_748 localclass_748 = this;
            class_809 localclass_809 = paramclass_809;
            localObject1 = (class_748)localObject1;
            System.err.println(((class_748)localObject1).getState() + " " + localObject1 + " Checking for kill honor: " + localclass_809 + "; " + localclass_748);
            if (((class_748)localObject1).isOnServer()) {
              if ((localclass_809 == localclass_748) && (!((class_748)localObject1).jdField_field_136_of_type_Class_619.a1().isEmpty()))
              {
                ((class_748)localObject1).b23(localclass_748.getId(), localclass_748.getId());
              }
              else
              {
                Iterator localIterator2 = ((class_748)localObject1).jdField_field_136_of_type_Class_619.a1().iterator();
                for (;;)
                {
                  if (!localIterator2.hasNext()) {
                    break label373;
                  }
                  class_755 localclass_755 = (class_755)localIterator2.next();
                  System.err.println("CHECKING HONOR OF CONTROLLED ENTITY: " + localclass_809 + ", which killed " + localclass_748 + "; IS THE KILLER " + localclass_755.jdField_field_1015_of_type_Class_365 + "? -> " + (localclass_755.jdField_field_1015_of_type_Class_365 == localclass_809));
                  if (localclass_755.jdField_field_1015_of_type_Class_365 == localclass_809)
                  {
                    localObject1.jdField_field_185_of_type_Int += 1;
                    ((class_748)localObject1).b23(localclass_755.jdField_field_1015_of_type_Class_748.getId(), localclass_748.getId());
                    break;
                  }
                }
              }
            }
          }
        }
        return;
      }
    }
  }
  
  private void d2()
  {
    if (!this.jdField_field_185_of_type_JavaUtilArrayList.isEmpty())
    {
      class_809 localclass_809 = (class_809)this.jdField_field_185_of_type_JavaUtilArrayList.get(this.jdField_field_185_of_type_JavaUtilArrayList.size() - 1);
      this.jdField_field_136_of_type_Class_619.a7(this);
      System.err.println("[SERVER] PLAYER " + this + " died, removing ALL control units");
      if (!this.jdField_field_136_of_type_Class_619.a1().isEmpty()) {
        a38(this.jdField_field_136_of_type_Int / 2);
      }
      a117(localclass_809);
      synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (localIterator.hasNext())
        {
          Object localObject2;
          if ((((localObject2 = (Sendable)localIterator.next()) instanceof class_750)) && ((localObject2 = (class_750)localObject2).a3() == this.jdField_field_182_of_type_Int))
          {
            ((class_750)localObject2).a117(localclass_809);
            break;
          }
        }
      }
      this.jdField_field_185_of_type_JavaUtilArrayList.clear();
    }
  }
  
  public final boolean a118(class_637 paramclass_637)
  {
    if ((!field_194) && (!isOnServer())) {
      throw new AssertionError();
    }
    try
    {
      class_797 localclass_797;
      if ((localclass_797 = b17()) != null)
      {
        class_670 localclass_670 = ((class_1041)this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface).a62().getSector(this.jdField_field_184_of_type_Int);
        boolean bool;
        if ((localclass_797 instanceof SegmentController))
        {
          Vector3f localVector3f1;
          (localVector3f1 = new Vector3f(paramclass_637.a8())).sub(new Vector3f(0.0F, 0.5F, 0.0F));
          Vector3f localVector3f2;
          (localVector3f2 = new Vector3f(paramclass_637.a8())).add(new Vector3f(0.0F, 0.5F, 0.0F));
          bool = ((PhysicsExt)localclass_670.a64()).testRayCollisionPoint(localVector3f1, localVector3f2, false, null, (SegmentController)localclass_797, true, null, false).hasHit();
        }
        else
        {
          bool.getTransformedAABB(this.jdField_field_182_of_type_JavaxVecmathVector3f, this.jdField_field_183_of_type_JavaxVecmathVector3f, 0.5F, this.jdField_field_184_of_type_JavaxVecmathVector3f, this.jdField_field_185_of_type_JavaxVecmathVector3f);
          bool = class_988.a(paramclass_637.a8(), this.jdField_field_182_of_type_JavaxVecmathVector3f, this.jdField_field_183_of_type_JavaxVecmathVector3f);
        }
        if (bool) {
          try
          {
            localclass_670.a78().a5(paramclass_637.b5());
            if (paramclass_637.a34() == -2)
            {
              a38(paramclass_637.a3());
            }
            else
            {
              int i = getInventory(null).b8(paramclass_637.a34(), paramclass_637.a3());
              sendInventoryModification(i, null);
              if (paramclass_637.a34() > 0) {
                a129(new ServerMessage("Picked up " + paramclass_637.a3() + "\n" + ElementKeyMap.getInfo(paramclass_637.a34()).getName(), 1, getId()));
              }
            }
            return true;
          }
          catch (NoSlotFreeException localNoSlotFreeException)
          {
            System.err.println("[SERVER] " + this + " Cannot pick up item: inventory is full");
            if (System.currentTimeMillis() - this.jdField_field_184_of_type_Long > 7000L)
            {
              a129(new ServerMessage("Inventory Full", 2, getId()));
              this.jdField_field_184_of_type_Long = System.currentTimeMillis();
            }
          }
        }
      }
    }
    catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound) {}
    return false;
  }
  
  public void cleanUpOnEntityDelete()
  {
    if (isOnServer()) {
      this.jdField_field_136_of_type_Class_619.a7(this);
    }
    System.err.println("[PLAYER][CLEANUP] " + this + " removed controlled entities");
    if ((!isOnServer()) && (this == ((class_371)this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface).a20()))
    {
      System.err.println("PLAYER SET TO NULL ON " + this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface);
      ((class_371)this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface).a44(null);
    }
    this.jdField_field_136_of_type_Class_850.a13();
    this.jdField_field_136_of_type_Class_787.b();
    System.err.println("[PLAYER][CLEANUP] " + this + " notified team change");
  }
  
  public final void a119(float paramFloat, class_809 paramclass_809)
  {
    b22(Math.max(0.0F, this.jdField_field_136_of_type_Float - paramFloat), paramclass_809);
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject != null) && ((paramObject instanceof class_748))) {
      return getId() == ((class_748)paramObject).getId();
    }
    return false;
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    paramclass_69 = (class_69[])paramclass_69.a4();
    b6(((Integer)paramclass_69[0].a4()).intValue());
    System.err.println("[" + (isOnServer() ? "SERVER" : "CLIENT") + "][TAG] SPAWNING POINT OF " + this + " READ: " + this.jdField_field_136_of_type_JavaxVecmathVector3f);
    a37((Vector3f)paramclass_69[1].a4());
    if ("sector".equals(paramclass_69[3].a2())) {
      this.jdField_field_139_of_type_Class_48.b1((class_48)paramclass_69[3].a4());
    }
    this.jdField_field_136_of_type_Class_639.fromTagStructure(paramclass_69[2]);
    if ((paramclass_69.length >= 6) && ("lspawn".equals(paramclass_69[4].a2())))
    {
      this.jdField_field_139_of_type_JavaxVecmathVector3f.set((Vector3f)paramclass_69[4].a4());
      System.err.println("[SERVER][PLAYER][TAG] read logout spawn point: " + this.jdField_field_139_of_type_JavaxVecmathVector3f);
    }
    else
    {
      this.jdField_field_139_of_type_JavaxVecmathVector3f.set(this.jdField_field_136_of_type_JavaxVecmathVector3f);
    }
    if ((paramclass_69.length >= 6) && ("lsector".equals(paramclass_69[5].a2())))
    {
      this.jdField_field_136_of_type_Class_48.b1((class_48)paramclass_69[5].a4());
      this.jdField_field_182_of_type_Class_48.b1((class_48)paramclass_69[5].a4());
      System.err.println("[SERVER][PLAYER][TAG] read logout spawn sector: " + this.jdField_field_182_of_type_Class_48);
    }
    else
    {
      this.jdField_field_182_of_type_Class_48.b1(this.jdField_field_139_of_type_Class_48);
      this.jdField_field_136_of_type_Class_48.b1(this.jdField_field_139_of_type_Class_48);
    }
    if (paramclass_69.length >= 7) {
      this.jdField_field_136_of_type_Class_850.fromTagStructure(paramclass_69[6]);
    }
    if (paramclass_69.length >= 11)
    {
      this.jdField_field_139_of_type_Long = ((Long)paramclass_69[7].a4()).longValue();
      this.jdField_field_182_of_type_Long = ((Long)paramclass_69[8].a4()).longValue();
      class_69.a7(this.jdField_field_136_of_type_JavaUtilHashSet, (class_69[])paramclass_69[9].a4());
    }
  }
  
  public class_69 toTagStructure()
  {
    class_69[] arrayOfclass_69;
    (arrayOfclass_69 = new class_69[11])[0] = new class_69(class_79.field_551, "credits", Integer.valueOf(this.jdField_field_136_of_type_Int));
    System.err.println("[" + (isOnServer() ? "SERVER" : "CLIENT") + "][TAG] SPAWNING POINT OF " + this + " WRITTEN: " + this.jdField_field_136_of_type_JavaxVecmathVector3f);
    arrayOfclass_69[1] = new class_69(class_79.field_557, "spawn", this.jdField_field_136_of_type_JavaxVecmathVector3f);
    arrayOfclass_69[2] = this.jdField_field_136_of_type_Class_639.toTagStructure();
    arrayOfclass_69[3] = new class_69(class_79.field_558, "sector", this.jdField_field_139_of_type_Class_48);
    if (this.jdField_field_139_of_type_JavaxVecmathVector3f.lengthSquared() == 0.0F)
    {
      ((class_1041)getState()).a59();
      GameServerController.a48(this);
    }
    System.err.println("[" + (isOnServer() ? "SERVER" : "CLIENT") + "][TAG] LOGOUT SPAWNING POINT OF " + this + " WRITTEN: " + this.jdField_field_139_of_type_JavaxVecmathVector3f + " SECTOR " + this.jdField_field_182_of_type_Class_48);
    arrayOfclass_69[4] = new class_69(class_79.field_557, "lspawn", this.jdField_field_139_of_type_JavaxVecmathVector3f);
    arrayOfclass_69[5] = new class_69(class_79.field_558, "lsector", this.jdField_field_182_of_type_Class_48);
    arrayOfclass_69[6] = this.jdField_field_136_of_type_Class_850.toTagStructure();
    arrayOfclass_69[7] = new class_69(class_79.field_552, null, Long.valueOf(this.jdField_field_139_of_type_Long));
    arrayOfclass_69[8] = new class_69(class_79.field_552, null, Long.valueOf(System.currentTimeMillis()));
    arrayOfclass_69[9] = class_69.a8(this.jdField_field_136_of_type_JavaUtilHashSet, class_79.field_556, "ips");
    arrayOfclass_69[10] = new class_69(class_79.field_548, null, null);
    return new class_69(class_79.field_561, "PlayerState", arrayOfclass_69);
  }
  
  public final class_797 a120()
  {
    return this.jdField_field_136_of_type_Class_797;
  }
  
  public final class_750 a121()
  {
    if (this.jdField_field_136_of_type_Class_750 == null) {
      synchronized (getState().getLocalAndRemoteObjectContainer().getLocalObjects())
      {
        Iterator localIterator = getState().getLocalAndRemoteObjectContainer().getLocalObjects().values().iterator();
        while (localIterator.hasNext())
        {
          Object localObject2;
          if ((((localObject2 = (Sendable)localIterator.next()) instanceof class_750)) && ((localObject2 = (class_750)localObject2).a3() == this.jdField_field_182_of_type_Int)) {
            this.jdField_field_136_of_type_Class_750 = ((class_750)localObject2);
          }
        }
      }
    }
    return this.jdField_field_136_of_type_Class_750;
  }
  
  public final class_787 a122()
  {
    return this.jdField_field_136_of_type_Class_787;
  }
  
  public final int a3()
  {
    return this.jdField_field_182_of_type_Int;
  }
  
  private class_743 a123()
  {
    class_743 localclass_743 = null;
    Iterator localIterator = this.jdField_field_136_of_type_JavaUtilSet.iterator();
    while (localIterator.hasNext())
    {
      localclass_743 = (class_743)localIterator.next();
      new Vector3f(localclass_743.getWorldTransform().origin).sub(((class_797)((class_755)this.jdField_field_136_of_type_Class_619.a1().iterator().next()).jdField_field_1015_of_type_Class_365).getWorldTransform().origin);
    }
    return localclass_743;
  }
  
  public final class_619 a124()
  {
    return this.jdField_field_136_of_type_Class_619;
  }
  
  public final int b5()
  {
    return this.jdField_field_136_of_type_Int;
  }
  
  public final class_48 a44()
  {
    return this.jdField_field_136_of_type_Class_48;
  }
  
  public final int c2()
  {
    return this.jdField_field_184_of_type_Int;
  }
  
  public final int d1()
  {
    return ((Integer)this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.shipControllerSlot.get()).intValue();
  }
  
  public final int e()
  {
    return this.jdField_field_186_of_type_Int;
  }
  
  public final class_797 b17()
  {
    Iterator localIterator = this.jdField_field_136_of_type_Class_619.a1().iterator();
    while (localIterator.hasNext())
    {
      class_755 localclass_755;
      if (((localclass_755 = (class_755)localIterator.next()).jdField_field_1015_of_type_Class_748.equals(this)) && ((localclass_755.jdField_field_1015_of_type_Class_365 instanceof class_797))) {
        return (class_797)localclass_755.jdField_field_1015_of_type_Class_365;
      }
    }
    throw new PlayerControlledTransformableNotFound(this);
  }
  
  public final float a14()
  {
    return this.jdField_field_136_of_type_Float;
  }
  
  public int getId()
  {
    return this.jdField_field_139_of_type_Int;
  }
  
  public class_639 getInventory(class_48 paramclass_48)
  {
    return this.jdField_field_136_of_type_Class_639;
  }
  
  public final class_633 a125()
  {
    return this.jdField_field_136_of_type_Class_633;
  }
  
  public final int f2()
  {
    return this.jdField_field_185_of_type_Int;
  }
  
  public final Vector3f a8()
  {
    if ((isOnServer()) || (!a7())) {
      return GlUtil.c(new Vector3f(), this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform);
    }
    if ((class_969.a1() instanceof class_191)) {
      return ((class_191)class_969.a1()).a76().c10();
    }
    if (class_969.a1() != null) {
      return class_969.a1().c10();
    }
    return new Vector3f(0.0F, 0.0F, 1.0F);
  }
  
  public final Vector3f b18()
  {
    if ((isOnServer()) || (!a7())) {
      return GlUtil.d(new Vector3f(), this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform);
    }
    if ((class_969.a1() instanceof class_191)) {
      return ((class_191)class_969.a1()).a76().d7();
    }
    if (class_969.a1() != null) {
      return class_969.a1().d7();
    }
    return new Vector3f(1.0F, 0.0F, 0.0F);
  }
  
  public final Vector3f c9()
  {
    if ((isOnServer()) || (!a7())) {
      return GlUtil.f(new Vector3f(), this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform);
    }
    if ((class_969.a1() instanceof class_191)) {
      return ((class_191)class_969.a1()).a76().f5();
    }
    if (class_969.a1() != null) {
      return class_969.a1().f5();
    }
    return new Vector3f(0.0F, 1.0F, 0.0F);
  }
  
  public final Vector3f a126(Vector3f paramVector3f)
  {
    if ((isOnServer()) || (!a7())) {
      return GlUtil.c(paramVector3f, this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform);
    }
    if ((class_969.a1() instanceof class_191)) {
      return ((class_191)class_969.a1()).a76().a182(paramVector3f);
    }
    if (class_969.a1() != null) {
      return class_969.a1().a182(paramVector3f);
    }
    paramVector3f.set(0.0F, 0.0F, 1.0F);
    return paramVector3f;
  }
  
  public final Vector3f b19(Vector3f paramVector3f)
  {
    if ((isOnServer()) || (!a7())) {
      return GlUtil.d(paramVector3f, this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform);
    }
    if ((class_969.a1() instanceof class_191)) {
      return ((class_191)class_969.a1()).a76().b31(paramVector3f);
    }
    if (class_969.a1() != null) {
      return class_969.a1().b31(paramVector3f);
    }
    paramVector3f.set(1.0F, 0.0F, 0.0F);
    return paramVector3f;
  }
  
  public final Vector3f c10(Vector3f paramVector3f)
  {
    if ((isOnServer()) || (!a7())) {
      return GlUtil.f(paramVector3f, this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform);
    }
    if ((class_969.a1() instanceof class_191)) {
      return ((class_191)class_969.a1()).a76().d11(paramVector3f);
    }
    if (class_969.a1() != null) {
      return class_969.a1().d11(paramVector3f);
    }
    paramVector3f.set(0.0F, 1.0F, 0.0F);
    return paramVector3f;
  }
  
  public final Vector3f d4()
  {
    return this.jdField_field_139_of_type_JavaxVecmathVector3f;
  }
  
  public final class_48 b20()
  {
    return this.jdField_field_182_of_type_Class_48;
  }
  
  public String getName()
  {
    return this.jdField_field_136_of_type_JavaLangString;
  }
  
  public final NetworkPlayer a127()
  {
    return this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer;
  }
  
  public final int g1()
  {
    return this.jdField_field_183_of_type_Int;
  }
  
  public final class_359 a128(SegmentController paramSegmentController)
  {
    class_359 localclass_359 = new class_359(this, paramSegmentController.getUniqueIdentifier());
    for (int i = 0; i < this.jdField_field_136_of_type_JavaUtilArrayList.size(); i++) {
      if (localclass_359.equals(this.jdField_field_136_of_type_JavaUtilArrayList.get(i))) {
        return (class_359)this.jdField_field_136_of_type_JavaUtilArrayList.get(i);
      }
    }
    throw new ShipConfigurationNotFoundException(paramSegmentController.getUniqueIdentifier());
  }
  
  public final ArrayList a75()
  {
    return this.jdField_field_136_of_type_JavaUtilArrayList;
  }
  
  public final Set a114()
  {
    return this.jdField_field_136_of_type_JavaUtilSet;
  }
  
  public final Vector3f e3()
  {
    return this.jdField_field_136_of_type_JavaxVecmathVector3f;
  }
  
  public final class_48 c11()
  {
    return this.jdField_field_139_of_type_Class_48;
  }
  
  public StateInterface getState()
  {
    return this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface;
  }
  
  public String getUniqueIdentifier()
  {
    return "ENTITY_PLAYERSTATE_" + this.jdField_field_136_of_type_JavaLangString;
  }
  
  private void e2()
  {
    Object localObject1;
    while (!this.jdField_field_182_of_type_JavaUtilArrayList.isEmpty())
    {
      localObject1 = (class_1134)this.jdField_field_182_of_type_JavaUtilArrayList.remove(0);
      try
      {
        ((class_1072)getState()).a().a102((class_1134)localObject1, this);
        System.err.println("WROTE BLUEPRINT " + ((class_1134)localObject1).field_1382);
      }
      catch (IOException localIOException1)
      {
        localIOException1;
      }
    }
    Object localObject2;
    while (!this.jdField_field_139_of_type_JavaUtilArrayList.isEmpty())
    {
      localObject1 = (class_1013)this.jdField_field_139_of_type_JavaUtilArrayList.remove(0);
      (localObject2 = new Transform()).setIdentity();
      try
      {
        class_797 localclass_797 = b17();
        ((Transform)localObject2).set(localclass_797.getWorldTransform());
        (localObject1 = class_1216.field_1429.a4(getState(), ((class_1013)localObject1).field_1287, ((class_1013)localObject1).field_1288, (Transform)localObject2, this.jdField_field_136_of_type_Int, 0, this.jdField_field_184_of_type_Int, getUniqueIdentifier(), jdField_field_136_of_type_JavaNioByteBuffer)).a(this.jdField_field_184_of_type_Int, true);
        a38(-((class_1133)localObject1).field_1379.a2());
      }
      catch (NotEnoughCreditsException localNotEnoughCreditsException)
      {
        localNotEnoughCreditsException;
      }
      catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
      {
        localPlayerControlledTransformableNotFound;
      }
      catch (EntityNotFountException localEntityNotFountException1)
      {
        localEntityNotFountException1;
      }
      catch (IOException localIOException2)
      {
        localIOException2;
      }
      catch (ErrorDialogException localErrorDialogException)
      {
        localErrorDialogException;
      }
      catch (EntityAlreadyExistsException localEntityAlreadyExistsException1)
      {
        isOnServer();
        localEntityAlreadyExistsException1.printStackTrace();
      }
      catch (StateParameterNotFoundException localStateParameterNotFoundException1)
      {
        localStateParameterNotFoundException1;
      }
    }
    while (!this.jdField_field_183_of_type_JavaUtilArrayList.isEmpty())
    {
      this.jdField_field_183_of_type_JavaUtilArrayList.remove(0);
      try
      {
        (localObject2 = class_1216.field_1429.a4(getState(), null, null, null, this.jdField_field_136_of_type_Int, 0, this.jdField_field_184_of_type_Int, getUniqueIdentifier(), jdField_field_136_of_type_JavaNioByteBuffer)).a(this.jdField_field_184_of_type_Int, false);
        a38(-((class_1133)localObject2).field_1379.a2());
      }
      catch (EntityNotFountException localEntityNotFountException2)
      {
        localEntityNotFountException2;
      }
      catch (IOException localIOException3)
      {
        localIOException3;
      }
      catch (EntityAlreadyExistsException localEntityAlreadyExistsException2)
      {
        localEntityAlreadyExistsException2;
      }
      catch (StateParameterNotFoundException localStateParameterNotFoundException2)
      {
        localStateParameterNotFoundException2;
      }
    }
  }
  
  public final void a129(ServerMessage paramServerMessage)
  {
    this.jdField_field_186_of_type_JavaUtilArrayList.add(paramServerMessage);
  }
  
  private void a130(NetworkPlayer paramNetworkPlayer)
  {
    NetworkPlayer localNetworkPlayer = paramNetworkPlayer;
    Object localObject1 = this;
    Iterator localIterator = localNetworkPlayer.buyBuffer.getReceiveBuffer().iterator();
    RemoteIntegerArray localRemoteIntegerArray;
    int m;
    Object localObject6;
    int i3;
    Object localObject5;
    while (localIterator.hasNext())
    {
      m = ((Integer)((RemoteField[])(localRemoteIntegerArray = (RemoteIntegerArray)localIterator.next()).get())[0].get()).intValue();
      short s2;
      ElementInformation localElementInformation = ElementKeyMap.getInfo(s2 = (short)((Integer)((RemoteField[])localRemoteIntegerArray.get())[1].get()).intValue());
      if (m <= 0) {
        System.err.println("[SERVER] ERROR: invalid quantity Shopping Buy: " + m + " of " + localElementInformation.getName() + " for " + localObject1);
      }
      System.err.println("[SERVER] Executing Shopping Buy: " + m + " of " + localElementInformation.getName() + " for " + localObject1);
      int i2;
      if ((localObject6 = ((class_748)localObject1).a123()) != null)
      {
        i2 = ((class_743)localObject6).a107(localElementInformation, m);
      }
      else
      {
        System.err.println("Exception no shop in distance found " + localObject1);
        continue;
      }
      if (i2 > ((class_748)localObject1).jdField_field_136_of_type_Int) {
        System.err.println("[SERVER] Executing Shopping Buy: not enough money: " + i2 + "/" + ((class_748)localObject1).jdField_field_136_of_type_Int);
      } else if (localElementInformation.getPrice() > 0L) {
        try
        {
          i3 = ((class_748)localObject1).jdField_field_136_of_type_Class_639.b8(s2, m);
          ((class_748)localObject1).a38(-i2);
          ((class_748)localObject1).sendInventoryModification(i3, null);
          i3 = ((class_743)localObject6).a108().a48(s2, -m);
          ((class_743)localObject6).sendInventoryModification(i3, null);
        }
        catch (NoSlotFreeException localNoSlotFreeException1)
        {
          System.err.println("No more slots free");
          if (((class_748)localObject1).isOnServer())
          {
            localObject5 = new ServerMessage("No space in inventory", 3, ((class_748)localObject1).getId());
            ((class_748)localObject1).a129((ServerMessage)localObject5);
          }
        }
      } else {
        System.err.println("[SERVER] Executing Shopping Buy: not buyable");
      }
    }
    localIterator = localNetworkPlayer.sellBuffer.getReceiveBuffer().iterator();
    while (localIterator.hasNext())
    {
      m = ((Integer)((RemoteField[])(localRemoteIntegerArray = (RemoteIntegerArray)localIterator.next()).get())[0].get()).intValue();
      int k;
      localObject5 = ElementKeyMap.getInfo((short)(k = ((Integer)((RemoteField[])localRemoteIntegerArray.get())[1].get()).intValue()));
      if (m <= 0) {
        System.err.println("[SERVER] ERROR: invalid quantity Shopping Sell: " + m + " of " + ((ElementInformation)localObject5).getName() + " for " + localObject1);
      }
      System.err.println("[SERVER] Executing Shopping Sell: " + m + " of " + ((ElementInformation)localObject5).getName() + " for " + localObject1);
      class_743 localclass_743;
      int i1;
      if ((localclass_743 = ((class_748)localObject1).a123()) != null)
      {
        i1 = localclass_743.a107((ElementInformation)localObject5, m);
      }
      else
      {
        System.err.println("Exception no shop in distance found " + localObject1);
        continue;
      }
      if (((ElementInformation)localObject5).getPrice() > 0L) {
        try
        {
          localObject6 = new IntOpenHashSet();
          i3 = m;
          for (int n = ((class_748)localObject1).jdField_field_136_of_type_Class_639.a42((short)k); (i3 > 0) && (n >= 0); n = ((class_748)localObject1).jdField_field_136_of_type_Class_639.a42((short)k))
          {
            int i;
            if ((i = ((class_748)localObject1).jdField_field_136_of_type_Class_639.a41(n)) < i3)
            {
              ((class_748)localObject1).jdField_field_136_of_type_Class_639.a47(n, (short)k, -i);
              i3 -= i;
            }
            else
            {
              ((class_748)localObject1).jdField_field_136_of_type_Class_639.a47(n, (short)k, -i3);
              i3 = 0;
            }
            ((IntOpenHashSet)localObject6).add(n);
          }
          ((class_748)localObject1).a38(i1);
          ((class_748)localObject1).jdField_field_136_of_type_Class_639.a56((Collection)localObject6);
          i3 = localclass_743.a108().b8((short)k, m);
          localclass_743.sendInventoryModification(i3, null);
        }
        catch (NoSlotFreeException localNoSlotFreeException2)
        {
          if (((class_748)localObject1).isOnServer())
          {
            ServerMessage localServerMessage = new ServerMessage("No space in inventory", 3, ((class_748)localObject1).getId());
            ((class_748)localObject1).a129(localServerMessage);
          }
        }
      }
    }
    Object localObject3;
    if (isOnServer())
    {
      localObject1 = paramNetworkPlayer.dropOrPickupSlots.getReceiveBuffer().iterator();
      Object localObject2;
      Object localObject4;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (RemoteInteger)((Iterator)localObject1).next();
        try
        {
          short s1;
          if (((s1 = getInventory(null).a45(((Integer)((RemoteInteger)localObject2).get()).intValue())) != 0) && ((localObject4 = ((class_1041)this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface).a62().getSector(this.jdField_field_184_of_type_Int)) != null))
          {
            m = getInventory(null).a41(((Integer)((RemoteInteger)localObject2).get()).intValue());
            getInventory(null).b9(((Integer)((RemoteInteger)localObject2).get()).intValue(), s1, 0);
            sendInventoryModification(((Integer)((RemoteInteger)localObject2).get()).intValue(), null);
            localObject2 = b17();
            System.err.println("[SERVER][PLAYER] " + this + " dropping item from " + localObject2);
            localObject2 = ((class_797)localObject2).getWorldTransform();
            localObject2 = new Vector3f(((Transform)localObject2).origin);
            Vector3f localVector3f;
            (localVector3f = new Vector3f(a8())).scale(2.0F);
            ((Vector3f)localObject2).add(localVector3f);
            ((class_670)localObject4).a78().a36((Vector3f)localObject2, s1, m);
          }
        }
        catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound)
        {
          System.err.println("CANNOT DROP ITEM");
          localPlayerControlledTransformableNotFound.printStackTrace();
        }
      }
      localObject1 = paramNetworkPlayer.catalogBuyBuffer.getReceiveBuffer().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (String)((RemoteField[])(localObject2 = (org.schema.schine.network.objects.remote.RemoteStringArray)((Iterator)localObject1).next()).get())[0].get();
        localObject4 = (String)((RemoteField[])localObject2.get())[1].get();
        String str = "#save;";
        System.err.println("[SERVER][PLAYER] RECEIVED SAVE BUY: " + (String)localObject3 + " " + (String)localObject4);
        if (((String)localObject3).startsWith(str))
        {
          if (((localObject2 = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(Integer.parseInt(((String)localObject3).substring(str.length())))) != null) && ((localObject2 instanceof class_747)))
          {
            localObject2 = (class_747)localObject2;
            if ((((Integer)class_1057.field_1332.a3()).intValue() < 0) || (this.jdField_field_136_of_type_Class_787.a1().size() < ((Integer)class_1057.field_1332.a3()).intValue()))
            {
              localObject2 = new class_1134((class_747)localObject2, (String)localObject4);
              this.jdField_field_182_of_type_JavaUtilArrayList.add(localObject2);
            }
            else
            {
              a129(new ServerMessage("Cannot save blueprint:\nout of slots: " + this.jdField_field_136_of_type_Class_787.a1().size() + "/" + class_1057.field_1332.a3(), 3, getId()));
            }
          }
          else
          {
            System.err.println("COULD NOT FIND SHIP WITH ID " + str);
          }
        }
        else
        {
          localObject2 = new class_1013((String)localObject3, (String)localObject4);
          this.jdField_field_139_of_type_JavaUtilArrayList.add(localObject2);
        }
      }
    }
    this.jdField_field_136_of_type_Class_639.a46(paramNetworkPlayer);
    localObject1 = getInventoryNetworkObject().getInventoryMultModBuffer().getReceiveBuffer();
    for (int j = 0; j < ((ObjectArrayList)localObject1).size(); j++)
    {
      localObject3 = (RemoteInventoryMultMod)((ObjectArrayList)localObject1).get(j);
      synchronized (this.jdField_field_184_of_type_JavaUtilArrayList)
      {
        this.jdField_field_184_of_type_JavaUtilArrayList.add(((RemoteInventoryMultMod)localObject3).get());
      }
    }
  }
  
  public final void a38(long paramLong)
  {
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLongArrayList)
    {
      this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLongArrayList.add(paramLong);
      return;
    }
  }
  
  private void b21(NetworkPlayer paramNetworkPlayer)
  {
    paramNetworkPlayer = paramNetworkPlayer.killedBuffer.getReceiveBuffer().iterator();
    while (paramNetworkPlayer.hasNext())
    {
      Object localObject1 = (RemoteIntegerArray)paramNetworkPlayer.next();
      if (!isOnServer())
      {
        int i = ((Integer)((RemoteIntegerArray)localObject1).get(0).get()).intValue();
        int j = ((Integer)((RemoteIntegerArray)localObject1).get(1).get()).intValue();
        Sendable localSendable = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(j);
        Object localObject2;
        if (((localObject2 = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(i)) == null) || (!(localObject2 instanceof class_748)))
        {
          System.err.println("[WARNING] killer could not be determined");
          if (localSendable != null)
          {
            localObject1 = (class_748)localSendable;
            System.err.println("VICTIM " + localObject1 + " DIED");
            class_619.c();
            if (((class_748)localObject1).a7()) {
              ((class_371)getState()).a32(null);
            }
          }
        }
        else if ((localSendable == null) || (!(localSendable instanceof class_748)))
        {
          ((class_371)getState()).a4().c1(getName() + " KILLED A GHOST! \n(unknown player entity #" + ((RemoteIntegerArray)localObject1).get() + ")");
        }
        else
        {
          localObject1 = (class_748)localSendable;
          System.err.println("VICTIM " + localObject1 + " DIED");
          class_619.c();
          if (((class_748)localObject1).a7()) {
            ((class_371)getState()).a32(null);
          }
          if (((class_748)localObject1).getId() == ((Sendable)localObject2).getId())
          {
            ((class_371)getState()).a4().c1(((class_748)localObject1).getName() + " COMMITTED SUICIDE ");
          }
          else
          {
            localObject2 = (class_748)localObject2;
            ((class_371)getState()).a4().c1(((class_748)localObject2).getName() + " KILLED " + ((class_748)localObject1).getName());
          }
        }
      }
      else
      {
        System.err.println("SERVER STATE " + getState() + ", PLAYER " + this + " RECEIVED KILLING COMMAND!");
        b22(0.0F, this);
      }
    }
  }
  
  public final void a13()
  {
    if ((!isOnServer()) && (class_52.c3()) && (Display.isActive()) && (a7()) && (!((class_371)getState()).a14().a18().a77().c()))
    {
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.keyboardOfController.forceClientUpdates();
      for (int i = 0; i < class_367.field_711.length; i++) {
        class_367.field_711[i].a1(this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.keyboardOfController);
      }
    }
  }
  
  public final void b4()
  {
    if ((!isOnServer()) && (class_52.c3()) && (Display.isActive()) && (a7()))
    {
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.camOrientation.forceClientUpdates();
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.mouseOfController.forceClientUpdates();
      if ((((class_371)getState()).a14() != null) && (c3())) {
        this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.setMouseDown();
      }
      if (class_969.a1() != null)
      {
        Transform localTransform = new Transform();
        GlUtil.b6(b18(), localTransform);
        GlUtil.a30(a8(), localTransform);
        GlUtil.d2(c9(), localTransform);
        Quat4f localQuat4f = new Quat4f();
        class_29.a5(localTransform.basis, localQuat4f);
        this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.camOrientation.set(new Vector4f(localQuat4f.field_596, localQuat4f.field_597, localQuat4f.field_598, localQuat4f.field_599));
      }
    }
  }
  
  private void c12(NetworkPlayer paramNetworkPlayer)
  {
    for (int i = 0; i < paramNetworkPlayer.controllerKeyBuffer.getReceiveBuffer().size(); i++)
    {
      RemoteIntegerArray localRemoteIntegerArray = (RemoteIntegerArray)paramNetworkPlayer.controllerKeyBuffer.getReceiveBuffer().get(i);
      Object localObject = (RemoteString)paramNetworkPlayer.controllerKeyNameBuffer.getReceiveBuffer().get(i);
      localObject = new class_359(this, (String)((RemoteString)localObject).get());
      int k;
      if ((k = this.jdField_field_136_of_type_JavaUtilArrayList.indexOf(localObject)) < 0) {
        this.jdField_field_136_of_type_JavaUtilArrayList.add(localObject);
      } else {
        localObject = (class_359)this.jdField_field_136_of_type_JavaUtilArrayList.get(k);
      }
      class_48 localclass_48 = new class_48(((Integer)localRemoteIntegerArray.get(1).get()).intValue(), ((Integer)localRemoteIntegerArray.get(2).get()).intValue(), ((Integer)localRemoteIntegerArray.get(3).get()).intValue());
      int j;
      if ((j = ((Integer)localRemoteIntegerArray.get(0).get()).intValue()) < 0) {
        ((class_359)localObject).a22(Math.abs(j + 1), getState() instanceof ServerStateInterface);
      } else {
        ((class_359)localObject).a20(j, localclass_48, getState() instanceof ServerStateInterface);
      }
      this.jdField_field_183_of_type_Boolean = true;
    }
  }
  
  private void f1()
  {
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShortArrayList)
    {
      for (int i = 0; i < this.jdField_field_136_of_type_ArrayOfBoolean.length; i++) {
        this.jdField_field_136_of_type_ArrayOfBoolean[i] = false;
      }
      for (i = 0; i < this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShortArrayList.size(); i++)
      {
        int k = this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShortArrayList.getShort(i);
        for (int m = 0; m < class_367.field_711.length; m++) {
          if (class_367.field_711[m].a9(Math.abs(k)))
          {
            this.jdField_field_136_of_type_ArrayOfBoolean[m] = (k > 0 ? 1 : false);
            break;
          }
        }
      }
      this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShortArrayList.clear();
    }
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilBytesByteArrayList)
    {
      for (int j = 0; j < this.jdField_field_139_of_type_ArrayOfBoolean.length; j++) {
        this.jdField_field_139_of_type_ArrayOfBoolean[j] = false;
      }
      Iterator localIterator = this.jdField_field_136_of_type_ItUnimiDsiFastutilBytesByteArrayList.iterator();
      while (localIterator.hasNext()) {
        switch (((Byte)localIterator.next()).byteValue())
        {
        case 0: 
          this.jdField_field_139_of_type_ArrayOfBoolean[0] = true;
          break;
        case 1: 
          this.jdField_field_139_of_type_ArrayOfBoolean[1] = true;
          break;
        case 2: 
          this.jdField_field_139_of_type_ArrayOfBoolean[2] = true;
          break;
        case 3: 
          this.jdField_field_139_of_type_ArrayOfBoolean[3] = true;
        }
      }
      this.jdField_field_136_of_type_ItUnimiDsiFastutilBytesByteArrayList.clear();
      return;
    }
  }
  
  private void d5(NetworkPlayer paramNetworkPlayer)
  {
    class_371 localclass_371;
    if ((!isOnServer()) && ((localclass_371 = (class_371)getState()).a20() == this))
    {
      paramNetworkPlayer = paramNetworkPlayer.roundEndBuffer.getReceiveBuffer().iterator();
      while (paramNetworkPlayer.hasNext())
      {
        RemoteIntegerArray localRemoteIntegerArray;
        int j = ((Integer)(localRemoteIntegerArray = (RemoteIntegerArray)paramNetworkPlayer.next()).get(0).get()).intValue();
        int k = ((Integer)localRemoteIntegerArray.get(1).get()).intValue();
        if ((i = ((Integer)localRemoteIntegerArray.get(2).get()).intValue()) < 0) {
          System.out.println("[CLIENT][ROUNDEND] NOBODY HAD THE LAST KILL");
        } else {
          System.out.println("[CLIENT][ROUNDEND] ENTITY " + i + " HAD THE LAST KILL");
        }
        int i = 0;
        synchronized (localclass_371.b())
        {
          Iterator localIterator = localclass_371.b().iterator();
          while (localIterator.hasNext()) {
            if (((class_12)localIterator.next() instanceof class_9)) {
              i = 1;
            }
          }
        }
        if (i == 0)
        {
          ??? = new class_9(localclass_371, j, k);
          localclass_371.b().add(???);
        }
      }
    }
  }
  
  public final void b22(float paramFloat, class_809 paramclass_809)
  {
    if (!isOnServer()) {
      return;
    }
    float f = this.jdField_field_136_of_type_Float;
    this.jdField_field_136_of_type_Float = paramFloat;
    System.err.println("[SERVER] NEW HEALTH IS " + paramFloat);
    if ((paramFloat <= 0.0F) && (f > 0.0F) && (this.jdField_field_184_of_type_Boolean)) {
      try
      {
        a129(new ServerMessage(getName() + " has died from\nlack of HP" + (paramclass_809 != null ? "\nresposible: " + paramclass_809 : ""), 2));
        this.jdField_field_185_of_type_JavaUtilArrayList.add(paramclass_809);
        this.jdField_field_184_of_type_Boolean = false;
        return;
      }
      catch (ErrorDialogException localErrorDialogException)
      {
        localErrorDialogException;
      }
    }
  }
  
  private void e4(NetworkPlayer paramNetworkPlayer)
  {
    if (isOnServer())
    {
      paramNetworkPlayer = paramNetworkPlayer.spawnPointSetBuffer.getReceiveBuffer().iterator();
      while (paramNetworkPlayer.hasNext())
      {
        RemoteVector3f localRemoteVector3f = (RemoteVector3f)paramNetworkPlayer.next();
        ((class_1041)getState()).a59().a49(this, localRemoteVector3f.getVector(), false);
      }
    }
  }
  
  public int hashCode()
  {
    return this.jdField_field_139_of_type_Int;
  }
  
  public final boolean a131(SegmentController paramSegmentController)
  {
    paramSegmentController = new class_359(this, paramSegmentController.getUniqueIdentifier());
    return this.jdField_field_136_of_type_JavaUtilArrayList.contains(paramSegmentController);
  }
  
  public void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    paramNetworkObject = (NetworkPlayer)paramNetworkObject;
    setId(((Integer)paramNetworkObject.field_87.get()).intValue());
    this.jdField_field_182_of_type_Int = ((Integer)paramNetworkObject.clientId.get()).intValue();
    b6(((Integer)paramNetworkObject.credits.get()).intValue());
    a73(paramNetworkObject.sectorPos.getVector());
    this.jdField_field_184_of_type_Int = ((Integer)paramNetworkObject.sectorId.get()).intValue();
    b22(((Float)paramNetworkObject.health.get()).floatValue(), null);
    this.jdField_field_185_of_type_Int = ((Integer)paramNetworkObject.kills.get()).intValue();
    this.jdField_field_186_of_type_Int = ((Integer)paramNetworkObject.deaths.get()).intValue();
    this.jdField_field_136_of_type_JavaLangString = ((String)paramNetworkObject.playerName.get());
    this.jdField_field_136_of_type_Class_850.a130(paramNetworkObject);
    this.jdField_field_136_of_type_Class_744.c();
    this.jdField_field_183_of_type_Int = ((Integer)paramNetworkObject.ping.get()).intValue();
    if ((!field_194) && (getId() < 0)) {
      throw new AssertionError();
    }
    if ((!field_194) && (this.jdField_field_182_of_type_Int < 0)) {
      throw new AssertionError();
    }
    if ((!field_194) && (getState().getId() < 0)) {
      throw new AssertionError();
    }
    if ((!isOnServer()) && (this.jdField_field_182_of_type_Int == this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface.getId()))
    {
      ((class_371)this.jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface).a44(this);
      System.out.println("[PlayerState] Client successfully received player state " + getState() + ", owner: " + this.jdField_field_182_of_type_Int);
    }
  }
  
  public void initialize()
  {
    this.jdField_field_136_of_type_Class_639 = new class_651(this, new class_48());
    this.jdField_field_136_of_type_Class_633 = new class_633(this);
  }
  
  public final boolean a7()
  {
    if (isOnServer()) {
      return false;
    }
    return (((class_371)getState()).a20() != null) && (((class_371)getState()).a20().getId() == getId());
  }
  
  public final boolean a132(class_367 paramclass_367)
  {
    if ((isOnServer()) || (!a7()))
    {
      boolean bool = paramclass_367.a((Short)this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.keyboardOfController.get());
      class_367 localclass_367 = paramclass_367;
      paramclass_367 = this;
      for (int i = 0; i < paramclass_367.jdField_field_136_of_type_ArrayOfBoolean.length; i++) {}
      paramclass_367 = (paramclass_367.jdField_field_136_of_type_ArrayOfBoolean[i] != 0) && (localclass_367 == class_367.field_711[i]) ? 1 : 0;
      return (bool) || (paramclass_367 != 0);
    }
    if ((class_52.c3()) && (Display.isActive()) && (Keyboard.isCreated()))
    {
      if ((!((class_371)getState()).a14().a18().a77().c()) && (((class_371)getState()).a14().a18().c())) {
        return paramclass_367.a6();
      }
      return false;
    }
    return false;
  }
  
  public boolean isMarkedForDeleteVolatile()
  {
    return this.jdField_field_139_of_type_Boolean;
  }
  
  public boolean isMarkedForDeleteVolatileSent()
  {
    return this.jdField_field_185_of_type_Boolean;
  }
  
  public final boolean b2()
  {
    if ((isOnServer()) || (!a7())) {
      return (this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.isMouseDown(0)) || ((0 < this.jdField_field_139_of_type_ArrayOfBoolean.length) && (this.jdField_field_139_of_type_ArrayOfBoolean[0] != 0));
    }
    if ((class_52.c3()) && (Display.isActive()) && (Mouse.isCreated()) && (c3())) {
      return Mouse.isButtonDown(0);
    }
    return false;
  }
  
  public boolean isOnServer()
  {
    return this.jdField_field_182_of_type_Boolean;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public void newNetworkObject()
  {
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer = new NetworkPlayer(getState(), this);
  }
  
  public final void a29(int paramInt1, int paramInt2)
  {
    System.err.println("SERVER NOTIFYING OF ROUND END");
    RemoteIntegerArray localRemoteIntegerArray;
    (localRemoteIntegerArray = new RemoteIntegerArray(3, this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer)).set(0, Integer.valueOf(paramInt1));
    localRemoteIntegerArray.set(1, Integer.valueOf(paramInt2));
    localRemoteIntegerArray.set(2, Integer.valueOf(-1));
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.roundEndBuffer.add(localRemoteIntegerArray);
  }
  
  public final void a133(class_796 paramclass_796)
  {
    this.jdField_field_136_of_type_Class_619.a12(paramclass_796);
  }
  
  public final void a109(Sendable paramSendable)
  {
    Sendable localSendable = paramSendable;
    paramSendable = this;
    if (a7()) {
      ((class_371)paramSendable.getState()).a4().a9(paramSendable, localSendable);
    }
  }
  
  private boolean c3()
  {
    class_443 localclass_443;
    return (!(localclass_443 = ((class_371)getState()).a14().a18().a79().a60()).f1()) && (localclass_443.c()) && (System.currentTimeMillis() - localclass_443.a5() > 400L);
  }
  
  public String printInventories()
  {
    return this.jdField_field_136_of_type_Class_639.toString();
  }
  
  public void sendInventoryModification(int paramInt, class_48 paramclass_48)
  {
    (paramclass_48 = new RemoteIntArray(3, this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer)).set(0, paramInt);
    if (!this.jdField_field_136_of_type_Class_639.a18(paramInt))
    {
      paramclass_48.set(1, this.jdField_field_136_of_type_Class_639.a45(paramInt));
      paramclass_48.set(2, this.jdField_field_136_of_type_Class_639.a41(paramInt));
    }
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.getInventoryUpdateBuffer().add(paramclass_48);
  }
  
  private void b23(int paramInt1, int paramInt2)
  {
    RemoteIntegerArray localRemoteIntegerArray;
    (localRemoteIntegerArray = new RemoteIntegerArray(2, this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer)).set(0, Integer.valueOf(paramInt1));
    localRemoteIntegerArray.set(1, Integer.valueOf(paramInt2));
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.killedBuffer.add(localRemoteIntegerArray);
  }
  
  public final void a55(class_797 paramclass_797)
  {
    this.jdField_field_136_of_type_Class_797 = paramclass_797;
  }
  
  public final void a134(class_750 paramclass_750)
  {
    this.jdField_field_136_of_type_Class_750 = paramclass_750;
  }
  
  public final void a36(int paramInt)
  {
    this.jdField_field_182_of_type_Int = paramInt;
  }
  
  public final void b6(int paramInt)
  {
    this.jdField_field_136_of_type_Int = Math.min(2147483647, Math.max(0, paramInt));
  }
  
  public final void a73(class_48 paramclass_48)
  {
    if (!paramclass_48.equals(this.jdField_field_136_of_type_Class_48)) {
      this.jdField_field_186_of_type_Boolean = true;
    }
    this.jdField_field_136_of_type_Class_48.b1(paramclass_48);
  }
  
  public final void c13(int paramInt)
  {
    this.jdField_field_184_of_type_Int = paramInt;
  }
  
  public final void d6(int paramInt)
  {
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.shipControllerSlot.set(Integer.valueOf(Math.min(10, Math.max(0, paramInt))), true);
  }
  
  public void setId(int paramInt)
  {
    if ((!field_194) && (paramInt == 0)) {
      throw new AssertionError();
    }
    this.jdField_field_139_of_type_Int = paramInt;
  }
  
  public void setMarkedForDeleteVolatile(boolean paramBoolean)
  {
    this.jdField_field_139_of_type_Boolean = paramBoolean;
  }
  
  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
  {
    this.jdField_field_185_of_type_Boolean = paramBoolean;
  }
  
  public final void a106(String paramString)
  {
    this.jdField_field_136_of_type_JavaLangString = paramString;
  }
  
  public final void a37(Vector3f paramVector3f)
  {
    this.jdField_field_136_of_type_JavaxVecmathVector3f.set(paramVector3f);
    System.err.println("[" + (isOnServer() ? "SERVER" : "CLIENT") + "] SPAWNING POINT OF " + this + " SET: " + this.jdField_field_136_of_type_JavaxVecmathVector3f);
  }
  
  public final void c1()
  {
    try
    {
      ((class_371)getState()).a14().a18().a79().a60().a51().b();
      ((class_371)getState()).a14().a18().a79().a60().a51().c2(false);
    }
    catch (ErrorDialogException localErrorDialogException)
    {
      localErrorDialogException;
    }
    b23(getId(), getId());
  }
  
  public String toString()
  {
    return "PlS[" + getName() + "; id(" + getId() + ")(" + this.jdField_field_182_of_type_Int + ")f(" + this.jdField_field_136_of_type_Class_850.a3() + ")]";
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    paramNetworkObject = (NetworkPlayer)paramNetworkObject;
    this.jdField_field_136_of_type_Class_744.d();
    Object localObject2 = null;
    this.jdField_field_136_of_type_ComBulletphysicsLinearmathTransform.basis.set(new Quat4f(this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.camOrientation.getVector()));
    if (!isOnServer())
    {
      b6(((Integer)paramNetworkObject.credits.get()).intValue());
      this.jdField_field_136_of_type_Float = ((Float)paramNetworkObject.health.get()).floatValue();
      int i = this.jdField_field_184_of_type_Int;
      a73(paramNetworkObject.sectorPos.getVector());
      int n = 0;
      this.jdField_field_184_of_type_Int = ((Integer)paramNetworkObject.sectorId.get()).intValue();
      if ((i != this.jdField_field_184_of_type_Int) && (a7())) {
        ((class_371)getState()).a34(i);
      }
      for (int k = 0; k < paramNetworkObject.messages.getReceiveBuffer().size(); k++)
      {
        String[] arrayOfString;
        int i2 = Integer.parseInt((arrayOfString = (localObject1 = ((String)((RemoteString)paramNetworkObject.messages.getReceiveBuffer().get(k)).get()).split("_", 2))[0].split(":"))[0]);
        int i1 = Integer.parseInt(arrayOfString[1]);
        Object localObject1 = localObject1[1];
        localObject1 = new ServerMessage((String)localObject1, i2, i1);
        ((class_371)getState()).c().add(localObject1);
      }
    }
    Object localObject3 = null;
    this.jdField_field_136_of_type_Class_740.c();
    this.jdField_field_136_of_type_Class_742.c();
    this.jdField_field_185_of_type_Int = ((Integer)paramNetworkObject.kills.get()).intValue();
    this.jdField_field_186_of_type_Int = ((Integer)paramNetworkObject.deaths.get()).intValue();
    this.jdField_field_136_of_type_Class_850.b21(paramNetworkObject);
    this.jdField_field_183_of_type_Int = ((Integer)paramNetworkObject.ping.get()).intValue();
    this.jdField_field_136_of_type_JavaxVecmathVector3f.set(this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.spawnPoint.getVector());
    Object localObject4;
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShortArrayList)
    {
      localObject3 = paramNetworkObject.keyboardOfControllerBuffer.getReceiveBuffer().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (RemoteShort)((Iterator)localObject3).next();
        this.jdField_field_136_of_type_ItUnimiDsiFastutilShortsShortArrayList.add((Short)((RemoteShort)localObject4).get());
      }
    }
    synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilBytesByteArrayList)
    {
      localObject3 = paramNetworkObject.mouseOfControllerBuffer.getReceiveBuffer().iterator();
      while (((Iterator)localObject3).hasNext())
      {
        localObject4 = (RemoteByte)((Iterator)localObject3).next();
        this.jdField_field_136_of_type_ItUnimiDsiFastutilBytesByteArrayList.add((Byte)((RemoteByte)localObject4).get());
      }
    }
    a130(paramNetworkObject);
    e4(paramNetworkObject);
    localObject3 = paramNetworkObject;
    ??? = this;
    if ((isOnServer()) && (!((NetworkPlayer)localObject3).spawnRequest.getReceiveBuffer().isEmpty())) {
      synchronized ((localObject3 = (class_1041)((class_748)???).getState()).d4())
      {
        ((class_1041)localObject3).d4().add(???);
      }
    }
    this.jdField_field_136_of_type_Class_619.a5(paramNetworkObject);
    b21(paramNetworkObject);
    d5(paramNetworkObject);
    try
    {
      c12(paramNetworkObject);
    }
    catch (IOException localIOException)
    {
      (??? = localIOException).printStackTrace();
      throw new ErrorDialogException(((IOException)???).getMessage());
    }
    catch (InterruptedException localInterruptedException)
    {
      (??? = localInterruptedException).printStackTrace();
      throw new ErrorDialogException(((InterruptedException)???).getMessage());
    }
    if (isOnServer())
    {
      for (int j = 0;; j++)
      {
        localObject3 = null;
        if (j >= this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.creditsDropBuffer.getReceiveBuffer().size()) {
          break;
        }
        int m = ((Integer)((RemoteInteger)this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.creditsDropBuffer.getReceiveBuffer().get(j)).get()).intValue();
        synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsIntArrayList)
        {
          this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsIntArrayList.add(m);
        }
      }
      if (getState().getLocalAndRemoteObjectContainer().getLocalObjects().containsKey(paramNetworkObject.aquiredTargetId.get()))
      {
        this.jdField_field_136_of_type_Class_797 = ((class_797)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get((Integer)paramNetworkObject.aquiredTargetId.get()));
        return;
      }
      this.jdField_field_136_of_type_Class_797 = null;
    }
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    if (isOnServer()) {
      this.jdField_field_136_of_type_Class_744.a();
    } else {
      this.jdField_field_136_of_type_Class_744.b();
    }
    this.jdField_field_136_of_type_Class_850.b4();
    Object localObject1;
    if (isOnServer())
    {
      localVector3f2 = null;
      (localObject1 = (class_1041)getState()).jdField_field_144_of_type_ItUnimiDsiFastutilIntsIntOpenHashSet.add(this.jdField_field_184_of_type_Int);
      Object localObject2;
      if (!this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsIntArrayList.isEmpty()) {
        synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsIntArrayList)
        {
          while (!this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsIntArrayList.isEmpty())
          {
            int j = this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsIntArrayList.removeInt(this.jdField_field_136_of_type_ItUnimiDsiFastutilIntsIntArrayList.size() - 1);
            int k = j;
            localObject2 = this;
            if ((!field_194) && (!((class_748)localObject2).isOnServer())) {
              throw new AssertionError();
            }
            if (k > 0) {
              try
              {
                Transform localTransform = ((class_748)localObject2).b17().getWorldTransform();
                class_670 localclass_670 = ((class_1041)((class_748)localObject2).jdField_field_136_of_type_OrgSchemaSchineNetworkStateInterface).a62().getSector(((class_748)localObject2).jdField_field_184_of_type_Int);
                Vector3f localVector3f1 = new Vector3f(localTransform.origin);
                (localVector3f2 = new Vector3f(((class_748)localObject2).a8())).scale(2.0F);
                localVector3f1.add(localVector3f2);
                k = Math.min(((class_748)localObject2).jdField_field_136_of_type_Int, k);
                ((class_748)localObject2).a38(-k);
                localclass_670.a78().a36(localVector3f1, (short)-2, k);
              }
              catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound2)
              {
                System.err.println("[SERVER][PLAYERSTATE] CANNOT DROP CREDITS: no transformable for palyer found");
                localPlayerControlledTransformableNotFound2.printStackTrace();
              }
            }
          }
        }
      }
      int i = 0;
      if (this.jdField_field_136_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer == null)
      {
        this.jdField_field_136_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer = ((RegisteredClientOnServer)((class_1041)localObject1).getClients().get(Integer.valueOf(this.jdField_field_182_of_type_Int)));
        i = 1;
      }
      if (this.jdField_field_136_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer != null)
      {
        this.jdField_field_183_of_type_Int = ((int)this.jdField_field_136_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getProcessor().getPingTime());
        if (i != 0)
        {
          this.jdField_field_139_of_type_JavaLangString = this.jdField_field_136_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getProcessor().getIp().substring(0, this.jdField_field_136_of_type_OrgSchemaSchineNetworkRegisteredClientOnServer.getProcessor().getIp().indexOf(":"));
          this.jdField_field_136_of_type_JavaUtilHashSet.add(this.jdField_field_139_of_type_JavaLangString);
          this.jdField_field_139_of_type_Long = System.currentTimeMillis();
        }
      }
      this.jdField_field_182_of_type_Class_48.b1(this.jdField_field_136_of_type_Class_48);
      try
      {
        localObject2 = b17();
        this.jdField_field_139_of_type_JavaxVecmathVector3f.set(((class_797)localObject2).getWorldTransform().origin);
      }
      catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound1) {}
      e2();
      d2();
    }
    this.jdField_field_136_of_type_Class_787.a();
    class_748 localclass_748;
    if (this.jdField_field_186_of_type_Boolean)
    {
      if (isOnServer())
      {
        localclass_748 = this;
        if (isOnServer())
        {
          localclass_748.jdField_field_136_of_type_Class_653.a5();
          localclass_748.jdField_field_136_of_type_Class_659.a4();
        }
      }
      localVector3f2 = null;
      this.field_192 = class_791.b38(this.jdField_field_136_of_type_Class_48);
      this.jdField_field_183_of_type_Long = -1L;
      this.jdField_field_186_of_type_Boolean = false;
    }
    Vector3f localVector3f2 = null;
    this.jdField_field_136_of_type_JavaUtilSet.clear();
    f1();
    try
    {
      this.jdField_field_136_of_type_Class_742.d();
      this.jdField_field_136_of_type_Class_740.d();
      this.jdField_field_136_of_type_Class_619.a10(paramclass_941);
    }
    catch (ZipException localZipException)
    {
      localZipException;
      if (isOnServer()) {
        ((class_1041)getState()).a59().broadcastMessage("[UPLOAD][ERROR] critical error while extracting upload file!", 3);
      }
    }
    catch (Exception localException)
    {
      (localObject1 = localException).printStackTrace();
      if (!isOnServer()) {
        throw new ErrorDialogException(((Exception)localObject1).getMessage());
      }
    }
    if ((!this.jdField_field_136_of_type_JavaUtilSet.isEmpty()) && (!isOnServer())) {
      class_971.field_98.add("PLAYER " + this + " IS IN SHOP DISTANCE " + ((class_371)getState()).a3() + "; " + ((class_371)getState()).a25() + "; " + ((class_371)getState()).a6());
    }
    if (this.field_192) {
      try
      {
        b17();
        localclass_748 = this;
        paramclass_941 = null;
        class_48 localclass_48 = class_789.a192(this.jdField_field_136_of_type_Class_48, new class_48());
        class_808 localclass_808 = class_808.values()[localclass_748.jdField_field_136_of_type_Class_659.a6(localclass_748.jdField_field_136_of_type_Class_659.a5(localclass_48))];
        if (localclass_808 == class_808.field_1070)
        {
          if (localclass_748.jdField_field_183_of_type_Long < 0L) {
            localclass_748.jdField_field_183_of_type_Long = System.currentTimeMillis();
          } else if (localclass_748.a7()) {
            ((class_371)localclass_748.getState()).a4().b1("WARNING\nSpace time disruption\ndetected");
          }
          long l4 = System.currentTimeMillis();
          if (localclass_748.a7()) {
            if (l4 - localclass_748.jdField_field_183_of_type_Long > 19000L) {
              ((class_371)localclass_748.getState()).a4().b1("WARNING\nYour space time is warping!\n1 second left");
            } else if (l4 - localclass_748.jdField_field_183_of_type_Long > 15000L) {
              ((class_371)localclass_748.getState()).a4().b1("WARNING\nYour space time is warping!\n5 seconds left");
            } else if (l4 - localclass_748.jdField_field_183_of_type_Long > 10000L) {
              ((class_371)localclass_748.getState()).a4().b1("WARNING\nYour space time is warping!\n10 seconds left");
            } else if (l4 - localclass_748.jdField_field_183_of_type_Long > 5000L) {
              ((class_371)localclass_748.getState()).a4().b1("WARNING\nYour space time is warping!\n15 seconds left");
            }
          }
          if ((localclass_748.isOnServer()) && (l4 - localclass_748.jdField_field_183_of_type_Long > 20000L))
          {
            localclass_748.jdField_field_183_of_type_Long = 0L;
            try
            {
              ((class_1041)localclass_748.getState()).a59().a45(localclass_748.b17(), new class_48(localclass_748.jdField_field_136_of_type_Class_48.field_475 + 60, localclass_748.jdField_field_136_of_type_Class_48.field_476 + 60, localclass_748.jdField_field_136_of_type_Class_48.field_477 + 60), 1);
            }
            catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound3)
            {
              localPlayerControlledTransformableNotFound3;
            }
          }
        }
      }
      catch (PlayerControlledTransformableNotFound localPlayerControlledTransformableNotFound4) {}
    }
    if ((isOnServer()) && (!this.jdField_field_184_of_type_Boolean))
    {
      System.err.println("Revived PlayerState " + this);
      this.jdField_field_136_of_type_Float = 300.0F;
      this.jdField_field_184_of_type_Boolean = true;
    }
    if (isOnServer())
    {
      this.jdField_field_136_of_type_Class_633.a2();
      while (!this.jdField_field_186_of_type_JavaUtilArrayList.isEmpty())
      {
        localObject1 = (ServerMessage)this.jdField_field_186_of_type_JavaUtilArrayList.remove(0);
        this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.messages.add(new RemoteString(((ServerMessage)localObject1).type + ":" + ((ServerMessage)localObject1).receiverPlayerId + "_" + ((ServerMessage)localObject1).message, this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer));
      }
      if (!this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLongArrayList.isEmpty())
      {
        long l1 = 0L;
        synchronized (this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLongArrayList)
        {
          for (int m = 0; m < this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLongArrayList.size(); m++)
          {
            long l3 = this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLongArrayList.getLong(m);
            l1 += l3;
          }
          this.jdField_field_136_of_type_ItUnimiDsiFastutilLongsLongArrayList.clear();
        }
        localVector3f2 = null;
        long l2;
        if ((l2 = this.jdField_field_136_of_type_Int + l1) > 2147483647L)
        {
          a129(new ServerMessage("WARNING:\nCannot hold more credits", 3, getId()));
          l2 = 2147483647L;
        }
        b6((int)l2);
      }
    }
    if ((this.jdField_field_183_of_type_Boolean) && (!isOnServer()) && (((class_371)getState()).a28()))
    {
      ((class_371)getState()).a14().a18().a79().a62().b();
      this.jdField_field_183_of_type_Boolean = false;
    }
    if (!this.jdField_field_184_of_type_JavaUtilArrayList.isEmpty()) {
      synchronized (this.jdField_field_184_of_type_JavaUtilArrayList)
      {
        ArrayList localArrayList = new ArrayList();
        while (!this.jdField_field_184_of_type_JavaUtilArrayList.isEmpty())
        {
          class_645 localclass_645 = (class_645)this.jdField_field_184_of_type_JavaUtilArrayList.remove(0);
          getInventoryNetworkObject();
          getInventory(null).a58(localclass_645);
        }
        if (!localArrayList.isEmpty()) {
          this.jdField_field_184_of_type_JavaUtilArrayList.addAll(localArrayList);
        }
      }
    }
    synchronized (this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer)
    {
      updateToNetworkObject();
      return;
    }
  }
  
  public void updateToFullNetworkObject()
  {
    if ((!field_194) && (getId() < 0)) {
      throw new AssertionError();
    }
    if ((!field_194) && (this.jdField_field_182_of_type_Int < 0)) {
      throw new AssertionError();
    }
    if ((!field_194) && (getState().getId() < 0)) {
      throw new AssertionError();
    }
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.field_87.set(Integer.valueOf(getId()));
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.clientId.set(Integer.valueOf(this.jdField_field_182_of_type_Int));
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.playerName.set(this.jdField_field_136_of_type_JavaLangString);
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.credits.set(Integer.valueOf(this.jdField_field_136_of_type_Int));
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.health.set(Float.valueOf(this.jdField_field_136_of_type_Float));
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.kills.set(Integer.valueOf(this.jdField_field_185_of_type_Int));
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.deaths.set(Integer.valueOf(this.jdField_field_186_of_type_Int));
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.ping.set(Integer.valueOf(this.jdField_field_183_of_type_Int));
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.sectorId.set(Integer.valueOf(this.jdField_field_184_of_type_Int));
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.sectorPos.set(this.jdField_field_136_of_type_Class_48);
    this.jdField_field_136_of_type_Class_850.d2();
    this.jdField_field_136_of_type_Class_744.e();
    this.jdField_field_136_of_type_Class_639.b10(this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer);
    if (isOnServer()) {
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.serverStartTime.set(Long.valueOf(((class_1041)getState()).a18()));
    }
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.spawnPoint.set(this.jdField_field_136_of_type_JavaxVecmathVector3f);
    this.jdField_field_136_of_type_Class_619.a3();
    synchronized (this.jdField_field_136_of_type_JavaUtilArrayList)
    {
      Iterator localIterator = this.jdField_field_136_of_type_JavaUtilArrayList.iterator();
      while (localIterator.hasNext()) {
        ((class_359)localIterator.next()).a13();
      }
    }
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.setChanged(true);
  }
  
  public void updateToNetworkObject()
  {
    this.jdField_field_136_of_type_Class_744.e();
    if (isOnServer())
    {
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.field_87.set(Integer.valueOf(getId()));
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.spawnPoint.set(this.jdField_field_136_of_type_JavaxVecmathVector3f);
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.credits.set(Integer.valueOf(this.jdField_field_136_of_type_Int));
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.health.set(Float.valueOf(this.jdField_field_136_of_type_Float));
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.kills.set(Integer.valueOf(this.jdField_field_185_of_type_Int));
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.deaths.set(Integer.valueOf(this.jdField_field_186_of_type_Int));
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.ping.set(Integer.valueOf(this.jdField_field_183_of_type_Int));
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.sectorId.set(Integer.valueOf(this.jdField_field_184_of_type_Int));
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.sectorPos.set(this.jdField_field_136_of_type_Class_48);
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.health.set(Float.valueOf(this.jdField_field_136_of_type_Float));
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.serverModTime.set(Long.valueOf(((class_1041)getState()).b9()));
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.isAdminClient.set(Boolean.valueOf(((class_1041)getState()).a59().a44(getName())));
    }
    this.jdField_field_136_of_type_Class_850.e2();
    if (a7())
    {
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.selectedEntityId.forceClientUpdates();
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.aquiredTargetId.forceClientUpdates();
      class_797 localclass_797 = ((class_371)getState()).a14().a18().a79().a60().a43();
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.selectedEntityId.set(Integer.valueOf(localclass_797 == null ? -1 : localclass_797.getId()));
      this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.aquiredTargetId.set(Integer.valueOf(this.jdField_field_136_of_type_Class_797 == null ? -1 : this.jdField_field_136_of_type_Class_797.getId()));
    }
  }
  
  public final void a135(class_365 paramclass_365)
  {
    this.jdField_field_136_of_type_Class_619.a11(paramclass_365);
  }
  
  public class_643 getInventoryNetworkObject()
  {
    return this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer;
  }
  
  public final class_653 a136()
  {
    return this.jdField_field_136_of_type_Class_653;
  }
  
  public final class_659 a137()
  {
    return this.jdField_field_136_of_type_Class_659;
  }
  
  public HashMap getInventories()
  {
    HashMap localHashMap;
    (localHashMap = new HashMap(1)).put(new class_48(), this.jdField_field_136_of_type_Class_639);
    return localHashMap;
  }
  
  public final class_740 a138()
  {
    return this.jdField_field_136_of_type_Class_740;
  }
  
  public final class_742 a139()
  {
    return this.jdField_field_136_of_type_Class_742;
  }
  
  public final class_744 a140()
  {
    return this.jdField_field_136_of_type_Class_744;
  }
  
  public final int h1()
  {
    return this.jdField_field_136_of_type_Class_850.a3();
  }
  
  public final class_850 a141()
  {
    return this.jdField_field_136_of_type_Class_850;
  }
  
  public final long a28()
  {
    return this.jdField_field_185_of_type_Long;
  }
  
  public final class_762 a142(int paramInt)
  {
    return this.jdField_field_136_of_type_Class_850.a142(paramInt);
  }
  
  public void sendInventoryModification(Collection paramCollection, class_48 paramclass_48)
  {
    class_639 localclass_639 = getInventory(paramclass_48);
    paramCollection = new class_645(paramCollection, localclass_639, paramclass_48);
    this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer.getInventoryMultModBuffer().add(new RemoteInventoryMultMod(paramCollection, this.jdField_field_136_of_type_OrgSchemaGameNetworkObjectsNetworkPlayer));
  }
  
  public void destroyPersistent()
  {
    if ((!field_194) && (!isOnServer())) {
      throw new AssertionError();
    }
    String str = "ENTITY_PLAYERSTATE_" + getName();
    new File(class_1041.jdField_field_144_of_type_JavaLangString + str + ".ent").delete();
  }
  
  public boolean isMarkedForPermanentDelete()
  {
    return this.field_193;
  }
  
  public void markedForPermanentDelete(boolean paramBoolean)
  {
    this.field_193 = paramBoolean;
  }
  
  public final long b14()
  {
    return this.jdField_field_139_of_type_Long;
  }
  
  public final long c14()
  {
    return this.jdField_field_182_of_type_Long;
  }
  
  public final HashSet a143()
  {
    return this.jdField_field_136_of_type_JavaUtilHashSet;
  }
  
  public boolean isUpdatable()
  {
    return true;
  }
  
  public final String a()
  {
    return this.jdField_field_139_of_type_JavaLangString;
  }
  
  static
  {
    new Vector3f(1.0F, 1.0F, 1.0F).lengthSquared();
    jdField_field_136_of_type_JavaNioByteBuffer = ByteBuffer.allocate(10240);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_748
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */