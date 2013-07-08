import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.game.common.data.element.Element;
import org.schema.game.common.data.physics.PairCachingGhostObjectAlignable;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.physics.RigidBodyExt;
import org.schema.game.common.data.world.SectorNotFoundException;
import org.schema.game.common.data.world.Universe;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.ControllerInterface;
import org.schema.schine.network.Identifiable;
import org.schema.schine.network.NetworkGravity;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.client.ClientState;
import org.schema.schine.network.objects.NetworkEntity;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteGravity;
import org.schema.schine.network.objects.remote.RemoteIntPrimitive;
import org.schema.schine.network.objects.remote.RemoteInteger;
import org.schema.schine.network.server.ServerStateInterface;

public abstract class class_797
  implements class_80, class_809, Sendable, class_1382, class_1421
{
  private int factionId = 0;
  private int field_200;
  private final StateInterface state;
  private class_858 remoteTransformable;
  private final boolean onServer;
  private int sectorId = -2;
  private final class_48 tagSectorId = new class_48();
  public int sectorChangedDelay;
  public class_48 transientSector = new class_48();
  private static ThreadLocal threadLocal = new class_803();
  private int lastCheckSector = -2147483648;
  private boolean hidden = false;
  private String owner = new String();
  boolean hiddenflag = false;
  private boolean markedForDelete;
  private boolean flagPhysicsInit;
  private boolean markedForDeleteSent;
  private boolean immediateStuck;
  public long lastSectorSwitch;
  private class_661 scheduledGravity;
  protected boolean flagGravityUpdate;
  private static final Vector3f noGrav = new Vector3f(0.0F, 0.0F, 0.0F);
  private Transform clientTransform = new Transform();
  private Transform lastWorldTransformTmp = new Transform();
  private Transform trans = new Transform();
  private Transform transTmp = new Transform();
  private Vector3f absSectorPos = new Vector3f();
  private Vector3f absCenterPos = new Vector3f();
  private int lastClientTransCalcSectorId = -1;
  private final class_661 gravity = new class_661();
  private Vector3f field_183 = new Vector3f();
  private boolean markedForPermanentDelete;
  private long lastSlowdown;
  private long slowdownStart;
  public class_48[] surround = new class_48[6];
  public class_48[] surroundAddedSecs = new class_48[6];
  public CollisionObject[] surroundAdded = new CollisionObject[6];
  private CollisionObject clientVirtualObject;
  private boolean flagGravityDeligation;
  
  public class_797(StateInterface paramStateInterface)
  {
    this.state = paramStateInterface;
    this.onServer = (paramStateInterface instanceof ServerStateInterface);
    for (paramStateInterface = 0; paramStateInterface < this.surround.length; paramStateInterface++)
    {
      this.surround[paramStateInterface] = new class_48();
      this.surroundAddedSecs[paramStateInterface] = new class_48();
    }
  }
  
  private void aiFromTagStructure(class_69 paramclass_69)
  {
    if (((this instanceof class_991)) && (paramclass_69.a3() != class_79.field_549)) {
      ((class_80)((class_991)this).a()).fromTagStructure(paramclass_69);
    }
  }
  
  private class_69 aiToTagStructure()
  {
    class_69 localclass_69;
    if ((this instanceof class_991))
    {
      assert (((class_991)this).a() != null) : this;
      localclass_69 = ((class_80)((class_991)this).a()).toTagStructure();
    }
    else
    {
      localclass_69 = new class_69(class_79.field_549, "noAI", Byte.valueOf((byte)0));
    }
    return localclass_69;
  }
  
  protected void handleGravity()
  {
    if ((getPhysicsDataContainer().getObject() instanceof RigidBody)) {
      ((RigidBody)getPhysicsDataContainer().getObject()).setGravity(this.gravity.jdField_field_928_of_type_JavaxVecmathVector3f);
    }
  }
  
  public void removeGravity()
  {
    setGravity(noGrav, null);
  }
  
  public void setGravity(Vector3f paramVector3f, class_797 paramclass_797)
  {
    if (getGravity().jdField_field_928_of_type_Class_797 != paramclass_797)
    {
      System.err.println("[GRAVITY] " + this + " " + getState() + " SOURCE CHANGE " + getGravity().jdField_field_928_of_type_Class_797 + " -> " + paramclass_797);
      getGravity().jdField_field_928_of_type_Class_797 = paramclass_797;
      getGravity().jdField_field_928_of_type_Boolean = true;
    }
    if (!getGravity().jdField_field_928_of_type_JavaxVecmathVector3f.equals(paramVector3f))
    {
      getGravity().jdField_field_928_of_type_JavaxVecmathVector3f.set(paramVector3f);
      getGravity().jdField_field_928_of_type_Boolean = true;
    }
    if (getGravity().jdField_field_928_of_type_Boolean)
    {
      ((NetworkGravity)getNetworkObject().gravity.get()).gravityId = (paramclass_797 != null ? paramclass_797.getId() : -1);
      ((NetworkGravity)getNetworkObject().gravity.get()).gravity.set(paramVector3f);
      getNetworkObject().gravity.setChanged(true);
    }
  }
  
  protected void checkGravityValid()
  {
    if (isHidden()) {
      return;
    }
    class_797 localclass_797 = this;
    class_661 localclass_661 = this.gravity;
    localclass_797.getGravityAABB(localclass_661.field_931, localclass_661.field_932);
    localclass_661.jdField_field_928_of_type_Class_797.getGravityAABB(localclass_661.field_929, localclass_661.field_930);
    if (localclass_797.sectorChangedDelay <= 0)
    {
      if (localclass_661.jdField_field_928_of_type_Class_797.getSectorId() != localclass_797.getSectorId())
      {
        System.err.println("[GRAVITY] gravity reset for " + localclass_797 + ": sector test failed: " + localclass_661.jdField_field_928_of_type_Class_797.getSectorId() + ", " + localclass_797.getSectorId());
        break label278;
      }
      if (!AabbUtil2.testAabbAgainstAabb2(localclass_661.field_931, localclass_661.field_932, localclass_661.field_929, localclass_661.field_930))
      {
        System.err.println("[GRAVITY] gravity reset for " + localclass_797 + " -> " + localclass_661.jdField_field_928_of_type_Class_797 + ": AABB test failed: " + localclass_661.field_931 + "; " + localclass_661.field_932 + "  ----  " + localclass_661.field_929 + "; " + localclass_661.field_930 + " of " + localclass_661.jdField_field_928_of_type_Class_797);
        break label278;
      }
    }
    else
    {
      localclass_797.sectorChangedDelay -= 1;
    }
    label278:
    if (((!localclass_797.isHidden()) && ((localclass_661.a()) || (localclass_661.b())) ? 1 : 0) == 0)
    {
      assert (((!getGravity().b()) && (!getGravity().a())) || (getGravity().jdField_field_928_of_type_Class_797 != null));
      removeGravity();
    }
  }
  
  public void getGravityAABB(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    getPhysicsDataContainer().getShape().getAabb(getWorldTransform(), paramVector3f1, paramVector3f2);
  }
  
  protected void searchForGravity()
  {
    if (isHidden()) {
      return;
    }
    Iterator localIterator = ((class_667)getState()).a().iterator();
    while (localIterator.hasNext())
    {
      class_797 localclass_797 = (class_797)localIterator.next();
      if ((getGravity().jdField_field_928_of_type_Class_797 != localclass_797) && (localclass_797.affectsGravityOf(this))) {
        setGravity(new Vector3f(0.0F, -9.89F, 0.0F), localclass_797);
      }
    }
  }
  
  protected boolean affectsGravityOf(class_797 paramclass_797)
  {
    return false;
  }
  
  protected void checkForGravity()
  {
    this.gravity.jdField_field_928_of_type_Boolean = false;
    if (gravityChecksRequired())
    {
      if (this.scheduledGravity != null)
      {
        setGravity(this.scheduledGravity.jdField_field_928_of_type_JavaxVecmathVector3f, this.scheduledGravity.jdField_field_928_of_type_Class_797);
        this.scheduledGravity = null;
      }
      else
      {
        searchForGravity();
      }
      checkGravityValid();
    }
    if (this.gravity.jdField_field_928_of_type_Boolean) {
      System.err.println("[GRAVITY] " + this + " changed gravity on " + getState() + " " + this.gravity.jdField_field_928_of_type_Class_797 + " -> " + this.gravity.jdField_field_928_of_type_JavaxVecmathVector3f);
    }
    if (this.flagGravityUpdate)
    {
      this.gravity.jdField_field_928_of_type_Boolean = true;
      this.flagGravityUpdate = false;
    }
  }
  
  public void scheduleGravity(Vector3f paramVector3f, class_797 paramclass_797)
  {
    this.scheduledGravity = new class_661();
    this.scheduledGravity.jdField_field_928_of_type_JavaxVecmathVector3f.set(paramVector3f);
    this.scheduledGravity.jdField_field_928_of_type_Class_797 = paramclass_797;
  }
  
  protected boolean gravityChecksRequired()
  {
    return (this.remoteTransformable.b1()) || ((isOnServer()) && (!isConrolledByActivePlayer()));
  }
  
  public boolean isConrolledByActivePlayer()
  {
    return ((this instanceof class_365)) && (!((class_365)this).a26().isEmpty());
  }
  
  public void cleanUpOnEntityDelete()
  {
    onPhysicsRemove();
  }
  
  private void clientSectorChange(int paramInt1, int paramInt2)
  {
    assert (!isOnServer());
    ((class_371)getState()).a4().a33(new class_363(this, paramInt1, paramInt2));
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof Identifiable)) && (((Identifiable)paramObject).getId() == getId());
  }
  
  public int getId()
  {
    return this.field_200;
  }
  
  public Transform getInitialTransform()
  {
    return this.remoteTransformable.a7();
  }
  
  public float getMass()
  {
    return getRemoteTransformable().getMass();
  }
  
  public abstract NetworkEntity getNetworkObject();
  
  public PhysicsExt getPhysics()
  {
    return (PhysicsExt)getPhysicsState().a();
  }
  
  public PhysicsDataContainer getPhysicsDataContainer()
  {
    return getRemoteTransformable().getPhysicsDataContainer();
  }
  
  public class_1407 getPhysicsState()
  {
    if (isOnServer())
    {
      long l1 = System.currentTimeMillis();
      class_670 localclass_670 = ((class_1041)getState()).a62().getSector(getSectorId());
      long l2;
      if ((l2 = System.currentTimeMillis() - l1) > 5L) {
        System.err.println("[SERVER][STO] WARNING: Loading sector " + getSectorId() + " for " + this + " took " + l2 + " ms");
      }
      assert (localclass_670 != null) : ("Server Sector NULL: " + this + "; Sector that is not found: " + getSectorId());
      if (localclass_670 == null)
      {
        System.err.println("[ERROR][FATAL] Fatal Exception: SECTOR NULL FOR " + this + " " + getSectorId());
        throw new SectorNotFoundException(getSectorId());
      }
      return localclass_670;
    }
    return (class_371)getState();
  }
  
  public Vector3f getRelativeUniverseServerPosition(class_797 paramclass_797)
  {
    assert (isOnServer());
    paramclass_797 = ((class_1041)getState()).a62().getSector(paramclass_797.getSectorId());
    class_670 localclass_670 = ((class_1041)getState()).a62().getSector(getSectorId());
    class_48 localclass_48;
    (localclass_48 = new class_48()).a6(paramclass_797.field_136, localclass_670.field_136);
    (paramclass_797 = new Vector3f(localclass_48.field_475 * Universe.getSectorSizeWithMargin(), localclass_48.field_476 * Universe.getSectorSizeWithMargin(), localclass_48.field_477 * Universe.getSectorSizeWithMargin())).add(getWorldTransform().origin);
    return paramclass_797;
  }
  
  public class_858 getRemoteTransformable()
  {
    return this.remoteTransformable;
  }
  
  public final int getSectorId()
  {
    return this.sectorId;
  }
  
  public StateInterface getState()
  {
    return this.state;
  }
  
  public class_48 getTagSectorId()
  {
    return this.tagSectorId;
  }
  
  public Transform getWorldTransform()
  {
    return this.remoteTransformable.getWorldTransform();
  }
  
  public Transform getWorldTransformClient()
  {
    assert (!isOnServer());
    class_371 localclass_371 = (class_371)getState();
    if (getSectorId() == localclass_371.a8()) {
      return this.remoteTransformable.getWorldTransform();
    }
    return this.clientTransform;
  }
  
  public void calcWorldTransformRelative(int paramInt, class_48 paramclass_48)
  {
    calcWorldTransformRelative(paramInt, paramclass_48, getSectorId());
  }
  
  public void calcWorldTransformRelative(int paramInt1, class_48 paramclass_48, int paramInt2)
  {
    this.lastClientTransCalcSectorId = -1;
    if (this.remoteTransformable == null) {
      throw new NullPointerException("No remote Transformable");
    }
    if (paramInt2 == paramInt1)
    {
      this.clientTransform.set(this.remoteTransformable.getWorldTransform());
      return;
    }
    class_48 localclass_481 = new class_48();
    class_48 localclass_482;
    if (isOnServer())
    {
      if ((localObject = ((class_1041)getState()).a62().getSector(paramInt2)) != null) {
        localclass_482 = ((class_670)localObject).field_136;
      }
    }
    else
    {
      if ((localObject = (class_665)this.state.getLocalAndRemoteObjectContainer().getLocalObjects().get(paramInt2)) == null)
      {
        System.err.println("[SimpleTrans] WARNING " + this + " Relative: Sector Not Found: " + paramInt2);
        this.clientTransform.set(this.remoteTransformable.getWorldTransform());
        this.clientTransform.origin.set(10000.0F, 10000.0F, 10000.0F);
        return;
      }
      localclass_482 = ((class_665)localObject).a34();
    }
    Object localObject = class_789.a192(paramclass_48, new class_48());
    if (class_791.a19(paramclass_48))
    {
      paramInt2 = (float)((System.currentTimeMillis() - this.state.getController().calculateStartTime()) % 1200000L) / 1200000.0F;
      if (!class_791.a19(paramclass_48)) {
        paramInt2 = 0.0F;
      }
      ((class_48)localObject).a5(16);
      ((class_48)localObject).a(8, 8, 8);
      ((class_48)localObject).c1(paramclass_48);
      localclass_481.b1((class_48)localObject);
    }
    else
    {
      paramInt2 = 0.0F;
    }
    (localObject = new class_48()).a6(localclass_482, paramclass_48);
    this.absSectorPos.set(((class_48)localObject).field_475 * Universe.getSectorSizeWithMargin(), ((class_48)localObject).field_476 * Universe.getSectorSizeWithMargin(), ((class_48)localObject).field_477 * Universe.getSectorSizeWithMargin());
    this.absCenterPos.set(localclass_481.field_475 * Universe.getSectorSizeWithMargin(), localclass_481.field_476 * Universe.getSectorSizeWithMargin(), localclass_481.field_477 * Universe.getSectorSizeWithMargin());
    this.trans.setIdentity();
    if (localclass_481.a4() > 0.0F)
    {
      this.trans.origin.add(this.absCenterPos);
      this.trans.basis.rotX(6.283186F * paramInt2);
      this.field_183.sub(this.absSectorPos, this.absCenterPos);
      this.trans.origin.add(this.field_183);
      this.trans.basis.transform(this.trans.origin);
    }
    else
    {
      this.trans.basis.setIdentity();
      this.trans.origin.set(this.absSectorPos);
    }
    this.transTmp.set(this.trans);
    (paramclass_48 = new Transform()).setIdentity();
    paramInt2 = -6.283186F * paramInt2;
    paramclass_48.basis.rotX(paramInt2);
    class_29.a1(paramclass_48, this.remoteTransformable.getWorldTransform());
    class_29.a1(this.transTmp, paramclass_48);
    this.clientTransform.set(getWorldTransform());
    this.clientTransform.origin.set(this.transTmp.origin);
    this.lastWorldTransformTmp.set(getWorldTransform());
    this.lastClientTransCalcSectorId = paramInt1;
  }
  
  public abstract void handleMovingInput(class_941 paramclass_941, class_755 paramclass_755);
  
  public int hashCode()
  {
    assert ((this.field_200 < 2147483647) && (this.field_200 > -2147483648));
    return this.field_200;
  }
  
  private class_670 getDefaultSector()
  {
    return ((class_1041)getState()).a62().getSector(class_670.field_139);
  }
  
  public void initFromNetworkObject(NetworkObject paramNetworkObject)
  {
    paramNetworkObject = (NetworkEntity)paramNetworkObject;
    setId(((Integer)paramNetworkObject.field_87.get()).intValue());
    setFactionId(getNetworkObject().factionCode.get().intValue());
    if (!isOnServer())
    {
      if (getSectorId() != paramNetworkObject.sector.get().intValue()) {
        clientSectorChange(getSectorId(), paramNetworkObject.sector.get().intValue());
      }
    }
    else {
      setSectorId(paramNetworkObject.sector.get().intValue());
    }
    if ((isOnServer()) && (getSectorId() == -2) && (paramNetworkObject.sector.get().intValue() == -2)) {
      try
      {
        class_670 localclass_670 = getDefaultSector();
        System.err.println("[SERVER] NO SECTOR INFORMATION PROVIDED ON INIT: ASSIGNING SECTOR " + localclass_670.a3() + " TO " + this);
        setSectorId(localclass_670.a3());
      }
      catch (Exception localException)
      {
        (paramNetworkObject = localException).printStackTrace();
        throw new IOException("THROWN DELIGATED: " + paramNetworkObject.getClass().getSimpleName() + ": " + paramNetworkObject.getMessage());
      }
    }
    getRemoteTransformable().a11(paramNetworkObject);
  }
  
  public void initialize()
  {
    if (this.remoteTransformable == null)
    {
      this.remoteTransformable = new class_801(this, this, this.state);
      return;
    }
    System.err.println("[Transformable][WARNING] Remote transformable already exists. skipped creation");
  }
  
  public boolean isHidden()
  {
    return this.hidden;
  }
  
  public boolean isMarkedForDeleteVolatile()
  {
    return this.markedForDelete;
  }
  
  public boolean isMarkedForDeleteVolatileSent()
  {
    return this.markedForDeleteSent;
  }
  
  public boolean isOnServer()
  {
    return this.onServer;
  }
  
  public boolean isClientSectorIdValidForSpawning(int paramInt)
  {
    return (paramInt == ((class_371)getState()).a8()) || (isPlayerNeighbor(paramInt));
  }
  
  public boolean isPlayerNeighbor(int paramInt)
  {
    return ((paramInt = (class_665)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramInt)) != null) && (class_670.a80(paramInt.a34(), ((class_371)getState()).a20().a44()));
  }
  
  public boolean isNeighbor(int paramInt1, int paramInt2)
  {
    class_665 localclass_6651 = (class_665)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramInt1);
    class_665 localclass_6652 = (class_665)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(paramInt2);
    if ((localclass_6651 != null) && (localclass_6652 != null)) {
      return class_670.a80(localclass_6651.a34(), localclass_6652.a34());
    }
    System.err.println("Exception while checking neighbor: " + localclass_6651 + "; " + localclass_6652 + ";    " + paramInt1 + "; " + paramInt2);
    return false;
  }
  
  public void onPhysicsAdd()
  {
    assert (this.sectorId != -2);
    getPhysicsDataContainer().onPhysicsAdd();
    if ((isOnServer()) || (isClientSectorIdValidForSpawning(getSectorId()))) {
      if (isOnServer())
      {
        for (int i = 0; i < Element.DIRECTIONSi.length; i++) {
          this.surround[i].b2(((class_1041)getState()).a62().getSector(getSectorId()).field_136, Element.DIRECTIONSi[i]);
        }
        getPhysics().addObject(getPhysicsDataContainer().getObject());
        if (hasVirtual()) {
          for (i = 0; i < this.surround.length; i++) {
            try
            {
              if (((class_1041)getState()).a62().isSectorLoaded(this.surround[i]))
              {
                class_670 localclass_6701 = ((class_1041)getState()).a62().getSector(this.surround[i]);
                class_670 localclass_6702 = ((class_1041)getState()).a62().getSector(getSectorId());
                calculateRel(localclass_6702, this.surround[i]);
                RigidBody localRigidBody2;
                if (((localRigidBody2 = localclass_6701.a64().getBodyFromShape(getPhysicsDataContainer().getShape(), 0.0F, new Transform(getClientTransform()))) instanceof RigidBodyExt))
                {
                  ((RigidBodyExt)localRigidBody2).virtualString = ("virtS" + localclass_6702.field_136 + "->" + localclass_6701.field_136 + "{" + getPhysicsDataContainer().getObject().toString() + "}");
                  ((RigidBodyExt)localRigidBody2).virtualSec = new class_48(localclass_6701.field_136);
                }
                localRigidBody2.setUserPointer(Integer.valueOf(getId()));
                localRigidBody2.setCollisionFlags(2);
                localRigidBody2.setActivationState(4);
                localclass_6701.a64().addObject(localRigidBody2);
                assert (localclass_6701.a64().containsObject(localRigidBody2));
                this.surroundAdded[i] = localRigidBody2;
                this.surroundAddedSecs[i].b1(this.surround[i]);
                localRigidBody2.activate(true);
              }
            }
            catch (Exception localException)
            {
              localException;
            }
          }
        }
      }
      else
      {
        if (getSectorId() == ((class_371)getState()).a8())
        {
          getPhysics().addObject(getPhysicsDataContainer().getObject());
          return;
        }
        getPhysicsDataContainer().getObject().getWorldTransform(getWorldTransform());
        calcWorldTransformRelative(((class_371)getState()).a8(), ((class_371)getState()).a20().a44());
        RigidBody localRigidBody1;
        (localRigidBody1 = getPhysics().getBodyFromShape(getPhysicsDataContainer().getShape(), 0.0F, new Transform(getWorldTransformClient()))).setCollisionFlags(2);
        localRigidBody1.setUserPointer(Integer.valueOf(getId()));
        getPhysics().addObject(localRigidBody1);
        if ((localRigidBody1 instanceof RigidBodyExt))
        {
          ((RigidBodyExt)localRigidBody1).virtualString = ("virtC" + ((class_371)getState()).a20().a44() + "{orig" + getPhysicsDataContainer().getObject().getWorldTransform(new Transform()).origin + "}");
          ((RigidBodyExt)localRigidBody1).virtualSec = new class_48(((class_371)getState()).a20().a44());
        }
        assert (getPhysics().containsObject(localRigidBody1));
        this.clientVirtualObject = localRigidBody1;
        this.clientVirtualObject.setInterpolationWorldTransform(getWorldTransformClient());
        this.clientVirtualObject.activate(true);
      }
    }
  }
  
  private boolean hasVirtual()
  {
    return !(this instanceof class_705);
  }
  
  public void onSectorInactiveClient() {}
  
  public void onPhysicsRemove()
  {
    getPhysicsDataContainer().onPhysicsRemove();
    try
    {
      if (isOnServer())
      {
        if (((class_1041)getState()).a62().getSector(getSectorId()) != null) {
          getPhysics().removeObject(getPhysicsDataContainer().getObject());
        }
        for (int i = 0; i < this.surroundAdded.length; i++) {
          try
          {
            class_670 localclass_670;
            if ((this.surroundAdded[i] != null) && ((localclass_670 = ((class_1041)getState()).a62().getSectorWithoutLoading(this.surroundAddedSecs[i])) != null))
            {
              localclass_670.a64().removeObject(this.surroundAdded[i]);
              assert (!localclass_670.a64().containsObject(this.surroundAdded[i])) : this.surroundAdded[i];
              this.surroundAdded[i] = null;
            }
          }
          catch (Exception localException1)
          {
            localException1;
          }
        }
        return;
      }
      getPhysics().removeObject(getPhysicsDataContainer().getObject());
      try
      {
        if (this.clientVirtualObject != null)
        {
          getPhysics().removeObject(this.clientVirtualObject);
          assert (!getPhysics().containsObject(this.clientVirtualObject)) : this.clientVirtualObject;
          this.clientVirtualObject = null;
        }
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        return;
      }
      return;
    }
    catch (SectorNotFoundException localSectorNotFoundException)
    {
      localSectorNotFoundException.printStackTrace();
      System.err.println("[EXCEPTION] OBJECT REMOVAL FROM PHYSICS FAILED -> can continue");
    }
  }
  
  public void setHiddenVolatile(boolean paramBoolean)
  {
    this.hidden = paramBoolean;
  }
  
  public void setHidden(boolean paramBoolean)
  {
    if (isOnServer())
    {
      this.hidden = paramBoolean;
      if (getNetworkObject() != null) {
        getNetworkObject().hidden.set(Boolean.valueOf(paramBoolean));
      }
    }
  }
  
  public void setId(int paramInt)
  {
    this.field_200 = paramInt;
  }
  
  public void setMarkedForDeleteVolatile(boolean paramBoolean)
  {
    this.markedForDelete = paramBoolean;
  }
  
  public void setMarkedForDeleteVolatileSent(boolean paramBoolean)
  {
    this.markedForDeleteSent = paramBoolean;
  }
  
  public void setMass(float paramFloat)
  {
    getRemoteTransformable().a8(paramFloat);
  }
  
  public void setPhysicsDataContainer(PhysicsDataContainer paramPhysicsDataContainer)
  {
    getRemoteTransformable().a9(paramPhysicsDataContainer);
  }
  
  public void setRemoteTransformable(class_858 paramclass_858)
  {
    this.remoteTransformable = paramclass_858;
  }
  
  public void setSectorId(int paramInt)
  {
    this.sectorId = paramInt;
  }
  
  public abstract String toNiceString();
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    assert (paramclass_69.a2().equals("transformable"));
    paramclass_69 = (class_69[])paramclass_69.a4();
    setMass(((Float)paramclass_69[0].a4()).floatValue());
    class_69[] arrayOfclass_69;
    float[] arrayOfFloat = new float[(arrayOfclass_69 = (class_69[])paramclass_69[1].a4()).length];
    for (int i = 0; i < arrayOfclass_69.length; i++) {
      arrayOfFloat[i] = ((Float)arrayOfclass_69[i].a4()).floatValue();
    }
    if ((paramclass_69.length > 2) && (paramclass_69[2].a3() != class_79.field_548)) {
      aiFromTagStructure(paramclass_69[2]);
    }
    if ((paramclass_69.length > 3) && (paramclass_69[3].a3() == class_79.field_558)) {
      getTagSectorId().b1((class_48)paramclass_69[3].a4());
    }
    if ((paramclass_69.length > 4) && (paramclass_69[4].a3() == class_79.field_551) && ("fid".equals(paramclass_69[4].a2()))) {
      setFactionId(((Integer)paramclass_69[4].a4()).intValue());
    }
    if ((paramclass_69.length > 5) && (paramclass_69[5].a3() == class_79.field_556) && ("own".equals(paramclass_69[4].a2()))) {
      this.owner = ((String)paramclass_69[5].a4());
    }
    Matrix4f localMatrix4f;
    if ((localMatrix4f = new Matrix4f(arrayOfFloat)).determinant() == 0.0F)
    {
      localMatrix4f.setIdentity();
      new Transform(localMatrix4f).getOpenGLMatrix(arrayOfFloat);
      try
      {
        throw new NullPointerException("ERROR: Read 0 matrix: " + this + ": catched! continue with standard matrix");
      }
      catch (Exception localException)
      {
        localException;
      }
    }
    this.remoteTransformable.a7().setFromOpenGLMatrix(arrayOfFloat);
    this.remoteTransformable.getWorldTransform().setFromOpenGLMatrix(arrayOfFloat);
  }
  
  public class_69 toTagStructure()
  {
    class_69 localclass_691 = new class_69(class_79.field_553, "mass", Float.valueOf(getMass()));
    class_69 localclass_692 = new class_69("transform", class_79.field_553);
    Object localObject = new float[16];
    this.remoteTransformable.getWorldTransform().getOpenGLMatrix((float[])localObject);
    for (int i = 0; i < 16; i++) {
      localclass_692.a1(new class_69(class_79.field_553, "matrix", Float.valueOf(localObject[i])));
    }
    class_69 localclass_693 = aiToTagStructure();
    if ((getSectorId() < 0) || (((class_1041)this.state).a62().getSector(getSectorId()) == null)) {
      localObject = new class_69(class_79.field_558, "sPos", this.transientSector);
    } else {
      localObject = new class_69(class_79.field_558, "sPos", ((class_1041)this.state).a62().getSector(getSectorId()).field_136);
    }
    class_69 localclass_694 = new class_69(class_79.field_551, "fid", Integer.valueOf(getFactionId()));
    class_69 localclass_695 = new class_69(class_79.field_556, "own", this.owner);
    return new class_69(class_79.field_561, "transformable", new class_69[] { localclass_691, localclass_692, localclass_693, localObject, localclass_694, localclass_695, new class_69(class_79.field_548, "fin", null) });
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    getRemoteTransformable().b((NetworkEntity)paramNetworkObject);
    paramNetworkObject = (NetworkEntity)paramNetworkObject;
    if (!isOnServer())
    {
      this.hidden = ((Boolean)paramNetworkObject.hidden.get()).booleanValue();
      if (getSectorId() != paramNetworkObject.sector.get().intValue())
      {
        if ((this instanceof class_750)) {
          System.err.println("[CLIENT] RECEIVED NEW SECTOR FOR " + this + ": " + getSectorId() + " -> " + paramNetworkObject.sector.get());
        }
        int i = getSectorId();
        clientSectorChange(i, paramNetworkObject.sector.get().intValue());
      }
      setFactionId(getNetworkObject().factionCode.get().intValue());
    }
    if (((NetworkGravity)paramNetworkObject.gravity.get()).gravityReceived)
    {
      if (((getState() instanceof ClientState)) && (getRemoteTransformable().b1()))
      {
        if ((this instanceof class_750)) {
          System.err.println(getState() + " [GRAVITY] " + this + " !!!IGNORE!!! RECEIVED REMOTE GRAVITY: " + ((NetworkGravity)paramNetworkObject.gravity.get()).gravityIdReceive + " --- " + ((NetworkGravity)paramNetworkObject.gravity.get()).gravityReceive);
        }
      }
      else
      {
        if ((this instanceof class_750)) {
          System.err.println(getState() + " [GRAVITY] " + this + " RECEIVED REMOTE GRAVITY: " + ((NetworkGravity)paramNetworkObject.gravity.get()).gravityIdReceive + " --- " + ((NetworkGravity)paramNetworkObject.gravity.get()).gravityReceive);
        }
        Sendable localSendable;
        if (((localSendable = (Sendable)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(((NetworkGravity)paramNetworkObject.gravity.get()).gravityIdReceive)) != null) && ((localSendable instanceof class_797)))
        {
          getGravity().jdField_field_928_of_type_JavaxVecmathVector3f.set(((NetworkGravity)paramNetworkObject.gravity.get()).gravityReceive);
          if (getGravity().jdField_field_928_of_type_Class_797 != localSendable)
          {
            getGravity().jdField_field_928_of_type_Class_797 = ((class_797)localSendable);
            this.flagGravityUpdate = true;
          }
        }
        else
        {
          getGravity().jdField_field_928_of_type_JavaxVecmathVector3f.set(0.0F, 0.0F, 0.0F);
          if (getGravity().jdField_field_928_of_type_Class_797 != localSendable)
          {
            getGravity().jdField_field_928_of_type_Class_797 = null;
            this.flagGravityUpdate = true;
          }
        }
        if (isOnServer()) {
          this.flagGravityDeligation = true;
        }
      }
      ((NetworkGravity)paramNetworkObject.gravity.get()).gravityReceived = false;
    }
  }
  
  private void calculateRel(class_670 paramclass_670, class_48 paramclass_48)
  {
    calcWorldTransformRelative(paramclass_48.equals(paramclass_670.field_136) ? paramclass_670.a3() : -1, paramclass_48, getSectorId());
    if ((toString().contains("schema_1370260089078")) && (!$assertionsDisabled) && (paramclass_670.a3() != getSectorId())) {
      throw new AssertionError();
    }
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    if ((isFlagPhysicsInit()) || (isHidden() != this.hiddenflag))
    {
      onPhysicsRemove();
      if ((!isHidden()) && (getPhysicsDataContainer().getObject() != null))
      {
        if (isOnServer()) {
          for (i = 0; i < Element.DIRECTIONSi.length; i++) {
            this.surround[i].b2(((class_1041)getState()).a62().getSector(getSectorId()).field_136, Element.DIRECTIONSi[i]);
          }
        }
        onPhysicsAdd();
      }
      this.hiddenflag = isHidden();
      getPhysicsDataContainer().updatePhysical();
      setFlagPhysicsInit(false);
    }
    int i = (getPhysicsDataContainer().isInitialized()) && (getPhysicsDataContainer().getObject() != null) && (!getPhysicsDataContainer().getObject().isStaticObject()) ? 1 : 0;
    Object localObject;
    if (isOnServer())
    {
      if (i != 0) {
        for (int j = 0; j < this.surroundAddedSecs.length; j++) {
          try
          {
            localclass_670 = ((class_1041)getState()).a62().getSector(getSectorId());
            calculateRel(localclass_670, this.surroundAddedSecs[j]);
            if (this.surroundAdded[j] != null) {
              this.surroundAdded[j].setWorldTransform(getClientTransform());
            }
          }
          catch (Exception localException1)
          {
            class_670 localclass_670 = null;
            localException1.printStackTrace();
          }
        }
      }
      if (Float.isNaN(getWorldTransform().origin.field_615)) {
        try
        {
          throw new IllegalStateException("Exception: NaN position for " + this + "; PObject: " + getPhysicsDataContainer().getObject());
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          getWorldTransform().origin.set(400.0F, 400.0F, 400.0F);
          if (getPhysicsDataContainer().getObject() != null)
          {
            ((GameServerController)getState().getController()).broadcastMessage("Invalid position (NaN) for \n" + this + "\nposition reset to\n local(400, 400, 400)", 3);
            (localObject = new Transform()).setIdentity();
            ((Transform)localObject).origin.set(400.0F, 400.0F, 400.0F);
            getPhysicsDataContainer().getObject().setWorldTransform((Transform)localObject);
          }
        }
      }
    }
    getRemoteTransformable().a10(paramclass_941);
    if (isOnServer())
    {
      if ((i != 0) && (isCheckSectorActive())) {
        try
        {
          ((class_1041)getState()).a62().getSectorBelonging(this);
        }
        catch (Exception localException3)
        {
          (localObject = localException3).printStackTrace();
          throw new ErrorDialogException(localObject.getClass().getSimpleName() + ": " + ((Exception)localObject).getMessage());
        }
      }
    }
    else if (isPlayerNeighbor(getSectorId()))
    {
      calcWorldTransformRelative(((class_371)getState()).a8(), ((class_371)getState()).a20().a44());
      if ((((class_371)getState()).a8() != getSectorId()) && (!isOnServer()) && (this.clientVirtualObject != null))
      {
        this.clientVirtualObject.setWorldTransform(getWorldTransformClient());
        this.clientVirtualObject.setInterpolationWorldTransform(getWorldTransformClient());
        ((RigidBody)this.clientVirtualObject).getMotionState().setWorldTransform(getWorldTransformClient());
        this.clientVirtualObject.activate(true);
      }
    }
    if (i != 0)
    {
      checkForGravity();
      handleGravity();
    }
    updateToNetworkObject();
  }
  
  protected boolean isCheckSectorActive()
  {
    return true;
  }
  
  public void updateToFullNetworkObject()
  {
    getNetworkObject().field_87.set(Integer.valueOf(getId()));
    getNetworkObject().sector.set(getSectorId());
    getNetworkObject().factionCode.set(getFactionId());
    getNetworkObject().hidden.set(Boolean.valueOf(isHidden()));
    assert (getState().getId() >= 0);
    getRemoteTransformable().c(getNetworkObject());
    updateToNetworkObject();
  }
  
  public void updateToNetworkObject()
  {
    if (isOnServer())
    {
      if (getNetworkObject().sector.getInt() != getSectorId()) {
        getNetworkObject().sector.set(getSectorId());
      }
      if (getNetworkObject().factionCode.getInt() != getFactionId()) {
        getNetworkObject().factionCode.set(getFactionId());
      }
    }
    if (this.flagGravityDeligation)
    {
      ((NetworkGravity)getNetworkObject().gravity.get()).gravity.set(getGravity().jdField_field_928_of_type_JavaxVecmathVector3f);
      ((NetworkGravity)getNetworkObject().gravity.get()).gravityId = (getGravity().jdField_field_928_of_type_Class_797 != null ? getGravity().jdField_field_928_of_type_Class_797.getId() : -1);
      getNetworkObject().gravity.setChanged(true);
      this.flagGravityDeligation = false;
    }
    getRemoteTransformable().d(getNetworkObject());
  }
  
  public void warpTransformable(float paramFloat1, float paramFloat2, float paramFloat3, boolean paramBoolean)
  {
    Transform localTransform;
    (localTransform = new Transform(getWorldTransform())).origin.set(paramFloat1, paramFloat2, paramFloat3);
    warpTransformable(localTransform, paramBoolean);
  }
  
  public void warpTransformable(Transform paramTransform, boolean paramBoolean)
  {
    if (getPhysicsDataContainer().isInitialized())
    {
      PairCachingGhostObjectAlignable localPairCachingGhostObjectAlignable;
      if (((getPhysicsDataContainer().getObject() instanceof PairCachingGhostObjectAlignable)) && ((localPairCachingGhostObjectAlignable = (PairCachingGhostObjectAlignable)getPhysicsDataContainer().getObject()).localWorldTransform != null)) {
        localPairCachingGhostObjectAlignable.localWorldTransform.set(paramTransform);
      }
      getRemoteTransformable().a12(new Transform(paramTransform));
      if ((isOnServer()) && (paramBoolean)) {
        getRemoteTransformable().a5(new Transform(paramTransform), getNetworkObject());
      }
    }
  }
  
  public int getFactionId()
  {
    return this.factionId;
  }
  
  public void setFactionId(int paramInt)
  {
    this.factionId = paramInt;
  }
  
  public void getRelationColor(class_762 paramclass_762, Vector4f paramVector4f, float paramFloat)
  {
    paramVector4f.field_596 = (paramFloat + 0.3F);
    paramVector4f.field_597 = 0.3F;
    paramVector4f.field_598 = paramFloat;
  }
  
  public boolean isHomeBaseFor(int paramInt)
  {
    if ((getFactionId() == 0) || (paramInt != getFactionId())) {
      return false;
    }
    return ((paramInt = ((class_1039)getState()).a().a156(paramInt)) != null) && (paramInt.d8().equals(getUniqueIdentifier()));
  }
  
  public boolean isHomeBase()
  {
    class_773 localclass_773;
    return ((localclass_773 = ((class_1039)getState()).a().a156(getFactionId())) != null) && (localclass_773.d8().equals(getUniqueIdentifier()));
  }
  
  public boolean isMarkedForPermanentDelete()
  {
    return this.markedForPermanentDelete;
  }
  
  public void markedForPermanentDelete(boolean paramBoolean)
  {
    this.markedForPermanentDelete = paramBoolean;
  }
  
  public boolean isFlagPhysicsInit()
  {
    return this.flagPhysicsInit;
  }
  
  public void setFlagPhysicsInit(boolean paramBoolean)
  {
    this.flagPhysicsInit = paramBoolean;
  }
  
  public void flagPhysicsSlowdown()
  {
    long l;
    if (((l = System.currentTimeMillis()) - this.lastSlowdown > 5000L) || (this.slowdownStart == 0L)) {
      this.slowdownStart = l;
    }
    this.lastSlowdown = l;
  }
  
  public long getSlowdownStart()
  {
    return this.slowdownStart;
  }
  
  public void resetSlowdownStart()
  {
    this.slowdownStart = 0L;
  }
  
  public long getLastSlowdown()
  {
    return this.lastSlowdown;
  }
  
  public boolean isUpdatable()
  {
    return true;
  }
  
  public boolean overlapsAABB(class_797 paramclass_797, float paramFloat)
  {
    Vector3f localVector3f1 = new Vector3f();
    Vector3f localVector3f2 = new Vector3f();
    Vector3f localVector3f3 = new Vector3f();
    Vector3f localVector3f4 = new Vector3f();
    getTransformedAABB(localVector3f1, localVector3f2, paramFloat, new Vector3f(), new Vector3f());
    paramclass_797.getTransformedAABB(localVector3f3, localVector3f4, paramFloat, new Vector3f(), new Vector3f());
    return AabbUtil2.testAabbAgainstAabb2(localVector3f1, localVector3f2, localVector3f3, localVector3f4);
  }
  
  public Transform getClientTransform()
  {
    return this.clientTransform;
  }
  
  public class_661 getGravity()
  {
    return this.gravity;
  }
  
  public boolean isGravitySource()
  {
    return false;
  }
  
  public boolean isImmediateStuck()
  {
    return this.immediateStuck;
  }
  
  public void setImmediateStuck(boolean paramBoolean)
  {
    this.immediateStuck = paramBoolean;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_797
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */