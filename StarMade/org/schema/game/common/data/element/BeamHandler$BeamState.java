/*    */ package org.schema.game.common.data.element;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
/*    */ import javax.vecmath.Vector4f;
/*    */ import lE;
/*    */ import le;
/*    */ import org.schema.game.common.data.world.Segment;
/*    */ import q;
/*    */ import xq;
/*    */ 
/*    */ public class BeamHandler$BeamState
/*    */ {
/*    */   public final q elementPos;
/*    */   public final Vector3f from;
/*    */   public final Vector3f to;
/*    */   public Vector3f hitPoint;
/* 41 */   public float timeRunning = 0.0F;
/*    */   public lE playerState;
/* 43 */   public long lastCheck = -1L;
/*    */   public float timeSpent;
/*    */   public le segmentHit;
/*    */   public float hitOneSegment;
/*    */   public Segment cachedLastSegment;
/*    */   private float power;
/* 49 */   public Vector4f color = new Vector4f();
/*    */   public float camDistStart;
/*    */   public float camDistEnd;
/*    */ 
/*    */   public BeamHandler$BeamState(BeamHandler paramBeamHandler, q paramq, Vector3f paramVector3f1, Vector3f paramVector3f2, lE paramlE, float paramFloat)
/*    */   {
/* 56 */     this.elementPos = paramq;
/* 57 */     this.from = paramVector3f1;
/* 58 */     this.to = paramVector3f2;
/* 59 */     this.playerState = paramlE;
/* 60 */     setPower(paramFloat);
/*    */   }
/*    */ 
/*    */   public boolean checkNecessary(xq paramxq, BeamState paramBeamState)
/*    */   {
/* 66 */     if ((this.lastCheck < 0L) || ((float)(System.currentTimeMillis() - this.lastCheck) > Math.min(300.0F, this.this$0.getBeamToHitInSecs(paramBeamState) / 3.0F * 1000.0F))) {
/* 67 */       this.lastCheck = System.currentTimeMillis();
/*    */ 
/* 69 */       return true;
/*    */     }
/* 71 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean equals(Object paramObject) {
/* 75 */     return ((BeamState)paramObject).elementPos.equals(this.elementPos);
/*    */   }
/*    */ 
/*    */   public float getSalvageSpeed()
/*    */   {
/* 82 */     return this.power;
/*    */   }
/*    */ 
/*    */   public int hashCode()
/*    */   {
/* 87 */     return this.elementPos.hashCode();
/*    */   }
/*    */ 
/*    */   public void setPower(float paramFloat)
/*    */   {
/* 94 */     this.power = paramFloat;
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.element.BeamHandler.BeamState
 * JD-Core Version:    0.6.2
 */