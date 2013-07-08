package com.bulletphysics.dynamics.constraintsolver;

import com.bulletphysics..Stack;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.util.ObjectPool;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class ContactConstraint
{
  public static final ContactSolverFunc resolveSingleCollision = new ContactSolverFunc()
  {
    public float resolveContact(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo info)
    {
      return ContactConstraint.resolveSingleCollision(body1, body2, contactPoint, info);
    }
  };
  public static final ContactSolverFunc resolveSingleFriction = new ContactSolverFunc()
  {
    public float resolveContact(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo info)
    {
      return ContactConstraint.resolveSingleFriction(body1, body2, contactPoint, info);
    }
  };
  public static final ContactSolverFunc resolveSingleCollisionCombined = new ContactSolverFunc()
  {
    public float resolveContact(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo info)
    {
      return ContactConstraint.resolveSingleCollisionCombined(body1, body2, contactPoint, info);
    }
  };
  
  public static void resolveSingleBilateral(RigidBody arg0, Vector3f arg1, RigidBody arg2, Vector3f arg3, float arg4, Vector3f arg5, float[] arg6, float arg7)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$com$bulletphysics$linearmath$Transform();
      tmp7_5.push$javax$vecmath$Vector3f();
      float normalLenSqr = normal.lengthSquared();
      assert (Math.abs(normalLenSqr) < 1.1F);
      if (normalLenSqr > 1.1F)
      {
        impulse[0] = 0.0F;
        return;
      }
      ObjectPool<JacobianEntry> jacobiansPool = ObjectPool.get(JacobianEntry.class);
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
      rel_pos1.sub(pos1, body1.getCenterOfMassPosition(tmp));
      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
      rel_pos2.sub(pos2, body2.getCenterOfMassPosition(tmp));
      Vector3f vel1 = localStack.get$javax$vecmath$Vector3f();
      body1.getVelocityInLocalPoint(rel_pos1, vel1);
      Vector3f vel2 = localStack.get$javax$vecmath$Vector3f();
      body2.getVelocityInLocalPoint(rel_pos2, vel2);
      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
      vel.sub(vel1, vel2);
      Matrix3f mat1 = body1.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
      mat1.transpose();
      Matrix3f mat2 = body2.getCenterOfMassTransform(localStack.get$com$bulletphysics$linearmath$Transform()).basis;
      mat2.transpose();
      JacobianEntry jac = (JacobianEntry)jacobiansPool.get();
      jac.init(mat1, mat2, rel_pos1, rel_pos2, normal, body1.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), body1.getInvMass(), body2.getInvInertiaDiagLocal(localStack.get$javax$vecmath$Vector3f()), body2.getInvMass());
      float jacDiagAB = jac.getDiagonal();
      float jacDiagABInv = 1.0F / jacDiagAB;
      Vector3f tmp1 = body1.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
      mat1.transform(tmp1);
      Vector3f tmp2 = body2.getAngularVelocity(localStack.get$javax$vecmath$Vector3f());
      mat2.transform(tmp2);
      float rel_vel = jac.getRelativeVelocity(body1.getLinearVelocity(localStack.get$javax$vecmath$Vector3f()), tmp1, body2.getLinearVelocity(localStack.get$javax$vecmath$Vector3f()), tmp2);
      jacobiansPool.release(jac);
      float local_a = jacDiagABInv;
      rel_vel = normal.dot(vel);
      float contactDamping = 0.2F;
      float velocityImpulse = -contactDamping * rel_vel * jacDiagABInv;
      impulse[0] = velocityImpulse;
      return;
    }
    finally
    {
      .Stack tmp389_387 = localStack;
      tmp389_387.pop$com$bulletphysics$linearmath$Transform();
      tmp389_387.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static float resolveSingleCollision(RigidBody arg0, RigidBody arg1, ManifoldPoint arg2, ContactSolverInfo arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
      Vector3f pos1_ = contactPoint.getPositionWorldOnA(localStack.get$javax$vecmath$Vector3f());
      Vector3f pos2_ = contactPoint.getPositionWorldOnB(localStack.get$javax$vecmath$Vector3f());
      Vector3f normal = contactPoint.normalWorldOnB;
      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
      rel_pos1.sub(pos1_, body1.getCenterOfMassPosition(tmpVec));
      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
      rel_pos2.sub(pos2_, body2.getCenterOfMassPosition(tmpVec));
      Vector3f vel1 = body1.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
      Vector3f vel2 = body2.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
      vel.sub(vel1, vel2);
      float rel_vel = normal.dot(vel);
      float Kfps = 1.0F / solverInfo.timeStep;
      float Kerp = solverInfo.erp;
      float Kcor = Kerp * Kfps;
      ConstraintPersistentData cpd = (ConstraintPersistentData)contactPoint.userPersistentData;
      assert (cpd != null);
      float distance = cpd.penetration;
      float positionalError = Kcor * -distance;
      float velocityError = cpd.restitution - rel_vel;
      float penetrationImpulse = positionalError * cpd.jacDiagABInv;
      float velocityImpulse = velocityError * cpd.jacDiagABInv;
      float normalImpulse = penetrationImpulse + velocityImpulse;
      float oldNormalImpulse = cpd.appliedImpulse;
      float sum = oldNormalImpulse + normalImpulse;
      cpd.appliedImpulse = (0.0F > sum ? 0.0F : sum);
      normalImpulse = cpd.appliedImpulse - oldNormalImpulse;
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      if (body1.getInvMass() != 0.0F)
      {
        tmp.scale(body1.getInvMass(), contactPoint.normalWorldOnB);
        body1.internalApplyImpulse(tmp, cpd.angularComponentA, normalImpulse);
      }
      if (body2.getInvMass() != 0.0F)
      {
        tmp.scale(body2.getInvMass(), contactPoint.normalWorldOnB);
        body2.internalApplyImpulse(tmp, cpd.angularComponentB, -normalImpulse);
      }
      return normalImpulse;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static float resolveSingleFriction(RigidBody arg0, RigidBody arg1, ManifoldPoint arg2, ContactSolverInfo arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      localStack.push$javax$vecmath$Vector3f();
      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
      Vector3f pos1 = contactPoint.getPositionWorldOnA(localStack.get$javax$vecmath$Vector3f());
      Vector3f pos2 = contactPoint.getPositionWorldOnB(localStack.get$javax$vecmath$Vector3f());
      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
      rel_pos1.sub(pos1, body1.getCenterOfMassPosition(tmpVec));
      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
      rel_pos2.sub(pos2, body2.getCenterOfMassPosition(tmpVec));
      ConstraintPersistentData cpd = (ConstraintPersistentData)contactPoint.userPersistentData;
      assert (cpd != null);
      float combinedFriction = cpd.friction;
      float limit = cpd.appliedImpulse * combinedFriction;
      if (cpd.appliedImpulse > 0.0F)
      {
        Vector3f vel1 = localStack.get$javax$vecmath$Vector3f();
        body1.getVelocityInLocalPoint(rel_pos1, vel1);
        Vector3f vel2 = localStack.get$javax$vecmath$Vector3f();
        body2.getVelocityInLocalPoint(rel_pos2, vel2);
        Vector3f vel = localStack.get$javax$vecmath$Vector3f();
        vel.sub(vel1, vel2);
        float vrel = cpd.frictionWorldTangential0.dot(vel);
        float local_j1 = -vrel * cpd.jacDiagABInvTangent0;
        float oldTangentImpulse = cpd.accumulatedTangentImpulse0;
        cpd.accumulatedTangentImpulse0 = (oldTangentImpulse + local_j1);
        cpd.accumulatedTangentImpulse0 = Math.min(cpd.accumulatedTangentImpulse0, limit);
        cpd.accumulatedTangentImpulse0 = Math.max(cpd.accumulatedTangentImpulse0, -limit);
        local_j1 = cpd.accumulatedTangentImpulse0 - oldTangentImpulse;
        float vrel = cpd.frictionWorldTangential1.dot(vel);
        float local_j2 = -vrel * cpd.jacDiagABInvTangent1;
        float oldTangentImpulse = cpd.accumulatedTangentImpulse1;
        cpd.accumulatedTangentImpulse1 = (oldTangentImpulse + local_j2);
        cpd.accumulatedTangentImpulse1 = Math.min(cpd.accumulatedTangentImpulse1, limit);
        cpd.accumulatedTangentImpulse1 = Math.max(cpd.accumulatedTangentImpulse1, -limit);
        local_j2 = cpd.accumulatedTangentImpulse1 - oldTangentImpulse;
        Vector3f vrel = localStack.get$javax$vecmath$Vector3f();
        if (body1.getInvMass() != 0.0F)
        {
          vrel.scale(body1.getInvMass(), cpd.frictionWorldTangential0);
          body1.internalApplyImpulse(vrel, cpd.frictionAngularComponent0A, local_j1);
          vrel.scale(body1.getInvMass(), cpd.frictionWorldTangential1);
          body1.internalApplyImpulse(vrel, cpd.frictionAngularComponent1A, local_j2);
        }
        if (body2.getInvMass() != 0.0F)
        {
          vrel.scale(body2.getInvMass(), cpd.frictionWorldTangential0);
          body2.internalApplyImpulse(vrel, cpd.frictionAngularComponent0B, -local_j1);
          vrel.scale(body2.getInvMass(), cpd.frictionWorldTangential1);
          body2.internalApplyImpulse(vrel, cpd.frictionAngularComponent1B, -local_j2);
        }
      }
      return cpd.appliedImpulse;
    }
    finally
    {
      localStack.pop$javax$vecmath$Vector3f();
    }
  }
  
  public static float resolveSingleCollisionCombined(RigidBody arg0, RigidBody arg1, ManifoldPoint arg2, ContactSolverInfo arg3)
  {
    .Stack localStack = .Stack.get();
    try
    {
      .Stack tmp7_5 = localStack;
      tmp7_5.push$javax$vecmath$Vector3f();
      tmp7_5.push$javax$vecmath$Matrix3f();
      Vector3f tmpVec = localStack.get$javax$vecmath$Vector3f();
      Vector3f pos1 = contactPoint.getPositionWorldOnA(localStack.get$javax$vecmath$Vector3f());
      Vector3f pos2 = contactPoint.getPositionWorldOnB(localStack.get$javax$vecmath$Vector3f());
      Vector3f normal = contactPoint.normalWorldOnB;
      Vector3f rel_pos1 = localStack.get$javax$vecmath$Vector3f();
      rel_pos1.sub(pos1, body1.getCenterOfMassPosition(tmpVec));
      Vector3f rel_pos2 = localStack.get$javax$vecmath$Vector3f();
      rel_pos2.sub(pos2, body2.getCenterOfMassPosition(tmpVec));
      Vector3f vel1 = body1.getVelocityInLocalPoint(rel_pos1, localStack.get$javax$vecmath$Vector3f());
      Vector3f vel2 = body2.getVelocityInLocalPoint(rel_pos2, localStack.get$javax$vecmath$Vector3f());
      Vector3f vel = localStack.get$javax$vecmath$Vector3f();
      vel.sub(vel1, vel2);
      float rel_vel = normal.dot(vel);
      float Kfps = 1.0F / solverInfo.timeStep;
      float Kerp = solverInfo.erp;
      float Kcor = Kerp * Kfps;
      ConstraintPersistentData cpd = (ConstraintPersistentData)contactPoint.userPersistentData;
      assert (cpd != null);
      float distance = cpd.penetration;
      float positionalError = Kcor * -distance;
      float velocityError = cpd.restitution - rel_vel;
      float penetrationImpulse = positionalError * cpd.jacDiagABInv;
      float velocityImpulse = velocityError * cpd.jacDiagABInv;
      float normalImpulse = penetrationImpulse + velocityImpulse;
      float oldNormalImpulse = cpd.appliedImpulse;
      float sum = oldNormalImpulse + normalImpulse;
      cpd.appliedImpulse = (0.0F > sum ? 0.0F : sum);
      normalImpulse = cpd.appliedImpulse - oldNormalImpulse;
      Vector3f tmp = localStack.get$javax$vecmath$Vector3f();
      if (body1.getInvMass() != 0.0F)
      {
        tmp.scale(body1.getInvMass(), contactPoint.normalWorldOnB);
        body1.internalApplyImpulse(tmp, cpd.angularComponentA, normalImpulse);
      }
      if (body2.getInvMass() != 0.0F)
      {
        tmp.scale(body2.getInvMass(), contactPoint.normalWorldOnB);
        body2.internalApplyImpulse(tmp, cpd.angularComponentB, -normalImpulse);
      }
      body1.getVelocityInLocalPoint(rel_pos1, vel1);
      body2.getVelocityInLocalPoint(rel_pos2, vel2);
      vel.sub(vel1, vel2);
      rel_vel = normal.dot(vel);
      tmp.scale(rel_vel, normal);
      Vector3f lat_vel = localStack.get$javax$vecmath$Vector3f();
      lat_vel.sub(vel, tmp);
      float lat_rel_vel = lat_vel.length();
      float combinedFriction = cpd.friction;
      if ((cpd.appliedImpulse > 0.0F) && (lat_rel_vel > 1.192093E-007F))
      {
        lat_vel.scale(1.0F / lat_rel_vel);
        Vector3f temp1 = localStack.get$javax$vecmath$Vector3f();
        temp1.cross(rel_pos1, lat_vel);
        body1.getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()).transform(temp1);
        Vector3f temp2 = localStack.get$javax$vecmath$Vector3f();
        temp2.cross(rel_pos2, lat_vel);
        body2.getInvInertiaTensorWorld(localStack.get$javax$vecmath$Matrix3f()).transform(temp2);
        Vector3f java_tmp1 = localStack.get$javax$vecmath$Vector3f();
        java_tmp1.cross(temp1, rel_pos1);
        Vector3f java_tmp2 = localStack.get$javax$vecmath$Vector3f();
        java_tmp2.cross(temp2, rel_pos2);
        tmp.add(java_tmp1, java_tmp2);
        float friction_impulse = lat_rel_vel / (body1.getInvMass() + body2.getInvMass() + lat_vel.dot(tmp));
        float normal_impulse = cpd.appliedImpulse * combinedFriction;
        friction_impulse = Math.min(friction_impulse, normal_impulse);
        friction_impulse = Math.max(friction_impulse, -normal_impulse);
        tmp.scale(-friction_impulse, lat_vel);
        body1.applyImpulse(tmp, rel_pos1);
        tmp.scale(friction_impulse, lat_vel);
        body2.applyImpulse(tmp, rel_pos2);
      }
      return normalImpulse;
    }
    finally
    {
      .Stack tmp665_663 = localStack;
      tmp665_663.pop$javax$vecmath$Vector3f();
      tmp665_663.pop$javax$vecmath$Matrix3f();
    }
  }
  
  public static float resolveSingleFrictionEmpty(RigidBody body1, RigidBody body2, ManifoldPoint contactPoint, ContactSolverInfo solverInfo)
  {
    return 0.0F;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     com.bulletphysics.dynamics.constraintsolver.ContactConstraint
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */