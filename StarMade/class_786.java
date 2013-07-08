import com.bulletphysics.collision.dispatch.CollisionWorld.ClosestRayResultCallback;
import com.bulletphysics.dynamics.RigidBody;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.network.objects.NetworkVehicle;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.NetworkObject;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public class class_786
  extends EditableSendableSegmentController
  implements class_991
{
  public class_786(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void fromTagStructure(class_69 paramclass_69)
  {
    if ((!field_136) && (!paramclass_69.a2().equals(getClass().getSimpleName()))) {
      throw new AssertionError();
    }
    paramclass_69 = (class_69[])paramclass_69.a4();
    super.fromTagStructure(paramclass_69[0]);
  }
  
  protected short getCoreType()
  {
    return 65;
  }
  
  public void handleHit(CollisionWorld.ClosestRayResultCallback paramClosestRayResultCallback, class_809 paramclass_809, float paramFloat)
  {
    super.handleHit(paramClosestRayResultCallback, paramclass_809, paramFloat);
  }
  
  public void initialize()
  {
    super.initialize();
    setMass(0.01F);
  }
  
  public final boolean a86(String[] paramArrayOfString, class_48 paramclass_48)
  {
    return true;
  }
  
  public void newNetworkObject()
  {
    setNetworkObject(new NetworkVehicle(getState(), this));
  }
  
  protected void onCoreDestroyed(class_809 paramclass_809) {}
  
  public void onPhysicsAdd()
  {
    super.onPhysicsAdd();
    ((RigidBody)getPhysicsDataContainer().getObject()).setGravity(new Vector3f(0.0F, -1.89F, 0.0F));
  }
  
  public void startCreatorThread()
  {
    if (getCreatorThread() == null) {
      setCreatorThread(new class_912(this));
    }
  }
  
  public String toNiceString()
  {
    return "Vehicle";
  }
  
  public class_69 toTagStructure()
  {
    return new class_69(class_79.field_561, getClass().getSimpleName(), new class_69[] { super.toTagStructure(), new class_69(class_79.field_548, null, null) });
  }
  
  public void updateFromNetworkObject(NetworkObject paramNetworkObject)
  {
    super.updateFromNetworkObject(paramNetworkObject);
  }
  
  public void updateLocal(class_941 paramclass_941)
  {
    super.updateLocal(paramclass_941);
  }
  
  public void updateToFullNetworkObject()
  {
    super.updateToFullNetworkObject();
  }
  
  protected String getSegmentControllerTypeString()
  {
    return "Vehicle";
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_786
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */