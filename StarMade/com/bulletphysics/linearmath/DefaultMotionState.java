/*    */ package com.bulletphysics.linearmath;
/*    */ 
/*    */ public class DefaultMotionState extends MotionState
/*    */ {
/* 35 */   public final Transform graphicsWorldTrans = new Transform();
/*    */ 
/* 38 */   public final Transform centerOfMassOffset = new Transform();
/*    */ 
/* 41 */   public final Transform startWorldTrans = new Transform();
/*    */ 
/*    */   public DefaultMotionState()
/*    */   {
/* 47 */     this.graphicsWorldTrans.setIdentity();
/* 48 */     this.centerOfMassOffset.setIdentity();
/* 49 */     this.startWorldTrans.setIdentity();
/*    */   }
/*    */ 
/*    */   public DefaultMotionState(Transform startTrans)
/*    */   {
/* 57 */     this.graphicsWorldTrans.set(startTrans);
/* 58 */     this.centerOfMassOffset.setIdentity();
/* 59 */     this.startWorldTrans.set(startTrans);
/*    */   }
/*    */ 
/*    */   public DefaultMotionState(Transform startTrans, Transform centerOfMassOffset)
/*    */   {
/* 67 */     this.graphicsWorldTrans.set(startTrans);
/* 68 */     this.centerOfMassOffset.set(centerOfMassOffset);
/* 69 */     this.startWorldTrans.set(startTrans);
/*    */   }
/*    */ 
/*    */   public Transform getWorldTransform(Transform out) {
/* 73 */     out.inverse(this.centerOfMassOffset);
/* 74 */     out.mul(this.graphicsWorldTrans);
/* 75 */     return out;
/*    */   }
/*    */ 
/*    */   public void setWorldTransform(Transform centerOfMassWorldTrans) {
/* 79 */     this.graphicsWorldTrans.set(centerOfMassWorldTrans);
/* 80 */     this.graphicsWorldTrans.mul(this.centerOfMassOffset);
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.DefaultMotionState
 * JD-Core Version:    0.6.2
 */