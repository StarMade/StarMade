package com.bulletphysics.dynamics;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import javax.vecmath.Vector3f;

public class RigidBodyConstructionInfo
{
  public float mass;
  public MotionState motionState;
  public final Transform startWorldTransform = new Transform();
  public CollisionShape collisionShape;
  public final Vector3f localInertia = new Vector3f();
  public float linearDamping = 0.0F;
  public float angularDamping = 0.0F;
  public float friction = 0.5F;
  public float restitution = 0.0F;
  public float linearSleepingThreshold = 0.8F;
  public float angularSleepingThreshold = 1.0F;
  public boolean additionalDamping = false;
  public float additionalDampingFactor = 0.005F;
  public float additionalLinearDampingThresholdSqr = 0.01F;
  public float additionalAngularDampingThresholdSqr = 0.01F;
  public float additionalAngularDampingFactor = 0.01F;
  
  public RigidBodyConstructionInfo(float mass, MotionState motionState, CollisionShape collisionShape)
  {
    this(mass, motionState, collisionShape, new Vector3f(0.0F, 0.0F, 0.0F));
  }
  
  public RigidBodyConstructionInfo(float mass, MotionState motionState, CollisionShape collisionShape, Vector3f localInertia)
  {
    this.mass = mass;
    this.motionState = motionState;
    this.collisionShape = collisionShape;
    this.localInertia.set(localInertia);
    this.startWorldTransform.setIdentity();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.RigidBodyConstructionInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */