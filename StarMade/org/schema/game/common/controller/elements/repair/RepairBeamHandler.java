/*  1:   */package org.schema.game.common.controller.elements.repair;
/*  2:   */
/*  3:   */import jH;
/*  4:   */import jI;
/*  5:   */import java.util.Collection;
/*  6:   */import javax.vecmath.Vector3f;
/*  7:   */import jq;
/*  8:   */import org.schema.game.common.controller.EditableSendableSegmentController;
/*  9:   */import org.schema.game.common.controller.SegmentController;
/* 10:   */import org.schema.game.common.data.element.BeamHandler;
/* 11:   */import org.schema.game.common.data.element.BeamHandler.BeamState;
/* 12:   */import org.schema.game.common.data.physics.CubeRayCastResult;
/* 13:   */import org.schema.game.common.data.world.Segment;
/* 14:   */import q;
/* 15:   */import xq;
/* 16:   */
/* 23:   */public class RepairBeamHandler
/* 24:   */  extends BeamHandler
/* 25:   */{
/* 26:   */  private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
/* 27:   */  
/* 28:   */  public RepairBeamHandler(SegmentController paramSegmentController, jq paramjq)
/* 29:   */  {
/* 30:30 */    super(paramSegmentController, paramjq);
/* 31:   */  }
/* 32:   */  
/* 34:   */  public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, q paramq)
/* 35:   */  {
/* 36:36 */    return (!paramSegmentController.equals(getSegmentController())) && ((paramSegmentController instanceof jH)) && (((jH)paramSegmentController).isRepariableFor((jI)getSegmentController(), paramArrayOfString, paramq));
/* 37:   */  }
/* 38:   */  
/* 39:   */  public float getBeamTimeoutInSecs()
/* 40:   */  {
/* 41:41 */    return 0.5F;
/* 42:   */  }
/* 43:   */  
/* 44:   */  public float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState)
/* 45:   */  {
/* 46:46 */    return paramBeamState.getSalvageSpeed();
/* 47:   */  }
/* 48:   */  
/* 51:   */  public void onBeamHit(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, xq paramxq, Collection paramCollection)
/* 52:   */  {
/* 53:53 */    if ((getSegmentController() instanceof EditableSendableSegmentController)) {
/* 54:54 */      ((EditableSendableSegmentController)getSegmentController()).handleRepair(paramBeamState, paramjq, paramq, paramVector3f1, paramCubeRayCastResult, paramxq);
/* 55:   */    }
/* 56:   */  }
/* 57:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairBeamHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */