/*    */ package org.schema.game.common.controller.elements.repair;
/*    */ 
/*    */ import jH;
/*    */ import jI;
/*    */ import java.util.Collection;
/*    */ import javax.vecmath.Vector3f;
/*    */ import jq;
/*    */ import org.schema.game.common.controller.EditableSendableSegmentController;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.data.element.BeamHandler;
/*    */ import org.schema.game.common.data.element.BeamHandler.BeamState;
/*    */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ import q;
/*    */ import xq;
/*    */ 
/*    */ public class RepairBeamHandler extends BeamHandler
/*    */ {
/*    */   private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
/*    */ 
/*    */   public RepairBeamHandler(SegmentController paramSegmentController, jq paramjq)
/*    */   {
/* 30 */     super(paramSegmentController, paramjq);
/*    */   }
/*    */ 
/*    */   public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, q paramq)
/*    */   {
/* 36 */     return (!paramSegmentController.equals(getSegmentController())) && ((paramSegmentController instanceof jH)) && (((jH)paramSegmentController).isRepariableFor((jI)getSegmentController(), paramArrayOfString, paramq));
/*    */   }
/*    */ 
/*    */   public float getBeamTimeoutInSecs()
/*    */   {
/* 41 */     return 0.5F;
/*    */   }
/*    */ 
/*    */   public float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState)
/*    */   {
/* 46 */     return paramBeamState.getSalvageSpeed();
/*    */   }
/*    */ 
/*    */   public void onBeamHit(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, xq paramxq, Collection paramCollection)
/*    */   {
/* 53 */     if ((getSegmentController() instanceof EditableSendableSegmentController))
/* 54 */       ((EditableSendableSegmentController)getSegmentController()).handleRepair(paramBeamState, paramjq, paramq, paramVector3f1, paramCubeRayCastResult, paramxq);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.repair.RepairBeamHandler
 * JD-Core Version:    0.6.2
 */