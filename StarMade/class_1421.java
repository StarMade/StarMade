import javax.vecmath.Vector3f;
import org.schema.schine.network.StateInterface;
import org.schema.schine.network.objects.container.PhysicsDataContainer;

public abstract interface class_1421
{
  public abstract float getMass();
  
  public abstract PhysicsDataContainer getPhysicsDataContainer();
  
  public abstract StateInterface getState();
  
  public abstract void getTransformedAABB(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3, Vector3f paramVector3f4);
  
  public abstract void initPhysics();
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_1421
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */