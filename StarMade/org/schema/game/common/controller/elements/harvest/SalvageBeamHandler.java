/*  1:   */package org.schema.game.common.controller.elements.harvest;
/*  2:   */
/*  3:   */import jH;
/*  4:   */import jI;
/*  5:   */import java.util.Collection;
/*  6:   */import javax.vecmath.Vector3f;
/*  7:   */import jq;
/*  8:   */import org.schema.game.common.controller.SegmentController;
/*  9:   */import org.schema.game.common.data.element.BeamHandler;
/* 10:   */import org.schema.game.common.data.element.BeamHandler.BeamState;
/* 11:   */import org.schema.game.common.data.physics.CubeRayCastResult;
/* 12:   */import org.schema.game.common.data.world.Segment;
/* 13:   */import q;
/* 14:   */import xq;
/* 15:   */
/* 18:   */public class SalvageBeamHandler
/* 19:   */  extends BeamHandler
/* 20:   */{
/* 21:   */  private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
/* 22:   */  
/* 23:   */  public SalvageBeamHandler(jI paramjI, jq paramjq)
/* 24:   */  {
/* 25:25 */    super((SegmentController)paramjI, paramjq);
/* 26:   */  }
/* 27:   */  
/* 29:   */  public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, q paramq)
/* 30:   */  {
/* 31:31 */    if ((!paramSegmentController.equals(getSegmentController())) && ((paramSegmentController instanceof jH))) { getSegmentController(); if (((jH)paramSegmentController).a(paramArrayOfString, paramq)) return true; } return false;
/* 32:   */  }
/* 33:   */  
/* 34:   */  public float getBeamTimeoutInSecs()
/* 35:   */  {
/* 36:36 */    return 0.5F;
/* 37:   */  }
/* 38:   */  
/* 39:   */  public float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState)
/* 40:   */  {
/* 41:41 */    return paramBeamState.getSalvageSpeed();
/* 42:   */  }
/* 43:   */  
/* 47:   */  public void onBeamHit(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, xq paramxq, Collection paramCollection)
/* 48:   */  {
/* 49:49 */    paramq = ((jI)getSegmentController()).a(paramBeamState, paramjq, paramCubeRayCastResult, paramCollection);
/* 50:50 */    ((jH)paramSegment.a()).handleBeingSalvaged(paramBeamState, paramjq, paramVector3f2, paramCubeRayCastResult, paramq);
/* 51:   */  }
/* 52:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.harvest.SalvageBeamHandler
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */