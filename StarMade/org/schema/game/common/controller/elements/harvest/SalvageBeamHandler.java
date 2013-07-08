package org.schema.game.common.controller.elements.harvest;

import class_48;
import class_721;
import class_852;
import class_854;
import class_941;
import java.util.Collection;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.BeamHandler;
import org.schema.game.common.data.element.BeamHandler.BeamState;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.world.Segment;

public class SalvageBeamHandler
  extends BeamHandler
{
  private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
  
  public SalvageBeamHandler(class_852 paramclass_852, class_721 paramclass_721)
  {
    super((SegmentController)paramclass_852, paramclass_721);
  }
  
  public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, class_48 paramclass_48)
  {
    if ((!paramSegmentController.equals(getSegmentController())) && ((paramSegmentController instanceof class_854)))
    {
      getSegmentController();
      if (((class_854)paramSegmentController).a4(paramArrayOfString, paramclass_48)) {
        return true;
      }
    }
    return false;
  }
  
  public float getBeamTimeoutInSecs()
  {
    return 0.5F;
  }
  
  public float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState)
  {
    return paramBeamState.getSalvageSpeed();
  }
  
  public void onBeamHit(BeamHandler.BeamState paramBeamState, class_721 paramclass_721, class_48 paramclass_48, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, class_941 paramclass_941, Collection paramCollection)
  {
    paramclass_48 = ((class_852)getSegmentController()).a3(paramBeamState, paramclass_721, paramCubeRayCastResult, paramCollection);
    ((class_854)paramSegment.a15()).handleBeingSalvaged(paramBeamState, paramclass_721, paramVector3f2, paramCubeRayCastResult, paramclass_48);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.harvest.SalvageBeamHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */