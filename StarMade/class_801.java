import javax.vecmath.Vector3f;
import org.schema.schine.network.Identifiable;
import org.schema.schine.network.StateInterface;

final class class_801
  extends class_858
{
  class_801(class_797 paramclass_797, Identifiable paramIdentifiable, StateInterface paramStateInterface)
  {
    super(paramIdentifiable, paramStateInterface);
  }
  
  public final StateInterface getState()
  {
    return this.field_116.getState();
  }
  
  public final void getTransformedAABB(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3, Vector3f paramVector3f4)
  {
    this.field_116.getTransformedAABB(paramVector3f1, paramVector3f2, paramFloat, paramVector3f3, paramVector3f4);
  }
  
  public final void initPhysics()
  {
    this.field_116.initPhysics();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_801
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */