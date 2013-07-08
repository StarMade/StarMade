import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.linearmath.Transform;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ParticleHandler;
import org.schema.game.common.controller.elements.ShipManagerContainer;
import org.schema.game.common.controller.elements.explosive.ExplosiveCollectionManager;
import org.schema.game.common.controller.elements.explosive.ExplosiveElementManager;
import org.schema.game.common.controller.elements.thrust.ThrusterElementManager;
import org.schema.game.common.data.element.BeamHandler;
import org.schema.game.common.data.element.BeamHandler.BeamState;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.factory.FactoryElement;
import org.schema.game.common.data.element.factory.ManufacturingElement;
import org.schema.game.common.data.element.ship.ShipElement;
import org.schema.game.common.data.element.spacestation.SpaceStationElement;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkSegmentController;
import org.schema.game.network.objects.NetworkShip;
import org.schema.game.network.objects.remote.RemoteSegmentPiece;
import org.schema.game.network.objects.remote.RemoteSegmentPieceBuffer;
import org.schema.game.server.controller.GameServerController;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.NetworkStateContainer;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;
import org.schema.schine.network.objects.remote.RemoteBoolean;
import org.schema.schine.network.objects.remote.RemoteBuffer;
import org.schema.schine.network.objects.remote.RemoteLongPrimitive;
import org.schema.schine.network.objects.remote.RemoteString;

public class class_747
  extends EditableSendableSegmentController
  implements class_75, class_365, class_852, class_739, class_798, ParticleHandler, class_991, class_1421
{
  public static final class_48 field_136;
  private final ArrayList jdField_field_136_of_type_JavaUtilArrayList = new ArrayList();
  private String jdField_field_136_of_type_JavaLangString = "";
  private Set jdField_field_136_of_type_JavaUtilSet = new HashSet();
  private final class_774 jdField_field_136_of_type_Class_774 = new class_766(paramStateInterface, this);
  private final ShipManagerContainer jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer = new ShipManagerContainer(this);
  private long jdField_field_136_of_type_Long = -1L;
  private long jdField_field_139_of_type_Long = -1L;
  private Vector3f jdField_field_136_of_type_JavaxVecmathVector3f = new Vector3f();
  private Vector3f jdField_field_139_of_type_JavaxVecmathVector3f = new Vector3f();
  private class_48 jdField_field_139_of_type_Class_48 = new class_48();
  private class_796 jdField_field_136_of_type_Class_796 = new class_796();
  private class_35 jdField_field_136_of_type_Class_35 = new class_35();
  private int jdField_field_136_of_type_Int;
  private long field_182;
  private class_1074 jdField_field_136_of_type_Class_1074;
  private String jdField_field_139_of_type_JavaLangString;
  
  public class_747(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public boolean allowedType(short paramShort)
  {
    if ((!SpaceStationElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0) {}
    if ((!FactoryElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) != 0) {}
    if ((!ManufacturingElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) == 0) {}
    int i;
    if (((i = ShipElement.class.isAssignableFrom(ElementKeyMap.getInfo(paramShort).getType()) ? 1 : 0) == 0) && (!isOnServer())) {
      ((class_52)getState().getController()).b1("Cannot place\n" + ElementKeyMap.getInfo(paramShort).getName() + "\non a ship");
    }
    return (super.allowedType(paramShort)) && (i != 0);
  }
  
  public void cleanUpOnEntityDelete()
  {
    super.cleanUpOnEntityDelete();
    Iterator localIterator = null;
    if (!this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty())
    {
      localIterator = this.jdField_field_136_of_type_JavaUtilArrayList.iterator();
      while (localIterator.hasNext()) {
        ((class_748)localIterator.next()).a124().b1();
      }
    }
  }
  
  public boolean isClientOwnObject()
  {
    return (!isOnServer()) && (this.jdField_field_136_of_type_JavaUtilArrayList.contains(((class_371)getState()).a20()));
  }
  
  public final class_774 a87()
  {
    return this.jdField_field_136_of_type_Class_774;
  }
  
  public final ArrayList a75()
  {
    return this.jdField_field_136_of_type_JavaUtilArrayList;
  }
  
  public final long a28()
  {
    return this.jdField_field_139_of_type_Long;
  }
  
  public final long b14()
  {
    return this.jdField_field_136_of_type_Long;
  }
  
  public final ShipManagerContainer a112()
  {
    return this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer;
  }
  
  public final float a14()
  {
    return this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getThrusterElementManager().getMaxVelocity();
  }
  
  public int getNearestIntersection(short paramShort, Vector3f paramVector3f1, Vector3f paramVector3f2, class_479 paramclass_479, int paramInt1, boolean paramBoolean, class_713 paramclass_713, class_48 paramclass_48, int paramInt2, float paramFloat, class_433 paramclass_433)
  {
    if ((paramShort == 121) && (this.jdField_field_136_of_type_Class_774.a184() != null))
    {
      if (!isOnServer()) {
        ((class_371)getState()).a4().b1("ERROR\nOnly one AI block is permitted\nper ship");
      }
      return 0;
    }
    if (paramShort == 1)
    {
      if (!isOnServer()) {
        ((class_371)getState()).a4().b1("ERROR\nCore Elements cannot be placed\nthey are used to spawn new ships");
      }
      return 0;
    }
    return super.getNearestIntersection(paramShort, paramVector3f1, paramVector3f2, paramclass_479, paramInt1, paramBoolean, paramclass_713, paramclass_48, paramInt2, paramFloat, paramclass_433);
  }
  
  public final NetworkShip a113()
  {
    return (NetworkShip)super.getNetworkObject();
  }
  
  public class_8 getParticleController()
  {
    if (!isOnServer()) {
      return ((class_371)getState()).getParticleController();
    }
    return ((class_1041)getState()).a62().getSector(getSectorId()).a63();
  }
  
  public final SegmentController a89()
  {
    return this;
  }
  
  public final Set a114()
  {
    return this.jdField_field_136_of_type_JavaUtilSet;
  }
  
  public final Vector3f a8()
  {
    return this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getThrusterElementManager().getVelocity();
  }
  
  public final void a90(class_941 paramclass_941, class_755 paramclass_755)
  {
    paramclass_755.jdField_field_1015_of_type_Class_748.a114().addAll(this.jdField_field_136_of_type_JavaUtilSet);
    if (((paramclass_755.jdField_field_1015_of_type_JavaLangObject instanceof class_48)) && (getPhysicsDataContainer().isInitialized())) {
      this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.handle(paramclass_755);
    }
  }
  
  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, class_809 paramclass_809, float paramFloat)
  {
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onHitNotice();
    super.handleHit(paramClosestRayResultCallback, paramclass_809, paramFloat);
    for (paramClosestRayResultCallback = 0; paramClosestRayResultCallback < this.jdField_field_136_of_type_JavaUtilArrayList.size(); paramClosestRayResultCallback++) {
      ((class_748)this.jdField_field_136_of_type_JavaUtilArrayList.get(paramClosestRayResultCallback)).a109(this);
    }
  }
  
  protected void onCoreHitAlreadyDestroyed(float paramFloat)
  {
    super.onCoreHitAlreadyDestroyed(paramFloat);
    if ((isOnServer()) && ((this instanceof class_747)) && (this.jdField_field_139_of_type_Long > 0L)) {
      this.jdField_field_139_of_type_Long = (Math.max(60000.0F, (float)this.jdField_field_139_of_type_Long - paramFloat * 10.0F));
    }
  }
  
  public final int a115(BeamHandler.BeamState paramBeamState, class_721 paramclass_721, CubeRayCastResult paramCubeRayCastResult, Collection paramCollection)
  {
    class_796 localclass_796 = new class_796(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos);
    if (((paramBeamState = paramclass_721.getHandler().beamHit(paramBeamState, localclass_796)) > 0) && (isOnServer()))
    {
      paramclass_721 = localclass_796.a9();
      if (localclass_796.a7().a20(localclass_796.a6(this.jdField_field_136_of_type_Class_35), false))
      {
        paramCollection.add(paramCubeRayCastResult.getSegment());
        ((class_672)localclass_796.a7()).a46(System.currentTimeMillis());
        localclass_796.a12();
        if ((!jdField_field_136_of_type_Boolean) && (localclass_796.a9() != 0)) {
          throw new AssertionError();
        }
        localclass_796.a15(getState().getId());
        ((NetworkSegmentController)localclass_796.a7().a15().getNetworkObject()).modificationBuffer.add(new RemoteSegmentPiece(localclass_796, this, (NetworkShip)super.getNetworkObject()));
        if (this.jdField_field_136_of_type_JavaUtilArrayList.size() > 0) {
          ((class_748)this.jdField_field_136_of_type_JavaUtilArrayList.get(0)).a125().a1(paramclass_721);
        }
      }
    }
    return paramBeamState;
  }
  
  public final boolean a7()
  {
    return ((Boolean)((NetworkShip)super.getNetworkObject()).cloaked.get()).booleanValue();
  }
  
  public final boolean b2()
  {
    return this.jdField_field_136_of_type_Long > 0L;
  }
  
  public final boolean c3()
  {
    return ((Boolean)((NetworkShip)super.getNetworkObject()).jamming.get()).booleanValue();
  }
  
  public final boolean a86(String[] paramArrayOfString, class_48 paramclass_48)
  {
    if (this.jdField_field_136_of_type_Long < 0L)
    {
      paramArrayOfString[0] = "You can only salvage overheating ships.";
      return false;
    }
    if (jdField_field_136_of_type_Class_48.equals(paramclass_48))
    {
      paramArrayOfString[0] = "Can't salvage core! Please Pick up manually.";
      return false;
    }
    if ((isHomeBase()) || ((getDockingController().b3()) && (getDockingController().a4().field_1740.a7().a15().isHomeBaseFor(getFactionId()))))
    {
      paramArrayOfString[0] = "Cannot salvage: home base protected";
      return false;
    }
    if (this.jdField_field_136_of_type_Class_774.a7())
    {
      paramArrayOfString[0] = "Can only salvage defeated AI ships!";
      return false;
    }
    if (this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty()) {
      return true;
    }
    paramArrayOfString[0] = "Can only salvage empty ships!";
    return false;
  }
  
  public void newNetworkObject()
  {
    setNetworkObject(new NetworkShip(getState(), this));
  }
  
  public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean)
  {
    switch (paramShort)
    {
    case 1: 
      isOnServer();
    }
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onAddedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
    super.onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, paramSegment, paramBoolean);
    if ((this.jdField_field_136_of_type_Class_1074 != null) && (isOnServer()) && (getLastModifier().length() == 0) && (this.jdField_field_136_of_type_Class_1074.a3().b1() < getElementClassCountMap().b1()))
    {
      System.err.println("[SERVER] WARNING: Possible modified Blueprint: " + this.jdField_field_136_of_type_Class_1074.a4() + " spawned by " + getSpawner());
      paramShort = "MODIFIED BLUEPRINT WARNING\n" + this.jdField_field_139_of_type_JavaLangString + " spawned blueprint\n" + this.jdField_field_136_of_type_Class_1074.a4() + "\nBB-Price: " + this.jdField_field_136_of_type_Class_1074.a3().b1() + "\nPrice of actual spawned: " + getElementClassCountMap().b1();
      System.err.println(paramShort);
      ((class_1041)getState()).a59().broadcastMessage(paramShort, 3);
      ((class_1041)getState()).a76(this.jdField_field_136_of_type_Class_1074, this.jdField_field_139_of_type_JavaLangString);
    }
  }
  
  public final void a91(class_748 paramclass_748, Sendable paramSendable, class_48 paramclass_48)
  {
    a13();
    if (((paramSendable instanceof class_750)) && (((class_750)paramSendable).getGravity().field_928 != null) && (((class_750)paramSendable).getGravity().field_928 != this))
    {
      System.err.println("[SHIP] removing gravity due to entering a ship != current gravity entity " + paramSendable + " -> " + this + "; current: " + ((class_750)paramSendable).getGravity().field_928);
      ((class_750)paramSendable).removeGravity();
    }
    if ((isOnServer()) && (paramclass_748.c2() != getSectorId()))
    {
      System.err.println("[SERVER][ONATTACHPLAYER] entering! " + this + " in a different sector");
      if (isOnServer()) {
        paramSendable = ((class_1041)getState()).a62().getSector(getSectorId()).jdField_field_136_of_type_Class_48;
      } else {
        paramSendable = ((class_665)getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSectorId())).a34();
      }
      paramclass_748.a73(new class_48(paramSendable));
      paramclass_748.c13(getSectorId());
      if ((paramclass_48 = paramclass_748.a121()) != null)
      {
        System.err.println("[SERVER][ONATTACHPLAYER] entering! Moving along playercharacter " + this + " in a different sector");
        paramclass_48.setSectorId(getSectorId());
      }
      else
      {
        System.err.println("[SERVER] WARNING NO PLAYER CHARACTER ATTACHED TO " + paramclass_748);
      }
    }
    if ((!isOnServer()) && ((paramSendable = (class_371)getState()).a20() == paramclass_748))
    {
      class_969.a().a5(this, "0022_ambience loop - interior cockpit (loop)", 1.0F, 1.0F);
      if (!paramSendable.a20().a131(this))
      {
        paramclass_48 = new class_359(paramSendable.a20(), getUniqueIdentifier());
        paramSendable.a20().a75().add(paramclass_48);
        paramclass_48.a20(9, new class_48(8, 8, 8), true);
      }
      paramSendable.a14().a18().a79().a60().a51().c2(true);
    }
  }
  
  public void onCollision(ManifoldPoint paramManifoldPoint, Sendable paramSendable)
  {
    super.onCollision(paramManifoldPoint, paramSendable);
    if ((isOnServer()) && ((paramSendable instanceof EditableSendableSegmentController)) && (!this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getExplosiveCollectionManager().getCollection().isEmpty()))
    {
      paramManifoldPoint.getPositionWorldOnB(this.jdField_field_136_of_type_JavaxVecmathVector3f);
      getWorldTransformInverse().transform(this.jdField_field_136_of_type_JavaxVecmathVector3f);
      this.jdField_field_136_of_type_JavaxVecmathVector3f.add(this.jdField_field_139_of_type_JavaxVecmathVector3f);
      try
      {
        for (int i = -1; i < 2; i++) {
          for (int j = -1; j < 2; j++) {
            for (int k = -1; k < 2; k++)
            {
              this.jdField_field_139_of_type_Class_48.b(Math.round(this.jdField_field_136_of_type_JavaxVecmathVector3f.field_615) + 8, Math.round(this.jdField_field_136_of_type_JavaxVecmathVector3f.field_616) + 8, Math.round(this.jdField_field_136_of_type_JavaxVecmathVector3f.field_617) + 8);
              this.jdField_field_139_of_type_Class_48.field_475 += i;
              this.jdField_field_139_of_type_Class_48.field_476 += j;
              this.jdField_field_139_of_type_Class_48.field_477 += k;
              class_796 localclass_796;
              if (((localclass_796 = getSegmentBuffer().a10(this.jdField_field_139_of_type_Class_48, false, this.jdField_field_136_of_type_Class_796)) != null) && (localclass_796.a9() == 14)) {
                this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.getExplosiveElementManager().addExplosion(this.jdField_field_139_of_type_Class_48, paramManifoldPoint.getPositionWorldOnB(new Vector3f()), (EditableSendableSegmentController)paramSendable, localclass_796);
              }
            }
          }
        }
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
    }
  }
  
  protected void onCoreDestroyed(class_809 paramclass_809)
  {
    for (int i = 0; i < this.jdField_field_136_of_type_JavaUtilArrayList.size(); i++) {
      ((class_748)this.jdField_field_136_of_type_JavaUtilArrayList.get(i)).b22(0.0F, paramclass_809);
    }
    this.jdField_field_136_of_type_Class_774.a117(paramclass_809);
    if ((isOnServer()) && (this.jdField_field_136_of_type_Long < 0L))
    {
      this.jdField_field_136_of_type_Long = System.currentTimeMillis();
      this.jdField_field_139_of_type_Long = Math.min(900000, getTotalElements() * 1000);
      System.err.println("[SERVER] MAIN CORE STARTED DESTRUCTION: in " + this.jdField_field_139_of_type_Long / 1000L + " - started " + this.jdField_field_136_of_type_Long);
    }
  }
  
  protected void onDamageServer(int paramInt, class_809 paramclass_809)
  {
    this.jdField_field_136_of_type_Class_774.b36(paramclass_809);
  }
  
  public final void a92(class_748 paramclass_748, boolean paramBoolean)
  {
    if ((!isOnServer()) && ((paramBoolean = (class_371)getState()).a20() == paramclass_748) && (((class_371)getState()).a20() == paramclass_748))
    {
      paramBoolean.a14().a18().a79().a60().a51().c2(false);
      class_969.a().a5(this, "0022_spaceship user - small engine thruster loop", 1.0F, 1.0F);
    }
    a13();
  }
  
  public void onProximity(SegmentController paramSegmentController)
  {
    this.jdField_field_136_of_type_Class_774.a168(paramSegmentController);
  }
  
  public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
  {
    switch (paramShort)
    {
    case 1: 
      if (!isOnServer()) {
        class_969.a().a10(this);
      }
      break;
    }
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
    super.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
  }
  
  private void a13()
  {
    if (getRealName().equals("undef")) {
      return;
    }
    StringBuffer localStringBuffer;
    (localStringBuffer = new StringBuffer()).append(getRealName());
    if (!this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty())
    {
      localStringBuffer.append(" <");
      for (int i = 0; i < this.jdField_field_136_of_type_JavaUtilArrayList.size(); i++) {
        try
        {
          localStringBuffer.append(((class_748)this.jdField_field_136_of_type_JavaUtilArrayList.get(i)).getName());
          if (i < this.jdField_field_136_of_type_JavaUtilArrayList.size() - 1) {
            localStringBuffer.append(", ");
          }
        }
        catch (Exception localException)
        {
          localException;
        }
      }
      localStringBuffer.append(">");
    }
    if (getFactionId() != 0)
    {
      localStringBuffer.append("[");
      class_773 localclass_773;
      if ((localclass_773 = ((class_1039)getState()).a().a156(getFactionId())) != null)
      {
        localStringBuffer.append(localclass_773.a());
      }
      else
      {
        localStringBuffer.append("factionUnknown");
        localStringBuffer.append(getFactionId());
      }
      localStringBuffer.append("]");
    }
    this.jdField_field_136_of_type_JavaLangString = localStringBuffer.toString();
  }
  
  public void startCreatorThread()
  {
    if (getCreatorThread() == null) {
      setCreatorThread(new class_912(this));
    }
  }
  
  public String toNiceString()
  {
    if (((String)((NetworkShip)super.getNetworkObject()).debugState.get()).length() > 0) {
      return this.jdField_field_136_of_type_JavaLangString + (String)((NetworkShip)super.getNetworkObject()).debugState.get() + "\n[CLIENTAI " + (this.jdField_field_136_of_type_Class_774.a7() ? "ACTIVE" : "INACTIVE") + "] " + this.jdField_field_136_of_type_Class_774.a181();
    }
    getState().getLocalAndRemoteObjectContainer().getLocalObjects().get(getSectorId());
    if (!isOnServer()) {
      return this.jdField_field_136_of_type_JavaLangString;
    }
    return this.jdField_field_136_of_type_JavaLangString;
  }
  
  public String toString()
  {
    return "Ship[" + getRealName() + "](" + getId() + ")";
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    super.updateFromNetworkObject(paramNetworkObject);
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.updateFromNetworkObject(paramNetworkObject);
    this.jdField_field_136_of_type_Class_774.d2();
    if (!isOnServer())
    {
      this.jdField_field_136_of_type_Long = ((NetworkShip)paramNetworkObject).coreDestructionStarted.get().longValue();
      this.jdField_field_139_of_type_Long = ((NetworkShip)paramNetworkObject).coreDestructionDuration.get().longValue();
      if (!((NetworkShip)paramNetworkObject).onHitNotices.getReceiveBuffer().isEmpty()) {
        this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.onHitNotice();
      }
    }
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    if (!isOnServer()) {
      if ((!this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty()) && (((class_748)this.jdField_field_136_of_type_JavaUtilArrayList.get(0)).a7()))
      {
        getRemoteTransformable().field_116 = false;
        getRemoteTransformable().a2(true);
      }
      else
      {
        getRemoteTransformable().field_116 = true;
        getRemoteTransformable().a2(false);
      }
    }
    super.updateLocal(paramclass_941);
    if ((!getDockingController().b3()) && (this.flagUpdateMass)) {
      if (isOnServer())
      {
        if (updateMass()) {
          this.flagUpdateMass = false;
        }
      }
      else
      {
        getPhysicsDataContainer().updateMass(getMass(), true);
        this.flagUpdateMass = false;
      }
    }
    label209:
    Object localObject;
    if (this.jdField_field_136_of_type_Long > 0L)
    {
      long l1 = System.currentTimeMillis() - this.jdField_field_136_of_type_Long;
      long l3 = this.jdField_field_139_of_type_Long - l1;
      if ((l1 <= 1000L) || (this.jdField_field_136_of_type_JavaUtilArrayList.size() <= 0))
      {
        if (l1 <= this.jdField_field_139_of_type_Long) {
          break label209;
        }
        if (isOnServer()) {
          destroy();
        }
      }
      this.jdField_field_136_of_type_Long = -1L;
      this.jdField_field_139_of_type_Long = -1L;
      if (!isOnServer())
      {
        float f1 = 3.0E-005F;
        if (l3 < 4000L) {
          f1 = 0.1F;
        } else if (l3 < 30000L) {
          f1 = 0.02F;
        } else if (l3 < 120000L) {
          f1 = 0.003F;
        } else if (l3 < 240000L) {
          f1 = 0.0005F;
        }
        if (Math.random() < f1)
        {
          class_371 localclass_371 = (class_371)getState();
          Vector3f localVector3f = new Vector3f();
          getSegmentBuffer().a6().a1(localVector3f);
          float f2 = localVector3f.length() / 2.0F;
          localObject = new Vector3f((float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D));
          while (((Vector3f)localObject).lengthSquared() == 0.0F) {
            ((Vector3f)localObject).set((float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D), (float)(Math.random() - 0.5D));
          }
          ((Vector3f)localObject).normalize();
          ((Vector3f)localObject).scale((float)(f2 * (1.0D + Math.random())));
          localVector3f.set(getWorldTransform().origin);
          localVector3f.add((Tuple3f)localObject);
          localclass_371.a27().a91().a23(localVector3f, (float)(2.0D + Math.random() * 40.0D));
        }
      }
    }
    if (isOnServer()) {
      try
      {
        if ((getTotalElements() <= 0) && (System.currentTimeMillis() - this.field_182 > 2000L) && (System.currentTimeMillis() - getTimeCreated() > 5000L) && (isEmptyOnServer()))
        {
          if (((localObject = getSegmentBuffer().a9(new class_48(jdField_field_136_of_type_Class_48), true)) != null) && (((class_796)localObject).a9() != 1))
          {
            System.err.println("[SERVER][SHIP] Empty SHIP: deleting " + this);
            markedForPermanentDelete(true);
            setMarkedForDeleteVolatile(true);
          }
          this.field_182 = System.currentTimeMillis();
        }
      }
      catch (IOException localIOException)
      {
        localIOException;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException;
      }
    }
    long l2 = System.currentTimeMillis();
    try
    {
      this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.updateLocal(paramclass_941);
    }
    catch (Exception localException)
    {
      System.err.println("Exception catched: " + localException + " printing startrace and then rethrowing");
      localException.printStackTrace();
      throw new ErrorDialogException(localException.getClass().getSimpleName() + ": " + localException.getMessage());
    }
    long l4;
    if ((l4 = System.currentTimeMillis() - l2) > 20L) {
      System.err.println("[SHIP] " + getState() + " " + this + " manager udpate took " + l4);
    }
    l2 = System.currentTimeMillis();
    if ((this.jdField_field_136_of_type_JavaLangString.length() == 0) || (this.jdField_field_136_of_type_Int != getFactionId()))
    {
      a13();
      this.jdField_field_136_of_type_Int = getFactionId();
    }
    long l5;
    if ((l5 = System.currentTimeMillis() - l2) > 5L) {
      System.err.println("[SHIP] " + getState() + " " + this + " name udpate took " + l5);
    }
    l2 = System.currentTimeMillis();
    this.jdField_field_136_of_type_Class_774.a74(paramclass_941);
    long l6;
    if ((l6 = System.currentTimeMillis() - l2) > 15L) {
      System.err.println("[SHIP] " + getState() + " " + this + " AI udpate took " + l6);
    }
  }
  
  public boolean updateMass()
  {
    if (isOnServer())
    {
      float f = Math.max(0.1F, getTotalElements() * 0.1F);
      setMass(f);
      return getPhysicsDataContainer().updateMass(f, false);
    }
    return true;
  }
  
  public void updateToFullNetworkObject()
  {
    super.updateToFullNetworkObject();
    if (isOnServer())
    {
      super.getNetworkObject();
      this.jdField_field_136_of_type_Class_774.c1();
      this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.updateToFullNetworkObject((NetworkShip)super.getNetworkObject());
    }
  }
  
  public void updateToNetworkObject()
  {
    super.updateToNetworkObject();
    if (isOnServer())
    {
      ((NetworkShip)super.getNetworkObject()).jamming.set(Boolean.valueOf(this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.isJamming()));
      ((NetworkShip)super.getNetworkObject()).cloaked.set(Boolean.valueOf(this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsShipManagerContainer.isCloaked()));
      ((NetworkShip)super.getNetworkObject()).coreDestructionStarted.set(this.jdField_field_136_of_type_Long);
      ((NetworkShip)super.getNetworkObject()).coreDestructionDuration.set(this.jdField_field_139_of_type_Long);
      this.jdField_field_136_of_type_Class_774.a186((NetworkShip)super.getNetworkObject());
    }
  }
  
  public void getRelationColor(class_762 paramclass_762, Vector4f paramVector4f, float paramFloat)
  {
    switch (class_745.field_1013[paramclass_762.ordinal()])
    {
    case 1: 
      paramVector4f.field_596 = (paramFloat + 1.0F);
      paramVector4f.field_597 = paramFloat;
      paramVector4f.field_598 = paramFloat;
      return;
    case 2: 
      paramVector4f.field_596 = paramFloat;
      paramVector4f.field_597 = (paramFloat + 1.0F);
      paramVector4f.field_598 = paramFloat;
      return;
    case 3: 
      if (this.jdField_field_136_of_type_JavaUtilArrayList.isEmpty())
      {
        paramVector4f.field_596 = (paramFloat + 0.5F);
        paramVector4f.field_597 = (paramFloat + 0.7F);
        paramVector4f.field_598 = (paramFloat + 0.9F);
        return;
      }
      paramVector4f.field_596 = (paramFloat + 0.3F);
      paramVector4f.field_597 = (paramFloat + 0.5F);
      paramVector4f.field_598 = (paramFloat + 0.7F);
    }
  }
  
  protected String getSegmentControllerTypeString()
  {
    return "Ship";
  }
  
  public final String a()
  {
    return "0022_ambience loop - interior cockpit (loop)";
  }
  
  public final String b()
  {
    return "0022_spaceship user - small engine thruster loop";
  }
  
  public final float b1()
  {
    return 1.0F;
  }
  
  public final void a116(class_1074 paramclass_1074, String paramString)
  {
    this.jdField_field_136_of_type_Class_1074 = paramclass_1074;
    this.jdField_field_139_of_type_JavaLangString = paramString;
  }
  
  static
  {
    jdField_field_136_of_type_Class_48 = new class_48(8, 8, 8);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_747
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */