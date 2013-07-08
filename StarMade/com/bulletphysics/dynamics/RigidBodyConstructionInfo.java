/*  1:   */package com.bulletphysics.dynamics;
/*  2:   */
/*  3:   */import com.bulletphysics.collision.shapes.CollisionShape;
/*  4:   */import com.bulletphysics.linearmath.MotionState;
/*  5:   */import com.bulletphysics.linearmath.Transform;
/*  6:   */import javax.vecmath.Vector3f;
/*  7:   */
/* 52:   */public class RigidBodyConstructionInfo
/* 53:   */{
/* 54:   */  public float mass;
/* 55:   */  public MotionState motionState;
/* 56:56 */  public final Transform startWorldTransform = new Transform();
/* 57:   */  
/* 58:   */  public CollisionShape collisionShape;
/* 59:59 */  public final Vector3f localInertia = new Vector3f();
/* 60:60 */  public float linearDamping = 0.0F;
/* 61:61 */  public float angularDamping = 0.0F;
/* 62:   */  
/* 64:64 */  public float friction = 0.5F;
/* 65:   */  
/* 66:66 */  public float restitution = 0.0F;
/* 67:   */  
/* 68:68 */  public float linearSleepingThreshold = 0.8F;
/* 69:69 */  public float angularSleepingThreshold = 1.0F;
/* 70:   */  
/* 76:76 */  public boolean additionalDamping = false;
/* 77:77 */  public float additionalDampingFactor = 0.005F;
/* 78:78 */  public float additionalLinearDampingThresholdSqr = 0.01F;
/* 79:79 */  public float additionalAngularDampingThresholdSqr = 0.01F;
/* 80:80 */  public float additionalAngularDampingFactor = 0.01F;
/* 81:   */  
/* 82:   */  public RigidBodyConstructionInfo(float mass, MotionState motionState, CollisionShape collisionShape) {
/* 83:83 */    this(mass, motionState, collisionShape, new Vector3f(0.0F, 0.0F, 0.0F));
/* 84:   */  }
/* 85:   */  
/* 86:   */  public RigidBodyConstructionInfo(float mass, MotionState motionState, CollisionShape collisionShape, Vector3f localInertia) {
/* 87:87 */    this.mass = mass;
/* 88:88 */    this.motionState = motionState;
/* 89:89 */    this.collisionShape = collisionShape;
/* 90:90 */    this.localInertia.set(localInertia);
/* 91:   */    
/* 92:92 */    this.startWorldTransform.setIdentity();
/* 93:   */  }
/* 94:   */}


/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.RigidBodyConstructionInfo
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */