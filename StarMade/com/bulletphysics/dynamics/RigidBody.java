package com.bulletphysics.dynamics;

import com.bulletphysics..Stack;
import com.bulletphysics.BulletGlobals;
import com.bulletphysics.collision.broadphase.BroadphaseProxy;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.dispatch.CollisionObjectType;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.linearmath.MatrixUtil;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.TransformUtil;
import com.bulletphysics.util.ObjectArrayList;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

public class RigidBody
  extends CollisionObject
{
  private static final float MAX_ANGVEL = 1.570796F;
  private final Matrix3f invInertiaTensorWorld = new Matrix3f();
  private final Vector3f linearVelocity = new Vector3f();
  private final Vector3f angularVelocity = new Vector3f();
  private float inverseMass;
  private float angularFactor;
  private final Vector3f gravity = new Vector3f();
  private final Vector3f invInertiaLocal = new Vector3f();
  private final Vector3f totalForce = new Vector3f();
  private final Vector3f totalTorque = new Vector3f();
  private float linearDamping;
  private float angularDamping;
  private boolean additionalDamping;
  private float additionalDampingFactor;
  private float additionalLinearDampingThresholdSqr;
  private float additionalAngularDampingThresholdSqr;
  private float additionalAngularDampingFactor;
  private float linearSleepingThreshold;
  private float angularSleepingThreshold;
  private MotionState optionalMotionState;
  private final ObjectArrayList<TypedConstraint> constraintRefs = new ObjectArrayList();
  public int contactSolverType;
  public int frictionSolverType;
  private static int uniqueId = 0;
  public int debugBodyId;
  
  public RigidBody(RigidBodyConstructionInfo constructionInfo)
  {
    setupRigidBody(constructionInfo);
  }
  
  public RigidBody(float mass, MotionState motionState, CollisionShape collisionShape)
  {
    this(mass, motionState, collisionShape, new Vector3f(0.0F, 0.0F, 0.0F));
  }
  
  public RigidBody(float mass, MotionState motionState, CollisionShape collisionShape, Vector3f localInertia)
  {
    RigidBodyConstructionInfo cinfo = new RigidBodyConstructionInfo(mass, motionState, collisionShape, localInertia);
    setupRigidBody(cinfo);
  }
  
  private void setupRigidBody(RigidBodyConstructionInfo constructionInfo)
  {
    this.internalType = CollisionObjectType.RIGID_BODY;
    this.linearVelocity.set(0.0F, 0.0F, 0.0F);
    this.angularVelocity.set(0.0F, 0.0F, 0.0F);
    this.angularFactor = 1.0F;
    this.gravity.set(0.0F, 0.0F, 0.0F);
    this.totalForce.set(0.0F, 0.0F, 0.0F);
    this.totalTorque.set(0.0F, 0.0F, 0.0F);
    this.linearDamping = 0.0F;
    this.angularDamping = 0.5F;
    this.linearSleepingThreshold = constructionInfo.linearSleepingThreshold;
    this.angularSleepingThreshold = constructionInfo.angularSleepingThreshold;
    this.optionalMotionState = constructionInfo.motionState;
    this.contactSolverType = 0;
    this.frictionSolverType = 0;
    this.additionalDamping = constructionInfo.additionalDamping;
    this.additionalDampingFactor = constructionInfo.additionalDampingFactor;
    this.additionalLinearDampingThresholdSqr = constructionInfo.additionalLinearDampingThresholdSqr;
    this.additionalAngularDampingThresholdSqr = constructionInfo.additionalAngularDampingThresholdSqr;
    this.additionalAngularDampingFactor = constructionInfo.additionalAngularDampingFactor;
    if (this.optionalMotionState != null) {
      this.optionalMotionState.getWorldTransform(this.worldTransform);
    } else {
      this.worldTransform.set(constructionInfo.startWorldTransform);
    }
    this.interpolationWorldTransform.set(this.worldTransform);
    this.interpolationLinearVelocity.set(0.0F, 0.0F, 0.0F);
    this.interpolationAngularVelocity.set(0.0F, 0.0F, 0.0F);
    this.friction = constructionInfo.friction;
    this.restitution = constructionInfo.restitution;
    setCollisionShape(constructionInfo.collisionShape);
    this.debugBodyId = (uniqueId++);
    setMassProps(constructionInfo.mass, constructionInfo.localInertia);
    setDamping(constructionInfo.linearDamping, constructionInfo.angularDamping);
    updateInertiaTensor();
  }
  
  public void destroy()
  {
    assert (this.constraintRefs.size() == 0);
  }
  
  public void proceedToTransform(Transform newTrans)
  {
    setCenterOfMassTransform(newTrans);
  }
  
  public static RigidBody upcast(CollisionObject colObj)
  {
    if (colObj.getInternalType() == CollisionObjectType.RIGID_BODY) {
      return (RigidBody)colObj;
    }
    return null;
  }
  
  public void predictIntegratedTransform(float timeStep, Transform predictedTransform)
  {
    TransformUtil.integrateTransform(this.worldTransform, this.linearVelocity, this.angularVelocity, timeStep, predictedTransform);
  }
  
  public void saveKinematicState(float timeStep)
  {
    if (timeStep != 0.0F)
    {
      if (getMotionState() != null) {
        getMotionState().getWorldTransform(this.worldTransform);
      }
      TransformUtil.calculateVelocity(this.interpolationWorldTransform, this.worldTransform, timeStep, this.linearVelocity, this.angularVelocity);
      this.interpolationLinearVelocity.set(this.linearVelocity);
      this.interpolationAngularVelocity.set(this.angularVelocity);
      this.interpolationWorldTransform.set(this.worldTransform);
    }
  }
  
  public void applyGravity()
  {
    if (isStaticOrKinematicObject()) {
      return;
    }
    applyCentralForce(this.gravity);
  }
  
  public void setGravity(Vector3f acceleration)
  {
    if (this.inverseMass != 0.0F) {
      this.gravity.scale(1.0F / this.inverseMass, acceleration);
    }
  }
  
  public Vector3f getGravity(Vector3f out)
  {
    out.set(this.gravity);
    return out;
  }
  
  public void setDamping(float lin_damping, float ang_damping)
  {
    this.linearDamping = MiscUtil.GEN_clamped(lin_damping, 0.0F, 1.0F);
    this.angularDamping = MiscUtil.GEN_clamped(ang_damping, 0.0F, 1.0F);
  }
  
  public float getLinearDamping()
  {
    return this.linearDamping;
  }
  
  public float getAngularDamping()
  {
    return this.angularDamping;
  }
  
  public float getLinearSleepingThreshold()
  {
    return this.linearSleepingThreshold;
  }
  
  public float getAngularSleepingThreshold()
  {
    return this.angularSleepingThreshold;
  }
  
  public void applyDamping(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      this.linearVelocity.scale((float)Math.pow(1.0F - this.linearDamping, timeStep));
      this.angularVelocity.scale((float)Math.pow(1.0F - this.angularDamping, timeStep));
      if (this.additionalDamping)
      {
        if ((this.angularVelocity.lengthSquared() < this.additionalAngularDampingThresholdSqr) && (this.linearVelocity.lengthSquared() < this.additionalLinearDampingThresholdSqr))
        {
          this.angularVelocity.scale(this.additionalDampingFactor);
          this.linearVelocity.scale(this.additionalDampingFactor);
        }
        float speed = this.linearVelocity.length();
        if (speed < this.linearDamping)
        {
          float dampVel = 0.005F;
          if (speed > dampVel)
          {
            Vector3f dir = localStack.get$javax$vecmath$Vector3f(this.linearVelocity);
            dir.normalize();
            dir.scale(dampVel);
            this.linearVelocity.sub(dir);
          }
          else
          {
            this.linearVelocity.set(0.0F, 0.0F, 0.0F);
          }
        }
        float dampVel = this.angularVelocity.length();
        if (dampVel < this.angularDamping)
        {
          float dir = 0.005F;
          if (dampVel > dir)
          {
            Vector3f dir = localStack.get$javax$vecmath$Vector3f(this.angularVelocity);
            dir.normalize();
            dir.scale(dir);
            this.angularVelocity.sub(dir);
          }
          else
          {
            this.angularVelocity.set(0.0F, 0.0F, 0.0F);
          }
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setMassProps(float mass, Vector3f inertia)
  {
    if (mass == 0.0F)
    {
      this.collisionFlags |= 1;
      this.inverseMass = 0.0F;
    }
    else
    {
      this.collisionFlags &= -2;
      this.inverseMass = (1.0F / mass);
    }
    this.invInertiaLocal.set(inertia.field_615 != 0.0F ? 1.0F / inertia.field_615 : 0.0F, inertia.field_616 != 0.0F ? 1.0F / inertia.field_616 : 0.0F, inertia.field_617 != 0.0F ? 1.0F / inertia.field_617 : 0.0F);
  }
  
  public float getInvMass()
  {
    return this.inverseMass;
  }
  
  public Matrix3f getInvInertiaTensorWorld(Matrix3f out)
  {
    out.set(this.invInertiaTensorWorld);
    return out;
  }
  
  public void integrateVelocities(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (isStaticOrKinematicObject()) {
        return;
      }
      this.linearVelocity.scaleAdd(this.inverseMass * step, this.totalForce, this.linearVelocity);
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f(this.totalTorque);
      this.invInertiaTensorWorld.transform(tmp);
      this.angularVelocity.scaleAdd(step, tmp, this.angularVelocity);
      float angvel = this.angularVelocity.length();
      if (angvel * step > 1.570796F) {
        this.angularVelocity.scale(1.570796F / step / angvel);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void setCenterOfMassTransform(Transform xform)
  {
    if (isStaticOrKinematicObject()) {
      this.interpolationWorldTransform.set(this.worldTransform);
    } else {
      this.interpolationWorldTransform.set(xform);
    }
    getLinearVelocity(this.interpolationLinearVelocity);
    getAngularVelocity(this.interpolationAngularVelocity);
    this.worldTransform.set(xform);
    updateInertiaTensor();
  }
  
  public void applyCentralForce(Vector3f force)
  {
    this.totalForce.add(force);
  }
  
  public Vector3f getInvInertiaDiagLocal(Vector3f out)
  {
    out.set(this.invInertiaLocal);
    return out;
  }
  
  public void setInvInertiaDiagLocal(Vector3f diagInvInertia)
  {
    this.invInertiaLocal.set(diagInvInertia);
  }
  
  public void setSleepingThresholds(float linear, float angular)
  {
    this.linearSleepingThreshold = linear;
    this.angularSleepingThreshold = angular;
  }
  
  public void applyTorque(Vector3f torque)
  {
    this.totalTorque.add(torque);
  }
  
  public void applyForce(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      applyCentralForce(force);
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      tmp.cross(rel_pos, force);
      tmp.scale(this.angularFactor);
      applyTorque(tmp);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void applyCentralImpulse(Vector3f impulse)
  {
    this.linearVelocity.scaleAdd(this.inverseMass, impulse, this.linearVelocity);
  }
  
  public void applyTorqueImpulse(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f(torque);
      this.invInertiaTensorWorld.transform(tmp);
      this.angularVelocity.add(tmp);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void applyImpulse(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if (this.inverseMass != 0.0F)
      {
        applyCentralImpulse(impulse);
        if (this.angularFactor != 0.0F)
        {
          Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
          tmp.cross(rel_pos, impulse);
          tmp.scale(this.angularFactor);
          applyTorqueImpulse(tmp);
        }
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public void internalApplyImpulse(Vector3f linearComponent, Vector3f angularComponent, float impulseMagnitude)
  {
    if (this.inverseMass != 0.0F)
    {
      this.linearVelocity.scaleAdd(impulseMagnitude, linearComponent, this.linearVelocity);
      if (this.angularFactor != 0.0F) {
        this.angularVelocity.scaleAdd(impulseMagnitude * this.angularFactor, angularComponent, this.angularVelocity);
      }
    }
  }
  
  public void clearForces()
  {
    this.totalForce.set(0.0F, 0.0F, 0.0F);
    this.totalTorque.set(0.0F, 0.0F, 0.0F);
  }
  
  public void updateInertiaTensor()
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Matrix3f();
      Matrix3f mat1 = localStack.get$javax$vecmath$Matrix3f();
      MatrixUtil.scale(mat1, this.worldTransform.basis, this.invInertiaLocal);
      Matrix3f mat2 = localStack.get$javax$vecmath$Matrix3f(this.worldTransform.basis);
      mat2.transpose();
      this.invInertiaTensorWorld.mul(mat1, mat2);
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public Vector3f getCenterOfMassPosition(Vector3f out)
  {
    out.set(this.worldTransform.origin);
    return out;
  }
  
  public Quat4f getOrientation(Quat4f out)
  {
    MatrixUtil.getRotation(this.worldTransform.basis, out);
    return out;
  }
  
  public Transform getCenterOfMassTransform(Transform out)
  {
    out.set(this.worldTransform);
    return out;
  }
  
  public Vector3f getLinearVelocity(Vector3f out)
  {
    out.set(this.linearVelocity);
    return out;
  }
  
  public Vector3f getAngularVelocity(Vector3f out)
  {
    out.set(this.angularVelocity);
    return out;
  }
  
  public void setLinearVelocity(Vector3f lin_vel)
  {
    assert (this.collisionFlags != 1);
    this.linearVelocity.set(lin_vel);
  }
  
  public void setAngularVelocity(Vector3f ang_vel)
  {
    assert (this.collisionFlags != 1);
    this.angularVelocity.set(ang_vel);
  }
  
  public Vector3f getVelocityInLocalPoint(Vector3f rel_pos, Vector3f out)
  {
    Vector3f vec = out;
    vec.cross(this.angularVelocity, rel_pos);
    vec.add(this.linearVelocity);
    return out;
  }
  
  public void translate(Vector3f local_v)
  {
    this.worldTransform.origin.add(local_v);
  }
  
  public void getAabb(Vector3f aabbMin, Vector3f aabbMax)
  {
    getCollisionShape().getAabb(this.worldTransform, aabbMin, aabbMax);
  }
  
  public float computeImpulseDenominator(Vector3f arg1, Vector3f arg2)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Matrix3f();
      Vector3f local_r0 = localStack.get$javax$vecmath$Vector3f();
      local_r0.sub(pos, getCenterOfMassPosition(localStack.get$javax$vecmath$Vector3f()));
      Vector3f local_c0 = localStack.get$javax$vecmath$Vector3f();
      local_c0.cross(local_r0, normal);
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      MatrixUtil.transposeTransform(tmp, local_c0, getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()));
      Vector3f vec = localStack.get$javax$vecmath$Vector3f();
      vec.cross(tmp, local_r0);
      return this.inverseMass + normal.dot(vec);
    }
    finally
    {
      .Stack tmp109_107 = localStack;
      tmp109_107.pop$javax$vecmath$Vector3f();
      tmp109_107.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public float computeAngularImpulseDenominator(Vector3f arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp5_4 = localStack;
      tmp5_4.push$javax$vecmath$Vector3f();
      tmp5_4.push$javax$vecmath$Matrix3f();
      Vector3f vec = localStack.get$javax$vecmath$Vector3f();
      MatrixUtil.transposeTransform(vec, axis, getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()));
      return axis.dot(vec);
    }
    finally
    {
      .Stack tmp45_44 = localStack;
      tmp45_44.pop$javax$vecmath$Vector3f();
      tmp45_44.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public void updateDeactivation(float arg1)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      if ((getActivationState() == 2) || (getActivationState() == 4)) {
        return;
      }
      if ((getLinearVelocity(localStack.get$javax$vecmath$Vector3f()).lengthSquared() < this.linearSleepingThreshold * this.linearSleepingThreshold) && (getAngularVelocity(localStack.get$javax$vecmath$Vector3f()).lengthSquared() < this.angularSleepingThreshold * this.angularSleepingThreshold))
      {
        this.deactivationTime += timeStep;
      }
      else
      {
        this.deactivationTime = 0.0F;
        setActivationState(0);
      }
      return;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public boolean wantsSleeping()
  {
    if (getActivationState() == 4) {
      return false;
    }
    if ((BulletGlobals.isDeactivationDisabled()) || (BulletGlobals.getDeactivationTime() == 0.0F)) {
      return false;
    }
    if ((getActivationState() == 2) || (getActivationState() == 3)) {
      return true;
    }
    return this.deactivationTime > BulletGlobals.getDeactivationTime();
  }
  
  public BroadphaseProxy getBroadphaseProxy()
  {
    return this.broadphaseHandle;
  }
  
  public void setNewBroadphaseProxy(BroadphaseProxy broadphaseProxy)
  {
    this.broadphaseHandle = broadphaseProxy;
  }
  
  public MotionState getMotionState()
  {
    return this.optionalMotionState;
  }
  
  public void setMotionState(MotionState motionState)
  {
    this.optionalMotionState = motionState;
    if (this.optionalMotionState != null) {
      motionState.getWorldTransform(this.worldTransform);
    }
  }
  
  public void setAngularFactor(float angFac)
  {
    this.angularFactor = angFac;
  }
  
  public float getAngularFactor()
  {
    return this.angularFactor;
  }
  
  public boolean isInWorld()
  {
    return getBroadphaseProxy() != null;
  }
  
  public boolean checkCollideWithOverride(CollisionObject local_co)
  {
    RigidBody otherRb = upcast(local_co);
    if (otherRb == null) {
      return true;
    }
    for (int local_i = 0; local_i < this.constraintRefs.size(); local_i++)
    {
      TypedConstraint local_c = (TypedConstraint)this.constraintRefs.getQuick(local_i);
      if ((local_c.getRigidBodyA() == otherRb) || (local_c.getRigidBodyB() == otherRb)) {
        return false;
      }
    }
    return true;
  }
  
  public void addConstraintRef(TypedConstraint local_c)
  {
    int index = this.constraintRefs.indexOf(local_c);
    if (index == -1) {
      this.constraintRefs.add(local_c);
    }
    this.checkCollideWith = true;
  }
  
  public void removeConstraintRef(TypedConstraint local_c)
  {
    this.constraintRefs.remove(local_c);
    this.checkCollideWith = (this.constraintRefs.size() > 0);
  }
  
  public TypedConstraint getConstraintRef(int index)
  {
    return (TypedConstraint)this.constraintRefs.getQuick(index);
  }
  
  public int getNumConstraintRefs()
  {
    return this.constraintRefs.size();
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.RigidBody
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */