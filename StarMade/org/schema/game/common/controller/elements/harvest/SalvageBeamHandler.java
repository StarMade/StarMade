/*    */ package org.schema.game.common.controller.elements.harvest;
/*    */ 
/*    */ import jH;
/*    */ import jI;
/*    */ import java.util.Collection;
/*    */ import javax.vecmath.Vector3f;
/*    */ import jq;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.data.element.BeamHandler;
/*    */ import org.schema.game.common.data.element.BeamHandler.BeamState;
/*    */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ import q;
/*    */ import xq;
/*    */ 
/*    */ public class SalvageBeamHandler extends BeamHandler
/*    */ {
/*    */   private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
/*    */ 
/*    */   public SalvageBeamHandler(jI paramjI, jq paramjq)
/*    */   {
/* 25 */     super((SegmentController)paramjI, paramjq);
/*    */   }
/*    */ 
/*    */   public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, q paramq)
/*    */   {
/* 31 */     if ((!paramSegmentController.equals(getSegmentController())) && ((paramSegmentController instanceof jH))) { getSegmentController(); if (((jH)paramSegmentController).a(paramArrayOfString, paramq)) return true;  } return false;
/*    */   }
/*    */ 
/*    */   public float getBeamTimeoutInSecs()
/*    */   {
/* 36 */     return 0.5F;
/*    */   }
/*    */ 
/*    */   public float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState)
/*    */   {
/* 41 */     return paramBeamState.getSalvageSpeed();
/*    */   }
/*    */ 
/*    */   public void onBeamHit(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, xq paramxq, Collection paramCollection)
/*    */   {
/* 49 */     paramq = ((jI)getSegmentController()).a(paramBeamState, paramjq, paramCubeRayCastResult, paramCollection);
/* 50 */     ((jH)paramSegment.a()).handleBeingSalvaged(paramBeamState, paramjq, paramVector3f2, paramCubeRayCastResult, paramq);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.harvest.SalvageBeamHandler
 * JD-Core Version:    0.6.2
 */