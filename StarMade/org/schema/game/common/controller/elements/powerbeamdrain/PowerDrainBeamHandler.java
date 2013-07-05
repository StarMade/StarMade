/*    */ package org.schema.game.common.controller.elements.powerbeamdrain;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import javax.vecmath.Vector3f;
/*    */ import jq;
/*    */ import ld;
/*    */ import le;
/*    */ import org.schema.game.common.controller.SegmentController;
/*    */ import org.schema.game.common.controller.elements.PowerAddOn;
/*    */ import org.schema.game.common.controller.elements.PowerManagerInterface;
/*    */ import org.schema.game.common.data.element.BeamHandler;
/*    */ import org.schema.game.common.data.element.BeamHandler.BeamState;
/*    */ import org.schema.game.common.data.physics.CubeRayCastResult;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ import q;
/*    */ import xq;
/*    */ 
/*    */ public class PowerDrainBeamHandler extends BeamHandler
/*    */ {
/*    */   private static final float BEAM_TIMEOUT_IN_SECS = 0.5F;
/*    */ 
/*    */   public PowerDrainBeamHandler(SegmentController paramSegmentController, jq paramjq)
/*    */   {
/* 30 */     super(paramSegmentController, paramjq);
/*    */   }
/*    */ 
/*    */   public boolean canhit(SegmentController paramSegmentController, String[] paramArrayOfString, q paramq)
/*    */   {
/* 36 */     return (!paramSegmentController.equals(getSegmentController())) && ((paramSegmentController instanceof ld)) && ((((ld)paramSegmentController).a() instanceof PowerManagerInterface));
/*    */   }
/*    */ 
/*    */   public float getBeamTimeoutInSecs()
/*    */   {
/* 43 */     return 0.5F;
/*    */   }
/*    */ 
/*    */   public float getBeamToHitInSecs(BeamHandler.BeamState paramBeamState)
/*    */   {
/* 48 */     return paramBeamState.getSalvageSpeed();
/*    */   }
/*    */ 
/*    */   public void onBeamHit(BeamHandler.BeamState paramBeamState, jq paramjq, q paramq, Segment paramSegment, Vector3f paramVector3f1, Vector3f paramVector3f2, CubeRayCastResult paramCubeRayCastResult, xq paramxq, Collection paramCollection)
/*    */   {
/* 57 */     paramq = ((PowerManagerInterface)((ld)getSegmentController()).a()).getPowerAddOn();
/*    */ 
/* 59 */     paramSegment = ((PowerManagerInterface)((ld)paramSegment.a()).a()).getPowerAddOn();
/*    */ 
/* 61 */     paramVector3f1 = new le(paramCubeRayCastResult.getSegment(), paramCubeRayCastResult.cubePos);
/*    */ 
/* 64 */     if (((
/* 64 */       paramBeamState = paramjq.getHandler().beamHit(paramBeamState, paramVector3f1)) > 0) && 
/* 64 */       (
/* 65 */       (paramVector3f1.a() == 331) || (paramVector3f1.a() == 2))) {
/* 66 */       double d = Math.min(paramSegment.getPower(), paramBeamState * 10000);
/*    */ 
/* 68 */       paramSegment.consumePowerInstantly(d);
/* 69 */       paramq.incPower(d);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.controller.elements.powerbeamdrain.PowerDrainBeamHandler
 * JD-Core Version:    0.6.2
 */