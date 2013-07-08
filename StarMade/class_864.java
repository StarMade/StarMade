import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.linearmath.AabbUtil2;
import com.bulletphysics.linearmath.Transform;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.vecmath.Vector3f;
import org.schema.common.FastMath;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ParticleHandler;
import org.schema.game.common.controller.elements.PlanetManagerContainer;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.physics.PhysicsExt;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkPlanet;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public class class_864
  extends EditableSendableSegmentController
  implements class_365, class_798, ParticleHandler, class_991
{
  private final ArrayList jdField_field_136_of_type_JavaUtilArrayList = new ArrayList();
  private final PlanetManagerContainer jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsPlanetManagerContainer = new PlanetManagerContainer(this);
  private class_764 jdField_field_136_of_type_Class_764 = new class_764(paramStateInterface, this);
  private class_810 jdField_field_136_of_type_Class_810 = class_810.field_1073;
  private final Vector3f jdField_field_136_of_type_JavaxVecmathVector3f = new Vector3f();
  private final Vector3f field_139 = new Vector3f();
  private final Vector3f field_182 = new Vector3f();
  private final Vector3f field_183 = new Vector3f();
  
  public class_864(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public boolean isClientOwnObject()
  {
    return (!isOnServer()) && (this.jdField_field_136_of_type_JavaUtilArrayList.contains(((class_371)getState()).a20()));
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if ((!jdField_field_136_of_type_Boolean) && (!paramclass_69.a2().equals("Planet"))) {
      throw new AssertionError();
    }
    paramclass_69 = (class_69[])paramclass_69.a4();
    super.fromTagStructure(paramclass_69[1]);
  }
  
  public final ArrayList a75()
  {
    return this.jdField_field_136_of_type_JavaUtilArrayList;
  }
  
  protected short getCoreType()
  {
    return 65;
  }
  
  public int getCreatorId()
  {
    return this.jdField_field_136_of_type_Class_810.ordinal();
  }
  
  public final PlanetManagerContainer a207()
  {
    return this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsPlanetManagerContainer;
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
  
  public final void a90(class_941 paramclass_941, class_755 paramclass_755)
  {
    if (((paramclass_755.field_1015 instanceof class_48)) && (getPhysicsDataContainer().isInitialized())) {
      this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsPlanetManagerContainer.handle(paramclass_755);
    }
  }
  
  public void initialize()
  {
    super.initialize();
    setMass(0.0F);
    setRealName("Planet");
  }
  
  public boolean isInboundSegmentPos(int paramInt1, int paramInt2, int paramInt3)
  {
    boolean bool = true;
    int i = 256 - paramInt1;
    int j = 256 - paramInt3;
    if (getMinPos().field_475 < -1)
    {
      i = Math.abs(paramInt1);
      j = Math.abs(paramInt3);
    }
    float f = FastMath.l(i * i + j * j);
    if ((paramInt2 < -64) || (paramInt2 > 64) || (f > 272.0F)) {
      bool = false;
    }
    if (getMaxPos() != null) {
      bool = (bool) && (paramInt1 <= getMaxPos().field_475 << 4) && (paramInt2 <= getMaxPos().field_476 << 4) && (paramInt3 <= getMaxPos().field_477 << 4);
    }
    if (getMinPos() != null) {
      bool = (bool) && (paramInt1 >= getMinPos().field_475 << 4) && (paramInt2 >= getMinPos().field_476 << 4) && (paramInt3 >= getMinPos().field_477 << 4);
    }
    return bool;
  }
  
  public final boolean a86(String[] paramArrayOfString, class_48 paramclass_48)
  {
    if ((isHomeBase()) || ((getDockingController().a4() != null) && (getDockingController().a4().field_1740.a7().a15().isHomeBaseFor(getFactionId()))))
    {
      paramArrayOfString[0] = "Cannot salvage: home base protected";
      return false;
    }
    return true;
  }
  
  public void newNetworkObject()
  {
    setNetworkObject(new NetworkPlanet(getState(), this));
  }
  
  public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean)
  {
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsPlanetManagerContainer.onAddedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
    super.onAddedElement(paramShort, paramByte1, paramByte2, paramByte3, paramInt, paramSegment, paramBoolean);
  }
  
  public final void a91(class_748 paramclass_748, Sendable paramSendable, class_48 paramclass_48)
  {
    if ((!isOnServer()) && (((class_371)getState()).a20() == paramclass_748) && ((paramSendable = (class_371)getState()).a20() == paramclass_748))
    {
      paramSendable.a14().a18().a79().a60().a53().c2(true);
      System.err.println("Entering space stationc ");
    }
  }
  
  protected void onCoreDestroyed(class_809 paramclass_809) {}
  
  public final void a92(class_748 paramclass_748, boolean paramBoolean)
  {
    if ((!isOnServer()) && ((paramBoolean = (class_371)getState()).a20() == paramclass_748) && (((class_371)getState()).a20() == paramclass_748)) {
      paramBoolean.a14().a18().a79().a60().a53().c2(false);
    }
  }
  
  public void onRemovedElement(short paramShort, int paramInt, byte paramByte1, byte paramByte2, byte paramByte3, Segment paramSegment, boolean paramBoolean)
  {
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsPlanetManagerContainer.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
    super.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
  }
  
  public void setCreatorId(int paramInt)
  {
    this.jdField_field_136_of_type_Class_810 = class_810.values()[paramInt];
  }
  
  public void startCreatorThread()
  {
    if (getCreatorThread() == null) {
      setCreatorThread(new class_910(this, this.jdField_field_136_of_type_Class_810));
    }
  }
  
  public String toNiceString()
  {
    return "Planet";
  }
  
  public String toString()
  {
    return "Planet(" + getId() + ")[s" + getSectorId() + "]";
  }
  
  public class_69 toTagStructure()
  {
    return new class_69(class_79.field_561, "Planet", new class_69[] { new class_69(class_79.field_549, null, Byte.valueOf(0)), super.toTagStructure(), new class_69(class_79.field_548, null, null) });
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    super.updateFromNetworkObject(paramNetworkObject);
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsPlanetManagerContainer.updateFromNetworkObject(paramNetworkObject);
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    super.updateLocal(paramclass_941);
    if (isOnServer()) {
      try
      {
        this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsPlanetManagerContainer.updateLocal(paramclass_941);
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
        return;
      }
    }
    try
    {
      this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsPlanetManagerContainer.updateLocal(paramclass_941);
      return;
    }
    catch (Exception localException)
    {
      (paramclass_941 = localException).printStackTrace();
      throw new ErrorDialogException(paramclass_941.getMessage());
    }
  }
  
  public void updateToFullNetworkObject()
  {
    super.updateToFullNetworkObject();
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsPlanetManagerContainer.updateToFullNetworkObject((NetworkPlanet)super.getNetworkObject());
  }
  
  public final class_810 a69()
  {
    return this.jdField_field_136_of_type_Class_810;
  }
  
  public boolean isGravitySource()
  {
    return true;
  }
  
  protected boolean affectsGravityOf(class_797 paramclass_797)
  {
    if ((paramclass_797.getSectorId() == getSectorId()) && ((paramclass_797.getMass() > 0.0F) || ((paramclass_797 instanceof class_750))))
    {
      Object localObject = paramclass_797;
      paramclass_797 = this;
      int i;
      if ((getSectorId() == ((class_797)localObject).getSectorId()) && (paramclass_797.getPhysicsDataContainer() != null) && (paramclass_797.getPhysicsDataContainer().isInitialized()))
      {
        paramclass_797.getGravityAABB(paramclass_797.jdField_field_136_of_type_JavaxVecmathVector3f, paramclass_797.field_139);
        ((class_797)localObject).getGravityAABB(paramclass_797.field_182, paramclass_797.field_183);
        if (AabbUtil2.testAabbAgainstAabb2(paramclass_797.field_182, paramclass_797.field_183, paramclass_797.jdField_field_136_of_type_JavaxVecmathVector3f, paramclass_797.field_139))
        {
          Vector3f localVector3f1 = new Vector3f(((class_797)localObject).getWorldTransform().origin);
          Vector3f localVector3f2;
          (localVector3f2 = new Vector3f(((class_797)localObject).getWorldTransform().origin)).field_616 -= 64.0F;
          localObject = new CubeRayCastResult(localVector3f1, localVector3f2, localObject, null);
          if (((paramclass_797 = paramclass_797.getPhysics().testRayCollisionPoint(localVector3f1, localVector3f2, (CubeRayCastResult)localObject, true)) != null) && (paramclass_797.hasHit()) && (((CubeRayCastResult)localObject).getSegment() != null))
          {
            i = (paramclass_797 = ((CubeRayCastResult)localObject).getSegment()).field_34.field_476 + 16 <= paramclass_797.a15().getMaxPos().field_476 << 4 ? 1 : 0;
            paramclass_797 = paramclass_797.a15().getSegmentBuffer().a2(paramclass_797.field_34.field_475, paramclass_797.field_34.field_476 + 16, paramclass_797.field_34.field_477);
          }
        }
      }
      if ((((i != 0) && (paramclass_797 == 0) ? 1 : 0) == 0 ? 1 : 0) != 0) {
        return true;
      }
    }
    return false;
  }
  
  public void getGravityAABB(Vector3f paramVector3f1, Vector3f paramVector3f2)
  {
    super.getGravityAABB(paramVector3f1, paramVector3f2);
    paramVector3f2.field_616 += 32.0F;
  }
  
  protected String getSegmentControllerTypeString()
  {
    return "Planet";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_864
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */