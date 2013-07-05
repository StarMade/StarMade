/*      */ package org.schema.game.common.data.physics;
/*      */ 
/*      */ import com.bulletphysics.BulletGlobals;
/*      */ import com.bulletphysics.BulletStats;
/*      */ import com.bulletphysics.collision.broadphase.Dispatcher;
/*      */ import com.bulletphysics.collision.dispatch.CollisionObject;
/*      */ import com.bulletphysics.collision.narrowphase.ManifoldPoint;
/*      */ import com.bulletphysics.collision.narrowphase.PersistentManifold;
/*      */ import com.bulletphysics.dynamics.RigidBody;
/*      */ import com.bulletphysics.dynamics.constraintsolver.ConstraintPersistentData;
/*      */ import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
/*      */ import com.bulletphysics.dynamics.constraintsolver.ContactConstraint;
/*      */ import com.bulletphysics.dynamics.constraintsolver.ContactSolverFunc;
/*      */ import com.bulletphysics.dynamics.constraintsolver.ContactSolverInfo;
/*      */ import com.bulletphysics.dynamics.constraintsolver.JacobianEntry;
/*      */ import com.bulletphysics.dynamics.constraintsolver.SolverBody;
/*      */ import com.bulletphysics.dynamics.constraintsolver.SolverConstraint;
/*      */ import com.bulletphysics.dynamics.constraintsolver.SolverConstraintType;
/*      */ import com.bulletphysics.dynamics.constraintsolver.TypedConstraint;
/*      */ import com.bulletphysics.linearmath.IDebugDraw;
/*      */ import com.bulletphysics.linearmath.MiscUtil;
/*      */ import com.bulletphysics.linearmath.Transform;
/*      */ import com.bulletphysics.linearmath.TransformUtil;
/*      */ import com.bulletphysics.util.IntArrayList;
/*      */ import com.bulletphysics.util.ObjectArrayList;
/*      */ import com.bulletphysics.util.ObjectPool;
/*      */ import java.util.Arrays;
/*      */ import javax.vecmath.Matrix3f;
/*      */ import javax.vecmath.Vector3f;
/*      */ 
/*      */ public class SequentialImpulseConstraintSolverExt extends ConstraintSolver
/*      */ {
/*      */   private static final int MAX_CONTACT_SOLVER_TYPES = 4;
/*      */   private static final int SEQUENTIAL_IMPULSE_MAX_SOLVER_POINTS = 128;
/*   54 */   private int[] gOrder = new int[256];
/*      */ 
/*   56 */   private int totalCpd = 0;
/*      */ 
/*   61 */   private final ObjectPool bodiesPool = ObjectPool.get(SolverBody.class);
/*   62 */   private final ObjectPool constraintsPool = ObjectPool.get(SolverConstraint.class);
/*   63 */   private final ObjectPool jacobiansPool = ObjectPool.get(JacobianEntry.class);
/*      */ 
/*   65 */   private final ObjectArrayList tmpSolverBodyPool = new ObjectArrayList();
/*   66 */   private final ObjectArrayList tmpSolverConstraintPool = new ObjectArrayList();
/*   67 */   private final ObjectArrayList tmpSolverFrictionConstraintPool = new ObjectArrayList();
/*   68 */   private final IntArrayList orderTmpConstraintPool = new IntArrayList();
/*   69 */   private final IntArrayList orderFrictionConstraintPool = new IntArrayList();
/*      */ 
/*   71 */   protected final ContactSolverFunc[][] contactDispatch = new ContactSolverFunc[4][4];
/*   72 */   protected final ContactSolverFunc[][] frictionDispatch = new ContactSolverFunc[4][4];
/*      */ 
/*   75 */   protected long btSeed2 = 0L;
/*      */   private final SequentialImpulseContraintSolverExtVariableSet v;
/*      */ 
/*      */   public SequentialImpulseConstraintSolverExt()
/*      */   {
/*   81 */     this.v = new SequentialImpulseContraintSolverExtVariableSet();
/*      */ 
/*   83 */     BulletGlobals.setContactDestroyedCallback(new SequentialImpulseConstraintSolverExt.1(this));
/*      */ 
/*   96 */     for (int i = 0; i < 4; i++)
/*   97 */       for (int j = 0; j < 4; j++) {
/*   98 */         this.contactDispatch[i][j] = ContactConstraint.resolveSingleCollision;
/*   99 */         this.frictionDispatch[i][j] = ContactConstraint.resolveSingleFriction;
/*      */       }
/*      */   }
/*      */ 
/*      */   public long rand2()
/*      */   {
/*  105 */     this.btSeed2 = (1664525L * this.btSeed2 + 1013904223L);
/*  106 */     return this.btSeed2;
/*      */   }
/*      */ 
/*      */   public int randInt2(int paramInt)
/*      */   {
/*  112 */     long l1 = paramInt;
/*  113 */     long l2 = rand2();
/*      */ 
/*  117 */     if (l1 <= 65536L) {
/*  118 */       l2 ^= l2 >>> 16;
/*  119 */       if (l1 <= 256L) {
/*  120 */         l2 ^= l2 >>> 8;
/*  121 */         if (l1 <= 16L) {
/*  122 */           l2 ^= l2 >>> 4;
/*  123 */           if (l1 <= 4L) {
/*  124 */             l2 ^= l2 >>> 2;
/*  125 */             if (l1 <= 2L) {
/*  126 */               l2 ^= l2 >>> 1;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  134 */     return (int)Math.abs(l2 % l1);
/*      */   }
/*      */ 
/*      */   private void initSolverBody(SolverBody paramSolverBody, CollisionObject paramCollisionObject)
/*      */   {
/*      */     RigidBody localRigidBody;
/*  138 */     if ((
/*  138 */       localRigidBody = RigidBody.upcast(paramCollisionObject)) != null)
/*      */     {
/*  139 */       localRigidBody.getAngularVelocity(paramSolverBody.angularVelocity);
/*  140 */       paramSolverBody.centerOfMassPosition.set(paramCollisionObject.getWorldTransform(this.v.centerOfMassTrans).origin);
/*  141 */       paramSolverBody.friction = paramCollisionObject.getFriction();
/*  142 */       paramSolverBody.invMass = localRigidBody.getInvMass();
/*  143 */       localRigidBody.getLinearVelocity(paramSolverBody.linearVelocity);
/*  144 */       paramSolverBody.originalBody = localRigidBody;
/*  145 */       paramSolverBody.angularFactor = localRigidBody.getAngularFactor();
/*      */     }
/*      */     else {
/*  148 */       paramSolverBody.angularVelocity.set(0.0F, 0.0F, 0.0F);
/*  149 */       paramSolverBody.centerOfMassPosition.set(paramCollisionObject.getWorldTransform(this.v.centerOfMassTrans).origin);
/*  150 */       paramSolverBody.friction = paramCollisionObject.getFriction();
/*  151 */       paramSolverBody.invMass = 0.0F;
/*  152 */       paramSolverBody.linearVelocity.set(0.0F, 0.0F, 0.0F);
/*  153 */       paramSolverBody.originalBody = null;
/*  154 */       paramSolverBody.angularFactor = 1.0F;
/*      */     }
/*      */ 
/*  157 */     paramSolverBody.pushVelocity.set(0.0F, 0.0F, 0.0F);
/*  158 */     paramSolverBody.turnVelocity.set(0.0F, 0.0F, 0.0F);
/*      */   }
/*      */ 
/*      */   private float restitutionCurve(float paramFloat1, float paramFloat2)
/*      */   {
/*  163 */     return paramFloat2 * -paramFloat1;
/*      */   }
/*      */ 
/*      */   private void resolveSplitPenetrationImpulseCacheFriendly(SolverBody paramSolverBody1, SolverBody paramSolverBody2, SolverConstraint paramSolverConstraint, ContactSolverInfo paramContactSolverInfo)
/*      */   {
/*  172 */     if (paramSolverConstraint.penetration < paramContactSolverInfo.splitImpulsePenetrationThreshold) {
/*  173 */       BulletStats.gNumSplitImpulseRecoveries += 1;
/*      */ 
/*  183 */       float f1 = paramSolverConstraint.contactNormal.dot(paramSolverBody1.pushVelocity) + paramSolverConstraint.relpos1CrossNormal.dot(paramSolverBody1.turnVelocity);
/*  184 */       float f2 = paramSolverConstraint.contactNormal.dot(paramSolverBody2.pushVelocity) + paramSolverConstraint.relpos2CrossNormal.dot(paramSolverBody2.turnVelocity);
/*      */ 
/*  186 */       f1 -= f2;
/*      */ 
/*  188 */       paramContactSolverInfo = -paramSolverConstraint.penetration * paramContactSolverInfo.erp2 / paramContactSolverInfo.timeStep;
/*      */ 
/*  191 */       f1 = paramSolverConstraint.restitution - f1;
/*      */ 
/*  193 */       paramContactSolverInfo *= paramSolverConstraint.jacDiagABInv;
/*  194 */       f1 *= paramSolverConstraint.jacDiagABInv;
/*  195 */       paramContactSolverInfo += f1;
/*      */ 
/*  199 */       paramContactSolverInfo = (
/*  199 */         f1 = paramSolverConstraint.appliedPushImpulse) + 
/*  199 */         paramContactSolverInfo;
/*  200 */       paramSolverConstraint.appliedPushImpulse = (0.0F > paramContactSolverInfo ? 0.0F : paramContactSolverInfo);
/*      */ 
/*  202 */       paramContactSolverInfo = paramSolverConstraint.appliedPushImpulse - f1;
/*      */       Vector3f localVector3f;
/*  204 */       (
/*  206 */         localVector3f = this.v.tmp)
/*  206 */         .scale(paramSolverBody1.invMass, paramSolverConstraint.contactNormal);
/*  207 */       paramSolverBody1.internalApplyPushImpulse(localVector3f, paramSolverConstraint.angularComponentA, paramContactSolverInfo);
/*      */ 
/*  209 */       localVector3f.scale(paramSolverBody2.invMass, paramSolverConstraint.contactNormal);
/*  210 */       paramSolverBody2.internalApplyPushImpulse(localVector3f, paramSolverConstraint.angularComponentB, -paramContactSolverInfo);
/*      */     }
/*      */   }
/*      */ 
/*      */   private float resolveSingleCollisionCombinedCacheFriendly(SolverBody paramSolverBody1, SolverBody paramSolverBody2, SolverConstraint paramSolverConstraint, ContactSolverInfo paramContactSolverInfo)
/*      */   {
/*  241 */     float f1 = paramSolverConstraint.contactNormal.dot(paramSolverBody1.linearVelocity) + paramSolverConstraint.relpos1CrossNormal.dot(paramSolverBody1.angularVelocity);
/*  242 */     float f2 = paramSolverConstraint.contactNormal.dot(paramSolverBody2.linearVelocity) + paramSolverConstraint.relpos2CrossNormal.dot(paramSolverBody2.angularVelocity);
/*      */ 
/*  244 */     f1 -= f2;
/*      */ 
/*  246 */     f2 = 0.0F;
/*  247 */     if ((!paramContactSolverInfo.splitImpulse) || (paramSolverConstraint.penetration > paramContactSolverInfo.splitImpulsePenetrationThreshold)) {
/*  248 */       f2 = -paramSolverConstraint.penetration * paramContactSolverInfo.erp / paramContactSolverInfo.timeStep;
/*      */     }
/*      */ 
/*  251 */     paramContactSolverInfo = paramSolverConstraint.restitution - f1;
/*      */ 
/*  253 */     f1 = f2 * paramSolverConstraint.jacDiagABInv;
/*  254 */     paramContactSolverInfo *= paramSolverConstraint.jacDiagABInv;
/*  255 */     paramContactSolverInfo = f1 + paramContactSolverInfo;
/*      */ 
/*  260 */     paramContactSolverInfo = (
/*  260 */       f1 = paramSolverConstraint.appliedImpulse) + 
/*  260 */       paramContactSolverInfo;
/*  261 */     paramSolverConstraint.appliedImpulse = (0.0F > paramContactSolverInfo ? 0.0F : paramContactSolverInfo);
/*      */ 
/*  263 */     paramContactSolverInfo = paramSolverConstraint.appliedImpulse - f1;
/*      */     Vector3f localVector3f;
/*  265 */     (
/*  267 */       localVector3f = this.v.tmp2)
/*  267 */       .scale(paramSolverBody1.invMass, paramSolverConstraint.contactNormal);
/*  268 */     paramSolverBody1.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentA, paramContactSolverInfo);
/*      */ 
/*  273 */     localVector3f.scale(paramSolverBody2.invMass, paramSolverConstraint.contactNormal);
/*  274 */     paramSolverBody2.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentB, -paramContactSolverInfo);
/*      */ 
/*  280 */     return paramContactSolverInfo;
/*      */   }
/*      */ 
/*      */   private float resolveSingleFrictionCacheFriendly(SolverBody paramSolverBody1, SolverBody paramSolverBody2, SolverConstraint paramSolverConstraint, ContactSolverInfo paramContactSolverInfo, float paramFloat)
/*      */   {
/*  289 */     paramContactSolverInfo = paramSolverConstraint.friction;
/*      */ 
/*  291 */     paramContactSolverInfo = paramFloat * paramContactSolverInfo;
/*      */ 
/*  293 */     if (paramFloat > 0.0F)
/*      */     {
/*  300 */       paramFloat = paramSolverConstraint.contactNormal.dot(paramSolverBody1.linearVelocity) + paramSolverConstraint.relpos1CrossNormal.dot(paramSolverBody1.angularVelocity);
/*  301 */       float f = paramSolverConstraint.contactNormal.dot(paramSolverBody2.linearVelocity) + paramSolverConstraint.relpos2CrossNormal.dot(paramSolverBody2.angularVelocity);
/*      */ 
/*  305 */       paramFloat = -(paramFloat - f) * 
/*  305 */         paramSolverConstraint.jacDiagABInv;
/*      */ 
/*  308 */       f = paramSolverConstraint.appliedImpulse;
/*  309 */       paramSolverConstraint.appliedImpulse = (f + paramFloat);
/*      */ 
/*  311 */       if (paramContactSolverInfo < paramSolverConstraint.appliedImpulse) {
/*  312 */         paramSolverConstraint.appliedImpulse = paramContactSolverInfo;
/*      */       }
/*  315 */       else if (paramSolverConstraint.appliedImpulse < -paramContactSolverInfo) {
/*  316 */         paramSolverConstraint.appliedImpulse = (-paramContactSolverInfo);
/*      */       }
/*      */ 
/*  319 */       paramFloat = paramSolverConstraint.appliedImpulse - f;
/*      */       Vector3f localVector3f;
/*  335 */       (
/*  337 */         localVector3f = this.v.tmp3)
/*  337 */         .scale(paramSolverBody1.invMass, paramSolverConstraint.contactNormal);
/*  338 */       paramSolverBody1.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentA, paramFloat);
/*      */ 
/*  340 */       localVector3f.scale(paramSolverBody2.invMass, paramSolverConstraint.contactNormal);
/*  341 */       paramSolverBody2.internalApplyImpulse(localVector3f, paramSolverConstraint.angularComponentB, -paramFloat);
/*      */     }
/*  343 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   protected void addFrictionConstraint(Vector3f paramVector3f1, int paramInt1, int paramInt2, int paramInt3, ManifoldPoint paramManifoldPoint, Vector3f paramVector3f2, Vector3f paramVector3f3, CollisionObject paramCollisionObject1, CollisionObject paramCollisionObject2, float paramFloat) {
/*  347 */     paramCollisionObject1 = RigidBody.upcast(paramCollisionObject1);
/*  348 */     paramCollisionObject2 = RigidBody.upcast(paramCollisionObject2);
/*      */ 
/*  350 */     SolverConstraint localSolverConstraint = (SolverConstraint)this.constraintsPool.get();
/*  351 */     this.tmpSolverFrictionConstraintPool.add(localSolverConstraint);
/*      */ 
/*  353 */     localSolverConstraint.contactNormal.set(paramVector3f1);
/*      */ 
/*  355 */     localSolverConstraint.solverBodyIdA = paramInt1;
/*  356 */     localSolverConstraint.solverBodyIdB = paramInt2;
/*  357 */     localSolverConstraint.constraintType = SolverConstraintType.SOLVER_FRICTION_1D;
/*  358 */     localSolverConstraint.frictionIndex = paramInt3;
/*      */ 
/*  360 */     localSolverConstraint.friction = paramManifoldPoint.combinedFriction;
/*  361 */     localSolverConstraint.originalContactPoint = null;
/*      */ 
/*  363 */     localSolverConstraint.appliedImpulse = 0.0F;
/*  364 */     localSolverConstraint.appliedPushImpulse = 0.0F;
/*  365 */     localSolverConstraint.penetration = 0.0F;
/*      */ 
/*  367 */     paramInt1 = this.v.ftorqueAxis1;
/*  368 */     paramInt2 = this.v.tmpMat;
/*      */ 
/*  371 */     paramInt1.cross(paramVector3f2, localSolverConstraint.contactNormal);
/*  372 */     localSolverConstraint.relpos1CrossNormal.set(paramInt1);
/*  373 */     if (paramCollisionObject1 != null) {
/*  374 */       localSolverConstraint.angularComponentA.set(paramInt1);
/*      */ 
/*  376 */       paramCollisionObject1.getInvInertiaTensorWorld(paramInt2).transform(localSolverConstraint.angularComponentA);
/*      */     }
/*      */     else {
/*  379 */       localSolverConstraint.angularComponentA.set(0.0F, 0.0F, 0.0F);
/*      */     }
/*      */ 
/*  383 */     paramInt1.cross(paramVector3f3, localSolverConstraint.contactNormal);
/*  384 */     localSolverConstraint.relpos2CrossNormal.set(paramInt1);
/*  385 */     if (paramCollisionObject2 != null) {
/*  386 */       localSolverConstraint.angularComponentB.set(paramInt1);
/*      */ 
/*  388 */       paramCollisionObject2.getInvInertiaTensorWorld(paramInt2).transform(localSolverConstraint.angularComponentB);
/*      */     }
/*      */     else {
/*  391 */       localSolverConstraint.angularComponentB.set(0.0F, 0.0F, 0.0F);
/*      */     }
/*      */ 
/*  399 */     paramInt1 = this.v.vec;
/*  400 */     paramInt2 = 0.0F;
/*  401 */     paramInt3 = 0.0F;
/*  402 */     if (paramCollisionObject1 != null) {
/*  403 */       paramInt1.cross(localSolverConstraint.angularComponentA, paramVector3f2);
/*  404 */       paramInt2 = paramCollisionObject1.getInvMass() + paramVector3f1.dot(paramInt1);
/*      */     }
/*  406 */     if (paramCollisionObject2 != null) {
/*  407 */       paramInt1.cross(localSolverConstraint.angularComponentB, paramVector3f3);
/*  408 */       paramInt3 = paramCollisionObject2.getInvMass() + paramVector3f1.dot(paramInt1);
/*      */     }
/*      */ 
/*  412 */     paramVector3f1 = paramFloat / (paramInt2 + paramInt3);
/*  413 */     localSolverConstraint.jacDiagABInv = paramVector3f1;
/*      */   }
/*      */ 
/*      */   public float solveGroupCacheFriendlySetup(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw) {
/*  417 */     BulletStats.pushProfile("solveGroupCacheFriendlySetup");
/*      */ 
/*  420 */     if (paramInt5 + paramInt3 == 0)
/*      */     {
/*  422 */       paramObjectArrayList1 = 0.0F;
/*      */ 
/*  800 */       BulletStats.popProfile(); return 0.0F;
/*      */     }
/*      */     try
/*      */     {
/*  424 */       paramObjectArrayList1 = null;
/*  425 */       paramInt1 = null; paramIDebugDraw = null;
/*      */ 
/*  450 */       Transform localTransform = this.v.tmpTrans;
/*      */ 
/*  494 */       Vector3f localVector3f1 = this.v.rel_pos1A;
/*  495 */       Vector3f localVector3f2 = this.v.rel_pos2A;
/*      */ 
/*  497 */       Vector3f localVector3f3 = this.v.pos1A;
/*  498 */       Vector3f localVector3f4 = this.v.pos2A;
/*  499 */       Vector3f localVector3f5 = this.v.velA;
/*  500 */       Vector3f localVector3f6 = this.v.torqueAxis0A;
/*  501 */       Vector3f localVector3f7 = this.v.torqueAxis1A;
/*  502 */       Vector3f localVector3f8 = this.v.vel1A;
/*  503 */       Vector3f localVector3f9 = this.v.vel2A;
/*      */ 
/*  506 */       Vector3f localVector3f10 = this.v.vecA;
/*      */ 
/*  508 */       Matrix3f localMatrix3f = this.v.tmpMatA;
/*      */ 
/*  510 */       for (int i = 0; i < paramInt3; i++)
/*      */       {
/*  512 */         paramInt1 = (CollisionObject)(
/*  512 */           paramObjectArrayList1 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + i))
/*  512 */           .getBody0();
/*  513 */         paramIDebugDraw = (CollisionObject)paramObjectArrayList1.getBody1();
/*      */ 
/*  515 */         int m = -1;
/*  516 */         int n = -1;
/*      */ 
/*  518 */         if (paramObjectArrayList1.getNumContacts() != 0)
/*      */         {
/*      */           SolverBody localSolverBody;
/*  520 */           if (paramInt1.getIslandTag() >= 0) {
/*  521 */             if (paramInt1.getCompanionId() >= 0)
/*      */             {
/*  523 */               m = paramInt1.getCompanionId();
/*      */             }
/*      */             else {
/*  526 */               m = this.tmpSolverBodyPool.size();
/*  527 */               localSolverBody = (SolverBody)this.bodiesPool.get();
/*  528 */               this.tmpSolverBodyPool.add(localSolverBody);
/*  529 */               initSolverBody(localSolverBody, paramInt1);
/*  530 */               paramInt1.setCompanionId(m);
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  535 */             m = this.tmpSolverBodyPool.size();
/*  536 */             localSolverBody = (SolverBody)this.bodiesPool.get();
/*  537 */             this.tmpSolverBodyPool.add(localSolverBody);
/*  538 */             initSolverBody(localSolverBody, paramInt1);
/*      */           }
/*      */ 
/*  541 */           if (paramIDebugDraw.getIslandTag() >= 0) {
/*  542 */             if (paramIDebugDraw.getCompanionId() >= 0) {
/*  543 */               n = paramIDebugDraw.getCompanionId();
/*      */             }
/*      */             else {
/*  546 */               n = this.tmpSolverBodyPool.size();
/*  547 */               localSolverBody = (SolverBody)this.bodiesPool.get();
/*  548 */               this.tmpSolverBodyPool.add(localSolverBody);
/*  549 */               initSolverBody(localSolverBody, paramIDebugDraw);
/*  550 */               paramIDebugDraw.setCompanionId(n);
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  555 */             n = this.tmpSolverBodyPool.size();
/*  556 */             localSolverBody = (SolverBody)this.bodiesPool.get();
/*  557 */             this.tmpSolverBodyPool.add(localSolverBody);
/*  558 */             initSolverBody(localSolverBody, paramIDebugDraw);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  564 */         for (int i1 = 0; i1 < paramObjectArrayList1.getNumContacts(); i1++)
/*      */         {
/*      */           ManifoldPoint localManifoldPoint;
/*  568 */           if ((
/*  568 */             localManifoldPoint = paramObjectArrayList1.getContactPoint(i1))
/*  568 */             .getDistance() <= 0.0F) {
/*  569 */             localManifoldPoint.getPositionWorldOnA(localVector3f3);
/*  570 */             localManifoldPoint.getPositionWorldOnB(localVector3f4);
/*      */ 
/*  572 */             localVector3f1.sub(localVector3f3, paramInt1.getWorldTransform(localTransform).origin);
/*  573 */             localVector3f2.sub(localVector3f4, paramIDebugDraw.getWorldTransform(localTransform).origin);
/*      */ 
/*  575 */             int i2 = this.tmpSolverConstraintPool.size();
/*      */ 
/*  581 */             SolverConstraint localSolverConstraint2 = (SolverConstraint)this.constraintsPool.get();
/*  582 */             this.tmpSolverConstraintPool.add(localSolverConstraint2);
/*  583 */             RigidBody localRigidBody1 = RigidBody.upcast(paramInt1);
/*  584 */             RigidBody localRigidBody2 = RigidBody.upcast(paramIDebugDraw);
/*      */ 
/*  586 */             localSolverConstraint2.solverBodyIdA = m;
/*  587 */             localSolverConstraint2.solverBodyIdB = n;
/*  588 */             localSolverConstraint2.constraintType = SolverConstraintType.SOLVER_CONTACT_1D;
/*      */ 
/*  590 */             localSolverConstraint2.originalContactPoint = localManifoldPoint;
/*      */ 
/*  593 */             localVector3f6.cross(localVector3f1, localManifoldPoint.normalWorldOnB);
/*      */ 
/*  595 */             if (localRigidBody1 != null) {
/*  596 */               localSolverConstraint2.angularComponentA.set(localVector3f6);
/*  597 */               localRigidBody1.getInvInertiaTensorWorld(localMatrix3f).transform(localSolverConstraint2.angularComponentA);
/*      */             }
/*      */             else
/*      */             {
/*  602 */               localSolverConstraint2.angularComponentA.set(0.0F, 0.0F, 0.0F);
/*      */             }
/*      */ 
/*  605 */             localVector3f7.cross(localVector3f2, localManifoldPoint.normalWorldOnB);
/*      */ 
/*  607 */             if (localRigidBody2 != null) {
/*  608 */               localSolverConstraint2.angularComponentB.set(localVector3f7);
/*  609 */               localRigidBody2.getInvInertiaTensorWorld(localMatrix3f).transform(localSolverConstraint2.angularComponentB);
/*      */             }
/*      */             else
/*      */             {
/*  614 */               localSolverConstraint2.angularComponentB.set(0.0F, 0.0F, 0.0F);
/*      */             }
/*      */ 
/*  622 */             float f2 = 0.0F;
/*  623 */             float f3 = 0.0F;
/*  624 */             if (localRigidBody1 != null) {
/*  625 */               localVector3f10.cross(localSolverConstraint2.angularComponentA, localVector3f1);
/*      */ 
/*  627 */               f2 = localRigidBody1.getInvMass() + localManifoldPoint.normalWorldOnB.dot(localVector3f10);
/*      */             }
/*      */ 
/*  630 */             if (localRigidBody2 != null) {
/*  631 */               localVector3f10.cross(localSolverConstraint2.angularComponentB, localVector3f2);
/*  632 */               f3 = localRigidBody2.getInvMass() + localManifoldPoint.normalWorldOnB.dot(localVector3f10);
/*      */             }
/*      */ 
/*  637 */             float f1 = 1.0F / (f2 + f3);
/*  638 */             localSolverConstraint2.jacDiagABInv = f1;
/*      */ 
/*  641 */             localSolverConstraint2.contactNormal.set(localManifoldPoint.normalWorldOnB);
/*  642 */             localSolverConstraint2.relpos1CrossNormal.cross(localVector3f1, localManifoldPoint.normalWorldOnB);
/*  643 */             localSolverConstraint2.relpos2CrossNormal.cross(localVector3f2, localManifoldPoint.normalWorldOnB);
/*      */ 
/*  645 */             if (localRigidBody1 != null) {
/*  646 */               localRigidBody1.getVelocityInLocalPoint(localVector3f1, localVector3f8);
/*      */             }
/*      */             else {
/*  649 */               localVector3f8.set(0.0F, 0.0F, 0.0F);
/*      */             }
/*      */ 
/*  652 */             if (localRigidBody2 != null) {
/*  653 */               localRigidBody2.getVelocityInLocalPoint(localVector3f2, localVector3f9);
/*      */             }
/*      */             else {
/*  656 */               localVector3f9.set(0.0F, 0.0F, 0.0F);
/*      */             }
/*      */ 
/*  659 */             localVector3f5.sub(localVector3f8, localVector3f9);
/*      */ 
/*  661 */             f1 = localManifoldPoint.normalWorldOnB.dot(localVector3f5);
/*      */ 
/*  663 */             localSolverConstraint2.penetration = Math.min(localManifoldPoint.getDistance() + paramContactSolverInfo.linearSlop, 0.0F);
/*      */ 
/*  666 */             localSolverConstraint2.friction = localManifoldPoint.combinedFriction;
/*  667 */             localSolverConstraint2.restitution = restitutionCurve(f1, localManifoldPoint.combinedRestitution);
/*  668 */             if (localSolverConstraint2.restitution <= 0.0F) {
/*  669 */               localSolverConstraint2.restitution = 0.0F;
/*      */             }
/*      */ 
/*  672 */             f2 = -localSolverConstraint2.penetration / paramContactSolverInfo.timeStep;
/*      */ 
/*  674 */             if (localSolverConstraint2.restitution > f2) {
/*  675 */               localSolverConstraint2.penetration = 0.0F;
/*      */             }
/*      */ 
/*  678 */             Vector3f localVector3f11 = this.v.tmp4;
/*      */ 
/*  681 */             if ((paramContactSolverInfo.solverMode & 0x4) != 0) {
/*  682 */               localSolverConstraint2.appliedImpulse = (localManifoldPoint.appliedImpulse * paramContactSolverInfo.warmstartingFactor);
/*  683 */               if (localRigidBody1 != null) {
/*  684 */                 localVector3f11.scale(localRigidBody1.getInvMass(), localSolverConstraint2.contactNormal);
/*  685 */                 ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdA)).internalApplyImpulse(localVector3f11, localSolverConstraint2.angularComponentA, localSolverConstraint2.appliedImpulse);
/*      */               }
/*  687 */               if (localRigidBody2 != null) {
/*  688 */                 localVector3f11.scale(localRigidBody2.getInvMass(), localSolverConstraint2.contactNormal);
/*  689 */                 ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdB)).internalApplyImpulse(localVector3f11, localSolverConstraint2.angularComponentB, -localSolverConstraint2.appliedImpulse);
/*      */               }
/*      */             }
/*      */             else {
/*  693 */               localSolverConstraint2.appliedImpulse = 0.0F;
/*      */             }
/*      */ 
/*  696 */             localSolverConstraint2.appliedPushImpulse = 0.0F;
/*      */ 
/*  698 */             localSolverConstraint2.frictionIndex = this.tmpSolverFrictionConstraintPool.size();
/*  699 */             if (!localManifoldPoint.lateralFrictionInitialized) {
/*  700 */               localManifoldPoint.lateralFrictionDir1.scale(f1, localManifoldPoint.normalWorldOnB);
/*  701 */               localManifoldPoint.lateralFrictionDir1.sub(localVector3f5, localManifoldPoint.lateralFrictionDir1);
/*      */ 
/*  704 */               if ((
/*  704 */                 f1 = localManifoldPoint.lateralFrictionDir1.lengthSquared()) > 
/*  704 */                 1.192093E-007F)
/*      */               {
/*  706 */                 localManifoldPoint.lateralFrictionDir1.scale(1.0F / (float)Math.sqrt(f1));
/*  707 */                 addFrictionConstraint(localManifoldPoint.lateralFrictionDir1, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/*  708 */                 localManifoldPoint.lateralFrictionDir2.cross(localManifoldPoint.lateralFrictionDir1, localManifoldPoint.normalWorldOnB);
/*  709 */                 localManifoldPoint.lateralFrictionDir2.normalize();
/*  710 */                 addFrictionConstraint(localManifoldPoint.lateralFrictionDir2, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/*      */               }
/*      */               else
/*      */               {
/*  715 */                 TransformUtil.planeSpace1(localManifoldPoint.normalWorldOnB, localManifoldPoint.lateralFrictionDir1, localManifoldPoint.lateralFrictionDir2);
/*  716 */                 addFrictionConstraint(localManifoldPoint.lateralFrictionDir1, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/*  717 */                 addFrictionConstraint(localManifoldPoint.lateralFrictionDir2, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/*      */               }
/*  719 */               localManifoldPoint.lateralFrictionInitialized = true;
/*      */             }
/*      */             else
/*      */             {
/*  723 */               addFrictionConstraint(localManifoldPoint.lateralFrictionDir1, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/*  724 */               addFrictionConstraint(localManifoldPoint.lateralFrictionDir2, m, n, i2, localManifoldPoint, localVector3f1, localVector3f2, paramInt1, paramIDebugDraw, 1.0F);
/*      */             }
/*      */ 
/*  728 */             SolverConstraint localSolverConstraint1 = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(localSolverConstraint2.frictionIndex);
/*  729 */             if ((paramContactSolverInfo.solverMode & 0x4) != 0) {
/*  730 */               localSolverConstraint1.appliedImpulse = (localManifoldPoint.appliedImpulseLateral1 * paramContactSolverInfo.warmstartingFactor);
/*  731 */               if (localRigidBody1 != null) {
/*  732 */                 localVector3f11.scale(localRigidBody1.getInvMass(), localSolverConstraint1.contactNormal);
/*  733 */                 ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdA)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentA, localSolverConstraint1.appliedImpulse);
/*      */               }
/*  735 */               if (localRigidBody2 != null) {
/*  736 */                 localVector3f11.scale(localRigidBody2.getInvMass(), localSolverConstraint1.contactNormal);
/*  737 */                 ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdB)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentB, -localSolverConstraint1.appliedImpulse);
/*      */               }
/*      */             }
/*      */             else {
/*  741 */               localSolverConstraint1.appliedImpulse = 0.0F;
/*      */             }
/*      */ 
/*  745 */             localSolverConstraint1 = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(localSolverConstraint2.frictionIndex + 1);
/*  746 */             if ((paramContactSolverInfo.solverMode & 0x4) != 0) {
/*  747 */               localSolverConstraint1.appliedImpulse = (localManifoldPoint.appliedImpulseLateral2 * paramContactSolverInfo.warmstartingFactor);
/*  748 */               if (localRigidBody1 != null) {
/*  749 */                 localVector3f11.scale(localRigidBody1.getInvMass(), localSolverConstraint1.contactNormal);
/*  750 */                 ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdA)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentA, localSolverConstraint1.appliedImpulse);
/*      */               }
/*  752 */               if (localRigidBody2 != null) {
/*  753 */                 localVector3f11.scale(localRigidBody2.getInvMass(), localSolverConstraint1.contactNormal);
/*  754 */                 ((SolverBody)this.tmpSolverBodyPool.getQuick(localSolverConstraint2.solverBodyIdB)).internalApplyImpulse(localVector3f11, localSolverConstraint1.angularComponentB, -localSolverConstraint1.appliedImpulse);
/*      */               }
/*      */             }
/*      */             else {
/*  758 */               localSolverConstraint1.appliedImpulse = 0.0F;
/*      */             }
/*      */ 
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  773 */       for (i = 0; i < paramInt5; i++) {
/*  774 */         ((TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + i))
/*  775 */           .buildJacobian();
/*      */       }
/*      */ 
/*  781 */       i = this.tmpSolverConstraintPool.size();
/*  782 */       int j = this.tmpSolverFrictionConstraintPool.size();
/*      */ 
/*  785 */       MiscUtil.resize(this.orderTmpConstraintPool, i, 0);
/*  786 */       MiscUtil.resize(this.orderFrictionConstraintPool, j, 0);
/*      */ 
/*  789 */       for (int k = 0; k < i; k++) {
/*  790 */         this.orderTmpConstraintPool.set(k, k);
/*      */       }
/*  792 */       for (k = 0; k < j; k++) {
/*  793 */         this.orderFrictionConstraintPool.set(k, k);
/*      */       }
/*      */ 
/*  797 */       return 0.0F; } finally { BulletStats.popProfile(); } throw paramObjectArrayList1;
/*      */   }
/*      */ 
/*      */   public float solveGroupCacheFriendlyIterations(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw)
/*      */   {
/*  805 */     BulletStats.pushProfile("solveGroupCacheFriendlyIterations");
/*      */     try {
/*  807 */       paramObjectArrayList1 = this.tmpSolverConstraintPool.size();
/*  808 */       paramInt1 = this.tmpSolverFrictionConstraintPool.size();
/*      */ 
/*  813 */       for (paramObjectArrayList2 = 0; paramObjectArrayList2 < paramContactSolverInfo.numIterations; paramObjectArrayList2++)
/*      */       {
/*  816 */         if (((paramContactSolverInfo.solverMode & 0x1) != 0) && 
/*  817 */           ((paramObjectArrayList2 & 0x7) == 0)) {
/*  818 */           for (paramInt2 = 0; paramInt2 < paramObjectArrayList1; paramInt2++) {
/*  819 */             paramInt3 = this.orderTmpConstraintPool.get(paramInt2);
/*  820 */             paramIDebugDraw = randInt2(paramInt2 + 1);
/*  821 */             this.orderTmpConstraintPool.set(paramInt2, this.orderTmpConstraintPool.get(paramIDebugDraw));
/*  822 */             this.orderTmpConstraintPool.set(paramIDebugDraw, paramInt3);
/*      */           }
/*      */ 
/*  825 */           for (paramInt2 = 0; paramInt2 < paramInt1; paramInt2++) {
/*  826 */             paramInt3 = this.orderFrictionConstraintPool.get(paramInt2);
/*  827 */             paramIDebugDraw = randInt2(paramInt2 + 1);
/*  828 */             this.orderFrictionConstraintPool.set(paramInt2, this.orderFrictionConstraintPool.get(paramIDebugDraw));
/*  829 */             this.orderFrictionConstraintPool.set(paramIDebugDraw, paramInt3);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  834 */         for (paramInt2 = 0; paramInt2 < paramInt5; paramInt2++)
/*      */         {
/*  838 */           if (((
/*  838 */             paramInt3 = (TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + paramInt2))
/*  838 */             .getRigidBodyA().getIslandTag() >= 0) && (paramInt3.getRigidBodyA().getCompanionId() >= 0)) {
/*  839 */             ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyA().getCompanionId())).writebackVelocity();
/*      */           }
/*  841 */           if ((paramInt3.getRigidBodyB().getIslandTag() >= 0) && (paramInt3.getRigidBodyB().getCompanionId() >= 0)) {
/*  842 */             ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyB().getCompanionId())).writebackVelocity();
/*      */           }
/*      */ 
/*  845 */           paramInt3.solveConstraint(paramContactSolverInfo.timeStep);
/*      */ 
/*  847 */           if ((paramInt3.getRigidBodyA().getIslandTag() >= 0) && (paramInt3.getRigidBodyA().getCompanionId() >= 0)) {
/*  848 */             ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyA().getCompanionId())).readVelocity();
/*      */           }
/*  850 */           if ((paramInt3.getRigidBodyB().getIslandTag() >= 0) && (paramInt3.getRigidBodyB().getCompanionId() >= 0)) {
/*  851 */             ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt3.getRigidBodyB().getCompanionId())).readVelocity();
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*  856 */         paramInt3 = this.tmpSolverConstraintPool.size();
/*  857 */         for (paramInt2 = 0; paramInt2 < paramInt3; paramInt2++) {
/*  858 */           paramIDebugDraw = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(this.orderTmpConstraintPool.get(paramInt2));
/*      */ 
/*  860 */           resolveSingleCollisionCombinedCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdB), paramIDebugDraw, paramContactSolverInfo);
/*      */         }
/*      */ 
/*  866 */         paramInt3 = this.tmpSolverFrictionConstraintPool.size();
/*      */ 
/*  868 */         for (paramInt2 = 0; paramInt2 < paramInt3; paramInt2++) {
/*  869 */           paramIDebugDraw = (SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(this.orderFrictionConstraintPool.get(paramInt2));
/*      */ 
/*  871 */           float f = ((SolverConstraint)this.tmpSolverConstraintPool.getQuick(paramIDebugDraw.frictionIndex)).appliedImpulse + ((SolverConstraint)this.tmpSolverConstraintPool.getQuick(paramIDebugDraw.frictionIndex)).appliedPushImpulse;
/*      */ 
/*  874 */           resolveSingleFrictionCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdB), paramIDebugDraw, paramContactSolverInfo, f);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  881 */       if (paramContactSolverInfo.splitImpulse) {
/*  882 */         for (paramObjectArrayList2 = 0; paramObjectArrayList2 < paramContactSolverInfo.numIterations; paramObjectArrayList2++)
/*      */         {
/*  884 */           paramInt2 = this.tmpSolverConstraintPool.size();
/*      */ 
/*  886 */           for (paramInt3 = 0; paramInt3 < paramInt2; paramInt3++) {
/*  887 */             paramIDebugDraw = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(this.orderTmpConstraintPool.get(paramInt3));
/*      */ 
/*  889 */             resolveSplitPenetrationImpulseCacheFriendly((SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdA), (SolverBody)this.tmpSolverBodyPool.getQuick(paramIDebugDraw.solverBodyIdB), paramIDebugDraw, paramContactSolverInfo);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  897 */       return 0.0F; } finally { BulletStats.popProfile(); } throw paramObjectArrayList1;
/*      */   }
/*      */ 
/*      */   public float solveGroupCacheFriendly(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw)
/*      */   {
/*  914 */     solveGroupCacheFriendlySetup(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, paramObjectArrayList3, paramInt4, paramInt5, paramContactSolverInfo, paramIDebugDraw);
/*  915 */     solveGroupCacheFriendlyIterations(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, paramObjectArrayList3, paramInt4, paramInt5, paramContactSolverInfo, paramIDebugDraw);
/*      */ 
/*  917 */     paramObjectArrayList1 = this.tmpSolverConstraintPool.size();
/*  918 */     for (paramInt1 = 0; paramInt1 < paramObjectArrayList1; paramInt1++)
/*      */     {
/*  921 */       paramInt2 = (ManifoldPoint)(
/*  921 */         paramObjectArrayList2 = (SolverConstraint)this.tmpSolverConstraintPool.getQuick(paramInt1)).originalContactPoint;
/*      */ 
/*  922 */       assert (paramInt2 != null);
/*  923 */       paramInt2.appliedImpulse = paramObjectArrayList2.appliedImpulse;
/*  924 */       paramInt2.appliedImpulseLateral1 = ((SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(paramObjectArrayList2.frictionIndex)).appliedImpulse;
/*  925 */       paramInt2.appliedImpulseLateral1 = ((SolverConstraint)this.tmpSolverFrictionConstraintPool.getQuick(paramObjectArrayList2.frictionIndex + 1)).appliedImpulse;
/*      */     }
/*      */ 
/*  930 */     if (paramContactSolverInfo.splitImpulse) {
/*  931 */       for (paramInt1 = 0; paramInt1 < this.tmpSolverBodyPool.size(); paramInt1++) {
/*  932 */         ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt1)).writebackVelocity(paramContactSolverInfo.timeStep);
/*  933 */         this.bodiesPool.release(this.tmpSolverBodyPool.getQuick(paramInt1));
/*      */       }
/*      */     }
/*      */     else {
/*  937 */       for (paramInt1 = 0; paramInt1 < this.tmpSolverBodyPool.size(); paramInt1++) {
/*  938 */         ((SolverBody)this.tmpSolverBodyPool.getQuick(paramInt1)).writebackVelocity();
/*  939 */         this.bodiesPool.release(this.tmpSolverBodyPool.getQuick(paramInt1));
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  954 */     this.tmpSolverBodyPool.clear();
/*      */ 
/*  956 */     for (paramInt1 = 0; paramInt1 < this.tmpSolverConstraintPool.size(); paramInt1++) {
/*  957 */       this.constraintsPool.release(this.tmpSolverConstraintPool.getQuick(paramInt1));
/*      */     }
/*  959 */     this.tmpSolverConstraintPool.clear();
/*      */ 
/*  961 */     for (paramInt1 = 0; paramInt1 < this.tmpSolverFrictionConstraintPool.size(); paramInt1++) {
/*  962 */       this.constraintsPool.release(this.tmpSolverFrictionConstraintPool.getQuick(paramInt1));
/*      */     }
/*  964 */     this.tmpSolverFrictionConstraintPool.clear();
/*      */ 
/*  966 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   public float solveGroup(ObjectArrayList paramObjectArrayList1, int paramInt1, ObjectArrayList paramObjectArrayList2, int paramInt2, int paramInt3, ObjectArrayList paramObjectArrayList3, int paramInt4, int paramInt5, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw, Dispatcher paramDispatcher)
/*      */   {
/*  974 */     BulletStats.pushProfile("solveGroup");
/*      */     try
/*      */     {
/*  977 */       if ((paramContactSolverInfo.solverMode & 0x8) != 0)
/*      */       {
/*  980 */         assert (paramObjectArrayList1 != null);
/*  981 */         assert (paramInt1 != 0);
/*  982 */         paramObjectArrayList1 = solveGroupCacheFriendly(paramObjectArrayList1, paramInt1, paramObjectArrayList2, paramInt2, paramInt3, paramObjectArrayList3, paramInt4, paramInt5, paramContactSolverInfo, paramIDebugDraw);
/*  983 */         return paramObjectArrayList1;
/*      */       }
/*      */ 
/*  986 */       paramObjectArrayList1 = new ContactSolverInfo(paramContactSolverInfo);
/*      */ 
/*  988 */       paramInt1 = paramContactSolverInfo.numIterations;
/*      */ 
/*  990 */       paramDispatcher = 0;
/*      */       int k;
/*      */       int m;
/*  993 */       for (int i = 0; i < paramInt3; i = (short)(i + 1)) {
/*  994 */         PersistentManifold localPersistentManifold1 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + i);
/*  995 */         prepareConstraints(localPersistentManifold1, paramObjectArrayList1, paramIDebugDraw);
/*      */ 
/*  997 */         for (k = 0; k < ((PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + i)).getNumContacts(); k = (short)(k + 1)) {
/*  998 */           this.gOrder[(paramDispatcher << 1)] = i;
/*  999 */           this.gOrder[((paramDispatcher << 1) + 1)] = k;
/*      */ 
/* 1002 */           paramDispatcher += 2;
/* 1003 */           if (paramDispatcher >= this.gOrder.length) {
/* 1004 */             m = this.gOrder.length;
/* 1005 */             this.gOrder = Arrays.copyOf(this.gOrder, m << 1);
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1013 */       for (i = 0; i < paramInt5; i++) {
/* 1014 */         ((TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + i))
/* 1015 */           .buildJacobian();
/*      */       }
/*      */ 
/* 1022 */       for (i = 0; i < paramInt1; i++)
/*      */       {
/* 1024 */         if (((paramContactSolverInfo.solverMode & 0x1) != 0) && 
/* 1025 */           ((i & 0x7) == 0)) {
/* 1026 */           for (Dispatcher localDispatcher = 0; localDispatcher < paramDispatcher; localDispatcher += 2)
/*      */           {
/* 1028 */             k = this.gOrder[localDispatcher];
/* 1029 */             m = this.gOrder[(localDispatcher + 1)];
/* 1030 */             paramInt3 = randInt2(localDispatcher / 2 + 1);
/* 1031 */             this.gOrder[localDispatcher] = this.gOrder[paramInt3];
/* 1032 */             this.gOrder[(localDispatcher + 1)] = this.gOrder[(paramInt3 + 1)];
/*      */ 
/* 1034 */             this.gOrder[paramInt3] = k;
/* 1035 */             this.gOrder[(paramInt3 + 1)] = m;
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/* 1040 */         for (int j = 0; j < paramInt5; j++)
/* 1041 */           ((TypedConstraint)paramObjectArrayList3.getQuick(paramInt4 + j))
/* 1042 */             .solveConstraint(paramObjectArrayList1.timeStep);
/*      */         PersistentManifold localPersistentManifold2;
/* 1045 */         for (j = 0; j < paramDispatcher; j += 2) {
/* 1046 */           localPersistentManifold2 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + this.gOrder[j]);
/* 1047 */           solve((RigidBody)localPersistentManifold2.getBody0(), (RigidBody)localPersistentManifold2.getBody1(), localPersistentManifold2.getContactPoint(this.gOrder[(j + 1)]), paramObjectArrayList1, i, paramIDebugDraw);
/*      */         }
/*      */ 
/* 1051 */         for (j = 0; j < paramDispatcher; j += 2) {
/* 1052 */           localPersistentManifold2 = (PersistentManifold)paramObjectArrayList2.getQuick(paramInt2 + this.gOrder[j]);
/* 1053 */           solveFriction((RigidBody)localPersistentManifold2.getBody0(), (RigidBody)localPersistentManifold2.getBody1(), localPersistentManifold2.getContactPoint(this.gOrder[(j + 1)]), paramObjectArrayList1, i, paramIDebugDraw);
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1060 */       return 0.0F; } finally { BulletStats.popProfile(); } throw paramObjectArrayList1;
/*      */   }
/*      */ 
/*      */   protected void prepareConstraints(PersistentManifold paramPersistentManifold, ContactSolverInfo paramContactSolverInfo, IDebugDraw paramIDebugDraw)
/*      */   {
/* 1068 */     paramIDebugDraw = (RigidBody)paramPersistentManifold.getBody0();
/* 1069 */     RigidBody localRigidBody = (RigidBody)paramPersistentManifold.getBody1();
/*      */ 
/* 1076 */     int i = paramPersistentManifold.getNumContacts();
/*      */ 
/* 1078 */     BulletStats.gTotalContactPoints += i;
/*      */ 
/* 1080 */     Vector3f localVector3f1 = this.v.tmpVecB;
/* 1081 */     Matrix3f localMatrix3f1 = this.v.tmpMat3B;
/*      */ 
/* 1083 */     Vector3f localVector3f2 = this.v.pos1B;
/* 1084 */     Vector3f localVector3f3 = this.v.pos2B;
/* 1085 */     Vector3f localVector3f4 = this.v.rel_pos1B;
/* 1086 */     Vector3f localVector3f5 = this.v.rel_pos2B;
/* 1087 */     Vector3f localVector3f6 = this.v.vel1B;
/* 1088 */     Vector3f localVector3f7 = this.v.vel2B;
/* 1089 */     Vector3f localVector3f8 = this.v.velB;
/* 1090 */     Vector3f localVector3f9 = this.v.totalImpulseB;
/* 1091 */     Vector3f localVector3f10 = this.v.torqueAxis0B;
/* 1092 */     Vector3f localVector3f11 = this.v.torqueAxis1B;
/* 1093 */     Vector3f localVector3f12 = this.v.ftorqueAxis0B;
/* 1094 */     Vector3f localVector3f13 = this.v.ftorqueAxis1B;
/*      */ 
/* 1096 */     for (int j = 0; j < i; j++)
/*      */     {
/*      */       ManifoldPoint localManifoldPoint;
/* 1098 */       if ((
/* 1098 */         localManifoldPoint = paramPersistentManifold.getContactPoint(j))
/* 1098 */         .getDistance() <= 0.0F) {
/* 1099 */         localManifoldPoint.getPositionWorldOnA(localVector3f2);
/* 1100 */         localManifoldPoint.getPositionWorldOnB(localVector3f3);
/*      */ 
/* 1102 */         localVector3f4.sub(localVector3f2, paramIDebugDraw.getCenterOfMassPosition(localVector3f1));
/* 1103 */         localVector3f5.sub(localVector3f3, localRigidBody.getCenterOfMassPosition(localVector3f1));
/*      */         Matrix3f localMatrix3f2;
/* 1106 */         (
/* 1107 */           localMatrix3f2 = paramIDebugDraw.getCenterOfMassTransform(this.v.com0).basis)
/* 1107 */           .transpose();
/*      */         Object localObject;
/* 1109 */         (
/* 1110 */           localObject = localRigidBody.getCenterOfMassTransform(this.v.com1).basis)
/* 1110 */           .transpose();
/*      */         JacobianEntry localJacobianEntry;
/* 1112 */         (
/* 1113 */           localJacobianEntry = (JacobianEntry)this.jacobiansPool.get())
/* 1113 */           .init(localMatrix3f2, (Matrix3f)localObject, localVector3f4, localVector3f5, localManifoldPoint.normalWorldOnB, paramIDebugDraw.getInvInertiaDiagLocal(this.v.in0), paramIDebugDraw.getInvMass(), localRigidBody.getInvInertiaDiagLocal(this.v.in1), localRigidBody.getInvMass());
/*      */ 
/* 1118 */         float f1 = localJacobianEntry.getDiagonal();
/* 1119 */         this.jacobiansPool.release(localJacobianEntry);
/*      */ 
/* 1122 */         if ((
/* 1122 */           localObject = (ConstraintPersistentData)localManifoldPoint.userPersistentData) != null)
/*      */         {
/* 1124 */           localObject.persistentLifeTime += 1;
/* 1125 */           if (((ConstraintPersistentData)localObject).persistentLifeTime != localManifoldPoint.getLifeTime())
/*      */           {
/* 1128 */             ((ConstraintPersistentData)localObject).reset();
/* 1129 */             ((ConstraintPersistentData)localObject).persistentLifeTime = localManifoldPoint.getLifeTime();
/*      */           }
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 1140 */           localObject = new ConstraintPersistentData();
/*      */ 
/* 1143 */           this.totalCpd += 1;
/*      */ 
/* 1145 */           localManifoldPoint.userPersistentData = localObject;
/* 1146 */           ((ConstraintPersistentData)localObject).persistentLifeTime = localManifoldPoint.getLifeTime();
/*      */         }
/*      */ 
/* 1149 */         assert (localObject != null);
/*      */ 
/* 1151 */         ((ConstraintPersistentData)localObject).jacDiagABInv = (1.0F / f1);
/*      */ 
/* 1156 */         ((ConstraintPersistentData)localObject).frictionSolverFunc = this.frictionDispatch[paramIDebugDraw.frictionSolverType][localRigidBody.frictionSolverType];
/* 1157 */         ((ConstraintPersistentData)localObject).contactSolverFunc = this.contactDispatch[paramIDebugDraw.contactSolverType][localRigidBody.contactSolverType];
/*      */ 
/* 1159 */         paramIDebugDraw.getVelocityInLocalPoint(localVector3f4, localVector3f6);
/* 1160 */         localRigidBody.getVelocityInLocalPoint(localVector3f5, localVector3f7);
/* 1161 */         localVector3f8.sub(localVector3f6, localVector3f7);
/*      */ 
/* 1164 */         f1 = localManifoldPoint.normalWorldOnB.dot(localVector3f8);
/*      */ 
/* 1166 */         float f2 = localManifoldPoint.combinedRestitution;
/*      */ 
/* 1168 */         ((ConstraintPersistentData)localObject).penetration = localManifoldPoint.getDistance();
/* 1169 */         ((ConstraintPersistentData)localObject).friction = localManifoldPoint.combinedFriction;
/* 1170 */         ((ConstraintPersistentData)localObject).restitution = restitutionCurve(f1, f2);
/* 1171 */         if (((ConstraintPersistentData)localObject).restitution <= 0.0F) {
/* 1172 */           ((ConstraintPersistentData)localObject).restitution = 0.0F;
/*      */         }
/*      */ 
/* 1178 */         f1 = -((ConstraintPersistentData)localObject).penetration / paramContactSolverInfo.timeStep;
/*      */ 
/* 1180 */         if (((ConstraintPersistentData)localObject).restitution > f1) {
/* 1181 */           ((ConstraintPersistentData)localObject).penetration = 0.0F;
/*      */         }
/*      */ 
/* 1184 */         f1 = paramContactSolverInfo.damping;
/* 1185 */         if ((paramContactSolverInfo.solverMode & 0x4) != 0) {
/* 1186 */           localObject.appliedImpulse *= f1;
/*      */         }
/*      */         else {
/* 1189 */           ((ConstraintPersistentData)localObject).appliedImpulse = 0.0F;
/*      */         }
/*      */ 
/* 1193 */         ((ConstraintPersistentData)localObject).prevAppliedImpulse = ((ConstraintPersistentData)localObject).appliedImpulse;
/*      */ 
/* 1196 */         TransformUtil.planeSpace1(localManifoldPoint.normalWorldOnB, ((ConstraintPersistentData)localObject).frictionWorldTangential0, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
/*      */ 
/* 1200 */         ((ConstraintPersistentData)localObject).accumulatedTangentImpulse0 = 0.0F;
/* 1201 */         ((ConstraintPersistentData)localObject).accumulatedTangentImpulse1 = 0.0F;
/*      */ 
/* 1203 */         f2 = paramIDebugDraw.computeImpulseDenominator(localVector3f2, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
/* 1204 */         float f3 = localRigidBody.computeImpulseDenominator(localVector3f3, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
/* 1205 */         f2 = f1 / (f2 + f3);
/* 1206 */         ((ConstraintPersistentData)localObject).jacDiagABInvTangent0 = f2;
/*      */ 
/* 1208 */         f2 = paramIDebugDraw.computeImpulseDenominator(localVector3f2, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
/* 1209 */         f3 = localRigidBody.computeImpulseDenominator(localVector3f3, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
/* 1210 */         f2 = f1 / (f2 + f3);
/* 1211 */         ((ConstraintPersistentData)localObject).jacDiagABInvTangent1 = f2;
/*      */ 
/* 1219 */         localVector3f9.scale(((ConstraintPersistentData)localObject).appliedImpulse, localManifoldPoint.normalWorldOnB);
/*      */ 
/* 1223 */         localVector3f10.cross(localVector3f4, localManifoldPoint.normalWorldOnB);
/*      */ 
/* 1225 */         ((ConstraintPersistentData)localObject).angularComponentA.set(localVector3f10);
/* 1226 */         paramIDebugDraw.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).angularComponentA);
/*      */ 
/* 1228 */         localVector3f11.cross(localVector3f5, localManifoldPoint.normalWorldOnB);
/*      */ 
/* 1230 */         ((ConstraintPersistentData)localObject).angularComponentB.set(localVector3f11);
/* 1231 */         localRigidBody.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).angularComponentB);
/*      */ 
/* 1234 */         localVector3f12.cross(localVector3f4, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
/*      */ 
/* 1236 */         ((ConstraintPersistentData)localObject).frictionAngularComponent0A.set(localVector3f12);
/* 1237 */         paramIDebugDraw.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent0A);
/*      */ 
/* 1240 */         localVector3f13.cross(localVector3f4, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
/*      */ 
/* 1242 */         ((ConstraintPersistentData)localObject).frictionAngularComponent1A.set(localVector3f13);
/* 1243 */         paramIDebugDraw.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent1A);
/*      */ 
/* 1246 */         localVector3f12.cross(localVector3f5, ((ConstraintPersistentData)localObject).frictionWorldTangential0);
/*      */ 
/* 1248 */         ((ConstraintPersistentData)localObject).frictionAngularComponent0B.set(localVector3f12);
/* 1249 */         localRigidBody.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent0B);
/*      */ 
/* 1252 */         localVector3f13.cross(localVector3f5, ((ConstraintPersistentData)localObject).frictionWorldTangential1);
/*      */ 
/* 1254 */         ((ConstraintPersistentData)localObject).frictionAngularComponent1B.set(localVector3f13);
/* 1255 */         localRigidBody.getInvInertiaTensorWorld(localMatrix3f1).transform(((ConstraintPersistentData)localObject).frictionAngularComponent1B);
/*      */ 
/* 1261 */         paramIDebugDraw.applyImpulse(localVector3f9, localVector3f4);
/*      */ 
/* 1263 */         localVector3f1.negate(localVector3f9);
/* 1264 */         localRigidBody.applyImpulse(localVector3f1, localVector3f5);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public float solveCombinedContactFriction(RigidBody paramRigidBody1, RigidBody paramRigidBody2, ManifoldPoint paramManifoldPoint, ContactSolverInfo paramContactSolverInfo, int paramInt, IDebugDraw paramIDebugDraw)
/*      */   {
/* 1272 */     paramInt = 0.0F;
/*      */ 
/* 1275 */     if (paramManifoldPoint.getDistance() <= 0.0F)
/*      */     {
/* 1278 */       paramRigidBody1 = ContactConstraint.resolveSingleCollisionCombined(paramRigidBody1, paramRigidBody2, paramManifoldPoint, paramContactSolverInfo);
/*      */ 
/* 1280 */       if (0.0F < paramRigidBody1) {
/* 1281 */         paramInt = paramRigidBody1;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1286 */     return paramInt;
/*      */   }
/*      */ 
/*      */   protected float solve(RigidBody paramRigidBody1, RigidBody paramRigidBody2, ManifoldPoint paramManifoldPoint, ContactSolverInfo paramContactSolverInfo, int paramInt, IDebugDraw paramIDebugDraw) {
/* 1290 */     paramInt = 0.0F;
/*      */ 
/* 1293 */     if (paramManifoldPoint.getDistance() <= 0.0F)
/*      */     {
/* 1296 */       paramRigidBody1 = ((ConstraintPersistentData)paramManifoldPoint.userPersistentData).contactSolverFunc
/* 1296 */         .resolveContact(paramRigidBody1, paramRigidBody2, paramManifoldPoint, paramContactSolverInfo);
/*      */ 
/* 1298 */       if (0.0F < paramRigidBody1) {
/* 1299 */         paramInt = paramRigidBody1;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1305 */     return paramInt;
/*      */   }
/*      */ 
/*      */   protected float solveFriction(RigidBody paramRigidBody1, RigidBody paramRigidBody2, ManifoldPoint paramManifoldPoint, ContactSolverInfo paramContactSolverInfo, int paramInt, IDebugDraw paramIDebugDraw)
/*      */   {
/* 1310 */     if (paramManifoldPoint.getDistance() <= 0.0F) {
/* 1311 */       ((ConstraintPersistentData)paramManifoldPoint.userPersistentData).frictionSolverFunc
/* 1312 */         .resolveContact(paramRigidBody1, paramRigidBody2, paramManifoldPoint, paramContactSolverInfo);
/*      */     }
/*      */ 
/* 1315 */     return 0.0F;
/*      */   }
/*      */ 
/*      */   public void reset()
/*      */   {
/* 1320 */     this.btSeed2 = 0L;
/*      */   }
/*      */ 
/*      */   public void setContactSolverFunc(ContactSolverFunc paramContactSolverFunc, int paramInt1, int paramInt2)
/*      */   {
/* 1328 */     this.contactDispatch[paramInt1][paramInt2] = paramContactSolverFunc;
/*      */   }
/*      */ 
/*      */   public void setFrictionSolverFunc(ContactSolverFunc paramContactSolverFunc, int paramInt1, int paramInt2)
/*      */   {
/* 1336 */     this.frictionDispatch[paramInt1][paramInt2] = paramContactSolverFunc;
/*      */   }
/*      */ 
/*      */   public void setRandSeed(long paramLong) {
/* 1340 */     this.btSeed2 = paramLong;
/*      */   }
/*      */ 
/*      */   public long getRandSeed() {
/* 1344 */     return this.btSeed2;
/*      */   }
/*      */ }

/* Location:           C:\Users\Raul\Desktop\StarMade\StarMade.jar
 * Qualified Name:     org.schema.game.common.data.physics.SequentialImpulseConstraintSolverExt
 * JD-Core Version:    0.6.2
 */