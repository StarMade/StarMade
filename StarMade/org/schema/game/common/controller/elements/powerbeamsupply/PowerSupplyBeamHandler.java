package org.schema.game.common.controller.elements.powerbeamsupply;

import class_48;
import class_721;
import class_796;
import class_798;
import class_941;
import java.util.Collection;
import javax.vecmath.Vector3f;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.controller.elements.PowerAddOn;
import org.schema.game.common.controller.elements.PowerManagerInterface;
import org.schema.game.common.data.element.BeamHandler;
import org.schema.game.common.data.element.BeamHandler.BeamState;
import org.schema.game.common.data.physics.CubeRayCastResult;
import org.schema.game.common.data.world.Segment;

public class PowerSupplyBeamHandler
  extends BeamHandler
{
  private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
  
  public PowerSupplyBeamHandler(SegmentController paramSegmentController, class_721 paramclass_721)
  {
    super(paramSegmentController, paramclass_721);
  }
  
  public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, class_48 paramclass_48)
  {
    return (!paramSegmentController.equals(getSegmentController())) && ((paramSegmentController instanceof class_798)) && ((((class_798)paramSegmentController).a() instanceof PowerManagerInterface));
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
    paramclass_48 = ((PowerManagerInterface)((class_798)getSegmentController()).a()).getPowerAddOn();
    paramSegment = ((PowerManagerInterface)((class_798)paramSegment.a15()).a()).getPowerAddOn();
    paramVector3f1 = new class_796(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos);
    if ((paramBeamState = paramclass_721.getHandler().beamHit(paramBeamState, paramVector3f1)) > 0)
    {
      double d = Math.min(paramclass_48.getPower(), paramBeamState * 10000);
      paramclass_48.consumePowerInstantly(d);
      paramSegment.incPower(d);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.controller.elements.powerbeamsupply.PowerSupplyBeamHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */