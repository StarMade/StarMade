package org.schema.game.common.data.physics;

import com.bulletphysics.BulletGlobals;
import com.bulletphysics.BulletStats;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.narrowphase.ManifoldPoint;
import com.bulletphysics.collision.narrowphase.PersistentManifold;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.constraintsolver.ConstraintPersistentData;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.ContactConstraint;
import com.bulletphysics.dynamics.constraintsolver.ContactSolverFunc;
import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
import com.bulletphysics.dynamics.constraintsolver.JacobianEntry;
import com.bulletphysics.dynamics.constraintsolver.SolverBody;
import com.bulletphysics.dynamics.constraintsolver.SolverConstraint;
import com.bulletphysics.dynamics.constraintsolver.SolverConstraintType;
import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
import com.bulletphysics.linearmath.IDebugDraw;
import com.bulletphysics.linearmath.MiscUtil;
import com.bulletphysics.linearmath.Transform;
import com.bulletphysics.linearmath.TransformUtil;
import com.bulletphysics.util.IntArrayList;
import com.bulletphysics.util.ObjectArrayList;
import com.bulletphysics.util.ObjectPool;
import java.util.Arrays;
import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class SequentialImpulseConstraintSolverExt
  extends ConstraintSolver
{
  private static final int MAX_CONTACT_SOLVER_TYPES = 4;
  private static final int SEQUENTIAL_IMPULSE_MAX_SOLVER_POINTS = 128;
  private int[] gOrder = new int[256];
  private int totalCpd = 0;
  private final ObjectPool bodiesPool = ObjectPool.get(SolverBody.class);
  private final ObjectPool constraintsPool = ObjectPool.get(SolverConstraint.class);
  private final ObjectPool jacobiansPool = ObjectPool.get(JacobianEntry.class);
  private final ObjectArrayList tmpSolverBodyPool = new ObjectArrayList();
  private final ObjectArrayList tmpSolverConstraintPool = new ObjectArrayList();
  private final ObjectArrayList tmpSolverFrictionConstraintPool = new ObjectArrayList();
  private final IntArrayList orderTmpConstraintPool = new IntArrayList();
  private final IntArrayList orderFrictionConstraintPool = new IntArrayList();
  protected final ContactSolverFunc[][] contactDispatch = new ContactSolverFunc[4][4];
  protected final ContactSolverFunc[][] frictionDispatch = new ContactSolverFunc[4][4];
  protected long btSeed2 = 0L;
  private final SequentialImpulseContraintSolverExtVariableSet field_428 = new SequentialImpulseContraintSolverExtVariableSet();
  
  public SequentialImpulseConstraintSolverExt()
  {
    BulletGlobals.setContactDestroyedCallback(new SequentialImpulseConstraintSolverExt.1(this));
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++)
      {
        this.contactDispatch[i][j] = ContactConstraint.resolveSingleCollision;
        this.frictionDispatch[i][j] = ContactConstraint.resolveSingleFriction;
      }
    }
  }
  
  public long rand2()
  {
    this.btSeed2 = (1664525L * this.btSeed2 + 1013904223L);
    return this.btSeed2;
  }
  
  public int randInt2(int paramInt)
  {
    long l1 = paramInt;
    long l2 = rand2();
    if (l1 <= 65536L)
    {
      l2 ^= l2 >>> 16;
      if (l1 <= 256L)
      {
        l2 ^= l2 >>> 8;
        if (l1 <= 16L)
        {
          l2 ^= l2 >>> 4;
          if (l1 <= 4L)
          {
            l2 ^= l2 >>> 2;
            if (l1 <= 2L) {
              l2 ^= l2 >>> 1;
            }
          }
        }
      }
    }
    return (int)Math.abs(l2 % l1);
  }
  
  private void initSolverBody(SolverBody paramSolverBody, CollisionObject paramCollisionObject)
  {
    RigidBody localRigidBody;
    if ((localRigidBody = RigidBody.upcast(paramCollisionObject)) != null)
    {
      localRigidBody.getAngularVelocity(paramSolverBody.angularVelocity);
      paramSolverBody.centerOfMassPosition.set(paramCollisionObject.getWorldTransform(this.field_428.centerOfMassTrans).origin);
      paramSolverBody.friction = paramCollisionObject.getFriction();
      paramSolverBody.invMass = localRigidBody.getInvMass();
      localRigidBody.getLinearVelocity(paramSolverBody.linearVelocity);
      paramSolverBody.originalBody = localRigidBody;
      paramSolverBody.angularFactor = localRigidBody.getAngularFactor();
    }
    else
    {
      paramSolverBody.angularVelocity.set(0.0F, 0.0F, 0.0F);
      paramSolverBody.centerOfMassPosition.set(paramCollisionObject.getWorldTransform(this.field_428.centerOfMassTrans).origin);
      paramSolverBody.friction = paramCollisionObject.getFriction();
      paramSolverBody.invMass = 0.0F;
      paramSolverBody.linearVelocity.set(0.0F, 0.0F, 0.0F);
      paramSolverBody.originalBody = null;
      paramSolverBody.angularFactor = 1.0F;
    }
    paramSolverBody.pushVelocity.set(0.0F, 0.0F, 0.0F);
    paramSolverBody.turnVelocity.set(0.0F, 0.0F, 0.0F);
  }
  
  private float restitutionCurve(float paramFloat1, float paramFloat2)
  {
    return paramFloat2 * -paramFloat1;
  }
  
  private void resolveSplitPenetrationImpulseCacheFriendly(SolverBody paramSolverBody1, SolverBody paramSolverBody2, SolverConstraint paramSolverConstraint, ContactSolverInfo paramContactSolverInfo)
  {
    if (paramSolverConstraint.penetration < paramContactSolverInfo.splitImpulsePenetrationThreshold)
    {
      BulletStats.gNumSplitImpulseRecoveries += 1;
      float f1 = paramSolverConstraint.contactNormal.dot(paramSolverBody1.pushVelocity) + paramSolverConstraint.relpos1CrossNormal.dot(paramSolverBody1.turnVelocity);
      float f2 = paramSolverConstraint.contactNormal.dot(paramSolverBody2.pushVelocity) + paramSolverConstraint.relpos2CrossNormal.dot(paramSolverBody2.turnVelocity);
      f1 -= f2;
      paramContactSolverInfo = -paramSolverConstraint.penetration * paramContactSolverInfo.erp2 / paramContactSolverInfo.timeStep;
      f1 = paramSolverConstraint.restitution - f1;
      paramContactSolverInfo *= paramSolverConstraint.jacDiagABInv;
      f1 *= paramSolverConstraint.jacDiagABInv;
      paramContactSolverInfo += f1;
      paramContactSolverInfo = (f1 = paramSolverConstraint.appliedPushImpulse) + paramContactSolverInfo;
      paramSolverConstraint.appliedPushImpulse = (0.0F > paramContactSolverInfo ? 0.0F : paramContactSolverInfo);
      paramContactSolverInfo = paramSolverConstraint.appliedPushImpulse - f1;
      Vector3f localVector3f;
      (localVector3f = this.field_428.tmp).scale(paramSolverBody1.invMass, paramSolverConstraint.contactNormal);
      paramSolverBody1.internalApplyPushImpulse(localVector3f, paramSolverConstraint.angularComponentA, paramContactSolverInfo);
      localVector3f.scale(paramSolverBody2.invMass, paramSolverConstraint.contactNormal);
      paramSolverBody2.internalApplyPushImpulse(localVector3f, paramSolverConstraint.angularComponentB, -paramContactSolverInfo);
    }
  }
  
  private float resolveSingleCollisionCombinedCacheFriendly(SolverBody paramSolverBody1, SolverBody paramSolverBody2, SolverConstraint paramSolverConstraint, ContactSolverInfo paramContactSolverInfo)
  {
    float f1 = paramSolverConstraint.contactNormal.dot(paramSolverBody1.linearVelocity) + paramSolverConstraint.relpos1CrossNormal.dot(paramSolverBody1.angularVelocity);
    float f2 = paramSolverConstraint.contactNormal.dot(paramSolverBody2.linearVelocity) + paramSolverConstraint.relpos2CrossNormal.dot(paramSolverBody2.angularVelocity);
    f1 -= f2;
    f2 = 0.0F;
    if ((!paramContactSolverInfo.splitImpulse) || (paramSolverConstraint.penetration > paramContactSolverInfo.splitImpulsePenetrationThreshold)) {
      f2 = -paramSolverConstraint.penetration * paramContactSolverInfo.erp / paramContactSolverInfo.timeStep;
    }
    paramContactSolverInfo = paramSolverConstraint.restitution - f1;
    f1 = f2 * paramSolverConstraint.jacDiagABInv;
    paramContactSolverInfo *= paramSolverConstraint.jacDiagABInv;
    paramContactSolverInfo = f1 + paramContactSolverInfo;
    paramContactSolverInfo = (f1 = paramSolverConstraint.appliedImpulse) + paramContactSolverInfo;
    paramSolverConstraint.appliedImpulse = (0.0F > paramContactSolverInfo ? 0.0F : paramContactSolverInfo);
    paramContactSolverInfo = paramSolverConstraint.appliedImpulse - f1;
    Vector3f localVector3f;
    (localVector3f = this.field_428.tmp2).scale(paramSolverBody1.invMass, paramSolverConstraint.contactNormal);
    paramSolverBody1.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentA, paramContactSolverInfo);
    localVector3f.scale(paramSolverBody2.invMass, paramSolverConstraint.contactNormal);
    paramSolverBody2.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentB, -paramContactSolverInfo);
    return paramContactSolverInfo;
  }
  
  private float resolveSingleFrictionCacheFriendly(SolverBody paramSolverBody1, SolverBody paramSolverBody2, SolverConstraint paramSolverConstraint, ContactSolverInfo paramContactSolverInfo, float paramFloat)
  {
    paramContactSolverInfo = paramSolverConstraint.friction;
    paramContactSolverInfo = paramFloat * paramContactSolverInfo;
    if (paramFloat > 0.0F)
    {
      paramFloat = paramSolverConstraint.contactNormal.dot(paramSolverBody1.linearVelocity) + paramSolverConstraint.relpos1CrossNormal.dot(paramSolverBody1.angularVelocity);
      float f = paramSolverConstraint.contactNormal.dot(paramSolverBody2.linearVelocity) + paramSolverConstraint.relpos2CrossNormal.dot(paramSolverBody2.angularVelocity);
      paramFloat = -(paramFloat - f) * paramSolverConstraint.jacDiagABInv;
      f = paramSolverConstraint.appliedImpulse;
      paramSolverConstraint.appliedImpulse = (f + paramFloat);
      if (paramContactSolverInfo < paramSolverConstraint.appliedImpulse) {
        paramSolverConstraint.appliedImpulse = paramContactSolverInfo;
      } else if (paramSolverConstraint.appliedImpulse < -paramContactSolverInfo) {
        paramSolverConstraint.appliedImpulse = (-paramContactSolverInfo);
      }
      paramFloat = paramSolverConstraint.appliedImpulse - f;
      Vector3f localVector3f;
      (localVector3f = this.field_428.tmp3).scale(paramSolverBody1.invMass, paramSolverConstraint.contactNormal);
      paramSolverBody1.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentA, paramFloat);
      localVector3f.scale(paramSolverBody2.invMass, paramSolverConstraint.contactNormal);
      paramSolverBody2.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentB, -paramFloat);
    }
    return 0.0F;
  }
  
  protected void addFrictionConstraint(Vector3f paramVector3f1, int paramInt1, int paramInt2, int paramInt3, ManifoldPoint paramManifoldPoint, Vector3f paramVector3f2, Vector3f paramVector3f3, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, float paramFloat)
  {
    paramCollisionObject1 = RigidBody.upcast(paramCollisionObject1);
    paramCollisionObject2 = RigidBody.upcast(paramCollisionObject2);
    SolverConstraint localSolverConstraint = (SolverConstraint)this.constraintsPool.get();
    this.tmpSolverFrictionConstraintPool.add(localSolverConstraint);
    localSolverConstraint.contactNormal.set(paramVector3f1);
    localSolverConstraint.solverBodyIdA = paramInt1;
    localSolverConstraint.solverBodyIdB = paramInt2;
    localSolverConstraint.constraintType = SolverConstraintType.SOLVER_FRICTION_1D;
    localSolverConstraint.frictionIndex = paramInt3;
    localSolverConstraint.friction = paramManifoldPoint.combinedFriction;
    localSolverConstraint.originalContactPoint = null;
    localSolverConstraint.appliedImpulse = 0.0F;
    localSolverConstraint.appliedPushImpulse = 0.0F;
    localSolverConstraint.penetration = 0.0F;
    paramInt1 = this.field_428.ftorqueAxis1;
    paramInt2 = this.field_428.tmpMat;
    paramInt1.cross(paramVector3f2, localSolverConstraint.contactNormal);
    localSolverConstraint.relpos1CrossNormal.set(paramInt1);
    if (paramCollisionObject1 != null)
    {
      localSolverConstraint.angularComponentA.set(paramInt1);
      paramCollisionObject1.getInvInertiaTensorWorld(paramInt2).transform(localSolverConstraint.angularComponentA);
    }
    else
    {
      localSolverConstraint.angularComponentA.set(0.0F, 0.0F, 0.0F);
    }
    paramInt1.cross(paramVector3f3, localSolverConstraint.contactNormal);
    localSolverConstraint.relpos2CrossNormal.set(paramInt1);
    if (paramCollisionObject2 != null)
    {
      localSolverConstraint.angularComponentB.set(paramInt1);
      paramCollisionObject2.getInvInertiaTensorWorld(paramInt2).transform(localSolverConstraint.angularComponentB);
    }
    else
    {
      localSolverConstraint.angularComponentB.set(0.0F, 0.0F, 0.0F);
    }
    paramInt1 = this.field_428.vec;
    paramInt2 = 0.0F;
    paramInt3 = 0.0F;
    if (paramCollisionObject1 != null)
    {
      paramInt1.cross(localSolverConstraint.angularComponentA, paramVector3f2);
      paramInt2 = paramCollisionObject1.getInvMass() + paramVector3f1.dot(paramInt1);
    }
    if (paramCollisionObject2 != null)
    {
      paramInt1.cross(localSolverConstraint.angularComponentB, paramVector3f3);
      paramInt3 = paramCollisionObject2.getInvMass() + paramVector3f1.dot(paramInt1);
    }
    paramVector3f1 = paramFloat / (paramInt2 + paramInt3);
    localSolverConstraint.jacDiagABInv = paramVector3f1;
  }
  
  public float solveGroupCacheFriendlySetup(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw)
  {
    BulletStats.pushProfile("solveGroupCacheFriendlySetup");
    if (paramInt5 + paramInt3 == 0)
    {
      paramObjectArrayList1 = 0.0F;
      BulletStats.popProfile();
      return 0.0F;
    }
    try
    {
      paramObjectArrayList1 = null;
      paramInt1 = null;
      paramIDebugDraw = null;
      Transform localTransform = this.field_428.tmpTrans;
      Vector3f localVector3f1 = this.field_428.rel_pos1A;
      Vector3f localVector3f2 = this.field_428.rel_pos2A;
      Vector3f localVector3f3 = this.field_428.pos1A;
      Vector3f localVector3f4 = this.field_428.pos2A;
      Vector3f localVector3f5 = this.field_428.velA;
      Vector3f localVector3f6 = this.field_428.torqueAxis0A;
      Vector3f localVector3f7 = this.field_428.torqueAxis1A;
      Vector3f localVector3f8 = this.field_428.vel1A;
      Vector3f localVector3f9 = this.field_428.vel2A;
      Vector3f localVector3f10 = this.field_428.vecA;
      Matrix3f localMatrix3f = this.field_428.tmpMatA;
      for (int i = 0; i < paramInt3; i++)
      {
        paramInt1 = (CollisionObject)(paramObjectArrayList1 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + i)).getBody0();
        paramIDebugDraw = (CollisionObject)paramObjectArrayList1.getBody1();
        int m = -1;
        int n = -1;
        if (paramObjectArrayList1.getNumContacts() != 0)
        {
          SolverBody localSolverBody;
          if (paramInt1.getIslandTag() >= 0)
          {
            if (paramInt1.getCompanionId() >= 0)
            {
              m = paramInt1.getCompanionId();
            }
            else
            {
              m = this.tmpSolverBodyPool.size();
              localSolverBody = (SolverBody)this.bodiesPool.get();
              this.tmpSolverBodyPool.add(localSolverBody);
              initSolverBody(localSolverBody, paramInt1);
              paramInt1.setCompanionId(m);
            }
          }
          else
          {
            m = this.tmpSolverBodyPool.size();
            localSolverBody = (SolverBody)this.bodiesPool.get();
            this.tmpSolverBodyPool.add(localSolverBody);
            initSolverBody(localSolverBody, paramInt1);
          }
          if (paramIDebugDraw.getIslandTag() >= 0)
          {
            if (paramIDebugDraw.getCompanionId() >= 0)
            {
              n = paramIDebugDraw.getCompanionId();
            }
            else
            {
              n = this.tmpSolverBodyPool.size();
              localSolverBody = (SolverBody)this.bodiesPool.get();
              this.tmpSolverBodyPool.add(localSolverBody);
              initSolverBody(localSolverBody, paramIDebugDraw);
              paramIDebugDraw.setCompanionId(n);
            }
          }
          else
          {
            n = this.tmpSolverBodyPool.size();
            localSolverBody = (SolverBody)this.bodiesPool.get();
            this.tmpSolverBodyPool.add(localSolverBody);
            initSolverBody(localSolverBody, paramIDebugDraw);
          }
        }
        for (int i1 = 0; i1 < paramObjectArrayList1.getNumContacts(); i1++)
        {
          ManifoldPoint localManifoldPoint;
          if ((localManifoldPoint = paramObjectArrayList1.getContactPoint(i1)).getDistance() <= 0.0F)
          {
            localManifoldPoint.getPositionWorldOnA(localVector3f3);
            localManifoldPoint.getPositionWorldOnB(localVector3f4);
            localVector3f1.sub(localVector3f3, paramInt1.getWorldTransform(localTransform).origin);
            localVector3f2.sub(localVector3f4, paramIDebugDraw.getWorldTransform(localTransform).origin);
            int i2 = this.tmpSolverConstraintPool.size();
            SolverConstraint localSolverConstraint2 = (SolverConstraint)this.constraintsPool.get();
            this.tmpSolverConstraintPool.add(localSolverConstraint2);
            RigidBody localRigidBody1 = RigidBody.upcast(paramInt1);
            RigidBody localRigidBody2 = RigidBody.upcast(paramIDebugDraw);
            localSolverConstraint2.solverBodyIdA = m;
            localSolverConstraint2.solverBodyIdB = n;
            localSolverConstraint2.constraintType = SolverConstraintType.SOLVER_CONTACT_1D;
            localSolverConstraint2.originalContactPoint = localManifoldPoint;
            localVector3f6.cross(localVector3f1, localManifoldPoint.normalWorldOnB);
            if (localRigidBody1 != null)
            {
              localSolverConstraint2.angularComponentA.set(localVector3f6);
              localRigidBody1.getInvInertiaTensorWorld(localMatrix3f).transform(localSolverConstraint2.angularComponentA);
            }
            else
            {
              localSolverConstraint2.angularComponentA.set(0.0F, 0.0F, 0.0F);
            }
            localVector3f7.cross(localVector3f2, localManifoldPoint.normalWorldOnB);
            if (localRigidBody2 != null)
            {
              localSolverConstraint2.angularComponentB.set(localVector3f7);
              localRigidBody2.getInvInertiaTensorWorld(localMatrix3f).transform(localSolverConstraint2.angularComponentB);
            }
            else
            {
              localSolverConstraint2.angularComponentB.set(0.0F, 0.0F, 0.0F);
            }
            float f2 = 0.0F;
            float f3 = 0.0F;
            if (localRigidBody1 != null)
            {
              localVector3f10.cross(localSolverConstraint2.angularComponentA, localVector3f1);
              f2 = localRigidBody1.getInvMass() + localManifoldPoint.normalWorldOnB.dot(localVector3f10);
            }
            if (localRigidBody2 != null)
            {
              localVector3f10.cross(localSolverConstraint2.angularComponentB, localVector3f2);
              f3 = localRigidBody2.getInvMass() + localManifoldPoint.normalWorldOnB.dot(localVector3f10);
            }
            float f1 = 1.0F / (f2 + f3);
            localSolverConstraint2.jacDiagABInv = f1;
            localSolverConstraint2.contactNormal.set(localManifoldPoint.normalWorldOnB);
            localSolverConstraint2.relpos1CrossNormal.cross(localVector3f1, localManifoldPoint.normalWorldOnB);
            localSolverConstraint2.relpos2CrossNormal.cross(localVector3f2, localManifoldPoint.normalWorldOnB);
            if (localRigidBody1 != null) {
              localRigidBody1.getVelocityInLocalPoint(localVector3f1, localVector3f8);
            } else {
              localVector3f8.set(0.0F, 0.0F, 0.0F);
            }
            if (localRigidBody2 != null) {
              localRigidBody2.getVelocityInLocalPoint(localVector3f2, localVector3f9);
            } else {
              localVector3f9.set(0.0F, 0.0F, 0.0F);
            }
            localVector3f5.sub(localVector3f8, localVector3f9);
            f1 = localManifoldPoint.normalWorldOnB.dot(localVector3f5);
            localSolverConstraint2.penetration = Math.min(localManifoldPoint.getDistance() + paramContactSolverInfo.linearSlop, 0.0F);
            localSolverConstraint2.friction = localManifoldPoint.combinedFriction;
            localSolverConstraint2.restitution = restitutionCurve(f1, localManifoldPoint.combinedRestitution);
            if (localSolverConstraint2.restitution <= 0.0F) {
              localSolverConstraint2.restitution = 0.0F;
            }
            f2 = -localSolverConstraint2.penetration / paramContactSolverInfo.timeStep;
            if (localSolverConstraint2.restitution > f2) {
              localSolverConstraint2.penetration = 0.0F;
            }
            Vector3f localVector3f11 = this.field_428.tmp4;
            if ((paramContactSolverInfo.solverMode & 0x4) != 0)
            {
              localSolverConstraint2.appliedImpulse = (localManifoldPoint.appliedImpulse * paramContactSolverInfo.warmstartingFactor);
              if (localRigidBody1 != null)
              {
                localVector3f11.scale(localRigidBody1.getInvMass(), localSolverConstraint2.contactNormal);
                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdA)).internalApplyImpulse(localVector3f11, localSolverConstraint2.angularComponentA, localSolverConstraint2.appliedImpulse);
              }
              if (localRigidBody2 != null)
              {
                localVector3f11.scale(localRigidBody2.getInvMass(), localSolverConstraint2.contactNormal);
                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdB)).internalApplyImpulse(localVector3f11, localSolverConstraint2.angularComponentB, -localSolverConstraint2.appliedImpulse);
              }
            }
            else
            {
              localSolverConstraint2.appliedImpulse = 0.0F;
            }
            localSolverConstraint2.appliedPushImpulse = 0.0F;
            localSolverConstraint2.frictionIndex = this.tmpSolverFrictionConstraintPool.size();
            if (!localManifoldPoint.lateralFrictionInitialized)
            {
              localManifoldPoint.lateralFrictionDir1.scale(f1, localManifoldPoint.normalWorldOnB);
              localManifoldPoint.lateralFrictionDir1.sub(localVector3f5, localManifoldPoint.lateralFrictionDir1);
              if ((f1 = localManifoldPoint.lateralFrictionDir1.lengthSquared()) > 1.192093E-007F)
              {
                localManifoldPoint.lateralFrictionDir1.scale(1.0F / (float)Math.sqrt(f1));
                addFrictionConstraint(localManifoldPoint.lateralFrictionDir1, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
                localManifoldPoint.lateralFrictionDir2.cross(localManifoldPoint.lateralFrictionDir1, localManifoldPoint.normalWorldOnB);
                localManifoldPoint.lateralFrictionDir2.normalize();
                addFrictionConstraint(localManifoldPoint.lateralFrictionDir2, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
              }
              else
              {
                TransformUtil.planeSpace1(localManifoldPoint.normalWorldOnB, localManifoldPoint.lateralFrictionDir1, localManifoldPoint.lateralFrictionDir2);
                addFrictionConstraint(localManifoldPoint.lateralFrictionDir1, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
                addFrictionConstraint(localManifoldPoint.lateralFrictionDir2, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
              }
              localManifoldPoint.lateralFrictionInitialized = true;
            }
            else
            {
              addFrictionConstraint(localManifoldPoint.lateralFrictionDir1, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
              addFrictionConstraint(localManifoldPoint.lateralFrictionDir2, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
            }
            SolverConstraint localSolverConstraint1 = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(localSolverConstraint2.frictionIndex);
            if ((paramContactSolverInfo.solverMode & 0x4) != 0)
            {
              localSolverConstraint1.appliedImpulse = (localManifoldPoint.appliedImpulseLateral1 * paramContactSolverInfo.warmstartingFactor);
              if (localRigidBody1 != null)
              {
                localVector3f11.scale(localRigidBody1.getInvMass(), localSolverConstraint1.contactNormal);
                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdA)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentA, localSolverConstraint1.appliedImpulse);
              }
              if (localRigidBody2 != null)
              {
                localVector3f11.scale(localRigidBody2.getInvMass(), localSolverConstraint1.contactNormal);
                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdB)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentB, -localSolverConstraint1.appliedImpulse);
              }
            }
            else
            {
              localSolverConstraint1.appliedImpulse = 0.0F;
            }
            localSolverConstraint1 = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(localSolverConstraint2.frictionIndex + 1);
            if ((paramContactSolverInfo.solverMode & 0x4) != 0)
            {
              localSolverConstraint1.appliedImpulse = (localManifoldPoint.appliedImpulseLateral2 * paramContactSolverInfo.warmstartingFactor);
              if (localRigidBody1 != null)
              {
                localVector3f11.scale(localRigidBody1.getInvMass(), localSolverConstraint1.contactNormal);
                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdA)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentA, localSolverConstraint1.appliedImpulse);
              }
              if (localRigidBody2 != null)
              {
                localVector3f11.scale(localRigidBody2.getInvMass(), localSolverConstraint1.contactNormal);
                ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdB)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentB, -localSolverConstraint1.appliedImpulse);
              }
            }
            else
            {
              localSolverConstraint1.appliedImpulse = 0.0F;
            }
          }
        }
      }
      for (i = 0; i < paramInt5; i++) {
        ((TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + i)).buildJacobian();
      }
      i = this.tmpSolverConstraintPool.size();
      int j = this.tmpSolverFrictionConstraintPool.size();
      MiscUtil.resize(this.orderTmpConstraintPool, i, 0);
      MiscUtil.resize(this.orderFrictionConstraintPool, j, 0);
      for (int k = 0; k < i; k++) {
        this.orderTmpConstraintPool.set(k, k);
      }
      for (k = 0; k < j; k++) {
        this.orderFrictionConstraintPool.set(k, k);
      }
      return 0.0F;
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  public float solveGroupCacheFriendlyIterations(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw)
  {
    BulletStats.pushProfile("solveGroupCacheFriendlyIterations");
    try
    {
      paramObjectArrayList1 = this.tmpSolverConstraintPool.size();
      paramInt1 = this.tmpSolverFrictionConstraintPool.size();
      for (paramObjectArrayList2 = 0; paramObjectArrayList2 < paramContactSolverInfo.numIterations; paramObjectArrayList2++)
      {
        if (((paramContactSolverInfo.solverMode & 0x1) != 0) && ((paramObjectArrayList2 & 0x7) == 0))
        {
          for (paramInt2 = 0; paramInt2 < paramObjectArrayList1; paramInt2++)
          {
            paramInt3 = this.orderTmpConstraintPool.get(paramInt2);
            paramIDebugDraw = randInt2(paramInt2 + 1);
            this.orderTmpConstraintPool.set(paramInt2, this.orderTmpConstraintPool.get(paramIDebugDraw));
            this.orderTmpConstraintPool.set(paramIDebugDraw, paramInt3);
          }
          for (paramInt2 = 0; paramInt2 < paramInt1; paramInt2++)
          {
            paramInt3 = this.orderFrictionConstraintPool.get(paramInt2);
            paramIDebugDraw = randInt2(paramInt2 + 1);
            this.orderFrictionConstraintPool.set(paramInt2, this.orderFrictionConstraintPool.get(paramIDebugDraw));
            this.orderFrictionConstraintPool.set(paramIDebugDraw, paramInt3);
          }
        }
        for (paramInt2 = 0; paramInt2 < paramInt5; paramInt2++)
        {
          if (((paramInt3 = (TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + paramInt2)).getRigidBodyA().getIslandTag() >= 0) && (paramInt3.getRigidBodyA().getCompanionId() >= 0)) {
            ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyA().getCompanionId())).writebackVelocity();
          }
          if ((paramInt3.getRigidBodyB().getIslandTag() >= 0) && (paramInt3.getRigidBodyB().getCompanionId() >= 0)) {
            ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyB().getCompanionId())).writebackVelocity();
          }
          paramInt3.solveConstraint(paramContactSolverInfo.timeStep);
          if ((paramInt3.getRigidBodyA().getIslandTag() >= 0) && (paramInt3.getRigidBodyA().getCompanionId() >= 0)) {
            ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyA().getCompanionId())).readVelocity();
          }
          if ((paramInt3.getRigidBodyB().getIslandTag() >= 0) && (paramInt3.getRigidBodyB().getCompanionId() >= 0)) {
            ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyB().getCompanionId())).readVelocity();
          }
        }
        paramInt3 = this.tmpSolverConstraintPool.size();
        for (paramInt2 = 0; paramInt2 < paramInt3; paramInt2++)
        {
          paramIDebugDraw = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(this.orderTmpConstraintPool.get(paramInt2));
          resolveSingleCollisionCombinedCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdB), paramIDebugDraw, paramContactSolverInfo);
        }
        paramInt3 = this.tmpSolverFrictionConstraintPool.size();
        for (paramInt2 = 0; paramInt2 < paramInt3; paramInt2++)
        {
          paramIDebugDraw = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(this.orderFrictionConstraintPool.get(paramInt2));
          float f = ((SolverConstraint)this.tmpSolverConstraintPool.getQuick(paramIDebugDraw.frictionIndex)).appliedImpulse + ((SolverConstraint)this.tmpSolverConstraintPool.getQuick(paramIDebugDraw.frictionIndex)).appliedPushImpulse;
          resolveSingleFrictionCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdB), paramIDebugDraw, paramContactSolverInfo, f);
        }
      }
      if (paramContactSolverInfo.splitImpulse) {
        for (paramObjectArrayList2 = 0; paramObjectArrayList2 < paramContactSolverInfo.numIterations; paramObjectArrayList2++)
        {
          paramInt2 = this.tmpSolverConstraintPool.size();
          for (paramInt3 = 0; paramInt3 < paramInt2; paramInt3++)
          {
            paramIDebugDraw = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(this.orderTmpConstraintPool.get(paramInt3));
            resolveSplitPenetrationImpulseCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdB), paramIDebugDraw, paramContactSolverInfo);
          }
        }
      }
      return 0.0F;
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  public float solveGroupCacheFriendly(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw)
  {
    solveGroupCacheFriendlySetup(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, paramObjectArrayList3, paramInt4, paramInt5, paramContactSolverInfo, paramIDebugDraw);
    solveGroupCacheFriendlyIterations(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, paramObjectArrayList3, paramInt4, paramInt5, paramContactSolverInfo, paramIDebugDraw);
    paramObjectArrayList1 = this.tmpSolverConstraintPool.size();
    for (paramInt1 = 0; paramInt1 < paramObjectArrayList1; paramInt1++)
    {
      paramInt2 = (ManifoldPoint)(paramObjectArrayList2 = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(paramInt1)).originalContactPoint;
      assert (paramInt2 != null);
      paramInt2.appliedImpulse = paramObjectArrayList2.appliedImpulse;
      paramInt2.appliedImpulseLateral1 = ((SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(paramObjectArrayList2.frictionIndex)).appliedImpulse;
      paramInt2.appliedImpulseLateral1 = ((SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(paramObjectArrayList2.frictionIndex + 1)).appliedImpulse;
    }
    if (paramContactSolverInfo.splitImpulse) {
      for (paramInt1 = 0; paramInt1 < this.tmpSolverBodyPool.size(); paramInt1++)
      {
        ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt1)).writebackVelocity(paramContactSolverInfo.timeStep);
        this.bodiesPool.release(this.tmpSolverBodyPool.getQuick(paramInt1));
      }
    } else {
      for (paramInt1 = 0; paramInt1 < this.tmpSolverBodyPool.size(); paramInt1++)
      {
        ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt1)).writebackVelocity();
        this.bodiesPool.release(this.tmpSolverBodyPool.getQuick(paramInt1));
      }
    }
    this.tmpSolverBodyPool.clear();
    for (paramInt1 = 0; paramInt1 < this.tmpSolverConstraintPool.size(); paramInt1++) {
      this.constraintsPool.release(this.tmpSolverConstraintPool.getQuick(paramInt1));
    }
    this.tmpSolverConstraintPool.clear();
    for (paramInt1 = 0; paramInt1 < this.tmpSolverFrictionConstraintPool.size(); paramInt1++) {
      this.constraintsPool.release(this.tmpSolverFrictionConstraintPool.getQuick(paramInt1));
    }
    this.tmpSolverFrictionConstraintPool.clear();
    return 0.0F;
  }
  
  public float solveGroup(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw, Dispatcher paramDispatcher)
  {
    BulletStats.pushProfile("solveGroup");
    try
    {
      if ((paramContactSolverInfo.solverMode & 0x8) != 0)
      {
        assert (paramObjectArrayList1 != null);
        assert (paramInt1 != 0);
        paramObjectArrayList1 = solveGroupCacheFriendly(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, paramObjectArrayList3, paramInt4, paramInt5, paramContactSolverInfo, paramIDebugDraw);
        return paramObjectArrayList1;
      }
      paramObjectArrayList1 = new ContactSolverInfo(paramContactSolverInfo);
      paramInt1 = paramContactSolverInfo.numIterations;
      paramDispatcher = 0;
      int k;
      int m;
      for (int i = 0; i < paramInt3; i = (short)(i + 1))
      {
        PersistentManifold localPersistentManifold1 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + i);
        prepareConstraints(localPersistentManifold1, paramObjectArrayList1, paramIDebugDraw);
        for (k = 0; k < ((PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + i)).getNumContacts(); k = (short)(k + 1))
        {
          this.gOrder[(paramDispatcher << 1)] = i;
          this.gOrder[((paramDispatcher << 1) + 1)] = k;
          paramDispatcher += 2;
          if (paramDispatcher >= this.gOrder.length)
          {
            m = this.gOrder.length;
            this.gOrder = Arrays.copyOf(this.gOrder, m << 1);
          }
        }
      }
      for (i = 0; i < paramInt5; i++) {
        ((TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + i)).buildJacobian();
      }
      for (i = 0; i < paramInt1; i++)
      {
        if (((paramContactSolverInfo.solverMode & 0x1) != 0) && ((i & 0x7) == 0)) {
          for (Dispatcher localDispatcher = 0; localDispatcher < paramDispatcher; localDispatcher += 2)
          {
            k = this.gOrder[localDispatcher];
            m = this.gOrder[(localDispatcher + 1)];
            paramInt3 = randInt2(localDispatcher / 2 + 1);
            this.gOrder[localDispatcher] = this.gOrder[paramInt3];
            this.gOrder[(localDispatcher + 1)] = this.gOrder[(paramInt3 + 1)];
            this.gOrder[paramInt3] = k;
            this.gOrder[(paramInt3 + 1)] = m;
          }
        }
        for (int j = 0; j < paramInt5; j++) {
          ((TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + j)).solveConstraint(paramObjectArrayList1.timeStep);
        }
        PersistentManifold localPersistentManifold2;
        for (j = 0; j < paramDispatcher; j += 2)
        {
          localPersistentManifold2 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + this.gOrder[j]);
          solve((RigidBody)localPersistentManifold2.getBody0(), (RigidBody)localPersistentManifold2.getBody1(), localPersistentManifold2.getContactPoint(this.gOrder[(j + 1)]), paramObjectArrayList1, i, paramIDebugDraw);
        }
        for (j = 0; j < paramDispatcher; j += 2)
        {
          localPersistentManifold2 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + this.gOrder[j]);
          solveFriction((RigidBody)localPersistentManifold2.getBody0(), (RigidBody)localPersistentManifold2.getBody1(), localPersistentManifold2.getContactPoint(this.gOrder[(j + 1)]), paramObjectArrayList1, i, paramIDebugDraw);
        }
      }
      return 0.0F;
    }
    finally
    {
      BulletStats.popProfile();
    }
  }
  
  protected void prepareConstraints(PersistentManifold paramPersistentManifold, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw)
  {
    paramIDebugDraw = (RigidBody)paramPersistentManifold.getBody0();
    RigidBody localRigidBody = (RigidBody)paramPersistentManifold.getBody1();
    int i = paramPersistentManifold.getNumContacts();
    BulletStats.gTotalContactPoints += i;
    Vector3f localVector3f1 = this.field_428.tmpVecB;
    Matrix3f localMatrix3f1 = this.field_428.tmpMat3B;
    Vector3f localVector3f2 = this.field_428.pos1B;
    Vector3f localVector3f3 = this.field_428.pos2B;
    Vector3f localVector3f4 = this.field_428.rel_pos1B;
    Vector3f localVector3f5 = this.field_428.rel_pos2B;
    Vector3f localVector3f6 = this.field_428.vel1B;
    Vector3f localVector3f7 = this.field_428.vel2B;
    Vector3f localVector3f8 = this.field_428.velB;
    Vector3f localVector3f9 = this.field_428.totalImpulseB;
    Vector3f localVector3f10 = this.field_428.torqueAxis0B;
    Vector3f localVector3f11 = this.field_428.torqueAxis1B;
    Vector3f localVector3f12 = this.field_428.ftorqueAxis0B;
    Vector3f localVector3f13 = this.field_428.ftorqueAxis1B;
    for (int j = 0; j < i; j++)
    {
      ManifoldPoint localManifoldPoint;
      if ((localManifoldPoint = paramPersistentManifold.getContactPoint(j)).getDistance() <= 0.0F)
      {
        localManifoldPoint.getPositionWorldOnA(localVector3f2);
        localManifoldPoint.getPositionWorldOnB(localVector3f3);
        localVector3f4.sub(localVector3f2, paramIDebugDraw.getCenterOfMassPosition(localVector3f1));
        localVector3f5.sub(localVector3f3, localRigidBody.getCenterOfMassPosition(localVector3f1));
        Matrix3f localMatrix3f2;
        (localMatrix3f2 = paramIDebugDraw.getCenterOfMassTransform(this.field_428.com0).basis).transpose();
        Object localObject;
        (localObject = localRigidBody.getCenterOfMassTransform(this.field_428.com1).basis).transpose();
        JacobianEntry localJacobianEntry;
        (localJacobianEntry = (JacobianEntry)this.jacobiansPool.get()).init(localMatrix3f2, (Matrix3f)localObject, localVector3f4, localVector3f5, localManifoldPoint.normalWorldOnB, paramIDebugDraw.getInvInertiaDiagLocal(this.field_428.in0), paramIDebugDraw.getInvMass(), localRigidBody.getInvInertiaDiagLocal(this.field_428.in1), localRigidBody.getInvMass());
        float f1 = localJacobianEntry.getDiagonal();
        this.jacobiansPool.release(localJacobianEntry);
        if ((localObject = (ConstraintPersistentData)localManifoldPoint.userPersistentData) != null)
        {
          localObject.persistentLifeTime += 1;
          if (((ConstraintPersistentData)localObject).persistentLifeTime != localManifoldPoint.getLifeTime())
          {
            ((ConstraintPersistentData)localObject).reset();
            ((ConstraintPersistentData)localObject).persistentLifeTime = localManifoldPoint.getLifeTime();
          }
        }
        else
        {
          localObject = new ConstraintPersistentData();
          this.totalCpd += 1;
          localManifoldPoint.userPersistentData = localObject;
          ((ConstraintPersistentData)localObject).persistentLifeTime = localManifoldPoint.getLifeTime();
        }
        assert (localObject != null);
        ((ConstraintPersistentData)localObject).jacDiagABInv = (1.0F / f1);
        ((ConstraintPersistentData)localObject).frictionSolverFunc = this.frictionDispatch[paramIDebugDraw.frictionSolverType][localRigidBody.frictionSolverType];
        ((ConstraintPersistentData)localObject).contactSolverFunc = this.contactDispatch[paramIDebugDraw.contactSolverType][localRigidBody.contactSolverType];
        paramIDebugDraw.getVelocityInLocalPoint(localVector3f4, localVector3f6);
        localRigidBody.getVelocityInLocalPoint(localVector3f5, localVector3f7);
        localVector3f8.sub(localVector3f6, localVector3f7);
        f1 = localManifoldPoint.normalWorldOnB.dot(localVector3f8);
        float f2 = localManifoldPoint.combinedRestitution;
        ((ConstraintPersistentData)localObject).penetration = localManifoldPoint.getDistance();
        ((ConstraintPersistentData)localObject).friction = localManifoldPoint.combinedFriction;
        ((ConstraintPersistentData)localObject).restitution = restitutionCurve(f1, f2);
        if (((ConstraintPersistentData)localObject).restitution <= 0.0F) {
          ((ConstraintPersistentData)localObject).restitution = 0.0F;
        }
        f1 = -((ConstraintPersistentData)localObject).penetration / paramContactSolverInfo.timeStep;
        if (((ConstraintPersistentData)localObject).restitution > f1) {
          ((ConstraintPersistentData)localObject).penetration = 0.0F;
        }
        f1 = paramContactSolverInfo.damping;
        if ((paramContactSolverInfo.solverMode & 0x4) != 0) {
          localObject.appliedImpulse *= f1;
        } else {
          ((ConstraintPersistentData)localObject).appliedImpulse = 0.0F;
        }
        ((ConstraintPersistentData)localObject).prevAppliedImpulse = ((ConstraintPersistentData)localObject).appliedImpulse;
        TransformUtil.planeSpace1(localManifoldPoint.normalWorldOnB, ((ConstraintPersistentData)localObject).frictionWorldTangential0, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
        ((ConstraintPersistentData)localObject).accumulatedTangentImpulse0 = 0.0F;
        ((ConstraintPersistentData)localObject).accumulatedTangentImpulse1 = 0.0F;
        f2 = paramIDebugDraw.computeImpulseDenominator(localVector3f2, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
        float f3 = localRigidBody.computeImpulseDenominator(localVector3f3, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
        f2 = f1 / (f2 + f3);
        ((ConstraintPersistentData)localObject).jacDiagABInvTangent0 = f2;
        f2 = paramIDebugDraw.computeImpulseDenominator(localVector3f2, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
        f3 = localRigidBody.computeImpulseDenominator(localVector3f3, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
        f2 = f1 / (f2 + f3);
        ((ConstraintPersistentData)localObject).jacDiagABInvTangent1 = f2;
        localVector3f9.scale(((ConstraintPersistentData)localObject).appliedImpulse, localManifoldPoint.normalWorldOnB);
        localVector3f10.cross(localVector3f4, localManifoldPoint.normalWorldOnB);
        ((ConstraintPersistentData)localObject).angularComponentA.set(localVector3f10);
        paramIDebugDraw.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).angularComponentA);
        localVector3f11.cross(localVector3f5, localManifoldPoint.normalWorldOnB);
        ((ConstraintPersistentData)localObject).angularComponentB.set(localVector3f11);
        localRigidBody.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).angularComponentB);
        localVector3f12.cross(localVector3f4, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
        ((ConstraintPersistentData)localObject).frictionAngularComponent0A.set(localVector3f12);
        paramIDebugDraw.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent0A);
        localVector3f13.cross(localVector3f4, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
        ((ConstraintPersistentData)localObject).frictionAngularComponent1A.set(localVector3f13);
        paramIDebugDraw.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent1A);
        localVector3f12.cross(localVector3f5, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
        ((ConstraintPersistentData)localObject).frictionAngularComponent0B.set(localVector3f12);
        localRigidBody.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent0B);
        localVector3f13.cross(localVector3f5, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
        ((ConstraintPersistentData)localObject).frictionAngularComponent1B.set(localVector3f13);
        localRigidBody.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent1B);
        paramIDebugDraw.applyImpulse(localVector3f9, localVector3f4);
        localVector3f1.negate(localVector3f9);
        localRigidBody.applyImpulse(localVector3f1, localVector3f5);
      }
    }
  }
  
  public float solveCombinedContactFriction(RigidBody paramRigidBody1, RigidBody paramRigidBody2, ManifoldPoint paramManifoldPoint, ContactSolverInfo paramContactSolverInfo, int paramInt, IDebugDraw paramIDebugDraw)
  {
    paramInt = 0.0F;
    if (paramManifoldPoint.getDistance() <= 0.0F)
    {
      paramRigidBody1 = ContactConstraint.resolveSingleCollisionCombined(paramRigidBody1, paramRigidBody2, paramManifoldPoint, paramContactSolverInfo);
      if (0.0F < paramRigidBody1) {
        paramInt = paramRigidBody1;
      }
    }
    return paramInt;
  }
  
  protected float solve(RigidBody paramRigidBody1, RigidBody paramRigidBody2, ManifoldPoint paramManifoldPoint, ContactSolverInfo paramContactSolverInfo, int paramInt, IDebugDraw paramIDebugDraw)
  {
    paramInt = 0.0F;
    if (paramManifoldPoint.getDistance() <= 0.0F)
    {
      paramRigidBody1 = ((ConstraintPersistentData)paramManifoldPoint.userPersistentData).contactSolverFunc.resolveContact(paramRigidBody1, paramRigidBody2, paramManifoldPoint, paramContactSolverInfo);
      if (0.0F < paramRigidBody1) {
        paramInt = paramRigidBody1;
      }
    }
    return paramInt;
  }
  
  protected float solveFriction(RigidBody paramRigidBody1, RigidBody paramRigidBody2, ManifoldPoint paramManifoldPoint, ContactSolverInfo paramContactSolverInfo, int paramInt, IDebugDraw paramIDebugDraw)
  {
    if (paramManifoldPoint.getDistance() <= 0.0F) {
      ((ConstraintPersistentData)paramManifoldPoint.userPersistentData).frictionSolverFunc.resolveContact(paramRigidBody1, paramRigidBody2, paramManifoldPoint, paramContactSolverInfo);
    }
    return 0.0F;
  }
  
  public void reset()
  {
    this.btSeed2 = 0L;
  }
  
  public void setContactSolverFunc(ContactSolverFunc paramContactSolverFunc, int paramInt1, int paramInt2)
  {
    this.contactDispatch[paramInt1][paramInt2] = paramContactSolverFunc;
  }
  
  public void setFrictionSolverFunc(ContactSolverFunc paramContactSolverFunc, int paramInt1, int paramInt2)
  {
    this.frictionDispatch[paramInt1][paramInt2] = paramContactSolverFunc;
  }
  
  public void setRandSeed(long paramLong)
  {
    this.btSeed2 = paramLong;
  }
  
  public long getRandSeed()
  {
    return this.btSeed2;
  }
}


/* Location:           C:\Users\Raul\Desktop\StarMadeDec\StarMadeR.zip
 * Qualified Name:     org.schema.game.common.data.physics.SequentialImpulseConstraintSolverExt
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */