import javax.vecmath.Vector3f;
import org.schema.game.common.data.element.BeamHandler.BeamState;
import org.schema.game.common.data.physics.CubeRayCastResult;

public abstract interface class_854
  extends class_1382, class_1421
{
  public abstract void handleBeingSalvaged(BeamHandler.BeamState paramBeamState, class_721 paramclass_721, Vector3f paramVector3f, CubeRayCastResult paramCubeRayCastResult, int paramInt);
  
  public abstract boolean isRepariableFor(class_852 paramclass_852, String[] paramArrayOfString, class_48 paramclass_48);
  
  public abstract boolean a4(String[] paramArrayOfString, class_48 paramclass_48);
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     class_854
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */