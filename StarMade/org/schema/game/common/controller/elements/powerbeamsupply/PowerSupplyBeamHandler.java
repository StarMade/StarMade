/*  1:   */package org.schema.game.common.controller.elements.powerbeamsupply;
/*  2:   */
/*  3:   */import java.util.Collection;
/*  4:   */import javax.vecmath.Vector3f;
/*  5:   */import jq;
/*  6:   */import ld;
/*  7:   */import le;
/*  8:   */import org.schema.game.common.controller.SegmentController;
/*  9:   */import org.schema.game.common.controller.elements.PowerAddOn;
/* 10:   */import org.schema.game.common.controller.elements.PowerManagerInterface;
/* 11:   */import org.schema.game.common.data.element.BeamHandler;
/* 12:   */import org.schema.game.common.data.element.BeamHandler.BeamState;
/* 13:   */import org.schema.game.common.data.physics.CubeRayCastResult;
/* 14:   */import org.schema.game.common.data.world.Segment;
/* 15:   */import q;
/* 16:   */import xq;
/* 17:   */
/* 23:   */public class PowerSupplyBeamHandler
/* 24:   */  extends BeamHandler
/* 25:   */{
/* 26:   */  private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
/* 27:   */  
/* 28:   */  public PowerSupplyBeamHandler(SegmentController paramSegmentController, jq paramjq)
/* 29:   */  {
/* 30:30 */    super(paramSegmentController, paramjq);
/* 31:   */  }
/* 32:   */  
/* 34:   */  public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, q paramq)
/* 35:   */  {
/* 36:36 */    return (!paramSegmentController.equals(getSegmentController())) && ((paramSegmentController instanceof ld)) && ((((ld)paramSegmentController).a() instanceof PowerManagerInterface));
/* 37:   */  }
/* 38:   */  
/* 41:   */  public float getBeamTimeoutInSecs()
/* 42:   */  {
/* 43:43 */    return 0.5F;
/* 44:   */  }
/* 45:   */  
/* 46:   */  public float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState)
/* 47:   */  {
/* 48:48 */    return paramBeamState.getSalvageSpeed();
/* 49:   */  }
/* 50:   */  
/* 55:   */  public void onBeamHit(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, xq paramxq, Collection paramCollection)
/* 56:   */  {
/* 57:57 */    paramq = ((PowerManagerInterface)((ld)getSegmentController()).a()).getPowerAddOn();
/* 58:   */    
/* 59:59 */    paramSegment = ((PowerManagerInterface)((ld)paramSegment.a()).a()).getPowerAddOn();
/* 60:   */    
/* 61:61 */    paramVector3f1 = new le(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos);
/* 62:   */    
/* 64:64 */    if ((paramBeamState = paramjq.getHandler().beamHit(paramBeamState, paramVector3f1)) > 0)
/* 65:   */    {
/* 66:66 */      double d = Math.min(paramq.getPower(), paramBeamState * 10000);
/* 67:   */      
/* 68:68 */      paramq.consumePowerInstantly(d);
/* 69:69 */      paramSegment.incPower(d);
/* 70:   */    }
/* 71:   */  }
/* 72:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.powerbeamsupply.PowerSupplyBeamHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */