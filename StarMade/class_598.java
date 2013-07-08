import javax.vecmath.Vector3f;
import org.schema.schine.network.StateInterface;

public abstract class class_598
  extends class_797
{
  private String field_136 = "SpaceEntityUniqueIdentifier";
  
  public class_598(StateInterface paramStateInterface)
  {
    super(paramStateInterface);
  }
  
  public void getTransformedAABB(Vector3f paramVector3f1, Vector3f paramVector3f2, float paramFloat, Vector3f paramVector3f3, Vector3f paramVector3f4) {}
  
  public void initPhysics() {}
  
  public String getUniqueIdentifier()
  {
    return this.field_136;
  }
  
  public boolean isVolatile()
  {
    return false;
  }
  
  public void handleMovingInput(class_941 paramclass_941, class_755 paramclass_755) {}
  
  public String toNiceString()
  {
    return "SpaceEntity(" + getId() + ")";
  }
  
  public void destroyPersistent() {}
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_598
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */