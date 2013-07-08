package com.bulletphysics.linearmath;

public class DefaultMotionState
  extends MotionState
{
  public final Transform graphicsWorldTrans = new Transform();
  public final Transform centerOfMassOffset = new Transform();
  public final Transform startWorldTrans = new Transform();
  
  public DefaultMotionState()
  {
    this.graphicsWorldTrans.setIdentity();
    this.centerOfMassOffset.setIdentity();
    this.startWorldTrans.setIdentity();
  }
  
  public DefaultMotionState(Transform startTrans)
  {
    this.graphicsWorldTrans.set(startTrans);
    this.centerOfMassOffset.setIdentity();
    this.startWorldTrans.set(startTrans);
  }
  
  public DefaultMotionState(Transform startTrans, Transform centerOfMassOffset)
  {
    this.graphicsWorldTrans.set(startTrans);
    this.centerOfMassOffset.set(centerOfMassOffset);
    this.startWorldTrans.set(startTrans);
  }
  
  public Transform getWorldTransform(Transform out)
  {
    out.inverse(this.centerOfMassOffset);
    out.mul(this.graphicsWorldTrans);
    return out;
  }
  
  public void setWorldTransform(Transform centerOfMassWorldTrans)
  {
    this.graphicsWorldTrans.set(centerOfMassWorldTrans);
    this.graphicsWorldTrans.mul(this.centerOfMassOffset);
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.linearmath.DefaultMotionState
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */