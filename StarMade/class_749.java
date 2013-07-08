import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.network.objects.NetworkSegmentProvider;
import org.schema.game.network.objects.remote.RemoteControlStructure;
import org.schema.game.network.objects.remote.RemoteControlStructureBuffer;
import org.schema.game.network.objects.remote.RemoteInventory;
import org.schema.game.network.objects.remote.RemoteInventoryBuffer;
import org.schema.game.network.objects.remote.RemoteSegmentRemoteObj;
import org.schema.game.network.objects.remote.RemoteSegmentSignature;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.objects.remote.RemoteVector3i;
import org.schema.schine.network.server.ServerStateInterface;

public class class_749
  implements Sendable
{
  private class_753 jdField_field_34_of_type_Class_753;
  private StateInterface jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface;
  private final boolean jdField_field_34_of_type_Boolean;
  private NetworkSegmentProvider jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
  private boolean field_35 = false;
  private int jdField_field_34_of_type_Int = -123123;
  private boolean field_36;
  private boolean field_187;
  private boolean field_188 = true;
  
  public class_749(StateInterface paramStateInterface)
  {
    this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface = paramStateInterface;
    this.jdField_field_34_of_type_Boolean = (paramStateInterface instanceof ServerStateInterface);
  }
  
  public void cleanUpOnEntityDelete() {}
  
  public int getId()
  {
    return this.jdField_field_34_of_type_Int;
  }
  
  public final NetworkSegmentProvider a47()
  {
    return this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
  }
  
  public final class_753 a48()
  {
    if (this.jdField_field_34_of_type_Class_753 == null) {
      this.jdField_field_34_of_type_Class_753 = ((class_753)this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface.getLocalAndRemoteObjectContainer().getLocalObjects().get(getId()));
    }
    return this.jdField_field_34_of_type_Class_753;
  }
  
  public StateInterface getState()
  {
    return this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface;
  }
  
  private void a49(NetworkSegmentProvider paramNetworkSegmentProvider)
  {
    synchronized (paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer())
    {
      if (!paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer().isEmpty()) {
        for (int i = 0; i < paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer().size(); i++)
        {
          class_802 localclass_802 = (class_802)((RemoteSegmentSignature)paramNetworkSegmentProvider.signatureBuffer.getReceiveBuffer().get(i)).get();
          synchronized (localObjectOpenHashSet = ((class_20)a48().getSegmentProvider()).b())
          {
            ObjectOpenHashSet localObjectOpenHashSet;
            if ((!field_189) && (localclass_802.jdField_field_1059_of_type_Class_48 == null)) {
              throw new AssertionError();
            }
            localObjectOpenHashSet.add(localclass_802);
          }
        }
      }
      return;
    }
  }
  
  public void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    setId(((Integer)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.field_87.get()).intValue());
  }
  
  public void initialize() {}
  
  public final boolean a1()
  {
    return (this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider != null) && (((Boolean)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.get()).booleanValue());
  }
  
  public boolean isMarkedForDeleteVolatile()
  {
    return this.field_36;
  }
  
  public boolean isMarkedForDeleteVolatileSent()
  {
    return this.field_187;
  }
  
  public boolean isOnServer()
  {
    return this.jdField_field_34_of_type_Boolean;
  }
  
  public void newNetworkObject()
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider = new NetworkSegmentProvider(this.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface, this);
    if (((this.jdField_field_34_of_type_Class_753 instanceof class_798)) && ((((class_798)this.jdField_field_34_of_type_Class_753).a() instanceof class_635)))
    {
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer = new RemoteInventoryBuffer(((class_798)this.jdField_field_34_of_type_Class_753).a(), this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider);
      return;
    }
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer = new RemoteInventoryBuffer(null, this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider);
  }
  
  public final void a2()
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.set(Boolean.valueOf(true));
  }
  
  public void setId(int paramInt)
  {
    this.jdField_field_34_of_type_Int = paramInt;
  }
  
  public void setMarkedForDeleteVolatile(boolean paramBoolean)
  {
    this.field_36 = paramBoolean;
  }
  
  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
  {
    this.field_187 = paramBoolean;
  }
  
  public final void a50(class_753 paramclass_753)
  {
    this.jdField_field_34_of_type_Class_753 = paramclass_753;
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    if (a48() == null)
    {
      System.err.println("[SendableSegmentProvider] no longer updating: missing segment controller: " + getId() + ": " + getState());
      return;
    }
    a49((NetworkSegmentProvider)paramNetworkObject);
    setId(((Integer)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.field_87.get()).intValue());
    if (isOnServer())
    {
      class_749 localclass_749;
      NetworkSegmentProvider localNetworkSegmentProvider;
      class_672 localclass_6722;
      for (int i = 0; i < ((NetworkSegmentProvider)paramNetworkObject).signatureRequestBuffer.getReceiveBuffer().size(); i++)
      {
        class_48 localclass_48 = ((RemoteVector3i)((NetworkSegmentProvider)paramNetworkObject).signatureRequestBuffer.getReceiveBuffer().get(i)).getVector();
        localclass_749 = this;
        try
        {
          class_672 localclass_6721;
          if ((localclass_6721 = (class_672)localclass_749.a48().getSegmentFromCache(localclass_48.field_475, localclass_48.field_476, localclass_48.field_477)) != null)
          {
            localNetworkSegmentProvider = localclass_749.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
            System.currentTimeMillis();
            class_672.d();
            synchronized (localNetworkSegmentProvider)
            {
              localNetworkSegmentProvider.signatureBuffer.add(new RemoteSegmentSignature(new class_802(new class_48(localclass_6721.field_34), localclass_6721.a44(), localclass_6721.a15().getId(), localclass_6721.g()), localNetworkSegmentProvider));
            }
          }
          localNetworkSegmentProvider = localclass_749.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
          ((class_1041)localclass_749.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface).a59().b10(localclass_749.a48(), new class_48((class_48)???), localNetworkSegmentProvider);
        }
        catch (Exception localException1)
        {
          localclass_6722 = null;
          localException1.printStackTrace();
          System.err.println("[SendableSegmentProvider] Exception catched for ID: " + localclass_749.a48() + "; if null, the segmentcontroller has probably been removed (id for both: " + localclass_749.getId() + ")");
        }
      }
      for (i = 0; i < ((NetworkSegmentProvider)paramNetworkObject).segmentRequestBuffer.getReceiveBuffer().size(); i++)
      {
        ??? = ((RemoteVector3i)((NetworkSegmentProvider)paramNetworkObject).segmentRequestBuffer.getReceiveBuffer().get(i)).getVector();
        localclass_749 = this;
        try
        {
          if ((localclass_6722 = (class_672)localclass_749.a48().getSegmentFromCache(((class_48)???).field_475, ((class_48)???).field_476, ((class_48)???).field_477)) != null)
          {
            localNetworkSegmentProvider = localclass_749.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
            (??? = new class_802(new class_48(localclass_6722.field_34), localclass_6722.a44(), localclass_6722.a15().getId(), localclass_6722.g())).jdField_field_1059_of_type_OrgSchemaGameCommonControllerSegmentController = localclass_749.a48();
            synchronized (localNetworkSegmentProvider)
            {
              localNetworkSegmentProvider.segmentBuffer.add(new RemoteSegmentRemoteObj((class_802)???, localNetworkSegmentProvider));
            }
          }
          localNetworkSegmentProvider = localObject1.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider;
          ((class_1041)localObject1.jdField_field_34_of_type_OrgSchemaSchineNetworkStateInterface).a59().a47(localObject1.a48(), new class_48((class_48)???), localNetworkSegmentProvider);
        }
        catch (Exception localException2)
        {
          ??? = null;
          localException2.printStackTrace();
        }
      }
    }
    if ((isOnServer()) && (!this.field_35) && (((Boolean)this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.requestedInitialControlMap.get()).booleanValue()))
    {
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.initialControlMap.add(new RemoteControlStructure(this, isOnServer()));
      this.field_35 = true;
    }
    if ((!isOnServer()) && ((this.jdField_field_34_of_type_Class_753 instanceof class_798)) && ((((class_798)this.jdField_field_34_of_type_Class_753).a() instanceof class_635)))
    {
      ManagerContainer localManagerContainer = ((class_798)this.jdField_field_34_of_type_Class_753).a();
      if (this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer.getReceiveBuffer().size() > 0)
      {
        System.err.println("[CLIENT] RECEIVED INITIAL INVETORY LIST FOR " + this.jdField_field_34_of_type_Class_753);
        localManagerContainer.handleInventoryFromNT(this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer);
      }
    }
    if ((isOnServer()) && (((Boolean)((NetworkSegmentProvider)paramNetworkObject).signalDelete.get()).booleanValue())) {
      setMarkedForDeleteVolatile(true);
    }
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    if ((a1()) && (this.field_188))
    {
      synchronized ((paramclass_941 = ((class_798)this.jdField_field_34_of_type_Class_753).a()).getInventories())
      {
        Iterator localIterator = paramclass_941.getInventories().values().iterator();
        while (localIterator.hasNext())
        {
          class_639 localclass_639 = (class_639)localIterator.next();
          this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.invetoryBuffer.add(new RemoteInventory(localclass_639, paramclass_941, true, this.jdField_field_34_of_type_Boolean));
        }
      }
      this.field_188 = false;
    }
  }
  
  public final void b3()
  {
    if (a1())
    {
      this.field_188 = true;
      if (isOnServer()) {
        ((class_1041)getState()).a65(this);
      }
    }
  }
  
  public void updateToFullNetworkObject()
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.field_87.set(Integer.valueOf(getId()));
    if (isOnServer()) {
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.set(Boolean.valueOf(true));
    }
  }
  
  public void updateToNetworkObject()
  {
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.field_87.set(Integer.valueOf(getId()));
    if (isOnServer()) {
      this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.connectionReady.set(Boolean.valueOf(true));
    }
  }
  
  public final void c1()
  {
    if (((this.jdField_field_34_of_type_Class_753 instanceof class_798)) && ((((class_798)this.jdField_field_34_of_type_Class_753).a() instanceof class_635))) {
      ((class_52)getState().getController()).a21(this.jdField_field_34_of_type_Class_753.getId());
    }
  }
  
  public final void d()
  {
    a48().getControlElementMap().setFlagRequested(true);
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.requestedInitialControlMap.forceClientUpdates();
    this.jdField_field_34_of_type_OrgSchemaGameNetworkObjectsNetworkSegmentProvider.requestedInitialControlMap.set(Boolean.valueOf(true), true);
  }
  
  public void destroyPersistent() {}
  
  public boolean isMarkedForPermanentDelete()
  {
    return false;
  }
  
  public void markedForPermanentDelete(boolean paramBoolean) {}
  
  public boolean isUpdatable()
  {
    return true;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_749
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */