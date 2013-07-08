package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics..Stack;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.TransformUtil;
import javax.vecmath.Vector3f;

public class SolverBody
{
  public final Vector3f angularVelocity = new Vector3f();
  public float angularFactor;
  public float invMass;
  public float friction;
  public RigidBody originalBody;
  public final Vector3f linearVelocity = new Vector3f();
  public final Vector3f centerOfMassPosition = new Vector3f();
  public final Vector3f pushVelocity = new Vector3f();
  public final Vector3f turnVelocity = new Vector3f();
  
  public void getVelocityInLocalPoint(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.cross(this.angularVelocity, rel_pos);
      velocity.add(this.linearVelocity, tmp);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void internalApplyImpulse(Vector3f linearComponent, Vector3f angularComponent, float impulseMagnitude)
  {
    if (this.invMass != 0.0F)
    {
      this.linearVelocity.scaleAdd(impulseMagnitude, linearComponent, this.linearVelocity);
      this.angularVelocity.scaleAdd(impulseMagnitude * this.angularFactor, angularComponent, this.angularVelocity);
    }
  }
  
  public void internalApplyPushImpulse(Vector3f linearComponent, Vector3f angularComponent, float impulseMagnitude)
  {
    if (this.invMass != 0.0F)
    {
      this.pushVelocity.scaleAdd(impulseMagnitude, linearComponent, this.pushVelocity);
      this.turnVelocity.scaleAdd(impulseMagnitude * this.angularFactor, angularComponent, this.turnVelocity);
    }
  }
  
  public void writebackVelocity()
  {
    if (this.invMass != 0.0F)
    {
      this.originalBody.setLinearVelocity(this.linearVelocity);
      this.originalBody.setAngularVelocity(this.angularVelocity);
    }
  }
  
  public void writebackVelocity(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$com$bulletphysics$linearmath$Transform();
      if (this.invMass != 0.0F)
      {
        this.originalBody.setLinearVelocity(this.linearVelocity);
        this.originalBody.setAngularVelocity(this.angularVelocity);
        Transform newTransform = localStack.get$com$bulletphysics$linearmath$Transform();
        Transform curTrans = this.originalBody.getWorldTransform(localStack.get$com$bulletphysics$linearmath$Transform());
        TransformUtil.integrateTransform(curTrans, this.pushVelocity, this.turnVelocity, timeStep, newTransform);
        this.originalBody.setWorldTransform(newTransform);
      }
      return;
    }
    finally
    {
      localStack.pop$com$bulletphysics$linearmath$Transform();
    }
  }
  
  public void readVelocity()
  {
    if (this.invMass != 0.0F)
    {
      this.originalBody.getLinearVelocity(this.linearVelocity);
      this.originalBody.getAngularVelocity(this.angularVelocity);
    }
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.SolverBody
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */