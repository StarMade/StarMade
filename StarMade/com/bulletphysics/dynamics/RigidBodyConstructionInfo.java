/*    */ package com.bulletphysics.dynamics;
/*    */ 
/*    */ import com.bulletphysics.collision.shapes.CollisionShape;
/*    */ import com.bulletphysics.linearmath.MotionState;
/*    */ import com.bulletphysics.linearmath.Transform;
/*    */ import javax.vecmath.Vector3f;
/*    */ 
/*    */ public class RigidBodyConstructionInfo
/*    */ {
/*    */   public float mass;
/*    */   public MotionState motionState;
/* 56 */   public final Transform startWorldTransform = new Transform();
/*    */   public CollisionShape collisionShape;
/* 59 */   public final Vector3f localInertia = new Vector3f();
/* 60 */   public float linearDamping = 0.0F;
/* 61 */   public float angularDamping = 0.0F;
/*    */ 
/* 64 */   public float friction = 0.5F;
/*    */ 
/* 66 */   public float restitution = 0.0F;
/*    */ 
/* 68 */   public float linearSleepingThreshold = 0.8F;
/* 69 */   public float angularSleepingThreshold = 1.0F;
/*    */ 
/* 76 */   public boolean additionalDamping = false;
/* 77 */   public float additionalDampingFactor = 0.005F;
/* 78 */   public float additionalLinearDampingThresholdSqr = 0.01F;
/* 79 */   public float additionalAngularDampingThresholdSqr = 0.01F;
/* 80 */   public float additionalAngularDampingFactor = 0.01F;
/*    */ 
/*    */   public RigidBodyConstructionInfo(float mass, MotionState motionState, CollisionShape collisionShape) {
/* 83 */     this(mass, motionState, collisionShape, new Vector3f(0.0F, 0.0F, 0.0F));
/*    */   }
/*    */ 
/*    */   public RigidBodyConstructionInfo(float mass, MotionState motionState, CollisionShape collisionShape, Vector3f localInertia) {
/* 87 */     this.mass = mass;
/* 88 */     this.motionState = motionState;
/* 89 */     this.collisionShape = collisionShape;
/* 90 */     this.localInertia.set(localInertia);
/*    */ 
/* 92 */     this.startWorldTransform.setIdentity();
/*    */   }
/*    */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     com.bulletphysics.dynamics.RigidBodyConstructionInfo
 * JD-Core Version:    0.6.2
 */