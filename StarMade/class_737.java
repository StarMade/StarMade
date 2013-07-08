import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.vecmath.Vector4f;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.ParticleHandler;
import org.schema.game.common.controller.elements.SpaceStationManagerContainer;
import org.schema.game.common.data.element.ElementDocking;
import org.schema.game.common.data.world.Segment;
import org.schema.game.common.data.world.Universe;
import org.schema.game.network.objects.NetworkSpaceStation;
import org.schema.schine.graphicsengine.shader.ErrorDialogException;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.Sendable;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public class class_737
  extends EditableSendableSegmentController
  implements class_365, class_798, ParticleHandler, class_991
{
  private final ArrayList jdField_field_136_of_type_JavaUtilArrayList = new ArrayList();
  private final SpaceStationManagerContainer jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer = new SpaceStationManagerContainer(this);
  private class_768 jdField_field_136_of_type_Class_768 = new class_768(paramStateInterface, this);
  private class_780 jdField_field_136_of_type_Class_780 = class_780.field_1037;
  
  public class_737(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public boolean isClientOwnObject()
  {
    return (!isOnServer()) && (this.jdField_field_136_of_type_JavaUtilArrayList.contains(((class_371)getState()).a20()));
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if ((!jdField_field_136_of_type_Boolean) && (!paramclass_69.a2().equals("SpaceStation"))) {
      throw new AssertionError();
    }
    paramclass_69 = (class_69[])paramclass_69.a4();
    super.fromTagStructure(paramclass_69[1]);
  }
  
  public final class_774 a87()
  {
    return this.jdField_field_136_of_type_Class_768;
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
    return this.jdField_field_136_of_type_Class_780.ordinal();
  }
  
  public final SpaceStationManagerContainer a88()
  {
    return this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer;
  }
  
  public class_8 getParticleController()
  {
    if (!isOnServer()) {
      return ((class_371)getState()).getParticleController();
    }
    return ((class_1041)getState()).a62().getSector(getSectorId()).a63();
  }
  
  public String toString()
  {
    return "SpaceStation[" + getUniqueIdentifier() + "(" + getId() + ")]";
  }
  
  public final SegmentController a89()
  {
    return this;
  }
  
  public final void a90(class_941 paramclass_941, class_755 paramclass_755)
  {
    if (((paramclass_755.field_1015 instanceof class_48)) && (getPhysicsDataContainer().isInitialized())) {
      this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.handle(paramclass_755);
    }
  }
  
  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, class_809 paramclass_809, float paramFloat)
  {
    super.handleHit(paramClosestRayResultCallback, paramclass_809, paramFloat);
  }
  
  public void initialize()
  {
    super.initialize();
    setMass(0.0F);
  }
  
  public final boolean a86(String[] paramArrayOfString, class_48 paramclass_48)
  {
    if (((class_1039)getState()).a().a18(getFactionId()))
    {
      paramArrayOfString[0] = "Faction owned station not salvagable\ndestroy faction block first";
      return false;
    }
    if ((isHomeBase()) || ((getDockingController().a4() != null) && (getDockingController().a4().field_1740.a7().a15().isHomeBaseFor(getFactionId()))))
    {
      paramArrayOfString[0] = "Cannot salvage: home base protected";
      return false;
    }
    return true;
  }
  
  public void newNetworkObject()
  {
    setNetworkObject(new NetworkSpaceStation(getState(), this));
  }
  
  public void onAddedElement(short paramShort, byte paramByte1, byte paramByte2, byte paramByte3, int paramInt, Segment paramSegment, boolean paramBoolean)
  {
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.onAddedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment);
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
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
    super.onRemovedElement(paramShort, paramInt, paramByte1, paramByte2, paramByte3, paramSegment, paramBoolean);
  }
  
  public void setCreatorId(int paramInt)
  {
    this.jdField_field_136_of_type_Class_780 = class_780.values()[paramInt];
  }
  
  public void startCreatorThread()
  {
    if (getCreatorThread() == null) {
      setCreatorThread(new class_728(this, this.jdField_field_136_of_type_Class_780));
    }
  }
  
  public String toNiceString()
  {
    return getRealName();
  }
  
  public class_69 toTagStructure()
  {
    return new class_69(class_79.field_561, "SpaceStation", new class_69[] { this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.toTagStructure(), super.toTagStructure(), new class_69(class_79.field_548, null, null) });
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    super.updateFromNetworkObject(paramNetworkObject);
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.updateFromNetworkObject(paramNetworkObject);
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    super.updateLocal(paramclass_941);
    try
    {
      this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.updateLocal(paramclass_941);
    }
    catch (Exception localException)
    {
      (paramclass_941 = localException).printStackTrace();
      throw new ErrorDialogException(paramclass_941.getMessage());
    }
    try
    {
      if ((isOnServer()) && (getTotalElements() <= 0) && (System.currentTimeMillis() - getTimeCreated() > 3000L) && (isEmptyOnServer()))
      {
        System.err.println("[SERVER][SPACESTATION] Empty station: deleting!!!!!!!!!!!!!!!!!!! " + this);
        setMarkedForDeleteVolatile(true);
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
  
  public void updateToFullNetworkObject()
  {
    super.updateToFullNetworkObject();
    this.jdField_field_136_of_type_OrgSchemaGameCommonControllerElementsSpaceStationManagerContainer.updateToFullNetworkObject((NetworkSpaceStation)super.getNetworkObject());
  }
  
  public void getRelationColor(class_762 paramclass_762, Vector4f paramVector4f, float paramFloat)
  {
    switch (class_735.field_1008[paramclass_762.ordinal()])
    {
    case 1: 
      paramVector4f.field_596 = (paramFloat + 0.8F);
      paramVector4f.field_597 = (paramFloat + 0.3F);
      paramVector4f.field_598 = (paramFloat + 0.1F);
      return;
    case 2: 
      paramVector4f.field_596 = (paramFloat + 0.3F);
      paramVector4f.field_597 = (paramFloat + 0.8F);
      paramVector4f.field_598 = (paramFloat + 0.1F);
      return;
    case 3: 
      paramVector4f.field_596 = 0.3F;
      paramVector4f.field_597 = (paramFloat + 0.2F);
      paramVector4f.field_598 = 0.7F;
    }
  }
  
  protected String getSegmentControllerTypeString()
  {
    return "Station";
  }
  
  public static void a13() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_737
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */