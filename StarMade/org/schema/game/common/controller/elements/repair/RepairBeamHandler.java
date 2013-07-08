package org.schema.game.common.controller.elements.repair;

import class_48;
import class_721;
import class_852;
import class_854;
import class_941;
import java.util.Collection;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.EditableSendableSegmentController;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.element.BeamHandler;
import org.schema.game.common.data.element.BeamHandler.BeamState;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.world.Segment;

public class RepairBeamHandler
  extends BeamHandler
{
  private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
  
  public RepairBeamHandler(SegmentController paramSegmentController, class_721 paramclass_721)
  {
    super(paramSegmentController, paramclass_721);
  }
  
  public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, class_48 paramclass_48)
  {
    return (!paramSegmentController.equals(getSegmentController())) && ((paramSegmentController instanceof class_854)) && (((class_854)paramSegmentController).isRepariableFor((class_852)getSegmentController(), paramArrayOfString, paramclass_48));
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
    if ((getSegmentController() instanceof EditableSendableSegmentController)) {
      ((EditableSendableSegmentController)getSegmentController()).handleRepair(paramBeamState, paramclass_721, paramclass_48, paramVector3f1, paramCubeRayCastResult, paramclass_941);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairBeamHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */