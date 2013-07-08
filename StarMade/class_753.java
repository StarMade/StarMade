import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayFIFOQueue;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.PrintStream;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ManagerContainer;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.PowerManagerInterface;
import org.schema.game.common.controller.elements.ShieldContainerInterface;
import org.schema.game.common.controller.elements.shield.ShieldCollectionManager;
import org.schema.game.common.data.element.ControlElementMap;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.SegmentData;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.game.network.objects.NetworkSegmentProvider;
import org.schema.game.network.objects.remote.RemoteSegmentPiece;
import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.SynchronizationContainerController;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
import org.schema.schine.network.objects.remote.RemoteString;
import org.schema.schine.network.objects.remote.RemoteVector3f;
import org.schema.schine.network.objects.remote.RemoteVector3i;
import org.schema.schine.network.objects.remote.RemoteVector4i;

public abstract class class_753
  extends SegmentController
  implements Sendable
{
  private NetworkSegmentController networkEntity;
  private boolean first = true;
  private final ObjectArrayFIFOQueue blockActivationBuffer = new ObjectArrayFIFOQueue();
  private int lastModifierId;
  private boolean lastModifierChanged;
  private class_749 serverSendableSegmentProvider;
  
  public class_753(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
    getControlElementMap().setSendableSegmentController(this);
  }
  
  public ObjectArrayFIFOQueue getBlockActivationBuffer()
  {
    return this.blockActivationBuffer;
  }
  
  public NetworkSegmentController getNetworkObject()
  {
    return this.networkEntity;
  }
  
  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, class_809 paramclass_809, float paramFloat) {}
  
  public void handleHitMissile(class_597 paramclass_597, Transform paramTransform) {}
  
  public void handleNTDockChanged()
  {
    getDockingController().a6(getNetworkObject());
  }
  
  private void handleReceivedBlockActivations(NetworkSegmentController paramNetworkSegmentController)
  {
    for (int i = 0; i < paramNetworkSegmentController.blockActivationBuffer.getReceiveBuffer().size(); i++)
    {
      class_46 localclass_46 = ((RemoteVector4i)paramNetworkSegmentController.blockActivationBuffer.getReceiveBuffer().get(i)).getVector(new class_46());
      synchronized (getBlockActivationBuffer())
      {
        System.err.println("[SERVER] RECEIVED BLOCK ACTIVATION");
        getBlockActivationBuffer().enqueue(localclass_46);
      }
    }
  }
  
  private void handleReceivedControllers(NetworkSegmentController paramNetworkSegmentController)
  {
    getControlElementMap().handleReceived();
  }
  
  private void handleReceivedDirty(NetworkSegmentController paramNetworkSegmentController)
  {
    if (!isOnServer()) {
      synchronized (paramNetworkSegmentController.dirtySegmentBuffer.getReceiveBuffer())
      {
        for (int i = 0; i < paramNetworkSegmentController.dirtySegmentBuffer.getReceiveBuffer().size(); i++)
        {
          class_48 localclass_48 = ((RemoteVector3i)paramNetworkSegmentController.dirtySegmentBuffer.getReceiveBuffer().get(i)).getVector(new class_48());
          Segment localSegment;
          if ((localSegment = getSegmentBuffer().a5(localclass_48)) != null)
          {
            assert (localclass_48.equals(localSegment.field_34));
            ((class_672)localSegment).a46(-1L);
            ((class_20)getSegmentProvider()).a19(localclass_48);
          }
          else
          {
            System.err.println("[CLIENT] WARNING: received dirty null segment " + localclass_48 + " on " + this);
          }
        }
        return;
      }
    }
  }
  
  private void handleReceivedExplosions(NetworkSegmentController paramNetworkSegmentController)
  {
    if (((class_371)getState()).a8() == getSectorId()) {
      for (int i = 0; i < paramNetworkSegmentController.explosions.getReceiveBuffer().size(); i++)
      {
        Vector3f localVector3f = ((RemoteVector3f)paramNetworkSegmentController.explosions.getReceiveBuffer().get(i)).getVector();
        ((class_371)getState()).a27().a91().a23(localVector3f, 15.0F);
        Transform localTransform;
        (localTransform = new Transform()).setIdentity();
        localTransform.origin.set(localVector3f);
        class_969.a8("0022_explosion", localTransform, 10.0F);
      }
    }
  }
  
  private void handleReceivedHarvestConnections(NetworkSegmentController paramNetworkSegmentController) {}
  
  protected void handleReceivedModifications(NetworkSegmentController paramNetworkSegmentController)
  {
    if (paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().size() > 32) {
      System.err.println(getState() + "; " + this + " [WARNING] !!!!! BIG MODIFICATION RECEIVED: " + paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().size());
    }
    synchronized (paramNetworkSegmentController.modificationBuffer.getReceiveBuffer())
    {
      for (int i = 0; i < paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().size(); i++)
      {
        class_796 localclass_796 = (class_796)((RemoteSegmentPiece)paramNetworkSegmentController.modificationBuffer.getReceiveBuffer().get(i)).get();
        assert ((!(getState() instanceof class_1041)) || (localclass_796 != null)) : "Implication: [serverState -> segmentPiece not null] failed. server pieces must not be null";
        if (localclass_796 != null)
        {
          if ((this instanceof class_784)) {
            ((class_784)this).a(true);
          }
          localclass_796.a7().a18(localclass_796);
        }
        if (isOnServer()) {
          paramNetworkSegmentController.modificationBuffer.add(new RemoteSegmentPiece(localclass_796, this, getNetworkObject()));
        }
      }
      return;
    }
  }
  
  public void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    super.initFromNetworkObject(paramNetworkObject);
    paramNetworkObject = (NetworkSegmentController)paramNetworkObject;
    if (!isOnServer())
    {
      getMinPos().b1(paramNetworkObject.minSize.getVector());
      getMaxPos().b1(paramNetworkObject.maxSize.getVector());
      if (((this instanceof class_798)) && ((((class_798)this).a() instanceof PowerManagerInterface))) {
        ((PowerManagerInterface)((class_798)this).a()).getPowerAddOn().setInitialPower(getNetworkObject().initialPower.getLong());
      }
      if (((this instanceof class_798)) && ((((class_798)this).a() instanceof ShieldContainerInterface))) {
        ((ShieldContainerInterface)((class_798)this).a()).getShieldManager().setInitialShields(getNetworkObject().initialShields.getLong());
      }
    }
    setRealName((String)paramNetworkObject.realName.get());
    setUniqueIdentifier((String)getNetworkObject().uniqueIdentifier.get());
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public void newNetworkObject() {}
  
  public void onCollision(ManifoldPoint paramManifoldPoint, Sendable paramSendable) {}
  
  public void cleanUpOnEntityDelete()
  {
    if (!isOnServer())
    {
      class_749 localclass_749;
      if (((localclass_749 = ((class_20)getSegmentProvider()).a16()) != null) && (localclass_749.a47() != null)) {
        localclass_749.a47().signalDelete.set(Boolean.valueOf(true), true);
      }
    }
    else if (this.serverSendableSegmentProvider != null)
    {
      this.serverSendableSegmentProvider.markedForPermanentDelete(true);
      this.serverSendableSegmentProvider = null;
    }
    super.cleanUpOnEntityDelete();
  }
  
  public void handleMovingInput(class_941 paramclass_941, class_755 paramclass_755) {}
  
  public String toNiceString()
  {
    return null;
  }
  
  public void setNetworkObject(NetworkSegmentController paramNetworkSegmentController)
  {
    this.networkEntity = paramNetworkSegmentController;
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    super.updateFromNetworkObject(paramNetworkObject);
    paramNetworkObject = (NetworkSegmentController)paramNetworkObject;
    handleReceivedModifications(paramNetworkObject);
    handleReceivedDirty(paramNetworkObject);
    handleReceivedBlockActivations(paramNetworkObject);
    getControlElementMap().handleReceived();
    if (!isOnServer())
    {
      if (getSectorId() == ((class_371)getState()).a8()) {
        handleReceivedExplosions(paramNetworkObject);
      }
      paramNetworkObject.minSize.getVector(getMinPos());
      paramNetworkObject.maxSize.getVector(getMaxPos());
    }
    else if (this.lastModifierId != paramNetworkObject.lastModifiedPlayerId.getInt())
    {
      int i = this.lastModifierId;
      this.lastModifierId = paramNetworkObject.lastModifiedPlayerId.getInt();
      System.err.println("[SERVER][SEGMENTCONTROLLER] LAST MODIFIER CHANGED TO PID: " + this.lastModifierId + " (from " + i + ")");
      this.lastModifierChanged = true;
    }
    setRealName((String)paramNetworkObject.realName.get());
    getDockingController().b2(paramNetworkObject);
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    Object localObject2;
    if (this.first)
    {
      if (!isOnServer())
      {
        long l1 = System.currentTimeMillis();
        (localObject2 = new class_749(getState())).initialize();
        ((class_749)localObject2).a50(this);
        ((class_749)localObject2).setId(getId());
        ((class_20)getSegmentProvider()).a14((class_749)localObject2);
        ((class_52)getState().getController()).a4().addNewSynchronizedObjectQueued((Sendable)localObject2);
        long l4;
        if ((l4 = System.currentTimeMillis() - l1) > 5L) {
          System.err.println("[SENSEGMENTCONTROLLER][CLIENT] WARNING: initializing segmentprovider of " + this + " took " + l4 + " ms");
        }
      }
      this.first = false;
    }
    if ((isOnServer()) && (this.lastModifierChanged) && (this.lastModifierId != 0))
    {
      try
      {
        localclass_748 = (class_748)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(this.lastModifierId);
        setLastModifier(localclass_748.getUniqueIdentifier());
        System.err.println("[SERVER][SENSEGMENTCONTROLLER] LAST MODIFIER CHANGED TO " + localclass_748);
      }
      catch (Exception localException2)
      {
        class_748 localclass_748 = null;
        localException2.printStackTrace();
      }
      this.lastModifierChanged = false;
    }
    try
    {
      if (!getBlockActivationBuffer().isEmpty())
      {
        assert (isOnServer());
        synchronized (getBlockActivationBuffer())
        {
          while (!getBlockActivationBuffer().isEmpty())
          {
            class_46 localclass_46 = (class_46)getBlockActivationBuffer().dequeue();
            localObject2 = new class_48(localclass_46.field_467, localclass_46.field_468, localclass_46.field_469);
            class_796 localclass_796 = getSegmentBuffer().a9((class_48)localObject2, true);
            int i;
            boolean bool;
            if ((i = Math.abs(localclass_46.field_470) < 2 ? 1 : 0) == 0)
            {
              System.err.println("[SERVER] NOT DELEGATING REQUEST " + localObject2 + " act(" + localclass_46 + ")");
              bool = localclass_46.field_470 != -2;
            }
            else
            {
              System.err.println("[SERVER] DELEGATING REQUEST " + localObject2 + " act(" + bool + ")");
              bool = bool.field_470 != 0;
            }
            if ((localclass_796.a9() == 56) && ((localclass_796.a7().a15() instanceof class_864)))
            {
              System.err.println("[SERVER] NOT ACTIVATING GRAVITY BLOCK ON PLANET" + bool);
            }
            else
            {
              localclass_796.a13(bool);
              System.err.println("[SERVER] ACTIVATING BLOCK " + bool);
              localclass_796.a7().a16().applySegmentData(localclass_796.a6(new class_35()), localclass_796.a4());
              ((class_672)localclass_796.a7()).a46(System.currentTimeMillis());
              getNetworkObject().modificationBuffer.add(new RemoteSegmentPiece(localclass_796, this, getNetworkObject()));
              if ((i != 0) && ((this instanceof class_798))) {
                ((class_798)this).a().handleBlockActivate(localclass_796, bool);
              }
            }
          }
        }
      }
      long l2 = System.currentTimeMillis();
      getControlElementMap().updateLocal(paramclass_941);
      long l3;
      if ((l3 = System.currentTimeMillis() - l2) > 20L) {
        System.err.println("[SENSEGMENTCONTROLLER][" + getState() + "] WARNING: getControlElementMap().updateLocal(timer) of " + this + " took " + l3 + " ms");
      }
    }
    catch (Exception localException1)
    {
      if (!isOnServer())
      {
        localException1.printStackTrace();
        throw new ErrorDialogException("CLIENT EXCEPTION: " + localException1.getClass().toString() + ": " + localException1.getMessage());
      }
      System.err.println("SERVER EXCEPTION IN SENDABLESEGMENT CONTROLLER");
      localException1.printStackTrace();
    }
    super.updateLocal(paramclass_941);
  }
  
  public void updateToFullNetworkObject()
  {
    super.updateToFullNetworkObject();
    assert (getUniqueIdentifier() != null);
    getNetworkObject().uniqueIdentifier.set(getUniqueIdentifier());
    Object localObject;
    if (((this instanceof class_798)) && ((((class_798)this).a() instanceof PowerManagerInterface)))
    {
      localObject = ((PowerManagerInterface)((class_798)this).a()).getPowerAddOn();
      getNetworkObject().initialPower.set(((PowerAddOn)localObject).getInitialPower());
    }
    if (((this instanceof class_798)) && ((((class_798)this).a() instanceof ShieldContainerInterface)))
    {
      localObject = ((ShieldContainerInterface)((class_798)this).a()).getShieldManager();
      getNetworkObject().initialShields.set(((ShieldCollectionManager)localObject).getInitialShields());
    }
    updateToNetworkObject();
  }
  
  public void updateToNetworkObject()
  {
    super.updateToNetworkObject();
    assert (getMinPos() != null);
    if (isOnServer())
    {
      getNetworkObject().minSize.set(getMinPos());
      getNetworkObject().maxSize.set(getMaxPos());
    }
    getNetworkObject().realName.set(getRealName());
  }
  
  public void writeAllBufferedSegmentsToDatabase()
  {
    long l1 = System.currentTimeMillis();
    synchronized (getSegmentProvider().field_14)
    {
      getSegmentBuffer().a18(new class_751(this), true);
    }
    long l2;
    if ((l2 = System.currentTimeMillis() - l1) > 10L) {
      System.err.println("[SENDABLESEGMENTVONTROLLER][WRITE] WARNING: segment writing of " + this + " on " + getState() + " took: " + l2 + " ms");
    }
  }
  
  public void setServerSendableSegmentController(class_749 paramclass_749)
  {
    this.serverSendableSegmentProvider = paramclass_749;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_753
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */