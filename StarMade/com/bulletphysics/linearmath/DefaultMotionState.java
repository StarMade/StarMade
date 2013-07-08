/*  1:   */package com.bulletphysics.linearmath;
/*  2:   */
/* 32:   */public class DefaultMotionState
/* 33:   */  extends MotionState
/* 34:   */{
/* 35:35 */  public final Transform graphicsWorldTrans = new Transform();
/* 36:   */  
/* 38:38 */  public final Transform centerOfMassOffset = new Transform();
/* 39:   */  
/* 41:41 */  public final Transform startWorldTrans = new Transform();
/* 42:   */  
/* 45:   */  public DefaultMotionState()
/* 46:   */  {
/* 47:47 */    this.graphicsWorldTrans.setIdentity();
/* 48:48 */    this.centerOfMassOffset.setIdentity();
/* 49:49 */    this.startWorldTrans.setIdentity();
/* 50:   */  }
/* 51:   */  
/* 55:   */  public DefaultMotionState(Transform startTrans)
/* 56:   */  {
/* 57:57 */    this.graphicsWorldTrans.set(startTrans);
/* 58:58 */    this.centerOfMassOffset.setIdentity();
/* 59:59 */    this.startWorldTrans.set(startTrans);
/* 60:   */  }
/* 61:   */  
/* 65:   */  public DefaultMotionState(Transform startTrans, Transform centerOfMassOffset)
/* 66:   */  {
/* 67:67 */    this.graphicsWorldTrans.set(startTrans);
/* 68:68 */    this.centerOfMassOffset.set(centerOfMassOffset);
/* 69:69 */    this.startWorldTrans.set(startTrans);
/* 70:   */  }
/* 71:   */  
/* 72:   */  public Transform getWorldTransform(Transform out) {
/* 73:73 */    out.inverse(this.centerOfMassOffset);
/* 74:74 */    out.mul(this.graphicsWorldTrans);
/* 75:75 */    return out;
/* 76:   */  }
/* 77:   */  
/* 78:   */  public void setWorldTransform(Transform centerOfMassWorldTrans) {
/* 79:79 */    this.graphicsWorldTrans.set(centerOfMassWorldTrans);
/* 80:80 */    this.graphicsWorldTrans.mul(this.centerOfMassOffset);
/* 81:   */  }
/* 82:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.linearmath.DefaultMotionState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */